package com.astryxion.chaospersists.compat.eeeabsmobs;

import java.lang.reflect.Field;
import javax.annotation.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

/**
 * Queen / King purple beams {@code setHealth} a large percent slice, then {@code hurt}. The Immortal
 * intercepts {@code setHealth} through damage adaptation and caps {@code hurt} at 30, so the same
 * beam that deletes Mobzilla barely scratches it.
 *
 * <p>{@code genericKill} is already in EEEAB's {@code bypasses_damage_cap} / {@code bypasses_damage_adapt}
 * tags, so a follow-up hit with that type applies the intended remaining loss. Direct health-id write
 * is only a fallback (unleash-energy still shrinks even bypass hits).
 */
public final class EeeabsMobsBeamCompat {

    private static EntityDataAccessor<Float> healthId;
    private static boolean healthIdResolved;

    private EeeabsMobsBeamCompat() {}

    public static void enforceBeamHealth(@Nullable LivingEntity target, float intendedHealth) {
        if (!EeeabsMobsCompat.isCombatMob(target) || target.level().isClientSide) {
            return;
        }
        float intended = Mth.clamp(intendedHealth, 0.0f, target.getMaxHealth());
        if (target.getHealth() <= intended + 0.05f) {
            return;
        }
        float lost = target.getHealth() - intended;
        target.hurt(target.damageSources().genericKill(), lost);
        if (target.getHealth() <= intended + 0.05f) {
            return;
        }
        setHealthDirect(target, intended);
        if (intended <= 0.0f && target.isAlive()) {
            target.hurt(target.damageSources().genericKill(), Float.MAX_VALUE);
        }
    }

    @SuppressWarnings("unchecked")
    private static void setHealthDirect(LivingEntity target, float health) {
        EntityDataAccessor<Float> accessor = healthAccessor();
        if (accessor == null) {
            return;
        }
        target.getEntityData().set(accessor, Mth.clamp(health, 0.0f, target.getMaxHealth()));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private static EntityDataAccessor<Float> healthAccessor() {
        if (healthIdResolved) {
            return healthId;
        }
        healthIdResolved = true;
        for (String name : new String[] {"DATA_HEALTH_ID", "f_20961_"}) {
            try {
                Field field = LivingEntity.class.getDeclaredField(name);
                field.setAccessible(true);
                healthId = (EntityDataAccessor<Float>) field.get(null);
                return healthId;
            } catch (ReflectiveOperationException ignored) {
            }
        }
        return null;
    }
}
