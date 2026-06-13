/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ThunderBolt
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.effect.LightningBoltEntity
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.MyUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class ThunderBolt
extends ThrowableEntity {
    public ThunderBolt(EntityType<? extends ThunderBolt> type, World par1World) {
        super(type, par1World);
    }

    public ThunderBolt(World par1World) {
        this(resolveEntityType(), par1World);
    }

    public ThunderBolt(World par1World, LivingEntity par3PlayerEntity) {
        super(resolveEntityType(), par3PlayerEntity, par1World);
    }

    public ThunderBolt(World par1World, LivingEntity par2Mob, int par3) {
        super(resolveEntityType(), par2Mob, par1World);
    }

    public ThunderBolt(World par1World, double par2, double par4, double par6) {
        super(resolveEntityType(), par2, par4, par6, par1World);
    }

    @Override
    protected void defineSynchedData() {
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends ThunderBolt> resolveEntityType() {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "thunder_bolt"));
        return type != null ? (EntityType<? extends ThunderBolt>) type : (EntityType<? extends ThunderBolt>) (EntityType<?>) EntityType.SNOWBALL;
    }

    @Override
    protected void onHit(RayTraceResult par1MovingObjectPosition) {
        Entity hitEntity = par1MovingObjectPosition.getType() == RayTraceResult.Type.ENTITY ? ((EntityRayTraceResult) par1MovingObjectPosition).getEntity() : null;
        if (hitEntity != null) {
            float var2 = 40.0f;
            if (MyUtils.isRoyalty(hitEntity)) {
                this.remove();
                return;
            }
            hitEntity.hurt(DamageSource.thrown(this, this.getOwner()), var2 / 2.0f);
            hitEntity.hurt(DamageSource.mobAttack((LivingEntity)this.getOwner()), var2 / 2.0f);
            hitEntity.setSecondsOnFire(1);
        }
        int mx = 20;
        for (int var3 = 0; var3 < mx; ++var3) {
            this.level.addParticle(ParticleTypes.SMOKE, this.getX() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), this.getY() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), this.getZ() + (double)this.random.nextFloat(), 0.0, 0.0, 0.0);
            this.level.addParticle(ParticleTypes.SMOKE, this.getX() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), this.getY() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), this.getZ() + (double)this.random.nextFloat() - (double)this.random.nextFloat(), 0.0, 0.0, 0.0);
            this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.level.random.nextGaussian(), this.level.random.nextGaussian(), this.level.random.nextGaussian());
        }
        this.playSound(SoundEvents.GENERIC_EXPLODE, 0.5f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
        if (!this.level.isClientSide) {
            this.level.explode(this, this.getX(), this.getY(), this.getZ(), 3.0f, this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
        }
        LightningBoltEntity lightning = EntityType.LIGHTNING_BOLT.create(this.level);
        if (lightning != null) {
            lightning.moveTo(this.getX(), this.getY() + 1.0, this.getZ());
            this.level.addFreshEntity(lightning);
        }
        this.remove();
    }

    @Override
    public void tick() {
        super.tick();
        int mx = 4;
        for (int i = 0; i < mx; ++i) {
            this.level.addParticle(ParticleTypes.FIREWORK, this.getX(), this.getY(), this.getZ(), this.level.random.nextGaussian() / 10.0, this.level.random.nextGaussian() / 10.0, this.level.random.nextGaussian() / 10.0);
        }
    }
}

