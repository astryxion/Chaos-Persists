package com.astryxion.chaospersists.util;

import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.scores.Team;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

/**
 * Chaos Persists mobs pick targets in custom AI instead of {@link Mob#canAttack}.
 * This keeps vanilla / Mob Battle scoreboard teams in force for those paths.
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class ScoreboardTeamCombatHandler {

    private ScoreboardTeamCombatHandler() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onTargetChange(LivingChangeTargetEvent event) {
        LivingEntity entity = event.getEntity();
        LivingEntity newTarget = event.getNewAboutToBeSetTarget();
        if (newTarget == null || !isChaosPersists(entity)) {
            return;
        }
        if (MyUtils.shouldSkipCombatTarget(entity, newTarget)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onAttack(LivingIncomingDamageEvent event) {
        if (event.getSource() == null || !(event.getSource().getEntity() instanceof LivingEntity attacker)) {
            return;
        }
        LivingEntity victim = event.getEntity();
        if (!isChaosPersists(attacker) || victim == null) {
            return;
        }
        if (!MyUtils.isScoreboardAlly(attacker, victim)) {
            return;
        }
        Team team = attacker.getTeam();
        if (team != null && team.isAllowFriendlyFire()) {
            return;
        }
        event.setCanceled(true);
    }

    private static boolean isChaosPersists(Entity entity) {
        if (entity == null) {
            return false;
        }
        ResourceLocation key = EntityType.getKey(entity.getType());
        return key != null && ChaosPersists.MODID.equals(key.getNamespace());
    }
}
