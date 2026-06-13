package com.astryxion.chaospersists.network;

import net.minecraft.network.PacketBuffer;

public class RiderControlMessage {
    public int keystate = 0;
    private int previous;

    public static void encode(RiderControlMessage message, PacketBuffer buf) {
        buf.writeByte(message.toInteger());
    }

    public static RiderControlMessage decode(PacketBuffer buf) {
        RiderControlMessage message = new RiderControlMessage();
        message.fromInteger(buf.readUnsignedByte());
        return message;
    }

    public void fromInteger(int value) {
        this.keystate = value;
    }

    public int toInteger() {
        return this.keystate;
    }

    public boolean hasChanged() {
        int current = this.keystate;
        boolean changed = this.previous != current;
        this.previous = current;
        return changed;
    }
}
