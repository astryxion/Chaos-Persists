/*
 * Decompiled with CFR 0_125.
 */
package com.astryxion.chaospersists.world.dimension.chunkprovider;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
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
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

public class ChunkProviderChaos4 extends ChunkGenerator {

    private final long seed;
    private World worldObj;
    private Random random;
    private final BlockState[] cachedBlockStates = new BlockState[256];

    public ChunkProviderChaos4(BiomeProvider biomeProvider, long seed, boolean mapFeaturesEnabled) {
        super(biomeProvider, DimensionSettings.bootstrap().structureSettings());
        this.seed = seed;
        this.random = new Random(seed);
        for (int j = 0; j < 8; ++j) {
            this.cachedBlockStates[j] = j == 0 ? Blocks.BEDROCK.defaultBlockState() : (j == 7 ? Blocks.GRASS_BLOCK.defaultBlockState() : Blocks.DIRT.defaultBlockState());
        }
    }

    public ChunkProviderChaos4(World par1World, long par2, boolean par4) {
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
        if (ChaosPersists.DANGER_BIOME != null) {
            return new SingleBiomeProvider(ChaosPersists.DANGER_BIOME);
        }
        return new SingleBiomeProvider(world.getBiome(new BlockPos(0, 64, 0)));
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return Codec.unit(this);
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        ChunkProviderChaos4 provider = new ChunkProviderChaos4(this.getBiomeSource(), seed, false);
        provider.worldObj = this.worldObj;
        return provider;
    }

    @Override
    public IBlockReader getBaseColumn(int x, int z) {
        final BlockState[] column = new BlockState[256];
        for (int y = 0; y < 256; ++y) {
            if (y < 8) {
                column[y] = this.cachedBlockStates[y] != null ? this.cachedBlockStates[y] : Blocks.AIR.defaultBlockState();
            } else {
                column[y] = Blocks.AIR.defaultBlockState();
            }
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
        return 8;
    }

    @Override
    public void fillFromNoise(IWorld world, StructureManager structureManager, IChunk chunk) {
        if (world instanceof WorldGenRegion) {
            this.worldObj = ((WorldGenRegion) world).getLevel();
        } else if (world instanceof World) {
            this.worldObj = (World) world;
        }
        for (int k = 0; k < this.cachedBlockStates.length; ++k) {
            BlockState state = this.cachedBlockStates[k];
            if (state == null) {
                continue;
            }
            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j1 = 0; j1 < 16; ++j1) {
                    chunk.setBlockState(new BlockPos(i1, k, j1), state, false);
                }
            }
        }
        if (chunk instanceof Chunk) {
            Chunk chunkEntity = (Chunk) chunk;
            int chunkX = chunk.getPos().x * 16;
            int chunkZ = chunk.getPos().z * 16;
            this.addScragglyTrees(this.worldObj, chunkX, chunkZ, chunkEntity);
        }
    }

    @Override
    public void buildSurfaceAndBedrock(WorldGenRegion region, IChunk chunk) {
        if (this.worldObj == null) {
            this.worldObj = region.getLevel();
        }
    }

    @Override
    public void applyCarvers(long seed, net.minecraft.world.biome.BiomeManager biomeManager, IChunk chunk, GenerationStage.Carving type) {
        super.applyCarvers(seed, biomeManager, chunk, type);
    }

    @Override
    public void applyBiomeDecoration(WorldGenRegion region, StructureManager structureManager) {
        int par2 = region.getCenterX();
        int par3 = region.getCenterZ();
        int k = par2 * 16;
        int l = par3 * 16;
        this.random.setSeed(region.getSeed());
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long) par2 * i1 + (long) par3 * j1 ^ region.getSeed());
        super.applyBiomeDecoration(region, structureManager);
    }

    public boolean isChunkGeneratedAt(int x, int z) {
        return false;
    }

    public boolean tick() {
        return false;
    }

    public String makeString() {
        return "DangerDimension";
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

    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    public BlockPos func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    public void addScragglyTrees(World world, int chunkX, int chunkZ, Chunk chunk) {
        int howmany = 1 + this.random.nextInt(10);
        if (ChaosPersists.LessLag == 1) {
            howmany /= 2;
        }
        if (ChaosPersists.LessLag == 2) {
            howmany /= 4;
        }
        if (howmany == 0) {
            return;
        }
        block0 : for (int i = 0; i < howmany; ++i) {
            int posX = 2 + chunkX + this.random.nextInt(12);
            int posZ = 2 + chunkZ + this.random.nextInt(12);
            for (int posY = 20; posY > 2; --posY) {
                if (ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) posX, (int) (posY - 1), (int) posZ) != Blocks.GRASS_BLOCK) continue;
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
            if (ix > 1) {
                ix = 1;
            }
            if (ix < -1) {
                ix = -1;
            }
            if (iz > 1) {
                iz = 1;
            }
            if (iz < -1) {
                iz = -1;
            }
            if ((bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) (x += ix), (int) (y += (iy = this.random.nextInt(3) > 0 ? 1 : 0)), (int) (z += iz))) != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) x, (int) y, (int) z, (Block) Blocks.OAK_LOG, (int) 0);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) (x + m), (int) y, (int) (z + n))) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) (x + m), (int) y, (int) (z + n), (Block) ChaosPersists.MyAppleLeaves, (int) 0);
                }
            }
            if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) x, (int) (y + 1), (int) z)) != Blocks.AIR) continue;
            ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) x, (int) (y + 1), (int) z, (Block) ChaosPersists.MyAppleLeaves, (int) 0);
        }
    }

    public void ScragglyTreeWithBranches(World world, int x, int y, int z, Chunk chunk) {
        int k;
        Block bid;
        int i = 1 + this.random.nextInt(3);
        int j = i + this.random.nextInt(12);
        for (k = 0; k < i; ++k) {
            bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) x, (int) (y + k), (int) z);
            if (k >= 1 && bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) {
                return;
            }
            ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) x, (int) (y + k), (int) z, (Block) Blocks.OAK_LOG, (int) 0);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = this.random.nextInt(2) - this.random.nextInt(2);
            int iz = this.random.nextInt(2) - this.random.nextInt(2);
            int iy = this.random.nextInt(4) > 0 ? 1 : 0;
            bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) (x += ix), (int) (y += iy), (int) (z += iz));
            if (bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) break;
            ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) x, (int) y, (int) z, (Block) Blocks.OAK_LOG, (int) 0);
            if (this.random.nextInt(4) == 1) {
                this.makeScragglyBranch(world, x, y, z, this.random.nextInt(1 + j - k), this.random.nextInt(2) - this.random.nextInt(2), this.random.nextInt(2) - this.random.nextInt(2), chunk);
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) (x + m), (int) y, (int) (z + n))) != Blocks.AIR) continue;
                    ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) (x + m), (int) y, (int) (z + n), (Block) ChaosPersists.MyAppleLeaves, (int) 0);
                }
            }
            if (this.random.nextInt(2) != 1 || (bid = ChaosPersists.getBlockIDInChunk((Chunk) chunk, (int) x, (int) (y + 1), (int) z)) != Blocks.AIR) continue;
            ChaosPersists.setBlockIDWithMetadataInChunk((Chunk) chunk, (int) x, (int) (y + 1), (int) z, (Block) ChaosPersists.MyAppleLeaves, (int) 0);
        }
    }
}
