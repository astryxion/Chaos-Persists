/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ChunkOreGenerator
 *  com.astryxion.chaospersists.OreGenericEgg
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.OreStats
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 *  com.astryxion.chaospersists.legacy.minecraft.world.chunk.Chunk
 */
package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.LevelChunk;

public class ChunkOreGenerator {
    public void generateOresInChunk(Level world, RandomSource random, int chunkX, int chunkZ, LevelChunk chunk) {
        int i;
        int randPosY;
        int randPosX;
        int patchy;
        int randPosZ;
        if (ChaosPersists.SpawnOres_stats.rate > 0) {
            patchy = ChaosPersists.SpawnOres_stats.rate + random.nextInt(30);
            if (random.nextInt(20) == 0) {
                patchy += 30;
            }
            if (ChaosPersists.LessOre != 0) {
                patchy /= 3;
            }
            for (i = 0; i < patchy; ++i) {
                Block b;
                int j;
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY > ChaosPersists.SpawnOres_stats.maxdepth || randPosY < ChaosPersists.SpawnOres_stats.mindepth) continue;
                if (random.nextInt(104) < 7) {
                    j = random.nextInt(7);
                    b = Blocks.AIR;
                    switch (j) {
                        case 0: {
                            b = ChaosPersists.MyBrutalflySpawnBlock;
                            break;
                        }
                        case 1: {
                            b = ChaosPersists.MyNastysaurusSpawnBlock;
                            break;
                        }
                        case 2: {
                            b = ChaosPersists.MyPointysaurusSpawnBlock;
                            break;
                        }
                        case 3: {
                            b = ChaosPersists.MyCricketSpawnBlock;
                            break;
                        }
                        case 4: {
                            b = ChaosPersists.MyFrogSpawnBlock;
                            break;
                        }
                        case 5: {
                            b = ChaosPersists.MyAloSpawnBlock;
                            break;
                        }
                        case 6: {
                            b = ChaosPersists.MyCrabSpawnBlock;
                            break;
                        }
                    }
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, b, ChaosPersists.SpawnOres_stats.clumpsize);
                    continue;
                }
                Block[] spawnOrePool = ChaosPersists.getMainSpawnOreBlocks();
                b = spawnOrePool[random.nextInt(spawnOrePool.length)];
                if (b == null) {
                    b = Blocks.AIR;
                }
                this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, b, ChaosPersists.SpawnOres_stats.clumpsize);
            }
        }
        if (ChaosPersists.Uranium_stats.rate > 0) {
            patchy = ChaosPersists.Uranium_stats.rate + random.nextInt(9);
            if (ChaosPersists.LessOre != 0) {
                patchy /= 3;
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY <= 0
                        || randPosY > ChaosPersists.Uranium_stats.maxdepth
                        || randPosY < ChaosPersists.Uranium_stats.mindepth) continue;
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyOreUraniumBlock,
                        ChaosPersists.Uranium_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyDeepslateOreUraniumBlock,
                        ChaosPersists.Uranium_stats.clumpsize,
                        Blocks.DEEPSLATE);
            }
        }
        if (ChaosPersists.Titanium_stats.rate > 0) {
            patchy = ChaosPersists.Titanium_stats.rate + random.nextInt(9);
            if (ChaosPersists.LessOre != 0) {
                patchy /= 3;
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY <= 0
                        || randPosY > ChaosPersists.Titanium_stats.maxdepth
                        || randPosY < ChaosPersists.Titanium_stats.mindepth) continue;
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyOreTitaniumBlock,
                        ChaosPersists.Titanium_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyDeepslateOreTitaniumBlock,
                        ChaosPersists.Titanium_stats.clumpsize,
                        Blocks.DEEPSLATE);
            }
        }
        if (ChaosPersists.Amethyst_stats.rate > 0) {
            patchy = ChaosPersists.Amethyst_stats.rate + random.nextInt(12);
            if (ChaosPersists.LessOre != 0) {
                patchy /= 3;
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY <= 0
                        || randPosY > ChaosPersists.Amethyst_stats.maxdepth
                        || randPosY < ChaosPersists.Amethyst_stats.mindepth) continue;
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyOreAmethystBlock,
                        ChaosPersists.Amethyst_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyDeepslateOreAmethystBlock,
                        ChaosPersists.Amethyst_stats.clumpsize,
                        Blocks.DEEPSLATE);
            }
        }
        if (ChaosPersists.Salt_stats.rate > 0) {
            patchy = ChaosPersists.Salt_stats.rate + random.nextInt(9);
            if (ChaosPersists.LessOre != 0) {
                patchy /= 3;
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY <= 0
                        || randPosY > ChaosPersists.Salt_stats.maxdepth
                        || randPosY < ChaosPersists.Salt_stats.mindepth) continue;
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyOreSaltBlock,
                        ChaosPersists.Salt_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                this.generateBlockOre(
                        world,
                        random,
                        randPosX,
                        randPosY,
                        randPosZ,
                        chunk,
                        ChaosPersists.MyDeepslateOreSaltBlock,
                        ChaosPersists.Salt_stats.clumpsize,
                        Blocks.DEEPSLATE);
            }
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = 1 + random.nextInt(50); // stone trolls: Y 1..50
            randPosZ = 3 + chunkZ + random.nextInt(10);
            this.generateBlockOre(
                    world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.RedAntTroll, 4, Blocks.STONE);
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = -63 + random.nextInt(64); // deepslate trolls: Y -63..0
            randPosZ = 3 + chunkZ + random.nextInt(10);
            this.generateBlockOre(
                    world,
                    random,
                    randPosX,
                    randPosY,
                    randPosZ,
                    chunk,
                    ChaosPersists.DeepslateRedAntTroll,
                    4,
                    Blocks.DEEPSLATE);
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = 1 + random.nextInt(50);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            this.generateBlockOre(
                    world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.TermiteTroll, 4, Blocks.STONE);
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = -63 + random.nextInt(64);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            this.generateBlockOre(
                    world,
                    random,
                    randPosX,
                    randPosY,
                    randPosZ,
                    chunk,
                    ChaosPersists.DeepslateTermiteTroll,
                    4,
                    Blocks.DEEPSLATE);
        }
        if (ChaosPersists.LessOre == 0) {
            if (ChaosPersists.Diamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Diamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Diamond_stats.maxdepth || randPosY < ChaosPersists.Diamond_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.DIAMOND_ORE, ChaosPersists.Diamond_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkDiamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkDiamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkDiamond_stats.maxdepth || randPosY < ChaosPersists.BlkDiamond_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.DIAMOND_BLOCK, ChaosPersists.BlkDiamond_stats.clumpsize);
                }
            }
            if (ChaosPersists.Emerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Emerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Emerald_stats.maxdepth || randPosY < ChaosPersists.Emerald_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.EMERALD_ORE, ChaosPersists.Emerald_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkEmerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkEmerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkEmerald_stats.maxdepth || randPosY < ChaosPersists.BlkEmerald_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.EMERALD_BLOCK, ChaosPersists.BlkEmerald_stats.clumpsize);
                }
            }
            if (ChaosPersists.Gold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Gold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Gold_stats.maxdepth || randPosY < ChaosPersists.Gold_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.GOLD_ORE, ChaosPersists.Gold_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkGold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkGold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkGold_stats.maxdepth || randPosY < ChaosPersists.BlkGold_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.GOLD_BLOCK, ChaosPersists.BlkGold_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkRuby_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkRuby_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkRuby_stats.maxdepth || randPosY < ChaosPersists.BlkRuby_stats.mindepth) continue;
                    this.generateBlockOre(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.MyBlockRubyBlock, ChaosPersists.BlkRuby_stats.clumpsize);
                }
            }
        }
    }

    public boolean generateBlockOre(
            Level par1World, RandomSource par2Random, int par3, int par4, int par5, LevelChunk chunk, Block newbid, int numberOfBlocks) {
        return this.generateBlockOre(
                par1World, par2Random, par3, par4, par5, chunk, newbid, numberOfBlocks, Blocks.STONE);
    }

    public boolean generateBlockOre(
            Level par1World,
            RandomSource par2Random,
            int par3,
            int par4,
            int par5,
            LevelChunk chunk,
            Block newbid,
            int numberOfBlocks,
            Block oldbid) {
        float f = par2Random.nextFloat() * 3.1415927f;
        double d0 = (float)(par3 + 8) + Mth.sin(f) * (float)numberOfBlocks / 8.0f;
        double d1 = (float)(par3 + 8) - Mth.sin(f) * (float)numberOfBlocks / 8.0f;
        double d2 = (float)(par5 + 8) + Mth.cos(f) * (float)numberOfBlocks / 8.0f;
        double d3 = (float)(par5 + 8) - Mth.cos(f) * (float)numberOfBlocks / 8.0f;
        double d4 = par4 + par2Random.nextInt(3) - 2;
        double d5 = par4 + par2Random.nextInt(3) - 2;
        for (int l = 0; l <= numberOfBlocks; ++l) {
            double d6 = d0 + (d1 - d0) * (double)l / (double)numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double)l / (double)numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double)l / (double)numberOfBlocks;
            double d9 = par2Random.nextDouble() * (double)numberOfBlocks / 16.0;
            double d10 = (double)(Mth.sin((float)l * 3.1415927f / (float)numberOfBlocks) + 1.0f) * d9 + 1.0;
            double d11 = (double)(Mth.sin((float)l * 3.1415927f / (float)numberOfBlocks) + 1.0f) * d9 + 1.0;
            int i1 = Mth.floor(d6 - d10 / 2.0);
            int j1 = Mth.floor(d7 - d11 / 2.0);
            int k1 = Mth.floor(d8 - d10 / 2.0);
            int l1 = Mth.floor(d6 + d10 / 2.0);
            int i2 = Mth.floor(d7 + d11 / 2.0);
            int j2 = Mth.floor(d8 + d10 / 2.0);
            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double)k2 + 0.5 - d6) / (d10 / 2.0);
                if (d12 * d12 >= 1.0) continue;
                for (int l2 = j1; l2 <= i2; ++l2) {
                    double d13 = ((double)l2 + 0.5 - d7) / (d11 / 2.0);
                    if (d12 * d12 + d13 * d13 >= 1.0) continue;
                    for (int i3 = k1; i3 <= j2; ++i3) {
                        double d14 = ((double)i3 + 0.5 - d8) / (d10 / 2.0);
                        Block bid = ChaosPersists.getBlockIDInChunk(chunk, k2, l2, i3);
                        if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0 || bid != oldbid) continue;
                        ChaosPersists.setBlockIDWithMetadataInChunk(chunk, k2, l2, i3, newbid, 0);
                    }
                }
            }
        }
        return true;
    }
}

