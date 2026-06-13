package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

public class RubyHoe extends HoeItem {
    public RubyHoe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1));
    }

    public RubyHoe(IItemTier tier, Item.Properties properties) {
        super(tier, -2, 0.0F, properties);
    }

    public String getMaterialName() {
        return "Ruby";
    }
}