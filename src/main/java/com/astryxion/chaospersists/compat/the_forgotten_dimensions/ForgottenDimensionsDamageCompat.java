package com.astryxion.chaospersists.compat.the_forgotten_dimensions;

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
 * Forgotten Dimensions elemental hits ({@code FireDamageProcedure} and the other element
 * procedures, plus {@code ElementalExecutionProcedure}) {@code hurt} then {@code setHealth} a
 * leftover slice. That second write skips {@code TheKing.hurt}'s 750 / 120 caps and hurt timer.
 * Same 1.7.10 boss curve Cataclysm / EEEAB already use.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgottenDimensionsDamageCompat {

    public static final String FORGOTTEN_DIMENSIONS_MODID = "the_forgotten_dimensions";

    private static boolean checked;
    private static boolean present;

    private ForgottenDimensionsDamageCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(FORGOTTEN_DIMENSIONS_MODID);
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
        if (!ChaosBosses.isBoss(victim) || !isForgottenDimensionsDamage(source) || !bypassesDefense(source)) {
            return;
        }
        event.setAmount(ChaosBossTrueDamage.applyDefense(victim, event.getAmount()));
    }

    private static boolean isForgottenDimensionsDamage(DamageSource source) {
        if (isForgottenDimensionsEntity(source.getEntity())
                || isForgottenDimensionsEntity(source.getDirectEntity())) {
            return true;
        }
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> FORGOTTEN_DIMENSIONS_MODID.equals(id.getNamespace()))
                .orElse(false);
    }

    private static boolean isForgottenDimensionsEntity(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof PartEntity<?> part) {
            entity = part.getParent();
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && FORGOTTEN_DIMENSIONS_MODID.equals(id.getNamespace());
    }

    private static boolean bypassesDefense(DamageSource source) {
        return source.is(DamageTypeTags.BYPASSES_ARMOR)
                || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.INDIRECT_MAGIC);
    }
}
