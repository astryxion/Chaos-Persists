package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.block.BlockState;

public class Ghost extends FlyingEntity {
    private BlockPos currentFlightTarget = null;

    public Ghost(EntityType<? extends Ghost> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.5f, height=1.5f
        this.xpReward = 5;
        this.noPhysics = true;
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0).build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
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
    protected float getVoicePitch() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level.random.nextInt(2) == 0) {
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
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.65, this.getDeltaMovement().z);
    }

    @Override
    protected void customServerAiStep() {
        int i = 0;
        int j = 0;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.level.random.nextInt(40) == 1 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.0) {
            PlayerEntity target = this.level.getNearestPlayer(this, 16.0);
            if (target != null) {
                this.currentFlightTarget = new BlockPos((int) target.getX() + this.random.nextInt(3) - this.random.nextInt(3), (int) target.getY() + 1, (int) target.getZ() + this.random.nextInt(3) - this.random.nextInt(3));
            } else {
                Block bid;
                for (i = 0; i < 3 && (bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY() + i, (int) this.getZ())).getBlock()) != Blocks.AIR; ++i) {
                }
                for (j = -1; j >= -3 && (bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY() + j, (int) this.getZ())).getBlock()) == Blocks.AIR; --j) {
                }
                this.currentFlightTarget = new BlockPos((int) this.getX() + this.random.nextInt(10) - this.random.nextInt(10), (int) this.getY() + i + j + this.random.nextInt(4) + 1, (int) this.getZ() + this.random.nextInt(10) - this.random.nextInt(10));
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.1 - this.getDeltaMovement().x) * 0.05;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.7 - this.getDeltaMovement().y) * 0.1;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.1 - this.getDeltaMovement().z) * 0.05;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.05f;
        this.yRot += var8 / 6.0f;
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = level.getBlockState(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(level.getBlockEntity(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)) instanceof MobSpawnerTileEntity)) {
                        continue;
                    }
                    MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity) level.getBlockEntity(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k));
                    String s = null;
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) {
                        s = id.getPath();
                    }
                    if (s == null || !s.equals("Ghost")) {
                        continue;
                    }
                    return true;
                }
            }
        }
        if (level instanceof net.minecraft.world.World && ((net.minecraft.world.World)level).isDay()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return ret;
        }
        ret = super.hurt(par1DamageSource, par2);
        return ret;
    }
}
