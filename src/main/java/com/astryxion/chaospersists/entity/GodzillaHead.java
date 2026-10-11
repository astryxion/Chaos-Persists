package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.ItemSpawnEgg;
import java.util.List;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GodzillaHead extends LivingEntity {
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    public GodzillaHead(EntityType<? extends GodzillaHead> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.refreshDimensions();
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.refreshDimensions();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Godzilla_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 1.3300000429153442)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(9.9f, 10.0f);
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public ItemStack getPickResult() {
        return ItemSpawnEgg.pickStackFor(this);
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return java.util.Collections.emptyList();
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, net.minecraft.world.level.block.state.BlockState state, net.minecraft.core.BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.IN_WALL)) {
            return false;
        }
        Entity e = source.getEntity();
        if (e instanceof Godzilla || e instanceof GodzillaHead) {
            return false;
        }
        e = source.getDirectEntity();
        if (e instanceof Godzilla || e instanceof GodzillaHead) {
            return false;
        }
        AABB box = this.getBoundingBox().inflate(32.0, 32.0, 32.0);
        List<Godzilla> mobzillas = this.level().getEntitiesOfClass(Godzilla.class, box);
        if (!mobzillas.isEmpty()) {
            return mobzillas.get(0).hurt(source, amount);
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yRot, float xRot, int steps, boolean teleport) {
        this.boatX = x;
        this.boatY = y;
        this.boatZ = z;
        this.boatYaw = yRot;
        this.boatPitch = xRot;
        this.boatPosRotationIncrements = steps > 0 ? steps : 6;
        this.velocityX = this.getDeltaMovement().x;
        this.velocityY = this.getDeltaMovement().y;
        this.velocityZ = this.getDeltaMovement().z;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpMotion(double x, double y, double z) {
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
        this.setDeltaMovement(x, y, z);
    }

    @Override
    public void tick() {
        super.tick();
        this.setOnGround(false);
        this.clearFire();
        if (this.level().isClientSide) {
            if (this.boatPosRotationIncrements > 0) {
                double x = this.getX() + (this.boatX - this.getX()) / (double) this.boatPosRotationIncrements;
                double y = this.getY() + (this.boatY - this.getY()) / (double) this.boatPosRotationIncrements;
                double z = this.getZ() + (this.boatZ - this.getZ()) / (double) this.boatPosRotationIncrements;
                this.setPos(x, y, z);
                this.setXRot((float) ((double) this.getXRot() + (this.boatPitch - (double) this.getXRot()) / (double) this.boatPosRotationIncrements));
                double yawDelta = Mth.wrapDegrees(this.boatYaw - (double) this.getYRot());
                this.setYRot((float) ((double) this.getYRot() + yawDelta / (double) this.boatPosRotationIncrements));
                this.setDeltaMovement(this.velocityX, this.velocityY, this.velocityZ);
                --this.boatPosRotationIncrements;
            }
        } else {
            this.syncToMobzilla();
        }
    }

    private void syncToMobzilla() {
        AABB box = this.getBoundingBox().inflate(32.0, 32.0, 32.0);
        List<Godzilla> mobzillas = this.level().getEntitiesOfClass(Godzilla.class, box);
        if (mobzillas.isEmpty() || !mobzillas.get(0).isAlive()) {
            this.discard();
            return;
        }
        Godzilla mobzilla = mobzillas.get(0);
        double yawRad = Math.toRadians(mobzilla.getYRot());
        this.setPos(
                mobzilla.getX() - 17.0 * Math.sin(yawRad),
                mobzilla.getY() + 16.0,
                mobzilla.getZ() + 17.0 * Math.cos(yawRad));
        this.setYRot(mobzilla.getYRot());
        this.setYHeadRot(mobzilla.getYHeadRot());
        this.setDeltaMovement(mobzilla.getDeltaMovement());
    }
}
