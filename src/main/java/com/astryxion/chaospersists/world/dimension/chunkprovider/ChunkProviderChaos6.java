/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.world.dimension.chunkprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
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

public class ChunkProviderChaos6 extends ChunkGenerator {

    public static final Codec<ChunkProviderChaos6> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BiomeProvider.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
            Codec.LONG.fieldOf("seed").stable().forGetter(ChunkProviderChaos6::getSeed)
    ).apply(instance, ChunkProviderChaos6::new));

    private final long seed;
    private final Random hellRNG;
    private final Random random;
    private LegacyNoiseGeneratorOctaves netherNoiseGen1;
    private LegacyNoiseGeneratorOctaves netherNoiseGen2;
    private LegacyNoiseGeneratorOctaves netherNoiseGen3;
    private LegacyNoiseGeneratorOctaves slowsandGravelNoiseGen;
    private LegacyNoiseGeneratorOctaves netherrackExculsivityNoiseGen;
    public LegacyNoiseGeneratorOctaves netherNoiseGen6;
    public LegacyNoiseGeneratorOctaves netherNoiseGen7;
    private World worldObj;
    private double[] noiseField;
    private final double[] slowsandNoise = new double[256];
    private final double[] gravelNoise = new double[256];
    private final double[] netherrackExclusivityNoise = new double[256];
    double[] noiseData1;
    double[] noiseData2;
    double[] noiseData3;
    double[] noiseData4;
    double[] noiseData5;

    public ChunkProviderChaos6(BiomeProvider biomeProvider, long seed) {
        super(biomeProvider, DimensionSettings.bootstrap().structureSettings());
        this.seed = seed;
        this.hellRNG = new Random(seed);
        this.random = new Random(seed);
        this.netherNoiseGen1 = new LegacyNoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen2 = new LegacyNoiseGeneratorOctaves(this.hellRNG, 16);
        this.netherNoiseGen3 = new LegacyNoiseGeneratorOctaves(this.hellRNG, 8);
        this.slowsandGravelNoiseGen = new LegacyNoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherrackExculsivityNoiseGen = new LegacyNoiseGeneratorOctaves(this.hellRNG, 4);
        this.netherNoiseGen6 = new LegacyNoiseGeneratorOctaves(this.hellRNG, 10);
        this.netherNoiseGen7 = new LegacyNoiseGeneratorOctaves(this.hellRNG, 16);
    }

    public ChunkProviderChaos6(World worldIn, long seed) {
        this(resolveBiomeProvider(worldIn), seed);
        this.worldObj = worldIn;
    }

    private static BiomeProvider resolveBiomeProvider(World world) {
        if (world instanceof ServerWorld) {
            ServerChunkProvider chunkSource = (ServerChunkProvider) world.getChunkSource();
            ChunkGenerator generator = chunkSource.getGenerator();
            if (generator != null) {
                return generator.getBiomeSource();
            }
        }
        if (ChaosPersists.CHAOS_BIOME != null) {
            return new SingleBiomeProvider(ChaosPersists.CHAOS_BIOME);
        }
        return new SingleBiomeProvider(world.getBiome(new BlockPos(0, 64, 0)));
    }

    public long getSeed() {
        return this.seed;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        ChunkProviderChaos6 provider = new ChunkProviderChaos6(this.getBiomeSource(), seed);
        provider.worldObj = this.worldObj;
        return provider;
    }

    @Override
    public IBlockReader getBaseColumn(int x, int z) {
        final BlockState[] column = new BlockState[256];
        for (int y = 0; y < 256; ++y) {
            column[y] = y < 64 ? Blocks.STONE.defaultBlockState() : Blocks.AIR.defaultBlockState();
        }
        return new IBlockReader() {
            @Override
            public BlockState getBlockState(BlockPos pos) {
                int y = pos.getY();
                return y >= 0 && y < 256 ? column[y] : Blocks.AIR.defaultBlockState();
            }

            @Override
            public net.minecraft.fluid.FluidState getFluidState(BlockPos pos) {
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
        this.hellRNG.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);
        TerrainBuffer primer = new TerrainBuffer();
        this.generateTerrain(chunkX, chunkZ, primer);
        this.replaceBiomeBlocks(chunkX, chunkZ, primer);
        primer.writeToChunk(chunk);
        if (chunk instanceof Chunk) {
            this.addScragglyTrees(this.worldObj, chunkX * 16, chunkZ * 16, (Chunk) chunk);
        }
    }

    @Override
    public void buildSurfaceAndBedrock(WorldGenRegion region, IChunk chunk) {
        if (this.worldObj == null) {
            this.worldObj = region.getLevel();
        }
        int chunkX = chunk.getPos().x;
        int chunkZ = chunk.getPos().z;
        if (chunk instanceof Chunk) {
            ChaosPersists.Chunker.generateOresInChunk(this.worldObj, this.random, chunkX * 16, chunkZ * 16, (Chunk) chunk);
        }
    }

    @Override
    public void applyCarvers(long seed, net.minecraft.world.biome.BiomeManager biomeManager, IChunk chunk, GenerationStage.Carving type) {
        // 1.12 chaos islands: no overworld caves/carvers (would destroy floating terrain).
    }

    @Override
    public void applyBiomeDecoration(WorldGenRegion region, StructureManager structureManager) {
        // Decoration is handled by ChaosWorld (butterflies, veggies, ants) and addScragglyTrees in fillFromNoise.
    }

    private void generateTerrain(int chunkX, int chunkZ, TerrainBuffer primer) {
        int b0 = 4;
        int k = b0 + 1;
        int b2 = 17;
        int l = b0 + 1;
        this.noiseField = this.initializeNoiseField(this.noiseField, chunkX * b0, 0, chunkZ * b0, k, b2, l);
        for (int i1 = 0; i1 < b0; ++i1) {
            for (int j1 = 0; j1 < b0; ++j1) {
                for (int k1 = 0; k1 < 16; ++k1) {
                    double d0 = 0.125;
                    double d1 = this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0];
                    double d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0];
                    double d3 = this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0];
                    double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0];
                    double d5 = (this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0;
                    double d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
                    double d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0;
                    double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4) * d0;
                    for (int l1 = 0; l1 < 8; ++l1) {
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
                                int x = i2 + i1 * 4;
                                int y = k1 * 8 + l1;
                                int z = j1 * 4 + k2;
                                BlockState state = d15 > 0.0 ? Blocks.AIR.defaultBlockState() : Blocks.STONE.defaultBlockState();
                                primer.setBlockState(x, y, z, state);
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
    }

    private void replaceBiomeBlocks(int chunkX, int chunkZ, TerrainBuffer primer) {
        byte b0 = 64;
        double d0 = 0.03125D;
        this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0, d0, 1.0D);
        this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, chunkX * 16, 109, chunkZ * 16, 16, 1, 16, d0, 1.0D, d0);
        this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, d0 * 2.0D, d0 * 2.0D, d0 * 2.0D);

        for (int k = 0; k < 16; k++) {
            for (int l = 0; l < 16; l++) {
                boolean flag = this.slowsandNoise[k + l * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                boolean flag1 = this.gravelNoise[k + l * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D;
                int i1 = (int) (this.netherrackExclusivityNoise[k + l * 16] / 3.0D + 3.0D + this.hellRNG.nextDouble() * 0.25D);
                int j1 = -1;
                BlockState block = Blocks.GRASS_BLOCK.defaultBlockState();
                BlockState block1 = Blocks.DIRT.defaultBlockState();

                for (int k1 = 127; k1 >= 0; k1--) {
                    if ((k1 < 127 - this.hellRNG.nextInt(5)) && (k1 > 0 + this.hellRNG.nextInt(5))) {
                        BlockState block2 = primer.getBlockState(k, k1, l);

                        if ((block2.getBlock() != Blocks.AIR) && !block2.isAir()) {
                            if (block2.getBlock() == Blocks.STONE) {
                                if (j1 == -1) {
                                    if (i1 <= 0) {
                                        block = Blocks.AIR.defaultBlockState();
                                        block1 = Blocks.STONE.defaultBlockState();
                                    } else if ((k1 >= b0 - 4) && (k1 <= b0 + 1)) {
                                        block = Blocks.STONE.defaultBlockState();
                                        block1 = Blocks.STONE.defaultBlockState();

                                        if (flag1) {
                                            block = Blocks.GRASS_BLOCK.defaultBlockState();
                                            block1 = Blocks.DIRT.defaultBlockState();
                                        }

                                        if (flag) {
                                            block = Blocks.GRASS_BLOCK.defaultBlockState();
                                            block1 = Blocks.DIRT.defaultBlockState();
                                        }
                                    }

                                    if ((k1 < b0) && (block.getBlock() == Blocks.AIR)) {
                                        block = Blocks.WATER.defaultBlockState();
                                    }

                                    j1 = i1;

                                    if (k1 >= b0 - 1) {
                                        primer.setBlockState(k, k1, l, block);
                                    } else {
                                        primer.setBlockState(k, k1, l, block1);
                                    }
                                } else if (j1 > 0) {
                                    j1--;
                                    primer.setBlockState(k, k1, l, block1);
                                }
                            }
                        } else {
                            j1 = -1;
                        }
                    } else {
                        primer.setBlockState(k, k1, l, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
    }

    private double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_) {
        int i2;
        int k1 = 0;
        int l1 = 0;
        double[] adouble1 = new double[p_73164_6_];
        if (p_73164_1_ == null) {
            p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_];
        }
        double d0 = 684.412;
        double d1 = 2053.236;
        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0, 0.0, 1.0);
        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0, 0.0, 100.0);
        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0 / 80.0, d1 / 60.0, d0 / 80.0);
        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0);
        for (i2 = 0; i2 < p_73164_6_; ++i2) {
            adouble1[i2] = Math.cos((double) i2 * 3.141592653589793 * 6.0 / (double) p_73164_6_) * 2.0;
            double d2 = i2;
            if (i2 > p_73164_6_ / 2) {
                d2 = p_73164_6_ - 1 - i2;
            }
            if (d2 >= 4.0) continue;
            d2 = 4.0 - d2;
            double[] arrd = adouble1;
            int n = i2;
            arrd[n] = arrd[n] - d2 * d2 * d2 * 10.0;
        }
        for (i2 = 0; i2 < p_73164_5_; ++i2) {
            for (int k2 = 0; k2 < p_73164_7_; ++k2) {
                double d3 = (this.noiseData4[l1] + 256.0) / 512.0;
                if (d3 > 1.0) {
                    d3 = 1.0;
                }
                double d4 = 0.0;
                double d5 = this.noiseData5[l1] / 8000.0;
                if (d5 < 0.0) {
                    d5 = -d5;
                }
                if ((d5 = d5 * 3.0 - 3.0) < 0.0) {
                    if ((d5 /= 2.0) < -1.0) {
                        d5 = -1.0;
                    }
                    d5 /= 1.4;
                    d5 /= 2.0;
                    d3 = 0.0;
                } else {
                    if (d5 > 1.0) {
                        d5 = 1.0;
                    }
                    d5 /= 6.0;
                }
                d3 += 0.5;
                d5 = d5 * (double) p_73164_6_ / 16.0;
                ++l1;
                for (int j2 = 0; j2 < p_73164_6_; ++j2) {
                    double d11;
                    double d6 = 0.0;
                    double d7 = adouble1[j2];
                    double d8 = this.noiseData2[k1] / 512.0;
                    double d9 = this.noiseData3[k1] / 512.0;
                    double d10 = (this.noiseData1[k1] / 10.0 + 1.0) / 2.0;
                    d6 = d10 < 0.0 ? d8 : (d10 > 1.0 ? d9 : d8 + (d9 - d8) * d10);
                    d6 -= d7;
                    if (j2 > p_73164_6_ - 4) {
                        d11 = (float) (j2 - (p_73164_6_ - 4)) / 3.0f;
                        d6 = d6 * (1.0 - d11) + -10.0 * d11;
                    }
                    if ((double) j2 < d4) {
                        d11 = (d4 - (double) j2) / 4.0;
                        if (d11 < 0.0) {
                            d11 = 0.0;
                        }
                        if (d11 > 1.0) {
                            d11 = 1.0;
                        }
                        d6 = d6 * (1.0 - d11) + -10.0 * d11;
                    }
                    p_73164_1_[k1] = d6;
                    ++k1;
                }
            }
        }
        return p_73164_1_;
    }

    public String makeString() {
        return "ChaosDimension";
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

    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    public void addScragglyTrees(World world, int chunkX, int chunkZ, Chunk chunk) {
        int howmany = 1 + world.random.nextInt(5);
        if (world.random.nextInt(4) != 0) {
            return;
        }
        if (ChaosPersists.LessLag == 1) {
            howmany /= 2;
        }
        if (ChaosPersists.LessLag == 2) {
            howmany /= 4;
        }
        if (howmany == 0) {
            return;
        }
        block0:
        for (int i = 0; i < howmany; ++i) {
            int posX = 2 + chunkX + this.random.nextInt(12);
            int posZ = 2 + chunkZ + this.random.nextInt(12);
            for (int posY = 120; posY > 50; --posY) {
                if (ChaosPersists.getBlockIDInChunk(chunk, posX, posY - 1, posZ) != Blocks.GRASS_BLOCK) continue;
                this.ScragglyTreeWithBranches(world, posX, posY, posZ, chunk);
                continue block0;
            }
        }
    }

    public void makeScragglyBranch(World world, int x, int y, int z, int len, int biasx, int biasz, Chunk chunk) {
        for (int k = 0; k < len; ++k) {
            int iy;
            Block bid;
            int ix = this.random.nextInt(2) - this.random.nextInt(2) + biasx;
            int iz = this.random.nextInt(2) - this.random.nextInt(2) + biasz;
            if (ix > 1) ix = 1;
            if (ix < -1) ix = -1;
            if (iz > 1) iz = 1;
            if (iz < -1) iz = -1;
            if ((bid = ChaosPersists.getBlockIDInChunk(chunk, x += ix, y += (iy = this.random.nextInt(3) > 0 ? 1 : 0), z += iz)) != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y, z, Blocks.OAK_LOG, 0);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n)) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyAppleLeaves, 0);
                }
            }
            if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + 1, z)) != Blocks.AIR) continue;
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + 1, z, ChaosPersists.MyAppleLeaves, 0);
        }
    }

    public void ScragglyTreeWithBranches(World world, int x, int y, int z, Chunk chunk) {
        int k;
        Block bid;
        int i = 1 + this.random.nextInt(3);
        int j = i + this.random.nextInt(12);
        for (k = 0; k < i; ++k) {
            bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + k, z);
            if (k >= 1 && bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + k, z, Blocks.OAK_LOG, 0);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = this.random.nextInt(2) - this.random.nextInt(2);
            int iz = this.random.nextInt(2) - this.random.nextInt(2);
            int iy = this.random.nextInt(4) > 0 ? 1 : 0;
            bid = ChaosPersists.getBlockIDInChunk(chunk, x += ix, y += iy, z += iz);
            if (bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) break;
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y, z, Blocks.OAK_LOG, 0);
            if (this.random.nextInt(4) == 1) {
                this.makeScragglyBranch(world, x, y, z, this.random.nextInt(1 + j - k), this.random.nextInt(2) - this.random.nextInt(2), this.random.nextInt(2) - this.random.nextInt(2), chunk);
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x + m, y, z + n)) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x + m, y, z + n, ChaosPersists.MyAppleLeaves, 0);
                }
            }
            if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk(chunk, x, y + 1, z)) != Blocks.AIR) continue;
            ChaosPersists.setBlockIDWithMetadataInChunk(chunk, x, y + 1, z, ChaosPersists.MyAppleLeaves, 0);
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
                double d0 = (double) xOffset * d3 * xScale;
                double d1 = (double) yOffset * d3 * yScale;
                double d2 = (double) zOffset * d3 * zScale;
                int l = MathHelper.floor(d0);
                int i1 = MathHelper.floor(d1);
                int j1 = MathHelper.floor(d2);
                double d4 = d0 - (double) l;
                double d5 = d1 - (double) i1;
                double d6 = d2 - (double) j1;
                l %= 16777216;
                j1 %= 16777216;
                i1 %= 16777216;
                int l2 = 0;
                for (int i3 = 0; i3 < xSize; ++i3) {
                    double d13 = d0 + (double) i3 * xScale + d4;
                    int i4 = MathHelper.floor(d13);
                    d13 -= (double) i4;
                    i4 %= 16777216;
                    for (int j3 = 0; j3 < zSize; ++j3) {
                        double d15 = d2 + (double) j3 * zScale + d6;
                        int k1 = MathHelper.floor(d15);
                        d15 -= (double) k1;
                        k1 %= 16777216;
                        for (int k3 = 0; k3 < ySize; ++k3) {
                            double d17 = d1 + (double) k3 * yScale + d5;
                            int j2 = MathHelper.floor(d17);
                            d17 -= (double) j2;
                            j2 %= 16777216;
                            double d19 = 0.0D;
                            double d20 = 0.0D;
                            double d21 = 0.0D;
                            for (int k4 = 0; k4 < 2; ++k4) {
                                int l1 = (i4 + k4) % 16777216;
                                int i2 = (j2 + k4) % 16777216;
                                for (int j4 = 0; j4 < 2; ++j4) {
                                    int l3 = (k1 + j4) % 16777216;
                                    double d10 = this.generatorCollection[j].noise((double) l1 * 0.00390625D, (double) i2 * 0.00390625D, (double) l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d11 = this.generatorCollection[j].noise((double) (l1 + 1) * 0.00390625D, (double) i2 * 0.00390625D, (double) l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d12 = this.generatorCollection[j].noise((double) l1 * 0.00390625D, (double) (i2 + 1) * 0.00390625D, (double) l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d14 = this.generatorCollection[j].noise((double) (l1 + 1) * 0.00390625D, (double) (i2 + 1) * 0.00390625D, (double) l3 * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    double d16 = MathHelper.lerp(d15, MathHelper.lerp(d13, d10, d11), MathHelper.lerp(d13, d12, d14));
                                    d19 += d16;
                                    d10 = this.generatorCollection[j].noise((double) l1 * 0.00390625D, (double) i2 * 0.00390625D, (double) (l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d11 = this.generatorCollection[j].noise((double) (l1 + 1) * 0.00390625D, (double) i2 * 0.00390625D, (double) (l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d12 = this.generatorCollection[j].noise((double) l1 * 0.00390625D, (double) (i2 + 1) * 0.00390625D, (double) (l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
                                    d14 = this.generatorCollection[j].noise((double) (l1 + 1) * 0.00390625D, (double) (i2 + 1) * 0.00390625D, (double) (l3 + 1) * 0.00390625D, 0.0D, 0.0D) * 0.55D;
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
}
