package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Fairy extends AmbientCreature {
    private int stuck_count;
    private static final EntityDataAccessor<Integer> FAIRY_TYPE =
            SynchedEntityData.defineId(Fairy.class, EntityDataSerializers.INT);
    private static final ResourceLocation TEXTURE0 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture.png");
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture2.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture3.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture4.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture5.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture6.png");
    private static final ResourceLocation TEXTURE6 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture7.png");
    private static final ResourceLocation TEXTURE7 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture8.png");
    private static final ResourceLocation TEXTURE8 =
            new ResourceLocation("chaospersists", "textures/entity/fairytexture9.png");

    int my_blink = 0;
    int blinker = 0;
    public int fairy_type = 0;
    private int force_sync = 10;
    private BlockPos currentFlightTarget = null;
    private String myowner = null;
    private final GenericTargetSorter targetSorter;

    public Fairy(EntityType<? extends Fairy> type, Level level) {
        super(type, level);
        this.my_blink = 20 + this.getRandom().nextInt(20);
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // 1.12 entityInit() ran after constructor set fairy_type; 1.20 defineSynchedData runs during super().
        if (!this.level().isClientSide()) {
            this.fairy_type = this.getRandom().nextInt(9);
        }
        this.entityData.define(FAIRY_TYPE, this.fairy_type);
    }

    public ResourceLocation getTexture(Fairy a) {
        int type = a.getFairyType();
        if (type == 8) {
            return TEXTURE8;
        }
        if (type == 7) {
            return TEXTURE7;
        }
        if (type == 6) {
            return TEXTURE6;
        }
        if (type == 5) {
            return TEXTURE5;
        }
        if (type == 4) {
            return TEXTURE4;
        }
        if (type == 3) {
            return TEXTURE3;
        }
        if (type == 2) {
            return TEXTURE2;
        }
        if (type == 1) {
            return TEXTURE1;
        }
        return TEXTURE0;
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (FAIRY_TYPE.equals(key)) {
            this.fairy_type = this.getFairyType();
        }
    }

    public int getFairyType() {
        return this.entityData.get(FAIRY_TYPE);
    }

    public void setFairyType(int par1) {
        this.fairy_type = par1;
        this.entityData.set(FAIRY_TYPE, par1);
    }

    public void setOwner(LivingEntity e) {
        if (e instanceof Player p) {
            this.myowner = p.getGameProfile().getName();
        }
    }

    public float getBlink() {
        if (this.blinker < this.my_blink / 2) {
            return 240.0f;
        }
        return 0.0f;
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return par1Entity.hurt(this.damageSources().mobAttack(this), 2.0f);
    }

    @Override
    protected float getSoundVolume() {
        return 0.25f;
    }

    @Override
    public float getVoicePitch() {
        return 1.7f;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.RATHIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.BIG_SPLAT;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void doPush(Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return 40;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (ChaosPersists.CrystalTorch != null) {
            this.spawnAtLocation(ChaosPersists.CrystalTorch.asItem());
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
        ++this.blinker;
        if (this.blinker > this.my_blink) {
            this.blinker = 0;
        }
        --this.force_sync;
        if (this.force_sync < 0) {
            this.force_sync = 10;
            if (this.level().isClientSide) {
                this.fairy_type = this.getFairyType();
            } else {
                this.setFairyType(this.fairy_type);
            }
        }
        long t = this.level().getDayTime() % 24000L;
        if (t < 12000L) {
            return;
        }
        if (this.level().isClientSide
                && this.getRandom().nextInt(5) == 0
                && this.getBlink() > 1.0f) {
            this.level()
                    .addParticle(
                            ParticleTypes.FIREWORK,
                            this.getX(),
                            this.getY() - 0.15000000596046448,
                            this.getZ(),
                            (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 8.0f,
                            (-this.getRandom().nextFloat()) / 8.0f,
                            (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 8.0f);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        if (this.myowner == null) {
            this.myowner = "null";
        }
        par1NBTTagCompound.putString("MyOwner", this.myowner);
        par1NBTTagCompound.putInt("FairyType", this.fairy_type);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.myowner = par1NBTTagCompound.getString("MyOwner");
        if (this.myowner != null && this.myowner.equals("null")) {
            this.myowner = null;
        }
        if (par1NBTTagCompound.contains("FairyType")) {
            this.fairy_type = par1NBTTagCompound.getInt("FairyType");
        } else {
            this.fairy_type = par1NBTTagCompound.getInt("fairyType");
        }
        this.setFairyType(this.fairy_type);
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.25, this.getZ()),
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
        int keepTrying = 25;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(200) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.5) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            this.stuck_count = 0;
            while (!bid.isAir() && keepTrying != 0) {
                int zdir = this.getRandom().nextInt(8);
                int xdir = this.getRandom().nextInt(8);
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + this.getRandom().nextInt(5) - 2,
                                (int) this.getZ() + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget);
                if (bid.isAir()
                        && !this.canSeeTarget(
                                this.currentFlightTarget.getX(),
                                this.currentFlightTarget.getY(),
                                this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE.defaultBlockState();
                }
                --keepTrying;
            }
        } else if (this.getRandom().nextInt(12) == 0 && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.currentFlightTarget = new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                if (this.distanceToSqr(e) < 6.0) {
                    this.doHurtTarget(e);
                }
            }
        } else if (this.myowner != null && this.level() instanceof ServerLevel serverLevel) {
            Player p = serverLevel.getServer().getPlayerList().getPlayerByName(this.myowner);
            if (p != null) {
                if (this.distanceToSqr(p) > 64.0) {
                    this.currentFlightTarget =
                            new BlockPos(
                                    (int) p.getX() + this.getRandom().nextInt(3) - this.getRandom().nextInt(3),
                                    (int) (p.getY() + 1.0),
                                    (int) p.getZ() + this.getRandom().nextInt(3) - this.getRandom().nextInt(3));
                }
                if (this.distanceToSqr(p) > 256.0) {
                    this.moveTo(
                            p.getX() + this.getRandom().nextFloat() - this.getRandom().nextFloat(),
                            p.getY(),
                            p.getZ() + this.getRandom().nextFloat() - this.getRandom().nextFloat());
                }
            }
        }
        if (this.getRandom().nextInt(250) == 1) {
            this.heal(1.0f);
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.2 - motion.x) * 0.1,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.1,
                        (Math.signum(var5) * 0.2 - motion.z) * 0.1));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 4.0f);
        MyUtils.applyChaosFlightMovement(this);
}

    public boolean canTriggerPressurePlate() {
        return false;
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
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.myowner != null) {
            return false;
        }
        return true;
    }

    public static boolean checkFairySpawnRules(
            EntityType<Fairy> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        int sc = 0;
        for (int k = -1; k <= 1; ++k) {
            for (int j = -1; j <= 1; ++j) {
                if (MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, 0, k)).isAir()) {
                    ++sc;
                }
            }
        }
        if (sc < 6) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        int sc = 0;
        BlockPos pos = this.blockPosition();
        for (int k = -1; k <= 1; ++k) {
            for (int j = -1; j <= 1; ++j) {
                if (MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, 0, k)).isAir()) {
                    ++sc;
                }
            }
        }
        if (sc < 6) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        return par1EntityLiving instanceof Monster;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(8.0, 8.0, 8.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }
}
