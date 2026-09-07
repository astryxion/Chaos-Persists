package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.EntityCannonFodder;
import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MyEntityAIAvoidEntity extends Goal {
    private final PathfinderMob theEntity;
    private final double farSpeed;
    private final double nearSpeed;
    private Entity closestLivingEntity;
    private final float distanceFromEntity;
    private net.minecraft.world.level.pathfinder.Path entityPath;
    private final PathNavigation entityPathNavigate;
    private final Class<?> targetEntityClass;

    public MyEntityAIAvoidEntity(
            PathfinderMob par1EntityCreature, Class<?> par2Class, float par3, double par4, double par6) {
        this.theEntity = par1EntityCreature;
        this.targetEntityClass = par2Class;
        this.distanceFromEntity = par3;
        this.farSpeed = par4;
        this.nearSpeed = par6;
        this.entityPathNavigate = par1EntityCreature.getNavigation();
        this.setFlags(java.util.EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.theEntity instanceof EntityCannonFodder cf && cf.get_is_activated() != 0) {
            return false;
        }
        if (this.theEntity instanceof TamableAnimal tamable
                && (tamable.isOrderedToSit() || tamable.isInSittingPose())) {
            return false;
        }
        if (this.targetEntityClass == Player.class) {
            if (this.theEntity instanceof TamableAnimal tamable && tamable.isTame()) {
                return false;
            }
            this.closestLivingEntity =
                    this.theEntity.level().getNearestPlayer(this.theEntity, (double) this.distanceFromEntity);
            if (this.closestLivingEntity == null) {
                return false;
            }
        } else {
            AABB box =
                    this.theEntity
                            .getBoundingBox()
                            .inflate((double) this.distanceFromEntity, 3.0, (double) this.distanceFromEntity);
            List<Monster> list = this.theEntity.level().getEntitiesOfClass(Monster.class, box);
            if (list.isEmpty()) {
                return false;
            }
            this.closestLivingEntity = list.get(0);
        }
        Vec3 fleeFrom = this.closestLivingEntity.position();
        Vec3 vec = DefaultRandomPos.getPos(this.theEntity, 16, 7);
        if (vec == null) {
            return false;
        }
        if (this.closestLivingEntity.distanceToSqr(vec) < this.closestLivingEntity.distanceToSqr(this.theEntity)) {
            return false;
        }
        this.entityPath = this.entityPathNavigate.createPath(vec.x, vec.y, vec.z, 0);
        return this.entityPath != null && this.entityPath.getNodeCount() > 0;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.theEntity instanceof TamableAnimal tamable
                && (tamable.isOrderedToSit() || tamable.isInSittingPose())) {
            return false;
        }
        return !this.entityPathNavigate.isDone();
    }

    @Override
    public void start() {
        this.entityPathNavigate.moveTo(this.entityPath, this.farSpeed);
    }

    @Override
    public void stop() {
        this.closestLivingEntity = null;
    }

    @Override
    public void tick() {
        if (this.theEntity.distanceToSqr(this.closestLivingEntity) < 49.0) {
            this.theEntity.getNavigation().setSpeedModifier(this.nearSpeed);
        } else {
            this.theEntity.getNavigation().setSpeedModifier(this.farSpeed);
        }
    }
}
