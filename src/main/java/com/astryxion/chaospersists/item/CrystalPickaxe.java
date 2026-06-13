package com.astryxion.chaospersists.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.IItemTier;

public class CrystalPickaxe extends PickaxeItem {
    private final int weaponDamage = 10;

    public CrystalPickaxe(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(1000));
    }

    public CrystalPickaxe(IItemTier tier, Item.Properties properties) {
        super(tier, (int)(10 - tier.getAttackDamageBonus()), -2.8F, properties);
    }

    public String getMaterialName() {
        return "Crystal";
    }
}