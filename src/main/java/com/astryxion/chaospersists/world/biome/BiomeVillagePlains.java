package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;

/**
 * Village Mania dimension biome. OreSpawn 1.7.10 ({@code DimensionOreSpawn3}) creates one
 * {@code BiomeGenUtopianPlains(BiomeVillageID)} (flag-gated Utopia base), then calls {@link BiomeGenUtopianPlains#setVillageCreatures()}
 * for robots and extra weighted entries — not a second full copy of the Utopia table pasted twice in one constructor.
 */
public class BiomeVillagePlains {
    public Biome build() {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        BiomeGenUtopianPlains.addUtopiaPlainsSpawnEntries(spawns);
        BiomeGenUtopianPlains.addVillageSpawnEntries(spawns);
        return BiomeGenUtopianPlains.buildBiome(353825, 0.7f, 0.5f, 0.125f, 0.05f, spawns);
    }
}
