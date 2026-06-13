package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

public class CliffRacer extends AnimalEntity {
    private BlockPos currentFlightTarget = null;

    public CliffRacer(EntityType<? extends CliffRacer> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.75f, height=0.5f
        this.xpReward = 5;
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.33000001311302185)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .build();
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.45f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.CLIFFRACER;
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
        return true;
    }

    public int mygetMaxHealth() {
        return 5;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.6, this.getDeltaMovement().z);
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.75, this.getZ()), new Vector3d(pX, pY, pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @Override
    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.random.nextInt(300) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.1f) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.random.nextInt(10) + 5;
                xdir = this.random.nextInt(10) + 5;
                if (this.random.nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.random.nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget = new BlockPos((int) this.getX() + xdir, (int) this.getY() + this.random.nextInt(11) - 5, (int) this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double) this.currentFlightTarget.getX(), (double) this.currentFlightTarget.getY(), (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.4 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.4 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.4 - this.getDeltaMovement().x) * 0.3;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.7 - this.getDeltaMovement().y) * 0.2;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.4 - this.getDeltaMovement().z) * 0.3;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.75f;
        this.yRot += var8 / 6.0f;
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
    public boolean canChangeDimensions() {
        return false;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(8);
        if (i == 0) {
            return Items.CHICKEN;
        }
        if (i == 1) {
            return ChaosPersists.UraniumNugget;
        }
        if (i == 2) {
            return ChaosPersists.TitaniumNugget;
        }
        return null;
    }

    @Override
    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) {
        return null;
    }
}
