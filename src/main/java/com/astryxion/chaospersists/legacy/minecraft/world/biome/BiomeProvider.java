package com.astryxion.chaospersists.legacy.minecraft.world.biome;

import java.util.List;
import net.minecraft.core.BlockPos;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import net.minecraft.world.level.Level;

/** Legacy 1.12 biome provider API. */
public class BiomeProvider {
  protected final Level level;

  public BiomeProvider(World world) {
    this.level = world != null ? world.getLevel() : null;
  }

  public Biome[] getBiomesForGeneration(Biome[] reuse, int x, int z, int width, int height) {
    return getBiomes(reuse, x, z, width, height, true);
  }

  public Biome[] getBiomes(Biome[] reuse, int x, int z, int width, int length, boolean cache) {
    if (reuse == null || reuse.length < width * length) {
      reuse = new Biome[width * length];
    }
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < length; j++) {
        reuse[i + j * width] = Biome.wrap(level.getBiome(new BlockPos(x + i, 64, z + j)).value());
      }
    }
    return reuse;
  }

  public boolean areBiomesViable(int x, int y, int z, List<Biome> biomes) {
    Biome biome = Biome.wrap(level.getBiome(new BlockPos(x, y, z)).value());
    for (Biome candidate : biomes) {
      if (candidate == biome) {
        return true;
      }
    }
    return false;
  }
}
