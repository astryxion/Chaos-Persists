package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.util.MyUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class IrukandjiArrow extends AbstractArrow {

    private int knockbackStrength;
    private int customTicksInGround = 0;

    public IrukandjiArrow(EntityType<? extends IrukandjiArrow> type, Level level) {
        super(type, level);
        this.setBaseDamage(100.0D);
    }

    public IrukandjiArrow(EntityType<? extends IrukandjiArrow> type, Level level, double x, double y, double z) {
        super(type, level);
        this.setPos(x, y, z);
    }

    public IrukandjiArrow(Level level, double x, double y, double z) {
        this(ChaosPersists.ENTITY_TYPE_IRUKANDJI_ARROW.get(), level, x, y, z);
    }

    public IrukandjiArrow(Level level, LivingEntity shooter, float velocity) {
        super(ChaosPersists.ENTITY_TYPE_IRUKANDJI_ARROW.get(), shooter, level, ItemStack.EMPTY, null);
        this.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, velocity, 1.0F);
    }

    public IrukandjiArrow(
            Level level, LivingEntity shooter, LivingEntity target, float velocity, float inaccuracy) {
        super(ChaosPersists.ENTITY_TYPE_IRUKANDJI_ARROW.get(), shooter, level, ItemStack.EMPTY, null);
        this.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, velocity, inaccuracy);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack((Item) (Object) ChaosPersists.MyIrukandjiArrow);
    }

    @Override
    public void tick() {
        if (!this.inGround && MyUtils.discardIfNextMoveUnloaded(this)) {
            return;
        }
        super.tick();

        if (!this.inGround) {
            customTicksInGround = 0;
        }

        if (this.inGround && !this.level().isClientSide) {
            customTicksInGround++;
            if (customTicksInGround >= 50) {
                this.spawnAtLocation(getPickupItem());
                this.discard();
            }
        }

        if (!this.inGround && this.isCritArrow()) {
            for (int i = 0; i < 4; ++i) {
                this.level()
                        .addParticle(
                                ParticleTypes.CRIT,
                                this.getX() + this.getDeltaMovement().x * i / 4.0,
                                this.getY() + this.getDeltaMovement().y * i / 4.0,
                                this.getZ() + this.getDeltaMovement().z * i / 4.0,
                                -this.getDeltaMovement().x,
                                -this.getDeltaMovement().y + 0.2,
                                -this.getDeltaMovement().z);
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() != HitResult.Type.ENTITY) {
            super.onHit(result);
            return;
        }
        this.onHitEntity((EntityHitResult) result);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!(result.getEntity() instanceof LivingEntity target)) {
            super.onHitEntity(result);
            return;
        }

        float damage = 100.0F;

        if (ChaosPersists.ultimate_sword_pvp == 0) {
            if (target instanceof Player
                    || Girlfriend.class.isInstance(target)
                    || Boyfriend.class.isInstance(target)) {
                this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
                this.discard();
                return;
            }

            if (target instanceof net.minecraft.world.entity.TamableAnimal tame && tame.isTame()) {
                this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
                this.discard();
                return;
            }
        }

        if (this.isCritArrow()) {
            damage *= 1.5F;
        }

        DamageSource source =
                this.getOwner() == null
                        ? this.damageSources().arrow(this, this)
                        : this.damageSources().arrow(this, this.getOwner());

        if (this.isOnFire()) {
            target.igniteForSeconds(5);
        }

        if (target.hurt(source, damage)) {
            if (this.knockbackStrength > 0) {
                double mx = this.getDeltaMovement().x;
                double mz = this.getDeltaMovement().z;
                float f = Mth.sqrt((float) (mx * mx + mz * mz));
                if (f > 0.0F) {
                    target.push(
                            mx * this.knockbackStrength * 0.6D / f,
                            0.1D,
                            mz * this.knockbackStrength * 0.6D / f);
                }
            }

            if (this.getOwner() instanceof ServerPlayer serverPlayer && target instanceof Player) {
                serverPlayer.connection.send(
                        new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
            }

            this.playSound(SoundEvents.ARROW_HIT, 1.0F, 1.0F);
            this.discard();
        }
    }

    public void setKnockbackStrength(int strength) {
        this.knockbackStrength = strength;
    }
}
