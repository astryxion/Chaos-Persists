package com.astryxion.chaospersists.legacy.forge.common.util;

import java.util.EnumMap;
import java.util.Locale;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;

/**
 * Legacy Forge 1.12 EnumHelper API preserved for Chaos Persists config-driven materials.
 */
public class EnumHelper {

    /** 1.12 {@code EnumHelper.getItemFromBlock} — same behavior via block item. */
    public static Item getItemFromBlock(Block block) {
        return block.asItem();
    }

    public static Tier addToolMaterial(
            String name,
            int harvestLevel,
            int maxUses,
            float efficiency,
            float damage,
            int enchantability) {
        final TagKey<Block> tag = harvestLevel >= 3 ? BlockTags.NEEDS_DIAMOND_TOOL
                : harvestLevel >= 2 ? BlockTags.NEEDS_IRON_TOOL
                : BlockTags.MINEABLE_WITH_PICKAXE;
        return new ForgeTier(harvestLevel, maxUses, efficiency, damage, enchantability, tag, () -> Ingredient.EMPTY);
    }

    public static ArmorMaterial addArmorMaterial(
            String name,
            String modId,
            int durability,
            int[] protection,
            int enchantability,
            SoundEvent equipSound,
            float toughness) {
        final EnumMap<ArmorItem.Type, Integer> prot = new EnumMap<>(ArmorItem.Type.class);
        prot.put(ArmorItem.Type.BOOTS, protection[3]);
        prot.put(ArmorItem.Type.LEGGINGS, protection[2]);
        prot.put(ArmorItem.Type.CHESTPLATE, protection[1]);
        prot.put(ArmorItem.Type.HELMET, protection[0]);
        final SoundEvent sound = equipSound == null ? SoundEvents.ARMOR_EQUIP_IRON : equipSound;
        final String materialName = modId + ":" + name.toLowerCase(Locale.ROOT);
        final EnumMap<ArmorItem.Type, Integer> durabilityForType = new EnumMap<>(ArmorItem.Type.class);
        durabilityForType.put(ArmorItem.Type.BOOTS, 13);
        durabilityForType.put(ArmorItem.Type.LEGGINGS, 15);
        durabilityForType.put(ArmorItem.Type.CHESTPLATE, 16);
        durabilityForType.put(ArmorItem.Type.HELMET, 11);
        return new ArmorMaterial() {
            @Override
            public int getDurabilityForType(ArmorItem.Type type) {
                return durabilityForType.getOrDefault(type, 1) * durability;
            }

            @Override
            public int getDefenseForType(ArmorItem.Type type) {
                return prot.getOrDefault(type, 0);
            }

            @Override
            public int getEnchantmentValue() {
                return enchantability;
            }

            @Override
            public SoundEvent getEquipSound() {
                return sound;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.EMPTY;
            }

            @Override
            public String getName() {
                return materialName;
            }

            @Override
            public float getToughness() {
                return toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0f;
            }
        };
    }
}
