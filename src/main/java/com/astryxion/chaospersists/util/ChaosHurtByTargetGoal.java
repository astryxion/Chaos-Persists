package com.astryxion.chaospersists.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;

/**
 * Retaliation goal that ignores creative/spectator players and royalty, matching OreSpawn
 * "royals do not fight royals" rules.
 */
public class ChaosHurtByTargetGoal extends HurtByTargetGoal {
    public ChaosHurtByTargetGoal(PathfinderMob mob, Class<?>... toIgnoreDamage) {
        super(mob, toIgnoreDamage);
    }

    @Override
    public boolean canUse() {
        LivingEntity attacker = this.mob.getLastHurtByMob();
        if (!MyUtils.isValidAggroTarget(attacker)
                || MyUtils.isRoyalty(attacker)
                || MyUtils.shouldSkipCombatTarget(this.mob, attacker)) {
            return false;
        }
        return super.canUse();
    }

    @Override
    protected void alertOther(Mob mob, LivingEntity target) {
        if (!MyUtils.isValidAggroTarget(target)
                || MyUtils.isRoyalty(target)
                || MyUtils.shouldSkipCombatTarget(mob, target)) {
            return;
        }
        super.alertOther(mob, target);
    }
}
