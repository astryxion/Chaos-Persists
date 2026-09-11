package com.astryxion.chaospersists.legacy.forge.fml.common.network;

/**
 * 1.12 GUI handler registration stub — crystal furnace uses {@code MenuProvider}; workbench uses menu open paths.
 */
public class NetworkRegistry {
    public static final NetworkRegistry INSTANCE = new NetworkRegistry();

    public void registerGuiHandler(Object modInstance, Object guiHandler) {
        // Preserved call site from 1.12.2 {@code make_some_more_things}; no-op on 1.20.1 client menu API.
    }
}
