package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SunspotUrchin extends ThrowableProjectile {

    private float my_rotation = 0.0f;
    private int my_index = 50;

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, Level level) {
        super(type, level);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, Level level, int par2) {
        super(type, level);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, LivingEntity shooter, Level level, int par3) {
        super(type, shooter, level);
    }

    public SunspotUrchin(EntityType<? extends SunspotUrchin> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    public int getUrchinIndex() {
        return this.my_index;
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {}

    @Override
    protected void onHit(HitResult result) {
        if (this.level().isClientSide) {
            return;
        }

        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit = (EntityHitResult) result;
            Entity hit = entityHit.getEntity();
            float damage = 3.0f;
            if (hit instanceof Creeper) {
                damage = 6.0f;
            }
            if (!(hit instanceof Player)) {
                hit.hurt(this.damageSources().thrown(this, this.getOwner()), damage);
                if (!hit.fireImmune()) {
                    hit.igniteForSeconds(5);
                }
            }
        } else if (result.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) result;
            BlockPos placePos = blockHit.getBlockPos().relative(blockHit.getDirection());
            if (this.level().isEmptyBlock(placePos)) {
                this.level().setBlockAndUpdate(placePos, Blocks.FIRE.defaultBlockState());
            }
        }

        for (int var3 = 0; var3 < 5; ++var3) {
            this.level()
                    .addParticle(
                            ParticleTypes.SMOKE,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            this.level().getRandom().nextFloat(),
                            this.level().getRandom().nextFloat(),
                            this.level().getRandom().nextFloat());
            this.level()
                    .addParticle(
                            new DustParticleOptions(new Vector3f(1.0F, 0.0F, 0.0F), 1.0F),
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            this.level().getRandom().nextFloat(),
                            this.level().getRandom().nextFloat(),
                            this.level().getRandom().nextFloat());
        }

        this.discard();
    }

    @Override
    public void tick() {
        if (MyUtils.discardIfNextMoveUnloaded(this)) {
            return;
        }
        super.tick();
        this.setSharedFlagOnFire(true);
        this.my_rotation += 30.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.setXRot(this.my_rotation);
        this.xRotO = this.my_rotation;
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }
}
