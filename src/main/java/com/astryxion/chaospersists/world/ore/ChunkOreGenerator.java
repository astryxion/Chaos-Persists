/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ChunkOreGenerator
 *  com.astryxion.chaospersists.OreGenericEgg
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.OreStats
 *  net.minecraft.block.Block
 *  net.minecraft.block.Blocks
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 */
package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ChunkOreGenerator {
    public void generateOresInChunk(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
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
                            b = ChaosPersists.MySpiderDriverSpawnBlock;
                            break;
                        }
                        case 6: {
                            b = ChaosPersists.MyCrabSpawnBlock;
                            break;
                        }
                    }
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, b, ChaosPersists.SpawnOres_stats.clumpsize);
                    continue;
                }
                j = random.nextInt(98);
                b = Blocks.AIR;
                switch (j) {
                    case 0: {
                        b = ChaosPersists.MySpiderSpawnBlock;
                        break;
                    }
                    case 1: {
                        b = ChaosPersists.MyBatSpawnBlock;
                        break;
                    }
                    case 2: {
                        b = ChaosPersists.MyCowSpawnBlock;
                        break;
                    }
                    case 3: {
                        b = ChaosPersists.MyPigSpawnBlock;
                        break;
                    }
                    case 4: {
                        b = ChaosPersists.MySquidSpawnBlock;
                        break;
                    }
                    case 5: {
                        b = ChaosPersists.MyChickenSpawnBlock;
                        break;
                    }
                    case 6: {
                        b = ChaosPersists.MyCreeperSpawnBlock;
                        break;
                    }
                    case 7: {
                        b = ChaosPersists.MySkeletonSpawnBlock;
                        break;
                    }
                    case 8: {
                        b = ChaosPersists.MyZombieSpawnBlock;
                        break;
                    }
                    case 9: {
                        b = ChaosPersists.MySlimeSpawnBlock;
                        break;
                    }
                    case 10: {
                        b = ChaosPersists.MyGhastSpawnBlock;
                        break;
                    }
                    case 11: {
                        b = ChaosPersists.MyZombiePigmanSpawnBlock;
                        break;
                    }
                    case 12: {
                        b = ChaosPersists.MyEndermanSpawnBlock;
                        break;
                    }
                    case 13: {
                        b = ChaosPersists.MyCaveSpiderSpawnBlock;
                        break;
                    }
                    case 14: {
                        b = ChaosPersists.MySilverfishSpawnBlock;
                        break;
                    }
                    case 15: {
                        b = ChaosPersists.MyMagmaCubeSpawnBlock;
                        break;
                    }
                    case 16: {
                        b = ChaosPersists.MyWitchSpawnBlock;
                        break;
                    }
                    case 17: {
                        b = ChaosPersists.MySheepSpawnBlock;
                        break;
                    }
                    case 18: {
                        b = ChaosPersists.MyWolfSpawnBlock;
                        break;
                    }
                    case 19: {
                        b = ChaosPersists.MyMooshroomSpawnBlock;
                        break;
                    }
                    case 20: {
                        b = ChaosPersists.MyOcelotSpawnBlock;
                        break;
                    }
                    case 21: {
                        b = ChaosPersists.MyBlazeSpawnBlock;
                        break;
                    }
                    case 22: {
                        b = ChaosPersists.MyWitherSkeletonSpawnBlock;
                        break;
                    }
                    case 23: {
                        b = ChaosPersists.MyEnderDragonSpawnBlock;
                        break;
                    }
                    case 24: {
                        b = ChaosPersists.MySnowGolemSpawnBlock;
                        break;
                    }
                    case 25: {
                        b = ChaosPersists.MyIronGolemSpawnBlock;
                        break;
                    }
                    case 26: {
                        b = ChaosPersists.MyWitherBossSpawnBlock;
                        break;
                    }
                    case 27: {
                        b = ChaosPersists.MyGirlfriendSpawnBlock;
                        break;
                    }
                    case 28: {
                        b = ChaosPersists.MyRedCowSpawnBlock;
                        break;
                    }
                    case 29: {
                        b = ChaosPersists.MyGoldCowSpawnBlock;
                        break;
                    }
                    case 30: {
                        b = ChaosPersists.MyEnchantedCowSpawnBlock;
                        break;
                    }
                    case 31: {
                        b = ChaosPersists.MyMOTHRASpawnBlock;
                        break;
                    }
                    case 32: {
                        b = ChaosPersists.MyAloSpawnBlock;
                        break;
                    }
                    case 33: {
                        b = ChaosPersists.MyCryoSpawnBlock;
                        break;
                    }
                    case 34: {
                        b = ChaosPersists.MyCamaSpawnBlock;
                        break;
                    }
                    case 35: {
                        b = ChaosPersists.MyVeloSpawnBlock;
                        break;
                    }
                    case 36: {
                        b = ChaosPersists.MyHydroSpawnBlock;
                        break;
                    }
                    case 37: {
                        b = ChaosPersists.MyBasilSpawnBlock;
                        break;
                    }
                    case 38: {
                        b = ChaosPersists.MyDragonflySpawnBlock;
                        break;
                    }
                    case 39: {
                        b = ChaosPersists.MyEmperorScorpionSpawnBlock;
                        break;
                    }
                    case 40: {
                        b = ChaosPersists.MyScorpionSpawnBlock;
                        break;
                    }
                    case 41: {
                        b = ChaosPersists.MyCaveFisherSpawnBlock;
                        break;
                    }
                    case 42: {
                        b = ChaosPersists.MySpyroSpawnBlock;
                        break;
                    }
                    case 43: {
                        b = ChaosPersists.MyBaryonyxSpawnBlock;
                        break;
                    }
                    case 44: {
                        b = ChaosPersists.MyGammaMetroidSpawnBlock;
                        break;
                    }
                    case 45: {
                        b = ChaosPersists.MyCockateilSpawnBlock;
                        break;
                    }
                    case 46: {
                        b = ChaosPersists.MyKyuubiSpawnBlock;
                        break;
                    }
                    case 47: {
                        b = ChaosPersists.MyAlienSpawnBlock;
                        break;
                    }
                    case 48: {
                        b = ChaosPersists.MyAttackSquidSpawnBlock;
                        break;
                    }
                    case 49: {
                        b = ChaosPersists.MyWaterDragonSpawnBlock;
                        break;
                    }
                    case 50: {
                        b = ChaosPersists.MyKrakenSpawnBlock;
                        break;
                    }
                    case 51: {
                        b = ChaosPersists.MyLizardSpawnBlock;
                        break;
                    }
                    case 52: {
                        b = ChaosPersists.MyCephadromeSpawnBlock;
                        break;
                    }
                    case 53: {
                        b = ChaosPersists.MyDragonSpawnBlock;
                        break;
                    }
                    case 54: {
                        b = ChaosPersists.MyBeeSpawnBlock;
                        break;
                    }
                    case 55: {
                        b = ChaosPersists.MyHorseSpawnBlock;
                        break;
                    }
                    case 56: {
                        b = ChaosPersists.MyTrooperBugSpawnBlock;
                        break;
                    }
                    case 57: {
                        b = ChaosPersists.MySpitBugSpawnBlock;
                        break;
                    }
                    case 58: {
                        b = ChaosPersists.MyStinkBugSpawnBlock;
                        break;
                    }
                    case 59: {
                        b = ChaosPersists.MyOstrichSpawnBlock;
                        break;
                    }
                    case 60: {
                        b = ChaosPersists.MyGazelleSpawnBlock;
                        break;
                    }
                    case 61: {
                        b = ChaosPersists.MyChipmunkSpawnBlock;
                        break;
                    }
                    case 62: {
                        b = ChaosPersists.MyCreepingHorrorSpawnBlock;
                        break;
                    }
                    case 63: {
                        b = ChaosPersists.MyTerribleTerrorSpawnBlock;
                        break;
                    }
                    case 64: {
                        b = ChaosPersists.MyCliffRacerSpawnBlock;
                        break;
                    }
                    case 65: {
                        b = ChaosPersists.MyTriffidSpawnBlock;
                        break;
                    }
                    case 66: {
                        b = ChaosPersists.MyPitchBlackSpawnBlock;
                        break;
                    }
                    case 67: {
                        b = ChaosPersists.MyLurkingTerrorSpawnBlock;
                        break;
                    }
                    case 68: {
                        b = ChaosPersists.MyGodzillaPartSpawnBlock;
                        break;
                    }
                    case 69: {
                        b = ChaosPersists.MyGodzillaSpawnBlock;
                        break;
                    }
                    case 70: {
                        b = ChaosPersists.MySmallWormSpawnBlock;
                        break;
                    }
                    case 71: {
                        b = ChaosPersists.MyMediumWormSpawnBlock;
                        break;
                    }
                    case 72: {
                        b = ChaosPersists.MyLargeWormSpawnBlock;
                        break;
                    }
                    case 73: {
                        b = ChaosPersists.MyCassowarySpawnBlock;
                        break;
                    }
                    case 74: {
                        b = ChaosPersists.MyCloudSharkSpawnBlock;
                        break;
                    }
                    case 75: {
                        b = ChaosPersists.MyGoldFishSpawnBlock;
                        break;
                    }
                    case 76: {
                        b = ChaosPersists.MyLeafMonsterSpawnBlock;
                        break;
                    }
                    case 77: {
                        b = ChaosPersists.MyTshirtSpawnBlock;
                        break;
                    }
                    case 78: {
                        b = ChaosPersists.MyEnderKnightSpawnBlock;
                        break;
                    }
                    case 79: {
                        b = ChaosPersists.MyEnderReaperSpawnBlock;
                        break;
                    }
                    case 80: {
                        b = ChaosPersists.MyBeaverSpawnBlock;
                        break;
                    }
                    case 81: {
                        b = ChaosPersists.MyTRexSpawnBlock;
                        break;
                    }
                    case 82: {
                        b = ChaosPersists.MyHerculesSpawnBlock;
                        break;
                    }
                    case 83: {
                        b = ChaosPersists.MyMantisSpawnBlock;
                        break;
                    }
                    case 84: {
                        b = ChaosPersists.MyStinkySpawnBlock;
                        break;
                    }
                    case 85: {
                        b = ChaosPersists.MyBoyfriendSpawnBlock;
                        break;
                    }
                    case 86: {
                        b = ChaosPersists.MyTheKingPartSpawnBlock;
                        break;
                    }
                    case 87: {
                        b = ChaosPersists.MyEasterBunnySpawnBlock;
                        break;
                    }
                    case 88: {
                        b = ChaosPersists.MyCaterKillerSpawnBlock;
                        break;
                    }
                    case 89: {
                        b = ChaosPersists.MyMolenoidSpawnBlock;
                        break;
                    }
                    case 90: {
                        b = ChaosPersists.MySeaMonsterSpawnBlock;
                        break;
                    }
                    case 91: {
                        b = ChaosPersists.MySeaViperSpawnBlock;
                        break;
                    }
                    case 92: {
                        b = ChaosPersists.MyLeonSpawnBlock;
                        break;
                    }
                    case 93: {
                        b = ChaosPersists.MyHammerheadSpawnBlock;
                        break;
                    }
                    case 94: {
                        b = ChaosPersists.MyRubberDuckySpawnBlock;
                        break;
                    }
                    case 95: {
                        b = ChaosPersists.MyVillagerSpawnBlock;
                        break;
                    }
                    case 96: {
                        b = ChaosPersists.MyCriminalSpawnBlock;
                        break;
                    }
                    case 97: {
                        b = ChaosPersists.MyTheQueenPartSpawnBlock;
                        break;
                    }
                }
                this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, b, ChaosPersists.SpawnOres_stats.clumpsize);
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
                if (randPosY > ChaosPersists.Uranium_stats.maxdepth || randPosY < ChaosPersists.Uranium_stats.mindepth) continue;
                this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.MyOreUraniumBlock, ChaosPersists.Uranium_stats.clumpsize);
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
                if (randPosY > ChaosPersists.Titanium_stats.maxdepth || randPosY < ChaosPersists.Titanium_stats.mindepth) continue;
                this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.MyOreTitaniumBlock, ChaosPersists.Titanium_stats.clumpsize);
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
                if (randPosY > ChaosPersists.Amethyst_stats.maxdepth || randPosY < ChaosPersists.Amethyst_stats.mindepth) continue;
                this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.MyOreAmethystBlock, ChaosPersists.Amethyst_stats.clumpsize);
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
                if (randPosY > ChaosPersists.Salt_stats.maxdepth || randPosY < ChaosPersists.Salt_stats.mindepth) continue;
                this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.MyOreSaltBlock, ChaosPersists.Salt_stats.clumpsize);
            }
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = random.nextInt(128);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            if (randPosY > 50 || randPosY < 5) continue;
            this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.RedAntTroll, 4);
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = random.nextInt(128);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            if (randPosY > 50 || randPosY < 5) continue;
            this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.TermiteTroll, 4);
        }
        if (ChaosPersists.LessOre == 0) {
            if (ChaosPersists.Diamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Diamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Diamond_stats.maxdepth || randPosY < ChaosPersists.Diamond_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.DIAMOND_ORE, ChaosPersists.Diamond_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkDiamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkDiamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkDiamond_stats.maxdepth || randPosY < ChaosPersists.BlkDiamond_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.DIAMOND_BLOCK, ChaosPersists.BlkDiamond_stats.clumpsize);
                }
            }
            if (ChaosPersists.Emerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Emerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Emerald_stats.maxdepth || randPosY < ChaosPersists.Emerald_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.EMERALD_ORE, ChaosPersists.Emerald_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkEmerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkEmerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkEmerald_stats.maxdepth || randPosY < ChaosPersists.BlkEmerald_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.EMERALD_BLOCK, ChaosPersists.BlkEmerald_stats.clumpsize);
                }
            }
            if (ChaosPersists.Gold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Gold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Gold_stats.maxdepth || randPosY < ChaosPersists.Gold_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.GOLD_ORE, ChaosPersists.Gold_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkGold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkGold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkGold_stats.maxdepth || randPosY < ChaosPersists.BlkGold_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, Blocks.GOLD_BLOCK, ChaosPersists.BlkGold_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkRuby_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkRuby_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkRuby_stats.maxdepth || randPosY < ChaosPersists.BlkRuby_stats.mindepth) continue;
                    this.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.MyBlockRubyBlock, ChaosPersists.BlkRuby_stats.clumpsize);
                }
            }
        }
    }

    public boolean generateOreBlock(World par1World, Random par2Random, int par3, int par4, int par5, Chunk chunk, Block newbid, int numberOfBlocks) {
        float f = par2Random.nextFloat() * 3.1415927f;
        double d0 = (float)(par3 + 8) + MathHelper.sin((float)f) * (float)numberOfBlocks / 8.0f;
        double d1 = (float)(par3 + 8) - MathHelper.sin((float)f) * (float)numberOfBlocks / 8.0f;
        double d2 = (float)(par5 + 8) + MathHelper.cos((float)f) * (float)numberOfBlocks / 8.0f;
        double d3 = (float)(par5 + 8) - MathHelper.cos((float)f) * (float)numberOfBlocks / 8.0f;
        double d4 = par4 + par2Random.nextInt(3) - 2;
        double d5 = par4 + par2Random.nextInt(3) - 2;
        for (int l = 0; l <= numberOfBlocks; ++l) {
            double d6 = d0 + (d1 - d0) * (double)l / (double)numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double)l / (double)numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double)l / (double)numberOfBlocks;
            double d9 = par2Random.nextDouble() * (double)numberOfBlocks / 16.0;
            double d10 = (double)(MathHelper.sin((float)((float)l * 3.1415927f / (float)numberOfBlocks)) + 1.0f) * d9 + 1.0;
            double d11 = (double)(MathHelper.sin((float)((float)l * 3.1415927f / (float)numberOfBlocks)) + 1.0f) * d9 + 1.0;
            int i1 = MathHelper.floor((double)(d6 - d10 / 2.0));
            int j1 = MathHelper.floor((double)(d7 - d11 / 2.0));
            int k1 = MathHelper.floor((double)(d8 - d10 / 2.0));
            int l1 = MathHelper.floor((double)(d6 + d10 / 2.0));
            int i2 = MathHelper.floor((double)(d7 + d11 / 2.0));
            int j2 = MathHelper.floor((double)(d8 + d10 / 2.0));
            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double)k2 + 0.5 - d6) / (d10 / 2.0);
                if (d12 * d12 >= 1.0) continue;
                for (int l2 = j1; l2 <= i2; ++l2) {
                    double d13 = ((double)l2 + 0.5 - d7) / (d11 / 2.0);
                    if (d12 * d12 + d13 * d13 >= 1.0) continue;
                    for (int i3 = k1; i3 <= j2; ++i3) {
                        double d14 = ((double)i3 + 0.5 - d8) / (d10 / 2.0);
                        Block bid = ChaosPersists.getBlockIDInChunk((Chunk)chunk, (int)k2, (int)l2, (int)i3);
                        if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0 || bid != Blocks.STONE) continue;
                        ChaosPersists.setBlockIDWithMetadataInChunk((Chunk)chunk, (int)k2, (int)l2, (int)i3, (Block)newbid, (int)0);
                    }
                }
            }
        }
        return true;
    }
}

