package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Right-click block with sifter: loot table depends on block below cursor (and water above for
 * "panning"). 1.7.10 used int-based {@code onItemUse}; 1.12.2 must override
 * {@link #onItemUse(PlayerEntity, World, BlockPos, Hand, Direction, float, float, float)}.
 */
public class ItemSifter extends Item {

    public ItemSifter(int i) { super(new Item.Properties()); }

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
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        ItemStack stack = player.getItemInHand(hand);
        trySift(stack, player, world, pos.getX(), pos.getY(), pos.getZ());
        return ActionResultType.SUCCESS;
    }

    private void dropItemRand(Item index, int par1, World world, int x, int y, int z) {
        ItemEntity entityItem = new ItemEntity(
                world,
                (double) (x + ChaosPersists.ChaosRand.nextInt(2) - ChaosPersists.ChaosRand.nextInt(2)) + 0.5,
                (double) y + 1.1,
                (double) (z + ChaosPersists.ChaosRand.nextInt(2) - ChaosPersists.ChaosRand.nextInt(2)) + 0.5,
                new ItemStack(index, par1));
        world.addFreshEntity(entityItem);
    }

    private void trySift(ItemStack stack, PlayerEntity player, World world, int par4, int par5, int par6) {
        int i;
        Block bid = world.getBlockState(new BlockPos(par4, par5, par6)).getBlock();
        Block bid2 = world.getBlockState(new BlockPos(par4, par5 + 1, par6)).getBlock();
        if (bid2 == Blocks.WATER) {
            bid = Blocks.WATER;
        }
        if (bid2 == Blocks.WATER) {
            bid = Blocks.WATER;
        }
        if (bid == Blocks.WATER) {
            i = world.random.nextInt(160);
            switch (i) {
                case 0:
                    this.dropItemRand(Items.COD, 1, world, par4, par5, par6);
                    break;
                case 1:
                    this.dropItemRand(ChaosPersists.MyGreenFish, 1, world, par4, par5, par6);
                    break;
                case 2:
                    this.dropItemRand(ChaosPersists.MyBlueFish, 1, world, par4, par5, par6);
                    break;
                case 3:
                    this.dropItemRand(ChaosPersists.MyPinkFish, 1, world, par4, par5, par6);
                    break;
                case 4:
                    this.dropItemRand(ChaosPersists.MyRockFish, 1, world, par4, par5, par6);
                    break;
                case 5:
                    this.dropItemRand(ChaosPersists.MyWoodFish, 1, world, par4, par5, par6);
                    break;
                case 6:
                    this.dropItemRand(ChaosPersists.MyGreyFish, 1, world, par4, par5, par6);
                    break;
                case 7:
                    this.dropItemRand(Items.GLASS_BOTTLE, 1, world, par4, par5, par6);
                    break;
                case 8:
                    this.dropItemRand(Items.IRON_INGOT, 1, world, par4, par5, par6);
                    break;
                case 9:
                    this.dropItemRand(Items.GOLD_NUGGET, 1, world, par4, par5, par6);
                    break;
                case 10:
                    this.dropItemRand(ChaosPersists.MyItemShoes, 1, world, par4, par5, par6);
                    break;
                case 11:
                    this.dropItemRand(ChaosPersists.MyItemShoes_1, 1, world, par4, par5, par6);
                    break;
                case 12:
                    this.dropItemRand(ChaosPersists.MyItemShoes_2, 1, world, par4, par5, par6);
                    break;
                case 13:
                    this.dropItemRand(ChaosPersists.MyItemShoes_3, 1, world, par4, par5, par6);
                    break;
                case 14:
                    this.dropItemRand(Items.GLASS_BOTTLE, 1, world, par4, par5, par6);
                    break;
                case 15:
                    this.dropItemRand(Items.BONE, 1, world, par4, par5, par6);
                    break;
                case 16:
                    this.dropItemRand(Item.byBlock(Blocks.STONE), 1, world, par4, par5, par6);
                    break;
                case 17:
                    this.dropItemRand(Items.BUCKET, 1, world, par4, par5, par6);
                    break;
                case 18:
                    this.dropItemRand(Items.WATER_BUCKET, 1, world, par4, par5, par6);
                    break;
                case 19:
                    if (world.random.nextInt(3) == 1) {
                        this.dropItemRand(Items.EMERALD, 1, world, par4, par5, par6);
                    } else {
                        this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    }
                    break;
                case 20:
                    if (world.random.nextInt(3) == 1) {
                        this.dropItemRand(ChaosPersists.MyRuby, 1, world, par4, par5, par6);
                    } else {
                        this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    }
                    break;
                case 21:
                    if (world.random.nextInt(3) == 1) {
                        this.dropItemRand(ChaosPersists.MyAmethyst, 1, world, par4, par5, par6);
                    } else {
                        this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    }
                    break;
                case 22:
                    this.dropItemRand(ChaosPersists.MyMothScale, 1, world, par4, par5, par6);
                    break;
                case 23:
                    this.dropItemRand(ChaosPersists.UraniumNugget, 1, world, par4, par5, par6);
                    break;
                case 24:
                    this.dropItemRand(ChaosPersists.TitaniumNugget, 1, world, par4, par5, par6);
                    break;
                case 25:
                    if (world.random.nextInt(2) == 1) {
                        this.dropItemRand(Items.DIAMOND, 1, world, par4, par5, par6);
                    } else {
                        this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    }
                    break;
                case 26:
                    this.dropItemRand(Items.IRON_INGOT, 1, world, par4, par5, par6);
                    break;
                case 27:
                    this.dropItemRand(Items.GOLD_NUGGET, 1, world, par4, par5, par6);
                    break;
                case 28:
                    this.dropItemRand(Items.REDSTONE, 1, world, par4, par5, par6);
                    break;
                case 29:
                    this.dropItemRand(Items.COAL, 1, world, par4, par5, par6);
                    break;
                case 30:
                    this.dropItemRand(ChaosPersists.MyItemShoes, 1, world, par4, par5, par6);
                    break;
                case 31:
                    this.dropItemRand(ChaosPersists.MyItemShoes_1, 1, world, par4, par5, par6);
                    break;
                case 32:
                    this.dropItemRand(ChaosPersists.MyItemShoes_2, 1, world, par4, par5, par6);
                    break;
                case 33:
                    this.dropItemRand(ChaosPersists.MyItemShoes_3, 1, world, par4, par5, par6);
                    break;
                case 34:
                    this.dropItemRand(Items.COD, 1, world, par4, par5, par6);
                    break;
                case 35:
                    this.dropItemRand(Items.GLASS_BOTTLE, 1, world, par4, par5, par6);
                    break;
                case 36:
                    this.dropItemRand(Items.BONE, 1, world, par4, par5, par6);
                    break;
                case 37:
                    this.dropItemRand(Item.byBlock(Blocks.STONE), 1, world, par4, par5, par6);
                    break;
                case 38:
                    this.dropItemRand(Item.byBlock(Blocks.STONE_BUTTON), 1, world, par4, par5, par6);
                    break;
                case 39:
                    this.dropItemRand(Items.BUCKET, 1, world, par4, par5, par6);
                    break;
                case 40:
                    this.dropItemRand(Items.WATER_BUCKET, 1, world, par4, par5, par6);
                    break;
            }
        }
        if (bid == Blocks.SAND) {
            i = world.random.nextInt(60);
            switch (i) {
                case 0:
                    this.dropItemRand(Items.IRON_HORSE_ARMOR, 1, world, par4, par5, par6);
                    break;
                case 1:
                    this.dropItemRand(Items.SHEARS, 1, world, par4, par5, par6);
                    break;
                case 2:
                    this.dropItemRand(Items.CARROT_ON_A_STICK, 1, world, par4, par5, par6);
                    break;
                case 3:
                    this.dropItemRand(Items.POISONOUS_POTATO, 1, world, par4, par5, par6);
                    break;
                case 4:
                    this.dropItemRand(Items.ITEM_FRAME, 1, world, par4, par5, par6);
                    break;
                case 5:
                    this.dropItemRand(Items.BONE, 1, world, par4, par5, par6);
                    break;
                case 6:
                    this.dropItemRand(Items.COMPASS, 1, world, par4, par5, par6);
                    break;
                case 7:
                    this.dropItemRand(Items.GLASS_BOTTLE, 1, world, par4, par5, par6);
                    break;
                case 8:
                    this.dropItemRand(Items.SADDLE, 1, world, par4, par5, par6);
                    break;
                case 9:
                    this.dropItemRand(Items.IRON_HELMET, 1, world, par4, par5, par6);
                    break;
                case 10:
                    this.dropItemRand(Items.IRON_CHESTPLATE, 1, world, par4, par5, par6);
                    break;
                case 11:
                    this.dropItemRand(Items.IRON_LEGGINGS, 1, world, par4, par5, par6);
                    break;
                case 12:
                    this.dropItemRand(Items.IRON_BOOTS, 1, world, par4, par5, par6);
                    break;
                case 13:
                    this.dropItemRand(Item.byBlock(Blocks.SAND), 1, world, par4, par5, par6);
                    break;
            }
        }
        if (bid == Blocks.GRAVEL) {
            i = world.random.nextInt(60);
            switch (i) {
                case 0:
                    this.dropItemRand(Items.FLINT, 1, world, par4, par5, par6);
                    break;
                case 1:
                    this.dropItemRand(ChaosPersists.MySalt, 1, world, par4, par5, par6);
                    break;
                case 2:
                    this.dropItemRand(Items.FLINT_AND_STEEL, 1, world, par4, par5, par6);
                    break;
                case 3:
                    this.dropItemRand(Items.SPIDER_EYE, 1, world, par4, par5, par6);
                    break;
                case 4:
                    this.dropItemRand(Items.ITEM_FRAME, 1, world, par4, par5, par6);
                    break;
                case 5:
                    this.dropItemRand(Items.FEATHER, 1, world, par4, par5, par6);
                    break;
                case 6:
                    this.dropItemRand(Items.STRING, 1, world, par4, par5, par6);
                    break;
                case 7:
                    this.dropItemRand(Items.GLASS_BOTTLE, 1, world, par4, par5, par6);
                    break;
                case 8:
                    this.dropItemRand(Items.LEAD, 1, world, par4, par5, par6);
                    break;
                case 9:
                    this.dropItemRand(Items.NAME_TAG, 1, world, par4, par5, par6);
                    break;
                case 10:
                    this.dropItemRand(Item.byBlock(Blocks.SAND), 1, world, par4, par5, par6);
                    break;
                case 11:
                    this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    break;
            }
        }
        if (bid == Blocks.DIRT) {
            i = world.random.nextInt(60);
            switch (i) {
                case 0:
                    this.dropItemRand(Items.STRING, 1, world, par4, par5, par6);
                    break;
                case 1:
                    this.dropItemRand(ChaosPersists.MySalt, 1, world, par4, par5, par6);
                    break;
                case 2:
                    this.dropItemRand(Items.SHEARS, 1, world, par4, par5, par6);
                    break;
                case 3:
                    this.dropItemRand(Items.STICK, 1, world, par4, par5, par6);
                    break;
                case 4:
                    this.dropItemRand(Items.BOWL, 1, world, par4, par5, par6);
                    break;
                case 5:
                    this.dropItemRand(Items.FLOWER_POT, 1, world, par4, par5, par6);
                    break;
                case 6:
                    this.dropItemRand(Items.OAK_SIGN, 1, world, par4, par5, par6);
                    break;
                case 7:
                    this.dropItemRand(Items.BRICK, 1, world, par4, par5, par6);
                    break;
                case 8:
                    this.dropItemRand(Items.PAPER, 1, world, par4, par5, par6);
                    break;
                case 9:
                    this.dropItemRand(Items.BONE, 1, world, par4, par5, par6);
                    break;
                case 10:
                    this.dropItemRand(Items.GLASS_BOTTLE, 1, world, par4, par5, par6);
                    break;
                case 11:
                    this.dropItemRand(Item.byBlock(Blocks.SAND), 1, world, par4, par5, par6);
                    break;
                case 12:
                    this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    break;
                case 13:
                    this.dropItemRand(Item.byBlock(Blocks.DIRT), 1, world, par4, par5, par6);
                    break;
            }
        }
        if (bid == Blocks.GRASS_BLOCK) {
            i = world.random.nextInt(60);
            switch (i) {
                case 0:
                    this.dropItemRand(Item.byBlock(Blocks.DANDELION), 1, world, par4, par5, par6);
                    break;
                case 1:
                    this.dropItemRand(Items.POPPY, 1, world, par4, par5, par6);
                    break;
                case 2:
                    this.dropItemRand(Item.byBlock(ChaosPersists.MyFlowerPinkBlock), 1, world, par4, par5, par6);
                    break;
                case 3:
                    this.dropItemRand(Item.byBlock(ChaosPersists.MyFlowerBlueBlock), 1, world, par4, par5, par6);
                    break;
                case 4:
                    this.dropItemRand(Item.byBlock(ChaosPersists.MyFlowerBlackBlock), 1, world, par4, par5, par6);
                    break;
                case 5:
                    this.dropItemRand(Item.byBlock(ChaosPersists.MyFlowerScaryBlock), 1, world, par4, par5, par6);
                    break;
                case 6:
                    this.dropItemRand(Items.WHEAT, 1, world, par4, par5, par6);
                    break;
                case 7:
                    this.dropItemRand(Items.PUMPKIN_SEEDS, 1, world, par4, par5, par6);
                    break;
                case 8:
                    this.dropItemRand(Items.MELON_SEEDS, 1, world, par4, par5, par6);
                    break;
                case 9:
                    this.dropItemRand(Items.CARROT, 1, world, par4, par5, par6);
                    break;
                case 10:
                    this.dropItemRand(Items.POTATO, 1, world, par4, par5, par6);
                    break;
                case 11:
                    this.dropItemRand(Item.byBlock(Blocks.DEAD_BUSH), 1, world, par4, par5, par6);
                    break;
                case 12:
                    this.dropItemRand(Item.byBlock(Blocks.GRAVEL), 1, world, par4, par5, par6);
                    break;
                case 13:
                    this.dropItemRand(Item.byBlock(Blocks.DIRT), 1, world, par4, par5, par6);
                    break;
                case 14:
                    this.dropItemRand(Item.byBlock(Blocks.GRASS_BLOCK), 1, world, par4, par5, par6);
                    break;
            }
        }
        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(net.minecraft.util.Hand.MAIN_HAND));
    }

    public String getMaterialName() {
        return "Unknown";
    }
}
