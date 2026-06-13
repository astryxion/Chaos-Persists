package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.EnumSet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.pathfinding.PathNavigator;

public class MyEntityAIFollowOwner extends Goal {
    private final TameableEntity thePet;
    private LivingEntity theOwner;
    private final World theWorld;
    private final float field_75336_f;
    private final PathNavigator petPathfinder;
    private int field_75343_h;
    private final float maxDist;
    private final float minDist;

    public MyEntityAIFollowOwner(TameableEntity par1TameableEntity, float par2, float par3, float par4) {
        this.thePet = par1TameableEntity;
        this.theWorld = par1TameableEntity.level;
        this.field_75336_f = par2;
        this.petPathfinder = par1TameableEntity.getNavigation();
        this.minDist = par4;
        this.maxDist = par3;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity var1 = this.thePet.getOwner();
        if (var1 == null) {
            return false;
        }
        this.theOwner = var1;
        if (this.thePet.isOrderedToSit()) {
            return false;
        }
        if (this.thePet instanceof Girlfriend && ChaosPersists.valentines_day != 0) {
            return false;
        }
        if (!(this.thePet.getY() >= 60.0 && this.theWorld.isDay()
                || this.thePet.distanceToSqr(var1) <= (double) (this.maxDist / 2.0f * (this.maxDist / 2.0f)))) {
            return true;
        }
        if (this.thePet.distanceToSqr(var1) < (double) (this.maxDist * this.maxDist)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.thePet.isOrderedToSit()) {
            return false;
        }
        if (this.petPathfinder.isDone()) {
            return false;
        }
        LivingEntity var1 = this.thePet.getOwner();
        if (var1 != null
                && (int) this.thePet.getZ() == (int) var1.getZ()
                && (int) this.thePet.getX() == (int) var1.getX()
                && (int) this.thePet.getY() < (int) var1.getY() + 2
                && (int) this.thePet.getY() > (int) var1.getY() - 2) {
            return false;
        }
        return this.thePet.distanceToSqr(this.theOwner) > (double) (this.minDist * this.minDist);
    }

    @Override
    public void start() {
        this.field_75343_h = 0;
    }

    @Override
    public void stop() {
        this.theOwner = null;
        this.petPathfinder.stop();
    }

    @Override
    public void tick() {
        this.thePet.getLookControl().setLookAt(this.theOwner, 10.0F, (float) this.thePet.getMaxHeadXRot());
        if (!this.thePet.isOrderedToSit() && --this.field_75343_h <= 0) {
            this.field_75343_h = 10;
            if (!this.petPathfinder.moveTo(this.theOwner, this.field_75336_f)
                    && this.thePet.distanceToSqr(this.theOwner) >= 144.0) {
                int var1 = MathHelper.floor(this.theOwner.getX()) - 2;
                int var2 = MathHelper.floor(this.theOwner.getZ()) - 2;
                int var3 = MathHelper.floor(this.theOwner.getBoundingBox().minY);
                for (int var4 = 0; var4 <= 4; ++var4) {
                    for (int var5 = 0; var5 <= 4; ++var5) {
                        BlockPos bp = new BlockPos(var1 + var4, var3, var2 + var5);
                        BlockPos bpDown = new BlockPos(var1 + var4, var3 - 1, var2 + var5);
                        if (var4 >= 1 && var5 >= 1 && var4 <= 3 && var5 <= 3) {
                            continue;
                        }
                        BlockState below = this.theWorld.getBlockState(bpDown);
                        BlockState here = this.theWorld.getBlockState(bp);
                        BlockPos above = new BlockPos(var1 + var4, var3 + 1, var2 + var5);
                        BlockState up = this.theWorld.getBlockState(above);
                        if (!below.isFaceSturdy(this.theWorld, bpDown, Direction.UP)
                                || !here.isAir()
                                || !up.isAir()) {
                            continue;
                        }
                        this.thePet.moveTo((float) (var1 + var4) + 0.5F, var3, (float) (var2 + var5) + 0.5F,
                                this.thePet.yRot, this.thePet.xRot);
                        this.petPathfinder.stop();
                        return;
                    }
                }
            }
        }
    }
}
