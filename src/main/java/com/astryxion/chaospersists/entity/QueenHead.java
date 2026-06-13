package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class QueenHead extends MobEntity {
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    public QueenHead(EntityType<? extends QueenHead> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=19.9f, height=10.0f
        this.noPhysics = true;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.TheQueen_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 1.3300000429153442)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (e != null && (e instanceof TheQueen || e instanceof QueenHead)) {
            return false;
        }
        e = par1DamageSource.getDirectEntity();
        if (e != null && (e instanceof TheQueen || e instanceof QueenHead)) {
            return false;
        }
        List<TheQueen> var5 = this.level.getEntitiesOfClass(TheQueen.class, this.getBoundingBox().inflate(48.0, 32.0, 48.0));
        Iterator<TheQueen> var2 = var5.iterator();
        TheQueen var4 = null;
        if (var2.hasNext()) {
            var4 = var2.next();
            ret = var4.hurt(par1DamageSource, par2);
        }
        return ret;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.boatPosRotationIncrements = this.getControllingPassenger() != null ? par9 + 8 : 6;
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.velocityX = this.getDeltaMovement().x;
        this.velocityY = this.getDeltaMovement().y;
        this.velocityZ = this.getDeltaMovement().z;
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.velocityX = par1;
        this.velocityY = par3;
        this.velocityZ = par5;
        this.setDeltaMovement(par1, par3, par5);
    }

    @Override
    public void tick() {
        if (this.removed) {
            return;
        }
        this.hasImpulse = true;
        this.clearFire();
        if (this.level.isClientSide) {
            if (this.boatPosRotationIncrements > 0) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double) this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double) this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double) this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                this.xRot = (float) ((double) this.xRot + (this.boatPitch - (double) this.xRot) / (double) this.boatPosRotationIncrements);
                double d10 = MathHelper.wrapDegrees(this.boatYaw - (double) this.yRot);
                if (this.getControllingPassenger() != null) {
                    d10 = MathHelper.wrapDegrees((double) this.getControllingPassenger().yRot - (double) this.yRot);
                }
                this.yRot = (float) ((double) this.yRot + d10 / (double) this.boatPosRotationIncrements);
                this.yHeadRot = this.yRot;
                --this.boatPosRotationIncrements;
            }
        } else {
            List<TheQueen> var5 = this.level.getEntitiesOfClass(TheQueen.class, this.getBoundingBox().inflate(32.0, 32.0, 32.0));
            Iterator<TheQueen> var2 = var5.iterator();
            TheQueen var4 = null;
            if (var2.hasNext()) {
                var4 = var2.next();
                this.setPos(var4.getX() - 30.0 * Math.sin(Math.toRadians(var4.yHeadRot)), var4.getY() + 12.0, var4.getZ() + 30.0 * Math.cos(Math.toRadians(var4.yHeadRot)));
                this.yRot = var4.yRot;
                this.yHeadRot = var4.yHeadRot;
                this.setDeltaMovement(var4.getDeltaMovement());
                this.setHealth(var4.getHealth());
            } else {
                this.remove();
            }
        }
    }
}
