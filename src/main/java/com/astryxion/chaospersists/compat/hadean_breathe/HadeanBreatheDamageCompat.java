package com.astryxion.chaospersists.compat.hadean_breathe;

import net.neoforged.fml.common.EventBusSubscriber;
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
import net.neoforged.neoforge.entity.PartEntity;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;

/**
 * Hadean Breathe Enforcer true-damage is {@code hadean_breathe:hadean_fire} ({@code bypasses_armor}
 * + {@code bypasses_enchantments}). Fireballs / sprints / the Hadean Fire effect call
 * {@code LivingEntity.hurt}, which skips OreSpawn {@code getArmorValue()}. Smashers also use
 * Vzling {@code EntityUtil.forceHurt} → {@code setHealth}, which skips {@code TheKing.hurt}
 * entirely. Same 1.7.10 boss curve Cataclysm / EEEAB already use.
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class HadeanBreatheDamageCompat {

    public static final String HADEAN_BREATHE_MODID = "hadean_breathe";

    private static boolean checked;
    private static boolean present;

    private HadeanBreatheDamageCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(HADEAN_BREATHE_MODID);
            checked = true;
        }
        return present;
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingIncomingDamageEvent event) {
        if (!isPresent() || event.isCanceled() || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        DamageSource source = event.getSource();
        if (victim == null || victim.level() == null || victim.level().isClientSide || source == null) {
            return;
        }
        if (!ChaosBosses.isBoss(victim) || !isHadeanDamage(source) || !bypassesDefense(source)) {
            return;
        }
        event.setAmount(ChaosBossTrueDamage.applyDefense(victim, event.getAmount()));
    }

    private static boolean isHadeanDamage(DamageSource source) {
        if (isHadeanEntity(source.getEntity()) || isHadeanEntity(source.getDirectEntity())) {
            return true;
        }
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> HADEAN_BREATHE_MODID.equals(id.getNamespace()))
                .orElse(false);
    }

    private static boolean isHadeanEntity(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof PartEntity<?> part) {
            entity = part.getParent();
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && HADEAN_BREATHE_MODID.equals(id.getNamespace());
    }

    private static boolean bypassesDefense(DamageSource source) {
        return source.is(DamageTypeTags.BYPASSES_ARMOR)
                || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.INDIRECT_MAGIC);
    }
}
