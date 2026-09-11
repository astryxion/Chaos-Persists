package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.FriendlyWeaponHits;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class UltimateArrow extends AbstractArrow {

    private int knockbackStrength;

    public UltimateArrow(EntityType<? extends UltimateArrow> type, Level level) {
        super(type, level);
    }

    public UltimateArrow(EntityType<? extends UltimateArrow> type, Level level, double x, double y, double z) {
        super(type, level);
        this.setPos(x, y, z);
    }

    public UltimateArrow(Level level, double x, double y, double z) {
        this(ChaosPersists.ENTITY_TYPE_ULTIMATE_ARROW.get(), level, x, y, z);
    }

    public UltimateArrow(Level level, LivingEntity shooter, float velocity) {
        super(ChaosPersists.ENTITY_TYPE_ULTIMATE_ARROW.get(), shooter, level);
        this.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, velocity, 1.0F);
    }

    public UltimateArrow(
            Level level, LivingEntity shooter, LivingEntity target, float velocity, float inaccuracy) {
        super(ChaosPersists.ENTITY_TYPE_ULTIMATE_ARROW.get(), shooter, level);
        this.shootFromRotation(shooter, shooter.getXRot(), shooter.getYRot(), 0.0F, velocity, inaccuracy);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.inGround && this.isCritArrow()) {
            Vec3 motion = this.getDeltaMovement();
            for (int i = 0; i < 4; ++i) {
                this.level()
                        .addParticle(
                                ParticleTypes.CRIT,
                                this.getX() + motion.x * i / 4.0,
                                this.getY() + motion.y * i / 4.0,
                                this.getZ() + motion.z * i / 4.0,
                                -motion.x,
                                -motion.y + 0.2,
                                -motion.z);
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
        Entity hitEntity = result.getEntity();
        float pitch = 1.2F / (this.random.nextFloat() * 0.2F + 0.9F);

        if (FriendlyWeaponHits.isListedIgnore(hitEntity)) {
            this.playSound(SoundEvents.ARROW_HIT, 1.0F, pitch);
            this.discard();
            return;
        }

        if (ChaosPersists.ultimate_sword_pvp == 0 && FriendlyWeaponHits.isFriendlyWhenPvpOff(hitEntity)) {
            this.playSound(SoundEvents.ARROW_HIT, 1.0F, pitch);
            LivingEntity heal = FriendlyWeaponHits.healTarget(hitEntity);
            if (heal != null) {
                heal.heal((float) ChaosPersists.UltimateBowPetHeal);
            }
            this.discard();
            return;
        }

        if (!(hitEntity instanceof LivingEntity hit)) {
            super.onHitEntity(result);
            return;
        }

        float velocity = (float) this.getDeltaMovement().length();
        int damage = Mth.ceil(velocity * (float) ChaosPersists.UltimateBowDamage);

        if (this.isCritArrow()) {
            damage += this.random.nextInt(damage / 2 + 2);
        }

        DamageSource source =
                this.getOwner() == null
                        ? this.damageSources().arrow(this, this)
                        : this.damageSources().arrow(this, this.getOwner());

        if (this.isOnFire()) {
            hit.setSecondsOnFire(5);
        }

        if (hit.hurt(source, damage)) {
            if (!this.level().isClientSide) {
                hit.setArrowCount(hit.getArrowCount() + 1);
            }

            applyKnockback(hit);

            if (this.getOwner() instanceof ServerPlayer serverPlayer
                    && hit instanceof Player
                    && hit != this.getOwner()) {
                serverPlayer.connection.send(
                        new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
            }

            this.playSound(SoundEvents.ARROW_HIT, 1.0F, pitch);
            this.discard();
        } else {
            this.setDeltaMovement(this.getDeltaMovement().scale(-0.10000000149D));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
        }
    }

    private void applyKnockback(Entity target) {
        if (this.knockbackStrength > 0) {
            Vec3 motion = this.getDeltaMovement();
            float f = Mth.sqrt((float) (motion.x * motion.x + motion.z * motion.z));
            if (f > 0.0F) {
                target.push(
                        motion.x * this.knockbackStrength * 0.6D / f,
                        0.1D,
                        motion.z * this.knockbackStrength * 0.6D / f);
            }
        }
    }

    public void setKnockbackStrength(int strength) {
        this.knockbackStrength = strength;
    }
}
