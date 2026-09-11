package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.init.Blocks;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.level.block.state.BlockState;

/** Legacy 1.12 ravine carving for custom chunk providers. */
public class MapGenRavine extends MapGenBase {
  @Override
  public void generate(World worldIn, int chunkX, int chunkZ, ChunkPrimer primer) {
    this.world = worldIn;
    if (this.rand.nextInt(50) != 0) {
      return;
    }
    double x = chunkX * 16 + this.rand.nextInt(16);
    double z = chunkZ * 16 + this.rand.nextInt(16);
    double y = this.rand.nextInt(this.rand.nextInt(40) + 8) + 20;
  digRavine(
        this.rand.nextLong(),
        chunkX,
        chunkZ,
        primer,
        x,
        y,
        z,
        2.0F + this.rand.nextFloat() * 2.0F,
        0.0F,
        0.0F,
        -1,
        -1,
        0.5D);
  }

  protected void digRavine(
      long seed,
      int chunkX,
      int chunkZ,
      ChunkPrimer primer,
      double x,
      double y,
      double z,
      float size,
      float angle,
      float pitch,
      int step,
      int maxSteps,
      double scale) {
    Random random = new Random(seed);
    double centerX = chunkX * 16 + 8;
    double centerZ = chunkZ * 16 + 8;
    if (maxSteps <= 0) {
      int span = range * 16 - 16;
      maxSteps = span - random.nextInt(span / 4);
    }
    float driftH = 0.0F;
    float driftV = 0.0F;
    for (; step < maxSteps; step++) {
      double radius = 2.0D + Math.sin(step * Math.PI / maxSteps) * size;
      double depth = radius * scale;
      double cosP = Math.cos(pitch);
      double sinP = Math.sin(pitch);
      x += Math.cos(angle) * cosP;
      y += sinP;
      z += Math.sin(angle) * cosP;
      pitch *= 0.7F;
      pitch += driftV * 0.1F;
      angle += driftH * 0.1F;
      driftV *= 0.9F;
      driftH *= 0.75F;
      driftV += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
      driftH += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
      double dx = x - centerX;
      double dz = z - centerZ;
      double remaining = maxSteps - step;
      double limit = size + 2.0F + 16.0F;
      if (dx * dx + dz * dz - remaining * remaining > limit * limit) {
        return;
      }
      int minX = (int) (x - radius) - chunkX * 16 - 1;
      int maxX = (int) (x + radius) - chunkX * 16 + 1;
      int minY = (int) (y - depth) - 1;
      int maxY = (int) (y + depth) + 1;
      int minZ = (int) (z - radius) - chunkZ * 16 - 1;
      int maxZ = (int) (z + radius) - chunkZ * 16 + 1;
      minX = Math.max(minX, 0);
      maxX = Math.min(maxX, 16);
      minY = Math.max(minY, 1);
      maxY = Math.min(maxY, 248);
      minZ = Math.max(minZ, 0);
      maxZ = Math.min(maxZ, 16);
      for (int lx = minX; lx < maxX; lx++) {
        double nx = (lx + chunkX * 16 + 0.5D - x) / radius;
        for (int lz = minZ; lz < maxZ; lz++) {
          double nz = (lz + chunkZ * 16 + 0.5D - z) / radius;
          if (nx * nx + nz * nz < 1.0D) {
            for (int ly = minY; ly < maxY; ly++) {
              BlockState state = primer.getBlockState(lx, ly, lz);
              if (state.getBlock() == Blocks.STONE
                  || state.getBlock() == Blocks.DIRT
                  || state.getBlock() == Blocks.GRASS) {
                if (ly < 10) {
                  primer.setBlockState(lx, ly, lz, Blocks.LAVA.defaultBlockState());
                } else {
                  primer.setBlockState(lx, ly, lz, Blocks.AIR.defaultBlockState());
                }
              }
            }
          }
        }
      }
    }
  }
}
