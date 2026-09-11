/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.InstantShelter
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockChest
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  com.astryxion.chaospersists.legacy.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  com.astryxion.chaospersists.legacy.minecraft.init.Blocks
 *  com.astryxion.chaospersists.legacy.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemEmptyMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.IIcon
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class InstantShelter extends Item {

    public InstantShelter(int i) {
        super(new Properties().stacksTo(16));
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
        int pposx = (int) (player.getX() + 0.99 * (double) dirx);
        int pposy = (int) player.getY();
        int pposz = (int) (player.getZ() + 0.99 * (double) dirz);
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
                return InteractionResult.FAIL;
            }
            if (deltax != 0 && deltaz != 0) {
                return InteractionResult.FAIL;
            }
            x = pposx;
            z = pposz;
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
            Direction facing = Direction.from2DDataValue(stuffdir % 4);
            for (i = -width; i <= width; ++i) {
                for (j = -length; j <= length; ++j) {
                    for (k = 0; k <= height + 1; ++k) {
                        BlockPos placePos = new BlockPos(x + i, y + k, z + j);
                        if (k == height + 1) {
                            world.setBlock(placePos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
                            continue;
                        }
                        if (k == 0) {
                            world.setBlock(placePos, Blocks.COBBLESTONE.defaultBlockState(), 3);
                            continue;
                        }
                        if (i == width || j == length || i == -width || j == -length) {
                            if (k == height) {
                                world.setBlock(placePos, Blocks.GLASS.defaultBlockState(), 3);
                                continue;
                            }
                            if ((k == 1 || k == 2) && i == deltax * width && j == deltaz * length) {
                                world.setBlock(placePos, Blocks.AIR.defaultBlockState(), 3);
                                continue;
                            }
                            world.setBlock(placePos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
                            continue;
                        }
                        world.setBlock(placePos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
            i = 2;
            k = 1;
            j = length - 1;
            BlockState furnaceState =
                    Blocks.FURNACE.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
            world.setBlock(
                    new BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax),
                    furnaceState,
                    3);
            i = 1;
            world.setBlock(
                    new BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax),
                    Blocks.CRAFTING_TABLE.defaultBlockState(),
                    3);
            i = 0;
            BlockPos chestPos = new BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
            BlockState chestState =
                    Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, facing);
            world.setBlock(chestPos, chestState, 3);
            if (world.getBlockEntity(chestPos) instanceof ChestBlockEntity chest) {
                chest.setItem(0, new ItemStack(Items.COMPASS));
                chest.setItem(1, new ItemStack(Items.MAP));
                chest.setItem(2, new ItemStack(Items.PORKCHOP, 8));
                chest.setItem(3, new ItemStack(Items.TORCH, 32));
                chest.setItem(4, new ItemStack(Items.COAL, 16));
                chest.setItem(5, new ItemStack(Items.RED_BED));
                chest.setItem(6, new ItemStack(Items.RED_BED));
                chest.setItem(7, new ItemStack(Items.OAK_DOOR));
                chest.setItem(8, new ItemStack(Items.IRON_PICKAXE));
                chest.setItem(9, new ItemStack(Items.IRON_SWORD));
                chest.setItem(10, new ItemStack(Items.IRON_AXE));
                chest.setItem(11, new ItemStack(Items.BUCKET));
                chest.setItem(
                        12,
                        new ItemStack(
                                (net.minecraft.world.level.ItemLike) (Object) ChaosPersists.MyOreSaltBlock,
                                4));
                chest.setItem(13, new ItemStack(Blocks.CHEST.asItem()));
            }
            if (!player.getAbilities().instabuild) {
                par1ItemStack.shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
