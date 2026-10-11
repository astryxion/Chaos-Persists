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

public class BlockLettuce extends Block {
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);

    public BlockLettuce() {
        this(0);
    }

    protected BlockLettuce(int par1) {
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
        return bid == ChaosPersists.MyLettucePlant1
                || bid == ChaosPersists.MyLettucePlant2
                || bid == ChaosPersists.MyLettucePlant3
                || bid == ChaosPersists.MyLettucePlant4
                || bid == Blocks.GRASS_BLOCK
                || bid == Blocks.DIRT
                || bid == Blocks.FARMLAND;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource par5Random) {
        int var7 = state.getValue(AGE);
        if ((var7 &= 255) >= 4) {
            Block bid = level.getBlockState(pos).getBlock();
            if (bid == ChaosPersists.MyLettucePlant1) {
                level.setBlock(pos, ChaosPersists.MyLettucePlant2.defaultBlockState(), 2);
            } else if (bid == ChaosPersists.MyLettucePlant2) {
                level.setBlock(pos, ChaosPersists.MyLettucePlant3.defaultBlockState(), 2);
            } else if (bid == ChaosPersists.MyLettucePlant3) {
                level.setBlock(pos, ChaosPersists.MyLettucePlant4.defaultBlockState(), 2);
            }
        } else {
            Block bid = level.getBlockState(pos).getBlock();
            level.setBlock(pos, bid.defaultBlockState().setValue(AGE, Math.min(15, var7 + 1)), 2);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        if (this == ChaosPersists.MyLettucePlant4) {
            RandomSource r = builder.getLevel().getRandom();
            return Collections.singletonList(new ItemStack(ChaosPersists.MyLettuce, 2 + r.nextInt(3)));
        }
        return Collections.emptyList();
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyLettuce);
    }
}
