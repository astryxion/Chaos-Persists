package com.astryxion.chaospersists.legacy.forge.event.terraingen;

import com.astryxion.chaospersists.legacy.minecraft.world.gen.MapGenBase;

/** Legacy Forge terrain event types for modded map generators. */
public class InitMapGenEvent {
  public enum EventType {
    CAVE,
    RAVINE,
    NETHER_CAVE,
    NETHER_BRIDGE,
    NETHER_FORTRESS,
    SCATTERED_FEATURE,
    STRONGHOLD,
    MINESHAFT,
    VILLAGE,
    OCEAN_MONUMENT
  }

  public static MapGenBase replaceGenerator(MapGenBase original, EventType type) {
    return original;
  }
}
