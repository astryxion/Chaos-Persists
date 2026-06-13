package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IBlockReader;

public class BlockDuplicatorLog extends Block {

    public BlockDuplicatorLog() {
        this(0.2F);
    }

    public BlockDuplicatorLog(float hardness) {
        super(AbstractBlock.Properties.of(Material.WOOD)
                .sound(SoundType.WOOD)
                .strength(hardness));
    }

    protected BlockDuplicatorLog(int par1, int par2) {
        this(0.2F);
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyDT);
    }

    public void breakRecursor(World world, int x, int y, int z, int xf, int yf, int zf, int recursion) {
        int var7 = 1;
        if (recursion > 1000) {
            return;
        }
        for (int var9 = -var7; var9 <= var7; ++var9) {
            for (int var10 = -var7; var10 <= var7; ++var10) {
                for (int var11 = -var7; var11 <= var7; ++var11) {
                    if (var9 == 0 && var10 == 0 && var11 == 0
                            || x + var9 == xf && y + var10 == yf && z + var11 == zf
                            || recursion > 0 && x + var9 >= xf - var7 && x + var9 <= xf + var7
                            && y + var10 >= yf - var7 && y + var10 <= yf + var7
                            && z + var11 >= zf - var7 && z + var11 <= zf + var7) {
                        continue;
                    }
                    BlockPos p = new BlockPos(x + var9, y + var10, z + var11);
                    if (world.getBlockState(p).getBlock() != this) {
                        continue;
                    }
                    BlockState oldState = world.getBlockState(p);
                    world.setBlock(p, Blocks.AIR.defaultBlockState(), 2);
                    popResource((ServerWorld) world, p, new ItemStack(ChaosPersists.MyDT));
                    breakRecursor(world, x + var9, y + var10, z + var11, x, y, z, recursion + 1);
                }
            }
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClientSide) {
            breakRecursor(world, pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ(), 0);
        }
        super.playerWillDestroy(world, pos, state, player);
    }
}
