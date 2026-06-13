package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ZooCage extends Item {
    private int cage_size = 2;

    public ZooCage(int i, int j) {
        super(new Item.Properties());
        this.cage_size = j;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity PlayerEntity = context.getPlayer();
        if (PlayerEntity == null) {
            return ActionResultType.FAIL;
        }
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Hand hand = context.getHand();
        ItemStack stack = PlayerEntity.getItemInHand(hand);
        int length;
        int dirx = 0;
        int dirz = 0;
        int width = length = this.cage_size / 2 + 1;
        int height = length;
        int cposx = pos.getX();
        int cposz = pos.getZ();
        if (cposx < 0) {
            dirx = -1;
        }
        if (cposz < 0) {
            dirz = -1;
        }
        int x = (int) (PlayerEntity.getX() + 0.99 * (double) dirx);
        int y = (int) PlayerEntity.getY() - 1;
        int z = (int) (PlayerEntity.getZ() + 0.99 * (double) dirz);
        world.playSound(null, PlayerEntity.getX(), PlayerEntity.getY(), PlayerEntity.getZ(), SoundEvents.GENERIC_EXPLODE, PlayerEntity.getSoundSource(), 1.0f, 1.5f);
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        for (int i = -width; i <= width; ++i) {
            for (int j = -length; j <= length; ++j) {
                for (int k = 0; k <= height + 1; ++k) {
                    BlockPos bp = new BlockPos(x + i, y + k, z + j);
                    if (k == height + 1) {
                        world.setBlock(bp, Blocks.QUARTZ_BLOCK.defaultBlockState(), 3);
                        continue;
                    }
                    if (k == 0) {
                        world.setBlock(bp, Blocks.QUARTZ_BLOCK.defaultBlockState(), 3);
                        continue;
                    }
                    if (i == width || j == length || i == -width || j == -length) {
                        world.setBlock(bp, Blocks.GLASS.defaultBlockState(), 3);
                        continue;
                    }
                    world.setBlock(bp, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
        if (!PlayerEntity.isCreative()) {
            stack.shrink(1);
        }
        return ActionResultType.SUCCESS;
    }
}
