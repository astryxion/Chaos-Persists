package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Cricket extends Animal {
    private static final EntityDataAccessor<Integer> SINGING =
            SynchedEntityData.defineId(Cricket.class, EntityDataSerializers.INT);
    public double moveSpeed = 0.15000000596046448;
    private int jumpcount = 0;

    public Cricket(EntityType<? extends Cricket> type, Level level) {
        super(type, level);
        this.xpReward = 1;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15000000596046448)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SINGING, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 8, 1.0));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getSinging() {
        return this.entityData.get(SINGING);
    }

    public void setSinging(int par1) {
        this.entityData.set(SINGING, par1);
    }

    private void jumpAround() {
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.x + (0.3 + Math.abs(this.getRandom().nextFloat() * 0.25)) * Math.sin(this.getRandom().nextFloat() * Math.PI * 2.0),
                motion.y + (0.55f + Math.abs(this.getRandom().nextFloat() * 0.35f)),
                motion.z + (0.3 + Math.abs(this.getRandom().nextFloat() * 0.25)) * Math.cos(this.getRandom().nextFloat() * Math.PI * 2.0));
        this.setPos(this.getX(), this.getY() + 0.25, this.getZ());
        this.setOnGround(false);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.moveSpeed);
        super.tick();
        if (!this.level().isClientSide) {
            int singing = this.getSinging();
            if (singing > 0) {
                this.setSinging(singing - 1);
            }
            if (this.jumpcount > 0) {
                --this.jumpcount;
            }
            if (this.jumpcount == 0 && this.getRandom().nextInt(50) == 1) {
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
        if (this.level().isClientSide) {
            return null;
        }
        if (this.getRandom().nextInt(2) == 0) {
            return null;
        }
        this.setSinging(40);
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
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    public static boolean checkCricketSpawnRules(
            EntityType<Cricket> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (pos.getY() < 30) {
            return false;
        }
        List<Cricket> buddies = level.getLevel().getEntitiesOfClass(Cricket.class, new net.minecraft.world.phys.AABB(pos).inflate(20.0, 10.0, 20.0));
        return buddies.size() <= 5;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 30.0) {
            return false;
        }
        if (level instanceof Level world) {
            List<Cricket> buddies =
                    world.getEntitiesOfClass(Cricket.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
            if (buddies.size() > 5) {
                return false;
            }
        }
        return true;
    }
}
