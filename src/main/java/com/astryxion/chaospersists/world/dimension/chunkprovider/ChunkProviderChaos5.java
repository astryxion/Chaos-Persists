/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.world.dimension.chunkprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.block.CrystalMaze;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.ImprovedNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.RegistryKey;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

public class ChunkProviderChaos5 extends ChunkGenerator {

    public static final Codec<ChunkProviderChaos5> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BiomeProvider.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
            Codec.LONG.fieldOf("seed").stable().forGetter(ChunkProviderChaos5::getSeed),
            Codec.BOOL.optionalFieldOf("generate_features", true).forGetter(ChunkProviderChaos5::isMapFeaturesEnabled)
    ).apply(instance, (biome, seed, features) -> new ChunkProviderChaos5(biome, seed, features)));

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

    public ChunkProviderChaos5(BiomeProvider biomeProvider, long seed, boolean mapFeaturesEnabled) {
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

    public ChunkProviderChaos5(World par1World, long par2, boolean par4) {
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
        if (ChaosPersists.CRYSTAL_BIOME != null) {
            return new SingleBiomeProvider(ChaosPersists.CRYSTAL_BIOME);
        }
        return new SingleBiomeProvider(world.getBiome(new BlockPos(0, 64, 0)));
    }

    public long getSeed() {
        return this.seed;
    }

    public boolean isMapFeaturesEnabled() {
        return this.mapFeaturesEnabled;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        ChunkProviderChaos5 provider = new ChunkProviderChaos5(this.getBiomeSource(), seed, this.mapFeaturesEnabled);
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
        this.replaceBlocksForBiome(chunkX, chunkZ, chunk);
        if (chunk instanceof Chunk) {
            Chunk chunkEntity = (Chunk) chunk;
            int worldX = chunkX * 16;
            int worldZ = chunkZ * 16;
            CrystalMaze cm = new CrystalMaze();
            cm.buildCrystalMaze(this.worldObj, worldX, 25, worldZ, chunkEntity);
            this.generateCrystals(this.worldObj, this.random, worldX, worldZ, chunkEntity);
            this.addCrystalTrees(this.worldObj, this.random, worldX, worldZ, chunkEntity);
            this.generateCrystalOres(this.worldObj, this.random, worldX, worldZ, chunkEntity);
            this.addCrystalFlowers(this.worldObj, this.random, worldX, worldZ, chunkEntity);
            this.addRice(this.worldObj, this.random, worldX, worldZ, chunkEntity);
            this.addQuinoa(this.worldObj, this.random, worldX, worldZ, chunkEntity);
        }
    }

    @Override
    public void applyCarvers(long seed, BiomeManager biomeManager, IChunk chunk, GenerationStage.Carving type) {
    }

    @Override
    public void applyBiomeDecoration(WorldGenRegion region, StructureManager structureManager) {
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
                                BlockState state = (d15 += d16) > 0.0 ? ChaosPersists.CrystalStone.defaultBlockState() : (y < b0 ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState());
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

    public void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, IChunk chunk) {
        double d0 = 0.03125;
        this.stoneNoise = this.field_147430_m.getRegion(this.stoneNoise, (double) (p_147422_1_ * 16), (double) (p_147422_2_ * 16), 16, 16, d0 * 2.0, d0 * 2.0, 1.0);
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                this.MygenBiomeTerrain(this.worldObj, this.random, chunk, p_147422_1_ * 16 + k, p_147422_2_ * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    private void MygenBiomeTerrain(World p_150560_1_, Random p_150560_2_, IChunk chunk, int p_150560_5_, int p_150560_6_, double p_150560_7_) {
        Block block = ChaosPersists.CrystalGrass;
        Block block1 = ChaosPersists.CrystalStone;
        int k = -1;
        int l = (int) (p_150560_7_ / 3.0 + 3.0 + p_150560_2_.nextDouble() * 0.25);
        int i1 = p_150560_5_ & 15;
        int j1 = p_150560_6_ & 15;
        for (int l1 = 255; l1 >= 0; --l1) {
            if (l1 <= 0 + p_150560_2_.nextInt(5)) {
                chunk.setBlockState(new BlockPos(i1, l1, j1), Blocks.BEDROCK.defaultBlockState(), false);
                continue;
            }
            BlockState block2state = chunk.getBlockState(new BlockPos(i1, l1, j1));
            Block block2 = block2state.getBlock();
            if (!block2state.isAir()) {
                if (block2 == ChaosPersists.CrystalStone) {
                    if (k == -1) {
                        if (l <= 0) {
                            block = null;
                            block1 = ChaosPersists.CrystalStone;
                        } else if (l1 >= 59 && l1 <= 64) {
                            block = ChaosPersists.CrystalGrass;
                            block1 = ChaosPersists.CrystalStone;
                        }
                        if (l1 < 63 && (block == null || block.defaultBlockState().isAir())) {
                            block = Blocks.WATER;
                        }
                        k = l;
                        if (l1 >= 62) {
                            chunk.setBlockState(new BlockPos(i1, l1, j1), block != null ? block.defaultBlockState() : Blocks.AIR.defaultBlockState(), false);
                            continue;
                        }
                        chunk.setBlockState(new BlockPos(i1, l1, j1), block1.defaultBlockState(), false);
                        continue;
                    }
                    if (k > 0) {
                        --k;
                        chunk.setBlockState(new BlockPos(i1, l1, j1), block1.defaultBlockState(), false);
                    }
                    continue;
                }
            }
            k = -1;
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
                    double d10 = (d9 < 0.0 ? d7 : (d9 > 1.0 ? d8 : d7 + (d8 - d7) * d9)) - d6;
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
        return "CrystalDimension";
    }

    public List<MobSpawnInfo.Spawners> getPossibleCreatures(EntityClassification creatureType, BlockPos pos) {
        if (this.worldObj == null) {
            return java.util.Collections.emptyList();
        }
        return this.worldObj.getBiome(pos).getMobSettings().getMobs(creatureType);
    }

    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    public void generateCrystals(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        this.addPinkTourmaline(world, random, chunkX, chunkZ, chunk);
        this.addTigersEye(world, random, chunkX, chunkZ, chunk);
    }

    public void addPinkTourmaline(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        if (random.nextInt(30) != 1) {
            return;
        }
        int randPosX = 3 + chunkX + random.nextInt(10);
        int randPosY = 30 + random.nextInt(5);
        int randPosZ = 3 + chunkZ + random.nextInt(10);
        int patchy = 1 + random.nextInt(10);
        for (int i = 0; i < patchy; ++i) {
            float dx = random.nextFloat() - random.nextFloat();
            float dz = random.nextFloat() - random.nextFloat();
            float dy = 0.5f + random.nextFloat() / 2.0f;
            int width = random.nextInt(2);
            int length = 1 + width * 3 + random.nextInt(15);
            float rx = randPosX;
            float ry = randPosY;
            float rz = randPosZ;
            for (int iy = 0; iy <= length; ++iy) {
                for (int ix = 0; ix <= width; ++ix) {
                    for (int iz = 0; iz <= width; ++iz) {
                        ChaosPersists.setBlockIDWithMetadataInChunk(chunk, (int) (rx + (float) ix), (int) ry, (int) (rz + (float) iz), ChaosPersists.CrystalCrystal, 0);
                    }
                }
                ry += dy;
                rx += dx;
                rz += dz;
            }
        }
    }

    public void addTigersEye(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        if (random.nextInt(30) != 1) {
            return;
        }
        int randPosX = 3 + chunkX + random.nextInt(10);
        int randPosY = 5 + random.nextInt(5);
        int randPosZ = 3 + chunkZ + random.nextInt(10);
        int patchy = 1 + random.nextInt(5);
        for (int i = 0; i < patchy; ++i) {
            float dx = random.nextFloat() - random.nextFloat();
            float dz = random.nextFloat() - random.nextFloat();
            float dy = 0.5f + random.nextFloat() / 2.0f;
            int width = 0;
            int length = width * 3 + random.nextInt(6);
            float rx = randPosX;
            float ry = randPosY;
            float rz = randPosZ;
            for (int iy = 0; iy <= length; ++iy) {
                for (int ix = 0; ix <= width; ++ix) {
                    for (int iz = 0; iz <= width; ++iz) {
                        ChaosPersists.setBlockIDWithMetadataInChunk(chunk, (int) (rx + (float) ix), (int) ry, (int) (rz + (float) iz), ChaosPersists.TigersEye, 0);
                    }
                }
                ry += dy;
                rx += dx;
                rz += dz;
            }
        }
    }

    public void addCrystalTrees(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        if (random.nextInt(5) != 0) {
            return;
        }
        int howmany = 0;
        int what = random.nextInt(5);
        howmany = random.nextInt(8);
        if (what != 0) {
            howmany *= 2;
        }
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = 4 + chunkX + random.nextInt(8);
            int posZ = 4 + chunkZ + random.nextInt(8);
            for (int posY = 128; posY > 40; --posY) {
                if (ChaosPersists.getBlockIDInChunk(chunk, posX, posY, posZ) != Blocks.AIR || ChaosPersists.getBlockIDInChunk(chunk, posX, posY - 1, posZ) != ChaosPersists.CrystalGrass) continue;
                if (what == 0) {
                    this.TallCrystalTree(world, posX, posY, posZ, chunk);
                    continue block0;
                }
                this.ScragglyCrystalTreeWithBranches(world, posX, posY, posZ, chunk);
                continue block0;
            }
        }
    }

    public void makeScragglyCrystalBranch(World world, int x, int y, int z, int len, int biasx, int biasz, Chunk chunk) {
        for (int k = 0; k < len; ++k) {
            int iy;
            Block bid;
            int ix = world.random.nextInt(2) - world.random.nextInt(2) + biasx;
            int iz = world.random.nextInt(2) - world.random.nextInt(2) + biasz;
            if (ix > 1) ix = 1;
            if (ix < -1) ix = -1;
            if (iz > 1) iz = 1;
            if (iz < -1) iz = -1;
            if ((bid = ChaosPersists.getBlockIDInChunk(chunk, x += ix, y += (iy = world.random.nextInt(3) > 0 ? 1 : 0), z += iz)) != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y, z, ChaosPersists.MyCrystalTreeLog, 0);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n)) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyCrystalLeaves2, 0);
                }
            }
            if (world.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + 1, z)) != Blocks.AIR) continue;
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + 1, z, ChaosPersists.MyCrystalLeaves2, 0);
        }
    }

    public void ScragglyCrystalTreeWithBranches(World world, int x, int y, int z, Chunk chunk) {
        int k;
        Block bid;
        int i = 1 + world.random.nextInt(2);
        int j = i + world.random.nextInt(8);
        for (k = 0; k < i; ++k) {
            bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + k, z);
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + k, z, ChaosPersists.MyCrystalTreeLog, 0);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = world.random.nextInt(2) - world.random.nextInt(2);
            int iz = world.random.nextInt(2) - world.random.nextInt(2);
            int iy = world.random.nextInt(4) > 0 ? 1 : 0;
            bid = ChaosPersists.getBlockIDInChunk(chunk, x += ix, y += iy, z += iz);
            if (bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) break;
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y, z, ChaosPersists.MyCrystalTreeLog, 0);
            if (world.random.nextInt(4) == 1) {
                this.makeScragglyCrystalBranch(world, x, y, z, world.random.nextInt(1 + j - k), world.random.nextInt(2) - world.random.nextInt(2), world.random.nextInt(2) - world.random.nextInt(2), chunk);
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n)) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyCrystalLeaves2, 0);
                }
            }
            if (world.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + 1, z)) != Blocks.AIR) continue;
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + 1, z, ChaosPersists.MyCrystalLeaves2, 0);
        }
    }

    public void TallCrystalTree(World world, int x, int y, int z, Chunk chunk) {
        int m;
        Block bid;
        int n;
        int k;
        int i = 10 + world.random.nextInt(12);
        if (ChaosPersists.LessLag == 1) {
            i -= 2;
        }
        if (ChaosPersists.LessLag == 2) {
            i -= 4;
        }
        int j = i + world.random.nextInt(18 - ChaosPersists.LessLag * 2);
        for (k = 0; k < i; ++k) {
            bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + k, z);
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + k, z, ChaosPersists.MyCrystalTreeLog, 0);
        }
        y += i - 1;
        for (k = i; k < j && ((bid = ChaosPersists.getBlockIDInChunk(chunk, x, ++y, z)) == Blocks.AIR || bid == ChaosPersists.MyCrystalTreeLog || bid == ChaosPersists.MyCrystalLeaves); ++k) {
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y, z, ChaosPersists.MyCrystalTreeLog, 0);
            if (k % 4 != 0) continue;
            for (m = -1; m < 2; ++m) {
                for (n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n)) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyCrystalLeaves, 0);
                }
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                if (world.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n)) != Blocks.AIR) continue;
                ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyCrystalTreeLog, 0);
            }
        }
        for (m = -3; m < 4; ++m) {
            for (n = -3; n < 4; ++n) {
                bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n);
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyCrystalLeaves, 0);
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n);
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyCrystalLeaves, 0);
            }
        }
    }

    public void generateCrystalOres(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        int i;
        int randPosY;
        int randPosX;
        int randPosZ;
        int patchy = 25 + random.nextInt(30);
        if (random.nextInt(20) == 0) {
            patchy += 30;
        }
        for (i = 0; i < patchy; ++i) {
            randPosX = 2 + chunkX + random.nextInt(12);
            randPosY = random.nextInt(128);
            randPosZ = 2 + chunkZ + random.nextInt(12);
            if (randPosY <= 45) continue;
            int j = random.nextInt(11);
            Block b = Blocks.AIR;
            switch (j) {
                case 0: b = ChaosPersists.MyUrchinSpawnBlock; break;
                case 1: b = ChaosPersists.MyFlounderSpawnBlock; break;
                case 2: b = ChaosPersists.MySkateSpawnBlock; break;
                case 3: b = ChaosPersists.MyRotatorSpawnBlock; break;
                case 4: b = ChaosPersists.MyPeacockSpawnBlock; break;
                case 5: b = ChaosPersists.MyFairySpawnBlock; break;
                case 6: b = ChaosPersists.MyDungeonBeastSpawnBlock; break;
                case 7: b = ChaosPersists.MyVortexSpawnBlock; break;
                case 8: b = ChaosPersists.MyRatSpawnBlock; break;
                case 9: b = ChaosPersists.MyWhaleSpawnBlock; break;
                case 10: b = ChaosPersists.MyIrukandjiSpawnBlock; break;
            }
            this.generateOre(world, random, randPosX, randPosY, randPosZ, chunk, b, 4, ChaosPersists.CrystalStone);
        }
        patchy = 3 + random.nextInt(8);
        for (i = 0; i < patchy; ++i) {
            randPosX = 2 + chunkX + random.nextInt(12);
            randPosY = random.nextInt(128);
            randPosZ = 2 + chunkZ + random.nextInt(12);
            this.generateOre(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.CrystalCoal, 6, ChaosPersists.CrystalStone);
        }
        patchy = 15 + random.nextInt(20);
        for (i = 0; i < patchy; ++i) {
            randPosX = 2 + chunkX + random.nextInt(12);
            randPosY = random.nextInt(128);
            randPosZ = 2 + chunkZ + random.nextInt(12);
            if (randPosY >= 25) continue;
            this.generateOre(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.CrystalRat, 6, ChaosPersists.CrystalStone);
        }
        patchy = 12 + random.nextInt(20);
        for (i = 0; i < patchy; ++i) {
            randPosX = 2 + chunkX + random.nextInt(12);
            randPosY = random.nextInt(128);
            randPosZ = 2 + chunkZ + random.nextInt(12);
            if (randPosY >= 25) continue;
            this.generateOre(world, random, randPosX, randPosY, randPosZ, chunk, ChaosPersists.CrystalFairy, 6, ChaosPersists.CrystalStone);
        }
    }

    public boolean generateOre(World par1World, Random par2Random, int par3, int par4, int par5, Chunk chunk, Block newbid, int numberOfBlocks, Block oldbid) {
        float f = par2Random.nextFloat() * (float) Math.PI;
        double d0 = (float) (par3 + 8) + MathHelper.sin(f) * (float) numberOfBlocks / 8.0f;
        double d1 = (float) (par3 + 8) - MathHelper.sin(f) * (float) numberOfBlocks / 8.0f;
        double d2 = (float) (par5 + 8) + MathHelper.cos(f) * (float) numberOfBlocks / 8.0f;
        double d3 = (float) (par5 + 8) - MathHelper.cos(f) * (float) numberOfBlocks / 8.0f;
        double d4 = par4 + par2Random.nextInt(3) - 2;
        double d5 = par4 + par2Random.nextInt(3) - 2;
        for (int l = 0; l <= numberOfBlocks; ++l) {
            double d6 = d0 + (d1 - d0) * (double) l / (double) numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double) l / (double) numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double) l / (double) numberOfBlocks;
            double d9 = par2Random.nextDouble() * (double) numberOfBlocks / 16.0;
            double d10 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) numberOfBlocks) + 1.0f) * d9 + 1.0;
            double d11 = (double) (MathHelper.sin((float) l * (float) Math.PI / (float) numberOfBlocks) + 1.0f) * d9 + 1.0;
            int i1 = MathHelper.floor(d6 - d10 / 2.0);
            int j1 = MathHelper.floor(d7 - d11 / 2.0);
            int k1 = MathHelper.floor(d8 - d10 / 2.0);
            int l1 = MathHelper.floor(d6 + d10 / 2.0);
            int i2 = MathHelper.floor(d7 + d11 / 2.0);
            int j2 = MathHelper.floor(d8 + d10 / 2.0);
            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double) k2 + 0.5 - d6) / (d10 / 2.0);
                if (d12 * d12 >= 1.0) continue;
                for (int l2 = j1; l2 <= i2; ++l2) {
                    double d13 = ((double) l2 + 0.5 - d7) / (d11 / 2.0);
                    if (d12 * d12 + d13 * d13 >= 1.0) continue;
                    for (int i3 = k1; i3 <= j2; ++i3) {
                        double d14 = ((double) i3 + 0.5 - d8) / (d10 / 2.0);
                        Block bid = ChaosPersists.getBlockIDInChunk(chunk, k2, l2, i3);
                        if (d12 * d12 + d13 * d13 + d14 * d14 >= 1.0 || bid != oldbid) continue;
                        ChaosPersists.setBlockIDWithMetadataInChunk(chunk, k2, l2, i3, newbid, 0);
                    }
                }
            }
        }
        return true;
    }

    public void addRice(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        if (random.nextInt(10) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 5; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 128; posY > 40; --posY) {
                if (ChaosPersists.getBlockIDInChunk(chunk, posX, posY, posZ) != Blocks.AIR || ChaosPersists.getBlockIDInChunk(chunk, posX, posY - 1, posZ) != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.setBlockIDWithMetadataInChunk(chunk, posX, posY, posZ, ChaosPersists.MyRicePlant, 0);
                continue block0;
            }
        }
    }

    public void addQuinoa(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        if (random.nextInt(20) != 0) {
            return;
        }
        block0:
        for (int i = 0; i < 5; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 128; posY > 40; --posY) {
                if (ChaosPersists.getBlockIDInChunk(chunk, posX, posY, posZ) != Blocks.AIR || ChaosPersists.getBlockIDInChunk(chunk, posX, posY - 1, posZ) != ChaosPersists.CrystalGrass) continue;
                ChaosPersists.setBlockIDWithMetadataInChunk(chunk, posX, posY, posZ, ChaosPersists.MyQuinoaPlant1, 0);
                continue block0;
            }
        }
    }

    public void addCrystalFlowers(World world, Random random, int chunkX, int chunkZ, Chunk chunk) {
        if (random.nextInt(3) != 0) {
            return;
        }
        int howmany = 1 + random.nextInt(13);
        int what = random.nextInt(4);
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = chunkX + random.nextInt(16);
            int posZ = chunkZ + random.nextInt(16);
            for (int posY = 128; posY > 40; --posY) {
                if (ChaosPersists.getBlockIDInChunk(chunk, posX, posY, posZ) != Blocks.AIR || ChaosPersists.getBlockIDInChunk(chunk, posX, posY - 1, posZ) != ChaosPersists.CrystalGrass) continue;
                if (what == 0) {
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, posX, posY, posZ, ChaosPersists.CrystalFlowerRedBlock, 0);
                    continue block0;
                }
                if (what == 1) {
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, posX, posY, posZ, ChaosPersists.CrystalFlowerGreenBlock, 0);
                    continue block0;
                }
                if (what == 2) {
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, posX, posY, posZ, ChaosPersists.CrystalFlowerBlueBlock, 0);
                    continue block0;
                }
                if (what == 3) {
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, posX, posY, posZ, ChaosPersists.CrystalFlowerYellowBlock, 0);
                }
                continue block0;
            }
        }
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
