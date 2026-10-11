package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

public class OreRuby extends Block {

    public OreRuby() {
        this(SoundType.STONE);
    }

    public OreRuby(int i) {
        this(SoundType.STONE);
    }

    public OreRuby(SoundType sound) {
        super(Block.Properties.of()
                .strength(10.0f, 4.0f)
                .sound(sound)
                .requiresCorrectToolForDrops());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
        if (tool != null && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0) {
            return Collections.singletonList(new ItemStack(this));
        }
        RandomSource random = builder.getLevel().getRandom();
        int fortune = tool != null ? EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, tool) : 0;
        int count = 1 + random.nextInt(2);
        if (fortune > 0) {
            count += random.nextInt(fortune + 1);
        }
        return Collections.singletonList(new ItemStack(ChaosPersists.MyRuby, Math.max(1, count)));
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        RandomSource r = level.getRandom();
        this.popExperience(level, pos, 5 + r.nextInt(5) + r.nextInt(5));
    }
}
