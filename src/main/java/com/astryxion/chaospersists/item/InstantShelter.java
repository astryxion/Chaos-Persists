/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.InstantShelter
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.MapItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.ChestTileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.MapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InstantShelter
extends Item {
    public InstantShelter(int i) {
        super(new Item.Properties());
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Hand hand = context.getHand();
        Direction facing = context.getClickedFace();
        ItemStack par1ItemStack = player.getItemInHand(hand);
        int cposx = pos.getX();
        int cposy = pos.getY();
        int cposz = pos.getZ();
        int deltax = 0;
        int deltaz = 0;
        boolean bid = false;
        int dirx = 0;
        int dirz = 0;
        int stuffdir = 0;
        int length = 3;
        int width = 3;
        int height = 3;
        if (cposx < 0) {
            dirx = -1;
        }
        if (cposz < 0) {
            dirz = -1;
        }
        int pposx = (int)(player.getX() + 0.99 * (double)dirx);
        int pposy = (int)player.getY();
        int pposz = (int)(player.getZ() + 0.99 * (double)dirz);
        if (cposx - pposx == 0 || cposz - pposz == 0) {
            int j;
            int i;
            int k;
            int x = cposx;
            int y = pposy - 1;
            int z = cposz;
            if (x - pposx < 0) {
                deltax = -1;
                stuffdir = 3;
            }
            if (x - pposx > 0) {
                deltax = 1;
                stuffdir = 2;
            }
            if (z - pposz < 0) {
                deltaz = -1;
                stuffdir = 5;
            }
            if (z - pposz > 0) {
                deltaz = 1;
                stuffdir = 4;
            }
            if (deltax == 0 && deltaz == 0) {
                return ActionResultType.FAIL;
            }
            if (deltax != 0 && deltaz != 0) {
                return ActionResultType.FAIL;
            }
            x = pposx;
            z = pposz;
            world.playSound(null, player.getX(), player.getY(), player.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, net.minecraft.util.SoundCategory.PLAYERS, 1.0f, 1.5f);
            if (world.isClientSide) {
                return ActionResultType.SUCCESS;
            }
            for (i = - width; i <= width; ++i) {
                for (j = - length; j <= length; ++j) {
                    for (k = 0; k <= height + 1; ++k) {
                        if (k == height + 1) {
                            world.setBlock(new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.OAK_PLANKS.defaultBlockState(), 3);
                            continue;
                        }
                        if (k == 0) {
                            world.setBlock(new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.COBBLESTONE.defaultBlockState(), 3);
                            continue;
                        }
                        if (i == width || j == length || i == - width || j == - length) {
                            if (k == height) {
                                world.setBlock(new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.GLASS.defaultBlockState(), 3);
                                continue;
                            }
                            if ((k == 1 || k == 2) && i == deltax * width && j == deltaz * length) {
                                world.setBlock(new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.AIR.defaultBlockState(), 3);
                                continue;
                            }
                            world.setBlock(new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.OAK_PLANKS.defaultBlockState(), 3);
                            continue;
                        }
                        world.setBlock(new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
            i = 2;
            k = 1;
            j = length - 1;
            world.setBlock(new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.FURNACE.defaultBlockState().setValue(net.minecraft.block.HorizontalBlock.FACING, net.minecraft.util.Direction.from2DDataValue(stuffdir)), 3);
            i = 1;
            world.setBlock(new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.CRAFTING_TABLE.defaultBlockState(), 3);
            i = 0;
            world.setBlock(new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.CHEST.defaultBlockState().setValue(net.minecraft.block.HorizontalBlock.FACING, net.minecraft.util.Direction.from2DDataValue(stuffdir)), 3);
            ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax));
            if (chest != null) {
                chest.setItem(0, new ItemStack(Items.COMPASS));
                chest.setItem(1, new ItemStack((Item)Items.MAP));
                chest.setItem(2, new ItemStack(Items.PORKCHOP, 8));
                chest.setItem(3, new ItemStack(Blocks.TORCH, 32));
                chest.setItem(4, new ItemStack(Items.COAL, 16));
                chest.setItem(5, new ItemStack(Items.RED_BED));
                chest.setItem(6, new ItemStack(Items.RED_BED));
                chest.setItem(7, new ItemStack(Items.OAK_DOOR));
                chest.setItem(8, new ItemStack(Items.IRON_PICKAXE));
                chest.setItem(9, new ItemStack(Items.IRON_SWORD));
                chest.setItem(10, new ItemStack(Items.IRON_AXE));
                chest.setItem(11, new ItemStack(Items.BUCKET));
                chest.setItem(12, new ItemStack(ChaosPersists.MyOreSaltBlock, 4));
                chest.setItem(13, new ItemStack((Block)Blocks.CHEST));
            }
            if (!player.isCreative()) {
                par1ItemStack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }}

