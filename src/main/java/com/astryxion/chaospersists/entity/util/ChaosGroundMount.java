package com.astryxion.chaospersists.entity.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

/**
 * Shared Ice-and-Fire rider-ground contract. Hover-climbers (spider/ant robots) and walkers
 * (ostrich) both import {@link ChaosMountGround} instead of OreSpawn boat-lerp travel.
 */
public interface ChaosGroundMount extends ICustomMoveController {

    default Mob asMob() {
        return (Mob) this;
    }

    default Player getRidingPlayer() {
        LivingEntity passenger = asMob().getControllingPassenger();
        return passenger instanceof Player player ? player : null;
    }

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

    default boolean isGoingUp() {
        return (getControlState() & 1) == 1;
    }

    default boolean isGoingDown() {
        return (getControlState() >> 1 & 1) == 1;
    }

    /** Ice and Fire hippogryph-style hover: Jump/X for vertical, noGravity, 0.9 XYZ friction. */
    default boolean hoversWhenRidden() {
        return false;
    }

    /**
     * {@code moveRelative} factor while hovering. Hippogryph uses {@code 0.1}. Robots should use a
     * smaller value so terminal speed stays near their old OreSpawn max_speed.
     */
    default float getHoverMoveFactor() {
        return 0.1f;
    }

    /** Ice and Fire hippogryph {@code tickRidden} vertical add while Jump / X is held. */
    default float getHoverVerticalSpeed() {
        return 0.2f;
    }

    /**
     * Multiplier on {@link net.minecraft.world.entity.ai.attributes.Attributes#MOVEMENT_SPEED} for
     * walking mounts. {@code 1.0} matches the current ostrich. Ice and Fire dragon walking uses
     * {@code 1.8}.
     */
    default float getGroundSpeedModifier() {
        return 1.0f;
    }
}
