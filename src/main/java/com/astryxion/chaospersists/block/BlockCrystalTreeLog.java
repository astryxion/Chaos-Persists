package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

public class BlockCrystalTreeLog extends RotatedPillarBlock {

    public BlockCrystalTreeLog() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(0.2F).noOcclusion());
    }

    @Override
    public boolean isFlammable(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.singletonList(new ItemStack(ChaosPersists.MyCrystalTreeLog));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}
