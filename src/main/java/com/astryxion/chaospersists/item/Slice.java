/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.BerthaHit
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.Slice
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.SwordItem
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.entity.BerthaHit;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.World;

public class Slice
extends SwordItem {
    public Slice(IItemTier par2EnumToolMaterial) {
        super(par2EnumToolMaterial, 3, -2.4F, new Item.Properties().stacksTo(1).durability(2600));
    }

    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        par1ItemStack.enchant(Enchantments.SHARPNESS, 5);
        par1ItemStack.enchant(Enchantments.BANE_OF_ARTHROPODS, 1);
    }

    public void onUseTick(World world, PlayerEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, (ItemStack)stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.SHARPNESS, 5);
            stack.enchant(Enchantments.BANE_OF_ARTHROPODS, 1);
        }
    }

    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        this.onUseTick(par2World, (PlayerEntity)null, stack, 0);
    }

    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity != null && (entity instanceof PlayerEntity || entity instanceof Girlfriend || entity instanceof Boyfriend)) {
            return true;
        }
        return false;
    }

    public boolean onEntitySwing(LivingEntity entityLiving, ItemStack stack) {
        if (entityLiving != null && entityLiving instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)entityLiving;
            double xzoff = 2.0;
            double yoff = 1.55;
            BerthaHit lb = new BerthaHit(p.level, (LivingEntity)p);
            lb.moveTo(p.getX() - xzoff * Math.sin(Math.toRadians(p.yHeadRot)), p.getY() + yoff, p.getZ() + xzoff * Math.cos(Math.toRadians(p.yHeadRot)), p.yHeadRot, p.xRot);
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 2.0, 2.0, 2.0);
            p.level.addFreshEntity((Entity)lb);
            stack.hurtAndBreak(1, p, (e) -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.MAINHAND));
        }
        return false;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    public boolean hitEntity(ItemStack par1ItemStack, MobEntity par2Mob, MobEntity par3Mob) {
        par1ItemStack.hurtAndBreak(1, par3Mob, (e) -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.MAINHAND));
        return true;
    }

    public int getUseDuration(ItemStack par1ItemStack) {
        return 9000;
    }}

