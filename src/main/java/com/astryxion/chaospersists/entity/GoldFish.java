package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import com.astryxion.chaospersists.util.MyUtils;

public class GoldFish extends Animal {
    private BlockPos currentFlightTarget = null;

    public GoldFish(EntityType<? extends GoldFish> type, Level level) {
        super(type, level);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2199999988079071)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    public int mygetMaxHealth() {
        return 6;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.level().isDay()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.45f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GENERIC_SPLASH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.GENERIC_SPLASH;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.LITTLE_SPLAT;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public void push(net.minecraft.world.entity.Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this))
                        .getType()
                == HitResult.Type.MISS;
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
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        int updown = 0;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if ((int) this.getY() < 120) {
            updown = 2;
        }
        if ((int) this.getY() > 140) {
            updown = -2;
        }
        if (this.getRandom().nextInt(300) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                zdir = this.getRandom().nextInt(5) + 5;
                xdir = this.getRandom().nextInt(5) + 5;
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + this.getRandom().nextInt(11) - 5 + updown,
                                (int) this.getZ() + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget);
                if (bid.isAir()
                        && !this.canSeeTarget(
                                (double) this.currentFlightTarget.getX(),
                                (double) this.currentFlightTarget.getY(),
                                (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE.defaultBlockState();
                }
                --keep_trying;
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.4 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.4 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.4 - motion.x) * 0.3,
                        (Math.signum(var3) * 0.7 - motion.y) * 0.2,
                        (Math.signum(var5) * 0.4 - motion.z) * 0.3));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 6.0f);
        this.setYBodyRot(this.getYRot());
        this.setYHeadRot(this.getYRot());
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

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    public static boolean checkGoldFishSpawnRules(
            EntityType<GoldFish> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return true;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = this.getRandom().nextInt(3);
        if (i == 0) {
            this.spawnAtLocation(new ItemStack(Blocks.GOLD_BLOCK));
        } else if (i == 1) {
            Item uranium =
                    ForgeRegistries.ITEMS.getValue(
                            new net.minecraft.resources.ResourceLocation(
                                    "chaospersists", "uraniumnugget"));
            if (uranium == null) {
                uranium = ChaosPersists.UraniumNugget;
            }
            if (uranium != null) {
                this.spawnAtLocation(new ItemStack(uranium));
            }
        } else if (i == 2) {
            Item titanium =
                    ForgeRegistries.ITEMS.getValue(
                            new net.minecraft.resources.ResourceLocation(
                                    "chaospersists", "titaniumnugget"));
            if (titanium == null) {
                titanium = ChaosPersists.TitaniumNugget;
            }
            if (titanium != null) {
                this.spawnAtLocation(new ItemStack(titanium));
            }
        }
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}
