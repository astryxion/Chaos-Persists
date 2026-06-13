package com.astryxion.chaospersists.mixin;

/**
 * 1.12.2 workaround for ForgeGradle #748 (Side.values polluted with BUKKIT in dev).
 * Forge 1.16.5 uses SimpleChannel and Dist; the obsolete NetworkRegistry mixin is not applied.
 */
public final class MixinNetworkRegistry {
    private MixinNetworkRegistry() {
    }
}
