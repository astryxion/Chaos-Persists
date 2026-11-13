package com.astryxion.chaospersists.legacy.minecraft.world.biome;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.entity.EnumCreatureType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import com.astryxion.chaospersists.legacy.minecraft.init.Blocks;
import net.minecraft.resources.ResourceLocation;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import net.minecraft.core.BlockPos;
import com.astryxion.chaospersists.legacy.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.level.block.state.BlockState;

/** Legacy 1.12 biome surface for Chaos Persists chunk decoration hooks. */
public class Biome {
  public BlockState topBlock =
      net.minecraft.world.level.block.Blocks.GRASS_BLOCK.defaultBlockState();
  public BlockState fillerBlock =
      net.minecraft.world.level.block.Blocks.DIRT.defaultBlockState();
  public final BiomeDecorator decorator = new BiomeDecorator();
  private final ResourceLocation registryName;
  /** Original 1.7.10 biome id path (e.g. {@code forest_hills}), used for spawn group matching. */
  private final String legacyPath;

  private Biome(ResourceLocation registryName, String legacyPath) {
    this.registryName = registryName;
    this.legacyPath = legacyPath;
  }

  private static final Map<ResourceLocation, float[]> LEGACY_TERRAIN = new HashMap<>();

  static {
    putTerrain("plains", 0.125F, 0.05F);
    putTerrain("desert", 0.125F, 0.05F);
    putTerrain("desert_hills", 0.25F, 0.05F);
    putTerrain("savanna", 0.125F, 0.05F);
    putTerrain("taiga", 0.2F, 0.2F);
    putTerrain("forest", 0.1F, 0.1F);
    putTerrain("ocean", -1.0F, 0.1F);
    putTerrain("deep_ocean", -1.8F, 0.1F);
  }

  private static void putTerrain(String path, float depth, float scale) {
    LEGACY_TERRAIN.put(ResourceLocation.parse(path), new float[] {depth, scale});
  }

  public static Biome wrap(net.minecraft.core.Holder<net.minecraft.world.level.biome.Biome> holder) {
    ResourceLocation id = holder.unwrapKey().map(net.minecraft.resources.ResourceKey::location)
        .orElse(net.minecraft.world.level.biome.Biomes.PLAINS.location());
    return new Biome(id, id.getPath());
  }

  public static Biome wrap(ResourceLocation id, String legacyPath) {
    return new Biome(id, legacyPath.toLowerCase(java.util.Locale.ROOT));
  }

  public float getBaseHeight() {
    float[] terrain = LEGACY_TERRAIN.get(registryName);
    return terrain != null ? terrain[0] : 0.125F;
  }

  public float getHeightVariation() {
    float[] terrain = LEGACY_TERRAIN.get(registryName);
    return terrain != null ? terrain[1] : 0.05F;
  }

  public void genTerrainBlocks(
      World worldIn, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
    BlockState top = this.topBlock;
    BlockState filler = this.fillerBlock;
    int depth = -1;
    int layers = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
    int lx = x & 15;
    int lz = z & 15;
    for (int y = 255; y >= 0; --y) {
      if (y <= rand.nextInt(1)) {
        primer.setBlockState(lx, y, lz, Blocks.BEDROCK.defaultBlockState());
      } else {
        BlockState existing = primer.getBlockState(lx, y, lz);
        if (existing.getBlock() == Blocks.AIR || existing.getBlock() == Blocks.WATER) {
          depth = -1;
        } else if (existing.getBlock() == Blocks.STONE) {
          if (depth == -1) {
            if (layers <= 0) {
              top = Blocks.AIR.defaultBlockState();
              filler = Blocks.STONE.defaultBlockState();
            } else if (y >= 59 && y <= 64) {
              top = this.topBlock;
              filler = this.fillerBlock;
            }
            if (y < 63 && top.getBlock() == Blocks.AIR) {
              top = Blocks.WATER.defaultBlockState();
            }
            depth = layers;
            if (y >= 62) {
              primer.setBlockState(lx, y, lz, top);
            } else if (y < 56 - layers) {
              primer.setBlockState(lx, y, lz, Blocks.GRAVEL.defaultBlockState());
            } else {
              primer.setBlockState(lx, y, lz, filler);
            }
          } else if (depth > 0) {
            --depth;
            primer.setBlockState(lx, y, lz, filler);
          }
        }
      }
    }
  }

  public ResourceLocation getRegistryName() {
    return registryName;
  }

  /** 1.7.10 biome name for {@link com.astryxion.chaospersists.world.biome.LegacyBiomeMatcher}. */
  public ResourceLocation getLegacyGroupId() {
    return ResourceLocation.parse(legacyPath);
  }

  public List<SpawnListEntry> getSpawnableList(EnumCreatureType type) {
    return Collections.emptyList();
  }

  public void decorate(World world, Random rand, BlockPos pos) {
    // Feature decoration is handled by 1.20 biome generation; 1.12 decorate is a no-op here.
  }

  public static final class SpawnListEntry {
    public final Class<?> entityClass;
    public final int weight;
    public final int minGroupCount;
    public final int maxGroupCount;

    public SpawnListEntry(Class<?> entityClass, int weight, int min, int max) {
      this.entityClass = entityClass;
      this.weight = weight;
      this.minGroupCount = min;
      this.maxGroupCount = max;
    }
  }

  public static final class BiomeDecorator {
    public int treesPerChunk;
    public int flowersPerChunk;
    public int grassPerChunk;
    public int bigMushroomsPerChunk;
    public int mushroomsPerChunk;
    public int reedsPerChunk;
  }

}
