package com.astryxion.chaospersists.legacy.minecraft.world.chunk;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

/** Legacy 1.12 chunk primer buffer (16×256×16). */
public class ChunkPrimer {
  private final BlockState[] data = new BlockState[65536];

  public ChunkPrimer() {
  }

  public void setBlockState(int x, int y, int z, BlockState state) {
    if (x < 0 || x >= 16 || y < 0 || y >= 256 || z < 0 || z >= 16) {
      return;
    }
    data[(x * 16 + z) * 256 + y] = state == null ? Blocks.AIR.defaultBlockState() : state;
  }

  public BlockState getBlockState(int x, int y, int z) {
    if (x < 0 || x >= 16 || y < 0 || y >= 256 || z < 0 || z >= 16) {
      return Blocks.AIR.defaultBlockState();
    }
    BlockState state = data[(x * 16 + z) * 256 + y];
    return state == null ? Blocks.AIR.defaultBlockState() : state;
  }
}
