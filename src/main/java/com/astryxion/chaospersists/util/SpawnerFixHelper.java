package com.astryxion.chaospersists.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Used by mixin-injected spawn validation: must live outside the mixin package
 * so it can be referenced at runtime without triggering IllegalClassLoadError.
 */
public final class SpawnerFixHelper {
    private static final ResourceLocation FALLBACK =
            new ResourceLocation("chaospersists", "ant");
    private static final Map<String, String> LEGACY_PATH_ALIASES = new HashMap<String, String>();

    static {
        // Legacy / malformed ids seen in older dungeon logic.
        // normalizePath() maps "t._rex" / "t.rex" to "t_rex" (dots -> underscores), so keys must use normalized form.
        LEGACY_PATH_ALIASES.put("t._rex", "trex");
        LEGACY_PATH_ALIASES.put("t.rex", "trex");
        LEGACY_PATH_ALIASES.put("t_rex", "trex");
        // Legacy Nightmare naming used in older OreSpawn code/configs.
        LEGACY_PATH_ALIASES.put("pitchblack", "nightmare");
        LEGACY_PATH_ALIASES.put("pitch_black", "nightmare");
        LEGACY_PATH_ALIASES.put("wtf", "gamma_metroid");
        LEGACY_PATH_ALIASES.put("goldfish", "gold_fish");
        LEGACY_PATH_ALIASES.put("gold_fish", "gold_fish");
    }

    /** True if entity is from our entity package and on first tick (spawner spawn). */
    public static boolean isChaosEntityFirstTick(Entity entity) {
        Package pkg = entity.getClass().getPackage();
        return pkg != null
                && pkg.getName().startsWith("com.astryxion.chaospersists.entity")
                && entity.tickCount == 0;
    }

    /**
     * 1.12 {@code getCanSpawnHere} spawner bypass: entity classes compare legacy display names
     * (e.g. {@code "Molenoid"}) while spawners store registry paths (e.g. {@code "molenoid"}).
     * Uses the same volume as large-mob spawner checks (TRex / Nightmare dungeons).
     */
    public static boolean isNearMatchingSpawnerForMob(Mob mob, LevelAccessor level) {
        if (mob == null || level == null) {
            return false;
        }
        return isNearMatchingSpawnerAt(mob.blockPosition(), level, EntityType.getKey(mob.getType()));
    }

    public static boolean isNearMatchingSpawnerAt(
            BlockPos base, LevelAccessor level, ResourceLocation mobId) {
        if (base == null || level == null || mobId == null) {
            return false;
        }
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int dz = -8; dz <= 8; ++dz) {
            for (int dx = -8; dx <= 8; ++dx) {
                for (int dy = -4; dy <= 8; ++dy) {
                    checkPos.set(base.getX() + dx, base.getY() + dy, base.getZ() + dz);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    BlockEntity blockEntity = MyUtils.getBlockEntityForSpawnRules(level, checkPos);
                    if (!(blockEntity instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation spawnerId = getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (spawnerId == null) {
                        continue;
                    }
                    if (entityIdsMatchForSpawner(
                            mobId, normalizeSpawnerEntityId(spawnerId))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Makes spawner ids resilient to malformed legacy values.
     * Returns a registered id whenever possible, otherwise a safe fallback.
     */
    public static ResourceLocation normalizeSpawnerEntityId(ResourceLocation input) {
        if (input != null && ForgeRegistries.ENTITY_TYPES.containsKey(input)) {
            return input;
        }

        String namespace = input == null ? "chaospersists" : normalizeToken(input.getNamespace(), true);
        String rawPath = input == null ? "" : input.getPath();
        String normalizedPath = normalizePath(rawPath);

        ResourceLocation candidate = tryResolve(namespace, normalizedPath);
        if (candidate != null) {
            return candidate;
        }

        String alias = LEGACY_PATH_ALIASES.get(normalizedPath);
        if (alias != null) {
            candidate = tryResolve(namespace, alias);
            if (candidate != null) {
                return candidate;
            }
        }

        // If namespace was wrong/missing in legacy data, try both common domains.
        candidate = tryResolve("chaospersists", normalizedPath);
        if (candidate != null) {
            return candidate;
        }
        candidate = tryResolve("minecraft", normalizedPath);
        if (candidate != null) {
            return candidate;
        }

        if (alias != null) {
            candidate = tryResolve("chaospersists", alias);
            if (candidate != null) {
                return candidate;
            }
            candidate = tryResolve("minecraft", alias);
            if (candidate != null) {
                return candidate;
            }
        }

        return ForgeRegistries.ENTITY_TYPES.containsKey(FALLBACK)
                ? FALLBACK
                : new ResourceLocation("minecraft", "pig");
    }

    /**
     * Used at entity construction callsites (e.g. EntityList#createEntityByIDFromName).
     * Keeps spawners and any legacy loaders resilient even if setEntityId was never re-run.
     */
    public static ResourceLocation normalizeEntityLookupId(ResourceLocation input) {
        ResourceLocation direct = normalizeSpawnerEntityId(input);
        if (direct != null && ForgeRegistries.ENTITY_TYPES.containsKey(direct)) {
            return direct;
        }

        // Fuzzy fallback: match registered ids by normalized path signature.
        String wanted = compactSignature(input == null ? "" : normalizePath(input.getPath()));
        if (!wanted.isEmpty()) {
            Set<ResourceLocation> ids = ForgeRegistries.ENTITY_TYPES.getKeys();
            for (ResourceLocation id : ids) {
                if ("chaospersists".equals(id.getNamespace())
                        && wanted.equals(compactSignature(normalizePath(id.getPath())))) {
                    return id;
                }
            }
            for (ResourceLocation id : ids) {
                if (wanted.equals(compactSignature(normalizePath(id.getPath())))) {
                    return id;
                }
            }
        }
        return ForgeRegistries.ENTITY_TYPES.containsKey(FALLBACK)
                ? FALLBACK
                : new ResourceLocation("minecraft", "pig");
    }

    private static ResourceLocation tryResolve(String namespace, String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        ResourceLocation id = new ResourceLocation(namespace, path);
        return ForgeRegistries.ENTITY_TYPES.containsKey(id) ? id : null;
    }

    private static String normalizeToken(String token, boolean allowMinecraftDefault) {
        if (token == null || token.isEmpty()) {
            return allowMinecraftDefault ? "chaospersists" : "";
        }
        String t = token.trim().toLowerCase(Locale.ROOT);
        if (t.equals("mod") || t.equals("orespawn")) {
            return "chaospersists";
        }
        return t;
    }

    private static String normalizePath(String path) {
        if (path == null) {
            return "";
        }
        String p = path.trim().toLowerCase(Locale.ROOT);
        p = p.replace(' ', '_').replace('-', '_').replace('.', '_');
        p = p.replaceAll("[^a-z0-9_/:]", "_");
        while (p.contains("__")) {
            p = p.replace("__", "_");
        }
        return p;
    }

    private static String compactSignature(String path) {
        return path == null ? "" : path.replace("_", "");
    }

    /**
     * Resolves duplicate/legacy registry ids (e.g. {@code chaospersists:t._rex} vs {@code chaospersists:trex})
     * to one canonical path so spawner checks match in production.
     */
    public static boolean entityIdsMatchForSpawner(ResourceLocation entityKey, ResourceLocation spawnerId) {
        if (entityKey == null || spawnerId == null) {
            return false;
        }
        String nsA = normalizeToken(entityKey.getNamespace(), true);
        String nsB = normalizeToken(spawnerId.getNamespace(), true);
        if (!nsA.equals(nsB)) {
            return false;
        }
        String pathA = canonicalEntityPathForSpawnerMatch(entityKey.getPath());
        String pathB = canonicalEntityPathForSpawnerMatch(spawnerId.getPath());
        return pathA.equalsIgnoreCase(pathB);
    }

    private static String canonicalEntityPathForSpawnerMatch(String rawPath) {
        String p = normalizePath(rawPath);
        String alias = LEGACY_PATH_ALIASES.get(p);
        return alias != null ? alias : p;
    }

    /**
     * Reads spawner entity id via public {@link BaseSpawner#save(CompoundTag)} NBT.
     * Do not reflect into {@code nextSpawnData}; that field name is not stable at runtime.
     */
    public static ResourceLocation getMobSpawnerEntityId(BaseSpawner logic) {
        if (logic == null) {
            return null;
        }
        SpawnData spawnData = readSpawnData(logic);
        if (spawnData == null) {
            return null;
        }
        String id = spawnData.getEntityToSpawn().getString("id");
        return ResourceLocation.tryParse(id);
    }

    private static SpawnData readSpawnData(BaseSpawner logic) {
        if (logic == null) {
            return null;
        }
        try {
            CompoundTag tag = logic.save(new CompoundTag());
            SpawnData spawnData = parseSpawnDataTag(tag);
            if (spawnData != null) {
                return spawnData;
            }
            return parseFirstSpawnPotential(tag);
        } catch (Exception ignored) {
            return null;
        }
    }

    private static SpawnData parseSpawnDataTag(CompoundTag tag) {
        if (tag == null || !tag.contains(BaseSpawner.SPAWN_DATA_TAG, 10)) {
            return null;
        }
        return SpawnData.CODEC
                .parse(NbtOps.INSTANCE, tag.getCompound(BaseSpawner.SPAWN_DATA_TAG))
                .resultOrPartial(msg -> {})
                .orElse(null);
    }

    private static SpawnData parseFirstSpawnPotential(CompoundTag tag) {
        if (tag == null || !tag.contains("SpawnPotentials", 9)) {
            return null;
        }
        ListTag list = tag.getList("SpawnPotentials", 10);
        SimpleWeightedRandomList<SpawnData> potentials =
                SpawnData.LIST_CODEC
                        .parse(NbtOps.INSTANCE, list)
                        .resultOrPartial(msg -> {})
                        .orElse(SimpleWeightedRandomList.empty());
        if (potentials.isEmpty()) {
            return null;
        }
        return potentials.getRandom(RandomSource.create()).map(WeightedEntry.Wrapper::getData).orElse(null);
    }

    public static ResourceLocation getMobSpawnerEntityIdFromBlockEntity(SpawnerBlockEntity spawner) {
        if (spawner == null) {
            return null;
        }
        try {
            return getMobSpawnerEntityId(spawner.getSpawner());
        } catch (Exception ignored) {
            return null;
        }
    }

    /** 1.12 {@code MobSpawnerLogic#setEntityId(ResourceLocation)} equivalent. */
    public static void setMobSpawnerEntityId(BaseSpawner logic, ResourceLocation id) {
        if (logic == null || id == null) {
            return;
        }
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(id);
        if (type == null) {
            return;
        }
        logic.setEntityId(type, null, RandomSource.create(), BlockPos.ZERO);
    }

    private SpawnerFixHelper() {}
}
