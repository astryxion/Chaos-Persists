package com.astryxion.chaospersists.legacy.minecraft.world.chunk;

import java.util.List;
import com.astryxion.chaospersists.legacy.minecraft.util.IProgressUpdate;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.biome.Biome;

/** Legacy 1.12 chunk provider interface. */
public interface IChunkProvider {
  boolean chunkExists(int x, int z);

  Chunk provideChunk(int x, int z);

  Chunk loadChunk(int x, int z);

  void populate(int x, int z);

  boolean saveChunks(boolean saveAllChunks, IProgressUpdate progress);

  boolean unloadQueuedChunks();

  boolean canSave();

  String makeString();

  List<Biome.SpawnListEntry> getPossibleCreatures(
      com.astryxion.chaospersists.legacy.minecraft.entity.EnumCreatureType creatureType, net.minecraft.core.BlockPos pos);

  net.minecraft.core.BlockPos getPrecipitationHeight(net.minecraft.core.BlockPos pos);

  void recreateStructures(int x, int z);

  boolean isInsideStructure(World worldIn, String structureName, net.minecraft.core.BlockPos pos);

  void saveExtraData();
}
