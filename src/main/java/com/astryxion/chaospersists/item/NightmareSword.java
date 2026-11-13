package com.astryxion.chaospersists.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class NightmareSword extends SwordItem {
    public NightmareSword(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1200).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.TIER_SWORD_MODIFIER, -2.4f)));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        ensureEnchantments(stack, level);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        ensureEnchantments(stack, level);
    }

    private void ensureEnchantments(ItemStack stack, Level level) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.KNOCKBACK, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 1);
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.KNOCKBACK), 3);
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FIRE_ASPECT), 1);
        }
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 5000;
    }
}
