package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.core.ChaosPersists;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Cataclysm-style hits are {@code flat + maxHealth * percent}. Huge OreSpawn HP then becomes a
 * weakness. When the tweak is on, those slices are treated as if the mob had {@link #BASELINE} HP.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ChaosPercentHealthDamageGuard {
    /** Vanilla-ish HP used when converting a percent-of-max-health slice. */
    private static final float BASELINE = 100.0f;
    /** Largest flat weapon/skill number mixed into a percent hit (Ignis ~21, Guardian ~22). */
    private static final float FLAT_CAP = 64.0f;
    private static final float SLICE_EPS = 0.85f;

    private ChaosPercentHealthDamageGuard() {}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurt(LivingHurtEvent event) {
        if (event == null || event.isCanceled()) {
            return;
        }
        float next = rescale(event.getEntity(), event.getSource(), event.getAmount());
        if (next != event.getAmount()) {
            event.setAmount(next);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onDamage(LivingDamageEvent event) {
        if (event == null || event.isCanceled()) {
            return;
        }
        float next = rescale(event.getEntity(), event.getSource(), event.getAmount());
        if (next != event.getAmount()) {
            event.setAmount(next);
        }
    }

    private static float rescale(@Nullable LivingEntity victim, @Nullable DamageSource source, float amount) {
        if (ChaosPersists.IgnorePercentHealthDamage == 0 || amount <= 0.0f || victim == null) {
            return amount;
        }
        if (victim.level() == null || victim.level().isClientSide) {
            return amount;
        }
        if (!ChaosPackConfig.isChaosEntity(victim)) {
            return amount;
        }
        if (!shouldGuard(source)) {
            return amount;
        }
        float maxHp = victim.getMaxHealth();
        if (maxHp < 1.0f) {
            return amount;
        }
        if (amount >= maxHp * 0.95f) {
            return Math.min(amount, BASELINE);
        }
        for (int hundredths = 50; hundredths >= 1; hundredths--) {
            float slice = maxHp * (hundredths / 100.0f);
            float remainder = amount - slice;
            if (remainder < -SLICE_EPS || remainder > FLAT_CAP) {
                continue;
            }
            float converted = Math.max(0.0f, remainder) + BASELINE * (hundredths / 100.0f);
            if (converted < amount) {
                return converted;
            }
            return amount;
        }
        return amount;
    }

    private static boolean shouldGuard(@Nullable DamageSource source) {
        if (source == null) {
            return false;
        }
        Entity causing = root(source.getEntity());
        Entity direct = root(source.getDirectEntity());
        if (ChaosPackConfig.isChaosEntity(causing) || ChaosPackConfig.isChaosEntity(direct)) {
            return false;
        }
        if (isForeignMod(causing) || isForeignMod(direct) || isForeignDamageType(source)) {
            return true;
        }
        return source.is(DamageTypeTags.BYPASSES_ARMOR)
                || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.INDIRECT_MAGIC);
    }

    private static boolean isForeignMod(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        if (id == null) {
            return false;
        }
        String ns = id.getNamespace();
        return !"minecraft".equals(ns) && !ChaosPersists.MODID.equals(ns);
    }

    private static boolean isForeignDamageType(DamageSource source) {
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(ResourceLocation::getNamespace)
                .filter(ns -> !"minecraft".equals(ns) && !ChaosPersists.MODID.equals(ns))
                .isPresent();
    }

    @Nullable
    private static Entity root(@Nullable Entity entity) {
        if (entity instanceof PartEntity<?> part) {
            Entity parent = part.getParent();
            return parent != null ? parent : entity;
        }
        return entity;
    }
}
