package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;

public class EmeraldShovel extends ShovelItem {
    public EmeraldShovel(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1300));
    }

    public EmeraldShovel(IItemTier tier, Item.Properties properties) {
        super(tier, 1.5F, -3.0F, properties);
    }

    public String getMaterialName() {
        return "Emerald";
    }
}