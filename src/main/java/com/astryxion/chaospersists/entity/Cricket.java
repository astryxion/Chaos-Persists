package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.BlockState;

public class Cricket extends AnimalEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Cricket.class, DataSerializers.BYTE);
    public double moveSpeed = 0.15000000596046448;
    private int singing = 0;
    private int jumpcount = 0;

    public Cricket(EntityType<? extends Cricket> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.1f, height=0.1f
        this.xpReward = 1;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 8, 1.0));
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15000000596046448)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getSinging() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setSinging(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    private void jumpAround() {
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + (double) (0.55f + Math.abs(this.level.random.nextFloat() * 0.35f)), this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 0.25, this.getZ());
        float f = 0.3f + Math.abs(this.level.random.nextFloat() * 0.25f);
        float d = (float) ((double) this.level.random.nextFloat() * 3.141592653589793 * 2.0);
        this.setDeltaMovement(this.getDeltaMovement().x + (double) f * Math.sin(d), this.getDeltaMovement().y, this.getDeltaMovement().z + (double) f * Math.cos(d));
        this.hasImpulse = true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.moveSpeed);
        super.tick();
        if (!this.level.isClientSide) {
            if (this.singing != 0) {
                --this.singing;
                if (this.singing <= 0) {
                    this.setSinging(0);
                }
            }
            if (this.jumpcount > 0) {
                --this.jumpcount;
            }
            if (this.jumpcount == 0 && this.level.random.nextInt(50) == 1) {
                this.jumpAround();
                this.jumpcount = 50;
            }
        }
    }

    public int mygetMaxHealth() {
        return 3;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (!this.level.isClientSide) {
            if (this.level.random.nextInt(2) == 0) {
                return null;
            }
            this.singing = 40;
            this.setSinging(this.singing);
        }
        return ChaosSounds.CRICKET;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected void playStepSound(BlockPos par1, BlockState par2) {
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource par1, int par2, boolean par3) {
    }

    @Override
    protected boolean isMovementNoisy() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) {
        return null;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        if (this.getY() < 30.0) {
            return false;
        }
        if (this.findBuddies() > 5) {
            return false;
        }
        return true;
    }

    private int findBuddies() {
        List<Cricket> var5 = this.level.getEntitiesOfClass(Cricket.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }
}
