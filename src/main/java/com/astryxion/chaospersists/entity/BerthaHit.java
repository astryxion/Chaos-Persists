package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.FriendlyWeaponHits;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BerthaHit extends ThrowableProjectile {
    /** 1.7.10 {@code EntityThrowable#getThrowerSpeed()}; 1.20.1 port wrongly used 0.4f. */
    private static final float THROW_SPEED = 1.5f;

    private int hit_type = 0;

    public BerthaHit(EntityType<? extends BerthaHit> type, Level level) {
        super(type, level);
    }

    public BerthaHit(EntityType<? extends BerthaHit> type, Level level, int par2) {
        super(type, level);
    }

    public BerthaHit(EntityType<? extends BerthaHit> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
        this.setPos(
                shooter.getX(),
                shooter.getY() + shooter.getEyeHeight(),
                shooter.getZ());
        this.setYRot(shooter.getYRot());
        this.setXRot(shooter.getXRot());
        this.setPos(
                this.getX() - Mth.cos(this.getYRot() * Mth.DEG_TO_RAD) * 0.16f,
                this.getY() - 0.1,
                this.getZ() - Mth.sin(this.getYRot() * Mth.DEG_TO_RAD) * 0.16f);
        float f = 0.4f;
        float yawRad = this.getYRot() * Mth.DEG_TO_RAD;
        float pitchRad = this.getXRot() * Mth.DEG_TO_RAD;
        double mx = -Mth.sin(yawRad) * Mth.cos(pitchRad) * f;
        double mz = Mth.cos(yawRad) * Mth.cos(pitchRad) * f;
        double my = -Mth.sin(pitchRad) * f;
        this.shoot(mx, my, mz, THROW_SPEED, 0.1f);
    }

    public BerthaHit(EntityType<? extends BerthaHit> type, LivingEntity shooter, Level level, int par3) {
        super(type, shooter, level);
    }

    public BerthaHit(EntityType<? extends BerthaHit> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    public void setHitType(int i) {
        this.hit_type = i;
    }

    private double maxOwnerReachDistanceSq() {
        return switch (this.hit_type) {
            case 2 -> 101.0;
            case 3 -> 64.0;
            default -> 81.0;
        };
    }

    /**
     * Resolve extended-reach hits on the swing tick. OreSpawn's BerthaHit flew fast enough
     * in 1.7.10 to feel instant; the 1.20.1 port was far slower and added air drag.
     *
     * <p>Entity-only along the aim vector: {@link ProjectileUtil#getHitResultOnMoveVector}
     * also hits blocks, so high/low swings into terrain never reached the target.
     */
    public boolean tryHitAlongPath() {
        Entity owner = this.getOwner();
        if (owner == null) {
            return false;
        }

        Vec3 motion = this.getDeltaMovement();
        double speed = motion.length();
        if (speed < 1.0E-6) {
            return false;
        }

        double maxReach = Math.sqrt(this.maxOwnerReachDistanceSq());
        Vec3 start = this.position();
        Vec3 end = start.add(motion.normalize().scale(maxReach));
        AABB searchBox = this.getBoundingBox().expandTowards(end.subtract(start)).inflate(1.0D);

        EntityHitResult entityHit =
                ProjectileUtil.getEntityHitResult(
                        this.level(), this, start, end, searchBox, this::canHitEntity);
        if (entityHit != null) {
            Entity target = entityHit.getEntity();
            if (target != null
                    && this.distanceToSqr(owner) <= this.maxOwnerReachDistanceSq()
                    && owner.distanceToSqr(target) <= this.maxOwnerReachDistanceSq()) {
                this.onHit(entityHit);
                return true;
            }
        }
        return false;
    }

    /**
     * Re-aim after {@code moveTo} using the shooter's head look (pitch included). Body yaw alone
     * made high/low swings fly sideways instead of at the target.
     */
    public void aimFromShooter(LivingEntity shooter, double speedMul) {
        float yaw = shooter.getYHeadRot();
        float pitch = shooter.getXRot();
        this.setYRot(yaw);
        this.setXRot(pitch);
        float f = 0.4f;
        float yawRad = yaw * Mth.DEG_TO_RAD;
        float pitchRad = pitch * Mth.DEG_TO_RAD;
        double mx = -Mth.sin(yawRad) * Mth.cos(pitchRad) * f;
        double mz = Mth.cos(yawRad) * Mth.cos(pitchRad) * f;
        double my = -Mth.sin(pitchRad) * f;
        this.shoot(mx, my, mz, THROW_SPEED, 0.0f);
        Vec3 dm = this.getDeltaMovement();
        this.setDeltaMovement(dm.x * speedMul, dm.y * speedMul, dm.z * speedMul);
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 motion = this.getDeltaMovement();
        if (!this.isNoGravity()) {
            motion = new Vec3(motion.x, motion.y - 0.03D, motion.z);
            this.setDeltaMovement(motion);
        }

        HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hit.getType() != HitResult.Type.MISS) {
            this.onHit(hit);
            return;
        }

        this.setPos(this.getX() + motion.x, this.getY() + motion.y, this.getZ() + motion.z);
    }

    @Override
    protected void onHit(HitResult result) {
        if (this.isRemoved()) {
            return;
        }
        if (result.getType() == HitResult.Type.ENTITY && this.getOwner() != null) {
            EntityHitResult entityHit = (EntityHitResult) result;
            Entity e = entityHit.getEntity();
            Entity owner = this.getOwner();
            if (FriendlyWeaponHits.isCompanion(e)
                    || FriendlyWeaponHits.isListedIgnore(e)
                    || (ChaosPersists.big_bertha_pvp == 0
                            && FriendlyWeaponHits.isFriendlyWhenPvpOff(e))) {
                this.discard();
                return;
            }
            if (this.hit_type == 0
                    && this.distanceToSqr(owner) < 81.0
                    && e != owner
                    && owner instanceof Player player) {
                e.hurt(this.damageSources().playerAttack(player), (float) ChaosPersists.bertha_stats.damage);
                e.setRemainingFireTicks(200);
                double ks = 2.25;
                double inair = 0.35;
                float f3 = (float) Math.atan2(e.getZ() - owner.getZ(), e.getX() - owner.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            if (this.hit_type == 2
                    && this.distanceToSqr(owner) < 101.0
                    && e != owner
                    && owner instanceof Player player) {
                e.hurt(this.damageSources().playerAttack(player), (float) ChaosPersists.royal_stats.damage);
                double ks = 1.5;
                double inair = 0.25;
                float f3 = (float) Math.atan2(e.getZ() - owner.getZ(), e.getX() - owner.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            if (this.hit_type == 3
                    && this.distanceToSqr(owner) < 64.0
                    && e != owner
                    && owner instanceof Player player) {
                e.hurt(this.damageSources().playerAttack(player), (float) ChaosPersists.hammy_stats.damage);
                double ks = 1.25;
                double inair = 0.65;
                float f3 = (float) Math.atan2(e.getZ() - owner.getZ(), e.getX() - owner.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
                if (!this.level().isClientSide
                        && this.hit_type == 3
                        && this.distanceToSqr(owner) < 64.0) {
                    this.level()
                            .explode(
                                    null,
                                    this.getX(),
                                    this.getY(),
                                    this.getZ(),
                                    1.5f,
                                    true,
                                    this.level()
                                                    .getGameRules()
                                                    .getBoolean(GameRules.RULE_MOBGRIEFING)
                                            ? ExplosionInteraction.MOB
                                            : ExplosionInteraction.NONE);
                }
            }
        } else if (!this.level().isClientSide
                && this.hit_type == 3
                && this.getOwner() != null
                && this.distanceToSqr(this.getOwner()) < 64.0) {
            this.level()
                    .explode(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            2.1f,
                            true,
                            this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                    ? ExplosionInteraction.MOB
                                    : ExplosionInteraction.NONE);
        }
        this.discard();
    }

    @Override
    protected void defineSynchedData() {}
}
