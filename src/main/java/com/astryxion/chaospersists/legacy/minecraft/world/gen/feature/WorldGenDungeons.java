package com.astryxion.chaospersists.legacy.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.core.BlockPos;
import com.astryxion.chaospersists.legacy.minecraft.init.Blocks;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

/** Legacy 1.12 dungeon feature (spawner room) for custom chunk population. */
public class WorldGenDungeons {
  public boolean generate(World world, Random rand, BlockPos pos) {
    int x = pos.getX();
    int y = pos.getY();
    int z = pos.getZ();
    world.setBlockState(new BlockPos(x - 1, y, z), Blocks.STONE.defaultBlockState(), 2);
    world.setBlockState(new BlockPos(x + 1, y, z), Blocks.STONE.defaultBlockState(), 2);
    world.setBlockState(new BlockPos(x, y, z - 1), Blocks.STONE.defaultBlockState(), 2);
    world.setBlockState(new BlockPos(x, y, z + 1), Blocks.STONE.defaultBlockState(), 2);
    world.setBlockState(new BlockPos(x, y - 1, z), Blocks.STONE.defaultBlockState(), 2);
    world.setBlockState(new BlockPos(x, y + 1, z), Blocks.STONE.defaultBlockState(), 2);
    world.setBlockState(
        new BlockPos(x, y, z),
        net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(),
        2);
    if (world.getLevel().getBlockEntity(new BlockPos(x, y, z)) instanceof SpawnerBlockEntity spawner) {
      spawner.setEntityId(EntityType.ZOMBIE, net.minecraft.util.RandomSource.create(rand.nextLong()));
    }
    return true;
  }
}
