package com.astryxion.chaospersists.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class MantisClaw extends SwordItem {
    private static final int WEAPON_DAMAGE = 10;

    public MantisClaw(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1000).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.4f)));
    }

    public String getMaterialName() {
        return "AMETHYST";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target != null && attacker != null && !target.level().isClientSide) {
            target.hurt(target.damageSources().generic(), 1.0f);
            attacker.heal(1.0f);
        }
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 3000;
    }
}
