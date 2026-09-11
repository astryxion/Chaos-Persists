package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.LaserBall;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Robot4 extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Robot4.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> STATE2 =
            SynchedEntityData.defineId(Robot4.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int reload_ticker = 0;
    private int was_attacked_ticker = 0;
    private float moveSpeed = 0.34f;

    public Robot4(EntityType<? extends Robot4> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 120;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(
                2, new MoveThroughVillageGoal(this, 0.8999999761581421, false, 4, () -> false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Robot4_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.34)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Robot4_stats.attack);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(STATE2, (byte) 0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isPersistenceRequired();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Robot4_stats.health;
    }

    public int getRobot4Health() {
        return (int) this.getHealth();
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

    @Override
    public int getArmorValue() {
        return ChaosPersists.Robot4_stats.defense;
    }

    @Override
    protected void jumpFromGround() {
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + 0.25, this.getDeltaMovement().z);
        super.jumpFromGround();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isClientSide) {
            if (this.getRandom().nextInt(3) == 1) {
                this.level()
                        .addParticle(
                                ParticleTypes.SMOKE,
                                this.getX()
                                        - 1.25
                                                * Math.sin(
                                                        Math.toRadians(this.getYRot() + 180.0f)),
                                this.getY() + 3.0 + (double) this.getRandom().nextFloat(),
                                this.getZ()
                                        + 1.25
                                                * Math.cos(
                                                        Math.toRadians(this.getYRot() + 180.0f)),
                                0.0,
                                (double) this.getRandom().nextFloat() / 2.0,
                                0.0);
            }
            if (this.getAttacking() != 0) {
                float shade = this.getRandom().nextFloat();
                this.level()
                        .addParticle(
                                new DustParticleOptions(new Vector3f(1.0F, shade, 0.0F), 1.0F),
                                this.getX()
                                        - 1.55
                                                * Math.sin(Math.toRadians(this.getYRot() + 35.0f)),
                                this.getY() + 2.25 + (double) this.getRandom().nextFloat(),
                                this.getZ()
                                        + 1.55
                                                * Math.cos(Math.toRadians(this.getYRot() + 35.0f)),
                                0.0,
                                0.0,
                                0.0);
            }
        }
    }

    public int getAttackStrength(Entity par1Entity) {
        int var2 = 0;
        if (this.level().getDifficulty() == Difficulty.EASY) {
            var2 = 15;
            if (this.level().getDifficulty() == Difficulty.NORMAL) {
                var2 = 20;
            } else if (this.level().getDifficulty() == Difficulty.HARD) {
                var2 = 25;
            }
        }
        return var2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(4) == 0) {
            return ChaosSounds.ROBOT_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ROBOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ROBOT_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var5 = 5 + this.getRandom().nextInt(10);
        for (int var4 = 0; var4 < var5; ++var4) {
            if (ChaosPersists.MyLaserBall != null) {
                this.spawnAtLocation(new ItemStack(ChaosPersists.MyLaserBall, 4));
            }
        }
        if (ChaosPersists.MyRayGun != null) {
            this.spawnAtLocation(new ItemStack(ChaosPersists.MyRayGun, 1));
        }
        this.spawnAtLocation(new ItemStack(Items.ITEM_FRAME, 1));
        int i = 10 + this.getRandom().nextInt(15);
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(15);
            switch (var3) {
                case 0:
                    this.spawnAtLocation(Items.REDSTONE);
                    break;
                case 1:
                    this.spawnAtLocation(Items.REPEATER);
                    break;
                case 2:
                    this.spawnAtLocation(Items.COMPARATOR);
                    break;
                case 3:
                    this.spawnAtLocation(Blocks.REDSTONE_BLOCK);
                    break;
                case 4:
                    this.spawnAtLocation(Blocks.DISPENSER);
                    break;
                case 5:
                    this.spawnAtLocation(Blocks.STICKY_PISTON);
                    break;
                case 6:
                    this.spawnAtLocation(Blocks.PISTON);
                    break;
                case 7:
                    this.spawnAtLocation(Blocks.LEVER);
                    break;
                case 8:
                    this.spawnAtLocation(Blocks.REDSTONE_BLOCK);
                    break;
                case 9:
                    this.spawnAtLocation(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        if (par1Entity instanceof LivingEntity living) {
            double ks = 2.0;
            double inair = 0.12;
            float f3 = (float) Math.atan2(living.getZ() - this.getZ(), living.getX() - this.getX());
            if (!living.isAlive() || living instanceof Player) {
                inair *= 2.0;
            }
            living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return super.doHurtTarget(par1Entity);
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.reload_ticker > 0) {
            --this.reload_ticker;
        }
        if (this.was_attacked_ticker > 0) {
            --this.was_attacked_ticker;
        }
        if (this.reload_ticker == 0 && this.getRandom().nextInt(8) == 1) {
            LivingEntity e = this.getTarget();
            if (this.getRandom().nextInt(50) == 1) {
                this.setTarget(null);
            }
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                if (this.distanceToSqr(e) < 256.0) {
                    float reach = 3.0f + e.getBbWidth() / 2.0f;
                    if (this.distanceToSqr(e) < (double) (reach * reach)) {
                        this.doHurtTarget(e);
                    } else {
                        double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        double rhdir = Math.toRadians((this.getYHeadRot() + 90.0f) % 360.0f);
                        double pi = 3.1415926545;
                        double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        if ((rdd = Math.abs(rdd)) < 0.5) {
                            double yoff = 2.0;
                            double xzoff = 1.75;
                            LaserBall projectile =
                                    new LaserBall(
                                            ChaosPersists.ENTITY_TYPE_LASER_BALL.get(),
                                            e.getX() - this.getX(),
                                            e.getY() - (this.getY() + yoff),
                                            e.getZ() - this.getZ(),
                                            this.level());
                            projectile.moveTo(
                                    this.getX()
                                            - xzoff
                                                    * Math.sin(
                                                            Math.toRadians(
                                                                    this.getYRot() + 45.0f)),
                                    this.getY() + yoff,
                                    this.getZ()
                                            + xzoff
                                                    * Math.cos(
                                                            Math.toRadians(
                                                                    this.getYRot() + 45.0f)),
                                    this.getYRot(),
                                    this.getXRot());
                            double var3 = e.getX() - projectile.getX();
                            double var5 = e.getY() - projectile.getY();
                            double var7 = e.getZ() - projectile.getZ();
                            float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                            projectile.shoot(var3, var5 + (double) var9, var7, 2.0f, 4.0f);
                            if (this.distanceToSqr(e) > 65.0) {
                                projectile.setSpecial();
                                this.reload_ticker = 30;
                                this.level()
                                        .playSound(
                                                null,
                                                this.getX(),
                                                this.getY(),
                                                this.getZ(),
                                                SoundEvents.FIREWORK_ROCKET_LAUNCH,
                                                SoundSource.HOSTILE,
                                                3.5f,
                                                0.5f);
                            } else {
                                this.reload_ticker = 10;
                                this.level()
                                        .playSound(
                                                null,
                                                this.getX(),
                                                this.getY(),
                                                this.getZ(),
                                                SoundEvents.FIREWORK_ROCKET_LAUNCH,
                                                SoundSource.HOSTILE,
                                                2.5f,
                                                1.0f);
                            }
                            this.level().addFreshEntity(projectile);
                        }
                        this.setAttacking(1);
                    }
                    this.getNavigation().moveTo(e, 0.75);
                }
            }
        }
        if (this.reload_ticker <= 0 && this.was_attacked_ticker <= 0) {
            this.setAttacking(0);
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if ("cactus".equals(par1DamageSource.getMsgId())) {
            return false;
        }
        if (this.getShielding() != 0 || this.was_attacked_ticker != 0) {
            return false;
        }
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        if (ret) {
            this.was_attacked_ticker = 65;
            this.setAttacking(1);
        }
        Entity src = par1DamageSource.getEntity();
        if (src instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
            ret = true;
        }
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
        if (par1EntityLiving instanceof Monster) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 4.0, 16.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> var2 = candidates.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (this.isSuitableTarget(var4, false)) {
                return var4;
            }
        }
        return null;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int getShielding() {
        return this.entityData.get(STATE2);
    }

    public void setShielding(int par1) {
        this.entityData.set(STATE2, (byte) par1);
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
                    if (id != null && "Robo-Warrior".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j <= 1; ++j) {
                for (int i = 1; i < 6; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.isAir() || state.is(Blocks.TALL_GRASS)) {
                        continue;
                    }
                    return false;
                }
            }
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_ROBOT4.get(),
                    serverLevel,
                    spawnReason,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
