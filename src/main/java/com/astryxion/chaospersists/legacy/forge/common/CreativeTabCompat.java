package com.astryxion.chaospersists.legacy.forge.common;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/** Legacy 1.12 {@code Item.setCreativeTab} / {@code Block.setCreativeTab} for Chaos Persists tab remap. */
public final class CreativeTabCompat {

  private static final Map<Item, CreativeModeTab> ITEM_TABS = new IdentityHashMap<>();

  private CreativeTabCompat() {}

  public static void setCreativeTab(Item item, CreativeModeTab tab) {
    if (item != null) {
      ITEM_TABS.put(item, tab);
    }
  }

  public static void setCreativeTab(Block block, CreativeModeTab tab) {
    if (block != null) {
      setCreativeTab(block.asItem(), tab);
    }
  }

  public static CreativeModeTab getCreativeTab(Item item) {
    return item == null ? null : ITEM_TABS.get(item);
  }
}
