package com.astryxion.chaospersists.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

/**
 * Display name: Nether Tracker. Shapeless: nether star + netherrack.
 * <p>
 * While held (main or off hand) in the Nether standing on netherrack, each tick replaces the
 * netherrack block under your feet with quartz block. Also keeps Sharpness II on the stack
 * (matches OreSpawn 1.7.10; a previous 1.12 port mistakenly used Fire Aspect).
 */
public class ItemNetherLost extends Item {

    public ItemNetherLost(int par1) {
        super(new Properties().stacksTo(1).durability(3000));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 2);
        }
    }

    private static void ensureSharpness(ItemStack stack, Level level) {
        if (level.isClientSide) {
            return;
        }
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.SHARPNESS, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 2);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        ensureSharpness(stack, level);
        if (level.isClientSide || !(entity instanceof Player player)) {
            return;
        }
        boolean holding = player.getMainHandItem() == stack || player.getOffhandItem() == stack;
        if (!holding) {
            return;
        }
        if (level.dimension() != Level.NETHER) {
            return;
        }
        BlockPos below = BlockPos.containing(player.getX(), player.getY() - 1.0, player.getZ());
        if (!level.getBlockState(below).is(Blocks.NETHERRACK)) {
            return;
        }
        level.setBlock(below, Blocks.QUARTZ_BLOCK.defaultBlockState(), 3);
    }
}
