package com.astryxion.chaospersists.util;

import java.util.Locale;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Resolves legacy 1.12 entity names (PascalCase, spaces) to 1.16 registry ids and spawns entities.
 */
public final class EntitySpawnHelper {

    private EntitySpawnHelper() {
    }

    /** Converts names like {@code KingHead}, {@code The Prince}, or {@code chaospersists:purple_power}. */
    public static ResourceLocation resolveEntityId(String legacyName) {
        if (legacyName == null || legacyName.isEmpty()) {
            return new ResourceLocation("chaospersists", "unknown");
        }
        String trimmed = legacyName.trim();
        if (trimmed.contains(":")) {
            int sep = trimmed.indexOf(':');
            String namespace = trimmed.substring(0, sep).toLowerCase(Locale.ROOT);
            String path = normalizePath(trimmed.substring(sep + 1));
            return new ResourceLocation(namespace, path);
        }
        return new ResourceLocation("chaospersists", normalizePath(trimmed));
    }

    private static String normalizePath(String path) {
        String withUnderscores = path.replace(' ', '_');
        String snake = withUnderscores.replaceAll("([a-z])([A-Z])", "$1_$2");
        snake = snake.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2");
        return snake.toLowerCase(Locale.ROOT);
    }

    public static Entity spawn(World world, String legacyName, double x, double y, double z) {
        if (world == null) {
            return null;
        }
        ResourceLocation id = SpawnerFixHelper.normalizeEntityLookupId(resolveEntityId(legacyName));
        EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(id);
        if (entityType == null) {
            return null;
        }
        Entity entity = entityType.create(world);
        if (entity != null) {
            entity.moveTo(x, y, z, world.random.nextFloat() * 360.0F, 0.0F);
            world.addFreshEntity(entity);
        }
        return entity;
    }
}
