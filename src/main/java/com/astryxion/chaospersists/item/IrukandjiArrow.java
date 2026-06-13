package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class IrukandjiArrow extends AbstractArrowEntity {

    private static final DataParameter<Integer> CRIT =
            EntityDataManager.defineId(IrukandjiArrow.class, DataSerializers.INT);

    private int knockbackStrength;
    private int customTicksInGround = 0;

    public IrukandjiArrow(EntityType<? extends IrukandjiArrow> type, World world) {
        super(type, world);
    }

    public IrukandjiArrow(World world, double x, double y, double z) {
        super(resolveEntityType(), x, y, z, world);
    }

    public IrukandjiArrow(World world, PlayerEntity player, float velocity) {
        super(resolveEntityType(), player, world);
        this.shootFromRotation(player, player.xRot, player.yRot, 0.0F, velocity, 1.0F);
    }

    public IrukandjiArrow(World world,
                          MobEntity shooter,
                          LivingEntity target,
                          float velocity,
                          float inaccuracy) {
        super(resolveEntityType(), shooter, world);
        this.shootFromRotation(shooter, shooter.xRot, shooter.yRot, 0.0F, velocity, inaccuracy);
    }

    private static EntityType<? extends IrukandjiArrow> resolveEntityType() {
        return (EntityType<? extends IrukandjiArrow>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "irukandji_arrow"));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CRIT, 0);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ChaosPersists.MyIrukandjiArrow);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.inGround) {
            customTicksInGround = 0;
        }

        if (this.inGround && !this.level.isClientSide) {
            customTicksInGround++;
            if (customTicksInGround >= 50) {
                this.spawnAtLocation(new ItemStack(ChaosPersists.MyIrukandjiArrow));
                this.remove();
            }
        }

        if (!this.inGround && this.isCritArrow()) {
            for (int i = 0; i < 4; ++i) {
                if (this.level.isClientSide) {
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
    }

    @Override
    protected void onHit(RayTraceResult result) {
        if (result.getType() != net.minecraft.util.math.RayTraceResult.Type.ENTITY) {
            super.onHit(result);
            return;
        }

        LivingEntity target = (LivingEntity) ((net.minecraft.util.math.EntityRayTraceResult) result).getEntity();

        float damage = 100.0F; // Chaos-tier damage

        // PvP protection logic
        if (ChaosPersists.ultimate_sword_pvp == 0) {

            if (target instanceof PlayerEntity
                    || target instanceof Girlfriend
                    || target instanceof Boyfriend) {
                this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
                this.remove();
                return;
            }

            if (target instanceof TameableEntity) {
                TameableEntity tame = (TameableEntity) target;
                if (tame.isTame()) {
                    this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
                    this.remove();
                    return;
                }
            }
        }

        if (this.isCritArrow()) {
            damage *= 1.5F;
        }

        Entity ownerEntity = this.getOwner();
        LivingEntity owner = ownerEntity instanceof LivingEntity ? (LivingEntity) ownerEntity : null;
        DamageSource source = owner == null
                ? DamageSource.arrow(this, this)
                : DamageSource.arrow(this, owner);

        if (this.isOnFire()) {
            target.setSecondsOnFire(5);
        }

        if (target.hurt(source, damage)) {

            if (this.knockbackStrength > 0) {
                float f = MathHelper.sqrt(
                        this.getDeltaMovement().x * this.getDeltaMovement().x +
                        this.getDeltaMovement().z * this.getDeltaMovement().z
                );

                if (f > 0.0F) {
                    target.setDeltaMovement(target.getDeltaMovement().add(
                            this.getDeltaMovement()).x * this.knockbackStrength * 0.6D / f,
                            0.1D,
                            this.getDeltaMovement().z * this.knockbackStrength * 0.6D / f
                    );
                }
            }

            if (owner instanceof ServerPlayerEntity
                    && target instanceof PlayerEntity) {
                ((ServerPlayerEntity) owner)
                        .connection
                        .send(new SChangeGameStatePacket(SChangeGameStatePacket.ARROW_HIT_PLAYER, 0.0F));
            }

            this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
            this.remove();
        }
    }

    public void setKnockbackStrength(int strength) {
        this.knockbackStrength = strength;
    }

    public double getDamage() {
        return 100.0;
    }
}
