package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockStrawberry extends CropsBlock {

    public BlockStrawberry() {
        this(0);
    }

    public BlockStrawberry(int par1) {
        super(AbstractBlock.Properties.copy(Blocks.WHEAT).randomTicks().noCollission());
    }

    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
    }

    @Override
    protected Item getBaseSeedId() {
        return ChaosPersists.MyStrawberrySeed;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyStrawberrySeed);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        LootContext ctx = builder.withParameter(LootParameters.BLOCK_STATE, state).create(LootParameterSets.BLOCK);
        Random rand = ctx.getRandom();
        int count = 1 + rand.nextInt(5);
        return Collections.singletonList(new ItemStack(ChaosPersists.MyStrawberry, count));
    }
}
