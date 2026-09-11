/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.InstantGarden
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockGrass
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
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class InstantGarden extends Item {

    public InstantGarden(int i) {
        super(new Properties().stacksTo(16));
    }

    private static Block modBlock(Object block) {
        return (Block) block;
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
        int height = 10;
        int width = 7;
        int length = 18;
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
            for (i = 0; i < height; ++i) {
                for (k = 0; k < length; ++k) {
                    for (j = -width; j <= width; ++j) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y + i, z + k * deltaz + j * deltax),
                                Blocks.AIR.defaultBlockState(),
                                2);
                        if (i != 0) {
                            continue;
                        }
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y + i - 1, z + k * deltaz + j * deltax),
                                Blocks.GRASS_BLOCK.defaultBlockState(),
                                2);
                    }
                }
            }
            for (k = 1; k < length - 1; ++k) {
                i = 0;
                for (j = -width; j <= width; ++j) {
                    if (i == 1) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                modBlock(ChaosPersists.MyRadishPlant).defaultBlockState(),
                                2);
                    }
                    if (i == 2) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                modBlock(ChaosPersists.MyLettucePlant1).defaultBlockState(),
                                2);
                    }
                    if (i == 3) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                Blocks.CARROTS.defaultBlockState(),
                                2);
                    }
                    if (i == 4) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.WATER.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax),
                                Blocks.COBBLESTONE.defaultBlockState(),
                                2);
                    }
                    if (i == 5) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                Blocks.POTATOES.defaultBlockState(),
                                2);
                    }
                    if (i == 6) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                Blocks.WHEAT.defaultBlockState(),
                                2);
                    }
                    if (i == 7) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                modBlock(ChaosPersists.MyTomatoPlant1).defaultBlockState(),
                                2);
                    }
                    if (i == 8) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.WATER.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax),
                                Blocks.COBBLESTONE.defaultBlockState(),
                                2);
                    }
                    if (i == 9) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                modBlock(ChaosPersists.MyCornPlant1).defaultBlockState(),
                                2);
                    }
                    if (i == 10) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                modBlock(ChaosPersists.MyStrawberryPlant).defaultBlockState(),
                                2);
                    }
                    if (i == 11) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax),
                                Blocks.COBBLESTONE.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.SAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                Blocks.SUGAR_CANE.defaultBlockState(),
                                2);
                    }
                    if (i == 12) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.WATER.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 2, z + k * deltaz + j * deltax),
                                Blocks.COBBLESTONE.defaultBlockState(),
                                2);
                    }
                    if (i == 13) {
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y - 1, z + k * deltaz + j * deltax),
                                Blocks.FARMLAND.defaultBlockState(),
                                2);
                        world.setBlock(
                                new BlockPos(x + k * deltax + j * deltaz, y, z + k * deltaz + j * deltax),
                                Blocks.MELON_STEM.defaultBlockState(),
                                2);
                    }
                    ++i;
                }
            }
            if (!player.getAbilities().instabuild) {
                par1ItemStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
