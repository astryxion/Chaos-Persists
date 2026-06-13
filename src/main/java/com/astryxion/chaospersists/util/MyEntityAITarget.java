package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;

public abstract class MyEntityAITarget extends Goal {
    protected MobEntity taskOwner;
    protected float targetDistance;
    protected boolean shouldCheckSight;
    private boolean nearbyOnly;
    private int targetSearchStatus = 0;
    private int targetSearchDelay = 0;
    private int field_75298_g = 0;

    public MyEntityAITarget(MobEntity par1LivingEntity, float par2, boolean par3) {
        this(par1LivingEntity, par2, par3, false);
    }

    public MyEntityAITarget(MobEntity par1LivingEntity, float par2, boolean par3, boolean par4) {
        this.taskOwner = par1LivingEntity;
        this.targetDistance = par2;
        this.shouldCheckSight = par3;
        this.nearbyOnly = par4;
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity var1 = this.taskOwner.getTarget();
        if (var1 == null) {
            return false;
        }
        if (!var1.isAlive()) {
            this.taskOwner.setTarget(null);
            return false;
        }
        if (this.taskOwner.distanceToSqr(var1) > (double) (this.targetDistance * this.targetDistance)) {
            return false;
        }
        if (this.taskOwner instanceof TameableEntity && ((TameableEntity) this.taskOwner).isTame()
                && var1 instanceof TameableEntity && ((TameableEntity) var1).isTame()) {
            return false;
        }
        if (this.shouldCheckSight) {
            if (this.taskOwner.getSensing().canSee(var1)) {
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

    protected boolean isSuitableTarget(LivingEntity par1LivingEntity, boolean par2) {
        if (par1LivingEntity == null) {
            return false;
        }
        if (par1LivingEntity == this.taskOwner) {
            return false;
        }
        if (!par1LivingEntity.isAlive()) {
            return false;
        }
        if (this.taskOwner instanceof TameableEntity && ((TameableEntity) this.taskOwner).isTame()) {
            if (par1LivingEntity instanceof TameableEntity && ((TameableEntity) par1LivingEntity).isTame()) {
                return false;
            }
            if (par1LivingEntity == ((TameableEntity) this.taskOwner).getOwner()) {
                return false;
            }
        }
        if (par1LivingEntity instanceof PlayerEntity) {
            if (ChaosPersists.valentines_day != 0) {
                return true;
            }
            return false;
        }
        if (par1LivingEntity instanceof ZombifiedPiglinEntity) {
            return false;
        }
        if (par1LivingEntity instanceof EndermanEntity) {
            return false;
        }
        if (par1LivingEntity instanceof Mothra) {
            return true;
        }
        if (this.shouldCheckSight && !this.taskOwner.getSensing().canSee(par1LivingEntity)) {
            return false;
        }
        if (par1LivingEntity instanceof CreeperEntity) {
            return true;
        }
          if (par1LivingEntity instanceof GhastEntity) {
            return true;
        }
        if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
                this.targetSearchStatus = 0;
            }
            if (this.targetSearchStatus == 0) {
                this.targetSearchStatus = this.canEasilyReach(par1LivingEntity) ? 1 : 2;
            }
            if (this.targetSearchStatus == 2) {
                return false;
            }
        }
        return true;
    }

    private boolean canEasilyReach(LivingEntity par1LivingEntity) {
        int var5;
        this.targetSearchDelay = 10 + this.taskOwner.getRandom().nextInt(5);
        Path var2 = this.taskOwner.getNavigation().createPath(par1LivingEntity, 0);
        if (var2 == null) {
            return false;
        }
        PathPoint var3 = var2.getEndNode();
        if (var3 == null) {
            return false;
        }
        int var4 = var3.x - MathHelper.floor(par1LivingEntity.getX());
        return (double) (var4 * var4 + (var5 = var3.z - MathHelper.floor(par1LivingEntity.getZ())) * var5) <= 2.25;
    }
}
