/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Basilisk
 *  com.astryxion.chaospersists.BasiliskMaze
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.ChestTileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BasiliskMaze {
    public static final int WTOP = 1;
    public static final int WRGT = 2;
    public static final int WBOT = 4;
    public static final int WLFT = 8;
    private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 3, 6, 15), new WeightedRandomChestContent(Items.DIAMOND, 0, 15, 25, 20), new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 4, 12, 15), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 3, 10, 20), new WeightedRandomChestContent(ChaosPersists.CagedGirlfriend, 0, 2, 4, 15), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 2, 20, 20), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 4, 16, 20), new WeightedRandomChestContent(ChaosPersists.MyIngotUranium, 0, 2, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyIngotTitanium, 0, 2, 6, 20), new WeightedRandomChestContent(ChaosPersists.MySunFish, 0, 2, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 3, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyLavaEel, 0, 5, 24, 20), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 6, 12, 20), new WeightedRandomChestContent(Items.DIAMOND_PICKAXE, 0, 1, 1, 15), new WeightedRandomChestContent(Items.DIAMOND_SWORD, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateFishingRod, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_CHESTPLATE, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_HELMET, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_LEGGINGS, 0, 1, 1, 15), new WeightedRandomChestContent((Item)Items.DIAMOND_BOOTS, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyThunderStaff, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MagicApple, 0, 1, 1, 15), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 2, 4, 15)};

    public void buildBasiliskMaze(World world, int x, int y, int z) {
        int depth = 20 + world.random.nextInt(10);
        this.clearArea(world, x + 3, y - depth - 4, z - 20);
        this.makeMaze(world, x + 3, y - depth - 3, z - 20, 10, 10, 3, 0);
        this.openMaze(world, x + 3, y - depth - 3, z - 20, 10, 10, 3);
        this.buildCastle(world, x + 3, y - depth - 4, z - 20);
        this.makeEntrance(world, x, y, z, depth);
    }

    private void makeMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz, int b) {
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
                    this.drawSide(world, x * cellsize, y * cellsize, (x + 1) * cellsize, y * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
                }
                if ((val & 2) != 0) {
                    this.drawSide(world, (x + 1) * cellsize - 1, y * cellsize, (x + 1) * cellsize - 1, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
                }
                if ((val & 4) != 0) {
                    this.drawSide(world, x * cellsize, (y + 1) * cellsize - 1, (x + 1) * cellsize, (y + 1) * cellsize - 1, xx, yy, zz, cellsize, gridh, gridw, b);
                }
                if ((val & 8) == 0) continue;
                this.drawSide(world, x * cellsize, y * cellsize, x * cellsize, (y + 1) * cellsize, xx, yy, zz, cellsize, gridh, gridw, b);
            }
        }
    }

    private void drawSide(World world, int fromx, int fromz, int tox, int toz, int x, int y, int z, int cellsize, int gridh, int gridw, int bb) {
        int i;
        Block blk = Blocks.OBSIDIAN;
        if (bb != 0) {
            blk = Blocks.BEDROCK;
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
                ChaosPersists.setBlockFast((World)world, (int)(i + x), (int)y, (int)(j + z), (Block)blk, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(i + x), (int)(y + 1), (int)(j + z), (Block)blk, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(i + x), (int)(y + 2), (int)(j + z), (Block)blk, (int)0, (int)2);
            }
        } else {
            int j = fromz;
            for (i = fromx; i <= tox; ++i) {
                if (i >= cellsize * gridw) continue;
                ChaosPersists.setBlockFast((World)world, (int)(i + x), (int)y, (int)(j + z), (Block)blk, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(i + x), (int)(y + 1), (int)(j + z), (Block)blk, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(i + x), (int)(y + 2), (int)(j + z), (Block)blk, (int)0, (int)2);
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

    private Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.entity.EntityType<?> spawnType = net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation(par1.contains(":") ? par1.split(":")[0] : "chaospersists", par1.contains(":") ? par1.split(":")[1] : par1));
        var8 = spawnType != null ? spawnType.create(par0World) : null;
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity)var8);
        }
        return var8;
    }

    private void clearArea(World world, int x, int y, int z) {
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
                    ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + j), (int)(z + k), (Block)Blocks.AIR, (int)0, (int)2);
                }
            }
        }
        for (i = 0; i < 5; ++i) {
            for (j = 0; j < 6; ++j) {
                for (k = 0; k < 30; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(x - i), (int)(y + j), (int)(z + k), (Block)Blocks.AIR, (int)0, (int)2);
                }
            }
        }
    }

    private void openMaze(World world, int xx, int yy, int zz, int xw, int zw, int csz) {
        int i;
        Block bid;
        for (i = 0; i < zw * csz; ++i) {
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(xx + 1, yy, zz + i)).getBlock();
            if (bid != Blocks.AIR) continue;
            ChaosPersists.setBlockFast((World)world, (int)xx, (int)yy, (int)(zz + i), (Block)Blocks.AIR, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)xx, (int)(yy + 1), (int)(zz + i), (Block)Blocks.AIR, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)xx, (int)(yy + 2), (int)(zz + i), (Block)Blocks.AIR, (int)0, (int)2);
            break;
        }
        for (i = zw * csz - 1; i >= 0; --i) {
            bid = world.getBlockState(new net.minecraft.util.math.BlockPos(xx + xw * csz - 2, yy, zz + i)).getBlock();
            if (bid != Blocks.AIR) continue;
            ChaosPersists.setBlockFast((World)world, (int)(xx + xw * csz - 1), (int)yy, (int)(zz + i), (Block)Blocks.AIR, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(xx + xw * csz - 1), (int)(yy + 1), (int)(zz + i), (Block)Blocks.AIR, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(xx + xw * csz - 1), (int)(yy + 2), (int)(zz + i), (Block)Blocks.AIR, (int)0, (int)2);
            break;
        }
    }

    private void buildCastle(World world, int x, int y, int z) {
        int i;
        int k;
        for (i = 0; i < 60; ++i) {
            for (k = 0; k < 30; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)y, (int)(z + k), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
            }
        }
        for (i = 0; i < 80; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(x + world.random.nextInt(28) + 1), (int)y, (int)(z + world.random.nextInt(28) + 1), (Block)Blocks.LAVA, (int)0, (int)2);
        }
        for (i = 0; i < 20; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(x + 30 + world.random.nextInt(28) + 1), (int)y, (int)(z + world.random.nextInt(28) + 1), (Block)ChaosPersists.MyRTPBlock, (int)0, (int)2);
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 30; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i), (int)(y + 4), (int)(z + k), (Block)Blocks.BEDROCK, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 30; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x + i + 30), (int)(y + 6), (int)(z + k), (Block)Blocks.BEDROCK, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 5; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x + 59), (int)(y + k + 1), (int)(z + i), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(x + 60), (int)(y + k + 1), (int)(z + i), (Block)Blocks.BEDROCK, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(x + 61), (int)(y + k + 1), (int)(z + i), (Block)Blocks.BEDROCK, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 5; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x + 30 + i), (int)(y + k + 1), (int)z, (Block)Blocks.OBSIDIAN, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(x + 30 + i), (int)(y + k + 1), (int)(z - 1), (Block)Blocks.BEDROCK, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(x + 30 + i), (int)(y + k + 1), (int)(z - 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 5; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x + 30 + i), (int)(y + k + 1), (int)(z + 29), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(x + 30 + i), (int)(y + k + 1), (int)(z + 30), (Block)Blocks.BEDROCK, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(x + 30 + i), (int)(y + k + 1), (int)(z + 31), (Block)Blocks.BEDROCK, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(x + 30), (int)(y + 5), (int)(z + i), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 4; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x - 4 + k), (int)y, (int)(z + i), (Block)Blocks.SANDSTONE, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 0; k < 4; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x - 4 + k), (int)(y + 5), (int)(z + i), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
            }
        }
        for (i = 0; i < 30; ++i) {
            for (k = 1; k < 5; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x - 5), (int)(y + k), (int)(z + i), (Block)Blocks.IRON_ORE, (int)0, (int)2);
            }
        }
        for (i = 0; i < 5; ++i) {
            for (k = 1; k < 5; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x - 4 + i), (int)(y + k), (int)(z - 1), (Block)Blocks.IRON_ORE, (int)0, (int)2);
            }
        }
        for (i = 0; i < 5; ++i) {
            for (k = 1; k < 5; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(x - 4 + i), (int)(y + k), (int)(z + 30), (Block)Blocks.IRON_ORE, (int)0, (int)2);
            }
        }
        for (k = 0; k < 4; ++k) {
            ChaosPersists.setBlockFast((World)world, (int)(x - 4), (int)(y + 1 + k), (int)z, (Block)Blocks.SANDSTONE, (int)0, (int)2);
        }
        for (k = 0; k < 4; ++k) {
            ChaosPersists.setBlockFast((World)world, (int)(x - 4), (int)(y + 1 + k), (int)(z + 15), (Block)Blocks.SANDSTONE, (int)0, (int)2);
        }
        for (k = 0; k < 4; ++k) {
            ChaosPersists.setBlockFast((World)world, (int)(x - 4), (int)(y + 1 + k), (int)(z + 29), (Block)Blocks.SANDSTONE, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast((World)world, (int)(x - 3), (int)(y + 3), (int)z, (Block)ChaosPersists.ExtremeTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(x - 3), (int)(y + 3), (int)(z + 15), (Block)ChaosPersists.ExtremeTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(x - 3), (int)(y + 3), (int)(z + 29), (Block)ChaosPersists.ExtremeTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(x + 30), (int)(y + 4), (int)(z + 2), (Block)Blocks.REDSTONE_TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(x + 30), (int)(y + 4), (int)(z + 15), (Block)Blocks.REDSTONE_TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(x + 30), (int)(y + 4), (int)(z + 27), (Block)Blocks.REDSTONE_TORCH, (int)0, (int)2);
        ChestTileEntity chest = null;
        i = 2 + world.random.nextInt(3);
        for (k = 0; k < i; ++k) {
            ChaosPersists.setBlockFast((World)world, (int)(x + 58), (int)(y + 4), (int)(z + 2 + k * 2), (Block)Blocks.TORCH, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(x + 58), (int)(y + 1), (int)(z + 2 + k * 2), (Block)Blocks.CHEST, (int)0, (int)2);
            chest = (ChestTileEntity)world.getBlockEntity(new net.minecraft.util.math.BlockPos(x + 58, y + 1, z + 2 + k * 2));
            if (chest == null) continue;
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(5 + world.random.nextInt(6)));
        }
        // Use actual spawners so the maze continuously functions after generation.
        for (int sx = 45; sx <= 47; ++sx) {
            world.setBlock(new net.minecraft.util.math.BlockPos(x + sx, y + 1, z + 15), Blocks.SPAWNER.defaultBlockState(), 2);
            TileEntity te = world.getBlockEntity(new net.minecraft.util.math.BlockPos(x + sx, y + 1, z + 15));
            if (te instanceof MobSpawnerTileEntity) {
                com.astryxion.chaospersists.util.SpawnerFixHelper.setSpawnerEntityId(((MobSpawnerTileEntity)te).getSpawner(), new ResourceLocation("chaospersists", "basilisk"));
            }
        }
    }

    public void makeEntrance(World world, int x, int y, int z, int depth)
    {
        int width = 8;

        for (int j = width; j >= 0; j--) {
          for (int i = 0; i < j * 2 + 4; i++) {
            ChaosPersists.setBlockFast(world, x + i - j, y + width - j, z - j, Blocks.SANDSTONE, 0, 2);
            ChaosPersists.setBlockFast(world, x + i - j, y + width - j, z + j + 3, Blocks.SANDSTONE, 0, 2);
            ChaosPersists.setBlockFast(world, x - j, y + width - j, z + i - j, Blocks.SANDSTONE, 0, 2);
            ChaosPersists.setBlockFast(world, x + j + 3, y + width - j, z + i - j, Blocks.SANDSTONE, 0, 2);
          }

        }

        int k = 0;
        // Continue the stair/shaft all the way down to the maze depth so the entrance is continuous.
        for (int j = width; j >= -depth; j--) {
          for (int i = 0; i < 4; i++) {
            ChaosPersists.setBlockFast(world, x + i, y + j, z, Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(world, x + i, y + j, z + 3, Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(world, x, y + j, z + i, Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(world, x + 3, y + j, z + i, Blocks.BEDROCK, 0, 2);
          }

          for (int l = 0; l < 2; l++) {
            for (int m = 0; m < 2; m++) {
              ChaosPersists.setBlockFast(world, x + 1 + l, y + j, z + 1 + m, Blocks.AIR, 0, 2);
            }
          }
          switch (k) {
          case 0:
            ChaosPersists.setBlockFast(world, x + 1, y + j, z + 1, Blocks.OBSIDIAN, 0, 2);
            break;
          case 1:
            ChaosPersists.setBlockFast(world, x + 2, y + j, z + 1, Blocks.OBSIDIAN, 0, 2);
            break;
          case 2:
            ChaosPersists.setBlockFast(world, x + 2, y + j, z + 2, Blocks.OBSIDIAN, 0, 2);
            break;
          default:
            ChaosPersists.setBlockFast(world, x + 1, y + j, z + 2, Blocks.OBSIDIAN, 0, 2);
          }

          k++;
          if (k <= 3) continue; k = 0;
        }
      }
    }