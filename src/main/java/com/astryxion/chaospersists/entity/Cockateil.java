package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
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

public class Cockateil extends Animal {
    private static final EntityDataAccessor<Integer> BIRD_TYPE =
            SynchedEntityData.defineId(Cockateil.class, EntityDataSerializers.INT);
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/bird1.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/bird2.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/bird3.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/bird4.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/entity/bird5.png");
    private static final ResourceLocation TEXTURE6 =
            new ResourceLocation("chaospersists", "textures/entity/bird6.png");

    private BlockPos currentFlightTarget = null;
    public int birdtype;
    private boolean killedByPlayer = false;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private int flyup = 0;

    public Cockateil(EntityType<? extends Cockateil> type, Level level) {
        super(type, level);
        this.xpReward = 2;
        this.setNoGravity(true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.33000001311302185)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.birdtype = this.getRandom().nextInt(6);
        this.entityData.define(BIRD_TYPE, this.birdtype);
    }

    @Override
    protected void registerGoals() {
    }

    public ResourceLocation getTexture() {
        this.birdtype = this.getBirdType();
        return switch (this.birdtype) {
            case 0 -> TEXTURE1;
            case 1 -> TEXTURE2;
            case 2 -> TEXTURE3;
            case 3 -> TEXTURE4;
            case 4 -> TEXTURE5;
            case 5 -> TEXTURE6;
            default -> TEXTURE1;
        };
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getBirdType() {
        return this.entityData.get(BIRD_TYPE);
    }

    public void setBirdType(int par1) {
        this.entityData.set(BIRD_TYPE, par1);
    }

    @Override
    protected float getSoundVolume() {
        return 0.55f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().isDay() && !this.level().isRaining()) {
            return ChaosSounds.BIRDS;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    public int mygetMaxHealth() {
        return 2;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if (e instanceof Player) {
            this.killedByPlayer = true;
        }
        return super.hurt(par1DamageSource, par2);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        } else if (this.getY() < (double) this.currentFlightTarget.getY()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.7, 1.0));
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.5, 1.0));
        }
    }

    public void setFlyUp() {
        this.flyup = 2;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        return hit.getType() == HitResult.Type.MISS;
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
        int keep_trying = 35;
        int stayup = 0;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.level().dimension() == ChaosPersists.getDimensionKey(4)) {
            stayup = 2;
        }
        if (this.lastX == (int) this.getX() && this.lastZ == (int) this.getZ()) {
            ++this.stuck_count;
        } else {
            this.stuck_count = 0;
            this.lastX = (int) this.getX();
            this.lastZ = (int) this.getZ();
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.stuck_count > 40
                || this.getRandom().nextInt(250) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 4.1) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            this.stuck_count = 0;
            while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                zdir = this.getRandom().nextInt(8) + 5 - this.flyup * 2;
                xdir = this.getRandom().nextInt(8) + 5 - this.flyup * 2;
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget = new BlockPos(
                        (int) this.getX() + xdir,
                        (int) this.getY() + this.getRandom().nextInt(9 + stayup) - 5 + this.flyup,
                        (int) this.getZ() + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget);
                if (bid.getBlock() == Blocks.AIR
                        && !this.canSeeTarget(
                                (double) this.currentFlightTarget.getX(),
                                (double) this.currentFlightTarget.getY(),
                                (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE.defaultBlockState();
                }
                --keep_trying;
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.3 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.3 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.3 - motion.x) * 0.25,
                        (Math.signum(var3) * 0.699999 - motion.y) * 0.200000001,
                        (Math.signum(var5) * 0.3 - motion.z) * 0.25));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 3.0f);
        MyUtils.applyChaosFlightMovement(this);
}

    public static boolean checkBirdSpawnRules(
            EntityType<Cockateil> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (MyUtils.getBlockStateForSpawnRules(level, pos).getBlock() != Blocks.AIR) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (level.getLevel().dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        return pos.getY() >= 50;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (level instanceof Level world && world.dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.birdtype = this.getBirdType();
        if (this.birdtype == 5 && this.killedByPlayer && this.getRandom().nextInt(3) == 1) {
            Item ruby = ForgeRegistries.ITEMS.getValue(new ResourceLocation("chaospersists", "ruby"));
            if (ruby != null) {
                this.spawnAtLocation(ruby);
                return;
            }
        }
        this.spawnAtLocation(Items.FEATHER);
    }

    @Override
    public AgeableMob getBreedOffspring(net.minecraft.server.level.ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("BirdType", this.getBirdType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.birdtype = tag.getInt("BirdType");
        this.setBirdType(this.birdtype);
    }
}
