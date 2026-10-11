package com.astryxion.chaospersists.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;

/** Rebuild creative tabs after config Done so disabled mob eggs vanish immediately. */
public final class ChaosCreativeTabs {
    private ChaosCreativeTabs() {}

    public static void refresh() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.player.connection == null) {
            return;
        }
        boolean opTab = mc.options.operatorItemsTab().get() && mc.player.canUseGameMasterBlocks();
        CreativeModeTabs.tryRebuildTabContents(
                mc.player.connection.enabledFeatures(), opTab, mc.player.connection.registryAccess());
    }
}
