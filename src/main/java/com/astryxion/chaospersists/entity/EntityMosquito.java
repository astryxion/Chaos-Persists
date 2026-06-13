package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.util.math.BlockPos;
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
import net.minecraft.block.BlockState;

public class EntityMosquito extends FlyingEntity {
    private BlockPos currentFlightTarget = null;

    public EntityMosquito(EntityType<? extends EntityMosquito> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.2f, height=0.2f
        this.xpReward = 5;
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
        return 0.4f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.MOSQUITO;
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
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.6000000238418579, this.getDeltaMovement().z);
    }

    @Override
    protected void customServerAiStep() {
        int keep_trying = 50;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.random.nextInt(20) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 3.0f) {
            PlayerEntity target = null;
            if (ChaosPersists.ChaosRand.nextInt(4) == 0) {
                target = this.level.getNearestPlayer(this, 10.0);
                if (target != null) {
                    this.currentFlightTarget = new BlockPos((int) target.getX(), (int) target.getY() + 2, (int) target.getZ());
                } else {
                    Block bid = Blocks.STONE;
                    while (bid != Blocks.AIR && keep_trying != 0) {
                        this.currentFlightTarget = new BlockPos((int) this.getX() + this.random.nextInt(6) - this.random.nextInt(6), (int) this.getY() + this.random.nextInt(6) - 2, (int) this.getZ() + this.random.nextInt(6) - this.random.nextInt(6));
                        bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                        --keep_trying;
                    }
                }
            } else {
                Block bid = Blocks.STONE;
                while (bid != Blocks.AIR && keep_trying != 0) {
                    this.currentFlightTarget = new BlockPos((int) this.getX() + this.random.nextInt(6) - this.random.nextInt(6), (int) this.getY() + this.random.nextInt(6) - 2, (int) this.getZ() + this.random.nextInt(6) - this.random.nextInt(6));
                    bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                    --keep_trying;
                }
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.5 - this.getDeltaMovement().x) * 0.10000000149011612;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.10000000149011612;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.5 - this.getDeltaMovement().z) * 0.10000000149011612;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.3f;
        this.yRot += var8;
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
        return true;
    }
}
