package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class LaserBall extends ThrowableProjectile {
    private float my_rotation = 0.0f;
    private int my_index = 81;
    private int is_special = 0;
    private int is_iceball = 0;
    private int is_acid = 0;
    private int is_irukandji = 0;
    private int ticksalive = 0;

    public LaserBall(EntityType<? extends LaserBall> type, Level level) {
        super(type, level);
    }

    public LaserBall(EntityType<? extends LaserBall> type, Level level, int par2) {
        super(type, level);
    }

    public LaserBall(EntityType<? extends LaserBall> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    public LaserBall(EntityType<? extends LaserBall> type, LivingEntity shooter, Level level, int par3) {
        super(type, shooter, level);
    }

    public LaserBall(EntityType<? extends LaserBall> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    public int getLaserBallIndex() {
        return this.my_index;
    }

    public void setSpecial() {
        this.is_special = 1;
    }

    public void setIceBall() {
        this.is_iceball = 1;
    }

    public void setAcid() {
        this.is_acid = 1;
    }

    public void setIrukandji() {
        this.is_irukandji = 1;
        this.is_acid = 1;
    }

    @Override
    protected void onHit(HitResult result) {
        if (this.level().isClientSide) {
            return;
        }
        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit = (EntityHitResult) result;
            Entity hit = entityHit.getEntity();
            float var2 = 16.0f;

            if (this.is_irukandji != 0) {
                hit.hurt(this.damageSources().thrown(this, this.getOwner()), 100.0f);
                this.discard();
                return;
            }

            if (this.is_acid != 0) {
                if (isEntityClass(hit, "TrooperBug")) {
                    this.discard();
                    return;
                }
                if (isEntityClass(hit, "SpitBug")) {
                    this.discard();
                    return;
                }
            }
            if (this.is_iceball == 0 && this.is_acid == 0) {
                if (isEntityClass(hit, "Robot2")) {
                    this.discard();
                    return;
                }
                if (isEntityClass(hit, "Robot3")) {
                    this.discard();
                    return;
                }
                if (isEntityClass(hit, "Robot4")) {
                    this.discard();
                    return;
                }
                if (isEntityClass(hit, "Robot5")) {
                    this.discard();
                    return;
                }
                if (isEntityClass(hit, "GiantRobot")) {
                    this.discard();
                    return;
                }
            }
            if (isEntityClass(hit, "Dragon") && this.is_acid == 0) {
                if (!hit.getPassengers().isEmpty()) {
                    this.discard();
                    return;
                }

                if (getDragonTypeFrom(hit) != 0 && this.is_iceball != 0) {
                    this.discard();
                    return;
                }
            }

            if (hit instanceof Player player && this.is_acid == 0) {
                if (player.getVehicle() != null) {
                    this.discard();
                    return;
                }
            }

            hit.hurt(this.damageSources().thrown(this, this.getOwner()), var2);
            if (this.is_iceball == 0 && hit instanceof net.minecraft.world.entity.LivingEntity living) {
                living.setSecondsOnFire(8);
            }
        } else if (this.is_irukandji != 0) {
            if (ChaosPersists.MyIrukandji != null) {
                this.spawnAtLocation(new ItemStack(ChaosPersists.MyIrukandji, 1));
            }
        }

        if (this.is_acid == 0) {
            int mx = 10;
            if (this.is_special != 0) {
                mx = 20;
            }
            for (int var3 = 0; var3 < mx; ++var3) {
                this.level()
                        .addParticle(
                                ParticleTypes.SMOKE,
                                this.getX() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getY() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getZ() + this.random.nextFloat(),
                                0.0,
                                0.0,
                                0.0);
                this.level()
                        .addParticle(
                                ParticleTypes.LARGE_SMOKE,
                                this.getX() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getY() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getZ() + this.random.nextFloat(),
                                0.0,
                                0.0,
                                0.0);
                this.level()
                        .addParticle(
                                ParticleTypes.FIREWORK,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                this.random.nextGaussian(),
                                this.random.nextGaussian(),
                                this.random.nextGaussian());
            }

            this.playSound(SoundEvents.GENERIC_EXPLODE, 0.5f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
            if (this.is_special != 0 || this.is_iceball != 0) {
                this.level()
                        .explode(
                                this,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                3.0f,
                                com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)
                                        ? ExplosionInteraction.MOB
                                        : ExplosionInteraction.NONE);
            }
        }
        this.discard();
    }

    @Override
    public void tick() {
        ++this.ticksalive;
        if (this.ticksalive > 200) {
            this.discard();
            return;
        }
        super.tick();
        this.my_rotation += 50.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.setXRot(this.my_rotation);
        this.xRotO = this.my_rotation;
        if (this.is_acid != 0) {
            return;
        }
        int mx = 4;
        if (this.is_special != 0) {
            mx = 10;
        }
        if (this.is_iceball != 0 && this.is_special == 0) {
            mx = 2;
        }
        for (int i = 0; i < mx; ++i) {
            this.level()
                    .addParticle(
                            ParticleTypes.FIREWORK,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            this.random.nextGaussian() / 2.0,
                            this.random.nextGaussian() / 2.0,
                            this.random.nextGaussian() / 2.0);
            if (this.is_iceball != 0) {
                continue;
            }
            // 1.7.10 reddust with gaussian/10 RGB reads near-black; do not use DustParticleOptions.REDSTONE.
            ParticleOptions dust = new DustParticleOptions(new Vector3f(0.0f, 0.0f, 0.0f), 1.0f);
            this.level()
                    .addParticle(
                            dust,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            this.random.nextGaussian() / 10.0,
                            this.random.nextGaussian() / 10.0,
                            this.random.nextGaussian() / 10.0);
        }
    }

    private static boolean isEntityClass(Entity hit, String simpleName) {
        return hit.getClass().getSimpleName().equals(simpleName);
    }

    private static int getDragonTypeFrom(Entity hit) {
        if (!isEntityClass(hit, "Dragon")) {
            return 0;
        }
        try {
            return (Integer) hit.getClass().getMethod("getDragonType").invoke(hit);
        } catch (ReflectiveOperationException ex) {
            return 0;
        }
    }

    @Override
    protected void defineSynchedData() {
    }
}
