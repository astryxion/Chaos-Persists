package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockCrystalPlant extends Block {
    private static void plantSetBlock(Level level, int x, int y, int z, Block block) {
        level.setBlock(new BlockPos(x, y, z), block.defaultBlockState(), 2);
    }
    private static final VoxelShape SAPLING_SHAPE = Shapes.box(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

    public BlockCrystalPlant() { this(0); }
    protected BlockCrystalPlant(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.of().noCollission().randomTicks().noOcclusion().sound(SoundType.GRASS));
        
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SAPLING_SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader par1World, BlockPos pos) {
        Block bid = par1World.getBlockState(pos.below()).getBlock();
        if (bid == Blocks.AIR) {
            return false;
        }
        if (bid == Blocks.GRASS_BLOCK || bid == Blocks.DIRT || bid == Blocks.FARMLAND || bid == ChaosPersists.CrystalGrass) {
            return true;
        }
        return false;
    }

    @Override
    public void animateTick(BlockState stateIn, Level par1World, BlockPos pos, RandomSource par5Random) {
        if (par1World.random.nextInt(30) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 10; ++j1) {
            par1World.addParticle(ParticleTypes.HAPPY_VILLAGER, (float)pos.getX() + par5Random.nextFloat(), (double)pos.getY() + par5Random.nextFloat(), (float)pos.getZ() + par5Random.nextFloat(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel par1World, BlockPos pos, RandomSource par5Random) {
        if (par1World.getRandom().nextInt(5) != 1) {
            return;
        }
        par1World.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        if (this == ChaosPersists.MyCrystalPlant) {
            this.TallCrystalTree(par1World, pos.getX(), pos.getY(), pos.getZ());
        }
        if (this == ChaosPersists.MyCrystalPlant2) {
            this.ScragglyCrystalTreeWithBranches(par1World, pos.getX(), pos.getY(), pos.getZ());
        }
        if (this == ChaosPersists.MyCrystalPlant3) {
            this.TallCrystalTreeBlue(par1World, pos.getX(), pos.getY(), pos.getZ());
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        if (this == ChaosPersists.MyCrystalPlant) {
            return Collections.singletonList(new ItemStack(ChaosPersists.MyCrystalPlant));
        }
        if (this == ChaosPersists.MyCrystalPlant2) {
            return Collections.singletonList(new ItemStack(ChaosPersists.MyCrystalPlant2));
        }
        return Collections.singletonList(new ItemStack(ChaosPersists.MyCrystalPlant3));
    }

    public void TallCrystalTree(Level level, int x, int y, int z) {
        int n;
        int m;
        int k;
        Block bid;
        int i = 10 + level.getRandom().nextInt(12);
        int j = i + level.getRandom().nextInt(18);
        for (k = 0; k < i; ++k) {
            bid = level.getBlockState(new BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves) {
                return;
            }
            plantSetBlock(level, (int)x, (int)(y + k), (int)z, ChaosPersists.MyCrystalTreeLog);
        }
        y += i - 1;
        for (k = i; k < j && ((bid = level.getBlockState(new BlockPos(x, ++y, z)).getBlock()) == Blocks.AIR || bid == ChaosPersists.MyCrystalTreeLog || bid == ChaosPersists.MyCrystalLeaves); ++k) {
            plantSetBlock(level, (int)x, (int)y, (int)z, ChaosPersists.MyCrystalTreeLog);
            if (k % 4 != 0) continue;
            for (m = -1; m < 2; ++m) {
                for (n = -1; n < 2; ++n) {
                    if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves);
                }
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalTreeLog);
            }
        }
        for (m = -3; m < 4; ++m) {
            for (n = -3; n < 4; ++n) {
                bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves);
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves);
            }
        }
    }

    public void makeScragglyCrystalBranch(Level level, int x, int y, int z, int len, int biasx, int biasz) {
        for (int k = 0; k < len; ++k) {
            Block bid;
            int iy;
            int ix = level.getRandom().nextInt(2) - level.getRandom().nextInt(2) + biasx;
            int iz = level.getRandom().nextInt(2) - level.getRandom().nextInt(2) + biasz;
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
            if ((bid = level.getBlockState(new BlockPos(x += ix, y += (iy = level.getRandom().nextInt(3) > 0 ? 1 : 0), z += iz)).getBlock()) != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) {
                return;
            }
            plantSetBlock(level, (int)x, (int)y, (int)z, ChaosPersists.MyCrystalTreeLog);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves2);
                }
            }
            if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x, y + 1, z)).getBlock()) != Blocks.AIR) continue;
            plantSetBlock(level, (int)x, (int)(y + 1), (int)z, ChaosPersists.MyCrystalLeaves2);
        }
    }

    public void ScragglyCrystalTreeWithBranches(Level level, int x, int y, int z) {
        int k;
        Block bid;
        int i = 1 + level.getRandom().nextInt(2);
        int j = i + level.getRandom().nextInt(8);
        for (k = 0; k < i; ++k) {
            bid = level.getBlockState(new BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) {
                return;
            }
            plantSetBlock(level, (int)x, (int)(y + k), (int)z, ChaosPersists.MyCrystalTreeLog);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = level.getRandom().nextInt(2) - level.getRandom().nextInt(2);
            int iz = level.getRandom().nextInt(2) - level.getRandom().nextInt(2);
            int iy = level.getRandom().nextInt(4) > 0 ? 1 : 0;
            bid = level.getBlockState(new BlockPos(x += ix, y += iy, z += iz)).getBlock();
            if (bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) break;
            plantSetBlock(level, (int)x, (int)y, (int)z, ChaosPersists.MyCrystalTreeLog);
            if (level.getRandom().nextInt(4) == 1) {
                this.makeScragglyCrystalBranch(level, x, y, z, level.getRandom().nextInt(1 + j - k), level.getRandom().nextInt(2) - level.getRandom().nextInt(2), level.getRandom().nextInt(2) - level.getRandom().nextInt(2));
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves2);
                }
            }
            if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x, y + 1, z)).getBlock()) != Blocks.AIR) continue;
            plantSetBlock(level, (int)x, (int)(y + 1), (int)z, ChaosPersists.MyCrystalLeaves2);
        }
    }

    public void TallCrystalTreeBlue(Level level, int x, int y, int z) {
        int n;
        int m;
        int k;
        Block bid;
        int i = 5 + level.getRandom().nextInt(6);
        int j = 2 + i + level.getRandom().nextInt(12);
        for (k = 0; k < i; ++k) {
            bid = level.getBlockState(new BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves3) {
                return;
            }
            plantSetBlock(level, (int)x, (int)(y + k), (int)z, ChaosPersists.MyCrystalTreeLog);
        }
        y += i - 1;
        for (k = i; k < j && ((bid = level.getBlockState(new BlockPos(x, ++y, z)).getBlock()) == Blocks.AIR || bid == ChaosPersists.MyCrystalTreeLog || bid == ChaosPersists.MyCrystalLeaves3); ++k) {
            plantSetBlock(level, (int)x, (int)y, (int)z, ChaosPersists.MyCrystalTreeLog);
            if (k % 3 != 0) continue;
            for (m = -1; m < 2; ++m) {
                for (n = -1; n < 2; ++n) {
                    if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves3);
                }
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                if (level.getRandom().nextInt(2) != 1 || (bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalTreeLog);
            }
        }
        for (m = -3; m < 4; ++m) {
            for (n = -3; n < 4; ++n) {
                bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves3);
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                bid = level.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                plantSetBlock(level, (int)(x + m), (int)y, (int)(z + n), ChaosPersists.MyCrystalLeaves3);
            }
        }
    }
}


