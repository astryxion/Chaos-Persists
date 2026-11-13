package com.astryxion.chaospersists.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class PoisonSword extends SwordItem {
    private static final int WEAPON_DAMAGE = 10;

    public PoisonSword(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(1300).attributes(SwordItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -2.4f)));
    }

    public String getMaterialName() {
        return "Emerald";
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 1);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.SHARPNESS, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SHARPNESS), 1);
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target != null) {
            int var2 = 10 + target.getRandom().nextInt(10);
            target.addEffect(new MobEffectInstance(MobEffects.POISON, var2 * 20, 0));
            var2 = 10 + target.getRandom().nextInt(10);
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, var2 * 20, 0));
            var2 = 10 + target.getRandom().nextInt(10);
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, var2 * 20, 0));
        }
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public int getUseDuration(ItemStack stack, net.minecraft.world.entity.LivingEntity user) {
        return 3000;
    }
}
