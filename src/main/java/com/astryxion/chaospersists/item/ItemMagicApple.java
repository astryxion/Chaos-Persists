/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ItemMagicApple
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.TheKing
 *  com.astryxion.chaospersists.TheQueen
 *  net.minecraft.block.Block
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.ChestTileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemMagicApple
extends Item {
    public int tree_radius = 6;
    public boolean no_critters = false;
    Random rand = ChaosPersists.ChaosRand;
    private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 1, 2, 3), new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 5, 15), new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 1, 3, 10), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 1, 10, 7), new WeightedRandomChestContent(ChaosPersists.CagedGirlfriend, 0, 1, 2, 6), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 10, 16), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 1, 6, 16), new WeightedRandomChestContent(ChaosPersists.UraniumNugget, 0, 1, 6, 6), new WeightedRandomChestContent(ChaosPersists.TitaniumNugget, 0, 1, 4, 6), new WeightedRandomChestContent(Items.BREAD, 0, 1, 8, 20), new WeightedRandomChestContent(Items.APPLE, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKIE, 0, 1, 16, 20), new WeightedRandomChestContent(Items.COOKED_BEEF, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKED_CHICKEN, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKED_COD, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 1, 8, 20), new WeightedRandomChestContent(Items.PUMPKIN_PIE, 0, 1, 4, 20), new WeightedRandomChestContent(Items.CARROT, 0, 1, 16, 20), new WeightedRandomChestContent(Items.POTATO, 0, 1, 16, 20), new WeightedRandomChestContent(ChaosPersists.MySunFish, 0, 1, 4, 6), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 1, 8, 6), new WeightedRandomChestContent(ChaosPersists.MyPopcornBag, 0, 1, 4, 16), new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 20), new WeightedRandomChestContent(Items.IRON_SWORD, 0, 1, 1, 20), new WeightedRandomChestContent(Items.DIAMOND_PICKAXE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.DIAMOND_SWORD, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.BOW, 0, 1, 1, 20), new WeightedRandomChestContent(Items.ARROW, 0, 1, 64, 20), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 2), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 1), new WeightedRandomChestContent(ChaosPersists.MyUltimateFishingRod, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.IRON_CHESTPLATE, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.IRON_HELMET, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.IRON_LEGGINGS, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.IRON_BOOTS, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.DIAMOND_CHESTPLATE, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.DIAMOND_HELMET, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.DIAMOND_LEGGINGS, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.DIAMOND_BOOTS, 0, 1, 1, 5), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 1, 1, 5)};

    public ItemMagicApple(int i) {
        super(new Item.Properties());
    }

    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        par1ItemStack.enchant(Enchantments.SILK_TOUCH, 2);
    }

    private Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.entity.EntityType<?> entityType = net.minecraft.util.registry.Registry.ENTITY_TYPE.byId(par1);
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity)var8);
        }
        return var8;
    }

    public void onUseTick(World world, PlayerEntity player, ItemStack stack, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.SILK_TOUCH, 2);
        }
    }

    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        this.onUseTick(par2World, (PlayerEntity)null, stack, 0);
    }

    private Boolean isBoringBlock(World world, int x, int y, int z) {
        Block var1 = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)).getBlock();
        if (var1 == Blocks.GRASS_BLOCK) {
            return true;
        }
        if (var1 == Blocks.CACTUS) {
            return true;
        }
        if (var1 == Blocks.POPPY) {
            return true;
        }
        if (var1 == Blocks.DANDELION) {
            return true;
        }
        if (var1 == Blocks.OAK_LEAVES) {
            return true;
        }
        if (var1 == Blocks.SNOW) {
            return true;
        }
        if (var1 == ChaosPersists.MyStrawberryPlant) {
            return true;
        }
        if (var1 == ChaosPersists.MyAppleLeaves) {
            return true;
        }
        if (world.isEmptyBlock(new BlockPos(x, y, z))) {
            return true;
        }
        if (var1 == null) {
            return true;
        }
        return false;
    }

    private Boolean isBoringBaseBlock(World world, int x, int y, int z) {
        if (world.isEmptyBlock(new BlockPos(x, y, z))) {
            return true;
        }
        Block var1 = world.getBlockState(new net.minecraft.util.math.BlockPos(x, y, z)).getBlock();
        if (var1 == Blocks.STONE) {
            return false;
        }
        if (var1 == Blocks.BEDROCK) {
            return false;
        }
        return true;
    }

    private void growVines(World world, int par2, int par3, int par4, int par5, int par6, Chunk chunk) {
        if (world.getBlockState(new net.minecraft.util.math.BlockPos(par2, par3, par4)).getBlock() != Blocks.AIR) {
            return;
        }
        this.FastSetBlock(world, par2, par3, par4, Blocks.VINE, par5, 2, chunk);
        while (par6 > 0) {
            if (world.getBlockState(new net.minecraft.util.math.BlockPos(par2, --par3, par4)).getBlock() != Blocks.AIR) {
                return;
            }
            this.FastSetBlock(world, par2, par3, par4, Blocks.VINE, par5, 2, chunk);
            --par6;
        }
    }

    private void make_branch(World world, int x, int y, int z, int this_width, int dirx, int dirz, Block ID, Block leafID, int tree_type, int t_radius, boolean bad_critters, Chunk chunk) {
        int current_width = this_width;
        int last_branch = 0;
        int branch_side = 1;
        int leaf_depth = 0;
        int leaf_width = 0;
        int xaccum = dirx;
        int zaccum = dirz;
        if (this.random.nextInt(2) == 0) {
            branch_side = -1;
        }
        while (current_width >= 0) {
            int length = this_width * 3 + this.random.nextInt(this_width + 3);
            for (int i = 0; i < length; ++i) {
                int realx;
                int realz;
                int j;
                for (j = - current_width; j <= current_width; ++j) {
                    realx = x + j * dirz + xaccum;
                    realz = z + j * dirx + zaccum;
                    if (this.isBoringBlock(world, realx, y, realz).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, realx, y, realz, ID, tree_type, 2, chunk);
                        } else {
                            this.FastSetBlock(world, realx, y, realz, ID, 0, 2, chunk);
                        }
                    }
                    if (i <= 0 || j != 0 || current_width < 3) continue;
                    if (tree_type >= 0 && this.random.nextInt(75) == 0 || tree_type < 0 && this.random.nextInt(50) == 0) {
                        if (bad_critters || !world.isEmptyBlock(new BlockPos(realx, y + 1, realz))) continue;
                        this.FastSetBlock(world, realx, y + 1, realz, (Block)Blocks.CHEST, 0, 2, chunk);
                        ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new BlockPos(realx, y + 1, realz));
                        if (chest == null) continue;
                        WeightedRandomChestContent.generateChestContents((Random)this.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(1 + this.random.nextInt(8)));
                        continue;
                    }
                    if (this.random.nextInt(50) != 0 || bad_critters || !world.isEmptyBlock(new BlockPos(realx, y + 1, realz)) || !world.isEmptyBlock(new BlockPos(realx, y + 2, realz)) || !world.isEmptyBlock(new BlockPos(realx, y + 3, realz))) continue;
                    Entity ent = null;
                    ent = this.spawnCreature(world, 99, (double)realx + 0.5, (double)y + 1.01, (double)realz + 0.5);
                }
                if (current_width < 3 || this_width <= 1) {
                    leaf_depth = 2 + this.random.nextInt(2);
                    leaf_width = 2 + this.random.nextInt(3);
                    for (int n = 0; n < leaf_depth; ++n) {
                        int lw = current_width + leaf_width - n;
                        if (current_width == 0 && length - i <= 2 && lw >= length - i) {
                            lw = length - i - 1;
                        }
                        if (lw < 0) {
                            lw = 0;
                        }
                        for (j = - lw; j <= lw; ++j) {
                            realx = x + j * Math.abs(dirz) + xaccum + dirx;
                            if (!this.isBoringBlock(world, realx, y + n, realz = z + j * Math.abs(dirx) + zaccum + dirz).booleanValue()) continue;
                            if (tree_type >= 0) {
                                this.FastSetBlock(world, realx, y + n, realz, leafID, tree_type, 2, chunk);
                                if (n != 0 || tree_type != 3 || lw == 0 || j != lw && j != - lw || this.random.nextInt(5) != 0) continue;
                                if (dirx == 0) {
                                    if (j == lw) {
                                        this.growVines(world, realx + 1, y, realz, 2, this.random.nextInt(10), chunk);
                                        continue;
                                    }
                                    this.growVines(world, realx - 1, y, realz, 8, this.random.nextInt(10), chunk);
                                    continue;
                                }
                                if (j == lw) {
                                    this.growVines(world, realx, y, realz + 1, 4, this.random.nextInt(10), chunk);
                                    continue;
                                }
                                this.growVines(world, realx, y, realz - 1, 1, this.random.nextInt(10), chunk);
                                continue;
                            }
                            Block local_leaf_type = leafID;
                            if (this.random.nextInt(20) == 1) {
                                if (this.random.nextInt(3) != 0) {
                                    local_leaf_type = Blocks.REDSTONE_BLOCK;
                                } else {
                                    int ilt = this.random.nextInt(4);
                                    if (ilt == 0) {
                                        local_leaf_type = ChaosPersists.MyBlockUraniumBlock;
                                    }
                                    if (ilt == 1) {
                                        local_leaf_type = ChaosPersists.MyBlockTitaniumBlock;
                                    }
                                    if (ilt == 2) {
                                        local_leaf_type = ChaosPersists.MyBlockRubyBlock;
                                    }
                                    if (ilt == 3) {
                                        local_leaf_type = ChaosPersists.MyBlockAmethystBlock;
                                    }
                                }
                            }
                            this.FastSetBlock(world, realx, y + n, realz, local_leaf_type, 0, 2, chunk);
                        }
                    }
                }
                if (current_width > 0 && last_branch > current_width && current_width != this_width && this.random.nextInt(current_width + 1) == 0) {
                    int subdirx = branch_side;
                    int subdirz = 0;
                    if (dirx != 0) {
                        subdirx = 0;
                        subdirz = branch_side;
                    }
                    this.make_branch(world, x + xaccum + current_width * subdirx, y, z + zaccum + current_width * subdirz, current_width - 1, subdirx, subdirz, ID, leafID, tree_type, t_radius, bad_critters, chunk);
                    last_branch = 0;
                    branch_side = branch_side < 0 ? 1 : -1;
                }
                xaccum += dirx;
                zaccum += dirz;
                ++last_branch;
            }
            --current_width;
        }
    }

    public void MakeBigSquareTree(World world, int x, int y, int z, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, boolean bad_critters, Chunk chunk) {
        int i;
        int j;
        int this_height = t_radius + this.random.nextInt(t_radius);
        int this_width = t_radius;
        int base_height = t_radius * 3;
        int spiral = 0;
        int current_y = 0;
        boolean branch = false;
        boolean do_floor = false;
        int platform_looper = 1;
        int last = -1;
        int last_last = -1;
        block6 : for (i = - t_radius; i <= t_radius; ++i) {
            if (this.isBoringBaseBlock(world, x + i, y, z - t_radius).booleanValue()) {
                for (j = 0; j < 20; ++j) {
                    if (y - j <= 0) continue;
                    if (!this.isBoringBaseBlock(world, x + i, y - j, z - t_radius).booleanValue()) break;
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, x + i, y - j, z - t_radius, ID, tree_type, 2, chunk);
                        continue;
                    }
                    this.FastSetBlock(world, x + i, y - j, z - t_radius, ID, 0, 2, chunk);
                }
            }
            if (this.isBoringBaseBlock(world, x + i, y, z + t_radius).booleanValue()) {
                for (j = 0; j < 20; ++j) {
                    if (y - j <= 0) continue;
                    if (!this.isBoringBaseBlock(world, x + i, y - j, z + t_radius).booleanValue()) break;
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, x + i, y - j, z + t_radius, ID, tree_type, 2, chunk);
                        continue;
                    }
                    this.FastSetBlock(world, x + i, y - j, z + t_radius, ID, 0, 2, chunk);
                }
            }
            if (this.isBoringBaseBlock(world, x - t_radius, y, z + i).booleanValue()) {
                for (j = 0; j < 20; ++j) {
                    if (y - j <= 0) continue;
                    if (!this.isBoringBaseBlock(world, x - t_radius, y - j, z + i).booleanValue()) break;
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, x - t_radius, y - j, z + i, ID, tree_type, 2, chunk);
                        continue;
                    }
                    this.FastSetBlock(world, x - t_radius, y - j, z + i, ID, 0, 2, chunk);
                }
            }
            if (!this.isBoringBaseBlock(world, x + t_radius, y, z + i).booleanValue()) continue;
            for (j = 0; j < 20; ++j) {
                if (y - j <= 0) continue;
                if (!this.isBoringBaseBlock(world, x + t_radius, y - j, z + i).booleanValue()) continue block6;
                if (tree_type >= 0) {
                    this.FastSetBlock(world, x + t_radius, y - j, z + i, ID, tree_type, 2, chunk);
                    continue;
                }
                this.FastSetBlock(world, x + t_radius, y - j, z + i, ID, 0, 2, chunk);
            }
        }
        current_y = y;
        do_floor = false;
        spiral = - this_width;
        while (this_width >= 0) {
            if (this_width != t_radius) {
                base_height = 0;
            }
            for (j = 0; j < this_height + base_height; ++j) {
                do_floor = false;
                for (i = - this_width; i <= this_width; ++i) {
                    if (this.isBoringBaseBlock(world, x + i, current_y, z - this_width).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, x + i, current_y, z - this_width, ID, tree_type, 2, chunk);
                        } else {
                            this.FastSetBlock(world, x + i, current_y, z - this_width, ID, 0, 2, chunk);
                        }
                    }
                    if (this.isBoringBaseBlock(world, x + i, current_y, z + this_width).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, x + i, current_y, z + this_width, ID, tree_type, 2, chunk);
                        } else {
                            this.FastSetBlock(world, x + i, current_y, z + this_width, ID, 0, 2, chunk);
                        }
                    }
                    if (this.isBoringBaseBlock(world, x - this_width, current_y, z + i).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, x - this_width, current_y, z + i, ID, tree_type, 2, chunk);
                        } else {
                            this.FastSetBlock(world, x - this_width, current_y, z + i, ID, 0, 2, chunk);
                        }
                    }
                    if (!this.isBoringBaseBlock(world, x + this_width, current_y, z + i).booleanValue()) continue;
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, x + this_width, current_y, z + i, ID, tree_type, 2, chunk);
                        continue;
                    }
                    this.FastSetBlock(world, x + this_width, current_y, z + i, ID, 0, 2, chunk);
                }
                if (this_width != 0 || j < this_height / 2) {
                    platform_looper = 1;
                    if (spiral == 0 && this_width >= 2 || spiral == this_width || spiral == this_width - 1 && j == this_height + base_height - 1) {
                        ++platform_looper;
                        if (spiral != 0 && this_width >= 3) {
                            ++platform_looper;
                        }
                        if (spiral == 0) {
                            do_floor = true;
                        }
                    }
                    for (int k = 0; k < platform_looper; ++k) {
                        if (this.isBoringBlock(world, x - spiral, current_y, z - this_width - 1).booleanValue()) {
                            this.FastSetBlock(world, x - spiral, current_y, z - this_width - 1, stepID, 0, 2, chunk);
                        }
                        if (this.isBoringBlock(world, x + spiral, current_y, z + this_width + 1).booleanValue()) {
                            this.FastSetBlock(world, x + spiral, current_y, z + this_width + 1, stepID, 0, 2, chunk);
                        }
                        if (this.isBoringBlock(world, x - this_width - 1, current_y, z + spiral).booleanValue()) {
                            this.FastSetBlock(world, x - this_width - 1, current_y, z + spiral, stepID, 0, 2, chunk);
                        }
                        if (this.isBoringBlock(world, x + this_width + 1, current_y, z - spiral).booleanValue()) {
                            this.FastSetBlock(world, x + this_width + 1, current_y, z - spiral, stepID, 0, 2, chunk);
                        }
                        if (this_width >= 3) {
                            if (this.isBoringBlock(world, x - spiral, current_y, z - this_width - 2).booleanValue()) {
                                this.FastSetBlock(world, x - spiral, current_y, z - this_width - 2, stepID, 0, 2, chunk);
                            }
                            if (this.isBoringBlock(world, x + spiral, current_y, z + this_width + 2).booleanValue()) {
                                this.FastSetBlock(world, x + spiral, current_y, z + this_width + 2, stepID, 0, 2, chunk);
                            }
                            if (this.isBoringBlock(world, x - this_width - 2, current_y, z + spiral).booleanValue()) {
                                this.FastSetBlock(world, x - this_width - 2, current_y, z + spiral, stepID, 0, 2, chunk);
                            }
                            if (this.isBoringBlock(world, x + this_width + 2, current_y, z - spiral).booleanValue()) {
                                this.FastSetBlock(world, x + this_width + 2, current_y, z - spiral, stepID, 0, 2, chunk);
                            }
                        }
                        if (platform_looper == 1) continue;
                        ++spiral;
                    }
                    if (do_floor) {
                        for (int m = - this_width; m <= this_width; ++m) {
                            for (int n = - this_width; n <= this_width; ++n) {
                                if (!this.isBoringBlock(world, x + m, current_y, z + n).booleanValue()) continue;
                                if (tree_type >= 0) {
                                    this.FastSetBlock(world, x + m, current_y, z + n, ID, tree_type, 2, chunk);
                                } else {
                                    this.FastSetBlock(world, x + m, current_y, z + n, ID, 0, 2, chunk);
                                }
                                if (m != 0 || n != 0 || this.random.nextInt(2) != 0 || bad_critters || !world.isEmptyBlock(new BlockPos(x, current_y + 1, z))) continue;
                                this.FastSetBlock(world, x, current_y + 1, z, (Block)Blocks.CHEST, 0, 2, chunk);
                                ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new BlockPos(x, current_y + 1, z));
                                if (chest == null) continue;
                                WeightedRandomChestContent.generateChestContents((Random)this.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(t_radius - this_width + this.random.nextInt(10)));
                            }
                        }
                    }
                }
                if (this_width != t_radius) {
                    int next = this.random.nextInt(4 + this_width);
                    while (next == last || next == last_last) {
                        next = this.random.nextInt(4 + this_width);
                    }
                    if (next < 4) {
                        last_last = last;
                        last = next;
                    }
                    switch (next) {
                        case 0: {
                            this.make_branch(world, x + this_width, current_y, z, this_width, 1, 0, ID, leafID, tree_type, t_radius, bad_critters, chunk);
                            break;
                        }
                        case 1: {
                            this.make_branch(world, x - this_width, current_y, z, this_width, -1, 0, ID, leafID, tree_type, t_radius, bad_critters, chunk);
                            break;
                        }
                        case 2: {
                            this.make_branch(world, x, current_y, z + this_width, this_width, 0, 1, ID, leafID, tree_type, t_radius, bad_critters, chunk);
                            break;
                        }
                        case 3: {
                            this.make_branch(world, x, current_y, z - this_width, this_width, 0, -1, ID, leafID, tree_type, t_radius, bad_critters, chunk);
                            break;
                        }
                    }
                }
                ++current_y;
                if (!do_floor) {
                    ++spiral;
                }
                if (spiral <= this_width) continue;
                spiral = - this_width;
            }
            if (Math.abs(spiral) > --this_width) {
                spiral = - this_width;
            }
            this_height += this.random.nextInt(t_radius);
        }
        if (this.isBoringBaseBlock(world, x, current_y, z).booleanValue()) {
            Entity var8;
            this.FastSetBlock(world, x, current_y, z, Blocks.EMERALD_BLOCK, 0, 2, chunk);
            this.FastSetBlock(world, x, current_y + 1, z, Blocks.EMERALD_BLOCK, 0, 2, chunk);
            if (stepID == Blocks.DIAMOND_BLOCK) {
                var8 = null;
                net.minecraft.entity.EntityType<?> kingType = net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "the_king"));
                var8 = kingType != null ? kingType.create(world) : null;
                if (var8 != null) {
                    var8.moveTo((double)x, (double)(current_y + 4), (double)z, world.random.nextFloat() * 360.0f, 0.0f);
                    world.addFreshEntity(var8);
                    com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity)var8);
                    ((TheKing)var8).setGuardMode(1);
                }
            }
            if (stepID == ChaosPersists.MyBlockAmethystBlock) {
                var8 = null;
                net.minecraft.entity.EntityType<?> queenType = net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "the_queen"));
                var8 = queenType != null ? queenType.create(world) : null;
                if (var8 != null) {
                    var8.moveTo((double)x, (double)(current_y + 4), (double)z, world.random.nextFloat() * 360.0f, 0.0f);
                    world.addFreshEntity(var8);
                    com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity)var8);
                    ((TheQueen)var8).setGuardMode(1);
                    ((TheQueen)var8).setBadMood(1);
                }
            }
        }
    }

    private void MakeCirclularBranch(World world, int iangle, int branchlen, int width, int startx, int starty, int startz, int twist, Block ID, Block leafID, int tree_type, Chunk chunk) {
        double curlen = 0.0;
        int curangle = iangle;
        double curx = startx;
        double curz = startz;
        for (curlen = 0.0; curlen < (double)branchlen; curlen += 0.5) {
            curx += 0.5 * Math.sin(Math.toRadians(curangle));
            curz += 0.5 * Math.cos(Math.toRadians(curangle));
            double tw = (double)width - (double)width * curlen / (double)branchlen;
            for (double wd = 0.0; wd <= tw; wd += 0.5) {
                int ta;
                double wz;
                double wx;
                Block id = leafID;
                if (wd < tw / 2.0) {
                    id = ID;
                }
                if (tw < 0.9) {
                    id = leafID;
                }
                if ((ta = curangle + 90) > 360) {
                    ta -= 360;
                }
                if (this.isBoringBlock(world, (int)(wx = curx + wd * Math.sin(Math.toRadians(ta))), starty, (int)(wz = curz + wd * Math.cos(Math.toRadians(ta)))).booleanValue()) {
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, tree_type, 2, chunk);
                    } else {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
                    }
                }
                if (id == ID && this.isBoringBlock(world, (int)wx, starty + 1, (int)wz).booleanValue()) {
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, tree_type, 2, chunk);
                    } else {
                        this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
                    }
                }
                if ((ta = curangle - 90) < 0) {
                    ta += 360;
                }
                if (this.isBoringBlock(world, (int)(wx = curx + wd * Math.sin(Math.toRadians(ta))), starty, (int)(wz = curz + wd * Math.cos(Math.toRadians(ta)))).booleanValue()) {
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, tree_type, 2, chunk);
                    } else {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
                    }
                }
                if (id != ID || !this.isBoringBlock(world, (int)wx, starty + 1, (int)wz).booleanValue()) continue;
                if (tree_type >= 0) {
                    this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, tree_type, 2, chunk);
                    continue;
                }
                this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
            }
            if ((curangle += twist) < 0) {
                curangle += 360;
            }
            if (curangle < 360) continue;
            curangle -= 360;
        }
    }

    public void MakeBigCircularTree(World world, int x, int y, int z, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, boolean bad_critters, Chunk chunk) {
        int i;
        double dt;
        double rad = t_radius;
        int curx = 0;
        int cury = 0;
        int curz = 0;
        int stepindex = this.random.nextInt(360);
        int ibranch = 0;
        cury = y;
        block0 : for (i = 0; i < 360; ++i) {
            dt = rad * Math.sin(Math.toRadians(i)) + 0.5;
            curx = (int)dt;
            dt = rad * Math.cos(Math.toRadians(i)) + 0.5;
            curz = (int)dt;
            if (!this.isBoringBaseBlock(world, x + curx, cury, z + curz).booleanValue()) continue;
            for (int j = 0; j < 20; ++j) {
                if (cury - j <= 0) continue;
                if (!this.isBoringBaseBlock(world, x + curx, cury - j, z + curz).booleanValue()) continue block0;
                if (tree_type >= 0) {
                    this.FastSetBlock(world, x + curx, cury - j, z + curz, ID, tree_type, 2, chunk);
                    continue;
                }
                this.FastSetBlock(world, x + curx, cury - j, z + curz, ID, 0, 2, chunk);
            }
        }
        cury = 1;
        while (rad > 0.0) {
            for (i = 0; i < 360; ++i) {
                dt = rad * Math.sin(Math.toRadians(i)) + 0.5;
                curx = (int)dt;
                dt = rad * Math.cos(Math.toRadians(i)) + 0.5;
                curz = (int)dt;
                if (this.isBoringBaseBlock(world, x + curx, y + cury, z + curz).booleanValue()) {
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, tree_type, 2, chunk);
                    } else {
                        this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, 0, 2, chunk);
                    }
                }
                if (i < stepindex - 1 || i > stepindex + 1 || rad <= 1.0) continue;
                dt = (rad + 1.9) * Math.sin(Math.toRadians(i)) + 0.5;
                curx = (int)dt;
                dt = (rad + 1.9) * Math.cos(Math.toRadians(i)) + 0.5;
                curz = (int)dt;
                for (int m = -1; m <= 1; ++m) {
                    for (int n = -1; n <= 1; ++n) {
                        if (!this.isBoringBaseBlock(world, x + curx + m, y + cury, z + curz + n).booleanValue()) continue;
                        this.FastSetBlock(world, x + curx + m, y + cury, z + curz + n, stepID, 0, 2, chunk);
                    }
                }
            }
            if (cury > (int)rad) {
                if ((ibranch += 80 + this.random.nextInt(80)) > 360) {
                    ibranch -= 360;
                }
                int ibranchlen = (int)(rad * 5.0) + this.random.nextInt((int)rad + 2);
                dt = rad * Math.sin(Math.toRadians(ibranch)) + 0.5;
                curx = (int)dt;
                dt = rad * Math.cos(Math.toRadians(ibranch)) + 0.5;
                curz = (int)dt;
                this.MakeCirclularBranch(world, ibranch, ibranchlen, (int)rad + 1, x + curx, y + cury, z + curz, this.random.nextInt(2) * (this.random.nextInt(2) == 0 ? -1 : 1), ID, leafID, tree_type, chunk);
            }
            if (cury % 6 == 0 && rad > 3.0) {
                for (double dr = rad - 0.25; dr > 0.0; dr -= 0.25) {
                    for (i = 0; i < 360; ++i) {
                        dt = dr * Math.sin(Math.toRadians(i)) + 0.5;
                        curx = (int)dt;
                        dt = dr * Math.cos(Math.toRadians(i)) + 0.5;
                        curz = (int)dt;
                        if (!this.isBoringBaseBlock(world, x + curx, y + cury, z + curz).booleanValue()) continue;
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, tree_type, 2, chunk);
                            continue;
                        }
                        this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, 0, 2, chunk);
                    }
                }
                if (this.random.nextInt(2) == 0 && !bad_critters && world.isEmptyBlock(new BlockPos(x, y + cury + 1, z))) {
                    this.FastSetBlock(world, x, y + cury + 1, z, (Block)Blocks.CHEST, 0, 2, chunk);
                    ChestTileEntity chest = (ChestTileEntity)world.getBlockEntity(new BlockPos(x, y + cury + 1, z));
                    if (chest != null) {
                        WeightedRandomChestContent.generateChestContents((Random)this.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(t_radius - (int)rad + this.random.nextInt(10)));
                    }
                }
            }
            if ((stepindex += 15 + (int)(((double)t_radius - rad) * 3.0)) > 360) {
                stepindex -= 360;
            }
            if ((rad -= 0.01 * (double)this.random.nextInt(15)) > 0.0 || !this.isBoringBaseBlock(world, x, y + ++cury, z).booleanValue()) continue;
            this.FastSetBlock(world, x, y + cury, z, Blocks.DIAMOND_BLOCK, 0, 2, chunk);
        }
    }

    public void MakeBigRoundTree(World world, int inx, int y, int inz, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, Chunk chunk) {
        int i;
        double dt;
        float fcurx;
        float fcurz;
        double rad = t_radius;
        int cury = 0;
        int ibranch = 0;
        float fx = inx;
        fx += 0.5f;
        float fz = inz;
        fz += 0.5f;
        cury = y;
        block0 : for (i = 0; i < 360; ++i) {
            dt = rad * Math.sin(Math.toRadians(i));
            fcurx = (float)dt;
            dt = rad * Math.cos(Math.toRadians(i));
            fcurz = (float)dt;
            if (!this.isBoringBaseBlock(world, (int)(fx + fcurx), cury, (int)(fz + fcurz)).booleanValue()) continue;
            for (int j = 0; j < 20; ++j) {
                if (cury - j <= 0) continue;
                if (!this.isBoringBaseBlock(world, (int)(fx + fcurx), cury - j, (int)(fz + fcurz)).booleanValue()) continue block0;
                if (tree_type >= 0) {
                    this.FastSetBlock(world, (int)(fx + fcurx), cury - j, (int)(fz + fcurz), ID, tree_type, 2, chunk);
                    continue;
                }
                this.FastSetBlock(world, (int)(fx + fcurx), cury - j, (int)(fz + fcurz), ID, 0, 2, chunk);
            }
        }
        cury = 1;
        while (rad > 0.0) {
            for (i = 0; i < 360; ++i) {
                dt = rad * Math.sin(Math.toRadians(i));
                fcurx = (float)dt;
                dt = rad * Math.cos(Math.toRadians(i));
                fcurz = (float)dt;
                if (!this.isBoringBaseBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz)).booleanValue()) continue;
                if (tree_type >= 0) {
                    this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, tree_type, 2, chunk);
                    continue;
                }
                this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, 0, 2, chunk);
            }
            if (cury > (int)rad) {
                if ((ibranch += 80 + world.random.nextInt(80)) > 360) {
                    ibranch -= 360;
                }
                int ibranchlen = (int)(rad * 5.0) + world.random.nextInt((int)rad + 2);
                dt = rad * Math.sin(Math.toRadians(ibranch));
                fcurx = (float)dt;
                dt = rad * Math.cos(Math.toRadians(ibranch));
                fcurz = (float)dt;
                this.MakeRoundBranch(world, ibranch, ibranchlen, (int)rad + 1, fx + fcurx, y + cury, fz + fcurz, ID, leafID, tree_type, chunk);
            }
            if (cury % 6 == 0 && rad > 3.0) {
                for (double dr = rad - 0.25; dr > 0.0; dr -= 0.25) {
                    for (i = 0; i < 360; ++i) {
                        dt = dr * Math.sin(Math.toRadians(i));
                        fcurx = (float)dt;
                        dt = dr * Math.cos(Math.toRadians(i));
                        fcurz = (float)dt;
                        if (!this.isBoringBaseBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz)).booleanValue()) continue;
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, tree_type, 2, chunk);
                            continue;
                        }
                        this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, 0, 2, chunk);
                    }
                }
            }
            if ((rad -= 0.01 * (double)world.random.nextInt(15)) > 0.0 || !this.isBoringBaseBlock(world, (int)fx, y + ++cury, (int)fz).booleanValue()) continue;
            this.FastSetBlock(world, (int)fx, y + cury, (int)fz, Blocks.DIAMOND_BLOCK, 0, 2, chunk);
        }
    }

    private void MakeRoundBranch(World world, int iangle, int branchlen, int width, float startx, int starty, float startz, Block ID, Block leafID, int tree_type, Chunk chunk) {
        double deltadir = 0.06283185200000001;
        double deltamag = 0.3499999940395355;
        int ixlast = 0;
        int izlast = 0;
        boolean xoff = false;
        boolean zoff = false;
        int radius = branchlen / 2;
        float centerx = (float)((double)startx + (double)radius * Math.sin(Math.toRadians(iangle)));
        float centerz = (float)((double)startz + (double)radius * Math.cos(Math.toRadians(iangle)));
        izlast = 0;
        ixlast = 0;
        for (double curdir = -3.1415926; curdir < 3.1415926; curdir += deltadir) {
            for (double h = 0.75; h < (double)radius; h += deltamag) {
                int ix = (int)((double)centerx + Math.cos(curdir) * h);
                int iz = (int)((double)centerz + Math.sin(curdir) * h);
                if (ix == ixlast && iz == izlast) continue;
                ixlast = ix;
                izlast = iz;
                Block id = ID;
                if ((double)radius - h < 2.0) {
                    id = leafID;
                }
                if (!this.isBoringBlock(world, ix, starty, iz).booleanValue()) continue;
                this.FastSetBlock(world, ix, starty, iz, id, tree_type, 2, chunk);
            }
        }
    }

    public void FastSetBlock(World world, int ix, int iy, int iz, Block id, int im, int iflg, Chunk chunk) {
        ChaosPersists.setBlockSuperFast((World)world, (int)ix, (int)iy, (int)iz, (Block)id, (int)im, (int)2, (Chunk)chunk);
    }

    @Override
    public ActionResultType useOn(net.minecraft.item.ItemUseContext context)
    {
      PlayerEntity par2PlayerEntity = context.getPlayer();
      if (par2PlayerEntity == null) {
        return ActionResultType.FAIL;
      }
      World world = context.getLevel();
      BlockPos pos = context.getClickedPos();
      Hand hand = context.getHand();
      Direction facing = context.getClickedFace();
      ItemStack par1ItemStack = context.getItemInHand();
      int clickedX = pos.getX();
      int clickedY = pos.getY();
      int clickedZ = pos.getZ();
      Block var1 = world.getBlockState(new net.minecraft.util.math.BlockPos(clickedX, clickedY, clickedZ)).getBlock();
      if ((var1 != Blocks.GRASS_BLOCK) && (var1 != Blocks.FARMLAND) && (var1 != Blocks.DIRT)) {
        return ActionResultType.FAIL;
      }

      int tree_type = this.random.nextInt(4);

      Block leaf_type = Blocks.OAK_LEAVES;

      this.no_critters = true;
      if (this.random.nextInt(2) == 1) this.no_critters = false;

      if (!world.isClientSide) {
        world.setBlock(new BlockPos(clickedX, clickedY, clickedZ), Blocks.GOLD_BLOCK.defaultBlockState(), 2);
      }
      for (int var3 = 0; var3 < 6; var3++)
      {
        par2PlayerEntity.level.addParticle(ParticleTypes.SMOKE, clickedX + 0.5F, clickedY + 1 + 0.25F, clickedZ + 0.5F, 0.0D, 0.0D, 0.0D);
        par2PlayerEntity.level.addParticle(net.minecraft.particles.ParticleTypes.EXPLOSION, clickedX + 0.5F, clickedY + 1 + 0.25F, clickedZ + 0.5F, 0.0D, 0.0D, 0.0D);
        par2PlayerEntity.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), clickedX + 0.5F, clickedY + 1 + 0.25F, clickedZ + 0.5F, 0.0D, 0.0D, 0.0D);
      }

      par2PlayerEntity.level.playSound(null, par2PlayerEntity.getX(), par2PlayerEntity.getY(), par2PlayerEntity.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 2.8F, 1.5F);
      if (!world.isClientSide)
      {
        int rand_treetype = this.random.nextInt(100);

        if (rand_treetype >= 20) {
          if (rand_treetype >= 40) {
            if ((tree_type != 3) && (this.random.nextInt(10) == 1)) leaf_type = ChaosPersists.MyAppleLeaves;
            MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.OAK_LOG, leaf_type, Blocks.MOSSY_COBBLESTONE, tree_type, this.tree_radius, this.no_critters, null);
          } else {
            MakeBigRoundTree(world, clickedX, clickedY, clickedZ, Blocks.OAK_LOG, leaf_type, Blocks.MOSSY_COBBLESTONE, tree_type, this.tree_radius, null);
          }
        } else if (rand_treetype == 1) {
          if (ChaosPersists.GinormousEmeraldTreeEnable != 0) {
            if (this.random.nextInt(2) == 0)
              MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.GOLD_BLOCK, Blocks.EMERALD_BLOCK, Blocks.DIAMOND_BLOCK, -1, this.tree_radius, true, null);
            else
              MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.OBSIDIAN, ChaosPersists.MyBlockRubyBlock, ChaosPersists.MyBlockAmethystBlock, -1, this.tree_radius, true, null);
          }
          else
            MakeBigSquareTree(world, clickedX, clickedY, clickedZ, Blocks.OAK_LOG, leaf_type, Blocks.IRON_ORE, tree_type, this.tree_radius, this.no_critters, null);
        }
        else {
          MakeBigCircularTree(world, clickedX, clickedY, clickedZ, Blocks.OAK_LOG, leaf_type, Blocks.MOSSY_COBBLESTONE, tree_type, this.tree_radius, this.no_critters, null);
        }
      }

      if (!par2PlayerEntity.isCreative())
      {
        par1ItemStack.shrink(1);
      }

      return ActionResultType.SUCCESS;
    }}

