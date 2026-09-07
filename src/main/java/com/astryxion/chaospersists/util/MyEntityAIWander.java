package com.astryxion.chaospersists.util;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

public class MyEntityAIWander extends Goal {
    private final PathfinderMob entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private final float speed;

    public MyEntityAIWander(PathfinderMob par1EntityCreature, float par2) {
        this.entity = par1EntityCreature;
        this.speed = par2;
        this.setFlags(java.util.EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.entity.getRandom().nextInt(60) != 0) {
            return false;
        }
        if (this.entity instanceof TamableAnimal tamable
                && (tamable.isInSittingPose() || tamable.isOrderedToSit())) {
            return false;
        }
        Vec3 var1 = DefaultRandomPos.getPos(this.entity, 12, 10);
        if (var1 == null) {
            return false;
        }
        this.xPosition = var1.x;
        this.yPosition = var1.y;
        this.zPosition = var1.z;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.entity instanceof TamableAnimal tamable
                && (tamable.isInSittingPose() || tamable.isOrderedToSit())) {
            return false;
        }
        if (this.entity instanceof TamableAnimal tamable) {
            var owner = tamable.getOwner();
            if (owner != null
                    && (int) this.entity.getZ() == (int) owner.getZ()
                    && (int) this.entity.getX() == (int) owner.getX()
                    && (int) this.entity.getY() < (int) owner.getY() + 2
                    && (int) this.entity.getY() > (int) owner.getY() - 2) {
                return false;
            }
        }
        return !this.entity.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(this.xPosition, this.yPosition, this.zPosition, (double) this.speed);
    }
}
