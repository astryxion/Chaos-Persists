package com.astryxion.chaospersists.compat.legendarymonsters;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

/**
 * Legendary Monsters bosses (Possessed Paladin, Obliterator, and other {@code IAnimatedBoss}
 * types) do not use vanilla health for the real HP pool. {@code getHealth()} is {@code max -
 * totalDamageTaken}, {@code setHealth} is overwritten every tick, and {@code hurt} is capped
 * (~21). Queen beams that {@code setHealth} a percent slice get reverted, which looks like the
 * boss "resisted" the beam.
 *
 * <p>{@code addDamage} with {@code genericKill} writes {@code totalDamageTaken} and skips the
 * per-hit cap ({@code BYPASSES_INVULNERABILITY}).
 */
public final class LegendaryMonstersBeamCompat {

    public static final String LEGENDARY_MONSTERS_MODID = "legendary_monsters";

    private static boolean checked;
    private static boolean present;

    private LegendaryMonstersBeamCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(LEGENDARY_MONSTERS_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isLegendaryMonsters(@Nullable Entity entity) {
        if (!isPresent() || entity == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && LEGENDARY_MONSTERS_MODID.equals(id.getNamespace());
    }

    public static void enforceBeamHealth(@Nullable LivingEntity target, float intendedHealth) {
        if (!isLegendaryMonsters(target) || target.level().isClientSide || !target.isAlive()) {
            return;
        }
        float intended = Mth.clamp(intendedHealth, 0.0f, target.getMaxHealth());
        float current = target.getHealth();
        if (current <= intended + 0.05f) {
            return;
        }
        float lost = current - intended;
        DamageSource bypass = target.damageSources().genericKill();
        if (invokeAddDamage(target, lost, bypass)) {
            return;
        }
        if (addTotalDamageTaken(target, lost)) {
            return;
        }
        target.hurt(bypass, lost);
    }

    private static boolean invokeAddDamage(LivingEntity target, float amount, DamageSource source) {
        Class<?> type = target.getClass();
        while (type != null && type != Object.class) {
            try {
                Method method = type.getDeclaredMethod("addDamage", float.class, DamageSource.class);
                method.setAccessible(true);
                method.invoke(target, amount, source);
                return true;
            } catch (NoSuchMethodException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return false;
            }
        }
        return false;
    }

    private static boolean addTotalDamageTaken(LivingEntity target, float amount) {
        Class<?> type = target.getClass();
        while (type != null && type != Object.class) {
            try {
                Field field = type.getDeclaredField("totalDamageTaken");
                field.setAccessible(true);
                float previous = field.getFloat(target);
                field.setFloat(target, previous + amount);
                return true;
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return false;
            }
        }
        return false;
    }
}
