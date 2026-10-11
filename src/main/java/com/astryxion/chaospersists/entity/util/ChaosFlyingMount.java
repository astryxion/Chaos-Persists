package com.astryxion.chaospersists.entity.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

/**
 * Shared Ice-and-Fire rider-flight contract. Mounts implement the synched accessors;
 * {@link ChaosMountFlight} owns the travel / takeoff / land algorithm.
 */
public interface ChaosFlyingMount extends ICustomMoveController, IFlyingMount {

    default Mob asMob() {
        return (Mob) this;
    }

    @Override
    default Player getRidingPlayer() {
        LivingEntity passenger = asMob().getControllingPassenger();
        return passenger instanceof Player player ? player : null;
    }

    void setFlying(boolean flying);

    void setHovering(boolean hovering);

    default void setStateField(int i, boolean newState) {
        byte prevState = getControlState();
        if (newState) {
            setControlState((byte) (prevState | (1 << i)));
        } else {
            setControlState((byte) (prevState & ~(1 << i)));
        }
    }

    @Override
    default void up(boolean up) {
        setStateField(0, up);
    }

    @Override
    default void down(boolean down) {
        setStateField(1, down);
    }

    @Override
    default void attack(boolean attack) {
        setStateField(2, attack);
    }

    @Override
    default void strike(boolean strike) {
        setStateField(3, strike);
    }

    @Override
    default void dismount(boolean dismount) {
        setStateField(4, dismount);
    }

    @Override
    default boolean isGoingUp() {
        return (getControlState() & 1) == 1;
    }

    @Override
    default boolean isGoingDown() {
        return (getControlState() >> 1 & 1) == 1;
    }

    default boolean isAttackingControl() {
        return (getControlState() >> 2 & 1) == 1;
    }

    default boolean isStrikingControl() {
        return (getControlState() >> 3 & 1) == 1;
    }

    default boolean isDismounting() {
        return (getControlState() >> 4 & 1) == 1;
    }

    @Override
    default double getFlightSpeedModifier() {
        return 1.0;
    }

    /** Hoverboard-style: already airborne the moment a rider mounts. */
    default boolean startsFlyingWhenMounted() {
        return false;
    }

    default float getMinimumSpeed() {
        return 0.2f;
    }

    default float getMaximumSpeed() {
        return 0.45f;
    }

    /**
     * Extra multiplier on {@link net.minecraft.world.entity.ai.attributes.Attributes#MOVEMENT_SPEED}
     * while airborne. {@code < 0} keeps the Ice and Fire {@code 5.2 + map()} dragon formula.
     * Hoverboard-style vehicles should return a small positive value so that formula cannot
     * explode a large movement-speed attribute into terminal velocity.
     */
    default float getRiddenAirSpeedMultiplier() {
        return -1.0f;
    }

    default boolean allowMousePitchControl() {
        return true;
    }

    default boolean allowLocalMotionControl() {
        return true;
    }

    /** Keep CP wing/activity animations in sync with Ice and Fire flying/hovering flags. */
    default void syncRiderFlightActivity(boolean airborne) {}

    default void copyRiderRotation(Entity passenger) {
        Player rider = getRidingPlayer();
        if (rider != null && rider.getUUID().equals(passenger.getUUID())) {
            Mob mount = asMob();
            mount.setYRot(passenger.getYRot());
            mount.setYHeadRot(passenger.getYHeadRot());
        }
    }
}
