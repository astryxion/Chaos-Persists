package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;

public class CrystalSword extends SwordItem {
    private final IItemTier toolMaterial;

    public CrystalSword(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1));
    }

    public CrystalSword(IItemTier tier, Item.Properties properties) {
        super(tier, 3, -2.4F, properties);
        this.toolMaterial = tier;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 300;
    }
}
