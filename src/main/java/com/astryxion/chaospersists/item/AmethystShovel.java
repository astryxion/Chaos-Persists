package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.IItemTier;

public class AmethystShovel extends ShovelItem {
    public AmethystShovel(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(2000));
    }

    public AmethystShovel(IItemTier tier, Item.Properties properties) {
        super(tier, 1.5F, -3.0F, properties);
    }

    public String getMaterialName() {
        return "Amethyst";
    }
}