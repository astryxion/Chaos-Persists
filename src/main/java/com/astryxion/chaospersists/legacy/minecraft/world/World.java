package com.astryxion.chaospersists.legacy.minecraft.world;

import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import com.astryxion.chaospersists.legacy.minecraft.world.biome.Biome;
import com.astryxion.chaospersists.legacy.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import com.astryxion.chaospersists.legacy.minecraft.world.storage.WorldInfo;

/** Legacy 1.12 {@code World} facade over {@link Level}. */
public class World {
  private final Level level;
  public final Random rand;
  public WorldProvider provider;
  private final WorldInfo worldInfo = new WorldInfo();
  private final BiomeProvider biomeProvider;

  public World(Level level) {
    this.level = level;
    this.rand = new Random(level.getRandom().nextLong());
    this.biomeProvider = new BiomeProvider(this);
  }

  public World(Level level, WorldProvider provider) {
    this(level);
    this.provider = provider;
    if (provider != null) {
      provider.world = this;
    }
  }

  public Level getLevel() {
    return level;
  }

  public BiomeProvider getBiomeProvider() {
    if (this.provider != null && this.provider.getBiomeProvider() != null) {
      return this.provider.getBiomeProvider();
    }
    return this.biomeProvider;
  }

  public WorldInfo getWorldInfo() {
    return worldInfo;
  }

  public long getSeed() {
    if (level instanceof net.minecraft.server.level.ServerLevel serverLevel) {
      return serverLevel.getSeed();
    }
    return level.random.nextLong();
  }

  public Random setRandomSeed(int x, int z, int feature) {
    long seed = (long) x * 341873128712L + (long) z * 132897987541L + feature + getSeed();
    this.rand.setSeed(seed);
    return this.rand;
  }

  public Biome getBiome(BlockPos pos) {
    Holder<net.minecraft.world.level.biome.Biome> holder = level.getBiome(pos);
    return Biome.wrap(holder.value());
  }

  public RandomSource getRandom() {
    return level.getRandom();
  }

  public int getMaxBuildHeight() {
    return level.getMaxBuildHeight();
  }

  public void setBlockState(BlockPos pos, net.minecraft.world.level.block.state.BlockState state, int flags) {
    level.setBlock(pos, state, flags);
  }

  public net.minecraft.world.level.block.state.BlockState getBlockState(BlockPos pos) {
    return level.getBlockState(pos);
  }

  public BlockPos getPrecipitationHeight(BlockPos pos) {
    return level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING, pos);
  }

  public boolean canBlockFreezeBody(BlockPos pos, boolean mustBeAtEdge) {
    return level.getBiome(pos).value().coldEnoughToSnow(pos);
  }

  public boolean canSnowAt(BlockPos pos, boolean checkLight) {
    return level.getBiome(pos).value().coldEnoughToSnow(pos);
  }
}
