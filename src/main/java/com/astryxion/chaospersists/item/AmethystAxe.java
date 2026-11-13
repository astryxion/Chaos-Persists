package com.astryxion.chaospersists.item;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class AmethystAxe extends AxeItem {
    private static final float WEAPON_DAMAGE = 14.0f;

    public AmethystAxe(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(2000).attributes(AxeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -3.0f)));
    }

    public String getMaterialName() {
        return "Amethyst";
    }
}
