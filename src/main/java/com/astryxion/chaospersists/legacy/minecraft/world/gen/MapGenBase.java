package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.chunk.ChunkPrimer;

/** Legacy 1.12 map feature generator base. */
public class MapGenBase {
  protected int range = 8;
  protected Random rand = new Random();
  protected World world;

  public void generate(World worldIn, int chunkX, int chunkZ, ChunkPrimer primer) {}
}
