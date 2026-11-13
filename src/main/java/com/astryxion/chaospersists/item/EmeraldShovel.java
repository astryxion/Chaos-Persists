package com.astryxion.chaospersists.item;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class EmeraldShovel extends ShovelItem {
    private static final float WEAPON_DAMAGE = 7.0f;

    public EmeraldShovel(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1300).attributes(ShovelItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -3.0f)));
    }

    public String getMaterialName() {
        return "Emerald";
    }
}
