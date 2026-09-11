package com.astryxion.chaospersists.legacy.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.core.BlockPos;
import com.astryxion.chaospersists.legacy.minecraft.init.Blocks;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** Legacy 1.12 lake feature for custom chunk population. */
public class WorldGenLakes {
  private final BlockState fluid;

  public WorldGenLakes(Block fluidBlock) {
    this.fluid = ((net.minecraft.world.level.block.Block) fluidBlock).defaultBlockState();
  }

  public boolean generate(World world, Random rand, BlockPos pos) {
    pos = pos.offset(8, 0, 8);
    for (int l = 0; l < 16; ++l) {
      for (int i1 = 0; i1 < 16; ++i1) {
        for (int j1 = 0; j1 < 8; ++j1) {
          if (!canReplace(world.getLevel().getBlockState(pos.offset(l, j1, i1)).getBlock())) {
            return false;
          }
        }
      }
    }
    for (int l = 0; l < 16; ++l) {
      for (int i1 = 0; i1 < 16; ++i1) {
        for (int j1 = 0; j1 < 8; ++j1) {
          if (l == 0
              || l == 15
              || i1 == 0
              || i1 == 15
              || j1 == 0
              || j1 == 7) {
            double d0 = (l - 7.5D) / 7.5D;
            double d1 = (i1 - 7.5D) / 7.5D;
            double d2 = (j1 - 3.5D) / 3.5D;
            if (d0 * d0 + d1 * d1 + d2 * d2 < 1.0D) {
              world.setBlockState(pos.offset(l, j1, i1), fluid, 2);
            }
          } else {
            world.setBlockState(pos.offset(l, j1, i1), fluid, 2);
          }
        }
      }
    }
    return true;
  }

  private static boolean canReplace(net.minecraft.world.level.block.Block block) {
    return block == Blocks.STONE
        || block == Blocks.DIRT
        || block == Blocks.GRASS
        || block == Blocks.LOG;
  }
}
