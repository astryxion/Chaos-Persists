package com.astryxion.chaospersists.network;

import com.astryxion.chaospersists.entity.util.ICustomMoveController;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Ice and Fire 1.20.1 {@code MessageDragonControl}. Control bits only — never {@code setPos} for
 * flying mounts, or server-side movement checks fail ("moved wrongly").
 */
public class MessageMountControl implements CustomPacketPayload {
    public static final Type<MessageMountControl> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("chaospersists", "mount_control"));
    public static final StreamCodec<FriendlyByteBuf, MessageMountControl> CODEC =
            StreamCodec.of((buffer, message) -> encode(message, buffer), MessageMountControl::decode);

    @Override
    public Type<MessageMountControl> type() {
        return TYPE;
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(TYPE, CODEC, MessageMountControl::handle);
    }

    public int dragonId;
    public byte controlState;
    private double posX;
    private double posY;
    private double posZ;

    public MessageMountControl(int dragonId, byte controlState, double posX, double posY, double posZ) {
        this.dragonId = dragonId;
        this.controlState = controlState;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public MessageMountControl() {
    }

    public static MessageMountControl decode(FriendlyByteBuf buf) {
        return new MessageMountControl(
                buf.readInt(), buf.readByte(), buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public static void encode(MessageMountControl message, FriendlyByteBuf buf) {
        buf.writeInt(message.dragonId);
        buf.writeByte(message.controlState);
        buf.writeDouble(message.posX);
        buf.writeDouble(message.posY);
        buf.writeDouble(message.posZ);
    }

    private static void handle(MessageMountControl message, IPayloadContext context) {
        Player player = context.player();
        Entity entity = player.getVehicle();
        if (!(entity instanceof ICustomMoveController) || entity.getId() != message.dragonId) {
            entity = player.level().getEntity(message.dragonId);
        }
        if (entity instanceof ICustomMoveController controller
                && isRidingOrBeingRiddenBy(entity, player)) {
            byte previous = controller.getControlState();
            controller.setControlState(message.controlState);
            boolean newShot =
                    ((message.controlState >> 2 & 1) == 1 && (previous >> 2 & 1) == 0)
                            || ((message.controlState >> 3 & 1) == 1 && (previous >> 3 & 1) == 0);
            if (newShot) {
                controller.applyRiderProjectileInput(player);
            }
        }
    }

    private static boolean isRidingOrBeingRiddenBy(Entity vehicle, Player player) {
        if (vehicle == null || player == null) {
            return false;
        }
        if (player.getVehicle() == vehicle) {
            return true;
        }
        return vehicle.getPassengers().contains(player);
    }
}
