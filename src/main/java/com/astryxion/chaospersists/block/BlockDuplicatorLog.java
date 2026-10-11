package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

public class BlockDuplicatorLog extends Block {

    public BlockDuplicatorLog() {
        this(0);
    }

    protected BlockDuplicatorLog(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.of().sound(SoundType.WOOD).randomTicks().strength(2.0f));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (ChaosPersists.enableduplicatortree != 0) {
            ChaosPersists.chaospersistsTrees.DuplicatorTree(level, pos.getX(), pos.getY(), pos.getZ());
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.singletonList(new ItemStack(this));
    }

    public boolean isFlammable(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.core.Direction direction) {
        return true;
    }
}
