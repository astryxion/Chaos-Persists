package com.astryxion.chaospersists.item;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class CrystalHoe extends HoeItem {

    public CrystalHoe(Tier tier, float attackDamage) {
        super(tier, new Properties().stacksTo(1).attributes(HoeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, attackDamage), -0.0f)));
    }
}
