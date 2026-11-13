package com.astryxion.chaospersists.compat.legendarymonsters;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * Possessed Paladin applies {@code legendary_monsters:soul_fracture}, which stacks and shrinks
 * max health. Legendary Monsters bosses reject every {@code addEffect} ({@code IAnimatedBoss}
 * returns false). Chaos Persists bosses get the same immunity when that mod is loaded.
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class LegendaryMonstersSoulFractureCompat {

    private LegendaryMonstersSoulFractureCompat() {}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (!LegendaryMonstersBeamCompat.isPresent()) {
            return;
        }
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null || !isSoulFracture(instance.getEffect().value())) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity != null && ChaosBosses.isBoss(entity)) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof net.minecraft.world.entity.LivingEntity)) return;
        if (!LegendaryMonstersBeamCompat.isPresent()
                || event.getEntity() == null
                || event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity.tickCount % 10 != 0 || !ChaosBosses.isBoss(entity)) {
            return;
        }
        stripSoulFracture(entity);
    }

    private static void stripSoulFracture(LivingEntity entity) {
        net.minecraft.core.Holder<MobEffect> toRemove = null;
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            if (isSoulFracture(instance.getEffect().value())) {
                toRemove = instance.getEffect();
                break;
            }
        }
        if (toRemove != null) {
            entity.removeEffect(toRemove);
        }
    }

    private static boolean isSoulFracture(MobEffect effect) {
        if (effect == null) {
            return false;
        }
        ResourceLocation id = BuiltInRegistries.MOB_EFFECT.getKey(effect);
        if (id == null
                || !LegendaryMonstersBeamCompat.LEGENDARY_MONSTERS_MODID.equals(id.getNamespace())) {
            return false;
        }
        String path = id.getPath().replace("_", "");
        return path.contains("soulfracture");
    }
}
