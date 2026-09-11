package com.astryxion.chaospersists.legacy.minecraft.world.chunk;

import net.minecraft.core.BlockPos;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

/** Legacy 1.12 {@code Chunk} — backs onto {@link LevelChunk} for ore/tree hooks. */
public class Chunk {
  private final Level level;
  private final int x;
  private final int z;
  private final ChunkPrimer primer;
  private final byte[] biomeArray = new byte[256];
  private LevelChunk levelChunk;

  public Chunk(World world, ChunkPrimer primer, int x, int z) {
    this(world.getLevel(), primer, x, z);
  }

  public Chunk(Level level, ChunkPrimer primer, int x, int z) {
    this(level, primer, x, z, false);
  }

  /**
   * Primer-only chunk for 1.20 {@code fillFromNoise}. Must not create a {@link LevelChunk} for the
   * same coordinates while the server chunk pipeline is building that chunk.
   */
  public static Chunk fromPrimer(Level level, ChunkPrimer primer, int x, int z) {
    return new Chunk(level, primer, x, z, true);
  }

  public static Chunk fromPrimer(World world, ChunkPrimer primer, int x, int z) {
    return fromPrimer(world.getLevel(), primer, x, z);
  }

  private Chunk(Level level, ChunkPrimer primer, int x, int z, boolean primerOnly) {
    this.level = level;
    this.primer = primer;
    this.x = x;
    this.z = z;
    if (!primerOnly) {
      this.levelChunk = new LevelChunk(level, new net.minecraft.world.level.ChunkPos(x, z));
      copyPrimerToChunk();
    }
  }

  /** Uses an existing {@link LevelChunk} (e.g. during 1.20 decoration) without copying a primer. */
  public static Chunk forLevelChunk(Level level, LevelChunk levelChunk) {
    net.minecraft.world.level.ChunkPos pos = levelChunk.getPos();
    Chunk chunk = new Chunk(level, new ChunkPrimer(), pos.x, pos.z);
    chunk.levelChunk = levelChunk;
    return chunk;
  }

  private void copyPrimerToChunk() {
    int baseX = x << 4;
    int baseZ = z << 4;
    for (int lx = 0; lx < 16; lx++) {
      for (int ly = 0; ly < 256; ly++) {
        for (int lz = 0; lz < 16; lz++) {
          BlockState state = primer.getBlockState(lx, ly, lz);
          levelChunk.setBlockState(new BlockPos(baseX + lx, ly, baseZ + lz), state, false);
        }
      }
    }
  }

  public ChunkPrimer getPrimer() {
    return primer;
  }

  public byte[] getBiomeArray() {
    return biomeArray;
  }

  /** No-op for primer-only chunks; 1.20 lighting runs on the proto chunk after {@code fillFromNoise}. */
  public void generateSkylightMap() {
    if (this.levelChunk != null) {
      this.levelChunk.setUnsaved(true);
    }
  }

  public LevelChunk toLevelChunk() {
    if (this.levelChunk == null) {
      throw new IllegalStateException("Chunk has no LevelChunk; use forLevelChunk during decoration");
    }
    return levelChunk;
  }

  public int getX() {
    return x;
  }

  public int getZ() {
    return z;
  }
}
