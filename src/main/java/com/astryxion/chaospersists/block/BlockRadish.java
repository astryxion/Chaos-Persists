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

public class BlockRadish extends CropsBlock {

    public BlockRadish() {
        this(0);
    }

    public BlockRadish(int par1) {
        super(AbstractBlock.Properties.copy(Blocks.WHEAT).randomTicks().noCollission());
    }

    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
    }

    @Override
    protected Item getBaseSeedId() {
        return ChaosPersists.MyRadish;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyRadish);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        LootContext ctx = builder.withParameter(LootParameters.BLOCK_STATE, state).create(LootParameterSets.BLOCK);
        Random rand = ctx.getRandom();
        int count = 2 + rand.nextInt(4);
        return Collections.singletonList(new ItemStack(ChaosPersists.MyRadish, count));
    }
}
