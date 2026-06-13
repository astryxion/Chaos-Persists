package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;

public class BiomeChaosPlains {
    public Biome build() {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        BiomeGenUtopianPlains.addChaosSpawnEntries(spawns);
        return BiomeGenUtopianPlains.buildBiome(353825, 0.7f, 0.5f, 0.125f, 0.05f, spawns, ChaosBiomeGeneration.customDimension());
    }
}
