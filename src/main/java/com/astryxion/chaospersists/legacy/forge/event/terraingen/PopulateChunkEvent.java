package com.astryxion.chaospersists.legacy.forge.event.terraingen;

import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.gen.IChunkGenerator;

/** Legacy Forge populate-chunk event types. */
public class PopulateChunkEvent {
  public static boolean onChunkPopulate(
      boolean newChunk,
      IChunkGenerator generator,
      World world,
      Random rand,
      int chunkX,
      int chunkZ,
      boolean hasVillage) {
    return true;
  }

  public static boolean onReplaceBiomeBlocks(
      IChunkGenerator generator, int x, int z, com.astryxion.chaospersists.legacy.minecraft.world.chunk.ChunkPrimer primer, World world) {
    return true;
  }

  public static class Populate {
    public enum EventType {
      LAVA,
      LAKE,
      DUNGEON,
      ICE,
      SNOW,
      REEDS,
      PUMPKIN,
      SUGARCANE,
      DESERT_WELL,
      VILLAGE,
      NETHER_LAVA,
      FIRE
    }
  }
}
