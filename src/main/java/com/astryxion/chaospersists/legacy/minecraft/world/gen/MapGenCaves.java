package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.init.Blocks;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.biome.Biome;
import com.astryxion.chaospersists.legacy.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Legacy 1.12 cave carving for Chaos Persists custom chunk providers (1:1 algorithm).
 */
public class MapGenCaves extends MapGenBase {
  private static final BlockState BLK_LAVA = Blocks.LAVA.defaultBlockState();
  private static final BlockState BLK_AIR = Blocks.AIR.defaultBlockState();

  @Override
  public void generate(World worldIn, int chunkX, int chunkZ, ChunkPrimer primer) {
    this.world = worldIn;
    int caves = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);
    if (this.rand.nextInt(15) != 0) {
      return;
    }
    double x = chunkX * 16 + this.rand.nextInt(16);
    double y = this.rand.nextInt(this.rand.nextInt(120) + 8);
    double z = chunkZ * 16 + this.rand.nextInt(16);
    int tunnels = 1;
    if (this.rand.nextInt(4) == 0) {
      addRoom(this.rand.nextLong(), chunkX, chunkZ, primer, x, y, z);
      tunnels += this.rand.nextInt(4);
    }
    for (int i = 0; i < tunnels; i++) {
      float angle = this.rand.nextFloat() * (float) Math.PI * 2.0F;
      float pitch = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
      float size = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
      addTunnel(
          this.rand.nextLong(),
          chunkX,
          chunkZ,
          primer,
          x,
          y,
          z,
          size,
          angle,
          pitch,
          0,
          0,
          1.0D);
    }
    for (int i = 0; i < caves; i++) {
      double cx = chunkX * 16 + this.rand.nextInt(16);
      double cy = this.rand.nextInt(this.rand.nextInt(120) + 8);
      double cz = chunkZ * 16 + this.rand.nextInt(16);
      int count = 1;
      if (this.rand.nextInt(4) == 0) {
        addRoom(this.rand.nextLong(), chunkX, chunkZ, primer, cx, cy, cz);
        count += this.rand.nextInt(4);
      }
      for (int j = 0; j < count; j++) {
        float angle = this.rand.nextFloat() * (float) Math.PI * 2.0F;
        float pitch = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
        float size = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
        addTunnel(
            this.rand.nextLong(),
            chunkX,
            chunkZ,
            primer,
            cx,
            cy,
            cz,
            size,
            angle,
            pitch,
            0,
            0,
            1.0D);
      }
    }
  }

  protected void addRoom(
      long seed,
      int chunkX,
      int chunkZ,
      ChunkPrimer primer,
      double x,
      double y,
      double z) {
    addTunnel(seed, chunkX, chunkZ, primer, x, y, z, 1.5F + this.rand.nextFloat(), 0.0F, 0.0F, -1, -1, 0.5D);
  }

  protected void addTunnel(
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
    double d4 = chunkX * 16 + 8;
    double d5 = chunkZ * 16 + 8;
    float f6 = 0.0F;
    float f7 = 0.0F;
    Random random = new Random(seed);
    if (maxSteps <= 0) {
      int i = range * 16 - 16;
      maxSteps = i - random.nextInt(i / 4);
    }
    boolean flag = false;
    if (step == -1) {
      step = maxSteps / 2;
      flag = true;
    }
    int j = random.nextInt(maxSteps / 2) + maxSteps / 4;
    boolean flag1 = random.nextInt(6) == 0;
    for (; step < maxSteps; step++) {
      double d12 = 1.5D + Math.sin(step * Math.PI / maxSteps) * size;
      double d13 = d12 * scale;
      double d14 = Math.cos(pitch);
      double d15 = Math.sin(pitch);
      x += Math.cos(angle) * d14;
      y += d15;
      z += Math.sin(angle) * d14;
      if (flag1) {
        pitch *= 0.92F;
      } else {
        pitch *= 0.7F;
      }
      pitch += f7 * 0.1F;
      angle += f6 * 0.1F;
      f7 *= 0.9F;
      f6 *= 0.75F;
      f7 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
      f6 += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
      if (!flag && step == j && size > 1.0F) {
        addTunnel(
            random.nextLong(),
            chunkX,
            chunkZ,
            primer,
            x,
            y,
            z,
            random.nextFloat() * 0.5F + 0.5F,
            angle - (float) Math.PI / 2.0F,
            pitch / 3.0F,
            step,
            maxSteps,
            1.0D);
        addTunnel(
            random.nextLong(),
            chunkX,
            chunkZ,
            primer,
            x,
            y,
            z,
            random.nextFloat() * 0.5F + 0.5F,
            angle + (float) Math.PI / 2.0F,
            pitch / 3.0F,
            step,
            maxSteps,
            1.0D);
        return;
      }
      if (!flag && random.nextInt(4) == 0) {
        continue;
      }
      double d6 = x - d4;
      double d7 = z - d5;
      double d8 = maxSteps - step;
      double d9 = size + 2.0F + 16.0F;
      if (d6 * d6 + d7 * d7 - d8 * d8 > d9 * d9) {
        return;
      }
      if (x < d4 - 16.0D - d12 * 2.0D
          || x > d4 + 16.0D + d12 * 2.0D
          || z < d5 - 16.0D - d12 * 2.0D
          || z > d5 + 16.0D + d12 * 2.0D) {
        continue;
      }
      int k1 = (int) (x - d12) - chunkX * 16 - 1;
      int l1 = (int) (x + d12) - chunkX * 16 + 1;
      int i2 = (int) (y - d13) - 1;
      int j2 = (int) (y + d13) + 1;
      int k2 = (int) (z - d12) - chunkZ * 16 - 1;
      int l2 = (int) (z + d12) - chunkZ * 16 + 1;
      if (k1 < 0) {
        k1 = 0;
      }
      if (l1 > 16) {
        l1 = 16;
      }
      if (i2 < 1) {
        i2 = 1;
      }
      if (j2 > 248) {
        j2 = 248;
      }
      if (k2 < 0) {
        k2 = 0;
      }
      if (l2 > 16) {
        l2 = 16;
      }
      boolean water = false;
      for (int i3 = k1; !water && i3 < l1; i3++) {
        for (int j3 = k2; !water && j3 < l2; j3++) {
          for (int k3 = j2 + 1; !water && k3 >= i2 - 1; k3--) {
            if (k3 >= 0 && k3 < 256) {
              BlockState state = primer.getBlockState(i3, k3, j3);
              if (state.getBlock() == Blocks.WATER || state.getFluidState().isSource()) {
                water = true;
              }
              if (k3 != i2 - 1 && i3 != k1 && i3 != l1 - 1 && j3 != k2 && j3 != l2 - 1) {
                k3 = i2;
              }
            }
          }
        }
      }
      for (int i3 = k1; i3 < l1; i3++) {
        double d10 = (i3 + chunkX * 16 + 0.5D - x) / d12;
        for (int j3 = k2; j3 < l2; j3++) {
          double d11 = (j3 + chunkZ * 16 + 0.5D - z) / d12;
          if (d10 * d10 + d11 * d11 < 1.0D) {
            for (int k3 = i2; k3 < j2; k3++) {
              digBlock(primer, i3, k3, j3, chunkX, chunkZ, water);
            }
          }
        }
      }
    }
  }

  protected void digBlock(
      ChunkPrimer primer, int localX, int y, int localZ, int chunkX, int chunkZ, boolean water) {
    BlockState state = primer.getBlockState(localX, y, localZ);
    if (state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT) {
      if (y < 10) {
        primer.setBlockState(localX, y, localZ, BLK_LAVA);
      } else {
        primer.setBlockState(localX, y, localZ, BLK_AIR);
        if (water && primer.getBlockState(localX, y - 1, localZ).getBlock() == Blocks.GRASS) {
          primer.setBlockState(localX, y - 1, localZ, Blocks.DIRT.defaultBlockState());
        }
      }
    } else if (canReplaceBlock(state)) {
      if (y < 10) {
        primer.setBlockState(localX, y, localZ, BLK_LAVA);
      } else {
        primer.setBlockState(localX, y, localZ, BLK_AIR);
      }
    }
  }

  protected boolean canReplaceBlock(BlockState state) {
    net.minecraft.world.level.block.Block block = state.getBlock();
    return block == Blocks.STONE
        || block == Blocks.DIRT
        || block == Blocks.GRASS
        || block == Blocks.GRAVEL
        || block == Blocks.SAND
        || block == Blocks.LOG;
  }
}
