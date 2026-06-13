/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.UltimateHoe
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.HoeItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;

public class UltimateHoe
extends HoeItem {
    public UltimateHoe(IItemTier par2) {
        super(par2, -3, 0.0F, new net.minecraft.item.Item.Properties().stacksTo(1).durability(3000).tab(net.minecraft.item.ItemGroup.TAB_TOOLS));
    }

    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        par1ItemStack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 2);
    }

    public void onUseTick(World world, PlayerEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), (ItemStack)stack);
        if (lvl <= 0) {
            stack.enchant(com.astryxion.chaospersists.core.ChaosPersists.legacyEnchantment(32), 2);
        }
    }

    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        this.onUseTick(par2World, (PlayerEntity)null, stack, 0);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity par2PlayerEntity = context.getPlayer();
        if (par2PlayerEntity == null) {
            return ActionResultType.FAIL;
        }
        World par3World = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction face = context.getClickedFace();
        ItemStack par1ItemStack = context.getItemInHand();
        int par4 = pos.getX();
        int par5 = pos.getY();
        int par6 = pos.getZ();
        if (!par2PlayerEntity.mayUseItemAt(pos, face, par1ItemStack)) {
            return ActionResultType.FAIL;
        }
        Block i1 = par3World.getBlockState(pos).getBlock();
        boolean air = par3World.isEmptyBlock(pos.above());
        if (face != Direction.DOWN && air && (i1 == Blocks.GRASS_BLOCK || i1 == Blocks.DIRT)) {
            Block block = Blocks.FARMLAND;
            BlockState state = block.defaultBlockState();
            net.minecraft.block.SoundType st = block.getSoundType(state, par3World, pos, null);
            par3World.playSound(null, (double)((float)par4 + 0.5f), (double)((float)par5 + 0.5f), (double)((float)par6 + 0.5f), st.getBreakSound(), SoundCategory.BLOCKS, (st.getVolume() + 1.0f) / 2.0f, st.getPitch() * 0.8f);
            if (par3World.isClientSide) {
                return ActionResultType.SUCCESS;
            }
            for (int i = -1; i <= 1; ++i) {
                for (int k = -1; k <= 1; ++k) {
                    for (int j = -1; j <= 1; ++j) {
                        i1 = par3World.getBlockState(new BlockPos(par4 + i, par5 + j, par6 + k)).getBlock();
                        air = par3World.isEmptyBlock(new BlockPos(par4 + i, par5 + j + 1, par6 + k));
                        if (!air || i1 != Blocks.GRASS_BLOCK && i1 != Blocks.DIRT) continue;
                        par3World.setBlock(new BlockPos(par4 + i, par5 + j, par6 + k), block.defaultBlockState(), 7);
                    }
                }
            }
            par1ItemStack.hurtAndBreak(1, par2PlayerEntity, (e) -> e.broadcastBreakEvent(net.minecraft.util.Hand.MAIN_HAND));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }}

