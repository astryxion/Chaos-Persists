package com.astryxion.chaospersists.legacy.minecraft.world.biome;

import com.astryxion.chaospersists.legacy.minecraft.world.World;

/** Legacy 1.12 single-biome provider. */
public class BiomeProviderSingle extends BiomeProvider {
  private final Biome biome;

  public BiomeProviderSingle(Biome biome) {
    super(null);
    this.biome = biome;
  }

  public BiomeProviderSingle(Biome biome, World world) {
    super(world);
    this.biome = biome;
  }

  @Override
  public Biome[] getBiomesForGeneration(Biome[] reuse, int x, int z, int width, int height) {
    if (reuse == null || reuse.length < width * height) {
      reuse = new Biome[width * height];
    }
    for (int i = 0; i < reuse.length; i++) {
      reuse[i] = biome;
    }
    return reuse;
  }

  @Override
  public Biome[] getBiomes(Biome[] reuse, int x, int z, int width, int length, boolean cache) {
    return getBiomesForGeneration(reuse, x, z, width, length);
  }
}
