package com.astryxion.chaospersists.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Mining dimension biome ({@code BiomeHills} / extreme hills in 1.12.2). {@link Biome} is final in 1.16.5;
 * this class builds the same spawn tables via {@link #build()}.
 */
public class BiomeMiningDimension {

    private static EntityType<?> entityType(String path) {
        return ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", path));
    }

    private static BiomeAmbience defaultAmbience() {
        return new BiomeAmbience.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(7907327)
                .build();
    }

    public Biome build() {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(entityType("alosaurus"), 100, 1, 1));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(entityType("trex"), 100, 1, 1));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(entityType("pointysaurus"), 100, 1, 1));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(entityType("cryolophosaurus"), 100, 1, 1));
        spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(entityType("alien"), 100, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("baryonyx"), 200, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("camarasaurus"), 250, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("bird"), 255, 1, 2));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("butterfly"), 100, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("baby_dragon"), 250, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("gamma_metroid"), 200, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("nastysaurus"), 200, 1, 1));
        spawns.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(entityType("velocity_raptor"), 200, 1, 1));
        return new Biome.Builder().precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.EXTREME_HILLS)
                .depth(1.0F).scale(0.5F).temperature(0.2F).downfall(0.3F).specialEffects(defaultAmbience())
                .mobSpawnSettings(spawns.build()).generationSettings(ChaosBiomeGeneration.hillsLike()).build();
    }
}
