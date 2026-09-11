package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.legacy.forge.fml.common.registry.EntityRegistry;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.level.LevelEvent;

/**
 * Injects {@link EntityRegistry#addSpawn} entries when Minecraft rolls spawns in the Nether/End.
 * Overworld uses {@link LegacyOverworldSpawnBiomeModifier} only to avoid duplicate spawn tables.
 */
public final class LegacyOverworldSpawnPopulation {
  private LegacyOverworldSpawnPopulation() {}

  public static boolean usesLegacyVanillaBiomeSpawns(ResourceKey<Level> dimension) {
    return dimension.equals(Level.OVERWORLD)
        || dimension.equals(Level.NETHER)
        || dimension.equals(Level.END);
  }

  public static void applyPotentialSpawns(LevelEvent.PotentialSpawns event, ServerLevel level) {
    if (!usesLegacyVanillaBiomeSpawns(level.dimension())) {
      return;
    }
    BlockPos pos = event.getPos();
    Holder<Biome> biome = level.getBiome(pos);
    if (LegacyBiomeMatcher.isModBiome(biome)) {
      return;
    }
    MobCategory category = event.getMobCategory();
    List<MobSpawnSettings.SpawnerData> legacySpawns =
        EntityRegistry.getSpawnerDataForBiomeAndCategory(biome, category);
    for (MobSpawnSettings.SpawnerData data : legacySpawns) {
      event.addSpawnerData(data);
    }
  }

  public static Holder<Biome> mergeLegacySpawnsIntoBiome(
      ServerLevel level, Holder<Biome> biome, BlockPos pos) {
    if (LegacyBiomeMatcher.isModBiome(biome)) {
      return biome;
    }
    if (!EntityRegistry.hasLegacySpawns(biome)) {
      return biome;
    }
    MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
    MobSpawnSettings existing = biome.value().getMobSettings();
    for (MobCategory category : MobCategory.values()) {
      for (MobSpawnSettings.SpawnerData data : existing.getMobs(category).unwrap()) {
        spawnBuilder.addSpawn(category, data);
      }
    }
    EntityRegistry.applyLegacySpawns(biome, spawnBuilder);
    Biome merged =
        new Biome.BiomeBuilder()
            .hasPrecipitation(biome.value().hasPrecipitation())
            .temperature(biome.value().getBaseTemperature())
            .downfall(biome.value().getModifiedClimateSettings().downfall())
            .specialEffects(biome.value().getSpecialEffects())
            .mobSpawnSettings(spawnBuilder.build())
            .generationSettings(biome.value().getGenerationSettings())
            .build();
    return Holder.direct(merged);
  }
}
