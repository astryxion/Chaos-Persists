/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ItemCornCob
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.BlockItem
 *  net.minecraft.util.IIcon
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.block.Block;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;

public class ItemCornCob
extends BlockItem {
    public ItemCornCob(int par2, float par3, Block par4, Block par5) {
        super(par4, new Item.Properties().food(new Food.Builder().nutrition(par2).saturationMod(par3).build()));
    }}

