package com.astryxion.chaospersists.compat.cataclysm;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Queen Scale, Royal Guardian, Mobzilla Scale, and Ultimate are the high-tier sets
 * that can stand up to Cataclysm's armor-bypass / percent-health hits.
 */
final class CataclysmHighTierGear {

    private static final EquipmentSlot[] ARMOR_SLOTS = {
        EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
    };

    private CataclysmHighTierGear() {}

    static int highTierPieceCount(LivingEntity entity) {
        if (entity == null) {
            return 0;
        }
        int count = 0;
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            if (isHighTierPiece(entity.getItemBySlot(slot))) {
                count++;
            }
        }
        return count;
    }

    /** 0.0–1.0 based on how many high-tier pieces are worn. */
    static float resistStrength(LivingEntity entity) {
        return highTierPieceCount(entity) / 4.0f;
    }

    static boolean immuneToBlazingBrand(LivingEntity entity) {
        return highTierPieceCount(entity) >= 3;
    }

    static boolean isHighTierPiece(ItemStack stack) {
        if (stack == null || stack.isEmpty() || !(stack.getItem() instanceof ItemChaosArmor chaos)) {
            return false;
        }
        int material = chaos.get_armor_material();
        if (material == 10 || material == 11 || material == 13) {
            return true;
        }
        ResourceLocation id = ForgeRegistries.ITEMS.getKey(stack.getItem());
        return id != null
                && ChaosPersists.MODID.equals(id.getNamespace())
                && id.getPath().startsWith("ultimate_");
    }
}
