package com.astryxion.chaospersists.item;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class CrystalPickaxe extends PickaxeItem {

    public CrystalPickaxe(Tier tier, float attackDamage) {
        super(tier, new Properties().stacksTo(1).attributes(PickaxeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, attackDamage), -2.8f)));
    }
}
