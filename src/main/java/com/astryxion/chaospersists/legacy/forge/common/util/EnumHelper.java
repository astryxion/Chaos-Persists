package com.astryxion.chaospersists.legacy.forge.common.util;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

public class EnumHelper {
    public static Item getItemFromBlock(Block block) {
        return block.asItem();
    }

    public static Tier addToolMaterial(String name, int harvestLevel, int maxUses,
            float efficiency, float damage, int enchantability) {
        TagKey<Block> incorrectBlocks = harvestLevel >= 3 ? BlockTags.INCORRECT_FOR_DIAMOND_TOOL
                : harvestLevel >= 2 ? BlockTags.INCORRECT_FOR_IRON_TOOL
                : BlockTags.INCORRECT_FOR_WOODEN_TOOL;
        return new SimpleTier(incorrectBlocks, maxUses, efficiency, damage, enchantability,
                () -> Ingredient.EMPTY);
    }

    public record ArmorSpec(Holder<ArmorMaterial> material, int durability) {
        public int durabilityFor(ArmorItem.Type type) {
            return type.getDurability(durability);
        }
    }

    public static ArmorSpec addArmorMaterial(String name, String modId, int durability,
            int[] protection, int enchantability, Holder<SoundEvent> equipSound, float toughness) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(modId, name.toLowerCase(Locale.ROOT));
        Map<ArmorItem.Type, Integer> defense = Map.of(
                ArmorItem.Type.HELMET, protection[0],
                ArmorItem.Type.CHESTPLATE, protection[1],
                ArmorItem.Type.LEGGINGS, protection[2],
                ArmorItem.Type.BOOTS, protection[3]);
        ArmorMaterial material = new ArmorMaterial(defense, enchantability,
                equipSound == null ? SoundEvents.ARMOR_EQUIP_IRON : equipSound,
                () -> Ingredient.EMPTY, List.of(new ArmorMaterial.Layer(id)), toughness, 0.0F);
        return new ArmorSpec(Holder.direct(material), durability);
    }
}
