package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

public class CrystalAxe extends AxeItem {
    private final int weaponDamage = 10;

    public CrystalAxe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1000));
    }

    public CrystalAxe(IItemTier tier, Item.Properties properties) {
        super(tier, 8.0F + tier.getAttackDamageBonus(), -3.0F, properties);
    }

    public String getMaterialName() {
        return "Crystal";
    }
}