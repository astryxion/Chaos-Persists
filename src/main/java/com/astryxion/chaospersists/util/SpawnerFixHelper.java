package com.astryxion.chaospersists.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class SpawnerFixHelper {
    private static final ResourceLocation FALLBACK = new ResourceLocation("chaospersists", "ant");
    private static final Map<String, String> LEGACY_PATH_ALIASES = new HashMap<String, String>();

    static {
        LEGACY_PATH_ALIASES.put("t._rex", "trex");
        LEGACY_PATH_ALIASES.put("t.rex", "trex");
        LEGACY_PATH_ALIASES.put("t_rex", "trex");
        LEGACY_PATH_ALIASES.put("pitchblack", "nightmare");
        LEGACY_PATH_ALIASES.put("pitch_black", "nightmare");
        LEGACY_PATH_ALIASES.put("wtf", "gamma_metroid");
        LEGACY_PATH_ALIASES.put("cockateil", "bird");
        LEGACY_PATH_ALIASES.put("spyro", "baby_dragon");
        LEGACY_PATH_ALIASES.put("goldfish", "gold_fish");
        LEGACY_PATH_ALIASES.put("gold_fish", "gold_fish");
    }

    public static boolean isChaosEntityFirstTick(Entity entity) {
        Package pkg = entity.getClass().getPackage();
        return pkg != null
                && pkg.getName().startsWith("com.astryxion.chaospersists.entity")
                && entity.tickCount == 0;
    }

    public static void setSpawnerEntityId(Object spawnerLogic, ResourceLocation input) {
        if (spawnerLogic == null || input == null) {
            return;
        }
        ResourceLocation normalized = normalizeSpawnerEntityId(input);
        if (normalized == null) {
            return;
        }
        EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(normalized);
        if (entityType != null) {
            try {
                if (mobSpawnerSetEntityIdType == null) {
                    mobSpawnerSetEntityIdType = spawnerLogic.getClass().getMethod("setEntityId", EntityType.class);
                }
                mobSpawnerSetEntityIdType.invoke(spawnerLogic, entityType);
                return;
            } catch (Exception ignored) {
            }
        }
        try {
            if (mobSpawnerSetEntityId == null) {
                mobSpawnerSetEntityId = ObfuscationReflectionHelper.findMethod(
                        spawnerLogic.getClass(),
                        "setEntityId",
                        ResourceLocation.class);
            }
            mobSpawnerSetEntityId.invoke(spawnerLogic, normalized);
        } catch (Exception ignored) {
        }
    }

    public static ResourceLocation normalizeSpawnerEntityId(ResourceLocation input) {
        if (input != null && isRegistered(input)) {
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

        return isRegistered(FALLBACK) ? FALLBACK : new ResourceLocation("minecraft", "pig");
    }

    public static ResourceLocation normalizeEntityLookupId(ResourceLocation input) {
        ResourceLocation direct = normalizeSpawnerEntityId(input);
        if (direct != null && isRegistered(direct)) {
            return direct;
        }

        String wanted = compactSignature(input == null ? "" : normalizePath(input.getPath()));
        if (!wanted.isEmpty()) {
            Set<ResourceLocation> ids = ForgeRegistries.ENTITIES.getKeys();
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
        return isRegistered(FALLBACK) ? FALLBACK : new ResourceLocation("minecraft", "pig");
    }

    private static ResourceLocation tryResolve(String namespace, String path) {
        if (path == null || path.isEmpty()) {
            return null;
        }
        ResourceLocation id = new ResourceLocation(namespace, path);
        return isRegistered(id) ? id : null;
    }

    private static boolean isRegistered(ResourceLocation id) {
        return ForgeRegistries.ENTITIES.containsKey(id);
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

    private static Method mobSpawnerGetEntityId;
    private static Method mobSpawnerSetEntityId;
    private static Method mobSpawnerSetEntityIdType;

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

    public static ResourceLocation getMobSpawnerEntityId(Object logic) {
        if (logic == null) {
            return null;
        }
        try {
            if (mobSpawnerGetEntityId == null) {
                mobSpawnerGetEntityId = ObfuscationReflectionHelper.findMethod(
                        logic.getClass(),
                        "getEntityId",
                        ResourceLocation.class);
            }
            return (ResourceLocation) mobSpawnerGetEntityId.invoke(logic);
        } catch (Exception e) {
            return null;
        }
    }

    private SpawnerFixHelper() {
    }
}
