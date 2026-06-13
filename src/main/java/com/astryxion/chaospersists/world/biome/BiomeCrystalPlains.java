package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;

public class BiomeCrystalPlains {
    public Biome build() {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        BiomeGenUtopianPlains.addCrystalSpawnEntries(spawns);
        return BiomeGenUtopianPlains.buildBiome(353825, 0.7f, 0.5f, 0.1f, 0.5f, spawns, ChaosBiomeGeneration.customDimension());
    }
}
