package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockRuby extends Block {

    public BlockRuby() {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .strength(4.0f, 4.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 8)
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
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (this == ChaosPersists.MyBlockMobzillaScaleBlock && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (this == ChaosPersists.MyBlockMobzillaScaleBlock && entity instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
        }
        super.stepOn(level, pos, state, entity);
    }
}
