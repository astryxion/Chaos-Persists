package com.astryxion.chaospersists.legacy.minecraft.world;

import com.astryxion.chaospersists.legacy.minecraft.world.biome.BiomeProvider;
import com.astryxion.chaospersists.legacy.minecraft.world.gen.IChunkGenerator;

/** Legacy 1.12 dimension provider base for Chaos Persists dimensions. */
public abstract class WorldProvider {
  public World world;
  public boolean hasSkyLight = true;
  public BiomeProvider biomeProvider;

  public void init() {}

  public abstract IChunkGenerator createChunkGenerator();

  public boolean isSurfaceWorld() {
    return true;
  }

  public boolean canRespawnHere() {
    return true;
  }

  public void setWorldTime(long time) {}

  public int getDimension() {
    return 0;
  }

  public com.astryxion.chaospersists.legacy.minecraft.world.biome.BiomeProvider getBiomeProvider() {
    return biomeProvider;
  }
}
