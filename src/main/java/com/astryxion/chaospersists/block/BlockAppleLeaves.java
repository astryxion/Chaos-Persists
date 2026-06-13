package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;
import javax.annotation.Nullable;

public class BlockAppleLeaves extends LeavesBlock implements IForgeShearable {

    public BlockAppleLeaves() {
        super(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)
                .randomTicks()
                .strength(0.2F)
                .sound(SoundType.GRASS)
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PERSISTENT, false)
                .setValue(DISTANCE, 7));
    }

    @Override
    public boolean isShearable(ItemStack item, World world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(PlayerEntity player, ItemStack item, World world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.playerDestroy(world, player, pos, state, te, stack);
        if (!world.isClientSide && !player.isCreative()) {
            if (world.random.nextInt(25) == 1) {
                popResource(world, pos, new ItemStack(Items.APPLE));
            }
            if (world.random.nextInt(500) == 2) {
                popResource(world, pos, new ItemStack(Items.GOLDEN_APPLE));
            }
            if (world.random.nextInt(1000) == 3) {
                popResource(world, pos, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
            }
            if (world.random.nextInt(10000) == 4) {
                popResource(world, pos, new ItemStack(ChaosPersists.MagicApple));
            }
        }
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return 1;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        Block block = adjacentState.getBlock();
        return ChaosPersists.FastGraphicsLeaves == 0 || block != this;
    }

    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType(BlockState state) {
        return RenderType.translucent();
    }
}
