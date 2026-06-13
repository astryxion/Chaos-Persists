package com.astryxion.chaospersists.util;

import java.util.EnumSet;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.vector.Vector3d;

public class MyEntityAIWander extends Goal {
    private final CreatureEntity entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private final float speed;

    public MyEntityAIWander(CreatureEntity par1EntityCreature, float par2) {
        this.entity = par1EntityCreature;
        this.speed = par2;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.entity.getRandom().nextInt(90) != 0) {
            return false;
        }
        if (this.entity instanceof TameableEntity && ((TameableEntity) this.entity).isOrderedToSit()) {
            return false;
        }
        net.minecraft.util.math.vector.Vector3d var1 = RandomPositionGenerator.getLandPos(this.entity, 10, 7);
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
        if (this.entity instanceof TameableEntity) {
            TameableEntity gf = (TameableEntity) this.entity;
            LivingEntity var1 = gf.getOwner();
            if (var1 != null
                    && (int) gf.getZ() == (int) var1.getZ()
                    && (int) gf.getX() == (int) var1.getX()
                    && (int) gf.getY() < (int) var1.getY() + 2
                    && (int) gf.getY() > (int) var1.getY() - 2) {
                return false;
            }
        }
        return !this.entity.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}
