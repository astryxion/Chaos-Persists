package com.astryxion.chaospersists.world.dimension.chunkgenerator;

import com.astryxion.chaospersists.legacy.minecraft.world.gen.NoiseGeneratorOctaves;
import java.util.Random;

/** 1.12 {@code ChunkProviderChaos6#initializeNoiseField}. */
final class ChaosIslandsNoise {
    private static final double COORD = 684.412D;
    private static final double HEIGHT_COORD = 2053.236D;

    private final NoiseGeneratorOctaves netherNoiseGen1;
    private final NoiseGeneratorOctaves netherNoiseGen2;
    private final NoiseGeneratorOctaves netherNoiseGen3;
    private final NoiseGeneratorOctaves netherNoiseGen6;
    private final NoiseGeneratorOctaves netherNoiseGen7;
    final NoiseGeneratorOctaves slowsandGravelNoiseGen;
    final NoiseGeneratorOctaves netherrackExclusivityNoiseGen;

    ChaosIslandsNoise(long seed) {
        Random random = new Random(seed);
        this.netherNoiseGen1 = new NoiseGeneratorOctaves(random, 16);
        this.netherNoiseGen2 = new NoiseGeneratorOctaves(random, 16);
        this.netherNoiseGen3 = new NoiseGeneratorOctaves(random, 8);
        this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(random, 4);
        this.netherrackExclusivityNoiseGen = new NoiseGeneratorOctaves(random, 4);
        this.netherNoiseGen6 = new NoiseGeneratorOctaves(random, 10);
        this.netherNoiseGen7 = new NoiseGeneratorOctaves(random, 16);
    }

    double[] initializeNoiseField(
            double[] buffer, int xOffset, int yOffset, int zOffset, int xSize, int ySize, int zSize) {
        int index = 0;
        int secondary = 0;
        double[] vertical = new double[ySize];
        int total = xSize * ySize * zSize;
        if (buffer == null || buffer.length < total) {
            buffer = new double[total];
        }

        double[] noiseData4 =
                this.netherNoiseGen6.generateNoiseOctaves(
                        null, xOffset, yOffset, zOffset, xSize, 1, zSize, 1.0, 0.0, 1.0);
        double[] noiseData5 =
                this.netherNoiseGen7.generateNoiseOctaves(
                        null, xOffset, yOffset, zOffset, xSize, 1, zSize, 100.0, 0.0, 100.0);
        double[] noiseData1 =
                this.netherNoiseGen3.generateNoiseOctaves(
                        null,
                        xOffset,
                        yOffset,
                        zOffset,
                        xSize,
                        ySize,
                        zSize,
                        COORD / 80.0,
                        HEIGHT_COORD / 60.0,
                        COORD / 80.0);
        double[] noiseData2 =
                this.netherNoiseGen1.generateNoiseOctaves(
                        null,
                        xOffset,
                        yOffset,
                        zOffset,
                        xSize,
                        ySize,
                        zSize,
                        COORD,
                        HEIGHT_COORD,
                        COORD);
        double[] noiseData3 =
                this.netherNoiseGen2.generateNoiseOctaves(
                        null,
                        xOffset,
                        yOffset,
                        zOffset,
                        xSize,
                        ySize,
                        zSize,
                        COORD,
                        HEIGHT_COORD,
                        COORD);

        for (int y = 0; y < ySize; ++y) {
            vertical[y] = Math.cos((double) y * Math.PI * 6.0 / (double) ySize) * 2.0;
            double depth = y;
            if (y > ySize / 2) {
                depth = ySize - 1 - y;
            }
            if (depth < 4.0) {
                depth = 4.0 - depth;
                vertical[y] -= depth * depth * depth * 10.0;
            }
        }

        for (int x = 0; x < xSize; ++x) {
            for (int z = 0; z < zSize; ++z) {
                double island = (noiseData4[secondary] + 256.0) / 512.0;
                if (island > 1.0) {
                    island = 1.0;
                }
                double heightNoise = 0.0;
                double modifier = noiseData5[secondary] / 8000.0;
                if (modifier < 0.0) {
                    modifier = -modifier;
                }
                modifier = modifier * 3.0 - 3.0;
                if (modifier < 0.0) {
                    modifier /= 2.0;
                    if (modifier < -1.0) {
                        modifier = -1.0;
                    }
                    modifier /= 1.4;
                    modifier /= 2.0;
                    island = 0.0;
                } else {
                    if (modifier > 1.0) {
                        modifier = 1.0;
                    }
                    modifier /= 6.0;
                }
                island += 0.5;
                modifier = modifier * (double) ySize / 16.0;
                ++secondary;

                for (int y = 0; y < ySize; ++y) {
                    double low = noiseData2[index] / 512.0;
                    double high = noiseData3[index] / 512.0;
                    double blend = (noiseData1[index] / 10.0 + 1.0) / 2.0;
                    double density =
                            blend < 0.0 ? low : (blend > 1.0 ? high : low + (high - low) * blend);
                    density -= vertical[y];

                    if (y > ySize - 4) {
                        double fade = (float) (y - (ySize - 4)) / 3.0f;
                        density = density * (1.0 - fade) + -10.0 * fade;
                    }
                    if ((double) y < heightNoise) {
                        double fade = (heightNoise - (double) y) / 4.0;
                        if (fade < 0.0) {
                            fade = 0.0;
                        }
                        if (fade > 1.0) {
                            fade = 1.0;
                        }
                        density = density * (1.0 - fade) + -10.0 * fade;
                    }
                    buffer[index] = density;
                    ++index;
                }
            }
        }
        return buffer;
    }
}
