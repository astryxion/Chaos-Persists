package com.astryxion.chaospersists.world.biome;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * Mining dimension uses vanilla {@code minecraft:windswept_hills}. Mod spawns are defined in datapack JSON
 * ({@code forge/biome_modifier/*_mining.json}, type {@code chaospersists:add_mining_dimension_spawns}) and
 * applied only inside the mining dimension via {@link #applyPotentialSpawns} so overworld windswept hills
 * are never modified.
 */
public final class BiomeMiningDimension {
    private BiomeMiningDimension() {}

    private static Holder<Biome> chunkGenSpawnBiome;

    /** Biome holder used only for mining-dimension chunk-gen mob spawning (mod mob table). */
    public static Holder<Biome> getChunkGenSpawnBiome(RegistryAccess registryAccess) {
        if (chunkGenSpawnBiome == null) {
            Registry<Biome> biomes = registryAccess.registryOrThrow(Registries.BIOME);
            Biome base = biomes.get(Biomes.WINDSWEPT_HILLS);
            if (base == null) {
                base = biomes.get(Biomes.PLAINS);
            }
            chunkGenSpawnBiome =
                    Holder.direct(
                            new Biome.BiomeBuilder()
                                    .hasPrecipitation(base.hasPrecipitation())
                                    .temperature(base.getBaseTemperature())
                                    .downfall(base.getModifiedClimateSettings().downfall())
                                    .specialEffects(base.getSpecialEffects())
                                    .mobSpawnSettings(buildMiningSpawns())
                                    .generationSettings(base.getGenerationSettings())
                                    .build());
        }
        return chunkGenSpawnBiome;
    }

    public static MobSpawnSettings buildMiningSpawns() {
        MobSpawnSettings.Builder spawns = new MobSpawnSettings.Builder();
        addMiningSpawns(spawns);
        return spawns.build();
    }

    /** Reference mirror of mining modifier JSON weights (config gates preserved). */
    public static void addMiningSpawns(MobSpawnSettings.Builder spawns) {
        if (ChaosPersists.AlosaurusEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_ALOSAURUS.get(), 20, 2, 4));
        }
        if (ChaosPersists.TRexEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_TREX.get(), 15, 2, 4));
        }
        if (ChaosPersists.PointysaurusEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_POINTYSAURUS.get(), 22, 4, 8));
        }
        if (ChaosPersists.CryolophosaurusEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_CRYOLOPHOSAURUS.get(), 40, 4, 8));
        }
        if (ChaosPersists.AlienEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_ALIEN.get(), 35, 2, 3));
        }
        if (ChaosPersists.CaveFisherEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_CAVE_FISHER.get(), 35, 4, 8));
        }
        if (ChaosPersists.BaryonyxEnable != 0) {
            spawns.addSpawn(
                    MobCategory.CREATURE,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_BARYONYX.get(), 6, 4, 8));
        }
        if (ChaosPersists.CamarasaurusEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_CAMARASAURUS.get(), 4, 2, 6));
        }
        if (ChaosPersists.CockateilEnable != 0) {
            spawns.addSpawn(
                    MobCategory.CREATURE,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_BIRD.get(), 10, 1, 2));
        }
        if (ChaosPersists.SpyroEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_BABY_DRAGON.get(), 5, 1, 2));
        }
        if (ChaosPersists.GammaMetroidEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_GAMMA_METROID.get(), 35, 4, 7));
        }
        if (ChaosPersists.NastysaurusEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_NASTYSAURUS.get(), 15, 2, 4));
        }
        if (ChaosPersists.VelocityRaptorEnable != 0) {
            spawns.addSpawn(
                    MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_VELOCITY_RAPTOR.get(), 4, 2, 6));
        }
        if (ChaosPersists.DragonflyEnable != 0) {
            spawns.addSpawn(
                    MobCategory.AMBIENT,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_DRAGONFLY.get(), 2, 1, 3));
        }
        if (ChaosPersists.ButterflyEnable != 0) {
            spawns.addSpawn(
                    MobCategory.AMBIENT,
                    new MobSpawnSettings.SpawnerData(ChaosPersists.ENTITY_TYPE_BUTTERFLY.get(), 5, 1, 2));
        }
    }

    /**
     * OreSpawn {@code ChunkProviderOreSpawn2}: custom monster/ambient lists merge with extreme hills;
     * creature/water lists stay on the biome. Replace monsters with the mining table only; add others.
     */
    public static void applyPotentialSpawns(LevelEvent.PotentialSpawns event, RegistryAccess registryAccess) {
        MobCategory category = event.getMobCategory();
        List<MobSpawnSettings.SpawnerData> miningSpawns = collectDatapackMiningSpawns(registryAccess, category);
        if (miningSpawns.isEmpty()) {
            return;
        }
        if (category == MobCategory.MONSTER) {
            List<MobSpawnSettings.SpawnerData> existing = new ArrayList<>(event.getSpawnerDataList());
            for (MobSpawnSettings.SpawnerData data : existing) {
                event.removeSpawnerData(data);
            }
        }
        for (MobSpawnSettings.SpawnerData data : miningSpawns) {
            event.addSpawnerData(data);
        }
    }

    public static List<MobSpawnSettings.SpawnerData> collectDatapackMiningSpawns(
            RegistryAccess registryAccess, MobCategory category) {
        Registry<BiomeModifier> registry =
                registryAccess.registryOrThrow(NeoForgeRegistries.Keys.BIOME_MODIFIERS);
        List<MobSpawnSettings.SpawnerData> result = new ArrayList<>();
        for (BiomeModifier modifier : registry) {
            if (modifier instanceof MiningDimensionAddSpawnsBiomeModifier miningModifier) {
                for (MobSpawnSettings.SpawnerData spawner : miningModifier.spawners()) {
                    if (spawner.type.getCategory() == category) {
                        result.add(spawner);
                    }
                }
            }
        }
        return result;
    }

    public static MapCodec<MiningDimensionAddSpawnsBiomeModifier> makeCodec() {
        return RecordCodecBuilder.mapCodec(
                builder -> builder.group(com.mojang.serialization.Codec.either(
                                        MobSpawnSettings.SpawnerData.CODEC.listOf(),
                                        MobSpawnSettings.SpawnerData.CODEC)
                                .xmap(
                                        either -> either.map(Function.identity(), List::of),
                                        list -> list.size() == 1 ? Either.right(list.get(0)) : Either.left(list))
                                .fieldOf("spawners")
                                .forGetter(MiningDimensionAddSpawnsBiomeModifier::spawners))
                        .apply(builder, MiningDimensionAddSpawnsBiomeModifier::new));
    }

    /**
     * Datapack biome modifier: registers spawn data from JSON without altering {@code windswept_hills} globally.
     */
    public record MiningDimensionAddSpawnsBiomeModifier(List<MobSpawnSettings.SpawnerData> spawners)
            implements BiomeModifier {
        @Override
        public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {}

        @Override
        public MapCodec<? extends BiomeModifier> codec() {
            return ChaosPersists.ADD_MINING_DIMENSION_SPAWNS.get();
        }
    }
}
