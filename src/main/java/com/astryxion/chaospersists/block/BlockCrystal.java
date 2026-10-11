package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockCrystal extends Block {

    public BlockCrystal() {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .strength(4.0f, 4.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 6)
                .noOcclusion());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }
}
