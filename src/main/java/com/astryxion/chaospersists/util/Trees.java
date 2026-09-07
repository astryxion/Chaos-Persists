/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CrystalFurnace
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Trees
 *  com.astryxion.chaospersists.compat.minecraft.block.Block
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockChest
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockGrass
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockLeaves
 *  com.astryxion.chaospersists.compat.minecraft.init.Blocks
 *  com.astryxion.chaospersists.compat.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.WeightedRandomChestContent
 *  com.astryxion.chaospersists.compat.minecraft.world.World
 */
package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import com.astryxion.chaospersists.compat.forge.common.util.EnumHelper;

public class Trees {
    public static final WeightedRandomChestContent[] CrystalChestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalTermiteBlock), 0, 1, 5, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalFlowerRedBlock), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalFlowerBlueBlock), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalFlowerGreenBlock), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalFlowerYellowBlock), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalPlanksBlock), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalWorkbenchBlock), 0, 1, 1, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalFurnaceBlock), 0, 1, 1, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.MyTigersEyeBlock), 0, 1, 10, 5), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalStone), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalRat), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalFairy), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalCoal), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalGrass), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalCrystal), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.CrystalTorch), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.MyCrystalLeaves), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.MyCrystalLeaves2), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.MyCrystalLeaves3), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.MyCrystalTreeLog), 0, 1, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(ChaosPersists.TigersEye), 0, 1, 10, 5), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodAxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodShovel, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodPickaxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalWoodHoe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkAxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkShovel, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkPickaxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkHoe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeSword, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeAxe, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeShovel, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyePickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeHoe, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneAxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneShovel, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStonePickaxe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalStoneHoe, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeIngot, 0, 1, 5, 5), new WeightedRandomChestContent(ChaosPersists.MyCrystalPinkIngot, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MyCrystalApple, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MyPeacockFeather, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MyPeacock, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyRawPeacock, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyRice, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyQuinoa, 0, 1, 10, 20), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 5), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.PeacockFeatherBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.RotatorEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.VortexEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.PeacockEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.DungeonBeastEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.FairyEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.RatEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.FlounderEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.WhaleEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.IrukandjiEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.SkateEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.UrchinEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.GhostEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.GhostSkellyEgg, 0, 1, 5, 10), new WeightedRandomChestContent(ChaosPersists.MySkateBow, 0, 1, 1, 2), new WeightedRandomChestContent(ChaosPersists.MyIrukandjiArrow, 0, 5, 10, 2), new WeightedRandomChestContent(ChaosPersists.MyIrukandji, 0, 2, 8, 5), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 2), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 2), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 4, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock(Blocks.OAK_LOG), 0, 1, 4, 10), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 1, 5, 2)};
    private void WindTreeBranch(net.minecraft.world.level.Level level, int x, int y, int z, int length, int dirx, int dirz) {
        for (int i = 1; i <= length; ++i) {
            ChaosPersists.setBlockFast(level, (int)(x + i * dirx), (int)y, (int)(z + i * dirz), Blocks.OAK_LOG, (int)0, (int)2);
            if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx, y + 1, z + i * dirz)).getBlock()) {
                ChaosPersists.setBlockFast(level, (int)(x + i * dirx), (int)(y + 1), (int)(z + i * dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (i < length / 3 && Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx, y + 2, z + i * dirz)).getBlock()) {
                ChaosPersists.setBlockFast(level, (int)(x + i * dirx), (int)(y + 2), (int)(z + i * dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (i <= length / 3) continue;
            if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx + dirz, y, z + i * dirz + dirx)).getBlock()) {
                ChaosPersists.setBlockFast(level, (int)(x + i * dirx + dirz), (int)y, (int)(z + i * dirz + dirx), Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (Blocks.AIR != level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx - dirz, y, z + i * dirz - dirx)).getBlock()) continue;
            ChaosPersists.setBlockFast(level, (int)(x + i * dirx - dirz), (int)y, (int)(z + i * dirz - dirx), Blocks.OAK_LEAVES, (int)0, (int)2);
        }
        if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + (length + 1) * dirx, y, z + (length + 1) * dirz)).getBlock()) {
            ChaosPersists.setBlockFast(level, (int)(x + (length + 1) * dirx), (int)y, (int)(z + (length + 1) * dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
        }
        if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + (length + 2) * dirx, y, z + (length + 2) * dirz)).getBlock()) {
            ChaosPersists.setBlockFast(level, (int)(x + (length + 2) * dirx), (int)y, (int)(z + (length + 2) * dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
        }
    }

    public void WindTree(net.minecraft.world.level.Level level, int x, int y, int z, int dir) {
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
        if ((bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y, z)).getBlock()) != Blocks.GRASS_BLOCK && bid != Blocks.DIRT) {
            return;
        }
        int height = level.getRandom().nextInt(8) + 40;
        int width = level.getRandom().nextInt(4) + 8;
        for (int j = 0; j < height; ++j) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(j + y), (int)z, Blocks.OAK_LOG, (int)0, (int)2);
            if (j <= height / 5) continue;
            ChaosPersists.setBlockFast(level, (int)(x + dirx), (int)(j + y), (int)(z + dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
            if (j <= height / 4 || j % 4 != 0) continue;
            this.WindTreeBranch(level, x, j + y, z, height - j, dirx, dirz);
        }
        ChaosPersists.setBlockFast(level, (int)x, (int)(y + height), (int)z, Blocks.OAK_LEAVES, (int)0, (int)2);
    }

    private void SkyTreeBranch(net.minecraft.world.level.Level level, int x, int y, int z, int length, int dirx, int dirz) {
        for (int i = 1; i < length; ++i) {
            ChaosPersists.setBlockFast(level, (int)(x + i * dirx), (int)y, (int)(z + i * dirz), ChaosPersists.MySkyTreeLog, (int)0, (int)2);
            if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx, y + 1, z + i * dirz)).getBlock()) {
                ChaosPersists.setBlockFast(level, (int)(x + i * dirx), (int)(y + 1), (int)(z + i * dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx + dirz, y, z + i * dirz + dirx)).getBlock()) {
                ChaosPersists.setBlockFast(level, (int)(x + i * dirx + dirz), (int)y, (int)(z + i * dirz + dirx), Blocks.OAK_LEAVES, (int)0, (int)2);
            }
            if (Blocks.AIR != level.getBlockState(new net.minecraft.core.BlockPos(x + i * dirx - dirz, y, z + i * dirz - dirx)).getBlock()) continue;
            ChaosPersists.setBlockFast(level, (int)(x + i * dirx - dirz), (int)y, (int)(z + i * dirz - dirx), Blocks.OAK_LEAVES, (int)0, (int)2);
        }
        if (Blocks.AIR == level.getBlockState(new net.minecraft.core.BlockPos(x + length * dirx, y, z + length * dirz)).getBlock()) {
            ChaosPersists.setBlockFast(level, (int)(x + length * dirx), (int)y, (int)(z + length * dirz), Blocks.OAK_LEAVES, (int)0, (int)2);
        }
    }

    public void SkyTree(net.minecraft.world.level.Level level, int x, int y, int z) {
        Block bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT) {
            return;
        }
        int height = level.getRandom().nextInt(15) + 190;
        if (height - y < 20) {
            return;
        }
        int width = level.getRandom().nextInt(10) + 25;
        for (int j = y; j <= height; ++j) {
            ChaosPersists.setBlockFast(level, (int)x, (int)j, (int)z, ChaosPersists.MySkyTreeLog, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast(level, (int)x, (int)(height + 1), (int)z, Blocks.OAK_LEAVES, (int)0, (int)2);
        this.SkyTreeBranch(level, x, height, z, width, 1, 0);
        this.SkyTreeBranch(level, x, height, z, width, -1, 0);
        this.SkyTreeBranch(level, x, height, z, width, 0, 1);
        this.SkyTreeBranch(level, x, height, z, width, 0, -1);
        height -= 5;
        this.SkyTreeBranch(level, x, height -= level.getRandom().nextInt(4), z, width /= 3, 1, 0);
        this.SkyTreeBranch(level, x, height, z, width, -1, 0);
        this.SkyTreeBranch(level, x, height, z, width, 0, 1);
        this.SkyTreeBranch(level, x, height, z, width, 0, -1);
    }

    public void DuplicatorTree(net.minecraft.world.level.Level level, int x, int y, int z) {
        int j;
        int i;
        int realy = y;
        Block bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y - 1, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
            bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y - 2, z)).getBlock();
            if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y - 3, z)).getBlock();
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
        bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 1, z)).getBlock();
        if (bid != ChaosPersists.MyDT) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 1), (int)z, ChaosPersists.MyDT, (int)0, (int)2);
            return;
        }
        bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 2, z)).getBlock();
        if (bid != ChaosPersists.MyDT) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 2), (int)z, ChaosPersists.MyDT, (int)0, (int)2);
            return;
        }
        bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 3, z)).getBlock();
        if (bid != ChaosPersists.MyDT) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 3), (int)z, ChaosPersists.MyDT, (int)0, (int)2);
            return;
        }
        bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 4, z)).getBlock();
        if (bid != ChaosPersists.MyAppleLeaves) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 4), (int)z, ChaosPersists.MyAppleLeaves, (int)0, (int)2);
            return;
        }
        for (i = -1; i <= 1; ++i) {
            for (j = -1; j <= 1; ++j) {
                if (j == 0 && i == 0 || (bid = level.getBlockState(new net.minecraft.core.BlockPos(x + i, realy + 3, z + j)).getBlock()) == ChaosPersists.MyAppleLeaves) continue;
                ChaosPersists.setBlockFast(level, (int)(x + i), (int)(realy + 3), (int)(z + j), ChaosPersists.MyAppleLeaves, (int)0, (int)2);
                return;
            }
        }
        Block bidm = Blocks.AIR;
        for (int tries = 0; tries < 20 && (bidm == Blocks.AIR || bidm == ChaosPersists.MyDT); ++tries) {
            i = level.getRandom().nextInt(5) - 2;
            j = level.getRandom().nextInt(5) - 2;
            bidm = level.getBlockState(new net.minecraft.core.BlockPos(x + i, realy + 1, z + j)).getBlock();
            net.minecraft.world.level.block.state.BlockState metaState = level.getBlockState(new net.minecraft.core.BlockPos(x + i, realy + 1, z + j));
            if (bidm == Blocks.AIR || bidm == ChaosPersists.MyDT) continue;
            for (int k = 0; k < 20; ++k) {
                i = level.getRandom().nextInt(5) - 2;
                j = level.getRandom().nextInt(5) - 2;
                bid = level.getBlockState(new net.minecraft.core.BlockPos(x + i, realy + 1, z + j)).getBlock();
                if (bid != Blocks.AIR) continue;
                level.setBlock(new net.minecraft.core.BlockPos(x + i, realy + 1, z + j), metaState, 2);
                return;
            }
        }
    }

    private void make_leaves(net.minecraft.world.level.Level level, int x, int y, int z) {
        for (int l1 = -3; l1 <= 3; ++l1) {
            for (int l2 = -3; l2 <= 3; ++l2) {
                for (int l3 = 0; l3 <= 2; ++l3) {
                    Block bid = level.getBlockState(new net.minecraft.core.BlockPos(x + l1, y + l3, z + l2)).getBlock();
                    if (bid != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast(level, (int)(x + l1), (int)(y + l3), (int)(z + l2), ChaosPersists.MyExperienceLeaves, (int)0, (int)2);
                }
            }
        }
    }

    private void grow_small_branch(net.minecraft.world.level.Level level, int x, int y, int z, int xdir, int zdir, int xxdir, int zzdir) {
        int n;
        int i2 = 0;
        int k2 = 0;
        int j2 = 0;
        int i = x;
        int j = y;
        int k = z;
        int grow = 4 + level.getRandom().nextInt(2);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i, (int)j, (int)k, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i, j, k);
            ++j;
            i2 = i += xdir;
            k2 = k += zdir;
        }
        grow = 4 + level.getRandom().nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i, (int)j, (int)k, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i, j, k);
            i += xdir;
            k += zdir;
        }
        grow = 4 + level.getRandom().nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i2, (int)j, (int)k2, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i2, j, k2);
            i2 += xxdir;
            k2 += zzdir;
        }
        j2 = --j;
        grow = 3 + level.getRandom().nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i, (int)j, (int)k, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i, j, k);
            i += xdir;
            k += zdir;
            --j;
        }
        grow = 3 + level.getRandom().nextInt(3);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i2, (int)j2, (int)k2, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i2, j2, k2);
            i2 += xxdir;
            k2 += zzdir;
            --j2;
        }
    }

    private void grow_branch(net.minecraft.world.level.Level level, int x, int y, int z, int xdir, int zdir, int xxdir, int zzdir) {
        int n;
        int i2 = 0;
        int k2 = 0;
        int j2 = 0;
        int i = x;
        int j = y;
        int k = z;
        int grow = 5 + level.getRandom().nextInt(4);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i, (int)j, (int)k, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i, j, k);
            ++j;
            i2 = i += xdir;
            k2 = k += zdir;
        }
        grow = 6 + level.getRandom().nextInt(5);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i, (int)j, (int)k, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i, j, k);
            i += xdir;
            k += zdir;
        }
        grow = 6 + level.getRandom().nextInt(5);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i2, (int)j, (int)k2, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i2, j, k2);
            i2 += xxdir;
            k2 += zzdir;
        }
        j2 = --j;
        grow = 4 + level.getRandom().nextInt(4);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i, (int)j, (int)k, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i, j, k);
            i += xdir;
            k += zdir;
            --j;
        }
        grow = 4 + level.getRandom().nextInt(4);
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, (int)i2, (int)j2, (int)k2, Blocks.OAK_LOG, (int)0, (int)2);
            this.make_leaves(level, i2, j2, k2);
            i2 += xxdir;
            k2 += zzdir;
            --j2;
        }
    }

    public void ExperienceTree(net.minecraft.world.level.Level level, int x, int y, int z) {
        int i;
        int j;
        int k;
        Block bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
            return;
        }
        for (j = 1; j < 6; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast(level, (int)(x + i), (int)(y + j), (int)(z + k), Blocks.OAK_LOG, (int)0, (int)2);
                }
            }
        }
        this.grow_branch(level, x, y + 6, z, 0, 1, 1, 1);
        this.grow_branch(level, x + 1, y + 6, z, 1, 0, 1, -1);
        this.grow_branch(level, x, y + 6, z + 1, -1, 0, -1, 1);
        this.grow_branch(level, x + 1, y + 6, z + 1, 0, -1, -1, -1);
        for (j = 7; j < 19; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast(level, (int)(x + i), (int)(y + j), (int)(z + k), Blocks.OAK_LOG, (int)0, (int)2);
                }
            }
        }
        this.grow_small_branch(level, x, y + 19, z, 0, 1, -1, 1);
        this.grow_small_branch(level, x + 1, y + 19, z, 1, 0, 1, 1);
        this.grow_small_branch(level, x, y + 19, z + 1, -1, 0, -1, -1);
        this.grow_small_branch(level, x + 1, y + 19, z + 1, 0, -1, 1, -1);
        int grow = 5 + level.getRandom().nextInt(6);
        for (j = 19; j < 19 + grow; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast(level, (int)(x + i), (int)(y + j), (int)(z + k), Blocks.OAK_LOG, (int)0, (int)2);
                    this.make_leaves(level, x + i, y + j, z + k);
                }
            }
        }
    }

    public void SmallTree(net.minecraft.world.level.Level level, int x, int y, int z) {
        int realy = y;
        Block bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y - 1, z)).getBlock();
        if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
            bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y - 2, z)).getBlock();
            if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y - 3, z)).getBlock();
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.FARMLAND) {
                    ChaosPersists.setBlockFast(level, (int)x, (int)y, (int)z, Blocks.AIR, (int)0, (int)2);
                    return;
                }
                realy = y - 3;
            } else {
                realy = y - 2;
            }
            return;
        }
        realy = y - 1;
        bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 1, z)).getBlock();
        if (bid == Blocks.AIR) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 1), (int)z, ChaosPersists.MySkyTreeLog, (int)0, (int)2);
        }
        if (level.getRandom().nextInt(2) == 1) {
            bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 2, z)).getBlock();
            if (bid == Blocks.AIR) {
                ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 2), (int)z, ChaosPersists.MySkyTreeLog, (int)0, (int)2);
            }
            if (level.getRandom().nextInt(2) == 1) {
                bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 3, z)).getBlock();
                if (bid == Blocks.AIR) {
                    ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 3), (int)z, ChaosPersists.MySkyTreeLog, (int)0, (int)2);
                }
            } else {
                --realy;
            }
        } else {
            realy -= 2;
        }
        if ((bid = level.getBlockState(new net.minecraft.core.BlockPos(x, realy + 4, z)).getBlock()) == Blocks.AIR) {
            ChaosPersists.setBlockFast(level, (int)x, (int)(realy + 4), (int)z, ChaosPersists.MyAppleLeaves, (int)0, (int)2);
        }
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                bid = level.getBlockState(new net.minecraft.core.BlockPos(x + i, realy + 3, z + j)).getBlock();
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockFast(level, (int)(x + i), (int)(realy + 3), (int)(z + j), ChaosPersists.MyAppleLeaves, (int)0, (int)2);
            }
        }
    }

    public void makeScragglyBranch(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource random,
            int x,
            int y,
            int z,
            int len,
            int biasx,
            int biasz) {
        Block appleLeaves = ChaosPersists.MyAppleLeaves;
        for (int k = 0; k < len; ++k) {
            Block bid;
            int iy;
            int ix = random.nextInt(2) - random.nextInt(2) + biasx;
            int iz = random.nextInt(2) - random.nextInt(2) + biasz;
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
            net.minecraft.core.BlockPos pos =
                    new net.minecraft.core.BlockPos(x += ix, y += (iy = random.nextInt(3) > 0 ? 1 : 0), z += iz);
            bid = level.getBlockState(pos).getBlock();
            if (bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != appleLeaves) {
                return;
            }
            ChaosPersists.setBlockFast(level, x, y, z, Blocks.OAK_LOG, 0, 2);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (random.nextInt(2) != 1
                            || !level.getBlockState(new net.minecraft.core.BlockPos(x + m, y, z + n)).isAir()) {
                        continue;
                    }
                    ChaosPersists.setBlockFast(level, x + m, y, z + n, (Block) appleLeaves, 0, 2);
                }
            }
            if (random.nextInt(2) != 1
                    || !level.getBlockState(new net.minecraft.core.BlockPos(x, y + 1, z)).isAir()) {
                continue;
            }
            ChaosPersists.setBlockFast(level, x, y + 1, z, (Block) appleLeaves, 0, 2);
        }
    }

    public void ScragglyTreeWithBranches(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource random, int x, int y, int z) {
        int k;
        Block bid;
        Block appleLeaves = ChaosPersists.MyAppleLeaves;
        int i = 1 + random.nextInt(3);
        int j = i + random.nextInt(12);
        for (k = 0; k < i; ++k) {
            bid = level.getBlockState(new net.minecraft.core.BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != appleLeaves) {
                return;
            }
            ChaosPersists.setBlockFast(level, x, y + k, z, Blocks.OAK_LOG, 0, 2);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = random.nextInt(2) - random.nextInt(2);
            int iz = random.nextInt(2) - random.nextInt(2);
            int iy = random.nextInt(4) > 0 ? 1 : 0;
            bid = level.getBlockState(new net.minecraft.core.BlockPos(x += ix, y += iy, z += iz)).getBlock();
            if (bid != Blocks.AIR && bid != Blocks.OAK_LOG && bid != appleLeaves) {
                break;
            }
            ChaosPersists.setBlockFast(level, x, y, z, Blocks.OAK_LOG, 0, 2);
            if (random.nextInt(4) == 1) {
                this.makeScragglyBranch(
                        level,
                        random,
                        x,
                        y,
                        z,
                        random.nextInt(1 + j - k),
                        random.nextInt(2) - random.nextInt(2),
                        random.nextInt(2) - random.nextInt(2));
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (random.nextInt(2) != 1
                            || !level.getBlockState(new net.minecraft.core.BlockPos(x + m, y, z + n)).isAir()) {
                        continue;
                    }
                    ChaosPersists.setBlockFast(level, x + m, y, z + n, (Block) appleLeaves, 0, 2);
                }
            }
            if (random.nextInt(2) != 1
                    || !level.getBlockState(new net.minecraft.core.BlockPos(x, y + 1, z)).isAir()) {
                continue;
            }
            ChaosPersists.setBlockFast(level, x, y + 1, z, (Block) appleLeaves, 0, 2);
        }
    }

    public void FairyTree(net.minecraft.world.level.Level level, int x, int y, int z) {
        int i;
        int k;
        int j;
        net.minecraft.util.RandomSource rnd = level.getRandom();
        Block crystalLog = ChaosPersists.MyCrystalTreeLog;
        for (j = 1; j < 6; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast(level, x + i, y + j, z + k, crystalLog, 0, 2);
                }
            }
        }
        this.grow_crystal_branch(level, x, y + 5, z, 0, 1, 1, 1, -1);
        this.grow_crystal_branch(level, x + 1, y + 5, z, 1, 0, 1, -1, -1);
        this.grow_crystal_branch(level, x, y + 5, z + 1, -1, 0, -1, 1, -1);
        this.grow_crystal_branch(level, x + 1, y + 5, z + 1, 0, -1, -1, -1, -1);
        this.grow_crystal_branch(level, x, y + 6, z, 0, 1, -1, 1, -1);
        this.grow_crystal_branch(level, x + 1, y + 6, z, 1, 0, 1, 1, -1);
        this.grow_crystal_branch(level, x, y + 6, z + 1, -1, 0, -1, -1, -1);
        this.grow_crystal_branch(level, x + 1, y + 6, z + 1, 0, -1, 1, -1, -1);
        int grow = 5 + rnd.nextInt(5);
        for (j = 6; j < 6 + grow; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast(level, x + i, y + j, z + k, crystalLog, 0, 2);
                    this.make_crystal_leaves(level, x + i, y + j, z + k);
                }
            }
        }
        net.minecraft.core.BlockPos spawnerPos = new net.minecraft.core.BlockPos(x - 1, y + 1, z);
        level.setBlock(spawnerPos, net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity spawnerEntity = level.getBlockEntity(spawnerPos);
        if (spawnerEntity instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
            net.minecraft.resources.ResourceLocation id =
                    SpawnerFixHelper.normalizeSpawnerEntityId(
                            new net.minecraft.resources.ResourceLocation(
                                    "chaospersists", "fairy"));
            net.minecraft.world.entity.EntityType<?> type =
                    net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getValue(id);
            if (type != null) {
                spawner.setEntityId(type, rnd);
            }
        }
        net.minecraft.core.BlockPos chestPos = new net.minecraft.core.BlockPos(x + 2, y + 1, z);
        level.setBlock(chestPos, net.minecraft.world.level.block.Blocks.CHEST.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity chestEntity = level.getBlockEntity(chestPos);
        if (chestEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
            WeightedRandomChestContent.generateChestContents(
                    rnd, CrystalChestContentsList, chest, 1 + rnd.nextInt(5));
        }
    }

    private void make_crystal_leaves(net.minecraft.world.level.Level level, int x, int y, int z) {
        Block crystalLeaves3 = ChaosPersists.MyCrystalLeaves3;
        for (int l1 = -2; l1 <= 2; ++l1) {
            for (int l2 = -2; l2 <= 2; ++l2) {
                for (int l3 = 0; l3 <= 1; ++l3) {
                    ChaosPersists.setBlockFast(level, x + l1, y + l3, z + l2, crystalLeaves3, 0, 2);
                }
            }
        }
    }

    private void make_crystal_castle_leaves(net.minecraft.world.level.Level level, int x, int y, int z) {
        Block crystalLeaves3 = ChaosPersists.MyCrystalLeaves3;
        Block crystalLeaves2 = ChaosPersists.MyCrystalLeaves2;
        for (int l1 = -1; l1 <= 1; ++l1) {
            for (int l2 = -1; l2 <= 1; ++l2) {
                for (int l3 = 0; l3 <= 1; ++l3) {
                    if (l3 != 0) {
                        ChaosPersists.setBlockFast(level, x + l1, y + l3, z + l2, crystalLeaves3, 0, 2);
                        continue;
                    }
                    ChaosPersists.setBlockFast(level, x + l1, y + l3, z + l2, crystalLeaves2, 0, 2);
                }
            }
        }
    }

    private void grow_crystal_branch(
            net.minecraft.world.level.Level level,
            int x,
            int y,
            int z,
            int xdir,
            int zdir,
            int xxdir,
            int zzdir,
            int ydir) {
        net.minecraft.util.RandomSource rnd = level.getRandom();
        Block crystalLog = ChaosPersists.MyCrystalTreeLog;
        int n;
        int i2 = 0;
        int k2 = 0;
        int j2 = 0;
        int i = x;
        int j = y;
        int k = z;
        int grow = 4 + rnd.nextInt(4);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, i, j, k, crystalLog, 0, 2);
            this.make_crystal_leaves(level, i, j, k);
            ++j;
            i2 = i += xdir;
            k2 = k += zdir;
        }
        grow = 5 + rnd.nextInt(5);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, i, j, k, crystalLog, 0, 2);
            this.make_crystal_leaves(level, i, j, k);
            i += xdir;
            k += zdir;
        }
        grow = 5 + rnd.nextInt(5);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, i2, j, k2, crystalLog, 0, 2);
            this.make_crystal_leaves(level, i2, j, k2);
            i2 += xxdir;
            k2 += zzdir;
        }
        j2 = --j;
        grow = 4 + rnd.nextInt(4);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, i, j, k, crystalLog, 0, 2);
            this.make_crystal_leaves(level, i, j, k);
            i += xdir;
            k += zdir;
            j += ydir;
        }
        grow = 4 + rnd.nextInt(4);
        if (ChaosPersists.LessLag == 1) {
            --grow;
        }
        if (ChaosPersists.LessLag == 2) {
            grow -= 2;
        }
        for (n = 0; n < grow; ++n) {
            ChaosPersists.setBlockFast(level, i2, j2, k2, crystalLog, 0, 2);
            this.make_crystal_leaves(level, i2, j2, k2);
            i2 += xxdir;
            k2 += zzdir;
            j2 += ydir;
        }
    }

    public void addSomething(net.minecraft.world.level.Level level, int x, int y, int z) {
        net.minecraft.util.RandomSource rnd = level.getRandom();
        int i = rnd.nextInt(3);
        if (i == 1) {
            net.minecraft.core.BlockPos spawnerPos = new net.minecraft.core.BlockPos(x, y + 1, z);
            level.setBlock(spawnerPos, net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(), 2);
            net.minecraft.world.level.block.entity.BlockEntity spawnerEntity = level.getBlockEntity(spawnerPos);
            if (spawnerEntity instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
                net.minecraft.resources.ResourceLocation id =
                        SpawnerFixHelper.normalizeSpawnerEntityId(
                                new net.minecraft.resources.ResourceLocation(
                                        "chaospersists", "fairy"));
                net.minecraft.world.entity.EntityType<?> type =
                        net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getValue(id);
                if (type != null) {
                    spawner.setEntityId(type, rnd);
                }
            }
        }
        if (i == 2) {
            net.minecraft.core.BlockPos chestPos = new net.minecraft.core.BlockPos(x, y + 1, z);
            level.setBlock(chestPos, net.minecraft.world.level.block.Blocks.CHEST.defaultBlockState(), 2);
            net.minecraft.world.level.block.entity.BlockEntity chestEntity = level.getBlockEntity(chestPos);
            if (chestEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
                WeightedRandomChestContent.generateChestContents(
                        rnd, CrystalChestContentsList, chest, 1 + rnd.nextInt(5));
            }
        }
    }

    public void FairyCastleTree(net.minecraft.world.level.Level level, int x, int y, int z) {
        net.minecraft.util.RandomSource rnd = level.getRandom();
        Block crystalLog = ChaosPersists.MyCrystalTreeLog;
        Block crystalTorch = ChaosPersists.CrystalTorch;
        int nc = 6;
        if (ChaosPersists.LessLag == 1) {
            --nc;
        }
        if (ChaosPersists.LessLag == 2) {
            nc -= 2;
        }
        int j = 3 + rnd.nextInt(3);
        int spread = 0;
        for (int iter = 0; iter < nc; ++iter) {
            int i;
            int k;
            int grow = 4 + rnd.nextInt(3);
            int width = 1 + rnd.nextInt(3);
            int randy = rnd.nextInt(3) - 1;
            for (i = - width; i <= width; ++i) {
                for (k = - width; k <= width; ++k) {
                    ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy, z + k, crystalLog, 0, 2);
                    if (i == - width || i == width || k == - width || k == width) {
                        this.make_crystal_castle_leaves(level, x + i + spread, y + j + randy, z + k);
                    }
                    if (iter != 0 && i == 0 && k == 0) {
                        this.addSomething(level, x + i + spread, y + j + randy, z + k);
                    }
                    if (i == - width && (k == - width || k == width)) {
                        ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy + 1, z + k, crystalTorch, 0, 2);
                    }
                    if (i != width || k != - width && k != width) continue;
                    ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy + 1, z + k, crystalTorch, 0, 2);
                }
            }
            if (iter != 0) {
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy, z + k, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i - spread, y + j + randy, z + k);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i - spread, y + j + randy, z + k);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy + 1, z + k, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy + 1, z + k, crystalTorch, 0, 2);
                    }
                }
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i, y + j + randy, z + k + spread, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i, y + j + randy, z + k + spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i, y + j + randy, z + k + spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i, y + j + randy + 1, z + k + spread, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i, y + j + randy + 1, z + k + spread, crystalTorch, 0, 2);
                    }
                }
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i, y + j + randy, z + k - spread, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i, y + j + randy, z + k - spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i, y + j + randy, z + k - spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i, y + j + randy + 1, z + k - spread, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i, y + j + randy + 1, z + k - spread, crystalTorch, 0, 2);
                    }
                }
            }
            if (iter >= 2) {
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy, z + k + spread, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i + spread, y + j + randy, z + k + spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i + spread, y + j + randy, z + k + spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy + 1, z + k + spread, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy + 1, z + k + spread, crystalTorch, 0, 2);
                    }
                }
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy, z + k - spread, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i - spread, y + j + randy, z + k - spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i - spread, y + j + randy, z + k - spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy + 1, z + k - spread, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy + 1, z + k - spread, crystalTorch, 0, 2);
                    }
                }
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy, z + k + spread, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i - spread, y + j + randy, z + k + spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i - spread, y + j + randy, z + k + spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy + 1, z + k + spread, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i - spread, y + j + randy + 1, z + k + spread, crystalTorch, 0, 2);
                    }
                }
                width = 1 + rnd.nextInt(3 + iter);
                randy = rnd.nextInt(3) - 1;
                for (i = - width; i <= width; ++i) {
                    for (k = - width; k <= width; ++k) {
                        ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy, z + k - spread, crystalLog, 0, 2);
                        if (i == - width || i == width || k == - width || k == width) {
                            this.make_crystal_castle_leaves(level, x + i + spread, y + j + randy, z + k - spread);
                        }
                        if (i == 0 && k == 0) {
                            this.addSomething(level, x + i + spread, y + j + randy, z + k - spread);
                        }
                        if (i == - width && (k == - width || k == width)) {
                            ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy + 1, z + k - spread, crystalTorch, 0, 2);
                        }
                        if (i != width || k != - width && k != width) continue;
                        ChaosPersists.setBlockFast(level, x + i + spread, y + j + randy + 1, z + k - spread, crystalTorch, 0, 2);
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

