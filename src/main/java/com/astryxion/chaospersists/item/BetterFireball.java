package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Mothra;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BetterFireball extends AbstractHurtingProjectile implements ItemSupplier {
    public LivingEntity shootingEntity;
    public int explosionPower = 1;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    private int ticksInAir;
    private int notme;
    private boolean small;

    public BetterFireball(EntityType<? extends BetterFireball> type, Level level) {
        super(type, level);
        this.setBoundingBox(this.getBoundingBox().inflate(1.0, 1.0, 1.0));
    }

    public BetterFireball(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        this(com.astryxion.chaospersists.core.ChaosPersists.ENTITY_TYPE_BETTER_FIREBALL.get(), level);
        this.shootingEntity = shooter;
        this.setPos(shooter.getX(), shooter.getY(), shooter.getZ());
        this.setDeltaMovement(Vec3.ZERO);
        double length = Math.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        if (length > 0.0) {
            this.accelerationX = accelX / length * 0.1;
            this.accelerationY = accelY / length * 0.1;
            this.accelerationZ = accelZ / length * 0.1;
        }
    }

    public void setNotMe() {
        this.notme = 1;
    }

    public void setBig() {
        this.explosionPower = 2;
    }

    public void setReallyBig() {
        this.explosionPower = 4;
    }

    public void setSmall() {
        this.small = true;
        this.setBoundingBox(this.getBoundingBox().inflate(-0.6875, -0.6875, -0.6875));
    }

    @Override
    public void tick() {
        if (this.ticksInAir >= 600) {
            this.discard();
            return;
        }
        if (!this.level().isClientSide
                && (this.shootingEntity != null && !this.shootingEntity.isAlive()
                        || !this.level().hasChunkAt(BlockPos.containing(this.getX(), this.getY(), this.getZ())))) {
            this.discard();
            return;
        }
        this.setSecondsOnFire(1);
        ++this.ticksInAir;
        Vec3 from = new Vec3(this.getX(), this.getY(), this.getZ());
        Vec3 to = from.add(this.getDeltaMovement());
        HitResult blockHit =
                this.level()
                        .clip(
                                new ClipContext(
                                        from,
                                        to,
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        EntityHitResult entityHit = this.findHitEntity(from, to);
        HitResult hit = blockHit;
        if (entityHit != null) {
            hit = entityHit;
        }
        if (hit.getType() != HitResult.Type.MISS) {
            this.onHit(hit);
            return;
        }
        Vec3 motion = this.getDeltaMovement();
        this.setPos(this.getX() + motion.x, this.getY() + motion.y, this.getZ() + motion.z);
        float horizontal = (float) Math.sqrt(motion.x * motion.x + motion.z * motion.z);
        this.setYRot((float) (Math.atan2(motion.z, motion.x) * 180.0 / Math.PI) + 90.0f);
        this.setXRot((float) (Math.atan2(horizontal, motion.y) * 180.0 / Math.PI) - 90.0f);
        float drag = 0.95f;
        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                float spread = 0.25f;
                this.level()
                        .addParticle(
                                ParticleTypes.BUBBLE,
                                this.getX() - motion.x * spread,
                                this.getY() - motion.y * spread,
                                this.getZ() - motion.z * spread,
                                motion.x,
                                motion.y,
                                motion.z);
            }
            drag = 0.8f;
        }
        motion = motion.add(this.accelerationX, this.accelerationY, this.accelerationZ);
        this.setDeltaMovement(motion.multiply(drag, drag, drag));
        this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
    }

    private EntityHitResult findHitEntity(Vec3 start, Vec3 end) {
        Entity hitEntity = null;
        double closest = 0.0;
        List<Entity> entities =
                this.level()
                        .getEntities(
                                this,
                                this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0),
                                e -> !e.isSpectator() && e.isPickable());
        for (Entity entity : entities) {
            if (entity == this.shootingEntity && this.ticksInAir < 25) {
                continue;
            }
            if (entity instanceof BetterFireball) {
                continue;
            }
            if (entity.getClass().getSimpleName().equals("GodzillaHead")) {
                continue;
            }
            if (isRoyaltyEntity(entity)) {
                continue;
            }
            if (this.notme != 0
                    && (entity instanceof Player
                            || entity.getClass().getSimpleName().equals("Dragon")
                            || entity instanceof Mothra)) {
                continue;
            }
            AABB box = entity.getBoundingBox().inflate(0.3);
            var hit = box.clip(start, end);
            if (hit.isPresent()) {
                double dist = start.distanceToSqr(hit.get());
                if (hitEntity == null || dist < closest) {
                    hitEntity = entity;
                    closest = dist;
                }
            }
        }
        return hitEntity == null ? null : new EntityHitResult(hitEntity);
    }

    @Override
    protected void onHit(HitResult result) {
        if (this.level().isClientSide) {
            return;
        }
        if (result instanceof EntityHitResult entityHit) {
            Entity hit = entityHit.getEntity();
            if (hit instanceof BetterFireball || hit instanceof Mothra) {
                return;
            }
            if (this.notme != 0
                    && (hit.getClass().getSimpleName().equals("Dragon") || hit instanceof Player)) {
                this.discard();
                return;
            }
            if (hit instanceof LivingEntity living) {
                if (hit == this.shootingEntity || isRoyaltyEntity(hit)) {
                    this.discard();
                    return;
                }
                if (!(living.getBbWidth() * living.getBbHeight() <= 30.0f)
                        && !isRoyaltyEntity(hit)
                        && !hit.getClass().getSimpleName().equals("Godzilla")
                        && !hit.getClass().getSimpleName().equals("GodzillaHead")
                        && !hit.getClass().getSimpleName().equals("PitchBlack")
                        && !hit.getClass().getSimpleName().equals("Kraken")) {
                    living.setHealth(living.getHealth() / 2.0f);
                }
            }
            if (!this.small) {
                hit.hurt(this.damageSources().mobProjectile(this, this.shootingEntity), 10.0f);
                hit.setSecondsOnFire(5);
            } else {
                hit.hurt(this.damageSources().mobProjectile(this, this.shootingEntity), 5.0f);
                hit.setSecondsOnFire(5);
            }
        } else if (result instanceof BlockHitResult) {
            BlockPos firePos = BlockPos.containing(result.getLocation());
            if (this.level().isEmptyBlock(firePos)) {
                this.level().setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());
            }
        }
        if (!this.small) {
            // 1.7.10 used newExplosion(null, ...) so blast damage is not attributed to the
            // shooter. Attributing to this fireball makes getEntity() resolve to shootingEntity
            // and starts royal family feuds via Teen/Adult hurt retaliation.
            this.level()
                    .explode(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            (float) this.explosionPower,
                            Level.ExplosionInteraction.MOB);
        }
        this.discard();
    }

    private static boolean isRoyaltyEntity(Entity e) {
        String n = e.getClass().getSimpleName();
        return n.equals("ThePrince")
                || n.equals("ThePrinceTeen")
                || n.equals("ThePrinceAdult")
                || n.equals("ThePrincess")
                || n.equals("TheKing")
                || n.equals("KingHead")
                || n.equals("TheQueen")
                || n.equals("QueenHead")
                || n.equals("PurplePower");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("ExplosionPower", this.explosionPower);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("ExplosionPower")) {
            this.explosionPower = tag.getInt("ExplosionPower");
        }
    }

    @Override
    protected boolean shouldBurn() {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.FIRE_CHARGE);
    }
}
