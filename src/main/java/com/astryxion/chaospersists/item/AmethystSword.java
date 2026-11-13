package com.astryxion.chaospersists.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class AmethystSword extends SwordItem {
    private static final int WEAPON_DAMAGE = 15;

    public AmethystSword(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(2000).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.4f)));
    }

    public String getMaterialName() {
        return "Amethyst";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 3500;
    }
}
