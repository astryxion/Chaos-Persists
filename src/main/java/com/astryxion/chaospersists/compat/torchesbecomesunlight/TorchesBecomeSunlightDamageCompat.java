package com.astryxion.chaospersists.compat.torchesbecomesunlight;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBossTrueDamage;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * Gathering Torches Become Sunlight Gun Knight Patriot phase two slams with
 * {@code rosmontislib:real_damage} ({@code bypasses_armor}) via
 * {@code FFEntityUtils.callBaseHurt} → {@code IMobPartner.invokeHurt} →
 * {@code LivingEntity.hurt}. That skips {@code Godzilla.hurt}'s 750 / 120 caps.
 * Same 1.7.10 boss curve Cataclysm / EEEAB already use.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class TorchesBecomeSunlightDamageCompat {

    public static final String TORCHES_BECOME_SUNLIGHT_MODID = "torchesbecomesunlight";
    public static final String ROSMONTISLIB_MODID = "rosmontislib";

    private static boolean checked;
    private static boolean present;

    private TorchesBecomeSunlightDamageCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(TORCHES_BECOME_SUNLIGHT_MODID);
            checked = true;
        }
        return present;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!isPresent() || event.isCanceled() || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        DamageSource source = event.getSource();
        if (victim == null || victim.level() == null || victim.level().isClientSide || source == null) {
            return;
        }
        if (!ChaosBosses.isBoss(victim) || !isTorchesBecomeSunlightDamage(source) || !bypassesDefense(source)) {
            return;
        }
        event.setAmount(ChaosBossTrueDamage.applyDefense(victim, event.getAmount()));
    }

    private static boolean isTorchesBecomeSunlightDamage(DamageSource source) {
        if (isTorchesBecomeSunlightEntity(source.getEntity())
                || isTorchesBecomeSunlightEntity(source.getDirectEntity())) {
            return true;
        }
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> TORCHES_BECOME_SUNLIGHT_MODID.equals(id.getNamespace())
                        || ROSMONTISLIB_MODID.equals(id.getNamespace()))
                .orElse(false);
    }

    private static boolean isTorchesBecomeSunlightEntity(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof PartEntity<?> part) {
            entity = part.getParent();
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && TORCHES_BECOME_SUNLIGHT_MODID.equals(id.getNamespace());
    }

    private static boolean bypassesDefense(DamageSource source) {
        return source.is(DamageTypeTags.BYPASSES_ARMOR)
                || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.INDIRECT_MAGIC);
    }
}
