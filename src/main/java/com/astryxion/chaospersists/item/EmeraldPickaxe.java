package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;

public class EmeraldPickaxe extends PickaxeItem {
    private final int weaponDamage = 10;

    public EmeraldPickaxe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1300));
    }

    public EmeraldPickaxe(IItemTier tier, Item.Properties properties) {
        super(tier, (int)(10.0F - tier.getAttackDamageBonus()), -2.8F, properties);
    }

    public String getMaterialName() {
        return "Emerald";
    }
}