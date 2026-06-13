package com.astryxion.chaospersists.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Display name: Nether Tracker. Shapeless: nether star + netherrack.
 * <p>
 * While held (main or off hand) in the Nether standing on netherrack, each tick replaces the
 * netherrack block under your feet with quartz block. Also keeps Sharpness II on the stack
 * (matches OreSpawn 1.7.10; a previous 1.12 port mistakenly used Fire Aspect).
 */
public class ItemNetherLost extends Item {

    public ItemNetherLost(int par1) { super(new Item.Properties()); }

    @Override
    public void onCraftedBy(ItemStack stack, World world, PlayerEntity player) {
        stack.enchant(Enchantments.SHARPNESS, 2);
    }

    public void onUseTick(World world, PlayerEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.SHARPNESS, 2);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        this.onUseTick(world, null, stack, 0);
        if (world == null || entity == null || !(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        boolean holding = player.getMainHandItem() == stack || player.getOffhandItem() == stack;
        if (!holding) {
            return;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) != -1) {
            return;
        }
        BlockPos below = new BlockPos((int) player.getX(), (int) player.getY() - 1, (int) player.getZ());
        if (world.getBlockState(below).getBlock() != Blocks.NETHERRACK) {
            return;
        }
        if (!world.isClientSide) {
            world.setBlock(below, Blocks.QUARTZ_BLOCK.defaultBlockState(), 3);
        }
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 3000;
    }
}
