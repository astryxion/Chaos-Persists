package com.astryxion.chaospersists.entity.util;

import com.astryxion.chaospersists.util.MyUtils;
import java.util.function.Consumer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

/**
 * Ice and Fire 1.20.1 ridden-ground algorithm (hippogryph hover + dragon/horse walking), extracted
 * so Chaos Persists ground mounts share one helper instead of OreSpawn {@code setPos} climb and
 * boat lerp.
 *
 * <p>Client-authoritative while {@link Mob#isControlledByLocalInstance()} — the server must not
 * {@code setDeltaMovement} on the controlling client path or motion halts. Vertical input for
 * hover-climbers is Jump = up and X = down via {@link ICustomMoveController}.
 */
public final class ChaosMountGround {
    private final Mob mount;
    private final ChaosGroundMount ground;

    public ChaosMountGround(ChaosGroundMount ground) {
        this.ground = ground;
        this.mount = ground.asMob();
    }

    public void onMounted() {
        if (this.ground.hoversWhenRidden()) {
            this.mount.setNoGravity(true);
            this.mount.noPhysics = false;
        }
        MyUtils.clearChaosFlight(this.mount);
    }

    public void onDismount() {
        this.ground.setControlState((byte) 0);
        this.mount.setNoGravity(false);
        this.mount.noPhysics = false;
        MyUtils.clearChaosFlight(this.mount);
    }

    /** Ice and Fire {@code EntityHippogryph#tickRidden} / {@code getRiddenRotation}. */
    public void applyRiderRotation(Player rider) {
        this.mount.setYRot(rider.getYRot());
        this.mount.setXRot(rider.getXRot() * 0.5f);
        this.mount.yRotO = this.mount.yBodyRot = this.mount.yHeadRot = this.mount.getYRot();
    }

    /**
     * @return {@code true} if rider movement was handled and the entity should not run AI travel
     */
    public boolean handleTravel(Vec3 travelVector, Consumer<Vec3> vanillaTravel) {
        Player rider = this.ground.getRidingPlayer();
        if (rider == null) {
            return false;
        }
        if (rider.isDeadOrDying()) {
            this.mount.ejectPassengers();
            this.mount.setNoGravity(false);
            vanillaTravel.accept(travelVector);
            return true;
        }

        MyUtils.clearChaosFlight(this.mount);
        this.mount.noPhysics = false;
        this.applyRiderRotation(rider);

        float strafe = rider.xxa * 0.5f;
        float forward = rider.zza;
        if (forward <= 0.0f) {
            forward *= 0.25f;
        }

        if (!this.mount.isControlledByLocalInstance()) {
            this.mount.setDeltaMovement(Vec3.ZERO);
            return true;
        }

        if (this.ground.hoversWhenRidden()) {
            this.mount.setNoGravity(true);
            this.mount.moveRelative(this.ground.getHoverMoveFactor(), new Vec3(strafe, 0.0, forward));
            float vertical = 0.0f;
            if (this.ground.isGoingUp() && !this.ground.isGoingDown()) {
                vertical = this.ground.getHoverVerticalSpeed();
            } else if (this.ground.isGoingDown() && !this.ground.isGoingUp()) {
                vertical = -this.ground.getHoverVerticalSpeed();
            }
            this.mount.setDeltaMovement(this.mount.getDeltaMovement().add(0.0, vertical, 0.0));
            this.mount.move(MoverType.SELF, this.mount.getDeltaMovement());
            this.mount.setDeltaMovement(this.mount.getDeltaMovement().scale(0.9));
            if (this.mount.horizontalCollision) {
                Vec3 motion = this.mount.getDeltaMovement();
                this.mount.setDeltaMovement(motion.x, 0.1, motion.z);
            }
            this.mount.calculateEntityAnimation(false);
            return true;
        }

        float speed = (float) this.mount.getAttributeValue(Attributes.MOVEMENT_SPEED);
        speed *= this.ground.getGroundSpeedModifier();
        this.mount.setSpeed(speed);
        if (Math.abs(rider.zza) > 0.001f || Math.abs(rider.xxa) > 0.001f) {
            vanillaTravel.accept(new Vec3(rider.xxa, travelVector.y, rider.zza));
        } else {
            vanillaTravel.accept(new Vec3(0.0, travelVector.y, 0.0));
        }
        return true;
    }

    public void finishRiddenMove() {
        if (this.ground.getRidingPlayer() != null && this.ground.hoversWhenRidden()) {
            this.mount.setNoGravity(true);
            this.mount.noPhysics = false;
        }
    }
}
