package com.astryxion.chaospersists.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class EmeraldPickaxe extends PickaxeItem {
    private static final int WEAPON_DAMAGE = 8;

    public EmeraldPickaxe(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1300).attributes(PickaxeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.8f)));
    }

    public String getMaterialName() {
        return "Emerald";
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        ensureSilkTouch(stack, level);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        ensureSilkTouch(stack, level);
    }

    private void ensureSilkTouch(ItemStack stack, Level level) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.SILK_TOUCH, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SILK_TOUCH), 1);
        }
    }
}
