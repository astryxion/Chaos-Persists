package com.astryxion.chaospersists.legacy.forge.fml.common.registry;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

/** Tracks blocks awaiting registration when 1.12 {@code setRegistryName} + {@code GameRegistry.register} is used. */
public final class LegacyBlockRegistry {
  private static final Map<Block, ResourceLocation> PENDING_KEYS = new IdentityHashMap<>();

  private LegacyBlockRegistry() {}

  public static void track(Block block, String modId, String path) {
    PENDING_KEYS.put(block, new ResourceLocation(modId, path));
  }

  static ResourceLocation getPendingKey(Block block) {
    return PENDING_KEYS.get(block);
  }

  static void clear(Block block) {
    PENDING_KEYS.remove(block);
  }
}
