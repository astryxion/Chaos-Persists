/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ItemMinersDream
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockLiquid
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockSand
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  com.astryxion.chaospersists.legacy.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  com.astryxion.chaospersists.legacy.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 *  com.astryxion.chaospersists.legacy.minecraft.world.WorldProvider
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ItemMinersDream extends Item {

    public ItemMinersDream(int i) {
        super(new Properties().stacksTo(16));
    }

    private static Block modBlock(Object block) {
        return (Block) block;
    }

    /** Stone/dirt/gravel-like blocks cleared to expose ore; ores and special blocks are left intact. */
    private static boolean isMinersDreamRemovableBlock(BlockState state) {
        if (state.is(BlockTags.STONE_ORE_REPLACEABLES)
                || state.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                || state.is(BlockTags.DIRT)
                || state.is(BlockTags.BASE_STONE_OVERWORLD)) {
            return true;
        }
        Block block = state.getBlock();
        return block == Blocks.DIRT
                || block == Blocks.GRAVEL
                || block == Blocks.WATER
                || block == Blocks.LAVA
                || block == Blocks.NETHERRACK
                || block == Blocks.END_STONE
                || block == Blocks.STONE
                || block == Blocks.GRANITE
                || block == Blocks.DIORITE
                || block == Blocks.ANDESITE
                || block == Blocks.TUFF
                || block == Blocks.DEEPSLATE
                || block == Blocks.COBBLED_DEEPSLATE
                || block == Blocks.CALCITE
                || block == Blocks.GRASS_BLOCK
                || block == Blocks.COARSE_DIRT
                || block == Blocks.PODZOL
                || block == Blocks.ROOTED_DIRT
                || block == Blocks.MUD
                || block == modBlock(ChaosPersists.CrystalStone);
    }

    private static boolean isMinersDreamTorchFloorBlock(BlockState state) {
        if (state.is(BlockTags.STONE_ORE_REPLACEABLES)
                || state.is(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
                || state.is(BlockTags.BASE_STONE_OVERWORLD)) {
            return true;
        }
        Block block = state.getBlock();
        return block == Blocks.STONE
                || block == Blocks.DIRT
                || block == Blocks.GRAVEL
                || block == Blocks.NETHERRACK
                || block == Blocks.END_STONE
                || block == Blocks.BEDROCK
                || block == Blocks.GRANITE
                || block == Blocks.DIORITE
                || block == Blocks.ANDESITE
                || block == Blocks.TUFF
                || block == Blocks.DEEPSLATE
                || block == Blocks.COBBLED_DEEPSLATE
                || block == Blocks.CALCITE
                || block == Blocks.GRASS_BLOCK
                || block == Blocks.COARSE_DIRT;
    }

    private static void clearIfRemovable(Level world, BlockPos pos) {
        if (isMinersDreamRemovableBlock(world.getBlockState(pos))) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        ItemStack par1ItemStack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
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
        int pposx = (int) (player.getX() + 0.99 * (double) dirx);
        int pposy = (int) player.getY();
        int pposz = (int) (player.getZ() + 0.99 * (double) dirz);
        if (cposx - pposx == 0 || cposz - pposz == 0) {
            Block bid;
            int k;
            int x = cposx;
            int y = Math.min(pposy, cposy);
            int z = cposz;
            if (cposy > pposy) {
                height += cposy - pposy;
            }
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
                return InteractionResult.FAIL;
            }
            if (deltax != 0 && deltaz != 0) {
                return InteractionResult.FAIL;
            }
            world.playSound(
                    player,
                    player.blockPosition(),
                    SoundEvents.GENERIC_EXPLODE,
                    SoundSource.PLAYERS,
                    1.0f,
                    1.5f);
            if (world.isClientSide()) {
                return InteractionResult.SUCCESS;
            }
            for (int i = 0; i < height; ++i) {
                for (k = 0; k < length; ++k) {
                    int j;
                    solid_count = 0;
                    for (j = -width; j <= width; ++j) {
                        BlockPos minePos = new BlockPos(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax);
                        clearIfRemovable(world, minePos);
                        if (i != height - 1) {
                            continue;
                        }
                        BlockPos ceilingPos = new BlockPos(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax);
                        BlockState ceilingState = world.getBlockState(ceilingPos);
                        if (isMinersDreamRemovableBlock(ceilingState)) {
                            world.setBlock(ceilingPos, Blocks.AIR.defaultBlockState(), 3);
                            continue;
                        }
                        bid = ceilingState.getBlock();
                        if (bid != Blocks.AIR) {
                            ++solid_count;
                        }
                        if (bid != Blocks.AIR
                                && bid != Blocks.GRAVEL
                                && bid != Blocks.SAND
                                && bid != Blocks.WATER
                                && bid != Blocks.LAVA) {
                            continue;
                        }
                        if (world.dimension().equals(ChaosPersists.getDimensionKey(5))) {
                            world.setBlock(
                                    ceilingPos,
                                    modBlock(ChaosPersists.CrystalStone).defaultBlockState(),
                                    2);
                            continue;
                        }
                        world.setBlock(ceilingPos, Blocks.COBBLESTONE.defaultBlockState(), 2);
                    }
                    if (i != height - 1 || solid_count != 0) {
                        continue;
                    }
                    for (j = -width; j <= width; ++j) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y + i + 1, z + k * deltaz + j * deltax),
                                Blocks.AIR.defaultBlockState(),
                                2);
                    }
                }
            }
            for (k = 0; k < length; ++k) {
                for (int j = -width; j <= width; ++j) {
                    clearIfRemovable(
                            world,
                            new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax));
                }
            }
            for (k = 0; k < length; k += torches) {
                BlockPos floorPos = new BlockPos(x + k * deltax, y - 1, z + k * deltaz);
                BlockPos torchPos = new BlockPos(x + k * deltax, y, z + k * deltaz);
                BlockState floorState = world.getBlockState(floorPos);
                if (isMinersDreamTorchFloorBlock(floorState) && world.getBlockState(torchPos).isAir()) {
                    world.setBlock(
                            torchPos,
                            modBlock(ChaosPersists.ExtremeTorch).defaultBlockState(),
                            2);
                }
                if (floorState.getBlock() != modBlock(ChaosPersists.CrystalStone) || !world.getBlockState(torchPos).isAir()) {
                    continue;
                }
                world.setBlock(
                        torchPos,
                        modBlock(ChaosPersists.CrystalTorch).defaultBlockState(),
                        2);
            }
            if (!player.getAbilities().instabuild) {
                par1ItemStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
