package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.level.LevelEvent;

/**
 * OreSpawn 1.7.10 chunk providers call {@code SpawnerAnimals.performWorldGenSpawning} at the end of
 * {@code populate}, after terrain decoration. Our {@link com.astryxion.chaospersists.core.ChaosWorld}
 * decoration runs on {@code ChunkEvent.Load}, so vanilla chunk-gen spawning often runs too early.
 */
public final class DimensionSpawnPopulation {
    private DimensionSpawnPopulation() {}

    /** Dimensions that used {@code performWorldGenSpawning} in OreSpawn 1.7.10 (not Danger/Islands). */
    public static boolean usesOreSpawnChunkPopulate(ResourceKey<Level> dimension) {
        return dimension.equals(ChaosPersists.getUtopiaDimensionKey())
                || dimension.equals(ChaosPersists.getMiningDimensionKey())
                || dimension.equals(ChaosPersists.getVillageDimensionKey())
                || dimension.equals(ChaosPersists.getCrystalDimensionKey())
                || dimension.equals(ChaosPersists.getChaosDimensionKey());
    }

    /**
     * Call at the end of deferred chunk decoration ({@code ChaosWorld#runChunkWorldGen}).
     */
    public static void afterChunkDecoration(ServerLevel level, LevelChunk chunk) {
        if (level.dimension().equals(ChaosPersists.getMiningDimensionKey())) {
            Holder<Biome> miningBiome =
                    BiomeMiningDimension.getChunkGenSpawnBiome(level.registryAccess());
            NaturalSpawner.spawnMobsForChunkGeneration(
                    level, miningBiome, chunk.getPos(), level.getRandom());
            return;
        }
        // Overworld spawns are injected once via LegacyOverworldSpawnBiomeModifier; re-merging here
        // tripled spawn rates and caused mid-air chunk-gen spawning on floating structures.
        if (level.dimension().equals(Level.NETHER) || level.dimension().equals(Level.END)) {
            BlockPos pos = chunk.getPos().getWorldPosition();
            Holder<Biome> biome =
                    LegacyOverworldSpawnPopulation.mergeLegacySpawnsIntoBiome(
                            level, level.getBiome(pos), pos);
            NaturalSpawner.spawnMobsForChunkGeneration(
                    level, biome, chunk.getPos(), level.getRandom());
            return;
        }
        if (!usesOreSpawnChunkPopulate(level.dimension())) {
            return;
        }
        Holder<Biome> biome = level.getBiome(chunk.getPos().getWorldPosition());
        NaturalSpawner.spawnMobsForChunkGeneration(level, biome, chunk.getPos(), level.getRandom());
    }

    /**
     * OreSpawn utopia chunk provider returned {@code null} for monsters; village/mining use biome lists.
     */
    public static void onDimensionPotentialSpawns(LevelEvent.PotentialSpawns event, ServerLevel level) {
        ResourceKey<Level> dimension = level.dimension();
        if (dimension.equals(Level.OVERWORLD)
                && !com.astryxion.chaospersists.config.ChaosPackConfig.allowOverworldSpawns()) {
            for (MobSpawnSettings.SpawnerData data : new ArrayList<>(event.getSpawnerDataList())) {
                if (com.astryxion.chaospersists.config.ChaosPackConfig.isChaosEntityType(data.type)) {
                    event.removeSpawnerData(data);
                }
            }
            return;
        }
        if (dimension.equals(ChaosPersists.getUtopiaDimensionKey())) {
            if (event.getMobCategory() == MobCategory.MONSTER) {
                for (MobSpawnSettings.SpawnerData data : new ArrayList<>(event.getSpawnerDataList())) {
                    event.removeSpawnerData(data);
                }
            }
            return;
        }
        if (dimension.equals(ChaosPersists.getMiningDimensionKey())) {
            BiomeMiningDimension.applyPotentialSpawns(event, level.registryAccess());
            return;
        }
        // Overworld uses biome modifiers only; runtime PotentialSpawns injection duplicated entries.
        if (dimension.equals(Level.NETHER) || dimension.equals(Level.END)) {
            LegacyOverworldSpawnPopulation.applyPotentialSpawns(event, level);
        }
    }
}
