package com.astryxion.chaospersists.proxy;

import com.astryxion.chaospersists.entity.util.ICustomMoveController;
import com.astryxion.chaospersists.network.MessageMountControl;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class CommonProxyChaos {
    private static final String PROTOCOL_VERSION = "1";

    /** Ice and Fire {@code NETWORK_WRAPPER} — static channel, messages registered in setup. */
    public static final SimpleChannel NETWORK =
            NetworkRegistry.ChannelBuilder.named(new ResourceLocation("chaospersists", "main_channel"))
                    .clientAcceptedVersions(PROTOCOL_VERSION::equals)
                    .serverAcceptedVersions(PROTOCOL_VERSION::equals)
                    .networkProtocolVersion(() -> PROTOCOL_VERSION)
                    .simpleChannel();

    public SimpleChannel getNetwork() {
        return NETWORK;
    }

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

    public void registerNetworkStuff() {
        NETWORK.registerMessage(
                0,
                MessageMountControl.class,
                MessageMountControl::encode,
                MessageMountControl::decode,
                MessageMountControl::handle);
    }

    public int setArmorPrefix(String string) {
        return 0;
    }
}
