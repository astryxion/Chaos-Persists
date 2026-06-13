package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.EntityCannonFodder;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.math.vector.Vector3d;

public class MyEntityAIAvoidEntity extends Goal {
    private final CreatureEntity theEntity;
    private final double farSpeed;
    private final double nearSpeed;
    private Entity closestLivingEntity;
    private final float distanceFromEntity;
    private Path entityPath;
    private final PathNavigator entityPathNavigate;
    private final Class<?> targetEntityClass;

    public MyEntityAIAvoidEntity(CreatureEntity par1EntityCreature, Class<?> par2Class, float par3, double par4, double par6) {
        this.theEntity = par1EntityCreature;
        this.targetEntityClass = par2Class;
        this.distanceFromEntity = par3;
        this.farSpeed = par4;
        this.nearSpeed = par6;
        this.entityPathNavigate = par1EntityCreature.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.theEntity instanceof EntityCannonFodder) {
            EntityCannonFodder cf = (EntityCannonFodder) this.theEntity;
            if (cf.get_is_activated() != 0) {
                return false;
            }
        }
        if (this.targetEntityClass == PlayerEntity.class) {
            if (this.theEntity instanceof TameableEntity && ((TameableEntity) this.theEntity).isTame()) {
                return false;
            }
            this.closestLivingEntity = this.theEntity.level.getNearestPlayer(this.theEntity, (double)this.distanceFromEntity);
            if (this.closestLivingEntity == null) {
                return false;
            }
        } else {
            List<? extends Entity> list = this.theEntity.level.getEntitiesOfClass(
                    (Class<? extends Entity>) this.targetEntityClass,
                    this.theEntity.getBoundingBox().inflate(this.distanceFromEntity, 3.0D, this.distanceFromEntity),
                    (Entity e) -> e instanceof MonsterEntity);
            if (list.isEmpty()) {
                return false;
            }
            this.closestLivingEntity = list.get(0);
        }
        net.minecraft.util.math.vector.Vector3d flee = RandomPositionGenerator.getLandPosAvoid(this.theEntity, 16, 7, this.closestLivingEntity.position());
        if (flee == null) {
            return false;
        }
        if (this.closestLivingEntity.distanceToSqr(flee.x, flee.y, flee.z)
                < this.closestLivingEntity.distanceToSqr(this.theEntity)) {
            return false;
        }
        this.entityPath = this.entityPathNavigate.createPath(new net.minecraft.util.math.BlockPos(flee), 0);
        if (this.entityPath == null) {
            return false;
        }
        PathPoint last = this.entityPath.getEndNode();
        return last != null
                && Math.abs(last.x + 0.5 - flee.x) < 0.5
                && Math.abs(last.y + 0.5 - flee.y) < 0.5
                && Math.abs(last.z + 0.5 - flee.z) < 0.5;
    }

    @Override
    public boolean canContinueToUse() {
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
        if (this.theEntity.distanceToSqr(this.closestLivingEntity) < 49.0D) {
            this.entityPathNavigate.moveTo(this.entityPath, this.nearSpeed);
        } else {
            this.entityPathNavigate.moveTo(this.entityPath, this.farSpeed);
        }
    }

    static CreatureEntity func_98217_a(MyEntityAIAvoidEntity par0EntityAIAvoidEntity) {
        return par0EntityAIAvoidEntity.theEntity;
    }
}
