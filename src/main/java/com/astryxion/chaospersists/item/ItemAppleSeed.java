package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemAppleSeed extends Item {

    public ItemAppleSeed(int i) { super(new Item.Properties()); }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Hand hand = context.getHand();
        Direction facing = context.getClickedFace();

        if (facing != Direction.UP) {
            return ActionResultType.FAIL;
        }

        ItemStack stack = player.getItemInHand(hand);

        Block ground = world.getBlockState(pos).getBlock();
        if (ground != Blocks.GRASS_BLOCK &&
            ground != Blocks.DIRT &&
            ground != Blocks.FARMLAND) {
            return ActionResultType.FAIL;
        }

        if (!world.isClientSide) {

            if (this == ChaosPersists.MyAppleSeed) {
                makeTree(world, pos.getX(), pos.getY(), pos.getZ(), ChaosPersists.MyAppleLeaves, null);
            } else if (this == ChaosPersists.MyCherrySeed) {
                makeTree(world, pos.getX(), pos.getY(), pos.getZ(), ChaosPersists.MyCherryLeaves, null);
            } else {
                makeTree(world, pos.getX(), pos.getY(), pos.getZ(), ChaosPersists.MyPeachLeaves, null);
            }

            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        return ActionResultType.SUCCESS;
    }

    public void makeTree(World world, int x, int y, int z, Block blkid, Chunk chunk) {

        Block ground = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (ground != Blocks.GRASS_BLOCK &&
            ground != Blocks.DIRT &&
            ground != Blocks.FARMLAND) {
            return;
        }

        int h1 = 12;
        int h2 = 6;
        int h3 = 9;
        int h4 = 6;
        int h5 = 14;
        int w1 = 5;
        int w2 = 3;

        if (blkid == ChaosPersists.MyPeachLeaves) {
            h1 = 10; h2 = 5; h3 = 7; h4 = 5; h5 = 12;
            w1 = 4; w2 = 2;
        }

        if (blkid == ChaosPersists.MyCherryLeaves) {
            h1 = 8; h2 = 3; h3 = 5; h4 = 3; h5 = 10;
            w1 = 3; w2 = 1;
        }

        // Main trunk
        for (int j = 1; j < h1; j++) {
            world.setBlock(new BlockPos(x, y + j, z), Blocks.OAK_LOG.defaultBlockState(), 2);
        }

        // First branch layer
        for (int j = 1; j < w1; j++) {
            ChaosPersists.setBlockSuperFast(world, x + j, y + h2, z, Blocks.OAK_LOG, 0, 2, chunk);
            ChaosPersists.setBlockSuperFast(world, x - j, y + h2, z, Blocks.OAK_LOG, 0, 2, chunk);
            ChaosPersists.setBlockSuperFast(world, x, y + h2, z + j, Blocks.OAK_LOG, 0, 2, chunk);
            ChaosPersists.setBlockSuperFast(world, x, y + h2, z - j, Blocks.OAK_LOG, 0, 2, chunk);
        }

        // Second branch layer
        for (int j = 1; j < w2; j++) {
            ChaosPersists.setBlockSuperFast(world, x + j, y + h3, z, Blocks.OAK_LOG, 0, 2, chunk);
            ChaosPersists.setBlockSuperFast(world, x - j, y + h3, z, Blocks.OAK_LOG, 0, 2, chunk);
            ChaosPersists.setBlockSuperFast(world, x, y + h3, z + j, Blocks.OAK_LOG, 0, 2, chunk);
            ChaosPersists.setBlockSuperFast(world, x, y + h3, z - j, Blocks.OAK_LOG, 0, 2, chunk);
        }

        // Leaf canopy
        for (int i = h4; i < h5; i++) {

            int width = 6;
            if (i > 8) width = 5;
            if (i > 10) width = 4;

            if (blkid != ChaosPersists.MyAppleLeaves) {
                width--;
            }

            for (int j = -width; j <= width; j++) {
                for (int k = -width; k <= width; k++) {

                    Block existing = world.getBlockState(new BlockPos(x + k, y + i, z + j)).getBlock();
                    if (existing != Blocks.AIR) continue;

                    ChaosPersists.setBlockSuperFast(world,
                            x + k,
                            y + i,
                            z + j,
                            blkid,
                            0,
                            2,
                            chunk);
                }
            }
        }
    }
}
