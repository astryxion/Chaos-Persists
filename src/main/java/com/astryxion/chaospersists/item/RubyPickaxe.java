package com.astryxion.chaospersists.item;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class RubyPickaxe extends PickaxeItem {
    private static final int WEAPON_DAMAGE = 18;

    public RubyPickaxe(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1500).attributes(PickaxeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.8f)));
    }

    public String getMaterialName() {
        return "Ruby";
    }
}
