package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.Random;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

/** Legacy 1.12 Perlin noise used for biome surface depth in chunk providers. */
public class NoiseGeneratorPerlin {
  private final ImprovedNoise noise;

  public NoiseGeneratorPerlin(Random random, int octavesIn) {
    this.noise = new ImprovedNoise(RandomSource.create(random.nextLong()));
  }

  public double[] getRegion(
      double[] values,
      double xOffset,
      double zOffset,
      int xSize,
      int zSize,
      double xScale,
      double zScale,
      double yScale) {
    int total = xSize * zSize;
    if (values == null || values.length < total) {
      values = new double[total];
    }
    int idx = 0;
    for (int j = 0; j < zSize; j++) {
      for (int i = 0; i < xSize; i++) {
        values[idx++] =
            noise.noise((xOffset + i) * xScale, 0.0, (zOffset + j) * zScale) * yScale;
      }
    }
    return values;
  }
}
