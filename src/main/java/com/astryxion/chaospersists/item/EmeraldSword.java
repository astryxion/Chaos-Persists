package com.astryxion.chaospersists.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class EmeraldSword extends SwordItem {
    private static final int WEAPON_DAMAGE = 10;

    public EmeraldSword(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1300).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.4f)));
    }

    public String getMaterialName() {
        return "Emerald";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 3000;
    }
}
