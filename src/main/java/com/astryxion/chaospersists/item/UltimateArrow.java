package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;

import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Items;
import net.minecraft.util.SoundEvents;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class UltimateArrow extends ArrowEntity {

    private static final DataParameter<Byte> CRIT =
            EntityDataManager.defineId(UltimateArrow.class, DataSerializers.BYTE);

    private int knockbackStrength;

    public UltimateArrow(EntityType<? extends UltimateArrow> type, World world) {
        super(type, world);
    }

    public UltimateArrow(World world, double x, double y, double z) {
        super(resolveEntityType(), world);
        this.setPos(x, y, z);
    }

    public UltimateArrow(World world, PlayerEntity player, float velocity) {
        super(resolveEntityType(), world);
        this.setOwner(player);
        this.shootFromRotation(player, player.xRot, player.yRot, 0.0F, velocity, 1.0F);
    }

    public UltimateArrow(World world,
                         MobEntity shooter,
                         LivingEntity target,
                         float velocity,
                         float inaccuracy) {
        super(resolveEntityType(), world);
        this.setOwner(shooter);
        this.shootFromRotation(shooter, shooter.xRot, shooter.yRot, 0.0F, velocity, inaccuracy);
    }

    private static EntityType<? extends UltimateArrow> resolveEntityType() {
        return (EntityType<? extends UltimateArrow>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ultimate_arrow"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CRIT, (byte) 0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.inGround && this.isCritArrow()) {
            for (int i = 0; i < 4; ++i) {
                this.level.addParticle(
                        ParticleTypes.CRIT,
                        this.getX() + this.getDeltaMovement().x * i / 4.0,
                        this.getY() + this.getDeltaMovement().y * i / 4.0,
                        this.getZ() + this.getDeltaMovement().z * i / 4.0,
                        -this.getDeltaMovement().x,
                        -this.getDeltaMovement().y + 0.2,
                        -this.getDeltaMovement().z
                );
            }
        }
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() != net.minecraft.util.math.RayTraceResult.Type.ENTITY) {
            super.onHit(result);
            return;
        }

        Entity hit = ((net.minecraft.util.math.EntityRayTraceResult) result).getEntity();

        if (ChaosPersists.ultimate_sword_pvp == 0) {
            if (hit instanceof PlayerEntity || hit instanceof Girlfriend || hit instanceof Boyfriend) {
                this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                if (hit instanceof LivingEntity) {
                    ((LivingEntity) hit).heal(1.0F);
                }
                this.remove();
                return;
            }
            if (hit instanceof TameableEntity && ((TameableEntity) hit).isTame()) {
                this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                ((TameableEntity) hit).heal(1.0F);
                this.remove();
                return;
            }
        }

        float velocity = MathHelper.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().y * this.getDeltaMovement().y + this.getDeltaMovement().z * this.getDeltaMovement().z);
        int damage = MathHelper.ceil(velocity * (float) ChaosPersists.UltimateBowDamage);

        if (this.isCritArrow()) {
            damage += this.random.nextInt(damage / 2 + 2);
        }

        Entity ownerEntity = this.getOwner();
        LivingEntity owner = ownerEntity instanceof LivingEntity ? (LivingEntity) ownerEntity : null;
        DamageSource source = owner == null
                ? DamageSource.arrow(this, this)
                : DamageSource.arrow(this, owner);

        if (this.isOnFire()) {
            hit.setSecondsOnFire(5);
        }

        if (hit.hurt(source, damage)) {
            if (hit instanceof MobEntity) {
                MobEntity living = (MobEntity) hit;
                if (!this.level.isClientSide) {
                    living.setArrowCount(living.getArrowCount() + 1);
                }
            }

            applyKnockback(hit);

            if (owner instanceof ServerPlayerEntity && hit instanceof PlayerEntity && hit != owner) {
                ((ServerPlayerEntity) owner).connection.send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
            }

            this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            this.remove();
        } else {
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, -0.10000000149D, -0.10000000149D, -0.10000000149D);
            this.yRot += 180.0F;
            this.yRotO += 180.0F;
        }
    }

    private void applyKnockback(Entity target) {
        if (this.knockbackStrength > 0) {
            float f = MathHelper.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
            if (f > 0.0F) {
                target.setDeltaMovement(target.getDeltaMovement().add(
                        this.getDeltaMovement().x * this.knockbackStrength * 0.6D / f,
                        0.1D,
                        this.getDeltaMovement().z * this.knockbackStrength * 0.6D / f
                ));
            }
        }
    }

    public void setKnockbackStrength(int strength) {
        this.knockbackStrength = strength;
    }

    @Override
    public double getBaseDamage() {
        return ChaosPersists.UltimateBowDamage;
    }
}
