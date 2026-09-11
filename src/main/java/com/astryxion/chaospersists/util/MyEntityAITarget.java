package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.compat.eeeabsmobs.EeeabsMobsCompat;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Mothra;
import java.util.EnumSet;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Node;

public abstract class MyEntityAITarget extends Goal {
    protected final Mob taskOwner;
    protected float targetDistance;
    protected boolean shouldCheckSight;
    private boolean nearbyOnly;
    private int targetSearchStatus;
    private int targetSearchDelay;
    private int field_75298_g;

    public MyEntityAITarget(Mob par1EntityLiving, float par2, boolean par3) {
        this(par1EntityLiving, par2, par3, false);
    }

    public MyEntityAITarget(Mob par1EntityLiving, float par2, boolean par3, boolean par4) {
        this.taskOwner = par1EntityLiving;
        this.targetDistance = par2;
        this.shouldCheckSight = par3;
        this.nearbyOnly = par4;
        this.targetSearchStatus = 0;
        this.targetSearchDelay = 0;
        this.field_75298_g = 0;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity var1 = this.taskOwner.getTarget();
        if (var1 == null) {
            return false;
        }
        if (!MyUtils.isValidAggroTarget(var1)) {
            this.taskOwner.setTarget(null);
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this.taskOwner, var1)) {
            this.taskOwner.setTarget(null);
            return false;
        }
        if (!var1.isAlive()) {
            this.taskOwner.setTarget(null);
            return false;
        }
        if (this.taskOwner.distanceToSqr(var1) > (double) (this.targetDistance * this.targetDistance)) {
            return false;
        }
        if (this.taskOwner instanceof TamableAnimal tame
                && tame.isTame()
                && var1 instanceof TamableAnimal other
                && other.isTame()) {
            return false;
        }
        if (MyUtils.isProtectedCompanion(this.taskOwner, var1)) {
            this.taskOwner.setTarget(null);
            return false;
        }
        if (!MyUtils.isHostileMobTarget(var1) && !(var1 instanceof Creeper) && !(var1 instanceof Ghast)) {
            this.taskOwner.setTarget(null);
            return false;
        }
        if (this.shouldCheckSight) {
            if (this.taskOwner.hasLineOfSight(var1)) {
                this.field_75298_g = 0;
            } else if (++this.field_75298_g > 60) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void start() {
        this.targetSearchStatus = 0;
        this.targetSearchDelay = 0;
        this.field_75298_g = 0;
    }

    @Override
    public void stop() {
        this.taskOwner.setTarget(null);
    }

    protected boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this.taskOwner) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this.taskOwner, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (this.taskOwner instanceof TamableAnimal tame && tame.isTame()) {
            if (par1EntityLiving instanceof TamableAnimal other && other.isTame()) {
                return false;
            }
            if (par1EntityLiving == tame.getOwner()) {
                return false;
            }
        }
        if (MyUtils.isProtectedCompanion(this.taskOwner, par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player) {
            if (ChaosPersists.valentines_day != 0) {
                return true;
            }
            return false;
        }
        if (par1EntityLiving instanceof ZombifiedPiglin) {
            return false;
        }
        if (par1EntityLiving instanceof EnderMan) {
            return false;
        }
        if (par1EntityLiving instanceof Mothra) {
            return true;
        }
        if (this.shouldCheckSight && !this.taskOwner.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Creeper) {
            return true;
        }
        if (par1EntityLiving instanceof Ghast) {
            return true;
        }
        if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
                this.targetSearchStatus = 0;
            }
            if (this.targetSearchStatus == 0) {
                this.targetSearchStatus = this.canEasilyReach(par1EntityLiving) ? 1 : 2;
            }
            if (this.targetSearchStatus == 2 && !EeeabsMobsCompat.skipNearbyOnlyReachCheck(par1EntityLiving)) {
                return false;
            }
        }
        return MyUtils.isHostileMobTarget(par1EntityLiving);
    }

    private boolean canEasilyReach(LivingEntity par1EntityLiving) {
        this.targetSearchDelay = 10 + this.taskOwner.getRandom().nextInt(5);
        Path var2 = this.taskOwner.getNavigation().createPath(par1EntityLiving, 0);
        if (var2 == null) {
            return false;
        }
        Node var3 = var2.getEndNode();
        if (var3 == null) {
            return false;
        }
        int var4 = var3.x - Mth.floor(par1EntityLiving.getX());
        int var5 = var3.z - Mth.floor(par1EntityLiving.getZ());
        return (double) (var4 * var4 + var5 * var5) <= 2.25;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
