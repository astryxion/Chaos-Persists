package com.astryxion.chaospersists.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import com.astryxion.chaospersists.network.RiderControlMessage;
import com.astryxion.chaospersists.network.RiderControlMessageHandler;

public class CommonProxyChaos {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel NETWORK = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("chaospersists", "chaospersists"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);
    private static int nextPacketId;

    public SimpleChannel getNetwork() {
        return NETWORK;
    }

    public void registerRenderThings() {
    }

    public void registerBlockModels() {
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
        NETWORK.registerMessage(
                nextPacketId++,
                RiderControlMessage.class,
                RiderControlMessage::encode,
                RiderControlMessage::decode,
                RiderControlMessageHandler::handle);
    }

    public int setArmorPrefix(String string) {
        return 0;
    }
}
