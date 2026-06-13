/*
 * Decompiled with CFR 0_125.
 *
 * 1.12.2: use PlayerEntity.onItemUse(BlockPos, Hand, Direction) — the old int-coord
 * method is never called by the game (matches 1.7.10: place MyExperiencePlant above soil).
 */
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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemExperienceTreeSeed extends Item {

    public ItemExperienceTreeSeed(int i) { super(new Item.Properties()); }

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
        if (ground != Blocks.GRASS_BLOCK && ground != Blocks.DIRT && ground != Blocks.FARMLAND) {
            return ActionResultType.FAIL;
        }

        BlockPos above = pos.above();
        if (!world.isEmptyBlock(above)) {
            return ActionResultType.FAIL;
        }

        if (!world.isClientSide) {
            world.setBlock(above, ChaosPersists.MyExperiencePlant.defaultBlockState(), 2);
            if (!player.isCreative()) {
                stack.shrink(1);
            }
        } else {
            for (int j1 = 0; j1 < 10; ++j1) {
                world.addParticle(ParticleTypes.HAPPY_VILLAGER,
                        (double) ((float) pos.getX() + world.random.nextFloat()),
                        (double) pos.getY() + 1.0 + (double) world.random.nextFloat(),
                        (double) ((float) pos.getZ() + world.random.nextFloat()),
                        0.0, 0.0, 0.0);
            }
        }

        return ActionResultType.SUCCESS;
    }
}
