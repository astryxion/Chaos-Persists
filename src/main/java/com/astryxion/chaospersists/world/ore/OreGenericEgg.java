package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

public class OreGenericEgg extends FallingBlock {

    public OreGenericEgg() {
        this(0);
    }

    public OreGenericEgg(int oldid) {
        super(Block.Properties.of()
                .strength(0.6f, 3.0f)
                .sound(SoundType.GRAVEL)
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
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.world.item.ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        int xp = 5 + level.getRandom().nextInt(3) + level.getRandom().nextInt(3);
        if (level.getRandom().nextInt(2) == 1) {
            this.popExperience(level, pos, xp);
        }
    }
}
