package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;

public class BlockCorn extends Block {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);

    private int myMaxHeight = 0;

    public BlockCorn() {
        this(0);
    }

    protected BlockCorn(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.of().noCollission().randomTicks().sound(SoundType.CROP).noOcclusion());
        registerDefaultState(stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Block bid = level.getBlockState(pos.below()).getBlock();
        if (bid == Blocks.AIR) {
            return false;
        }
        return bid == ChaosPersists.MyCornPlant1
                || bid == ChaosPersists.MyCornPlant2
                || bid == ChaosPersists.MyCornPlant3
                || bid == ChaosPersists.MyCornPlant4
                || bid == Blocks.GRASS_BLOCK
                || bid == Blocks.DIRT
                || bid == Blocks.FARMLAND;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource par5Random) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        Block bid;
        int Height = 1;
        boolean dontGrow = false;
        if (this != ChaosPersists.MyCornPlant1 && this != ChaosPersists.MyCornPlant2) {
            return;
        }
        int var7 = state.getValue(AGE);
        this.myMaxHeight = var7 >> 8;
        var7 &= 255;
        if (this.myMaxHeight == 0) {
            this.myMaxHeight = 4 + ChaosPersists.ChaosRand.nextInt(4);
        }
        if (level.getBlockState(new BlockPos(par2, par3 + 1, par4)).isAir()) {
            for (int var6 = 1; var6 < 10; ++var6) {
                bid = level.getBlockState(new BlockPos(par2, par3 - var6, par4)).getBlock();
                if (bid != ChaosPersists.MyCornPlant1
                        && bid != ChaosPersists.MyCornPlant2
                        && bid != ChaosPersists.MyCornPlant3
                        && bid != ChaosPersists.MyCornPlant4) {
                    break;
                }
                ++Height;
                if (bid == ChaosPersists.MyCornPlant3 || bid == ChaosPersists.MyCornPlant4) {
                    dontGrow = true;
                }
            }
            if (dontGrow) {
                this.myMaxHeight = Height;
            }
            if (var7 >= 6 - this.myMaxHeight / 3) {
                if (Height < this.myMaxHeight) {
                    level.setBlock(
                            new BlockPos(par2, par3 + 1, par4), ChaosPersists.MyCornPlant1.defaultBlockState(), 2);
                    level.setBlock(new BlockPos(par2, par3, par4), ChaosPersists.MyCornPlant2.defaultBlockState(), 2);
                } else {
                    for (int i = 1; i < this.myMaxHeight - 1; ++i) {
                        bid = level.getBlockState(new BlockPos(par2, par3 - i, par4)).getBlock();
                        if (bid == ChaosPersists.MyCornPlant2) {
                            level.setBlock(
                                    new BlockPos(par2, par3 - i, par4),
                                    ChaosPersists.MyCornPlant3.defaultBlockState(),
                                    2);
                        } else if (bid == ChaosPersists.MyCornPlant3) {
                            level.setBlock(
                                    new BlockPos(par2, par3 - i, par4),
                                    ChaosPersists.MyCornPlant4.defaultBlockState(),
                                    2);
                        }
                    }
                    bid = level.getBlockState(new BlockPos(par2, par3, par4)).getBlock();
                    level.setBlock(new BlockPos(par2, par3, par4), bid.defaultBlockState(), 2);
                }
            } else {
                bid = level.getBlockState(new BlockPos(par2, par3, par4)).getBlock();
                level.setBlock(
                        new BlockPos(par2, par3, par4),
                        bid.defaultBlockState().setValue(AGE, Math.min(15, var7 + 1)),
                        2);
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        if (this == ChaosPersists.MyCornPlant4) {
            RandomSource r = builder.getLevel().getRandom();
            return Collections.singletonList(new ItemStack(ChaosPersists.MyCornCob, 1 + r.nextInt(2)));
        }
        return Collections.emptyList();
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyCornCob);
    }
}
