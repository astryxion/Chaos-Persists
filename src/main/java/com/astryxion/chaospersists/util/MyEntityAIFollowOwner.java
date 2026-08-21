package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Girlfriend;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;

/**
 * OreSpawn 1.7.10 follow: walk when a path exists, teleport only if pathing fails and the owner is
 * 12+ blocks away. Always-teleport-at-12 made pets blink instead of run.
 */
public class MyEntityAIFollowOwner extends Goal {
    private final TamableAnimal thePet;
    private LivingEntity theOwner;
    private final Level theWorld;
    private final float followSpeed;
    private final PathNavigation petPathfinder;
    private int repathDelay;
    private final float maxDist;
    private final float minDist;

    public MyEntityAIFollowOwner(TamableAnimal par1EntityTameable, float par2, float par3, float par4) {
        this.thePet = par1EntityTameable;
        this.theWorld = par1EntityTameable.level();
        this.followSpeed = par2;
        this.petPathfinder = par1EntityTameable.getNavigation();
        this.minDist = par4;
        this.maxDist = par3;
        this.setFlags(java.util.EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    private boolean isPetStaying() {
        return this.thePet.isInSittingPose() || this.thePet.isOrderedToSit();
    }

    @Override
    public boolean canUse() {
        LivingEntity owner = this.thePet.getOwner();
        if (owner == null) {
            return false;
        }
        this.theOwner = owner;
        if (this.isPetStaying()) {
            return false;
        }
        if (MyUtils.isPrinceFlying(this.thePet)) {
            return false;
        }
        if (Girlfriend.class.isInstance(this.thePet) && ChaosPersists.valentines_day != 0) {
            return false;
        }
        double distSq = this.thePet.distanceToSqr(this.theOwner);
        if (!(this.thePet.getY() >= 60.0 && MyUtils.isDay(this.theWorld)
                || distSq <= (double) (this.maxDist / 2.0f * (this.maxDist / 2.0f)))) {
            return true;
        }
        return distSq >= (double) (this.maxDist * this.maxDist);
    }

    @Override
    public boolean canContinueToUse() {
        if (this.isPetStaying() || MyUtils.isPrinceFlying(this.thePet)) {
            return false;
        }
        LivingEntity owner = this.thePet.getOwner();
        if (owner == null) {
            return false;
        }
        this.theOwner = owner;
        if ((int) this.thePet.getZ() == (int) owner.getZ()
                && (int) this.thePet.getX() == (int) owner.getX()
                && (int) this.thePet.getY() < (int) owner.getY() + 2
                && (int) this.thePet.getY() > (int) owner.getY() - 2) {
            return false;
        }
        return this.thePet.distanceToSqr(this.theOwner) > (double) (this.minDist * this.minDist);
    }

    @Override
    public void start() {
        this.repathDelay = 0;
        this.petPathfinder.setCanFloat(true);
        if (this.petPathfinder instanceof GroundPathNavigation ground) {
            ground.setCanOpenDoors(true);
            ground.setCanPassDoors(true);
        }
    }

    @Override
    public void stop() {
        this.theOwner = null;
        this.petPathfinder.stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        MyUtils.setChaseTarget(this.thePet, this.theOwner);
        if (this.isPetStaying() || this.theOwner == null) {
            return;
        }
        if (this.theOwner.level() != this.thePet.level()) {
            if (RoyalPetFollowHelper.isRoyalPet(this.thePet)) {
                RoyalPetFollowHelper.tryFollowTeleport(this.thePet, this.theOwner);
            } else {
                RoyalPetFollowHelper.teleportToOwnerOnGround(this.thePet, this.theOwner);
            }
            return;
        }
        if (--this.repathDelay > 0) {
            return;
        }
        this.repathDelay = 10;
        boolean reached = this.petPathfinder.moveTo(this.theOwner, (double) this.followSpeed);
        if (!reached
                && this.thePet.distanceToSqr(this.theOwner) >= 144.0
                && !MyUtils.shouldPrinceSkipFollowTeleport(this.thePet, this.theOwner)) {
            RoyalPetFollowHelper.teleportToOwnerOnGround(this.thePet, this.theOwner);
        }
    }
}
