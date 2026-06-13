/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.InstantGarden
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InstantGarden
extends Item {
    public InstantGarden(int i) {
        super(new Item.Properties());
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity PlayerEntity = context.getPlayer();
        if (PlayerEntity == null) {
            return ActionResultType.FAIL;
        }
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Hand hand = context.getHand();
        Direction facing = context.getClickedFace();
        ItemStack par1ItemStack = PlayerEntity.getItemInHand(hand);
        int cposx = pos.getX();
        int cposy = pos.getY();
        int cposz = pos.getZ();
        int deltax = 0;
        int deltaz = 0;
        boolean bid = false;
        int dirx = 0;
        int dirz = 0;
        int height = 10;
        int width = 7;
        int length = 18;
        if (cposx < 0) {
            dirx = -1;
        }
        if (cposz < 0) {
            dirz = -1;
        }
        int pposx = (int)(PlayerEntity.getX() + 0.99 * (double)dirx);
        int pposy = (int)PlayerEntity.getY();
        int pposz = (int)(PlayerEntity.getZ() + 0.99 * (double)dirz);
        if (cposx - pposx == 0 || cposz - pposz == 0) {
            int j;
            int i;
            int k;
            int x = cposx;
            int y = pposy;
            int z = cposz;
            if (x - pposx < 0) {
                deltax = -1;
            }
            if (x - pposx > 0) {
                deltax = 1;
            }
            if (z - pposz < 0) {
                deltaz = -1;
            }
            if (z - pposz > 0) {
                deltaz = 1;
            }
            if (deltax == 0 && deltaz == 0) {
                return ActionResultType.FAIL;
            }
            if (deltax != 0 && deltaz != 0) {
                return ActionResultType.FAIL;
            }
            world.playSound(null, PlayerEntity.getX(), PlayerEntity.getY(), PlayerEntity.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0f, 1.5f);
            if (world.isClientSide) {
                return ActionResultType.SUCCESS;
            }
            for (i = 0; i < height; ++i) {
                for (k = 0; k < length; ++k) {
                    for (j = - width; j <= width; ++j) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax), Blocks.AIR.defaultBlockState(), 2);
                        if (i != 0) continue;
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y + i - 1, z + k * deltaz + j * deltax), Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                    }
                }
            }
            for (k = 1; k < length - 1; ++k) {
                i = 0;
                for (j = - width; j <= width; ++j) {
                    if (i == 1) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), ChaosPersists.MyRadishPlant.defaultBlockState(), 2);
                    }
                    if (i == 2) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), ChaosPersists.MyLettucePlant1.defaultBlockState(), 2);
                    }
                    if (i == 3) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), Blocks.CARROTS.defaultBlockState(), 2);
                    }
                    if (i == 4) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.WATER.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax), Blocks.COBBLESTONE.defaultBlockState(), 2);
                    }
                    if (i == 5) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), Blocks.POTATOES.defaultBlockState(), 2);
                    }
                    if (i == 6) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), Blocks.WHEAT.defaultBlockState(), 2);
                    }
                    if (i == 7) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), ChaosPersists.MyTomatoPlant1.defaultBlockState(), 2);
                    }
                    if (i == 8) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.WATER.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax), Blocks.COBBLESTONE.defaultBlockState(), 2);
                    }
                    if (i == 9) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), ChaosPersists.MyCornPlant1.defaultBlockState(), 2);
                    }
                    if (i == 10) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), ChaosPersists.MyStrawberryPlant.defaultBlockState(), 2);
                    }
                    if (i == 11) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax), Blocks.COBBLESTONE.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.SAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), Blocks.SUGAR_CANE.defaultBlockState(), 2);
                    }
                    if (i == 12) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.WATER.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax), Blocks.COBBLESTONE.defaultBlockState(), 2);
                    }
                    if (i == 13) {
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax), Blocks.FARMLAND.defaultBlockState(), 2);
                        world.setBlock(new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax), Blocks.MELON_STEM.defaultBlockState(), 2);
                    }
                    ++i;
                }
            }
            if (!PlayerEntity.isCreative()) {
                par1ItemStack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }}

