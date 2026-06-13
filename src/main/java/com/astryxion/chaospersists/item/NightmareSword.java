package com.astryxion.chaospersists.item;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.World;

public class NightmareSword extends SwordItem {
    public NightmareSword(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1200));
    }

    public NightmareSword(IItemTier tier, Item.Properties properties) {
        super(tier, 3, -2.4F, properties);
    }

    @Override
    public void onCraftedBy(ItemStack stack, World level, PlayerEntity player) {
        this.ensureEnchantments(stack);
    }

    @Override
    public void onUseTick(World world, LivingEntity entity, ItemStack stack, int count) {
        this.ensureEnchantments(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World level, Entity entity, int slot, boolean selected) {
        this.ensureEnchantments(stack);
    }

    private void ensureEnchantments(ItemStack stack) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.SHARPNESS, 1);
            stack.enchant(Enchantments.KNOCKBACK, 3);
            stack.enchant(Enchantments.FIRE_ASPECT, 1);
        }
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (e) -> e.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 5000;
    }
}
