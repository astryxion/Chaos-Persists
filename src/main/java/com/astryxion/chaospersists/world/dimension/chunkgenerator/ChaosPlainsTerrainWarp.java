package com.astryxion.chaospersists.world.dimension.chunkgenerator;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;

import java.util.stream.IntStream;

/**
 * Fixed-biome rolling-hill warp modeled on Twilight Forest {@code TFTerrainWarp} and 1.12 Utopia
 * ({@code baseHeight 0.125}, {@code heightVariation 0.05}). Optional 1.7 {@code noiseGen6} depth
 * noise lowers large basins below sea level — that is Village Mania / Utopia's big ponds, not
 * {@code WorldGenLakes}.
 */
public class ChaosPlainsTerrainWarp {

    private final int cellWidth;
    private final int cellHeight;
    private final int cellCountY;
    private final NoiseSettings noiseSettings;
    private final ChaosPlainsNoiseSlider topSlide;
    private final ChaosPlainsNoiseSlider bottomSlide;
    private final double depth;
    private final double scale;
    private final double dimensionDensityFactor;
    private final double dimensionDensityOffset;
    /** Lifts TF-style low terrain (around Y 5) up to overworld sea-level plains (around Y 60). */
    private final int terrainHeightOffset;
    /** Multiplier on blended horizontal noise (crystal uses higher values for 1.12 heightVariation 0.5). */
    private final double noiseStrength;
    /**
     * 1.7 {@code ChunkProviderGenerate} depth-noise amount. 1.0 is vanilla; 0 disables. Large
     * negative pockets become sea-level lakes before villages generate.
     */
    private final double depthNoiseStrength;

    private ChaosPlainsBlendedNoise blendedNoise;
    private PerlinNoise depthNoise;
    private int depthNoiseOwner;

    public ChaosPlainsTerrainWarp(
            int width,
            int height,
            int yCount,
            ChaosPlainsNoiseSlider topSlide,
            ChaosPlainsNoiseSlider bottomSlide,
            NoiseSettings settings,
            double depth,
            double scale,
            double dimensionDensityFactor,
            double dimensionDensityOffset,
            int terrainHeightOffset,
            double noiseStrength,
            double depthNoiseStrength) {
        this.cellWidth = width;
        this.cellHeight = height;
        this.cellCountY = yCount;
        this.noiseSettings = settings;
        this.topSlide = topSlide;
        this.bottomSlide = bottomSlide;
        this.depth = depth;
        this.scale = scale;
        this.dimensionDensityFactor = dimensionDensityFactor;
        this.dimensionDensityOffset = dimensionDensityOffset;
        this.terrainHeightOffset = terrainHeightOffset;
        this.noiseStrength = noiseStrength;
        this.depthNoiseStrength = depthNoiseStrength;
    }

    public void fillNoiseColumn(RandomState random, double[] column, int x, int z, int min, int max) {
        ChaosPlainsBlendedNoise blend = this.getBlendedNoise(random);
        double modifiedDepth = this.depth * 0.5D - 0.125D;
        double modifiedScale = this.scale * 0.9D + 0.1D;
        double offset = modifiedDepth * 0.265625D;
        double factor = 96.0D / modifiedScale;

        double scaleXZ = 684.412D * ChaosPlainsBlendedNoise.XZ_SCALE;
        double scaleY = 684.412D * ChaosPlainsBlendedNoise.Y_SCALE;
        double factorXZ = scaleXZ / ChaosPlainsBlendedNoise.XZ_FACTOR;
        double factorY = scaleY / ChaosPlainsBlendedNoise.Y_FACTOR;
        double density = -0.46875D;
        // 1.7: d5 += d13 * 0.2 * 8.5/8 * 4  → about 6.8 blocks at the most negative depth.
        double depthShiftBlocks = this.sampleDepthHeightShift(random, x, z);

        for (int index = 0; index <= max; ++index) {
            int y = index + min;
            double noise = blend.sampleAndClampNoise(x, y, z, scaleXZ, scaleY, factorXZ, factorY);
            double totalDensity =
                    this.computeInitialDensity(y, offset, factor, density, depthShiftBlocks)
                            + noise * this.noiseStrength;
            totalDensity = this.applySlide(totalDensity, y);
            column[index] = totalDensity;
        }
    }

    private ChaosPlainsBlendedNoise getBlendedNoise(RandomState random) {
        if (this.blendedNoise == null) {
            WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(0L));
            this.blendedNoise = new ChaosPlainsBlendedNoise(worldgenRandom);
        }
        return this.blendedNoise;
    }

    /**
     * 1.7-style depth reshape, but with pond-scale octaves so basins stay lakes instead of seas.
     */
    private double sampleDepthHeightShift(RandomState random, int cellX, int cellZ) {
        if (this.depthNoiseStrength <= 0.0D) {
            return 0.0D;
        }
        PerlinNoise noise = this.getDepthNoise(random);
        double d13 = noise.getValue(cellX * 200.0D, 10.0D, cellZ * 200.0D) / 8000.0D;
        if (d13 < 0.0D) {
            d13 = -d13 * 0.3D;
        }
        d13 = d13 * 3.0D - 2.0D;
        if (d13 < 0.0D) {
            d13 /= 2.0D;
            if (d13 < -1.0D) {
                d13 = -1.0D;
            }
            d13 /= 1.4D;
            d13 /= 2.0D;
        } else {
            if (d13 > 1.0D) {
                d13 = 1.0D;
            }
            d13 /= 8.0D;
        }
        // Cell units * cell height: 0.2 * 8.5/8 * 4 * cellHeight ≈ 6.8 blocks at d13 = -1.
        return d13 * 0.85D * (double) this.cellHeight * this.depthNoiseStrength;
    }

    private PerlinNoise getDepthNoise(RandomState random) {
        int owner = System.identityHashCode(random);
        if (this.depthNoise == null || this.depthNoiseOwner != owner) {
            RandomSource source =
                    random
                            .getOrCreateRandomFactory(
                                    ResourceLocation.fromNamespaceAndPath(
                                            "chaospersists", "plains_depth_noise"))
                            .fromHashOf("init");
            // Pond-scale octaves (~80 blocks). -15 is overworld ocean scale and made inland seas.
            this.depthNoise = PerlinNoise.create(source, IntStream.rangeClosed(-12, 0));
            this.depthNoiseOwner = owner;
        }
        return this.depthNoise;
    }

    private double computeInitialDensity(
            int y, double offset, double factor, double density, double depthShiftBlocks) {
        double adjustedY =
                (double) y - ((double) this.terrainHeightOffset + depthShiftBlocks) / (double) this.cellHeight;
        double base = 1.0D - adjustedY * 2.0D / 32.0D + density;
        double factored = base * this.dimensionDensityFactor + this.dimensionDensityOffset;
        double total = (factored + offset) * factor;
        return total * (total > 0.0D ? 4.0D : 1.0D);
    }

    private double applySlide(double density, int height) {
        int minCell = Math.floorDiv(this.noiseSettings.minY(), this.cellHeight);
        int relativeHeight = height - minCell;
        density = this.topSlide.applySlide(density, this.cellCountY - relativeHeight);
        density = this.bottomSlide.applySlide(density, relativeHeight);
        return density;
    }
}
