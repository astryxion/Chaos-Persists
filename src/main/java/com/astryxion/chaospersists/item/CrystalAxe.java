package com.astryxion.chaospersists.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class CrystalAxe extends AxeItem {

    public CrystalAxe(Tier tier, float attackDamage) {
        super(tier, new Properties().stacksTo(1).attributes(AxeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, attackDamage), -3.0f)));
    }
}
