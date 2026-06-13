package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

public class EmeraldAxe extends AxeItem {
    private final int weaponDamage = 10;

    public EmeraldAxe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1300));
    }

    public EmeraldAxe(IItemTier tier, Item.Properties properties) {
        super(tier, 8.0F + tier.getAttackDamageBonus(), -3.0F, properties);
    }

    public String getMaterialName() {
        return "Emerald";
    }
}