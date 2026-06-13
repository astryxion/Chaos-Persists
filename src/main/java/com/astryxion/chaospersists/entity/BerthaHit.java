package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.registries.ForgeRegistries;

public class BerthaHit extends ThrowableEntity {
    private int hit_type = 0;

    public BerthaHit(EntityType<? extends BerthaHit> type, World par1World) {
        super(type, par1World);
    }

    public BerthaHit(World par1World, int par2) {
        this(typeFor(par1World), par1World);
    }

    public BerthaHit(World par1World, LivingEntity par2LivingEntity) {
        this(typeFor(par1World), par1World);
        this.setOwner(par2LivingEntity);
        this.setPos(par2LivingEntity.getX(), par2LivingEntity.getEyeY(), par2LivingEntity.getZ());
        float f = 0.4f;
        float yaw = par2LivingEntity.yRot * ((float) Math.PI / 180F);
        float pitch = par2LivingEntity.xRot * ((float) Math.PI / 180F);
        double mx = -MathHelper.sin(yaw) * MathHelper.cos(pitch) * f;
        double mz = MathHelper.cos(yaw) * MathHelper.cos(pitch) * f;
        double my = -MathHelper.sin(pitch) * f;
        this.setDeltaMovement(mx, my, mz);
        this.shoot(mx, my, mz, 0.4f, 0.1f);
    }

    public BerthaHit(World par1World, LivingEntity par2LivingEntity, int par3) {
        this(par1World, par2LivingEntity);
    }

    public BerthaHit(World par1World, double par2, double par4, double par6) {
        super(typeFor(par1World), par1World);
        this.setPos(par2, par4, par6);
    }

    public void setHitType(int i) {
        this.hit_type = i;
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends BerthaHit> typeFor(World level) {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bertha_hit"));
        if (type != null) {
            return (EntityType<? extends BerthaHit>) type;
        }
        return (EntityType<? extends BerthaHit>) (EntityType<?>) EntityType.SNOWBALL;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void onHit(RayTraceResult par1MovingObjectPosition) {
        if (this.removed) {
            return;
        }
        if (par1MovingObjectPosition.getType() == RayTraceResult.Type.ENTITY && this.getOwner() != null) {
            Entity e = ((EntityRayTraceResult) par1MovingObjectPosition).getEntity();
            LivingEntity thrower = this.getOwner() instanceof LivingEntity ? (LivingEntity) this.getOwner() : null;
            if (thrower == null) {
                this.remove();
                return;
            }
            if (ChaosPersists.big_bertha_pvp == 0 && (e instanceof net.minecraft.entity.player.PlayerEntity || e instanceof Girlfriend || e instanceof Boyfriend)) {
                this.remove();
                return;
            }
            if (ChaosPersists.big_bertha_pvp == 0 && e instanceof TameableEntity && ((TameableEntity) e).isTame()) {
                this.remove();
                return;
            }
            if (this.hit_type == 0 && this.distanceToSqr(thrower) < 81.0 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), (float) ChaosPersists.bertha_stats.damage);
                e.setSecondsOnFire(10);
                double ks = 2.25;
                double inair = 0.35;
                float f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            if (this.hit_type == 2 && this.distanceToSqr(thrower) < 101.0 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), (float) ChaosPersists.royal_stats.damage);
                double ks = 1.5;
                double inair = 0.25;
                float f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            if (this.hit_type == 3 && this.distanceToSqr(thrower) < 64.0 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), (float) ChaosPersists.hammy_stats.damage);
                double ks = 1.25;
                double inair = 0.65;
                float f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (!this.level.isClientSide && this.hit_type == 3 && this.distanceToSqr(thrower) < 64.0) {
                    this.level.explode(null, this.getX(), this.getY(), this.getZ(), 1.5f, this.level.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING), Explosion.Mode.DESTROY);
                }
            }
        } else if (!this.level.isClientSide && this.hit_type == 3 && this.getOwner() != null && this.distanceToSqr(this.getOwner()) < 64.0) {
            this.level.explode(null, this.getX(), this.getY(), this.getZ(), 2.1f, this.level.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING), Explosion.Mode.DESTROY);
        }
        this.remove();
    }

    @Override
    public void tick() {
        super.tick();
    }
}
