package com.astryxion.chaospersists.item;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class AmethystShovel extends ShovelItem {
    private static final float WEAPON_DAMAGE = 12.0f;

    public AmethystShovel(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(2000).attributes(ShovelItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -3.0f)));
    }

    public String getMaterialName() {
        return "Amethyst";
    }
}
