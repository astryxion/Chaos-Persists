package com.astryxion.chaospersists.client.event;

import com.astryxion.chaospersists.client.input.KeyHandler;
import com.astryxion.chaospersists.entity.util.ICustomMoveController;
import com.astryxion.chaospersists.network.MessageMountControl;
import com.astryxion.chaospersists.proxy.CommonProxyChaos;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Ice and Fire rider bits, applied at client tick START. A/D strafe; R/G fire.
 */
@OnlyIn(Dist.CLIENT)
public class MountFlightClientEvents {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null || player.level() == null || !player.level().isClientSide) {
            return;
        }
        if (!(player.getVehicle() instanceof ICustomMoveController moveController)) {
            return;
        }
        Entity entity = player.getVehicle();
        byte previousState = moveController.getControlState();
        applyTo(moveController);
        byte controlState = moveController.getControlState();
        if (controlState != previousState) {
            CommonProxyChaos.NETWORK.sendToServer(
                    new MessageMountControl(
                            entity.getId(),
                            controlState,
                            entity.getX(),
                            entity.getY(),
                            entity.getZ()));
        }
    }

    public static void applyTo(ICustomMoveController controller) {
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;
        if (player == null) {
            return;
        }
        Input input = player.input;
        controller.up(KeyHandler.isFlyUp(mc, input));
        controller.down(KeyHandler.isActive(KeyHandler.KEY_FLY_DOWN));
        controller.attack(KeyHandler.isMountFireLeft(mc, input));
        controller.strike(KeyHandler.isMountFireRight(mc, input));
        controller.dismount(mc.options.keyShift.isDown());
    }
}
