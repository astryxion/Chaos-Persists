package com.astryxion.chaospersists.network;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.util.ICustomMoveController;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Ice and Fire 1.20.1 {@code MessageDragonControl}. Control bits only — never {@code setPos} for
 * flying mounts, or server-side movement checks fail ("moved wrongly").
 */
public class MessageMountControl {
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

    /**
     * Ice and Fire {@code MessageHandler}: run on both physical dists so integrated singleplayer
     * (Dist.CLIENT + logical server) actually applies the packet.
     */
    public static void handle(final MessageMountControl message, final Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        Player sender = context.getSender();
        context.enqueueWork(
                () -> {
                    DistExecutor.unsafeRunWhenOn(
                            Dist.DEDICATED_SERVER, () -> () -> apply(message, sender, context));
                    DistExecutor.unsafeRunWhenOn(
                            Dist.CLIENT, () -> () -> apply(message, sender, context));
                });
        context.setPacketHandled(true);
    }

    private static void apply(
            MessageMountControl message, Player sender, NetworkEvent.Context context) {
        Player player = sender;
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            player = ChaosPersists.proxy.getClientSidePlayer();
        }
        if (player == null) {
            return;
        }
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
