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
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockChest
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockFlower
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockGrass
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockLeaves
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockTallGrass
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  com.astryxion.chaospersists.legacy.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  com.astryxion.chaospersists.legacy.minecraft.init.Blocks
 *  com.astryxion.chaospersists.legacy.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.WeightedRandomChestContent
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 *  com.astryxion.chaospersists.legacy.minecraft.world.chunk.Chunk
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.ChunkPos;
import org.joml.Vector3f;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;

public class ItemMagicApple extends Item {
    public int tree_radius = 6;
    public boolean no_critters = false;
    Random rand = ChaosPersists.ChaosRand;
    private WeightedRandomChestContent[] chestContentsList;

    /** Built on first use so {@link ChaosPersists} static items are registered (1.12 init order). */
    private WeightedRandomChestContent[] getChestContentsList() {
        if (this.chestContentsList == null) {
            this.chestContentsList = buildChestContentsList();
        }
        return this.chestContentsList;
    }

    private static WeightedRandomChestContent[] buildChestContentsList() {
        return new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 1, 2, 3), new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 5, 15), new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 1, 3, 10), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 1, 10, 7), new WeightedRandomChestContent(ChaosPersists.CagedGirlfriend, 0, 1, 2, 6), new WeightedRandomChestContent(Items.IRON_INGOT, 0, 1, 10, 16), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 1, 6, 16), new WeightedRandomChestContent(ChaosPersists.UraniumNugget, 0, 1, 6, 6), new WeightedRandomChestContent(ChaosPersists.TitaniumNugget, 0, 1, 4, 6), new WeightedRandomChestContent(Items.BREAD, 0, 1, 8, 20), new WeightedRandomChestContent(Items.APPLE, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKIE, 0, 1, 16, 20), new WeightedRandomChestContent(Items.COOKED_BEEF, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKED_CHICKEN, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKED_COD, 0, 1, 8, 20), new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 1, 8, 20), new WeightedRandomChestContent(Items.PUMPKIN_PIE, 0, 1, 4, 20), new WeightedRandomChestContent(Items.CARROT, 0, 1, 16, 20), new WeightedRandomChestContent(Items.POTATO, 0, 1, 16, 20), new WeightedRandomChestContent(ChaosPersists.MySunFish, 0, 1, 4, 6), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 1, 8, 6), new WeightedRandomChestContent(ChaosPersists.MyPopcornBag, 0, 1, 4, 16), new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 20), new WeightedRandomChestContent(Items.IRON_SWORD, 0, 1, 1, 20), new WeightedRandomChestContent(Items.DIAMOND_PICKAXE, 0, 1, 1, 5), new WeightedRandomChestContent(Items.DIAMOND_SWORD, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.BOW, 0, 1, 1, 20), new WeightedRandomChestContent(Items.ARROW, 0, 1, 64, 20), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 2), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 1), new WeightedRandomChestContent(ChaosPersists.MyUltimateFishingRod, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.IRON_CHESTPLATE, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.IRON_HELMET, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.IRON_LEGGINGS, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.IRON_BOOTS, 0, 1, 1, 20), new WeightedRandomChestContent((Item)Items.DIAMOND_CHESTPLATE, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.DIAMOND_HELMET, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.DIAMOND_LEGGINGS, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.DIAMOND_BOOTS, 0, 1, 1, 5), new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 1, 1, 5)};
    }

    public ItemMagicApple(int i) {
        super(new Properties().stacksTo(1));
    }

    private static Block modBlock(Object block) {
        return (Block) block;
    }

    private static Block logBlockForTreeType(int treeType) {
        return switch (treeType & 3) {
            case 1 -> Blocks.SPRUCE_LOG;
            case 2 -> Blocks.BIRCH_LOG;
            case 3 -> Blocks.JUNGLE_LOG;
            default -> Blocks.OAK_LOG;
        };
    }

    private static Block leafBlockForTreeType(int treeType, Block leafOverride) {
        if (leafOverride == modBlock(ChaosPersists.MyAppleLeaves)) {
            return leafOverride;
        }
        if (leafOverride != Blocks.OAK_LEAVES) {
            return leafOverride;
        }
        return switch (treeType & 3) {
            case 1 -> Blocks.SPRUCE_LEAVES;
            case 2 -> Blocks.BIRCH_LEAVES;
            case 3 -> Blocks.JUNGLE_LEAVES;
            default -> Blocks.OAK_LEAVES;
        };
    }

    /** Schedule skylight rebuild after tree gen; does not block the apple use handler. */
    private void relightTreeVolume(Level world, int cx, int cy, int cz, int radius, Player player) {
        if (!(world instanceof ServerLevel serverLevel)) {
            return;
        }
        int horizontal = radius * 3 + 20;
        Set<Long> touched = new HashSet<>();
        for (int x = cx - horizontal; x <= cx + horizontal; x++) {
            for (int z = cz - horizontal; z <= cz + horizontal; z++) {
                touched.add(ChunkPos.asLong(x >> 4, z >> 4));
            }
        }
        ServerPlayer syncTo = player instanceof ServerPlayer serverPlayer ? serverPlayer : null;
        ChaosPersists.scheduleSkylightRelight(serverLevel, touched, syncTo);
    }

    private static int logMetaForBranch(int dirx, int dirz) {
        if (dirx != 0) {
            return 1;
        }
        if (dirz != 0) {
            return 2;
        }
        return 0;
    }

    @Override
    public void onCraftedBy(ItemStack par1ItemStack, Level par2World, Player par3EntityPlayer) {
        par1ItemStack.enchant(Enchantments.SILK_TOUCH, 2);
    }

    private Entity spawnCreature(Level par0World, int par1, double par2, double par4, double par6) {
        return ItemSpawnEgg.spawnCreature(par0World, par1, null, par2, par4, par6);
    }

    public void onUsingTick(ItemStack stack, Player player, int count) {
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack);
        if (lvl <= 0) {
            stack.enchant(Enchantments.SILK_TOUCH, 2);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level par2World, net.minecraft.world.entity.Entity par3Entity, int par4, boolean par5) {
        this.onUsingTick(stack, null, 0);
    }

    private Boolean isBoringBlock(Level world, int x, int y, int z) {
        Block var1 = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (var1 == Blocks.TALL_GRASS) {
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
        if (var1 == Blocks.OAK_LEAVES
                || var1 == Blocks.SPRUCE_LEAVES
                || var1 == Blocks.BIRCH_LEAVES
                || var1 == Blocks.JUNGLE_LEAVES
                || var1 == Blocks.ACACIA_LEAVES
                || var1 == Blocks.DARK_OAK_LEAVES) {
            return true;
        }
        if (var1 == Blocks.SNOW) {
            return true;
        }
        if (var1 == modBlock(ChaosPersists.MyStrawberryPlant)) {
            return true;
        }
        if (var1 == modBlock(ChaosPersists.MyAppleLeaves)) {
            return true;
        }
        if (world.getBlockState(new BlockPos(x, y, z)).isAir()) {
            return true;
        }
        if (var1 == null) {
            return true;
        }
        return false;
    }

    private Boolean isBoringBaseBlock(Level world, int x, int y, int z) {
        if (world.getBlockState(new BlockPos(x, y, z)).isAir()) {
            return true;
        }
        Block var1 = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        if (var1 == Blocks.STONE) {
            return false;
        }
        if (var1 == Blocks.BEDROCK) {
            return false;
        }
        return true;
    }

    private void growVines(Level world, int par2, int par3, int par4, int par5, int par6, LevelChunk chunk) {
        if (world.getBlockState(new BlockPos(par2, par3, par4)).getBlock() != Blocks.AIR) {
            return;
        }
        this.FastSetBlock(world, par2, par3, par4, Blocks.VINE, par5, 2, chunk);
        while (par6 > 0) {
            if (world.getBlockState(new BlockPos(par2, --par3, par4)).getBlock() != Blocks.AIR) {
                return;
            }
            this.FastSetBlock(world, par2, par3, par4, Blocks.VINE, par5, 2, chunk);
            --par6;
        }
    }

    private void make_branch(Level world, int x, int y, int z, int this_width, int dirx, int dirz, Block ID, Block leafID, int tree_type, int t_radius, boolean bad_critters, LevelChunk chunk) {
        int current_width = this_width;
        int last_branch = 0;
        int branch_side = 1;
        int leaf_depth = 0;
        int leaf_width = 0;
        int xaccum = dirx;
        int zaccum = dirz;
        if (this.rand.nextInt(2) == 0) {
            branch_side = -1;
        }
        int logMeta = logMetaForBranch(dirx, dirz);
        while (current_width >= 0) {
            int length = this_width * 3 + this.rand.nextInt(this_width + 3);
            for (int i = 0; i < length; ++i) {
                int realx;
                int realz;
                int j;
                for (j = - current_width; j <= current_width; ++j) {
                    realx = x + j * dirz + xaccum;
                    realz = z + j * dirx + zaccum;
                    if (this.isBoringBlock(world, realx, y, realz).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, realx, y, realz, ID, logMeta, 2, chunk);
                        } else {
                            this.FastSetBlock(world, realx, y, realz, ID, 0, 2, chunk);
                        }
                    }
                    if (i <= 0 || j != 0 || current_width < 3) continue;
                    if (tree_type >= 0 && this.rand.nextInt(75) == 0 || tree_type < 0 && this.rand.nextInt(50) == 0) {
                        if (bad_critters || !world.getBlockState(new BlockPos(realx, y + 1, realz)).isAir()) continue;
                        this.FastSetBlock(world, realx, y + 1, realz, Blocks.CHEST, 0, 2, chunk);
                        BlockPos chestPos = new BlockPos(realx, y + 1, realz);
                        if (!(world.getBlockEntity(chestPos) instanceof ChestBlockEntity chest)) {
                            continue;
                        }
                        WeightedRandomChestContent.generateChestContents(
                                this.rand, this.getChestContentsList(), chest, 1 + this.rand.nextInt(8));
                        continue;
                    }
                    if (this.rand.nextInt(50) != 0 || bad_critters || !world.getBlockState(new BlockPos(realx, y + 1, realz)).isAir() || !world.getBlockState(new BlockPos(realx, y + 2, realz)).isAir() || !world.getBlockState(new BlockPos(realx, y + 3, realz)).isAir()) continue;
                    Entity ent = null;
                    ent = this.spawnCreature(world, 99, (double) realx + 0.5, (double) y + 1.01, (double) realz + 0.5);
                }
                if (current_width < 3 || this_width <= 1) {
                    leaf_depth = 2 + this.rand.nextInt(2);
                    leaf_width = 2 + this.rand.nextInt(3);
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
                                this.FastSetBlock(world, realx, y + n, realz, leafID, 0, 2, chunk);
                                if (n != 0 || tree_type != 3 || lw == 0 || j != lw && j != - lw || this.rand.nextInt(5) != 0) continue;
                                if (dirx == 0) {
                                    if (j == lw) {
                                        this.growVines(world, realx + 1, y, realz, 2, this.rand.nextInt(10), chunk);
                                        continue;
                                    }
                                    this.growVines(world, realx - 1, y, realz, 8, this.rand.nextInt(10), chunk);
                                    continue;
                                }
                                if (j == lw) {
                                    this.growVines(world, realx, y, realz + 1, 4, this.rand.nextInt(10), chunk);
                                    continue;
                                }
                                this.growVines(world, realx, y, realz - 1, 1, this.rand.nextInt(10), chunk);
                                continue;
                            }
                            Block local_leaf_type = leafID;
                            if (this.rand.nextInt(20) == 1) {
                                if (this.rand.nextInt(3) != 0) {
                                    local_leaf_type = Blocks.REDSTONE_BLOCK;
                                } else {
                                    int ilt = this.rand.nextInt(4);
                                    if (ilt == 0) {
                                        local_leaf_type = modBlock(ChaosPersists.MyBlockUraniumBlock);
                                    }
                                    if (ilt == 1) {
                                        local_leaf_type = modBlock(ChaosPersists.MyBlockTitaniumBlock);
                                    }
                                    if (ilt == 2) {
                                        local_leaf_type = modBlock(ChaosPersists.MyBlockRubyBlock);
                                    }
                                    if (ilt == 3) {
                                        local_leaf_type = modBlock(ChaosPersists.MyBlockAmethystBlock);
                                    }
                                }
                            }
                            this.FastSetBlock(world, realx, y + n, realz, local_leaf_type, 0, 2, chunk);
                        }
                    }
                }
                if (current_width > 0 && last_branch > current_width && current_width != this_width && this.rand.nextInt(current_width + 1) == 0) {
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

    public void MakeBigSquareTree(Level world, int x, int y, int z, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, boolean bad_critters, LevelChunk chunk) {
        // 1.7.10 passed Blocks.log + meta=tree_type; 1.20 needs distinct log/leaf blocks.
        if (tree_type >= 0) {
            ID = logBlockForTreeType(tree_type);
            leafID = leafBlockForTreeType(tree_type, leafID);
        }
        int i;
        int j;
        int this_height = t_radius + this.rand.nextInt(t_radius);
        if (this_height < 3) {
            this_height = 3;
        }
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
                        this.FastSetBlock(world, x + i, y - j, z - t_radius, ID, 0, 2, chunk);
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
                        this.FastSetBlock(world, x + i, y - j, z + t_radius, ID, 0, 2, chunk);
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
                        this.FastSetBlock(world, x - t_radius, y - j, z + i, ID, 0, 2, chunk);
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
                    this.FastSetBlock(world, x + t_radius, y - j, z + i, ID, 0, 2, chunk);
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
                            this.FastSetBlock(world, x + i, current_y, z - this_width, ID, 0, 2, chunk);
                        } else {
                            this.FastSetBlock(world, x + i, current_y, z - this_width, ID, 0, 2, chunk);
                        }
                    }
                    if (this.isBoringBaseBlock(world, x + i, current_y, z + this_width).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, x + i, current_y, z + this_width, ID, 0, 2, chunk);
                        } else {
                            this.FastSetBlock(world, x + i, current_y, z + this_width, ID, 0, 2, chunk);
                        }
                    }
                    if (this.isBoringBaseBlock(world, x - this_width, current_y, z + i).booleanValue()) {
                        if (tree_type >= 0) {
                            this.FastSetBlock(world, x - this_width, current_y, z + i, ID, 0, 2, chunk);
                        } else {
                            this.FastSetBlock(world, x - this_width, current_y, z + i, ID, 0, 2, chunk);
                        }
                    }
                    if (!this.isBoringBaseBlock(world, x + this_width, current_y, z + i).booleanValue()) continue;
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, x + this_width, current_y, z + i, ID, 0, 2, chunk);
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
                                    this.FastSetBlock(world, x + m, current_y, z + n, ID, 0, 2, chunk);
                                } else {
                                    this.FastSetBlock(world, x + m, current_y, z + n, ID, 0, 2, chunk);
                                }
                                if (m != 0 || n != 0 || this.rand.nextInt(2) != 0 || bad_critters || !world.getBlockState(new BlockPos(x, current_y + 1, z)).isAir()) continue;
                                this.FastSetBlock(world, x, current_y + 1, z, Blocks.CHEST, 0, 2, chunk);
                                BlockPos chestPos = new BlockPos(x, current_y + 1, z);
                                if (!(world.getBlockEntity(chestPos) instanceof ChestBlockEntity chest)) {
                                    continue;
                                }
                                WeightedRandomChestContent.generateChestContents(
                                        this.rand,
                                        this.getChestContentsList(),
                                        chest,
                                        t_radius - this_width + this.rand.nextInt(10));
                            }
                        }
                    }
                }
                if (this_width != t_radius) {
                    int next = this.rand.nextInt(4 + this_width);
                    while (next == last || next == last_last) {
                        next = this.rand.nextInt(4 + this_width);
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
            this_height += this.rand.nextInt(t_radius);
        }
        if (this.isBoringBaseBlock(world, x, current_y, z).booleanValue()) {
            Entity var8;
            this.FastSetBlock(world, x, current_y, z, Blocks.EMERALD_BLOCK, 0, 2, chunk);
            this.FastSetBlock(world, x, current_y + 1, z, Blocks.EMERALD_BLOCK, 0, 2, chunk);
            if (world instanceof ServerLevel serverLevel) {
            if (stepID == Blocks.DIAMOND_BLOCK) {
                var8 = ChaosPersists.ENTITY_TYPE_THE_KING.get().create(serverLevel);
                if (var8 != null) {
                    var8.moveTo((double) x, (double) (current_y + 4), (double) z, world.getRandom().nextFloat() * 360.0f, 0.0f);
                    serverLevel.addFreshEntity(var8);
                    if (var8 instanceof Mob mob) {
                        MyUtils.playAmbientSound(mob);
                    }
                    ((TheKing) var8).setGuardMode(1);
                }
            }
            if (stepID == modBlock(ChaosPersists.MyBlockAmethystBlock)) {
                var8 = ChaosPersists.ENTITY_TYPE_THE_QUEEN.get().create(serverLevel);
                if (var8 != null) {
                    var8.moveTo((double) x, (double) (current_y + 4), (double) z, world.getRandom().nextFloat() * 360.0f, 0.0f);
                    serverLevel.addFreshEntity(var8);
                    if (var8 instanceof Mob mob) {
                        MyUtils.playAmbientSound(mob);
                    }
                    ((TheQueen) var8).setGuardMode(1);
                    ((TheQueen) var8).setBadMood(1);
                }
            }
            }
        }
    }

    private void MakeCirclularBranch(Level world, int iangle, int branchlen, int width, int startx, int starty, int startz, int twist, Block ID, Block leafID, int tree_type, LevelChunk chunk) {
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
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
                    } else {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
                    }
                }
                if (id == ID && this.isBoringBlock(world, (int)wx, starty + 1, (int)wz).booleanValue()) {
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
                    } else {
                        this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
                    }
                }
                if ((ta = curangle - 90) < 0) {
                    ta += 360;
                }
                if (this.isBoringBlock(world, (int)(wx = curx + wd * Math.sin(Math.toRadians(ta))), starty, (int)(wz = curz + wd * Math.cos(Math.toRadians(ta)))).booleanValue()) {
                    if (tree_type >= 0) {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
                    } else {
                        this.FastSetBlock(world, (int)wx, starty, (int)wz, id, 0, 2, chunk);
                    }
                }
                if (id != ID || !this.isBoringBlock(world, (int)wx, starty + 1, (int)wz).booleanValue()) continue;
                if (tree_type >= 0) {
                    this.FastSetBlock(world, (int)wx, starty + 1, (int)wz, leafID, 0, 2, chunk);
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

    public void MakeBigCircularTree(Level world, int x, int y, int z, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, boolean bad_critters, LevelChunk chunk) {
        // 1.7.10 passed Blocks.log + meta=tree_type; 1.20 needs distinct log/leaf blocks.
        if (tree_type >= 0) {
            ID = logBlockForTreeType(tree_type);
            leafID = leafBlockForTreeType(tree_type, leafID);
        }
        int i;
        double dt;
        double rad = t_radius;
        int curx = 0;
        int cury = 0;
        int curz = 0;
        int stepindex = this.rand.nextInt(360);
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
                    this.FastSetBlock(world, x + curx, cury - j, z + curz, ID, 0, 2, chunk);
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
                        this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, 0, 2, chunk);
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
                if ((ibranch += 80 + this.rand.nextInt(80)) > 360) {
                    ibranch -= 360;
                }
                int ibranchlen = (int)(rad * 5.0) + this.rand.nextInt((int)rad + 2);
                dt = rad * Math.sin(Math.toRadians(ibranch)) + 0.5;
                curx = (int)dt;
                dt = rad * Math.cos(Math.toRadians(ibranch)) + 0.5;
                curz = (int)dt;
                this.MakeCirclularBranch(world, ibranch, ibranchlen, (int)rad + 1, x + curx, y + cury, z + curz, this.rand.nextInt(2) * (this.rand.nextInt(2) == 0 ? -1 : 1), ID, leafID, tree_type, chunk);
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
                            this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, 0, 2, chunk);
                            continue;
                        }
                        this.FastSetBlock(world, x + curx, y + cury, z + curz, ID, 0, 2, chunk);
                    }
                }
                if (this.rand.nextInt(2) == 0 && !bad_critters && world.getBlockState(new BlockPos(x, y + cury + 1, z)).isAir()) {
                    this.FastSetBlock(world, x, y + cury + 1, z, Blocks.CHEST, 0, 2, chunk);
                    BlockPos chestPos = new BlockPos(x, y + cury + 1, z);
                    if (world.getBlockEntity(chestPos) instanceof ChestBlockEntity chest) {
                        WeightedRandomChestContent.generateChestContents(
                                this.rand,
                                this.getChestContentsList(),
                                chest,
                                t_radius - (int) rad + this.rand.nextInt(10));
                    }
                }
            }
            if ((stepindex += 15 + (int)(((double)t_radius - rad) * 3.0)) > 360) {
                stepindex -= 360;
            }
            // Always advance height each pass. Decompiled 1.7 used || with ++cury in the
            // condition; short-circuit skipped the increment until rad hit 0 → flat stump disks.
            rad -= 0.01D * (double) (1 + this.rand.nextInt(14));
            ++cury;
            if (rad > 0.0D || !this.isBoringBaseBlock(world, x, y + cury, z).booleanValue()) {
                continue;
            }
            this.FastSetBlock(world, x, y + cury, z, Blocks.DIAMOND_BLOCK, 0, 2, chunk);
        }
    }

    public void MakeBigRoundTree(Level world, int inx, int y, int inz, Block ID, Block leafID, Block stepID, int tree_type, int t_radius, LevelChunk chunk) {
        // 1.7.10 passed Blocks.log + meta=tree_type; 1.20 needs distinct log/leaf blocks.
        if (tree_type >= 0) {
            ID = logBlockForTreeType(tree_type);
            leafID = leafBlockForTreeType(tree_type, leafID);
        }
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
                    this.FastSetBlock(world, (int)(fx + fcurx), cury - j, (int)(fz + fcurz), ID, 0, 2, chunk);
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
                    this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, 0, 2, chunk);
                    continue;
                }
                this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, 0, 2, chunk);
            }
            if (cury > (int)rad) {
                if ((ibranch += 80 + world.getRandom().nextInt(80)) > 360) {
                    ibranch -= 360;
                }
                int ibranchlen = (int)(rad * 5.0) + world.getRandom().nextInt((int)rad + 2);
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
                            this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, 0, 2, chunk);
                            continue;
                        }
                        this.FastSetBlock(world, (int)(fx + fcurx), y + cury, (int)(fz + fcurz), ID, 0, 2, chunk);
                    }
                }
            }
            // Same height-advance fix as MakeBigCircularTree — without it trees stay 1-block flat disks.
            rad -= 0.01D * (double) (1 + world.getRandom().nextInt(14));
            ++cury;
            if (rad > 0.0D || !this.isBoringBaseBlock(world, (int) fx, y + cury, (int) fz).booleanValue()) {
                continue;
            }
            this.FastSetBlock(world, (int)fx, y + cury, (int)fz, Blocks.DIAMOND_BLOCK, 0, 2, chunk);
        }
    }

    private void MakeRoundBranch(Level world, int iangle, int branchlen, int width, float startx, int starty, float startz, Block ID, Block leafID, int tree_type, LevelChunk chunk) {
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
                this.FastSetBlock(world, ix, starty, iz, id, 0, 2, chunk);
            }
        }
    }

    public void FastSetBlock(Level world, int ix, int iy, int iz, Block id, int im, int iflg, LevelChunk chunk) {
        ChaosPersists.setBlockSuperFast(world, ix, iy, iz, id, im, 2, chunk);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player par2EntityPlayer = context.getPlayer();
        Level world = context.getLevel();
        if (par2EntityPlayer == null) {
            return InteractionResult.FAIL;
        }
        ItemStack par1ItemStack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        int clickedX = pos.getX();
        int clickedY = pos.getY();
        int clickedZ = pos.getZ();
        Block var1 = world.getBlockState(pos).getBlock();
        if (var1 != Blocks.GRASS_BLOCK && var1 != Blocks.FARMLAND && var1 != Blocks.DIRT) {
            return InteractionResult.FAIL;
        }

        int tree_type = this.rand.nextInt(4);
        Block log_block = logBlockForTreeType(tree_type);
        Block leaf_type = Blocks.OAK_LEAVES;
        int gen_radius = Math.max(4, this.tree_radius);

        this.no_critters = true;
        if (this.rand.nextInt(2) == 1) {
            this.no_critters = false;
        }

        if (!world.isClientSide()) {
            world.setBlock(pos, Blocks.GOLD_BLOCK.defaultBlockState(), 2);
        }
        for (int var3 = 0; var3 < 6; var3++) {
            world.addParticle(
                    ParticleTypes.LARGE_SMOKE,
                    clickedX + 0.5,
                    clickedY + 1 + 0.25,
                    clickedZ + 0.5,
                    0.0,
                    0.0,
                    0.0);
            world.addParticle(
                    ParticleTypes.EXPLOSION,
                    clickedX + 0.5,
                    clickedY + 1 + 0.25,
                    clickedZ + 0.5,
                    0.0,
                    0.0,
                    0.0);
            world.addParticle(
                    new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f),
                    clickedX + 0.5,
                    clickedY + 1 + 0.25,
                    clickedZ + 0.5,
                    0.0,
                    0.0,
                    0.0);
        }

        world.playSound(
                par2EntityPlayer,
                par2EntityPlayer.blockPosition(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS,
                2.8f,
                1.5f);

        if (!world.isClientSide()) {
            int rand_treetype = this.rand.nextInt(100);

            if (rand_treetype >= 20) {
                if (rand_treetype >= 40) {
                    if (tree_type != 3 && this.rand.nextInt(10) == 1) {
                        leaf_type = modBlock(ChaosPersists.MyAppleLeaves);
                    }
                    leaf_type = leafBlockForTreeType(tree_type, leaf_type);
                    MakeBigSquareTree(
                            world,
                            clickedX,
                            clickedY,
                            clickedZ,
                            log_block,
                            leaf_type,
                            Blocks.MOSSY_COBBLESTONE,
                            tree_type,
                            gen_radius,
                            this.no_critters,
                            null);
                } else {
                    leaf_type = leafBlockForTreeType(tree_type, leaf_type);
                    MakeBigRoundTree(
                            world,
                            clickedX,
                            clickedY,
                            clickedZ,
                            log_block,
                            leaf_type,
                            Blocks.MOSSY_COBBLESTONE,
                            tree_type,
                            gen_radius,
                            null);
                }
            } else if (rand_treetype == 1) {
                if (ChaosPersists.GinormousEmeraldTreeEnable != 0) {
                    if (this.rand.nextInt(2) == 0) {
                        MakeBigSquareTree(
                                world,
                                clickedX,
                                clickedY,
                                clickedZ,
                                Blocks.GOLD_BLOCK,
                                Blocks.EMERALD_BLOCK,
                                Blocks.DIAMOND_BLOCK,
                                -1,
                                gen_radius,
                                true,
                                null);
                    } else {
                        MakeBigSquareTree(
                                world,
                                clickedX,
                                clickedY,
                                clickedZ,
                                Blocks.OBSIDIAN,
                                modBlock(ChaosPersists.MyBlockRubyBlock),
                                modBlock(ChaosPersists.MyBlockAmethystBlock),
                                -1,
                                gen_radius,
                                true,
                                null);
                    }
                } else {
                    leaf_type = leafBlockForTreeType(tree_type, leaf_type);
                    MakeBigSquareTree(
                            world,
                            clickedX,
                            clickedY,
                            clickedZ,
                            log_block,
                            leaf_type,
                            Blocks.IRON_ORE,
                            tree_type,
                            gen_radius,
                            this.no_critters,
                            null);
                }
            } else {
                leaf_type = leafBlockForTreeType(tree_type, leaf_type);
                MakeBigCircularTree(
                        world,
                        clickedX,
                        clickedY,
                        clickedZ,
                        log_block,
                        leaf_type,
                        Blocks.MOSSY_COBBLESTONE,
                        tree_type,
                        gen_radius,
                        this.no_critters,
                        null);
            }
            relightTreeVolume(world, clickedX, clickedY, clickedZ, gen_radius, par2EntityPlayer);
        }

        if (!par2EntityPlayer.getAbilities().instabuild) {
            par1ItemStack.shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}

