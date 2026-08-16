package com.astryxion.chaospersists.world;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * Tracks chunks that finished OreSpawn-style populate decoration in mod dimensions.
 * Without this, re-visiting a dimension only loads terrain and never runs giant trees / islands / etc.
 */
public final class ChaosChunkDecorationData extends SavedData {
  private static final String DATA_NAME = "chaospersists_chunk_decoration";
  private static final String TAG_CHUNKS = "chunks";
  private static final String TAG_RELIT = "relit";

  private final Set<Long> decoratedChunks = new HashSet<>();
  private final Set<Long> relitChunks = new HashSet<>();

  public ChaosChunkDecorationData() {}

  public static ChaosChunkDecorationData get(ServerLevel level) {
    return level.getDataStorage()
        .computeIfAbsent(ChaosChunkDecorationData::load, ChaosChunkDecorationData::new, DATA_NAME);
  }

  private static long pack(ResourceKey<Level> dimension, int chunkX, int chunkZ) {
    long dimHash = dimension.location().toString().hashCode();
    return (dimHash << 32) ^ (Integer.toUnsignedLong(chunkX) << 16) ^ (chunkZ & 0xFFFFL);
  }

  public boolean isDecorated(ResourceKey<Level> dimension, int chunkX, int chunkZ) {
    return decoratedChunks.contains(pack(dimension, chunkX, chunkZ));
  }

  public boolean isRelit(ResourceKey<Level> dimension, int chunkX, int chunkZ) {
    return relitChunks.contains(pack(dimension, chunkX, chunkZ));
  }

  public void markDecorated(ResourceKey<Level> dimension, int chunkX, int chunkZ) {
    if (decoratedChunks.add(pack(dimension, chunkX, chunkZ))) {
      setDirty();
    }
  }

  public void markRelit(ResourceKey<Level> dimension, int chunkX, int chunkZ) {
    if (relitChunks.add(pack(dimension, chunkX, chunkZ))) {
      setDirty();
    }
  }

  @Override
  public CompoundTag save(CompoundTag tag) {
    ListTag chunks = new ListTag();
    for (Long packed : decoratedChunks) {
      chunks.add(net.minecraft.nbt.LongTag.valueOf(packed));
    }
    tag.put(TAG_CHUNKS, chunks);
    ListTag relit = new ListTag();
    for (Long packed : relitChunks) {
      relit.add(net.minecraft.nbt.LongTag.valueOf(packed));
    }
    tag.put(TAG_RELIT, relit);
    return tag;
  }

  public static ChaosChunkDecorationData load(CompoundTag tag) {
    ChaosChunkDecorationData data = new ChaosChunkDecorationData();
    ListTag chunks = tag.getList(TAG_CHUNKS, Tag.TAG_LONG);
    for (Tag chunkTag : chunks) {
      data.decoratedChunks.add(((net.minecraft.nbt.LongTag) chunkTag).getAsLong());
    }
    ListTag relit = tag.getList(TAG_RELIT, Tag.TAG_LONG);
    for (Tag chunkTag : relit) {
      data.relitChunks.add(((net.minecraft.nbt.LongTag) chunkTag).getAsLong());
    }
    return data;
  }
}
