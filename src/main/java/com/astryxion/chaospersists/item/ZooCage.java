package com.astryxion.chaospersists.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class ZooCage extends Item {
    private final int cage_size;

    public ZooCage(int i, int j) {
        super(new Properties().stacksTo(16));
        this.cage_size = j;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
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
        if (player == null) {
            return InteractionResult.PASS;
        }
        int x = (int) (player.getX() + 0.99 * (double) dirx);
        int y = (int) player.getY() - 1;
        int z = (int) (player.getZ() + 0.99 * (double) dirz);
        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(),
                SoundSource.PLAYERS,
                1.0f,
                1.5f);
        if (world.isClientSide) {
            return InteractionResult.SUCCESS;
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
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
