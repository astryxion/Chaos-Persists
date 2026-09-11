package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Squid;
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
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Mantis extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Mantis.class, EntityDataSerializers.BYTE);
    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private Entity rt = null;

    public Mantis(EntityType<? extends Mantis> type, Level level) {
        super(type, level);
        this.xpReward = 100;
        this.targetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Mantis_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3199999928474426)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Mantis_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Mantis_stats.defense);
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
        return 0.35f;
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

    public int mygetMaxHealth() {
        return ChaosPersists.Mantis_stats.health;
    }

    private void dropItemRand(Item index, int par1) {
        if (index == null) {
            return;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5),
                        is);
        this.level().addFreshEntity(entityItem);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.MyMantisClaw, 1);
        this.dropItemRand(ChaosPersists.MyMantisClaw, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var4 = 2 + this.getRandom().nextInt(10);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        var4 = 1 + this.getRandom().nextInt(3);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(ChaosPersists.UraniumNugget, 1);
        }
        var4 = 1 + this.getRandom().nextInt(3);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
        }
        var4 = 2 + this.getRandom().nextInt(3);
        for (int i = 0; i < var4; ++i) {
            this.dropItemRand(Items.DIAMOND, 1);
        }
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y * 0.6, motion.z);
        if (this.isInWater() && this.getRandom().nextInt(20) == 1) {
            this.doHurtTarget(this);
        }
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
                float reach = 5.0f + e.getBbWidth() / 2.0f;
                if (this.distanceToSqr(e) < (double) (reach * reach)) {
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
        if (this.getRandom().nextInt(100) == 1) {
            this.heal(1.0f);
        }
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

    public static boolean checkMantisSpawnRules(
            EntityType<Mantis> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k <= 2; ++k) {
            for (int j = -2; j <= 2; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Mantis".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 1; i < 6; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        if (level.getLevel().dimension().equals(ChaosPersists.getDimensionKey(6)) && random.nextInt(6) != 0) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        List<Mantis> nearby =
                level.getLevel()
                        .getEntitiesOfClass(Mantis.class, new AABB(pos).inflate(32.0, 16.0, 32.0));
        return nearby.isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k <= 2; ++k) {
            for (int j = -2; j <= 2; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Mantis".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 1; i < 6; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        if (this.level().dimension().equals(ChaosPersists.getDimensionKey(6)) && this.getRandom().nextInt(6) != 0) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level().isDay()) {
            return false;
        }
        return this.level()
                .getEntitiesOfClass(Mantis.class, this.getBoundingBox().inflate(32.0, 16.0, 32.0))
                .isEmpty();
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
        if (par1EntityLiving instanceof Mantis) {
            return false;
        }
        if (par1EntityLiving instanceof Irukandji) {
            return false;
        }
        if (par1EntityLiving instanceof Skate) {
            return false;
        }
        if (par1EntityLiving instanceof Flounder) {
            return false;
        }
        if (par1EntityLiving instanceof Whale) {
            return false;
        }
        if (par1EntityLiving instanceof Squid) {
            return false;
        }
        if (par1EntityLiving instanceof WaterDragon) {
            return false;
        }
        if (par1EntityLiving instanceof AttackSquid) {
            return false;
        }
        String n = par1EntityLiving.getClass().getSimpleName();
        if ("TerribleTerror".equals(n)
                || "LurkingTerror".equals(n)
                || "CloudShark".equals(n)
                || "Rotator".equals(n)
                || "Bee".equals(n)) {
            return false;
        }
        if (par1EntityLiving instanceof Mothra) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return true;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return true;
        }
        if (par1EntityLiving instanceof Cockateil) {
            return true;
        }
        if ("Fairy".equals(par1EntityLiving.getClass().getSimpleName())) {
            return true;
        }
        if (MyUtils.isAttackableNonMob(par1EntityLiving)) {
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
                                LivingEntity.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0));
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
