package com.astryxion.chaospersists.compat.salmonsgenesisreincarnation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

/**
 * Salmon's Genesis Reincarnation (CurseForge: Nether Warlord Boss) bosses keep HP on a
 * {@code CUSTOM_HEALTH} accessor. {@code setHealth} ignores losses while {@code INV_TIMER} is
 * up, then clamps whatever remains: Nether Warlord 18, Salmon 2, Immortal Golem 100, Cosmic
 * Steve 1. Cosmic Steve also reports ~2e9 armor, so {@code hurt} after the beam slice is
 * soaked. Queen / King purple beams write a percent of current HP and never land.
 *
 * <p>{@code setBossHealth} writes {@code CUSTOM_HEALTH} without the cap or invuln gate.
 */
public final class SalmonsGenesisReincarnationBeamCompat {

    public static final String SALMONS_GENESIS_REINCARNATION_MODID = "salmonsgenesisreincarnation";

    private static boolean checked;
    private static boolean present;

    private SalmonsGenesisReincarnationBeamCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(SALMONS_GENESIS_REINCARNATION_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isSalmonsGenesisReincarnation(@Nullable Entity entity) {
        if (!isPresent() || entity == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && SALMONS_GENESIS_REINCARNATION_MODID.equals(id.getNamespace());
    }

    public static void enforceBeamHealth(@Nullable LivingEntity target, float intendedHealth) {
        if (!isSalmonsGenesisReincarnation(target) || target.level().isClientSide) {
            return;
        }
        float intended = Mth.clamp(intendedHealth, 0.0f, target.getMaxHealth());
        if (target.getHealth() <= intended + 0.05f) {
            return;
        }
        invokeSetInv(target, 0);
        if (invokeSetBossHealth(target, intended)) {
            return;
        }
        writeCustomHealth(target, intended);
    }

    private static boolean invokeSetBossHealth(LivingEntity target, float health) {
        Class<?> type = target.getClass();
        while (type != null && type != Object.class) {
            try {
                Method method = type.getDeclaredMethod("setBossHealth", float.class);
                method.setAccessible(true);
                method.invoke(target, health);
                return true;
            } catch (NoSuchMethodException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return false;
            }
        }
        return false;
    }

    private static void invokeSetInv(LivingEntity target, int ticks) {
        Class<?> type = target.getClass();
        while (type != null && type != Object.class) {
            try {
                Method method = type.getDeclaredMethod("setInv", int.class);
                method.setAccessible(true);
                method.invoke(target, ticks);
                return;
            } catch (NoSuchMethodException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static boolean writeCustomHealth(LivingEntity target, float health) {
        Class<?> type = target.getClass();
        while (type != null && type != Object.class) {
            try {
                Field field = type.getDeclaredField("CUSTOM_HEALTH");
                field.setAccessible(true);
                EntityDataAccessor<Float> accessor = (EntityDataAccessor<Float>) field.get(null);
                target.getEntityData().set(accessor, health);
                return true;
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException | ClassCastException ignored) {
                return false;
            }
        }
        return false;
    }
}
