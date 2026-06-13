/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.BetterFireball
 *  com.astryxion.chaospersists.Dragon
 *  com.astryxion.chaospersists.Godzilla
 *  com.astryxion.chaospersists.GodzillaHead
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.PitchBlack
 *  net.minecraft.block.Block
 *  net.minecraft.block.FireBlock
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.block.Blocks
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.GodzillaHead;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.entity.PitchBlack;
import java.util.List;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class BetterFireball
extends ThrowableEntity {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private boolean inGround = false;
    public LivingEntity shootingEntity;
    private int ticksAlive;
    private int ticksInAir = 0;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    public int field_92012_e = 1;
    private int notme = 0;
    private boolean small = false;

    @SuppressWarnings("unchecked")
    private static EntityType<? extends ThrowableEntity> fireballEntityType() {
        return (EntityType<? extends ThrowableEntity>)(EntityType<?>)EntityType.SMALL_FIREBALL;
    }

    public BetterFireball(World par1World) {
        super(fireballEntityType(), par1World);
        this.setFireballSize(1.0f, 1.0f);
    }

    @Override
    protected void defineSynchedData() {
    }

    public BetterFireball(World par1World, LivingEntity par2Mob, double par3, double par5, double par7) {
        super(fireballEntityType(), par1World);
        this.shootingEntity = par2Mob;
        this.setOwner(par2Mob);
        this.setFireballSize(1.0f, 1.0f);
        this.moveTo(par2Mob.getX(), par2Mob.getY(), par2Mob.getZ(), par2Mob.yRot, par2Mob.xRot);
        this.setPos(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 0.0);
        this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
        this.setDeltaMovement(0.0, this.getDeltaMovement().y, this.getDeltaMovement().z);
        double var9 = MathHelper.sqrt((double)(par3 * par3 + par5 * par5 + par7 * par7));
        this.accelerationX = par3 / var9 * 0.1;
        this.accelerationY = par5 / var9 * 0.1;
        this.accelerationZ = par7 / var9 * 0.1;
    }

    private void setFireballSize(float width, float height) {
        double w = (double)width;
        double h = (double)height;
        this.setBoundingBox(new AxisAlignedBB(-w * 0.5, 0.0, -w * 0.5, w * 0.5, h, w * 0.5));
    }

    public void setNotMe() {
        this.notme = 1;
    }

    public void setBig() {
        this.field_92012_e = 2;
    }

    public void setReallyBig() {
        this.field_92012_e = 4;
    }

    public void setSmall() {
        this.small = true;
        this.setFireballSize(0.3125f, 0.3125f);
    }

    private float getMotionFactor() {
        return 0.95f;
    }

    @Override
    public void tick() {
        Vector3d var15 = null;
        Vector3d var2 = null;
        RayTraceResult var3 = null;
        Entity var4 = null;
        List<Entity> var5 = null;
        double var6 = 0.0;
        Entity var9 = null;
        float var10 = 0.3f;
        double var13 = 0.0;
        float var16 = 0.0f;
        float var17 = 0.0f;
        float var18 = 0.0f;
        if (this.ticksAlive >= 600 || this.ticksInAir >= 600) {
            this.remove();
            return;
        }
        if (!this.level.isClientSide && (this.shootingEntity != null && !this.shootingEntity.isAlive() || !this.level.hasChunkAt(new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ())))) {
            this.remove();
        } else {
            super.tick();
            this.setSecondsOnFire(1);
            if (this.inGround) {
                Block var1 = this.level.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock();
                if (var1 != Blocks.AIR) {
                    ++this.ticksAlive;
                }
                this.inGround = false;
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, (double)(this.random.nextFloat() * 0.2f), (double)(this.random.nextFloat() * 0.2f), (double)(this.random.nextFloat() * 0.2f));
            } else {
                ++this.ticksInAir;
            }
            var15 = new Vector3d((double)this.getX(), (double)this.getY(), (double)this.getZ());
            var2 = new Vector3d((double)(this.getX() + this.getDeltaMovement().x), (double)(this.getY() + this.getDeltaMovement().y), (double)(this.getZ() + this.getDeltaMovement().z));
            BlockRayTraceResult blockHit = this.level.clip(new RayTraceContext(var15, var2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
            var3 = blockHit.getType() != RayTraceResult.Type.MISS ? blockHit : null;
            var15 = new Vector3d((double)this.getX(), (double)this.getY(), (double)this.getZ());
            var2 = new Vector3d((double)(this.getX() + this.getDeltaMovement().x), (double)(this.getY() + this.getDeltaMovement().y), (double)(this.getZ() + this.getDeltaMovement().z));
            if (var3 != null) {
                var2 = blockHit.getLocation();
            }
            var4 = null;
            var5 = this.level.getEntities((Entity)this, this.getBoundingBox().inflate(this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z).inflate(1.0, 1.0, 1.0));
            var6 = 0.0;
            for (int var8 = 0; var8 < var5.size(); ++var8) {
                AxisAlignedBB var11;
                Optional<Vector3d> hitOptional;
                var9 = var5.get(var8);
                if (this.shootingEntity == var9) {
                    var3 = null;
                    break;
                }
                if (var9 instanceof BetterFireball) {
                    var3 = null;
                    break;
                }
                if (var9 instanceof GodzillaHead) {
                    var3 = null;
                    break;
                }
                if (MyUtils.isRoyalty((Entity)var9)) {
                    var3 = null;
                    break;
                }
                if (this.notme != 0 && (var9 instanceof PlayerEntity || var9 instanceof Dragon || var9 instanceof Mothra)) {
                    var3 = null;
                    break;
                }
                if (!var9.isPickable() || var9 == this.shootingEntity && this.ticksInAir < 25 || !(hitOptional = (var11 = var9.getBoundingBox().inflate((double)var10, (double)var10, (double)var10)).clip(var15, var2)).isPresent() || (var13 = var15.distanceTo(hitOptional.get())) >= var6 && var6 != 0.0) continue;
                var4 = var9;
                var6 = var13;
            }
            if (var4 != null) {
                var3 = new EntityRayTraceResult(var4);
            }
            if (var3 != null) {
                this.onImpact(var3);
            }
            com.astryxion.chaospersists.util.MyUtils.addEntityX(this, this.getDeltaMovement().x);
            com.astryxion.chaospersists.util.MyUtils.addEntityY(this, this.getDeltaMovement().y);
            com.astryxion.chaospersists.util.MyUtils.addEntityZ(this, this.getDeltaMovement().z);
            var16 = MathHelper.sqrt((double)(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z));
            this.yRot = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) + 90.0f;
            this.xRot = (float)(Math.atan2(var16, this.getDeltaMovement().y) * 180.0 / 3.141592653589793) - 90.0f;
            while (this.xRot - this.xRotO < -180.0f) {
                this.xRotO -= 360.0f;
            }
            while (this.xRot - this.xRotO >= 180.0f) {
                this.xRotO += 360.0f;
            }
            while (this.yRot - this.yRotO < -180.0f) {
                this.yRotO -= 360.0f;
            }
            while (this.yRot - this.yRotO >= 180.0f) {
                this.yRotO += 360.0f;
            }
            this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2f;
            this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2f;
            var17 = this.getMotionFactor();
            if (this.isInWater()) {
                for (int var19 = 0; var19 < 4; ++var19) {
                    var18 = 0.25f;
                    this.level.addParticle(ParticleTypes.BUBBLE, this.getX() - this.getDeltaMovement().x * (double)var18, this.getY() - this.getDeltaMovement().y * (double)var18, this.getZ() - this.getDeltaMovement().z * (double)var18, this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                }
                var17 = 0.8f;
            }
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, this.accelerationX, this.accelerationY, this.accelerationZ);
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, (double)var17, (double)var17, (double)var17);
            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            this.setPos(this.getX(), this.getY(), this.getZ());
        }
    }

    protected void onImpact(RayTraceResult par1MovingObjectPosition) {
        if (!this.level.isClientSide) {
            if (par1MovingObjectPosition.getType() == RayTraceResult.Type.ENTITY) {
                Entity entityHit = ((EntityRayTraceResult)par1MovingObjectPosition).getEntity();
                if (entityHit instanceof BetterFireball) {
                    return;
                }
                if (entityHit instanceof Mothra) {
                    return;
                }
                if (this.notme != 0 && (entityHit instanceof Dragon || entityHit instanceof PlayerEntity)) {
                    this.remove();
                    return;
                }
                Entity e = entityHit;
                if (e instanceof MobEntity) {
                    MobEntity el = (MobEntity)e;
                    if (!(el.getBbWidth() * el.getBbHeight() <= 30.0f || MyUtils.isRoyalty((Entity)el) || el instanceof Godzilla || el instanceof GodzillaHead || el instanceof PitchBlack || el instanceof Kraken)) {
                        el.setHealth(el.getHealth() / 2.0f);
                    }
                }
                if (!this.small) {
                    entityHit.hurt(DamageSource.indirectMagic((Entity)this, (Entity)this.shootingEntity), 10.0f);
                    entityHit.setSecondsOnFire(5);
                } else {
                    entityHit.hurt(DamageSource.indirectMagic((Entity)this, (Entity)this.shootingEntity), 5.0f);
                    entityHit.setSecondsOnFire(5);
                }
            } else if (par1MovingObjectPosition.getType() == RayTraceResult.Type.BLOCK) {
                BlockRayTraceResult blockResult = (BlockRayTraceResult)par1MovingObjectPosition;
                int i = blockResult.getBlockPos().getX();
                int j = blockResult.getBlockPos().getY();
                int k = blockResult.getBlockPos().getZ();
                Direction side = blockResult.getDirection();
                if (side == Direction.DOWN) --j;
                else if (side == Direction.UP) ++j;
                else if (side == Direction.NORTH) --k;
                else if (side == Direction.SOUTH) ++k;
                else if (side == Direction.WEST) --i;
                else if (side == Direction.EAST) ++i;
                BlockPos firePos = new BlockPos(i, j, k);
                if (this.level.isEmptyBlock(firePos)) {
                    this.level.setBlock(firePos, Blocks.FIRE.defaultBlockState(), 3);
                }
            }
            if (!this.small) {
                this.level.explode((Entity)null, this.getX(), this.getY(), this.getZ(), (float)this.field_92012_e, this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            }
            this.remove();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("ExplosionPower", this.field_92012_e);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        if (par1CompoundNBT.contains("ExplosionPower")) {
            this.field_92012_e = par1CompoundNBT.getInt("ExplosionPower");
        }
    }
}
