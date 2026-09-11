package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.Random;

/** 1.12 {@code NoiseGeneratorPerlin} for nether-style island density fields. */
final class LegacyNoiseGeneratorPerlin {
    private final int[] perm;
    private final double offsetX;
    private final double offsetY;
    private final double offsetZ;

    private static final double[] GRAD_X = {
        1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0
    };
    private static final double[] GRAD_Y = {
        1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0
    };
    private static final double[] GRAD_Z = {
        0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0
    };

    LegacyNoiseGeneratorPerlin(Random random) {
        this.perm = new int[512];
        this.offsetX = random.nextDouble() * 256.0;
        this.offsetY = random.nextDouble() * 256.0;
        this.offsetZ = random.nextDouble() * 256.0;

        int[] source = new int[256];
        for (int i = 0; i < 256; ++i) {
            source[i] = i;
        }
        for (int i = 0; i < 256; ++i) {
            int swap = random.nextInt(256 - i) + i;
            int temp = source[i];
            source[i] = source[swap];
            source[swap] = temp;
            this.perm[i] = source[i];
            this.perm[i + 256] = source[i];
        }
    }

    void sampleRegion(
            double[] buffer,
            double x,
            double y,
            double z,
            int xSize,
            int ySize,
            int zSize,
            double xScale,
            double yScale,
            double zScale,
            double amplitude) {
        if (ySize == 1) {
            int index = 0;
            double invAmp = 1.0 / amplitude;
            for (int localX = 0; localX < xSize; ++localX) {
                double sampleX = x + localX * xScale + this.offsetX;
                int floorX = floor(sampleX);
                int permX = floorX & 255;
                sampleX -= floorX;
                double fadeX = fade(sampleX);

                for (int localZ = 0; localZ < zSize; ++localZ) {
                    double sampleZ = z + localZ * zScale + this.offsetZ;
                    int floorZ = floor(sampleZ);
                    int permZ = floorZ & 255;
                    sampleZ -= floorZ;
                    double fadeZ = fade(sampleZ);

                    int hashA = this.perm[permX] + 0;
                    int hashB = this.perm[permX + 1] + 0;
                    int idxA = this.perm[hashA] + permZ;
                    int idxB = this.perm[hashB] + permZ;
                    int idxC = this.perm[hashA + 1] + permZ;
                    int idxD = this.perm[hashB + 1] + permZ;

                    double lower = lerp(
                            fadeX,
                            grad(this.perm[idxA], sampleX, sampleZ),
                            grad(this.perm[idxC], sampleX - 1.0, sampleZ));
                    double upper = lerp(
                            fadeX,
                            grad(this.perm[idxB], sampleX, sampleZ - 1.0),
                            grad(this.perm[idxD], sampleX - 1.0, sampleZ - 1.0));
                    buffer[index++] += lerp(fadeZ, lower, upper) * invAmp;
                }
            }
            return;
        }

        int index = 0;
        double invAmp = 1.0 / amplitude;
        int lastPermY = -1;
        double n0 = 0.0;
        double n1 = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;

        for (int localX = 0; localX < xSize; ++localX) {
            double sampleX = x + localX * xScale + this.offsetX;
            int floorX = floor(sampleX);
            int permX = floorX & 255;
            sampleX -= floorX;
            double fadeX = fade(sampleX);

            for (int localZ = 0; localZ < zSize; ++localZ) {
                double sampleZ = z + localZ * zScale + this.offsetZ;
                int floorZ = floor(sampleZ);
                int permZ = floorZ & 255;
                sampleZ -= floorZ;
                double fadeZ = fade(sampleZ);

                for (int localY = 0; localY < ySize; ++localY) {
                    double sampleY = y + localY * yScale + this.offsetY;
                    int floorY = floor(sampleY);
                    int permY = floorY & 255;
                    sampleY -= floorY;
                    double fadeY = fade(sampleY);

                    if (localY == 0 || permY != lastPermY) {
                        lastPermY = permY;
                        int hashA = this.perm[permX] + permY;
                        int hashB = this.perm[permX + 1] + permY;
                        int idxA = this.perm[hashA] + permZ;
                        int idxB = this.perm[hashB] + permZ;
                        int idxC = this.perm[hashA + 1] + permZ;
                        int idxD = this.perm[hashB + 1] + permZ;

                        n0 = lerp(
                                fadeX,
                                grad3(this.perm[idxA], sampleX, sampleY, sampleZ),
                                grad3(this.perm[idxB], sampleX - 1.0, sampleY, sampleZ));
                        n1 = lerp(
                                fadeX,
                                grad3(this.perm[idxC], sampleX, sampleY - 1.0, sampleZ),
                                grad3(this.perm[idxD], sampleX - 1.0, sampleY - 1.0, sampleZ));
                        n2 = lerp(
                                fadeX,
                                grad3(this.perm[idxA + 1], sampleX, sampleY, sampleZ - 1.0),
                                grad3(this.perm[idxB + 1], sampleX - 1.0, sampleY, sampleZ - 1.0));
                        n3 = lerp(
                                fadeX,
                                grad3(this.perm[idxC + 1], sampleX, sampleY - 1.0, sampleZ - 1.0),
                                grad3(this.perm[idxD + 1], sampleX - 1.0, sampleY - 1.0, sampleZ - 1.0));
                    }

                    double lower = lerp(fadeY, n0, n1);
                    double upper = lerp(fadeY, n2, n3);
                    buffer[index++] += lerp(fadeZ, lower, upper) * invAmp;
                }
            }
        }
    }

    private static int floor(double value) {
        int i = (int) value;
        return value < i ? i - 1 : i;
    }

    private static double fade(double value) {
        return value * value * value * (value * (value * 6.0 - 15.0) + 10.0);
    }

    private static double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private static double grad(int hash, double x, double z) {
        int idx = hash & 15;
        return GRAD_X[idx] * x + GRAD_Z[idx] * z;
    }

    private static double grad3(int hash, double x, double y, double z) {
        int idx = hash & 15;
        return GRAD_X[idx] * x + GRAD_Y[idx] * y + GRAD_Z[idx] * z;
    }
}
