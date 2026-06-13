package com.astryxion.chaospersists.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.inventory.EquipmentSlotType;

public class FairySword extends SwordItem {
    private final int weaponDamage;
    private final IItemTier toolMaterial;

    public FairySword(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1300));
    }

    public FairySword(IItemTier tier, Item.Properties properties) {
        super(tier, (int)(15 - tier.getAttackDamageBonus()), -2.4F, properties);
        this.toolMaterial = tier;
        this.weaponDamage = 15;
    }

    public String getMaterialName() {
        return "Fairy";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (e) -> e.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 4000;
    }
}