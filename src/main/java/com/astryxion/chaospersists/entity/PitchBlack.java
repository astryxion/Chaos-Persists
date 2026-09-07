package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class PitchBlack extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(PitchBlack.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> ACTIVITY =
            SynchedEntityData.defineId(PitchBlack.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> SCALE_INT =
            SynchedEntityData.defineId(PitchBlack.class, EntityDataSerializers.INT);
    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;
    private boolean spawnedFromEgg = false;
    private boolean spawnedFromSpawner = false;
    private RenderInfo renderdata = new RenderInfo();
    private float myMoveSpeed = 0.2f;
    private int damageTicker = 0;
    private int wingSound = 0;
    private boolean scaleInitialized = false;

    public PitchBlack(EntityType<? extends PitchBlack> type, Level level) {
        super(type, level);
        this.xpReward = 200;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 1.0, false, 14, () -> false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.PitchBlack_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.PitchBlack_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.PitchBlack_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(ACTIVITY, (byte) 0);
        this.entityData.define(SCALE_INT, 0);
        // Minecraft may call defineSynchedData() from the Entity base constructor before
        // this subclass's field initializers run, so ensure renderdata is non-null.
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

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide && !this.scaleInitialized) {
            this.assignInitialScaleIfNeeded();
        }
        this.refreshDimensions();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (SCALE_INT.equals(key)) {
            this.applyScaleToDimensions();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("SpawnedFromEgg", this.spawnedFromEgg);
        tag.putBoolean("SpawnedFromSpawner", this.spawnedFromSpawner);
        tag.putFloat("Fscale", this.getPitchBlackScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.spawnedFromEgg = tag.getBoolean("SpawnedFromEgg");
        this.spawnedFromSpawner = tag.getBoolean("SpawnedFromSpawner");
        if (tag.contains("Fscale")) {
            this.setPitchBlackScale(tag.getFloat("Fscale"));
            this.scaleInitialized = true;
        }
        this.applyScaleToDimensions();
    }

    public void setSpawnedFromEgg() {
        this.spawnedFromEgg = true;
    }

    public void setSpawnedFromSpawner() {
        this.spawnedFromSpawner = true;
        this.setPersistenceRequired();
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int getActivity() {
        return this.entityData.get(ACTIVITY);
    }

    public void setActivity(int par1) {
        this.entityData.set(ACTIVITY, (byte) par1);
    }

    public float getPitchBlackScale() {
        int i = this.entityData.get(SCALE_INT);
        float f = i / 10.0f;
        if (f < 0.5f) {
            return 0.5f;
        }
        return f;
    }

    public void setPitchBlackScale(float par1) {
        if (par1 < 0.5f) {
            par1 = 0.5f;
        }
        int i = (int) (par1 * 10.0001f);
        this.entityData.set(SCALE_INT, i);
        this.scaleInitialized = true;
        this.applyScaleToDimensions();
    }

    private void assignInitialScaleIfNeeded() {
        if (this.scaleInitialized && this.entityData.get(SCALE_INT) != 0) {
            return;
        }
        float t = 0.5f;
        if (this.getRandom().nextInt(4) == 1) {
            t = 1.0f;
        }
        if (this.getRandom().nextInt(8) == 2) {
            t = 2.0f;
        }
        if (this.getRandom().nextInt(32) == 3) {
            t = 3.0f;
        }
        if (this.getRandom().nextInt(64) == 4) {
            t = 4.0f;
        }
        if (ChaosPersists.NightmareSize == 1) {
            t = 0.5f;
        }
        if (ChaosPersists.NightmareSize == 2) {
            t = 1.0f;
        }
        if (ChaosPersists.NightmareSize == 3) {
            t = 2.0f;
        }
        if (ChaosPersists.NightmareSize == 4) {
            t = 3.0f;
        }
        if (ChaosPersists.NightmareSize == 5) {
            t = 4.0f;
        }
        this.setPitchBlackScale(t);
        this.xpReward = (int) (100.0f * t);
        this.scaleInitialized = true;
    }

    private void applyScaleToDimensions() {
        this.refreshDimensions();
        if (this.level().isClientSide) {
            return;
        }
        float scale = this.getPitchBlackScale();
        float previousMax = (float) this.getAttribute(Attributes.MAX_HEALTH).getBaseValue();
        float newMax = (float) this.mygetMaxHealth();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) newMax);
        this.getAttribute(Attributes.ATTACK_DAMAGE)
                .setBaseValue((double) (scale * (float) ChaosPersists.PitchBlack_stats.attack));
        this.getAttribute(Attributes.ARMOR)
                .setBaseValue((double) (ChaosPersists.PitchBlack_stats.defense + (int) (2.0f * scale)));
        float currentHealth = this.getHealth();
        if (currentHealth > newMax) {
            this.setHealth(newMax);
        } else if (currentHealth >= previousMax - 0.01f) {
            this.setHealth(newMax);
        }
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

    public int mygetMaxHealth() {
        return (int) ((float) ChaosPersists.PitchBlack_stats.health * this.getPitchBlackScale());
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.spawnedFromEgg || this.spawnedFromSpawner) {
            return false;
        }
        if (this.tickCount < 40) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.level().isDay()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f - 0.7f * (4.0f / this.getPitchBlackScale());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(5) == 2) {
            return ChaosSounds.PITCHBLACK_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.PITCHBLACK_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.PITCHBLACK_DEAD;
    }

    private void syncSpawnerSpawnStateIfNeeded() {
        if (this.level().isClientSide || this.spawnedFromSpawner || this.tickCount > 40) {
            return;
        }
        BlockPos base = this.blockPosition();
        for (int dx = -8; dx <= 8; ++dx) {
            for (int dy = -4; dy <= 8; ++dy) {
                for (int dz = -8; dz <= 8; ++dz) {
                    BlockPos check = base.offset(dx, dy, dz);
                    if (!(this.level().getBlockEntity(check) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id == null) {
                        continue;
                    }
                    String path = SpawnerFixHelper.normalizeSpawnerEntityId(id).getPath();
                    if ("nightmare".equalsIgnoreCase(path)) {
                        this.setSpawnedFromSpawner();
                        float t = this.getPitchBlackScale();
                        if (t > 1.0f) {
                            this.setPitchBlackScale(1.0f);
                        }
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void tick() {
        this.syncSpawnerSpawnStateIfNeeded();
        if (this.getPitchBlackScale() < 0.5f) {
            this.setPitchBlackScale(0.5f);
        }
        this.myMoveSpeed = 0.2f;
        this.getAttribute(Attributes.MOVEMENT_SPEED)
                .setBaseValue((double) (this.myMoveSpeed + 0.1f * this.getPitchBlackScale()));
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
        ++this.wingSound;
        if (this.wingSound > 20) {
            if (!this.level().isClientSide) {
                this.level()
                        .playSound(
                                null,
                                this.blockPosition(),
                                ChaosSounds.MOTHRA_WINGS,
                                SoundSource.HOSTILE,
                                1.0f,
                                1.0f);
            }
            this.wingSound = 0;
        }
        if (!this.level().isClientSide && this.getRandom().nextInt(250) == 1) {
            this.heal(1.0f + this.getPitchBlackScale());
            if (this.getRandom().nextInt(5) == 0) {
                BlockState bid = Blocks.AIR.defaultBlockState();
                if (this.getY() > 10.0) {
                    for (int i = 0; i < 10; ++i) {
                        bid = this.level().getBlockState(this.blockPosition().below(i));
                        if (bid.getBlock() != Blocks.AIR) {
                            break;
                        }
                    }
                } else {
                    bid = Blocks.STONE.defaultBlockState();
                }
                if (bid.getBlock() != Blocks.AIR) {
                    if (this.findSomethingToAttack() == null) {
                        this.setActivity(0);
                    }
                }
            } else {
                this.setActivity(1);
                this.getNavigation().stop();
            }
        }
        if (this.getActivity() == 0 && this.getRandom().nextInt(10) == 1) {
            if (this.findSomethingToAttack() != null) {
                this.setActivity(1);
                this.getNavigation().stop();
            }
        }
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        if (!this.level().isClientSide
                && (this.spawnedFromEgg || this.spawnedFromSpawner)
                && this.getHealth() > 0.0f
                && reason == Entity.RemovalReason.DISCARDED) {
            return;
        }
        super.remove(reason);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        float damage = (float) ChaosPersists.PitchBlack_stats.attack * this.getPitchBlackScale();
        if (target instanceof EnderDragon dragon) {
            DamageSource src = this.damageSources().explosion(this, this);
            if (this.getRandom().nextInt(8) == 1) {
                return dragon.hurt(src, damage);
            }
            return dragon.hurt(this.damageSources().mobAttack(this), damage);
        }
        if (!target.hurt(this.damageSources().mobAttack(this), damage)) {
            return false;
        }
        if (target instanceof LivingEntity living) {
            double ks = 1.15 * (double) this.getPitchBlackScale();
            double inair = 0.08 * (double) this.getPitchBlackScale();
            float f3 = (float) Mth.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());
            if (!living.isAlive() || target instanceof Player) {
                inair *= 2.0;
            }
            living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return true;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        Vec3 from = new Vec3(this.getX(), this.getY() + 0.75, this.getZ());
        Vec3 to = new Vec3(pX, pY, pZ);
        return this.level().clip(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this))
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
        if (this.damageTicker > 0) {
            --this.damageTicker;
        }
        if (this.getActivity() == 0) {
            super.customServerAiStep();
            return;
        }
        if (this.isDeadOrDying()) {
            return;
        }
        int keepTrying = 50;
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(150) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (!bid.isAir() && keepTrying > 0) {
                int zdir = this.getRandom().nextInt(20) + 5 * (int) this.getPitchBlackScale();
                int xdir = this.getRandom().nextInt(20) + 5 * (int) this.getPitchBlackScale();
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + this.getRandom().nextInt(11) - 5,
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
        } else if (this.getRandom().nextInt(8) == 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                double d1 = 5.0 + (double) (e.getBbWidth() / 2.0f);
                d1 += (double) this.getPitchBlackScale();
                d1 *= d1;
                this.setAttacking(1);
                String name = e.getClass().getSimpleName();
                if (e instanceof EnderDragon || "Godzilla".equals(name) || "GodzillaHead".equals(name)) {
                    d1 = Math.max(d1, 100.0);
                }
                this.currentFlightTarget =
                        new BlockPos((int) e.getX(), (int) (e.getY() + 2.0), (int) e.getZ());
                if (this.distanceToSqr(e) < d1) {
                    this.doHurtTarget(e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.4 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.4 - this.getZ();
        double myspeed = 0.5 + (double) (this.getPitchBlackScale() / 10.0f);
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * myspeed - motion.x) * 0.33,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.20000000149011612,
                        (Math.signum(var5) * myspeed - motion.z) * 0.33));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 5.0f);
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
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        // /kill and other absolute damage must pierce i-frames
        if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            if (this.damageTicker > 0) {
                return false;
            }
            this.damageTicker = 20;
        }
        boolean ret = super.hurt(source, amount);
        Entity e = source.getEntity();
        if (e != null) {
            this.currentFlightTarget = new BlockPos((int) e.getX(), (int) (e.getY() + 2.0), (int) e.getZ());
        }
        this.setActivity(1);
        this.getNavigation().stop();
        return ret;
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof PitchBlack) {
            return false;
        }
        if (par1EntityLiving instanceof TerribleTerror) {
            return false;
        }
        if (par1EntityLiving instanceof LurkingTerror) {
            return false;
        }
        if (par1EntityLiving instanceof CreepingHorror) {
            return false;
        }
        if (par1EntityLiving instanceof EnderReaper) {
            return false;
        }
        if (par1EntityLiving instanceof EnderKnight) {
            return false;
        }
        if (par1EntityLiving instanceof LeafMonster) {
            return false;
        }
        if (par1EntityLiving instanceof Triffid) {
            return false;
        }
        if (par1EntityLiving instanceof Island) {
            return false;
        }
        if (par1EntityLiving instanceof IslandToo) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.isCreative();
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        double d1 = 16.0 + (double) (this.getPitchBlackScale() * 6.0f);
        double d2 = 10.0 + (double) (this.getPitchBlackScale() * 4.0f);
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(d1, d2, d1));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var3 = var2.next();
            if (!this.isSuitableTarget(var3, false)) {
                continue;
            }
            return var3;
        }
        return null;
    }

    private void dropItemRand(Item index, int par1) {
        if (index == null) {
            return;
        }
        float spread = this.getPitchBlackScale();
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) (ChaosPersists.ChaosRand.nextInt(5) * spread)
                                - (double) (ChaosPersists.ChaosRand.nextInt(5) * spread),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) (ChaosPersists.ChaosRand.nextInt(5) * spread)
                                - (double) (ChaosPersists.ChaosRand.nextInt(5) * spread),
                        is);
        this.level().addFreshEntity(entityItem);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 3 + this.getRandom().nextInt(2 + (int) (5.0f * this.getPitchBlackScale()));
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.ROTTEN_FLESH, 1);
            int j = this.getRandom().nextInt(10);
            if (j == 0) {
                this.dropItemRand(Items.FEATHER, 1);
            } else if (j == 1) {
                this.dropItemRand(Items.STRING, 1);
            } else if (j == 2) {
                this.dropItemRand(Items.FLINT, 1);
            } else if (j == 3) {
                this.dropItemRand(Items.BEEF, 1);
            }
        }
        this.dropItemRand(ChaosPersists.MyNightmareScale, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        i = 2 + (int) this.getPitchBlackScale() + this.getRandom().nextInt(2 + (int) (5.0f * this.getPitchBlackScale()));
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.ZooKeeper, 1);
        }
    }

    public static boolean checkPitchBlackSpawnRules(
            EntityType<PitchBlack> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        for (int k = -5; k <= 5; ++k) {
            for (int j = -5; j <= 5; ++j) {
                for (int i = -2; i <= 6; ++i) {
                    BlockPos check = pos.offset(j, i, k);
                    if (MyUtils.getBlockStateForSpawnRules(level, check).getBlock() == Blocks.SPAWNER) {
                        if (level instanceof Level world) {
                            BlockEntity be = world.getBlockEntity(check);
                            if (be instanceof SpawnerBlockEntity spawner) {
                                ResourceLocation id =
                                        SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                                if (id != null && "nightmare".equalsIgnoreCase(id.getPath())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (level.getMaxLocalRawBrightness(pos) > 7) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        if (level.getLevel().dimension().equals(ChaosPersists.getDimensionKey(6))) {
            List<PitchBlack> nearby =
                    level.getLevel()
                            .getEntitiesOfClass(PitchBlack.class, new AABB(pos).inflate(16.0, 16.0, 16.0));
            if (!nearby.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (spawnReason == MobSpawnType.SPAWN_EGG || spawnReason == MobSpawnType.COMMAND) {
            return true;
        }
        BlockPos pos = this.blockPosition();
        for (int k = -5; k <= 5; ++k) {
            for (int j = -5; j <= 5; ++j) {
                for (int i = -2; i <= 6; ++i) {
                    BlockPos check = pos.offset(j, i, k);
                    if (MyUtils.getBlockStateForSpawnRules(level, check).getBlock() == Blocks.SPAWNER) {
                        if (level instanceof Level world) {
                            BlockEntity be = world.getBlockEntity(check);
                            if (be instanceof SpawnerBlockEntity spawner) {
                                ResourceLocation id =
                                        SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                                if (id != null && "nightmare".equalsIgnoreCase(id.getPath())) {
                                    if (this.getPitchBlackScale() > 1.0f) {
                                        this.setPitchBlackScale(1.0f);
                                    }
                                    this.setSpawnedFromSpawner();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (level.getMaxLocalRawBrightness(pos) > 7) {
            return false;
        }
        if (this.level().isDay()) {
            return false;
        }
        if (this.level().dimension().equals(ChaosPersists.getDimensionKey(6))) {
            if (!this.level()
                    .getEntitiesOfClass(PitchBlack.class, this.getBoundingBox().inflate(16.0, 16.0, 16.0))
                    .isEmpty()) {
                return false;
            }
        }
        float scale = this.getPitchBlackScale();
        if (scale < 1.1f) {
            return true;
        }
        int ix = scale > 3.1f ? 2 : 1;
        int iy = ix * 3;
        for (int k = -ix; k <= ix; ++k) {
            for (int j = -ix; j <= ix; ++j) {
                for (int i = 1; i <= iy; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public EntityDimensions getDimensions(net.minecraft.world.entity.Pose pose) {
        float scale = this.getPitchBlackScale();
        return EntityDimensions.fixed(2.5f * scale, 3.5f * scale);
    }
}
