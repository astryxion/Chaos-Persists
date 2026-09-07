package com.astryxion.chaospersists.proxy;

import com.astryxion.chaospersists.network.RiderControlMessage;
import com.astryxion.chaospersists.network.RiderControlMessageHandler;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class CommonProxyChaos {
    private SimpleChannel network;

    public SimpleChannel getNetwork() {
        return this.network;
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

    public void registerNetworkStuff() {
        this.network = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("chaospersists", "chaospersists"),
                () -> "1.0",
                "1.0"::equals,
                "1.0"::equals);
        this.network.registerMessage(
                0,
                RiderControlMessage.class,
                RiderControlMessage::encode,
                RiderControlMessage::decode,
                RiderControlMessageHandler::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

    public int setArmorPrefix(String string) {
        return 0;
    }
}
