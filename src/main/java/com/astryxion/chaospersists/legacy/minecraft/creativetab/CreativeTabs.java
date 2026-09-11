package com.astryxion.chaospersists.legacy.minecraft.creativetab;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;

/**
 * Legacy 1.12 {@code CreativeTabs} — wraps {@link CreativeModeTab} for Chaos Persists tab remap.
 */
public class CreativeTabs {

  public static final CreativeModeTab TOOLS =
      BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.TOOLS_AND_UTILITIES);
  public static final CreativeModeTab FOOD =
      BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.FOOD_AND_DRINKS);
  public static final CreativeModeTab COMBAT =
      BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.COMBAT);

  private final CreativeModeTab delegate;

  protected CreativeTabs(CreativeModeTab delegate) {
    this.delegate = delegate;
  }

  public CreativeModeTab getDelegate() {
    return delegate;
  }

  public static CreativeTabs wrap(CreativeModeTab tab) {
    return new CreativeTabs(tab) {};
  }
}
