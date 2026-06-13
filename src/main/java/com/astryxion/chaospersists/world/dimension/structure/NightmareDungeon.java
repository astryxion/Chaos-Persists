/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.NightmareDungeon
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.block.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.ChestTileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.world.World;

public class NightmareDungeon {
    private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 3, 10, 20), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBody, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceLegs, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceHelmet, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBoots, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyExperienceSword, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBody, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.UltimateLegs, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.UltimateHelmet, 0, 1, 1, 25), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBoots, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateShovel, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateHoe, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateAxe, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyBertha, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MySlice, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 6, 12, 20), new WeightedRandomChestContent(ChaosPersists.MyButterCandy, 0, 6, 12, 20), new WeightedRandomChestContent(ChaosPersists.MyAmethystPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyThunderStaff, 0, 1, 1, 5)};

    private void setThisBlock(World world, int cposx, int cposy, int cposz) {
        if (world.random.nextInt(2) == 1) {
            this.FastSetBlock(world, cposx, cposy, cposz, Blocks.BEDROCK);
        } else {
            this.FastSetBlock(world, cposx, cposy, cposz, Blocks.OBSIDIAN);
        }
    }

    public void makeDungeon(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        int j;
        int width = 25;
        int height = 12;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, ChaosPersists.MyRTPBlock);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height - 1;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                k = 0;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
                k = width - 1;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 0; j < height; ++j) {
                i = 0;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
                i = width - 1;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
            }
        }
        BlockPos spawnerPos = new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + width / 2);
        world.setBlock(spawnerPos, Blocks.SPAWNER.defaultBlockState(), 2);
        MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity)world.getBlockEntity(spawnerPos);
        if (tileentitymobspawner != null) {
            if (world.random.nextInt(2) == 1) {
                com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
            } else {
                com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
        }
        ChestTileEntity chest = null;
        BlockPos chestPos1 = new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + 1, cposz + width / 2 + 1);
        world.setBlock(chestPos1, Blocks.CHEST.defaultBlockState(), 2);
        chest = (ChestTileEntity)world.getBlockEntity(chestPos1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(4 + world.random.nextInt(7)));
        }
        BlockPos chestPos2 = new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + 1, cposz + width / 2 - 1);
        world.setBlock(chestPos2, Blocks.CHEST.defaultBlockState(), 2);
        chest = (ChestTileEntity)world.getBlockEntity(chestPos2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(4 + world.random.nextInt(7)));
        }
    }

    public void FastSetBlock(World world, int ix, int iy, int iz, Block id) {
        ChaosPersists.setBlockFast((World)world, (int)ix, (int)iy, (int)iz, (Block)id, (int)0, (int)3);
    }
}

