package com.astryxion.chaospersists.client.event;

import com.astryxion.chaospersists.client.item.BigWeaponModelHandler;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;

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
        net.neoforged.neoforge.common.NeoForge.EVENT_BUS.register(new MountFlightClientEvents());
        modBus.addListener((net.neoforged.neoforge.client.event.RegisterMenuScreensEvent event) ->
                event.register(ChaosPersists.MENU_CRYSTAL_WORKBENCH.get(),
                        com.astryxion.chaospersists.client.gui.CrystalWorkbenchGUI::new));
        modBus.addListener(BigWeaponModelHandler::onModifyBakingResult);
        modBus.addListener(com.astryxion.chaospersists.client.input.KeyHandler::registerKeyMappings);
        net.neoforged.fml.ModLoadingContext.get()
                .registerExtensionPoint(
                        net.neoforged.neoforge.client.gui.IConfigScreenFactory.class,
                        () -> (container, parent) ->
                                new com.astryxion.chaospersists.client.gui.ChaosConfigScreen(parent));
    }
}
