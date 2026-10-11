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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;

public class BlockQuinoa extends Block {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);

    private int myMaxHeight = 0;

    public BlockQuinoa() {
        this(0);
    }

    protected BlockQuinoa(int par1) {
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
        return bid == ChaosPersists.MyQuinoaPlant1
                || bid == ChaosPersists.MyQuinoaPlant2
                || bid == ChaosPersists.MyQuinoaPlant3
                || bid == ChaosPersists.MyQuinoaPlant4
                || bid == Blocks.GRASS_BLOCK
                || bid == Blocks.DIRT
                || bid == Blocks.FARMLAND
                || bid == ChaosPersists.CrystalGrass;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource par5Random) {
        Block bid;
        int Height = 1;
        boolean dontGrow = false;
        if (this != ChaosPersists.MyQuinoaPlant1 && this != ChaosPersists.MyQuinoaPlant3) {
            return;
        }
        int var7 = state.getValue(AGE);
        this.myMaxHeight = var7 >> 8;
        var7 &= 255;
        if (this.myMaxHeight == 0) {
            this.myMaxHeight = 2 + ChaosPersists.ChaosRand.nextInt(3);
        }
        if (level.getBlockState(pos.above()).isAir()) {
            for (int var6 = 1; var6 < 10; ++var6) {
                bid = level.getBlockState(pos.below(var6)).getBlock();
                if (bid != ChaosPersists.MyQuinoaPlant1
                        && bid != ChaosPersists.MyQuinoaPlant2
                        && bid != ChaosPersists.MyQuinoaPlant3
                        && bid != ChaosPersists.MyQuinoaPlant4) {
                    break;
                }
                ++Height;
                if (bid == ChaosPersists.MyQuinoaPlant3 || bid == ChaosPersists.MyQuinoaPlant4) {
                    dontGrow = true;
                }
            }
            if (dontGrow) {
                this.myMaxHeight = Height;
            }
            if (var7 >= 5 - this.myMaxHeight / 3) {
                if (Height < this.myMaxHeight) {
                    level.setBlock(pos.above(), ChaosPersists.MyQuinoaPlant1.defaultBlockState(), 2);
                    level.setBlock(pos, ChaosPersists.MyQuinoaPlant2.defaultBlockState(), 2);
                } else {
                    bid = level.getBlockState(pos).getBlock();
                    if (bid == ChaosPersists.MyQuinoaPlant1) {
                        level.setBlock(pos, ChaosPersists.MyQuinoaPlant3.defaultBlockState(), 2);
                    } else if (bid == ChaosPersists.MyQuinoaPlant3) {
                        level.setBlock(pos, ChaosPersists.MyQuinoaPlant4.defaultBlockState(), 2);
                    }
                    bid = level.getBlockState(pos).getBlock();
                    level.setBlock(pos, bid.defaultBlockState(), 2);
                }
            } else {
                bid = level.getBlockState(pos).getBlock();
                level.setBlock(pos, bid.defaultBlockState(), 2);
            }
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        if (this == ChaosPersists.MyQuinoaPlant4) {
            RandomSource r = builder.getLevel().getRandom();
            return Collections.singletonList(new ItemStack(ChaosPersists.MyQuinoa, 3 + r.nextInt(3)));
        }
        return Collections.emptyList();
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyQuinoa);
    }
}
