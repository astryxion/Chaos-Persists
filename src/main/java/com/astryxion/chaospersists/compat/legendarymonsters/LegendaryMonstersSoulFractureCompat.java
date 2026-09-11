package com.astryxion.chaospersists.compat.legendarymonsters;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Possessed Paladin applies {@code legendary_monsters:soul_fracture}, which stacks and shrinks
 * max health. Legendary Monsters bosses reject every {@code addEffect} ({@code IAnimatedBoss}
 * returns false). Chaos Persists bosses get the same immunity when that mod is loaded.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class LegendaryMonstersSoulFractureCompat {

    private LegendaryMonstersSoulFractureCompat() {}

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (!LegendaryMonstersBeamCompat.isPresent()) {
            return;
        }
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null || !isSoulFracture(instance.getEffect())) {
            return;
        }
        LivingEntity entity = event.getEntity();
        if (entity != null && ChaosBosses.isBoss(entity)) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!LegendaryMonstersBeamCompat.isPresent()
                || event.getEntity() == null
                || event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity entity = event.getEntity();
        if (entity.tickCount % 10 != 0 || !ChaosBosses.isBoss(entity)) {
            return;
        }
        stripSoulFracture(entity);
    }

    private static void stripSoulFracture(LivingEntity entity) {
        MobEffect toRemove = null;
        for (MobEffectInstance instance : entity.getActiveEffects()) {
            if (isSoulFracture(instance.getEffect())) {
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
        ResourceLocation id = ForgeRegistries.MOB_EFFECTS.getKey(effect);
        if (id == null
                || !LegendaryMonstersBeamCompat.LEGENDARY_MONSTERS_MODID.equals(id.getNamespace())) {
            return false;
        }
        String path = id.getPath().replace("_", "");
        return path.contains("soulfracture");
    }
}
