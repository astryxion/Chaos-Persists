package com.astryxion.chaospersists.legacy.minecraft.init;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import com.astryxion.chaospersists.legacy.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ForgeRegistries;

/** Legacy 1.12 biome constants for spawn registration and chunk population. */
public final class Biomes {
  private static final Map<String, String> PATH_ALIASES = new HashMap<>();

  static {
    PATH_ALIASES.put("desert_hills", "desert");
    PATH_ALIASES.put("extreme_hills_edge", "windswept_gravelly_hills");
    PATH_ALIASES.put("extreme_hills_with_trees", "windswept_forest");
    PATH_ALIASES.put("forest_hills", "forest");
    PATH_ALIASES.put("jungle_hills", "sparse_jungle");
    PATH_ALIASES.put("redwood_taiga", "old_growth_pine_taiga");
    PATH_ALIASES.put("redwood_taiga_hills", "old_growth_pine_taiga");
    PATH_ALIASES.put("mesa", "badlands");
    PATH_ALIASES.put("mesa_rock", "badlands");
    PATH_ALIASES.put("mesa_clear_rock", "eroded_badlands");
    PATH_ALIASES.put("cold_taiga", "snowy_taiga");
    PATH_ALIASES.put("cold_taiga_hills", "snowy_taiga");
    PATH_ALIASES.put("roofed_forest", "dark_forest");
    PATH_ALIASES.put("stone_beach", "stony_shore");
    PATH_ALIASES.put("swampland", "swamp");
    PATH_ALIASES.put("ice_plains", "snowy_plains");
    PATH_ALIASES.put("hell", "nether_wastes");
    PATH_ALIASES.put("birch_forest_hills", "birch_forest");
    PATH_ALIASES.put("taiga_hills", "taiga");
    PATH_ALIASES.put("mushroom_island", "mushroom_fields");
  }

  public static final Biome BEACH = wrap("BEACH");
  public static final Biome BIRCH_FOREST = wrap("BIRCH_FOREST");
  public static final Biome BIRCH_FOREST_HILLS = wrap("BIRCH_FOREST_HILLS");
  public static final Biome COLD_TAIGA = wrap("COLD_TAIGA");
  public static final Biome COLD_TAIGA_HILLS = wrap("COLD_TAIGA_HILLS");
  public static final Biome DEEP_OCEAN = wrap("DEEP_OCEAN");
  public static final Biome DESERT = wrap("DESERT");
  public static final Biome DESERT_HILLS = wrap("DESERT_HILLS");
  public static final Biome EXTREME_HILLS = wrap("EXTREME_HILLS");
  public static final Biome EXTREME_HILLS_EDGE = wrap("EXTREME_HILLS_EDGE");
  public static final Biome EXTREME_HILLS_WITH_TREES = wrap("EXTREME_HILLS_WITH_TREES");
  public static final Biome FOREST = wrap("FOREST");
  public static final Biome FOREST_HILLS = wrap("FOREST_HILLS");
  public static final Biome FROZEN_RIVER = wrap("FROZEN_RIVER");
  public static final Biome HELL = wrap("HELL");
  public static final Biome ICE_PLAINS = wrap("ICE_PLAINS");
  public static final Biome JUNGLE = wrap("JUNGLE");
  public static final Biome JUNGLE_HILLS = wrap("JUNGLE_HILLS");
  public static final Biome MESA = wrap("MESA");
  public static final Biome MESA_CLEAR_ROCK = wrap("MESA_CLEAR_ROCK");
  public static final Biome MESA_ROCK = wrap("MESA_ROCK");
  public static final Biome MUSHROOM_ISLAND = wrap("MUSHROOM_ISLAND");
  public static final Biome OCEAN = wrap("OCEAN");
  public static final Biome PLAINS = wrap("PLAINS");
  public static final Biome REDWOOD_TAIGA = wrap("REDWOOD_TAIGA");
  public static final Biome REDWOOD_TAIGA_HILLS = wrap("REDWOOD_TAIGA_HILLS");
  public static final Biome RIVER = wrap("RIVER");
  public static final Biome ROOFED_FOREST = wrap("ROOFED_FOREST");
  public static final Biome SAVANNA = wrap("SAVANNA");
  public static final Biome SAVANNA_PLATEAU = wrap("SAVANNA_PLATEAU");
  public static final Biome STONE_BEACH = wrap("STONE_BEACH");
  public static final Biome SWAMPLAND = wrap("SWAMPLAND");
  public static final Biome TAIGA = wrap("TAIGA");
  public static final Biome TAIGA_HILLS = wrap("TAIGA_HILLS");

  private Biomes() {}

  private static Biome wrap(String legacyConstant) {
    String path = legacyConstant.toLowerCase();
    String resolved = PATH_ALIASES.getOrDefault(path, path);
    net.minecraft.world.level.biome.Biome biome =
        ForgeRegistries.BIOMES.getValue(new ResourceLocation(resolved));
    if (biome == null) {
      biome =
          ForgeRegistries.BIOMES.getValue(
              net.minecraft.world.level.biome.Biomes.PLAINS.location());
    }
    return Biome.wrap(biome, path);
  }
}
