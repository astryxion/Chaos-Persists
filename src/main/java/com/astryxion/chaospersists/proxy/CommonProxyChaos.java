package com.astryxion.chaospersists.proxy;

import com.astryxion.chaospersists.entity.util.ICustomMoveController;
import net.minecraft.world.entity.player.Player;

public class CommonProxyChaos {
    public Player getClientSidePlayer() {
        return null;
    }

    public void registerRenderThings() {
    }

    public void registerBlockModels() {
    }

    public void registerBlockRenderLayers() {
    }

    public void registerBlockColors() {
    }

    public void registerLeafColors() {
    }

    public void registerItemColors() {
    }

    public void registerSoundThings() {
    }

    public void registerKeyboardInput() {
    }

    /** Client only: stamp this frame's rider keys onto the mount before travel. */
    public void applyLocalMountControls(ICustomMoveController controller) {}

    public int setArmorPrefix(String string) {
        return 0;
    }
}
