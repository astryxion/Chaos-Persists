package com.astryxion.chaospersists.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class CrystalSword extends SwordItem {

    public CrystalSword(Tier tier, float attackDamage) {
        super(tier, new Properties().stacksTo(1).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, attackDamage), -2.4f)));
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 300;
    }
}
