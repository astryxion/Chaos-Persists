package com.astryxion.chaospersists.util;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public final class ChaosEnchantments {
    private ChaosEnchantments() {}

    public static int level(ResourceKey<Enchantment> key, ItemStack stack) {
        var enchantments = EnchantmentHelper.getEnchantmentsForCrafting(stack);
        for (Holder<Enchantment> enchantment : enchantments.keySet()) {
            if (enchantment.is(key)) {
                return enchantments.getLevel(enchantment);
            }
        }
        return 0;
    }

    public static int looting(net.minecraft.world.damagesource.DamageSource source) {
        if (source.getEntity() instanceof net.minecraft.world.entity.LivingEntity attacker) {
            return level(net.minecraft.world.item.enchantment.Enchantments.LOOTING, attacker.getMainHandItem());
        }
        return 0;
    }
}
