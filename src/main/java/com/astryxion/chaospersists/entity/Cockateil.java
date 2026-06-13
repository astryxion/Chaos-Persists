/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Cockateil
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.AnimalEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class Cockateil
extends AnimalEntity {
    private static final DataParameter<Integer> BIRD_TYPE = EntityDataManager.defineId(Cockateil.class, DataSerializers.INT);
    private BlockPos currentFlightTarget = null;
    public int birdtype;
    private boolean killedByPlayerEntity = false;
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/bird1.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/bird2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/bird3.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/bird4.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/bird5.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/entity/bird6.png");
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private int flyup = 0;

    public Cockateil(EntityType<? extends Cockateil> type, World par1World) {
        super(type, par1World);
                this.xpReward = 2;
        
            }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.33000001311302185)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .build();
    }

    public ResourceLocation getTexture() {
        this.birdtype = this.getBirdType();
        switch (this.birdtype) {
            case 0: {
                return texture1;
            }
            case 1: {
                return texture2;
            }
            case 2: {
                return texture3;
            }
            case 3: {
                return texture4;
            }
            case 4: {
                return texture5;
            }
            case 5: {
                return texture6;
            }
        }
        return null;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.birdtype = this.random.nextInt(6);
        this.entityData.define(BIRD_TYPE, this.birdtype);
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getBirdType() {
        return this.entityData.get(BIRD_TYPE).intValue();
    }

    public void setBirdType(int par1) {
        this.entityData.set(BIRD_TYPE, par1);
    }

    protected float getSoundVolume() {
        return 0.55f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.level.isDay() && !this.level.isRaining()) {
            return com.astryxion.chaospersists.core.ChaosSounds.BIRDS;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.DUCK_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.DUCK_HURT;
    }

    public boolean canBePushed() {
        return true;
    }

    public int mygetMaxHealth() {
        return 2;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof PlayerEntity) {
            this.killedByPlayerEntity = true;
        }
        return super.hurt(par1DamageSource, par2);
    }

    public void tick() {
        super.tick();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        } else {
            double my = this.getDeltaMovement().y;
            if (this.getY() < (double)this.currentFlightTarget.getY()) {
                my *= 0.7;
            } else {
                my *= 0.5;
            }
            this.setDeltaMovement(this.getDeltaMovement().x, my, this.getDeltaMovement().z);
        }
    }

    public int getAttackStrength(Entity par1Entity) {
        return 1;
    }

    public void setFlyUp() {
        this.flyup = 2;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new net.minecraft.util.math.RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.75, this.getZ()), new Vector3d(pX, pY, pZ), net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this)).getType() == net.minecraft.util.math.RayTraceResult.Type.MISS;
    }

    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 35;
        int stayup = 0;
        if (!this.isAlive()) {
            return;
        }
        super.customServerAiStep();
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level) == ChaosPersists.getDimension(4)) {
            stayup = 2;
        }
        if (this.lastX == (int)this.getX() && this.lastZ == (int)this.getZ()) {
            ++this.stuck_count;
        } else {
            this.stuck_count = 0;
            this.lastX = (int)this.getX();
            this.lastZ = (int)this.getZ();
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.stuck_count > 40 || this.random.nextInt(250) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 4.1f) {
            Block bid = Blocks.STONE;
            this.stuck_count = 0;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.random.nextInt(8) + 5 - this.flyup * 2;
                xdir = this.random.nextInt(8) + 5 - this.flyup * 2;
                if (this.random.nextInt(2) == 0) {
                    zdir = - zdir;
                }
                if (this.random.nextInt(2) == 0) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new net.minecraft.util.math.BlockPos((int)this.getX() + xdir, (int)this.getY() + this.random.nextInt(9 + stayup) - 5 + this.flyup, (int)this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        double var1 = (double)this.currentFlightTarget.getX() + 0.3 - this.getX();
        double var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double)this.currentFlightTarget.getZ() + 0.3 - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.3 - this.getDeltaMovement().x) * 0.25, (Math.signum(var3) * 0.699999 - this.getDeltaMovement().y) * 0.200000001, (Math.signum(var5) * 0.3 - this.getDeltaMovement().z) * 0.25);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.zza = 0.8f;
        this.yRot += var8 / 3.0f;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        if (!this.level.isDay()) {
            return false;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level) == ChaosPersists.getDimension(4)) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    protected Item getDropItem() {
        this.birdtype = this.getBirdType();
        if (this.birdtype == 5 && this.killedByPlayerEntity && this.level.random.nextInt(3) == 1) {
            return ChaosPersists.MyRuby;
        }
        return Items.FEATHER;
    }

    public void initCreature() {
    }

    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) { return null; }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("BirdType", this.getBirdType());
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.birdtype = par1CompoundNBT.getInt("BirdType");
        this.setBirdType(this.birdtype);
    }
}

