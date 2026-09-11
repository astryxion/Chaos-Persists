package com.astryxion.chaospersists.legacy.forge.fml.common.registry;

import com.astryxion.chaospersists.legacy.minecraft.world.biome.Biome;
import com.astryxion.chaospersists.world.biome.LegacyBiomeMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Legacy 1.12 {@code EntityRegistry.addSpawn} — records spawn entries during {@code ChaosPersists}
 * init and applies them via {@link com.astryxion.chaospersists.world.biome.LegacyOverworldSpawnBiomeModifier}.
 */
public final class EntityRegistry {

  private EntityRegistry() {}

  public record LegacySpawnEntry(
      ResourceLocation entityId,
      int weight,
      int minGroup,
      int maxGroup,
      MobCategory category,
      ResourceLocation legacyBiomeGroup) {}

  private static final Map<Class<?>, ResourceLocation> ENTITY_CLASS_IDS = new HashMap<>();
  private static final List<LegacySpawnEntry> LEGACY_SPAWNS = new ArrayList<>();

  public static void registerModEntity(
      ResourceLocation id,
      Class<?> entityClass,
      String name,
      int id2,
      Object mod,
      int trackingRange,
      int updateFrequency,
      boolean sendsVelocityUpdates) {
    if (entityClass != null && id != null) {
      ENTITY_CLASS_IDS.put(entityClass, id);
    }
  }

  public static void addSpawn(
      Class<?> entityClass,
      int weight,
      int minGroup,
      int maxGroup,
      MobCategory category,
      Biome[] biomes) {
    if (entityClass == null || biomes == null || biomes.length == 0 || weight <= 0) {
      return;
    }
    ResourceLocation entityId = ENTITY_CLASS_IDS.get(entityClass);
    if (entityId == null) {
      return;
    }
    for (Biome biome : biomes) {
      if (biome == null) {
        continue;
      }
      ResourceLocation legacyGroupId = biome.getLegacyGroupId();
      if (legacyGroupId == null) {
        continue;
      }
      LEGACY_SPAWNS.add(
          new LegacySpawnEntry(entityId, weight, minGroup, maxGroup, category, legacyGroupId));
    }
  }

  public static List<LegacySpawnEntry> getLegacySpawns() {
    return Collections.unmodifiableList(LEGACY_SPAWNS);
  }

  /**
   * OreSpawn 1.7.10 {@code EntityRegistry.addSpawn} used the {@code EnumCreatureType} argument for
   * the biome spawn list, not the entity class hierarchy. Biome groups expand to 1.20 tags via
   * {@link LegacyBiomeMatcher} (Alex's Mobs {@code am_mob_spawns} pattern).
   */
  public static void applyLegacySpawns(Holder<net.minecraft.world.level.biome.Biome> biome, MobSpawnSettings.Builder builder) {
    if (builder == null || biome == null || LegacyBiomeMatcher.isModBiome(biome)) {
      return;
    }
    for (LegacySpawnEntry entry : LEGACY_SPAWNS) {
      if (!LegacyBiomeMatcher.matches(biome, entry.legacyBiomeGroup())) {
        continue;
      }
      EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(entry.entityId());
      if (type == null) {
        continue;
      }
      builder.addSpawn(
          entry.category(),
          new MobSpawnSettings.SpawnerData(
              type, entry.weight(), entry.minGroup(), entry.maxGroup()));
    }
  }

  public static boolean hasLegacySpawns(Holder<net.minecraft.world.level.biome.Biome> biome) {
    if (biome == null || LegacyBiomeMatcher.isModBiome(biome)) {
      return false;
    }
    for (LegacySpawnEntry entry : LEGACY_SPAWNS) {
      if (LegacyBiomeMatcher.matches(biome, entry.legacyBiomeGroup())) {
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(entry.entityId());
        if (type != null) {
          return true;
        }
      }
    }
    return false;
  }

  public static List<MobSpawnSettings.SpawnerData> getSpawnerDataForBiome(
      Holder<net.minecraft.world.level.biome.Biome> biome) {
    List<MobSpawnSettings.SpawnerData> result = new ArrayList<>();
    if (biome == null || LegacyBiomeMatcher.isModBiome(biome)) {
      return result;
    }
    for (LegacySpawnEntry entry : LEGACY_SPAWNS) {
      if (!LegacyBiomeMatcher.matches(biome, entry.legacyBiomeGroup())) {
        continue;
      }
      EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(entry.entityId());
      if (type == null) {
        continue;
      }
      result.add(
          new MobSpawnSettings.SpawnerData(
              type, entry.weight(), entry.minGroup(), entry.maxGroup()));
    }
    return result;
  }

  /** Legacy spawn category from {@link #addSpawn}; used when injecting spawns at spawn time. */
  public static List<MobSpawnSettings.SpawnerData> getSpawnerDataForBiomeAndCategory(
      Holder<net.minecraft.world.level.biome.Biome> biome, MobCategory category) {
    List<MobSpawnSettings.SpawnerData> result = new ArrayList<>();
    if (biome == null || category == null || LegacyBiomeMatcher.isModBiome(biome)) {
      return result;
    }
    for (LegacySpawnEntry entry : LEGACY_SPAWNS) {
      if (entry.category() != category
          || !LegacyBiomeMatcher.matches(biome, entry.legacyBiomeGroup())) {
        continue;
      }
      EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(entry.entityId());
      if (type == null) {
        continue;
      }
      result.add(
          new MobSpawnSettings.SpawnerData(
              type, entry.weight(), entry.minGroup(), entry.maxGroup()));
    }
    return result;
  }

  /**
   * 1.7.10 registered hostile mobs like {@code Bee} on the ambient spawn list. Use legacy category
   * for spawn placement when it differs from the entity type's {@link MobCategory}.
   */
  public static Map<Class<?>, ResourceLocation> getEntityClassIds() {
    return Collections.unmodifiableMap(ENTITY_CLASS_IDS);
  }

  @Nullable
  public static Class<?> getEntityClass(ResourceLocation entityId) {
    if (entityId == null) {
      return null;
    }
    for (Map.Entry<Class<?>, ResourceLocation> entry : ENTITY_CLASS_IDS.entrySet()) {
      if (entityId.equals(entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }

  public static MobCategory getLegacyPlacementCategory(EntityType<?> type) {
    if (type == null) {
      return MobCategory.MONSTER;
    }
    ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(type);
    if (id == null) {
      return type.getCategory();
    }
    Set<MobCategory> legacyCategories = EnumSet.noneOf(MobCategory.class);
    for (LegacySpawnEntry entry : LEGACY_SPAWNS) {
      if (entry.entityId().equals(id)) {
        legacyCategories.add(entry.category());
      }
    }
    if (legacyCategories.isEmpty()) {
      return type.getCategory();
    }
    MobCategory entityCategory = type.getCategory();
    if (entityCategory == MobCategory.MONSTER && legacyCategories.contains(MobCategory.AMBIENT)) {
      return MobCategory.AMBIENT;
    }
    if (legacyCategories.contains(MobCategory.WATER_CREATURE)) {
      return MobCategory.WATER_CREATURE;
    }
    if (legacyCategories.contains(MobCategory.WATER_AMBIENT)) {
      return MobCategory.WATER_AMBIENT;
    }
    if (legacyCategories.contains(entityCategory)) {
      return entityCategory;
    }
    return legacyCategories.iterator().next();
  }
}
