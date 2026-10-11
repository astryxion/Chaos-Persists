package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.entity.RenderInfo;
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

public class GhostSkelly extends AmbientCreature {
    private BlockPos currentFlightTarget = null;
    private RenderInfo renderdata = new RenderInfo();

    public GhostSkelly(EntityType<? extends GhostSkelly> type, Level par1World) {
        super(type, par1World);
        this.xpReward = 10;
        this.noPhysics = true;
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    public RenderInfo getRenderInfo() {
        return this.renderdata;
    }

    public void setRenderInfo(RenderInfo r) {
        this.renderdata.rf1 = r.rf1;
        this.renderdata.rf2 = r.rf2;
        this.renderdata.rf3 = r.rf3;
        this.renderdata.rf4 = r.rf4;
        this.renderdata.ri1 = r.ri1;
        this.renderdata.ri2 = r.ri2;
        this.renderdata.ri3 = r.ri3;
        this.renderdata.ri4 = r.ri4;
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
        return 0.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().getRandom().nextInt(2) == 0) {
            return ChaosSounds.CHAIN_RATTLES;
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
        return 5;
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
                        (int) (target.getY() + 1.0),
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
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add((Math.signum(var1) * 0.1 - motion.x) * 0.05, (Math.signum(var3) * 0.7 - motion.y) * 0.1, (Math.signum(var5) * 0.1 - motion.z) * 0.05));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 6.0f);
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

    public static boolean checkGhostSkellySpawnRules(
            EntityType<GhostSkelly> type,
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
                    if (id != null && "Ghost Pumpkin Skelly".equals(id.getPath())) {
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
