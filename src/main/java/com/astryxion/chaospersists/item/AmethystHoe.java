package com.astryxion.chaospersists.item;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class AmethystHoe extends HoeItem {
    private static final int WEAPON_DAMAGE = 1;

    public AmethystHoe(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(2000).attributes(HoeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -0.0f)));
    }

    public String getMaterialName() {
        return "Amethyst";
    }
}
