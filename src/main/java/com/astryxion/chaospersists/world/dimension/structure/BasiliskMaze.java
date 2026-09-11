/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Basilisk
 *  com.astryxion.chaospersists.BasiliskMaze
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockChest
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  com.astryxion.chaospersists.legacy.minecraft.init.Blocks
 *  com.astryxion.chaospersists.legacy.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.WeightedRandomChestContent
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 */
package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.awt.Point;
import java.util.Vector;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class BasiliskMaze {
    public static final int WTOP = 1;
    public static final int WRGT = 2;
    public static final int WBOT = 4;
    public static final int WLFT = 8;
    private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 3, 6, 15), new WeightedRandomChestContent(Items.DIAMOND, 0, 15, 25, 20), new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 4, 12, 15), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 3, 10, 20), new WeightedRandomChestContent(ChaosPersists.CagedGirlfriend, 0, 2, 4, 15), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 2, 20, 20), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 4, 16, 20), new WeightedRandomChestContent(ChaosPersists.MyIngotUranium, 0, 2, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyIngotTitanium, 0, 2, 6, 20), new WeightedRandomChestContent(ChaosPersists.MySunFish, 0, 2, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 3, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyLavaEel, 0, 5, 24, 20), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 6, 12, 20), new WeightedRandomChestContent(Items.DIAMOND_PICKAXE, 0, 1, 1, 15), new WeightedRandomChestContent(Items.DIAMOND_SWORD, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateFishingRod, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_CHESTPLATE, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_HELMET, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_LEGGINGS, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_BOOTS, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyThunderStaff, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MagicApple, 0, 1, 1, 15), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 2, 4, 15)};

    public void buildBasiliskMaze(Object worldObj, int x, int y, int z) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int depth = 20 + level.getRandom().nextInt(10);
        this.clearArea(level, x + 3, y - depth - 4, z - 20);
        this.makeMaze(level, x + 3, y - depth - 3, z - 20, 10, 10, 3, 0);
        this.openMaze(level, x + 3, y - depth - 3, z - 20, 10, 10, 3);
        this.buildCastle(level, x + 3, y - depth - 4, z - 20);
        this.makeEntrance(level, x, y, z, depth);
    }

    private void makeMaze(net.minecraft.world.level.Level level, int xx, int yy, int zz, int xw, int zw, int csz, int b) {
        int y;
        int x;
        int gridw = xw;
        int gridh = zw;
        int cellsize = csz;
        if (cellsize < 3) {
            cellsize = 3;
        }
        int[][] cells = new int[gridw][gridh];
        int full = 15;
        for (x = 0; x < gridw; ++x) {
            for (y = 0; y < gridh; ++y) {
                cells[x][y] = full;
            }
        }
        int left = 128;
        int right = 32;
        y = 0;
        while (y < gridh) {
            int[] arrn = cells[0];
            int n = y;
            arrn[n] = arrn[n] | left;
            int[] arrn2 = cells[gridw - 1];
            int n2 = y++;
            arrn2[n2] = arrn2[n2] | right;
        }
        int top = 16;
        int bottom = 64;
        for (x = 0; x < gridw; ++x) {
            int[] arrn = cells[x];
            arrn[0] = arrn[0] | top;
            int[] arrn3 = cells[x];
            int n = gridh - 1;
            arrn3[n] = arrn3[n] | bottom;
        }
        Vector<Point> outlist = new Vector<Point>(gridw * gridh);
        Vector<Point> inlist = new Vector<Point>(10, 10);
        Vector frontlist = new Vector(10, 10);
        for (x = 0; x < gridw; ++x) {
            for (y = 0; y < gridh; ++y) {
                outlist.addElement(new Point(x, y));
            }
        }
        Point current_cell = (Point)this.rndElement(outlist);
        inlist.addElement(current_cell);
        this.moveNbrs(current_cell, cells, outlist, frontlist);
        while (!frontlist.isEmpty()) {
            current_cell = (Point)this.rndElement(frontlist);
            inlist.addElement(current_cell);
            this.moveNbrs(current_cell, cells, outlist, frontlist);
            int dir = this.findInNbr(current_cell, cells, inlist);
            this.removeWall(current_cell, dir, cells);
        }
        current_cell = null;
        for (x = 0; x < gridw; ++x) {
            for (y = 0; y < gridh; ++y) {
                int val = cells[x][y];
                if ((val & 1) != 0) {
                    this.drawSide(level, x * cellsize, y * cellsize, (x + 1) * cellsize, y * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
                }
                if ((val & 2) != 0) {
                    this.drawSide(level, (x + 1) * cellsize - 1, y * cellsize, (x + 1) * cellsize - 1, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
                }
                if ((val & 4) != 0) {
                    this.drawSide(level, x * cellsize, (y + 1) * cellsize - 1, (x + 1) * cellsize, (y + 1) * cellsize - 1, xx, yy, zz, cellsize, gridh, gridw, b);
                }
                if ((val & 8) == 0) continue;
                this.drawSide(level, x * cellsize, y * cellsize, x * cellsize, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
            }
        }
    }

    private void drawSide(
            net.minecraft.world.level.Level level,
            int fromx,
            int fromz,
            int tox,
            int toz,
            int x,
            int y,
            int z,
            int cellsize,
            int gridh,
            int gridw,
            int bb) {
        int i;
        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        if (bb != 0) {
            blk = net.minecraft.world.level.block.Blocks.BEDROCK;
        }
        if (fromx > tox) {
            i = fromx;
            fromx = tox;
            tox = i;
        }
        if (fromz > toz) {
            i = fromz;
            fromz = toz;
            toz = i;
        }
        if (fromx == tox) {
            i = fromx;
            for (int j = fromz; j <= toz; ++j) {
                if (j >= cellsize * gridh) continue;
                ChaosPersists.setBlockFast(level, i + x, y, j + z, blk, 0, 2);
                ChaosPersists.setBlockFast(level, i + x, y + 1, j + z, blk, 0, 2);
                ChaosPersists.setBlockFast(level, i + x, y + 2, j + z, blk, 0, 2);
            }
        } else {
            int j = fromz;
            for (i = fromx; i <= tox; ++i) {
                if (i >= cellsize * gridw) continue;
                ChaosPersists.setBlockFast(level, i + x, y, j + z, blk, 0, 2);
                ChaosPersists.setBlockFast(level, i + x, y + 1, j + z, blk, 0, 2);
                ChaosPersists.setBlockFast(level, i + x, y + 2, j + z, blk, 0, 2);
            }
        }
    }

    private int findInNbr(Point p, int[][] cells, Vector inlist) {
        int d = this.rnd(4) - 1;
        for (int k = 0; k < 4; ++k) {
            switch (d) {
                case 0: {
                    if ((cells[p.x][p.y] & 16) != 0 || inlist.indexOf(new Point(p.x, p.y - 1)) < 0) break;
                    return 1;
                }
                case 1: {
                    if ((cells[p.x][p.y] & 32) != 0 || inlist.indexOf(new Point(p.x + 1, p.y)) < 0) break;
                    return 2;
                }
                case 2: {
                    if ((cells[p.x][p.y] & 64) != 0 || inlist.indexOf(new Point(p.x, p.y + 1)) < 0) break;
                    return 4;
                }
                case 3: {
                    if ((cells[p.x][p.y] & 128) != 0 || inlist.indexOf(new Point(p.x - 1, p.y)) < 0) break;
                    return 8;
                }
            }
            d = (d + 1) % 4;
        }
        return 0;
    }

    private void moveNbrs(Point p, int[][] cells, Vector outlist, Vector frontlist) {
        Point s;
        if ((cells[p.x][p.y] & 16) == 0) {
            s = new Point(p.x, p.y - 1);
            this.movePoint(s, outlist, frontlist);
        }
        if ((cells[p.x][p.y] & 32) == 0) {
            s = new Point(p.x + 1, p.y);
            this.movePoint(s, outlist, frontlist);
        }
        if ((cells[p.x][p.y] & 64) == 0) {
            s = new Point(p.x, p.y + 1);
            this.movePoint(s, outlist, frontlist);
        }
        if ((cells[p.x][p.y] & 128) == 0) {
            s = new Point(p.x - 1, p.y);
            this.movePoint(s, outlist, frontlist);
        }
    }

    private void movePoint(Point p, Vector v, Vector w) {
        int i = v.indexOf(p);
        if (i >= 0) {
            v.removeElementAt(i);
            w.addElement(p);
        }
    }

    private void removeWall(Point p, int d, int[][] cells) {
        int[] arrn = cells[p.x];
        int n = p.y;
        arrn[n] = arrn[n] ^ d;
        switch (d) {
            case 1: {
                int[] arrn2 = cells[p.x];
                int n2 = p.y - 1;
                arrn2[n2] = arrn2[n2] ^ 4;
                break;
            }
            case 2: {
                int[] arrn3 = cells[p.x + 1];
                int n3 = p.y;
                arrn3[n3] = arrn3[n3] ^ 8;
                break;
            }
            case 4: {
                int[] arrn4 = cells[p.x];
                int n4 = p.y + 1;
                arrn4[n4] = arrn4[n4] ^ 1;
                break;
            }
            case 8: {
                int[] arrn5 = cells[p.x - 1];
                int n5 = p.y;
                arrn5[n5] = arrn5[n5] ^ 2;
            }
        }
    }

    private int rnd(int n) {
        return (int)(Math.random() * (double)n + 1.0);
    }

    private Object rndElement(Vector v) {
        int i = this.rnd(v.size()) - 1;
        Object s = v.elementAt(i);
        v.removeElementAt(i);
        return s;
    }

    private net.minecraft.world.entity.Entity spawnCreature(
            net.minecraft.world.level.Level level, String par1, double par2, double par4, double par6) {
        net.minecraft.resources.ResourceLocation id =
                new net.minecraft.resources.ResourceLocation(
                        par1.contains(":") ? par1.split(":")[0] : "chaospersists",
                        par1.contains(":") ? par1.split(":")[1] : par1);
        net.minecraft.world.entity.EntityType<?> type =
                net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getValue(id);
        if (type == null) {
            return null;
        }
        net.minecraft.world.entity.Entity entity = type.create(level);
        if (entity != null) {
            entity.moveTo(par2, par4, par6, level.getRandom().nextFloat() * 360.0f, 0.0f);
            level.addFreshEntity(entity);
        }
        return entity;
    }

    private void clearArea(net.minecraft.world.level.Level level, int x, int y, int z) {
        int i;
        int k;
        int j;
        for (i = 0; i < 60; ++i) {
            int hi = 5;
            if (i >= 30) {
                hi = 7;
            }
            for (j = 0; j < hi; ++j) {
                for (k = 0; k < 30; ++k) {
                    ChaosPersists.setBlockFast(level, (int)(x + i), (int)(y + j), (int)(z + k), net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                }
            }
        }
        for (i = 0; i < 5; ++i) {
            for (j = 0; j < 6; ++j) {
                for (k = 0; k < 30; ++k) {
                    ChaosPersists.setBlockFast(level, (int)(x - i), (int)(y + j), (int)(z + k), net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                }
            }
        }
    }

    private void openMaze(net.minecraft.world.level.Level level, int xx, int yy, int zz, int xw, int zw, int csz) {
        int i;
        for (i = 0; i < zw * csz; ++i) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(xx + 1, yy, zz + i)).isAir()) {
                continue;
            }
            ChaosPersists.setBlockFast(level, xx, yy, zz + i, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            ChaosPersists.setBlockFast(level, xx, yy + 1, zz + i, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            ChaosPersists.setBlockFast(level, xx, yy + 2, zz + i, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            break;
        }
        for (i = zw * csz - 1; i >= 0; --i) {
            if (!level.getBlockState(new net.minecraft.core.BlockPos(xx + xw * csz - 2, yy, zz + i)).isAir()) {
                continue;
            }
            ChaosPersists.setBlockFast(level, xx + xw * csz - 1, yy, zz + i, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            ChaosPersists.setBlockFast(level, xx + xw * csz - 1, yy + 1, zz + i, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            ChaosPersists.setBlockFast(level, xx + xw * csz - 1, yy + 2, zz + i, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            break;
        }
    }

    private void placeBasiliskSpawner(net.minecraft.world.level.Level level, int x, int y, int z) {
        net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x, y, z);
        level.setBlock(pos, net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
            net.minecraft.resources.ResourceLocation id =
                    SpawnerFixHelper.normalizeSpawnerEntityId(
                            new net.minecraft.resources.ResourceLocation("chaospersists", "basilisk"));
            net.minecraft.world.entity.EntityType<?> type =
                    net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getValue(id);
            if (type != null) {
                spawner.setEntityId(type, level.getRandom());
            }
        }
    }

    private void buildCastle(net.minecraft.world.level.Level level, int x, int y, int z) {
        int i;
        int k;
        for (i = 0; i < 60; ++i) {
            for (k = 0; k < 30; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x + i), (int)y, (int)(z + k), net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
            }
        }
        for (i = 0; i < 80; ++i) {
            ChaosPersists.setBlockFast(
                    level,
                    x + level.getRandom().nextInt(28) + 1,
                    y,
                    z + level.getRandom().nextInt(28) + 1,
                    net.minecraft.world.level.block.Blocks.LAVA,
                    0,
                    2);
        }
        for (i = 0; i < 20; ++i) {
            ChaosPersists.setBlockFast(
                    level,
                    x + 30 + level.getRandom().nextInt(28) + 1,
                    y,
                    z + level.getRandom().nextInt(28) + 1,
                    (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyRTPBlock,
                    0,
                    2);
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 30; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x + i), (int)(y + 4), (int)(z + k), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 30; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x + i + 30), (int)(y + 6), (int)(z + k), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 5; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x + 59), (int)(y + k + 1), (int)(z + i), net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
                ChaosPersists.setBlockFast(level, (int)(x + 60), (int)(y + k + 1), (int)(z + i), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
                ChaosPersists.setBlockFast(level, (int)(x + 61), (int)(y + k + 1), (int)(z + i), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 5; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x + 30 + i), (int)(y + k + 1), (int)z, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
                ChaosPersists.setBlockFast(level, (int)(x + 30 + i), (int)(y + k + 1), (int)(z - 1), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
                ChaosPersists.setBlockFast(level, (int)(x + 30 + i), (int)(y + k + 1), (int)(z - 2), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 5; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x + 30 + i), (int)(y + k + 1), (int)(z + 29), net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
                ChaosPersists.setBlockFast(level, (int)(x + 30 + i), (int)(y + k + 1), (int)(z + 30), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
                ChaosPersists.setBlockFast(level, (int)(x + 30 + i), (int)(y + k + 1), (int)(z + 31), net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            ChaosPersists.setBlockFast(level, (int)(x + 30), (int)(y + 5), (int)(z + i), net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 4; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x - 4 + k), (int)y, (int)(z + i), net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 4; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x - 4 + k), (int)(y + 5), (int)(z + i), net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 1; k < 5; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x - 5), (int)(y + k), (int)(z + i), net.minecraft.world.level.block.Blocks.IRON_ORE, 0, 2);
            }
        }
        for (i = 0; i < 5; ++i) {
            for (k = 1; k < 5; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x - 4 + i), (int)(y + k), (int)(z - 1), net.minecraft.world.level.block.Blocks.IRON_ORE, 0, 2);
            }
        }
        for (i = 0; i < 5; ++i) {
            for (k = 1; k < 5; ++k) {
                ChaosPersists.setBlockFast(level, (int)(x - 4 + i), (int)(y + k), (int)(z + 30), net.minecraft.world.level.block.Blocks.IRON_ORE, 0, 2);
            }
        }
        for (k = 0; k < 4; ++k) {
            ChaosPersists.setBlockFast(level, (int)(x - 4), (int)(y + 1 + k), (int)z, net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
        }
        for (k = 0; k < 4; ++k) {
            ChaosPersists.setBlockFast(level, (int)(x - 4), (int)(y + 1 + k), (int)(z + 15), net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
        }
        for (k = 0; k < 4; ++k) {
            ChaosPersists.setBlockFast(level, (int)(x - 4), (int)(y + 1 + k), (int)(z + 29), net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
        }
        ChaosPersists.setBlockFast(
                level, x - 3, y + 3, z, (net.minecraft.world.level.block.Block) (Object) ChaosPersists.ExtremeTorch, 0, 2);
        ChaosPersists.setBlockFast(
                level,
                x - 3,
                y + 3,
                z + 15,
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.ExtremeTorch,
                0,
                2);
        ChaosPersists.setBlockFast(
                level,
                x - 3,
                y + 3,
                z + 29,
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.ExtremeTorch,
                0,
                2);
        ChaosPersists.setBlockFast(level, (int)(x + 30), (int)(y + 4), (int)(z + 2), net.minecraft.world.level.block.Blocks.REDSTONE_TORCH, 0, 2);
        ChaosPersists.setBlockFast(level, (int)(x + 30), (int)(y + 4), (int)(z + 15), net.minecraft.world.level.block.Blocks.REDSTONE_TORCH, 0, 2);
        ChaosPersists.setBlockFast(level, (int)(x + 30), (int)(y + 4), (int)(z + 27), net.minecraft.world.level.block.Blocks.REDSTONE_TORCH, 0, 2);
        net.minecraft.util.RandomSource rand = level.getRandom();
        i = 2 + rand.nextInt(3);
        for (k = 0; k < i; ++k) {
            int chestZ = z + 2 + k * 2;
            ChaosPersists.setBlockFast(
                    level, x + 58, y + 4, chestZ, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
            ChaosPersists.setBlockFast(
                    level, x + 58, y + 1, chestZ, net.minecraft.world.level.block.Blocks.CHEST, 0, 2);
            net.minecraft.world.level.block.entity.BlockEntity blockEntity =
                    level.getBlockEntity(new net.minecraft.core.BlockPos(x + 58, y + 1, chestZ));
            if (blockEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
                WeightedRandomChestContent.generateChestContents(
                        rand, this.chestContentsList, chest, 5 + rand.nextInt(6));
            }
        }
        for (int sx = 45; sx <= 47; ++sx) {
            this.placeBasiliskSpawner(level, x + sx, y + 1, z + 15);
        }
    }

    public void makeEntrance(net.minecraft.world.level.Level level, int x, int y, int z, int depth)
    {
        int width = 8;

        for (int j = width; j >= 0; j--) {
          for (int i = 0; i < j * 2 + 4; i++) {
            ChaosPersists.setBlockFast(
                    level, x + i - j, y + width - j, z - j, net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
            ChaosPersists.setBlockFast(
                    level, x + i - j, y + width - j, z + j + 3, net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
            ChaosPersists.setBlockFast(
                    level, x - j, y + width - j, z + i - j, net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
            ChaosPersists.setBlockFast(
                    level, x + j + 3, y + width - j, z + i - j, net.minecraft.world.level.block.Blocks.SANDSTONE, 0, 2);
          }

        }

        int k = 0;
        // Continue the stair/shaft all the way down to the maze depth so the entrance is continuous.
        for (int j = width; j >= -depth; j--) {
          for (int i = 0; i < 4; i++) {
            ChaosPersists.setBlockFast(level, x + i, y + j, z, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(level, x + i, y + j, z + 3, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(level, x, y + j, z + i, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(level, x + 3, y + j, z + i, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
          }

          for (int l = 0; l < 2; l++) {
            for (int m = 0; m < 2; m++) {
              ChaosPersists.setBlockFast(
                      level, x + 1 + l, y + j, z + 1 + m, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
          }
          switch (k) {
          case 0:
            ChaosPersists.setBlockFast(level, x + 1, y + j, z + 1, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
            break;
          case 1:
            ChaosPersists.setBlockFast(level, x + 2, y + j, z + 1, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
            break;
          case 2:
            ChaosPersists.setBlockFast(level, x + 2, y + j, z + 2, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
            break;
          default:
            ChaosPersists.setBlockFast(level, x + 1, y + j, z + 2, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
          }

          k++;
          if (k <= 3) continue; k = 0;
        }
      }
    }