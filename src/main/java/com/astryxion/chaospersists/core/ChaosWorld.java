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
 *  com.astryxion.chaospersists.compat.minecraft.block.Block
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockChest
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockGrass
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockLeaves
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockLiquid
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockSand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  com.astryxion.chaospersists.compat.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.WeightedRandomChestContent
 *  com.astryxion.chaospersists.compat.minecraft.world.World
 *  com.astryxion.chaospersists.compat.minecraft.world.WorldProvider
 *  com.astryxion.chaospersists.compat.minecraft.world.biome.Biome
 *  com.astryxion.chaospersists.compat.minecraft.world.chunk.Chunk
 *  com.astryxion.chaospersists.compat.minecraft.world.chunk.IChunkProvider
 *  com.astryxion.chaospersists.compat.minecraft.world.gen.feature.WorldGenMinable
 */
package com.astryxion.chaospersists.core;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.world.biome.BiomeMiningDimension;
import com.astryxion.chaospersists.world.dimension.CrystalChunkDecorator;

import com.astryxion.chaospersists.world.dimension.structure.ChaosLocateStructures;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChaosWorld {
    private static final Logger LOGGER = LogManager.getLogger();
    public static int recently_placed = 50;
    private static final boolean DEBUG_NATURAL_DUPLICATOR_SPAWNS = true;

    private record PendingChunkGen(ResourceKey<Level> dimension, int chunkX, int chunkZ) {}

    /** Chunks that loaded before the server main loop; processed on {@link ServerStartedEvent}. */
    private static final Set<PendingChunkGen> PENDING_CHUNK_GEN = ConcurrentHashMap.newKeySet();

    /** Chunks already queued or pending — prevents duplicate plant/ore passes per chunk. */
    private static final Set<PendingChunkGen> SCHEDULED_CHUNK_GEN = ConcurrentHashMap.newKeySet();

    /** Chunks that finished {@link #runChunkWorldGen} (1.12 runs populate decoration once per chunk). */
    private static final Set<PendingChunkGen> PROCESSED_WORLD_GEN = ConcurrentHashMap.newKeySet();

    /** Deferred world-gen: legacy Java structures cannot run inside {@link net.minecraft.world.level.WorldGenLevel} without chunk-load deadlocks. */
    private static final Queue<PendingChunkGen> CHUNK_GEN_QUEUE = new ConcurrentLinkedQueue<>();

    /** Mod-dimension chunks near a player — decorated first so dimensions are playable immediately. */
    private static final Queue<PendingChunkGen> MOD_NEAR_CHUNK_GEN_QUEUE =
            new ConcurrentLinkedQueue<>();

    /** Parked deferred chunks with no nearby player — must not spin on the hot queue. */
    private static final Queue<PendingChunkGen> IDLE_CHUNK_GEN_QUEUE =
            new ConcurrentLinkedQueue<>();

    /** Dimensions that rely on deferred {@link #runChunkWorldGen} instead of vanilla chunk decoration. */
    private static final Set<ResourceKey<Level>> DEFERRED_POPULATE_DIMENSIONS =
            Set.of(
                    ChaosPersists.getUtopiaDimensionKey(),
                    ChaosPersists.getMiningDimensionKey(),
                    ChaosPersists.getVillageDimensionKey(),
                    ChaosPersists.getDangerDimensionKey(),
                    ChaosPersists.getCrystalDimensionKey(),
                    ChaosPersists.getChaosDimensionKey());

    /** Chunks captured from {@link ChunkEvent.Load} to avoid re-entrant {@link Level#getChunk} deadlocks. */
    private static final Map<PendingChunkGen, LevelChunk> CHUNK_GEN_CHUNKS =
            new java.util.concurrent.ConcurrentHashMap<>();

    private static final int MAX_CHUNK_GEN_PER_TICK = 8;

    /** Utopia/danger/crystal populate is heavy (giant trees, islands, crystal structures). */
    private static final int MAX_HEAVY_CHUNK_GEN_PER_TICK = 4;

    /** Mining/village decoration includes ore passes and dungeons. */
    private static final int MAX_MEDIUM_CHUNK_GEN_PER_TICK = 6;

    /** Chebyshev chunk radius around each player for priority decoration in mod dimensions. */
    private static final int NEAR_PLAYER_CHUNK_RADIUS = 10;

    /** Max time spent decorating per server tick — keep low so leaving a mod dim cannot starve overworld. */
    private static final long CHUNK_GEN_TICK_BUDGET_NS = 40_000_000L;

    /** When parking idle work, stop scanning the hot queue after this many consecutive parks. */
    private static final int MAX_IDLE_PARK_SCAN_PER_TICK = 64;

    /** Reserved for future worldgen-safe block writes; deferred path is active today. */
    static final ThreadLocal<Boolean> DURING_POPULATE_FEATURE = ThreadLocal.withInitial(() -> Boolean.FALSE);

    /**
     * True while {@link #prepareStructureFootprint} is synchronously flushing neighbor chunks.
     * Structure placement must be skipped during that flush or each prep pass spawns cascades of dungeons.
     */
    private static final ThreadLocal<Boolean> FLUSHING_FOR_STRUCTURE = ThreadLocal.withInitial(() -> Boolean.FALSE);

    public static boolean isDuringPopulateFeature() {
        return Boolean.TRUE.equals(DURING_POPULATE_FEATURE.get());
    }

    public static boolean isFlushingForStructure() {
        return Boolean.TRUE.equals(FLUSHING_FOR_STRUCTURE.get());
    }

    private boolean shouldPlaceStructures() {
        return !isFlushingForStructure();
    }

    private static boolean locateSlot(
            Level level, int blockX, int blockZ, ResourceKey<net.minecraft.world.level.levelgen.structure.Structure> key) {
        return ChaosLocateStructures.isBlockChunk(level, blockX, blockZ, key);
    }

    /** 1.7 used 2D chunk biomes; sample the surface so locate and Java agree in 1.18+ 3D biomes. */
    private static Holder<Biome> chunkSurfaceBiome(Level level, int chunkOriginX, int chunkOriginZ) {
        int x = chunkOriginX + 8;
        int z = chunkOriginZ + 8;
        int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
        if (y < level.getMinBuildHeight()) {
            y = level.getMinBuildHeight();
        } else if (y >= level.getMaxBuildHeight()) {
            y = level.getMaxBuildHeight() - 1;
        }
        return level.getBiome(new BlockPos(x, y, z));
    }

    /** Top matching block at this column, including under trees. */
    private static int findSurfaceBlockY(
            Level level, int posX, int posZ, java.util.function.Predicate<BlockState> ground) {
        int top = level.getHeight(Heightmap.Types.WORLD_SURFACE, posX, posZ);
        int min = Math.max(level.getMinBuildHeight() + 1, 40);
        for (int y = top; y >= min; --y) {
            if (ground.test(level.getBlockState(new BlockPos(posX, y, posZ)))) {
                return y;
            }
        }
        return -1;
    }

    public void generate(
            Random random,
            int chunkX,
            int chunkZ,
            Level level,
            LevelChunk levelChunk,
            Object chunkGenerator,
            Object chunkProvider) {
        if (level.isClientSide()) {
            return;
        }
        LevelChunk chunk =
                levelChunk != null ? levelChunk : level.getChunk(chunkX, chunkZ);
        if (recently_placed > 0) {
            --recently_placed;
        }
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())) {
            this.generateSurface(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            if (this.shouldPlaceStructures()) {
                boolean altarChunk =
                        locateSlot(
                                        level,
                                        chunkX * 16,
                                        chunkZ * 16,
                                        ChaosLocateStructures.KING_ALTAR)
                                || locateSlot(
                                        level,
                                        chunkX * 16,
                                        chunkZ * 16,
                                        ChaosLocateStructures.QUEEN_ALTAR);
                if (altarChunk) {
                    this.addKingAltar(
                            level,
                            net.minecraft.util.RandomSource.create(random.nextLong()),
                            chunkX * 16,
                            chunkZ * 16);
                } else if (!this.addHugeTree(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16,
                        chunk)) {
                    if (!this.addAppleTrees(
                            level,
                            net.minecraft.util.RandomSource.create(random.nextLong()),
                            chunkX * 16,
                            chunkZ * 16,
                            chunk)) {
                        this.addOtherTrees(
                                level,
                                net.minecraft.util.RandomSource.create(random.nextLong()),
                                chunkX * 16,
                                chunkZ * 16);
                    }
                    this.addVeggies(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()), chunkX * 16, chunkZ * 16);
                }
                boolean rbd = false;
                rbd = this.addRubyDungeon(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                if (!rbd) {
                    this.addGenericDungeon(
                            level,
                            net.minecraft.util.RandomSource.create(random.nextLong()),
                            chunkX * 16,
                            chunkZ * 16);
                }
            }
            this.tryPlaceWaterLake(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16,
                    chunk,
                    16, // Utopia: uncommon ponds (1.7 relied more on terrain valleys)
                    false);
            return;
        }
        if (level.dimension().equals(ChaosPersists.getMiningDimensionKey())) {
            int i;
            int baseX = chunkX * 16;
            int baseZ = chunkZ * 16;
            net.minecraft.util.RandomSource chunkOreRandom =
                    net.minecraft.util.RandomSource.create(random.nextLong());
            ChaosPersists.Chunker.generateOresInChunk(level, chunkOreRandom, baseX, baseZ, chunk);
            if (ChaosPersists.LessOre == 0) {
                ChaosPersists.Chunker.generateOresInChunk(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        baseX,
                        baseZ,
                        chunk);
                ChaosPersists.Chunker.generateOresInChunk(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        baseX,
                        baseZ,
                        chunk);
            }
            this.generateRuby(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            if (ChaosPersists.LessOre == 0) {
                int randPosY;
                int randPosX;
                int randPosZ;
                net.minecraft.util.RandomSource oreRandom =
                        net.minecraft.util.RandomSource.create(random.nextLong());
                this.generateRuby(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                this.generateRuby(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                for (i = 0; i < 45; ++i) {
                    randPosX = chunkX * 16 + random.nextInt(16);
                    randPosY = random.nextInt(128);
                    randPosZ = chunkZ * 16 + random.nextInt(16);
                    if (randPosY >= 50) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level, oreRandom, randPosX, randPosY, randPosZ, chunk, Blocks.LAPIS_ORE, 7);
                }
                for (i = 0; i < 25; ++i) {
                    randPosX = chunkX * 16 + random.nextInt(16);
                    randPosY = random.nextInt(128);
                    randPosZ = chunkZ * 16 + random.nextInt(16);
                    if (randPosY >= 50) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level, oreRandom, randPosX, randPosY, randPosZ, chunk, Blocks.LAPIS_ORE, 4);
                }
            }
            if (this.shouldPlaceStructures()) {
                this.addBasiliskMaze(level, chunkX * 16, chunkZ * 16);
                this.addKyuubiDungeon(level, chunkX * 16, chunkZ * 16);
                this.addBeeHive(level, chunkX * 16, chunkZ * 16);
                this.addShadowDungeon(level, chunkX * 16, chunkZ * 16);
                this.addAlienWTF(level, chunkX * 16, chunkZ * 16);
                this.addEnderKnight(level, chunkX * 16, chunkZ * 16);
                this.addLeonNest(level, chunkX * 16, chunkZ * 16);
                this.addGenericDungeon(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
            }
            this.addLavaAndWater(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.addAnts(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16,
                    2);
            this.addAnts(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16,
                    2);
            this.addMosquitos(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.addMosquitos(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.addVeggies(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()), chunkX * 16, chunkZ * 16);
            this.addRocks(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            return;
        }
        if (level.dimension().equals(ChaosPersists.getDimensionKey(3))) {
            if (ChaosPersists.MosquitoEnable != 0) {
                this.addMosquitos(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX,
                        chunkZ);
            }
            this.addAnts(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16,
                    4);
            if (this.shouldPlaceStructures()) {
                this.addAppleTrees(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16,
                        chunk);
                this.addGenericDungeon(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                this.addDamselInDistress(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                this.addSpiderHangout(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                this.addRedAntHangout(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
            }
            this.tryPlaceWaterLake(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16,
                    chunk,
                    16, // uncommon extra WorldGenLakes; big water comes from valley sea-level flood
                    false);
            return;
        }
        if (level.dimension().equals(ChaosPersists.getDangerDimensionKey())) {
            if (this.shouldPlaceStructures()) {
                RandomSource d4Random = net.minecraft.util.RandomSource.create(random.nextLong());
                this.addD4Castle(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4GenericDungeon(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4EnderCastle(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4IncaPyramid(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4RobotLab(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4Mini(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4RubyDungeon(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4CephadromeAltar(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4Greenhouse(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4NightmareRookery(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4StinkyHouse(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4WhiteHouse(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addPumpkin(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4Rainbow(level, d4Random, chunkX * 16, chunkZ * 16);
                this.addD4CloudShark(level, d4Random, chunkX * 16, chunkZ * 16);
            }
            this.addUnstableAnts(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.addIslands(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.addD4Rocks(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.addScragglyTrees(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            return;
        }
        if (level.dimension().equals(ChaosPersists.getCrystalDimensionKey())) {
            RandomSource crystalRandom = net.minecraft.util.RandomSource.create(random.nextLong());
            CrystalChunkDecorator.decorate(level, crystalRandom, chunkX * 16, chunkZ * 16, chunk);
            if (!this.addFairyTree(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16)) {
                this.addCrystalTermites(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                this.addRotatorStation(level, net.minecraft.util.RandomSource.create(random.nextLong()), chunkX * 16, chunkZ * 16);
                this.addUrchinSpawner(level, net.minecraft.util.RandomSource.create(random.nextLong()), chunkX * 16, chunkZ * 16);
                this.addCrystalHauntedHouse(level, net.minecraft.util.RandomSource.create(random.nextLong()), chunkX * 16, chunkZ * 16);
                this.addRoundRotator(level, net.minecraft.util.RandomSource.create(random.nextLong()), chunkX * 16, chunkZ * 16);
                this.addCrystalBattleTower(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
                this.addIrukandji(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
            }
            this.addCrystalChestsAndSpawners(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            if (level.getRandom().nextInt(4) == 1) {
                this.addRocks(
                        level,
                        net.minecraft.util.RandomSource.create(random.nextLong()),
                        chunkX * 16,
                        chunkZ * 16);
            }
            return;
        }
        if (level.dimension().equals(ChaosPersists.getChaosDimensionKey())) {
            int baseX = chunkX * 16;
            int baseZ = chunkZ * 16;
            ChaosPersists.Chunker.generateOresInChunk(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    baseX,
                    baseZ,
                    chunk);
            // 1.7 ChunkProviderOreSpawn6: scraggly in provideChunk + biome decorate in populate
            this.addChaosScragglyTrees(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    baseX,
                    baseZ);
            this.addChaosSurfaceVegetation(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    baseX,
                    baseZ);
            this.addButterfliesAndMoths(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    baseX,
                    baseZ);
            this.addVeggies(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    baseX,
                    baseZ);
            this.addAnts(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    baseX,
                    baseZ,
                    2);
            return;
        }
        if (level.dimension() == net.minecraft.world.level.Level.NETHER) {
            this.generateNether(level, random, chunkX * 16, chunkZ * 16, chunk);
        } else if (level.dimension() == net.minecraft.world.level.Level.OVERWORLD) {
            this.generateSurface(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
            this.generateOres(level, random, chunkX * 16, chunkZ * 16, chunk);
        } else if (level.dimension() == net.minecraft.world.level.Level.END) {
            this.generateEnd(
                    level,
                    net.minecraft.util.RandomSource.create(random.nextLong()),
                    chunkX * 16,
                    chunkZ * 16);
        }
    }

    private void generateEnd(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        this.addEndAnts(level, random, chunkX, chunkZ);
        this.addEndKnights(level, random, chunkX, chunkZ);
        this.addEndReapers(level, random, chunkX, chunkZ);
        this.addHospital(level, random, chunkX, chunkZ);
        this.addEnderCastle(level, random, chunkX, chunkZ);
    }

    private void generateNether(
            net.minecraft.world.level.Level level,
            Random random,
            int chunkX,
            int chunkZ,
            LevelChunk providedChunk) {
        net.minecraft.world.level.chunk.LevelChunk levelChunk =
                providedChunk != null
                        ? providedChunk
                        : (net.minecraft.world.level.chunk.LevelChunk)
                                level.getChunk(chunkX / 16, chunkZ / 16);
        net.minecraft.util.RandomSource oreRandom =
                net.minecraft.util.RandomSource.create(random.nextLong());
        int i;
        int randPosY;
        int randPosZ;
        int randPosX;
        if (ChaosPersists.MosquitoEnable != 0) {
            this.addNetherMosquitos(
                    level, net.minecraft.util.RandomSource.create(random.nextLong()), chunkX, chunkZ);
        }
        this.addNetherAnts(level, net.minecraft.util.RandomSource.create(random.nextLong()), chunkX, chunkZ);
        int patchy = 15 + random.nextInt(10);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 3;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(13);
            randPosY = random.nextInt(108) + 10;
            randPosZ = 3 + chunkZ + random.nextInt(13);
            ChaosPersists.Chunker.generateBlockOre(
                    level,
                    oreRandom,
                    randPosX,
                    randPosY,
                    randPosZ,
                    levelChunk,
                    ChaosPersists.MyLavafoamBlock,
                    6,
                    Blocks.NETHERRACK);
        }
        patchy = 5 + random.nextInt(5);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 3;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(13);
            randPosY = random.nextInt(108) + 10;
            randPosZ = 3 + chunkZ + random.nextInt(13);
            ChaosPersists.Chunker.generateBlockOre(
                    level,
                    oreRandom,
                    randPosX,
                    randPosY,
                    randPosZ,
                    levelChunk,
                    ChaosPersists.MyOreRubyBlock,
                    2,
                    Blocks.NETHERRACK);
        }
    }

    /**
     * Apply {@code forge/biome_modifier/*_mining.json} spawns only in the mining dimension (literal windswept hills).
     */
    @SubscribeEvent
    public void onDimensionPotentialSpawns(LevelEvent.PotentialSpawns event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }
        com.astryxion.chaospersists.world.biome.DimensionSpawnPopulation.onDimensionPotentialSpawns(
                event, serverLevel);
    }

    /**
     * 1.20.1 replacement for {@code GameRegistry.registerWorldGenerator(chaospersistsGen, 10)}.
     * Never calls {@link Level#getChunk} from inside {@link ChunkEvent.Load} (re-entrant load deadlock).
     * {@link net.minecraft.server.MinecraftServer#isRunning()} is true during {@code prepareLevels}
     * (spawn-prep at 0%), so use {@link net.minecraft.server.MinecraftServer#isReady()} and flush on
     * {@link ServerStartedEvent} instead.
     */
    @SubscribeEvent
    public void onChunkLoadForWorldGen(ChunkEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }
        boolean newChunk = event.isNewChunk();
        ResourceKey<Level> dimension = serverLevel.dimension();
        LevelChunk chunk = (LevelChunk) event.getChunk();
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;

        if (isDeferredPopulateDimension(dimension)) {
            if (com.astryxion.chaospersists.world.ChaosChunkDecorationData.get(serverLevel)
                    .isDecorated(dimension, chunkX, chunkZ)) {
                return;
            }
        } else if (!newChunk) {
            return;
        }

        PendingChunkGen pending = new PendingChunkGen(dimension, chunkX, chunkZ);
        if (PROCESSED_WORLD_GEN.contains(pending) || !SCHEDULED_CHUNK_GEN.add(pending)) {
            return;
        }
        CHUNK_GEN_CHUNKS.put(pending, chunk);
        if (!serverLevel.getServer().isReady()) {
            PENDING_CHUNK_GEN.add(pending);
            return;
        }
        this.queuePendingChunk(serverLevel, pending);
    }

    private static boolean isDeferredPopulateDimension(ResourceKey<Level> dimension) {
        return DEFERRED_POPULATE_DIMENSIONS.contains(dimension);
    }

    private static int getMaxChunkGenPerTick(ResourceKey<Level> dimension) {
        if (dimension.equals(ChaosPersists.getUtopiaDimensionKey())
                || dimension.equals(ChaosPersists.getDangerDimensionKey())
                || dimension.equals(ChaosPersists.getCrystalDimensionKey())) {
            return MAX_HEAVY_CHUNK_GEN_PER_TICK;
        }
        if (dimension.equals(ChaosPersists.getMiningDimensionKey())
                || dimension.equals(ChaosPersists.getVillageDimensionKey())) {
            return MAX_MEDIUM_CHUNK_GEN_PER_TICK;
        }
        if (isDeferredPopulateDimension(dimension)) {
            return MAX_CHUNK_GEN_PER_TICK;
        }
        return MAX_CHUNK_GEN_PER_TICK;
    }

    private void queuePendingChunk(ServerLevel level, PendingChunkGen pending) {
        if (isDeferredPopulateDimension(pending.dimension)
                && this.isChunkNearPlayer(level, pending.chunkX, pending.chunkZ)) {
            MOD_NEAR_CHUNK_GEN_QUEUE.add(pending);
        } else {
            CHUNK_GEN_QUEUE.add(pending);
        }
    }

    private boolean isChunkNearPlayer(ServerLevel level, int chunkX, int chunkZ) {
        // Empty dimension is NOT "near" — treating it as near kept decorating crystal/utopia after
        // the player left, burning the tick budget and making overworld entities look like slow-mo.
        if (level.players().isEmpty()) {
            return false;
        }
        for (net.minecraft.server.level.ServerPlayer player : level.players()) {
            net.minecraft.world.level.ChunkPos playerChunk = player.chunkPosition();
            if (Math.abs(playerChunk.x - chunkX) <= NEAR_PLAYER_CHUNK_RADIUS
                    && Math.abs(playerChunk.z - chunkZ) <= NEAR_PLAYER_CHUNK_RADIUS) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chunks that missed decoration are re-queued while a player is in a mod dimension.
     */
    @SubscribeEvent
    public void onPlayerTickCatchUpDeferredPopulate(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide()) {
            return;
        }
        if (!(event.player.level() instanceof ServerLevel level)) {
            return;
        }
        ResourceKey<Level> dimension = level.dimension();
        if (!isDeferredPopulateDimension(dimension)) {
            return;
        }
        if (event.player.tickCount % 20 != 0) {
            return;
        }
        int centerX = event.player.chunkPosition().x;
        int centerZ = event.player.chunkPosition().z;
        for (int dx = -NEAR_PLAYER_CHUNK_RADIUS; dx <= NEAR_PLAYER_CHUNK_RADIUS; ++dx) {
            for (int dz = -NEAR_PLAYER_CHUNK_RADIUS; dz <= NEAR_PLAYER_CHUNK_RADIUS; ++dz) {
                int chunkX = centerX + dx;
                int chunkZ = centerZ + dz;
                if (com.astryxion.chaospersists.world.ChaosChunkDecorationData.get(level)
                        .isDecorated(dimension, chunkX, chunkZ)) {
                    continue;
                }
                if (!level.hasChunk(chunkX, chunkZ)) {
                    continue;
                }
                PendingChunkGen pending = new PendingChunkGen(dimension, chunkX, chunkZ);
                if (PROCESSED_WORLD_GEN.contains(pending) || !SCHEDULED_CHUNK_GEN.add(pending)) {
                    continue;
                }
                MOD_NEAR_CHUNK_GEN_QUEUE.add(pending);
            }
        }
    }

    private static int findCrystalPlantingY(Level level, int x, int z) {
        if (ChaosPersists.CrystalGrass == null) {
            return -1;
        }
        int topY = level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
        for (int y = topY + 1; y >= level.getMinBuildHeight() + 8; --y) {
            net.minecraft.core.BlockPos feet = new net.minecraft.core.BlockPos(x, y, z);
            net.minecraft.core.BlockPos ground = feet.below();
            if (level.getBlockState(feet).isAir()
                    && level.getBlockState(ground).is(ChaosPersists.CrystalGrass)
                    && level.getFluidState(ground).isEmpty()) {
                return y;
            }
        }
        return -1;
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        for (PendingChunkGen pending : PENDING_CHUNK_GEN) {
            if (!PROCESSED_WORLD_GEN.contains(pending)) {
                ServerLevel level = event.getServer().getLevel(pending.dimension);
                if (level == null) {
                    CHUNK_GEN_QUEUE.add(pending);
                    continue;
                }
                this.queuePendingChunk(level, pending);
            }
        }
        PENDING_CHUNK_GEN.clear();
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        CHUNK_GEN_QUEUE.clear();
        MOD_NEAR_CHUNK_GEN_QUEUE.clear();
        IDLE_CHUNK_GEN_QUEUE.clear();
        PENDING_CHUNK_GEN.clear();
        SCHEDULED_CHUNK_GEN.clear();
        PROCESSED_WORLD_GEN.clear();
        CHUNK_GEN_CHUNKS.clear();
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        if (CHUNK_GEN_QUEUE.isEmpty()
                && MOD_NEAR_CHUNK_GEN_QUEUE.isEmpty()
                && IDLE_CHUNK_GEN_QUEUE.isEmpty()) {
            return;
        }
        MinecraftServer server = event.getServer();
        if (server == null || !server.isReady()) {
            return;
        }
        long deadline = System.nanoTime() + CHUNK_GEN_TICK_BUDGET_NS;
        Map<ResourceKey<Level>, Integer> dimensionProcessed = new HashMap<>();
        int parkedIdle = 0;

        while (System.nanoTime() < deadline) {
            PendingChunkGen pending = MOD_NEAR_CHUNK_GEN_QUEUE.poll();
            boolean fromNear = pending != null;
            if (pending == null) {
                pending = CHUNK_GEN_QUEUE.poll();
            }
            if (pending == null) {
                break;
            }

            ServerLevel level = server.getLevel(pending.dimension);
            if (level == null) {
                SCHEDULED_CHUNK_GEN.remove(pending);
                CHUNK_GEN_CHUNKS.remove(pending);
                continue;
            }

            ResourceKey<Level> dimension = pending.dimension;
            if (isDeferredPopulateDimension(dimension)
                    && !fromNear
                    && !this.isChunkNearPlayer(level, pending.chunkX, pending.chunkZ)) {
                IDLE_CHUNK_GEN_QUEUE.add(pending);
                ++parkedIdle;
                // Player left the dim (or never nearby): do not burn the tick reshuffling the queue.
                if (MOD_NEAR_CHUNK_GEN_QUEUE.isEmpty()
                        && parkedIdle >= MAX_IDLE_PARK_SCAN_PER_TICK) {
                    PendingChunkGen rest;
                    while ((rest = CHUNK_GEN_QUEUE.poll()) != null) {
                        IDLE_CHUNK_GEN_QUEUE.add(rest);
                    }
                    break;
                }
                continue;
            }

            int maxForDimension = getMaxChunkGenPerTick(dimension);
            int processed = dimensionProcessed.getOrDefault(dimension, 0);
            if (processed >= maxForDimension) {
                if (fromNear) {
                    MOD_NEAR_CHUNK_GEN_QUEUE.add(pending);
                } else {
                    CHUNK_GEN_QUEUE.add(pending);
                }
                break;
            }

            LevelChunk chunk = CHUNK_GEN_CHUNKS.remove(pending);
            if (chunk == null && !level.hasChunk(pending.chunkX, pending.chunkZ)) {
                SCHEDULED_CHUNK_GEN.remove(pending);
                continue;
            }
            SCHEDULED_CHUNK_GEN.remove(pending);
            if (PROCESSED_WORLD_GEN.add(pending)) {
                this.runChunkWorldGen(
                        level, chunk != null ? chunk : level.getChunk(pending.chunkX, pending.chunkZ));
                dimensionProcessed.put(dimension, processed + 1);
            }
        }

        // Promote parked work back only for dimensions that currently have players nearby.
        if (!IDLE_CHUNK_GEN_QUEUE.isEmpty()) {
            int promoteBudget = 32;
            Queue<PendingChunkGen> stillIdle = new ConcurrentLinkedQueue<>();
            PendingChunkGen idle;
            while (promoteBudget-- > 0 && (idle = IDLE_CHUNK_GEN_QUEUE.poll()) != null) {
                ServerLevel level = server.getLevel(idle.dimension);
                if (level == null) {
                    SCHEDULED_CHUNK_GEN.remove(idle);
                    CHUNK_GEN_CHUNKS.remove(idle);
                    continue;
                }
                if (!level.hasChunk(idle.chunkX, idle.chunkZ)) {
                    SCHEDULED_CHUNK_GEN.remove(idle);
                    CHUNK_GEN_CHUNKS.remove(idle);
                    continue;
                }
                if (this.isChunkNearPlayer(level, idle.chunkX, idle.chunkZ)) {
                    MOD_NEAR_CHUNK_GEN_QUEUE.add(idle);
                } else {
                    stillIdle.add(idle);
                }
            }
            while (!stillIdle.isEmpty()) {
                IDLE_CHUNK_GEN_QUEUE.add(stillIdle.poll());
            }
            // Leftover idle entries stay parked until a player is near them again.
        }
    }

    private void runChunkWorldGen(ServerLevel serverLevel, LevelChunk chunk) {
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        Random random = new Random();
        random.setSeed(serverLevel.getSeed());
        random.setSeed(random.nextLong() ^ ((long) chunkX << 16) ^ (long) chunkZ);
        ChaosPersists.beginPopulateLightCollection();
        try {
            this.generate(random, chunkX, chunkZ, serverLevel, chunk, null, null);
            com.astryxion.chaospersists.world.biome.DimensionSpawnPopulation.afterChunkDecoration(
                    serverLevel, chunk);
            ResourceKey<Level> dimension = serverLevel.dimension();
            if (isDeferredPopulateDimension(dimension)) {
                com.astryxion.chaospersists.world.ChaosChunkDecorationData.get(serverLevel)
                        .markDecorated(dimension, chunkX, chunkZ);
            }
        } finally {
            java.util.Set<Long> touched = ChaosPersists.endPopulateLightCollection();
            if (!touched.isEmpty()) {
                ChaosPersists.queueChunkRelight(serverLevel, touched);
            }
        }
    }

    public void generateSurface(
            Level level, RandomSource random, int chunkX, int chunkZ) {
        this.addStrawberries(level, random, chunkX, chunkZ);
        this.addCorn(level, random, chunkX, chunkZ);
        this.addTomatoes(level, random, chunkX, chunkZ);
        this.addVeggies(level, random, chunkX, chunkZ);
        this.addButterfliesAndMoths(level, random, chunkX, chunkZ);
        if (ChaosPersists.MosquitoEnable != 0) {
            this.addMosquitos(level, random, chunkX, chunkZ);
        }
        if (ChaosPersists.DisableOverworldDungeons == 0
                && level.dimension() == net.minecraft.world.level.Level.OVERWORLD) {
            this.addPlayPool(level, random, chunkX, chunkZ);
            this.addWaterDragonLair(level, random, chunkX, chunkZ);
            this.addGoldFishBowl(level, random, chunkX, chunkZ);
            this.addGirlfriendIsland(level, random, chunkX, chunkZ);
            this.addMonsterIsland(level, random, chunkX, chunkZ);
            this.addFrogPond(level, random, chunkX, chunkZ);
            this.addANest(level, random, chunkX, chunkZ);
            this.addHauntedHouse(level, random, chunkX, chunkZ);
            this.addLeafMonster(level, random, chunkX, chunkZ);
            this.addSpitBug(level, random, chunkX, chunkZ);
            this.addIgloo(level, random, chunkX, chunkZ);
            this.addBouncyCastle(level, random, chunkX, chunkZ);
            this.addRubberDuckyPond(level, random, chunkX, chunkZ);
        }
        this.addAnts(level, random, chunkX, chunkZ, 4);
        Holder<Biome> biome = level.getBiome(new BlockPos(chunkX, 0, chunkZ));
        if (biome.is(Biomes.RIVER) || biome.is(Biomes.WINDSWEPT_HILLS) || biome.is(Biomes.DESERT)) {
            this.addRocks(level, random, chunkX, chunkZ);
        }
    }

    /**
     * 1.12 {@code WorldGenLakes} ponds for custom plains dimensions (vanilla removed lake features in 1.20.1).
     */
    private void tryPlaceWaterLake(
            Level level,
            RandomSource random,
            int chunkX,
            int chunkZ,
            LevelChunk chunk,
            int chunkRarity,
            boolean crystalTerrain) {
        if (random.nextInt(chunkRarity) != 0) {
            return;
        }
        // One classic attempt (plus one retry). Extra retries made Village Mania look flooded.
        for (int attempt = 0; attempt < 2; ++attempt) {
            int x = chunkX + random.nextInt(16) + 8;
            int z = chunkZ + random.nextInt(16) + 8;
            // Prefer near-surface so lakes settle into the land instead of blasting deep pits.
            int surfaceY = chunk.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x & 15, z & 15);
            int y = attempt == 0 ? surfaceY + random.nextInt(4) : random.nextInt(Math.max(8, surfaceY + 1));
            if (this.placeWaterLakeAt(level, random, new BlockPos(x, y, z), crystalTerrain)) {
                return;
            }
        }
    }

    /**
     * Faithful 1.12 {@code WorldGenLakes} (16×8×16) plus a sand/clay/gravel/dirt bed pass.
     * Classic lakes got mixed beds from biome sand/clay generators, not from the lake feature itself.
     */
    private boolean placeWaterLakeAt(
            Level level, RandomSource random, BlockPos position, boolean crystalTerrain) {
        final int horiz = 16;
        final int vert = 8;
        final int waterLayers = 4;
        int x = position.getX() - 8;
        int z = position.getZ() - 8;
        int y = position.getY();
        while (y > level.getMinBuildHeight() + 5 && level.getBlockState(new BlockPos(x, y, z)).isAir()) {
            --y;
        }
        if (y <= level.getMinBuildHeight() + 4) {
            return false;
        }
        y -= 4;

        boolean[] lake = new boolean[horiz * horiz * vert];
        int blobCount = random.nextInt(4) + 4;
        for (int blob = 0; blob < blobCount; ++blob) {
            double sizeX = random.nextDouble() * 6.0D + 3.0D;
            double sizeY = random.nextDouble() * 4.0D + 2.0D;
            double sizeZ = random.nextDouble() * 6.0D + 3.0D;
            double rangeX = Math.max(1.0D, horiz - sizeX - 2.0D);
            double rangeY = Math.max(1.0D, vert - sizeY - 4.0D);
            double rangeZ = Math.max(1.0D, horiz - sizeZ - 2.0D);
            double centerX = random.nextDouble() * rangeX + 1.0D + sizeX / 2.0D;
            double centerY = random.nextDouble() * rangeY + 2.0D + sizeY / 2.0D;
            double centerZ = random.nextDouble() * rangeZ + 1.0D + sizeZ / 2.0D;
            for (int dx = 1; dx < horiz - 1; ++dx) {
                for (int dz = 1; dz < horiz - 1; ++dz) {
                    for (int dy = 1; dy < vert - 1; ++dy) {
                        double nx = ((double) dx - centerX) / (sizeX / 2.0D);
                        double ny = ((double) dy - centerY) / (sizeY / 2.0D);
                        double nz = ((double) dz - centerZ) / (sizeZ / 2.0D);
                        if (nx * nx + ny * ny + nz * nz < 1.0D) {
                            lake[(dx * horiz + dz) * vert + dy] = true;
                        }
                    }
                }
            }
        }

        for (int dx = 0; dx < horiz; ++dx) {
            for (int dz = 0; dz < horiz; ++dz) {
                for (int dy = 0; dy < vert; ++dy) {
                    boolean edge =
                            !lake[(dx * horiz + dz) * vert + dy]
                                    && ((dx < horiz - 1 && lake[((dx + 1) * horiz + dz) * vert + dy])
                                            || (dx > 0 && lake[((dx - 1) * horiz + dz) * vert + dy])
                                            || (dz < horiz - 1 && lake[(dx * horiz + dz + 1) * vert + dy])
                                            || (dz > 0 && lake[(dx * horiz + (dz - 1)) * vert + dy])
                                            || (dy < vert - 1 && lake[(dx * horiz + dz) * vert + dy + 1])
                                            || (dy > 0 && lake[(dx * horiz + dz) * vert + (dy - 1)]));
                    if (edge) {
                        BlockPos check = new BlockPos(x + dx, y + dy, z + dz);
                        BlockState state = level.getBlockState(check);
                        if (dy >= waterLayers && !state.getFluidState().isEmpty()) {
                            return false;
                        }
                        if (dy < waterLayers
                                && !state.blocksMotion()
                                && !state.is(Blocks.WATER)) {
                            return false;
                        }
                    }
                    if (lake[(dx * horiz + dz) * vert + dy]
                            && !isLakeReplaceable(level.getBlockState(new BlockPos(x + dx, y + dy, z + dz)))) {
                        return false;
                    }
                }
            }
        }

        final int placeFlags = 3;
        BlockState water = Blocks.WATER.defaultBlockState();
        BlockState air = Blocks.AIR.defaultBlockState();
        for (int dx = 0; dx < horiz; ++dx) {
            for (int dz = 0; dz < horiz; ++dz) {
                for (int dy = 0; dy < vert; ++dy) {
                    if (lake[(dx * horiz + dz) * vert + dy]) {
                        BlockPos pos = new BlockPos(x + dx, y + dy, z + dz);
                        if (dy >= waterLayers) {
                            level.setBlock(pos, air, placeFlags);
                        } else {
                            level.setBlock(pos, water, placeFlags);
                            level.scheduleTick(pos, water.getFluidState().getType(), 0);
                        }
                    }
                }
            }
        }

        // Mixed lake beds (1.7 sand/clay/gravel disks that decorated water bodies).
        for (int dx = 0; dx < horiz; ++dx) {
            for (int dz = 0; dz < horiz; ++dz) {
                boolean columnHasWater = false;
                for (int dy = 0; dy < waterLayers; ++dy) {
                    if (lake[(dx * horiz + dz) * vert + dy]) {
                        columnHasWater = true;
                        break;
                    }
                }
                if (!columnHasWater) {
                    continue;
                }
                BlockPos floor = new BlockPos(x + dx, y, z + dz);
                // Floor is the solid under the lowest water cell in this column.
                for (int dy = 0; dy < waterLayers; ++dy) {
                    if (lake[(dx * horiz + dz) * vert + dy]) {
                        floor = new BlockPos(x + dx, y + dy - 1, z + dz);
                        break;
                    }
                }
                BlockState floorState = level.getBlockState(floor);
                if (!floorState.is(Blocks.DIRT)
                        && !floorState.is(Blocks.GRASS_BLOCK)
                        && !floorState.is(Blocks.STONE)
                        && !floorState.is(Blocks.COARSE_DIRT)
                        && !floorState.is(BlockTags.DIRT)
                        && !floorState.is(BlockTags.BASE_STONE_OVERWORLD)) {
                    continue;
                }
                int pick = random.nextInt(100);
                BlockState bed;
                if (pick < 28) {
                    bed = Blocks.SAND.defaultBlockState();
                } else if (pick < 48) {
                    bed = Blocks.GRAVEL.defaultBlockState();
                } else if (pick < 63) {
                    bed = Blocks.CLAY.defaultBlockState();
                } else {
                    bed = Blocks.DIRT.defaultBlockState();
                }
                level.setBlock(floor, bed, placeFlags);
            }
        }

        BlockState grass =
                crystalTerrain && ChaosPersists.CrystalGrass != null
                        ? ChaosPersists.CrystalGrass.defaultBlockState()
                        : Blocks.GRASS_BLOCK.defaultBlockState();
        for (int dx = 0; dx < horiz; ++dx) {
            for (int dz = 0; dz < horiz; ++dz) {
                for (int dy = waterLayers; dy < vert; ++dy) {
                    if (!lake[(dx * horiz + dz) * vert + dy]) {
                        continue;
                    }
                    BlockPos below = new BlockPos(x + dx, y + dy - 1, z + dz);
                    BlockPos here = new BlockPos(x + dx, y + dy, z + dz);
                    if (level.getBlockState(below).is(Blocks.DIRT) && level.canSeeSky(here)) {
                        level.setBlock(below, grass, placeFlags);
                    }
                }
            }
        }
        return true;
    }

    /** Terrain lakes may only replace natural ground — not village/structure materials. */
    private static boolean isLakeReplaceable(BlockState state) {
        if (state.isAir() || state.is(Blocks.WATER) || state.is(Blocks.CAVE_AIR) || state.is(Blocks.VOID_AIR)) {
            return true;
        }
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK
                || block == Blocks.DIRT
                || block == Blocks.COARSE_DIRT
                || block == Blocks.ROOTED_DIRT
                || block == Blocks.PODZOL
                || block == Blocks.MUD
                || block == Blocks.STONE
                || block == Blocks.DEEPSLATE
                || block == Blocks.GRAVEL
                || block == Blocks.SAND
                || block == Blocks.RED_SAND
                || block == Blocks.CLAY
                || block == Blocks.ANDESITE
                || block == Blocks.DIORITE
                || block == Blocks.GRANITE
                || block == Blocks.TUFF
                || state.is(BlockTags.DIRT)
                || state.is(BlockTags.BASE_STONE_OVERWORLD)
                || state.is(BlockTags.REPLACEABLE);
    }

    public void generateRuby(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.Ruby_stats.rate <= 0) {
            return;
        }
        int patchy = ChaosPersists.Ruby_stats.rate + random.nextInt(7);
        block0:
        for (int i = 0; i < patchy; ++i) {
            int randPosX = 3 + chunkX + random.nextInt(10);
            int randPosY = random.nextInt(128);
            int randPosZ = 3 + chunkZ + random.nextInt(10);
            if (randPosY <= 0
                    || randPosY > ChaosPersists.Ruby_stats.maxdepth
                    || randPosY < ChaosPersists.Ruby_stats.mindepth) {
                continue;
            }
            for (int m = randPosY; m > 0; --m) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(randPosX, m, randPosZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(randPosX, m - 1, randPosZ);
                if (!level.getBlockState(pos).is(Blocks.LAVA)
                        || !level.getBlockState(below).is(Blocks.STONE)) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level, randPosX, m - 1, randPosZ, (Block) ChaosPersists.MyOreRubyBlock, 0, 2);
                continue block0;
            }
        }
        block1:
        for (int i = 0; i < patchy; ++i) {
            int randPosX = 3 + chunkX + random.nextInt(10);
            int randPosY = -1 - random.nextInt(63); // Y -1..-63
            int randPosZ = 3 + chunkZ + random.nextInt(10);
            for (int m = randPosY; m > -64; --m) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(randPosX, m, randPosZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(randPosX, m - 1, randPosZ);
                if (!level.getBlockState(pos).is(Blocks.LAVA)
                        || !level.getBlockState(below).is(Blocks.DEEPSLATE)) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level,
                        randPosX,
                        m - 1,
                        randPosZ,
                        ChaosPersists.MyDeepslateOreRubyBlock,
                        0,
                        2);
                continue block1;
            }
        }
    }

    public void generateOres(
            net.minecraft.world.level.Level level,
            Random random,
            int chunkX,
            int chunkZ,
            net.minecraft.world.level.chunk.LevelChunk providedChunk) {
        net.minecraft.world.level.chunk.LevelChunk levelChunk =
                providedChunk != null
                        ? providedChunk
                        : (net.minecraft.world.level.chunk.LevelChunk)
                                level.getChunk(chunkX / 16, chunkZ / 16);
        net.minecraft.util.RandomSource oreRandom =
                net.minecraft.util.RandomSource.create(random.nextLong());
        int i;
        int randPosY;
        int randPosZ;
        int patchy;
        int randPosX;
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
                            b = ChaosPersists.MyAloSpawnBlock;
                            break;
                        }
                        case 6: {
                            b = ChaosPersists.MyCrabSpawnBlock;
                            break;
                        }
                    }
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            b,
                            ChaosPersists.SpawnOres_stats.clumpsize);
                    continue;
                }
                Block[] spawnOrePool = ChaosPersists.getMainSpawnOreBlocks();
                b = spawnOrePool[random.nextInt(spawnOrePool.length)];
                if (b == null) {
                    b = Blocks.AIR;
                }
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
                        b,
                        ChaosPersists.SpawnOres_stats.clumpsize);
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
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
                        ChaosPersists.MyOreUraniumBlock,
                        ChaosPersists.Uranium_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
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
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
                        ChaosPersists.MyOreTitaniumBlock,
                        ChaosPersists.Titanium_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
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
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
                        ChaosPersists.MyOreAmethystBlock,
                        ChaosPersists.Amethyst_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
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
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
                        ChaosPersists.MyOreSaltBlock,
                        ChaosPersists.Salt_stats.clumpsize,
                        Blocks.STONE);
            }
            for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -63 + random.nextInt(63); // deepslate: Y -63..-1
                randPosZ = 3 + chunkZ + random.nextInt(10);
                ChaosPersists.Chunker.generateBlockOre(
                        level,
                        oreRandom,
                        randPosX,
                        randPosY,
                        randPosZ,
                        levelChunk,
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
            randPosY = 1 + random.nextInt(50);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            ChaosPersists.Chunker.generateBlockOre(
                    level,
                    oreRandom,
                    randPosX,
                    randPosY,
                    randPosZ,
                    levelChunk,
                    ChaosPersists.RedAntTroll,
                    4,
                    Blocks.STONE);
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = -63 + random.nextInt(64);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            ChaosPersists.Chunker.generateBlockOre(
                    level,
                    oreRandom,
                    randPosX,
                    randPosY,
                    randPosZ,
                    levelChunk,
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
            ChaosPersists.Chunker.generateBlockOre(
                    level,
                    oreRandom,
                    randPosX,
                    randPosY,
                    randPosZ,
                    levelChunk,
                    ChaosPersists.TermiteTroll,
                    4,
                    Blocks.STONE);
        }
        patchy = 4 + random.nextInt(4);
        if (ChaosPersists.LessOre != 0) {
            patchy /= 2;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 3 + chunkX + random.nextInt(10);
            randPosY = -63 + random.nextInt(64);
            randPosZ = 3 + chunkZ + random.nextInt(10);
            ChaosPersists.Chunker.generateBlockOre(
                    level,
                    oreRandom,
                    randPosX,
                    randPosY,
                    randPosZ,
                    levelChunk,
                    ChaosPersists.DeepslateTermiteTroll,
                    4,
                    Blocks.DEEPSLATE);
        }
        if (ChaosPersists.Ruby_stats.rate > 0) {
            patchy = ChaosPersists.Ruby_stats.rate + random.nextInt(5);
            block116 : for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = random.nextInt(128);
                randPosZ = 3 + chunkZ + random.nextInt(10);
                if (randPosY <= 0
                        || randPosY > ChaosPersists.Ruby_stats.maxdepth
                        || randPosY < ChaosPersists.Ruby_stats.mindepth) continue;
                for (int m = randPosY; m > 0; --m) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(randPosX, m, randPosZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(randPosX, m - 1, randPosZ);
                    if (!level.getBlockState(pos).is(Blocks.LAVA)
                            || !level.getBlockState(below).is(Blocks.STONE)) {
                        continue;
                    }
                    ChaosPersists.setBlockFast(
                            level, randPosX, m - 1, randPosZ, (Block) ChaosPersists.MyOreRubyBlock, 0, 2);
                    continue block116;
                }
            }
            block117 : for (i = 0; i < patchy; ++i) {
                randPosX = 3 + chunkX + random.nextInt(10);
                randPosY = -1 - random.nextInt(63); // Y -1..-63
                randPosZ = 3 + chunkZ + random.nextInt(10);
                for (int m = randPosY; m > -64; --m) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(randPosX, m, randPosZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(randPosX, m - 1, randPosZ);
                    if (!level.getBlockState(pos).is(Blocks.LAVA)
                            || !level.getBlockState(below).is(Blocks.DEEPSLATE)) {
                        continue;
                    }
                    ChaosPersists.setBlockFast(
                            level,
                            randPosX,
                            m - 1,
                            randPosZ,
                            ChaosPersists.MyDeepslateOreRubyBlock,
                            0,
                            2);
                    continue block117;
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
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            Blocks.DIAMOND_ORE,
                            ChaosPersists.Diamond_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkDiamond_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkDiamond_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkDiamond_stats.maxdepth || randPosY < ChaosPersists.BlkDiamond_stats.mindepth) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            Blocks.DIAMOND_BLOCK,
                            ChaosPersists.BlkDiamond_stats.clumpsize);
                }
            }
            if (ChaosPersists.Emerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Emerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Emerald_stats.maxdepth || randPosY < ChaosPersists.Emerald_stats.mindepth) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            Blocks.EMERALD_ORE,
                            ChaosPersists.Emerald_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkEmerald_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkEmerald_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkEmerald_stats.maxdepth || randPosY < ChaosPersists.BlkEmerald_stats.mindepth) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            Blocks.EMERALD_BLOCK,
                            ChaosPersists.BlkEmerald_stats.clumpsize);
                }
            }
            if (ChaosPersists.Gold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.Gold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.Gold_stats.maxdepth || randPosY < ChaosPersists.Gold_stats.mindepth) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            Blocks.GOLD_ORE,
                            ChaosPersists.Gold_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkGold_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkGold_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkGold_stats.maxdepth || randPosY < ChaosPersists.BlkGold_stats.mindepth) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            Blocks.GOLD_BLOCK,
                            ChaosPersists.BlkGold_stats.clumpsize);
                }
            }
            if (ChaosPersists.BlkRuby_stats.rate > 0) {
                for (i = 0; i < ChaosPersists.BlkRuby_stats.rate; ++i) {
                    randPosX = 3 + chunkX + random.nextInt(10);
                    randPosY = random.nextInt(128);
                    randPosZ = 3 + chunkZ + random.nextInt(10);
                    if (randPosY > ChaosPersists.BlkRuby_stats.maxdepth || randPosY < ChaosPersists.BlkRuby_stats.mindepth) continue;
                    ChaosPersists.Chunker.generateBlockOre(
                            level,
                            oreRandom,
                            randPosX,
                            randPosY,
                            randPosZ,
                            levelChunk,
                            ChaosPersists.MyBlockRubyBlock,
                            ChaosPersists.BlkRuby_stats.clumpsize);
                }
            }
        }
    }

    /** OreSpawn 1.7.10 {@code addCorn}/{@code addTomatoes}: Utopia, Village Mania, or Plains. */
    private static boolean isCornTomatoChunkEligible(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                || level.dimension().equals(ChaosPersists.getVillageDimensionKey())) {
            return true;
        }
        return level.getBiome(new BlockPos(chunkX, 0, chunkZ)).is(Biomes.PLAINS);
    }

    private static boolean isStrawberryForestBiome(Holder<Biome> biome) {
        return biome.is(Biomes.FOREST)
                || biome.is(Biomes.WINDSWEPT_FOREST)
                || biome.is(Biomes.OLD_GROWTH_BIRCH_FOREST)
                || biome.is(Biomes.BIRCH_FOREST);
    }

    private static boolean canSpawnStrawberryAt(net.minecraft.world.level.Level level, int posX, int posY, int posZ) {
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())) {
            return true;
        }
        return isStrawberryForestBiome(level.getBiome(new BlockPos(posX, posY, posZ)));
    }

    private static boolean isButterflyOverworldBiome(Holder<Biome> biome) {
        return biome.is(Biomes.FOREST)
                || biome.is(Biomes.WINDSWEPT_FOREST)
                || biome.is(Biomes.RIVER)
                || biome.is(Biomes.JUNGLE)
                || biome.is(Biomes.SPARSE_JUNGLE)
                || biome.is(Biomes.SWAMP)
                || biome.is(Biomes.BIRCH_FOREST)
                || biome.is(Biomes.OLD_GROWTH_BIRCH_FOREST)
                || biome.is(Biomes.DARK_FOREST);
    }

    private static boolean isButterflyBiomeChunkGate(net.minecraft.world.level.Level level, Holder<Biome> chunkBiome) {
        return level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                || level.dimension().equals(ChaosPersists.getDimensionKey(6))
                || isButterflyOverworldBiome(chunkBiome);
    }

    private static boolean canSpawnVeggiesAt(net.minecraft.world.level.Level level, int posX, int posY, int posZ) {
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                || level.dimension().equals(ChaosPersists.getMiningDimensionKey())
                || level.dimension().equals(ChaosPersists.getDimensionKey(6))) {
            return true;
        }
        Holder<Biome> biome = level.getBiome(new BlockPos(posX, posY, posZ));
        return biome.is(Biomes.RIVER) || biome.is(Biomes.SWAMP);
    }

    public void addStrawberries(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(20) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 5; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 40; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                if (!canSpawnStrawberryAt(level, posX, posY, posZ)) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY, posZ, (Block) ChaosPersists.MyStrawberryPlant, 0, 2);
                continue block0;
            }
        }
    }

    public boolean addHauntedHouse(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.HAUNTED_HOUSE)) {
            return false;
        }
        net.minecraft.core.Holder<net.minecraft.world.level.biome.Biome> biomeHolder =
                chunkSurfaceBiome(level, chunkX, chunkZ);
        if (biomeHolder.is(net.minecraft.world.level.biome.Biomes.PLAINS)
                || biomeHolder.is(net.minecraft.world.level.biome.Biomes.TAIGA)
                || biomeHolder.is(net.minecraft.world.level.biome.Biomes.SWAMP)) {
            int posX = chunkX + 8;
            int posZ = chunkZ + 8;
            int grassY =
                    findSurfaceBlockY(
                            level,
                            posX,
                            posZ,
                            state -> state.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK));
            if (grassY < 0) {
                return false;
            }
            ChaosPersists.MyDungeon.makeHauntedHouse(level, posX, grassY + 1, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addANest(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        boolean placed = false;
        if (locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.NEST)) {
            placed |= this.placeForestHive(level, chunkX, chunkZ, true);
        }
        if (level.dimension() == net.minecraft.world.level.Level.OVERWORLD
                && locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.BEE_HIVE)) {
            placed |= this.placeForestHive(level, chunkX, chunkZ, false);
        }
        return placed;
    }

    private boolean placeForestHive(
            net.minecraft.world.level.Level level, int chunkX, int chunkZ, boolean mantis) {
        Holder<Biome> biome = chunkSurfaceBiome(level, chunkX, chunkZ);
        if (!(biome.is(Biomes.FOREST)
                || biome.is(Biomes.WINDSWEPT_FOREST)
                || biome.is(Biomes.JUNGLE)
                || biome.is(Biomes.SPARSE_JUNGLE)
                || biome.is(Biomes.BIRCH_FOREST)
                || biome.is(Biomes.OLD_GROWTH_BIRCH_FOREST))) {
            return false;
        }
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        int grassY =
                findSurfaceBlockY(
                        level,
                        posX,
                        posZ,
                        state -> state.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK));
        if (grassY < 0) {
            return false;
        }
        if (mantis) {
            ChaosPersists.MyDungeon.makeMantisHive(level, posX, grassY + 1, posZ);
        } else {
            ChaosPersists.MyDungeon.makeSmallBeeHive(level, posX, grassY + 1, posZ);
        }
        recently_placed = 50;
        return true;
    }

    /** OreSpawn 1.7.10 {@code addCorn}: 1/35 per chunk, up to 6 clusters (LessLag reduces count). */
    public void addCorn(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        int nc = 6;
        if (random.nextInt(35) != 1) {
            return;
        }
        if (ChaosPersists.LessLag == 1) {
            nc = 5;
        }
        if (ChaosPersists.LessLag == 2) {
            nc = 3;
        }
        if (!isCornTomatoChunkEligible(level, chunkX, chunkZ)) {
            return;
        }
        block0:
        for (int j = 0; j < nc; ++j) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            boolean is_all_air = true;
            for (int posY = 100; posY > 40; --posY) {
                int i;
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                for (i = 1; i < 10; ++i) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY + i, posZ)).isAir()) {
                        is_all_air = false;
                    }
                }
                if (!is_all_air) {
                    continue block0;
                }
                int corn_height = random.nextInt(5);
                if (++corn_height == 1) {
                    ChaosPersists.setBlockFast(
                            level, posX, posY, posZ, (Block) ChaosPersists.MyCornPlant1, 0, 2);
                }
                if (corn_height == 2) {
                    ChaosPersists.setBlockFast(
                            level, posX, posY, posZ, (Block) ChaosPersists.MyCornPlant2, 0, 2);
                    ChaosPersists.setBlockFast(
                            level, posX, posY + 1, posZ, (Block) ChaosPersists.MyCornPlant1, 0, 2);
                }
                if (corn_height <= 2) {
                    continue block0;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY, posZ, (Block) ChaosPersists.MyCornPlant2, 0, 2);
                for (i = 1; i < corn_height; ++i) {
                    ChaosPersists.setBlockFast(
                            level, posX, posY + i, posZ, (Block) ChaosPersists.MyCornPlant4, 0, 2);
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY + corn_height, posZ, (Block) ChaosPersists.MyCornPlant1, 0, 2);
                continue block0;
            }
        }
    }

    /** OreSpawn 1.7.10 {@code addTomatoes}: 1/70 per chunk, 5 placement attempts. */
    public void addTomatoes(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        boolean is_all_air = true;
        if (random.nextInt(70) != 1) {
            return;
        }
        if (!isCornTomatoChunkEligible(level, chunkX, chunkZ)) {
            return;
        }
        block0:
        for (int j = 0; j < 5; ++j) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            is_all_air = true;
            for (int posY = 100; posY > 40; --posY) {
                int i;
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                for (i = 1; i < 10; ++i) {
                    if (level.getBlockState(new net.minecraft.core.BlockPos(posX, posY + i, posZ)).isAir()) {
                        continue;
                    }
                    is_all_air = false;
                }
                if (!is_all_air) {
                    continue block0;
                }
                int corn_height = random.nextInt(3);
                if (++corn_height == 1) {
                    ChaosPersists.setBlockFast(
                            level, posX, posY, posZ, (Block) ChaosPersists.MyTomatoPlant1, 0, 2);
                }
                if (corn_height == 2) {
                    ChaosPersists.setBlockFast(
                            level, posX, posY, posZ, (Block) ChaosPersists.MyTomatoPlant2, 0, 2);
                    ChaosPersists.setBlockFast(
                            level, posX, posY + 1, posZ, (Block) ChaosPersists.MyTomatoPlant1, 0, 2);
                }
                if (corn_height <= 2) {
                    continue block0;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY, posZ, (Block) ChaosPersists.MyTomatoPlant3, 0, 2);
                for (i = 1; i < corn_height; ++i) {
                    ChaosPersists.setBlockFast(
                            level, posX, posY + i, posZ, (Block) ChaosPersists.MyTomatoPlant4, 0, 2);
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY + corn_height, posZ, (Block) ChaosPersists.MyTomatoPlant1, 0, 2);
                continue block0;
            }
        }
    }

    public void addButterfliesAndMoths(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(10 + ChaosPersists.LessLag * 2) != 0) {
            return;
        }
        Holder<Biome> biome = level.getBiome(new BlockPos(chunkX, 0, chunkZ));
        if (isButterflyBiomeChunkGate(level, biome)) {
            block0:
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(pos).isAir()) {
                        break;
                    }
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                            .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    if (!level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                            && !level.dimension().equals(ChaosPersists.getDimensionKey(6))
                            && !isButterflyOverworldBiome(level.getBiome(new BlockPos(posX, posY, posZ)))) {
                        continue;
                    }
                    int which = random.nextInt(3);
                    if (which == 0) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY, posZ, (Block) ChaosPersists.MyButterflyPlant, 0, 2);
                        continue block0;
                    }
                    if (which == 1) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY, posZ, (Block) ChaosPersists.MyMothPlant, 0, 2);
                        continue block0;
                    }
                    ChaosPersists.setBlockFast(
                            level, posX, posY, posZ, (Block) ChaosPersists.MyFireflyPlant, 0, 2);
                    continue block0;
                }
            }
        }
    }

    public void addPlayPool(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.PLAY_POOL)) {
            return;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.OCEAN)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir() || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makePlayPool(level, posX, posY, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addFrogPond(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.FROG_POND)) {
            return;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.PLAINS)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeFrogPond(level, posX, posY, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addGoldFishBowl(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.GOLDFISH_BOWL)) {
            return;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.OCEAN)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeGoldFishBowl(level, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public boolean addLeafMonster(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.LEAF_MONSTER)) {
            return false;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.PLAINS)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeLeafMonsterDungeon(level, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addRubberDuckyPond(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.RUBBER_DUCKY_POND)) {
            return false;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.PLAINS)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeRubberDuckyPond(level, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addSpitBug(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.SPIT_BUG)) {
            return false;
        }
        Holder<Biome> biome = chunkSurfaceBiome(level, chunkX, chunkZ);
        // Mangrove swamp is the 1.19+ equivalent of 1.7 Swampland for this structure.
        if (biome.is(net.minecraft.world.level.biome.Biomes.SWAMP)
                || biome.is(net.minecraft.world.level.biome.Biomes.MANGROVE_SWAMP)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !this.isSpitBugLairSurface(level.getBlockState(below))) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeSpitBugLair(level, posX, posY, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    /** Regular swamp grass plus mangrove mud floors. */
    private boolean isSpitBugLairSurface(net.minecraft.world.level.block.state.BlockState below) {
        return below.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                || below.is(net.minecraft.world.level.block.Blocks.MUD)
                || below.is(net.minecraft.world.level.block.Blocks.MUDDY_MANGROVE_ROOTS);
    }

    public boolean addIgloo(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.IGLOO)) {
            return false;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.SNOWY_PLAINS)) {
            int posX = chunkX + 8;
            int posZ = chunkZ + 8;
            int groundY =
                    findSurfaceBlockY(
                            level,
                            posX,
                            posZ,
                            state ->
                                    state.is(net.minecraft.world.level.block.Blocks.SNOW)
                                            || state.is(net.minecraft.world.level.block.Blocks.SNOW_BLOCK)
                                            || state.is(net.minecraft.world.level.block.Blocks.POWDER_SNOW)
                                            || state.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                                            || state.is(net.minecraft.world.level.block.Blocks.ICE));
            if (groundY < 0) {
                return false;
            }
            ChaosPersists.MyDungeon.makeIgloo(level, posX, groundY - 1, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addBouncyCastle(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.BOUNCY_CASTLE)) {
            return false;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.DESERT)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.SAND)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeBouncyCastle(level, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addDamselInDistress(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.DAMSEL_IN_DISTRESS)) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 40; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                        || !this.quickSpaceCheck(level, posX, posY - 1, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeDamselInDistress(level, posX, posY - 1, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addSpiderHangout(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.SPIDER_HANGOUT)) {
            return false;
        }
        if (ChaosPersists.SpiderDriverEnable == 0) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 40; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                        || !this.quickSpaceCheck(level, posX, posY - 1, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeSpiderHangout(level, posX, posY - 1, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addRedAntHangout(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.RED_ANT_HANGOUT)) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 40; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                        || !this.quickSpaceCheck(level, posX, posY - 1, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeRedAntHangout(level, posX, posY - 1, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public void addWaterDragonLair(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.WATER_DRAGON_LAIR)) {
            return;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.OCEAN)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir() || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeWaterDragonLair(level, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addGirlfriendIsland(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.GIRLFRIEND_ISLAND)) {
            return;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.OCEAN)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeGirlfriendIsland(level, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addMonsterIsland(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.MONSTER_ISLAND)) {
            return;
        }
        if (chunkSurfaceBiome(level, chunkX, chunkZ).is(net.minecraft.world.level.biome.Biomes.OCEAN)) {
            for (int i = 0; i < 4; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                    if (!level.getBlockState(pos).isAir()
                            || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    ChaosPersists.MyDungeon.makeMonsterIsland(level, posX, posY - 1, posZ);
                    recently_placed = 50;
                    return;
                }
            }
        }
    }

    public void addMosquitos(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(25 + ChaosPersists.LessLag * 2) != 0) {
            return;
        }
        if ((level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                        || level.dimension().equals(ChaosPersists.getDimensionKey(3)))
                && random.nextInt(3) != 0) {
            return;
        }
        Holder<Biome> biome = level.getBiome(new BlockPos(chunkX, 0, chunkZ));
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                || level.dimension().equals(ChaosPersists.getMiningDimensionKey())
                || level.dimension().equals(ChaosPersists.getDimensionKey(3))
                || biome.is(Biomes.JUNGLE)
                || biome.is(Biomes.SWAMP)) {
            block0:
            for (int i = 0; i < 2; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(pos).isAir()) {
                        break;
                    }
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                            .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    ChaosPersists.setBlockFast(
                            level, posX, posY, posZ, (Block) ChaosPersists.MyMosquitoPlant, 0, 2);
                    continue block0;
                }
            }
        }
    }

    public void addNetherMosquitos(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(25) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 20; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.NETHERRACK)) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY, posZ, (Block) ChaosPersists.MyMosquitoPlant, 0, 2);
                continue block0;
            }
        }
    }

    public void addNetherAnts(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.RedAntEnable == 0) {
            return;
        }
        if (random.nextInt(25) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 20; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.NETHERRACK)) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY - 1, posZ, (Block) ChaosPersists.MyRedAntBlock, 0, 2);
                continue block0;
            }
        }
    }

    public void addAnts(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int chunkX,
            int chunkZ,
            int redfreq) {
        if (ChaosPersists.RedAntEnable == 0
                && ChaosPersists.BlackAntEnable == 0
                && ChaosPersists.RainbowAntEnable == 0
                && ChaosPersists.UnstableAntEnable == 0) {
            return;
        }
        if (redfreq < 2) {
            redfreq = 2;
        }
        if (random.nextInt(30 + ChaosPersists.LessLag * 4) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 40; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                if (random.nextInt(redfreq) == 0) {
                    int which = random.nextInt(4);
                    if (which == 0 && ChaosPersists.RedAntEnable != 0) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY - 1, posZ, (Block) ChaosPersists.MyRedAntBlock, 0, 2);
                    }
                    if (which == 1 && ChaosPersists.RainbowAntEnable != 0) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY - 1, posZ, (Block) ChaosPersists.MyRainbowAntBlock, 0, 2);
                    }
                    if (which == 2 && ChaosPersists.UnstableAntEnable != 0) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY - 1, posZ, (Block) ChaosPersists.MyUnstableAntBlock, 0, 2);
                    }
                    if (which != 3 || ChaosPersists.TermiteEnable == 0) {
                        continue block0;
                    }
                    ChaosPersists.setBlockFast(
                            level, posX, posY - 1, posZ, (Block) ChaosPersists.TermiteBlock, 0, 2);
                    continue block0;
                }
                if (ChaosPersists.BlackAntEnable == 0) {
                    continue block0;
                }
                ChaosPersists.setBlockFast(level, posX, posY - 1, posZ, (Block) ChaosPersists.MyAntBlock, 0, 2);
                continue block0;
            }
        }
    }

    public void addEndAnts(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
    }

    public void addEndKnights(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ENDER_KNIGHT_DUNGEON)) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.END_STONE)
                        || !this.quickSpaceCheck(level, posX, posY, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeEnderKnightDungeon(level, posX, posY, posZ);
                return;
            }
        }
    }

    public void addEndReapers(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ENDER_REAPER_GRAVEYARD)) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.END_STONE)
                        || !this.quickSpaceCheck(level, posX, posY, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeEnderReaperGraveyard(level, posX, posY, posZ);
                return;
            }
        }
    }

    public void addHospital(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ENDER_DRAGON_HOSPITAL)) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.END_STONE)
                        || !this.quickSpaceCheck(level, posX, posY, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeEnderDragonHospital(level, posX, posY, posZ);
                return;
            }
        }
    }

    public void addEnderCastle(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ENDER_CASTLE)) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 90; posY > 10; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.END_STONE)
                        || !this.quickBigSpaceCheck(level, posX, posY, posZ)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeEnderCastle(level, posX, posY, posZ);
                return;
            }
        }
    }

    public void addUnstableAnts(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.UnstableAntEnable == 0) {
            return;
        }
        if (random.nextInt(30) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 20; posY > 2; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY - 1, posZ, (Block) ChaosPersists.MyUnstableAntBlock, 0, 2);
                continue block0;
            }
        }
    }

    public void addCrystalTermites(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.TermiteEnable == 0) {
            return;
        }
        if (random.nextInt(40) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || level.getBlockState(below).getBlock() != ChaosPersists.CrystalGrass) {
                    continue;
                }
                ChaosPersists.setBlockFast(
                        level, posX, posY - 1, posZ, (Block) ChaosPersists.CrystalTermiteBlock, 0, 2);
                continue block0;
            }
        }
    }

    public boolean addRotatorStation(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.RotatorEnable == 0) {
            return false;
        }
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ROTATOR_STATION)) {
            return false;
        }
        net.minecraft.world.level.block.Block crystalGrass =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalGrass;
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir() || !level.getBlockState(below).is(crystalGrass)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeRotatorStation(level, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addRoundRotator(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.RotatorEnable == 0) {
            return false;
        }
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ROUND_ROTATOR)) {
            return false;
        }
        net.minecraft.world.level.block.Block crystalGrass =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalGrass;
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir() || !level.getBlockState(below).is(crystalGrass)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeRoundRotator(level, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addUrchinSpawner(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (ChaosPersists.UrchinEnable == 0) {
            return false;
        }
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.URCHIN_SPAWNER)) {
            return false;
        }
        net.minecraft.world.level.block.Block crystalGrass =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalGrass;
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir() || !level.getBlockState(below).is(crystalGrass)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeUrchinSpawner(level, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addCrystalHauntedHouse(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.CRYSTAL_HAUNTED_HOUSE)) {
            return false;
        }
        net.minecraft.world.level.block.Block crystalGrass =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalGrass;
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(crystalGrass)) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeCrystalHauntedHouse(level, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public boolean addCrystalBattleTower(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.CRYSTAL_BATTLE_TOWER)) {
            return false;
        }
        net.minecraft.world.level.block.Block crystalGrass =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalGrass;
        for (int i = 0; i < 3; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 100; posY > 50; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || level.getBlockState(below).getBlock() != crystalGrass) {
                    continue;
                }
                ChaosPersists.MyDungeon.makeCrystalBattleTower(level, posX, posY, posZ);
                recently_placed = 50;
                return true;
            }
        }
        return false;
    }

    public void addIrukandji(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
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
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                if (!level.getBlockState(pos).isAir()
                        || !level.getBlockState(below).is(net.minecraft.world.level.block.Blocks.WATER)) {
                    continue;
                }
                this.placeChaosWorldSpawner(level, posX, posY, posZ, "irukandji");
                return;
            }
        }
    }

    public void addCrystalChestsAndSpawners(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        for (int i = 0; i < 3; ++i) {
            int posX = 1 + chunkX + random.nextInt(14);
            int posY = 25;
            int posZ = 1 + chunkZ + random.nextInt(14);
            net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
            if (!level.getBlockState(pos).isAir()) {
                continue;
            }
            if (level.getBlockState(new net.minecraft.core.BlockPos(posX + 1, posY, posZ)).isAir()) {
                this.addCrystalChest(level, random, posX, posY, posZ, 5);
                break;
            }
            if (level.getBlockState(new net.minecraft.core.BlockPos(posX - 1, posY, posZ)).isAir()) {
                this.addCrystalChest(level, random, posX, posY, posZ, 4);
                break;
            }
            if (level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ + 1)).isAir()) {
                this.addCrystalChest(level, random, posX, posY, posZ, 2);
                break;
            }
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ - 1)).isAir()) {
                break;
            }
            this.addCrystalChest(level, random, posX, posY, posZ, 3);
            break;
        }
    }

    public void addCrystalChest(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int x,
            int y,
            int z,
            int dir) {
        int i = random.nextInt(3);
        if (i == 0) {
            net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x, y, z);
            level.setBlock(
                    pos,
                    net.minecraft.world.level.block.Blocks.CHEST
                            .defaultBlockState()
                            .setValue(
                                    net.minecraft.world.level.block.ChestBlock.FACING,
                                    this.crystalChestFacingFromLegacyMeta(dir)),
                    3);
            net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
                WeightedRandomChestContent.generateChestContents(
                        random,
                        Trees.CrystalChestContentsList,
                        chest,
                        1 + random.nextInt(3));
            }
        } else {
            int t = random.nextInt(2);
            if (t == 0) {
                this.placeChaosWorldSpawner(level, x, y, z, "dungeon_beast");
            }
            if (t == 1) {
                this.placeChaosWorldSpawner(level, x, y, z, "rat");
            }
        }
    }

    private static net.minecraft.core.Direction crystalChestFacingFromLegacyMeta(int meta) {
        return switch (meta) {
            case 2 -> net.minecraft.core.Direction.NORTH;
            case 3 -> net.minecraft.core.Direction.SOUTH;
            case 4 -> net.minecraft.core.Direction.WEST;
            case 5 -> net.minecraft.core.Direction.EAST;
            default -> net.minecraft.core.Direction.NORTH;
        };
    }

    private void placeChaosWorldSpawner(
            net.minecraft.world.level.Level level, int x, int y, int z, String mobPath) {
        net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x, y, z);
        level.setBlock(pos, net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
            net.minecraft.resources.ResourceLocation id =
                    SpawnerFixHelper.normalizeSpawnerEntityId(
                            net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(
                                    "chaospersists", mobPath));
            net.minecraft.world.entity.EntityType<?> type =
                    net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getValue(id);
            if (type != null) {
                spawner.setEntityId(type, level.getRandom());
            }
        }
    }

    public void addIslands(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        int posX = 2 + chunkX + random.nextInt(12);
        int posZ = 2 + chunkZ + random.nextInt(12);
        if (random.nextInt(10 + ChaosPersists.LessLag * 2) != 1) {
            return;
        }
        int plantY = this.findIslandsPlantingY(level, posX, posZ);
        if (plantY > 0) {
            ChaosPersists.setBlockFast(level, posX, plantY, posZ, (Block) ChaosPersists.MyIslandBlock, 0, 2);
        }
    }

    private int findIslandsGrassSurfaceY(net.minecraft.world.level.Level level, int posX, int posZ) {
        for (int y = Math.min(128, level.getMaxBuildHeight() - 1); y > level.getMinBuildHeight(); --y) {
            if (level.getBlockState(new net.minecraft.core.BlockPos(posX, y, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                return y;
            }
        }
        return -1;
    }

    private int findIslandsPlantingY(net.minecraft.world.level.Level level, int posX, int posZ) {
        int minY = level.getMinBuildHeight() + 1;
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 2); posY > minY; --posY) {
            net.minecraft.core.BlockPos airPos = new net.minecraft.core.BlockPos(posX, posY, posZ);
            if (!level.getBlockState(airPos).isAir()) {
                break;
            }
            if (level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                return posY;
            }
        }
        return -1;
    }

    public void addScragglyTrees(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int chunkX,
            int chunkZ) {
        int howmany = 1 + random.nextInt(10);
        if (ChaosPersists.LessLag == 1) {
            howmany /= 2;
        }
        if (ChaosPersists.LessLag == 2) {
            howmany /= 4;
        }
        if (howmany == 0) {
            return;
        }
        Trees trees = ChaosPersists.chaospersistsTrees;
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = 2 + chunkX + random.nextInt(12);
            int posZ = 2 + chunkZ + random.nextInt(12);
            int plantY = this.findIslandsPlantingY(level, posX, posZ);
            if (plantY > 0) {
                trees.ScragglyTreeWithBranches(level, random, posX, plantY, posZ);
                continue block0;
            }
        }
    }

    /**
     * 1.7 {@code ChunkProviderOreSpawn6.addScragglyTrees}: 25% of chunks, {@code 1 + rand(5)}
     * trees, plant on grass scanning Y 120→50.
     */
    public void addChaosScragglyTrees(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int chunkX,
            int chunkZ) {
        if (random.nextInt(4) != 0) {
            return;
        }
        int howmany = 1 + random.nextInt(5);
        if (ChaosPersists.LessLag == 1) {
            howmany /= 2;
        }
        if (ChaosPersists.LessLag == 2) {
            howmany /= 4;
        }
        if (howmany == 0) {
            return;
        }
        Trees trees = ChaosPersists.chaospersistsTrees;
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = 2 + chunkX + random.nextInt(12);
            int posZ = 2 + chunkZ + random.nextInt(12);
            for (int posY = 120; posY > 50; --posY) {
                if (level.getBlockState(new BlockPos(posX, posY - 1, posZ))
                        .is(Blocks.GRASS_BLOCK)) {
                    trees.ScragglyTreeWithBranches(level, random, posX, posY, posZ);
                    continue block0;
                }
            }
        }
    }

    /**
     * 1.7 {@code BiomeGenUtopianPlains.setChaosCreatures} biome decorate:
     * treesPerChunk=1, flowersPerChunk=2, grassPerChunk=4 (mushrooms disabled).
     * Flower/grass use 1.7 WorldGenFlowers / WorldGenTallGrass: flowers keep a random
     * start Y (rare), grass walks down to the surface first then scatters (common).
     */
    public void addChaosSurfaceVegetation(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int chunkX,
            int chunkZ) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        for (int i = 0; i < 1; ++i) {
            int posX = chunkX + 8 + random.nextInt(16);
            int posZ = chunkZ + 8 + random.nextInt(16);
            int plantY = this.findChaosTreePlantY(level, posX, posZ);
            if (plantY > 0) {
                this.placeChaosOakTree(serverLevel, random, new BlockPos(posX, plantY, posZ));
            }
        }
        for (int i = 0; i < 2; ++i) {
            int posX = chunkX + 8 + random.nextInt(16);
            int posZ = chunkZ + 8 + random.nextInt(16);
            int topY = this.findChaosColumnTopY(level, posX, posZ);
            if (topY < 0) {
                continue;
            }
            // 1.7 WorldGenFlowers: random Y with no walk-down → most scatters miss (rare)
            int startY = random.nextInt(topY + 32);
            Block flower = random.nextBoolean() ? Blocks.DANDELION : Blocks.POPPY;
            this.placeChaosPlantCluster(level, random, posX, startY, posZ, flower.defaultBlockState(), 64);
        }
        for (int i = 0; i < 4; ++i) {
            int posX = chunkX + 8 + random.nextInt(16);
            int posZ = chunkZ + 8 + random.nextInt(16);
            int topY = this.findChaosColumnTopY(level, posX, posZ);
            if (topY < 0) {
                continue;
            }
            // 1.7 WorldGenTallGrass: rand(height*2) then walk down through air to the surface
            int startY = random.nextInt(Math.max(1, topY * 2));
            while (startY > 0) {
                BlockState at = level.getBlockState(new BlockPos(posX, startY, posZ));
                if (!at.isAir() && !at.is(Blocks.OAK_LEAVES) && !at.is(ChaosPersists.MyAppleLeaves)) {
                    break;
                }
                --startY;
            }
            this.placeChaosPlantCluster(
                    level, random, posX, startY + 1, posZ, Blocks.GRASS.defaultBlockState(), 128);
        }
    }

    /** 1.7 WorldGenFlowers / WorldGenTallGrass scatter; plants may sit on grass or dirt. */
    private void placeChaosPlantCluster(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int centerX,
            int centerY,
            int centerZ,
            BlockState plant,
            int attempts) {
        for (int n = 0; n < attempts; ++n) {
            int x = centerX + random.nextInt(8) - random.nextInt(8);
            int y = centerY + random.nextInt(4) - random.nextInt(4);
            int z = centerZ + random.nextInt(8) - random.nextInt(8);
            BlockPos pos = new BlockPos(x, y, z);
            if (!level.getBlockState(pos).isAir()) {
                continue;
            }
            BlockState below = level.getBlockState(pos.below());
            if (below.is(Blocks.GRASS_BLOCK) || below.is(Blocks.DIRT)) {
                level.setBlock(pos, plant, 2);
            }
        }
    }

    /** Highest solid block in the Chaos island band (for 1.7-style random plant Y). */
    private int findChaosColumnTopY(net.minecraft.world.level.Level level, int posX, int posZ) {
        for (int posY = 120; posY > 50; --posY) {
            BlockState state = level.getBlockState(new BlockPos(posX, posY, posZ));
            if (!state.isAir() && !state.liquid()) {
                return posY;
            }
        }
        int height =
                level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, posX, posZ) - 1;
        return height > level.getMinBuildHeight() ? height : -1;
    }

    /** 1.7 WorldGenTrees: oak on grass or dirt under air. */
    private int findChaosTreePlantY(net.minecraft.world.level.Level level, int posX, int posZ) {
        for (int posY = 120; posY > 50; --posY) {
            if (!level.getBlockState(new BlockPos(posX, posY, posZ)).isAir()) {
                continue;
            }
            BlockState below = level.getBlockState(new BlockPos(posX, posY - 1, posZ));
            if (below.is(Blocks.GRASS_BLOCK) || below.is(Blocks.DIRT)) {
                return posY;
            }
        }
        int airY =
                level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, posX, posZ);
        if (airY <= level.getMinBuildHeight() + 1 || airY >= level.getMaxBuildHeight()) {
            return -1;
        }
        BlockState below = level.getBlockState(new BlockPos(posX, airY - 1, posZ));
        if (below.is(Blocks.GRASS_BLOCK) || below.is(Blocks.DIRT)) {
            return airY;
        }
        return -1;
    }

    private void placeChaosOakTree(
            ServerLevel level, net.minecraft.util.RandomSource random, BlockPos pos) {
        level.registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.CONFIGURED_FEATURE)
                .getHolder(net.minecraft.data.worldgen.features.TreeFeatures.OAK)
                .ifPresent(
                        holder ->
                                holder.value()
                                        .place(
                                                level,
                                                level.getChunkSource().getGenerator(),
                                                random,
                                                pos));
    }

    public boolean addAppleTrees(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int chunkX,
            int chunkZ,
            LevelChunk chunk) {
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
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = 2 + chunkX + random.nextInt(12);
            int posZ = 2 + chunkZ + random.nextInt(12);
            int surfaceY = this.findGrassSurfaceY(level, posX, posZ);
            if (surfaceY < 0) {
                continue;
            }
            int posY = surfaceY + 1;
            {
                ItemAppleSeed a = (ItemAppleSeed) ChaosPersists.MyAppleSeed;
                if (which < 8) {
                    a.makeTree(level, posX, posY - 1, posZ, ChaosPersists.MyAppleLeaves, chunk);
                }
                if (which == 8) {
                    a.makeTree(level, posX, posY - 1, posZ, ChaosPersists.MyCherryLeaves, chunk);
                }
                if (which == 9) {
                    a.makeTree(level, posX, posY - 1, posZ, ChaosPersists.MyPeachLeaves, chunk);
                }
                added = true;
                continue block0;
            }
        }
        return added;
    }

    public boolean addHugeTree(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int chunkX,
            int chunkZ,
            LevelChunk chunk) {
        boolean kingTree = locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.KING_TREE);
        boolean queenTree = locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.QUEEN_TREE);
        if (kingTree || queenTree) {
            return this.placeKingOrQueenTree(level, chunkX, chunkZ, chunk, kingTree);
        }

        int made_one = 0;

        if (random.nextInt(50) != 0) {
            return false;
        }
        if ((ChaosPersists.LessLag == 1) && (random.nextInt(2) != 0)) {
            return false;
        }

        if ((ChaosPersists.LessLag == 2) && (random.nextInt(4) != 0)) {
            return false;
        }

        for (int i = 0; (i < 3) && (made_one == 0); i++) {
            int posX = 4 + chunkX + random.nextInt(8);
            int posZ = 4 + chunkZ + random.nextInt(8);
            int surfaceY = this.findGrassSurfaceY(level, posX, posZ);
            if (surfaceY < 0) {
                continue;
            }
            int posY = surfaceY + 1;
            {
                ItemMagicApple a = (ItemMagicApple) (Object) ChaosPersists.MagicApple;
                if (a == null) {
                    continue;
                }

                int tree_type = random.nextInt(4);
                int tree_radius = 6 - random.nextInt(2);
                boolean no_critters = false;
                Block leaf_type = Blocks.OAK_LEAVES;

                if (random.nextInt(100) > 25) {
                    no_critters = true;
                }
                int rand_treetype = random.nextInt(100);
                if (rand_treetype > 75) {
                    if ((tree_type != 3) && (random.nextInt(20) == 0)) {
                        leaf_type = ChaosPersists.MyAppleLeaves;
                    }
                    a.MakeBigSquareTree(
                            level,
                            posX,
                            posY - 1,
                            posZ,
                            Blocks.OAK_LOG,
                            leaf_type,
                            Blocks.MOSSY_COBBLESTONE,
                            tree_type,
                            tree_radius,
                            no_critters,
                            null);
                } else if (rand_treetype > 15) {
                    tree_radius = 6 - random.nextInt(3);
                    a.MakeBigCircularTree(
                            level,
                            posX,
                            posY - 1,
                            posZ,
                            Blocks.OAK_LOG,
                            leaf_type,
                            Blocks.MOSSY_COBBLESTONE,
                            tree_type,
                            tree_radius,
                            no_critters,
                            null);
                } else {
                    tree_radius = 6 - random.nextInt(3);
                    a.MakeBigRoundTree(
                            level,
                            posX,
                            posY - 1,
                            posZ,
                            Blocks.OAK_LOG,
                            leaf_type,
                            Blocks.MOSSY_COBBLESTONE,
                            tree_type,
                            tree_radius,
                            null);
                }

                made_one = 1;
                break;
            }
        }

        return made_one != 0;
    }

    private boolean placeKingOrQueenTree(
            net.minecraft.world.level.Level level,
            int chunkX,
            int chunkZ,
            LevelChunk chunk,
            boolean king) {
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        int grassY =
                findSurfaceBlockY(
                        level,
                        posX,
                        posZ,
                        state -> state.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK));
        if (grassY < 0) {
            return false;
        }
        ItemMagicApple a = (ItemMagicApple) (Object) ChaosPersists.MagicApple;
        if (a == null) {
            return false;
        }
        if (king) {
            a.MakeBigSquareTree(
                    level,
                    posX,
                    grassY,
                    posZ,
                    Blocks.GOLD_BLOCK,
                    Blocks.EMERALD_BLOCK,
                    Blocks.DIAMOND_BLOCK,
                    -1,
                    6,
                    true,
                    chunk);
        } else {
            a.MakeBigSquareTree(
                    level,
                    posX,
                    grassY,
                    posZ,
                    Blocks.OBSIDIAN,
                    ChaosPersists.MyBlockRubyBlock,
                    ChaosPersists.MyBlockAmethystBlock,
                    -1,
                    6,
                    true,
                    chunk);
        }
        recently_placed = 50;
        return true;
    }

    /**
     * Surface Y of grass under open air, using the heightmap so Utopia hills above the old
     * hard-coded Y 100–127 caps still get giant trees.
     */
    private int findGrassSurfaceY(net.minecraft.world.level.Level level, int posX, int posZ) {
        int airY =
                level.getHeight(
                        net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                        posX,
                        posZ);
        if (airY <= level.getMinBuildHeight() + 1 || airY >= level.getMaxBuildHeight()) {
            return -1;
        }
        if (level.getBlockState(new net.minecraft.core.BlockPos(posX, airY - 1, posZ))
                .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
            return airY - 1;
        }
        return -1;
    }

    public void addVeggies(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(15) != 0) {
            return;
        }
        Holder<Biome> biome = level.getBiome(new BlockPos(chunkX, 0, chunkZ));
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())
                || level.dimension().equals(ChaosPersists.getMiningDimensionKey())
                || level.dimension().equals(ChaosPersists.getDimensionKey(6))
                || biome.is(Biomes.RIVER)
                || biome.is(Biomes.SWAMP)) {
            block0:
            for (int i = 0; i < 8; ++i) {
                int posX = chunkX + random.nextInt(16);
                int posZ = chunkZ + random.nextInt(16);
                for (int posY = 100; posY > 40; --posY) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(pos).isAir()) {
                        break;
                    }
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                            .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    if (!canSpawnVeggiesAt(level, posX, posY, posZ)) {
                        continue;
                    }
                    int what = random.nextInt(6);
                    if (what == 0) {
                        ChaosPersists.setBlockFast(level, posX, posY, posZ, Blocks.CARROTS, 0, 2);
                        continue block0;
                    }
                    if (what == 1) {
                        ChaosPersists.setBlockFast(level, posX, posY, posZ, Blocks.POTATOES, 0, 2);
                        continue block0;
                    }
                    if (what == 2) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY, posZ, (Block) ChaosPersists.MyRadishPlant, 0, 2);
                        continue block0;
                    }
                    if (what == 3) {
                        ChaosPersists.setBlockFast(
                                level, posX, posY, posZ, (Block) ChaosPersists.MyLettucePlant1, 0, 2);
                        continue block0;
                    }
                    if (what == 4) {
                        if (random.nextInt(10) != 0) {
                            continue block0;
                        }
                        ChaosPersists.setBlockFast(level, posX, posY, posZ, Blocks.MELON_STEM, 0, 2);
                        continue block0;
                    }
                    if (random.nextInt(50) != 1 || ChaosPersists.enableduplicatortree == 0) {
                        continue block0;
                    }
                    ChaosPersists.setBlockFast(level, posX, posY, posZ, (Block) ChaosPersists.MyDT, 0, 2);
                    if (DEBUG_NATURAL_DUPLICATOR_SPAWNS) {
                        LOGGER.info(
                                "ChaosPersists DEBUG: natural duplicator log placed at dim {} ({}, {}, {})",
                                level.dimension().location(),
                                posX,
                                posY,
                                posZ);
                    }
                    continue block0;
                }
            }
        }
    }

    public void addRocks(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(5) != 0) {
            return;
        }
        if (ChaosPersists.RockEnable == 0) {
            return;
        }
        Block crystalGrass = ChaosPersists.CrystalGrass;
        int howmany = 3 + random.nextInt(10);
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 110; posY > 40; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                net.minecraft.core.BlockPos below = new net.minecraft.core.BlockPos(posX, posY - 1, posZ);
                Block bid = level.getBlockState(below).getBlock();
                if (bid != Blocks.GRASS && bid != Blocks.SAND && bid != crystalGrass) {
                    continue;
                }
                this.spawnCreature(level, "Rock", (double) posX, (double) posY, (double) posZ);
                continue block0;
            }
        }
    }

    public void addD4Rocks(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(7) != 0) {
            return;
        }
        if (ChaosPersists.RockEnable == 0) {
            return;
        }
        int howmany = 3 + random.nextInt(10);
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 20; posY > 5; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                this.spawnCreature(level, "Rock", (double) posX, (double) posY, (double) posZ);
                continue block0;
            }
        }
    }

    public boolean addFairyTree(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        Block crystalGrass = ChaosPersists.CrystalGrass;
        if (random.nextInt(5) != 0) {
            return false;
        }
        for (int posY = 128; posY > 40; --posY) {
            net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
            if (!level.getBlockState(pos).isAir()
                    || level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ)).getBlock()
                            != crystalGrass) {
                continue;
            }
            for (int i = -8; i <= 8; ++i) {
                for (int j = -8; j <= 8; ++j) {
                    if (level.getBlockState(new net.minecraft.core.BlockPos(posX + i, posY, posZ + j)).isAir()) {
                        continue;
                    }
                    return false;
                }
            }
            for (int i = -2; i <= 2; ++i) {
                for (int j = -2; j <= 2; ++j) {
                    if (level.getBlockState(new net.minecraft.core.BlockPos(posX + i, posY - 1, posZ + j)).getBlock()
                            == crystalGrass) {
                        continue;
                    }
                    return false;
                }
            }
            if (random.nextInt(5) != 1) {
                ChaosPersists.chaospersistsTrees.FairyTree(level, posX, posY - 1, posZ);
            } else {
                ChaosPersists.chaospersistsTrees.FairyCastleTree(level, posX, posY, posZ);
            }
            recently_placed = 50;
            break;
        }
        return true;
    }

    public boolean addRubyDungeon(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.RUBY_DUNGEON)) {
            return false;
        }
        for (int i = 0; i < 8; ++i) {
            int posX = chunkX + random.nextInt(8);
            int posZ = chunkZ + random.nextInt(8);
            for (int posY = 50; posY > 5; --posY) {
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                        .is(net.minecraft.world.level.block.Blocks.LAVA)) {
                    continue;
                }
                ChaosPersists.RubyDungeon.makeDungeon(level, posX, posY, posZ);
                return true;
            }
        }
        return false;
    }

    public boolean addGenericDungeon(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.DUNGEON)) {
            return false;
        }
        // 1.7.10 OreSpawnWorld.addGenericDungeon: fixed underground Y (not heightmap — tall
        // Utopia trees/king altars would otherwise place rat/dungeon-beast rooms in mid-air).
        int posX = chunkX + random.nextInt(4);
        int posZ = chunkZ + random.nextInt(4);
        int posY = 5 + random.nextInt(40);
        ChaosPersists.MyDungeon.makeDungeon(level, posX, posY, posZ);
        return true;
    }

    public boolean addBeeHive(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.BEE_NEST)) {
            return false;
        }
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        int grassY =
                findSurfaceBlockY(
                        level,
                        posX,
                        posZ,
                        state -> state.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK));
        if (grassY < 0) {
            return false;
        }
        ChaosPersists.MyDungeon.makeBeeHive(level, posX, grassY + 3, posZ);
        recently_placed = 50;
        return true;
    }

    public boolean addAlienWTF(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ALIEN_WTF_DUNGEON)) {
            return false;
        }
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    net.minecraft.core.BlockPos above = new net.minecraft.core.BlockPos(posX, posY + 1, posZ);
                    net.minecraft.core.BlockPos ground = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(above).isAir()
                            || !level.getBlockState(ground).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
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
            ChaosPersists.MyDungeon.makeAlienWTFDungeon(level, lowestX, lowestY, lowestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addEnderKnight(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ENDER_KNIGHT_DUNGEON)) {
            return false;
        }
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    net.minecraft.core.BlockPos above = new net.minecraft.core.BlockPos(posX, posY + 1, posZ);
                    net.minecraft.core.BlockPos ground = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(above).isAir()
                            || !level.getBlockState(ground).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
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
            ChaosPersists.MyDungeon.makeEnderKnightDungeon(level, lowestX, lowestY, lowestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addLeonNest(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.LEON_NEST)) {
            return false;
        }
        int highestY = Integer.MIN_VALUE;
        int highestX = chunkX;
        int highestZ = chunkZ;
        boolean found = false;
        int minY = Math.max(level.getMinBuildHeight() + 1, 40);
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                int top =
                        Math.min(
                                level.getMaxBuildHeight() - 2,
                                level.getHeight(
                                                net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE,
                                                posX,
                                                posZ)
                                        + 1);
                for (int posY = top; posY > minY; --posY) {
                    net.minecraft.core.BlockPos above = new net.minecraft.core.BlockPos(posX, posY + 1, posZ);
                    net.minecraft.core.BlockPos ground = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    net.minecraft.world.level.block.state.BlockState aboveState = level.getBlockState(above);
                    if ((!aboveState.isAir() && !aboveState.is(net.minecraft.world.level.block.Blocks.SNOW))
                            || !level.getBlockState(ground).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                        continue;
                    }
                    if (posY <= highestY) continue block1;
                    highestY = posY + 1;
                    highestX = posX;
                    highestZ = posZ;
                    found = true;
                    continue block1;
                }
            }
        }
        if (found) {
            ChaosPersists.MyDungeon.makeLeonNest(level, highestX, highestY, highestZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addShadowDungeon(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.NIGHTMARE_DUNGEON)) {
            return false;
        }
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        int minY = Math.max(level.getMinBuildHeight() + 1, 40);
        int groundY = -1;
        int top =
                Math.min(
                        level.getMaxBuildHeight() - 2,
                        level.getHeight(
                                net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, posX, posZ));
        for (int posY = top; posY >= minY; --posY) {
            net.minecraft.world.level.block.state.BlockState state =
                    level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ));
            if (state.isAir()
                    || state.is(net.minecraft.world.level.block.Blocks.SNOW)
                    || state.is(net.minecraft.world.level.block.Blocks.POWDER_SNOW)) {
                continue;
            }
            groundY = posY;
            break;
        }
        if (groundY < 0) {
            return false;
        }
        ChaosPersists.MyDungeon.makeShadowDungeon(level, posX, groundY, posZ);
        recently_placed = 50;
        return true;
    }

    public boolean addD4RubyDungeon(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.DANGER_RUBY_DUNGEON)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            ChaosPersists.RubyDungeon.makeDungeon(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4CephadromeAltar(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.CEPHADROME_ALTAR)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            ChaosPersists.MyDungeon.makeCephadromeAltar(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4Castle(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ENORMOUS_CASTLE)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -20; x < 33; ++x) {
                for (int z = -4; z < 33; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            if (random.nextInt(2) == 1) {
                ChaosPersists.MyDungeon.makeEnormousCastle(level, posX, posY, posZ);
            } else {
                ChaosPersists.MyDungeon.makeEnormousCastleQ(level, posX, posY, posZ);
            }
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4Greenhouse(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.GREENHOUSE)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -2; x < 25; ++x) {
                for (int z = -4; z < 25; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            ChaosPersists.MyDungeon.makeGreenhouseDungeon(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4NightmareRookery(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.NIGHTMARE_ROOKERY)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -5; x < 25; ++x) {
                for (int z = -4; z < 5; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            ChaosPersists.MyDungeon.makeNightmareRookery(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4StinkyHouse(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.STINKY_HOUSE)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -8; x < 20; ++x) {
                for (int z = -8; z < 20; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            ChaosPersists.MyDungeon.makeStinkyHouse(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4WhiteHouse(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.WHITE_HOUSE)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -20; x < 30; ++x) {
                for (int z = -20; z < 30; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            ChaosPersists.MyDungeon.makeWhiteHouse(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4EnderCastle(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.DANGER_ENDER_CASTLE)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -5; x < 25; ++x) {
                for (int z = -5; z < 25; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            ChaosPersists.MyDungeon.makeEnderCastle(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4IncaPyramid(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.INCA_PYRAMID)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -10; x < 50; ++x) {
                for (int z = -10; z < 40; ++z) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 18, posZ + z))
                            .isAir()) {
                        return false;
                    }
                }
            }
            ChaosPersists.MyDungeon.makeIncaPyramid(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4RobotLab(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.ROBOT_LAB)) {
            return false;
        }
        net.minecraft.world.level.block.Block appleLeaves =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyAppleLeaves;
        net.minecraft.world.level.block.Block scaryLeaves =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyScaryLeaves;
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            for (int x = -5; x < 60; ++x) {
                for (int z = -5; z < 70; ++z) {
                    net.minecraft.world.level.block.Block bid =
                            level.getBlockState(new net.minecraft.core.BlockPos(posX + x, posY + 4, posZ + z)).getBlock();
                    if (bid == net.minecraft.world.level.block.Blocks.AIR
                            || bid == net.minecraft.world.level.block.Blocks.OAK_LOG
                            || bid == appleLeaves
                            || bid == scaryLeaves) {
                        continue;
                    }
                    return false;
                }
            }
            ChaosPersists.MyDungeon.makeRobotLab(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4Mini(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.MINI_DUNGEON)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            ChaosPersists.MyDungeon.makeMiniDungeon(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addPumpkin(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.PUMPKIN)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = Math.min(128, level.getMaxBuildHeight() - 1); posY > level.getMinBuildHeight() + 3; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            ChaosPersists.MyDungeon.makePumpkin(level, posX, posY + 1, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public boolean addD4CloudShark(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.CLOUD_SHARK)) {
            return false;
        }
        int posX = 4 + chunkX + random.nextInt(8);
        int posZ = 4 + chunkZ + random.nextInt(8);
        ChaosPersists.MyDungeon.makeCloudSharkDungeon(level, posX, 150 + random.nextInt(10), posZ);
        return true;
    }

    public boolean addD4Rainbow(net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.RAINBOW)) {
            return false;
        }
        int posX = 4 + chunkX + random.nextInt(8);
        int posZ = 4 + chunkZ + random.nextInt(8);
        int posY = 70 + random.nextInt(20);
        ChaosPersists.MyDungeon.makeRainbow(level, posX, posY, posZ);
        recently_placed = 50;
        return true;
    }

    public boolean addD4GenericDungeon(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.DANGER_DUNGEON)) {
            return false;
        }
        int posX = chunkX + random.nextInt(8);
        int posZ = chunkZ + random.nextInt(8);
        for (int posY = 20; posY > 4; --posY) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                continue;
            }
            ChaosPersists.MyDungeon.makeDungeon(level, posX, posY, posZ);
            recently_placed = 50;
            return true;
        }
        return false;
    }

    public void addLavaAndWater(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        if (random.nextInt(5) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 6; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 128; posY > 75; --posY) {
                net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(posX, posY, posZ);
                if (!level.getBlockState(pos).isAir()) {
                    break;
                }
                if (!level.getBlockState(new net.minecraft.core.BlockPos(posX, posY - 1, posZ))
                        .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    continue;
                }
                net.minecraft.core.BlockPos deep =
                        new net.minecraft.core.BlockPos(posX, posY - 2, posZ);
                if (!level.getBlockState(deep).is(net.minecraft.world.level.block.Blocks.DIRT)
                        && !level.getBlockState(deep).is(net.minecraft.world.level.block.Blocks.STONE)) {
                    continue block0;
                }
                int air = 0;
                int non_air = 0;
                net.minecraft.core.BlockPos side =
                        new net.minecraft.core.BlockPos(posX + 1, posY - 1, posZ);
                if (level.getBlockState(side).isAir()) {
                    ++air;
                }
                if (level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.DIRT)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.STONE)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    ++non_air;
                }
                side = new net.minecraft.core.BlockPos(posX - 1, posY - 1, posZ);
                if (level.getBlockState(side).isAir()) {
                    ++air;
                }
                if (level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.DIRT)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.STONE)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    ++non_air;
                }
                side = new net.minecraft.core.BlockPos(posX, posY - 1, posZ + 1);
                if (level.getBlockState(side).isAir()) {
                    ++air;
                }
                if (level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.DIRT)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.STONE)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    ++non_air;
                }
                side = new net.minecraft.core.BlockPos(posX, posY - 1, posZ - 1);
                if (level.getBlockState(side).isAir()) {
                    ++air;
                }
                if (level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.DIRT)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.STONE)
                        || level.getBlockState(side).is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                    ++non_air;
                }
                if (air == 0 || non_air == 0) {
                    continue block0;
                }
                int what = random.nextInt(2);
                if (what == 0) {
                    ChaosPersists.setBlockFast(level, posX, posY, posZ, Blocks.WATER, 0, 3);
                    ChaosPersists.setBlockFast(level, posX, posY - 1, posZ, Blocks.WATER, 0, 3);
                    ChaosPersists.setBlockFast(level, posX, posY - 2, posZ, Blocks.WATER, 0, 3);
                } else {
                    ChaosPersists.setBlockFast(level, posX, posY, posZ, Blocks.LAVA, 0, 3);
                    ChaosPersists.setBlockFast(level, posX, posY - 1, posZ, Blocks.LAVA, 0, 3);
                    ChaosPersists.setBlockFast(level, posX, posY - 2, posZ, Blocks.LAVA, 0, 3);
                }
                return;
            }
        }
    }

    public boolean addOtherTrees(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
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
        if (level.dimension().equals(ChaosPersists.getUtopiaDimensionKey())) {
            int dir = 0;
            int what = random.nextInt(2);
            block0:
            for (int i = 0; i < nc; ++i) {
                int posX = 3 + chunkX + random.nextInt(10);
                int posZ = 3 + chunkZ + random.nextInt(10);
                int surfaceY = this.findGrassSurfaceY(level, posX, posZ);
                if (surfaceY < 0) {
                    continue;
                }
                int posY = surfaceY + 1;
                {
                    ++count;
                    Trees trees = ChaosPersists.chaospersistsTrees;
                    if (trees == null) {
                        continue block0;
                    }
                    if (what == 0) {
                        trees.WindTree(level, posX, posY - 1, posZ, dir);
                        if (count < 4) {
                            continue block0;
                        }
                        return true;
                    }
                    trees.SkyTree(level, posX, posY - 1, posZ);
                    if (count < 3) {
                        continue block0;
                    }
                    return true;
                }
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }

    public boolean addKingAltar(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int chunkX, int chunkZ) {
        boolean kingSlot = locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.KING_ALTAR);
        boolean queenSlot = locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.QUEEN_ALTAR);
        if (!kingSlot && !queenSlot) {
            return false;
        }
        int posX = chunkX + 8;
        int posZ = chunkZ + 8;
        int grassY = -1;
        int top = level.getHeight(Heightmap.Types.WORLD_SURFACE, posX, posZ);
        for (int y = top; y > 40; --y) {
            if (level.getBlockState(new net.minecraft.core.BlockPos(posX, y, posZ))
                    .is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
                grassY = y;
                break;
            }
        }
        if (grassY < 0) {
            return false;
        }
        if (kingSlot && (!queenSlot || random.nextInt(2) == 0)) {
            ChaosPersists.MyDungeon.makeKingAltar(level, posX, grassY, posZ);
        } else {
            ChaosPersists.MyDungeon.makeQueenAltar(level, posX, grassY, posZ);
        }
        recently_placed = 100;
        return true;
    }

    public void addBasiliskMaze(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.BASILISK_MAZE)) {
            return;
        }
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    net.minecraft.core.BlockPos above = new net.minecraft.core.BlockPos(posX, posY + 1, posZ);
                    net.minecraft.core.BlockPos ground = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(above).isAir() || level.getBlockState(ground).isAir()) {
                        continue;
                    }
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
            int mazeY = lowestY - 2;
            ChaosPersists.BMaze.buildBasiliskMaze(level, lowestX, mazeY, lowestZ);
            recently_placed = 50;
        }
    }

    public void addKyuubiDungeon(net.minecraft.world.level.Level level, int chunkX, int chunkZ) {
        if (!locateSlot(level, chunkX, chunkZ, ChaosLocateStructures.KYUUBI_DUNGEON)) {
            return;
        }
        int lowestY = 128;
        int lowestX = chunkX;
        int lowestZ = chunkZ;
        boolean found = false;
        for (int i = 0; i < 16; i += 3) {
            block1 : for (int j = 0; j < 16; j += 3) {
                int posX = chunkX + i;
                int posZ = chunkZ + j;
                for (int posY = 128; posY > 30; --posY) {
                    net.minecraft.core.BlockPos above = new net.minecraft.core.BlockPos(posX, posY + 1, posZ);
                    net.minecraft.core.BlockPos ground = new net.minecraft.core.BlockPos(posX, posY, posZ);
                    if (!level.getBlockState(above).isAir()
                            || level.getBlockState(ground).isAir()) {
                        continue;
                    }
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
            int dungeonY = lowestY - 2;
            ChaosPersists.MyDungeon.makeKyuubiDungeon(level, lowestX, dungeonY, lowestZ);
            recently_placed = 50;
        }
    }

    /**
     * Preloads every chunk in a structure footprint and runs deferred {@link #generate} on each
     * chunk once so multi-chunk builds are not clipped by unfinished neighbor generation.
     * Does not wipe terrain — structures clear only what they intentionally replace (1.7.10 parity).
     */
    public void prepareStructureFootprint(
            net.minecraft.world.level.Level level,
            int minX,
            int minY,
            int minZ,
            int maxX,
            int maxY,
            int maxZ) {
        if (!(level instanceof ServerLevel serverLevel) || level.isClientSide() || isDuringPopulateFeature()) {
            return;
        }
        int loX = Math.min(minX, maxX);
        int hiX = Math.max(minX, maxX);
        int loZ = Math.min(minZ, maxZ);
        int hiZ = Math.max(minZ, maxZ);
        this.ensureChunksGenerated(serverLevel, loX, loZ, hiX, hiZ);
        int minChunkX = loX >> 4;
        int maxChunkX = hiX >> 4;
        int minChunkZ = loZ >> 4;
        int maxChunkZ = hiZ >> 4;
        FLUSHING_FOR_STRUCTURE.set(true);
        try {
            for (int cx = minChunkX; cx <= maxChunkX; ++cx) {
                for (int cz = minChunkZ; cz <= maxChunkZ; ++cz) {
                    this.flushChunkWorldGenForStructure(serverLevel, cx, cz);
                }
            }
        } finally {
            FLUSHING_FOR_STRUCTURE.set(false);
        }
    }

    private void flushChunkWorldGenForStructure(ServerLevel serverLevel, int chunkX, int chunkZ) {
        PendingChunkGen pending = new PendingChunkGen(serverLevel.dimension(), chunkX, chunkZ);
        if (!PROCESSED_WORLD_GEN.add(pending)) {
            return;
        }
        SCHEDULED_CHUNK_GEN.remove(pending);
        CHUNK_GEN_QUEUE.remove(pending);
        if (serverLevel.hasChunk(chunkX, chunkZ)) {
            this.runChunkWorldGen(serverLevel, serverLevel.getChunk(chunkX, chunkZ));
        }
    }

    /**
     * Forces generation/loading of every chunk touched by large multi-chunk structures so
     * later terrain generation does not overwrite pre-placed blocks.
     */
    private void ensureChunksGenerated(
            net.minecraft.world.level.Level level, int minX, int minZ, int maxX, int maxZ) {
        if (level == null || isDuringPopulateFeature()) {
            return;
        }
        int minChunkX = minX >> 4;
        int maxChunkX = maxX >> 4;
        int minChunkZ = minZ >> 4;
        int maxChunkZ = maxZ >> 4;
        for (int cx = minChunkX; cx <= maxChunkX; ++cx) {
            for (int cz = minChunkZ; cz <= maxChunkZ; ++cz) {
                level.getChunk(new net.minecraft.core.BlockPos((cx << 4) + 8, 0, (cz << 4) + 8));
            }
        }
    }

    private boolean quickSpaceCheck(net.minecraft.world.level.Level level, int posX, int posY, int posZ) {
        for (int i = -2; i < 10; ++i) {
            for (int k = -2; k < 10; ++k) {
                if (level.getBlockState(new net.minecraft.core.BlockPos(posX + i, posY + 4, posZ + k)).isAir()) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean quickBigSpaceCheck(net.minecraft.world.level.Level level, int posX, int posY, int posZ) {
        for (int i = -5; i < 25; ++i) {
            for (int k = -5; k < 25; ++k) {
                if (level.getBlockState(new net.minecraft.core.BlockPos(posX + i, posY + 8, posZ + k)).isAir()) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private boolean quickReallyBigSpaceCheck(net.minecraft.world.level.Level level, int posX, int posY, int posZ) {
        // Sample several heights through the king/queen altar clear volume (not only Y+8).
        // Checking a single slice let overhanging canopies pass, then the altar air-clear sliced them.
        int[] sampleHeights = {4, 8, 16, 24, 32, 40, 48};
        for (int sampleY : sampleHeights) {
            for (int i = -5; i < 55; ++i) {
                for (int k = -5; k < 55; ++k) {
                    if (level.getBlockState(new net.minecraft.core.BlockPos(posX + i, posY + sampleY, posZ + k))
                            .isAir()) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean D4BigSpaceCheck(net.minecraft.world.level.Level level, int posX, int posY, int posZ) {
        Block appleLeaves = ChaosPersists.MyAppleLeaves;
        Block scaryLeaves = ChaosPersists.MyScaryLeaves;
        for (int i = -25; i < 40; ++i) {
            for (int k = -25; k < 30; ++k) {
                Block bid = level.getBlockState(new net.minecraft.core.BlockPos(posX + i, posY + 4, posZ + k))
                        .getBlock();
                if (bid == Blocks.AIR || bid == Blocks.OAK_LOG || bid == appleLeaves || bid == scaryLeaves) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    private Entity spawnCreature(Level level, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.resources.ResourceLocation requested;
        if (par1 != null && par1.contains(":")) {
            String[] parts = par1.split(":", 2);
            requested = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(parts[0], parts[1]);
        } else {
            String normalizedPath = (par1 == null ? "" : par1.trim().toLowerCase(Locale.ROOT).replace(' ', '_'));
            requested = net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", normalizedPath);
        }
        requested = SpawnerFixHelper.normalizeEntityLookupId(requested);
        EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(requested);
        if (entityType != null) {
            var8 = entityType.create(level);
        }
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
            var8.moveTo(par2, par4 + 0.01, par6, level.getRandom().nextFloat() * 360.0F, 0.0F);
            level.addFreshEntity(var8);
            if (var8 instanceof Mob mob) {
                MyUtils.playAmbientSound(mob);
            }
        }
        return var8;
    }
}

