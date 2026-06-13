package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;

public class RubyPickaxe extends PickaxeItem {
    private final int weaponDamage = 12;

    public RubyPickaxe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1500));
    }

    public RubyPickaxe(IItemTier tier, Item.Properties properties) {
        super(tier, (int)(12 - tier.getAttackDamageBonus()), -2.8F, properties);
    }

    public String getMaterialName() {
        return "Ruby";
    }
}