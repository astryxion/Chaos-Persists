package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class Ghost extends AmbientCreature {
    private BlockPos currentFlightTarget = null;

    public Ghost(EntityType<? extends Ghost> type, Level par1World) {
        super(type, par1World);
        this.xpReward = 5;
        this.noPhysics = true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.3f;
    }

    @Override
    public float getVoicePitch() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().getRandom().nextInt(2) == 0) {
            return ChaosSounds.GHOST_SOUND;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource ds) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return 2;
    }

    @Override
    public void tick() {
        if (this.isPersistenceRequired()) {
            this.noPhysics = false;
        }
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.65, 1.0));
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        int i = 0;
        int j = 0;
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.level().getRandom().nextInt(40) == 1
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.0) {
            Player target = this.level().getNearestPlayer(this.getX(), this.getY(), this.getZ(), 16.0, false);
            if (target != null) {
                this.currentFlightTarget = new BlockPos(
                        (int) target.getX() + this.getRandom().nextInt(3) - this.getRandom().nextInt(3),
                        (int) target.getY() + 1,
                        (int) target.getZ() + this.getRandom().nextInt(3) - this.getRandom().nextInt(3));
            } else {
                BlockState bid;
                for (i = 0;
                        i < 3
                                && (bid = this.level()
                                                .getBlockState(
                                                        BlockPos.containing(this.getX(), this.getY() + i, this.getZ())))
                                        .getBlock()
                                != Blocks.AIR;
                        ++i) {
                }
                for (j = -1;
                        j >= -3
                                && (bid = this.level()
                                                .getBlockState(
                                                        BlockPos.containing(
                                                                this.getX(), this.getY() + j, this.getZ())))
                                        .getBlock()
                                == Blocks.AIR;
                        --j) {
                }
                this.currentFlightTarget = new BlockPos(
                        (int) this.getX() + this.getRandom().nextInt(10) - this.getRandom().nextInt(10),
                        (int) this.getY() + i + j + this.getRandom().nextInt(4) + 1,
                        (int) this.getZ() + this.getRandom().nextInt(10) - this.getRandom().nextInt(10));
            }
        }
        MyUtils.steerChaosFlightForward(
                this,
                (double) this.currentFlightTarget.getX() + 0.5,
                (double) this.currentFlightTarget.getY() + 0.1,
                (double) this.currentFlightTarget.getZ() + 0.5,
                0.2);
        MyUtils.applyChaosFlightMovement(this);
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
    public boolean canBeCollidedWith() {
        return false;
    }

    private static boolean isDaytime(LevelAccessor level) {
        if (level instanceof Level world) {
            return world.isDay();
        }
        return false;
    }

    public static boolean checkGhostSpawnRules(
            EntityType<Ghost> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (!isDaytime(level)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "ghost".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (isDaytime(level)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }
}
