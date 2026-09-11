package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.item.ItemEntity;
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

public class Vortex extends Monster {
    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;
    private int winded = 0;
    private int busy_fighting = 0;
    private int was_spawnered = 0;

    public Vortex(EntityType<? extends Vortex> type, Level level) {
        super(type, level);
        this.xpReward = 200;
        this.targetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Vortex_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3499999940395355)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Vortex_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Vortex_stats.defense);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void doPush(Entity entity) {
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.busy_fighting != 0) {
            return false;
        }
        if (this.was_spawnered != 0) {
            return false;
        }
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Vortex_stats.health;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.VORTEXLIVE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.VORTEXLIVE;
    }

    @Override
    public void tick() {
        LivingEntity e;
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
        this.busy_fighting = 0;
        e = this.findSomethingToAttack();
        if (e != null) {
            this.busy_fighting = 1;
            if (this.level().isClientSide) {
                for (int i = 0; i < 20; ++i) {
                    double d = this.getRandom().nextDouble() * 3.5;
                    d *= d;
                    double dir = this.getRandom().nextDouble() * 2.0 * Math.PI;
                    double dx = Math.cos(dir -= Math.PI) * d / 2.0;
                    double dz = Math.sin(dir) * d / 2.0;
                    this.level()
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX() + dx,
                                    this.getY() + 0.75 + d,
                                    this.getZ() + dz,
                                    Math.cos(dir) * (double) this.getRandom().nextFloat() / 4.0,
                                    (double) (this.getRandom().nextFloat() / 2.0f),
                                    Math.sin(dir += 1.5707963267948966)
                                            * (double) this.getRandom().nextFloat()
                                            / 4.0);
                }
            }
        }
        if (!this.level().isClientSide && this.getRandom().nextInt(200) == 1) {
            this.heal(1.0f);
        }
        if (this.isPersistenceRequired()) {
            return;
        }
        if (this.busy_fighting != 0) {
            return;
        }
        if (this.was_spawnered != 0) {
            return;
        }
        if (!this.level().isClientSide) {
            long t = this.level().getDayTime() % 24000L;
            if (t < 12000L && this.getRandom().nextInt(500) == 1) {
                this.discard();
            }
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
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        LivingEntity e = null;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.winded > 0) {
            --this.winded;
        }
        if (this.getRandom().nextInt(300) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (!bid.isAir() && keep_trying != 0) {
                zdir = this.getRandom().nextInt(14) + 10;
                xdir = this.getRandom().nextInt(14) + 10;
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
                --keep_trying;
            }
        }
        if ((e = this.findSomethingToAttack()) != null) {
            this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY(), (int) e.getZ());
            double d = this.distanceToSqr(e);
            if (d < 81.0 && this.winded == 0) {
                double a = Math.atan2(this.getZ() - e.getZ(), this.getX() - e.getX());
                double pm = 1.0;
                if (e instanceof Player) {
                    pm = 2.0;
                }
                double pull = (10.0 - Math.sqrt(d)) * 0.10000000149011612;
                double pullY = (10.0 - Math.sqrt(d)) * 0.05000000074505806 * pm;
                e.push(Math.cos(a) * pull, pullY, Math.sin(a) * pull);
            }
            float reach = 4.0f + e.getBbWidth() / 2.0f;
            if (this.distanceToSqr(e) < (double) (reach * reach) && this.getRandom().nextInt(8) == 2) {
                this.doHurtTarget(e);
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.4 - motion.x) * 0.2,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.20000000149011612,
                        (Math.signum(var5) * 0.4 - motion.z) * 0.2));
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

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity attacker = source.getEntity();
        boolean ret = super.hurt(source, amount);
        if (attacker != null && this.currentFlightTarget != null) {
            this.currentFlightTarget =
                    new BlockPos((int) attacker.getX(), (int) attacker.getY(), (int) attacker.getZ());
        }
        this.winded = 20;
        return ret;
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
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
        }
        if (par1EntityLiving instanceof Vortex) {
            return false;
        }
        if (par1EntityLiving instanceof Rotator) {
            return false;
        }
        if (par1EntityLiving instanceof Peacock) {
            return false;
        }
        if (par1EntityLiving instanceof Irukandji) {
            return false;
        }
        if (par1EntityLiving instanceof Skate) {
            return false;
        }
        if (par1EntityLiving instanceof Whale) {
            return false;
        }
        if (par1EntityLiving instanceof Flounder) {
            return false;
        }
        if (par1EntityLiving instanceof Urchin) {
            return false;
        }
        if (par1EntityLiving instanceof CrystalCow) {
            return false;
        }
        String targetClassName = par1EntityLiving.getClass().getSimpleName();
        if (targetClassName.equals("Mothra") || targetClassName.equals("Brutalfly")) {
            return false;
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(16.0, 10.0, 16.0));
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

    private void dropItemRand(Item index, int par1) {
        if (index == null) {
            return;
        }
        ItemStack is = new ItemStack(index, par1);
        double ox = ChaosPersists.ChaosRand.nextInt(6) - ChaosPersists.ChaosRand.nextInt(6);
        double oz = ChaosPersists.ChaosRand.nextInt(6) - ChaosPersists.ChaosRand.nextInt(6);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ox,
                        this.getY() + 1.0 + (double) this.getRandom().nextInt(10),
                        this.getZ() + oz,
                        is);
        this.level().addFreshEntity(entityItem);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.VortexEye, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int i = 5 + this.getRandom().nextInt(7);
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(10);
            if (var3 == 0) {
                this.dropItemRand(Items.STICK, 1);
            }
            if (var3 == 1) {
                this.dropItemRand(ChaosPersists.MyTigersEyeIngot, 1);
            }
            if (var3 == 2) {
                this.dropItemRand(ChaosPersists.MyCrystalPinkIngot, 1);
            }
            if (var3 == 3) {
                this.dropItemRand(Items.IRON_INGOT, 1);
            }
            if (var3 == 4) {
                this.dropItemRand(ChaosPersists.UraniumNugget, 1);
            }
            if (var3 == 6) {
                this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
            }
            if (var3 == 7) {
                this.dropItemRand(ChaosPersists.MyIrukandji, 1);
            }
            if (var3 == 8) {
                this.dropItemRand(ChaosPersists.CrystalCoal.asItem(), 1);
            }
        }
    }

    protected Item getDropItem() {
        return ChaosPersists.FairyEgg;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Vortex".equals(id.getPath())) {
                        this.was_spawnered = 1;
                        return true;
                    }
                }
            }
        }
        for (int k = -2; k <= 2; ++k) {
            for (int j = -2; j <= 2; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (!MyUtils.getBlockStateForSpawnRules(level, checkPos).isAir()) {
                        return false;
                    }
                }
            }
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (!CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            long t = level.getLevelData().getDayTime() % 24000L;
            if (t < 12000L) {
                return false;
            }
            if (this.getRandom().nextInt(2) != 1) {
                return false;
            }
        }
        if (!this.level()
                .getEntitiesOfClass(
                        Vortex.class, this.getBoundingBox().inflate(20.0, 16.0, 20.0), v -> v != this)
                .isEmpty()) {
            return false;
        }
        return true;
    }

    protected boolean isValidLightLevel(LevelAccessor level) {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            return true;
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_VORTEX.get(),
                    serverLevel,
                    MobSpawnType.NATURAL,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
