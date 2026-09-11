package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import com.astryxion.chaospersists.util.MyUtils;

public class CloudShark extends Monster {
    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;

    public CloudShark(EntityType<? extends CloudShark> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.targetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.CloudShark_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.30000001192092896)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.CloudShark_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.CloudShark_stats.defense);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.level().isDay()) {
            return false;
        }
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.CloudShark_stats.health;
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
        return SoundEvents.GENERIC_SPLASH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.LITTLE_SPLAT;
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
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
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
        int xdir = 1;
        int zdir = 1;
        int keepTrying = 50;
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
            while (!bid.isAir() && keepTrying != 0) {
                zdir = this.getRandom().nextInt(10) + 8;
                xdir = this.getRandom().nextInt(10) + 8;
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + this.getRandom().nextInt(5) - 2 + updown,
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
        }
        if (this.getRandom().nextInt(9) == 2) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY(), (int) e.getZ());
                if (this.distanceToSqr(e) < 9.0) {
                    this.doHurtTarget(e);
                }
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
    public boolean doHurtTarget(Entity par1Entity) {
        float f = (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
        return par1Entity.hurt(this.damageSources().mobAttack(this), f);
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
        if (e != null) {
            this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY(), (int) e.getZ());
        }
        return ret;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = this.getRandom().nextInt(3);
        Item drop = null;
        if (i == 0) {
            drop = Items.PAPER;
        } else if (i == 1) {
            drop = Items.STRING;
        } else if (i == 2) {
            drop = Items.BONE;
        }
        if (drop != null) {
            this.spawnAtLocation(drop);
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public static boolean checkCloudSharkSpawnRules(
            EntityType<CloudShark> type,
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof RockBase) {
            return false;
        }
        if (par1EntityLiving instanceof EntityAnt) {
            return false;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return true;
        }
        if (par1EntityLiving instanceof Cockateil) {
            return true;
        }
        if (par1EntityLiving instanceof EntityMosquito) {
            return true;
        }
        if (par1EntityLiving instanceof Firefly) {
            return true;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.isCreative();
        }
        if (par1EntityLiving instanceof GoldFish) {
            return true;
        }
        if (par1EntityLiving instanceof CliffRacer) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 10.0, 12.0));
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
