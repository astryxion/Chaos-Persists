package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ArmorStats;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ItemChaosArmor extends ArmorItem {
    private static final Type[] SLOT_TYPES = {Type.HELMET, Type.CHESTPLATE, Type.LEGGINGS, Type.BOOTS};

    private int armor_material = 0;
    private int armor_type = 0;

    public ItemChaosArmor(ArmorMaterial material, int unused, int slotIndex) {
        super(material, SLOT_TYPES[slotIndex], new Properties().stacksTo(1));
        this.armor_material = 0;
        if (material == ChaosPersists.armorLAVAEEL) {
            this.armor_material = 1;
        }
        if (material == ChaosPersists.armorMOTHSCALE) {
            this.armor_material = 2;
        }
        if (material == ChaosPersists.armorEMERALD) {
            this.armor_material = 3;
        }
        if (material == ChaosPersists.armorEXPERIENCE) {
            this.armor_material = 4;
        }
        if (material == ChaosPersists.armorRUBY) {
            this.armor_material = 5;
        }
        if (material == ChaosPersists.armorAMETHYST) {
            this.armor_material = 6;
        }
        if (material == ChaosPersists.armorPINK) {
            this.armor_material = 7;
        }
        if (material == ChaosPersists.armorTIGERSEYE) {
            this.armor_material = 8;
        }
        if (material == ChaosPersists.armorPEACOCK) {
            this.armor_material = 9;
        }
        if (material == ChaosPersists.armorMOBZILLA) {
            this.armor_material = 10;
        }
        if (material == ChaosPersists.armorROYAL) {
            this.armor_material = 11;
        }
        if (material == ChaosPersists.armorLAPIS) {
            this.armor_material = 12;
        }
        if (material == ChaosPersists.armorQUEEN) {
            this.armor_material = 13;
        }
        this.armor_type = slotIndex;
    }

    public int get_armor_material() {
        return this.armor_material;
    }

    public int get_armor_type() {
        return this.armor_type;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        applyArmorEnchantments(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        ArmorStats stats = getArmorStats();
        if (stats == null) {
            return;
        }
        int enchanted = stats.e_aquaaffinity + stats.e_blastprotection + stats.e_featherfalling + stats.e_fireprotection;
        if ((enchanted += stats.e_projectileprotection + stats.e_protection + stats.e_respiration + stats.e_unbreaking) > 0) {
            int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, stack);
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_PROTECTION, stack);
            }
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLAST_PROTECTION, stack);
            }
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PROJECTILE_PROTECTION, stack);
            }
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.RESPIRATION, stack);
            }
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.AQUA_AFFINITY, stack);
            }
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.UNBREAKING, stack);
            }
            if (lvl <= 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FALL_PROTECTION, stack);
            }
            if (lvl == 0) {
                applyArmorEnchantments(stack);
            }
        }
    }

    private void applyArmorEnchantments(ItemStack stack) {
        ArmorStats a = getArmorStats();
        if (a == null) {
            return;
        }
        if (a.e_protection != 0) {
            stack.enchant(Enchantments.ALL_DAMAGE_PROTECTION, a.e_protection);
        }
        if (a.e_fireprotection != 0) {
            stack.enchant(Enchantments.FIRE_PROTECTION, a.e_fireprotection);
        }
        if (a.e_blastprotection != 0) {
            stack.enchant(Enchantments.BLAST_PROTECTION, a.e_blastprotection);
        }
        if (a.e_projectileprotection != 0) {
            stack.enchant(Enchantments.PROJECTILE_PROTECTION, a.e_projectileprotection);
        }
        if (a.e_unbreaking != 0) {
            stack.enchant(Enchantments.UNBREAKING, a.e_unbreaking);
        }
        if (this.armor_type == 3 && a.e_featherfalling != 0) {
            stack.enchant(Enchantments.FALL_PROTECTION, a.e_featherfalling);
        }
        if (this.armor_type == 0) {
            if (a.e_respiration != 0) {
                stack.enchant(Enchantments.RESPIRATION, a.e_respiration);
            }
            if (a.e_aquaaffinity != 0) {
                stack.enchant(Enchantments.AQUA_AFFINITY, a.e_aquaaffinity);
            }
        }
    }

    ArmorStats liveArmorStats() {
        return getArmorStats();
    }

    private ArmorStats getArmorStats() {
        return switch (this.armor_material) {
            case 1 -> ChaosPersists.LavaEel_armorstats;
            case 2 -> ChaosPersists.MothScale_armorstats;
            case 3 -> ChaosPersists.Emerald_armorstats;
            case 4 -> ChaosPersists.Experience_armorstats;
            case 5 -> ChaosPersists.Ruby_armorstats;
            case 6 -> ChaosPersists.Amethyst_armorstats;
            case 7 -> ChaosPersists.Pink_armorstats;
            case 8 -> ChaosPersists.TigersEye_armorstats;
            case 9 -> ChaosPersists.Peacock_armorstats;
            case 10 -> ChaosPersists.Mobzilla_armorstats;
            case 11 -> ChaosPersists.Royal_armorstats;
            case 12 -> ChaosPersists.Lapis_armorstats;
            case 13 -> ChaosPersists.Queen_armorstats;
            default -> ChaosPersists.Ultimate_armorstats;
        };
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        String layer = slot == EquipmentSlot.LEGS ? "2" : "1";
        return "chaospersists:textures/armor/" + getArmorTextureBaseName() + "_" + layer + ".png";
    }

    private String getArmorTextureBaseName() {
        return switch (this.armor_material) {
            case 1 -> "lavaeel";
            case 2 -> "mothscale";
            case 3 -> "emerald";
            case 4 -> "experience";
            case 5 -> "ruby";
            case 6 -> "amethyst";
            case 7 -> "pink";
            case 8 -> "tigerseye";
            case 9 -> "peacock";
            case 10 -> "mobzilla";
            case 11 -> "royal";
            case 12 -> "lapis";
            case 13 -> "queen";
            default -> "ultimate";
        };
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        ItemStack boots;
        if ((this.armor_material == 11 || this.armor_material == 9)
                && player != null
                && (boots = player.getItemBySlot(EquipmentSlot.FEET)) != null
                && !boots.isEmpty()
                && (boots.getItem() == ChaosPersists.RoyalBoots && ChaosPersists.RoyalGlideEnable != 0
                        || boots.getItem() == ChaosPersists.PeacockFeatherBoots)) {
            if (player.getDeltaMovement().y < -0.10000000149011612) {
                player.setDeltaMovement(player.getDeltaMovement().x, -0.10000000149011612, player.getDeltaMovement().z);
            }
            player.fallDistance = 0.0f;
        }
        if (this.armor_material == 13
                && player != null
                && (boots = player.getItemBySlot(EquipmentSlot.FEET)) != null
                && !boots.isEmpty()
                && boots.getItem() == ChaosPersists.QueenBoots
                && ChaosPersists.RoyalGlideEnable != 0) {
            if (player.getDeltaMovement().y < -0.25) {
                player.setDeltaMovement(player.getDeltaMovement().x, -0.25, player.getDeltaMovement().z);
            }
            player.fallDistance = 0.0f;
        }
    }
}
