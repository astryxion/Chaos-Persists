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

public class ChunkProviderChaos2 extends ChunkGenerator {
    private final long seed;
    private Random random;
    private World world;
    private final double[] heightMap;
    private final float[] parabolicField;
    public LegacyNoiseGeneratorOctaves scale;
    public LegacyNoiseGeneratorOctaves depth;
    public LegacyNoiseGeneratorOctaves forest;
    private LegacyNoiseGeneratorOctaves field_185991_j;
    private LegacyNoiseGeneratorOctaves field_185992_k;
    private LegacyNoiseGeneratorOctaves field_185993_l;
    private LegacyNoiseGeneratorPerlin height;
    private double[] stoneNoise;
    private double[] field_186002_u = new double[256];
    private Biome[] biomesForGeneration;
    double[] field_185986_e;
    double[] field_185987_f;
    double[] field_185988_g;
    double[] field_185989_h;

    public ChunkProviderChaos2(BiomeProvider biomeProvider, long seed) {
        super(biomeProvider, DimensionSettings.bootstrap().structureSettings());
        this.seed = seed;
        this.random = new Random(seed);
        this.stoneNoise = new double[256];
        this.scale = new LegacyNoiseGeneratorOctaves(this.random, 10);
        this.depth = new LegacyNoiseGeneratorOctaves(this.random, 16);
        this.forest = new LegacyNoiseGeneratorOctaves(this.random, 8);
        this.field_185991_j = new LegacyNoiseGeneratorOctaves(this.random, 16);
        this.field_185992_k = new LegacyNoiseGeneratorOctaves(this.random, 16);
        this.field_185993_l = new LegacyNoiseGeneratorOctaves(this.random, 8);
        this.height = new LegacyNoiseGeneratorPerlin(this.random, 4);
        this.heightMap = new double[825];
        this.parabolicField = new float[25];
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                float f = 10.0F / MathHelper.sqrt((float)((i * i + j * j) + 0.2F));
                this.parabolicField[i + 2 + (j + 2) * 5] = f;
            }
        }
    }

    public ChunkProviderChaos2(World worldIn, long seed, BiomeProvider bp) {
        this(bp, seed);
        this.world = worldIn;
    }

    private static BiomeProvider resolveBiomeProvider(World world) {
        if (world instanceof ServerWorld) {
            ServerChunkProvider chunkSource = (ServerChunkProvider) world.getChunkSource();
            ChunkGenerator generator = chunkSource.getGenerator();
            if (generator != null) {
                return generator.getBiomeSource();
            }
        }
        if (ChaosPersists.MINING_BIOME != null) {
            return new SingleBiomeProvider(ChaosPersists.MINING_BIOME);
        }
        return new SingleBiomeProvider(world.getBiome(new BlockPos(0, 64, 0)));
    }

    public ChunkProviderChaos2(World worldIn, long seed) {
        this(resolveBiomeProvider(worldIn), seed);
        this.world = worldIn;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return Codec.unit(this);
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        ChunkProviderChaos2 provider = new ChunkProviderChaos2(this.getBiomeSource(), seed);
        provider.world = this.world;
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
    public void fillFromNoise(IWorld worldIn, StructureManager structureManager, IChunk chunk) {
        if (worldIn instanceof WorldGenRegion) {
            this.world = ((WorldGenRegion) worldIn).getLevel();
        } else if (worldIn instanceof World) {
            this.world = (World) worldIn;
        }
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        this.random.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
        TerrainBuffer chunkPrimer = new TerrainBuffer();
        this.setBlocksInChunk(chunkX, chunkZ, chunkPrimer);
        chunkPrimer.writeToChunk(chunk);
    }

    @Override
    public void buildSurfaceAndBedrock(WorldGenRegion region, IChunk chunk) {
        if (this.world == null) {
            this.world = region.getLevel();
        }
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        this.biomesForGeneration = this.getBiomesForGeneration(this.biomesForGeneration, chunkX * 16, chunkZ * 16, 16, 16);
        this.replaceBiomeBlocks(chunkX, chunkZ, chunk, this.biomesForGeneration);
        if (chunk instanceof Chunk) {
            ChaosPersists.Chunker.generateOresInChunk(this.world, this.random, chunkX * 16, chunkZ * 16, (Chunk) chunk);
            if (ChaosPersists.LessOre == 0) {
                ChaosPersists.Chunker.generateOresInChunk(this.world, this.random, chunkX * 16, chunkZ * 16, (Chunk) chunk);
                ChaosPersists.Chunker.generateOresInChunk(this.world, this.random, chunkX * 16, chunkZ * 16, (Chunk) chunk);
            }
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

    private void replaceBiomeBlocks(int x, int z, IChunk primer, Biome[] biomesIn) {
        double d0 = 0.03125D;
        this.field_186002_u = this.height.getRegion(this.field_186002_u, (x * 16), (z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Biome biome = biomesIn[j + i * 16];
                this.generateBiomeTerrain(this.world, this.random, primer, x * 16 + i, z * 16 + j, this.field_186002_u[j + i * 16], biome);
            }
        }
    }

    private void replaceBiomeBlocks(int x, int z, TerrainBuffer primer, Biome[] biomesIn) {
        double d0 = 0.03125D;
        this.field_186002_u = this.height.getRegion(this.field_186002_u, (x * 16), (z * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Biome biome = biomesIn[j + i * 16];
                this.generateBiomeTerrain(this.world, this.random, primer, x * 16 + i, z * 16 + j, this.field_186002_u[j + i * 16], biome);
            }
        }
    }

    private void generateBiomeTerrain(World worldIn, Random rand, IChunk chunkPrimerIn, int x, int z, double noiseVal, Biome biome) {
        int seaLevel = 63;
        BlockState topBlock = Blocks.GRASS_BLOCK.defaultBlockState();
        BlockState fillerBlock = Blocks.DIRT.defaultBlockState();
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 0xF;
        int i1 = z & 0xF;
        for (int y = 255; y >= 0; y--) {
            if (y == 0) {
                chunkPrimerIn.setBlockState(new BlockPos(l, y, i1), Blocks.BEDROCK.defaultBlockState(), false);
            } else {
                BlockState iblockstate2 = chunkPrimerIn.getBlockState(new BlockPos(l, y, i1));
                if (iblockstate2.isAir()) {
                    j = -1;
                } else if (iblockstate2.is(Blocks.STONE)) {
                    if (j == -1) {
                        if (k <= 0) {
                            topBlock = Blocks.AIR.defaultBlockState();
                            fillerBlock = Blocks.STONE.defaultBlockState();
                        } else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
                            topBlock = Blocks.GRASS_BLOCK.defaultBlockState();
                            fillerBlock = Blocks.DIRT.defaultBlockState();
                        }
                        if (y < seaLevel && topBlock.isAir()) {
                            fillerBlock = Blocks.WATER.defaultBlockState();
                        }
                        j = k;
                        if (y >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(new BlockPos(l, y, i1), topBlock, false);
                        } else if (y < seaLevel - 7 - k) {
                            topBlock = Blocks.AIR.defaultBlockState();
                            fillerBlock = Blocks.STONE.defaultBlockState();
                            chunkPrimerIn.setBlockState(new BlockPos(l, y, i1), Blocks.GRAVEL.defaultBlockState(), false);
                        } else {
                            chunkPrimerIn.setBlockState(new BlockPos(l, y, i1), fillerBlock, false);
                        }
                    } else if (j > 0) {
                        j--;
                        chunkPrimerIn.setBlockState(new BlockPos(l, y, i1), fillerBlock, false);
                        if (j == 0 && fillerBlock.is(Blocks.SAND)) {
                            j = rand.nextInt(4);
                            fillerBlock = Blocks.STONE.defaultBlockState();
                        }
                    }
                }
            }
        }
    }

    private void generateBiomeTerrain(World worldIn, Random rand, TerrainBuffer chunkPrimerIn, int x, int z, double noiseVal, Biome biome) {
        int seaLevel = 63;
        BlockState topBlock = Blocks.GRASS_BLOCK.defaultBlockState();
        BlockState fillerBlock = Blocks.DIRT.defaultBlockState();
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 0xF;
        int i1 = z & 0xF;
        for (int y = 255; y >= 0; y--) {
            if (y == 0) {
                chunkPrimerIn.setBlockState(l, y, i1, Blocks.BEDROCK.defaultBlockState());
            } else {
                BlockState iblockstate2 = chunkPrimerIn.getBlockState(l, y, i1);
                if (iblockstate2.isAir()) {
                    j = -1;
                } else if (iblockstate2.is(Blocks.STONE)) {
                    if (j == -1) {
                        if (k <= 0) {
                            topBlock = Blocks.AIR.defaultBlockState();
                            fillerBlock = Blocks.STONE.defaultBlockState();
                        } else if (y >= seaLevel - 4 && y <= seaLevel + 1) {
                            topBlock = Blocks.GRASS_BLOCK.defaultBlockState();
                            fillerBlock = Blocks.DIRT.defaultBlockState();
                        }
                        if (y < seaLevel && topBlock.isAir()) {
                            fillerBlock = Blocks.WATER.defaultBlockState();
                        }
                        j = k;
                        if (y >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(l, y, i1, topBlock);
                        } else if (y < seaLevel - 7 - k) {
                            topBlock = Blocks.AIR.defaultBlockState();
                            fillerBlock = Blocks.STONE.defaultBlockState();
                            chunkPrimerIn.setBlockState(l, y, i1, Blocks.GRAVEL.defaultBlockState());
                        } else {
                            chunkPrimerIn.setBlockState(l, y, i1, fillerBlock);
                        }
                    } else if (j > 0) {
                        j--;
                        chunkPrimerIn.setBlockState(l, y, i1, fillerBlock);
                        if (j == 0 && fillerBlock.is(Blocks.SAND)) {
                            j = rand.nextInt(4);
                            fillerBlock = Blocks.STONE.defaultBlockState();
                        }
                    }
                }
            }
        }
    }

    private void setBlocksInChunk(int x, int z, TerrainBuffer chunkPrimer) {
        this.biomesForGeneration = this.getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);
        for (int i = 0; i < 4; i++) {
            int j = i * 5;
            int k = (i + 1) * 5;
            for (int l = 0; l < 4; l++) {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;
                for (int i2 = 0; i2 < 32; i2++) {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;
                    for (int j2 = 0; j2 < 8; j2++) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;
                        for (int k2 = 0; k2 < 4; k2++) {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;
                            for (int l2 = 0; l2 < 4; l2++) {
                                if ((lvt_45_1_ += d16) > 0.0D) {
                                    chunkPrimer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.STONE.defaultBlockState());
                                } else if (i2 * 8 + j2 < 63) {
                                    chunkPrimer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, Blocks.WATER.defaultBlockState());
                                }
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

    private void generateHeightmap(int x, int y, int z) {
        this.field_185989_h = this.depth.generateNoiseOctaves(this.field_185989_h, x, z, 5, 5, 200.0D, 200.0D, 0.5D);
        float coordScale = 684.412F;
        float heightScale = 684.412F;
        this.field_185986_e = this.field_185993_l.generateNoiseOctaves(this.field_185986_e, x, y, z, 5, 33, 5, 8.55515D, 4.277575D, 8.55515D);
        this.field_185987_f = this.field_185991_j.generateNoiseOctaves(this.field_185987_f, x, y, z, 5, 33, 5, coordScale, heightScale, coordScale);
        this.field_185988_g = this.field_185992_k.generateNoiseOctaves(this.field_185988_g, x, y, z, 5, 33, 5, coordScale, heightScale, coordScale);
        int i = 0;
        int j = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome surroundingBiome = this.biomesForGeneration[k + 2 + (l + 2) * 10];
                for (int j1 = -i1; j1 < i1; j1++) {
                    for (int k1 = -i1; k1 <= i1; k1++) {
                        Biome biome = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float baseHeight = biome.getDepth();
                        float heightVariation = biome.getScale();
                        float f7 = this.parabolicField[j1 + 2 + (k1 + 2) * 5] / (baseHeight + 2.0F);
                        if (biome.getDepth() > surroundingBiome.getDepth()) {
                            f7 /= 2.0F;
                        }
                        f2 += heightVariation * f7;
                        f3 += baseHeight * f7;
                        f4 += f7;
                    }
                }
                f2 /= f4;
                f3 /= f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.field_185989_h[j] / 8000.0D;
                if (d7 < 0.0D) {
                    d7 = -d7 * 0.3D;
                }
                if (d7 < 0.0D) {
                    d7 /= 2.0D;
                    if (d7 < -1.0D) {
                        d7 = -1.0D;
                    }
                    d7 /= 1.4D;
                    d7 /= 2.0D;
                } else {
                    if (d7 > 1.0D) {
                        d7 = 1.0D;
                    }
                    d7 /= 8.0D;
                }
                j++;
                double d8 = f3;
                double d9 = f2;
                d8 += d7 * 0.2D;
                d8 = d8 * 8.5D / 8.0D;
                double d0 = 8.5D + d8 * 4.0D;
                for (int l1 = 0; l1 < 33; l1++) {
                    double d1 = (l1 - d0) * 12.0D * 128.0D / 256.0D / d9;
                    if (d1 < 0.0D) {
                        d1 *= 4.0D;
                    }
                    double d2 = this.field_185987_f[i] / 512.0D;
                    double d3 = this.field_185988_g[i] / 512.0D;
                    double d4 = (this.field_185986_e[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.lerp(d4, d2, d3) - d1;
                    if (l1 > 29) {
                        double d6 = ((l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }
                    this.heightMap[i] = d5;
                    i++;
                }
            }
        }
    }

    private static final class TerrainBuffer {
        private final BlockState[] blocks = new BlockState[65536];

        void setBlockState(int x, int y, int z, BlockState state) {
            this.blocks[(y << 8) | (z << 4) | x] = state;
        }

        BlockState getBlockState(int x, int y, int z) {
            BlockState state = this.blocks[(y << 8) | (z << 4) | x];
            return state != null ? state : Blocks.AIR.defaultBlockState();
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
