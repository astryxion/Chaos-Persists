package com.astryxion.chaospersists.util;

import net.minecraft.util.Mth;

public final class ChaosArmorMath {
    private ChaosArmorMath() {}

    public static float vanillaDamageAfterArmor(float damage, float armor, float toughness) {
        float armorLoss = damage / (2.0F + toughness / 4.0F);
        float effectiveArmor = Mth.clamp(armor - armorLoss, armor * 0.2F, 20.0F);
        return damage * (1.0F - effectiveArmor / 25.0F);
    }
}
