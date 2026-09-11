package com.astryxion.chaospersists.legacy.forge.event.terraingen;

import java.util.Random;
import com.astryxion.chaospersists.legacy.minecraft.world.World;
import com.astryxion.chaospersists.legacy.minecraft.world.gen.NoiseGeneratorOctaves;
import com.astryxion.chaospersists.legacy.minecraft.world.gen.NoiseGeneratorPerlin;

/** Legacy Forge noise generator init event for {@code ChunkProviderChaos2}. */
public class InitNoiseGensEvent {
  public static class Context {}

  public static class ContextOverworld extends Context {
    private NoiseGeneratorOctaves lperlin1;
    private NoiseGeneratorOctaves lperlin2;
    private NoiseGeneratorOctaves perlin;
    private NoiseGeneratorPerlin height;
    private NoiseGeneratorOctaves scale;
    private NoiseGeneratorOctaves depth;
    private NoiseGeneratorOctaves forest;

    public ContextOverworld(
        NoiseGeneratorOctaves lperlin1,
        NoiseGeneratorOctaves lperlin2,
        NoiseGeneratorOctaves perlin,
        NoiseGeneratorPerlin height,
        NoiseGeneratorOctaves scale,
        NoiseGeneratorOctaves depth,
        NoiseGeneratorOctaves forest) {
      this.lperlin1 = lperlin1;
      this.lperlin2 = lperlin2;
      this.perlin = perlin;
      this.height = height;
      this.scale = scale;
      this.depth = depth;
      this.forest = forest;
    }

    public NoiseGeneratorOctaves getLPerlin1() {
      return lperlin1;
    }

    public NoiseGeneratorOctaves getLPerlin2() {
      return lperlin2;
    }

    public NoiseGeneratorOctaves getPerlin() {
      return perlin;
    }

    public NoiseGeneratorPerlin getHeight() {
      return height;
    }

    public NoiseGeneratorOctaves getScale() {
      return scale;
    }

    public NoiseGeneratorOctaves getDepth() {
      return depth;
    }

    public NoiseGeneratorOctaves getForest() {
      return forest;
    }
  }
}
