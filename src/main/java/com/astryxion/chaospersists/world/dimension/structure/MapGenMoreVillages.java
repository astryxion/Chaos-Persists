/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.world.dimension.chunkprovider.ChunkProviderChaos3;
import java.util.Random;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.RegistryKey;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.structure.VillageStructure;

public class MapGenMoreVillages extends VillageStructure {
    private final int field_82665_g = 9;
    private final int field_82666_h = 7;

    public MapGenMoreVillages() {
        super(VillageConfig.CODEC);
    }

    @Override
    public boolean isFeatureChunk(ChunkGenerator generator, BiomeProvider biomeProvider, long seed, SharedSeedRandom rand, int chunkX, int chunkZ, Biome biome, ChunkPos chunkPos, VillageConfig config) {
        return this.canSpawnStructureAtCoords(chunkX, chunkZ, biomeProvider, generator, seed);
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2, BiomeProvider biomeProvider, ChunkGenerator generator, long worldSeed) {
        int var3 = par1;
        int var4 = par2;
        if (par1 < 0) {
            par1 -= this.field_82665_g - 1;
        }
        if (par2 < 0) {
            par2 -= this.field_82665_g - 1;
        }
        int var5 = par1 / this.field_82665_g;
        int var6 = par2 / this.field_82665_g;
        Random var7 = new Random(worldSeed);
        var7.setSeed((long) var5 * 341873128712L + (long) var6 * 132897987541L + 10387312L);
        var5 *= this.field_82665_g;
        var6 *= this.field_82665_g;
        if (var3 == (var5 += var7.nextInt(this.field_82665_g - this.field_82666_h)) && var4 == (var6 += var7.nextInt(this.field_82665_g - this.field_82666_h))) {
            if (generator instanceof ChunkProviderChaos3) {
                return true;
            }
            Biome biome = biomeProvider.getNoiseBiome((var3 * 16 + 8) >> 2, 0, (var4 * 16 + 8) >> 2);
            return this.isBiomeKey(biome, Biomes.PLAINS) || this.isBiomeKey(biome, Biomes.DESERT) || this.isBiomeKey(biome, Biomes.SAVANNA) || this.isBiomeKey(biome, Biomes.TAIGA);
        }
        return false;
    }

    private boolean isBiomeKey(Biome biome, RegistryKey<Biome> key) {
        return key.location().equals(ForgeRegistries.BIOMES.getKey(biome));
    }
}
