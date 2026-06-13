package com.astryxion.chaospersists.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Datapack noise dimensions use vanilla chunk generation; biomes need a surface builder and
 * decoration features. {@link BiomeGenerationSettings#EMPTY} leaves exposed stone with no grass.
 */
public final class ChaosBiomeGeneration {

    private ChaosBiomeGeneration() {
    }

    /** No trees, ores, or surface builders — custom {@code ChunkProviderChaos*} builds terrain. */
    public static BiomeGenerationSettings customDimension() {
        return BiomeGenerationSettings.EMPTY;
    }

    public static BiomeGenerationSettings plainsLike() {
        BiomeGenerationSettings fromRegistry = fromVanillaBiome("plains");
        return fromRegistry != null ? fromRegistry : fallbackPlains();
    }

    public static BiomeGenerationSettings hillsLike() {
        BiomeGenerationSettings fromRegistry = fromVanillaBiome("mountains");
        if (fromRegistry == null) {
            fromRegistry = fromVanillaBiome("wooded_mountains");
        }
        return fromRegistry != null ? fromRegistry : plainsLike();
    }

    private static BiomeGenerationSettings fromVanillaBiome(String path) {
        Biome biome = ForgeRegistries.BIOMES.getValue(new ResourceLocation("minecraft", path));
        return biome != null ? biome.getGenerationSettings() : null;
    }

    private static BiomeGenerationSettings fallbackPlains() {
        ConfiguredSurfaceBuilder<?> surface = SurfaceBuilder.DEFAULT.configured(
                new SurfaceBuilderConfig(
                        Blocks.GRASS_BLOCK.defaultBlockState(),
                        Blocks.DIRT.defaultBlockState(),
                        Blocks.GRASS_BLOCK.defaultBlockState()));
        return new BiomeGenerationSettings.Builder().surfaceBuilder(surface).build();
    }
}
