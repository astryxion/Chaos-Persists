package com.astryxion.chaospersists.world.dimension.chunkgenerator;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Beardifier;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Predicate;

/**
 * Twilight Forest-style terrain for Utopia, Village, and Crystal: zeroed noise router plus classic
 * rolling-hill warp. Optional per-block micro-bump is for Utopia/Crystal only — Village Mania uses
 * 1.7 {@code ChunkProviderOreSpawn3} settings (vanilla overworld interpolation, no extra ripple)
 * so jigsaw villages sit on broad slopes instead of choppy 12-block noise.
 */
public class ChaosPlainsChunkGenerator extends ChaosChunkGeneratorWrapper {

    public static final Codec<ChaosPlainsChunkGenerator> CODEC =
            RecordCodecBuilder.create(instance ->
                    instance.group(
                                    ChunkGenerator.CODEC.fieldOf("wrapped_generator").forGetter(generator -> generator.delegate),
                                    NoiseGeneratorSettings.CODEC
                                            .fieldOf("noise_generation_settings")
                                            .forGetter(generator -> generator.noiseGeneratorSettings),
                                    Codec.DOUBLE
                                            .optionalFieldOf("depth", 0.125D)
                                            .forGetter(generator -> generator.depth),
                                    Codec.DOUBLE
                                            .optionalFieldOf("scale", 0.05D)
                                            .forGetter(generator -> generator.scale),
                                    Codec.DOUBLE
                                            .optionalFieldOf("density_factor", 2.5D)
                                            .forGetter(generator -> generator.densityFactor),
                                    Codec.DOUBLE
                                            .optionalFieldOf("density_offset", -1.25D)
                                            .forGetter(generator -> generator.densityOffset),
                                    Codec.INT
                                            .optionalFieldOf("terrain_height_offset", 55)
                                            .forGetter(generator -> generator.terrainHeightOffset),
                                    Codec.DOUBLE
                                            .optionalFieldOf("noise_strength", 1.0D)
                                            .forGetter(generator -> generator.noiseStrength),
                                    Codec.DOUBLE
                                            .optionalFieldOf("micro_bump_strength", 1.0D)
                                            .forGetter(generator -> generator.microBumpStrength),
                                    Codec.DOUBLE
                                            .optionalFieldOf("depth_noise_strength", 0.0D)
                                            .forGetter(generator -> generator.depthNoiseStrength))
                            .apply(instance, ChaosPlainsChunkGenerator::new));

    private static final BlockState[] EMPTY_COLUMN = new BlockState[0];

    private final Holder<NoiseGeneratorSettings> noiseGeneratorSettings;
    private final double depth;
    private final double scale;
    private final double densityFactor;
    private final double densityOffset;
    private final int terrainHeightOffset;
    private final double noiseStrength;
    /** 0 disables the short-wavelength overlay (Village Mania / 1.7 OreSpawn). */
    private final double microBumpStrength;
    /** 1.7 overworld depth noise. 0 off; 1.0 matches {@code ChunkProviderOreSpawn3} lake basins. */
    private final double depthNoiseStrength;

    private final BlockState defaultBlock;
    private final BlockState defaultFluid;
    private final Optional<ChaosPlainsTerrainWarp> warper;

    /** Lazy per-world micro-bump sampler — not part of the rolling-hill warp. */
    private ImprovedNoise microBumpNoise;
    private int microBumpOwner = 0;

    public ChaosPlainsChunkGenerator(
            ChunkGenerator delegate,
            Holder<NoiseGeneratorSettings> noiseGenSettings,
            double depth,
            double scale,
            double densityFactor,
            double densityOffset,
            int terrainHeightOffset,
            double noiseStrength,
            double microBumpStrength,
            double depthNoiseStrength) {
        super(delegate);

        this.noiseGeneratorSettings = noiseGenSettings;
        this.depth = depth;
        this.scale = scale;
        this.densityFactor = densityFactor;
        this.densityOffset = densityOffset;
        this.terrainHeightOffset = terrainHeightOffset;
        this.noiseStrength = noiseStrength;
        this.microBumpStrength = microBumpStrength;
        this.depthNoiseStrength = depthNoiseStrength;

        if (delegate instanceof NoiseBasedChunkGenerator noiseGen && noiseGen.generatorSettings().isBound()) {
            this.defaultBlock = noiseGen.generatorSettings().value().defaultBlock();
            this.defaultFluid = noiseGen.generatorSettings().value().defaultFluid();
        } else {
            this.defaultBlock = Blocks.STONE.defaultBlockState();
            this.defaultFluid = Blocks.WATER.defaultBlockState();
        }

        if (noiseGenSettings.isBound()) {
            NoiseSettings settings = noiseGenSettings.value().noiseSettings();
            this.warper =
                    Optional.of(
                            new ChaosPlainsTerrainWarp(
                                    settings.getCellWidth(),
                                    settings.getCellHeight(),
                                    settings.height() / settings.getCellHeight(),
                                    new ChaosPlainsNoiseSlider(-10.0D, 3, 0),
                                    new ChaosPlainsNoiseSlider(15.0D, 3, 0),
                                    settings,
                                    depth,
                                    scale,
                                    densityFactor,
                                    densityOffset,
                                    terrainHeightOffset,
                                    noiseStrength,
                                    depthNoiseStrength));
        } else {
            this.warper = Optional.empty();
        }
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void addDebugScreenInfo(List<String> lines, RandomState random, BlockPos pos) {
        this.delegate.addDebugScreenInfo(lines, random, pos);
    }

    @Override
    public int getSeaLevel() {
        return this.noiseGeneratorSettings.value().seaLevel();
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Types heightMap, LevelHeightAccessor level, RandomState random) {
        if (this.warper.isEmpty()) {
            return super.getBaseHeight(x, z, heightMap, level, random);
        }

        NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
        int minY = Math.max(settings.minY(), level.getMinBuildHeight());
        int maxY = Math.min(settings.minY() + settings.height(), level.getMaxBuildHeight());
        int minCell = Math.floorDiv(minY, settings.getCellHeight());
        int maxCell = Math.floorDiv(maxY - minY, settings.getCellHeight());
        return maxCell <= 0
                ? level.getMinBuildHeight()
                : this.iterateNoiseColumn(random, x, z, null, heightMap.isOpaque(), minCell, maxCell)
                        .orElse(level.getMinBuildHeight());
    }

    @Override
    public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor level, RandomState random) {
        if (this.warper.isEmpty()) {
            return super.getBaseColumn(x, z, level, random);
        }

        NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
        int minY = Math.max(settings.minY(), level.getMinBuildHeight());
        int maxY = Math.min(settings.minY() + settings.height(), level.getMaxBuildHeight());
        int minCell = Math.floorDiv(minY, settings.getCellHeight());
        int maxCell = Math.floorDiv(maxY - minY, settings.getCellHeight());
        if (maxCell <= 0) {
            return new NoiseColumn(minY, EMPTY_COLUMN);
        }

        BlockState[] column = new BlockState[maxCell * settings.getCellHeight()];
        this.iterateNoiseColumn(random, x, z, column, null, minCell, maxCell);
        return new NoiseColumn(minY, column);
    }

    private OptionalInt iterateNoiseColumn(
            RandomState random,
            int x,
            int z,
            @Nullable BlockState[] states,
            @Nullable Predicate<BlockState> predicate,
            int min,
            int max) {
        NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
        int cellWidth = settings.getCellWidth();
        int cellHeight = settings.getCellHeight();
        int xDiv = Math.floorDiv(x, cellWidth);
        int zDiv = Math.floorDiv(z, cellWidth);
        int xMod = Math.floorMod(x, cellWidth);
        int zMod = Math.floorMod(z, cellWidth);
        // Must be a fraction inside the cell (vanilla NoiseBasedChunkGenerator). Integer
        // xMod/cellWidth is always 0, so villages queried the 4x4 cell corner instead of
        // the actual column — start Y missed the real surface and pieces floated or buried.
        double xLerp = (double) xMod / (double) cellWidth;
        double zLerp = (double) zMod / (double) cellWidth;
        double[][] columns =
                new double[][] {
                    this.makeAndFillNoiseColumn(random, xDiv, zDiv, min, max),
                    this.makeAndFillNoiseColumn(random, xDiv, zDiv + 1, min, max),
                    this.makeAndFillNoiseColumn(random, xDiv + 1, zDiv, min, max),
                    this.makeAndFillNoiseColumn(random, xDiv + 1, zDiv + 1, min, max)
                };

        for (int cell = max - 1; cell >= 0; cell--) {
            double d00 = columns[0][cell];
            double d10 = columns[1][cell];
            double d20 = columns[2][cell];
            double d30 = columns[3][cell];
            double d01 = columns[0][cell + 1];
            double d11 = columns[1][cell + 1];
            double d21 = columns[2][cell + 1];
            double d31 = columns[3][cell + 1];

            for (int height = cellHeight - 1; height >= 0; height--) {
                double cellFraction = height / (double) cellHeight;
                double noiseVal = Mth.lerp3(cellFraction, xLerp, zLerp, d00, d01, d20, d21, d10, d11, d30, d31);
                noiseVal += this.sampleMicroBump(random, x, z);
                int layer = cell * cellHeight + height;
                int blockY = layer + min * cellHeight;
                BlockState state = this.generateBaseState(noiseVal, blockY);

                if (states != null) {
                    states[layer] = state;
                }

                if (predicate != null && predicate.test(state)) {
                    return OptionalInt.of(blockY + 1);
                }
            }
        }

        return OptionalInt.empty();
    }

    @Override
    public CompletableFuture<ChunkAccess> createBiomes(
            Executor executor, RandomState random, Blender blender, StructureManager manager, ChunkAccess chunkAccess) {
        return CompletableFuture.supplyAsync(
                Util.wrapThreadWithTaskName(
                        "init_biomes",
                        () -> {
                            chunkAccess.fillBiomesFromNoise(this.getBiomeSource(), Climate.empty());
                            return chunkAccess;
                        }),
                Util.backgroundExecutor());
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(
            Executor executor, Blender blender, RandomState random, StructureManager structureManager, ChunkAccess chunkAccess) {
        if (this.warper.isEmpty()) {
            return super.fillFromNoise(executor, blender, random, structureManager, chunkAccess);
        }

        NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
        int cellHeight = settings.getCellHeight();
        int minY = Math.max(settings.minY(), chunkAccess.getMinBuildHeight());
        int maxY = Math.min(settings.minY() + settings.height(), chunkAccess.getMaxBuildHeight());
        int minCell = Math.floorDiv(minY, cellHeight);
        int maxCell = Math.floorDiv(maxY - minY, cellHeight);

        if (maxCell <= 0) {
            return CompletableFuture.completedFuture(chunkAccess);
        }

        int maxIndex = chunkAccess.getSectionIndex(maxCell * cellHeight - 1 + minY);
        int minIndex = chunkAccess.getSectionIndex(minY);
        Set<LevelChunkSection> sections = Sets.newHashSet();

        for (int index = maxIndex; index >= minIndex; index--) {
            LevelChunkSection section = chunkAccess.getSection(index);
            section.acquire();
            sections.add(section);
        }

        return CompletableFuture.supplyAsync(
                        () -> this.doFill(random, structureManager, chunkAccess, minCell, maxCell),
                        Util.backgroundExecutor())
                .whenCompleteAsync(
                        (chunk, throwable) -> {
                            for (LevelChunkSection section : sections) {
                                section.release();
                            }
                        },
                        executor);
    }

    private ChunkAccess doFill(
            RandomState random,
            StructureManager structureManager,
            ChunkAccess access,
            int min,
            int max) {
        NoiseSettings settings = this.noiseGeneratorSettings.value().noiseSettings();
        int cellWidth = settings.getCellWidth();
        int cellHeight = settings.getCellHeight();
        int cellCountX = 16 / cellWidth;
        int cellCountZ = 16 / cellWidth;
        Heightmap oceanFloor = access.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap surface = access.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);
        ChunkPos chunkPos = access.getPos();
        int minX = chunkPos.getMinBlockX();
        int minZ = chunkPos.getMinBlockZ();
        // Vanilla structure terrain_adaptation (beard_thin from village JSON) via Beardifier.
        Beardifier beardifier = Beardifier.forStructuresInChunk(structureManager, chunkPos);
        ChaosPlainsTerrainWarp terrainWarp = this.warper.get();
        ChaosPlainsNoiseInterpolator interpolator =
                new ChaosPlainsNoiseInterpolator(
                        cellCountX,
                        max,
                        cellCountZ,
                        chunkPos,
                        min,
                        (state, columns, x, z, minY, maxY) ->
                                terrainWarp.fillNoiseColumn(state, columns, x, z, minY, maxY));
        List<ChaosPlainsNoiseInterpolator> interpolators = Lists.newArrayList(interpolator);
        interpolators.forEach(noiseInterpolator -> noiseInterpolator.initialiseFirstX(random));

        for (int cellX = 0; cellX < cellCountX; cellX++) {
            int advanceX = cellX;
            interpolators.forEach(noiseInterpolator -> noiseInterpolator.advanceX(random, advanceX));

            for (int cellZ = 0; cellZ < cellCountZ; cellZ++) {
                int sectionIndex = access.getSectionsCount() - 1;
                LevelChunkSection section = access.getSection(sectionIndex);

                for (int cellY = max - 1; cellY >= 0; cellY--) {
                    int advanceY = cellY;
                    int advanceZ = cellZ;
                    interpolators.forEach(noiseInterpolator -> noiseInterpolator.selectYZ(advanceY, advanceZ));

                    for (int height = cellHeight - 1; height >= 0; height--) {
                        int blockY = (min + cellY) * cellHeight + height;
                        int localY = blockY & 15;
                        int sectionY = access.getSectionIndex(blockY);

                        if (sectionIndex != sectionY) {
                            sectionIndex = sectionY;
                            section = access.getSection(sectionY);
                        }

                        double heightFraction = (double) height / (double) cellHeight;
                        interpolators.forEach(noiseInterpolator -> noiseInterpolator.updateY(heightFraction));

                        for (int widthX = 0; widthX < cellWidth; widthX++) {
                            int worldX = minX + cellX * cellWidth + widthX;
                            int localX = worldX & 15;
                            double widthFractionX = (double) widthX / (double) cellWidth;
                            interpolators.forEach(noiseInterpolator -> noiseInterpolator.updateX(widthFractionX));

                            for (int widthZ = 0; widthZ < cellWidth; widthZ++) {
                                int worldZ = minZ + cellZ * cellWidth + widthZ;
                                int localZ = worldZ & 15;
                                double widthFractionZ = (double) widthZ / (double) cellWidth;
                                double noiseVal =
                                        interpolator.updateZ(widthFractionZ)
                                                + beardifier.compute(
                                                        new DensityFunction.SinglePointContext(
                                                                worldX, blockY, worldZ))
                                                + this.sampleMicroBump(random, worldX, worldZ);
                                BlockState state = this.generateBaseState(noiseVal, blockY);

                                if (state != Blocks.AIR.defaultBlockState()) {
                                    section.setBlockState(localX, localY, localZ, state, false);
                                    oceanFloor.update(localX, blockY, localZ, state);
                                    surface.update(localX, blockY, localZ, state);
                                }
                            }
                        }
                    }
                }
            }

            interpolators.forEach(ChaosPlainsNoiseInterpolator::swapSlices);
        }

        return access;
    }

    private double[] makeAndFillNoiseColumn(RandomState random, int x, int z, int min, int max) {
        double[] column = new double[max + 1];
        this.warper.get().fillNoiseColumn(random, column, x, z, min, max);
        return column;
    }

    /**
     * Optional short-wavelength overlay. Village Mania sets strength to 0 so the 4×8 noise
     * cells stay as smooth 1.7 rolling hills. Utopia/Crystal keep the default 1.0.
     */
    private double sampleMicroBump(RandomState random, int blockX, int blockZ) {
        if (this.microBumpStrength <= 0.0D) {
            return 0.0D;
        }
        ImprovedNoise noise = this.getMicroBumpNoise(random);
        // ~12–16 block patches (one octave only — a fine overlay looked harsh).
        double n = noise.noise(blockX * 0.085D, 0.0D, blockZ * 0.085D);
        // tanh keeps extremes near ±1 block instead of spiking 2–3 high.
        double heightBlocks = Math.tanh(n * 1.2D) * 0.75D;
        return heightBlocks * 7.0D * this.microBumpStrength;
    }

    private ImprovedNoise getMicroBumpNoise(RandomState random) {
        int owner = System.identityHashCode(random);
        if (this.microBumpNoise == null || this.microBumpOwner != owner) {
            RandomSource source =
                    random
                            .getOrCreateRandomFactory(
                                    new ResourceLocation(
                                            "chaospersists", "plains_micro_bump"))
                            .fromHashOf("init");
            this.microBumpNoise = new ImprovedNoise(source);
            this.microBumpOwner = owner;
        }
        return this.microBumpNoise;
    }

    /**
     * Match 1.7/1.12 {@code ChunkProviderOreSpawn3}: stone where density &gt; 0, otherwise water
     * below sea level and air above. 1.7 depth noise pulls large basins under Y 63, which become
     * the winding Village Mania / Utopia ponds — villages then generate on those shores.
     */
    private BlockState generateBaseState(double noiseVal, double level) {
        if (noiseVal > 0.0D) {
            return this.defaultBlock;
        }
        int seaLevel = this.noiseGeneratorSettings.value().seaLevel();
        if (seaLevel > 0 && level < seaLevel) {
            return this.defaultFluid;
        }
        return Blocks.AIR.defaultBlockState();
    }
}
