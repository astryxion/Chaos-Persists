package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.legacy.forge.fml.common.registry.EntityRegistry;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * OreSpawn 1.7.10 relied on implicit spawn rules; 1.20.1 requires explicit spawn placement
 * registration. Called once at the end of {@code preInit} after {@link EntityRegistry#addSpawn}
 * records legacy categories.
 *
 * <p>OreSpawn 1.7.10 {@code waterCreature} used {@code Material.water}: spawn position must be in
 * water (Y {@code >=} 50). 1.20 {@code NO_RESTRICTIONS} plus {@code Y >= 50} only let lizards and
 * ducks spawn in mid-air on skylands and floating structures.
 */
public final class ChaosSpawnPlacements {
  private ChaosSpawnPlacements() {}

  @FunctionalInterface
  private interface EntitySpawnRuleInvoker {
    boolean test(
        EntityType<? extends Mob> type,
        ServerLevelAccessor level,
        MobSpawnType spawnType,
        BlockPos pos,
        RandomSource random);
  }

  private static final Map<ResourceLocation, EntitySpawnRuleInvoker> ENTITY_SPAWN_RULES = new HashMap<>();

  /** Ambient mobs that fly or hover; all other ambient spawns use ground placement. */
  private static final Set<String> AERIAL_AMBIENT_MOBS =
      Set.of(
          "cliff_racer",
          "dragonfly",
          "firefly",
          "butterfly",
          "moth",
          "mosquito",
          "ghost",
          "ghost_pumpkin_skelly",
          "fairy");

  public static void registerAll(RegisterSpawnPlacementsEvent event) {
    for (ResourceLocation id : BuiltInRegistries.ENTITY_TYPE.keySet()) {
      if (!ChaosPersists.MODID.equals(id.getNamespace())) {
        continue;
      }
      EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(id);
      if (type == null || type.getCategory() == MobCategory.MISC) {
        continue;
      }
      registerEntity(event, type);
    }
  }

  public static void refreshLegacyRules() {
    ENTITY_SPAWN_RULES.clear();
    for (Map.Entry<Class<?>, ResourceLocation> entry : EntityRegistry.getEntityClassIds().entrySet()) {
      Class<?> entityClass = entry.getKey();
      ResourceLocation entityId = entry.getValue();
      if (entityClass == null || entityId == null || ENTITY_SPAWN_RULES.containsKey(entityId)) {
        continue;
      }
      for (Method method : entityClass.getDeclaredMethods()) {
        if (!Modifier.isStatic(method.getModifiers())
            || method.getReturnType() != boolean.class
            || method.getParameterCount() != 5
            || !method.getName().startsWith("check")
            || !method.getName().endsWith("SpawnRules")) {
          continue;
        }
        ENTITY_SPAWN_RULES.put(
            entityId,
            (type, level, spawnType, pos, random) -> {
              try {
                return (boolean) method.invoke(null, type, level, spawnType, pos, random);
              } catch (ReflectiveOperationException ignored) {
                return false;
              }
            });
        break;
      }
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static void registerEntity(RegisterSpawnPlacementsEvent event, EntityType<?> type) {
    ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
    if (id == null) {
      return;
    }
    switch (id.getPath()) {
      case "large_worm" ->
          event.register(
              (EntityType<WormLarge>) type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              ChaosSpawnPlacements::checkLargeWormSpawnRules, Operation.REPLACE);
      case "frog" ->
          event.register(
              (EntityType<Frog>) type,
              SpawnPlacementTypes.NO_RESTRICTIONS,
              Heightmap.Types.MOTION_BLOCKING,
              Frog::checkFrogSpawnRules, Operation.REPLACE);
      case "attack_squid" ->
          event.register(
              (EntityType<AttackSquid>) type,
              SpawnPlacementTypes.IN_WATER,
              Heightmap.Types.MOTION_BLOCKING,
              AttackSquid::checkAttackSquidSpawnRules, Operation.REPLACE);
      case "sea_viper" ->
          event.register(
              (EntityType<SeaViper>) type,
              SpawnPlacementTypes.IN_WATER,
              Heightmap.Types.MOTION_BLOCKING,
              SeaViper::checkSeaViperSpawnRules, Operation.REPLACE);
      case "whale", "sea_monster" ->
          event.register(
              (EntityType<? extends Mob>) type,
              SpawnPlacementTypes.IN_WATER,
              Heightmap.Types.MOTION_BLOCKING,
              ChaosSpawnPlacements::checkSubmergedWaterSpawnRules, Operation.REPLACE);
      default -> {
        EntitySpawnRuleInvoker invoker = ENTITY_SPAWN_RULES.get(id);
        if (invoker != null) {
          registerWithLegacyCategory(event, (EntityType<? extends Mob>) type, invoker);
        } else {
          registerByLegacyCategory(event, type);
        }
      }
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static void registerWithLegacyCategory(
      RegisterSpawnPlacementsEvent event, EntityType<? extends Mob> type, EntitySpawnRuleInvoker invoker) {
    ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
    MobCategory category = EntityRegistry.getLegacyPlacementCategory(type);
    switch (category) {
      case MONSTER ->
          event.register(
              type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              (entityType, level, spawnType, pos, random) ->
                  !isSubmergedInWater(level, pos)
                      && invoker.test(entityType, level, spawnType, pos, random), Operation.REPLACE);
      case CREATURE ->
          event.register(
              type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              (entityType, level, spawnType, pos, random) ->
                  !isSubmergedInWater(level, pos)
                      && invoker.test(entityType, level, spawnType, pos, random), Operation.REPLACE);
      case AMBIENT -> {
        if (isAerialAmbientMob(id)) {
          event.register(
              type,
              SpawnPlacementTypes.NO_RESTRICTIONS,
              Heightmap.Types.MOTION_BLOCKING,
              (entityType, level, spawnType, pos, random) ->
                  !isSubmergedInWater(level, pos)
                      && pos.getY() >= 50
                      && invoker.test(entityType, level, spawnType, pos, random), Operation.REPLACE);
        } else {
          event.register(
              type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              (entityType, level, spawnType, pos, random) ->
                  !isSubmergedInWater(level, pos)
                      && invoker.test(entityType, level, spawnType, pos, random), Operation.REPLACE);
        }
      }
      case WATER_CREATURE, WATER_AMBIENT ->
          event.register(
              type,
              SpawnPlacementTypes.IN_WATER,
              Heightmap.Types.MOTION_BLOCKING,
              (entityType, level, spawnType, pos, random) ->
                  isLegacyWaterCreaturePosition(level, pos)
                      && invoker.test(entityType, level, spawnType, pos, random), Operation.REPLACE);
      default ->
          event.register(
              type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              (entityType, level, spawnType, pos, random) ->
                  !isSubmergedInWater(level, pos)
                      && invoker.test(entityType, level, spawnType, pos, random), Operation.REPLACE);
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static void registerByLegacyCategory(RegisterSpawnPlacementsEvent event, EntityType<?> type) {
    ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(type);
    MobCategory category = EntityRegistry.getLegacyPlacementCategory(type);
    switch (category) {
      case MONSTER ->
          event.register(
              (EntityType<? extends Monster>) type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              ChaosSpawnPlacements::checkLandMonsterSpawnRules, Operation.REPLACE);
      case CREATURE ->
          event.register(
              (EntityType<? extends Animal>) type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              ChaosSpawnPlacements::checkLandAnimalSpawnRules, Operation.REPLACE);
      case AMBIENT -> {
        if (isAerialAmbientMob(id)) {
          event.register(
              (EntityType<? extends Mob>) type,
              SpawnPlacementTypes.NO_RESTRICTIONS,
              Heightmap.Types.MOTION_BLOCKING,
              (entityType, level, spawnType, pos, random) ->
                  !isSubmergedInWater(level, pos) && pos.getY() >= 50
                      && ChaosSpawnPlacements.checkLandAmbientSpawnRules(
                          entityType, level, spawnType, pos, random), Operation.REPLACE);
        } else {
          event.register(
              (EntityType<? extends Mob>) type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              ChaosSpawnPlacements::checkLandAmbientSpawnRules, Operation.REPLACE);
        }
      }
      case WATER_CREATURE, WATER_AMBIENT ->
          event.register(
              (EntityType<? extends Mob>) type,
              SpawnPlacementTypes.IN_WATER,
              Heightmap.Types.MOTION_BLOCKING,
              ChaosSpawnPlacements::checkLegacyWaterCreatureSpawnRules, Operation.REPLACE);
      default ->
          event.register(
              (EntityType<? extends Mob>) type,
              SpawnPlacementTypes.ON_GROUND,
              Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
              ChaosSpawnPlacements::checkLandMobSpawnRules, Operation.REPLACE);
    }
  }

  private static boolean isAerialAmbientMob(ResourceLocation id) {
    return id != null && AERIAL_AMBIENT_MOBS.contains(id.getPath());
  }

  private static boolean isSubmergedInWater(ServerLevelAccessor level, BlockPos pos) {
    if (level.getFluidState(pos).is(FluidTags.WATER)) {
      return true;
    }
    return level.getFluidState(pos.below()).is(FluidTags.WATER);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static boolean checkLargeWormSpawnRules(
      EntityType<? extends Mob> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    if (isSubmergedInWater(level, pos)) {
      return false;
    }
    return WormLarge.checkWormLargeSpawnRules((EntityType<WormLarge>) type, level, spawnType, pos, random);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private static boolean checkLandAnimalSpawnRules(
      EntityType<? extends Animal> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    if (isSubmergedInWater(level, pos)) {
      return false;
    }
    return Animal.checkAnimalSpawnRules(type, level, spawnType, pos, random);
  }

  private static boolean checkLandMobSpawnRules(
      EntityType<? extends Mob> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    if (isSubmergedInWater(level, pos)) {
      return false;
    }
    return Mob.checkMobSpawnRules(type, level, spawnType, pos, random);
  }

  private static boolean checkLandAmbientSpawnRules(
      EntityType<? extends Mob> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    if (isSubmergedInWater(level, pos)) {
      return false;
    }
    return Mob.checkMobSpawnRules(type, level, spawnType, pos, random);
  }

  private static boolean checkLandMonsterSpawnRules(
      EntityType<? extends Monster> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    if (isSubmergedInWater(level, pos)) {
      return false;
    }
    return Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random);
  }

  /** OreSpawn 1.7.10 water creatures: in water, Y >= 50; day/night in entity {@code checkSpawnRules}. */
  private static boolean checkLegacyWaterCreatureSpawnRules(
      EntityType<? extends Mob> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    return isLegacyWaterCreaturePosition(level, pos);
  }

  private static boolean isLegacyWaterCreaturePosition(ServerLevelAccessor level, BlockPos pos) {
    return pos.getY() >= 50 && isSubmergedInWater(level, pos);
  }

  /** Whales / sea monsters spawn submerged in ocean water columns. */
  private static boolean checkSubmergedWaterSpawnRules(
      EntityType<? extends Mob> type,
      ServerLevelAccessor level,
      MobSpawnType spawnType,
      BlockPos pos,
      RandomSource random) {
    return pos.getY() >= 50 && level.getFluidState(pos).is(FluidTags.WATER);
  }
}
