/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ItemMinersDream
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
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
 *  net.minecraft.world.Dimension
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMinersDream
extends Item {
    public ItemMinersDream(int i) { super(new Item.Properties()); }

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
        int dirx = 0;
        int dirz = 0;
        int height = 5;
        int width = 5;
        int length = 64;
        int torches = 5;
        int solid_count = 0;
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
            Block bid;
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
            world.playSound(null, player.getX(), player.getY(), player.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, net.minecraft.util.SoundCategory.PLAYERS, 1.0f, 1.5f);
            if (world.isClientSide) {
                return ActionResultType.SUCCESS;
            }
            for (int i = 0; i < height; ++i) {
                for (k = 0; k < length; ++k) {
                    int j;
                    solid_count = 0;
                    for (j = - width; j <= width; ++j) {
                        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax)).getBlock();
                        if (bid == Blocks.STONE || bid == Blocks.DIRT || bid == Blocks.GRAVEL || bid == Blocks.WATER || bid == Blocks.WATER || bid == Blocks.LAVA || bid == Blocks.LAVA || bid == Blocks.NETHERRACK || bid == Blocks.END_STONE || bid == ChaosPersists.CrystalStone) {
                            world.setBlock(new net.minecraft.util.math.BlockPos(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax), Blocks.AIR.defaultBlockState(), 2);
                        }
                        if (i != height - 1) continue;
                        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax)).getBlock();
                        if (bid != Blocks.AIR) {
                            ++solid_count;
                        }
                        if (bid != Blocks.AIR && bid != Blocks.GRAVEL && bid != Blocks.SAND && bid != Blocks.WATER && bid != Blocks.WATER && bid != Blocks.LAVA && bid != Blocks.LAVA) continue;
                        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(world) == ChaosPersists.getDimension(5)) {
                            world.setBlock(new net.minecraft.util.math.BlockPos(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax), ChaosPersists.CrystalStone.defaultBlockState(), 2);
                            continue;
                        }
                        world.setBlock(new net.minecraft.util.math.BlockPos(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax), Blocks.COBBLESTONE.defaultBlockState(), 2);
                    }
                    if (i != height - 1 || solid_count != 0) continue;
                    for (j = - width; j <= width; ++j) {
                        world.setBlock(new net.minecraft.util.math.BlockPos(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax), Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
            for (k = 0; k < length; k += torches) {
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + k * deltax, y - 1, z + k * deltaz)).getBlock();
                if ((bid == Blocks.STONE || bid == Blocks.DIRT || bid == Blocks.GRAVEL || bid == Blocks.NETHERRACK || bid == Blocks.END_STONE || bid == Blocks.BEDROCK) && world.isEmptyBlock(new net.minecraft.util.math.BlockPos(x + k * deltax, y, z + k * deltaz))) {
                    world.setBlock(new net.minecraft.util.math.BlockPos(x + k * deltax, y, z + k * deltaz), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
                }
                if (bid != ChaosPersists.CrystalStone || !world.isEmptyBlock(new net.minecraft.util.math.BlockPos(x + k * deltax, y, z + k * deltaz))) continue;
                world.setBlock(new net.minecraft.util.math.BlockPos(x + k * deltax, y, z + k * deltaz), ChaosPersists.CrystalTorch.defaultBlockState(), 2);
            }
            if (!player.isCreative()) {
                par1ItemStack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }}

