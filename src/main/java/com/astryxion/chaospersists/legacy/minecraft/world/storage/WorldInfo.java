package com.astryxion.chaospersists.legacy.minecraft.world.storage;

import com.astryxion.chaospersists.legacy.minecraft.world.WorldType;

/** Legacy 1.12 world info for terrain type on custom dimensions. */
public class WorldInfo {
  private WorldType terrainType = WorldType.DEFAULT;

  public WorldType getTerrainType() {
    return terrainType;
  }

  public void setTerrainType(WorldType type) {
    this.terrainType = type;
  }
}
