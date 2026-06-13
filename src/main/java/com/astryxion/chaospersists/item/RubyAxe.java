package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

public class RubyAxe extends AxeItem {
    private final int weaponDamage = 12;

    public RubyAxe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1500));
    }

    public RubyAxe(IItemTier tier, Item.Properties properties) {
        super(tier, 8.0F + tier.getAttackDamageBonus(), -3.0F, properties);
    }

    public String getMaterialName() {
        return "Ruby";
    }
}