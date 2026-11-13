package com.astryxion.chaospersists.legacy.minecraft.world.storage.loot;

import net.minecraft.resources.ResourceLocation;

/** Legacy 1.12 {@code LootTableList} chest ids for {@link net.neoforged.neoforge.event.LootTableLoadEvent}. */
public final class LootTableList {

  public static final ResourceLocation CHESTS_SIMPLE_DUNGEON =
      ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon");
  public static final ResourceLocation CHESTS_JUNGLE_TEMPLE =
      ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple");
  public static final ResourceLocation CHESTS_DESERT_PYRAMID =
      ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid");

  private LootTableList() {}
}
