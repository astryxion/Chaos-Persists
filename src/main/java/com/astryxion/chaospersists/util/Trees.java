/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CrystalFurnace
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Trees
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.BlockEntity.MobSpawnerBaseLogic
 *  net.minecraft.BlockEntity.BlockEntity
 *  net.minecraft.BlockEntity.ChestTileEntity
 *  net.minecraft.BlockEntity.MobSpawnerTileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.world.World;

public class Trees {
    public static final WeightedRandomChestContent[] CrystalChestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.CrystalTermiteBlock.asItem(), 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.CrystalFlowerRedBlock.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalFlowerBlueBlock.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalFlowerGreenBlock.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalFlowerYellowBlock.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalPlanksBlock.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalWorkbenchBlock.asItem(), 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.CrystalFurnaceBlock.asItem(), 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeBlock.asItem(), 0, 1, 10, 5), new WeightedRandomChestContent(ChaosPersists.CrystalStone.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalRat.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalFairy.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalCoal.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalGrass.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalCrystal.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalTorch.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalLeaves.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalLeaves2.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalLeaves3.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalTreeLog.asItem(), 0, 1, 10, 10), new WeightedRandomChestContent(ChaosPersists.TigersEye.asItem(), 0, 1, 10, 5), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodAxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodShovel, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodPickaxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodHoe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkAxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkShovel, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkPickaxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkHoe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeSword, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeAxe, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeShovel, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyePickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeHoe, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneAxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneShovel, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStonePickaxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneHoe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeIngot, 0, 1, 5, 5), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkIngot, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalApple, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MyPeacockFeather, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MyPeacock, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyRawPeacock, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyRice, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyQuinoa, 0, 1, 10, 20), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.RotatorEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.VortexEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.PeacockEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.DungeonBeastEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.FairyEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.RatEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.FlounderEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.WhaleEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.IrukandjiEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.SkateEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.UrchinEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.GhostEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.GhostSkellyEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MySkateBow, 0, 1, 1, 2), new WeightedRandomChestContent(ChaosPersists.MyIrukandjiArrow, 0, 5, 10, 2), new WeightedRandomChestContent(ChaosPersists.MyIrukandji, 0, 2, 8, 5), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 2), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 2), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 4, 10), new WeightedRandomChestContent(Blocks.OAK_LOG.asItem(), 0, 1, 4, 10), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 1, 5, 2)};

    private void WindTreeBranch(World world, int x, int y, int z, int length, int dirx, int dirz) {
        for (int i = 1; i <= length; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx), (int)y, (int)(z + i * dirz), (Block)Blocks.OAK_LOG, (int)0, (int)2);
            if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx, y + 1, z + i * dirz)).getBlock()) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx), (int)(y + 1), (int)(z + i * dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (i < length / 3 && Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx, y + 2, z + i * dirz)).getBlock()) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx), (int)(y + 2), (int)(z + i * dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (i <= length / 3) continue;
            if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx + dirz, y, z + i * dirz + dirx)).getBlock()) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx + dirz), (int)y, (int)(z + i * dirz + dirx), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (Blocks.AIR != world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx - dirz, y, z + i * dirz - dirx)).getBlock()) continue;
            ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx - dirz), (int)y, (int)(z + i * dirz - dirx), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
        }
        if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + (length + 1) * dirx, y, z + (length + 1) * dirz)).getBlock()) {
            ChaosPersists.setBlockFast((World)world, (int)(x + (length + 1) * dirx), (int)y, (int)(z + (length + 1) * dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
        }
        if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + (length + 2) * dirx, y, z + (length + 2) * dirz)).getBlock()) {
            ChaosPersists.setBlockFast((World)world, (int)(x + (length + 2) * dirx), (int)y, (int)(z + (length + 2) * dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
        }
    }

    public void WindTree(World world, int x, int y, int z, int dir) {
        Block bid;
        if (dir < 0 || dir > 3) {
            return;
        }
        int dirx = 1;
        int dirz = 0;
        if (dir == 1) {
            dirx = -1;
            dirz = 0;
        }
        if (dir == 2) {
            dirx = 0;
            dirz = 1;
        }
        if (dir == 3) {
            dirx = 0;
            dirz = -1;
        }
        if ((bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)).getBlock()) != Blocks.GRASS_BLOCK && bid != Blocks.DIRT) {
            return;
        }
        int height = world.random.nextInt(8) + 40;
        int width = world.random.nextInt(4) + 8;
        for (int j = 0; j < height; ++j) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(j + y), (int)z, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            if (j <= height / 5) continue;
            ChaosPersists.setBlockFast((World)world, (int)(x + dirx), (int)(j + y), (int)(z + dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
            if (j <= height / 4 || j % 4 != 0) continue;
            this.WindTreeBranch(world, x, j + y, z, height - j, dirx, dirz);
        }
        ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + height), (int)z, (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
    }

    private void SkyTreeBranch(World world, int x, int y, int z, int length, int dirx, int dirz) {
        for (int i = 1; i < length; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx), (int)y, (int)(z + i * dirz), (Block)ChaosPersists.MySkyTreeLog, (int)0, (int)2);
            if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx, y + 1, z + i * dirz)).getBlock()) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx), (int)(y + 1), (int)(z + i * dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx + dirz, y, z + i * dirz + dirx)).getBlock()) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx + dirz), (int)y, (int)(z + i * dirz + dirx), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (Blocks.AIR != world.getBlockState(new net.minecraft.util.math.BlockPos(x + i * dirx - dirz, y, z + i * dirz - dirx)).getBlock()) continue;
            ChaosPersists.setBlockFast((World)world, (int)(x + i * dirx - dirz), (int)y, (int)(z + i * dirz - dirx), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
        }
        if (Blocks.AIR == world.getBlockState(new net.minecraft.util.math.BlockPos(x + length * dirx, y, z + length * dirz)).getBlock()) {
            ChaosPersists.setBlockFast((World)world, (int)(x + length * dirx), (int)y, (int)(z + length * dirz), (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
        }
    }

    public void SkyTree(World world, int x, int y, int z) {
        Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT) {
            return;
        }
        int height = world.random.nextInt(15) + 190;
        if (height - y < 20) {
            return;
        }
        int width = world.random.nextInt(10) + 25;
        for (int j = y; j <= height; ++j) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)j, (int)z, (Block)ChaosPersists.MySkyTreeLog, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast((World)world, (int)x, (int)(height + 1), (int)z, (Block)Blocks.OAK_LEAVES, (int)0, (int)2);
        this.SkyTreeBranch(world, x, height, z, width, 1, 0);
        this.SkyTreeBranch(world, x, height, z, width, -1, 0);
        this.SkyTreeBranch(world, x, height, z, width, 0, 1);
        this.SkyTreeBranch(world, x, height, z, width, 0, -1);
        height -= 5;
        this.SkyTreeBranch(world, x, height -= world.random.nextInt(4), z, width /= 3, 1, 0);
        this.SkyTreeBranch(world, x, height, z, width, -1, 0);
        this.SkyTreeBranch(world, x, height, z, width, 0, 1);
        this.SkyTreeBranch(world, x, height, z, width, 0, -1);
    }

    public void DuplicatorTree(World world, int x, int y, int z) {
        int j;
        int i;
        int realy = y;
        Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y - 1, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y - 2, z)).getBlock();
            if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y - 3, z)).getBlock();
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                    return;
                }
                realy = y - 3;
            } else {
                realy = y - 2;
            }
            return;
        }
        realy = y - 1;
        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 1, z)).getBlock();
        if (bid != ChaosPersists.MyDT) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 1), (int)z, (Block)ChaosPersists.MyDT, (int)0, (int)2);
            return;
        }
        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 2, z)).getBlock();
        if (bid != ChaosPersists.MyDT) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 2), (int)z, (Block)ChaosPersists.MyDT, (int)0, (int)2);
            return;
        }
        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 3, z)).getBlock();
        if (bid != ChaosPersists.MyDT) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 3), (int)z, (Block)ChaosPersists.MyDT, (int)0, (int)2);
            return;
        }
        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 4, z)).getBlock();
        if (bid != ChaosPersists.MyAppleLeaves) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 4), (int)z, (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
            return;
        }
        for (i = -1; i <= 1; ++i) {
            for (j = -1; j <= 1; ++j) {
                if (j == 0 && i == 0 || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + i, realy + 3, z + j)).getBlock()) == ChaosPersists.MyAppleLeaves) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(realy + 3), (int)(z + j), (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
                return;
            }
        }
        Block bidm = Blocks.AIR;
        for (int tries = 0; tries < 20 && (bidm == Blocks.AIR || bidm == ChaosPersists.MyDT); ++tries) {
            i = world.random.nextInt(5) - 2;
            j = world.random.nextInt(5) - 2;
            bidm = world.getBlockState(new net.minecraft.util.math.BlockPos(x + i, realy + 1, z + j)).getBlock();
            net.minecraft.block.BlockState metaState = world.getBlockState(new net.minecraft.util.math.BlockPos(x + i, realy + 1, z + j));
            if (bidm == Blocks.AIR || bidm == ChaosPersists.MyDT) continue;
            for (int k = 0; k < 20; ++k) {
                i = world.random.nextInt(5) - 2;
                j = world.random.nextInt(5) - 2;
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + i, realy + 1, z + j)).getBlock();
                if (bid != Blocks.AIR) continue;
                world.setBlock(new net.minecraft.util.math.BlockPos(x + i, realy + 1, z + j), metaState, 2);
                return;
            }
        }
    }

    private void make_leaves(World world, int x, int y, int z) {
        for (int l1 = -3; l1 <= 3; ++l1) {
            for (int l2 = -3; l2 <= 3; ++l2) {
                for (int l3 = 0; l3 <= 2; ++l3) {
                    Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + l1, y + l3, z + l2)).getBlock();
                    if (bid != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + l1), (int)(y + l3), (int)(z + l2), (Block)ChaosPersists.MyExperienceLeaves, (int)0, (int)2);
                }
            }
        }
    }

    private void grow_small_branch(World world, int x, int y, int z, int xdir, int zdir, int xxdir, int zzdir) {
        int n;
        int i2 = 0;
        int k2 = 0;
        int j2 = 0;
        int i = x;
        int j = y;
        int k = z;
        int grow = 4 + world.random.nextInt(2);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i, j, k);
            ++j;
            i2 = i += xdir;
            k2 = k += zdir;
        }
        grow = 4 + world.random.nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i, j, k);
            i += xdir;
            k += zdir;
        }
        grow = 4 + world.random.nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i2, (int)j, (int)k2, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i2, j, k2);
            i2 += xxdir;
            k2 += zzdir;
        }
        j2 = --j;
        grow = 3 + world.random.nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i, j, k);
            i += xdir;
            k += zdir;
            --j;
        }
        grow = 3 + world.random.nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i2, (int)j2, (int)k2, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i2, j2, k2);
            i2 += xxdir;
            k2 += zzdir;
            --j2;
        }
    }

    private void grow_branch(World world, int x, int y, int z, int xdir, int zdir, int xxdir, int zzdir) {
        int n;
        int i2 = 0;
        int k2 = 0;
        int j2 = 0;
        int i = x;
        int j = y;
        int k = z;
        int grow = 5 + world.random.nextInt(4);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i, j, k);
            ++j;
            i2 = i += xdir;
            k2 = k += zdir;
        }
        grow = 6 + world.random.nextInt(5);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i, j, k);
            i += xdir;
            k += zdir;
        }
        grow = 6 + world.random.nextInt(5);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i2, (int)j, (int)k2, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i2, j, k2);
            i2 += xxdir;
            k2 += zzdir;
        }
        j2 = --j;
        grow = 4 + world.random.nextInt(4);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i, j, k);
            i += xdir;
            k += zdir;
            --j;
        }
        grow = 4 + world.random.nextInt(4);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i2, (int)j2, (int)k2, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(world, i2, j2, k2);
            i2 += xxdir;
            k2 += zzdir;
            --j2;
        }
    }

    public void ExperienceTree(World world, int x, int y, int z) {
        int i;
        int j;
        int k;
        Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
            return;
        }
        for (j = 1; j < 6; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j), (int)(z + k), (Block)Blocks.OAK_LOG, (int)0, (int)2);
                }
            }
        }
        this.grow_branch(world, x, y + 6, z, 0, 1, 1, 1);
        this.grow_branch(world, x + 1, y + 6, z, 1, 0, 1, -1);
        this.grow_branch(world, x, y + 6, z + 1, -1, 0, -1, 1);
        this.grow_branch(world, x + 1, y + 6, z + 1, 0, -1, -1, -1);
        for (j = 7; j < 19; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j), (int)(z + k), (Block)Blocks.OAK_LOG, (int)0, (int)2);
                }
            }
        }
        this.grow_small_branch(world, x, y + 19, z, 0, 1, -1, 1);
        this.grow_small_branch(world, x + 1, y + 19, z, 1, 0, 1, 1);
        this.grow_small_branch(world, x, y + 19, z + 1, -1, 0, -1, -1);
        this.grow_small_branch(world, x + 1, y + 19, z + 1, 0, -1, 1, -1);
        int grow = 5 + world.random.nextInt(6);
        for (j = 19; j < 19 + grow; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j), (int)(z + k), (Block)Blocks.OAK_LOG, (int)0, (int)2);
                    this.make_leaves(world, x + i, y + j, z + k);
                }
            }
        }
    }

    public void SmallTree(World world, int x, int y, int z) {
        int realy = y;
        Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y - 1, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y - 2, z)).getBlock();
            if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y - 3, z)).getBlock();
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                    ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)Blocks.AIR, (int)0, (int)2);
                    return;
                }
                realy = y - 3;
            } else {
                realy = y - 2;
            }
            return;
        }
        realy = y - 1;
        bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 1, z)).getBlock();
        if (bid == Blocks.AIR) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 1), (int)z, (Block)ChaosPersists.MySkyTreeLog, (int)0, (int)2);
        }
        if (world.random.nextInt(2) == 1) {
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 2, z)).getBlock();
            if (bid == Blocks.AIR) {
                ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 2), (int)z, (Block)ChaosPersists.MySkyTreeLog, (int)0, (int)2);
            }
            if (world.random.nextInt(2) == 1) {
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 3, z)).getBlock();
                if (bid == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 3), (int)z, (Block)ChaosPersists.MySkyTreeLog, (int)0, (int)2);
                }
            } else {
                --realy;
            }
        } else {
            realy -= 2;
        }
        if ((bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, realy + 4, z)).getBlock()) == Blocks.AIR) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(realy + 4), (int)z, (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
        }
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + i, realy + 3, z + j)).getBlock();
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(realy + 3), (int)(z + j), (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
            }
        }
    }

    public void makeScragglyBranch(World world, int x, int y, int z, int len, int biasx, int biasz) {
        for (int k = 0; k < len; ++k) {
            Block bid;
            int iy;
            int ix = world.random.nextInt(2) - world.random.nextInt(2) + biasx;
            int iz = world.random.nextInt(2) - world.random.nextInt(2) + biasz;
            if (ix > 1) {
                ix = 1;
            }
            if (ix < -1) {
                ix = -1;
            }
            if (iz > 1) {
                iz = 1;
            }
            if (iz < -1) {
                iz = -1;
            }
            if ((bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x += ix, y += (iy = world.random.nextInt(3) > 0 ? 1 : 0), z += iz)).getBlock()) != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) {
                return;
            }
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
                }
            }
            if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y + 1, z)).getBlock()) != Blocks.AIR) continue;
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + 1), (int)z, (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
        }
    }

    public void ScragglyTreeWithBranches(World world, int x, int y, int z) {
        int k;
        Block bid;
        int i = 1 + world.random.nextInt(3);
        int j = i + world.random.nextInt(12);
        for (k = 0; k < i; ++k) {
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) {
                return;
            }
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + k), (int)z, (Block)Blocks.OAK_LOG, (int)0, (int)2);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = world.random.nextInt(2) - world.random.nextInt(2);
            int iz = world.random.nextInt(2) - world.random.nextInt(2);
            int iy = world.random.nextInt(4) > 0 ? 1 : 0;
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x += ix, y += iy, z += iz)).getBlock();
            if (bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != ChaosPersists.MyAppleLeaves) break;
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)Blocks.OAK_LOG, (int)0, (int)2);
            if (world.random.nextInt(4) == 1) {
                this.makeScragglyBranch(world, x, y, z, world.random.nextInt(1 + j - k), world.random.nextInt(2) - world.random.nextInt(2), world.random.nextInt(2) - world.random.nextInt(2));
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
                }
            }
            if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y + 1, z)).getBlock()) != Blocks.AIR) continue;
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + 1), (int)z, (Block)ChaosPersists.MyAppleLeaves, (int)0, (int)2);
        }
    }

    public void FairyTree(World world, int x, int y, int z) {
        int i;
        int k;
        int j;
        for (j = 1; j < 6; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j), (int)(z + k), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                }
            }
        }
        this.grow_crystal_branch(world, x, y + 5, z, 0, 1, 1, 1, -1);
        this.grow_crystal_branch(world, x + 1, y + 5, z, 1, 0, 1, -1, -1);
        this.grow_crystal_branch(world, x, y + 5, z + 1, -1, 0, -1, 1, -1);
        this.grow_crystal_branch(world, x + 1, y + 5, z + 1, 0, -1, -1, -1, -1);
        this.grow_crystal_branch(world, x, y + 6, z, 0, 1, -1, 1, -1);
        this.grow_crystal_branch(world, x + 1, y + 6, z, 1, 0, 1, 1, -1);
        this.grow_crystal_branch(world, x, y + 6, z + 1, -1, 0, -1, -1, -1);
        this.grow_crystal_branch(world, x + 1, y + 6, z + 1, 0, -1, 1, -1, -1);
        int grow = 5 + world.random.nextInt(5);
        for (j = 6; j < 6 + grow; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j), (int)(z + k), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                    this.make_crystal_leaves(world, x + i, y + j, z + k);
                }
            }
        }
        world.setBlock(new net.minecraft.util.math.BlockPos(x - 1, y + 1, z), Blocks.SPAWNER.defaultBlockState(), 2);
        MobSpawnerTileEntity MobSpawnerTileEntity = (MobSpawnerTileEntity)world.getBlockEntity(new net.minecraft.util.math.BlockPos(x - 1, y + 1, z));
        if (MobSpawnerTileEntity != null) {
            com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(MobSpawnerTileEntity.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "fairy"));
        }
        world.setBlock(new net.minecraft.util.math.BlockPos(x + 2, y + 1, z), Blocks.CHEST.defaultBlockState(), 2);
        ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new net.minecraft.util.math.BlockPos(x + 2, y + 1, z));
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])CrystalChestContentsList, (IInventory)chest, (int)(1 + world.random.nextInt(5)));
        }
    }

    private void make_crystal_leaves(World world, int x, int y, int z) {
        for (int l1 = -2; l1 <= 2; ++l1) {
            for (int l2 = -2; l2 <= 2; ++l2) {
                for (int l3 = 0; l3 <= 1; ++l3) {
                    Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + l1, y + l3, z + l2)).getBlock();
                    if (bid != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + l1), (int)(y + l3), (int)(z + l2), (Block)ChaosPersists.MyCrystalLeaves3, (int)0, (int)2);
                }
            }
        }
    }

    private void make_crystal_castle_leaves(World world, int x, int y, int z) {
        for (int l1 = -1; l1 <= 1; ++l1) {
            for (int l2 = -1; l2 <= 1; ++l2) {
                for (int l3 = 0; l3 <= 1; ++l3) {
                    Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(x + l1, y + l3, z + l2)).getBlock();
                    if (bid != Blocks.AIR) continue;
                    if (l3 != 0) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + l1), (int)(y + l3), (int)(z + l2), (Block)ChaosPersists.MyCrystalLeaves3, (int)0, (int)2);
                        continue;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(x + l1), (int)(y + l3), (int)(z + l2), (Block)ChaosPersists.MyCrystalLeaves2, (int)0, (int)2);
                }
            }
        }
    }

    private void grow_crystal_branch(World world, int x, int y, int z, int xdir, int zdir, int xxdir, int zzdir, int ydir) {
        int n;
        int i2 = 0;
        int k2 = 0;
        int j2 = 0;
        int i = x;
        int j = y;
        int k = z;
        int grow = 4 + world.random.nextInt(4);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            this.make_crystal_leaves(world, i, j, k);
            ++j;
            i2 = i += xdir;
            k2 = k += zdir;
        }
        grow = 5 + world.random.nextInt(5);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            this.make_crystal_leaves(world, i, j, k);
            i += xdir;
            k += zdir;
        }
        grow = 5 + world.random.nextInt(5);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i2, (int)j, (int)k2, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            this.make_crystal_leaves(world, i2, j, k2);
            i2 += xxdir;
            k2 += zzdir;
        }
        j2 = --j;
        grow = 4 + world.random.nextInt(4);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)j, (int)k, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            this.make_crystal_leaves(world, i, j, k);
            i += xdir;
            k += zdir;
            j += ydir;
        }
        grow = 4 + world.random.nextInt(4);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast((World)world, (int)i2, (int)j2, (int)k2, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            this.make_crystal_leaves(world, i2, j2, k2);
            i2 += xxdir;
            k2 += zzdir;
            j2 += ydir;
        }
    }

    public void addSomething(World world, int x, int y, int z) {
        int i = world.random.nextInt(3);
        if (i == 1) {
            world.setBlock(new net.minecraft.util.math.BlockPos(x, y + 1, z), Blocks.SPAWNER.defaultBlockState(), 2);
            MobSpawnerTileEntity MobSpawnerTileEntity = (MobSpawnerTileEntity)world.getBlockEntity(new net.minecraft.util.math.BlockPos(x, y + 1, z));
            if (MobSpawnerTileEntity != null) {
                com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(MobSpawnerTileEntity.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "fairy"));
            }
        }
        if (i == 2) {
            world.setBlock(new net.minecraft.util.math.BlockPos(x, y + 1, z), Blocks.CHEST.defaultBlockState(), 2);
            ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new net.minecraft.util.math.BlockPos(x, y + 1, z));
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])CrystalChestContentsList, (IInventory)chest, (int)(1 + world.random.nextInt(5)));
            }
        }
    }

    public void FairyCastleTree(World world, int x, int y, int z) {
        int nc = 6;
        if (ChaosPersists.LessLag == 1) {
            --nc;
        }
        if (ChaosPersists.LessLag == 2) {
            nc -= 2;
        }
        int j = 3 + world.random.nextInt(3);
        int spread = 0;
        for (int iter = 0; iter < nc; ++iter) {
            int i;
            int k;
            int grow = 4 + world.random.nextInt(3);
            int width = 1 + world.random.nextInt(3);
            int randy = world.random.nextInt(3) - 1;
            for (i = - width; i <= width; ++i) {
                for (k = - width; k <= width; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy), (int)(z + k), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                    if (i == - width || i == width || k == - width || k == width) {
                        this.make_crystal_castle_leaves(world, x + i + spread, y + j + randy, z + k);
                    }
                    if (iter != 0 && i == 0 && k == 0) {
                        this.addSomething(world, x + i + spread, y + j + randy, z + k);
                    }
                    if (i == - width && (k == - width || k == width)) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy + 1), (int)(z + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                    if (i != width || k != - width && k != width) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy + 1), (int)(z + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                }
            }
            if (iter != 0) {
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy), (int)(z + k), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i - spread, y + j + randy, z + k);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i - spread, y + j + randy, z + k);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy + 1), (int)(z + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy + 1), (int)(z + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j + randy), (int)(z + k + spread), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i, y + j + randy, z + k + spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i, y + j + randy, z + k + spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j + randy + 1), (int)(z + k + spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j + randy + 1), (int)(z + k + spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j + randy), (int)(z + k - spread), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i, y + j + randy, z + k - spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i, y + j + randy, z + k - spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j + randy + 1), (int)(z + k - spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j + randy + 1), (int)(z + k - spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
            }
            if (iter >= 2) {
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy), (int)(z + k + spread), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i + spread, y + j + randy, z + k + spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i + spread, y + j + randy, z + k + spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy + 1), (int)(z + k + spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy + 1), (int)(z + k + spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy), (int)(z + k - spread), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i - spread, y + j + randy, z + k - spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i - spread, y + j + randy, z + k - spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy + 1), (int)(z + k - spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy + 1), (int)(z + k - spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy), (int)(z + k + spread), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i - spread, y + j + randy, z + k + spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i - spread, y + j + randy, z + k + spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy + 1), (int)(z + k + spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i - spread), (int)(y + j + randy + 1), (int)(z + k + spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
                width = 1 + world.random.nextInt(3 + iter);
                randy = world.random.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy), (int)(z + k - spread), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(world, x + i + spread, y + j + randy, z + k - spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(world, x + i + spread, y + j + randy, z + k - spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy + 1), (int)(z + k - spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast((World)world, (int)(x + i + spread), (int)(y + j + randy + 1), (int)(z + k - spread), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                    }
                }
            }
            j += grow;
            if (iter == 0) {
                spread = 3;
            }
            spread += grow;
        }
    }
}

