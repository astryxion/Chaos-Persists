package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.AttackSquid;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class WaterBall extends ThrowableProjectile {
    private float my_rotation = 0.0f;
    private int my_index = 49;

    public WaterBall(EntityType<? extends WaterBall> type, Level level) {
        super(type, level);
    }

    public WaterBall(EntityType<? extends WaterBall> type, LivingEntity thrower, Level level) {
        super(type, thrower, level);
    }

    public WaterBall(Level level) {
        this(ChaosPersists.ENTITY_TYPE_WATER_BALL.get(), level);
    }

    public WaterBall(Level level, LivingEntity thrower) {
        this(ChaosPersists.ENTITY_TYPE_WATER_BALL.get(), thrower, level);
    }

    public WaterBall(EntityType<? extends WaterBall> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    public WaterBall(Level level, double x, double y, double z) {
        this(ChaosPersists.ENTITY_TYPE_WATER_BALL.get(), x, y, z, level);
    }

    public int getWaterBallIndex() {
        return this.my_index;
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    public void tick() {
        super.tick();
        this.my_rotation += 30.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.setXRot(this.my_rotation);
        this.xRotO = this.my_rotation;
        if (this.level().isClientSide) {
            this.level()
                    .addParticle(
                            ParticleTypes.SPLASH,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            0.0,
                            0.0,
                            0.0);
        }
    }

    private static boolean isLegacyDragon(Entity entity) {
        return entity != null && entity.getClass().getName().endsWith(".Dragon");
    }

    private static int getLegacyDragonType(Entity entity) {
        if (!isLegacyDragon(entity)) {
            return 0;
        }
        try {
            return (Integer) entity.getClass().getMethod("getDragonType").invoke(entity);
        } catch (ReflectiveOperationException ex) {
            return 0;
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() == HitResult.Type.ENTITY) {
            Entity entity = ((EntityHitResult) result).getEntity();
            float damage = 2.0f;
            if (entity instanceof Creeper) {
                damage = 5.0f;
            }
            if (entity instanceof com.astryxion.chaospersists.entity.WaterDragon) {
                return;
            }
            if (entity instanceof AttackSquid) {
                return;
            }
            if (isLegacyDragon(entity) && getLegacyDragonType(entity) != 0) {
                return;
            }
            if (entity instanceof Player player && player.getVehicle() != null) {
                return;
            }
            Entity owner = this.getOwner();
            entity.hurt(
                    this.damageSources().thrown(this, owner instanceof LivingEntity ? (LivingEntity) owner : null),
                    damage);
            if (this.random.nextInt(10) == 1) {
                Item waterball =
                        ForgeRegistries.ITEMS.getValue(
                                new ResourceLocation("chaospersists", "waterball"));
                if (waterball != null) {
                    entity.spawnAtLocation(new ItemStack(waterball));
                }
            }
            entity.clearFire();
        }
        if (this.level().isClientSide) {
            for (int i = 0; i < 8; ++i) {
                this.level()
                        .addParticle(
                                ParticleTypes.BUBBLE,
                                this.getX() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getY() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getZ() + this.random.nextFloat(),
                                0.0,
                                0.0,
                                0.0);
                this.level()
                        .addParticle(
                                ParticleTypes.SPLASH,
                                this.getX() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getY() + this.random.nextFloat() - this.random.nextFloat(),
                                this.getZ() + this.random.nextFloat() - this.random.nextFloat(),
                                0.0,
                                0.0,
                                0.0);
            }
        }
        this.playSound(
                SoundEvents.GENERIC_SPLASH,
                0.5f,
                1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }
}
