package com.astryxion.chaospersists.client.event;

import com.astryxion.chaospersists.client.item.BigWeaponModelHandler;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;

/**
 * Registers client-only mod-bus listeners. Kept in a separate class so dedicated servers never
 * resolve {@code @OnlyIn} methods or client event types on {@link ChaosPersists}.
 */
@OnlyIn(Dist.CLIENT)
public final class ClientModBusEvents {

    private ClientModBusEvents() {}

    public static void register(IEventBus modBus, ChaosPersists mod) {
        modBus.addListener(mod::registerEntityRenderers);
        modBus.addListener(mod::clientInit);
        modBus.addListener(BigWeaponModelHandler::onModifyBakingResult);
        modBus.addListener(com.astryxion.chaospersists.client.input.KeyHandler::registerKeyMappings);
        net.minecraftforge.fml.ModLoadingContext.get()
                .registerExtensionPoint(
                        net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory.class,
                        () ->
                                new net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory(
                                        (mc, parent) ->
                                                new com.astryxion.chaospersists.client.gui.ChaosConfigScreen(parent)));
    }
}
