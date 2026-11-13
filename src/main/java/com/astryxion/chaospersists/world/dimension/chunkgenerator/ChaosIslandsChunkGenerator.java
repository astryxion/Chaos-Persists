package com.astryxion.chaospersists.world.dimension.chunkgenerator;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.biome.BiomeManager;
import org.jetbrains.annotations.Nullable;

/** 1.12 {@code ChunkProviderChaos6} — floating sky islands over void (Y 0–127). */
public class ChaosIslandsChunkGenerator extends ChaosChunkGeneratorWrapper {

    public static final int ISLAND_TOP = 128;
    public static final int SEA_LEVEL = 64;

    public static final MapCodec<ChaosIslandsChunkGenerator> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    instance.group(ChunkGenerator.CODEC.fieldOf("wrapped_generator").forGetter(generator -> generator.delegate))
                            .apply(instance, ChaosIslandsChunkGenerator::new));

    @Nullable
    private ChaosIslandsNoise islandsNoise;
    private long noiseSeed = Long.MIN_VALUE;

    public ChaosIslandsChunkGenerator(ChunkGenerator delegate) {
        super(delegate);
    }

    @Override
    protected MapCodec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void addDebugScreenInfo(List<String> lines, RandomState random, BlockPos pos) {
        this.delegate.addDebugScreenInfo(lines, random, pos);
    }

    @Override
    public void applyCarvers(
            WorldGenRegion region,
            long seed,
            RandomState random,
            BiomeManager biomeManager,
            StructureManager manager,
            ChunkAccess chunkAccess,
            GenerationStep.Carving carving) {
        // Terrain is fully custom; skip vanilla carvers from the wrapped generator.
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender blender,
            RandomState random,
            StructureManager structureManager,
            ChunkAccess chunkAccess) {
        long seed = seedFrom(structureManager);
        return CompletableFuture.completedFuture(this.generateIslands(seed, chunkAccess));
    }

    private ChunkAccess generateIslands(long worldSeed, ChunkAccess chunk) {
        this.clearIslandVolume(chunk);

        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        ChaosIslandsNoise noise = this.noiseFor(worldSeed);
        int cell = 4;
        int cellsX = cell + 1;
        int cellsY = 17;
        int cellsZ = cell + 1;
        double[] noiseField =
                noise.initializeNoiseField(
                        null, chunkX * cell, 0, chunkZ * cell, cellsX, cellsY, cellsZ);

        BlockState stone = Blocks.STONE.defaultBlockState();
        BlockState air = Blocks.AIR.defaultBlockState();

        for (int i = 0; i < cell; ++i) {
            for (int j = 0; j < cell; ++j) {
                for (int k = 0; k < 16; ++k) {
                    double d0 = 0.125;
                    int base = ((i + 0) * cellsZ + j + 0) * cellsY;
                    double d1 = noiseField[base + k + 0];
                    double d2 = noiseField[((i + 0) * cellsZ + j + 1) * cellsY + k + 0];
                    double d3 = noiseField[((i + 1) * cellsZ + j + 0) * cellsY + k + 0];
                    double d4 = noiseField[((i + 1) * cellsZ + j + 1) * cellsY + k + 0];
                    double d5 = (noiseField[base + k + 1] - d1) * d0;
                    double d6 = (noiseField[((i + 0) * cellsZ + j + 1) * cellsY + k + 1] - d2) * d0;
                    double d7 = (noiseField[((i + 1) * cellsZ + j + 0) * cellsY + k + 1] - d3) * d0;
                    double d8 = (noiseField[((i + 1) * cellsZ + j + 1) * cellsY + k + 1] - d4) * d0;
                    for (int l = 0; l < 8; ++l) {
                        double d9 = 0.25;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i2 = 0; i2 < 4; ++i2) {
                            double d14 = 0.25;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for (int k2 = 0; k2 < 4; ++k2) {
                                int x = i2 + i * 4;
                                int y = k * 8 + l;
                                int z = j * 4 + k2;
                                if (y >= ISLAND_TOP) {
                                    continue;
                                }
                                BlockState state = d15 > 0.0 ? air : stone;
                                this.setBlock(chunk, x, y, z, state);
                                d15 += d16;
                            }
                            d10 += d12;
                            d11 += d13;
                        }
                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
        return chunk;
    }

    private void clearIslandVolume(ChunkAccess chunk) {
        BlockState air = Blocks.AIR.defaultBlockState();
        int minY = Math.max(chunk.getMinBuildHeight(), 0);
        int maxY = Math.min(chunk.getMaxBuildHeight(), ISLAND_TOP);
        for (int localX = 0; localX < 16; ++localX) {
            for (int localZ = 0; localZ < 16; ++localZ) {
                for (int y = minY; y < maxY; ++y) {
                    this.setBlock(chunk, localX, y, localZ, air);
                }
            }
        }
    }

    @Override
    public void buildSurface(
            WorldGenRegion level, StructureManager manager, RandomState random, ChunkAccess chunkAccess) {
        int chunkX = chunkAccess.getPos().x;
        int chunkZ = chunkAccess.getPos().z;
        RandomSource rng = RandomSource.create((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
        Random hellRng = new Random(rng.nextLong());

        double scale = 0.03125D;
        ChaosIslandsNoise noise = this.noiseFor(level.getSeed());
        double[] grassNoise = new double[256];
        double[] stoneNoise = new double[256];
        double[] depthNoise = new double[256];
        noise.slowsandGravelNoiseGen.generateNoiseOctaves(
                grassNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, scale, scale, 1.0D);
        noise.slowsandGravelNoiseGen.generateNoiseOctaves(
                stoneNoise, chunkX * 16, 109, chunkZ * 16, 16, 1, 16, scale, 1.0D, scale);
        noise.netherrackExclusivityNoiseGen.generateNoiseOctaves(
                depthNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, scale * 2.0D, scale * 2.0D, scale * 2.0D);

        BlockState grass = Blocks.GRASS_BLOCK.defaultBlockState();
        BlockState dirt = Blocks.DIRT.defaultBlockState();
        BlockState water = Blocks.WATER.defaultBlockState();
        BlockState stone = Blocks.STONE.defaultBlockState();
        BlockState air = Blocks.AIR.defaultBlockState();

        for (int localX = 0; localX < 16; ++localX) {
            for (int localZ = 0; localZ < 16; ++localZ) {
                boolean grassPatch = grassNoise[localX + localZ * 16] + hellRng.nextDouble() * 0.2D > 0.0D;
                boolean stonePatch = stoneNoise[localX + localZ * 16] + hellRng.nextDouble() * 0.2D > 0.0D;
                int depth = (int) (depthNoise[localX + localZ * 16] / 3.0D + 3.0D + hellRng.nextDouble() * 0.25D);
                int remaining = -1;
                BlockState surface = grass;
                BlockState filler = dirt;

                for (int y = ISLAND_TOP - 1; y >= 0; --y) {
                    if (y < ISLAND_TOP - 1 - hellRng.nextInt(5) && y > hellRng.nextInt(5)) {
                        BlockState current = chunkAccess.getBlockState(new BlockPos(localX, y, localZ));
                        if (!current.isAir()) {
                            if (current.is(Blocks.STONE)) {
                                if (remaining == -1) {
                                    if (depth <= 0) {
                                        surface = air;
                                        filler = stone;
                                    } else if (y >= SEA_LEVEL - 4 && y <= SEA_LEVEL + 1) {
                                        surface = stone;
                                        filler = stone;
                                        if (stonePatch) {
                                            surface = grass;
                                            filler = dirt;
                                        }
                                        if (grassPatch) {
                                            surface = grass;
                                            filler = dirt;
                                        }
                                    }
                                    if (y < SEA_LEVEL && surface.isAir()) {
                                        surface = water;
                                    }
                                    remaining = depth;
                                    chunkAccess.setBlockState(
                                            new BlockPos(localX, y, localZ), y >= SEA_LEVEL - 1 ? surface : filler, false);
                                } else if (remaining > 0) {
                                    --remaining;
                                    chunkAccess.setBlockState(new BlockPos(localX, y, localZ), filler, false);
                                }
                            }
                        } else {
                            remaining = -1;
                        }
                    } else {
                        chunkAccess.setBlockState(new BlockPos(localX, y, localZ), air, false);
                    }
                }
            }
        }

        Heightmap oceanFloor = chunkAccess.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap surfaceMap = chunkAccess.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
        for (int localX = 0; localX < 16; ++localX) {
            for (int localZ = 0; localZ < 16; ++localZ) {
                for (int y = 0; y < ISLAND_TOP; ++y) {
                    BlockState state = chunkAccess.getBlockState(new BlockPos(localX, y, localZ));
                    if (!state.isAir()) {
                        oceanFloor.update(localX, y, localZ, state);
                        surfaceMap.update(localX, y, localZ, state);
                    }
                }
            }
        }
    }

    private void setBlock(ChunkAccess chunk, int localX, int y, int localZ, BlockState state) {
        if (y < chunk.getMinBuildHeight() || y >= chunk.getMaxBuildHeight()) {
            return;
        }
        LevelChunkSection section = chunk.getSection(chunk.getSectionIndex(y));
        section.setBlockState(localX, y & 15, localZ, state, false);
    }

    private ChaosIslandsNoise noiseFor(long seed) {
        if (this.islandsNoise == null || this.noiseSeed != seed) {
            this.noiseSeed = seed;
            this.islandsNoise = new ChaosIslandsNoise(seed);
        }
        return this.islandsNoise;
    }

    private static long seedFrom(StructureManager structureManager) {
        try {
            Field optionsField = StructureManager.class.getDeclaredField("worldOptions");
            optionsField.setAccessible(true);
            Object options = optionsField.get(structureManager);
            if (options instanceof WorldOptions worldOptions) {
                return worldOptions.seed();
            }
        } catch (ReflectiveOperationException ignored) {
        }
        try {
            Field levelField = StructureManager.class.getDeclaredField("level");
            levelField.setAccessible(true);
            Object level = levelField.get(structureManager);
            if (level instanceof ServerLevel serverLevel) {
                return serverLevel.getSeed();
            }
        } catch (ReflectiveOperationException ignored) {
        }
        return 0L;
    }

    @Override
    public int getSeaLevel() {
        return SEA_LEVEL;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getGenDepth() {
        return ISLAND_TOP;
    }
}
