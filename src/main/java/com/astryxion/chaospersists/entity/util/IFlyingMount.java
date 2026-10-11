package com.astryxion.chaospersists.entity.util;

import net.minecraft.world.entity.player.Player;

/** Ice and Fire 1.20.1 {@code IFlyingMount} — ridden-flight contract. */
public interface IFlyingMount {

    Player getRidingPlayer();

    double getFlightSpeedModifier();

    default boolean fliesLikeElytra() {
        return false;
    }

    boolean isFlying();

    default boolean isGoingUp() {
        return false;
    }

    default boolean isGoingDown() {
        return false;
    }

    default boolean isHovering() {
        return false;
    }

    default double getYSpeedMod() {
        return 10;
    }
}
