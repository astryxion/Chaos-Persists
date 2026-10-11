package com.astryxion.chaospersists.compat.creeperoverhaul;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Creeper Overhaul registers biome variants as their own entity types ({@code jungle_creeper},
 * {@code desert_creeper}, ocean pufferfish, etc.). The launcher otherwise always fires vanilla.
 */
public final class CreeperOverhaulLauncherCompat {

    public static final String CREEPER_OVERHAUL_MODID = "creeperoverhaul";

    private static boolean checked;
    private static boolean present;
    private static List<EntityType<?>> overhaulTypes;

    private CreeperOverhaulLauncherCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(CREEPER_OVERHAUL_MODID);
            checked = true;
        }
        return present;
    }

    @Nullable
    public static Creeper createLaunchedCreeper(Level level, RandomSource random) {
        EntityType<?> type = pickType(random);
        if (type == null) {
            return EntityType.CREEPER.create(level);
        }
        if (type.create(level) instanceof Creeper creeper) {
            return creeper;
        }
        return EntityType.CREEPER.create(level);
    }

    @Nullable
    private static EntityType<?> pickType(RandomSource random) {
        if (!isPresent()) {
            return EntityType.CREEPER;
        }
        List<EntityType<?>> types = overhaulCreeperTypes();
        if (types.isEmpty()) {
            return EntityType.CREEPER;
        }
        return types.get(random.nextInt(types.size()));
    }

    private static List<EntityType<?>> overhaulCreeperTypes() {
        if (overhaulTypes != null) {
            return overhaulTypes;
        }
        List<EntityType<?>> types = new ArrayList<>();
        for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES) {
            ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(type);
            if (id != null
                    && CREEPER_OVERHAUL_MODID.equals(id.getNamespace())
                    && id.getPath().contains("creeper")) {
                types.add(type);
            }
        }
        overhaulTypes = types;
        return types;
    }
}
