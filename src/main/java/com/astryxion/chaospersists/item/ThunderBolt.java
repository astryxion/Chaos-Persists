package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.compat.illageandspillage.IllageAndSpillageExecuteCompat;
import com.astryxion.chaospersists.compat.mutantmonsters.MutantMonstersExecuteCompat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThunderBolt extends ThrowableProjectile {

    public ThunderBolt(EntityType<? extends ThunderBolt> type, Level level) {
        super(type, level);
    }

    public ThunderBolt(EntityType<? extends ThunderBolt> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    public ThunderBolt(EntityType<? extends ThunderBolt> type, LivingEntity shooter, Level level, int par3) {
        super(type, shooter, level);
    }

    public ThunderBolt(EntityType<? extends ThunderBolt> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit = (EntityHitResult) result;
            Entity hit = entityHit.getEntity();
            float var2 = 40.0f;
            if (isRoyalty(hit)) {
                this.discard();
                return;
            }
            hit.hurt(this.damageSources().thrown(this, this.getOwner()), var2 / 2.0f);
            if (this.getOwner() instanceof LivingEntity thrower) {
                hit.hurt(this.damageSources().mobAttack(thrower), var2 / 2.0f);
            }
            if (hit instanceof LivingEntity living) {
                MutantMonstersExecuteCompat.forceExecuteIfDowned(living);
                IllageAndSpillageExecuteCompat.forceExecuteIfDowned(living);
            }
            hit.setRemainingFireTicks(20);
        }
        int mx = 20;
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
                            this.getZ() + this.random.nextFloat() - this.random.nextFloat(),
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
        if (!this.level().isClientSide) {
            this.level()
                    .explode(
                            this,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            3.0f,
                            this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)
                                    ? ExplosionInteraction.MOB
                                    : ExplosionInteraction.NONE);
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(this.level());
            if (lightning != null) {
                lightning.moveTo(this.getX(), this.getY() + 1.0, this.getZ());
                lightning.setVisualOnly(false);
                this.level().addFreshEntity(lightning);
            }
        }
        this.discard();
    }

    @Override
    public void tick() {
        super.tick();
        int mx = 4;
        for (int i = 0; i < mx; ++i) {
            this.level()
                    .addParticle(
                            ParticleTypes.FIREWORK,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            this.random.nextGaussian() / 10.0,
                            this.random.nextGaussian() / 10.0,
                            this.random.nextGaussian() / 10.0);
        }
    }

    private static boolean isRoyalty(Entity e) {
        if (!(e instanceof LivingEntity)) {
            return false;
        }
        String n = e.getClass().getSimpleName();
        return "ThePrince".equals(n)
                || "ThePrinceTeen".equals(n)
                || "ThePrinceAdult".equals(n)
                || "ThePrincess".equals(n)
                || "TheKing".equals(n)
                || "KingHead".equals(n)
                || "TheQueen".equals(n)
                || "QueenHead".equals(n)
                || "PurplePower".equals(n);
    }

    @Override
    protected void defineSynchedData() {}
}
