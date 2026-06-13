package com.astryxion.chaospersists.util;

import java.util.EnumSet;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.Goal.Flag;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.vector.Vector3d;

public class MyEntityAIWanderALot extends Goal {
    private CreatureEntity entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private int xzRange = 10;
    private int busy = 0;

    public MyEntityAIWanderALot(CreatureEntity par1CreatureEntity, int par1, double par2) {
        this.entity = par1CreatureEntity;
        this.xzRange = par1;
        this.speed = par2;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public void setBusy(int i) {
        this.busy = i;
    }

    @Override
    public boolean canUse() {
        if (this.busy != 0) {
            return false;
        }
        if (this.entity.getRandom().nextInt(30) != 0) {
            return false;
        }
        if (this.entity instanceof TameableEntity && ((TameableEntity) this.entity).isOrderedToSit()) {
            return false;
        }
        Vector3d var1 = RandomPositionGenerator.getLandPos(this.entity, this.xzRange, 7);
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
        return !this.entity.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}
