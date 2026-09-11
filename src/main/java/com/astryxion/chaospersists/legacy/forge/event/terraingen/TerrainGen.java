package com.astryxion.chaospersists.legacy.forge.event.terraingen;

import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.gen.IChunkGenerator;
import com.astryxion.chaospersists.legacy.minecraft.world.gen.MapGenBase;

/** Legacy Forge terrain hooks (pass-through preserves 1.12 populate flow). */
public final class TerrainGen {
  private TerrainGen() {}

  public static MapGenBase getModdedMapGen(MapGenBase original, InitMapGenEvent.EventType type) {
    return original;
  }

  public static boolean populate(
      IChunkGenerator generator,
      World world,
      Random rand,
      int chunkX,
      int chunkZ,
      boolean hasVillage,
      PopulateChunkEvent.Populate.EventType type) {
    return true;
  }

  public static InitNoiseGensEvent.Context getModdedNoiseGenerators(
      World world, Random rand, InitNoiseGensEvent.Context context) {
    return context;
  }
}
