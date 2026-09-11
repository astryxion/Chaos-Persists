package com.astryxion.chaospersists.legacy.minecraft.world.storage.loot;

import net.minecraft.resources.ResourceLocation;

/** Legacy 1.12 {@code LootTableList} chest ids for {@link net.minecraftforge.event.LootTableLoadEvent}. */
public final class LootTableList {

  public static final ResourceLocation CHESTS_SIMPLE_DUNGEON =
      new ResourceLocation("minecraft", "chests/simple_dungeon");
  public static final ResourceLocation CHESTS_JUNGLE_TEMPLE =
      new ResourceLocation("minecraft", "chests/jungle_temple");
  public static final ResourceLocation CHESTS_DESERT_PYRAMID =
      new ResourceLocation("minecraft", "chests/desert_pyramid");

  private LootTableList() {}
}
