package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IForgeShearable;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.extensions.IForgeBlockState;

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
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;
import javax.annotation.Nullable;

public class BlockScaryLeaves extends LeavesBlock implements IForgeShearable, IPlantable {

    public BlockScaryLeaves() {
        this(0.2F);
    }

    public BlockScaryLeaves(float hardness) {
        super(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)
                .randomTicks()
                .strength(hardness)
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

    private void maybeDropCherryOrPeach(ServerWorld world, BlockPos dropPos, Random rand) {
        if (rand.nextInt(25) != 1) {
            return;
        }
        if (this == ChaosPersists.MyCherryLeaves) {
            popResource(world, dropPos, new ItemStack(ChaosPersists.MyCherry));
        } else if (this == ChaosPersists.MyPeachLeaves) {
            popResource(world, dropPos, new ItemStack(ChaosPersists.MyPeach));
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        maybeDropCherryOrPeach(world, pos, world.random);
        super.spawnAfterBreak(state, world, pos, stack);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        int var7 = 2;
        if (world.hasChunksAt(pos.offset(-var7, -var7, -var7), pos.offset(var7, var7, var7))) {
            for (int var12 = -var7; var12 <= var7; ++var12) {
                for (int var13 = -var7; var13 <= 0; ++var13) {
                    for (int var14 = -var7; var14 <= var7; ++var14) {
                        BlockPos off = new BlockPos(par2 + var12, par3 + var13, par4 + var14);
                        int totaldist = Math.abs(var12) + Math.abs(var13) + Math.abs(var14);
                        BlockState offState = world.getBlockState(off);
                        Block bid = offState.getBlock();
                        if (totaldist > 3
                                || bid == Blocks.AIR
                                || !offState.canSustainPlant(world, off, net.minecraft.util.Direction.UP, this)) {
                            continue;
                        }
                        long t = world.getDayTime();
                        if (this == ChaosPersists.MyScaryLeaves && (t % 24000L) < 12000L) {
                            ChaosPersists.setBlockFast(world, par2, par3, par4, ChaosPersists.MyAppleLeaves, 0, 3);
                        }
                        if (world.getBlockState(new BlockPos(par2, par3 - 1, par4)).getBlock() == Blocks.AIR
                                && world.random.nextInt(20) == 3) {
                            maybeDropCherryOrPeach(world, new BlockPos(par2, par3 - 1, par4), world.random);
                        }
                        return;
                    }
                }
            }
            removeLeaves(world, par2, par3, par4);
        }
    }

    private void removeLeaves(ServerWorld world, int par2, int par3, int par4) {
        BlockPos pos = new BlockPos(par2, par3, par4);
        BlockState st = world.getBlockState(pos);
        this.maybeDropCherryOrPeach(world, pos, world.random);
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        return ChaosPersists.FastGraphicsLeaves == 0 || adjacentState.getBlock() != this;
    }

    @OnlyIn(Dist.CLIENT)
    public RenderType getRenderType(BlockState state) {
        return RenderType.translucent();
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.PLAINS;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return this.defaultBlockState();
    }
}
