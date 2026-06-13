package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.TrooperBug;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.registries.ForgeRegistries;

public class LaserBall extends ThrowableEntity {
    private float my_rotation = 0.0f;
    private int my_index = 81;
    private int is_special = 0;
    private int is_iceball = 0;
    private int is_acid = 0;
    private int is_irukandji = 0;
    private int ticksalive = 0;

    @Override
    protected void defineSynchedData() {
    }

    public LaserBall(EntityType<? extends LaserBall> type, World level) {
        super(type, level);
    }

    public LaserBall(World level) {
        this(laserBallType(), level);
    }

    public LaserBall(World level, int par2) {
        this(level);
    }

    public LaserBall(World level, LivingEntity thrower) {
        super(laserBallType(), thrower, level);
    }

    protected LaserBall(EntityType<? extends LaserBall> type, LivingEntity thrower, World level) {
        super(type, thrower, level);
    }

    public LaserBall(World level, LivingEntity thrower, int par3) {
        this(level, thrower);
    }

    public LaserBall(World level, double x, double y, double z) {
        super(laserBallType(), x, y, z, level);
    }

    protected LaserBall(EntityType<? extends LaserBall> type, double x, double y, double z, World level) {
        super(type, x, y, z, level);
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends LaserBall> laserBallType() {
        return (EntityType<? extends LaserBall>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "laser_ball"));
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
    protected void onHit(RayTraceResult hitResult) {
        if (this.level.isClientSide) {
            return;
        }
        if (hitResult.getType() == RayTraceResult.Type.ENTITY) {
            Entity entityHit = ((EntityRayTraceResult) hitResult).getEntity();
            float var2 = 16.0F;

            if (this.is_irukandji != 0) {
                entityHit.hurt(DamageSource.thrown(this, this.getOwner()), 100.0F);
                this.remove();
                return;
            }

            if (this.is_acid != 0) {
                if (entityHit instanceof TrooperBug) {
                    this.remove();
                    return;
                }
                if (entityHit instanceof SpitBug) {
                    this.remove();
                    return;
                }
            }
            if (this.is_iceball == 0 && this.is_acid == 0) {
                if (entityHit instanceof Robot2) {
                    this.remove();
                    return;
                }
                if (entityHit instanceof Robot3) {
                    this.remove();
                    return;
                }
                if (entityHit instanceof Robot4) {
                    this.remove();
                    return;
                }
                if (entityHit instanceof Robot5) {
                    this.remove();
                    return;
                }
                if (entityHit instanceof GiantRobot) {
                    this.remove();
                    return;
                }
            }
            if (entityHit instanceof Dragon && this.is_acid == 0) {
                Dragon d = (Dragon) entityHit;
                if (!d.getPassengers().isEmpty()) {
                    this.remove();
                    return;
                }
                if (d.getDragonType() != 0 && this.is_iceball != 0) {
                    this.remove();
                    return;
                }
            }

            if (entityHit instanceof PlayerEntity && this.is_acid == 0) {
                PlayerEntity d = (PlayerEntity) entityHit;
                if (d.getVehicle() != null) {
                    this.remove();
                    return;
                }
            }

            entityHit.hurt(DamageSource.thrown(this, this.getOwner()), var2);
            if (this.is_iceball == 0) {
                entityHit.setSecondsOnFire(1);
            }
        } else if (this.is_irukandji != 0) {
            this.spawnAtLocation(new ItemStack(ChaosPersists.MyIrukandji));
        }

        if (this.is_acid == 0) {
            int mx = 10;
            if (this.is_special != 0) {
                mx = 20;
            }
            for (int var3 = 0; var3 < mx; ++var3) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX() + this.random.nextFloat() - this.random.nextFloat(), this.getY() + this.random.nextFloat() - this.random.nextFloat(), this.getZ() + this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
                this.level.addParticle(ParticleTypes.SMOKE, this.getX() + this.random.nextFloat() - this.random.nextFloat(), this.getY() + this.random.nextFloat() - this.random.nextFloat(), this.getZ() + this.random.nextFloat() - this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
                this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.level.random.nextGaussian(), this.level.random.nextGaussian(), this.level.random.nextGaussian());
            }

            this.playSound(SoundEvents.GENERIC_EXPLODE, 0.5F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.5F);
            if (this.is_special != 0 || this.is_iceball != 0) {
                boolean grief = this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
                this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3.0F, grief ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            }
        }
        this.remove();
    }

    @Override
    public void tick() {
        ++this.ticksalive;
        if (this.ticksalive > 200) {
            this.remove();
            return;
        }
        super.tick();
        this.my_rotation += 50.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.xRot = this.xRotO = this.my_rotation;
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
            this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.level.random.nextGaussian() / 2.0, this.level.random.nextGaussian() / 2.0, this.level.random.nextGaussian() / 2.0);
            if (this.is_iceball != 0) {
                continue;
            }
            this.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), this.getX(), this.getY(), this.getZ(), this.level.random.nextGaussian() / 10.0, this.level.random.nextGaussian() / 10.0, this.level.random.nextGaussian() / 10.0);
        }
    }
}
