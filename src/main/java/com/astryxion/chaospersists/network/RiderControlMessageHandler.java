package com.astryxion.chaospersists.network;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RiderControlMessageHandler {

    public static void handle(RiderControlMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (context.getDirection() == NetworkDirection.PLAY_TO_SERVER) {
                ChaosPersists.flyup_keystate = message.keystate;
            }
        });
        context.setPacketHandled(true);
    }
}
