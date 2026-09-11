package com.astryxion.chaospersists.legacy.minecraft.util.math;

/** Legacy 1.12 block position alias. */
public class BlockPos extends net.minecraft.core.BlockPos {
  public BlockPos(int x, int y, int z) {
    super(x, y, z);
  }

  public BlockPos(double x, double y, double z) {
    super((int) x, (int) y, (int) z);
  }
}
