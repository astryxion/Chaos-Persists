package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class OreSalt extends Block {

    public OreSalt() {
        this(SoundType.STONE);
    }

    public OreSalt(SoundType sound) {
        super(Block.Properties.of()
                .strength(5.0f, 2.0f)
                .sound(sound)
                .requiresCorrectToolForDrops());
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
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof EntityAnt) {
            entity.hurt(level.damageSources().cactus(), 5.0f);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof EntityAnt) {
            entity.hurt(level.damageSources().cactus(), 5.0f);
        }
        super.stepOn(level, pos, state, entity);
    }
}
