/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.world.dimension.chunkprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.ImprovedNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

public class ChunkProviderChaos extends ChunkGenerator {
    private final long seed;
    private Random random;
    private LegacyNoiseGeneratorOctaves field_147431_j;
    private LegacyNoiseGeneratorOctaves field_147432_k;
    private LegacyNoiseGeneratorOctaves field_147429_l;
    private LegacyNoiseGeneratorPerlin field_147430_m;
    public LegacyNoiseGeneratorOctaves noiseGen5;
    public LegacyNoiseGeneratorOctaves noiseGen6;
    public LegacyNoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private final boolean terrainAmplified;
    private final double[] field_147434_q;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private Biome[] biomesForGeneration;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    int[][] field_73219_j = new int[32][32];

    public ChunkProviderChaos(BiomeProvider biomeProvider, long seed, boolean mapFeaturesEnabled) {
        super(biomeProvider, DimensionSettings.bootstrap().structureSettings());
        this.seed = seed;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        this.terrainAmplified = false;
        this.random = new Random(seed);
        this.field_147431_j = new LegacyNoiseGeneratorOctaves(this.random, 16);
        this.field_147432_k = new LegacyNoiseGeneratorOctaves(this.random, 16);
        this.field_147429_l = new LegacyNoiseGeneratorOctaves(this.random, 8);
        this.field_147430_m = new LegacyNoiseGeneratorPerlin(this.random, 4);
        this.noiseGen5 = new LegacyNoiseGeneratorOctaves(this.random, 10);
        this.noiseGen6 = new LegacyNoiseGeneratorOctaves(this.random, 16);
        this.mobSpawnerNoise = new LegacyNoiseGeneratorOctaves(this.random, 8);
        this.field_147434_q = new double[825];
        this.parabolicField = new float[25];
        for (int j = -2; j <= 2; ++j) {
            for (int k = -2; k <= 2; ++k) {
                float f = 10.0f / MathHelper.sqrt((float)((float)(j * j + k * k) + 0.2f));
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }
    }

    public ChunkProviderChaos(World par1World, long par2, boolean par4) {
        this(resolveBiomeProvider(par1World), par2, par4);
        this.worldObj = par1World;
    }

    private static BiomeProvider resolveBiomeProvider(World world) {
        if (world instanceof ServerWorld) {
            ServerChunkProvider chunkSource = (ServerChunkProvider) world.getChunkSource();
            ChunkGenerator generator = chunkSource.getGenerator();
            if (generator != null) {
                return generator.getBiomeSource();
            }
        }
        if (ChaosPersists.UTOPIA_BIOME != null) {
            return new SingleBiomeProvider(ChaosPersists.UTOPIA_BIOME);
        }
        return new SingleBiomeProvider(world.getBiome(new BlockPos(0, 64, 0)));
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return Codec.unit(this);
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        ChunkProviderChaos provider = new ChunkProviderChaos(this.getBiomeSource(), seed, this.mapFeaturesEnabled);
        provider.worldObj = this.worldObj;
        return provider;
    }

    @Override
    public IBlockReader getBaseColumn(int x, int z) {
        final BlockState[] column = new BlockState[256];
        for (int y = 0; y < 256; ++y) {
            column[y] = y < 63 ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
        }
        return new IBlockReader() {
            @Override
            public BlockState getBlockState(BlockPos pos) {
                int y = pos.getY();
                return y >= 0 && y < 256 ? column[y] : Blocks.AIR.defaultBlockState();
            }

            @Override
            public net.minecraft.fluid.FluidState getFluidState(BlockPos pos) {
                int y = pos.getY();
                if (y >= 0 && y < 63) {
                    return Fluids.WATER.defaultFluidState();
                }
                return Fluids.EMPTY.defaultFluidState();
            }

            @Override
            public net.minecraft.tileentity.TileEntity getBlockEntity(BlockPos pos) {
                return null;
            }
        };
    }

    @Override
    public int getBaseHeight(int x, int z, Heightmap.Type heightmapType) {
        return 64;
    }

    @Override
    public void fillFromNoise(IWorld world, StructureManager structureManager, IChunk chunk) {
        if (world instanceof WorldGenRegion) {
            this.worldObj = ((WorldGenRegion) world).getLevel();
        } else if (world instanceof World) {
            this.worldObj = (World) world;
        }
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        this.generateTerrainIntoChunk(chunkX, chunkZ, chunk);
    }

    @Override
    public void buildSurfaceAndBedrock(WorldGenRegion region, IChunk chunk) {
        if (this.worldObj == null) {
            this.worldObj = region.getLevel();
        }
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        this.biomesForGeneration = this.getBiomesForGeneration(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        this.replaceBlocksForBiome(chunkX, chunkZ, chunk, this.biomesForGeneration);
        if (chunk instanceof Chunk) {
            ChaosPersists.Chunker.generateOresInChunk(this.worldObj, this.random, chunkX * 16, chunkZ * 16, (Chunk) chunk);
        }
    }

    @Override
    public void applyCarvers(long seed, BiomeManager biomeManager, IChunk chunk, GenerationStage.Carving type) {
        super.applyCarvers(seed, biomeManager, chunk, type);
    }

    @Override
    public void applyBiomeDecoration(WorldGenRegion region, StructureManager structureManager) {
        super.applyBiomeDecoration(region, structureManager);
    }

    private Biome[] getBiomesForGeneration(Biome[] storage, int x, int z, int width, int height) {
        if (storage == null || storage.length < width * height) {
            storage = new Biome[width * height];
        }
        BiomeProvider biomeProvider = this.getBiomeSource();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                storage[i + j * width] = biomeProvider.getNoiseBiome(x + i, 0, z + j);
            }
        }
        return storage;
    }

    private void generateTerrainIntoChunk(int chunkX, int chunkZ, IChunk chunk) {
        this.random.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
        TerrainBuffer primer = new TerrainBuffer();
        this.func_147424_a(chunkX, chunkZ, primer);
        primer.writeToChunk(chunk);
    }

    public void func_147424_a(int p_147424_1_, int p_147424_2_, TerrainBuffer primer) {
        int b0 = 63;
        this.biomesForGeneration = this.getBiomesForGeneration(this.biomesForGeneration, p_147424_1_ * 4 - 2, p_147424_2_ * 4 - 2, 10, 10);
        this.func_147423_a(p_147424_1_ * 4, 0, p_147424_2_ * 4);
        for (int k = 0; k < 4; ++k) {
            int l = k * 5;
            int i1 = (k + 1) * 5;
            for (int j1 = 0; j1 < 4; ++j1) {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;
                for (int k2 = 0; k2 < 32; ++k2) {
                    double d0 = 0.125;
                    double d1 = this.field_147434_q[k1 + k2];
                    double d2 = this.field_147434_q[l1 + k2];
                    double d3 = this.field_147434_q[i2 + k2];
                    double d4 = this.field_147434_q[j2 + k2];
                    double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;
                    for (int l2 = 0; l2 < 8; ++l2) {
                        double d9 = 0.25;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int i3 = 0; i3 < 4; ++i3) {
                            int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
                            int short1 = 256;
                            j3 -= short1;
                            double d14 = 0.25;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;
                            for (int k3 = 0; k3 < 4; ++k3) {
                                j3 += short1;
                                int x = (j3 >> 12) & 15;
                                int z = (j3 >> 8) & 15;
                                int y = j3 & 255;
                                BlockState state = (d15 += d16) > 0.0 ? Blocks.STONE.defaultBlockState() : (k2 * 8 + l2 < b0 ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState());
                                primer.setBlockState(x, y, z, state);
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
    }

    public void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, IChunk chunk, Biome[] p_147422_5_) {
        double d0 = 0.03125;
        this.stoneNoise = this.field_147430_m.getRegion(this.stoneNoise, (double)(p_147422_1_ * 16), (double)(p_147422_2_ * 16), 16, 16, d0 * 2.0, d0 * 2.0, 1.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                Biome biomegenbase = p_147422_5_[l + k * 16];
                biomegenbase.buildSurfaceAt(this.random, chunk, p_147422_1_ * 16 + k, p_147422_2_ * 16 + l, 0,
                        this.stoneNoise[l + k * 16], Blocks.STONE.defaultBlockState(), Blocks.WATER.defaultBlockState(), 63, this.random.nextLong());
            }
        }
    }

    private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_) {
        double d0 = 684.412;
        double d1 = 684.412;
        double d2 = 512.0;
        double d3 = 512.0;
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0, 200.0, 0.5);
        this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 8.555150000000001, 4.277575000000001, 8.555150000000001);
        this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412, 684.412, 684.412);
        this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412, 684.412, 684.412);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;
        double d4 = 8.5;
        for (int j1 = 0; j1 < 5; ++j1) {
            for (int k1 = 0; k1 < 5; ++k1) {
                float f = 0.0f;
                float f1 = 0.0f;
                float f2 = 0.0f;
                int b0 = 2;
                Biome biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];
                for (int l1 = - b0; l1 <= b0; ++l1) {
                    for (int i2 = - b0; i2 <= b0; ++i2) {
                        Biome biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f3 = biomegenbase1.getDepth();
                        float f4 = biomegenbase1.getScale();
                        if (this.terrainAmplified && f3 > 0.0f) {
                            f3 = 1.0f + f3 * 2.0f;
                            f4 = 1.0f + f4 * 4.0f;
                        }
                        float f5 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f3 + 2.0f);
                        if (biomegenbase1.getDepth() > biomegenbase.getDepth()) {
                            f5 /= 2.0f;
                        }
                        f += f4 * f5;
                        f1 += f3 * f5;
                        f2 += f5;
                    }
                }
                f /= f2;
                f1 /= f2;
                f = f * 0.9f + 0.1f;
                f1 = (f1 * 4.0f - 1.0f) / 8.0f;
                double d13 = this.field_147426_g[i1] / 8000.0;
                if (d13 < 0.0) {
                    d13 = (- d13) * 0.3;
                }
                if ((d13 = d13 * 3.0 - 2.0) < 0.0) {
                    if ((d13 /= 2.0) < -1.0) {
                        d13 = -1.0;
                    }
                    d13 /= 1.4;
                    d13 /= 2.0;
                } else {
                    if (d13 > 1.0) {
                        d13 = 1.0;
                    }
                    d13 /= 8.0;
                }
                ++i1;
                double d12 = f1;
                double d14 = f;
                d12 += d13 * 0.2;
                d12 = d12 * 8.5 / 8.0;
                double d5 = 8.5 + d12 * 4.0;
                for (int j2 = 0; j2 < 33; ++j2) {
                    double d6 = ((double)j2 - d5) * 12.0 * 128.0 / 256.0 / d14;
                    if (d6 < 0.0) {
                        d6 *= 4.0;
                    }
                    double d7 = this.field_147428_e[l] / 512.0;
                    double d8 = this.field_147425_f[l] / 512.0;
                    double d9 = (this.field_147427_d[l] / 10.0 + 1.0) / 2.0;
                    double d10 = (d7 + (d8 - d7) * MathHelper.clamp(d9, 0.0, 1.0)) - d6;
                    if (j2 > 29) {
                        double d11 = (float)(j2 - 29) / 3.0f;
                        d10 = d10 * (1.0 - d11) + -10.0 * d11;
                    }
                    this.field_147434_q[l] = d10;
                    ++l;
                }
            }
        }
    }

    public String makeString() {
        return "UtopiaDimension";
    }

    private static final class TerrainBuffer {
        private final BlockState[] blocks = new BlockState[65536];

        void setBlockState(int x, int y, int z, BlockState state) {
            this.blocks[(y << 8) | (z << 4) | x] = state;
        }

        void writeToChunk(IChunk chunk) {
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    for (int y = 0; y < 256; ++y) {
                        BlockState state = this.blocks[(y << 8) | (z << 4) | x];
                        if (state != null) {
                            chunk.setBlockState(new BlockPos(x, y, z), state, false);
                        }
                    }
                }
            }
        }
    }

    private static final class LegacyNoiseGeneratorOctaves {
        private final ImprovedNoiseGenerator[] generatorCollection;
        private final int octaves;

        LegacyNoiseGeneratorOctaves(Random random, int octavesIn) {
            this.octaves = octavesIn;
            this.generatorCollection = new ImprovedNoiseGenerator[octavesIn];
            for (int i = 0; i < octavesIn; ++i) {
                this.generatorCollection[i] = new ImprovedNoiseGenerator(random);
            }
        }

        double[] generateNoiseOctaves(double[] noiseArray, int xOffset, int zOffset, int xSize, int zSize, double xScale, double zScale, double yScale) {
            return this.generateNoiseOctaves(noiseArray, xOffset, 0, zOffset, xSize, 1, zSize, xScale, yScale, zScale);
        }

        double[] generateNoiseOctaves(double[] noiseArray, int xOffset, int yOffset, int zOffset, int xSize, int ySize, int zSize, double xScale, double yScale, double zScale) {
            if (noiseArray == null) {
                noiseArray = new double[xSize * ySize * zSize];
            } else {
                for (int i = 0; i < noiseArray.length; ++i) {
                    noiseArray[i] = 0.0D;
                }
            }
            double d3 = 1.0D;
            for (int j = 0; j < this.octaves; ++j) {
                double d0 = (double)xOffset * d3 * xScale;
                double d1 = (double)yOffset * d3 * yScale;
                double d2 = (double)zOffset * d3 * zScale;
                int l = MathHelper.floor(d0);
                int i1 = MathHelper.floor(d1);
                int j1 = MathHelper.floor(d2);
                double d4 = d0 - (double)l;
                double d5 = d1 - (double)i1;
                double d6 = d2 - (double)j1;
                l %= 16777216;
                j1 %= 16777216;
                i1 %= 16777216;
                int l2 = 0;
                for (int i3 = 0; i3 < xSize; ++i3) {
                    double d13 = d0 + (double)i3 * xScale + d4;
                    int i4 = MathHelper.floor(d13);
                    d13 -= (double)i4;
                    i4 %= 16777216;
                    for (int j3 = 0; j3 < zSize; ++j3) {
                        double d15 = d2 + (double)j3 * zScale + d6;
                        int k1 = MathHelper.floor(d15);
                        d15 -= (double)k1;
                        k1 %= 16777216;
                        for (int k3 = 0; k3 < ySize; ++k3) {
                            double d17 = d1 + (double)k3 * yScale + d5;
                            int j2 = MathHelper.floor(d17);
                            d17 -= (double)j2;
                            j2 %= 16777216;
                            double d19 = 0.0D;
                            double d20 = 0.0D;
                            double d21 = 0.0D;
                            for (int k4 = 0; k4 < 2; ++k4) {
                                int l1 = (i4 + k4) % 16777216;
                                int i2 = (j2 + k4) % 16777216;
                                for (int j4 = 0; j4 < 2; ++j4) {
                                    int l3 = (k1 + j4) % 16777216;
                                    double d10 = this.generatorCollection[j].noise((double)l1 * 0.00390625D, (double)i2 * 0.00390625D, (double)l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d11 = this.generatorCollection[j].noise((double)(l1 + 1) * 0.00390625D, (double)i2 * 0.00390625D, (double)l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d12 = this.generatorCollection[j].noise((double)l1 * 0.00390625D, (double)(i2 + 1) * 0.00390625D, (double)l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d14 = this.generatorCollection[j].noise((double)(l1 + 1) * 0.00390625D, (double)(i2 + 1) * 0.00390625D, (double)l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d16 = MathHelper.lerp(d15, MathHelper.lerp(d13, d10, d11), MathHelper.lerp(d13, d12, d14));
                                    d19 += d16;
                                    d10 = this.generatorCollection[j].noise((double)l1 * 0.00390625D, (double)i2 * 0.00390625D, (double)(l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d11 = this.generatorCollection[j].noise((double)(l1 + 1) * 0.00390625D, (double)i2 * 0.00390625D, (double)(l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d12 = this.generatorCollection[j].noise((double)l1 * 0.00390625D, (double)(i2 + 1) * 0.00390625D, (double)(l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d14 = this.generatorCollection[j].noise((double)(l1 + 1) * 0.00390625D, (double)(i2 + 1) * 0.00390625D, (double)(l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d16 = MathHelper.lerp(d15, MathHelper.lerp(d13, d10, d11), MathHelper.lerp(d13, d12, d14));
                                    d20 += d16;
                                }
                                d21 += MathHelper.lerp(d17, d19, d20);
                            }
                            noiseArray[l2++] += d21 * 128.0D;
                        }
                    }
                }
                d3 /= 2.0D;
            }
            return noiseArray;
        }
    }

    private static final class LegacyNoiseGeneratorPerlin {
        private final ImprovedNoiseGenerator[] noiseLevels;

        LegacyNoiseGeneratorPerlin(Random random, int levels) {
            this.noiseLevels = new ImprovedNoiseGenerator[levels];
            for (int i = 0; i < levels; ++i) {
                this.noiseLevels[i] = new ImprovedNoiseGenerator(random);
            }
        }

        double[] getRegion(double[] noiseArray, double xOffset, double zOffset, int xSize, int zSize, double xzScale, double zScale, double xzFactor) {
            if (noiseArray != null && noiseArray.length >= xSize * zSize) {
                for (int i = 0; i < noiseArray.length; ++i) {
                    noiseArray[i] = 0.0D;
                }
            } else {
                noiseArray = new double[xSize * zSize];
            }
            double d0 = 1.0D;
            double d1 = 1.0D;
            for (int j = 0; j < this.noiseLevels.length; ++j) {
                int k = 0;
                for (int l = 0; l < xSize; ++l) {
                    for (int i1 = 0; i1 < zSize; ++i1) {
                        double d2 = this.noiseLevels[j].noise((double)l * d0 * xzScale, 0.0D, (double)i1 * d0 * zScale, 0.0D, 0.0D) / d1;
                        d2 = MathHelper.clamp(d2, -1.0D, 1.0D);
                        noiseArray[k] += d2;
                        ++k;
                    }
                }
                d0 /= 2.0D;
                d1 /= 2.0D;
            }
            return noiseArray;
        }
    }
}
