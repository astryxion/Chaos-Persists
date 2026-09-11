package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.List;
import net.minecraft.core.BlockPos;
import com.astryxion.chaospersists.legacy.minecraft.entity.EnumCreatureType;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.biome.Biome;
import com.astryxion.chaospersists.legacy.minecraft.world.chunk.Chunk;

/** Legacy 1.12 chunk generator interface for Chaos Persists dimension providers. */
public interface IChunkGenerator {
  Chunk getLoadedChunk(int x, int z);

  Chunk generateChunk(int x, int z);

  boolean generateStructures(Chunk chunkIn, int x, int z);

  void populate(int x, int z);

  boolean isChunkGeneratedAt(int x, int z);

  boolean tick();

  boolean isInsideStructure(World worldIn, String structureName, BlockPos pos);

  void recreateStructures(Chunk chunkIn, int x, int z);

  BlockPos getNearestStructurePos(
      World worldIn, String structureName, BlockPos position, boolean findUnexplored);

  List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos);
}
