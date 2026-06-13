package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ItemRandomDungeon extends Item {

    Random rand = ChaosPersists.ChaosRand;

    public ItemRandomDungeon(int i) { super(new Item.Properties()); }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity player) {
        stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
        }
    }

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
        ItemStack stack = player.getItemInHand(hand);
        Block clicked = world.getBlockState(pos).getBlock();
        if (clicked != Blocks.STONE && clicked != Blocks.COBBLESTONE && clicked != Blocks.GRASS_BLOCK && clicked != Blocks.DIRT) {
            return ActionResultType.FAIL;
        }
        if (pos.getY() < 40) {
            return ActionResultType.FAIL;
        }
        if (!world.isClientSide) {
            world.setBlock(pos.above(), ChaosPersists.MyDungeonSpawnerBlock.defaultBlockState(), 2);
        }
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return ActionResultType.SUCCESS;
    }
}
