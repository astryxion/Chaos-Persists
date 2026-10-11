package com.astryxion.chaospersists.entity.util;

import net.minecraft.world.entity.player.Player;

/** Ice and Fire 1.20.1 {@code ICustomMoveController} — bit-packed rider controls. */
public interface ICustomMoveController {
    void up(boolean up);

    void down(boolean down);

    void attack(boolean attack);

    void strike(boolean strike);

    void dismount(boolean dismount);

    void setControlState(byte state);

    byte getControlState();

    /**
     * OreSpawn 1.7.10 spawned rider projectiles from the ridden entity's travel using
     * {@code moveStrafing}. Call this on the server as soon as A/D control bits arrive.
     */
    default void applyRiderProjectileInput(Player rider) {}
}
