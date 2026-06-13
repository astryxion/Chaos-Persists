/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.UltimatePickaxe
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.PickaxeItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UltimatePickaxe
extends PickaxeItem {
    private int weaponDamage = 15;

    public UltimatePickaxe(IItemTier par2) {
        super(par2, 1, -2.0F, new net.minecraft.item.Item.Properties().stacksTo(1).durability(3000).tab(net.minecraft.item.ItemGroup.TAB_TOOLS));
    }

    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        par1ItemStack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 5);
        par1ItemStack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 5);
    }

    public void onUseTick(World world, PlayerEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), (ItemStack)stack);
        if (lvl <= 0) {
            stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 5);
            stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(35), 5);
        }
    }

    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        this.onUseTick(par2World, (PlayerEntity)null, stack, 0);
    }

    public boolean canHarvestBlock(Block par1Block) {
        return true;
    }

    public int getDamageVsEntity(Entity par1Entity) {
        if (par1Entity instanceof Girlfriend) {
            return 1;
        }
        if (par1Entity instanceof Boyfriend) {
            return 1;
        }
        if (par1Entity instanceof PlayerEntity) {
            return 1;
        }
        return this.weaponDamage;
    }

    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
        if (entity != null && ChaosPersists.ultimate_sword_pvp == 0) {
            TameableEntity t;
            if (entity instanceof PlayerEntity || entity instanceof Girlfriend || entity instanceof Boyfriend) {
                return true;
            }
            if (entity instanceof TameableEntity && (t = (TameableEntity)entity).isTame()) {
                return true;
            }
        }
        return false;
    }

    private ItemStack dropItemAnItem(World world, int x, int y, int z, Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(world, (double)x, (double)y, (double)z, is);
        if (var3 != null) {
            world.addFreshEntity((Entity)var3);
        }
        return is;
    }

    public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World, Block par3, int par4, int par5, int par6, LivingEntity par7LivingEntity) {
        net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos(par4, par5, par6);
        net.minecraft.block.BlockState state = par2World.getBlockState(pos);
        if ((double)state.getDestroySpeed(par2World, pos) != 0.0) {
            par1ItemStack.hurtAndBreak(1, par7LivingEntity, (e) -> e.broadcastBreakEvent(net.minecraft.util.Hand.MAIN_HAND));
        }
        if (!par2World.isClientSide) {
            if (par3 == Blocks.IRON_ORE && par2World.random.nextInt(2) != 0) {
                this.dropItemAnItem(par2World, par4, par5, par6, Items.IRON_INGOT, 1 + par2World.random.nextInt(2));
            }
            if (par3 == Blocks.GOLD_ORE && par2World.random.nextInt(2) != 0) {
                this.dropItemAnItem(par2World, par4, par5, par6, Items.GOLD_INGOT, 1 + par2World.random.nextInt(2));
            }
            if (par3 == Blocks.STONE && par2World.random.nextInt(100) == 2) {
                int i = par2World.random.nextInt(10);
                if (i == 0) {
                    this.dropItemAnItem(par2World, par4, par5, par6, Items.DIAMOND, 1);
                }
                if (i == 1) {
                    this.dropItemAnItem(par2World, par4, par5, par6, Items.EMERALD, 1);
                }
                if (i == 2) {
                    this.dropItemAnItem(par2World, par4, par5, par6, ChaosPersists.MyAmethyst, 1);
                }
                if (i == 3) {
                    this.dropItemAnItem(par2World, par4, par5, par6, ChaosPersists.MyRuby, 1);
                }
                if (i == 4) {
                    this.dropItemAnItem(par2World, par4, par5, par6, ChaosPersists.UraniumNugget, 1);
                }
                if (i == 5) {
                    this.dropItemAnItem(par2World, par4, par5, par6, ChaosPersists.TitaniumNugget, 1);
                }
            }
        }
        return true;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }}

