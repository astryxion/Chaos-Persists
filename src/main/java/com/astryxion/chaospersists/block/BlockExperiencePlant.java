package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;

public class BlockExperiencePlant extends SugarCaneBlock {

    public BlockExperiencePlant() {
        this(0);
    }

    protected BlockExperiencePlant(int par1) {
        super(AbstractBlock.Properties.copy(Blocks.SUGAR_CANE).randomTicks().noCollission());
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos) {
        Block bid = world.getBlockState(pos.below()).getBlock();
        if (bid == Blocks.AIR) {
            return false;
        }
        return bid == Blocks.GRASS_BLOCK || bid == Blocks.DIRT || bid == Blocks.FARMLAND;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        if (world.random.nextInt(20) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 20; ++j1) {
            world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                    (double) ((float) pos.getX() + world.random.nextFloat()),
                    (double) pos.getY() + (double) world.random.nextFloat(),
                    (double) ((float) pos.getZ() + world.random.nextFloat()),
                    0.0, 0.0, 0.0);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (rand.nextInt(10) != 1) {
            return;
        }
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        ChaosPersists.chaospersistsTrees.ExperienceTree(world, pos.getX(), pos.getY() - 1, pos.getZ());
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyExperiencePlant);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(ChaosPersists.MyExperiencePlant));
    }

    protected Item getSeedItem() {
        return ChaosPersists.MyExperienceTreeSeed;
    }
}
