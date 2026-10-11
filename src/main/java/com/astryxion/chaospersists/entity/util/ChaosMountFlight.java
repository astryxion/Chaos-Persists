package com.astryxion.chaospersists.entity.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * Ice and Fire 1.20.1 {@code EntityDragonBase} ridden-flight algorithm, extracted so every Chaos
 * Persists flying mount can import one helper instead of duplicating OreSpawn travel.
 *
 * <p>Client-authoritative while {@link Mob#isControlledByLocalInstance()} — the server must not
 * {@code setDeltaMovement} in the air branch or client motion halts. Vertical input is Jump = up
 * and X = down via {@link ICustomMoveController}. Friction is equal on XYZ ({@code 0.9}).
 */
public final class ChaosMountFlight {
    private final Mob mount;
    private final ChaosFlyingMount flying;

    public int spacebarTicks;
    public boolean gliding;
    public float glidingSpeedBonus;
    private boolean overAir;

    public ChaosMountFlight(ChaosFlyingMount flying) {
        this.flying = flying;
        this.mount = flying.asMob();
    }

    public void tick() {
        this.overAir = isOverAirLogic();
        if (!this.mount.level().isClientSide) {
            updateRider();
        }
    }

    public void onMounted() {
        if (this.flying.startsFlyingWhenMounted()) {
            this.flying.setHovering(true);
            this.flying.setFlying(true);
            syncActivity();
        }
    }

    public void onDismount() {
        this.flying.setFlying(false);
        this.flying.setHovering(false);
        this.flying.setControlState((byte) 0);
        this.spacebarTicks = 0;
        this.gliding = false;
        this.glidingSpeedBonus = 0.0f;
        this.mount.setNoGravity(false);
        this.mount.noPhysics = false;
        syncActivity();
    }

    /**
     * @return {@code true} if rider flight/walk was handled and the entity should not run AI travel
     */
    public boolean handleTravel(Vec3 travelVector, Consumer<Vec3> vanillaTravel) {
        if (!this.flying.allowLocalMotionControl() || this.flying.getRidingPlayer() == null) {
            return false;
        }
        Player rider = this.flying.getRidingPlayer();
        if (rider == null) {
            vanillaTravel.accept(travelVector);
            return true;
        }
        if (rider.isDeadOrDying()) {
            this.mount.ejectPassengers();
            this.mount.setNoGravity(false);
            vanillaTravel.accept(travelVector);
            return true;
        }

        MyUtils.clearChaosFlight(this.mount);
        this.mount.noPhysics = false;

        if (this.mount.level().isClientSide) {
            ChaosPersists.proxy.applyLocalMountControls(this.flying);
        }

        // OreSpawn 1.7.10 took off as soon as fly-up was held. Do not wait on IAF spacebarTicks.
        if (!this.flying.isHovering()
                && !this.flying.isFlying()
                && this.flying.isGoingUp()
                && !this.flying.isGoingDown()
                && !this.mount.isInWater()) {
            this.flying.setHovering(true);
            this.spacebarTicks = 0;
            this.glidingSpeedBonus = 0.0f;
            syncActivity();
        }

        // Note: when motion is handled by the client no server side setDeltaMovement() should be called
        // otherwise the movement will halt
        float flyingSpeed;
        if (this.flying.isHovering() || this.flying.isFlying()) {
            double forward = rider.zza;
            double strafing = rider.xxa;
            double vertical = 0;
            float speed = (float) this.mount.getAttributeValue(Attributes.MOVEMENT_SPEED);
            float customAir = this.flying.getRiddenAirSpeedMultiplier();
            float airSpeedModifier =
                    customAir >= 0.0f
                            ? customAir
                            : (float)
                                    (5.2f
                                            + 1.0f
                                                    * Mth.clamp(
                                                            Mth.map(
                                                                    speed,
                                                                    this.flying.getMinimumSpeed(),
                                                                    this.flying.getMaximumSpeed(),
                                                                    0f,
                                                                    1.5f),
                                                            0f,
                                                            1.5f));
            speed *= airSpeedModifier;
            if (forward > 0) {
                this.flying.setFlying(true);
                this.flying.setHovering(false);
                syncActivity();
            }

            this.gliding = this.flying.allowMousePitchControl() && rider.isSprinting();
            if (!this.gliding) {
                speed += this.glidingSpeedBonus;
                forward *= rider.zza > 0 ? 1.0f : 0.5f;
                strafing *= 0.4f;
                if (this.flying.isGoingUp() && !this.flying.isGoingDown()) {
                    vertical = 1f;
                } else if (this.flying.isGoingDown() && !this.flying.isGoingUp()) {
                    vertical = -1f;
                }
            } else {
                speed *= 1.5f;
                strafing *= 0.1f;
                this.glidingSpeedBonus =
                        (float)
                                Mth.clamp(
                                        this.glidingSpeedBonus + this.mount.getDeltaMovement().y * -0.05d,
                                        -0.8d,
                                        1.5d);
                speed += this.glidingSpeedBonus;
                float pitch = rider.getXRot();
                forward = Mth.abs(Mth.cos(pitch * ((float) Math.PI / 180F)));
                vertical = Mth.abs(Mth.sin(pitch * ((float) Math.PI / 180F)));
                if (this.flying.isGoingUp() && !this.flying.isGoingDown()) {
                    vertical = Math.max(vertical, 0.5);
                } else if (this.flying.isGoingDown() && !this.flying.isGoingUp()) {
                    vertical = Math.min(vertical, -0.5);
                } else if (this.flying.isGoingUp() && this.flying.isGoingDown()) {
                    vertical = 0;
                } else if (pitch < 0) {
                    vertical *= 1;
                } else if (pitch > 0) {
                    vertical *= -1;
                }
            }
            this.glidingSpeedBonus -= (float) (this.glidingSpeedBonus * 0.01d);

            if (this.mount.isControlledByLocalInstance()) {
                // Vanilla friction on Y axis is smaller, which will influence terminal speed for
                // climbing and diving. Use same friction coefficient on all axis.
                flyingSpeed = speed * 0.1F;
                this.mount.setSpeed(flyingSpeed);
                this.mount.moveRelative(flyingSpeed, new Vec3(strafing, vertical, forward));
                this.mount.move(MoverType.SELF, this.mount.getDeltaMovement());
                this.mount.setDeltaMovement(this.mount.getDeltaMovement().multiply(new Vec3(0.9, 0.9, 0.9)));

                Vec3 currentMotion = this.mount.getDeltaMovement();
                if (this.mount.horizontalCollision) {
                    currentMotion = new Vec3(currentMotion.x, 0.1D, currentMotion.z);
                }
                this.mount.setDeltaMovement(currentMotion);
                this.mount.calculateEntityAnimation(false);
            } else {
                this.mount.setDeltaMovement(Vec3.ZERO);
            }
            return true;
        } else if (this.mount.isInWater() || this.mount.isInLava()) {
            double forward = rider.zza;
            double strafing = rider.xxa;
            double vertical = 0;
            float speed = (float) this.mount.getAttributeValue(Attributes.MOVEMENT_SPEED);

            if (this.flying.isGoingUp() && !this.flying.isGoingDown()) {
                vertical = 0.5f;
            } else if (this.flying.isGoingDown() && !this.flying.isGoingUp()) {
                vertical = -0.5f;
            }

            flyingSpeed = speed;
            this.mount.setSpeed(flyingSpeed);
            this.mount.setZza((float) forward);
            vanillaTravel.accept(travelVector.add(strafing, vertical, forward));
            return true;
        } else {
            double forward = rider.zza;
            double strafing = rider.xxa * 0.5f;
            double vertical = travelVector.y;
            float speed = (float) this.mount.getAttributeValue(Attributes.MOVEMENT_SPEED);

            float groundSpeedModifier = (float) (1.8F * this.flying.getFlightSpeedModifier());
            speed *= groundSpeedModifier;
            forward *= speed;
            forward *= rider.isSprinting() ? 1.2f : 1.0f;
            forward *= rider.zza > 0 ? 1.0f : 0.2f;

            if (this.mount.isControlledByLocalInstance()) {
                this.mount.setSpeed(speed);
                vanillaTravel.accept(new Vec3(strafing, vertical, forward));
            } else {
                this.mount.setDeltaMovement(Vec3.ZERO);
            }
            return true;
        }
    }

    /**
     * Ice and Fire {@code EntityDragonBase#updateRider} takeoff / hover / land / rider shots.
     */
    public void updateRider() {
        Entity controllingPassenger = this.mount.getControllingPassenger();
        if (!(controllingPassenger instanceof Player rider)) {
            return;
        }

        if (this.flying.startsFlyingWhenMounted()
                && !this.flying.isFlying()
                && !this.flying.isHovering()) {
            this.flying.setHovering(true);
            this.glidingSpeedBonus = 0.0f;
            syncActivity();
        }

        if (this.flying.isGoingUp()) {
            if (!this.flying.isFlying() && !this.flying.isHovering()) {
                this.spacebarTicks += 2;
            }
        }
        if (this.spacebarTicks > 0) {
            this.spacebarTicks--;
        }
        // OreSpawn 1.7.10 took off as soon as fly-up was held. Do not wait on IAF spacebarTicks.
        if (this.flying.isGoingUp()
                && !this.flying.isGoingDown()
                && !this.flying.isFlying()
                && !this.flying.isHovering()) {
            if (!this.mount.isInWater()) {
                this.flying.setHovering(true);
                this.spacebarTicks = 0;
                this.glidingSpeedBonus = 0;
                syncActivity();
            }
        }
        if (this.flying.isFlying() || this.flying.isHovering()) {
            if (rider.zza > 0) {
                this.flying.setFlying(true);
                this.flying.setHovering(false);
            } else {
                this.flying.setFlying(false);
                this.flying.setHovering(true);
            }
            if (!this.overAir && this.flying.isGoingDown() && !this.mount.isInWater()) {
                this.flying.setFlying(false);
                this.flying.setHovering(false);
            }
            syncActivity();
        }

        // Ice and Fire {@code EntityDragonBase#updateRider}: rider projectiles from control bits.
        this.flying.applyRiderProjectileInput(rider);

        if (this.mount.getControllingPassenger() != null
                && this.mount.getControllingPassenger().isShiftKeyDown()) {
            this.mount.getControllingPassenger().stopRiding();
        }

        if (this.mount.getFeetBlockState().getFluidState().isSource()
                && this.mount.isInWater()
                && !this.flying.isGoingUp()) {
            this.flying.setFlying(false);
            this.flying.setHovering(false);
            syncActivity();
        }
    }

    public Vec3 prepareRiddenMove(Vec3 pos) {
        if (this.mount.isVehicle() && this.mount.isControlledByLocalInstance() && this.mount.horizontalCollision) {
            this.mount.setDeltaMovement(this.mount.getDeltaMovement().multiply(0.6F, 1, 0.6F));
        }
        return pos;
    }

    public void finishRiddenMove() {
        if (this.flying.getRidingPlayer() != null) {
            this.mount.setNoGravity(this.flying.isHovering() || this.flying.isFlying());
            this.mount.noPhysics = false;
        }
    }

    public boolean isOverAir() {
        return this.overAir;
    }

    private boolean isOverAirLogic() {
        return this.mount
                .level()
                .isEmptyBlock(
                        BlockPos.containing(
                                this.mount.getBlockX(),
                                this.mount.getBoundingBox().minY - 1,
                                this.mount.getBlockZ()));
    }

    private void syncActivity() {
        this.flying.syncRiderFlightActivity(this.flying.isFlying() || this.flying.isHovering());
    }
}
