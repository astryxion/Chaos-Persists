package com.astryxion.chaospersists.entity;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;

public class SunspotUrchin extends ThrowableEntity {
    private float my_rotation = 0.0f;
    private int my_index = 50;

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, World par1World) {
        super(type, par1World);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, World par1World, int par2) {
        super(type, par1World);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, World par1World, LivingEntity par2Mob) {
        super(type, par2Mob, par1World);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, World par1World, LivingEntity par2Mob, int par3) {
        super(type, par2Mob, par1World);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, World par1World, double par2, double par4, double par6) {
        super(type, par2, par4, par6, par1World);
    }

    @Override
    protected void defineSynchedData() {
    }

    public int getUrchinIndex() {
        return this.my_index;
    }

    @Override
    protected void onHit(RayTraceResult par1MovingObjectPosition) {
        super.onHit(par1MovingObjectPosition);
        if (par1MovingObjectPosition.getType() == RayTraceResult.Type.ENTITY) {
            Entity entityHit = ((EntityRayTraceResult) par1MovingObjectPosition).getEntity();
            float var2 = 3.0f;
            if (entityHit instanceof CreeperEntity) {
                var2 = 6.0f;
            }
            if (!(entityHit instanceof PlayerEntity)) {
                entityHit.hurt(DamageSource.thrown(this, this.getOwner()), var2);
                if (entityHit instanceof LivingEntity && !((LivingEntity) entityHit).fireImmune()) {
                    entityHit.setSecondsOnFire(5);
                }
            }
        } else if (par1MovingObjectPosition.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockHit = (BlockRayTraceResult) par1MovingObjectPosition;
            int i = blockHit.getBlockPos().getX();
            int j = blockHit.getBlockPos().getY();
            int k = blockHit.getBlockPos().getZ();
            Direction sideHit = blockHit.getDirection();
            switch (sideHit) {
                case DOWN:
                    --j;
                    break;
                case UP:
                    ++j;
                    break;
                case NORTH:
                    --k;
                    break;
                case SOUTH:
                    ++k;
                    break;
                case WEST:
                    --i;
                    break;
                case EAST:
                    ++i;
                    break;
                default:
                    break;
            }
            BlockPos firePos = new BlockPos(i, j, k);
            if (this.level.isEmptyBlock(firePos)) {
                this.level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());
            }
        }
        for (int var3 = 0; var3 < 5; ++var3) {
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), (double) this.level.random.nextFloat(), (double) this.level.random.nextFloat(), (double) this.level.random.nextFloat());
            this.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), this.getX(), this.getY(), this.getZ(), (double) this.level.random.nextFloat(), (double) this.level.random.nextFloat(), (double) this.level.random.nextFloat());
        }
        if (!this.level.isClientSide) {
            this.remove();
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setSecondsOnFire(1);
        this.my_rotation += 30.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.xRot = this.xRotO = this.my_rotation;
        this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
    }
}
