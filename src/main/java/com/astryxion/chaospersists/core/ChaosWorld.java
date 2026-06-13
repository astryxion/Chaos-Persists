/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.IWorldGenerator
 *  com.astryxion.chaospersists.BasiliskMaze
 *  com.astryxion.chaospersists.GenericDungeon
 *  com.astryxion.chaospersists.ItemAppleSeed
 *  com.astryxion.chaospersists.ItemMagicApple
 *  com.astryxion.chaospersists.OreGenericEgg
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.ChaosWorld
 *  com.astryxion.chaospersists.OreStats
 *  com.astryxion.chaospersists.RubyBirdDungeon
 *  com.astryxion.chaospersists.Trees
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.ChestTileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenMinable
 */
package com.astryxion.chaospersists.core;

import com.astryxion.chaospersists.world.dimension.structure.BasiliskMaze;
import com.astryxion.chaospersists.world.dimension.structure.GenericDungeon;
import com.astryxion.chaospersists.item.ItemAppleSeed;
import com.astryxion.chaospersists.item.ItemMagicApple;
import com.astryxion.chaospersists.world.ore.OreGenericEgg;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.OreStats;
import com.astryxion.chaospersists.world.dimension.structure.RubyBirdDungeon;
import com.astryxion.chaospersists.util.Trees;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.biome.Biomes;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.block.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraft.util.math.MathHelper;
import java.util.function.Predicate;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.logging.log4j.LogManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChaosWorld {
    public static int recently_placed = 50;
    private static final boolean DEBUG_NATURAL_DUPLICATOR_SPAWNS = true;
    /** 1.12.2 IWorldGenerator ran during chunk populate, not inside ChunkEvent.Load (avoids getChunk/setBlock deadlocks). */
    private static final Map<RegistryKey<World>, Set<Long>> decoratedChunkKeysByDimension = new ConcurrentHashMap<RegistryKey<World>, Set<Long>>();
    private static final Queue<PendingChunkDecoration> pendingChunkDecorations = new ConcurrentLinkedQueue<PendingChunkDecoration>();
    private static final Queue<PendingChunkDecoration> pendingSpawnerNormalizations = new ConcurrentLinkedQueue<PendingChunkDecoration>();

    public static void queueNormalizeSpawnersInChunk(ServerWorld world, ChunkPos pos) {
        pendingSpawnerNormalizations.add(new PendingChunkDecoration(world, pos));
    }

    private static final class PendingChunkDecoration {
        final ServerWorld world;
        final ChunkPos pos;
        int attempts;

        PendingChunkDecoration(ServerWorld world, ChunkPos pos) {
            this.world = world;
            this.pos = pos;
            this.attempts = 0;
        }
    }

    /** 1.12.2 {@code GameRegistry.registerWorldGenerator(this.chaospersistsGen, 10)} — invoked from {@link #onChunkLoad}. */
    public void generate(Random random, int chunkX, int chunkZ, World world, Chunk chunk) {
        if (world.isClientSide) {
            return;
        }
        // Never call world.getChunk from ChunkEvent.Load — it deadlocks while the chunk is still loading (world gen stuck at 0%).
        if (chunk == null) {
            chunk = world.getChunk(chunkX, chunkZ);
        }
        if (recently_placed > 0) {
            --recently_placed;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension()) {
            this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
            if (!this.addHugeTree(world, random, chunkX * 16, chunkZ * 16, chunk)) {
                if (!this.addAppleTrees(world, random, chunkX * 16, chunkZ * 16, chunk) && !this.addOtherTrees(world, random, chunkX * 16, chunkZ * 16) && recently_placed == 0) {
                    this.addKingAltar(world, random, chunkX * 16, chunkZ * 16);
                }
                this.addVeggies(world, random, chunkX * 16, chunkZ * 16);
            }
            boolean rbd = false;
            rbd = this.addRubyDungeon(world, random, chunkX * 16, chunkZ * 16);
            if (!rbd) {
                this.addGenericDungeon(world, random, chunkX * 16, chunkZ * 16);
            }
            return;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(2)) {
            int i;
            this.generateRuby(world, random, chunkX * 16, chunkZ * 16);
            if (ChaosPersists.LessOre == 0) {
                int randPosY;
                int randPosX;
                int randPosZ;
                this.generateRuby(world, random, chunkX * 16, chunkZ * 16);
                this.generateRuby(world, random, chunkX * 16, chunkZ * 16);
                for (i = 0; i < 45; ++i) {
                    randPosX = chunkX * 16 + random.nextInt(16);
                    randPosY = random.nextInt(128);
                    randPosZ = chunkZ * 16 + random.nextInt(16);
                    if (randPosY >= 50) continue;
                    new WorldGenMinable(Blocks.LAPIS_ORE.defaultBlockState(), 7).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
                for (i = 0; i < 25; ++i) {
                    randPosX = chunkX * 16 + random.nextInt(16);
                    randPosY = random.nextInt(128);
                    randPosZ = chunkZ * 16 + random.nextInt(16);
                    if (randPosY >= 50) continue;
                    new WorldGenMinable(Blocks.LAPIS_ORE.defaultBlockState(), 4).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (recently_placed == 0 && random.nextInt(95) == 1) {
                i = random.nextInt(7);
                if (i == 0) {
                    this.addBasiliskMaze(world, random, chunkX * 16, chunkZ * 16);
                }
                if (i == 1) {
                    this.addKyuubiDungeon(world, random, chunkX * 16, chunkZ * 16);
                }
                if (i == 2) {
                    this.addBeeHive(world, random, chunkX * 16, chunkZ * 16);
                }
                if (i == 3) {
                    this.addShadowDungeon(world, random, chunkX * 16, chunkZ * 16);
                }
                if (i == 4) {
                    this.addAlienWTF(world, random, chunkX * 16, chunkZ * 16);
                }
                if (i == 5) {
                    this.addEnderKnight(world, random, chunkX * 16, chunkZ * 16);
                }
                if (i == 6) {
                    this.addLeonNest(world, random, chunkX * 16, chunkZ * 16);
                }
            } else {
                this.addGenericDungeon(world, random, chunkX * 16, chunkZ * 16);
            }
            this.addLavaAndWater(world, random, chunkX * 16, chunkZ * 16);
            this.addAnts(world, random, chunkX * 16, chunkZ * 16, 2);
            this.addAnts(world, random, chunkX * 16, chunkZ * 16, 2);
            this.addMosquitos(world, random, chunkX * 16, chunkZ * 16);
            this.addMosquitos(world, random, chunkX * 16, chunkZ * 16);
            this.addVeggies(world, random, chunkX * 16, chunkZ * 16);
            this.addRocks(world, random, chunkX * 16, chunkZ * 16);
            return;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(3)) {
            if (ChaosPersists.MosquitoEnable != 0) {
                this.addMosquitos(world, random, chunkX, chunkZ);
            }
            this.addAnts(world, random, chunkX * 16, chunkZ * 16, 4);
            this.addAppleTrees(world, random, chunkX * 16, chunkZ * 16, chunk);
            this.addGenericDungeon(world, random, chunkX * 16, chunkZ * 16);
            if (recently_placed == 0) {
                this.addDamselInDistress(world, random, chunkX * 16, chunkZ * 16);
            }
            if (recently_placed == 0) {
                this.addSpiderHangout(world, random, chunkX * 16, chunkZ * 16);
            }
            if (recently_placed == 0) {
                this.addRedAntHangout(world, random, chunkX * 16, chunkZ * 16);
            }
            return;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(4)) {
            int i;
            if (recently_placed == 0 && random.nextInt(100) == 0 && this.D4BigSpaceCheck(world, chunkX * 16, 7, chunkZ * 16)) {
                i = random.nextInt(19);
                if (i < 3) {
                    this.addD4Castle(world, random, chunkX * 16, chunkZ * 16);
                } else if (i < 7) {
                    this.addD4GenericDungeon(world, random, chunkX * 16, chunkZ * 16);
                } else {
                    if (i == 7) {
                        this.addD4EnderCastle(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 8) {
                        this.addD4IncaPyramid(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 9) {
                        this.addD4RobotLab(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 10) {
                        this.addD4Mini(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 11) {
                        this.addD4RubyDungeon(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 12) {
                        this.addD4CephadromeAltar(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 13) {
                        this.addD4Greenhouse(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 14) {
                        this.addD4NightmareRookery(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 15) {
                        this.addD4StinkyHouse(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 16) {
                        this.addD4WhiteHouse(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 17) {
                        this.addPumpkin(world, random, chunkX * 16, chunkZ * 16);
                    }
                    if (i == 18) {
                        this.addD4Rainbow(world, random, chunkX * 16, chunkZ * 16);
                    }
                }
            }
            if ((i = random.nextInt(300)) == 0) {
                this.addD4CloudShark(world, random, chunkX * 16, chunkZ * 16);
            }
            this.addUnstableAnts(world, random, chunkX * 16, chunkZ * 16);
            this.addIslands(world, random, chunkX * 16, chunkZ * 16);
            if (chunk != null && world instanceof ServerWorld) {
                new com.astryxion.chaospersists.world.dimension.chunkprovider.ChunkProviderChaos4(
                        world, ((ServerWorld) world).getSeed(), false)
                        .addScragglyTrees(world, chunkX * 16, chunkZ * 16, chunk);
            }
            this.addD4Rocks(world, random, chunkX * 16, chunkZ * 16);
            return;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(5)) {
            if (!this.addFairyTree(world, random, chunkX * 16, chunkZ * 16)) {
                this.addCrystalTermites(world, random, chunkX * 16, chunkZ * 16);
                if (recently_placed == 0) {
                    if (!(this.addRotatorStation(world, random, chunkX * 16, chunkZ * 16) || this.addUrchinSpawner(world, random, chunkX * 16, chunkZ * 16) || this.addCrystalHauntedHouse(world, random, chunkX * 16, chunkZ * 16) || this.addRoundRotator(world, random, chunkX * 16, chunkZ * 16))) {
                        this.addCrystalBattleTower(world, random, chunkX * 16, chunkZ * 16);
                    }
                    this.addIrukandji(world, random, chunkX * 16, chunkZ * 16);
                }
            }
            this.addCrystalChestsAndSpawners(world, random, chunkX * 16, chunkZ * 16);
            if (world.random.nextInt(4) == 1) {
                this.addRocks(world, random, chunkX * 16, chunkZ * 16);
            }
            return;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(6)) {
            this.addButterfliesAndMoths(world, random, chunkX * 16, chunkZ * 16);
            this.addVeggies(world, random, chunkX * 16, chunkZ * 16);
            this.addAnts(world, random, chunkX * 16, chunkZ * 16, 2);
            return;
        }
        switch (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world)) {
            case -1: {
                this.generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 0: {
                this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
                this.generateOres(world, random, chunkX * 16, chunkZ * 16, chunk);
                break;
            }
            case 1: {
                this.generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
        }
    }

    private void generateEnd(World world, Random random, int chunkX, int chunkZ) {
        this.addEndAnts(world, random, chunkX, chunkZ);
        int i = world.random.nextInt(4);
        if (i == 0) {
            this.addEndKnights(world, random, chunkX, chunkZ);
        }
        if (i == 1) {
            this.addEndReapers(world, random, chunkX, chunkZ);
        }
        if (i == 2) {
            this.addHospital(world, random, chunkX, chunkZ);
        }
        if (i == 3) {
            this.addEnderCastle(world, random, chunkX, chunkZ);
        }
    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ) {
        int i;
        int randPosY;
        int randPosZ;
        int randPosX;
        if (ChaosPersists.MosquitoEnable != 0) {
            this.addNetherMosquitos(world, random, chunkX, chunkZ);
        }
        this.addNetherAnts(world, random, chunkX, chunkZ);
        int patchy = 15 + random.nextInt(10);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 3;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(13);
            randPosY = random.nextInt(108) + 10;
            randPosZ = 3 + chunkZ + random.nextInt(13);
            new WorldGenMinable(ChaosPersists.MyLavafoamBlock.defaultBlockState(), 6, BlockMatcher.forBlock(Blocks.NETHERRACK)).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }
        patchy = 5 + random.nextInt(5);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 3;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(13);
            randPosY = random.nextInt(108) + 10;
            randPosZ = 3 + chunkZ + random.nextInt(13);
            new WorldGenMinable(ChaosPersists.MyOreRubyBlock.defaultBlockState(), 2, BlockMatcher.forBlock(Blocks.NETHERRACK)).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
        }
    }

    public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
        boolean ahh = false;
        this.addStrawberries(world, random, chunkX, chunkZ);
        this.addTomatoes(world, random, chunkX, chunkZ);
        this.addVeggies(world, random, chunkX, chunkZ);
        this.addButterfliesAndMoths(world, random, chunkX, chunkZ);
        if (ChaosPersists.MosquitoEnable != 0) {
            this.addMosquitos(world, random, chunkX, chunkZ);
        }
        if (ChaosPersists.DisableOverworldDungeons == 0 && com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == 0 && recently_placed == 0) {
            int i = world.random.nextInt(6);
            if (i == 0) {
                this.addPlayPool(world, random, chunkX, chunkZ);
            }
            if (i == 1) {
                this.addWaterDragonLair(world, random, chunkX, chunkZ);
            }
            if (i == 2) {
                this.addGoldFishBowl(world, random, chunkX, chunkZ);
            }
            if (i == 3) {
                this.addGirlfriendIsland(world, random, chunkX, chunkZ);
            }
            if (i == 4) {
                this.addMonsterIsland(world, random, chunkX, chunkZ);
            }
            if (i == 5) {
                this.addFrogPond(world, random, chunkX, chunkZ);
            }
            if (!(ahh = this.addANest(world, random, chunkX, chunkZ))) {
                ahh = this.addHauntedHouse(world, random, chunkX, chunkZ);
            }
            if (!ahh) {
                ahh = this.addLeafMonster(world, random, chunkX, chunkZ);
            }
            if (!ahh) {
                ahh = this.addSpitBug(world, random, chunkX, chunkZ);
            }
            if (!ahh) {
                ahh = this.addIgloo(world, random, chunkX, chunkZ);
            }
            if (!ahh) {
                ahh = this.addBouncyCastle(world, random, chunkX, chunkZ);
            }
            if (!ahh) {
                ahh = this.addRubberDuckyPond(world, random, chunkX, chunkZ);
            }
        }
        this.addAnts(world, random, chunkX, chunkZ, 4);
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == net.minecraftforge.registries.ForgeRegistries.BIOMES.getValue(Biomes.RIVER.location()) || b == net.minecraftforge.registries.ForgeRegistries.BIOMES.getValue(Biomes.MOUNTAINS.location()) || b == net.minecraftforge.registries.ForgeRegistries.BIOMES.getValue(Biomes.DESERT.location())) {
            this.addRocks(world, random, chunkX, chunkZ);
        }
    }

    public void generateRuby(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.Ruby_stats.rate <= 0) {
            return;
        }
        int patchy = ChaosPersists.Ruby_stats.rate + random.nextInt(7);
        block0 : for (int i = 0; i < patchy; ++i) {
            int randPosX = 3 + chunkX + random.nextInt(10);
            int randPosY = random.nextInt(128);
            int randPosZ = 3 + chunkZ + random.nextInt(10);
            if (randPosY > ChaosPersists.Ruby_stats.maxdepth || randPosY < ChaosPersists.Ruby_stats.mindepth) continue;
            for (int m = randPosY; m > 5; --m) {
                Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(randPosX, m, randPosZ)).getBlock();
                    if (bid != Blocks.LAVA && bid != Blocks.LAVA || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(randPosX, m - 1, randPosZ)).getBlock()) != Blocks.STONE) continue;
                ChaosPersists.setBlockFast((World)world, (int)randPosX, (int)(m - 1), (int)randPosZ, (Block)ChaosPersists.MyOreRubyBlock, (int)0, (int)2);
                continue block0;
            }
        }
    }

    public void generateOres(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        int i;
        int randPosY;
        int randPosZ;
        int patchy;
        int randPosX;
        com.astryxion.chaospersists.world.ore.ChunkOreGenerator chunkOreGenerator = new com.astryxion.chaospersists.world.ore.ChunkOreGenerator();
        if (ChaosPersists.SpawnOres_stats.rate > 0) {
            patchy = ChaosPersists.SpawnOres_stats.rate + random.nextInt(20);
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
                    chunkOreGenerator.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, b, ChaosPersists.SpawnOres_stats.clumpsize);
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
                chunkOreGenerator.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, b, ChaosPersists.SpawnOres_stats.clumpsize);
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
                new WorldGenMinable(ChaosPersists.MyOreUraniumBlock.defaultBlockState(), ChaosPersists.Uranium_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
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
                new WorldGenMinable(ChaosPersists.MyOreTitaniumBlock.defaultBlockState(), ChaosPersists.Titanium_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
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
                new WorldGenMinable(ChaosPersists.MyOreAmethystBlock.defaultBlockState(), ChaosPersists.Amethyst_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
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
                new WorldGenMinable(ChaosPersists.MyOreSaltBlock.defaultBlockState(), ChaosPersists.Salt_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
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
            chunkOreGenerator.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.RedAntTroll, 4);
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
            chunkOreGenerator.generateOreBlock(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.TermiteTroll, 4);
        }
        if (ChaosPersists.Ruby_stats.rate > 0) {
            patchy = ChaosPersists.Ruby_stats.rate + random.nextInt(5);
            block116 : for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY > ChaosPersists.Ruby_stats.maxdepth || randPosY < ChaosPersists.Ruby_stats.mindepth) continue;
                for (int m = randPosY; m > 5; --m) {
                    Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(randPosX, m, randPosZ)).getBlock();
                    if (bid != Blocks.LAVA && bid != Blocks.LAVA || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(randPosX, m - 1, randPosZ)).getBlock()) != Blocks.STONE) continue;
                    ChaosPersists.setBlockFast((World)world, (int)randPosX, (int)(m - 1), (int)randPosZ, (Block)ChaosPersists.MyOreRubyBlock, (int)0, (int)2);
                    continue block116;
                }
            }
        }
        if (ChaosPersists.LessOre == 0) {
            if (ChaosPersists.Diamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Diamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Diamond_stats.maxdepth || randPosY < ChaosPersists.Diamond_stats.mindepth) continue;
                    new WorldGenMinable(Blocks.DIAMOND_ORE.defaultBlockState(), ChaosPersists.Diamond_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (ChaosPersists.BlkDiamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkDiamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkDiamond_stats.maxdepth || randPosY < ChaosPersists.BlkDiamond_stats.mindepth) continue;
                    new WorldGenMinable(Blocks.DIAMOND_BLOCK.defaultBlockState(), ChaosPersists.BlkDiamond_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (ChaosPersists.Emerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Emerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Emerald_stats.maxdepth || randPosY < ChaosPersists.Emerald_stats.mindepth) continue;
                    new WorldGenMinable(Blocks.EMERALD_ORE.defaultBlockState(), ChaosPersists.Emerald_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (ChaosPersists.BlkEmerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkEmerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkEmerald_stats.maxdepth || randPosY < ChaosPersists.BlkEmerald_stats.mindepth) continue;
                    new WorldGenMinable(Blocks.EMERALD_BLOCK.defaultBlockState(), ChaosPersists.BlkEmerald_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (ChaosPersists.Gold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Gold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Gold_stats.maxdepth || randPosY < ChaosPersists.Gold_stats.mindepth) continue;
                    new WorldGenMinable(Blocks.GOLD_ORE.defaultBlockState(), ChaosPersists.Gold_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (ChaosPersists.BlkGold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkGold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkGold_stats.maxdepth || randPosY < ChaosPersists.BlkGold_stats.mindepth) continue;
                    new WorldGenMinable(Blocks.GOLD_BLOCK.defaultBlockState(), ChaosPersists.BlkGold_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
            if (ChaosPersists.BlkRuby_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkRuby_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkRuby_stats.maxdepth || randPosY < ChaosPersists.BlkRuby_stats.mindepth) continue;
                    new WorldGenMinable(ChaosPersists.MyBlockRubyBlock.defaultBlockState(), ChaosPersists.BlkRuby_stats.clumpsize).generate(world, random, new BlockPos(randPosX, randPosY, randPosZ));
                }
            }
        }
    }

    public void addStrawberries(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(20) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension() || b == ForgeRegistries.BIOMES.getValue(Biomes.FOREST.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.WOODED_HILLS.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.BIRCH_FOREST_HILLS.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.BIRCH_FOREST.location())) {
            block0 : for (int i = 0; i < 5; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyStrawberryPlant, (int)0, (int)2);
                    continue block0;
                }
            }
        }
    }

    public boolean addHauntedHouse(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(285) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.PLAINS.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.TAIGA.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.SWAMP.location())) {
            for (int i = 0; i < 5; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.MyDungeon.makeHauntedHouse(world, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addANest(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(230) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.FOREST.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.WOODED_HILLS.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.JUNGLE.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.JUNGLE.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.BIRCH_FOREST.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.BIRCH_FOREST_HILLS.location())) {
            for (int i = 0; i < 5; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 128; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    if (random.nextInt(2) == 0) {
                        ChaosPersists.MyDungeon.makeSmallBeeHive(world, posX, posY, posZ);
                    } else {
                        ChaosPersists.MyDungeon.makeMantisHive(world, posX, posY, posZ);
                    }
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Official 1.12.2 corn runs on {@link DecorateBiomeEvent.Decorate.EventType#GRASS} with 1% chance (see {@link #onDecorateGrassForCorn}).
     */
    private void placeCornClusters(World world, Random random, int baseX, int baseZ) {
        block0:
        for (int j = 0; j < 32; ++j) {
            int posX = baseX + random.nextInt(8) - random.nextInt(8);
            int posZ = baseZ + random.nextInt(8) - random.nextInt(8);

            for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                if (world.getBlockState(new BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) {
                    continue;
                }

                boolean is_all_air = true;
                for (int i = 1; i < 10; ++i) {
                    if (!world.isEmptyBlock(new BlockPos(posX, posY + i, posZ))) {
                        is_all_air = false;
                        break;
                    }
                }
                if (!is_all_air) {
                    continue;
                }

                int maxHeight = 21;
                int height = 0;
                int yCursor = posY;

                while (height < maxHeight && yCursor < 255 && world.isEmptyBlock(new BlockPos(posX, yCursor, posZ))) {
                    ChaosPersists.setBlockFast((World)world, posX, yCursor, posZ, (Block)ChaosPersists.MyCornPlant4, 0, 2);
                    height += random.nextInt(5) + 3;
                    ++yCursor;
                }

                if (yCursor < 255 && world.isEmptyBlock(new BlockPos(posX, yCursor, posZ))) {
                    ChaosPersists.setBlockFast((World)world, posX, yCursor, posZ, (Block)ChaosPersists.MyCornPlant1, 0, 2);
                }
                continue block0;
            }
        }
    }

    /** 1.12.2 {@code EntityRegistry.addSpawn} entries queued via {@link ChaosPersists#addChaosSpawn}. */
    public void onBiomeLoad(BiomeLoadingEvent event) {
        ChaosPersists.applyPendingChaosSpawns(event);
    }

    /** 1.16.5 equivalent of {@code GameRegistry.registerWorldGenerator(chaospersistsGen, 10)}. */
    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        if (event.getWorld().isClientSide() || !(event.getWorld() instanceof ServerWorld)) {
            return;
        }
        ServerWorld world = (ServerWorld) event.getWorld();
        ChunkPos pos = event.getChunk().getPos();
        pendingChunkDecorations.add(new PendingChunkDecoration(world, pos));
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.SERVER) {
            return;
        }
        PendingChunkDecoration pending;
        int budget = 8;
        while (budget-- > 0 && (pending = pendingChunkDecorations.poll()) != null) {
            if (!pending.world.hasChunk(pending.pos.x, pending.pos.z)) {
                continue;
            }
            Chunk chunk = pending.world.getChunkSource().getChunkNow(pending.pos.x, pending.pos.z);
            if (chunk == null || !chunk.getStatus().isOrAfter(ChunkStatus.FULL)) {
                if (++pending.attempts < 40) {
                    pendingChunkDecorations.add(pending);
                }
                continue;
            }
            this.runDeferredChunkDecoration(pending.world, pending.pos);
        }
        PendingChunkDecoration spawnerPending;
        int spawnerBudget = 4;
        while (spawnerBudget-- > 0 && (spawnerPending = pendingSpawnerNormalizations.poll()) != null) {
            if (!spawnerPending.world.hasChunk(spawnerPending.pos.x, spawnerPending.pos.z)) {
                continue;
            }
            Chunk chunk = spawnerPending.world.getChunkSource().getChunkNow(spawnerPending.pos.x, spawnerPending.pos.z);
            if (chunk == null || !chunk.getStatus().isOrAfter(ChunkStatus.FULL)) {
                if (++spawnerPending.attempts < 40) {
                    pendingSpawnerNormalizations.add(spawnerPending);
                }
                continue;
            }
            if (ChaosPersists.instance != null) {
                ChaosPersists.instance.normalizeSpawnersInChunk(spawnerPending.world, spawnerPending.pos);
            }
        }
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        if (event.getWorld() instanceof World) {
            RegistryKey<World> dim = ((World) event.getWorld()).dimension();
            decoratedChunkKeysByDimension.remove(dim);
            pendingChunkDecorations.removeIf(p -> p.world.dimension().equals(dim));
            pendingSpawnerNormalizations.removeIf(p -> p.world.dimension().equals(dim));
        }
    }

    private void runDeferredChunkDecoration(ServerWorld world, ChunkPos pos) {
        if (!world.hasChunk(pos.x, pos.z)) {
            return;
        }
        Set<Long> decorated = decoratedChunkKeysByDimension.computeIfAbsent(
                world.dimension(),
                key -> ConcurrentHashMap.newKeySet());
        long chunkKey = ChunkPos.asLong(pos.x, pos.z);
        if (!decorated.add(chunkKey)) {
            return;
        }
        Chunk chunk = world.getChunk(pos.x, pos.z);
        ChaosPersists.setWorldGenChunkContext(chunk, world);
        try {
            this.generate(world.getRandom(), pos.x, pos.z, world, chunk);
            this.tryDecorateGrassForCorn(world, world.getRandom(), pos.x << 4, pos.z << 4);
        } finally {
            ChaosPersists.setWorldGenChunkContext(null, null);
        }
    }

    /** 1.12.2 {@link net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate} GRASS at 1% — same chance/position rules. */
    private void tryDecorateGrassForCorn(World world, Random rand, int chunkX, int chunkZ) {
        if (world.isClientSide || !world.dimensionType().hasSkyLight()) {
            return;
        }
        if (rand.nextDouble() > 0.01D) {
            return;
        }
        int ox = rand.nextInt(16) + 8;
        int oz = rand.nextInt(16) + 8;
        BlockPos column = new BlockPos(chunkX + ox, 0, chunkZ + oz);
        BlockPos surface = world.getHeightmapPos(net.minecraft.world.gen.Heightmap.Type.WORLD_SURFACE, column);
        this.placeCornClusters(world, rand, surface.getX(), surface.getZ());
    }

    public void addTomatoes(World world, Random random, int chunkX, int chunkZ) {
        boolean is_all_air = true;
        if (random.nextInt(15) != 1) {
            return;
        }
        block0 : for (int j = 0; j < 8; ++j) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            is_all_air = true;
            for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                int i;
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                for (i = 1; i < 10; ++i) {
                    if (world.isEmptyBlock(new BlockPos(posX, posY + i, posZ))) continue;
                    is_all_air = false;
                }
                if (!is_all_air) continue block0;
                Biome biome = world.getBiome(new BlockPos(posX, posY, posZ));
                float temp = biome.getTemperature(new BlockPos(posX, posY, posZ));
                boolean validBiome = temp > 0.2F && biome.getBiomeCategory() != net.minecraft.world.biome.Biome.Category.OCEAN;
                if ((com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension() || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == 0 || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(2)) && validBiome) {
                    int corn_height = random.nextInt(3);
                    if (++corn_height == 1) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyTomatoPlant1, (int)0, (int)2);
                    }
                    if (corn_height == 2) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyTomatoPlant2, (int)0, (int)2);
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY + 1), (int)posZ, (Block)ChaosPersists.MyTomatoPlant1, (int)0, (int)2);
                    }
                    if (corn_height <= 2) continue block0;
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyTomatoPlant3, (int)0, (int)2);
                    for (i = 1; i < corn_height; ++i) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY + i), (int)posZ, (Block)ChaosPersists.MyTomatoPlant4, (int)0, (int)2);
                    }
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY + corn_height), (int)posZ, (Block)ChaosPersists.MyTomatoPlant1, (int)0, (int)2);
                    continue block0;
                }
            }
        }
    }

    public void addButterfliesAndMoths(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(10 + ChaosPersists.LessLag * 2) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension() || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(6) || b == ForgeRegistries.BIOMES.getValue(Biomes.FOREST.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.WOODED_HILLS.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.RIVER.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.JUNGLE.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.JUNGLE.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.SWAMP.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.BIRCH_FOREST.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.BIRCH_FOREST_HILLS.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.DARK_FOREST.location())) {
            block0 : for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                int which = 0;
                for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    which = random.nextInt(3);
                    if (which == 0) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyButterflyPlant, (int)0, (int)2);
                        continue block0;
                    }
                    if (which == 1) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyMothPlant, (int)0, (int)2);
                        continue block0;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyFireflyPlant, (int)0, (int)2);
                    continue block0;
                }
            }
        }
    }

    public void addPlayPool(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(350) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.OCEAN.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.WATER) continue;
                    ChaosPersists.MyDungeon.makePlayPool(world, posX, posY, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addFrogPond(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(350) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.PLAINS.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.MyDungeon.makeFrogPond(world, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addGoldFishBowl(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(350) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.OCEAN.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.WATER) continue;
                    ChaosPersists.MyDungeon.makeGoldFishBowl(world, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public boolean addLeafMonster(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(275) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.PLAINS.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.MyDungeon.makeLeafMonsterDungeon(world, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addRubberDuckyPond(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(275) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.PLAINS.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.MyDungeon.makeRubberDuckyPond(world, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addSpitBug(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(190) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.SWAMP.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.MyDungeon.makeSpitBugLair(world, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addIgloo(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(220) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.SNOWY_TUNDRA.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.SNOW) continue;
                    ChaosPersists.MyDungeon.makeIgloo(world, posX, posY - 2, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addBouncyCastle(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(230) != 0) {
            return false;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.DESERT.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.SAND) continue;
                    ChaosPersists.MyDungeon.makeBouncyCastle(world, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addDamselInDistress(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(250) != 0) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            boolean which = false;
            for (int posY = 100; posY > 40; --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK || !this.quickSpaceCheck(world, posX, posY - 1, posZ)) continue;
                ChaosPersists.MyDungeon.makeDamselInDistress(world, posX, posY - 1, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addSpiderHangout(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(350) != 0) {
            return false;
        }
        if (ChaosPersists.SpiderDriverEnable == 0) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            boolean which = false;
            for (int posY = 100; posY > 40; --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK || !this.quickSpaceCheck(world, posX, posY - 1, posZ)) continue;
                ChaosPersists.MyDungeon.makeSpiderHangout(world, posX, posY - 1, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addRedAntHangout(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(250) != 0) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            boolean which = false;
            for (int posY = 100; posY > 40; --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK || !this.quickSpaceCheck(world, posX, posY - 1, posZ)) continue;
                ChaosPersists.MyDungeon.makeRedAntHangout(world, posX, posY - 1, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public void addWaterDragonLair(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(350) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.OCEAN.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.WATER) continue;
                    ChaosPersists.MyDungeon.makeWaterDragonLair(world, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addGirlfriendIsland(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(300) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.OCEAN.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.WATER) continue;
                    ChaosPersists.MyDungeon.makeGirlfriendIsland(world, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addMonsterIsland(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(300) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (b == ForgeRegistries.BIOMES.getValue(Biomes.OCEAN.location())) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                boolean which = false;
                for (int posY = 100; posY > 40; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.WATER) continue;
                    ChaosPersists.MyDungeon.makeMonsterIsland(world, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addMosquitos(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(25 + ChaosPersists.LessLag * 2) != 0) {
            return;
        }
        if ((com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension() || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(3)) && random.nextInt(3) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension() || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(2) || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(3) || b == ForgeRegistries.BIOMES.getValue(Biomes.JUNGLE.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.SWAMP.location())) {
            block0 : for (int i = 0; i < 2; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyMosquitoPlant, (int)0, (int)2);
                    continue block0;
                }
            }
        }
    }

    public void addNetherMosquitos(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(25) != 0) {
            return;
        }
        block0 : for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 20; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.NETHERRACK) continue;
                ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyMosquitoPlant, (int)0, (int)2);
                continue block0;
            }
        }
    }

    public void addNetherAnts(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.RedAntEnable == 0) {
            return;
        }
        if (random.nextInt(25) != 0) {
            return;
        }
        block0 : for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 20; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.NETHERRACK) continue;
                ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.MyRedAntBlock, (int)0, (int)2);
                continue block0;
            }
        }
    }

    public void addAnts(World world, Random random, int chunkX, int chunkZ, int redfreq) {
        if (ChaosPersists.RedAntEnable == 0 && ChaosPersists.BlackAntEnable == 0 && ChaosPersists.RainbowAntEnable == 0 && ChaosPersists.UnstableAntEnable == 0) {
            return;
        }
        if (redfreq < 2) {
            redfreq = 2;
        }
        if (random.nextInt(30 + ChaosPersists.LessLag * 4) != 0) {
            return;
        }
        block0 : for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                if (random.nextInt(redfreq) == 0) {
                    int which = random.nextInt(4);
                    if (which == 0 && ChaosPersists.RedAntEnable != 0) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.MyRedAntBlock, (int)0, (int)2);
                    }
                    if (which == 1 && ChaosPersists.RainbowAntEnable != 0) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.MyRainbowAntBlock, (int)0, (int)2);
                    }
                    if (which == 2 && ChaosPersists.UnstableAntEnable != 0) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.MyUnstableAntBlock, (int)0, (int)2);
                    }
                    if (which != 3 || ChaosPersists.TermiteEnable == 0) continue block0;
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.TermiteBlock, (int)0, (int)2);
                    continue block0;
                }
                if (ChaosPersists.BlackAntEnable == 0) continue block0;
                ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.MyAntBlock, (int)0, (int)2);
                continue block0;
            }
        }
    }

    public void addEndAnts(World world, Random random, int chunkX, int chunkZ) {
    }

    public void addEndKnights(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(25) != 0) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.END_STONE || !this.quickSpaceCheck(world, posX, posY, posZ)) continue;
                ChaosPersists.MyDungeon.makeEnderKnightDungeon(world, posX, posY, posZ);
                return;
            }
        }
    }

    public void addEndReapers(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(25) != 0) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.END_STONE || !this.quickSpaceCheck(world, posX, posY, posZ)) continue;
                ChaosPersists.MyDungeon.makeEnderReaperGraveyard(world, posX, posY, posZ);
                return;
            }
        }
    }

    public void addHospital(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(25) != 0) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.END_STONE || !this.quickSpaceCheck(world, posX, posY, posZ)) continue;
                ChaosPersists.MyDungeon.makeEnderDragonHospital(world, posX, posY, posZ);
                return;
            }
        }
    }

    public void addEnderCastle(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(50) != 0) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.END_STONE || !this.quickBigSpaceCheck(world, posX, posY, posZ)) continue;
                ChaosPersists.MyDungeon.makeEnderCastle(world, posX, posY, posZ);
                return;
            }
        }
    }

    public void addUnstableAnts(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.UnstableAntEnable == 0) {
            return;
        }
        if (random.nextInt(30) != 0) {
            return;
        }
        block0 : for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 20; posY > 2 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.MyUnstableAntBlock, (int)0, (int)2);
                continue block0;
            }
        }
    }

    public void addCrystalTermites(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.TermiteEnable == 0) {
            return;
        }
        if (random.nextInt(40) != 0) {
            return;
        }
        block0 : for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)ChaosPersists.CrystalTermiteBlock, (int)0, (int)2);
                continue block0;
            }
        }
    }

    public boolean addRotatorStation(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.RotatorEnable == 0) {
            return false;
        }
        if (random.nextInt(150) != 0) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.MyDungeon.makeRotatorStation(world, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addRoundRotator(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.RotatorEnable == 0) {
            return false;
        }
        if (random.nextInt(150) != 0) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.MyDungeon.makeRoundRotator(world, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addUrchinSpawner(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.UrchinEnable == 0) {
            return false;
        }
        if (random.nextInt(180) != 0) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.MyDungeon.makeUrchinSpawner(world, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addCrystalHauntedHouse(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(230) != 0) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.MyDungeon.makeCrystalHauntedHouse(world, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addCrystalBattleTower(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(280) != 0) {
            return false;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.MyDungeon.makeCrystalBattleTower(world, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public void addIrukandji(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.IrukandjiEnable == 0) {
            return;
        }
        if (random.nextInt(80) != 0) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.WATER) continue;
                ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)Blocks.SPAWNER, (int)0, (int)2);
                MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity)world.getBlockEntity(new BlockPos(posX, posY, posZ));
                if (tileentitymobspawner != null) {
                    com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "irukandji"));
                }
                return;
            }
        }
    }

    public void addCrystalChestsAndSpawners(World world, Random random, int chunkX, int chunkZ) {
        Block bid = Blocks.AIR;
        for (int i = 0; i < 3; ++i) {
            int posY;
            int posZ;
            int posX = 1 + chunkX + random.nextInt(14);
            posY = 25;
            posZ = 1 + chunkZ + random.nextInt(14);
            if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ))) continue;
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.AIR) break;
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + 1, posY, posZ)).getBlock();
            if (bid == Blocks.AIR) {
                this.addCrystalChest(world, posX, posY, posZ, 5);
                break;
            }
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX - 1, posY, posZ)).getBlock();
            if (bid == Blocks.AIR) {
                this.addCrystalChest(world, posX, posY, posZ, 4);
                break;
            }
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ + 1)).getBlock();
            if (bid == Blocks.AIR) {
                this.addCrystalChest(world, posX, posY, posZ, 2);
                break;
            }
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ - 1)).getBlock();
            if (bid != Blocks.AIR) break;
            this.addCrystalChest(world, posX, posY, posZ, 3);
            break;
        }
    }

    public void addCrystalChest(World world, int x, int y, int z, int dir) {
        int i = world.random.nextInt(3);
        if (i == 0) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)Blocks.CHEST, (int)0, (int)2);
            net.minecraft.util.Direction chestFacing = net.minecraft.util.Direction.NORTH;
            if (dir == 3) {
                chestFacing = net.minecraft.util.Direction.SOUTH;
            } else if (dir == 4) {
                chestFacing = net.minecraft.util.Direction.WEST;
            } else if (dir == 5) {
                chestFacing = net.minecraft.util.Direction.EAST;
            }
            world.setBlock(new net.minecraft.util.math.BlockPos(x, y, z), net.minecraft.block.Blocks.CHEST.defaultBlockState().setValue(net.minecraft.block.ChestBlock.FACING, chestFacing), 3);
            ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new BlockPos(x, y, z));
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])Trees.CrystalChestContentsList, (IInventory)chest, (int)(1 + world.random.nextInt(3)));
            }
        } else {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)Blocks.SPAWNER, (int)0, (int)2);
            MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity)world.getBlockEntity(new BlockPos(x, y, z));
            if (tileentitymobspawner != null) {
                int t = world.random.nextInt(2);
                if (t == 0) {
                    com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
                }
                if (t == 1) {
                    com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
                }
            }
        }
    }

    public void addIslands(World world, Random random, int chunkX, int chunkZ) {
        int posX = 2 + chunkX + random.nextInt(12);
        int posZ = 2 + chunkZ + random.nextInt(12);
        if (random.nextInt(10 + ChaosPersists.LessLag * 2) != 1) {
            return;
        }
        for (int posY = 20; posY > 2 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
            if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
            ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyIslandBlock, (int)0, (int)2);
            // 1.7.10: island blocks used random block ticks (setTickRandomly), so growth spread over time.
            // Do not scheduleUpdate here — that made every new chunk fire islands together and tank TPS.
            break;
        }
    }

    public boolean addAppleTrees(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        int freq = Math.abs(chunkX / 16) + Math.abs(chunkZ / 16);
        int howmany = 2;
        int which = 0;
        boolean added = false;
        howmany += random.nextInt(2 + (15 - (freq %= 15)) / 2);
        which = random.nextInt(10);
        if (random.nextInt(15 + freq) != 0) {
            return false;
        }
        if (ChaosPersists.LessLag == 1) {
            howmany /= 2;
        }
        if (ChaosPersists.LessLag == 2 && (howmany /= 4) < 1) {
            return false;
        }
        block0 : for (int i = 0; i < howmany; ++i) {
            int posX = 2 + chunkX + random.nextInt(12);
            int posZ = 2 + chunkZ + random.nextInt(12);
            for (int posY = 100; posY > 50 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                if (!(ChaosPersists.MyAppleSeed instanceof ItemAppleSeed)) {
                    continue;
                }
                ItemAppleSeed a = (ItemAppleSeed) ChaosPersists.MyAppleSeed;
                if (which < 8 && ChaosPersists.MyAppleLeaves != null) {
                    a.makeTree(world, posX, posY - 1, posZ, ChaosPersists.MyAppleLeaves, chunk);
                }
                if (which == 8 && ChaosPersists.MyCherryLeaves != null) {
                    a.makeTree(world, posX, posY - 1, posZ, ChaosPersists.MyCherryLeaves, chunk);
                }
                if (which == 9 && ChaosPersists.MyPeachLeaves != null) {
                    a.makeTree(world, posX, posY - 1, posZ, ChaosPersists.MyPeachLeaves, chunk);
                }
                added = true;
                continue block0;
            }
        }
        return added;
    }

    public boolean addHugeTree(World world, Random random, int chunkX, int chunkZ, Chunk chunk)
    {
      int made_one = 0;

      if (random.nextInt(50) != 0) return false;
      if ((ChaosPersists.LessLag == 1) && 
        (random.nextInt(2) != 0)) return false;

      if ((ChaosPersists.LessLag == 2) && 
        (random.nextInt(4) != 0)) return false;

      for (int i = 0; (i < 3) && (made_one == 0); i++) {
        int posX = 4 + chunkX + random.nextInt(8);
        int posZ = 4 + chunkZ + random.nextInt(8);
        for (int posY = 127; (posY > 50) && (made_one == 0); posY--)
        {
          if ((!world.isEmptyBlock(new BlockPos(posX, posY, posZ))) || (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK)) {
            continue;
          }
          ItemMagicApple a = (ItemMagicApple)ChaosPersists.MagicApple;

          int tree_type = random.nextInt(4);
          int tree_radius = 6 - random.nextInt(2);
          boolean no_critters = false;
          Block leaf_type = Blocks.OAK_LEAVES;

          if (random.nextInt(100) > 25) {
            no_critters = true;
          }
          int rand_treetype = random.nextInt(100);
          if (rand_treetype > 75) {
            if ((tree_type != 3) && (random.nextInt(20) == 0)) leaf_type = ChaosPersists.MyAppleLeaves;
            a.MakeBigSquareTree(world, posX, posY - 1, posZ, Blocks.OAK_LOG, leaf_type, Blocks.MOSSY_COBBLESTONE, tree_type, tree_radius, no_critters, chunk);
          } else if (rand_treetype == 0) {
            tree_radius = 6;
            no_critters = true;
            if (random.nextInt(2) == 0)
              a.MakeBigSquareTree(world, posX, posY - 1, posZ, Blocks.GOLD_BLOCK, Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK, -1, tree_radius, no_critters, chunk);
            else {
              a.MakeBigSquareTree(world, posX, posY - 1, posZ, Blocks.OBSIDIAN, ChaosPersists.MyBlockRubyBlock, ChaosPersists.MyBlockAmethystBlock, -1, tree_radius, no_critters, chunk);
            }
          }
          else if (rand_treetype > 15) {
            tree_radius = 6 - random.nextInt(3);
            a.MakeBigCircularTree(world, posX, posY - 1, posZ, Blocks.OAK_LOG, leaf_type, Blocks.MOSSY_COBBLESTONE, tree_type, tree_radius, no_critters, chunk);
          } else {
            tree_radius = 6 - random.nextInt(3);
            a.MakeBigRoundTree(world, posX, posY - 1, posZ, Blocks.OAK_LOG, leaf_type, Blocks.MOSSY_COBBLESTONE, tree_type, tree_radius, chunk);
          }

          made_one = 1;
          break;
        }

      }

      return made_one != 0;
    }

    public void addVeggies(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(15) != 0) {
            return;
        }
        Biome b = world.getBiomeManager().getNoiseBiomeAtPosition(chunkX >> 2, 0, chunkZ >> 2);
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension() || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(2) || com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(6) || b == ForgeRegistries.BIOMES.getValue(Biomes.RIVER.location()) || b == ForgeRegistries.BIOMES.getValue(Biomes.SWAMP.location())) {
            block0 : for (int i = 0; i < 8; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    int what = random.nextInt(6);
                    if (what == 0) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)Blocks.CARROTS, (int)0, (int)2);
                        continue block0;
                    }
                    if (what == 1) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)Blocks.POTATOES, (int)0, (int)2);
                        continue block0;
                    }
                    if (what == 2) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyRadishPlant, (int)0, (int)2);
                        continue block0;
                    }
                    if (what == 3) {
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyLettucePlant1, (int)0, (int)2);
                        continue block0;
                    }
                    if (what == 4) {
                        if (random.nextInt(10) != 0) continue block0;
                        ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)Blocks.MELON_STEM, (int)0, (int)2);
                        continue block0;
                    }
                    if (random.nextInt(50) != 1 || ChaosPersists.enableduplicatortree == 0) continue block0;
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)ChaosPersists.MyDT, (int)0, (int)2);
                    if (DEBUG_NATURAL_DUPLICATOR_SPAWNS) {
                        org.apache.logging.log4j.LogManager.getLogger(ChaosWorld.class).info("ChaosPersists DEBUG: natural duplicator log placed at dim {} ({}, {}, {})", com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world), posX, posY, posZ);
                    }
                    continue block0;
                }
            }
        }
    }

    public void addRocks(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(5) != 0) {
            return;
        }
        if (ChaosPersists.RockEnable == 0) {
            return;
        }
        int howmany = 3 + random.nextInt(10);
        block0 : for (int i = 0; i < howmany; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 110; posY > 40 && world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() == Blocks.AIR; --posY) {
                Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock();
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.SAND && bid != ChaosPersists.CrystalGrass) continue;
                this.spawnCreature(world, "Rock", (double)posX, (double)posY, (double)posZ);
                continue block0;
            }
        }
    }

    public void addD4Rocks(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(7) != 0) {
            return;
        }
        if (ChaosPersists.RockEnable == 0) {
            return;
        }
        int howmany = 3 + random.nextInt(10);
        block0 : for (int i = 0; i < howmany; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 20; posY > 5 && world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() == Blocks.AIR; --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                this.spawnCreature(world, "Rock", (double)posX, (double)posY, (double)posZ);
                continue block0;
            }
        }
    }

    public boolean addFairyTree(World world, Random random, int chunkX, int chunkZ) {
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        if (random.nextInt(5) != 0) {
            return false;
        }
        for (int posY = 128; posY > 40; --posY) {
            Block bid;
            int i;
            int j;
            if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != ChaosPersists.CrystalGrass) continue;
            for (i = -8; i <= 8; ++i) {
                for (j = -8; j <= 8; ++j) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + i, posY, posZ + j)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            for (i = -2; i <= 2; ++i) {
                for (j = -2; j <= 2; ++j) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + i, posY - 1, posZ + j)).getBlock();
                    if (bid == ChaosPersists.CrystalGrass) continue;
                    return false;
                }
            }
            if (random.nextInt(5) != 1) {
                ChaosPersists.chaospersistsTrees.FairyTree(world, posX, posY - 1, posZ);
            } else {
                ChaosPersists.chaospersistsTrees.FairyCastleTree(world, posX, posY, posZ);
            }
            recently_placed = 50;
            break;
        }
        return true;
    }

    public boolean addRubyDungeon(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(15) != 0) {
            return false;
        }
        for (int i = 0; i < 8; ++i) {
            int posX = chunkX + random.nextInt(8);
            int posZ = chunkZ + random.nextInt(8);
            for (int posY = 50; posY > 5; --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.LAVA) continue;
                ChaosPersists.RubyDungeon.makeDungeon(world, posX, posY, posZ);
                return true;
            }
        }
        return false;
    }

    public boolean addGenericDungeon(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(16) != 0) {
            return false;
        }
        if (ChaosPersists.LessLag == 1 && random.nextInt(2) != 0) {
            return false;
        }
        if (ChaosPersists.LessLag == 2 && random.nextInt(4) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(4);
        int posZ = chunkZ + random.nextInt(4);
        int posY = 5 + random.nextInt(40);
        ChaosPersists.MyDungeon.makeDungeon(world, posX, posY, posZ);
        return true;
    }

    public boolean addBeeHive(World world, Random random, int chunkX, int chunkZ) {
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    if (posY >= lowestY) continue block1;
                    lowestY = posY;
                    lowestX = posX;
                    lowestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && lowestY > 40) {
            ChaosPersists.MyDungeon.makeBeeHive(world, lowestX, lowestY + 3, lowestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addAlienWTF(World world, Random random, int chunkX, int chunkZ) {
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    if (posY >= lowestY) continue block1;
                    lowestY = posY;
                    lowestX = posX;
                    lowestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && lowestY > 40) {
            ChaosPersists.MyDungeon.makeAlienWTFDungeon(world, lowestX, lowestY, lowestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addEnderKnight(World world, Random random, int chunkX, int chunkZ) {
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    if (posY >= lowestY) continue block1;
                    lowestY = posY;
                    lowestX = posX;
                    lowestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && lowestY > 40) {
            ChaosPersists.MyDungeon.makeEnderKnightDungeon(world, lowestX, lowestY, lowestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addLeonNest(World world, Random random, int chunkX, int chunkZ) {
        int highestY = 30;
        int highestX = chunkX;
        int highestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 80; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    if (posY <= highestY) continue block1;
                    highestY = posY + 1;
                    highestX = posX;
                    highestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && highestY > 80) {
            ChaosPersists.MyDungeon.makeLeonNest(world, highestX, highestY, highestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addShadowDungeon(World world, Random random, int chunkX, int chunkZ) {
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    if (posY >= lowestY) continue block1;
                    lowestY = posY;
                    lowestX = posX;
                    lowestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && lowestY > 40) {
            ChaosPersists.MyDungeon.makeShadowDungeon(world, lowestX, lowestY, lowestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4RubyDungeon(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            ChaosPersists.RubyDungeon.makeDungeon(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4CephadromeAltar(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            ChaosPersists.MyDungeon.makeCephadromeAltar(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4Castle(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -20; x < 33; ++x) {
                for (int z = -4; z < 33; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            if (random.nextInt(2) == 1) {
                ChaosPersists.MyDungeon.makeEnormousCastle(world, posX, posY, posZ);
            } else {
                ChaosPersists.MyDungeon.makeEnormousCastleQ(world, posX, posY, posZ);
            }
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4Greenhouse(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -2; x < 25; ++x) {
                for (int z = -4; z < 25; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeGreenhouseDungeon(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4NightmareRookery(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -5; x < 25; ++x) {
                for (int z = -4; z < 5; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeNightmareRookery(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4StinkyHouse(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -8; x < 20; ++x) {
                for (int z = -8; z < 20; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeStinkyHouse(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4WhiteHouse(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -20; x < 30; ++x) {
                for (int z = -20; z < 300; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeWhiteHouse(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4EnderCastle(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -5; x < 25; ++x) {
                for (int z = -5; z < 25; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeEnderCastle(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4IncaPyramid(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -10; x < 50; ++x) {
                for (int z = -10; z < 40; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 18, posZ + z)).getBlock();
                    if (bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeIncaPyramid(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4RobotLab(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            for (int x = -5; x < 60; ++x) {
                for (int z = -5; z < 70; ++z) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + x, posY + 4, posZ + z)).getBlock();
                    if (bid == Blocks.AIR || bid == Blocks.OAK_LOG || bid == ChaosPersists.MyAppleLeaves || bid == ChaosPersists.MyScaryLeaves || bid == Blocks.AIR) continue;
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeRobotLab(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4Mini(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            ChaosPersists.MyDungeon.makeMiniDungeon(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addPumpkin(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(2) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            ChaosPersists.MyDungeon.makePumpkin(world, posX, posY + 1, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4CloudShark(World world, Random random, int chunkX, int chunkZ) {
        int posX = 4 + chunkX + random.nextInt(8);
        int posZ = 4 + chunkZ + random.nextInt(8);
        ChaosPersists.MyDungeon.makeCloudSharkDungeon(world, posX, 150 + world.random.nextInt(10), posZ);
        return true;
    }

    public boolean addD4Rainbow(World world, Random random, int chunkX, int chunkZ) {
        int posX = 4 + chunkX + random.nextInt(8);
        int posZ = 4 + chunkZ + random.nextInt(8);
        ChaosPersists.MyDungeon.makeRainbow(world, posX, 70 + world.random.nextInt(20), posZ);
        recently_placed = 50;
        return true;
    }

    public boolean addD4GenericDungeon(World world, Random random, int chunkX, int chunkZ) {
        if (ChaosPersists.LessLag != 0 && random.nextInt(4) != 0) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock();
            if (bid != Blocks.GRASS_BLOCK) continue;
            ChaosPersists.MyDungeon.makeDungeon(world, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public void addLavaAndWater(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(5) != 0) {
            return;
        }
        block0 : for (int i = 0; i < 6; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 128; posY > 75 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 2, posZ)).getBlock();
                if (bid != Blocks.DIRT && bid != Blocks.STONE) continue block0;
                int air = 0;
                int non_air = 0;
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + 1, posY - 1, posZ)).getBlock();
                if (bid == Blocks.AIR) {
                    ++air;
                }
                if (bid == Blocks.DIRT || bid == Blocks.STONE || bid == Blocks.GRASS_BLOCK) {
                    ++non_air;
                }
                if ((bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX - 1, posY - 1, posZ)).getBlock()) == Blocks.AIR) {
                    ++air;
                }
                if (bid == Blocks.DIRT || bid == Blocks.STONE || bid == Blocks.GRASS_BLOCK) {
                    ++non_air;
                }
                if ((bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ + 1)).getBlock()) == Blocks.AIR) {
                    ++air;
                }
                if (bid == Blocks.DIRT || bid == Blocks.STONE || bid == Blocks.GRASS_BLOCK) {
                    ++non_air;
                }
                if ((bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ - 1)).getBlock()) == Blocks.AIR) {
                    ++air;
                }
                if (bid == Blocks.DIRT || bid == Blocks.STONE || bid == Blocks.GRASS_BLOCK) {
                    ++non_air;
                }
                if (air == 0 || non_air == 0) continue block0;
                int what = random.nextInt(2);
                if (what == 0) {
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)Blocks.WATER, (int)0, (int)3);
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)Blocks.WATER, (int)0, (int)3);
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 2), (int)posZ, (Block)Blocks.WATER, (int)0, (int)3);
                } else {
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)posY, (int)posZ, (Block)Blocks.LAVA, (int)0, (int)3);
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 1), (int)posZ, (Block)Blocks.LAVA, (int)0, (int)3);
                    ChaosPersists.setBlockFast((World)world, (int)posX, (int)(posY - 2), (int)posZ, (Block)Blocks.LAVA, (int)0, (int)3);
                }
                return;
            }
        }
    }

    public boolean addOtherTrees(World world, Random random, int chunkX, int chunkZ) {
        int nc = 5;
        int count = 0;
        if (random.nextInt(30) != 0) {
            return false;
        }
        if (ChaosPersists.LessLag == 1) {
            if (random.nextInt(2) != 0) {
                return false;
            }
            nc = 4;
        }
        if (ChaosPersists.LessLag == 2) {
            if (random.nextInt(4) != 0) {
                return false;
            }
            nc = 3;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension()) {
            int dir = 0;
            int what = random.nextInt(2);
            block0 : for (int i = 0; i < nc; ++i) {
                int posX = 3 + chunkX + random.nextInt(10);
                int posZ = 3 + chunkZ + random.nextInt(10);
                for (int posY = 100; posY > 50 && world.isEmptyBlock(new BlockPos(posX, posY, posZ)); --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                    ++count;
                    if (what == 0) {
                        ChaosPersists.chaospersistsTrees.WindTree(world, posX, posY - 1, posZ, dir);
                        if (count < 4) continue block0;
                        return true;
                    }
                    ChaosPersists.chaospersistsTrees.SkyTree(world, posX, posY - 1, posZ);
                    if (count < 3) continue block0;
                    return true;
                }
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    public boolean addKingAltar(World world, Random random, int chunkX, int chunkZ) {
        if (random.nextInt(2000) != 1) {
            return false;
        }
        for (int i = 0; i < 8; ++i) {
            int posX = 3 + chunkX + random.nextInt(10);
            int posZ = 3 + chunkZ + random.nextInt(10);
            for (int posY = 100; posY > 50; --posY) {
                if (!world.isEmptyBlock(new BlockPos(posX, posY, posZ)) || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY - 1, posZ)).getBlock() != Blocks.GRASS_BLOCK) continue;
                if (!this.quickReallyBigSpaceCheck(world, posX, posY - 1, posZ)) {
                    return false;
                }
                if (random.nextInt(2) == 0) {
                    ChaosPersists.MyDungeon.makeKingAltar(world, posX, posY - 1, posZ);
                } else {
                    ChaosPersists.MyDungeon.makeQueenAltar(world, posX, posY - 1, posZ);
                }
                recently_placed = 100;
                return true;
            }
        }
        return false;
    }

    public void addBasiliskMaze(World world, Random random, int chunkX, int chunkZ) {
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() == Blocks.AIR) continue;
                    if (posY >= lowestY) continue block1;
                    lowestY = posY;
                    lowestX = posX;
                    lowestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && lowestY > 40) {
            ChaosPersists.BMaze.buildBasiliskMaze(world, lowestX, lowestY - 2, lowestZ);
            recently_placed = 50;
        }
    }

    public void addKyuubiDungeon(World world, Random random, int chunkX, int chunkZ) {
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY + 1, posZ)).getBlock() != Blocks.AIR || world.getBlockState(new net.minecraft.util.math.BlockPos(posX, posY, posZ)).getBlock() == Blocks.AIR) continue;
                    if (posY >= lowestY) continue block1;
                    lowestY = posY;
                    lowestX = posX;
                    lowestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found && lowestY > 40) {
            ChaosPersists.MyDungeon.makeKyuubiDungeon(world, lowestX, lowestY - 2, lowestZ);
            recently_placed = 50;
        }
    }

    /**
     * Forces generation/loading of every chunk touched by large multi-chunk structures so
     * later terrain generation does not overwrite pre-placed blocks.
     */
    private void ensureChunksGenerated(World world, int minX, int minZ, int maxX, int maxZ) {
        if (world == null) {
            return;
        }
        int minChunkX = minX >> 4;
        int maxChunkX = maxX >> 4;
        int minChunkZ = minZ >> 4;
        int maxChunkZ = maxZ >> 4;
        for (int cx = minChunkX; cx <= maxChunkX; ++cx) {
            for (int cz = minChunkZ; cz <= maxChunkZ; ++cz) {
                world.getChunk(new BlockPos((cx << 4) + 8, 0, (cz << 4) + 8));
            }
        }
    }

    private boolean quickSpaceCheck(World world, int posX, int posY, int posZ) {
        for (int i = -2; i < 10; ++i) {
            for (int k = -2; k < 10; ++k) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX + i, posY + 4, posZ + k)).getBlock() == Blocks.AIR) continue;
                return false;
            }
        }
        return true;
    }

    private boolean quickBigSpaceCheck(World world, int posX, int posY, int posZ) {
        for (int i = -5; i < 25; ++i) {
            for (int k = -5; k < 25; ++k) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX + i, posY + 8, posZ + k)).getBlock() == Blocks.AIR) continue;
                return false;
            }
        }
        return true;
    }

    private boolean quickReallyBigSpaceCheck(World world, int posX, int posY, int posZ) {
        for (int i = -5; i < 55; ++i) {
            for (int k = -5; k < 55; ++k) {
                if (world.getBlockState(new net.minecraft.util.math.BlockPos(posX + i, posY + 8, posZ + k)).getBlock() == Blocks.AIR) continue;
                return false;
            }
        }
        return true;
    }

    private boolean D4BigSpaceCheck(World world, int posX, int posY, int posZ) {
        for (int i = -25; i < 40; ++i) {
            for (int k = -25; k < 30; ++k) {
                Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(posX + i, posY + 4, posZ + k)).getBlock();
                if (bid == Blocks.AIR || bid == Blocks.OAK_LOG || bid == ChaosPersists.MyAppleLeaves || bid == ChaosPersists.MyScaryLeaves) continue;
                return false;
            }
        }
        return true;
    }

    private Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.util.ResourceLocation requested;
        if (par1 != null && par1.contains(":")) {
            String[] parts = par1.split(":", 2);
            requested = new net.minecraft.util.ResourceLocation(parts[0], parts[1]);
        } else {
            String normalizedPath = (par1 == null ? "" : par1.trim().toLowerCase(Locale.ROOT).replace(' ', '_'));
            requested = new net.minecraft.util.ResourceLocation("chaospersists", normalizedPath);
        }
        requested = SpawnerFixHelper.normalizeEntityLookupId(requested);
        net.minecraft.entity.EntityType<?> spawnType = net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(requested);
        var8 = spawnType != null ? spawnType.create((World)par0World) : null;
        if (var8 != null) {
            if (par2 > 0.0) {
                par2 += 0.5;
            }
            if (par2 < 0.0) {
                par2 -= 0.5;
            }
            if (par6 > 0.0) {
                par6 += 0.5;
            }
            if (par6 < 0.0) {
                par6 -= 0.5;
            }
            var8.moveTo(par2, par4 + 0.01, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity)var8);
        }
        return var8;
    }

    /** Port of 1.12.2 {@code WorldGenMinable} — preserves {@code new WorldGenMinable(...).generate(world, random, pos)} call sites. */
    private static final class WorldGenMinable {
        private final BlockState oreState;
        private final int veinSize;
        private final Predicate<BlockState> canPlaceOn;

        WorldGenMinable(BlockState state, int size) {
            this(state, size, BlockMatcher.forBlock(Blocks.STONE));
        }

        WorldGenMinable(BlockState state, int size, Predicate<BlockState> canPlaceOn) {
            this.oreState = state;
            this.veinSize = size;
            this.canPlaceOn = canPlaceOn;
        }

        boolean generate(World world, Random rand, BlockPos pos) {
            float angle = rand.nextFloat() * (float)Math.PI;
            double spread = (double)((float)(pos.getX() + 8) + MathHelper.sin(angle) * (float)this.veinSize / 8.0F);
            double spreadZ = (double)((float)(pos.getZ() + 8) + MathHelper.cos(angle) * (float)this.veinSize / 8.0F);
            double yMid = (double)(pos.getY() + rand.nextInt(3) - 2);
            double dx = (double)((float)(pos.getX() + 8) + MathHelper.sin(angle + 1.5707964F) * (float)this.veinSize / 8.0F);
            double dz = (double)((float)(pos.getZ() + 8) + MathHelper.cos(angle + 1.5707964F) * (float)this.veinSize / 8.0F);
            double xStep = (spread - dx) / (double)this.veinSize;
            double yStep = (yMid - yMid) / (double)this.veinSize;
            double zStep = (spreadZ - dz) / (double)this.veinSize;
            double x = spread;
            double y = yMid;
            double z = spreadZ;

            for (int i = 0; i < this.veinSize; ++i) {
                BlockPos placePos = new BlockPos(x, y, z);
                if (this.canPlaceOn.test(world.getBlockState(placePos))) {
                    world.setBlock(placePos, this.oreState, 2);
                }
                x += xStep;
                y += yStep;
                z += zStep;
            }
            return true;
        }
    }

    /** Port of 1.12.2 {@code BlockMatcher.forBlock}. */
    private static final class BlockMatcher {
        static Predicate<BlockState> forBlock(Block block) {
            return state -> state.getBlock() == block;
        }
    }
}

