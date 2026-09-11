package com.astryxion.chaospersists.legacy.minecraft.world.gen;

import java.util.Random;

/** Legacy 1.12 octave noise used by Chaos Persists chunk providers. */
public class NoiseGeneratorOctaves {
    private final LegacyNoiseGeneratorPerlin[] generators;
    private final int octaves;

    public NoiseGeneratorOctaves(Random random, int octavesIn) {
        this.octaves = octavesIn;
        this.generators = new LegacyNoiseGeneratorPerlin[octavesIn];
        for (int i = 0; i < octavesIn; i++) {
            this.generators[i] = new LegacyNoiseGeneratorPerlin(random);
        }
    }

    public double[] generateNoiseOctaves(
            double[] noise,
            int xOffset,
            int yOffset,
            int zOffset,
            int xSize,
            int ySize,
            int zSize,
            double xScale,
            double yScale,
            double zScale) {
        int total = xSize * ySize * zSize;
        if (noise == null || noise.length < total) {
            noise = new double[total];
        } else {
            for (int i = 0; i < total; i++) {
                noise[i] = 0.0;
            }
        }

        double amplitude = 1.0D;
        double freqX = xScale;
        double freqY = yScale;
        double freqZ = zScale;

        for (int octave = 0; octave < this.octaves; ++octave) {
            double sampleX = xOffset * amplitude * freqX;
            double sampleY = yOffset * amplitude * freqY;
            double sampleZ = zOffset * amplitude * freqZ;
            this.generators[octave].sampleRegion(
                    noise,
                    sampleX,
                    sampleY,
                    sampleZ,
                    xSize,
                    ySize,
                    zSize,
                    freqX * amplitude,
                    freqY * amplitude,
                    freqZ * amplitude,
                    amplitude);
            amplitude /= 2.0D;
        }
        return noise;
    }

    /** 1.12 overload: y size 1, y offset 10, y scale 1.0. */
    public double[] generateNoiseOctaves(
            double[] noise,
            int xOffset,
            int zOffset,
            int xSize,
            int zSize,
            double xScale,
            double zScale,
            double unused) {
        return generateNoiseOctaves(
                noise, xOffset, 10, zOffset, xSize, 1, zSize, xScale, 1.0, zScale);
    }
}
