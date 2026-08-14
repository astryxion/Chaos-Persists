/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CrystalFurnace
 *  com.astryxion.chaospersists.GenericDungeon
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.OreGenericEgg
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.compat.minecraft.block.Block
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockBush
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockChest
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockDeadBush
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockFire
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockFlower
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockGrass
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockLeaves
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockLiquid
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockPistonBase
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockPistonMoving
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockSand
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockSlab
 *  com.astryxion.chaospersists.compat.minecraft.block.BlockTallGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  com.astryxion.chaospersists.compat.minecraft.init.Blocks
 *  com.astryxion.chaospersists.compat.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.ItemEmptyMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.SpawnerBlockEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  com.astryxion.chaospersists.compat.minecraft.world.World
 */
package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.world.ore.OreGenericEgg;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.astryxion.chaospersists.compat.forge.common.util.EnumHelper;

public class GenericDungeon {
    private final WeightedRandomChestContent[] RainbowContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MagicApple, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.CloudSharkEgg, 0, 4, 10, 25), new WeightedRandomChestContent(Items.BONE, 0, 2, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 2, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 25), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 25)};
    private final WeightedRandomChestContent[] WhiteHouseContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 6, 12, 35), new WeightedRandomChestContent(ChaosPersists.UraniumNugget, 0, 2, 6, 10), new WeightedRandomChestContent(ChaosPersists.TitaniumNugget, 0, 2, 6, 10), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 2, 6, 35), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 6, 25), new WeightedRandomChestContent(ChaosPersists.CriminalEgg, 0, 4, 10, 35), new WeightedRandomChestContent(Items.EMERALD, 0, 6, 16, 35), new WeightedRandomChestContent(Items.PORKCHOP, 0, 6, 16, 35), new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 6, 16, 35), new WeightedRandomChestContent(Items.DIAMOND, 0, 6, 16, 35), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 6, 16, 35)};
    private final WeightedRandomChestContent[] RubberDuckyContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyDeadStinkBug, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySunFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySparkFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyGreenFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyBlueFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyPinkFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyRockFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyWoodFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyGreyFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.RubberDuckyEgg, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyPeacockFeather, 0, 4, 10, 35), new WeightedRandomChestContent(Items.FEATHER, 0, 6, 16, 35)};
    private final WeightedRandomChestContent[] StinkyHouseContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyDeadStinkBug, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.StinkyEgg, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.StinkBugEgg, 0, 4, 10, 35), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.COAL, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 35)};
    private final WeightedRandomChestContent[] NightmareRookeryContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyDeadStinkBug, 0, 4, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.MyFlowerBlackBlock), 0, 4, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.MyFlowerScaryBlock), 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 4, 10, 25), new WeightedRandomChestContent(ChaosPersists.AntRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.SpiderRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 35), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] MonsterIslandContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.CreeperRepellent), 0, 4, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.KrakenRepellent), 0, 4, 10, 35), new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.PORKCHOP, 0, 3, 10, 35), new WeightedRandomChestContent(Items.BEEF, 0, 3, 10, 35), new WeightedRandomChestContent(Items.CHICKEN, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COD, 0, 3, 10, 35), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 35), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyRawBacon, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyRawPeacock, 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_LOG), 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] GreenhouseContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.GreenGoo, 0, 4, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.CreeperRepellent), 0, 4, 10, 35), new WeightedRandomChestContent(Items.FLOWER_POT, 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_SAPLING), 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_LEAVES), 0, 6, 16, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DIRT), 0, 6, 16, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_LOG), 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] CrystalBattleTowerRatContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 3, 10, 35), new WeightedRandomChestContent(Items.BEEF, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_CHICKEN, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_COD, 0, 3, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyBLT, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySalad, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] CrystalBattleTowerDungeonBeastContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 25), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 5, 15, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] CrystalBattleTowerUrchinContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyFairySword, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] CrystalBattleTowerRotatorContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyRatSword, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] CrystalBattleTowerVortexContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.CrystalCoal), 0, 6, 10, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.CrystalCoal), 0, 6, 10, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeSword, 0, 1, 1, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.MyTigersEyeBlock), 0, 4, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] RobotContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.REDSTONE, 0, 1, 10, 35), new WeightedRandomChestContent(Items.REPEATER, 0, 1, 10, 35), new WeightedRandomChestContent(Items.MINECART, 0, 1, 1, 35), new WeightedRandomChestContent(Items.FIRE_CHARGE, 0, 1, 10, 35), new WeightedRandomChestContent(Items.HOPPER_MINECART, 0, 1, 1, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.REDSTONE_BLOCK), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.RAIL), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DETECTOR_RAIL), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.STICKY_PISTON), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.PISTON), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.REDSTONE_TORCH), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.TNT), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.RAIL), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.LEVER), 0, 1, 10, 35), new WeightedRandomChestContent(ChaosPersists.AntRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.SpiderRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(Items.IRON_DOOR, 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.REDSTONE_TORCH), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_BUTTON), 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.IRON_BARS), 0, 1, 10, 35), new WeightedRandomChestContent(Items.COMPARATOR, 0, 1, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.ACTIVATOR_RAIL), 0, 1, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyRayGun, 0, 1, 1, 35)};
    private final WeightedRandomChestContent[] IncaPyramidContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.GOLDEN_SWORD, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_BOOTS, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_LEGGINGS, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_HELMET, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_CHESTPLATE, 0, 1, 1, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DANDELION), 0, 3, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.POPPY), 0, 3, 10, 35), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 3, 10, 35), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 3, 10, 35), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyCornCob, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 25), new WeightedRandomChestContent(Items.BONE, 0, 4, 10, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.GOLD_BLOCK), 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] DamselContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 35), new WeightedRandomChestContent(Items.IRON_SWORD, 0, 1, 1, 35), new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 3, 10, 35), new WeightedRandomChestContent(Items.BEEF, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_CHICKEN, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_COD, 0, 3, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyBLT, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySalad, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] EnderCastleContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.ENDER_CHEST), 0, 2, 4, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DIAMOND_BLOCK), 0, 2, 4, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DRAGON_EGG), 0, 1, 1, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.MyEnderPearlBlock), 0, 3, 6, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.MyEyeOfEnderBlock), 0, 3, 6, 35), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 25), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 4, 35), new WeightedRandomChestContent(Items.ENDER_EYE, 0, 2, 4, 35)};
    private final WeightedRandomChestContent[] BouncyContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 35), new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.POPPY), 0, 6, 16, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DANDELION), 0, 6, 16, 25), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 4, 20)};
    private final WeightedRandomChestContent[] SpitBugContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 35), new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyAmethystPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.InstantGarden, 0, 2, 4, 25), new WeightedRandomChestContent(ChaosPersists.InstantShelter, 0, 2, 4, 25)};
    private final WeightedRandomChestContent[] GraveContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ENDER_EYE, 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.POPPY), 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DANDELION), 0, 6, 16, 35), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 6, 16, 35)};
    private final WeightedRandomChestContent[] HospitalContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.ENDER_CHEST), 0, 2, 4, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DIAMOND_BLOCK), 0, 2, 4, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DRAGON_EGG), 0, 1, 1, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.MyEnderPearlBlock), 0, 3, 6, 35), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 4, 35), new WeightedRandomChestContent(Items.ENDER_EYE, 0, 2, 4, 35)};
    private final WeightedRandomChestContent[] MiniContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyCrystalApple, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.InstantGarden, 0, 2, 4, 25), new WeightedRandomChestContent(ChaosPersists.InstantShelter, 0, 2, 4, 25)};
    private final WeightedRandomChestContent[] LeafMonsterContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.FLOWER_POT, 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_SAPLING), 0, 6, 16, 35), new WeightedRandomChestContent(Items.FLOWER_POT, 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_SAPLING), 0, 6, 16, 35), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_LEAVES), 0, 6, 16, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DIRT), 0, 6, 16, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_LOG), 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] CloudSharkContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.PAPER, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyExperienceTreeSeed, 0, 1, 2, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] WaterDragonContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 25), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.IRON_BLOCK), 0, 6, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] SquidContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 15), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 5, 15, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] KnightContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.PAPER, 0, 2, 8, 20), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.OAK_PLANKS), 0, 4, 8, 20), new WeightedRandomChestContent(Items.ENDER_EYE, 0, 2, 8, 15), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 8, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] AlienWTFContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DIAMOND_BLOCK), 0, 1, 2, 15), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.MyIngotUranium, 0, 1, 2, 5), new WeightedRandomChestContent(ChaosPersists.MyIngotTitanium, 0, 1, 2, 5), new WeightedRandomChestContent((Item)ChaosPersists.UltimateHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.UltimateLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.MyRayGun, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 1, 5, 20), new WeightedRandomChestContent(ChaosPersists.MyPopcornBag, 0, 2, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] shadowContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.GLOWSTONE_DUST, 0, 2, 8, 20), new WeightedRandomChestContent(Items.NETHER_WART, 0, 4, 8, 20), new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 2, 8, 15), new WeightedRandomChestContent(Items.BLAZE_POWDER, 0, 2, 8, 15), new WeightedRandomChestContent(Items.FIRE_CHARGE, 0, 4, 8, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25), new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceTreeSeed, 0, 2, 4, 15), new WeightedRandomChestContent(ChaosPersists.MyElevator, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRatSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyRubySword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyBigHammer, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyIngotTitanium, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyIngotUranium, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.EnderReaperEgg, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] kyuubiContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.REDSTONE, 0, 2, 8, 10), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.REDSTONE_BLOCK), 0, 4, 8, 15), new WeightedRandomChestContent(Items.QUARTZ, 0, 2, 8, 15), new WeightedRandomChestContent(Items.COAL, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.KyuubiEgg, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] blazeContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 2, 8, 15), new WeightedRandomChestContent(Items.BLAZE_POWDER, 0, 2, 8, 15), new WeightedRandomChestContent(Items.FIRE_CHARGE, 0, 4, 8, 15), new WeightedRandomChestContent(Items.FLINT_AND_STEEL, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBoots, 0, 1, 1, 15), new WeightedRandomChestContent(Items.BLAZE_SPAWN_EGG, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] beeContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.SUGAR, 0, 2, 8, 15), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)Blocks.DANDELION), 0, 4, 8, 15), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 5, 15, 15), new WeightedRandomChestContent(Items.PAPER, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyFairySword, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyButterCandy, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 10), new WeightedRandomChestContent(ChaosPersists.BeeEgg, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] mantisContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyMantisClaw, 0, 1, 1, 10), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 4, 8, 15), new WeightedRandomChestContent(ChaosPersists.UraniumNugget, 0, 1, 3, 5), new WeightedRandomChestContent(ChaosPersists.TitaniumNugget, 0, 1, 3, 5), new WeightedRandomChestContent(ChaosPersists.MantisEgg, 0, 2, 4, 20), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 10), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25), new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 3, 15)};
    private final WeightedRandomChestContent[] level1ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.EMERALD, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MinersDream, 0, 4, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level2ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 2, 8, 15), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.CreeperLauncher, 0, 2, 10, 15), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyFairySword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level3ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRatSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 2, 8, 15), new WeightedRandomChestContent(Items.INK_SAC, 0, 2, 8, 15), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyAmethystPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level4ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MagicApple, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRayGun, 0, 1, 1, 15), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.CreeperRepellent), 0, 4, 10, 15), new WeightedRandomChestContent(EnumHelper.getItemFromBlock((Block)ChaosPersists.KrakenRepellent), 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.ZooKeeper, 0, 10, 16, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubySword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyThunderStaff, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level5ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15), new WeightedRandomChestContent(Items.WITHER_SKELETON_SPAWN_EGG, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnderDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(Items.SNOW_GOLEM_SPAWN_EGG, 0, 1, 4, 15), new WeightedRandomChestContent(Items.IRON_GOLEM_SPAWN_EGG, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WitherBossEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GoldCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnchantedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MOTHRAEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CryoEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CamaEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.VeloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HydroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BasilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EmperorScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaveFisherEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpyroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BaryonyxEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CockateilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GammaMetroidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KyuubiEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AlienEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AttackSquidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WaterDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CephadromeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KrakenEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LizardEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BeeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TrooperBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpitBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.StinkBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.OstrichEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GazelleEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ChipmunkEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CreepingHorrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TerribleTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CliffRacerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TriffidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LurkingTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SmallWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MediumWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LargeWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TRexEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GodzillaEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MantisEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HerculesEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.VortexEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RatEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DungeonBeastEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.FairyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WhaleEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SkateEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.IrukandjiEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot1Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot2Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot3Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot4Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot5Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CriminalEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CoinEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BoyfriendEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EasterBunnyEgg, 0, 1, 4, 5), new WeightedRandomChestContent(ChaosPersists.MolenoidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaMonsterEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaViperEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaterKillerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LeonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HammerheadEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RubberDuckyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.NastysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PointysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BrutalflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CricketEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.FrogEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AntRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.SpiderRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.JefferyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpiderDriverEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CrabEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CassowaryEgg, 0, 1, 4, 15)};
    private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 6, 12, 20), new WeightedRandomChestContent(ChaosPersists.MyButterCandy, 0, 6, 12, 20), new WeightedRandomChestContent(Items.EMERALD, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyMothScale, 0, 2, 8, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyLavaEel, 0, 2, 8, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBoots, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.EnderDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WitherBossEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GoldCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnchantedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MOTHRAEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CryoEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CamaEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.VeloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HydroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BasilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EmperorScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaveFisherEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpyroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BaryonyxEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CockateilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GammaMetroidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KyuubiEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AlienEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AttackSquidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WaterDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CephadromeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KrakenEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LizardEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BeeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TrooperBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpitBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.StinkBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.OstrichEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GazelleEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ChipmunkEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CreepingHorrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TerribleTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CliffRacerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TriffidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LurkingTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SmallWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MediumWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LargeWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CassowaryEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MolenoidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaMonsterEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaViperEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaterKillerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LeonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HammerheadEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RubberDuckyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.NastysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PointysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BrutalflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CricketEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.FrogEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.JefferyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpiderDriverEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CrabEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 3, 10, 20)};
    private int[] king = new int[]{-1, -1, 24, 3, -1, 24, 5, -1, 17, 12, -1, 16, 15, -1, 15, 14, -1, 15, 6, 3, 5, -1, 14, 6, 4, 3, -1, 14, 5, -1, 14, 5, -1, 12, 9, -1, 11, 11, -1, 8, 17, -1, 5, 23, -1, 3, 27, -1, 2, 29, -1, 1, 31, -1, 0, 33, -1, 13, 6, -1, 12, 9, -1, 11, 3, 1, 2, 1, 4, -1, 10, 3, 2, 2, 3, 2, -1, 10, 2, 4, 2, 3, 2, -1, 9, 2, 5, 2, 4, 6, -1, 9, 2, 5, 2, 6, 4, -1, 8, 2, 6, 1, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 15, 2, -1, -1, -1};
    private int[] queen = new int[]{-1, -1, 24, 3, -1, 24, 5, -1, 17, 12, -1, 16, 15, -1, 15, 14, -1, 15, 6, 3, 5, -1, 14, 6, 4, 3, -1, 14, 5, -1, 14, 5, -1, 12, 9, -1, 11, 11, -1, 8, 17, -1, 5, 23, -1, 3, 27, -1, 2, 29, -1, 1, 31, -1, 0, 33, -1, 13, 6, -1, 12, 9, -1, 11, 3, 1, 2, 1, 4, -1, 10, 3, 2, 2, 3, 2, -1, 10, 2, 4, 2, 3, 2, -1, 9, 2, 5, 2, 4, 6, -1, 9, 2, 5, 2, 6, 4, -1, 8, 2, 6, 1, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 15, 2, -1, -1, -1};
    private int[] blkcolors = new int[]{14, 1, 4, 5, 3, 11, 10, 6};

    private void setThisBlock(Level level, net.minecraft.util.RandomSource rand, int cposx, int cposy, int cposz) {
        if (rand.nextInt(2) == 1) {
            this.FastSetBlock(level, cposx, cposy, cposz, Blocks.MOSSY_COBBLESTONE);
        } else {
            this.FastSetBlock(level, cposx, cposy, cposz, Blocks.COBBLESTONE);
        }
    }

    private ChestBlockEntity getChestTileEntity(Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.entity.BlockEntity blockEntity =
                level.getBlockEntity(new BlockPos(cposx, cposy, cposz));
        if (blockEntity instanceof ChestBlockEntity chest) {
            return chest;
        }
        return null;
    }

    private void setBlockMeta(Level level, int x, int y, int z, int meta, int flags) {
        BlockPos pos = new BlockPos(x, y, z);
        level.setBlock(
                pos,
                Blocks.CHEST.defaultBlockState().setValue(net.minecraft.world.level.block.ChestBlock.FACING, chestFacingFromLegacyMeta(meta)),
                flags);
    }

    private void placeDoor(Level level, BlockPos pos, Direction facing, net.minecraft.world.level.block.Block doorBlock) {
        BlockState lower =
                ChaosPersists.prepareBlockStateForWorldGen(
                        doorBlock
                                .defaultBlockState()
                                .setValue(net.minecraft.world.level.block.DoorBlock.FACING, facing)
                                .setValue(
                                        net.minecraft.world.level.block.DoorBlock.HALF,
                                        net.minecraft.world.level.block.state.properties.DoubleBlockHalf.LOWER));
        level.setBlock(pos, lower, 2);
        level.setBlock(
                pos.above(),
                ChaosPersists.prepareBlockStateForWorldGen(
                        lower.setValue(
                                net.minecraft.world.level.block.DoorBlock.HALF,
                                net.minecraft.world.level.block.state.properties.DoubleBlockHalf.UPPER)),
                2);
    }

    private void placeLevelDoor(
            net.minecraft.world.level.Level level,
            int x,
            int y,
            int z,
            net.minecraft.core.Direction facing,
            net.minecraft.world.level.block.Block doorBlock) {
        this.placeLevelDoor(
                level,
                x,
                y,
                z,
                facing,
                doorBlock,
                net.minecraft.world.level.block.state.properties.DoorHingeSide.LEFT);
    }

    private void placeLevelDoor(
            net.minecraft.world.level.Level level,
            int x,
            int y,
            int z,
            net.minecraft.core.Direction facing,
            net.minecraft.world.level.block.Block doorBlock,
            net.minecraft.world.level.block.state.properties.DoorHingeSide hinge) {
        net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x, y, z);
        net.minecraft.world.level.block.state.BlockState lower =
                doorBlock
                        .defaultBlockState()
                        .setValue(net.minecraft.world.level.block.DoorBlock.FACING, facing)
                        .setValue(net.minecraft.world.level.block.DoorBlock.HINGE, hinge)
                        .setValue(
                                net.minecraft.world.level.block.DoorBlock.HALF,
                                net.minecraft.world.level.block.state.properties.DoubleBlockHalf.LOWER);
        level.setBlock(pos, lower, 2);
        level.setBlock(
                pos.above(),
                lower.setValue(
                        net.minecraft.world.level.block.DoorBlock.HALF,
                        net.minecraft.world.level.block.state.properties.DoubleBlockHalf.UPPER),
                2);
    }

    private SpawnerBlockEntity getSpawnerTileEntity(Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.entity.BlockEntity blockEntity =
                level.getBlockEntity(new BlockPos(cposx, cposy, cposz));
        if (blockEntity instanceof SpawnerBlockEntity spawner) {
            return spawner;
        }
        return null;
    }

    public void makeDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        int k;
        int j;
        int width = 12;
        int height = 6;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height - 1;
            for (k = 0; k < width; ++k) {
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                k = 0;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
                k = width - 1;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 0; j < height; ++j) {
                i = 0;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
                i = width - 1;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
            }
        }
        net.minecraft.core.BlockPos spawnerPos =
                new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 1, cposz + width / 2);
        level.setBlock(spawnerPos, net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity spawnerEntity = level.getBlockEntity(spawnerPos);
        if (spawnerEntity instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
            int t = rand.nextInt(12);
            if (t == 0) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "scorpion");
            }
            if (t == 1) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "alien");
            }
            if (t == 2) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "cryolophosaurus");
            }
            if (t == 3) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "gamma_metroid");
            }
            if (t == 4) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "kyuubi");
            }
            if (t == 5) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "bee");
            }
            if (t == 6) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "cloud_shark");
            }
            if (t == 7) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "lurking_terror");
            }
            if (t == 8) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "terrible_terror");
            }
            if (t == 9) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "rotator");
            }
            if (t == 10) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "rat");
            }
            if (t == 11) {
                this.setSpawnerEntityId(spawner, level, "chaospersists", "dungeon_beast");
            }
        }
        net.minecraft.core.BlockPos chestPos =
                new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 1, cposz + 1);
        level.setBlock(chestPos, net.minecraft.world.level.block.Blocks.CHEST.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity chestEntity = level.getBlockEntity(chestPos);
        if (chestEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
            WeightedRandomChestContent.generateChestContents(
                    rand, this.chestContentsList, chest, 5 + rand.nextInt(7));
        }
    }

    private void setSpawnerEntityId(
            net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner,
            net.minecraft.world.level.Level level,
            String namespace,
            String path) {
        net.minecraft.resources.ResourceLocation id =
                SpawnerFixHelper.normalizeSpawnerEntityId(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(namespace, path));
        net.minecraft.world.entity.EntityType<?> type = net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES.getValue(id);
        if (type != null) {
            spawner.setEntityId(type, level.getRandom());
        }
    }

    private void setSpawnerEntityId(
            SpawnerBlockEntity spawner, net.minecraft.resources.ResourceLocation id) {
        if (spawner == null || id == null || spawner.getLevel() == null) {
            return;
        }
        this.setSpawnerEntityId(
                spawner,
                spawner.getLevel(),
                id.getNamespace(),
                id.getPath());
    }

    private static net.minecraft.core.Direction chestFacingFromLegacyMeta(int meta) {
        return switch (meta) {
            case 2 -> net.minecraft.core.Direction.NORTH;
            case 3 -> net.minecraft.core.Direction.SOUTH;
            case 4 -> net.minecraft.core.Direction.WEST;
            case 5 -> net.minecraft.core.Direction.EAST;
            default -> net.minecraft.core.Direction.NORTH;
        };
    }

    private void placeLevelSpawner(
            net.minecraft.world.level.Level level, int x, int y, int z, String namespace, String path) {
        net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x, y, z);
        level.setBlock(pos, net.minecraft.world.level.block.Blocks.SPAWNER.defaultBlockState(), 2);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.SpawnerBlockEntity spawner) {
            this.setSpawnerEntityId(spawner, level, namespace, path);
        }
    }

    private void placeLevelSpawnerFromLegacyMobName(
            net.minecraft.world.level.Level level, int x, int y, int z, String whichmob) {
        String path = whichmob.toLowerCase(java.util.Locale.ROOT).replace(' ', '_');
        this.placeLevelSpawner(level, x, y, z, "chaospersists", path);
    }

    private void fill_shadow_chests(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource rand,
            int cposx,
            int cposy,
            int cposz,
            int width,
            int height) {
        int j = height;
        this.fillLevelChestAt(
                level, rand, cposx + 1, cposy + j, cposz + width / 2, 5, this.shadowContentsList, 3 + rand.nextInt(7));
        this.fillLevelChestAt(
                level,
                rand,
                cposx + width - 2,
                cposy + j,
                cposz + width / 2,
                4,
                this.shadowContentsList,
                3 + rand.nextInt(7));
        this.fillLevelChestAt(
                level, rand, cposx + width / 2, cposy + j, cposz + 1, 3, this.shadowContentsList, 3 + rand.nextInt(7));
        this.fillLevelChestAt(
                level,
                rand,
                cposx + width / 2,
                cposy + j,
                cposz + width - 2,
                2,
                this.shadowContentsList,
                3 + rand.nextInt(7));
    }

    private void fill_mantishive_chests(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource rand,
            int cposx,
            int cposy,
            int cposz,
            int width,
            int height) {
        int j = height;
        this.fillLevelChestAt(
                level, rand, cposx + 1, cposy + j, cposz + width / 2, 5, this.mantisContentsList, 3 + rand.nextInt(7));
        this.fillLevelChestAt(
                level,
                rand,
                cposx + width - 2,
                cposy + j,
                cposz + width / 2,
                4,
                this.mantisContentsList,
                3 + rand.nextInt(7));
        this.fillLevelChestAt(
                level, rand, cposx + width / 2, cposy + j, cposz + 1, 3, this.mantisContentsList, 3 + rand.nextInt(7));
        this.fillLevelChestAt(
                level,
                rand,
                cposx + width / 2,
                cposy + j,
                cposz + width - 2,
                2,
                this.mantisContentsList,
                3 + rand.nextInt(7));
    }

    private void fillLevelChestAt(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource rand,
            int x,
            int y,
            int z,
            int chestMeta,
            WeightedRandomChestContent[] contents,
            int rolls) {
        net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x, y, z);
        level.setBlock(
                pos,
                net.minecraft.world.level.block.Blocks.CHEST
                        .defaultBlockState()
                        .setValue(
                                net.minecraft.world.level.block.ChestBlock.FACING,
                                chestFacingFromLegacyMeta(chestMeta)),
                3);
        this.fillExistingChestAt(level, rand, x, y, z, contents, rolls);
    }

    private void fillExistingChestAt(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource rand,
            int x,
            int y,
            int z,
            WeightedRandomChestContent[] contents,
            int rolls) {
        ChestBlockEntity chest = this.getChestTileEntity(level, x, y, z);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents(rand, contents, chest, rolls);
        }
    }

    private void placeLevelDoubleChest(
            net.minecraft.world.level.Level level,
            int x1,
            int y1,
            int z1,
            int x2,
            int y2,
            int z2,
            int chestMeta) {
        Direction facing = chestFacingFromLegacyMeta(chestMeta);
        BlockPos pos1 = new BlockPos(x1, y1, z1);
        BlockPos pos2 = new BlockPos(x2, y2, z2);
        Direction from1to2 = Direction.fromDelta(x2 - x1, y2 - y1, z2 - z1);
        ChestType type1 = ChestType.SINGLE;
        ChestType type2 = ChestType.SINGLE;
        if (from1to2 != null && from1to2.getAxis().isHorizontal()) {
            if (from1to2 == facing.getClockWise()) {
                type1 = ChestType.LEFT;
                type2 = ChestType.RIGHT;
            } else if (from1to2 == facing.getCounterClockWise()) {
                type1 = ChestType.RIGHT;
                type2 = ChestType.LEFT;
            }
        }
        BlockState base = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, facing);
        int flags = 2 | 16;
        level.setBlock(pos1, base.setValue(ChestBlock.TYPE, type1), flags);
        level.setBlock(pos2, base.setValue(ChestBlock.TYPE, type2), flags);
    }

    public void FastSetBlock(Object world, int ix, int iy, int iz, Object id) {
        if (world instanceof net.minecraft.world.level.Level level) {
            ChaosPersists.setBlockFast(level, ix, iy, iz, (net.minecraft.world.level.block.Block) id, 0, 2);
        }
    }

    public void FastSetBlock(Level level, int ix, int iy, int iz, Block id) {
        this.FastSetBlock((Object) level, ix, iy, iz, id);
    }

    public void makeEnormousCastle(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level worldLevel = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = worldLevel.getRandom();
        net.minecraft.world.level.block.Block extremeTorch =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.ExtremeTorch;
        int j;
        int k;
        int i;
        int width = 28;
        int height = 16;
        int platformwidth = 11;
        int level = 0;
        if (worldLevel.isClientSide()) {
            return;
        }
        level = 1 + rand.nextInt(6);
        if (level <= 3 && rand.nextInt(3) != 1) {
            level += 3;
        }
        for (i = -20; i < width + 4; ++i) {
            for (j = 1; j < height + 10; ++j) {
                for (k = -4; k < width + 4; ++k) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.STONE);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                k = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                i = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                i = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        ChaosPersists.setBlockFast(worldLevel, cposx + 1, cposy + 1, cposz + 1, extremeTorch, 0, 2);
        ChaosPersists.setBlockFast(worldLevel, cposx + 1, cposy + 1, cposz + width - 2, extremeTorch, 0, 2);
        ChaosPersists.setBlockFast(worldLevel, cposx + width - 2, cposy + 1, cposz + 1, extremeTorch, 0, 2);
        ChaosPersists.setBlockFast(worldLevel, cposx + width - 2, cposy + 1, cposz + width - 2, extremeTorch, 0, 2);
        for (i = -4; i < width + 4; ++i) {
            for (k = -4; k < width + 4; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy, cposz + k, Blocks.STONE);
                }
                if (i != -4 && k != -4 && i != width + 3 && k != width + 3) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        SpawnerBlockEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - 3, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - 3, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "terrible_terror"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - 3, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - 3, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "terrible_terror"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + 2, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + 2, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "terrible_terror"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + 2, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + 2, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner == null) continue;
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "terrible_terror"));
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "emperor_scorpion"));
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "emperor_scorpion"));
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 4, cposz + width / 2);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "emperor_scorpion"));
        }
        j = height;
        this.buildLevel(worldLevel, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Cloud Shark", 1, -1, 5, 1, level);
        j += 10;
        if (level >= 2) {
            this.buildLevel(worldLevel, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Lurking Terror", 0, 0, 4, 2, level);
        }
        j += 10;
        if (level >= 3) {
            this.buildLevel(worldLevel, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 4, "Rotator", 1, 1, 4, 3, level);
        }
        j += 9;
        if (level >= 4) {
            this.buildLevel(worldLevel, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 3, "Bee", 0, 0, 4, 4, level);
        }
        j += 9;
        if (level >= 5) {
            this.buildLevel(worldLevel, cposx + 3, cposy + j, cposz + 3, width - 6, 8, 3, "Mantis", 1, 1, 4, 5, level);
        }
        j += 8;
        if (level >= 6) {
            this.buildLevel(worldLevel, cposx + 3, cposy + j, cposz + 3, width - 6, 16, 3, "Mothra", 0, 0, 3, 6, level);
        }
        j += 16;
        for (i = 0; i < platformwidth; ++i) {
            j = height;
            for (k = - platformwidth / 2; k <= platformwidth / 2; ++k) {
                this.FastSetBlock(worldLevel, cposx + i - 20, cposy + j, cposz + k + width / 2, Blocks.QUARTZ_BLOCK);
                if (i != 0 && i != platformwidth - 1 && k != - platformwidth / 2 && k != platformwidth / 2 || i == 0 && k >= -1 && k <= 1) continue;
                this.FastSetBlock(worldLevel, cposx + i - 20, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        for (i = -10; i <= -3; ++i) {
            j = height;
            for (k = -2; k < 3; ++k) {
                if (i == -3 || i == -10) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k + width / 2, Blocks.QUARTZ_BLOCK);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = -21;
        for (j = height; j >= 0; --j) {
            for (k = -2; k < 3; ++k) {
                for (int t = 0; t < 6; ++t) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + t + 1, cposz + k + width / 2, Blocks.AIR);
                }
                if (j == 0) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k + width / 2, Blocks.QUARTZ_BLOCK);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
            --i;
        }
        if (level >= 6) {
            int span = width * 3;
            for (int tries = 0; tries < 100; ++tries) {
                j = -1;
                i = rand.nextInt(span);
                k = rand.nextInt(span);
                if (i >= span / 4 && i <= span * 3 / 4 && k >= span / 4 && k <= span * 3 / 4) continue;
                worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + (i -= span / 2) + width / 2, cposy + j, cposz + (k -= span / 2) + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + i + width / 2, cposy + j, cposz + k + width / 2);
                if (tileentitymobspawner == null) continue;
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
        }
    }

    public void buildLevel(Object worldObj, int cposx, int cposy, int cposz, int width, int height, int pw, String critter, int stepside, int stepoff, int holelen, int decor, int level) {
        net.minecraft.world.level.Level worldLevel = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = worldLevel.getRandom();
        int j;
        int i;
        int k;
        for (i = - pw; i < width + pw; ++i) {
            for (j = 1; j < height; ++j) {
                for (k = - pw; k < width + pw; ++k) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
                k = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                Block blk = Blocks.BEDROCK;
                if (k == 0 || k == width - 1) {
                    blk = Blocks.GOLD_BLOCK;
                }
                i = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, blk);
                i = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = - pw; i < width + pw; ++i) {
            for (k = - pw; k < width + pw; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy, cposz + k, Blocks.STONE);
                }
                if (i != - pw && k != - pw && i != width + (pw - 1) && k != width + (pw - 1)) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = - height / 2;
        i += width / 2;
        for (j = 1; j < height; ++j) {
            if (stepside != 0) {
                k = -1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.STONE);
            } else {
                k = width;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.STONE);
            }
            ++i;
        }
        if (stepoff >= 0) {
            if (stepside == 0) {
                k = -1;
                k -= stepoff;
            } else {
                k = width;
                k += stepoff;
            }
            i = width / 2;
            j = 0;
            for (int l = 0; l < holelen; ++l) {
                this.FastSetBlock(worldLevel, cposx + i + l, cposy + j, cposz + k, Blocks.AIR);
            }
        }
        SpawnerBlockEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner == null) continue;
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
        }
        this.addLevelDecorations(worldLevel, cposx, cposy, cposz, width, height, decor, level);
    }

    public void addLevelDecorations(Level worldLevel, int cposx, int cposy, int cposz, int width, int height, int decor, int difficulty) {
        int j;
        SpawnerBlockEntity tileentitymobspawner = null;
        int reward = 1;
        String critter = "Alosaurus";
        if (decor == 6) {
            this.FastSetBlock(worldLevel, cposx, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx + width / 2, cposy + height, cposz + width / 2, Blocks.AIR);
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            for (int i = 1; i < width - 1; ++i) {
                for (j = 1; j < 5; ++j) {
                    for (int k = 1; k < width - 1; ++k) {
                        this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.DIRT);
                    }
                }
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 4, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
            for (j = 0; j < 10; ++j) {
                this.FastSetBlock(worldLevel, cposx + 1, cposy + j, cposz + 1, Blocks.AIR);
            }
            this.fill_chests(worldLevel, cposx, cposy + 4, cposz, width, height, decor, reward);
        }
        if (decor == 5) {
            if (difficulty == 5) {
                critter = "Alosaurus";
                reward = 1;
            }
            if (difficulty == 6) {
                critter = "T. Rex";
                reward = 2;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 4) {
            if (difficulty == 4) {
                critter = "Alosaurus";
                reward = 1;
            }
            if (difficulty == 5) {
                critter = "T. Rex";
                reward = 2;
            }
            if (difficulty == 6) {
                critter = "Basilisk";
                reward = 3;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 3) {
            if (difficulty == 3) {
                critter = "Alosaurus";
                reward = 1;
            }
            if (difficulty == 4) {
                critter = "T. Rex";
                reward = 2;
            }
            if (difficulty == 5) {
                critter = "Basilisk";
                reward = 3;
            }
            if (difficulty == 6) {
                critter = "Hercules Beetle";
                reward = 4;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 2) {
            if (difficulty == 2) {
                critter = "Alosaurus";
                reward = 1;
            }
            if (difficulty == 3) {
                critter = "T. Rex";
                reward = 2;
            }
            if (difficulty == 4) {
                critter = "Basilisk";
                reward = 3;
            }
            if (difficulty == 5) {
                critter = "Hercules Beetle";
                reward = 4;
            }
            if (difficulty == 6) {
                critter = "Jumpy Bug";
                reward = 5;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 1) {
            if (difficulty == 1) {
                critter = "Alosaurus";
            }
            if (difficulty == 2) {
                critter = "T. Rex";
            }
            if (difficulty == 3) {
                critter = "Basilisk";
            }
            if (difficulty == 4) {
                critter = "Hercules Beetle";
            }
            if (difficulty == 5) {
                critter = "Jumpy Bug";
            }
            if (difficulty == 6) {
                critter = "Hammerhead";
            }
            reward = difficulty;
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
    }

    private void fill_chests(Object worldObj, int cposx, int cposy, int cposz, int width, int height, int decor, int reward) {
        net.minecraft.world.level.Level worldLevel = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = worldLevel.getRandom();
        ChestBlockEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.level1ContentsList;
        if (reward == 2) {
            chestContents = this.level2ContentsList;
        }
        if (reward == 3) {
            chestContents = this.level3ContentsList;
        }
        if (reward == 4) {
            chestContents = this.level4ContentsList;
        }
        if (reward == 5) {
            chestContents = this.level5ContentsList;
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + 1, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + 1, cposy + 1, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + 1, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.ThePrinceEgg, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width - 2, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + width - 2, cposy + 1, cposz + width / 2, 4, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + width - 2, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.RoyalHelmet, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.RoyalBody, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + width / 2, cposy + 1, cposz + 1, 3, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + width / 2, cposy + 1, cposz + 1);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.RoyalLegs, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.RoyalBoots, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 1, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + width / 2, cposy + 1, cposz + width - 2, 2, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + width / 2, cposy + 1, cposz + width - 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.MyRoyal, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
    }

    public void makeRotatorStation(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block crystalStone =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalStone;
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx, cposy + 4, cposz),
                crystalStone.defaultBlockState(),
                2);
        this.placeLevelSpawner(level, cposx, cposy + 5, cposz, "chaospersists", "rotator");
        this.placeLevelSpawner(level, cposx, cposy + 6, cposz, "chaospersists", "rotator");
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx, cposy + 7, cposz),
                crystalStone.defaultBlockState(),
                2);
        net.minecraft.core.BlockPos chestPos = new net.minecraft.core.BlockPos(cposx, cposy + 8, cposz);
        level.setBlock(
                chestPos,
                net.minecraft.world.level.block.Blocks.CHEST
                        .defaultBlockState()
                        .setValue(
                                net.minecraft.world.level.block.ChestBlock.FACING,
                                chestFacingFromLegacyMeta(2)),
                2);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(chestPos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
            chest.setItem(
                    1,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.RotatorEgg, 1 + rand.nextInt(5)));
            chest.setItem(
                    2,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.CrystalCoal, 4 + rand.nextInt(16)));
            chest.setItem(
                    3,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.CrystalCoal, 4 + rand.nextInt(16)));
        }
    }

    public void makeBeeHive(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        int k;
        int j;
        int width = 10;
        int height = 30;
        if (level.isClientSide()) {
            return;
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < 5; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(level, cposx + i, cposy - j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(level, cposx + i, cposy - j, cposz + k, net.minecraft.world.level.block.Blocks.COAL_ORE);
            }
        }
        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.COAL_ORE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 1; j < height; ++j) {
                    if (k == 0 || i == 0 || k == width - 1 || i == width - 1) {
                        blk = net.minecraft.world.level.block.Blocks.COAL_ORE;
                        if ((j & 1) == 1) {
                            blk = net.minecraft.world.level.block.Blocks.GOLD_ORE;
                        }
                        this.FastSetBlock(level, cposx + i, cposy - j, cposz + k, blk);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy - j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (j = 0; j < 4; ++j) {
            this.placeLevelSpawner(
                    level,
                    cposx + width / 2,
                    cposy - 2 - j * (height / 4),
                    cposz + width / 2,
                    "chaospersists",
                    "bee");
        }
        this.fill_beehive_chests(level, level.getRandom(), cposx, cposy, cposz, width, height);
    }

    private void fill_beehive_chests(
            net.minecraft.world.level.Level level,
            net.minecraft.util.RandomSource rand,
            int cposx,
            int cposy,
            int cposz,
            int width,
            int height) {
        for (int j = 2; j < height - 1; j += 2) {
            this.fillLevelChestAt(
                    level, rand, cposx + 1, cposy - j, cposz + width / 2, 5, this.beeContentsList, 1 + rand.nextInt(5));
            this.fillLevelChestAt(
                    level,
                    rand,
                    cposx + width - 2,
                    cposy - j,
                    cposz + width / 2,
                    4,
                    this.beeContentsList,
                    1 + rand.nextInt(5));
            this.fillLevelChestAt(
                    level, rand, cposx + width / 2, cposy - j, cposz + 1, 3, this.beeContentsList, 1 + rand.nextInt(5));
            this.fillLevelChestAt(
                    level,
                    rand,
                    cposx + width / 2,
                    cposy - j,
                    cposz + width - 2,
                    2,
                    this.beeContentsList,
                    1 + rand.nextInt(5));
        }
    }

    public void makeHauntedHouse(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int k;
        int j;
        int i;
        int deltax = 1;
        int deltaz = 0;
        int stuffdir = 2;
        int length = 3;
        int width = 3;
        int height = 3;
        int x = cposx;
        int z = cposz;
        int y = cposy;
        if (level.isClientSide()) {
            return;
        }
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.core.Direction facing = chestFacingFromLegacyMeta(stuffdir);
        for (i = -width; i <= width; ++i) {
            for (j = -length; j <= length; ++j) {
                for (k = 0; k <= height + 1; ++k) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x + i, y + k, z + j);
                    if (k == height + 1) {
                        level.setBlock(pos, net.minecraft.world.level.block.Blocks.OAK_PLANKS.defaultBlockState(), 3);
                        continue;
                    }
                    if (k == 0) {
                        level.setBlock(pos, net.minecraft.world.level.block.Blocks.COBBLESTONE.defaultBlockState(), 3);
                        continue;
                    }
                    if (i == width || j == length || i == -width || j == -length) {
                        if (k == height) {
                            level.setBlock(pos, net.minecraft.world.level.block.Blocks.GLASS.defaultBlockState(), 3);
                            continue;
                        }
                        if ((k == 1 || k == 2) && i == deltax * width && j == deltaz * length) {
                            level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                            continue;
                        }
                        level.setBlock(pos, net.minecraft.world.level.block.Blocks.OAK_PLANKS.defaultBlockState(), 3);
                        continue;
                    }
                    level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
        i = 2;
        k = 1;
        j = length - 1;
        net.minecraft.core.BlockPos furnacePos =
                new net.minecraft.core.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
        level.setBlock(
                furnacePos,
                net.minecraft.world.level.block.Blocks.FURNACE
                        .defaultBlockState()
                        .setValue(net.minecraft.world.level.block.FurnaceBlock.FACING, facing),
                3);
        i = 1;
        level.setBlock(
                new net.minecraft.core.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax),
                net.minecraft.world.level.block.Blocks.CRAFTING_TABLE.defaultBlockState(),
                3);
        i = 0;
        net.minecraft.core.BlockPos chestPos =
                new net.minecraft.core.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
        level.setBlock(
                chestPos,
                net.minecraft.world.level.block.Blocks.CHEST
                        .defaultBlockState()
                        .setValue(net.minecraft.world.level.block.ChestBlock.FACING, facing),
                3);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(chestPos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
            if (rand.nextInt(2) == 0) {
                chest.setItem(0, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COMPASS));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(1, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.MAP));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(2, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COOKED_PORKCHOP, 8));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(3, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.TORCH, 32));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(4, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COAL, 16));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(5, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.RED_BED));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(6, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.RED_BED));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(7, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.OAK_DOOR));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(8, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_PICKAXE));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(9, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_SWORD));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(10, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_AXE));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(11, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.BUCKET));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        12,
                        new net.minecraft.world.item.ItemStack(
                                ChaosPersists.MyOreSaltBlock, 4));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        13,
                        new net.minecraft.world.item.ItemStack(net.minecraft.world.level.block.Blocks.CHEST.asItem()));
            }
        }
        this.placeLevelSpawner(level, cposx, cposy + 1, cposz, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx, cposy + 2, cposz, "chaospersists", "ghost");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz, "chaospersists", "ghost_pumpkin_skelly");
    }

    public void makeMantisHive(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        int k;
        int j;
        int width = 13;
        if (level.isClientSide()) {
            return;
        }
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < width; ++i) {
            for (j = 0; j < 20; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        int yoff = 0;
        int zoff = 0;
        int xoff = 0;
        while (width > 0) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < width; ++k) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.GOLD_ORE;
                        if ((yoff & 1) != 0) {
                            blk = net.minecraft.world.level.block.Blocks.EMERALD_ORE;
                        }
                        this.FastSetBlock(level, cposx + i + xoff, cposy - yoff, cposz + k + zoff, blk);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i + xoff, cposy - yoff, cposz + k + zoff, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
            if (width <= 11 && width >= 7) {
                this.fill_mantishive_chests(level, rand, cposx + xoff, cposy - yoff, cposz + zoff, width, 0);
            }
            ++xoff;
            ++zoff;
            ++yoff;
            width -= 2;
        }
        --xoff;
        --zoff;
        --yoff;
        for (j = 4; j < 7; ++j) {
            this.placeLevelSpawner(level, cposx + xoff, cposy + j - yoff, cposz + yoff, "chaospersists", "mantis");
        }
    }

    public void makeKyuubiDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        int k;
        int j;
        int width = 5;
        int height = 5;
        int depth = 20;
        int length = 12;
        int rwidth = 30;
        int rheight = 18;
        int rlength = 20;
        if (level.isClientSide()) {
            return;
        }
        net.minecraft.world.level.block.Block blk;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < 5; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(level, cposx + i, cposy - j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        j = height;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.SANDSTONE);
            }
        }
        this.FastSetBlock(level, cposx + width / 2, cposy + j, cposz + width / 2, net.minecraft.world.level.block.Blocks.AIR);
        blk = net.minecraft.world.level.block.Blocks.SANDSTONE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        blk = net.minecraft.world.level.block.Blocks.STONE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = -1; j > -depth; --j) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (i = 1; i < width - 1; ++i) {
            for (k = 1; k < width - 1; ++k) {
                for (j = -depth; j > -depth + 2; --j) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.WATER);
                }
            }
        }
        for (i = 1; i < width - 1; ++i) {
            for (k = 1; k < width - 1; ++k) {
                j = -depth + 2;
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
            }
        }
        int x = cposx + width + length - 2;
        int z = cposz - rwidth / 2;
        int y = cposy - depth;
        blk = net.minecraft.world.level.block.Blocks.NETHERRACK;
        for (i = 0; i < rlength; ++i) {
            for (k = 0; k < rwidth; ++k) {
                for (j = 0; j < rheight; ++j) {
                    if (k == 0 || k == rwidth - 1 || j == 0 || j == rheight - 1 || i == 0 || i == rlength - 1) {
                        this.FastSetBlock(level, x + i, y + j, z + k, blk);
                        continue;
                    }
                    this.FastSetBlock(level, x + i, y + j, z + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        x = cposx + width - 1;
        z = cposz;
        y = cposy - depth;
        for (i = 0; i < length; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < width; ++j) {
                    if (k == 0 || k == width - 1 || j == 0 || j == width - 1) {
                        blk = net.minecraft.world.level.block.Blocks.STONE;
                        if (j > 0 && j < width - 1) {
                            blk = net.minecraft.world.level.block.Blocks.LAVA;
                        }
                        this.FastSetBlock(level, x + i, y + j, z + k, blk);
                        continue;
                    }
                    this.FastSetBlock(level, x + i, y + j, z + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        x = cposx + width + length - 2;
        z = cposz - rwidth / 2;
        y = cposy - depth;
        this.addlavasquare(level, x + 2, ++y, z + 2);
        this.addlavasquare(level, x + 4, y, z + 6);
        this.addlavasquare(level, x + 12, y, z + 10);
        this.addlavasquare(level, x + 6, y, z + 15);
        this.addlavasquare(level, x + 3, y, z + 22);
        this.addkyuubi(level, x + rlength / 4, y, z + rwidth * 3 / 4 - 3);
        this.addblaze(level, x + rlength * 2 / 3 - 3, y, z + rwidth / 4 - 2);
        this.FastSetBlock(level, x + 7, y, z + 1, net.minecraft.world.level.block.Blocks.FIRE);
        this.FastSetBlock(level, x + 5, y, z + 9, net.minecraft.world.level.block.Blocks.FIRE);
        this.FastSetBlock(level, x + 2, y, z + 12, net.minecraft.world.level.block.Blocks.FIRE);
        this.FastSetBlock(level, x + 16, y, z + 18, net.minecraft.world.level.block.Blocks.FIRE);
        this.FastSetBlock(level, x + 2, y, z + 27, net.minecraft.world.level.block.Blocks.FIRE);
        this.FastSetBlock(level, x + 18, y, z + 28, net.minecraft.world.level.block.Blocks.FIRE);
    }

    private void addlavasquare(net.minecraft.world.level.Level level, int x, int y, int z) {
        this.FastSetBlock(level, x - 1, y, z, net.minecraft.world.level.block.Blocks.NETHERRACK);
        this.FastSetBlock(level, x + 1, y, z, net.minecraft.world.level.block.Blocks.NETHERRACK);
        this.FastSetBlock(level, x, y, z + 1, net.minecraft.world.level.block.Blocks.NETHERRACK);
        this.FastSetBlock(level, x, y, z - 1, net.minecraft.world.level.block.Blocks.NETHERRACK);
        this.FastSetBlock(level, x, y, z, net.minecraft.world.level.block.Blocks.LAVA);
    }

    private void addkyuubi(net.minecraft.world.level.Level level, int x, int y, int z) {
        int i;
        int k;
        int width = 9;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                    this.FastSetBlock(level, x + i, y, z + k, net.minecraft.world.level.block.Blocks.NETHER_BRICKS);
                    continue;
                }
                this.FastSetBlock(level, x + i, y, z + k, net.minecraft.world.level.block.Blocks.LAVA);
            }
        }
        width = 7;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                    this.FastSetBlock(level, x + i + 1, y + 1, z + k + 1, net.minecraft.world.level.block.Blocks.NETHER_BRICKS);
                    continue;
                }
                this.FastSetBlock(level, x + i + 1, y + 1, z + k + 1, net.minecraft.world.level.block.Blocks.LAVA);
            }
        }
        for (int j = 0; j < 3; ++j) {
            this.placeLevelSpawner(level, x + 4, y + j + 2, z + 4, "chaospersists", "kyuubi");
        }
        this.fillLevelChestAt(level, rand, x + 4, y + 5, z + 4, 2, this.kyuubiContentsList, 7 + rand.nextInt(7));
    }

    private void addblaze(net.minecraft.world.level.Level level, int x, int y, int z) {
        int j;
        int k;
        int i;
        int width = 7;
        int height = 4;
        int xx = x;
        int yy = y;
        int zz = z;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    this.FastSetBlock(level, xx + i, yy + j, zz + k, net.minecraft.world.level.block.Blocks.OBSIDIAN);
                }
            }
        }
        ++xx;
        yy += height;
        ++zz;
        width = 5;
        height = 1;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    this.FastSetBlock(level, xx + i, yy + j, zz + k, net.minecraft.world.level.block.Blocks.OBSIDIAN);
                }
            }
        }
        ++xx;
        yy += height;
        ++zz;
        width = 3;
        height = 6;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    this.FastSetBlock(level, xx + i, yy + j, zz + k, net.minecraft.world.level.block.Blocks.OBSIDIAN);
                }
            }
        }
        ++xx;
        yy += height;
        ++zz;
        width = 1;
        height = 5;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    this.FastSetBlock(level, xx + i, yy + j, zz + k, net.minecraft.world.level.block.Blocks.OBSIDIAN);
                }
            }
        }
        for (j = 0; j < 2; ++j) {
            this.placeLevelSpawner(level, xx - 1, yy + height + j - 3, zz, "minecraft", "blaze");
            this.placeLevelSpawner(level, xx + 1, yy + height + j - 3, zz, "minecraft", "blaze");
            this.placeLevelSpawner(level, xx, yy + height + j - 3, zz - 1, "minecraft", "blaze");
            this.placeLevelSpawner(level, xx, yy + height + j - 3, zz + 1, "minecraft", "blaze");
        }
        this.fillLevelChestAt(level, rand, x, y + 4, z + 3, 4, this.blazeContentsList, 4 + rand.nextInt(5));
        this.fillLevelChestAt(level, rand, x + 3, y + 4, z, 2, this.blazeContentsList, 3 + rand.nextInt(5));
        this.fillLevelChestAt(level, rand, x + 3, y + 4, z + 6, 3, this.blazeContentsList, 5 + rand.nextInt(5));
        this.fillLevelChestAt(level, rand, x + 6, y + 4, z + 3, 5, this.blazeContentsList, 6 + rand.nextInt(5));
    }

    public void makeSmallBeeHive(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int blk;
        int j;
        int k;
        int i;
        int width = 7;
        int height = 21;
        net.minecraft.util.RandomSource rand = level.getRandom();
        if (level.isClientSide()) {
            return;
        }
        for (i = -3; i < width + 3; ++i) {
            for (j = height * 2 / 3; j < height; ++j) {
                for (k = -3; k < width + 3; ++k) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                j = height * 2 / 3;
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.SPONGE);
                blk = rand.nextInt(height / 3);
                blk *= 2;
                blk -= Math.abs(i - width / 2);
                if ((blk -= Math.abs(k - width / 2)) < 1) {
                    blk = 1;
                }
                if (i == width / 2 && k == width / 2) {
                    blk = height * 2 / 3;
                }
                for (j = 0; j < blk; ++j) {
                    this.FastSetBlock(
                            level,
                            cposx + i,
                            cposy + height * 2 / 3 - j,
                            cposz + k,
                            net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE);
                }
            }
        }
        j = height * 2 / 3;
        for (blk = 0; blk < height / 6; ++blk) {
            ++j;
            for (i = 0; i < width; ++i) {
                for (k = 0; k < width; ++k) {
                    if (k == 0 || i == 0 || k == width - 1 || i == width - 1) {
                        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.SPONGE);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
            ++j;
            for (i = -1; i < width + 1; ++i) {
                for (k = -1; k < width + 1; ++k) {
                    if (k == -1 || i == -1 || k == width || i == width) {
                        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.SPONGE);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        ++j;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.SPONGE);
            }
        }
        j = height * 2 / 3 + 1;
        for (i = -1; i < 1; ++i) {
            for (k = 2; k < 4; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                this.FastSetBlock(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                this.FastSetBlock(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
            }
        }
        for (blk = 0; blk < 3; ++blk) {
            this.placeLevelSpawner(level, cposx + 1, cposy + blk + j, cposz + 1, "chaospersists", "bee");
        }
        this.fillLevelChestAt(
                level,
                rand,
                cposx + width / 2,
                cposy + j,
                cposz + width / 2,
                5,
                this.beeContentsList,
                7 + rand.nextInt(5));
    }

    public void makeShadowDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int k;
        net.minecraft.world.level.block.Block blk;
        int i;
        int width;
        int totalwidth = 19;
        String whichmob = null;
        if (level.isClientSide()) {
            return;
        }
        int yoff = 0;
        int zoff = 0;
        int xoff = 0;
        for (width = totalwidth; width > 0; width -= 2) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < width; ++k) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                        if ((yoff & 1) != 0) {
                            blk = net.minecraft.world.level.block.Blocks.BEDROCK;
                        }
                        if (k >= width / 2 - 1 && k <= width / 2 + 1 || i >= width / 2 - 1 && i <= width / 2 + 1) {
                            blk = net.minecraft.world.level.block.Blocks.SOUL_SAND;
                        }
                        this.FastSetBlock(level, cposx + i + xoff, cposy - yoff, cposz + k + zoff, blk);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i + xoff, cposy - yoff, cposz + k + zoff, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
            if (width <= 15 && width >= 9) {
                if ((yoff & 1) != 0) {
                    this.fill_shadow_chests(level, level.getRandom(), cposx + xoff, cposy - yoff, cposz + zoff, width, 0);
                    whichmob = "Ender Reaper";
                } else {
                    whichmob = "Nightmare";
                }
                this.placeLevelSpawnerFromLegacyMobName(
                        level, cposx + xoff + 1, cposy - yoff, cposz + zoff + 1, whichmob);
                this.placeLevelSpawnerFromLegacyMobName(
                        level, cposx + xoff + width - 2, cposy - yoff, cposz + zoff + 1, whichmob);
                this.placeLevelSpawnerFromLegacyMobName(
                        level, cposx + xoff + 1, cposy - yoff, cposz + zoff + width - 2, whichmob);
                this.placeLevelSpawnerFromLegacyMobName(
                        level, cposx + xoff + width - 2, cposy - yoff, cposz + zoff + width - 2, whichmob);
            }
            ++xoff;
            ++zoff;
            ++yoff;
        }
        yoff = 0;
        zoff = 0;
        xoff = 0;
        for (width = totalwidth; width > 0; width -= 2) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < width; ++k) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                        if ((yoff & 1) != 0) {
                            blk = net.minecraft.world.level.block.Blocks.BEDROCK;
                        }
                        this.FastSetBlock(level, cposx + i + xoff, cposy + yoff, cposz + k + zoff, blk);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i + xoff, cposy + yoff, cposz + k + zoff, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
            ++xoff;
            ++zoff;
            ++yoff;
        }
    }

    public void makeAlienWTFDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int j;
        net.minecraft.world.level.block.Block blk;
        int k;
        int i;
        int width = 5;
        int height = 5;
        int xwidth = 3;
        int zwidth = 6;
        int depth = 20;
        cposy -= depth - 3;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < width; ++k) {
                    if (i == 0 || j == 0 || k == 0 || i == width - 1 || j == height - 1 || k == width - 1) {
                        this.FastSetBlock(level, cposx + i - 2, cposy + j, cposz + k - 2, net.minecraft.world.level.block.Blocks.LAPIS_ORE);
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i - 2, cposy + j, cposz + k - 2, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        int s = 0;
        --cposx;
        --cposz;
        for (j = 3; j < depth; ++j) {
            for (i = 0; i < 4; ++i) {
                for (k = 0; k < 4; ++k) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == 3 || k == 3) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, 0, 2);
                }
            }
            switch (s) {
                case 0: {
                    ChaosPersists.setBlockFast(level, cposx + 1, cposy + j, cposz + 1, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
                    break;
                }
                case 1: {
                    ChaosPersists.setBlockFast(level, cposx + 2, cposy + j, cposz + 1, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
                    break;
                }
                case 2: {
                    ChaosPersists.setBlockFast(level, cposx + 2, cposy + j, cposz + 2, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
                    break;
                }
                default: {
                    ChaosPersists.setBlockFast(level, cposx + 1, cposy + j, cposz + 2, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
                }
            }
            if (++s <= 3) continue;
            s = 0;
        }
        this.makePart(level, ++cposx, cposy, ++cposz + 7, 9, 5, 1, 1, 1);
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    if (i == 0 || i == xwidth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 2, blk);
                }
            }
        }
        this.makePart(level, cposx + 7, cposy, cposz, 11, 6, 1, -1, 2);
        xwidth = 6;
        zwidth = 3;
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    if (k == 0 || k == zwidth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(level, cposx + i + 2, cposy + j, cposz - k, blk);
                }
            }
        }
        this.makePart(level, cposx - 7, cposy, cposz, 13, 7, -1, 1, 3);
        xwidth = 6;
        zwidth = 3;
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    if (k == 0 || k == zwidth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(level, cposx - i - 2, cposy + j, cposz + k, blk);
                }
            }
        }
        this.makePart(level, cposx, cposy, cposz - 7, 15, 8, -1, -1, 4);
        xwidth = 3;
        zwidth = 6;
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    if (i == 0 || i == xwidth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(level, cposx - i, cposy + j, cposz - k - 2, blk);
                }
            }
        }
    }

    private void placeAlienOrGammaSpawner(
            net.minecraft.world.level.Level level, net.minecraft.util.RandomSource rand, int x, int y, int z) {
        String path = rand.nextInt(2) == 0 ? "alien" : "gamma_metroid";
        this.placeLevelSpawner(level, x, y, z, "chaospersists", path);
    }

    private void makePart(
            net.minecraft.world.level.Level level,
            int cposx,
            int cposy,
            int cposz,
            int width,
            int height,
            int dx,
            int dz,
            int difficulty) {
        int i;
        int j;
        int k;
        net.minecraft.world.level.block.Block blk;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                blk = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                if (i == width / 2 || k == width / 2) {
                    blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                }
                this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, blk);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, blk);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                k = 0;
                this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, blk);
                k = width - 1;
                this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, blk);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 0; j < height; ++j) {
                i = 0;
                this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, net.minecraft.world.level.block.Blocks.OBSIDIAN);
                i = width - 1;
                this.FastSetBlock(level, cposx + i * dx, cposy + j, cposz + k * dz, net.minecraft.world.level.block.Blocks.OBSIDIAN);
            }
        }
        for (j = 0; j < difficulty; ++j) {
            this.placeAlienOrGammaSpawner(level, rand, cposx + dx * width / 2, cposy + j + 2, cposz + dz * width / 2);
            this.placeAlienOrGammaSpawner(
                    level, rand, cposx + dx * width / 2 + dx, cposy + j + 2, cposz + dz * width / 2 + dz);
        }
        this.fillLevelChestAt(
                level, rand, cposx + width * dx / 2, cposy + 1, cposz + dz, 2, this.AlienWTFContentsList, 3 + rand.nextInt(5));
        if (difficulty > 1) {
            this.fillLevelChestAt(
                    level,
                    rand,
                    cposx + width * dx / 2,
                    cposy + 1,
                    cposz + (width - 2) * dz,
                    2,
                    this.AlienWTFContentsList,
                    3 + rand.nextInt(5));
        }
        if (difficulty > 2) {
            this.fillLevelChestAt(
                    level,
                    rand,
                    cposx + dx,
                    cposy + 1,
                    cposz + width / 2 * dz,
                    2,
                    this.AlienWTFContentsList,
                    3 + rand.nextInt(5));
        }
        if (difficulty > 3) {
            this.fillLevelChestAt(
                    level,
                    rand,
                    cposx + (width - 2) * dx,
                    cposy + 1,
                    cposz + width / 2 * dz,
                    2,
                    this.AlienWTFContentsList,
                    3 + rand.nextInt(5));
        }
    }

    public void makeEnderKnightDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.world.level.block.Block blk;
        int k;
        int j;
        int height = 6;
        int zwidth = 5;
        for (int i = 0; i < 4; ++i) {
            for (k = 0; k < 5; ++k) {
                for (j = 0; j < 5; ++j) {
                    this.FastSetBlock(level, cposx, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
            ++cposx;
        }
        zwidth = 5;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                if (k == 2 && j >= 1 && j <= 3) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                }
                this.FastSetBlock(level, cposx, cposy + j, cposz + k, blk);
            }
        }
        ++cposx;
        --cposz;
        zwidth = 7;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (j == 0 || j == height - 1) {
                    blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                }
                if (j == 0 && k > 0 && k < zwidth - 1) {
                    blk = net.minecraft.world.level.block.Blocks.END_STONE;
                }
                if (k == 0 || k == zwidth - 1) {
                    blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                }
                this.FastSetBlock(level, cposx, cposy + j, cposz + k, blk);
            }
            if (k != 1 && k != 2 && k != zwidth - 3 && k != zwidth - 2) continue;
            this.makeShelves(level, cposx, cposy + 1, cposz + k);
        }
        --cposz;
        for (int m = 0; m < 5; ++m) {
            ++cposx;
            zwidth = 9;
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < height; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0 || j == height - 1) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (j == 0 && k > 0 && k < zwidth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.END_STONE;
                    }
                    if (k == 0 || k == zwidth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    this.FastSetBlock(level, cposx, cposy + j, cposz + k, blk);
                }
                if (k == 1 || k == 2 || k == zwidth - 3 || k == zwidth - 2) {
                    this.makeShelves(level, cposx, cposy + 1, cposz + k);
                }
                if (m != 2 || k != 4) continue;
                this.placeLevelSpawner(level, cposx, cposy + 2, cposz + k, "chaospersists", "ender_knight");
                this.placeLevelSpawner(level, cposx, cposy + 3, cposz + k, "chaospersists", "ender_knight");
            }
        }
        ++cposz;
        ++cposx;
        zwidth = 7;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (j == 0 || j == height - 1) {
                    blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                }
                if (j == 0 && k > 0 && k < zwidth - 1) {
                    blk = net.minecraft.world.level.block.Blocks.END_STONE;
                }
                if (k == 0 || k == zwidth - 1) {
                    blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                }
                this.FastSetBlock(level, cposx, cposy + j, cposz + k, blk);
            }
            if (k != 1 && k != 2 && k != zwidth - 3 && k != zwidth - 2) continue;
            this.makeShelves(level, cposx, cposy + 1, cposz + k);
        }
        ++cposz;
        ++cposx;
        zwidth = 5;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                this.FastSetBlock(level, cposx, cposy + j, cposz + k, blk);
            }
        }
    }

    private void makeShelves(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int j;
        int k;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i = rand.nextInt(4);
        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.AIR;
        if (i == 0) {
            this.fillLevelChestAt(level, rand, cposx, cposy, cposz, 2, this.KnightContentsList, 3 + rand.nextInt(5));
        }
        if (i == 1) {
            blk = net.minecraft.world.level.block.Blocks.BOOKSHELF;
            k = 1 + rand.nextInt(4);
            for (j = 0; j < k; ++j) {
                this.FastSetBlock(level, cposx, cposy + j, cposz, blk);
            }
        }
        if (i == 2) {
            blk = net.minecraft.world.level.block.Blocks.COBWEB;
            k = 1 + rand.nextInt(4);
            for (j = 0; j < k; ++j) {
                this.FastSetBlock(level, cposx, cposy + j, cposz, blk);
            }
        }
    }

    public void makePlayPool(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < 4; ++i) {
            this.placeLevelSpawner(level, cposx + i, cposy + 16, cposz, "chaospersists", "attack_squid");
        }
        this.placeLevelDoubleChest(level, cposx + 1, cposy + 17, cposz, cposx + 2, cposy + 17, cposz, 0);
        this.fillExistingChestAt(level, rand, cposx + 1, cposy + 17, cposz, this.SquidContentsList, 3 + rand.nextInt(5));
        for (i = 0; i < 4; ++i) {
            level.setBlock(
                    new net.minecraft.core.BlockPos(cposx + i, cposy + 18, cposz),
                    net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                    3);
        }
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx - 1, cposy + 18, cposz),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 4, cposy + 18, cposz),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
    }

    public void makeWaterDragonLair(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        float curx;
        float curdeg;
        int i;
        int j;
        float curz;
        net.minecraft.world.level.block.Block blk;
        float currad;
        net.minecraft.util.RandomSource rand = level.getRandom();
        float radius = 10.0f;
        for (currad = 0.0f; currad < radius; currad += 0.33f) {
            for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                curx = (float) ((double) currad * Math.cos(Math.toRadians(curdeg)));
                curz = (float) ((double) currad * Math.sin(Math.toRadians(curdeg)));
                blk = net.minecraft.world.level.block.Blocks.BEDROCK;
                if (currad > 5.0f && currad < 6.0f) {
                    blk = net.minecraft.world.level.block.Blocks.IRON_BLOCK;
                }
                this.FastSetBlock(level, (int) ((float) cposx + curx + 0.5f), cposy + 7, (int) ((float) cposz + curz + 0.5f), blk);
            }
        }
        for (i = 1; i < 10; ++i) {
            this.FastSetBlock(
                    level,
                    (int) ((float) (cposx + i) + 0.5f),
                    cposy + 7,
                    (int) ((float) cposz + 0.5f),
                    net.minecraft.world.level.block.Blocks.IRON_BLOCK);
            this.FastSetBlock(
                    level,
                    (int) ((float) (cposx - i) + 0.5f),
                    cposy + 7,
                    (int) ((float) cposz + 0.5f),
                    net.minecraft.world.level.block.Blocks.IRON_BLOCK);
            this.FastSetBlock(
                    level,
                    (int) ((float) cposx + 0.5f),
                    cposy + 7,
                    (int) ((float) (cposz + i) + 0.5f),
                    net.minecraft.world.level.block.Blocks.IRON_BLOCK);
            this.FastSetBlock(
                    level,
                    (int) ((float) cposx + 0.5f),
                    cposy + 7,
                    (int) ((float) (cposz - i) + 0.5f),
                    net.minecraft.world.level.block.Blocks.IRON_BLOCK);
        }
        this.FastSetBlock(
                level,
                (int) ((float) cposx + 0.5f),
                cposy + 7,
                (int) ((float) cposz + 0.5f),
                net.minecraft.world.level.block.Blocks.AIR);
        this.FastSetBlock(
                level,
                (int) ((float) (cposx + 1) + 0.5f),
                cposy + 7,
                (int) ((float) cposz + 0.5f),
                net.minecraft.world.level.block.Blocks.GLOWSTONE);
        this.FastSetBlock(
                level,
                (int) ((float) (cposx - 1) + 0.5f),
                cposy + 7,
                (int) ((float) cposz + 0.5f),
                net.minecraft.world.level.block.Blocks.GLOWSTONE);
        this.FastSetBlock(
                level,
                (int) ((float) cposx + 0.5f),
                cposy + 7,
                (int) ((float) (cposz + 1) + 0.5f),
                net.minecraft.world.level.block.Blocks.GLOWSTONE);
        this.FastSetBlock(
                level,
                (int) ((float) cposx + 0.5f),
                cposy + 7,
                (int) ((float) (cposz - 1) + 0.5f),
                net.minecraft.world.level.block.Blocks.GLOWSTONE);
        currad = 10.0f;
        net.minecraft.world.level.block.Block waterDragonSpawn =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyWaterDragonSpawnBlock;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float) ((double) currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float) ((double) currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(
                    level,
                    (int) ((float) cposx + curx + 0.5f),
                    cposy + 1,
                    (int) ((float) cposz + curz + 0.5f),
                    net.minecraft.world.level.block.Blocks.GLOWSTONE);
            blk = net.minecraft.world.level.block.Blocks.LAPIS_BLOCK;
            if (rand.nextInt(2) == 0) {
                blk = waterDragonSpawn;
            }
            this.FastSetBlock(level, (int) ((float) cposx + curx + 0.5f), cposy + 2, (int) ((float) cposz + curz + 0.5f), blk);
            blk = net.minecraft.world.level.block.Blocks.LAPIS_BLOCK;
            if (rand.nextInt(2) == 0) {
                blk = waterDragonSpawn;
            }
            this.FastSetBlock(level, (int) ((float) cposx + curx + 0.5f), cposy + 3, (int) ((float) cposz + curz + 0.5f), blk);
            this.FastSetBlock(
                    level,
                    (int) ((float) cposx + curx + 0.5f),
                    cposy + 4,
                    (int) ((float) cposz + curz + 0.5f),
                    net.minecraft.world.level.block.Blocks.GLOWSTONE);
            this.FastSetBlock(
                    level,
                    (int) ((float) cposx + curx + 0.5f),
                    cposy + 5,
                    (int) ((float) cposz + curz + 0.5f),
                    net.minecraft.world.level.block.Blocks.BEDROCK);
            this.FastSetBlock(
                    level,
                    (int) ((float) cposx + curx + 0.5f),
                    cposy + 6,
                    (int) ((float) cposz + curz + 0.5f),
                    net.minecraft.world.level.block.Blocks.BEDROCK);
        }
        for (i = -3; i <= 3; ++i) {
            for (j = -3; j <= 3; ++j) {
                this.FastSetBlock(level, cposx + i, cposy, cposz + j, net.minecraft.world.level.block.Blocks.SAND);
                this.FastSetBlock(level, cposx + i, cposy - 1, cposz + j, net.minecraft.world.level.block.Blocks.STONE);
            }
        }
        for (i = -2; i <= 2; ++i) {
            for (j = -2; j <= 2; ++j) {
                this.FastSetBlock(level, cposx + i, cposy + 3, cposz + j, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
            }
        }
        this.FastSetBlock(level, cposx, cposy + 4, cposz, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
        this.FastSetBlock(level, cposx, cposy + 3, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx + 1, cposy + 3, cposz + 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx - 1, cposy + 3, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx + 1, cposy + 3, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx - 1, cposy + 3, cposz + 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.placeLevelSpawner(level, cposx + 1, cposy + 3, cposz, "chaospersists", "water_dragon");
        this.placeLevelSpawner(level, cposx - 1, cposy + 3, cposz, "chaospersists", "water_dragon");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz + 1, "chaospersists", "water_dragon");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz - 1, "chaospersists", "water_dragon");
        this.fillLevelChestAt(
                level, rand, cposx, cposy + 1, cposz - 1, 2, this.WaterDragonContentsList, 4 + rand.nextInt(5));
    }

    public void makeCloudSharkDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        this.FastSetBlock(level, cposx, cposy, cposz, net.minecraft.world.level.block.Blocks.GLOWSTONE);
        this.FastSetBlock(level, cposx, cposy - 1, cposz, net.minecraft.world.level.block.Blocks.GLOWSTONE);
        this.placeLevelSpawner(level, cposx + 1, cposy, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx - 1, cposy, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx, cposy, cposz + 1, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx, cposy, cposz - 1, "chaospersists", "cloud_shark");
        this.fillLevelChestAt(
                level, rand, cposx, cposy + 1, cposz, 0, this.CloudSharkContentsList, 4 + rand.nextInt(5));
    }

    public void makeLeafMonsterDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int j;
        int i;
        int k;
        net.minecraft.world.level.block.Block blk;
        for (i = -2; i < 6; ++i) {
            for (k = -3; k < 2; ++k) {
                for (j = 0; j < 4; ++j) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                for (j = -1; j > -5; --j) {
                    blk = net.minecraft.world.level.block.Blocks.OAK_LOG;
                    net.minecraft.world.level.block.state.BlockState bid =
                            level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j, cposz + k));
                    if (!bid.isAir() && !bid.is(net.minecraft.world.level.block.Blocks.TALL_GRASS)) {
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                for (j = 0; j < 10; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.OAK_LOG;
                    if (!(j >= 2 || k != 0 && k != 1 || i != 1 && i != 2)) {
                        blk = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    if (k == 1 && (i == 1 || i == 2)) {
                        blk = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    if (k == 2 && (i == 1 || i == 2)) continue;
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                for (j = 0; j < 10; ++j) {
                    if (k != 2 || i != 1 && i != 2) continue;
                    ChaosPersists.setBlockFast(
                            level,
                            cposx + i,
                            cposy + j,
                            cposz + k,
                            net.minecraft.world.level.block.Blocks.LADDER,
                            2,
                            2);
                }
            }
        }
        this.FastSetBlock(level, cposx + 1, cposy + 2, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
        this.FastSetBlock(level, cposx + 2, cposy + 2, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
        for (i = -3; i < 7; ++i) {
            for (k = -3; k < 7; ++k) {
                j = 9;
                if (i >= 0 && i <= 3 && k >= 0 && k <= 3) continue;
                blk = net.minecraft.world.level.block.Blocks.OAK_LOG;
                if (i == -3 || i == 6 || k == -3 || k == 6) {
                    blk = net.minecraft.world.level.block.Blocks.OAK_LEAVES;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = -3; i < 7; ++i) {
            for (k = -3; k < 7; ++k) {
                for (j = 10; j < 13; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == -3 || i == 6 || k == -3 || k == 6) {
                        blk = net.minecraft.world.level.block.Blocks.OAK_LEAVES;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = -2; i < 6; ++i) {
            for (k = -2; k < 6; ++k) {
                j = 13;
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == -2 || i == 5 || k == -2 || k == 5) {
                    blk = net.minecraft.world.level.block.Blocks.OAK_LOG;
                }
                if (i == -1 || i == 4 || k == -1 || k == 4) {
                    blk = net.minecraft.world.level.block.Blocks.OAK_LEAVES;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = -1; i < 5; ++i) {
            for (k = -1; k < 5; ++k) {
                j = 14;
                blk = net.minecraft.world.level.block.Blocks.OAK_LEAVES;
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                j = 15;
                blk = net.minecraft.world.level.block.Blocks.OAK_LOG;
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 1; i < 3; ++i) {
            for (k = 1; k < 3; ++k) {
                j = 16;
                blk = net.minecraft.world.level.block.Blocks.OAK_LEAVES;
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        this.placeLevelSpawner(level, cposx - 2, cposy + 10, cposz - 2, "chaospersists", "leaf_monster");
        this.placeLevelSpawner(level, cposx + 5, cposy + 10, cposz + 5, "chaospersists", "leaf_monster");
        this.placeLevelSpawner(level, cposx - 2, cposy + 10, cposz + 5, "chaospersists", "leaf_monster");
        this.placeLevelSpawner(level, cposx + 5, cposy + 10, cposz - 2, "chaospersists", "leaf_monster");
        this.placeLevelDoubleChest(level, cposx + 1, cposy + 10, cposz + 5, cposx + 2, cposy + 10, cposz + 5, 0);
        this.fillExistingChestAt(
                level, rand, cposx + 1, cposy + 10, cposz + 5, this.LeafMonsterContentsList, 12 + rand.nextInt(5));
    }

    public void makeMiniDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        int j;
        int i;
        int k;
        net.minecraft.world.level.block.Block blk;
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < 10; ++i) {
            for (k = 0; k < 10; ++k) {
                for (j = 0; j < 7; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == 9 || k == 9) {
                        blk = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    if (i == 0 && k == 0) {
                        blk = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (i == 9 && k == 9) {
                        blk = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (i == 0 && k == 9) {
                        blk = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (i == 9 && k == 0) {
                        blk = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (j == 0) {
                        blk = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (j == 6 && (i == 0 || k == 0 || i == 9 || k == 9)) {
                        blk = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 1; i < 9; ++i) {
            for (k = 1; k < 9; ++k) {
                j = 7;
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == 1 || i == 8 || k == 1 || k == 8) {
                    blk = net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 2; i < 8; ++i) {
            for (k = 2; k < 8; ++k) {
                j = 8;
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == 2 || i == 7 || k == 2 || k == 7) {
                    blk = net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        i = -6;
        j = 1;
        k = 3;
        for (int m = 0; m < 6; ++m) {
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.OAK_PLANKS);
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 1, net.minecraft.world.level.block.Blocks.OAK_PLANKS);
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 2, net.minecraft.world.level.block.Blocks.OAK_PLANKS);
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 3, net.minecraft.world.level.block.Blocks.OAK_PLANKS);
            this.FastSetBlock(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.OAK_FENCE);
            this.FastSetBlock(level, cposx + i, cposy + j + 1, cposz + k + 3, net.minecraft.world.level.block.Blocks.OAK_FENCE);
            this.FastSetBlock(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.TORCH);
            this.FastSetBlock(level, cposx + i, cposy + j + 2, cposz + k + 3, net.minecraft.world.level.block.Blocks.TORCH);
            ++i;
            ++j;
        }
        for (i = 3; i < 7; ++i) {
            for (k = 3; k < 7; ++k) {
                j = 9;
                if (i != 3 && i != 6 && k != 3 && k != 6) {
                    continue;
                }
                this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "butterfly");
            }
        }
        k = 0;
        i = 0;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.COBBLESTONE);
        }
        this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "terrible_terror");
        k = 9;
        i = 9;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.COBBLESTONE);
        }
        this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "butterfly");
        i = 0;
        k = 9;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.COBBLESTONE);
        }
        this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "terrible_terror");
        i = 9;
        k = 0;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.COBBLESTONE);
        }
        this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "butterfly");
        this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz + 1, "chaospersists", "terrible_terror");
        this.placeLevelSpawner(level, cposx + 8, cposy + 1, cposz + 8, "chaospersists", "terrible_terror");
        this.placeLevelSpawner(level, cposx + 8, cposy + 1, cposz + 1, "chaospersists", "butterfly");
        this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz + 8, "chaospersists", "butterfly");
        this.placeLevelSpawner(level, cposx + 4, cposy + 1, cposz + 4, "chaospersists", "lurking_terror");
        this.placeLevelSpawner(level, cposx + 5, cposy + 1, cposz + 5, "chaospersists", "lurking_terror");
        this.fillLevelChestAt(level, rand, cposx + 3, cposy + 1, cposz + 3, 0, this.MiniContentsList, 4 + rand.nextInt(5));
    }

    public void makeGoldFishBowl(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        int k;
        int j = 1;
        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.GLASS;
        for (i = 0; i < 5; ++i) {
            for (k = 0; k < 5; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        j = 2;
        for (i = -1; i < 6; ++i) {
            for (k = -1; k < 6; ++k) {
                blk = net.minecraft.world.level.block.Blocks.SAND;
                if (i == -1 || k == -1 || i == 5 || k == 5) {
                    blk = net.minecraft.world.level.block.Blocks.GLASS;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        j = 3;
        for (i = -1; i < 6; ++i) {
            for (k = -1; k < 6; ++k) {
                blk = net.minecraft.world.level.block.Blocks.WATER;
                if (i == -1 || k == -1 || i == 5 || k == 5) {
                    blk = net.minecraft.world.level.block.Blocks.GLASS;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        k = 0;
        i = 0;
        blk = net.minecraft.world.level.block.Blocks.GLOWSTONE;
        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
        k = 4;
        i = 4;
        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
        k = 4;
        i = 0;
        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
        k = 0;
        i = 4;
        this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
        j = 4;
        for (i = -1; i < 6; ++i) {
            for (k = -1; k < 6; ++k) {
                blk = net.minecraft.world.level.block.Blocks.WATER;
                if (i == -1 || k == -1 || i == 5 || k == 5) {
                    blk = net.minecraft.world.level.block.Blocks.GLASS;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (j = 5; j < 8; ++j) {
            for (i = -1; i < 6; ++i) {
                for (k = -1; k < 6; ++k) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == -1 || k == -1 || i == 5 || k == 5) {
                        blk = net.minecraft.world.level.block.Blocks.GLASS;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        j = 8;
        blk = net.minecraft.world.level.block.Blocks.GLASS;
        for (i = 0; i < 5; ++i) {
            for (k = 0; k < 5; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        i = 2;
        k = 2;
        j = 6;
        this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "gold_fish");
    }

    public void makeEnderReaperGraveyard(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int i;
        int j;
        int k;
        int width = 11;
        int length = 13;
        for (j = 1; j < 5; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    if (!level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy - j, cposz + k)).isAir()) {
                        continue;
                    }
                    this.FastSetBlock(level, cposx + i, cposy - j, cposz + k, net.minecraft.world.level.block.Blocks.END_STONE);
                }
            }
        }
        j = 0;
        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.END_STONE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (j = 1; j < 5; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        blk = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz + 1, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width - 2, cposy + 1, cposz + length - 2, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz + length - 2, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width - 2, cposy + 1, cposz + 1, "chaospersists", "ender_reaper");
        this.makeAGrave(level, cposx, cposy, cposz, 1, 6);
        this.makeAGrave(level, cposx, cposy, cposz, 3, 4);
        this.makeAGrave(level, cposx, cposy, cposz, 5, 4);
        this.makeAGrave(level, cposx, cposy, cposz, 7, 4);
        this.makeAGrave(level, cposx, cposy, cposz, 3, 8);
        this.makeAGrave(level, cposx, cposy, cposz, 5, 8);
        this.makeAGrave(level, cposx, cposy, cposz, 7, 8);
        this.makeAGrave(level, cposx, cposy, cposz, 9, 6);
    }

    public void makeAGrave(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz, int xoff, int zoff) {
        net.minecraft.util.RandomSource rand = level.getRandom();
        this.FastSetBlock(level, cposx + xoff, cposy + 1, cposz + zoff - 1, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx + xoff, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.fillLevelChestAt(
                level, rand, cposx + xoff, cposy, cposz + zoff, 0, this.GraveContentsList, 3 + rand.nextInt(3));
    }

    public void makeUrchinSpawner(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block crystalStone =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalStone;
        net.minecraft.world.level.block.Block crystalCrystal =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalCrystal;
        net.minecraft.world.level.block.Block tigersEye =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.TigersEye;
        int patchy = 3;
        for (int i = 0; i < patchy; ++i) {
            net.minecraft.world.level.block.Block bid = crystalStone;
            if (i == 1) {
                bid = crystalCrystal;
            }
            if (i == 2) {
                bid = tigersEye;
            }
            float dx = rand.nextFloat() - rand.nextFloat();
            float dz = rand.nextFloat() - rand.nextFloat();
            float dy = 0.5f + rand.nextFloat() / 2.0f;
            int width = rand.nextInt(2);
            int length = 10 + width * 3 + rand.nextInt(5);
            if (i != 0) {
                length /= 2;
            }
            float rx = cposx;
            float ry = cposy;
            float rz = cposz;
            for (int iy = 0; iy <= length; ++iy) {
                for (int ix = 0; ix <= width; ++ix) {
                    for (int iz = 0; iz <= width; ++iz) {
                        ChaosPersists.setBlockFast(level, (int) (rx + (float) ix), (int) ry, (int) (rz + (float) iz), bid, 0, 2);
                    }
                }
                ry += dy;
                rx += dx;
                rz += dz;
            }
        }
        this.placeLevelSpawner(level, cposx, cposy + 1, cposz, "chaospersists", "crystal_urchin");
        this.placeLevelSpawner(level, cposx, cposy + 2, cposz, "chaospersists", "crystal_urchin");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz, "chaospersists", "crystal_urchin");
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx, cposy, cposz),
                net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(),
                2);
        net.minecraft.core.BlockPos chestPos = new net.minecraft.core.BlockPos(cposx, cposy - 1, cposz);
        level.setBlock(
                chestPos,
                net.minecraft.world.level.block.Blocks.CHEST
                        .defaultBlockState()
                        .setValue(
                                net.minecraft.world.level.block.ChestBlock.FACING,
                                chestFacingFromLegacyMeta(2)),
                2);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(chestPos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
            chest.setItem(
                    1,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.UrchinEgg, 1 + rand.nextInt(5)));
            chest.setItem(
                    2,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.CrystalCoal, 4 + rand.nextInt(16)));
            chest.setItem(
                    3,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.CrystalCoal, 4 + rand.nextInt(16)));
        }
    }

    public void makeSpitBugLair(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i;
        int green = 5;
        int dark_green = 13;
        int width = 9;
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast(level, cposx + i, cposy + width - i + 2, cposz, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
            ChaosPersists.setBlockFast(level, cposx + i, cposy + width - i + 1, cposz, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
            ChaosPersists.setBlockFast(level, cposx + i, cposy + width - i, cposz, net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE, 0, 2);
            ChaosPersists.setBlockFast(level, cposx - i, cposy + width - i + 2, cposz, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
            ChaosPersists.setBlockFast(level, cposx - i, cposy + width - i + 1, cposz, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
            ChaosPersists.setBlockFast(level, cposx - i, cposy + width - i, cposz, net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE, 0, 2);
        }
        ChaosPersists.setBlockFast(level, cposx, cposy + width + 3, cposz, net.minecraft.world.level.block.Blocks.EMERALD_ORE, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + width + 2, cposz, net.minecraft.world.level.block.Blocks.EMERALD_ORE, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + width + 1, cposz, net.minecraft.world.level.block.Blocks.EMERALD_ORE, 0, 2);
        this.placeLevelSpawner(level, cposx, cposy + width, cposz, "chaospersists", "spit_bug");
        this.placeLevelSpawner(level, cposx, cposy + width - 1, cposz, "chaospersists", "spit_bug");
        this.placeLevelSpawner(level, cposx, cposy + width - 2, cposz, "chaospersists", "spit_bug");
        for (i = 0; i < width; ++i) {
            for (int j = - i; j <= i; ++j) {
                ChaosPersists.setBlockFast(level, cposx - width + i + 1, cposy, cposz + j, net.minecraft.world.level.block.Blocks.TERRACOTTA, green, 2);
                ChaosPersists.setBlockFast(level, cposx + width - i - 1, cposy, cposz + j, net.minecraft.world.level.block.Blocks.TERRACOTTA, green, 2);
                if (j == - i || j == i) {
                    ChaosPersists.setBlockFast(level, cposx - width + i + 1, cposy + 1, cposz + j, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
                    ChaosPersists.setBlockFast(level, cposx + width - i - 1, cposy + 1, cposz + j, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
                    ChaosPersists.setBlockFast(level, cposx - width + i + 1, cposy + 2, cposz + j, net.minecraft.world.level.block.Blocks.STONE_BRICKS, 3, 2);
                    ChaosPersists.setBlockFast(level, cposx + width - i - 1, cposy + 2, cposz + j, net.minecraft.world.level.block.Blocks.STONE_BRICKS, 3, 2);
                    continue;
                }
                ChaosPersists.setBlockFast(level, cposx - width + i + 1, cposy + 1, cposz + j, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width - i - 1, cposy + 1, cposz + j, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx - width + i + 1, cposy + 2, cposz + j, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width - i - 1, cposy + 2, cposz + j, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
        }
        this.fillLevelChestAt(level, rand, cposx, cposy + 1, cposz, 0, this.SpitBugContentsList, 4 + rand.nextInt(4));
    }

    public void makeIgloo(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        float curdeg;
        float curx;
        float curz;
        // 1.7.10 Blocks.snow was the full cube; 1.20 Blocks.SNOW is the thin layer.
        net.minecraft.world.level.block.Block snowBlock = net.minecraft.world.level.block.Blocks.SNOW_BLOCK;
        float currad = 6.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 1, (int)((float)cposz + curz + 0.5f), snowBlock);
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 2, (int)((float)cposz + curz + 0.5f), net.minecraft.world.level.block.Blocks.ICE);
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 3, (int)((float)cposz + curz + 0.5f), snowBlock);
        }
        currad = 5.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 4, (int)((float)cposz + curz + 0.5f), net.minecraft.world.level.block.Blocks.ICE);
        }
        currad = 4.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), snowBlock);
        }
        currad = 3.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 10.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), net.minecraft.world.level.block.Blocks.ICE);
        }
        currad = 2.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 15.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), snowBlock);
        }
        currad = 1.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 15.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(level, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), net.minecraft.world.level.block.Blocks.ICE);
        }
        this.FastSetBlock(level, (int)((float)cposx - 6.0f + 0.5f), cposy, (int)((float)cposz + 0.5f), net.minecraft.world.level.block.Blocks.OAK_PLANKS);
        this.FastSetBlock(level, (int)((float)cposx - 6.0f + 0.5f), cposy + 1, (int)((float)cposz + 0.5f), net.minecraft.world.level.block.Blocks.AIR);
        this.FastSetBlock(level, (int)((float)cposx - 6.0f + 0.5f), cposy + 2, (int)((float)cposz + 0.5f), net.minecraft.world.level.block.Blocks.AIR);
        int doorX = (int)((float)cposx - 6.0f + 0.5f);
        int doorZ = (int)((float)cposz + 0.5f);
        this.placeLevelDoor(level, doorX, cposy + 1, doorZ, net.minecraft.core.Direction.NORTH, net.minecraft.world.level.block.Blocks.OAK_DOOR);
        this.placeLevelSpawner(level, cposx + 2, cposy + 1, cposz - 4, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx - 1, cposy + 1, cposz + 1, "chaospersists", "ghost");
        this.placeLevelSpawner(level, cposx + 3, cposy + 1, cposz + 4, "chaospersists", "ghost_pumpkin_skelly");
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx - 3, cposy + 1, cposz - 3),
                net.minecraft.world.level.block.Blocks.CHEST
                        .defaultBlockState()
                        .setValue(
                                net.minecraft.world.level.block.ChestBlock.FACING,
                                chestFacingFromLegacyMeta(2)),
                2);
        net.minecraft.world.level.block.entity.BlockEntity iglooChestBe =
                level.getBlockEntity(new net.minecraft.core.BlockPos(cposx - 3, cposy + 1, cposz - 3));
        if (iglooChestBe instanceof net.minecraft.world.level.block.entity.ChestBlockEntity iglooChest) {
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(0, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COMPASS));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(1, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.MAP));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(2, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COOKED_PORKCHOP, 8));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(3, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.TORCH, 32));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(4, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COAL, 16));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(5, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.RED_BED));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(6, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.RED_BED));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(7, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.OAK_DOOR));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(8, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_PICKAXE));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(9, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_SWORD));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(10, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.IRON_AXE));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(11, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.BUCKET));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(13, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.CHEST));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(14, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.GOLD_NUGGET, 6));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(15, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.GOLD_NUGGET, 8));
            }
            if (rand.nextInt(2) == 0) {
                iglooChest.setItem(16, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.GOLD_NUGGET, 10));
            }
        }
    }

    public void makeEnderDragonHospital(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block eyeBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEyeOfEnderBlock;
        int j;
        int i;
        int k;
        net.minecraft.world.level.block.Block blk;
        for (i = 0; i < 10; ++i) {
            for (k = 0; k < 10; ++k) {
                for (j = 0; j < 7; ++j) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == 9 || k == 9) {
                        blk = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    if (i == 0 && k == 0) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (i == 9 && k == 9) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (i == 0 && k == 9) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (i == 9 && k == 0) {
                        blk = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (j == 0) {
                        blk = net.minecraft.world.level.block.Blocks.END_STONE;
                    }
                    if (j == 6 && (i == 0 || k == 0 || i == 9 || k == 9)) {
                        blk = net.minecraft.world.level.block.Blocks.END_STONE;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 1; i < 9; ++i) {
            for (k = 1; k < 9; ++k) {
                j = 7;
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == 1 || i == 8 || k == 1 || k == 8) {
                    blk = eyeBlock;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 2; i < 8; ++i) {
            for (k = 2; k < 8; ++k) {
                j = 8;
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == 2 || i == 7 || k == 2 || k == 7) {
                    blk = eyeBlock;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 3; i < 7; ++i) {
            for (k = 3; k < 7; ++k) {
                j = 9;
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == 3 || i == 6 || k == 3 || k == 6) {
                    blk = eyeBlock;
                }
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        i = -6;
        j = 1;
        k = 3;
        for (int m = 0; m < 6; ++m) {
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.END_STONE);
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 1, net.minecraft.world.level.block.Blocks.END_STONE);
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 2, net.minecraft.world.level.block.Blocks.END_STONE);
            this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 3, net.minecraft.world.level.block.Blocks.END_STONE);
            this.FastSetBlock(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS);
            this.FastSetBlock(level, cposx + i, cposy + j + 1, cposz + k + 3, net.minecraft.world.level.block.Blocks.IRON_BARS);
            this.FastSetBlock(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.GLOWSTONE);
            this.FastSetBlock(level, cposx + i, cposy + j + 2, cposz + k + 3, net.minecraft.world.level.block.Blocks.GLOWSTONE);
            ++i;
            ++j;
        }
        this.FastSetBlock(level, cposx, cposy + 7, cposz, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx, cposy + 7, cposz + 9, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx + 9, cposy + 7, cposz, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx + 9, cposy + 7, cposz + 9, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx, cposy + 8, cposz, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx, cposy + 8, cposz + 9, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx + 9, cposy + 8, cposz, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        this.FastSetBlock(level, cposx + 9, cposy + 8, cposz + 9, net.minecraft.world.level.block.Blocks.OBSIDIAN);
        net.minecraft.world.entity.boss.enderdragon.EndCrystal hospitalCrystal =
                net.minecraft.world.entity.EntityType.END_CRYSTAL.create(level);
        if (hospitalCrystal != null) {
            hospitalCrystal.moveTo(cposx + 0.5, cposy + 9, cposz + 0.5, rand.nextFloat() * 360.0F, 0.0F);
            level.addFreshEntity(hospitalCrystal);
        }
        this.FastSetBlock(level, cposx, cposy + 9, cposz, net.minecraft.world.level.block.Blocks.BEDROCK);
        hospitalCrystal = net.minecraft.world.entity.EntityType.END_CRYSTAL.create(level);
        if (hospitalCrystal != null) {
            hospitalCrystal.moveTo(cposx + 0.5, cposy + 9, cposz + 9.5, rand.nextFloat() * 360.0F, 0.0F);
            level.addFreshEntity(hospitalCrystal);
        }
        this.FastSetBlock(level, cposx, cposy + 9, cposz + 9, net.minecraft.world.level.block.Blocks.BEDROCK);
        hospitalCrystal = net.minecraft.world.entity.EntityType.END_CRYSTAL.create(level);
        if (hospitalCrystal != null) {
            hospitalCrystal.moveTo(cposx + 9.5, cposy + 9, cposz + 0.5, rand.nextFloat() * 360.0F, 0.0F);
            level.addFreshEntity(hospitalCrystal);
        }
        this.FastSetBlock(level, cposx + 9, cposy + 9, cposz, net.minecraft.world.level.block.Blocks.BEDROCK);
        hospitalCrystal = net.minecraft.world.entity.EntityType.END_CRYSTAL.create(level);
        if (hospitalCrystal != null) {
            hospitalCrystal.moveTo(cposx + 9.5, cposy + 9, cposz + 9.5, rand.nextFloat() * 360.0F, 0.0F);
            level.addFreshEntity(hospitalCrystal);
        }
        this.FastSetBlock(level, cposx + 9, cposy + 9, cposz + 9, net.minecraft.world.level.block.Blocks.BEDROCK);
        this.placeLevelSpawner(level, cposx + 3, cposy + 9, cposz + 3, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + 3, cposy + 9, cposz + 6, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + 6, cposy + 9, cposz + 3, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + 6, cposy + 9, cposz + 6, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz + 1, "chaospersists", "nightmare");
        this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz + 8, "chaospersists", "nightmare");
        this.placeLevelSpawner(level, cposx + 8, cposy + 1, cposz + 1, "chaospersists", "nightmare");
        this.placeLevelSpawner(level, cposx + 8, cposy + 1, cposz + 8, "chaospersists", "nightmare");
        this.fillLevelChestAt(level, rand, cposx + 4, cposy + 1, cposz + 4, 0, this.HospitalContentsList, 6 + rand.nextInt(5));
    }

    public void makeCrystalHauntedHouse(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int k;
        int j;
        int i;
        int deltax = 1;
        int deltaz = 0;
        int stuffdir = 2;
        int length = 3;
        int width = 3;
        int height = 3;
        int x = cposx;
        int z = cposz;
        int y = cposy;
        if (level.isClientSide()) {
            return;
        }
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.core.Direction facing = chestFacingFromLegacyMeta(stuffdir);
        net.minecraft.world.level.block.Block crystalPlanks =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalPlanksBlock;
        net.minecraft.world.level.block.Block crystalStone =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalStone;
        net.minecraft.world.level.block.Block crystalWorkbench =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalWorkbenchBlock;
        for (i = - width; i <= width; ++i) {
            for (j = - length; j <= length; ++j) {
                for (k = 0; k <= height + 1; ++k) {
                    net.minecraft.core.BlockPos pos = new net.minecraft.core.BlockPos(x + i, y + k, z + j);
                    if (k == height + 1) {
                        level.setBlock(pos, crystalPlanks.defaultBlockState(), 3);
                        continue;
                    }
                    if (k == 0) {
                        level.setBlock(pos, crystalStone.defaultBlockState(), 3);
                        continue;
                    }
                    if (i == width || j == length || i == - width || j == - length) {
                        if (k == height) {
                            level.setBlock(pos, net.minecraft.world.level.block.Blocks.GLASS.defaultBlockState(), 3);
                            continue;
                        }
                        if ((k == 1 || k == 2) && i == deltax * width && j == deltaz * length) {
                            level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                            continue;
                        }
                        level.setBlock(pos, crystalPlanks.defaultBlockState(), 3);
                        continue;
                    }
                    level.setBlock(pos, net.minecraft.world.level.block.Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }
        i = 2;
        k = 1;
        j = length - 1;
        net.minecraft.core.BlockPos furnacePos =
                new net.minecraft.core.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
        level.setBlock(
                furnacePos,
                ChaosPersists.CrystalFurnaceBlock
                        .defaultBlockState()
                        .setValue(com.astryxion.chaospersists.block.CrystalFurnace.FACING, facing),
                3);
        i = 1;
        level.setBlock(
                new net.minecraft.core.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax),
                crystalWorkbench.defaultBlockState(),
                3);
        i = 0;
        net.minecraft.core.BlockPos chestPos =
                new net.minecraft.core.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
        level.setBlock(
                chestPos,
                net.minecraft.world.level.block.Blocks.CHEST
                        .defaultBlockState()
                        .setValue(net.minecraft.world.level.block.ChestBlock.FACING, facing),
                3);
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = level.getBlockEntity(chestPos);
        if (blockEntity instanceof net.minecraft.world.level.block.entity.ChestBlockEntity chest) {
            if (rand.nextInt(2) == 0) {
                chest.setItem(0, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.COMPASS));
            }
            if (rand.nextInt(3) != 0) {
                chest.setItem(
                        2,
                        new net.minecraft.world.item.ItemStack(ChaosPersists.MyPeacock, 8));
            }
            if (rand.nextInt(3) != 0) {
                chest.setItem(
                        3,
                        new net.minecraft.world.item.ItemStack(ChaosPersists.CrystalTorch, 32));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        4,
                        new net.minecraft.world.item.ItemStack(ChaosPersists.CrystalCoal, 16));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(5, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.RED_BED));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(6, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.RED_BED));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(7, new net.minecraft.world.item.ItemStack(net.minecraft.world.item.Items.OAK_DOOR));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        8,
                        new net.minecraft.world.item.ItemStack(ChaosPersists.MyCrystalPinkPickaxe));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        9,
                        new net.minecraft.world.item.ItemStack(ChaosPersists.MyCrystalPinkSword));
            }
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        10,
                        new net.minecraft.world.item.ItemStack(ChaosPersists.MyCrystalPinkAxe));
            }
            chest.setItem(
                    11,
                    new net.minecraft.world.item.ItemStack(ChaosPersists.KrakenRepellent));
            if (rand.nextInt(2) == 0) {
                chest.setItem(
                        13,
                        new net.minecraft.world.item.ItemStack(net.minecraft.world.level.block.Blocks.CHEST.asItem()));
            }
        }
        this.placeLevelSpawner(level, cposx, cposy + 1, cposz, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx, cposy + 2, cposz, "chaospersists", "ghost");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz, "chaospersists", "ghost_pumpkin_skelly");
    }

    public void makeBouncyCastle(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block lavafoamBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyLavafoamBlock;
        int meta = 0;
        int length = 4;
        int width = 4;
        int height = 5;
        if (level.isClientSide()) {
            return;
        }
        for (int i = - width; i <= width; ++i) {
            for (int j = - length; j <= length; ++j) {
                for (int k = 0; k < height; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    meta = 0;
                    if (k == height - 1 || k == 0) {
                        bid = lavafoamBlock;
                    }
                    if (i == - width || i == width) {
                        bid = lavafoamBlock;
                    }
                    if (j == - length || j == length) {
                        bid = lavafoamBlock;
                    }
                    if (!(i != - width && i != width || j != - length && j != length)) {
                        bid = net.minecraft.world.level.block.Blocks.TERRACOTTA;
                        meta = 14;
                    }
                    if ((k == 1 || k == 2) && i == 0 && j == -length) {
                        meta = 0;
                        bid = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + k, cposz + j, bid, meta, 2);
                }
            }
        }
        this.placeLevelSpawner(level, cposx - 1, cposy + 3, cposz + length - 1, "minecraft", "silverfish");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz + length - 1, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx + 1, cposy + 3, cposz + length - 1, "chaospersists", "scorpion");
        this.placeLevelSpawner(level, cposx + width - 1, cposy + 3, cposz - 1, "minecraft", "silverfish");
        this.placeLevelSpawner(level, cposx + width - 1, cposy + 3, cposz, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx + width - 1, cposy + 3, cposz + 1, "chaospersists", "scorpion");
        this.placeLevelSpawner(level, cposx - width + 1, cposy + 3, cposz - 1, "minecraft", "silverfish");
        this.placeLevelSpawner(level, cposx - width + 1, cposy + 3, cposz, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx - width + 1, cposy + 3, cposz + 1, "chaospersists", "scorpion");
        this.fillLevelChestAt(
                level,
                rand,
                cposx + width - 1,
                cposy + 3,
                cposz + length - 1,
                2,
                this.BouncyContentsList,
                6 + rand.nextInt(5));
    }

    public void makeEnderCastle(Object worldObj, int cposx, int cposy, int cposz) {
        int j;
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block enderKnightSpawn = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEnderKnightSpawnBlock;
        net.minecraft.world.level.block.Block enderReaperSpawn = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEnderReaperSpawnBlock;
        net.minecraft.world.level.block.Block endermanSpawn = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEndermanSpawnBlock;
        net.minecraft.world.level.block.Block enderDragonSpawn = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEnderDragonSpawnBlock;
        net.minecraft.world.level.block.Block eyeOfEnderBlock = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEyeOfEnderBlock;
        net.minecraft.world.level.block.Block enderPearlBlock = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEnderPearlBlock;
        int k;
        int m;
        int i;
        int width = 22;
        int height = 12;
        WeightedRandomChestContent[] chestContents = this.EnderCastleContentsList;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = -3; i <= width + 3; ++i) {
            for (k = -3; k <= width + 3; ++k) {
                for (j = 0; j <= 1; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0) {
                        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (j == 1 && (i == -3 || i == width + 3 || k == width + 3 | k == -3)) {
                        bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        for (i = 0; i <= width; ++i) {
            for (k = 0; k <= width; ++k) {
                for (j = 1; j <= height; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || i == width || k == width | k == 0) {
                        bid = net.minecraft.world.level.block.Blocks.BEDROCK;
                    }
                    if (j == height && bid == net.minecraft.world.level.block.Blocks.BEDROCK && (i + k & 1) == 0) {
                        bid = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    if (j == height - 2 && bid == net.minecraft.world.level.block.Blocks.BEDROCK && (i + k & 1) == 0) {
                        int which = rand.nextInt(4);
                        if (which == 0) {
                            bid = enderKnightSpawn;
                        }
                        if (which == 1) {
                            bid = enderReaperSpawn;
                        }
                        if (which == 2) {
                            bid = endermanSpawn;
                        }
                        if (which == 3) {
                            bid = enderDragonSpawn;
                        }
                    }
                    if (j == 7 && bid == net.minecraft.world.level.block.Blocks.BEDROCK && (i + k & 1) != 0) {
                        bid = eyeOfEnderBlock;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        for (i = -1; i <= width + 1; ++i) {
            for (k = -1; k <= width + 1; ++k) {
                for (j = 1; j <= height - 1; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 6 || j > 8) {
                        if (i == -1 || i == width + 1 || k == width + 1 | k == -1) {
                            bid = net.minecraft.world.level.block.Blocks.BEDROCK;
                        }
                        if (j == 6 && bid != net.minecraft.world.level.block.Blocks.AIR && rand.nextInt(2) == 1) {
                            ChaosPersists.setBlockFast(level, cposx + i, cposy + j - 1, cposz + k, enderPearlBlock, 0, 2);
                            if (rand.nextInt(3) == 1) {
                                ChaosPersists.setBlockFast(level, cposx + i, cposy + j - 2, cposz + k, enderPearlBlock, 0, 2);
                            }
                        }
                    }
                    if (j == 7) {
                        if (i == -1 || i == width + 1 || k == width + 1 | k == -1) {
                            bid = net.minecraft.world.level.block.Blocks.BEDROCK;
                        }
                        if (bid == net.minecraft.world.level.block.Blocks.BEDROCK && (i + k & 1) == 0) {
                            bid = net.minecraft.world.level.block.Blocks.AIR;
                        }
                    }
                    if (bid == Blocks.AIR) continue;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        this.makeAColumn(level, cposx - 2, cposy, cposz - 2, height + 1, 0);
        this.makeAColumn(level, cposx + width - 2, cposy, cposz - 2, height + 1, 1);
        this.makeAColumn(level, cposx - 2, cposy, cposz + width - 2, height + 1, 2);
        this.makeAColumn(level, cposx + width - 2, cposy, cposz + width - 2, height + 1, 3);
        j = 8;
        for (i = 1; i <= width - 1; ++i) {
            for (k = 1; k <= width - 1; ++k) {
                bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                if (i == width / 2 || k == width / 2 || i == k || i == width - k) {
                    bid = net.minecraft.world.level.block.Blocks.BEDROCK;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        j = 9;
        for (i = -2; i <= 2; ++i) {
            for (k = -2; k <= 2; ++k) {
                bid = net.minecraft.world.level.block.Blocks.LAVA;
                ChaosPersists.setBlockFast(level, cposx + i + width / 2, cposy + j, cposz + k + width / 2, bid, 0, 2);
            }
        }
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast(level, cposx + width / 2 + m, cposy + j, cposz + width / 2 + 3, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + width / 2 + m, cposy + j, cposz + width / 2 - 3, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + width / 2 + 3, cposy + j, cposz + width / 2 + m, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + width / 2 - 3, cposy + j, cposz + width / 2 + m, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        }
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 2, cposy + j, cposz + width / 2 - 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 2, cposy + j, cposz + width / 2 + 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 2, cposy + j, cposz + width / 2 + 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 2, cposy + j, cposz + width / 2 - 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j, cposz + width / 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + width / 2, cposy + j + 1, cposz + width / 2),
                net.minecraft.world.level.block.Blocks.ENDER_CHEST
                        .defaultBlockState()
                        .setValue(
                                net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING,
                                chestFacingFromLegacyMeta(2)),
                2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 2, cposz + width / 2, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 3, cposz + width / 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + j + 3, cposz + width / 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 1, cposy + j + 3, cposz + width / 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 3, cposz + width / 2 - 1, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 3, cposz + width / 2 + 1, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + j + 4, cposz + width / 2, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 1, cposy + j + 4, cposz + width / 2, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 4, cposz + width / 2 - 1, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 4, cposz + width / 2 + 1, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 4, cposz + width / 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 5, cposz + width / 2, net.minecraft.world.level.block.Blocks.BEDROCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + j + 6, cposz + width / 2, net.minecraft.world.level.block.Blocks.DRAGON_EGG, 0, 2);
        this.placeLevelSpawner(level, cposx + width / 2 + 5, cposy + j, cposz + width / 2 + 5, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width / 2 + 5, cposy + j + 1, cposz + width / 2 + 5, "chaospersists", "ender_knight");
        this.placeLevelSpawner(level, cposx + width / 2 - 5, cposy + j, cposz + width / 2 + 5, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width / 2 - 5, cposy + j + 1, cposz + width / 2 + 5, "chaospersists", "ender_knight");
        this.placeLevelSpawner(level, cposx + width / 2 + 5, cposy + j, cposz + width / 2 - 5, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width / 2 + 5, cposy + j + 1, cposz + width / 2 - 5, "chaospersists", "ender_knight");
        this.placeLevelSpawner(level, cposx + width / 2 - 5, cposy + j, cposz + width / 2 - 5, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width / 2 - 5, cposy + j + 1, cposz + width / 2 - 5, "chaospersists", "ender_knight");
        j = 4;
        for (i = 1; i <= width - 1; ++i) {
            for (k = 1; k <= width - 1; ++k) {
                bid = net.minecraft.world.level.block.Blocks.AIR;
                if (i <= 5 || k <= 5 || i >= width - 5 || k >= width - 5) {
                    bid = net.minecraft.world.level.block.Blocks.BEDROCK;
                }
                if (bid != net.minecraft.world.level.block.Blocks.AIR) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
                if (i == 5 && k >= 5 && k <= width - 5) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 3, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                }
                if (i == width - 5 && k >= 5 && k <= width - 5) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 3, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                }
                if (k == 5 && i >= 5 && i <= width - 5) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 3, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                }
                if (k != width - 5 || i < 5 || i > width - 5) continue;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 3, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
            }
        }
        bid = net.minecraft.world.level.block.Blocks.BEDROCK;
        j = 3;
        k = width / 2;
        i = width - 6;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k + m, bid, 0, 2);
        }
        j = 2;
        k = width / 2;
        i = width - 7;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k + m, bid, 0, 2);
        }
        j = 1;
        k = width / 2;
        i = width - 8;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k + m, bid, 0, 2);
        }
        j = 4;
        i = width - 5;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k + m, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 2, cposz + k + m, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 3, cposz + k + m, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        }
        j = 1;
        this.placeLevelSpawner(level, cposx + width / 2, cposy + j, cposz + width / 2, "chaospersists", "ender_reaper");
        this.placeLevelSpawner(level, cposx + width / 2, cposy + j + 1, cposz + width / 2, "chaospersists", "ender_knight");
        j = 5;
        this.placeLevelSpawner(level, cposx + 1, cposy + j, cposz + width / 2 - 1, "chaospersists", "cavefisher");
        this.placeLevelSpawner(level, cposx + 1, cposy + j, cposz + width / 2 + 1, "chaospersists", "cavefisher");
        this.fillLevelChestAt(level, rand, cposx + 1, cposy + j, cposz + width / 2, 2, chestContents, 6 + rand.nextInt(5));
        this.placeLevelSpawner(level, cposx + width / 2 - 1, cposy + j, cposz + 1, "chaospersists", "cavefisher");
        this.placeLevelSpawner(level, cposx + width / 2 + 1, cposy + j, cposz + 1, "chaospersists", "cavefisher");
        this.fillLevelChestAt(level, rand, cposx + width / 2, cposy + j, cposz + 1, 3, chestContents, 6 + rand.nextInt(5));
        this.placeLevelSpawner(level, cposx + width / 2 - 1, cposy + j, cposz + width - 1, "chaospersists", "cavefisher");
        this.placeLevelSpawner(level, cposx + width / 2 + 1, cposy + j, cposz + width - 1, "chaospersists", "cavefisher");
        this.fillLevelChestAt(level, rand, cposx + width / 2, cposy + j, cposz + width - 1, 4, chestContents, 6 + rand.nextInt(5));
    }

    private void makeAColumn(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz, int height, int dir) {
        net.minecraft.world.level.block.Block bid;
        int k;
        int i;
        int j;
        int width = 4;
        int halfwidth = 2;
        int step = dir;
        for (i = -2; i <= width + 2; ++i) {
            for (k = -2; k <= width + 2; ++k) {
                j = height + 2;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.OBSIDIAN, 0, 2);
            }
        }
        for (i = -2; i <= width + 2; ++i) {
            for (k = -2; k <= width + 2; ++k) {
                bid = net.minecraft.world.level.block.Blocks.AIR;
                if (i == -2 || i == width + 2 || k == width + 2 | k == -2) {
                    bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                }
                j = height + 3;
                if (bid != net.minecraft.world.level.block.Blocks.AIR && (i + k & 1) == 0) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        for (i = 0; i <= width; ++i) {
            for (k = 0; k <= width; ++k) {
                for (j = 1; j <= height + 2; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || i == width || k == width | k == 0) {
                        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (!(j % 3 != 0 && j % 3 != 1 || j == height + 2 || bid != net.minecraft.world.level.block.Blocks.OBSIDIAN || i != halfwidth && k != halfwidth)) {
                        bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        if (dir == 0) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width - 1, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz + width - 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width - 1, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz + width - 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
        }
        if (dir == 1) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + 1, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz + width - 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + 1, cposy + j, cposz + width, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz + width - 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            if (++step > 3) {
                step = 0;
            }
        }
        if (dir == 2) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width - 1, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz + 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width - 1, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + width, cposy + j, cposz + 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            if (++step > 3) {
                step = 0;
            }
            if (++step > 3) {
                step = 0;
            }
        }
        if (dir == 3) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + 1, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz + 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx + 1, cposy + j, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
                ChaosPersists.setBlockFast(level, cposx, cposy + j, cposz + 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
            }
            if (++step > 3) {
                step = 0;
            }
            if (++step > 3) {
                step = 0;
            }
        }
        bid = net.minecraft.world.level.block.Blocks.NETHER_BRICKS;
        k = 0;
        for (j = 1; j <= height + 2; ++j) {
            if (step == 0) {
                k = 1;
                i = 1;
            }
            if (step == 1) {
                i = 1;
                k = 3;
            }
            if (step == 2) {
                i = 3;
                k = 3;
            }
            if (step == 3) {
                i = 3;
                k = 1;
            }
            if (++step > 3) {
                step = 0;
            }
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
        }
    }

    public void makeDamselInDistress(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i;
        int j;
        int k;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int meta = 0;
        int length = 4;
        int width = 4;
        int height = 5;
        if (level.isClientSide()) {
            return;
        }
        for (i = - width; i <= width; ++i) {
            for (j = - length; j <= length; ++j) {
                for (k = 0; k < height; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    meta = 0;
                    if (k == 0) {
                        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (i == - width || i == width) {
                        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (j == - length || j == length) {
                        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                    }
                    if (bid == net.minecraft.world.level.block.Blocks.COBBLESTONE && rand.nextInt(8) == 1) {
                        bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                    }
                    if (!(k != 1 && k != 2 && k != 3 || i != 0 && i != -1 && i != 1 || j != - length)) {
                        meta = 0;
                        bid = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + k, cposz + j, bid, meta, 2);
                }
            }
        }
        meta = 0;
        for (i = - width + 1; i <= width - 1; ++i) {
            for (j = - length; j <= length - 1; ++j) {
                k = height;
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (rand.nextInt(8) == 1) {
                    bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + k, cposz + j, bid, meta, 2);
            }
        }
        for (i = - width + 2; i <= width - 2; ++i) {
            for (j = - length; j <= length - 2; ++j) {
                k = height + 1;
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (rand.nextInt(8) == 1) {
                    bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + k, cposz + j, bid, meta, 2);
            }
        }
        k = height;
        j = - length;
        for (int m = width; m >= 0; --m) {
            for (i = m; i >= 0; --i) {
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (rand.nextInt(8) == 1) {
                    bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + k, cposz + j, bid, meta, 2);
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (rand.nextInt(8) == 1) {
                    bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast(level, cposx - i, cposy + k, cposz + j, bid, meta, 2);
            }
            ++k;
        }
        for (i = - width + 1; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = length - 3;
                ChaosPersists.setBlockFast(
                        level, cposx - i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.IRON_BARS, 0, 2);
            }
        }
        this.placeLevelSpawner(level, cposx - width + 1, cposy + 1, cposz - length + 1, "chaospersists", "scorpion");
        this.placeLevelSpawner(level, cposx + width - 1, cposy + 1, cposz - length + 1, "chaospersists", "scorpion");
        this.fillLevelChestAt(
                level, rand, cposx + width - 1, cposy + 1, cposz + length - 1, 2, this.DamselContentsList, 10 + rand.nextInt(5));
        com.astryxion.chaospersists.entity.Dragon.spawnCreature(
                level, "girlfriend", cposx - width + 2, cposy + 1, cposz + length - 1);
    }

    public void makeIncaPyramid(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i;
        int m;
        int k;
        int j;
        int p;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block air = net.minecraft.world.level.block.Blocks.AIR;
        int meta = 0;
        int width = 21;
        int depth = 11;
        int height = 9;
        int basewidth = 41;
        int basedepth = 31;
        int baseheight = 10;
        net.minecraft.world.level.block.Block creeperRepellent =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CreeperRepellent;
        if (level.isClientSide()) {
            return;
        }
        for (j = 0; j < baseheight; ++j) {
            for (i = 0; i < basewidth - j * 2; ++i) {
                for (k = 0; k < basedepth - j * 2; ++k) {
                    meta = 0;
                    bid = air;
                    if (i == 0 || k == 0 || i == basewidth - j * 2 - 1 || k == basedepth - j * 2 - 1) {
                        bid = net.minecraft.world.level.block.Blocks.STONE;
                        if (rand.nextInt(2) == 0) {
                            bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                        }
                        if (rand.nextInt(4) == 0) {
                            bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                        }
                    }
                    if (j == 0) {
                        bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                    }
                    if (k == 1 && j % 3 == 2 && i != 0 && i != basewidth - j * 2 - 1) {
                        bid = net.minecraft.world.level.block.Blocks.TORCH;
                        meta = 3;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i + j, cposy + j, cposz + k + j, bid, meta, 2);
                    if (k != basedepth - j * 2 - 1 || j % 3 != 2 || i == 0 || i == basewidth - j * 2 - 1) continue;
                    meta = 4;
                    ChaosPersists.setBlockFast(
                            level, cposx + i + j, cposy + j, cposz + k + j - 1, net.minecraft.world.level.block.Blocks.TORCH, meta, 2);
                }
            }
        }
        meta = 0;
        for (m = 0; m < baseheight * 2 - 1; ++m) {
            i = - baseheight + m;
            for (p = -2; p <= 2; ++p) {
                k = basedepth / 2;
                k += p;
                j = m / 2;
                if (p < -1 || p > 1) {
                    bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == air) {
                        ChaosPersists.setBlockFast(
                                level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.STONE_BRICKS, meta, 2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast(
                                    level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.TORCH, meta, 2);
                        }
                    }
                } else if (m % 2 == 1
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level,
                            cposx + i,
                            cposy + j + 1,
                            cposz + k,
                            net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB,
                            meta,
                            2);
                }
                while (j >= 0
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE, meta, 2);
                    --j;
                }
            }
        }
        meta = 0;
        for (m = 0; m < baseheight * 2 - 1; ++m) {
            i = basewidth + baseheight - m - 1;
            for (p = -2; p <= 2; ++p) {
                k = basedepth / 2;
                k += p;
                j = m / 2;
                if (p < -1 || p > 1) {
                    bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == air) {
                        ChaosPersists.setBlockFast(
                                level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.STONE_BRICKS, meta, 2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast(
                                    level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.TORCH, meta, 2);
                        }
                    }
                } else if (m % 2 == 1
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level,
                            cposx + i,
                            cposy + j + 1,
                            cposz + k,
                            net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB,
                            meta,
                            2);
                }
                while (j >= 0
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE, meta, 2);
                    --j;
                }
            }
        }
        meta = 0;
        for (m = 0; m < baseheight * 2 - 1; ++m) {
            k = - baseheight + m;
            for (p = -2; p <= 2; ++p) {
                i = basewidth / 2;
                i += p;
                j = m / 2;
                if (p < -1 || p > 1) {
                    bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == air) {
                        ChaosPersists.setBlockFast(
                                level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.STONE_BRICKS, meta, 2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast(
                                    level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.TORCH, meta, 2);
                        }
                    }
                } else if (m % 2 == 1
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level,
                            cposx + i,
                            cposy + j + 1,
                            cposz + k,
                            net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB,
                            meta,
                            2);
                }
                while (j >= 0
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE, meta, 2);
                    --j;
                }
            }
        }
        meta = 0;
        for (m = 0; m < baseheight * 2 - 1; ++m) {
            k = basedepth + baseheight - m - 1;
            for (p = -2; p <= 2; ++p) {
                i = basewidth / 2;
                i += p;
                j = m / 2;
                if (p < -1 || p > 1) {
                    bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == air) {
                        ChaosPersists.setBlockFast(
                                level, cposx + i, cposy + j + 1, cposz + k, net.minecraft.world.level.block.Blocks.STONE_BRICKS, meta, 2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast(
                                    level, cposx + i, cposy + j + 2, cposz + k, net.minecraft.world.level.block.Blocks.TORCH, meta, 2);
                        }
                    }
                } else if (m % 2 == 1
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level,
                            cposx + i,
                            cposy + j + 1,
                            cposz + k,
                            net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB,
                            meta,
                            2);
                }
                while (j >= 0
                        && (bid = level.getBlockState(new net.minecraft.core.BlockPos(cposx + i, cposy + j, cposz + k))
                                        .getBlock())
                                == air) {
                    ChaosPersists.setBlockFast(
                            level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE, meta, 2);
                    --j;
                }
            }
        }
        cposx += baseheight;
        cposy += baseheight;
        cposz += baseheight;
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < depth; ++k) {
                    bid = air;
                    meta = 0;
                    if (i == 0 || k == 0 || i == width - 1 || k == depth - 1) {
                        bid = net.minecraft.world.level.block.Blocks.STONE;
                        if (rand.nextInt(2) == 0) {
                            bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                        }
                        if (rand.nextInt(4) == 0) {
                            bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                        }
                    }
                    if (j == 0 || j == height - 1) {
                        bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                    }
                    if (j == 1 || j == 2 || j == 3) {
                        if ((k == 0 || k == depth - 1) && i >= width / 2 - 1 && i <= width / 2 + 1) {
                            bid = j == 3 ? net.minecraft.world.level.block.Blocks.OAK_FENCE : air;
                        }
                        if ((i == 0 || i == width - 1) && k >= depth / 2 - 1 && k <= depth / 2 + 1) {
                            bid = j == 3 ? net.minecraft.world.level.block.Blocks.OAK_FENCE : air;
                        }
                    }
                    if ((j == height - 3 || j == height - 2) && (i + k) % 2 == 1) {
                        if (j == height - 3) {
                            if (bid != air) {
                                bid = net.minecraft.world.level.block.Blocks.REDSTONE_LAMP;
                            }
                        } else {
                            bid = air;
                        }
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, meta, 2);
                }
            }
        }
        bid = net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB;
        meta = 0;
        j = height;
        for (i = -1; i <= width; ++i) {
            for (k = -1; k <= depth; ++k) {
                if (i != -1 && k != -1 && i != width && k != depth || (i + k & 1) != 1) continue;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, meta, 2);
            }
        }
        this.makepoolalter(level, cposx + 1, cposy, cposz + 1);
        this.makepoolalter(level, cposx + width - 2, cposy, cposz + depth - 2);
        this.makepoolalter(level, cposx + 1, cposy, cposz + depth - 2);
        this.makepoolalter(level, cposx + width - 2, cposy, cposz + 1);
        this.makepoolalter(level, cposx + width / 2, cposy, cposz + depth / 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + 2, cposz + depth / 2 - 1, creeperRepellent, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 1, cposy + 2, cposz + depth / 2 + 1, creeperRepellent, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + 2, cposz + depth / 2 + 1, creeperRepellent, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 1, cposy + 2, cposz + depth / 2 - 1, creeperRepellent, 0, 2);
        this.placeLevelSpawner(level, cposx + width / 2 - 2, cposy + 1, cposz + depth / 2, "chaospersists", "molenoid");
        ChaosPersists.setBlockFast(
                level,
                cposx + width / 2 + 2,
                cposy + 1,
                cposz + depth / 2,
                net.minecraft.world.level.block.Blocks.OAK_TRAPDOOR,
                3,
                2);
        ChaosPersists.setBlockFast(
                level, cposx + width / 2 + 2, cposy, cposz + depth / 2, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(
                level, cposx + width / 2 + 2, cposy, cposz + depth / 2 + 1, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        i = cposx + width / 2 + 2;
        k = cposz + depth / 2;
        for (j = 1; j < baseheight; ++j) {
            ChaosPersists.setBlockFast(
                    level, i, cposy - j, k + 1, net.minecraft.world.level.block.Blocks.COBBLESTONE, 0, 2);
            ChaosPersists.setBlockFast(level, i, cposy - j, k, net.minecraft.world.level.block.Blocks.LADDER, 2, 2);
        }
        this.makeincagraves(level, cposx - baseheight, cposy - baseheight, cposz - baseheight, basewidth, basedepth);
    }

    private void makepoolalter(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        for (int i = -1; i <= 1; ++i) {
            for (int k = -1; k <= 1; ++k) {
                ChaosPersists.setBlockFast(
                        level, cposx + i, cposy + 1, cposz + k, net.minecraft.world.level.block.Blocks.COBBLESTONE, 0, 2);
            }
        }
        ChaosPersists.setBlockFast(level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.WATER, 0, 2);
    }

    private void makeincagraves(
            net.minecraft.world.level.Level level, int cposx, int cposy, int cposz, int width, int depth) {
        int i;
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(level, cposx + i, cposy, cposz + 5, 1);
        }
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(level, cposx + i, cposy, cposz + 10, 1);
        }
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(level, cposx + i, cposy, cposz + 20, 3);
        }
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(level, cposx + i, cposy, cposz + 25, 3);
        }
    }

    private void makeincagrave(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz, int dir) {
        net.minecraft.util.RandomSource rand = level.getRandom();
        if (dir == 1) {
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy, cposz, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy, cposz + 1, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy + 1, cposz + 1, net.minecraft.world.level.block.Blocks.DANDELION, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy, cposz + 2, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy, cposz, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy, cposz + 1, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy + 1, cposz + 1, net.minecraft.world.level.block.Blocks.DANDELION, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy, cposz + 2, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx, cposy + 1, cposz + 1, net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB, 0, 2);
            if (rand.nextInt(3) == 1) {
                this.placeLevelSpawner(level, cposx, cposy + 2, cposz, "chaospersists", "ghost");
            }
            this.fillLevelChestAt(level, rand, cposx, cposy + 1, cposz - 1, 2, this.IncaPyramidContentsList, 10 + rand.nextInt(5));
        }
        if (dir == 3) {
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy, cposz, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy, cposz - 1, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy + 1, cposz - 1, net.minecraft.world.level.block.Blocks.DANDELION, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy, cposz - 2, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx - 1, cposy + 1, cposz - 2, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy, cposz, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy, cposz - 1, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy + 1, cposz - 1, net.minecraft.world.level.block.Blocks.DANDELION, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy, cposz - 2, net.minecraft.world.level.block.Blocks.GRASS_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + 1, cposy + 1, cposz - 2, net.minecraft.world.level.block.Blocks.POPPY, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx, cposy + 1, cposz - 1, net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB, 0, 2);
            ChaosPersists.setBlockFast(
                    level, cposx, cposy + 1, cposz - 2, net.minecraft.world.level.block.Blocks.SMOOTH_STONE_SLAB, 0, 2);
            if (rand.nextInt(3) == 1) {
                this.placeLevelSpawner(level, cposx, cposy + 2, cposz, "chaospersists", "ghost");
            }
            this.fillLevelChestAt(level, rand, cposx, cposy + 1, cposz + 1, 2, this.IncaPyramidContentsList, 10 + rand.nextInt(5));
        }
    }

    public void makeRobotLab(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int width = 10;
        int length = 20;
        int height = 5;
        if (level.isClientSide()) {
            return;
        }
        for (int j = 0; j <= height; ++j) {
            for (int i = 0; i < width; ++i) {
                for (int k = 0; k < length; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        if (i == width / 2 || i == width / 2 - 1) {
                            bid = net.minecraft.world.level.block.Blocks.IRON_BLOCK;
                        }
                    }
                    if (j == height) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                            bid = net.minecraft.world.level.block.Blocks.AIR;
                        }
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        this.placeLevelDoor(
                level,
                cposx + width / 2,
                cposy + 1,
                cposz,
                net.minecraft.core.Direction.SOUTH,
                net.minecraft.world.level.block.Blocks.IRON_DOOR,
                net.minecraft.world.level.block.state.properties.DoorHingeSide.LEFT);
        this.placeLevelDoor(
                level,
                cposx + width / 2 - 1,
                cposy + 1,
                cposz,
                net.minecraft.core.Direction.SOUTH,
                net.minecraft.world.level.block.Blocks.IRON_DOOR,
                net.minecraft.world.level.block.state.properties.DoorHingeSide.RIGHT);
        ChaosPersists.setBlockFast(
                level, cposx + width / 2 - 2, cposy + 2, cposz - 1, net.minecraft.world.level.block.Blocks.STONE_BUTTON, 4, 2);
        ChaosPersists.setBlockFast(
                level, cposx + width / 2 + 1, cposy + 2, cposz - 1, net.minecraft.world.level.block.Blocks.STONE_BUTTON, 4, 2);
        this.makerobomain(level, cposx, cposy, cposz + length - 1);
        this.makerobopillar(level, cposx, cposy, cposz + length / 3, 0);
        this.makerobopillar(level, cposx, cposy, cposz + length * 2 / 3, 0);
        this.makerobopillar(level, cposx, cposy, cposz + (length - 1), 0);
        this.makerobopillar(level, cposx + width - 1, cposy, cposz + length / 3, 1);
        this.makerobopillar(level, cposx + width - 1, cposy, cposz + length * 2 / 3, 1);
        this.makerobopillar(level, cposx + width - 1, cposy, cposz + (length - 1), 1);
    }

    private void makerobopillar(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz, int dir) {
        for (int j = 0; j < 5; ++j) {
            for (int i = -1; i < 2; ++i) {
                for (int k = -1; k < 2; ++k) {
                    net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    if (j == 2 || j == 3) {
                        if (k == 0 && (i == -1 || i == 1)) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 0 && (k == -1 || k == 1)) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        if (dir == 0) {
            this.placeLevelSpawner(level, cposx + 1, cposy + 1, cposz, "chaospersists", "robo-sniper");
        }
        if (dir == 1) {
            this.placeLevelSpawner(level, cposx - 1, cposy + 1, cposz, "chaospersists", "robo-sniper");
        }
    }

    public void makerobomain(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int width = 30;
        int length = 30;
        int height = 9;
        cposx -= 10;
        for (int j = 0; j <= height; ++j) {
            for (int i = 0; i < width; ++i) {
                for (int k = 0; k < length; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        if (i == width / 2 || i == width / 2 - 1) {
                            bid = net.minecraft.world.level.block.Blocks.IRON_BLOCK;
                        }
                    }
                    if (j == height) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                            bid = net.minecraft.world.level.block.Blocks.AIR;
                        }
                    }
                    if ((j == 1 || j == 2 || j == 3) && k == 0 && i >= width / 3 && i < width * 2 / 3) {
                        bid = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        this.makeroboaltar(level, cposx + width / 2 - 4, cposy, cposz + 6);
        this.makeroborailway(level, cposx + 3, cposy, cposz + 10);
        this.makeroboassemblyline(level, cposx + width - 4, cposy, cposz + 4);
        this.makerobotreasureroom(level, cposx + 9, cposy, cposz + 18);
        this.makerobotower(level, cposx + width / 2 - 6, cposy + height, cposz + length / 2 - 6);
    }

    public void makerobotower(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int i;
        int j;
        int k;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        for (j = 0; j < 2; ++j) {
            for (i = 0; i < 12; ++i) {
                for (k = 0; k < 12; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 1) {
                        if (i == 0 || k == 0 || i == 11 || k == 11) {
                            bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                        }
                        if (i == 0 && (k == 0 || k == 11)) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 11 && (k == 0 || k == 11)) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                    }
                    if (j == 0) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        this.makerobopillar(level, cposx + 4, cposy + 1, cposz + 4, 1);
        this.makerobopillar(level, cposx + 7, cposy + 1, cposz + 7, 0);
        this.makerobopillar(level, cposx + 4, cposy + 1, cposz + 7, 1);
        this.makerobopillar(level, cposx + 7, cposy + 1, cposz + 4, 0);
        for (j = 5; j < 35; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 3; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (j < 15) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    } else if (j < 25) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        if (k == 2) {
                            bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                        }
                    } else {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        if (k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                        }
                        if (k == 2) {
                            bid = net.minecraft.world.level.block.Blocks.AIR;
                        }
                    }
                    ChaosPersists.setBlockFast(level, cposx + i + 5, cposy + j, cposz + k + 5, bid, 0, 2);
                }
            }
        }
    }

    public void makeroboaltar(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int i;
        int k;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.IRON_BLOCK;
        for (i = 0; i < 8; ++i) {
            for (k = 0; k < 8; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy, cposz + k, bid, 0, 2);
            }
        }
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = 0; i < 6; ++i) {
            for (k = 0; k < 6; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i + 1, cposy + 1, cposz + k + 1, bid, 0, 2);
            }
        }
        ChaosPersists.setBlockFast(
                level, cposx + 2, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 2, cposy + 2, cposz + 2, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(
                level, cposx + 5, cposy + 1, cposz + 5, net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 5, cposy + 2, cposz + 5, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(
                level, cposx + 5, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 5, cposy + 2, cposz + 2, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        ChaosPersists.setBlockFast(
                level, cposx + 2, cposy + 1, cposz + 5, net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 2, cposy + 2, cposz + 5, net.minecraft.world.level.block.Blocks.TORCH, 0, 2);
        this.placeLevelSpawner(level, cposx + 3, cposy + 2, cposz + 3, "chaospersists", "robo-pounder");
        this.placeLevelSpawner(level, cposx + 4, cposy + 2, cposz + 4, "chaospersists", "robo-pounder");
    }

    public void makeroborailway(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 0, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 0, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 1, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 1, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.POWERED_RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 1, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.LEVER, 5, 2);
        ChaosPersists.setBlockFast(level, cposx + 2, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.LEVER, 5, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 2, net.minecraft.world.level.block.Blocks.POWERED_RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 3, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 3, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 4, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 4, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 5, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 5, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 6, net.minecraft.world.level.block.Blocks.POWERED_RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 1, cposy + 1, cposz + 6, net.minecraft.world.level.block.Blocks.LEVER, 5, 2);
        ChaosPersists.setBlockFast(level, cposx + 2, cposy + 1, cposz + 6, net.minecraft.world.level.block.Blocks.LEVER, 5, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 6, net.minecraft.world.level.block.Blocks.POWERED_RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 7, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 7, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 8, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 8, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 9, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 9, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 10, net.minecraft.world.level.block.Blocks.POWERED_RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 1, cposy + 1, cposz + 10, net.minecraft.world.level.block.Blocks.LEVER, 5, 2);
        ChaosPersists.setBlockFast(level, cposx + 2, cposy + 1, cposz + 10, net.minecraft.world.level.block.Blocks.LEVER, 5, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 10, net.minecraft.world.level.block.Blocks.POWERED_RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 11, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 11, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 0, cposy + 1, cposz + 12, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 3, cposy + 1, cposz + 12, net.minecraft.world.level.block.Blocks.RAIL, 0, 2);
    }

    public void makeroboassemblyline(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        for (int k = 0; k < 24; ++k) {
            if (k % 3 == 1) {
                ChaosPersists.setBlockFast(
                        level, cposx - 2, cposy + 1, cposz + k, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 1, 2);
                ChaosPersists.setBlockFast(
                        level, cposx, cposy + 2, cposz + k, net.minecraft.world.level.block.Blocks.STICKY_PISTON, 3, 2);
                ChaosPersists.setBlockFast(
                        level, cposx, cposy + 3, cposz + k, net.minecraft.world.level.block.Blocks.WHITE_CARPET, 0, 2);
            }
            if (k % 3 == 0) {
                ChaosPersists.setBlockFast(level, cposx, cposy + 2, cposz + k, net.minecraft.world.level.block.Blocks.LEVER, 13, 2);
            }
            ChaosPersists.setBlockFast(level, cposx, cposy + 1, cposz + k, net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + 1, cposy + 1, cposz + k, net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK, 0, 2);
        }
    }

    public void makerobotreasureroom(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block air = net.minecraft.world.level.block.Blocks.AIR;
        for (int j = 1; j < 7; ++j) {
            for (int i = 0; i < 12; ++i) {
                for (int k = 0; k < 8; ++k) {
                    bid = air;
                    if (i == 0 || k == 0 || i == 11 || k == 7) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 2 && i == 11) {
                        bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    if (j == 3 && bid != air) {
                        bid = net.minecraft.world.level.block.Blocks.IRON_BARS;
                    }
                    if (!(j != 1 && j != 2 && j != 3 || k != 0 || i != 1 && i != 2)) {
                        bid = air;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        this.placeLevelSpawner(level, cposx + 10, cposy + 1, cposz + 1, "chaospersists", "robo-warrior");
        this.fillLevelChestAt(level, rand, cposx + 8, cposy + 1, cposz + 1, 2, this.RobotContentsList, 10 + rand.nextInt(5));
        this.fillLevelChestAt(level, rand, cposx + 6, cposy + 1, cposz + 1, 2, this.RobotContentsList, 10 + rand.nextInt(5));
    }

    public void makeKingAltar(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int k;
        int i;
        int j;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int width = 51;
        int length = 51;
        int height = 48;
        if (level.isClientSide()) {
            return;
        }
        for (j = 0; j <= height + 10; ++j) {
            for (i = -5; i < width + 5; ++i) {
                for (k = -5; k < length + 5; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        j = 0;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                for (int v = 1; v < 10; ++v) {
                    net.minecraft.core.BlockPos checkPos = new net.minecraft.core.BlockPos(cposx + i, cposy + j - v, cposz + k);
                    net.minecraft.world.level.block.state.BlockState checkState = level.getBlockState(checkPos);
                    if (!checkState.isAir()
                            && !checkState.is(net.minecraft.world.level.block.Blocks.TALL_GRASS)
                            && !checkState.is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    bid = net.minecraft.world.level.block.Blocks.DIRT;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j - v, cposz + k, bid, 0, 2);
                }
            }
        }
        this.makekingcolumn(level, cposx + 1, cposy + 1, cposz + 1);
        this.makekingcolumn(level, cposx + width - 8, cposy + 1, cposz + length - 8);
        this.makekingcolumn(level, cposx + 1, cposy + 1, cposz + length - 8);
        this.makekingcolumn(level, cposx + width - 8, cposy + 1, cposz + 1);
        j = height - 1;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        j = height;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = -1; i <= width; ++i) {
            for (k = -1; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        this.makekingbackground(level, cposx + 4, cposy + 10, cposz + 9);
        this.makekingcenteraltar(level, cposx + width / 2, cposy, cposz + length / 2);
    }

    private void makekingcolumn(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int k;
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int meta = 0;
        int width = 5;
        int length = 5;
        int height = 44;
        if (level.isClientSide()) {
            return;
        }
        int j = 0;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = 0; i < width + 2; ++i) {
            for (k = 0; k < length + 2; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, meta, 2);
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + height + 1, cposz + k, bid, meta, 2);
            }
        }
        ++cposx;
        ++cposz;
        ++cposy;
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j % 4 == 0
                            && bid != net.minecraft.world.level.block.Blocks.AIR
                            && (i == 2 || k == 2)) {
                        bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                    }
                    if (j % 4 == 1 && bid != net.minecraft.world.level.block.Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                        }
                    }
                    if (j % 4 == 2 && bid != net.minecraft.world.level.block.Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                        }
                        if (i == 2 || k == 2) {
                            bid = net.minecraft.world.level.block.Blocks.EMERALD_BLOCK;
                        }
                    }
                    if (j % 4 == 3 && bid != net.minecraft.world.level.block.Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = net.minecraft.world.level.block.Blocks.GOLD_BLOCK;
                        }
                    }
                    meta = 0;
                    if (bid == net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK) {
                        meta = 2;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, meta, 2);
                }
            }
        }
    }

    private void makekingbackground(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        boolean meta = false;
        int curz = 0;
        int cury = 0;
        int height = 33;
        int width = 33;
        bid = net.minecraft.world.level.block.Blocks.STONE;
        for (int m = 0; m < this.king.length; ++m) {
            int v = this.king[m];
            if (v < 0) {
                bid = net.minecraft.world.level.block.Blocks.STONE;
                while (curz < width) {
                    ChaosPersists.setBlockFast(level, cposx, cposy + cury, cposz + curz, bid, 0, 2);
                    ++curz;
                }
                ++cury;
                curz = 0;
                continue;
            }
            for (int n = 0; n < v; ++n) {
                ChaosPersists.setBlockFast(level, cposx, cposy + cury, cposz + curz, bid, 0, 2);
                ++curz;
            }
            bid = bid == net.minecraft.world.level.block.Blocks.STONE
                    ? net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK
                    : net.minecraft.world.level.block.Blocks.STONE;
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy - 1, cposz + i, net.minecraft.world.level.block.Blocks.GOLD_BLOCK, 0, 2);
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy + height, cposz + i, net.minecraft.world.level.block.Blocks.GOLD_BLOCK, 0, 2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy + i, cposz - 1, net.minecraft.world.level.block.Blocks.GOLD_BLOCK, 0, 2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy + i, cposz + width, net.minecraft.world.level.block.Blocks.GOLD_BLOCK, 0, 2);
        }
        ChaosPersists.setBlockFast(level, cposx, cposy - 2, cposz - 2, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 1, cposz + width + 1, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy - 2, cposz + width + 1, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 1, cposz - 2, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy - 1, cposz - 2, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 2, cposz + width + 1, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy - 1, cposz + width + 1, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 2, cposz - 2, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
    }

    private void makekingcenteraltar(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int k;
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        boolean meta = false;
        int width = 10;
        int length = 10;
        int j = 0;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 6;
        length = 20;
        j = 0;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 20;
        length = 6;
        j = 0;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 8;
        length = 8;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 4;
        length = 18;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                if (i == width && (k == - length || k == length)) {
                    bid = net.minecraft.world.level.block.Blocks.LAPIS_BLOCK;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = net.minecraft.world.level.block.Blocks.LAPIS_BLOCK;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 18;
        length = 4;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                if (i == width && (k == - length || k == length)) {
                    bid = net.minecraft.world.level.block.Blocks.LAPIS_BLOCK;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = net.minecraft.world.level.block.Blocks.LAPIS_BLOCK;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 7;
        length = 7;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
            }
        }
        width = 3;
        length = 17;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 17;
        length = 3;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 6;
        length = 6;
        j = 3;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 2;
        length = 16;
        j = 3;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 16;
        length = 2;
        j = 3;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 2;
        length = 2;
        j = 4;
        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
            }
        }
        level.setBlock(new net.minecraft.core.BlockPos(cposx, cposy + j, cposz), net.minecraft.world.level.block.Blocks.CHEST.defaultBlockState().setValue(net.minecraft.world.level.block.ChestBlock.FACING, chestFacingFromLegacyMeta(2)), 3);
        net.minecraft.world.level.block.entity.BlockEntity kingChestBe = level.getBlockEntity(new net.minecraft.core.BlockPos(cposx, cposy + j, cposz));
        if (kingChestBe instanceof net.minecraft.world.level.block.entity.ChestBlockEntity kingChest) {
            kingChest.setItem(13, new net.minecraft.world.item.ItemStack((net.minecraft.world.item.Item)(Object)ChaosPersists.TheKingEgg));
        }
    }

    public void makeLeonNest(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int j;
        int k;
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int rad = 10;
        int dist = 0;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (j = 0; j <= rad; ++j) {
            for (i = -rad; i <= rad; ++i) {
                for (k = -rad; k <= rad; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    dist = j * j + i * i + k * k;
                    if ((dist = (int) Math.sqrt(dist)) > rad) continue;
                    if (dist >= rad - 2) {
                        int which = rand.nextInt(6);
                        if (which == 0) {
                            bid = net.minecraft.world.level.block.Blocks.OAK_LEAVES;
                        }
                        if (which == 1) {
                            bid = net.minecraft.world.level.block.Blocks.OAK_LOG;
                        }
                        if (which == 2) {
                            bid = net.minecraft.world.level.block.Blocks.OAK_PLANKS;
                        }
                        if (which == 3) {
                            bid = net.minecraft.world.level.block.Blocks.DIRT;
                        }
                        if (which == 4) {
                            bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                        }
                        if (which == 5) {
                            bid = net.minecraft.world.level.block.Blocks.MOSSY_COBBLESTONE;
                        }
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy - j, cposz + k, bid, 0, 2);
                }
            }
        }
        for (j = 1; j <= 5; ++j) {
            for (i = -rad; i <= rad; ++i) {
                for (k = -rad; k <= rad; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        this.placeLevelSpawner(level, cposx, cposy - (rad - 4), cposz, "chaospersists", "leonopteryx");
    }

    public void makeCephadromeAltar(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int k;
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int width = 4;
        int length = 4;
        int j = 0;
        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 3;
        length = 3;
        j = 1;
        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (k == 0 || i == 0) {
                    bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                }
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 3;
        length = 3;
        j = 2;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.AIR;
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 3;
        length = 3;
        j = 3;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.AIR;
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = net.minecraft.world.level.block.Blocks.END_STONE;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 3;
        length = 3;
        j = 4;
        net.minecraft.world.level.block.Block extremeTorch =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.ExtremeTorch;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.AIR;
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = extremeTorch;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 2;
        length = 2;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (k == 0 || i == 0) {
                    bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                }
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = net.minecraft.world.level.block.Blocks.STONE_BRICKS;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 1;
        length = 1;
        j = 3;
        net.minecraft.world.level.block.Block eyeOfEnderBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyEyeOfEnderBlock;
        bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.COBBLESTONE;
                if (k == 0 && i == 0) {
                    bid = eyeOfEnderBlock;
                }
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = net.minecraft.world.level.block.Blocks.END_STONE;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
    }

    public void makeCrystalBattleTower(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int j;
        float curx;
        float curdeg;
        float currad;
        float curz;
        net.minecraft.world.level.block.Block blk =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalStone;
        net.minecraft.world.level.block.Block crystalCrystal =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalCrystal;
        float radius = 10.0f;
        for (j = 0; j <= 20; ++j) {
            blk = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalStone;
            if (j % 5 == 0) {
                for (currad = 0.0f; currad < radius; currad += 0.33f) {
                    for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                        curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                        curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                        this.FastSetBlock(
                                level,
                                (int)((float)cposx + curx + 0.5f),
                                cposy + j,
                                (int)((float)cposz + curz + 0.5f),
                                blk);
                    }
                }
                continue;
            }
            currad = 10.0f;
            for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                blk = (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalStone;
                if (j % 5 >= 1 && j % 5 <= 3 && (curdeg < 10.0f || curdeg > 350.0f)) {
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                }
                this.FastSetBlock(
                        level,
                        (int)((float)cposx + curx + 0.5f),
                        cposy + j,
                        (int)((float)cposz + curz + 0.5f),
                        blk);
            }
        }
        radius = 10.0f;
        for (j = 21; j <= 22; ++j) {
            blk = crystalCrystal;
            currad = 10.0f;
            for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                this.FastSetBlock(
                        level,
                        (int)((float)cposx + curx + 0.5f),
                        cposy + j,
                        (int)((float)cposz + curz + 0.5f),
                        blk);
            }
        }
        j = 1;
        this.placeLevelSpawner(level, cposx, cposy + j + 1, cposz, "chaospersists", "rat");
        this.placeLevelSpawner(level, cposx, cposy + j + 2, cposz, "chaospersists", "rat");
        this.fillLevelChestAt(
                level, rand, cposx, cposy + j, cposz, 0, this.CrystalBattleTowerRatContentsList, 5 + rand.nextInt(5));
        j = 6;
        this.placeLevelSpawner(level, cposx, cposy + j + 1, cposz, "chaospersists", "dungeon_beast");
        this.placeLevelSpawner(level, cposx, cposy + j + 2, cposz, "chaospersists", "dungeon_beast");
        this.fillLevelChestAt(
                level,
                rand,
                cposx,
                cposy + j,
                cposz,
                0,
                this.CrystalBattleTowerDungeonBeastContentsList,
                5 + rand.nextInt(5));
        j = 11;
        this.placeLevelSpawner(level, cposx, cposy + j + 1, cposz, "chaospersists", "crystal_urchin");
        this.placeLevelSpawner(level, cposx, cposy + j + 2, cposz, "chaospersists", "crystal_urchin");
        this.fillLevelChestAt(
                level,
                rand,
                cposx,
                cposy + j,
                cposz,
                0,
                this.CrystalBattleTowerUrchinContentsList,
                5 + rand.nextInt(5));
        j = 16;
        this.placeLevelSpawner(level, cposx, cposy + j + 1, cposz, "chaospersists", "rotator");
        this.placeLevelSpawner(level, cposx, cposy + j + 2, cposz, "chaospersists", "rotator");
        this.fillLevelChestAt(
                level,
                rand,
                cposx,
                cposy + j,
                cposz,
                0,
                this.CrystalBattleTowerRotatorContentsList,
                5 + rand.nextInt(5));
        j = 21;
        this.placeLevelSpawner(level, cposx, cposy + j + 1, cposz, "chaospersists", "vortex");
        this.placeLevelSpawner(level, cposx, cposy + j + 2, cposz, "chaospersists", "vortex");
        this.fillLevelChestAt(
                level,
                rand,
                cposx,
                cposy + j,
                cposz,
                0,
                this.CrystalBattleTowerVortexContentsList,
                6 + rand.nextInt(6));
    }

    public void makeGirlfriendIsland(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int j;
        int i;
        for (i = -5; i <= 5; ++i) {
            int k = 3;
            if (i == -5 || i == 5) {
                k = 1;
            }
            if (i == -4 || i == 4) {
                k = 2;
            }
            if (i == -3 || i == 3) {
                k = 2;
            }
            for (j = - k; j <= k; ++j) {
                this.FastSetBlock(level, cposx + i, cposy, cposz + j, net.minecraft.world.level.block.Blocks.SAND);
                this.FastSetBlock(level, cposx + i, cposy - 1, cposz + j, net.minecraft.world.level.block.Blocks.STONE);
            }
        }
        for (i = -2; i <= 2; ++i) {
            for (j = -2; j <= 2; ++j) {
                this.FastSetBlock(level, cposx + i, cposy + 3, cposz + j, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
            }
        }
        this.FastSetBlock(level, cposx, cposy + 4, cposz, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
        this.FastSetBlock(level, cposx, cposy + 3, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx + 1, cposy + 3, cposz + 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx - 1, cposy + 3, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx + 1, cposy + 3, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx - 1, cposy + 3, cposz + 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.placeLevelSpawner(level, cposx + 1, cposy + 3, cposz, "chaospersists", "girlfriend");
        this.placeLevelSpawner(level, cposx - 1, cposy + 3, cposz, "chaospersists", "boyfriend");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz + 1, "chaospersists", "gold_fish");
        this.placeLevelSpawner(level, cposx, cposy + 3, cposz - 1, "chaospersists", "gold_fish");
        this.fillLevelChestAt(
                level, rand, cposx, cposy + 1, cposz - 1, 0, this.DamselContentsList, 4 + rand.nextInt(5));
        this.fillLevelChestAt(
                level, rand, cposx, cposy + 1, cposz + 1, 0, this.DamselContentsList, 4 + rand.nextInt(5));
    }

    public void makeGreenhouseDungeon(Object worldObj, int cposx, int cposy, int cposz) {
        int k;
        int i;
        int j;
        int height = 7;
        int width = 15;
        int length = 23;
        int t = 0;
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        for (i = 0; i < length; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == length - 1 || k == width - 1) {
                        blk = net.minecraft.world.level.block.Blocks.GLASS;
                    }
                    if (j == height - 1) {
                        blk = net.minecraft.world.level.block.Blocks.IRON_BLOCK;
                        if (i % 4 == 3 && k % 4 == 3) {
                            blk = net.minecraft.world.level.block.Blocks.GLOWSTONE;
                        }
                        if (k % 4 == 1) {
                            blk = net.minecraft.world.level.block.Blocks.GLASS;
                        }
                    }
                    if (j == 0) {
                        blk = net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
                        if (i != 0 && k != 0 && i != length - 1 && k != width - 1 && i % 3 == 2) {
                            blk = net.minecraft.world.level.block.Blocks.WATER;
                        }
                    }
                    if (j == 1 && i != 0 && k != 0 && i != length - 1 && k != width - 1 && i % 3 != 2 && rand.nextInt(3) != 1) {
                        blk = net.minecraft.world.level.block.Blocks.FARMLAND;
                        this.FastSetBlock(level, cposx + i, cposy + j - 1, cposz + k, blk);
                        t = rand.nextInt(20);
                        blk = net.minecraft.world.level.block.Blocks.AIR;
                        if (t == 0) {
                            blk = net.minecraft.world.level.block.Blocks.DANDELION;
                        }
                        if (t == 1) {
                            blk = net.minecraft.world.level.block.Blocks.POPPY;
                        }
                        if (t == 2) {
                            blk = net.minecraft.world.level.block.Blocks.BROWN_MUSHROOM;
                        }
                        if (t == 3) {
                            blk = net.minecraft.world.level.block.Blocks.RED_MUSHROOM;
                        }
                        if (t == 4) {
                            blk = net.minecraft.world.level.block.Blocks.WHEAT;
                        }
                        if (t == 5) {
                            blk = net.minecraft.world.level.block.Blocks.CARROTS;
                        }
                        if (t == 6) {
                            blk = net.minecraft.world.level.block.Blocks.POTATOES;
                        }
                        if (t == 7) {
                            blk = net.minecraft.world.level.block.Blocks.SUGAR_CANE;
                        }
                        if (t == 9) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyCornPlant1;
                        }
                        if (t == 10) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyTomatoPlant1;
                        }
                        if (t == 11) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyStrawberryPlant;
                        }
                        if (t == 12) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyButterflyPlant;
                        }
                        if (t == 13) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyMothPlant;
                        }
                        if (t == 14) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyRadishPlant;
                        }
                        if (t == 15) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyLettucePlant1;
                        }
                        if (t == 16) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyFlowerPinkBlock;
                        }
                        if (t == 17) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyFlowerBlueBlock;
                        }
                        if (t == 18) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyQuinoaPlant1;
                        }
                        if (t == 19) {
                            blk = (net.minecraft.world.level.block.Block)(Object)ChaosPersists.MyRicePlant;
                        }
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 0; i < length; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = height; j <= height + 6; ++j) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.AIR);
                }
            }
        }
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 1, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        this.placeLevelDoor(
                level,
                cposx + width / 2,
                cposy + 1,
                cposz,
                net.minecraft.core.Direction.SOUTH,
                net.minecraft.world.level.block.Blocks.IRON_DOOR,
                net.minecraft.world.level.block.state.properties.DoorHingeSide.LEFT);
        this.placeLevelDoor(
                level,
                cposx + width / 2 - 1,
                cposy + 1,
                cposz,
                net.minecraft.core.Direction.SOUTH,
                net.minecraft.world.level.block.Blocks.IRON_DOOR,
                net.minecraft.world.level.block.state.properties.DoorHingeSide.RIGHT);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 2, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 1, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.STONE, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 - 2, cposy + 2, cposz - 1, net.minecraft.world.level.block.Blocks.STONE_BUTTON, 4, 2);
        ChaosPersists.setBlockFast(level, cposx + width / 2 + 1, cposy + 2, cposz - 1, net.minecraft.world.level.block.Blocks.STONE_BUTTON, 4, 2);
        i = length / 2;
        k = width / 2;
        j = height + 1;
        this.placeLevelSpawner(level, cposx + i, cposy + j, cposz + k, "chaospersists", "triffid");
        this.placeLevelSpawner(level, cposx + i, cposy + height + 2, cposz + k, "chaospersists", "triffid");
        this.fillLevelChestAt(level, rand, cposx + i, cposy + height, cposz + k, 0, this.GreenhouseContentsList, 5 + rand.nextInt(5));
    }

    public void makeMonsterIsland(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int j;
        int i;
        String monster = "Sea Viper";
        if (rand.nextInt(2) == 0) {
            monster = "Sea Monster";
        }
        for (i = -5; i <= 5; ++i) {
            int k = 3;
            if (i == -5 || i == 5) {
                k = 1;
            }
            if (i == -4 || i == 4) {
                k = 2;
            }
            if (i == -3 || i == 3) {
                k = 2;
            }
            for (j = - k; j <= k; ++j) {
                this.FastSetBlock(level, cposx + i, cposy, cposz + j, net.minecraft.world.level.block.Blocks.SAND);
                this.FastSetBlock(level, cposx + i, cposy - 1, cposz + j, net.minecraft.world.level.block.Blocks.STONE);
            }
        }
        for (i = -2; i <= 2; ++i) {
            for (j = -2; j <= 2; ++j) {
                this.FastSetBlock(level, cposx + i, cposy + 3, cposz + j, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
            }
        }
        this.FastSetBlock(level, cposx, cposy + 4, cposz, net.minecraft.world.level.block.Blocks.OAK_LEAVES);
        this.FastSetBlock(level, cposx, cposy + 3, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx, cposy + 2, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx + 1, cposy + 3, cposz + 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx - 1, cposy + 3, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx + 1, cposy + 3, cposz - 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.FastSetBlock(level, cposx - 1, cposy + 3, cposz + 1, net.minecraft.world.level.block.Blocks.OAK_LOG);
        this.placeLevelSpawnerFromLegacyMobName(level, cposx + 1, cposy + 3, cposz, monster);
        this.placeLevelSpawnerFromLegacyMobName(level, cposx - 1, cposy + 3, cposz, monster);
        this.placeLevelSpawnerFromLegacyMobName(level, cposx, cposy + 3, cposz + 1, monster);
        this.placeLevelSpawnerFromLegacyMobName(level, cposx, cposy + 3, cposz - 1, monster);
        this.fillLevelChestAt(
                level,
                rand,
                cposx,
                cposy + 1,
                cposz - 1,
                0,
                this.MonsterIslandContentsList,
                4 + rand.nextInt(5));
        this.fillLevelChestAt(
                level,
                rand,
                cposx,
                cposy + 1,
                cposz + 1,
                0,
                this.MonsterIslandContentsList,
                4 + rand.nextInt(5));
    }

    public void makeNightmareRookery(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int h = 0;
        int k = 0;
        int j = 0;
        int i = 0;
        block0 : for (i = -5; i <= 20; ++i) {
            k += rand.nextInt(3) - 1;
            h = rand.nextInt(20) + 1;
            for (j = 0; j < h; ++j) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE);
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i + 1, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i - 1, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 1, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k - 1, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (j < 18) continue;
                this.placeLevelSpawner(level, cposx + i, cposy + j + 2, cposz + k, "chaospersists", "nightmare");
                this.fillLevelChestAt(
                        level, rand, cposx + i, cposy + j + 1, cposz + k, 0, this.NightmareRookeryContentsList, 4 + rand.nextInt(5));
                if (!(level.getBlockEntity(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k))
                        instanceof net.minecraft.world.level.block.entity.ChestBlockEntity)) {
                    continue block0;
                }
                continue block0;
            }
        }
        block2 : for (i = -5; i <= 20; ++i) {
            k += rand.nextInt(3) - 1;
            h = rand.nextInt(20) + 1;
            for (j = 0; j < h; ++j) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE);
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i + 1, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i - 1, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k + 1, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (rand.nextInt(j + 5) == 1) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k - 1, net.minecraft.world.level.block.Blocks.STONE);
                }
                if (j < 18) continue;
                this.placeLevelSpawner(level, cposx + i, cposy + j + 2, cposz + k, "chaospersists", "nightmare");
                this.fillLevelChestAt(
                        level, rand, cposx + i, cposy + j + 1, cposz + k, 0, this.NightmareRookeryContentsList, 4 + rand.nextInt(5));
                if (!(level.getBlockEntity(new net.minecraft.core.BlockPos(cposx + i, cposy + j + 1, cposz + k))
                        instanceof net.minecraft.world.level.block.entity.ChestBlockEntity)) {
                    continue block2;
                }
                continue block2;
            }
        }
    }

    public void makeStinkyHouse(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int k;
        int i;
        net.minecraft.world.level.block.Block bid;
        int height = 2;
        int width = 9;
        int length = 12;
        int yardwidth = 16;
        int yardlength = 24;
        for (i = 0; i <= yardlength; ++i) {
            for (k = 0; k <= yardwidth; ++k) {
                bid = net.minecraft.world.level.block.Blocks.AIR;
                if (i == 0 || i == yardlength || k == 0 || k == yardwidth) {
                    bid = net.minecraft.world.level.block.Blocks.OAK_FENCE;
                }
                if (bid == net.minecraft.world.level.block.Blocks.OAK_FENCE && rand.nextInt(3) == 1) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                }
                if (bid == net.minecraft.world.level.block.Blocks.AIR && rand.nextInt(10) == 1) {
                    bid = net.minecraft.world.level.block.Blocks.DEAD_BUSH;
                }
                if (bid == net.minecraft.world.level.block.Blocks.AIR) {
                    continue;
                }
                this.FastSetBlock(level, cposx + i - 5, cposy + 1, cposz + k - 4, bid);
            }
        }
        for (i = 0; i <= length; ++i) {
            for (k = 0; k <= width; ++k) {
                for (int j = 0; j <= height; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || i == length || k == 0 || k == width) {
                        bid = net.minecraft.world.level.block.Blocks.OAK_PLANKS;
                    }
                    if (bid == net.minecraft.world.level.block.Blocks.OAK_PLANKS
                            && j == 1
                            && (i == 1 || i == length - 1 || k == 1 || k == width - 1)) {
                        bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                    }
                    if (j == height) {
                        bid = net.minecraft.world.level.block.Blocks.OAK_PLANKS;
                    }
                    if (rand.nextInt(10) == 1) {
                        bid = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    if (!(j != 0 && j != 1 || i != 0 || k != width / 2 && k != width / 2 + 1)) {
                        bid = net.minecraft.world.level.block.Blocks.AIR;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j + 1, cposz + k, bid);
                }
            }
        }
        this.placeLevelSpawner(level, cposx + 2, cposy + 1, cposz + 2, "chaospersists", "stink_bug");
        this.placeLevelSpawner(level, cposx + length - 2, cposy + 1, cposz + width - 2, "chaospersists", "stinky");
        this.fillLevelChestAt(
                level, rand, cposx + length / 2, cposy + 1, cposz + width / 2, 0, this.StinkyHouseContentsList, 8 + rand.nextInt(5));
    }

    public void makeRubberDuckyPond(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        for (i = 0; i < 2; ++i) {
            this.placeLevelSpawner(level, cposx + i, cposy + 6, cposz, "chaospersists", "rubber_ducky");
        }
        this.placeLevelDoubleChest(level, cposx, cposy + 5, cposz, cposx + 1, cposy + 5, cposz, 0);
        this.fillExistingChestAt(
                level, rand, cposx + 1, cposy + 5, cposz, this.RubberDuckyContentsList, 8 + rand.nextInt(5));
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx, cposy + 4, cposz),
                net.minecraft.world.level.block.Blocks.GLASS.defaultBlockState(),
                2);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 1, cposy + 4, cposz),
                net.minecraft.world.level.block.Blocks.GLASS.defaultBlockState(),
                2);
        for (i = 0; i < 2; ++i) {
            level.setBlock(
                    new net.minecraft.core.BlockPos(cposx + i, cposy + 3, cposz),
                    net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                    3);
        }
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx - 1, cposy + 3, cposz),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 2, cposy + 3, cposz),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
        for (i = 0; i < 12; ++i) {
            for (int k = 0; k < 11; ++k) {
                this.FastSetBlock(
                        level,
                        cposx + i - 5,
                        cposy - 1,
                        cposz + k - 5,
                        net.minecraft.world.level.block.Blocks.SANDSTONE);
                bid = net.minecraft.world.level.block.Blocks.WATER;
                if (i == 0 || k == 0 || i == 11 || k == 10) {
                    bid = net.minecraft.world.level.block.Blocks.SAND;
                }
                this.FastSetBlock(level, cposx + i - 5, cposy, cposz + k - 5, bid);
                bid = net.minecraft.world.level.block.Blocks.AIR;
                this.FastSetBlock(level, cposx + i - 5, cposy + 1, cposz + k - 5, bid);
                this.FastSetBlock(level, cposx + i - 5, cposy + 2, cposz + k - 5, bid);
            }
        }
    }

    public void makeWhiteHouse(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        this.makefountain(level, cposx - 5, cposy, cposz - 15);
        this.makefountain(level, cposx + 15, cposy, cposz - 15);
        this.makewalkway(level, cposx + 7, cposy, cposz - 15);
        this.makewhbase(level, cposx - 4, cposy, cposz - 6);
        this.makewhwalls(level, cposx - 3, cposy + 2, cposz - 5);
        this.makewhroof(level, cposx - 4, cposy, cposz - 6);
        this.makewhinterior(level, cposx - 1, cposy + 2, cposz - 3);
    }

    private void makefountain(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block air = net.minecraft.world.level.block.Blocks.AIR;
        for (int i = 0; i < 7; ++i) {
            for (int k = 0; k < 5; ++k) {
                for (int j = 0; j < 15; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.WATER;
                    if (i == 0 || k == 0 || i == 6 || k == 4) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 1 && i == 3 && k == 2) {
                        bid = net.minecraft.world.level.block.Blocks.GLOWSTONE;
                    }
                    if (j > 1) {
                        bid = air;
                        if (j <= 4 && i == 3 && k == 2) {
                            bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        }
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, bid);
                }
            }
        }
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 3, cposy + 5, cposz + 2),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 2, cposy + 5, cposz + 2),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 4, cposy + 5, cposz + 2),
                net.minecraft.world.level.block.Blocks.WATER.defaultBlockState(),
                3);
    }

    private void makewalkway(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block air = net.minecraft.world.level.block.Blocks.AIR;
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 10; ++k) {
                for (int j = 0; j < 15; ++j) {
                    bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    if (j == 1) {
                        bid = air;
                        if (k > 6) {
                            bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                        }
                    }
                    if (j > 1) {
                        bid = air;
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, bid);
                }
            }
        }
    }

    private void makewhbase(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int i;
        int k;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block crystalTorch =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalTorch;
        for (i = 0; i < 25; ++i) {
            for (k = 0; k < 25; ++k) {
                bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                this.FastSetBlock(level, cposx + i, cposy + 1, cposz + k, bid);
                if (i != 0 && i != 24 || k != 0 && k != 24) continue;
                this.FastSetBlock(level, cposx + i, cposy + 2, cposz + k, crystalTorch);
            }
        }
        for (i = 1; i < 24; ++i) {
            for (k = 1; k < 24; ++k) {
                bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                this.FastSetBlock(level, cposx + i, cposy + 2, cposz + k, bid);
            }
        }
    }

    private void makewhwalls(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block air = net.minecraft.world.level.block.Blocks.AIR;
        for (int i = 0; i < 23; ++i) {
            for (int k = 0; k < 23; ++k) {
                for (int j = 0; j < 6; ++j) {
                    bid = air;
                    if (i == 0 || k == 0 || i == 22 || k == 22) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j != 0 && bid != air) {
                        if (k == 22) {
                            if ((j & 1) == 1) {
                                if ((i & 1) == 0 || (k & 1) == 0) {
                                    bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                                }
                            } else if ((i & 1) == 1 || (k & 1) == 1) {
                                bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                            }
                        } else if (k != 0) {
                            if ((j & 1) == 1) {
                                if (i == 2 || k == 2 || i == 20 || k == 20) {
                                    bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                                }
                            } else if (i == 1 || k == 1 || i == 21 || k == 21) {
                                bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                            }
                            if (j > 0 && j < 5 && k > 7 && k < 15) {
                                bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                            }
                        } else if ((j & 1) == 1) {
                            if (i == 2 || k == 2 || i == 20 || k == 20) {
                                bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                            }
                        } else if (i == 1 || k == 1 || i == 21 || k == 21) {
                            bid = net.minecraft.world.level.block.Blocks.GLASS_PANE;
                        }
                    }
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, bid);
                }
            }
        }
        ChaosPersists.setBlockFast(level, cposx + 11, cposy, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + 11, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.AIR, 0, 2);
        this.placeLevelDoor(
                level,
                cposx + 11,
                cposy,
                cposz,
                net.minecraft.core.Direction.SOUTH,
                net.minecraft.world.level.block.Blocks.IRON_DOOR);
        ChaosPersists.setBlockFast(
                level, cposx + 12, cposy + 1, cposz - 1, net.minecraft.world.level.block.Blocks.STONE_BUTTON, 4, 2);
    }

    private void makewhroof(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block air = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block crystalTorch =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalTorch;
        for (int j = 0; j < 13; ++j) {
            for (int i = 0; i < 25 - 2 * j; ++i) {
                for (int k = 0; k < 25 - 2 * j; ++k) {
                    bid = air;
                    if (i == 0 || k == 0 || i == 24 - 2 * j || k == 24 - 2 * j) {
                        bid = net.minecraft.world.level.block.Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0 && bid != air && (i + k & 1) == 1) {
                        bid = net.minecraft.world.level.block.Blocks.EMERALD_BLOCK;
                    }
                    if (j == 12) {
                        bid = net.minecraft.world.level.block.Blocks.EMERALD_BLOCK;
                    }
                    this.FastSetBlock(level, cposx + i + j, cposy + 8 + j, cposz + k + j, bid);
                    if (i != 0 && i != 24 - 2 * j || k != 0 && k != 24 - 2 * j) continue;
                    this.FastSetBlock(level, cposx + i + j, cposy + 8 + j + 1, cposz + k + j, crystalTorch);
                }
            }
        }
        bid = net.minecraft.world.level.block.Blocks.OAK_FENCE;
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 11, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 10, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 9, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 8, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 7, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 6, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 5, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 4, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 3, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 2, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 1, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 0, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 11, cposy + 8 + 0, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 13, cposy + 8 + 0, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 0, cposz + 11, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 0, cposz + 13, bid);
        bid = crystalTorch;
        this.FastSetBlock(level, cposx + 11, cposy + 8 + 1, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 13, cposy + 8 + 1, cposz + 12, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 1, cposz + 11, bid);
        this.FastSetBlock(level, cposx + 12, cposy + 8 + 1, cposz + 13, bid);
    }

    private void makewhinterior(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i;
        int zoff = 1;
        int xoff = 0;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 3, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 2, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 3, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 2, 2);
        }
        xoff = 11;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 3, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 2, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 3, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 2, 2);
        }
        zoff = 7;
        xoff = 0;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 3, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 2, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 3, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 2, 2);
        }
        xoff = 11;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 3, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 2, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 3, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 2, 2);
        }
        zoff = 13;
        xoff = 0;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 3, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 2, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 3, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 2, 2);
        }
        xoff = 11;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 3, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 1, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 2, net.minecraft.world.level.block.Blocks.PISTON_HEAD, 1, 2);
            ChaosPersists.setBlockFast(
                    level, cposx + xoff + i, cposy, cposz + zoff + 3, net.minecraft.world.level.block.Blocks.QUARTZ_STAIRS, 2, 2);
        }
        zoff = 18;
        xoff = 2;
        this.placeLevelSpawner(level, cposx + xoff, cposy + 1, cposz + zoff, "chaospersists", "criminal");
        this.fillLevelChestAt(level, rand, cposx + xoff, cposy, cposz + zoff, 0, this.WhiteHouseContentsList, 3 + rand.nextInt(5));
        xoff = 6;
        this.placeLevelSpawner(level, cposx + xoff, cposy + 1, cposz + zoff, "chaospersists", "criminal");
        this.fillLevelChestAt(level, rand, cposx + xoff, cposy, cposz + zoff, 0, this.WhiteHouseContentsList, 3 + rand.nextInt(5));
        xoff = 12;
        this.placeLevelSpawner(level, cposx + xoff, cposy + 1, cposz + zoff, "chaospersists", "criminal");
        this.fillLevelChestAt(level, rand, cposx + xoff, cposy, cposz + zoff, 0, this.WhiteHouseContentsList, 3 + rand.nextInt(5));
        xoff = 16;
        this.placeLevelSpawner(level, cposx + xoff, cposy + 1, cposz + zoff, "chaospersists", "criminal");
        this.fillLevelChestAt(level, rand, cposx + xoff, cposy, cposz + zoff, 0, this.WhiteHouseContentsList, 3 + rand.nextInt(5));
    }

    public void makeQueenAltar(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        int k;
        int i;
        int j;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        int width = 51;
        int length = 51;
        int height = 48;
        if (level.isClientSide()) {
            return;
        }
        for (j = 0; j <= height + 10; ++j) {
            for (i = -5; i < width + 5; ++i) {
                for (k = -5; k < length + 5; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                }
            }
        }
        j = 0;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                for (int v = 1; v < 10; ++v) {
                    net.minecraft.core.BlockPos checkPos = new net.minecraft.core.BlockPos(cposx + i, cposy + j - v, cposz + k);
                    net.minecraft.world.level.block.state.BlockState checkState = level.getBlockState(checkPos);
                    if (!checkState.isAir()
                            && !checkState.is(net.minecraft.world.level.block.Blocks.TALL_GRASS)
                            && !checkState.is(net.minecraft.world.level.block.Blocks.WATER)) {
                        continue;
                    }
                    bid = net.minecraft.world.level.block.Blocks.DIRT;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j - v, cposz + k, bid, 0, 2);
                }
            }
        }
        this.makequeencolumn(level, cposx + 1, cposy + 1, cposz + 1);
        this.makequeencolumn(level, cposx + width - 8, cposy + 1, cposz + length - 8);
        this.makequeencolumn(level, cposx + 1, cposy + 1, cposz + length - 8);
        this.makequeencolumn(level, cposx + width - 8, cposy + 1, cposz + 1);
        j = height - 1;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        j = height;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = -1; i <= width; ++i) {
            for (k = -1; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        this.makequeenbackground(level, cposx + 4, cposy + 10, cposz + 9);
        this.makequeencenteraltar(level, cposx + width / 2, cposy, cposz + length / 2);
    }

    private void makequeencolumn(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int k;
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block amethystBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyBlockAmethystBlock;
        int meta = 0;
        int width = 5;
        int length = 5;
        int height = 44;
        if (level.isClientSide()) {
            return;
        }
        int j = 0;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = 0; i < width + 2; ++i) {
            for (k = 0; k < length + 2; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, meta, 2);
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + height + 1, cposz + k, bid, meta, 2);
            }
        }
        ++cposx;
        ++cposz;
        ++cposy;
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    bid = net.minecraft.world.level.block.Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                    }
                    if (j % 4 == 0
                            && bid != net.minecraft.world.level.block.Blocks.AIR
                            && (i == 2 || k == 2)) {
                        bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                    }
                    if (j % 4 == 1 && bid != net.minecraft.world.level.block.Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                    }
                    if (j % 4 == 2 && bid != net.minecraft.world.level.block.Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 2 || k == 2) {
                            bid = amethystBlock;
                        }
                    }
                    if (j % 4 == 3 && bid != net.minecraft.world.level.block.Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = net.minecraft.world.level.block.Blocks.REDSTONE_BLOCK;
                        }
                    }
                    meta = 0;
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, meta, 2);
                }
            }
        }
    }

    private void makequeenbackground(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        boolean meta = false;
        int curz = 0;
        int cury = 0;
        int height = 33;
        int width = 33;
        net.minecraft.world.level.block.Block rubyBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyBlockRubyBlock;
        bid = net.minecraft.world.level.block.Blocks.STONE;
        for (int m = 0; m < this.queen.length; ++m) {
            int v = this.queen[m];
            if (v < 0) {
                bid = net.minecraft.world.level.block.Blocks.STONE;
                while (curz < width) {
                    ChaosPersists.setBlockFast(level, cposx, cposy + cury, cposz + curz, bid, 0, 2);
                    ++curz;
                }
                ++cury;
                curz = 0;
                continue;
            }
            for (int n = 0; n < v; ++n) {
                ChaosPersists.setBlockFast(level, cposx, cposy + cury, cposz + curz, bid, 0, 2);
                ++curz;
            }
            bid = bid == net.minecraft.world.level.block.Blocks.STONE ? rubyBlock : net.minecraft.world.level.block.Blocks.STONE;
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy - 1, cposz + i, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy + height, cposz + i, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy + i, cposz - 1, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast(level, cposx, cposy + i, cposz + width, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        }
        ChaosPersists.setBlockFast(level, cposx, cposy - 2, cposz - 2, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 1, cposz + width + 1, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy - 2, cposz + width + 1, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 1, cposz - 2, net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy - 1, cposz - 2, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 2, cposz + width + 1, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy - 1, cposz + width + 1, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
        ChaosPersists.setBlockFast(level, cposx, cposy + height + 2, cposz - 2, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
    }

    private void makequeencenteraltar(net.minecraft.world.level.Level level, int cposx, int cposy, int cposz) {
        int k;
        int i;
        net.minecraft.world.level.block.Block bid = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.block.Block amethystBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyBlockAmethystBlock;
        boolean meta = false;
        int width = 10;
        int length = 10;
        int j = 0;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 6;
        length = 20;
        j = 0;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 20;
        length = 6;
        j = 0;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 8;
        length = 8;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 4;
        length = 18;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                if (i == width && (k == - length || k == length)) {
                    bid = amethystBlock;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = amethystBlock;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 18;
        length = 4;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
                if (i == width && (k == - length || k == length)) {
                    bid = amethystBlock;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = amethystBlock;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 7;
        length = 7;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
            }
        }
        width = 3;
        length = 17;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 17;
        length = 3;
        j = 2;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 6;
        length = 6;
        j = 3;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 2;
        length = 16;
        j = 3;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 16;
        length = 2;
        j = 3;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
            }
        }
        width = 2;
        length = 2;
        j = 4;
        bid = net.minecraft.world.level.block.Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, bid, 0, 2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + 1, cposz + k, (net.minecraft.world.level.block.Block)(Object)ChaosPersists.CrystalTorch, 0, 2);
            }
        }
        level.setBlock(new net.minecraft.core.BlockPos(cposx, cposy + j, cposz), net.minecraft.world.level.block.Blocks.CHEST.defaultBlockState().setValue(net.minecraft.world.level.block.ChestBlock.FACING, chestFacingFromLegacyMeta(2)), 3);
        net.minecraft.world.level.block.entity.BlockEntity queenChestBe = level.getBlockEntity(new net.minecraft.core.BlockPos(cposx, cposy + j, cposz));
        if (queenChestBe instanceof net.minecraft.world.level.block.entity.ChestBlockEntity queenChest) {
            queenChest.setItem(13, new net.minecraft.world.item.ItemStack((net.minecraft.world.item.Item)(Object)ChaosPersists.TheQueenEgg));
        }
    }

    public void makeFrogPond(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        for (int i = -4; i <= 4; ++i) {
            for (int j = -4; j <= 4; ++j) {
                boolean rim = i == -4 || i == 4 || j == -4 || j == 4;
                this.FastSetBlock(
                        level,
                        cposx + i,
                        cposy - 1,
                        cposz + j,
                        net.minecraft.world.level.block.Blocks.DIRT);
                if (rim) {
                    this.FastSetBlock(
                            level,
                            cposx + i,
                            cposy,
                            cposz + j,
                            net.minecraft.world.level.block.Blocks.GRASS_BLOCK);
                } else {
                    this.FastSetBlock(
                            level,
                            cposx + i,
                            cposy,
                            cposz + j,
                            net.minecraft.world.level.block.Blocks.WATER);
                }
                this.FastSetBlock(
                        level,
                        cposx + i,
                        cposy + 1,
                        cposz + j,
                        net.minecraft.world.level.block.Blocks.AIR);
                this.FastSetBlock(
                        level,
                        cposx + i,
                        cposy + 2,
                        cposz + j,
                        net.minecraft.world.level.block.Blocks.AIR);
            }
        }
        this.FastSetBlock(level, cposx, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.WATER);
        this.FastSetBlock(level, cposx - 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.WATER);
        this.FastSetBlock(level, cposx + 1, cposy + 1, cposz, net.minecraft.world.level.block.Blocks.WATER);
        this.FastSetBlock(level, cposx, cposy + 1, cposz - 1, net.minecraft.world.level.block.Blocks.WATER);
        this.FastSetBlock(level, cposx, cposy + 1, cposz + 1, net.minecraft.world.level.block.Blocks.WATER);
        this.placeLevelSpawner(level, cposx, cposy + 2, cposz, "chaospersists", "frog");
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx - 1, cposy + 2, cposz),
                net.minecraft.world.level.block.Blocks.LILY_PAD.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx + 1, cposy + 2, cposz),
                net.minecraft.world.level.block.Blocks.LILY_PAD.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx, cposy + 2, cposz - 1),
                net.minecraft.world.level.block.Blocks.LILY_PAD.defaultBlockState(),
                3);
        level.setBlock(
                new net.minecraft.core.BlockPos(cposx, cposy + 2, cposz + 1),
                net.minecraft.world.level.block.Blocks.LILY_PAD.defaultBlockState(),
                3);
    }

    public void makePumpkin(Object worldObj, int cposx, int cposy, int cposz) {
        int k;
        int j;
        int i;
        int width = 14;
        int depth = 12;
        int height = 14;
        int dark_green = 13;
        int orange = 1;
        int which_color = 0;
        net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.AIR;
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < depth; ++k) {
                    which_color = 0;
                    blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == 0 || j == height - 1) {
                        blk = net.minecraft.world.level.block.Blocks.TERRACOTTA;
                        which_color = orange;
                    }
                    if (i == 0 || i == width - 1) {
                        blk = net.minecraft.world.level.block.Blocks.TERRACOTTA;
                        which_color = orange;
                    }
                    if (k == 0 || k == depth - 1) {
                        blk = net.minecraft.world.level.block.Blocks.TERRACOTTA;
                        which_color = orange;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, which_color, 2);
                }
            }
        }
        i = width / 2 - 1;
        k = 0;
        j = 11;
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 5, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 10;
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 5, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 9;
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 5, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 8;
        ChaosPersists.setBlockFast(level, cposx + i + 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 7;
        ChaosPersists.setBlockFast(level, cposx + i + 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 4;
        ChaosPersists.setBlockFast(level, cposx + i + 1, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 3;
        ChaosPersists.setBlockFast(level, cposx + i + 1, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 2;
        ChaosPersists.setBlockFast(level, cposx + i + 1, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i + 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 1;
        ChaosPersists.setBlockFast(level, cposx + i + 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        i = width / 2;
        k = 0;
        j = 11;
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 5, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 10;
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 5, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 9;
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 5, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 8;
        ChaosPersists.setBlockFast(level, cposx + i - 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 7;
        ChaosPersists.setBlockFast(level, cposx + i - 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 4;
        ChaosPersists.setBlockFast(level, cposx + i - 1, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 3;
        ChaosPersists.setBlockFast(level, cposx + i - 1, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 2;
        ChaosPersists.setBlockFast(level, cposx + i - 1, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 3, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        ChaosPersists.setBlockFast(level, cposx + i - 4, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        j = 1;
        ChaosPersists.setBlockFast(level, cposx + i - 2, cposy + j, cposz + k, Blocks.AIR, 0, 2);
        k = depth / 2 - 1;
        for (j = 0; j < 4; ++j) {
            for (i = 0; i < 3; ++i) {
                ChaosPersists.setBlockFast(level, cposx + width / 2 - i - j, cposy + height + j, cposz + k, net.minecraft.world.level.block.Blocks.TERRACOTTA, dark_green, 2);
            }
        }
        for (j = 0; j < 5; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast(level, cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1, net.minecraft.world.level.block.Blocks.OAK_PLANKS, 0, 2);
                }
            }
        }
        j = 5;
        for (i = 0; i < 2; ++i) {
            for (k = 0; k < 2; ++k) {
                ChaosPersists.setBlockFast(level, cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1, Blocks.NETHERRACK, 0, 2);
            }
        }
        j = 6;
        k = 0;
        for (i = 0; i < 2; ++i) {
            ChaosPersists.setBlockFast(level, cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1, Blocks.FIRE, 0, 2);
        }
        j = 6;
        k = 1;
        this.placeLevelSpawner(level, cposx + width / 2 - 1, cposy + j + 1, cposz + depth / 2 + k - 1, "chaospersists", "ghost_pumpkin_skelly");
        this.placeLevelSpawner(level, cposx + width / 2, cposy + j + 1, cposz + depth / 2 + k - 1, "chaospersists", "ghost_pumpkin_skelly");
    }

    public void makeRoundRotator(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        float cury;
        float curx;
        float curdeg;
        net.minecraft.world.level.block.Block crystalPinkBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyCrystalPinkBlock;
        net.minecraft.world.level.block.Block crystalCoalBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.CrystalCoal;
        float radius = 6.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)radius * Math.cos(Math.toRadians(curdeg)));
            cury = (float)((double)radius * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(
                    level,
                    (int)((float)cposx + curx + 0.5f),
                    (int)((float)(cposy + 6) + cury + 0.5f),
                    cposz,
                    net.minecraft.world.level.block.Blocks.BEDROCK);
        }
        radius = 2.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)radius * Math.cos(Math.toRadians(curdeg)));
            cury = (float)((double)radius * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(
                    level,
                    (int)((float)cposx + curx + 0.5f),
                    (int)((float)(cposy + 6) + cury + 0.5f),
                    cposz,
                    crystalPinkBlock);
        }
        this.placeLevelSpawner(level, cposx + 1, cposy + 6 + 1, cposz, "chaospersists", "rotator");
        this.placeLevelSpawner(level, cposx - 1, cposy + 6 - 1, cposz, "chaospersists", "rotator");
        this.placeLevelSpawner(level, cposx + 1, cposy + 6 - 1, cposz, "chaospersists", "rotator");
        this.placeLevelSpawner(level, cposx - 1, cposy + 6 + 1, cposz, "chaospersists", "rotator");
        this.placeLevelSpawner(level, cposx + 5, cposy + 6, cposz, "chaospersists", "dungeon_beast");
        this.placeLevelSpawner(level, cposx - 5, cposy + 6, cposz, "chaospersists", "dungeon_beast");
        this.placeLevelSpawner(level, cposx, cposy + 6 - 5, cposz, "chaospersists", "dungeon_beast");
        this.placeLevelSpawner(level, cposx, cposy + 6 + 5, cposz, "chaospersists", "dungeon_beast");
        this.FastSetBlock(level, cposx + 1, cposy + 6, cposz, crystalCoalBlock);
        this.FastSetBlock(level, cposx - 1, cposy + 6, cposz, crystalCoalBlock);
        this.FastSetBlock(level, cposx, cposy + 6 + 1, cposz, crystalCoalBlock);
        this.FastSetBlock(level, cposx, cposy + 6 - 1, cposz, crystalCoalBlock);
        this.fillLevelChestAt(
                level, rand, cposx, cposy + 6, cposz, 2, this.CrystalBattleTowerVortexContentsList, 6 + rand.nextInt(6));
    }

    public void makeRainbow(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int k;
        int i;
        net.minecraft.world.level.block.Block blk;
        net.minecraft.world.level.block.Block terracotta = net.minecraft.world.level.block.Blocks.TERRACOTTA;
        int width = 12;
        int depth = 1;
        int blk_color = 0;
        blk_color = 0;
        int j = 35;
        width = 12;
        depth = 1;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, terracotta, blk_color, 2);
            }
        }
        k = 0;
        for (i = - width + 1; i < width; i += 3) {
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, net.minecraft.world.level.block.Blocks.WATER, 0, 2);
            ChaosPersists.setBlockFast(level, cposx + i, cposy + j - 1, cposz + k, net.minecraft.world.level.block.Blocks.WATER, 0, 2);
        }
        width = 13;
        depth = 2;
        j = 26;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == - width || i == width - 1) {
                    blk = terracotta;
                }
                if (k == - depth || k == depth) {
                    blk = terracotta;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, blk_color, 2);
            }
        }
        width = 14;
        depth = 3;
        j = 27;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == - width || i == width - 1) {
                    blk = terracotta;
                }
                if (k == - depth || k == depth) {
                    blk = terracotta;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, blk_color, 2);
            }
        }
        width = 13;
        depth = 2;
        j = 28;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                blk = net.minecraft.world.level.block.Blocks.AIR;
                if (i == - width || i == width - 1) {
                    blk = terracotta;
                }
                if (k == - depth || k == depth) {
                    blk = terracotta;
                }
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, blk_color, 2);
            }
        }
        j = 29;
        width = 12;
        depth = 1;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, terracotta, blk_color, 2);
            }
        }
        j = 30;
        for (int m = 3; m < 11; ++m) {
            blk_color = this.blkcolors[m - 3];
            for (i = 0; i < m; ++i) {
                ChaosPersists.setBlockFast(level, cposx + m, cposy + j + i, cposz, terracotta, blk_color, 2);
                ChaosPersists.setBlockFast(level, cposx - (m + 1), cposy + j + i, cposz, terracotta, blk_color, 2);
            }
            for (i = - m + 1; i <= m; ++i) {
                ChaosPersists.setBlockFast(level, cposx + i, cposy + j + m, cposz, terracotta, blk_color, 2);
            }
        }
        this.placeLevelSpawner(level, cposx + 2, cposy + j, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx - 3, cposy + j, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx + 2, cposy + j + 1, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx - 3, cposy + j + 1, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx + 2, cposy + j + 2, cposz, "chaospersists", "cloud_shark");
        this.placeLevelSpawner(level, cposx - 3, cposy + j + 2, cposz, "chaospersists", "cloud_shark");
        this.placeLevelDoubleChest(level, cposx, cposy + j, cposz, cposx - 1, cposy + j, cposz, 2);
        this.fillExistingChestAt(level, rand, cposx, cposy + j, cposz, this.RainbowContentsList, 10 + rand.nextInt(5));
        this.fillExistingChestAt(level, rand, cposx - 1, cposy + j, cposz, this.RainbowContentsList, 10 + rand.nextInt(5));
    }

    public void makeEnormousCastleQ(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level worldLevel = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = worldLevel.getRandom();
        net.minecraft.world.level.block.Block extremeTorch =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.ExtremeTorch;
        int j;
        int k;
        int i;
        int width = 28;
        int height = 16;
        int platformwidth = 11;
        int level = 0;
        if (worldLevel.isClientSide()) {
            return;
        }
        level = 1 + rand.nextInt(6);
        if (level <= 3 && rand.nextInt(3) != 1) {
            level += 3;
        }
        for (i = -20; i < width + 4; ++i) {
            for (j = 1; j < height + 10; ++j) {
                for (k = -4; k < width + 4; ++k) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.OBSIDIAN);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                k = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                i = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                i = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        ChaosPersists.setBlockFast(worldLevel, cposx + 1, cposy + 1, cposz + 1, extremeTorch, 0, 2);
        ChaosPersists.setBlockFast(worldLevel, cposx + 1, cposy + 1, cposz + width - 2, extremeTorch, 0, 2);
        ChaosPersists.setBlockFast(worldLevel, cposx + width - 2, cposy + 1, cposz + 1, extremeTorch, 0, 2);
        ChaosPersists.setBlockFast(worldLevel, cposx + width - 2, cposy + 1, cposz + width - 2, extremeTorch, 0, 2);
        for (i = -4; i < width + 4; ++i) {
            for (k = -4; k < width + 4; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy, cposz + k, Blocks.OBSIDIAN);
                }
                if (i != -4 && k != -4 && i != width + 3 && k != width + 3) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        SpawnerBlockEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - 3, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - 3, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "lurking_terror"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - 3, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - 3, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "lurking_terror"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + 2, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + 2, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "lurking_terror"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + 2, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + 2, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner == null) continue;
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "lurking_terror"));
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "emperor_scorpion"));
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "emperor_scorpion"));
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 4, cposz + width / 2);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "emperor_scorpion"));
        }
        j = height;
        this.buildLevelQ(worldLevel, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Rotator", 1, -1, 5, 1, level);
        j += 10;
        if (level >= 2) {
            this.buildLevelQ(worldLevel, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Bee", 0, 0, 4, 2, level);
        }
        j += 10;
        if (level >= 3) {
            this.buildLevelQ(worldLevel, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 4, "Mantis", 1, 1, 4, 3, level);
        }
        j += 9;
        if (level >= 4) {
            this.buildLevelQ(worldLevel, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 3, "Mothra", 0, 0, 4, 4, level);
        }
        j += 9;
        if (level >= 5) {
            this.buildLevelQ(worldLevel, cposx + 3, cposy + j, cposz + 3, width - 6, 8, 3, "Brutalfly", 1, 1, 4, 5, level);
        }
        j += 8;
        if (level >= 6) {
            this.buildLevelQ(worldLevel, cposx + 3, cposy + j, cposz + 3, width - 6, 16, 3, "Vortex", 0, 0, 3, 6, level);
        }
        j += 16;
        for (i = 0; i < platformwidth; ++i) {
            j = height;
            for (k = - platformwidth / 2; k <= platformwidth / 2; ++k) {
                this.FastSetBlock(worldLevel, cposx + i - 20, cposy + j, cposz + k + width / 2, ChaosPersists.MyBlockAmethystBlock);
                if (i != 0 && i != platformwidth - 1 && k != - platformwidth / 2 && k != platformwidth / 2 || i == 0 && k >= -1 && k <= 1) continue;
                this.FastSetBlock(worldLevel, cposx + i - 20, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        for (i = -10; i <= -3; ++i) {
            j = height;
            for (k = -2; k < 3; ++k) {
                if (i == -3 || i == -10) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k + width / 2, ChaosPersists.MyBlockAmethystBlock);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = -21;
        for (j = height; j >= 0; --j) {
            for (k = -2; k < 3; ++k) {
                for (int t = 0; t < 6; ++t) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + t + 1, cposz + k + width / 2, Blocks.AIR);
                }
                if (j == 0) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k + width / 2, ChaosPersists.MyBlockAmethystBlock);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
            --i;
        }
        if (level >= 6) {
            int span = width * 3;
            for (int tries = 0; tries < 100; ++tries) {
                j = -1;
                i = rand.nextInt(span);
                k = rand.nextInt(span);
                if (i >= span / 4 && i <= span * 3 / 4 && k >= span / 4 && k <= span * 3 / 4) continue;
                worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + (i -= span / 2) + width / 2, cposy + j, cposz + (k -= span / 2) + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + i + width / 2, cposy + j, cposz + k + width / 2);
                if (tileentitymobspawner == null) continue;
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
        }
    }

    public void buildLevelQ(Object worldObj, int cposx, int cposy, int cposz, int width, int height, int pw, String critter, int stepside, int stepoff, int holelen, int decor, int level) {
        net.minecraft.world.level.Level worldLevel = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = worldLevel.getRandom();
        int j;
        int i;
        int k;
        for (i = - pw; i < width + pw; ++i) {
            for (j = 1; j < height; ++j) {
                for (k = - pw; k < width + pw; ++k) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
                k = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                Block blk = Blocks.BEDROCK;
                if (k == 0 || k == width - 1) {
                    blk = ChaosPersists.MyBlockRubyBlock;
                }
                i = 0;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, blk);
                i = width - 1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = - pw; i < width + pw; ++i) {
            for (k = - pw; k < width + pw; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(worldLevel, cposx + i, cposy, cposz + k, Blocks.OBSIDIAN);
                }
                if (i != - pw && k != - pw && i != width + (pw - 1) && k != width + (pw - 1)) continue;
                this.FastSetBlock(worldLevel, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = - height / 2;
        i += width / 2;
        for (j = 1; j < height; ++j) {
            if (stepside != 0) {
                k = -1;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.OBSIDIAN);
            } else {
                k = width;
                this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.OBSIDIAN);
            }
            ++i;
        }
        if (stepoff >= 0) {
            if (stepside == 0) {
                k = -1;
                k -= stepoff;
            } else {
                k = width;
                k += stepoff;
            }
            i = width / 2;
            j = 0;
            for (int l = 0; l < holelen; ++l) {
                this.FastSetBlock(worldLevel, cposx + i + l, cposy + j, cposz + k, Blocks.AIR);
            }
        }
        SpawnerBlockEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner == null) continue;
            this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
        }
        this.addLevelDecorationsQ(worldLevel, cposx, cposy, cposz, width, height, decor, level);
    }

    public void addLevelDecorationsQ(Level worldLevel, int cposx, int cposy, int cposz, int width, int height, int decor, int difficulty) {
        int j;
        SpawnerBlockEntity tileentitymobspawner = null;
        int reward = 1;
        String critter = "T. Rex";
        if (decor == 6) {
            this.FastSetBlock(worldLevel, cposx, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(worldLevel, cposx + width - 1, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(worldLevel, cposx + width / 2, cposy + height, cposz + width / 2, Blocks.AIR);
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "nightmare"));
            }
            for (int i = 1; i < width - 1; ++i) {
                for (j = 1; j < 5; ++j) {
                    for (int k = 1; k < width - 1; ++k) {
                        this.FastSetBlock(worldLevel, cposx + i, cposy + j, cposz + k, Blocks.DIRT);
                    }
                }
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 4, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", "large_worm"));
            }
            for (j = 0; j < 10; ++j) {
                this.FastSetBlock(worldLevel, cposx + 1, cposy + j, cposz + 1, Blocks.AIR);
            }
            this.fill_chests(worldLevel, cposx, cposy + 4, cposz, width, height, decor, reward);
        }
        if (decor == 5) {
            if (difficulty == 5) {
                critter = "T. Rex";
                reward = 1;
            }
            if (difficulty == 6) {
                critter = "Nastysaurus";
                reward = 2;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 4) {
            if (difficulty == 4) {
                critter = "T. Rex";
                reward = 1;
            }
            if (difficulty == 5) {
                critter = "Nastysaurus";
                reward = 2;
            }
            if (difficulty == 6) {
                critter = "Basilisk";
                reward = 3;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 3) {
            if (difficulty == 3) {
                critter = "T. Rex";
                reward = 1;
            }
            if (difficulty == 4) {
                critter = "Nastysaurus";
                reward = 2;
            }
            if (difficulty == 5) {
                critter = "Basilisk";
                reward = 3;
            }
            if (difficulty == 6) {
                critter = "Hercules Beetle";
                reward = 4;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 2) {
            if (difficulty == 2) {
                critter = "T. Rex";
                reward = 1;
            }
            if (difficulty == 3) {
                critter = "Nastysaurus";
                reward = 2;
            }
            if (difficulty == 4) {
                critter = "Basilisk";
                reward = 3;
            }
            if (difficulty == 5) {
                critter = "Hercules Beetle";
                reward = 4;
            }
            if (difficulty == 6) {
                critter = "Jumpy Bug";
                reward = 5;
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(worldLevel, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
        if (decor == 1) {
            if (difficulty == 1) {
                critter = "T. Rex";
            }
            if (difficulty == 2) {
                critter = "Nastysaurus";
            }
            if (difficulty == 3) {
                critter = "Basilisk";
            }
            if (difficulty == 4) {
                critter = "Hercules Beetle";
            }
            if (difficulty == 5) {
                critter = "Jumpy Bug";
            }
            if (difficulty == 6) {
                critter = "CaterKiller";
            }
            reward = difficulty;
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(worldLevel, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(worldLevel, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(worldLevel, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chestsQ(worldLevel, cposx, cposy, cposz, width, height, decor, reward);
        }
    }

    private void fill_chestsQ(Object worldObj, int cposx, int cposy, int cposz, int width, int height, int decor, int reward) {
        net.minecraft.world.level.Level worldLevel = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = worldLevel.getRandom();
        ChestBlockEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.level1ContentsList;
        if (reward == 2) {
            chestContents = this.level2ContentsList;
        }
        if (reward == 3) {
            chestContents = this.level3ContentsList;
        }
        if (reward == 4) {
            chestContents = this.level4ContentsList;
        }
        if (reward == 5) {
            chestContents = this.level5ContentsList;
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + 1, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + 1, cposy + 1, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + 1, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.ThePrincessEgg, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width - 2, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + width - 2, cposy + 1, cposz + width / 2, 4, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + width - 2, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.QueenHelmet, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.QueenBody, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + width / 2, cposy + 1, cposz + 1, 3, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + width / 2, cposy + 1, cposz + 1);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.QueenLegs, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.QueenBoots, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
        worldLevel.setBlock(new net.minecraft.core.BlockPos(cposx + width / 2, cposy + 1, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(worldLevel,cposx + width / 2, cposy + 1, cposz + width - 2, 2, 3);
        chest = this.getChestTileEntity(worldLevel, cposx + width / 2, cposy + 1, cposz + width - 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.MyRoyal, 1));
            } else {
                WeightedRandomChestContent.generateChestContents(rand, (WeightedRandomChestContent[])chestContents, chest, (int)(5 + rand.nextInt(7)));
            }
        }
    }

    public void makeSpiderHangout(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        int i;
        int k;
        int j;
        for (i = 0; i < 20; ++i) {
            for (j = -1; j < 20; ++j) {
                for (k = 0; k < 20; ++k) {
                    net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == -1) {
                        blk = net.minecraft.world.level.block.Blocks.STONE;
                    }
                    if (j == 0) {
                        blk = net.minecraft.world.level.block.Blocks.GRAVEL;
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, 0, 2);
                }
            }
        }
        for (j = 1; j < 4; ++j) {
            this.placeLevelSpawner(level, cposx, cposy + j, cposz, "chaospersists", "spider_driver");
            this.placeLevelSpawner(level, cposx + 19, cposy + j, cposz + 19, "chaospersists", "spider_driver");
            this.placeLevelSpawner(level, cposx + 19, cposy + j, cposz, "chaospersists", "spider_driver");
            this.placeLevelSpawner(level, cposx, cposy + j, cposz + 19, "chaospersists", "spider_driver");
        }
        com.astryxion.chaospersists.entity.Dragon.spawnCreature(level, "robot_spider", cposx + 10, cposy + 1, cposz + 10);
    }

    public void makeRedAntHangout(Object worldObj, int cposx, int cposy, int cposz) {
        net.minecraft.world.level.Level level = (net.minecraft.world.level.Level) worldObj;
        net.minecraft.util.RandomSource rand = level.getRandom();
        net.minecraft.world.level.block.Block redAntBlock =
                (net.minecraft.world.level.block.Block) (Object) ChaosPersists.MyRedAntBlock;
        for (int i = 0; i < 16; ++i) {
            for (int j = -1; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    net.minecraft.world.level.block.Block blk = net.minecraft.world.level.block.Blocks.AIR;
                    if (j == -1) {
                        blk = net.minecraft.world.level.block.Blocks.STONE;
                    }
                    if (j == 0) {
                        blk = net.minecraft.world.level.block.Blocks.GRAVEL;
                        if (!(i >= 3 && i <= 12 || k >= 3 && k <= 12)) {
                            blk = redAntBlock;
                        }
                    }
                    ChaosPersists.setBlockFast(level, cposx + i, cposy + j, cposz + k, blk, 0, 2);
                }
            }
        }
        com.astryxion.chaospersists.entity.Dragon.spawnCreature(level, "robot_red_ant", cposx + 8, cposy + 1, cposz + 8);
    }
}

