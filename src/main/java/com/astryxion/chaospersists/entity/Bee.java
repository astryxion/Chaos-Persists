package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Bee extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Bee.class, EntityDataSerializers.BYTE);
    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private Entity rt = null;

    public Bee(EntityType<? extends Bee> type, Level level) {
        super(type, level);
        this.xpReward = 25;
        this.targetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Bee_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3199999928474426)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Bee_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Bee_stats.defense);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    protected float getSoundVolume() {
        return 0.25f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.BEEBUZZ;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DRAGONFLY_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
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
        return ChaosPersists.Bee_stats.health;
    }

    private void dropItemRand(Item index, int par1) {
        if (index == null) {
            return;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        is);
        this.level().addFreshEntity(entityItem);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4 = 2 + this.getRandom().nextInt(10);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        var4 = 2 + this.getRandom().nextInt(10);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(ChaosPersists.MyButterCandy, 1);
        }
        var4 = 2 + this.getRandom().nextInt(10);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(Items.DANDELION, 1);
        }
        var4 = 2 + this.getRandom().nextInt(10);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(Items.SUGAR, 1);
        }
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y * 0.6, motion.z);
        if (this.isInWater() && this.getRandom().nextInt(4) == 1) {
            this.doHurtTarget(this);
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        boolean var4 = par1Entity.hurt(this.damageSources().mobAttack(this), (float) ChaosPersists.Bee_stats.attack);
        if (this.getRandom().nextInt(3) == 1 && par1Entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.POISON, 50, 0));
        }
        return var4;
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
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        int keepTrying = 50;
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
        if (this.stuck_count > 50
                || this.getRandom().nextInt(300) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            this.stuck_count = 0;
            while (!bid.isAir() && keepTrying != 0) {
                int zdir = this.getRandom().nextInt(9) + 4;
                int xdir = this.getRandom().nextInt(9) + 4;
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + this.getRandom().nextInt(6) - 3,
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
        } else {
            LivingEntity e = this.getTarget();
            if (e != null && (!e.isAlive() || !this.isSuitableTarget(e, false))) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.rt instanceof LivingEntity living && living.isAlive() ? living : null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                this.setAttacking(1);
                this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY() + 1, (int) e.getZ());
                if (this.distanceToSqr(e) < 16.0) {
                    this.doHurtTarget(e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.5 - motion.x) * 0.30000000149011613,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.20000000149011612,
                        (Math.signum(var5) * 0.5 - motion.z) * 0.30000000149011613));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 4.0f);
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
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = super.hurt(par1DamageSource, par2);
        Entity e = par1DamageSource.getEntity();
        if (e instanceof LivingEntity living && this.currentFlightTarget != null) {
            this.rt = e;
            this.currentFlightTarget = new BlockPos((int) living.getX(), (int) living.getY(), (int) living.getZ());
        }
        return ret;
    }

    public static boolean checkBeeSpawnRules(
            EntityType<Bee> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (level.getLevel().dimension().equals(ChaosPersists.getDimensionKey(4))) {
            return true;
        }
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Bee".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        if (this.level().dimension().equals(ChaosPersists.getDimensionKey(4))) {
            return true;
        }
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Bee".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level().isDay()) {
            return false;
        }
        return true;
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving.isInWater() || par1EntityLiving.getFluidHeight(FluidTags.WATER) > 0.0) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.isCreative();
        }
        if (par1EntityLiving instanceof Villager) {
            return true;
        }
        String n = par1EntityLiving.getClass().getSimpleName();
        if ("Girlfriend".equals(n) || "Boyfriend".equals(n)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(10.0, 6.0, 10.0));
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

    public void forceAttackTarget(LivingEntity target) {
        if (target == null || !target.isAlive()) {
            return;
        }
        this.rt = target;
        this.currentFlightTarget = new BlockPos((int) target.getX(), (int) target.getY() + 1, (int) target.getZ());
        this.setAttacking(1);
        if (this.distanceToSqr(target) < 16.0) {
            this.doHurtTarget(target);
        }
    }
}
