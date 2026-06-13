/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ArmorStats
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ArmorItem$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.util.ArmorStats;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChaosArmor
extends ArmorItem {
    private int armor_material = 0;
    private int armor_type = 0;
    private int original_d = 0;

    public ItemChaosArmor(IArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET}[par4], new Item.Properties().tab(net.minecraft.item.ItemGroup.TAB_COMBAT));
        this.armor_material = 0;
        if (par2EnumArmorMaterial == ChaosPersists.armorLAVAEEL) {
            this.armor_material = 1;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorMOTHSCALE) {
            this.armor_material = 2;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorEMERALD) {
            this.armor_material = 3;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorEXPERIENCE) {
            this.armor_material = 4;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorRUBY) {
            this.armor_material = 5;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorAMETHYST) {
            this.armor_material = 6;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorPINK) {
            this.armor_material = 7;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorTIGERSEYE) {
            this.armor_material = 8;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorPEACOCK) {
            this.armor_material = 9;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorMOBZILLA) {
            this.armor_material = 10;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorROYAL) {
            this.armor_material = 11;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorLAPIS) {
            this.armor_material = 12;
        }
        if (par2EnumArmorMaterial == ChaosPersists.armorQUEEN) {
            this.armor_material = 13;
        }
        this.armor_type = par4;
        this.original_d = this.getDefense();
    }

    public int get_armor_material() {
        return this.armor_material;
    }

    public int get_armor_type() {
        return this.armor_type;
    }

    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        ArmorStats a = null;
        if (this.armor_material == 0) {
            a = ChaosPersists.Ultimate_armorstats;
        }
        if (this.armor_material == 1) {
            a = ChaosPersists.LavaEel_armorstats;
        }
        if (this.armor_material == 2) {
            a = ChaosPersists.MothScale_armorstats;
        }
        if (this.armor_material == 3) {
            a = ChaosPersists.Emerald_armorstats;
        }
        if (this.armor_material == 4) {
            a = ChaosPersists.Experience_armorstats;
        }
        if (this.armor_material == 5) {
            a = ChaosPersists.Ruby_armorstats;
        }
        if (this.armor_material == 6) {
            a = ChaosPersists.Amethyst_armorstats;
        }
        if (this.armor_material == 7) {
            a = ChaosPersists.Pink_armorstats;
        }
        if (this.armor_material == 8) {
            a = ChaosPersists.TigersEye_armorstats;
        }
        if (this.armor_material == 9) {
            a = ChaosPersists.Peacock_armorstats;
        }
        if (this.armor_material == 10) {
            a = ChaosPersists.Mobzilla_armorstats;
        }
        if (this.armor_material == 11) {
            a = ChaosPersists.Royal_armorstats;
        }
        if (this.armor_material == 12) {
            a = ChaosPersists.Lapis_armorstats;
        }
        if (this.armor_material == 13) {
            a = ChaosPersists.Queen_armorstats;
        }
        if (a != null) {
            if (a.e_protection != 0) {
                par1ItemStack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), a.e_protection);
            }
            if (a.e_fireprotection != 0) {
                par1ItemStack.enchant(Enchantments.FIRE_PROTECTION, a.e_fireprotection);
            }
            if (a.e_blastprotection != 0) {
                par1ItemStack.enchant(Enchantments.BLAST_PROTECTION, a.e_blastprotection);
            }
            if (a.e_projectileprotection != 0) {
                par1ItemStack.enchant(Enchantments.PROJECTILE_PROTECTION, a.e_projectileprotection);
            }
            if (a.e_unbreaking != 0) {
                par1ItemStack.enchant(Enchantments.UNBREAKING, a.e_unbreaking);
            }
            if (this.armor_type == 3 && a.e_featherfalling != 0) {
                par1ItemStack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), a.e_featherfalling);
            }
            if (this.armor_type == 0) {
                if (a.e_respiration != 0) {
                    par1ItemStack.enchant(Enchantments.RESPIRATION, a.e_respiration);
                }
                if (a.e_aquaaffinity != 0) {
                    par1ItemStack.enchant(Enchantments.AQUA_AFFINITY, a.e_aquaaffinity);
                }
            }
        }
    }

    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        ArmorStats a = null;
        int lvl = 0;
        int enchanted = 0;
        if (this.armor_material == 0) {
            a = ChaosPersists.Ultimate_armorstats;
        }
        if (this.armor_material == 1) {
            a = ChaosPersists.LavaEel_armorstats;
        }
        if (this.armor_material == 2) {
            a = ChaosPersists.MothScale_armorstats;
        }
        if (this.armor_material == 3) {
            a = ChaosPersists.Emerald_armorstats;
        }
        if (this.armor_material == 4) {
            a = ChaosPersists.Experience_armorstats;
        }
        if (this.armor_material == 5) {
            a = ChaosPersists.Ruby_armorstats;
        }
        if (this.armor_material == 6) {
            a = ChaosPersists.Amethyst_armorstats;
        }
        if (this.armor_material == 7) {
            a = ChaosPersists.Pink_armorstats;
        }
        if (this.armor_material == 8) {
            a = ChaosPersists.TigersEye_armorstats;
        }
        if (this.armor_material == 9) {
            a = ChaosPersists.Peacock_armorstats;
        }
        if (this.armor_material == 10) {
            a = ChaosPersists.Mobzilla_armorstats;
        }
        if (this.armor_material == 11) {
            a = ChaosPersists.Royal_armorstats;
        }
        if (this.armor_material == 12) {
            a = ChaosPersists.Lapis_armorstats;
        }
        if (this.armor_material == 13) {
            a = ChaosPersists.Queen_armorstats;
        }
        if (a != null) {
            enchanted = a.e_aquaaffinity + a.e_blastprotection + a.e_featherfalling + a.e_fireprotection;
            if ((enchanted += a.e_projectileprotection + a.e_protection + a.e_respiration + a.e_unbreaking) > 0) {
                lvl = EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), stack);
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
                    lvl = EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), stack);
                }
                if (lvl == 0) {
                    if (a.e_protection != 0) {
                        stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(0), a.e_protection);
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
                        stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(2), a.e_featherfalling);
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
            }
        }
    }

    /** 1.12.2: return path for layer 1 (head/chest/boots) or layer 2 (leggings). Must match actual files: assets/chaospersists/textures/armor/<name>_1.png and _2.png */
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        String layer = (slot == EquipmentSlotType.LEGS) ? "2" : "1";
        String base = getArmorTextureBaseName();
        return "chaospersists:textures/armor/" + base + "_" + layer + ".png";
    }

    /** Base texture name per armor material (no path, no layer suffix). */
    private String getArmorTextureBaseName() {
        switch (this.armor_material) {
            case 0:  return "ultimate";
            case 1:  return "lavaeel";
            case 2:  return "mothscale";
            case 3:  return "emerald";
            case 4:  return "experience";
            case 5:  return "ruby";
            case 6:  return "amethyst";
            case 7:  return "pink";
            case 8:  return "tigerseye";
            case 9:  return "peacock";
            case 10: return "mobzilla";
            case 11: return "royal";
            case 12: return "lapis";
            case 13: return "queen";
            default: return "ultimate";
        }
    }

    public void onArmorTick(World world, PlayerEntity player, ItemStack itemStack) {
        ItemStack boots = null;
        java.lang.Object ia = null;
        java.lang.Object it = null;
        if ((this.armor_material == 11 || this.armor_material == 9) && player != null && (boots = player.getItemBySlot(EquipmentSlotType.FEET)) != null && (boots.getItem() == ChaosPersists.RoyalBoots && ChaosPersists.RoyalGlideEnable != 0 || boots.getItem() == ChaosPersists.PeacockFeatherBoots)) {
            if (player.getDeltaMovement().y < -0.10000000149011612) {
                player.setDeltaMovement(player.getDeltaMovement().x, -0.10000000149011612, player.getDeltaMovement().z);
            }
            player.fallDistance = 0.0f;
        }
        if (this.armor_material == 13 && player != null && (boots = player.getItemBySlot(EquipmentSlotType.FEET)) != null && boots.getItem() == ChaosPersists.QueenBoots && ChaosPersists.RoyalGlideEnable != 0) {
            if (player.getDeltaMovement().y < -0.25) {
                player.setDeltaMovement(player.getDeltaMovement().x, -0.25, player.getDeltaMovement().z);
            }
            player.fallDistance = 0.0f;
        }
    }}

