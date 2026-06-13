package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class OreAmethyst extends Block {

    public OreAmethyst() {
        this(0);
    }

    public OreAmethyst(int i) {
        super(AbstractBlock.Properties.of(Material.STONE).strength(10.0f, 4.0f).harvestTool(ToolType.PICKAXE).harvestLevel(2));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        Random rand = builder.getOptionalParameter(LootParameters.THIS_ENTITY) != null
            ? builder.getLevel().random
            : new Random();
        int fortune = 0;
        if (builder.getOptionalParameter(LootParameters.TOOL) != null) {
            fortune = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE,
                builder.getOptionalParameter(LootParameters.TOOL));
        }
        int count = 1 + rand.nextInt(2);
        if (fortune > 0) {
            count += rand.nextInt(fortune + 1);
        }
        count = Math.max(1, count);
        Item drop = ChaosPersists.MyAmethyst;
        return Collections.nCopies(count, new ItemStack(drop));
    }

    @Override
    public void spawnAfterBreak(BlockState state, net.minecraft.world.server.ServerWorld world, BlockPos pos, ItemStack stack) {
        super.spawnAfterBreak(state, world, pos, stack);
        int xp = 5 + world.random.nextInt(5) + world.random.nextInt(5);
        popExperience(world, pos, xp);
    }

    @Override
    public boolean canHarvestBlock(BlockState state, IBlockReader world, BlockPos pos, PlayerEntity player) {
        return true;
    }
}
