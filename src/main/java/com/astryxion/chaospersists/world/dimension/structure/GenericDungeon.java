/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.CrystalFurnace
 *  com.astryxion.chaospersists.GenericDungeon
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.OreGenericEgg
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.BushBlock
 *  net.minecraft.block.ChestBlock
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.FireBlock
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.PistonBlock
 *  net.minecraft.block.BlockPistonMoving
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.SlabBlock
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.item.EnderCrystalEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.MapItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.ChestTileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.world.ore.OreGenericEgg;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.ChestBlock;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.MovingPistonBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.block.BlockState;

public class GenericDungeon {
    private final WeightedRandomChestContent[] RainbowContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MagicApple, 0, 1, 1, 25), new WeightedRandomChestContent(ChaosPersists.CloudSharkEgg, 0, 4, 10, 25), new WeightedRandomChestContent(Items.BONE, 0, 2, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 2, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 25), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 25)};
    private final WeightedRandomChestContent[] WhiteHouseContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 6, 12, 35), new WeightedRandomChestContent(ChaosPersists.UraniumNugget, 0, 2, 6, 10), new WeightedRandomChestContent(ChaosPersists.TitaniumNugget, 0, 2, 6, 10), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 2, 6, 35), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 6, 25), new WeightedRandomChestContent(ChaosPersists.CriminalEgg, 0, 4, 10, 35), new WeightedRandomChestContent(Items.EMERALD, 0, 6, 16, 35), new WeightedRandomChestContent(Items.PORKCHOP, 0, 6, 16, 35), new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 6, 16, 35), new WeightedRandomChestContent(Items.DIAMOND, 0, 6, 16, 35), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 6, 16, 35)};
    private final WeightedRandomChestContent[] RubberDuckyContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyDeadStinkBug, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySunFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySparkFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyGreenFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyBlueFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyPinkFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyRockFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyWoodFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyGreyFish, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.RubberDuckyEgg, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyPeacockFeather, 0, 4, 10, 35), new WeightedRandomChestContent(Items.FEATHER, 0, 6, 16, 35)};
    private final WeightedRandomChestContent[] StinkyHouseContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyDeadStinkBug, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.StinkyEgg, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.StinkBugEgg, 0, 4, 10, 35), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.COAL, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 35)};
    private final WeightedRandomChestContent[] NightmareRookeryContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyDeadStinkBug, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyFlowerBlackBlock.asItem(), 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyFlowerScaryBlock.asItem(), 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 4, 10, 25), new WeightedRandomChestContent(ChaosPersists.AntRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.SpiderRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 35), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] MonsterIslandContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.CreeperRepellent.asItem(), 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.KrakenRepellent.asItem(), 0, 4, 10, 35), new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.PORKCHOP, 0, 3, 10, 35), new WeightedRandomChestContent(Items.BEEF, 0, 3, 10, 35), new WeightedRandomChestContent(Items.CHICKEN, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COD, 0, 3, 10, 35), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 3, 10, 35), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyRawBacon, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyRawPeacock, 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.OAK_LOG.asItem(), 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] GreenhouseContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.GreenGoo, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.CreeperRepellent.asItem(), 0, 4, 10, 35), new WeightedRandomChestContent(Items.FLOWER_POT, 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.OAK_SAPLING.asItem(), 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.OAK_LEAVES.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Blocks.DIRT.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Blocks.OAK_LOG.asItem(), 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] CrystalBattleTowerRatContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 3, 10, 35), new WeightedRandomChestContent(Items.BEEF, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_CHICKEN, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_COD, 0, 3, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyBLT, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySalad, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] CrystalBattleTowerDungeonBeastContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 25), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 5, 15, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] CrystalBattleTowerUrchinContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyFairySword, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] CrystalBattleTowerRotatorContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyRatSword, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] CrystalBattleTowerVortexContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.CrystalCoal.asItem(), 0, 6, 10, 10), new WeightedRandomChestContent(ChaosPersists.CrystalCoal.asItem(), 0, 6, 10, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyTigersEyeBlock.asItem(), 0, 4, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] RobotContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.REDSTONE, 0, 1, 10, 35), new WeightedRandomChestContent(Items.REPEATER, 0, 1, 10, 35), new WeightedRandomChestContent(Items.MINECART, 0, 1, 1, 35), new WeightedRandomChestContent(Items.FIRE_CHARGE, 0, 1, 10, 35), new WeightedRandomChestContent(Items.HOPPER_MINECART, 0, 1, 1, 35), new WeightedRandomChestContent(Blocks.REDSTONE_BLOCK.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.RAIL.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.DETECTOR_RAIL.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.STICKY_PISTON.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.PISTON.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.REDSTONE_TORCH.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.TNT.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.RAIL.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.LEVER.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(ChaosPersists.AntRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.SpiderRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(Items.IRON_DOOR, 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.REDSTONE_TORCH.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.OAK_BUTTON.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.IRON_BARS.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(Items.COMPARATOR, 0, 1, 10, 35), new WeightedRandomChestContent(Blocks.ACTIVATOR_RAIL.asItem(), 0, 1, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyRayGun, 0, 1, 1, 35)};
    private final WeightedRandomChestContent[] IncaPyramidContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.GOLDEN_SWORD, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_BOOTS, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_LEGGINGS, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_HELMET, 0, 1, 1, 35), new WeightedRandomChestContent((Item)Items.GOLDEN_CHESTPLATE, 0, 1, 1, 35), new WeightedRandomChestContent(Blocks.DANDELION.asItem(), 0, 3, 10, 35), new WeightedRandomChestContent(Blocks.POPPY.asItem(), 0, 3, 10, 35), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 3, 10, 35), new WeightedRandomChestContent(Items.GOLD_INGOT, 0, 3, 10, 35), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyCornCob, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 25), new WeightedRandomChestContent(Items.BONE, 0, 4, 10, 35), new WeightedRandomChestContent(Blocks.GOLD_BLOCK.asItem(), 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] DamselContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.IRON_PICKAXE, 0, 1, 1, 35), new WeightedRandomChestContent(Items.IRON_SWORD, 0, 1, 1, 35), new WeightedRandomChestContent(Items.COOKED_PORKCHOP, 0, 3, 10, 35), new WeightedRandomChestContent(Items.BEEF, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_CHICKEN, 0, 3, 10, 35), new WeightedRandomChestContent(Items.COOKED_COD, 0, 3, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyBLT, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MySalad, 0, 4, 10, 35), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 4, 10, 35)};
    private final WeightedRandomChestContent[] EnderCastleContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Blocks.ENDER_CHEST.asItem(), 0, 2, 4, 35), new WeightedRandomChestContent(Blocks.DIAMOND_BLOCK.asItem(), 0, 2, 4, 35), new WeightedRandomChestContent(Blocks.DRAGON_EGG.asItem(), 0, 1, 1, 35), new WeightedRandomChestContent(ChaosPersists.MyEnderPearlBlock.asItem(), 0, 3, 6, 35), new WeightedRandomChestContent(ChaosPersists.MyEyeOfEnderBlock.asItem(), 0, 3, 6, 35), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 25), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 4, 35), new WeightedRandomChestContent(Items.ENDER_EYE, 0, 2, 4, 35)};
    private final WeightedRandomChestContent[] BouncyContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 35), new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Blocks.POPPY.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Blocks.DANDELION.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 4, 20)};
    private final WeightedRandomChestContent[] SpitBugContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 35), new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyAmethystPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.InstantGarden, 0, 2, 4, 25), new WeightedRandomChestContent(ChaosPersists.InstantShelter, 0, 2, 4, 25)};
    private final WeightedRandomChestContent[] GraveContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.ENDER_EYE, 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.POPPY.asItem(), 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.DANDELION.asItem(), 0, 6, 16, 35), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 6, 16, 35)};
    private final WeightedRandomChestContent[] HospitalContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Blocks.ENDER_CHEST.asItem(), 0, 2, 4, 35), new WeightedRandomChestContent(Blocks.DIAMOND_BLOCK.asItem(), 0, 2, 4, 35), new WeightedRandomChestContent(Blocks.DRAGON_EGG.asItem(), 0, 1, 1, 35), new WeightedRandomChestContent(ChaosPersists.MyEnderPearlBlock.asItem(), 0, 3, 6, 35), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 4, 35), new WeightedRandomChestContent(Items.ENDER_EYE, 0, 2, 4, 35)};
    private final WeightedRandomChestContent[] MiniContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.GOLDEN_APPLE, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyCrystalApple, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 6, 16, 35), new WeightedRandomChestContent(ChaosPersists.InstantGarden, 0, 2, 4, 25), new WeightedRandomChestContent(ChaosPersists.InstantShelter, 0, 2, 4, 25)};
    private final WeightedRandomChestContent[] LeafMonsterContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.FLOWER_POT, 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.OAK_SAPLING.asItem(), 0, 6, 16, 35), new WeightedRandomChestContent(Items.FLOWER_POT, 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.OAK_SAPLING.asItem(), 0, 6, 16, 35), new WeightedRandomChestContent(Blocks.OAK_LEAVES.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Blocks.DIRT.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Blocks.OAK_LOG.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] CloudSharkContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(Items.BONE, 0, 6, 16, 25), new WeightedRandomChestContent(Items.STRING, 0, 6, 16, 25), new WeightedRandomChestContent(Items.PAPER, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyExperienceTreeSeed, 0, 1, 2, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] WaterDragonContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.COD, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyUltimateAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimatePickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyUltimateShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 25), new WeightedRandomChestContent(Blocks.IRON_BLOCK.asItem(), 0, 6, 16, 25), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] SquidContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 15), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 5, 15, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] KnightContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.PAPER, 0, 2, 8, 20), new WeightedRandomChestContent(Blocks.OAK_PLANKS.asItem(), 0, 4, 8, 20), new WeightedRandomChestContent(Items.ENDER_EYE, 0, 2, 8, 15), new WeightedRandomChestContent(Items.ENDER_PEARL, 0, 2, 8, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25)};
    private final WeightedRandomChestContent[] AlienWTFContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Blocks.DIAMOND_BLOCK.asItem(), 0, 1, 2, 15), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.MyIngotUranium, 0, 1, 2, 5), new WeightedRandomChestContent(ChaosPersists.MyIngotTitanium, 0, 1, 2, 5), new WeightedRandomChestContent((Item)ChaosPersists.UltimateHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.UltimateLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.UltimateBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.MyRayGun, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyCornDog, 0, 1, 10, 20), new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 1, 5, 20), new WeightedRandomChestContent(ChaosPersists.MyPopcornBag, 0, 2, 8, 20), new WeightedRandomChestContent(ChaosPersists.MyFireFish, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] shadowContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.GLOWSTONE_DUST, 0, 2, 8, 20), new WeightedRandomChestContent(Items.NETHER_WART, 0, 4, 8, 20), new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 2, 8, 15), new WeightedRandomChestContent(Items.BLAZE_POWDER, 0, 2, 8, 15), new WeightedRandomChestContent(Items.FIRE_CHARGE, 0, 4, 8, 15), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25), new WeightedRandomChestContent(Items.INK_SAC, 0, 6, 16, 25), new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceTreeSeed, 0, 2, 4, 15), new WeightedRandomChestContent(ChaosPersists.MyElevator, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRatSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyRubySword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyBigHammer, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyIngotTitanium, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyIngotUranium, 0, 1, 1, 5), new WeightedRandomChestContent(ChaosPersists.MyUltimateSword, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyUltimateBow, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.EnderReaperEgg, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] kyuubiContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.REDSTONE, 0, 2, 8, 10), new WeightedRandomChestContent(Blocks.REDSTONE_BLOCK.asItem(), 0, 4, 8, 15), new WeightedRandomChestContent(Items.QUARTZ, 0, 2, 8, 15), new WeightedRandomChestContent(Items.COAL, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 20), new WeightedRandomChestContent(ChaosPersists.KyuubiEgg, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] blazeContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.BLAZE_ROD, 0, 2, 8, 15), new WeightedRandomChestContent(Items.BLAZE_POWDER, 0, 2, 8, 15), new WeightedRandomChestContent(Items.FIRE_CHARGE, 0, 4, 8, 15), new WeightedRandomChestContent(Items.FLINT_AND_STEEL, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBoots, 0, 1, 1, 15), new WeightedRandomChestContent(Items.BLAZE_SPAWN_EGG, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] beeContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.SUGAR, 0, 2, 8, 15), new WeightedRandomChestContent(Blocks.DANDELION.asItem(), 0, 4, 8, 15), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 5, 15, 15), new WeightedRandomChestContent(Items.PAPER, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyFairySword, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyButterCandy, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 10), new WeightedRandomChestContent(ChaosPersists.BeeEgg, 0, 2, 8, 15)};
    private final WeightedRandomChestContent[] mantisContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyMantisClaw, 0, 1, 1, 10), new WeightedRandomChestContent(Items.GOLD_NUGGET, 0, 4, 8, 15), new WeightedRandomChestContent(ChaosPersists.UraniumNugget, 0, 1, 3, 5), new WeightedRandomChestContent(ChaosPersists.TitaniumNugget, 0, 1, 3, 5), new WeightedRandomChestContent(ChaosPersists.MantisEgg, 0, 2, 4, 20), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 10), new WeightedRandomChestContent(Items.ROTTEN_FLESH, 0, 6, 16, 25), new WeightedRandomChestContent(Items.DIAMOND, 0, 1, 3, 15)};
    private final WeightedRandomChestContent[] level1ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.EMERALD, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MinersDream, 0, 4, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level2ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 2, 8, 15), new WeightedRandomChestContent(Items.EXPERIENCE_BOTTLE, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.CreeperLauncher, 0, 2, 10, 15), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.CrystalPinkBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyFairySword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level3ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MySquidZooka, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRatSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethyst, 0, 2, 8, 15), new WeightedRandomChestContent(Items.INK_SAC, 0, 2, 8, 15), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeHelmet, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBody, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeLegs, 0, 1, 1, 10), new WeightedRandomChestContent((Item)ChaosPersists.TigersEyeBoots, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.MyAmethystPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyAmethystSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.AmethystBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level4ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MagicApple, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRayGun, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.CreeperRepellent.asItem(), 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.KrakenRepellent.asItem(), 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceCatcher, 0, 4, 10, 15), new WeightedRandomChestContent(ChaosPersists.ZooKeeper, 0, 10, 16, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubyAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyRubySword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyThunderStaff, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.RubyBoots, 0, 1, 1, 15)};
    private final WeightedRandomChestContent[] level5ContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyNightmareSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyPoisonSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.WitherSkeletonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnderDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SnowGolemEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.IronGolemEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WitherBossEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GoldCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnchantedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MOTHRAEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CryoEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CamaEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.VeloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HydroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BasilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EmperorScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaveFisherEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpyroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BaryonyxEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CockateilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GammaMetroidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KyuubiEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AlienEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AttackSquidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WaterDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CephadromeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KrakenEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LizardEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BeeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TrooperBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpitBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.StinkBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.OstrichEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GazelleEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ChipmunkEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CreepingHorrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TerribleTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CliffRacerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TriffidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LurkingTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SmallWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MediumWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LargeWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TRexEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GodzillaEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MantisEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HerculesEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.VortexEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RatEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DungeonBeastEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.FairyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WhaleEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SkateEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.IrukandjiEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot1Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot2Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot3Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot4Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.Robot5Egg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CriminalEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CoinEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BoyfriendEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EasterBunnyEgg, 0, 1, 4, 5), new WeightedRandomChestContent(ChaosPersists.MolenoidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaMonsterEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaViperEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaterKillerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LeonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HammerheadEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RubberDuckyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.NastysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PointysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BrutalflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CricketEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.FrogEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AntRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.SpiderRobotKit, 0, 1, 1, 10), new WeightedRandomChestContent(ChaosPersists.JefferyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpiderDriverEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CrabEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CassowaryEgg, 0, 1, 4, 15)};
    private final WeightedRandomChestContent[] chestContentsList = new WeightedRandomChestContent[]{new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 6, 12, 20), new WeightedRandomChestContent(ChaosPersists.MyButterCandy, 0, 6, 12, 20), new WeightedRandomChestContent(Items.EMERALD, 0, 2, 8, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldPickaxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldShovel, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldHoe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldAxe, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyEmeraldSword, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.EmeraldBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyMothScale, 0, 2, 8, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.MothScaleBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyLavaEel, 0, 2, 8, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.LavaEelBoots, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBody, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceLegs, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceHelmet, 0, 1, 1, 15), new WeightedRandomChestContent((Item)ChaosPersists.ExperienceBoots, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.MyExperienceSword, 0, 1, 1, 15), new WeightedRandomChestContent(ChaosPersists.WitherSkeletonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnderDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SnowGolemEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.IronGolemEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WitherBossEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GoldCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EnchantedCowEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MOTHRAEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CryoEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CamaEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.VeloEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HydroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BasilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.EmperorScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ScorpionEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaveFisherEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpyroEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BaryonyxEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CockateilEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GammaMetroidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KyuubiEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AlienEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.AttackSquidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.WaterDragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CephadromeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.KrakenEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LizardEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.DragonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BeeEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TrooperBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpitBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.StinkBugEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.OstrichEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.GazelleEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.ChipmunkEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CreepingHorrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TerribleTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CliffRacerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.TriffidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PitchBlackEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LurkingTerrorEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SmallWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MediumWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LargeWormEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CassowaryEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.MolenoidEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaMonsterEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SeaViperEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CaterKillerEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.LeonEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.HammerheadEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.RubberDuckyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.NastysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.PointysaurusEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.BrutalflyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CricketEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.FrogEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.JefferyEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.SpiderDriverEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CrabEgg, 0, 1, 4, 15), new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 3, 10, 20)};
    private int[] king = new int[]{-1, -1, 24, 3, -1, 24, 5, -1, 17, 12, -1, 16, 15, -1, 15, 14, -1, 15, 6, 3, 5, -1, 14, 6, 4, 3, -1, 14, 5, -1, 14, 5, -1, 12, 9, -1, 11, 11, -1, 8, 17, -1, 5, 23, -1, 3, 27, -1, 2, 29, -1, 1, 31, -1, 0, 33, -1, 13, 6, -1, 12, 9, -1, 11, 3, 1, 2, 1, 4, -1, 10, 3, 2, 2, 3, 2, -1, 10, 2, 4, 2, 3, 2, -1, 9, 2, 5, 2, 4, 6, -1, 9, 2, 5, 2, 6, 4, -1, 8, 2, 6, 1, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 15, 2, -1, -1, -1};
    private int[] queen = new int[]{-1, -1, 24, 3, -1, 24, 5, -1, 17, 12, -1, 16, 15, -1, 15, 14, -1, 15, 6, 3, 5, -1, 14, 6, 4, 3, -1, 14, 5, -1, 14, 5, -1, 12, 9, -1, 11, 11, -1, 8, 17, -1, 5, 23, -1, 3, 27, -1, 2, 29, -1, 1, 31, -1, 0, 33, -1, 13, 6, -1, 12, 9, -1, 11, 3, 1, 2, 1, 4, -1, 10, 3, 2, 2, 3, 2, -1, 10, 2, 4, 2, 3, 2, -1, 9, 2, 5, 2, 4, 6, -1, 9, 2, 5, 2, 6, 4, -1, 8, 2, 6, 1, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 8, 2, 5, 2, -1, 15, 2, -1, -1, -1};
    private int[] blkcolors = new int[]{14, 1, 4, 5, 3, 11, 10, 6};

    private void setThisBlock(World world, int cposx, int cposy, int cposz) {
        if (world.random.nextInt(2) == 1) {
            this.FastSetBlock(world, cposx, cposy, cposz, Blocks.MOSSY_COBBLESTONE);
        } else {
            this.FastSetBlock(world, cposx, cposy, cposz, Blocks.COBBLESTONE);
        }
    }

    private ChestTileEntity getChestTileEntity(World world, int cposx, int cposy, int cposz) {
        ChestTileEntity chest = null;
        TileEntity t = null;
        t = world.getBlockEntity(new net.minecraft.util.math.BlockPos(cposx, cposy, cposz));
        if (t != null && t instanceof ChestTileEntity) {
            chest = (ChestTileEntity)t;
            return chest;
        }
        return null;
    }

    private boolean setWorldBlockState(World world, BlockPos pos, net.minecraft.block.BlockState state, int flags) {
        return ChaosPersists.setBlockFast(world, pos.getX(), pos.getY(), pos.getZ(), state.getBlock(), 0, flags);
    }

    private void setBlockMeta(World world, int x, int y, int z, int meta, int flags) {
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof ChestBlock) {
            Direction facing = Direction.NORTH;
            if (meta == 3) {
                facing = Direction.SOUTH;
            } else if (meta == 4) {
                facing = Direction.WEST;
            } else if (meta == 5) {
                facing = Direction.EAST;
            }
            this.setWorldBlockState(world, pos, state.setValue(ChestBlock.FACING, facing), flags);
            return;
        }
        this.setWorldBlockState(world, pos, state, flags);
    }

    private void placeDoor(World world, BlockPos pos, Direction facing, DoorBlock door) {
        this.setWorldBlockState(world, pos, door.defaultBlockState().setValue(DoorBlock.FACING, facing).setValue(DoorBlock.HALF, DoubleBlockHalf.LOWER), 2);
        this.setWorldBlockState(world, pos.above(), door.defaultBlockState().setValue(DoorBlock.FACING, facing).setValue(DoorBlock.HALF, DoubleBlockHalf.UPPER), 2);
    }

    private MobSpawnerTileEntity getSpawnerTileEntity(World world, int cposx, int cposy, int cposz) {
        MobSpawnerTileEntity chest = null;
        TileEntity t = null;
        t = world.getBlockEntity(new net.minecraft.util.math.BlockPos(cposx, cposy, cposz));
        if (t != null && t instanceof MobSpawnerTileEntity) {
            chest = (MobSpawnerTileEntity)t;
            return chest;
        }
        return null;
    }

    private void setSpawnerEntityId(MobSpawnerTileEntity spawner, net.minecraft.util.ResourceLocation id) {
        if (spawner == null || id == null) {
            return;
        }
        SpawnerFixHelper.setSpawnerEntityId(spawner.getSpawner(), id);
    }

    public void makeDungeon(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        int j;
        int width = 12;
        int height = 6;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.MOSSY_COBBLESTONE);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height - 1;
            for (k = 0; k < width; ++k) {
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                k = 0;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
                k = width - 1;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 0; j < height; ++j) {
                i = 0;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
                i = width - 1;
                this.setThisBlock(world, cposx + i, cposy + j, cposz + k);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        MobSpawnerTileEntity tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 1, cposz + width / 2);
        if (tileentitymobspawner != null) {
            int t = world.random.nextInt(12);
            if (t == 0) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "scorpion"));
            }
            if (t == 1) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "alien"));
            }
            if (t == 2) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cryolophosaurus"));
            }
            if (t == 3) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "gamma_metroid"));
            }
            if (t == 4) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "kyuubi"));
            }
            if (t == 5) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "bee"));
            }
            if (t == 6) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
            }
            if (t == 7) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
            }
            if (t == 8) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
            }
            if (t == 9) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
            }
            if (t == 10) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
            }
            if (t == 11) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
            }
        }
        ChestTileEntity chest = null;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + 1, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])this.chestContentsList, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
        }
    }

    public void FastSetBlock(World world, int ix, int iy, int iz, Block id) {
        ChaosPersists.setBlockFast((World)world, (int)ix, (int)iy, (int)iz, (Block)id, (int)0, (int)2);
    }

    public void makeEnormousCastle(World world, int cposx, int cposy, int cposz) {
        int j;
        int k;
        int i;
        int width = 28;
        int height = 16;
        int platformwidth = 11;
        int level = 0;
        if (world.isClientSide) {
            return;
        }
        level = 1 + world.random.nextInt(6);
        if (level <= 3 && world.random.nextInt(3) != 1) {
            level += 3;
        }
        for (i = -20; i < width + 4; ++i) {
            for (j = 1; j < height + 10; ++j) {
                for (k = -4; k < width + 4; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.STONE);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                k = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                i = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                i = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz + 1), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz + width - 2), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + 1, cposz + 1), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + 1, cposz + width - 2), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        for (i = -4; i < width + 4; ++i) {
            for (k = -4; k < width + 4; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(world, cposx + i, cposy, cposz + k, Blocks.STONE);
                }
                if (i != -4 && k != -4 && i != width + 3 && k != width + 3) continue;
                this.FastSetBlock(world, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + 2, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + 2, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + 2, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + 2, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 4, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
        }
        j = height;
        this.buildLevel(world, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Cloud Shark", 1, -1, 5, 1, level);
        j += 10;
        if (level >= 2) {
            this.buildLevel(world, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Lurking Terror", 0, 0, 4, 2, level);
        }
        j += 10;
        if (level >= 3) {
            this.buildLevel(world, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 4, "Rotator", 1, 1, 4, 3, level);
        }
        j += 9;
        if (level >= 4) {
            this.buildLevel(world, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 3, "Bee", 0, 0, 4, 4, level);
        }
        j += 9;
        if (level >= 5) {
            this.buildLevel(world, cposx + 3, cposy + j, cposz + 3, width - 6, 8, 3, "Mantis", 1, 1, 4, 5, level);
        }
        j += 8;
        if (level >= 6) {
            this.buildLevel(world, cposx + 3, cposy + j, cposz + 3, width - 6, 16, 3, "Mothra", 0, 0, 3, 6, level);
        }
        j += 16;
        for (i = 0; i < platformwidth; ++i) {
            j = height;
            for (k = - platformwidth / 2; k <= platformwidth / 2; ++k) {
                this.FastSetBlock(world, cposx + i - 20, cposy + j, cposz + k + width / 2, Blocks.QUARTZ_BLOCK);
                if (i != 0 && i != platformwidth - 1 && k != - platformwidth / 2 && k != platformwidth / 2 || i == 0 && k >= -1 && k <= 1) continue;
                this.FastSetBlock(world, cposx + i - 20, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        for (i = -10; i <= -3; ++i) {
            j = height;
            for (k = -2; k < 3; ++k) {
                if (i == -3 || i == -10) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + width / 2, Blocks.QUARTZ_BLOCK);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = -21;
        for (j = height; j >= 0; --j) {
            for (k = -2; k < 3; ++k) {
                for (int t = 0; t < 6; ++t) {
                    this.FastSetBlock(world, cposx + i, cposy + j + t + 1, cposz + k + width / 2, Blocks.AIR);
                }
                if (j == 0) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + width / 2, Blocks.QUARTZ_BLOCK);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
            --i;
        }
        if (level >= 6) {
            int span = width * 3;
            for (int tries = 0; tries < 100; ++tries) {
                j = -1;
                i = world.random.nextInt(span);
                k = world.random.nextInt(span);
                if (i >= span / 4 && i <= span * 3 / 4 && k >= span / 4 && k <= span * 3 / 4) continue;
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + (i -= span / 2) + width / 2, cposy + j, cposz + (k -= span / 2) + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i + width / 2, cposy + j, cposz + k + width / 2);
                if (tileentitymobspawner == null) continue;
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
        }
    }

    public void buildLevel(World world, int cposx, int cposy, int cposz, int width, int height, int pw, String critter, int stepside, int stepoff, int holelen, int decor, int level) {
        int j;
        int i;
        int k;
        for (i = - pw; i < width + pw; ++i) {
            for (j = 1; j < height; ++j) {
                for (k = - pw; k < width + pw; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
                k = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                Block blk = Blocks.BEDROCK;
                if (k == 0 || k == width - 1) {
                    blk = Blocks.GOLD_BLOCK;
                }
                i = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                i = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = - pw; i < width + pw; ++i) {
            for (k = - pw; k < width + pw; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(world, cposx + i, cposy, cposz + k, Blocks.STONE);
                }
                if (i != - pw && k != - pw && i != width + (pw - 1) && k != width + (pw - 1)) continue;
                this.FastSetBlock(world, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = - height / 2;
        i += width / 2;
        for (j = 1; j < height; ++j) {
            if (stepside != 0) {
                k = -1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.STONE);
            } else {
                k = width;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.STONE);
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
                this.FastSetBlock(world, cposx + i + l, cposy + j, cposz + k, Blocks.AIR);
            }
        }
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner == null) continue;
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
        }
        this.addLevelDecorations(world, cposx, cposy, cposz, width, height, decor, level);
    }

    public void addLevelDecorations(World world, int cposx, int cposy, int cposz, int width, int height, int decor, int difficulty) {
        int j;
        MobSpawnerTileEntity tileentitymobspawner = null;
        int reward = 1;
        String critter = "Alosaurus";
        if (decor == 6) {
            this.FastSetBlock(world, cposx, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx + width - 1, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx + width - 1, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx + width - 1, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx + width - 1, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx + width / 2, cposy + height, cposz + width / 2, Blocks.AIR);
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            for (int i = 1; i < width - 1; ++i) {
                for (j = 1; j < 5; ++j) {
                    for (int k = 1; k < width - 1; ++k) {
                        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.DIRT);
                    }
                }
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 4, cposz + width / 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
            for (j = 0; j < 10; ++j) {
                this.FastSetBlock(world, cposx + 1, cposy + j, cposz + 1, Blocks.AIR);
            }
            this.fill_chests(world, cposx, cposy + 4, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(world, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(world, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(world, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(world, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
        }
    }

    private void fill_chests(World world, int cposx, int cposy, int cposz, int width, int height, int decor, int reward) {
        ChestTileEntity chest = null;
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
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + 1, cposy + 1, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.ThePrinceEgg, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width - 2, cposy + 1, cposz + width / 2, 4, 3);
        chest = this.getChestTileEntity(world, cposx + width - 2, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.RoyalHelmet, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.RoyalBody, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + 1, cposz + 1, 3, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + 1, cposz + 1);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.RoyalLegs, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.RoyalBoots, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + 1, cposz + width - 2, 2, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + 1, cposz + width - 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.MyRoyal, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
    }

    public void makeRotatorStation(World world, int cposx, int cposy, int cposz) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 4, cposz), ChaosPersists.CrystalStone.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 5, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 5, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 6, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 6, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 7, cposz), ChaosPersists.CrystalStone.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 8, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx, cposy + 8, cposz, 2, 3);
        chest = this.getChestTileEntity(world, cposx, cposy + 8, cposz);
        if (chest != null) {
            chest.setItem(1, new ItemStack(ChaosPersists.RotatorEgg, 1 + world.random.nextInt(5)));
            chest.setItem(2, new ItemStack(ChaosPersists.CrystalCoal.asItem(), 4 + world.random.nextInt(16)));
            chest.setItem(3, new ItemStack(ChaosPersists.CrystalCoal.asItem(), 4 + world.random.nextInt(16)));
        }
    }

    public void makeBeeHive(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        int j;
        int width = 10;
        int height = 30;
        if (world.isClientSide) {
            return;
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < 5; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy - j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy - j, cposz + k, Blocks.COAL_ORE);
            }
        }
        Block blk = Blocks.COAL_ORE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 1; j < height; ++j) {
                    if (k == 0 || i == 0 || k == width - 1 || i == width - 1) {
                        blk = Blocks.COAL_ORE;
                        if ((j & 1) == 1) {
                            blk = Blocks.GOLD_ORE;
                        }
                        this.FastSetBlock(world, cposx + i, cposy - j, cposz + k, blk);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy - j, cposz + k, Blocks.AIR);
                }
            }
        }
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy - 2 - j * (height / 4), cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy - 2 - j * (height / 4), cposz + width / 2);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "bee"));
        }
        this.fill_beehive_chests(world, cposx, cposy, cposz, width, height);
    }

    private void fill_beehive_chests(World world, int cposx, int cposy, int cposz, int width, int height) {
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.beeContentsList;
        for (int j = 2; j < height - 1; j += 2) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy - j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
            this.setBlockMeta(world,cposx + 1, cposy - j, cposz + width / 2, 5, 3);
            chest = this.getChestTileEntity(world, cposx + 1, cposy - j, cposz + width / 2);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(1 + world.random.nextInt(5)));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy - j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
            this.setBlockMeta(world,cposx + width - 2, cposy - j, cposz + width / 2, 4, 3);
            chest = this.getChestTileEntity(world, cposx + width - 2, cposy - j, cposz + width / 2);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(1 + world.random.nextInt(5)));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy - j, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
            this.setBlockMeta(world,cposx + width / 2, cposy - j, cposz + 1, 3, 3);
            chest = this.getChestTileEntity(world, cposx + width / 2, cposy - j, cposz + 1);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(1 + world.random.nextInt(5)));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy - j, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
            this.setBlockMeta(world,cposx + width / 2, cposy - j, cposz + width - 2, 2, 3);
            chest = this.getChestTileEntity(world, cposx + width / 2, cposy - j, cposz + width - 2);
            if (chest == null) continue;
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(1 + world.random.nextInt(5)));
        }
    }

    public void makeHauntedHouse(World world, int cposx, int cposy, int cposz) {
        int k;
        int j;
        int i;
        int deltax = 0;
        int deltaz = 0;
        boolean bid = false;
        boolean dirx = false;
        boolean dirz = false;
        int stuffdir = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        int length = 3;
        int width = 3;
        int height = 3;
        deltax = 1;
        stuffdir = 2;
        int x = cposx;
        int z = cposz;
        int y = cposy;
        if (world.isClientSide) {
            return;
        }
        for (i = - width; i <= width; ++i) {
            for (j = - length; j <= length; ++j) {
                for (k = 0; k <= height + 1; ++k) {
                    if (k == height + 1) {
                        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.OAK_PLANKS.defaultBlockState(), 2);
                        continue;
                    }
                    if (k == 0) {
                        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.COBBLESTONE.defaultBlockState(), 2);
                        continue;
                    }
                    if (i == width || j == length || i == - width || j == - length) {
                        if (k == height) {
                            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.GLASS.defaultBlockState(), 2);
                            continue;
                        }
                        if ((k == 1 || k == 2) && i == deltax * width && j == deltaz * length) {
                            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.AIR.defaultBlockState(), 2);
                            continue;
                        }
                        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.OAK_PLANKS.defaultBlockState(), 2);
                        continue;
                    }
                    this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
        i = 2;
        k = 1;
        j = length - 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.FURNACE.defaultBlockState(), 2);
        this.setBlockMeta(world,x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax, stuffdir, 3);
        i = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.CRAFTING_TABLE.defaultBlockState(), 2);
        i = 0;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax, stuffdir, 3);
        ChestTileEntity chest = this.getChestTileEntity(world, x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
        if (chest != null) {
            if (world.random.nextInt(2) == 0) {
                chest.setItem(0, new ItemStack(Items.COMPASS));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(1, new ItemStack((Item)Items.MAP));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(2, new ItemStack(Items.COOKED_PORKCHOP, 8));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(3, new ItemStack(Items.TORCH, 32));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(4, new ItemStack(Items.COAL, 16));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(5, new ItemStack(Items.RED_BED));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(6, new ItemStack(Items.RED_BED));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(7, new ItemStack(Items.OAK_DOOR));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(8, new ItemStack(Items.IRON_PICKAXE));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(9, new ItemStack(Items.IRON_SWORD));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(10, new ItemStack(Items.IRON_AXE));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(11, new ItemStack(Items.BUCKET));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(12, new ItemStack(ChaosPersists.MyOreSaltBlock, 4));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(13, new ItemStack((Block)Blocks.CHEST));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost_pumpkin_skelly"));
        }
    }

    public void makeMantisHive(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        int j;
        int width = 13;
        MobSpawnerTileEntity tileentitymobspawner = null;
        if (world.isClientSide) {
            return;
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < 20; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
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
                        Block blk = Blocks.GOLD_ORE;
                        if ((yoff & 1) != 0) {
                            blk = Blocks.EMERALD_ORE;
                        }
                        this.FastSetBlock(world, cposx + i + xoff, cposy - yoff, cposz + k + zoff, blk);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i + xoff, cposy - yoff, cposz + k + zoff, Blocks.AIR);
                }
            }
            if (width <= 11 && width >= 7) {
                this.fill_mantishive_chests(world, cposx + xoff, cposy - yoff, cposz + zoff, width, 0);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy + j - yoff, cposz + yoff), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff, cposy + j - yoff, cposz + yoff);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "mantis"));
        }
    }

    private void fill_mantishive_chests(World world, int cposx, int cposy, int cposz, int width, int height) {
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.mantisContentsList;
        int j = height;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + 1, cposy + j, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + j, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width - 2, cposy + j, cposz + width / 2, 4, 3);
        chest = this.getChestTileEntity(world, cposx + width - 2, cposy + j, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + j, cposz + 1, 3, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + j, cposz + width - 2, 2, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + width - 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
    }

    public void makeKyuubiDungeon(World world, int cposx, int cposy, int cposz) {
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
        if (world.isClientSide) {
            return;
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < 5; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy - j, cposz + k, Blocks.AIR);
                }
            }
        }
        j = height;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.SANDSTONE);
            }
        }
        this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2, Blocks.AIR);
        Block blk = Blocks.SANDSTONE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        blk = Blocks.STONE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = -1; j > - depth; --j) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 1; i < width - 1; ++i) {
            for (k = 1; k < width - 1; ++k) {
                for (j = - depth; j > - depth + 2; --j) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.WATER);
                }
            }
        }
        // Keep the shaft open; this layer was creating a random-looking stone plug in the drop.
        for (i = 1; i < width - 1; ++i) {
            for (k = 1; k < width - 1; ++k) {
                j = - depth + 2;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
            }
        }
        int x = cposx + width + length - 2;
        int z = cposz - rwidth / 2;
        int y = cposy - depth;
        blk = Blocks.NETHERRACK;
        for (i = 0; i < rlength; ++i) {
            for (k = 0; k < rwidth; ++k) {
                for (j = 0; j < rheight; ++j) {
                    if (k == 0 || k == rwidth - 1 || j == 0 || j == rheight - 1 || i == 0 || i == rlength - 1) {
                        this.FastSetBlock(world, x + i, y + j, z + k, blk);
                        continue;
                    }
                    this.FastSetBlock(world, x + i, y + j, z + k, Blocks.AIR);
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
                        blk = Blocks.STONE;
                        if (j > 0 && j < width - 1) {
                            blk = Blocks.LAVA;
                        }
                        this.FastSetBlock(world, x + i, y + j, z + k, blk);
                        continue;
                    }
                    this.FastSetBlock(world, x + i, y + j, z + k, Blocks.AIR);
                }
            }
        }
        x = cposx + width + length - 2;
        z = cposz - rwidth / 2;
        y = cposy - depth;
        this.addlavasquare(world, x + 2, ++y, z + 2);
        this.addlavasquare(world, x + 4, y, z + 6);
        this.addlavasquare(world, x + 12, y, z + 10);
        this.addlavasquare(world, x + 6, y, z + 15);
        this.addlavasquare(world, x + 3, y, z + 22);
        this.addkyuubi(world, x + rlength / 4, y, z + rwidth * 3 / 4 - 3);
        this.addblaze(world, x + rlength * 2 / 3 - 3, y, z + rwidth / 4 - 2);
        this.FastSetBlock(world, x + 7, y, z + 1, (Block)Blocks.FIRE);
        this.FastSetBlock(world, x + 5, y, z + 9, (Block)Blocks.FIRE);
        this.FastSetBlock(world, x + 2, y, z + 12, (Block)Blocks.FIRE);
        this.FastSetBlock(world, x + 16, y, z + 18, (Block)Blocks.FIRE);
        this.FastSetBlock(world, x + 2, y, z + 27, (Block)Blocks.FIRE);
        this.FastSetBlock(world, x + 18, y, z + 28, (Block)Blocks.FIRE);
    }

    private void addlavasquare(World world, int x, int y, int z) {
        this.FastSetBlock(world, x - 1, y, z, Blocks.NETHERRACK);
        this.FastSetBlock(world, x + 1, y, z, Blocks.NETHERRACK);
        this.FastSetBlock(world, x, y, z + 1, Blocks.NETHERRACK);
        this.FastSetBlock(world, x, y, z - 1, Blocks.NETHERRACK);
        this.FastSetBlock(world, x, y, z, Blocks.LAVA);
    }

    private void addkyuubi(World world, int x, int y, int z) {
        int i;
        int k;
        int width = 9;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        MobSpawnerTileEntity tileentitymobspawner = null;
        chestContents = this.kyuubiContentsList;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                    this.FastSetBlock(world, x + i, y, z + k, Blocks.NETHER_BRICKS);
                    continue;
                }
                this.FastSetBlock(world, x + i, y, z + k, Blocks.LAVA);
            }
        }
        width = 7;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                    this.FastSetBlock(world, x + i + 1, y + 1, z + k + 1, Blocks.NETHER_BRICKS);
                    continue;
                }
                this.FastSetBlock(world, x + i + 1, y + 1, z + k + 1, Blocks.LAVA);
            }
        }
        for (int j = 0; j < 3; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + 4, y + j + 2, z + 4), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, x + 4, y + j + 2, z + 4);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "kyuubi"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + 4, y + 5, z + 4), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x + 4, y + 5, z + 4, 2, 3);
        chest = this.getChestTileEntity(world, x + 4, y + 5, z + 4);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(7 + world.random.nextInt(7)));
        }
    }

    private void addblaze(World world, int x, int y, int z) {
        int j;
        int k;
        int i;
        int width = 7;
        int height = 4;
        int xx = x;
        int yy = y;
        int zz = z;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        MobSpawnerTileEntity tileentitymobspawner = null;
        chestContents = this.blazeContentsList;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    this.FastSetBlock(world, xx + i, yy + j, zz + k, Blocks.OBSIDIAN);
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
                    this.FastSetBlock(world, xx + i, yy + j, zz + k, Blocks.OBSIDIAN);
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
                    this.FastSetBlock(world, xx + i, yy + j, zz + k, Blocks.OBSIDIAN);
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
                    this.FastSetBlock(world, xx + i, yy + j, zz + k, Blocks.OBSIDIAN);
                }
            }
        }
        for (j = 0; j < 2; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(xx - 1, yy + height + j - 3, zz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, xx - 1, yy + height + j - 3, zz);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "blaze"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(xx + 1, yy + height + j - 3, zz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, xx + 1, yy + height + j - 3, zz);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "blaze"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(xx, yy + height + j - 3, zz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, xx, yy + height + j - 3, zz - 1);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "blaze"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(xx, yy + height + j - 3, zz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, xx, yy + height + j - 3, zz + 1);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "blaze"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x, y + 4, z + 3), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x, y + 4, z + 3, 4, 3);
        chest = this.getChestTileEntity(world, x, y + 4, z + 3);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + 3, y + 4, z), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x + 3, y + 4, z, 2, 3);
        chest = this.getChestTileEntity(world, x + 3, y + 4, z);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + 3, y + 4, z + 6), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x + 3, y + 4, z + 6, 3, 3);
        chest = this.getChestTileEntity(world, x + 3, y + 4, z + 6);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + 6, y + 4, z + 3), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x + 6, y + 4, z + 3, 5, 3);
        chest = this.getChestTileEntity(world, x + 6, y + 4, z + 3);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(5)));
        }
    }

    public void makeSmallBeeHive(World world, int cposx, int cposy, int cposz) {
        int blk;
        int j;
        int k;
        int i;
        int width = 7;
        int height = 21;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        if (world.isClientSide) {
            return;
        }
        for (i = -3; i < width + 3; ++i) {
            for (j = height * 2 / 3; j < height; ++j) {
                for (k = -3; k < width + 3; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                j = height * 2 / 3;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.SPONGE);
                blk = world.random.nextInt(height / 3);
                blk *= 2;
                blk -= Math.abs(i - width / 2);
                if ((blk -= Math.abs(k - width / 2)) < 1) {
                    blk = 1;
                }
                if (i == width / 2 && k == width / 2) {
                    blk = height * 2 / 3;
                }
                for (j = 0; j < blk; ++j) {
                    this.FastSetBlock(world, cposx + i, cposy + height * 2 / 3 - j, cposz + k, Blocks.MOSSY_COBBLESTONE);
                }
            }
        }
        j = height * 2 / 3;
        for (blk = 0; blk < height / 6; ++blk) {
            ++j;
            for (i = 0; i < width; ++i) {
                for (k = 0; k < width; ++k) {
                    if (k == 0 || i == 0 || k == width - 1 || i == width - 1) {
                        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.SPONGE);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
            ++j;
            for (i = -1; i < width + 1; ++i) {
                for (k = -1; k < width + 1; ++k) {
                    if (k == -1 || i == -1 || k == width || i == width) {
                        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.SPONGE);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        ++j;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.SPONGE);
            }
        }
        j = height * 2 / 3 + 1;
        for (i = -1; i < 1; ++i) {
            for (k = 2; k < 4; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k, Blocks.AIR);
                this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k, Blocks.AIR);
            }
        }
        for (blk = 0; blk < 3; ++blk) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + blk + j, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + blk + j, cposz + 1);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "bee"));
        }
        chestContents = this.beeContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + j, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(7 + world.random.nextInt(5)));
        }
    }

    public void makeShadowDungeon(World world, int cposx, int cposy, int cposz) {
        int k;
        Block blk;
        int i;
        int width;
        int totalwidth = 19;
        MobSpawnerTileEntity tileentitymobspawner = null;
        String whichmob = null;
        if (world.isClientSide) {
            return;
        }
        int yoff = 0;
        int zoff = 0;
        int xoff = 0;
        for (width = totalwidth; width > 0; width -= 2) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < width; ++k) {
                    if (k == 0 || k == width - 1 || i == 0 || i == width - 1) {
                        blk = Blocks.OBSIDIAN;
                        if ((yoff & 1) != 0) {
                            blk = Blocks.BEDROCK;
                        }
                        if (k >= width / 2 - 1 && k <= width / 2 + 1 || i >= width / 2 - 1 && i <= width / 2 + 1) {
                            blk = Blocks.SOUL_SAND;
                        }
                        this.FastSetBlock(world, cposx + i + xoff, cposy - yoff, cposz + k + zoff, blk);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i + xoff, cposy - yoff, cposz + k + zoff, Blocks.AIR);
                }
            }
            if (width <= 15 && width >= 9) {
                if ((yoff & 1) != 0) {
                    this.fill_shadow_chests(world, cposx + xoff, cposy - yoff, cposz + zoff, width, 0);
                    whichmob = "Ender Reaper";
                } else {
                    whichmob = "Nightmare";
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff + 1, cposy - yoff, cposz + zoff + 1), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff + 1, cposy - yoff, cposz + zoff + 1);
                if (tileentitymobspawner != null) {
                    this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", whichmob.toLowerCase().replace(' ', '_')));
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff + width - 2, cposy - yoff, cposz + zoff + 1), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff + width - 2, cposy - yoff, cposz + zoff + 1);
                if (tileentitymobspawner != null) {
                    this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", whichmob.toLowerCase().replace(' ', '_')));
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff + 1, cposy - yoff, cposz + zoff + width - 2), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff + 1, cposy - yoff, cposz + zoff + width - 2);
                if (tileentitymobspawner != null) {
                    this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", whichmob.toLowerCase().replace(' ', '_')));
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff + width - 2, cposy - yoff, cposz + zoff + width - 2), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff + width - 2, cposy - yoff, cposz + zoff + width - 2);
                if (tileentitymobspawner != null) {
                    this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", whichmob.toLowerCase().replace(' ', '_')));
                }
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
                        blk = Blocks.OBSIDIAN;
                        if ((yoff & 1) != 0) {
                            blk = Blocks.BEDROCK;
                        }
                        this.FastSetBlock(world, cposx + i + xoff, cposy + yoff, cposz + k + zoff, blk);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i + xoff, cposy + yoff, cposz + k + zoff, Blocks.AIR);
                }
            }
            ++xoff;
            ++zoff;
            ++yoff;
        }
    }

    private void fill_shadow_chests(World world, int cposx, int cposy, int cposz, int width, int height) {
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.shadowContentsList;
        int j = height;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + 1, cposy + j, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + j, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + j, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width - 2, cposy + j, cposz + width / 2, 4, 3);
        chest = this.getChestTileEntity(world, cposx + width - 2, cposy + j, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + j, cposz + 1, 3, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + j, cposz + width - 2, 2, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + width - 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(7)));
        }
    }

    public void makeAlienWTFDungeon(World world, int cposx, int cposy, int cposz) {
        int j;
        Block blk;
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
                        this.FastSetBlock(world, cposx + i - 2, cposy + j, cposz + k - 2, Blocks.LAPIS_ORE);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i - 2, cposy + j, cposz + k - 2, Blocks.AIR);
                }
            }
        }
        int s = 0;
        --cposx;
        --cposz;
        for (j = 3; j < depth; ++j) {
            for (i = 0; i < 4; ++i) {
                for (k = 0; k < 4; ++k) {
                    blk = Blocks.AIR;
                    if (i == 0 || k == 0 || i == 3 || k == 3) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)0, (int)2);
                }
            }
            switch (s) {
                case 0: {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + j), (int)(cposz + 1), (Block)Blocks.STONE, (int)0, (int)2);
                    break;
                }
                case 1: {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + j), (int)(cposz + 1), (Block)Blocks.STONE, (int)0, (int)2);
                    break;
                }
                case 2: {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + j), (int)(cposz + 2), (Block)Blocks.STONE, (int)0, (int)2);
                    break;
                }
                default: {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + j), (int)(cposz + 2), (Block)Blocks.STONE, (int)0, (int)2);
                }
            }
            if (++s <= 3) continue;
            s = 0;
        }
        this.makePart(world, ++cposx, cposy, ++cposz + 7, 9, 5, 1, 1, 1);
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    if (i == 0 || i == xwidth - 1) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 2, blk);
                }
            }
        }
        this.makePart(world, cposx + 7, cposy, cposz, 11, 6, 1, -1, 2);
        xwidth = 6;
        zwidth = 3;
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    if (k == 0 || k == zwidth - 1) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(world, cposx + i + 2, cposy + j, cposz - k, blk);
                }
            }
        }
        this.makePart(world, cposx - 7, cposy, cposz, 13, 7, -1, 1, 3);
        xwidth = 6;
        zwidth = 3;
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    if (k == 0 || k == zwidth - 1) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(world, cposx - i - 2, cposy + j, cposz + k, blk);
                }
            }
        }
        this.makePart(world, cposx, cposy, cposz - 7, 15, 8, -1, -1, 4);
        xwidth = 3;
        zwidth = 6;
        for (i = 0; i < xwidth; ++i) {
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < 4; ++j) {
                    blk = Blocks.AIR;
                    if (j == 0 || j == 3) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    if (i == 0 || i == xwidth - 1) {
                        blk = Blocks.LAPIS_ORE;
                    }
                    this.FastSetBlock(world, cposx - i, cposy + j, cposz - k - 2, blk);
                }
            }
        }
    }

    private void makePart(World world, int cposx, int cposy, int cposz, int width, int height, int dx, int dz, int difficulty) {
        int i;
        int j;
        int k;
        Block blk;
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < width; ++k) {
                    this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                blk = Blocks.QUARTZ_BLOCK;
                if (i == width / 2 || k == width / 2) {
                    blk = Blocks.OBSIDIAN;
                }
                this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, blk);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                blk = Blocks.OBSIDIAN;
                this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, blk);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                blk = Blocks.OBSIDIAN;
                k = 0;
                this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, blk);
                k = width - 1;
                this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, blk);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 0; j < height; ++j) {
                i = 0;
                this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, Blocks.OBSIDIAN);
                i = width - 1;
                this.FastSetBlock(world, cposx + i * dx, cposy + j, cposz + k * dz, Blocks.OBSIDIAN);
            }
        }
        for (j = 0; j < difficulty; ++j) {
            int t;
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + dx * width / 2, cposy + j + 2, cposz + dz * width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + dx * width / 2, cposy + j + 2, cposz + dz * width / 2);
            if (tileentitymobspawner != null) {
                t = world.random.nextInt(2);
                if (t == 0) {
                    SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "alien"));
                }
                if (t == 1) {
                    SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "gamma_metroid"));
                }
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + dx * width / 2 + dx, cposy + j + 2, cposz + dz * width / 2 + dz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + dx * width / 2 + dx, cposy + j + 2, cposz + dz * width / 2 + dz);
            if (tileentitymobspawner == null) continue;
            t = world.random.nextInt(2);
            if (t == 0) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "alien"));
            }
            if (t != 1) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "gamma_metroid"));
        }
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.AlienWTFContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width * dx / 2, cposy + 1, cposz + dz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + width * dx / 2, cposy + 1, cposz + dz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
        if (difficulty > 1) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width * dx / 2, cposy + 1, cposz + (width - 2) * dz), Blocks.CHEST.defaultBlockState(), 2);
            chest = this.getChestTileEntity(world, cposx + width * dx / 2, cposy + 1, cposz + (width - 2) * dz);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
            }
        }
        if (difficulty > 2) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + dx, cposy + 1, cposz + width / 2 * dz), Blocks.CHEST.defaultBlockState(), 2);
            chest = this.getChestTileEntity(world, cposx + dx, cposy + 1, cposz + width / 2 * dz);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
            }
        }
        if (difficulty > 3) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + (width - 2) * dx, cposy + 1, cposz + width / 2 * dz), Blocks.CHEST.defaultBlockState(), 2);
            chest = this.getChestTileEntity(world, cposx + (width - 2) * dx, cposy + 1, cposz + width / 2 * dz);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
            }
        }
    }

    public void makeEnderKnightDungeon(World world, int cposx, int cposy, int cposz) {
        Block blk;
        int k;
        int j;
        int height = 6;
        int zwidth = 5;
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (int i = 0; i < 4; ++i) {
            for (k = 0; k < 5; ++k) {
                for (j = 0; j < 5; ++j) {
                    this.FastSetBlock(world, cposx, cposy + j, cposz + k, Blocks.AIR);
                }
            }
            ++cposx;
        }
        zwidth = 5;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = Blocks.OBSIDIAN;
                if (k == 2 && j >= 1 && j <= 3) {
                    blk = Blocks.AIR;
                }
                this.FastSetBlock(world, cposx, cposy + j, cposz + k, blk);
            }
        }
        ++cposx;
        --cposz;
        zwidth = 7;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = Blocks.AIR;
                if (j == 0 || j == height - 1) {
                    blk = Blocks.OBSIDIAN;
                }
                if (j == 0 && k > 0 && k < zwidth - 1) {
                    blk = Blocks.END_STONE;
                }
                if (k == 0 || k == zwidth - 1) {
                    blk = Blocks.OBSIDIAN;
                }
                this.FastSetBlock(world, cposx, cposy + j, cposz + k, blk);
            }
            if (k != 1 && k != 2 && k != zwidth - 3 && k != zwidth - 2) continue;
            this.makeShelves(world, cposx, cposy + 1, cposz + k);
        }
        --cposz;
        for (int m = 0; m < 5; ++m) {
            ++cposx;
            zwidth = 9;
            for (k = 0; k < zwidth; ++k) {
                for (j = 0; j < height; ++j) {
                    blk = Blocks.AIR;
                    if (j == 0 || j == height - 1) {
                        blk = Blocks.OBSIDIAN;
                    }
                    if (j == 0 && k > 0 && k < zwidth - 1) {
                        blk = Blocks.END_STONE;
                    }
                    if (k == 0 || k == zwidth - 1) {
                        blk = Blocks.OBSIDIAN;
                    }
                    this.FastSetBlock(world, cposx, cposy + j, cposz + k, blk);
                }
                if (k == 1 || k == 2 || k == zwidth - 3 || k == zwidth - 2) {
                    this.makeShelves(world, cposx, cposy + 1, cposz + k);
                }
                if (m != 2 || k != 4) continue;
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz + k);
                if (tileentitymobspawner != null) {
                    SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz + k);
                if (tileentitymobspawner == null) continue;
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
            }
        }
        ++cposz;
        ++cposx;
        zwidth = 7;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = Blocks.AIR;
                if (j == 0 || j == height - 1) {
                    blk = Blocks.OBSIDIAN;
                }
                if (j == 0 && k > 0 && k < zwidth - 1) {
                    blk = Blocks.END_STONE;
                }
                if (k == 0 || k == zwidth - 1) {
                    blk = Blocks.OBSIDIAN;
                }
                this.FastSetBlock(world, cposx, cposy + j, cposz + k, blk);
            }
            if (k != 1 && k != 2 && k != zwidth - 3 && k != zwidth - 2) continue;
            this.makeShelves(world, cposx, cposy + 1, cposz + k);
        }
        ++cposz;
        ++cposx;
        zwidth = 5;
        for (k = 0; k < zwidth; ++k) {
            for (j = 0; j < height; ++j) {
                blk = Blocks.OBSIDIAN;
                this.FastSetBlock(world, cposx, cposy + j, cposz + k, blk);
            }
        }
    }

    private void makeShelves(World world, int cposx, int cposy, int cposz) {
        int j;
        int k;
        int i = world.random.nextInt(4);
        Block blk = Blocks.AIR;
        if (i == 0) {
            ChestTileEntity chest = null;
            WeightedRandomChestContent[] chestContents = null;
            chestContents = this.KnightContentsList;
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy, cposz), Blocks.CHEST.defaultBlockState(), 2);
            chest = this.getChestTileEntity(world, cposx, cposy, cposz);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
            }
        }
        if (i == 1) {
            blk = Blocks.BOOKSHELF;
            k = 1 + world.random.nextInt(4);
            for (j = 0; j < k; ++j) {
                this.FastSetBlock(world, cposx, cposy + j, cposz, blk);
            }
        }
        if (i == 2) {
            blk = Blocks.COBWEB;
            k = 1 + world.random.nextInt(4);
            for (j = 0; j < k; ++j) {
                this.FastSetBlock(world, cposx, cposy + j, cposz, blk);
            }
        }
    }

    public void makePlayPool(World world, int cposx, int cposy, int cposz) {
        int i;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.SquidContentsList;
        for (i = 0; i < 4; ++i) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + 16, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + 16, cposz);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "attack_squid"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 17, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + 17, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + 17, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
        for (i = 0; i < 4; ++i) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + 18, cposz), Blocks.WATER.defaultBlockState(), 3);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 18, cposz), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 4, cposy + 18, cposz), Blocks.WATER.defaultBlockState(), 3);
    }

    public void makeWaterDragonLair(World world, int cposx, int cposy, int cposz) {
        float curx;
        float curdeg;
        int i;
        int j;
        float curz;
        Block blk;
        float currad;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.WaterDragonContentsList;
        float radius = 10.0f;
        for (currad = 0.0f; currad < radius; currad += 0.33f) {
            for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                blk = Blocks.BEDROCK;
                if (currad > 5.0f && currad < 6.0f) {
                    blk = Blocks.IRON_BLOCK;
                }
                this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 7, (int)((float)cposz + curz + 0.5f), blk);
            }
        }
        for (i = 1; i < 10; ++i) {
            this.FastSetBlock(world, (int)((float)(cposx + i) + 0.5f), cposy + 7, (int)((float)cposz + 0.5f), Blocks.IRON_BLOCK);
            this.FastSetBlock(world, (int)((float)(cposx - i) + 0.5f), cposy + 7, (int)((float)cposz + 0.5f), Blocks.IRON_BLOCK);
            this.FastSetBlock(world, (int)((float)cposx + 0.5f), cposy + 7, (int)((float)(cposz + i) + 0.5f), Blocks.IRON_BLOCK);
            this.FastSetBlock(world, (int)((float)cposx + 0.5f), cposy + 7, (int)((float)(cposz - i) + 0.5f), Blocks.IRON_BLOCK);
        }
        this.FastSetBlock(world, (int)((float)cposx + 0.5f), cposy + 7, (int)((float)cposz + 0.5f), Blocks.AIR);
        this.FastSetBlock(world, (int)((float)(cposx + 1) + 0.5f), cposy + 7, (int)((float)cposz + 0.5f), Blocks.GLOWSTONE);
        this.FastSetBlock(world, (int)((float)(cposx - 1) + 0.5f), cposy + 7, (int)((float)cposz + 0.5f), Blocks.GLOWSTONE);
        this.FastSetBlock(world, (int)((float)cposx + 0.5f), cposy + 7, (int)((float)(cposz + 1) + 0.5f), Blocks.GLOWSTONE);
        this.FastSetBlock(world, (int)((float)cposx + 0.5f), cposy + 7, (int)((float)(cposz - 1) + 0.5f), Blocks.GLOWSTONE);
        currad = 10.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 1, (int)((float)cposz + curz + 0.5f), Blocks.GLOWSTONE);
            blk = Blocks.LAPIS_BLOCK;
            if (world.random.nextInt(2) == 0) {
                blk = ChaosPersists.MyWaterDragonSpawnBlock;
            }
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 2, (int)((float)cposz + curz + 0.5f), blk);
            blk = Blocks.LAPIS_BLOCK;
            if (world.random.nextInt(2) == 0) {
                blk = ChaosPersists.MyWaterDragonSpawnBlock;
            }
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 3, (int)((float)cposz + curz + 0.5f), blk);
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 4, (int)((float)cposz + curz + 0.5f), Blocks.GLOWSTONE);
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), Blocks.BEDROCK);
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 6, (int)((float)cposz + curz + 0.5f), Blocks.BEDROCK);
        }
        for (i = -3; i <= 3; ++i) {
            for (j = -3; j <= 3; ++j) {
                this.FastSetBlock(world, cposx + i, cposy, cposz + j, (Block)Blocks.SAND);
                this.FastSetBlock(world, cposx + i, cposy - 1, cposz + j, Blocks.STONE);
            }
        }
        for (i = -2; i <= 2; ++i) {
            for (j = -2; j <= 2; ++j) {
                this.FastSetBlock(world, cposx + i, cposy + 3, cposz + j, (Block)Blocks.OAK_LEAVES);
            }
        }
        this.FastSetBlock(world, cposx, cposy + 4, cposz, (Block)Blocks.OAK_LEAVES);
        this.FastSetBlock(world, cposx, cposy + 3, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx, cposy + 2, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx, cposy + 1, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx + 1, cposy + 3, cposz + 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx - 1, cposy + 3, cposz - 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx + 1, cposy + 3, cposz - 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx - 1, cposy + 3, cposz + 1, Blocks.OAK_LOG);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "water_dragon"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "water_dragon"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "water_dragon"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "water_dragon"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz - 1), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz - 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
    }

    public void makeCloudSharkDungeon(World world, int cposx, int cposy, int cposz) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.CloudSharkContentsList;
        this.FastSetBlock(world, cposx, cposy, cposz, Blocks.GLOWSTONE);
        this.FastSetBlock(world, cposx, cposy - 1, cposz, Blocks.GLOWSTONE);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy, cposz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy, cposz - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
    }

    public void makeLeafMonsterDungeon(World world, int cposx, int cposy, int cposz) {
        int j;
        int i;
        int k;
        Block blk;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.LeafMonsterContentsList;
        for (i = -2; i < 6; ++i) {
            for (k = -3; k < 2; ++k) {
                for (j = 0; j < 4; ++j) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                for (j = -1; j > -5; --j) {
                    blk = Blocks.OAK_LOG;
                    Block bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k)).getBlock();
                    if (bid != Blocks.AIR && bid != Blocks.GRASS_BLOCK) continue;
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                for (j = 0; j < 10; ++j) {
                    blk = Blocks.OAK_LOG;
                    if (!(j >= 2 || k != 0 && k != 1 || i != 1 && i != 2)) {
                        blk = Blocks.AIR;
                    }
                    if (k == 1 && (i == 1 || i == 2)) {
                        blk = Blocks.AIR;
                    }
                    if (k == 2 && (i == 1 || i == 2)) continue;
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                for (j = 0; j < 10; ++j) {
                    if (k != 2 || i != 1 && i != 2) continue;
                    blk = Blocks.LADDER;
                    this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.LADDER.defaultBlockState().setValue(net.minecraft.block.LadderBlock.FACING, Direction.NORTH), 3);
                }
            }
        }
        this.FastSetBlock(world, cposx + 1, cposy + 2, cposz - 1, (Block)Blocks.OAK_LEAVES);
        this.FastSetBlock(world, cposx + 2, cposy + 2, cposz - 1, (Block)Blocks.OAK_LEAVES);
        for (i = -3; i < 7; ++i) {
            for (k = -3; k < 7; ++k) {
                j = 9;
                if (i >= 0 && i <= 3 && k >= 0 && k <= 3) continue;
                blk = Blocks.OAK_LOG;
                if (i == -3 || i == 6 || k == -3 || k == 6) {
                    blk = Blocks.OAK_LEAVES;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = -3; i < 7; ++i) {
            for (k = -3; k < 7; ++k) {
                for (j = 10; j < 13; ++j) {
                    blk = Blocks.AIR;
                    if (i == -3 || i == 6 || k == -3 || k == 6) {
                        blk = Blocks.OAK_LEAVES;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = -2; i < 6; ++i) {
            for (k = -2; k < 6; ++k) {
                j = 13;
                blk = Blocks.AIR;
                if (i == -2 || i == 5 || k == -2 || k == 5) {
                    blk = Blocks.OAK_LOG;
                }
                if (i == -1 || i == 4 || k == -1 || k == 4) {
                    blk = Blocks.OAK_LEAVES;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = -1; i < 5; ++i) {
            for (k = -1; k < 5; ++k) {
                j = 14;
                blk = Blocks.OAK_LEAVES;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 0; i < 4; ++i) {
            for (k = 0; k < 4; ++k) {
                j = 15;
                blk = Blocks.OAK_LOG;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 1; i < 3; ++i) {
            for (k = 1; k < 3; ++k) {
                j = 16;
                blk = Blocks.OAK_LEAVES;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 2, cposy + 10, cposz - 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 2, cposy + 10, cposz - 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "leaf_monster"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 5, cposy + 10, cposz + 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 5, cposy + 10, cposz + 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "leaf_monster"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 2, cposy + 10, cposz + 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 2, cposy + 10, cposz + 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "leaf_monster"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 5, cposy + 10, cposz - 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 5, cposy + 10, cposz - 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "leaf_monster"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 10, cposz + 5), Blocks.CHEST.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + 10, cposz + 5), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + 10, cposz + 5);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(12 + world.random.nextInt(5)));
        }
    }

    public void makeMiniDungeon(World world, int cposx, int cposy, int cposz) {
        int j;
        int i;
        int k;
        Block blk;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.MiniContentsList;
        for (i = 0; i < 10; ++i) {
            for (k = 0; k < 10; ++k) {
                for (j = 0; j < 7; ++j) {
                    blk = Blocks.AIR;
                    if (i == 0 || k == 0 || i == 9 || k == 9) {
                        blk = Blocks.IRON_BARS;
                    }
                    if (i == 0 && k == 0) {
                        blk = Blocks.COBBLESTONE;
                    }
                    if (i == 9 && k == 9) {
                        blk = Blocks.COBBLESTONE;
                    }
                    if (i == 0 && k == 9) {
                        blk = Blocks.COBBLESTONE;
                    }
                    if (i == 9 && k == 0) {
                        blk = Blocks.COBBLESTONE;
                    }
                    if (j == 0) {
                        blk = Blocks.COBBLESTONE;
                    }
                    if (j == 6 && (i == 0 || k == 0 || i == 9 || k == 9)) {
                        blk = Blocks.COBBLESTONE;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 1; i < 9; ++i) {
            for (k = 1; k < 9; ++k) {
                j = 7;
                blk = Blocks.AIR;
                if (i == 1 || i == 8 || k == 1 || k == 8) {
                    blk = Blocks.GRASS_BLOCK;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 2; i < 8; ++i) {
            for (k = 2; k < 8; ++k) {
                j = 8;
                blk = Blocks.AIR;
                if (i == 2 || i == 7 || k == 2 || k == 7) {
                    blk = Blocks.GRASS_BLOCK;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        i = -6;
        j = 1;
        k = 3;
        for (int m = 0; m < 6; ++m) {
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.OAK_PLANKS);
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 1, Blocks.OAK_PLANKS);
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 2, Blocks.OAK_PLANKS);
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 3, Blocks.OAK_PLANKS);
            this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k, Blocks.OAK_FENCE);
            this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + 3, Blocks.OAK_FENCE);
            this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k, Blocks.TORCH);
            this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k + 3, Blocks.TORCH);
            ++i;
            ++j;
        }
        for (i = 3; i < 7; ++i) {
            for (k = 3; k < 7; ++k) {
                j = 9;
                blk = Blocks.AIR;
                if (i != 3 && i != 6 && k != 3 && k != 6) continue;
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
                if (tileentitymobspawner == null) continue;
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "butterfly"));
            }
        }
        k = 0;
        i = 0;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.COBBLESTONE);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
        }
        k = 9;
        i = 9;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.COBBLESTONE);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "butterfly"));
        }
        i = 0;
        k = 9;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.COBBLESTONE);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
        }
        i = 9;
        k = 0;
        for (j = 7; j < 11; ++j) {
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.COBBLESTONE);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "butterfly"));
        }
        k = 1;
        i = 1;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
        }
        k = 8;
        i = 8;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "terrible_terror"));
        }
        i = 8;
        k = 1;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "butterfly"));
        }
        i = 1;
        k = 8;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "butterfly"));
        }
        i = 4;
        k = 4;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
        }
        i = 5;
        k = 5;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 3, cposy + 1, cposz + 3), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + 3, cposy + 1, cposz + 3);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
    }

    public void makeGoldFishBowl(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        MobSpawnerTileEntity tileentitymobspawner = null;
        int j = 1;
        Block blk = Blocks.GLASS;
        for (i = 0; i < 5; ++i) {
            for (k = 0; k < 5; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        j = 2;
        for (i = -1; i < 6; ++i) {
            for (k = -1; k < 6; ++k) {
                blk = Blocks.SAND;
                if (i == -1 || k == -1 || i == 5 || k == 5) {
                    blk = Blocks.GLASS;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        j = 3;
        for (i = -1; i < 6; ++i) {
            for (k = -1; k < 6; ++k) {
                blk = Blocks.WATER;
                if (i == -1 || k == -1 || i == 5 || k == 5) {
                    blk = Blocks.GLASS;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        k = 0;
        i = 0;
        blk = Blocks.GLOWSTONE;
        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
        k = 4;
        i = 4;
        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
        k = 4;
        i = 0;
        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
        k = 0;
        i = 4;
        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
        j = 4;
        for (i = -1; i < 6; ++i) {
            for (k = -1; k < 6; ++k) {
                blk = Blocks.WATER;
                if (i == -1 || k == -1 || i == 5 || k == 5) {
                    blk = Blocks.GLASS;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (j = 5; j < 8; ++j) {
            for (i = -1; i < 6; ++i) {
                for (k = -1; k < 6; ++k) {
                    blk = Blocks.AIR;
                    if (i == -1 || k == -1 || i == 5 || k == 5) {
                        blk = Blocks.GLASS;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        j = 8;
        blk = Blocks.GLASS;
        for (i = 0; i < 5; ++i) {
            for (k = 0; k < 5; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        i = 2;
        k = 2;
        j = 6;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "gold_fish"));
        }
    }

    public void makeEnderReaperGraveyard(World world, int cposx, int cposy, int cposz) {
        int i;
        int j;
        int k;
        int width = 11;
        int length = 13;
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 1; j < 5; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    if (world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy - j, cposz + k)).getBlock() != Blocks.AIR) continue;
                    this.FastSetBlock(world, cposx + i, cposy - j, cposz + k, Blocks.END_STONE);
                }
            }
        }
        j = 0;
        Block blk = Blocks.END_STONE;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (j = 1; j < 5; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    blk = Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        blk = Blocks.IRON_BARS;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        i = 1;
        k = 1;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = width - 2;
        k = length - 2;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = 1;
        k = length - 2;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = width - 2;
        k = 1;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        this.makeAGrave(world, cposx, cposy, cposz, 1, 6);
        this.makeAGrave(world, cposx, cposy, cposz, 3, 4);
        this.makeAGrave(world, cposx, cposy, cposz, 5, 4);
        this.makeAGrave(world, cposx, cposy, cposz, 7, 4);
        this.makeAGrave(world, cposx, cposy, cposz, 3, 8);
        this.makeAGrave(world, cposx, cposy, cposz, 5, 8);
        this.makeAGrave(world, cposx, cposy, cposz, 7, 8);
        this.makeAGrave(world, cposx, cposy, cposz, 9, 6);
    }

    public void makeAGrave(World world, int cposx, int cposy, int cposz, int xoff, int zoff) {
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.GraveContentsList;
        this.FastSetBlock(world, cposx + xoff, cposy + 1, cposz + zoff - 1, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + xoff, cposy, cposz + zoff + 1, Blocks.OBSIDIAN);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy, cposz + zoff), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + xoff, cposy, cposz + zoff);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(3)));
        }
    }

    public void makeUrchinSpawner(World world, int cposx, int cposy, int cposz) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        int patchy = 3;
        for (int i = 0; i < patchy; ++i) {
            Block bid = ChaosPersists.CrystalStone;
            if (i == 1) {
                bid = ChaosPersists.CrystalCrystal;
            }
            if (i == 2) {
                bid = ChaosPersists.TigersEye;
            }
            float dx = world.random.nextFloat() - world.random.nextFloat();
            float dz = world.random.nextFloat() - world.random.nextFloat();
            float dy = 0.5f + world.random.nextFloat() / 2.0f;
            int width = world.random.nextInt(2);
            int length = 10 + width * 3 + world.random.nextInt(5);
            if (i != 0) {
                length /= 2;
            }
            float rx = cposx;
            float ry = cposy;
            float rz = cposz;
            for (int iy = 0; iy <= length; ++iy) {
                for (int ix = 0; ix <= width; ++ix) {
                    for (int iz = 0; iz <= width; ++iz) {
                        ChaosPersists.setBlockFast((World)world, (int)((int)(rx + (float)ix)), (int)((int)ry), (int)((int)(rz + (float)iz)), (Block)bid, (int)0, (int)2);
                    }
                }
                ry += dy;
                rx += dx;
                rz += dz;
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "crystal_urchin"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "crystal_urchin"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "crystal_urchin"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy, cposz), Blocks.AIR.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy - 1, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx, cposy - 1, cposz, 2, 3);
        chest = this.getChestTileEntity(world, cposx, cposy - 1, cposz);
        if (chest != null) {
            chest.setItem(1, new ItemStack(ChaosPersists.UrchinEgg, 1 + world.random.nextInt(5)));
            chest.setItem(2, new ItemStack(ChaosPersists.CrystalCoal.asItem(), 4 + world.random.nextInt(16)));
            chest.setItem(3, new ItemStack(ChaosPersists.CrystalCoal.asItem(), 4 + world.random.nextInt(16)));
        }
    }

    public void makeSpitBugLair(World world, int cposx, int cposy, int cposz) {
        int i;
        int green = 5;
        int dark_green = 13;
        int width = 9;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.SpitBugContentsList;
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + width - i + 2), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + width - i + 1), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + width - i), (int)cposz, (Block)Blocks.MOSSY_COBBLESTONE, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - i), (int)(cposy + width - i + 2), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - i), (int)(cposy + width - i + 1), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - i), (int)(cposy + width - i), (int)cposz, (Block)Blocks.MOSSY_COBBLESTONE, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + width + 3), (int)cposz, (Block)Blocks.EMERALD_ORE, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + width + 2), (int)cposz, (Block)Blocks.EMERALD_ORE, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + width + 1), (int)cposz, (Block)Blocks.EMERALD_ORE, (int)0, (int)2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + width + 0, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + width + 0, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spit_bug"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + width - 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + width - 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spit_bug"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + width - 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + width - 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spit_bug"));
        }
        boolean k = false;
        for (i = 0; i < width; ++i) {
            for (int j = - i; j <= i; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx - width + i + 1), (int)cposy, (int)(cposz + j), (Block)ChaosPersists.legacyStainedHardenedClay(green), (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - i - 1), (int)cposy, (int)(cposz + j), (Block)ChaosPersists.legacyStainedHardenedClay(green), (int)0, (int)2);
                if (j == - i || j == i) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx - width + i + 1), (int)(cposy + 1), (int)(cposz + j), (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + width - i - 1), (int)(cposy + 1), (int)(cposz + j), (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx - width + i + 1), (int)(cposy + 2), (int)(cposz + j), (Block)Blocks.STONE_BRICKS, (int)3, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + width - i - 1), (int)(cposy + 2), (int)(cposz + j), (Block)Blocks.STONE_BRICKS, (int)3, (int)2);
                    continue;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx - width + i + 1), (int)(cposy + 1), (int)(cposz + j), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - i - 1), (int)(cposy + 1), (int)(cposz + j), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx - width + i + 1), (int)(cposy + 2), (int)(cposz + j), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - i - 1), (int)(cposy + 2), (int)(cposz + j), (Block)Blocks.AIR, (int)0, (int)2);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(4)));
        }
    }

    public void makeIgloo(World world, int cposx, int cposy, int cposz) {
        float curdeg;
        float curx;
        float curz;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        float currad = 6.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 1, (int)((float)cposz + curz + 0.5f), Blocks.SNOW);
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 2, (int)((float)cposz + curz + 0.5f), Blocks.ICE);
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 3, (int)((float)cposz + curz + 0.5f), Blocks.SNOW);
        }
        currad = 5.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 4, (int)((float)cposz + curz + 0.5f), Blocks.ICE);
        }
        currad = 4.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), Blocks.SNOW);
        }
        currad = 3.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 10.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), Blocks.ICE);
        }
        currad = 2.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 15.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), Blocks.SNOW);
        }
        currad = 1.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 15.0f) {
            curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
            curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + 5, (int)((float)cposz + curz + 0.5f), Blocks.ICE);
        }
        this.FastSetBlock(world, (int)((float)cposx - 6.0f + 0.5f), cposy, (int)((float)cposz + 0.5f), Blocks.OAK_PLANKS);
        this.FastSetBlock(world, (int)((float)cposx - 6.0f + 0.5f), cposy + 1, (int)((float)cposz + 0.5f), Blocks.AIR);
        this.FastSetBlock(world, (int)((float)cposx - 6.0f + 0.5f), cposy + 2, (int)((float)cposz + 0.5f), Blocks.AIR);
        this.placeDoor(world, new BlockPos((int)((float)cposx - 6.0f + 0.5f), cposy + 1, (int)((float)cposz + 0.5f)), Direction.NORTH, (DoorBlock)Blocks.OAK_DOOR);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + 1, cposz - 4), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 2, cposy + 1, cposz - 4);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 1, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 1, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 3, cposy + 1, cposz + 4), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 3, cposy + 1, cposz + 4);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost_pumpkin_skelly"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + 1, cposz - 3), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        chest = this.getChestTileEntity(world, cposx - 3, cposy + 1, cposz - 3);
        if (chest != null) {
            if (world.random.nextInt(2) == 0) {
                chest.setItem(0, new ItemStack(Items.COMPASS));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(1, new ItemStack((Item)Items.MAP));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(2, new ItemStack(Items.COOKED_PORKCHOP, 8));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(3, new ItemStack(Blocks.TORCH, 32));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(4, new ItemStack(Items.COAL, 16));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(5, new ItemStack(Items.RED_BED));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(6, new ItemStack(Items.RED_BED));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(7, new ItemStack(Items.OAK_DOOR));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(8, new ItemStack(Items.IRON_PICKAXE));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(9, new ItemStack(Items.IRON_SWORD));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(10, new ItemStack(Items.IRON_AXE));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(11, new ItemStack(Items.BUCKET));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(13, new ItemStack((Block)Blocks.CHEST));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(14, new ItemStack(Items.GOLD_NUGGET, 6));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(15, new ItemStack(Items.GOLD_NUGGET, 8));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(16, new ItemStack(Items.GOLD_NUGGET, 10));
            }
        }
    }

    public void makeEnderDragonHospital(World world, int cposx, int cposy, int cposz) {
        int j;
        int i;
        int k;
        Block blk;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.HospitalContentsList;
        for (i = 0; i < 10; ++i) {
            for (k = 0; k < 10; ++k) {
                for (j = 0; j < 7; ++j) {
                    blk = Blocks.AIR;
                    if (i == 0 || k == 0 || i == 9 || k == 9) {
                        blk = Blocks.IRON_BARS;
                    }
                    if (i == 0 && k == 0) {
                        blk = Blocks.OBSIDIAN;
                    }
                    if (i == 9 && k == 9) {
                        blk = Blocks.OBSIDIAN;
                    }
                    if (i == 0 && k == 9) {
                        blk = Blocks.OBSIDIAN;
                    }
                    if (i == 9 && k == 0) {
                        blk = Blocks.OBSIDIAN;
                    }
                    if (j == 0) {
                        blk = Blocks.END_STONE;
                    }
                    if (j == 6 && (i == 0 || k == 0 || i == 9 || k == 9)) {
                        blk = Blocks.END_STONE;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 1; i < 9; ++i) {
            for (k = 1; k < 9; ++k) {
                j = 7;
                blk = Blocks.AIR;
                if (i == 1 || i == 8 || k == 1 || k == 8) {
                    blk = ChaosPersists.MyEyeOfEnderBlock;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 2; i < 8; ++i) {
            for (k = 2; k < 8; ++k) {
                j = 8;
                blk = Blocks.AIR;
                if (i == 2 || i == 7 || k == 2 || k == 7) {
                    blk = ChaosPersists.MyEyeOfEnderBlock;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = 3; i < 7; ++i) {
            for (k = 3; k < 7; ++k) {
                j = 9;
                blk = Blocks.AIR;
                if (i == 3 || i == 6 || k == 3 || k == 6) {
                    blk = ChaosPersists.MyEyeOfEnderBlock;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        i = -6;
        j = 1;
        k = 3;
        for (int m = 0; m < 6; ++m) {
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.END_STONE);
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 1, Blocks.END_STONE);
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 2, Blocks.END_STONE);
            this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 3, Blocks.END_STONE);
            this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k, Blocks.IRON_BARS);
            this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + 3, Blocks.IRON_BARS);
            this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k, Blocks.GLOWSTONE);
            this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k + 3, Blocks.GLOWSTONE);
            ++i;
            ++j;
        }
        this.FastSetBlock(world, cposx + 0, cposy + 7, cposz + 0, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 0, cposy + 7, cposz + 9, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 9, cposy + 7, cposz + 0, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 9, cposy + 7, cposz + 9, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 0, cposy + 8, cposz + 0, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 0, cposy + 8, cposz + 9, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 9, cposy + 8, cposz + 0, Blocks.OBSIDIAN);
        this.FastSetBlock(world, cposx + 9, cposy + 8, cposz + 9, Blocks.OBSIDIAN);
        EnderCrystalEntity entityendercrystal = new EnderCrystalEntity(EntityType.END_CRYSTAL, world);
        entityendercrystal.moveTo((double)((float)cposx + 0.5f), (double)(cposy + 9), (double)((float)cposz + 0.5f), world.random.nextFloat() * 360.0f, 0.0f);
        world.addFreshEntity((Entity)entityendercrystal);
        this.FastSetBlock(world, cposx, cposy + 9, cposz, Blocks.BEDROCK);
        entityendercrystal = new EnderCrystalEntity(EntityType.END_CRYSTAL, world);
        entityendercrystal.moveTo((double)((float)cposx + 0.5f), (double)(cposy + 9), (double)((float)cposz + 9.5f), world.random.nextFloat() * 360.0f, 0.0f);
        world.addFreshEntity((Entity)entityendercrystal);
        this.FastSetBlock(world, cposx, cposy + 9, cposz + 9, Blocks.BEDROCK);
        entityendercrystal = new EnderCrystalEntity(EntityType.END_CRYSTAL, world);
        entityendercrystal.moveTo((double)((float)cposx + 9.5f), (double)(cposy + 9), (double)((float)cposz + 0.5f), world.random.nextFloat() * 360.0f, 0.0f);
        world.addFreshEntity((Entity)entityendercrystal);
        this.FastSetBlock(world, cposx + 9, cposy + 9, cposz, Blocks.BEDROCK);
        entityendercrystal = new EnderCrystalEntity(EntityType.END_CRYSTAL, world);
        entityendercrystal.moveTo((double)((float)cposx + 9.5f), (double)(cposy + 9), (double)((float)cposz + 9.5f), world.random.nextFloat() * 360.0f, 0.0f);
        world.addFreshEntity((Entity)entityendercrystal);
        this.FastSetBlock(world, cposx + 9, cposy + 9, cposz + 9, Blocks.BEDROCK);
        i = 3;
        k = 3;
        j = 9;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = 3;
        k = 6;
        j = 9;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = 6;
        k = 3;
        j = 9;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = 6;
        k = 6;
        j = 9;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        i = 1;
        k = 1;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
        }
        i = 1;
        k = 8;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
        }
        i = 8;
        k = 1;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
        }
        i = 8;
        k = 8;
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 4, cposy + 1, cposz + 4), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + 4, cposy + 1, cposz + 4);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(5)));
        }
    }

    public void makeCrystalHauntedHouse(World world, int cposx, int cposy, int cposz) {
        int k;
        int j;
        int i;
        int deltax = 0;
        int deltaz = 0;
        boolean bid = false;
        boolean dirx = false;
        boolean dirz = false;
        int stuffdir = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        int length = 3;
        int width = 3;
        int height = 3;
        deltax = 1;
        stuffdir = 2;
        int x = cposx;
        int z = cposz;
        int y = cposy;
        if (world.isClientSide) {
            return;
        }
        for (i = - width; i <= width; ++i) {
            for (j = - length; j <= length; ++j) {
                for (k = 0; k <= height + 1; ++k) {
                    if (k == height + 1) {
                        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), ChaosPersists.CrystalPlanksBlock.defaultBlockState(), 2);
                        continue;
                    }
                    if (k == 0) {
                        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), ChaosPersists.CrystalStone.defaultBlockState(), 2);
                        continue;
                    }
                    if (i == width || j == length || i == - width || j == - length) {
                        if (k == height) {
                            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.GLASS.defaultBlockState(), 2);
                            continue;
                        }
                        if ((k == 1 || k == 2) && i == deltax * width && j == deltaz * length) {
                            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.AIR.defaultBlockState(), 2);
                            continue;
                        }
                        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), ChaosPersists.CrystalPlanksBlock.defaultBlockState(), 2);
                        continue;
                    }
                    this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i, y + k, z + j), Blocks.AIR.defaultBlockState(), 2);
                }
            }
        }
        i = 2;
        k = 1;
        j = length - 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), ChaosPersists.CrystalFurnaceBlock.defaultBlockState(), 2);
        this.setBlockMeta(world,x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax, stuffdir, 3);
        i = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), ChaosPersists.CrystalWorkbenchBlock.defaultBlockState(), 2);
        i = 0;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax, stuffdir, 3);
        ChestTileEntity chest = this.getChestTileEntity(world, x + i * deltax + j * deltaz, y + k, z + i * deltaz + j * deltax);
        if (chest != null) {
            if (world.random.nextInt(2) == 0) {
                chest.setItem(0, new ItemStack(Items.COMPASS));
            }
            if (world.random.nextInt(3) != 0) {
                chest.setItem(2, new ItemStack(ChaosPersists.MyPeacock, 8));
            }
            if (world.random.nextInt(3) != 0) {
                chest.setItem(3, new ItemStack(ChaosPersists.CrystalTorch, 32));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(4, new ItemStack(ChaosPersists.CrystalCoal, 16));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(5, new ItemStack(Items.RED_BED));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(6, new ItemStack(Items.RED_BED));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(7, new ItemStack(Items.OAK_DOOR));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(8, new ItemStack(ChaosPersists.MyCrystalPinkPickaxe));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(9, new ItemStack(ChaosPersists.MyCrystalPinkSword));
            }
            if (world.random.nextInt(2) == 0) {
                chest.setItem(10, new ItemStack(ChaosPersists.MyCrystalPinkAxe));
            }
            chest.setItem(11, new ItemStack(ChaosPersists.KrakenRepellent));
            if (world.random.nextInt(2) == 0) {
                chest.setItem(13, new ItemStack((Block)Blocks.CHEST));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost_pumpkin_skelly"));
        }
    }

    public void makeBouncyCastle(World world, int cposx, int cposy, int cposz) {
        boolean deltax = false;
        boolean deltaz = false;
        Block bid = Blocks.AIR;
        int meta = 0;
        boolean dirx = false;
        boolean dirz = false;
        int stuffdir = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.BouncyContentsList;
        int length = 4;
        int width = 4;
        int height = 5;
        deltax = true;
        stuffdir = 2;
        int x = cposx;
        int z = cposz;
        int y = cposy;
        if (world.isClientSide) {
            return;
        }
        for (int i = - width; i <= width; ++i) {
            for (int j = - length; j <= length; ++j) {
                for (int k = 0; k < height; ++k) {
                    bid = Blocks.AIR;
                    meta = 0;
                    if (k == height - 1 || k == 0) {
                        bid = ChaosPersists.MyLavafoamBlock;
                    }
                    if (i == - width || i == width) {
                        bid = ChaosPersists.MyLavafoamBlock;
                    }
                    if (j == - length || j == length) {
                        bid = ChaosPersists.MyLavafoamBlock;
                    }
                    if (!(i != - width && i != width || j != - length && j != length)) {
                        bid = ChaosPersists.legacyStainedHardenedClay(14);
                        meta = 0;
                    }
                    if ((k == 1 || k == 2) && i == 0 && j == - length) {
                        meta = 0;
                        bid = Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + k), (int)(cposz + j), (Block)bid, (int)meta, (int)2);
                }
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 3, cposz + length - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 3, cposz + length - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "silverfish"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz + length - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz + length - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 3, cposz + length - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 3, cposz + length - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 1, cposy + 3, cposz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width - 1, cposy + 3, cposz - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "silverfish"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width - 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 1, cposy + 3, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width - 1, cposy + 3, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - width + 1, cposy + 3, cposz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - width + 1, cposy + 3, cposz - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("minecraft", "silverfish"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - width + 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - width + 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - width + 1, cposy + 3, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - width + 1, cposy + 3, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 1, cposy + 3, cposz + length - 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        chest = this.getChestTileEntity(world, cposx + width - 1, cposy + 3, cposz + length - 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(5)));
        }
    }

    public void makeEnderCastle(World world, int cposx, int cposy, int cposz) {
        int j;
        int k;
        int m;
        int i;
        int width = 22;
        int height = 12;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        MobSpawnerTileEntity tileentitymobspawner = null;
        chestContents = this.EnderCastleContentsList;
        Block bid = Blocks.OBSIDIAN;
        for (i = -3; i <= width + 3; ++i) {
            for (k = -3; k <= width + 3; ++k) {
                for (j = 0; j <= 1; ++j) {
                    bid = Blocks.AIR;
                    if (j == 0) {
                        bid = Blocks.OBSIDIAN;
                    }
                    if (j == 1 && (i == -3 || i == width + 3 || k == width + 3 | k == -3)) {
                        bid = Blocks.IRON_BARS;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        for (i = 0; i <= width; ++i) {
            for (k = 0; k <= width; ++k) {
                for (j = 1; j <= height; ++j) {
                    bid = Blocks.AIR;
                    if (i == 0 || i == width || k == width | k == 0) {
                        bid = Blocks.BEDROCK;
                    }
                    if (j == height && bid == Blocks.BEDROCK && (i + k & 1) == 0) {
                        bid = Blocks.AIR;
                    }
                    if (j == height - 2 && bid == Blocks.BEDROCK && (i + k & 1) == 0) {
                        int which = world.random.nextInt(4);
                        if (which == 0) {
                            bid = ChaosPersists.MyEnderKnightSpawnBlock;
                        }
                        if (which == 1) {
                            bid = ChaosPersists.MyEnderReaperSpawnBlock;
                        }
                        if (which == 2) {
                            bid = ChaosPersists.MyEndermanSpawnBlock;
                        }
                        if (which == 3) {
                            bid = ChaosPersists.MyEnderDragonSpawnBlock;
                        }
                    }
                    if (j == 7 && bid == Blocks.BEDROCK && (i + k & 1) != 0) {
                        bid = ChaosPersists.MyEyeOfEnderBlock;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        for (i = -1; i <= width + 1; ++i) {
            for (k = -1; k <= width + 1; ++k) {
                for (j = 1; j <= height - 1; ++j) {
                    bid = Blocks.AIR;
                    if (j == 6 || j > 8) {
                        if (i == -1 || i == width + 1 || k == width + 1 | k == -1) {
                            bid = Blocks.BEDROCK;
                        }
                        if (j == 6 && bid != Blocks.AIR && world.random.nextInt(2) == 1) {
                            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j - 1), (int)(cposz + k), (Block)ChaosPersists.MyEnderPearlBlock, (int)0, (int)2);
                            if (world.random.nextInt(3) == 1) {
                                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j - 2), (int)(cposz + k), (Block)ChaosPersists.MyEnderPearlBlock, (int)0, (int)2);
                            }
                        }
                    }
                    if (j == 7) {
                        if (i == -1 || i == width + 1 || k == width + 1 | k == -1) {
                            bid = Blocks.BEDROCK;
                        }
                        if (bid == Blocks.BEDROCK && (i + k & 1) == 0) {
                            bid = Blocks.AIR;
                        }
                    }
                    if (bid == Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.makeAColumn(world, cposx - 2, cposy, cposz - 2, height + 1, 0);
        this.makeAColumn(world, cposx + width - 2, cposy, cposz - 2, height + 1, 1);
        this.makeAColumn(world, cposx - 2, cposy, cposz + width - 2, height + 1, 2);
        this.makeAColumn(world, cposx + width - 2, cposy, cposz + width - 2, height + 1, 3);
        j = 8;
        for (i = 1; i <= width - 1; ++i) {
            for (k = 1; k <= width - 1; ++k) {
                bid = Blocks.OBSIDIAN;
                if (i == width / 2 || k == width / 2 || i == k || i == width - k) {
                    bid = Blocks.BEDROCK;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        j = 9;
        for (i = -2; i <= 2; ++i) {
            for (k = -2; k <= 2; ++k) {
                bid = Blocks.LAVA;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i + width / 2), (int)(cposy + j), (int)(cposz + k + width / 2), (Block)bid, (int)0, (int)2);
            }
        }
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + m), (int)(cposy + j), (int)(cposz + width / 2 + 3), (Block)Blocks.BEDROCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + m), (int)(cposy + j), (int)(cposz + width / 2 - 3), (Block)Blocks.BEDROCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 3), (int)(cposy + j), (int)(cposz + width / 2 + m), (Block)Blocks.BEDROCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 3), (int)(cposy + j), (int)(cposz + width / 2 + m), (Block)Blocks.BEDROCK, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 2), (int)(cposy + j), (int)(cposz + width / 2 - 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 2), (int)(cposy + j), (int)(cposz + width / 2 + 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 2), (int)(cposy + j), (int)(cposz + width / 2 + 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 2), (int)(cposy + j), (int)(cposz + width / 2 - 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j), (int)(cposz + width / 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j + 1, cposz + width / 2), Blocks.ENDER_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 2), (int)(cposz + width / 2), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 3), (int)(cposz + width / 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 1), (int)(cposy + j + 3), (int)(cposz + width / 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 1), (int)(cposy + j + 3), (int)(cposz + width / 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 3), (int)(cposz + width / 2 - 1), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 3), (int)(cposz + width / 2 + 1), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 1), (int)(cposy + j + 4), (int)(cposz + width / 2), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 1), (int)(cposy + j + 4), (int)(cposz + width / 2), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 4), (int)(cposz + width / 2 - 1), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 4), (int)(cposz + width / 2 + 1), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 4), (int)(cposz + width / 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 5), (int)(cposz + width / 2), (Block)Blocks.BEDROCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + j + 6), (int)(cposz + width / 2), (Block)Blocks.DRAGON_EGG, (int)0, (int)2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 5, cposy + j, cposz + width / 2 + 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 5, cposy + j, cposz + width / 2 + 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 5, cposy + j + 1, cposz + width / 2 + 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 5, cposy + j + 1, cposz + width / 2 + 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 5, cposy + j, cposz + width / 2 + 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 5, cposy + j, cposz + width / 2 + 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 5, cposy + j + 1, cposz + width / 2 + 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 5, cposy + j + 1, cposz + width / 2 + 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 5, cposy + j, cposz + width / 2 - 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 5, cposy + j, cposz + width / 2 - 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 5, cposy + j + 1, cposz + width / 2 - 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 5, cposy + j + 1, cposz + width / 2 - 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 5, cposy + j, cposz + width / 2 - 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 5, cposy + j, cposz + width / 2 - 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 5, cposy + j + 1, cposz + width / 2 - 5), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 5, cposy + j + 1, cposz + width / 2 - 5);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
        }
        j = 4;
        for (i = 1; i <= width - 1; ++i) {
            for (k = 1; k <= width - 1; ++k) {
                bid = Blocks.AIR;
                if (i <= 5 || k <= 5 || i >= width - 5 || k >= width - 5) {
                    bid = Blocks.BEDROCK;
                }
                if (bid != Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
                if (i == 5 && k >= 5 && k <= width - 5) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 3), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                }
                if (i == width - 5 && k >= 5 && k <= width - 5) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 3), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                }
                if (k == 5 && i >= 5 && i <= width - 5) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 3), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                }
                if (k != width - 5 || i < 5 || i > width - 5) continue;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 3), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
            }
        }
        bid = Blocks.BEDROCK;
        j = 3;
        k = width / 2;
        i = width - 6;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k + m), (Block)bid, (int)0, (int)2);
        }
        j = 2;
        k = width / 2;
        i = width - 7;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k + m), (Block)bid, (int)0, (int)2);
        }
        j = 1;
        k = width / 2;
        i = width - 8;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k + m), (Block)bid, (int)0, (int)2);
        }
        j = 4;
        i = width - 5;
        for (m = -1; m <= 1; ++m) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k + m), (Block)Blocks.AIR, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k + m), (Block)Blocks.AIR, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 3), (int)(cposz + k + m), (Block)Blocks.AIR, (int)0, (int)2);
        }
        j = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + j, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_reaper"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j + 1, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + j + 1, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ender_knight"));
        }
        j = 5;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + j, cposz + width / 2 - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + j, cposz + width / 2 - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cavefisher"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + j, cposz + width / 2 + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + j, cposz + width / 2 + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cavefisher"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + j, cposz + width / 2), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + j, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + j, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 1, cposy + j, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cavefisher"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + j, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 1, cposy + j, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cavefisher"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH), 2);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + j, cposz + width - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 1, cposy + j, cposz + width - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cavefisher"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + j, cposz + width - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 1, cposy + j, cposz + width - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cavefisher"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + j, cposz + width - 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.WEST), 2);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + j, cposz + width - 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(5)));
        }
    }

    private void makeAColumn(World world, int cposx, int cposy, int cposz, int height, int dir) {
        Block bid;
        int k;
        int i;
        int j;
        int width = 4;
        int halfwidth = 2;
        int step = dir;
        for (i = -2; i <= width + 2; ++i) {
            for (k = -2; k <= width + 2; ++k) {
                j = height + 2;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.OBSIDIAN, (int)0, (int)2);
            }
        }
        for (i = -2; i <= width + 2; ++i) {
            for (k = -2; k <= width + 2; ++k) {
                bid = Blocks.AIR;
                if (i == -2 || i == width + 2 || k == width + 2 | k == -2) {
                    bid = Blocks.OBSIDIAN;
                }
                j = height + 3;
                if (bid != Blocks.AIR && (i + k & 1) == 0) {
                    bid = Blocks.AIR;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        for (i = 0; i <= width; ++i) {
            for (k = 0; k <= width; ++k) {
                for (j = 1; j <= height + 2; ++j) {
                    bid = Blocks.AIR;
                    if (i == 0 || i == width || k == width | k == 0) {
                        bid = Blocks.OBSIDIAN;
                    }
                    if (!(j % 3 != 0 && j % 3 != 1 || j == height + 2 || bid != Blocks.OBSIDIAN || i != halfwidth && k != halfwidth)) {
                        bid = Blocks.IRON_BARS;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        if (dir == 0) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - 1), (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)(cposz + width - 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - 1), (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)(cposz + width - 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
        }
        if (dir == 1) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)(cposz + width - 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + j), (int)(cposz + width), (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)(cposz + width - 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
            if (++step > 3) {
                step = 0;
            }
        }
        if (dir == 2) {
            for (j = 1; j <= 2; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - 1), (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)(cposz + 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width - 1), (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width), (int)(cposy + j), (int)(cposz + 1), (Block)Blocks.AIR, (int)0, (int)2);
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
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)(cposz + 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
            for (j = 9; j <= 10; ++j) {
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + j), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + j), (int)(cposz + 1), (Block)Blocks.AIR, (int)0, (int)2);
            }
            if (++step > 3) {
                step = 0;
            }
            if (++step > 3) {
                step = 0;
            }
        }
        bid = Blocks.NETHER_BRICKS;
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
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
        }
    }

    public void makeDamselInDistress(World world, int cposx, int cposy, int cposz) {
        int i;
        int j;
        int k;
        Block bid = Blocks.AIR;
        int meta = 0;
        int stuffdir = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.DamselContentsList;
        int length = 4;
        int width = 4;
        int height = 5;
        stuffdir = 2;
        if (world.isClientSide) {
            return;
        }
        for (i = - width; i <= width; ++i) {
            for (j = - length; j <= length; ++j) {
                for (k = 0; k < height; ++k) {
                    bid = Blocks.AIR;
                    meta = 0;
                    if (k == 0) {
                        bid = Blocks.COBBLESTONE;
                    }
                    if (i == - width || i == width) {
                        bid = Blocks.COBBLESTONE;
                    }
                    if (j == - length || j == length) {
                        bid = Blocks.COBBLESTONE;
                    }
                    if (bid == Blocks.COBBLESTONE && world.random.nextInt(8) == 1) {
                        bid = Blocks.MOSSY_COBBLESTONE;
                    }
                    if (!(k != 1 && k != 2 && k != 3 || i != 0 && i != -1 && i != 1 || j != - length)) {
                        meta = 0;
                        bid = Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + k), (int)(cposz + j), (Block)bid, (int)meta, (int)2);
                }
            }
        }
        meta = 0;
        for (i = - width + 1; i <= width - 1; ++i) {
            for (j = - length; j <= length - 1; ++j) {
                k = height;
                bid = Blocks.COBBLESTONE;
                if (world.random.nextInt(8) == 1) {
                    bid = Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + k), (int)(cposz + j), (Block)bid, (int)meta, (int)2);
            }
        }
        for (i = - width + 2; i <= width - 2; ++i) {
            for (j = - length; j <= length - 2; ++j) {
                k = height + 1;
                bid = Blocks.COBBLESTONE;
                if (world.random.nextInt(8) == 1) {
                    bid = Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + k), (int)(cposz + j), (Block)bid, (int)meta, (int)2);
            }
        }
        k = height;
        j = - length;
        for (int m = width; m >= 0; --m) {
            for (i = m; i >= 0; --i) {
                bid = Blocks.COBBLESTONE;
                if (world.random.nextInt(8) == 1) {
                    bid = Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + k), (int)(cposz + j), (Block)bid, (int)meta, (int)2);
                bid = Blocks.COBBLESTONE;
                if (world.random.nextInt(8) == 1) {
                    bid = Blocks.MOSSY_COBBLESTONE;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx - i), (int)(cposy + k), (int)(cposz + j), (Block)bid, (int)meta, (int)2);
            }
            ++k;
        }
        for (i = - width + 1; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = length - 3;
                ChaosPersists.setBlockFast((World)world, (int)(cposx - i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.IRON_BARS, (int)0, (int)2);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - width + 1, cposy + 1, cposz - length + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - width + 1, cposy + 1, cposz - length + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 1, cposy + 1, cposz - length + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width - 1, cposy + 1, cposz - length + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 1, cposy + 1, cposz + length - 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        chest = this.getChestTileEntity(world, cposx + width - 1, cposy + 1, cposz + length - 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
        }
        Entity var8 = null;
        var8 = ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", "girlfriend")).create(world);
        if (var8 != null) {
            var8.moveTo((double)(cposx - width + 2), (double)(cposy + 1), (double)(cposz + length - 1), world.random.nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(var8);
        }
    }

    public void makeIncaPyramid(World world, int cposx, int cposy, int cposz) {
        int i;
        int m;
        int k;
        int j;
        int p;
        Block bid = Blocks.AIR;
        int meta = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        Object chest = null;
        Object chestContents = null;
        int width = 21;
        int depth = 11;
        int height = 9;
        int basewidth = 41;
        int basedepth = 31;
        int baseheight = 10;
        if (world.isClientSide) {
            return;
        }
        for (j = 0; j < baseheight; ++j) {
            for (i = 0; i < basewidth - j * 2; ++i) {
                for (k = 0; k < basedepth - j * 2; ++k) {
                    meta = 0;
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == basewidth - j * 2 - 1 || k == basedepth - j * 2 - 1) {
                        bid = Blocks.STONE;
                        if (world.random.nextInt(2) == 0) {
                            bid = Blocks.COBBLESTONE;
                        }
                        if (world.random.nextInt(4) == 0) {
                            bid = Blocks.MOSSY_COBBLESTONE;
                        }
                    }
                    if (j == 0) {
                        bid = Blocks.STONE_BRICKS;
                    }
                    if (k == 1 && j % 3 == 2 && i != 0 && i != basewidth - j * 2 - 1) {
                        bid = Blocks.TORCH;
                        meta = 3;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i + j), (int)(cposy + j), (int)(cposz + k + j), (Block)bid, (int)meta, (int)2);
                    if (k != basedepth - j * 2 - 1 || j % 3 != 2 || i == 0 || i == basewidth - j * 2 - 1) continue;
                    meta = 4;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i + j), (int)(cposy + j), (int)(cposz + k + j - 1), (Block)Blocks.TORCH, (int)meta, (int)2);
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
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == Blocks.AIR) {
                        ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_BRICKS, (int)meta, (int)2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.TORCH, (int)meta, (int)2);
                        }
                    }
                } else if (m % 2 == 1 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_SLAB, (int)meta, (int)2);
                }
                while (j >= 0 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.STONE, (int)meta, (int)2);
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
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == Blocks.AIR) {
                        ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_BRICKS, (int)meta, (int)2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.TORCH, (int)meta, (int)2);
                        }
                    }
                } else if (m % 2 == 1 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_SLAB, (int)meta, (int)2);
                }
                while (j >= 0 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.STONE, (int)meta, (int)2);
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
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == Blocks.AIR) {
                        ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_BRICKS, (int)meta, (int)2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.TORCH, (int)meta, (int)2);
                        }
                    }
                } else if (m % 2 == 1 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_SLAB, (int)meta, (int)2);
                }
                while (j >= 0 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.STONE, (int)meta, (int)2);
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
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock();
                    if (bid == Blocks.AIR) {
                        ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_BRICKS, (int)meta, (int)2);
                        if (m == 0 || m == baseheight * 2 - 2) {
                            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 2), (int)(cposz + k), (Block)Blocks.TORCH, (int)meta, (int)2);
                        }
                    }
                } else if (m % 2 == 1 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)Blocks.STONE_SLAB, (int)meta, (int)2);
                }
                while (j >= 0 && (bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k)).getBlock()) == Blocks.AIR) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.STONE, (int)meta, (int)2);
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
                    bid = Blocks.AIR;
                    meta = 0;
                    if (i == 0 || k == 0 || i == width - 1 || k == depth - 1) {
                        bid = Blocks.STONE;
                        if (world.random.nextInt(2) == 0) {
                            bid = Blocks.COBBLESTONE;
                        }
                        if (world.random.nextInt(4) == 0) {
                            bid = Blocks.MOSSY_COBBLESTONE;
                        }
                    }
                    if (j == 0 || j == height - 1) {
                        bid = Blocks.STONE_BRICKS;
                    }
                    if (j == 1 || j == 2 || j == 3) {
                        if ((k == 0 || k == depth - 1) && i >= width / 2 - 1 && i <= width / 2 + 1) {
                            bid = j == 3 ? Blocks.OAK_FENCE : Blocks.AIR;
                        }
                        if ((i == 0 || i == width - 1) && k >= depth / 2 - 1 && k <= depth / 2 + 1) {
                            bid = j == 3 ? Blocks.OAK_FENCE : Blocks.AIR;
                        }
                    }
                    if ((j == height - 3 || j == height - 2) && (i + k) % 2 == 1) {
                        if (j == height - 3) {
                            if (bid != Blocks.AIR) {
                                bid = Blocks.REDSTONE_LAMP;
                            }
                        } else {
                            bid = Blocks.AIR;
                        }
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
                }
            }
        }
        bid = Blocks.STONE_SLAB;
        meta = 0;
        j = height;
        for (i = -1; i <= width; ++i) {
            for (k = -1; k <= depth; ++k) {
                if (i != -1 && k != -1 && i != width && k != depth || (i + k & 1) != 1) continue;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
            }
        }
        this.makepoolalter(world, cposx + 1, cposy, cposz + 1);
        this.makepoolalter(world, cposx + width - 2, cposy, cposz + depth - 2);
        this.makepoolalter(world, cposx + 1, cposy, cposz + depth - 2);
        this.makepoolalter(world, cposx + width - 2, cposy, cposz + 1);
        this.makepoolalter(world, cposx + width / 2, cposy, cposz + depth / 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + 2, cposz + depth / 2 - 1), ChaosPersists.CreeperRepellent.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + 2, cposz + depth / 2 + 1), ChaosPersists.CreeperRepellent.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + 2, cposz + depth / 2 + 1), ChaosPersists.CreeperRepellent.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + 2, cposz + depth / 2 - 1), ChaosPersists.CreeperRepellent.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 2, cposy + 1, cposz + depth / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 2, cposy + 1, cposz + depth / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "molenoid"));
        }
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 2), (int)(cposy + 1), (int)(cposz + depth / 2), (Block)Blocks.OAK_TRAPDOOR, (int)3, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 2), (int)cposy, (int)(cposz + depth / 2), (Block)Blocks.AIR, (int)0, (int)2);
        i = cposx + width / 2 + 2;
        k = cposz + depth / 2;
        for (j = 1; j < baseheight; ++j) {
            ChaosPersists.setBlockFast((World)world, (int)i, (int)(cposy - j), (int)(k + 1), (Block)Blocks.COBBLESTONE, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)i, (int)(cposy - j), (int)k, (Block)Blocks.LADDER, (int)2, (int)2);
        }
        this.makeincagraves(world, cposx - baseheight, cposy - baseheight, cposz - baseheight, basewidth, basedepth);
    }

    private void makepoolalter(World world, int cposx, int cposy, int cposz) {
        for (int i = -1; i <= 1; ++i) {
            for (int k = -1; k <= 1; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + 1), (int)(cposz + k), (Block)Blocks.COBBLESTONE, (int)0, (int)2);
            }
        }
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)cposz, (Block)Blocks.WATER, (int)0, (int)2);
    }

    private void makeincagraves(World world, int cposx, int cposy, int cposz, int width, int depth) {
        int i;
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(world, cposx + i, cposy, cposz + 5, 1);
        }
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(world, cposx + i, cposy, cposz + 10, 1);
        }
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(world, cposx + i, cposy, cposz + 20, 3);
        }
        for (i = 5; i < width - 5; i += 6) {
            this.makeincagrave(world, cposx + i, cposy, cposz + 25, 3);
        }
    }

    private void makeincagrave(World world, int cposx, int cposy, int cposz, int dir) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.IncaPyramidContentsList;
        if (dir == 1) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)cposy, (int)cposz, (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)(cposy + 1), (int)cposz, (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)cposy, (int)(cposz + 1), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)(cposy + 1), (int)(cposz + 1), (Block)Blocks.DANDELION, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)cposy, (int)(cposz + 2), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)cposy, (int)cposz, (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)cposz, (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)cposy, (int)(cposz + 1), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz + 1), (Block)Blocks.DANDELION, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)cposy, (int)(cposz + 2), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)cposz, (Block)Blocks.STONE, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)(cposz + 1), (Block)Blocks.STONE_SLAB, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.STONE_SLAB, (int)0, (int)2);
            if (world.random.nextInt(3) == 1) {
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz);
                if (tileentitymobspawner != null) {
                    SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost"));
                }
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz - 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
            chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz - 1);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
            }
        }
        if (dir == 3) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)cposy, (int)cposz, (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)(cposy + 1), (int)cposz, (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)cposy, (int)(cposz - 1), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)(cposy + 1), (int)(cposz - 1), (Block)Blocks.DANDELION, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)cposy, (int)(cposz - 2), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx - 1), (int)(cposy + 1), (int)(cposz - 2), (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)cposy, (int)cposz, (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)cposz, (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)cposy, (int)(cposz - 1), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz - 1), (Block)Blocks.DANDELION, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)cposy, (int)(cposz - 2), (Block)Blocks.GRASS_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz - 2), (Block)Blocks.POPPY, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)cposz, (Block)Blocks.STONE, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)(cposz - 1), (Block)Blocks.STONE_SLAB, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)(cposz - 2), (Block)Blocks.STONE_SLAB, (int)0, (int)2);
            if (world.random.nextInt(3) == 1) {
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz);
                if (tileentitymobspawner != null) {
                    SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost"));
                }
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
            chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz + 1);
            if (chest != null) {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
            }
        }
    }

    public void makeRobotLab(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        boolean meta = false;
        int width = 10;
        int length = 20;
        int height = 5;
        if (world.isClientSide) {
            return;
        }
        for (int j = 0; j <= height; ++j) {
            for (int i = 0; i < width; ++i) {
                for (int k = 0; k < length; ++k) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0) {
                        bid = Blocks.QUARTZ_BLOCK;
                        if (i == width / 2 || i == width / 2 - 1) {
                            bid = Blocks.IRON_BLOCK;
                        }
                    }
                    if (j == height) {
                        bid = Blocks.QUARTZ_BLOCK;
                        if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                            bid = Blocks.AIR;
                        }
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + 1), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + 2), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 1), (int)(cposy + 1), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 1), (int)(cposy + 2), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        this.placeDoor(world, new BlockPos(cposx + width / 2, cposy + 1, cposz), Direction.SOUTH, (DoorBlock)Blocks.IRON_DOOR);
        this.placeDoor(world, new BlockPos(cposx + width / 2 - 1, cposy + 1, cposz), Direction.SOUTH, (DoorBlock)Blocks.IRON_DOOR);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 2), (int)(cposy + 2), (int)(cposz - 1), (Block)Blocks.STONE_BUTTON, (int)4, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 1), (int)(cposy + 2), (int)(cposz - 1), (Block)Blocks.STONE_BUTTON, (int)4, (int)2);
        this.makerobomain(world, cposx, cposy, cposz + length - 1);
        this.makerobopillar(world, cposx, cposy, cposz + length / 3, 0);
        this.makerobopillar(world, cposx, cposy, cposz + length * 2 / 3, 0);
        this.makerobopillar(world, cposx, cposy, cposz + (length - 1), 0);
        this.makerobopillar(world, cposx + width - 1, cposy, cposz + length / 3, 1);
        this.makerobopillar(world, cposx + width - 1, cposy, cposz + length * 2 / 3, 1);
        this.makerobopillar(world, cposx + width - 1, cposy, cposz + (length - 1), 1);
    }

    private void makerobopillar(World world, int cposx, int cposy, int cposz, int dir) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (int j = 0; j < 5; ++j) {
            for (int i = -1; i < 2; ++i) {
                for (int k = -1; k < 2; ++k) {
                    Block bid = Blocks.QUARTZ_BLOCK;
                    if (j == 2 || j == 3) {
                        if (k == 0 && (i == -1 || i == 1)) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 0 && (k == -1 || k == 1)) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        if (dir == 0) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 1, cposz);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "robo-sniper"));
            }
        }
        if (dir == 1) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 1, cposz);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "robo-sniper"));
            }
        }
    }

    public void makerobomain(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        int width = 30;
        int length = 30;
        int height = 9;
        cposx -= 10;
        for (int j = 0; j <= height; ++j) {
            for (int i = 0; i < width; ++i) {
                for (int k = 0; k < length; ++k) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0) {
                        bid = Blocks.QUARTZ_BLOCK;
                        if (i == width / 2 || i == width / 2 - 1) {
                            bid = Blocks.IRON_BLOCK;
                        }
                    }
                    if (j == height) {
                        bid = Blocks.QUARTZ_BLOCK;
                        if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                            bid = Blocks.AIR;
                        }
                    }
                    if ((j == 1 || j == 2 || j == 3) && k == 0 && i >= width / 3 && i < width * 2 / 3) {
                        bid = Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.makeroboaltar(world, cposx + width / 2 - 4, cposy, cposz + 6);
        this.makeroborailway(world, cposx + 3, cposy, cposz + 10);
        this.makeroboassemblyline(world, cposx + width - 4, cposy, cposz + 4);
        this.makerobotreasureroom(world, cposx + 9, cposy, cposz + 18);
        this.makerobotower(world, cposx + width / 2 - 6, cposy + height, cposz + length / 2 - 6);
    }

    public void makerobotower(World world, int cposx, int cposy, int cposz) {
        int i;
        int j;
        int k;
        Block bid = Blocks.AIR;
        for (j = 0; j < 2; ++j) {
            for (i = 0; i < 12; ++i) {
                for (k = 0; k < 12; ++k) {
                    bid = Blocks.AIR;
                    if (j == 1) {
                        if (i == 0 || k == 0 || i == 11 || k == 11) {
                            bid = Blocks.IRON_BARS;
                        }
                        if (i == 0 && (k == 0 || k == 11)) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 11 && (k == 0 || k == 11)) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                    }
                    if (j == 0) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.makerobopillar(world, cposx + 4, cposy + 1, cposz + 4, 1);
        this.makerobopillar(world, cposx + 7, cposy + 1, cposz + 7, 0);
        this.makerobopillar(world, cposx + 4, cposy + 1, cposz + 7, 1);
        this.makerobopillar(world, cposx + 7, cposy + 1, cposz + 4, 0);
        for (j = 5; j < 35; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 3; ++k) {
                    bid = Blocks.AIR;
                    if (j < 15) {
                        bid = Blocks.QUARTZ_BLOCK;
                    } else if (j < 25) {
                        bid = Blocks.QUARTZ_BLOCK;
                        if (k == 2) {
                            bid = Blocks.IRON_BARS;
                        }
                    } else {
                        bid = Blocks.QUARTZ_BLOCK;
                        if (k == 1) {
                            bid = Blocks.IRON_BARS;
                        }
                        if (k == 2) {
                            bid = Blocks.AIR;
                        }
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 5), (int)(cposy + j), (int)(cposz + k + 5), (Block)bid, (int)0, (int)2);
                }
            }
        }
    }

    public void makeroboaltar(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        Block bid = Blocks.AIR;
        MobSpawnerTileEntity tileentitymobspawner = null;
        bid = Blocks.IRON_BLOCK;
        for (i = 0; i < 8; ++i) {
            for (k = 0; k < 8; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)cposy, (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        bid = Blocks.QUARTZ_BLOCK;
        for (i = 0; i < 6; ++i) {
            for (k = 0; k < 6; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 1), (int)(cposy + 1), (int)(cposz + k + 1), (Block)bid, (int)0, (int)2);
            }
        }
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.REDSTONE_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 2), (int)(cposz + 2), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 5), (int)(cposy + 1), (int)(cposz + 5), (Block)Blocks.REDSTONE_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 5), (int)(cposy + 2), (int)(cposz + 5), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 5), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.REDSTONE_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 5), (int)(cposy + 2), (int)(cposz + 2), (Block)Blocks.TORCH, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 1), (int)(cposz + 5), (Block)Blocks.REDSTONE_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 2), (int)(cposz + 5), (Block)Blocks.TORCH, (int)0, (int)2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 3, cposy + 2, cposz + 3), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 3, cposy + 2, cposz + 3);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "robo-pounder"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 4, cposy + 2, cposz + 4), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 4, cposy + 2, cposz + 4);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "robo-pounder"));
        }
    }

    public void makeroborailway(World world, int cposx, int cposy, int cposz) {
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 0), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 0), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 1), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 1), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.POWERED_RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.LEVER, (int)5, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.LEVER, (int)5, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 2), (Block)Blocks.POWERED_RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 3), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 3), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 4), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 4), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 5), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 5), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 6), (Block)Blocks.POWERED_RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz + 6), (Block)Blocks.LEVER, (int)5, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 1), (int)(cposz + 6), (Block)Blocks.LEVER, (int)5, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 6), (Block)Blocks.POWERED_RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 7), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 7), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 8), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 8), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 9), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 9), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 10), (Block)Blocks.POWERED_RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz + 10), (Block)Blocks.LEVER, (int)5, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 2), (int)(cposy + 1), (int)(cposz + 10), (Block)Blocks.LEVER, (int)5, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 10), (Block)Blocks.POWERED_RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 11), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 11), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 0), (int)(cposy + 1), (int)(cposz + 12), (Block)Blocks.RAIL, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 3), (int)(cposy + 1), (int)(cposz + 12), (Block)Blocks.RAIL, (int)0, (int)2);
    }

    public void makeroboassemblyline(World world, int cposx, int cposy, int cposz) {
        for (int k = 0; k < 24; ++k) {
            if (k % 3 == 1) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx - 2), (int)(cposy + 1), (int)(cposz + k), (Block)Blocks.QUARTZ_STAIRS, (int)1, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 2), (int)(cposz + k), (Block)Blocks.STICKY_PISTON, (int)3, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 3), (int)(cposz + k), (Block)Blocks.WHITE_CARPET, (int)0, (int)2);
            }
            if (k % 3 == 0) {
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 2), (int)(cposz + k), (Block)Blocks.LEVER, (int)13, (int)2);
            }
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + 1), (int)(cposz + k), (Block)Blocks.QUARTZ_BLOCK, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + 1), (int)(cposy + 1), (int)(cposz + k), (Block)Blocks.QUARTZ_BLOCK, (int)0, (int)2);
        }
    }

    public void makerobotreasureroom(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        MobSpawnerTileEntity tileentitymobspawner = null;
        chestContents = this.RobotContentsList;
        for (int j = 1; j < 7; ++j) {
            for (int i = 0; i < 12; ++i) {
                for (int k = 0; k < 8; ++k) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == 11 || k == 7) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 2 && i == 11) {
                        bid = Blocks.IRON_BARS;
                    }
                    if (j == 3 && bid != Blocks.AIR) {
                        bid = Blocks.IRON_BARS;
                    }
                    if (!(j != 1 && j != 2 && j != 3 || k != 0 || i != 1 && i != 2)) {
                        bid = Blocks.AIR;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 10, cposy + 1, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 10, cposy + 1, cposz + 1);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "robo-warrior"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 8, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        chest = this.getChestTileEntity(world, cposx + 8, cposy + 1, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 6, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.NORTH), 2);
        chest = this.getChestTileEntity(world, cposx + 6, cposy + 1, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
        }
    }

    public void makeKingAltar(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        int j;
        Block bid = Blocks.AIR;
        int width = 51;
        int length = 51;
        int height = 48;
        if (world.isClientSide) {
            return;
        }
        for (j = 0; j <= height + 10; ++j) {
            for (i = -5; i < width + 5; ++i) {
                for (k = -5; k < length + 5; ++k) {
                    bid = Blocks.AIR;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        j = 0;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                bid = Blocks.GRASS_BLOCK;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                for (int v = 1; v < 10; ++v) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j - v, cposz + k)).getBlock();
                    if (bid != Blocks.AIR && bid != Blocks.GRASS_BLOCK && bid != Blocks.WATER) continue;
                    bid = Blocks.DIRT;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j - v), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.makekingcolumn(world, cposx + 1, cposy + 1, cposz + 1);
        this.makekingcolumn(world, cposx + width - 8, cposy + 1, cposz + length - 8);
        this.makekingcolumn(world, cposx + 1, cposy + 1, cposz + length - 8);
        this.makekingcolumn(world, cposx + width - 8, cposy + 1, cposz + 1);
        j = height - 1;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        j = height;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = -1; i <= width; ++i) {
            for (k = -1; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        this.makekingbackground(world, cposx + 4, cposy + 10, cposz + 9);
        this.makekingcenteraltar(world, cposx + width / 2, cposy, cposz + length / 2);
    }

    private void makekingcolumn(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block bid = Blocks.AIR;
        int meta = 0;
        int width = 5;
        int length = 5;
        int height = 44;
        if (world.isClientSide) {
            return;
        }
        int j = 0;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = 0; i < width + 2; ++i) {
            for (k = 0; k < length + 2; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + height + 1), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
            }
        }
        ++cposx;
        ++cposz;
        ++cposy;
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j % 4 == 0 && bid != Blocks.AIR && (i == 2 || k == 2)) {
                        bid = Blocks.GOLD_BLOCK;
                    }
                    if (j % 4 == 1 && bid != Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = Blocks.GOLD_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = Blocks.GOLD_BLOCK;
                        }
                    }
                    if (j % 4 == 2 && bid != Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = Blocks.GOLD_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = Blocks.GOLD_BLOCK;
                        }
                        if (i == 2 || k == 2) {
                            bid = Blocks.EMERALD_BLOCK;
                        }
                    }
                    if (j % 4 == 3 && bid != Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = Blocks.GOLD_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = Blocks.GOLD_BLOCK;
                        }
                    }
                    meta = 0;
                    if (bid == Blocks.QUARTZ_BLOCK) {
                        meta = 2;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
                }
            }
        }
    }

    private void makekingbackground(World world, int cposx, int cposy, int cposz) {
        int i;
        Block bid = Blocks.AIR;
        boolean meta = false;
        int curz = 0;
        int cury = 0;
        int height = 33;
        int width = 33;
        bid = Blocks.STONE;
        for (int m = 0; m < this.king.length; ++m) {
            int v = this.king[m];
            if (v < 0) {
                bid = Blocks.STONE;
                while (curz < width) {
                    ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + cury), (int)(cposz + curz), (Block)bid, (int)0, (int)2);
                    ++curz;
                }
                ++cury;
                curz = 0;
                continue;
            }
            for (int n = 0; n < v; ++n) {
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + cury), (int)(cposz + curz), (Block)bid, (int)0, (int)2);
                ++curz;
            }
            bid = bid == Blocks.STONE ? Blocks.QUARTZ_BLOCK : Blocks.STONE;
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 1), (int)(cposz + i), (Block)Blocks.GOLD_BLOCK, (int)0, (int)2);
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height), (int)(cposz + i), (Block)Blocks.GOLD_BLOCK, (int)0, (int)2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + i), (int)(cposz - 1), (Block)Blocks.GOLD_BLOCK, (int)0, (int)2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + i), (int)(cposz + width), (Block)Blocks.GOLD_BLOCK, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 2), (int)(cposz - 2), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 1), (int)(cposz + width + 1), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 2), (int)(cposz + width + 1), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 1), (int)(cposz - 2), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 1), (int)(cposz - 2), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 2), (int)(cposz + width + 1), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 1), (int)(cposz + width + 1), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 2), (int)(cposz - 2), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
    }

    private void makekingcenteraltar(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block bid = Blocks.AIR;
        boolean meta = false;
        int width = 10;
        int length = 10;
        int j = 0;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 6;
        length = 20;
        j = 0;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 20;
        length = 6;
        j = 0;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 8;
        length = 8;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.QUARTZ_BLOCK;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 4;
        length = 18;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.QUARTZ_BLOCK;
                if (i == width && (k == - length || k == length)) {
                    bid = Blocks.LAPIS_BLOCK;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = Blocks.LAPIS_BLOCK;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 18;
        length = 4;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.QUARTZ_BLOCK;
                if (i == width && (k == - length || k == length)) {
                    bid = Blocks.LAPIS_BLOCK;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = Blocks.LAPIS_BLOCK;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 7;
        length = 7;
        j = 2;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
            }
        }
        width = 3;
        length = 17;
        j = 2;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 17;
        length = 3;
        j = 2;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 6;
        length = 6;
        j = 3;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 2;
        length = 16;
        j = 3;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 16;
        length = 2;
        j = 3;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 2;
        length = 2;
        j = 4;
        bid = Blocks.QUARTZ_BLOCK;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx, cposy + j, cposz, 2, 3);
        ChestTileEntity chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            chest.setItem(13, new ItemStack(ChaosPersists.TheKingEgg));
        }
    }

    public void makeLeonNest(World world, int cposx, int cposy, int cposz) {
        int j;
        int k;
        int i;
        Block bid = Blocks.AIR;
        int rad = 10;
        int dist = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 0; j <= rad; ++j) {
            for (i = - rad; i <= rad; ++i) {
                for (k = - rad; k <= rad; ++k) {
                    bid = Blocks.AIR;
                    dist = j * j + i * i + k * k;
                    if ((dist = (int)Math.sqrt(dist)) > rad) continue;
                    if (dist >= rad - 2) {
                        int which = world.random.nextInt(6);
                        if (which == 0) {
                            bid = Blocks.OAK_LEAVES;
                        }
                        if (which == 1) {
                            bid = Blocks.OAK_LOG;
                        }
                        if (which == 2) {
                            bid = Blocks.OAK_PLANKS;
                        }
                        if (which == 3) {
                            bid = Blocks.DIRT;
                        }
                        if (which == 4) {
                            bid = Blocks.COBBLESTONE;
                        }
                        if (which == 5) {
                            bid = Blocks.MOSSY_COBBLESTONE;
                        }
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy - j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        for (j = 1; j <= 5; ++j) {
            for (i = - rad; i <= rad; ++i) {
                for (k = - rad; k <= rad; ++k) {
                    bid = Blocks.AIR;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy - (rad - 4), cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy - (rad - 4), cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "leonopteryx"));
        }
    }

    public void makeCephadromeAltar(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block bid = Blocks.AIR;
        boolean meta = false;
        int width = 4;
        int length = 4;
        int j = 0;
        bid = Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 3;
        length = 3;
        j = 1;
        bid = Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.COBBLESTONE;
                if (k == 0 || i == 0) {
                    bid = Blocks.STONE_BRICKS;
                }
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = Blocks.STONE_BRICKS;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 3;
        length = 3;
        j = 2;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.AIR;
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = Blocks.STONE_BRICKS;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 3;
        length = 3;
        j = 3;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.AIR;
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = Blocks.END_STONE;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 3;
        length = 3;
        j = 4;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.AIR;
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = ChaosPersists.ExtremeTorch;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 2;
        length = 2;
        j = 2;
        bid = Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.COBBLESTONE;
                if (k == 0 || i == 0) {
                    bid = Blocks.STONE_BRICKS;
                }
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = Blocks.STONE_BRICKS;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 1;
        length = 1;
        j = 3;
        bid = Blocks.COBBLESTONE;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.COBBLESTONE;
                if (k == 0 && i == 0) {
                    bid = ChaosPersists.MyEyeOfEnderBlock;
                }
                if (!(k != - length && k != length || i != - width && i != width)) {
                    bid = Blocks.END_STONE;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
    }

    public void makeCrystalBattleTower(World world, int cposx, int cposy, int cposz) {
        int j;
        float curx;
        float curdeg;
        float currad;
        float curz;
        Block blk;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        float radius = 10.0f;
        for (j = 0; j <= 20; ++j) {
            blk = ChaosPersists.CrystalStone;
            if (j % 5 == 0) {
                for (currad = 0.0f; currad < radius; currad += 0.33f) {
                    for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                        curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                        curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                        this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + j, (int)((float)cposz + curz + 0.5f), blk);
                    }
                }
                continue;
            }
            currad = 10.0f;
            for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                blk = ChaosPersists.CrystalStone;
                if (j % 5 >= 1 && j % 5 <= 3 && (curdeg < 10.0f || curdeg > 350.0f)) {
                    blk = Blocks.AIR;
                }
                this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + j, (int)((float)cposz + curz + 0.5f), blk);
            }
        }
        radius = 10.0f;
        for (j = 21; j <= 22; ++j) {
            blk = ChaosPersists.CrystalCrystal;
            currad = 10.0f;
            for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
                curx = (float)((double)currad * Math.cos(Math.toRadians(curdeg)));
                curz = (float)((double)currad * Math.sin(Math.toRadians(curdeg)));
                this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), cposy + j, (int)((float)cposz + curz + 0.5f), blk);
            }
        }
        j = 1;
        chestContents = this.CrystalBattleTowerRatContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rat"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(5)));
        }
        j = 6;
        chestContents = this.CrystalBattleTowerDungeonBeastContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(5)));
        }
        j = 11;
        chestContents = this.CrystalBattleTowerUrchinContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "crystal_urchin"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "crystal_urchin"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(5)));
        }
        j = 16;
        chestContents = this.CrystalBattleTowerRotatorContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(5)));
        }
        j = 21;
        chestContents = this.CrystalBattleTowerVortexContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "vortex"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "vortex"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(6)));
        }
    }

    public void makeGirlfriendIsland(World world, int cposx, int cposy, int cposz) {
        int j;
        int i;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.DamselContentsList;
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
                this.FastSetBlock(world, cposx + i, cposy, cposz + j, (Block)Blocks.SAND);
                this.FastSetBlock(world, cposx + i, cposy - 1, cposz + j, Blocks.STONE);
            }
        }
        for (i = -2; i <= 2; ++i) {
            for (j = -2; j <= 2; ++j) {
                this.FastSetBlock(world, cposx + i, cposy + 3, cposz + j, (Block)Blocks.OAK_LEAVES);
            }
        }
        this.FastSetBlock(world, cposx, cposy + 4, cposz, (Block)Blocks.OAK_LEAVES);
        this.FastSetBlock(world, cposx, cposy + 3, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx, cposy + 2, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx, cposy + 1, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx + 1, cposy + 3, cposz + 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx - 1, cposy + 3, cposz - 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx + 1, cposy + 3, cposz - 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx - 1, cposy + 3, cposz + 1, Blocks.OAK_LOG);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "girlfriend"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "boyfriend"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz + 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "gold_fish"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "gold_fish"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz - 1), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz - 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
    }

    public void makeGreenhouseDungeon(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        int j;
        int height = 7;
        int width = 15;
        int length = 23;
        int t = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.GreenhouseContentsList;
        for (i = 0; i < length; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = 0; j < height; ++j) {
                    Block blk = Blocks.AIR;
                    if (i == 0 || k == 0 || i == length - 1 || k == width - 1) {
                        blk = Blocks.GLASS;
                    }
                    if (j == height - 1) {
                        blk = Blocks.IRON_BLOCK;
                        if (i % 4 == 3 && k % 4 == 3) {
                            blk = Blocks.GLOWSTONE;
                        }
                        if (k % 4 == 1) {
                            blk = Blocks.GLASS;
                        }
                    }
                    if (j == 0) {
                        blk = Blocks.GRASS_BLOCK;
                        if (i != 0 && k != 0 && i != length - 1 && k != width - 1 && i % 3 == 2) {
                            blk = Blocks.WATER;
                        }
                    }
                    if (j == 1 && i != 0 && k != 0 && i != length - 1 && k != width - 1 && i % 3 != 2 && world.random.nextInt(3) != 1) {
                        blk = Blocks.FARMLAND;
                        this.FastSetBlock(world, cposx + i, cposy + j - 1, cposz + k, blk);
                        t = world.random.nextInt(20);
                        blk = Blocks.AIR;
                        if (t == 0) {
                            blk = Blocks.DANDELION;
                        }
                        if (t == 1) {
                            blk = Blocks.POPPY;
                        }
                        if (t == 2) {
                            blk = Blocks.BROWN_MUSHROOM;
                        }
                        if (t == 3) {
                            blk = Blocks.RED_MUSHROOM;
                        }
                        if (t == 4) {
                            blk = Blocks.WHEAT;
                        }
                        if (t == 5) {
                            blk = Blocks.CARROTS;
                        }
                        if (t == 6) {
                            blk = Blocks.POTATOES;
                        }
                        if (t == 7) {
                            blk = Blocks.SUGAR_CANE;
                        }
                        if (t == 9) {
                            blk = ChaosPersists.MyCornPlant1;
                        }
                        if (t == 10) {
                            blk = ChaosPersists.MyTomatoPlant1;
                        }
                        if (t == 11) {
                            blk = ChaosPersists.MyStrawberryPlant;
                        }
                        if (t == 12) {
                            blk = ChaosPersists.MyButterflyPlant;
                        }
                        if (t == 13) {
                            blk = ChaosPersists.MyMothPlant;
                        }
                        if (t == 14) {
                            blk = ChaosPersists.MyRadishPlant;
                        }
                        if (t == 15) {
                            blk = ChaosPersists.MyLettucePlant1;
                        }
                        if (t == 16) {
                            blk = ChaosPersists.MyFlowerPinkBlock;
                        }
                        if (t == 17) {
                            blk = ChaosPersists.MyFlowerBlueBlock;
                        }
                        if (t == 18) {
                            blk = ChaosPersists.MyQuinoaPlant1;
                        }
                        if (t == 19) {
                            blk = ChaosPersists.MyRicePlant;
                        }
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                }
            }
        }
        for (i = 0; i < length; ++i) {
            for (k = 0; k < width; ++k) {
                for (j = height; j <= height + 6; ++j) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + 1), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2), (int)(cposy + 2), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 1), (int)(cposy + 1), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 1), (int)(cposy + 2), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        this.placeDoor(world, new BlockPos(cposx + width / 2, cposy + 1, cposz), Direction.SOUTH, (DoorBlock)Blocks.IRON_DOOR);
        this.placeDoor(world, new BlockPos(cposx + width / 2 - 1, cposy + 1, cposz), Direction.SOUTH, (DoorBlock)Blocks.IRON_DOOR);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 2), (int)(cposy + 2), (int)cposz, (Block)Blocks.STONE, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 1), (int)(cposy + 2), (int)cposz, (Block)Blocks.STONE, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - 2), (int)(cposy + 2), (int)(cposz - 1), (Block)Blocks.STONE_BUTTON, (int)4, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + 1), (int)(cposy + 2), (int)(cposz - 1), (Block)Blocks.STONE_BUTTON, (int)4, (int)2);
        i = length / 2;
        k = width / 2;
        j = height + 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "triffid"));
        }
        j = height + 2;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "triffid"));
        }
        j = height;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + i, cposy + j, cposz + k);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(5)));
        }
    }

    public void makeMonsterIsland(World world, int cposx, int cposy, int cposz) {
        int j;
        int i;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        String monster = "Sea Viper";
        chestContents = this.MonsterIslandContentsList;
        if (world.random.nextInt(2) == 0) {
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
                this.FastSetBlock(world, cposx + i, cposy, cposz + j, (Block)Blocks.SAND);
                this.FastSetBlock(world, cposx + i, cposy - 1, cposz + j, Blocks.STONE);
            }
        }
        for (i = -2; i <= 2; ++i) {
            for (j = -2; j <= 2; ++j) {
                this.FastSetBlock(world, cposx + i, cposy + 3, cposz + j, (Block)Blocks.OAK_LEAVES);
            }
        }
        this.FastSetBlock(world, cposx, cposy + 4, cposz, (Block)Blocks.OAK_LEAVES);
        this.FastSetBlock(world, cposx, cposy + 3, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx, cposy + 2, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx, cposy + 1, cposz, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx + 1, cposy + 3, cposz + 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx - 1, cposy + 3, cposz - 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx + 1, cposy + 3, cposz - 1, Blocks.OAK_LOG);
        this.FastSetBlock(world, cposx - 1, cposy + 3, cposz + 1, Blocks.OAK_LOG);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", monster.toLowerCase().replace(' ', '_')));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 3, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 3, cposz);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", monster.toLowerCase().replace(' ', '_')));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz + 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz + 1);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", monster.toLowerCase().replace(' ', '_')));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 3, cposz - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 3, cposz - 1);
        if (tileentitymobspawner != null) {
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", monster.toLowerCase().replace(' ', '_')));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz - 1), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz - 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx, cposy + 1, cposz + 1);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
        }
    }

    public void makeNightmareRookery(World world, int cposx, int cposy, int cposz) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        String monster = "Nightmare";
        chestContents = this.NightmareRookeryContentsList;
        int h = 0;
        int k = 0;
        int j = 0;
        int i = 0;
        block0 : for (i = -5; i <= 20; ++i) {
            k += world.random.nextInt(3) - 1;
            h = world.random.nextInt(20) + 1;
            for (j = 0; j < h; ++j) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.STONE);
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i + 1, cposy + j, cposz + k, Blocks.STONE);
                }
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i - 1, cposy + j, cposz + k, Blocks.STONE);
                }
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 1, Blocks.STONE);
                }
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k - 1, Blocks.STONE);
                }
                if (j < 18) continue;
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 2, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j + 2, cposz + k);
                if (tileentitymobspawner != null) {
                    this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", monster.toLowerCase().replace(' ', '_')));
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k), Blocks.CHEST.defaultBlockState(), 2);
                chest = this.getChestTileEntity(world, cposx + i, cposy + j + 1, cposz + k);
                if (chest == null) continue block0;
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
                continue block0;
            }
        }
        block2 : for (i = -5; i <= 20; ++i) {
            k += world.random.nextInt(3) - 1;
            h = world.random.nextInt(20) + 1;
            for (j = 0; j < h; ++j) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.STONE);
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i + 1, cposy + j, cposz + k, Blocks.STONE);
                }
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i - 1, cposy + j, cposz + k, Blocks.STONE);
                }
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + 1, Blocks.STONE);
                }
                if (world.random.nextInt(j + 5) == 1) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k - 1, Blocks.STONE);
                }
                if (j < 18) continue;
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 2, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j + 2, cposz + k);
                if (tileentitymobspawner != null) {
                    this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", monster.toLowerCase().replace(' ', '_')));
                }
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j + 1, cposz + k), Blocks.CHEST.defaultBlockState(), 2);
                chest = this.getChestTileEntity(world, cposx + i, cposy + j + 1, cposz + k);
                if (chest == null) continue block2;
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(4 + world.random.nextInt(5)));
                continue block2;
            }
        }
    }

    public void makeStinkyHouse(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block bid;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.StinkyHouseContentsList;
        int height = 2;
        int width = 9;
        int length = 12;
        int yardwidth = 16;
        int yardlength = 24;
        for (i = 0; i <= yardlength; ++i) {
            for (k = 0; k <= yardwidth; ++k) {
                bid = Blocks.AIR;
                if (i == 0 || i == yardlength || k == 0 || k == yardwidth) {
                    bid = Blocks.OAK_FENCE;
                }
                if (bid == Blocks.OAK_FENCE && world.random.nextInt(3) == 1) {
                    bid = Blocks.AIR;
                }
                if (bid == Blocks.AIR && world.random.nextInt(10) == 1) {
                    bid = Blocks.DEAD_BUSH;
                }
                if (bid == Blocks.AIR) continue;
                this.FastSetBlock(world, cposx + i - 5, cposy + 1, cposz + k - 4, bid);
            }
        }
        for (i = 0; i <= length; ++i) {
            for (k = 0; k <= width; ++k) {
                for (int j = 0; j <= height; ++j) {
                    bid = Blocks.AIR;
                    if (i == 0 || i == length || k == 0 || k == width) {
                        bid = Blocks.OAK_PLANKS;
                    }
                    if (bid == Blocks.OAK_PLANKS && j == 1 && (i == 1 || i == length - 1 || k == 1 || k == width - 1)) {
                        bid = Blocks.GLASS_PANE;
                    }
                    if (j == height) {
                        bid = Blocks.OAK_PLANKS;
                    }
                    if (world.random.nextInt(10) == 1) {
                        bid = Blocks.AIR;
                    }
                    if (!(j != 0 && j != 1 || i != 0 || k != width / 2 && k != width / 2 + 1)) {
                        bid = Blocks.AIR;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k, bid);
                }
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + 1, cposz + 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 2, cposy + 1, cposz + 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "stink_bug"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + length - 2, cposy + 1, cposz + width - 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + length - 2, cposy + 1, cposz + width - 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "stinky"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + length / 2, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + length / 2, cposy + 1, cposz + width / 2);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(8 + world.random.nextInt(5)));
        }
    }

    public void makeRubberDuckyPond(World world, int cposx, int cposy, int cposz) {
        int i;
        Block bid = Blocks.AIR;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.RubberDuckyContentsList;
        for (i = 0; i < 2; ++i) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + 6, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + 6, cposz);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rubber_ducky"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 5, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 5, cposz), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + 5, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(8 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 4, cposz), Blocks.GLASS.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 4, cposz), Blocks.GLASS.defaultBlockState(), 2);
        for (i = 0; i < 2; ++i) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + 3, cposz), Blocks.WATER.defaultBlockState(), 3);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 3, cposz), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + 3, cposz), Blocks.WATER.defaultBlockState(), 3);
        for (i = 0; i < 12; ++i) {
            for (int k = 0; k < 11; ++k) {
                bid = Blocks.WATER;
                if (i == 0 || k == 0 || i == 11 || k == 10) {
                    bid = Blocks.SAND;
                }
                this.FastSetBlock(world, cposx + i - 5, cposy, cposz + k - 5, bid);
                bid = Blocks.AIR;
                this.FastSetBlock(world, cposx + i - 5, cposy + 1, cposz + k - 5, bid);
                this.FastSetBlock(world, cposx + i - 5, cposy + 2, cposz + k - 5, bid);
            }
        }
    }

    public void makeWhiteHouse(World world, int cposx, int cposy, int cposz) {
        Object tileentitymobspawner = null;
        Object chest = null;
        Object chestContents = null;
        this.makefountain(world, cposx - 5, cposy, cposz - 15);
        this.makefountain(world, cposx + 15, cposy, cposz - 15);
        this.makewalkway(world, cposx + 7, cposy, cposz - 15);
        this.makewhbase(world, cposx - 4, cposy, cposz - 6);
        this.makewhwalls(world, cposx - 3, cposy + 2, cposz - 5);
        this.makewhroof(world, cposx - 4, cposy, cposz - 6);
        this.makewhinterior(world, cposx - 1, cposy + 2, cposz - 3);
    }

    private void makefountain(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        for (int i = 0; i < 7; ++i) {
            for (int k = 0; k < 5; ++k) {
                for (int j = 0; j < 15; ++j) {
                    bid = Blocks.WATER;
                    if (i == 0 || k == 0 || i == 6 || k == 4) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 1 && i == 3 && k == 2) {
                        bid = Blocks.GLOWSTONE;
                    }
                    if (j > 1) {
                        bid = Blocks.AIR;
                        if (j <= 4 && i == 3 && k == 2) {
                            bid = Blocks.QUARTZ_BLOCK;
                        }
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, bid);
                }
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 3, cposy + 5, cposz + 2), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + 5, cposz + 2), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 4, cposy + 5, cposz + 2), Blocks.WATER.defaultBlockState(), 3);
    }

    private void makewalkway(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 10; ++k) {
                for (int j = 0; j < 15; ++j) {
                    bid = Blocks.QUARTZ_BLOCK;
                    if (j == 1) {
                        bid = Blocks.AIR;
                        if (k > 6) {
                            bid = Blocks.QUARTZ_BLOCK;
                        }
                    }
                    if (j > 1) {
                        bid = Blocks.AIR;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, bid);
                }
            }
        }
    }

    private void makewhbase(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        Block bid = Blocks.AIR;
        for (i = 0; i < 25; ++i) {
            for (k = 0; k < 25; ++k) {
                bid = Blocks.QUARTZ_BLOCK;
                this.FastSetBlock(world, cposx + i, cposy + 1, cposz + k, bid);
                if (i != 0 && i != 24 || k != 0 && k != 24) continue;
                this.FastSetBlock(world, cposx + i, cposy + 2, cposz + k, ChaosPersists.CrystalTorch);
            }
        }
        for (i = 1; i < 24; ++i) {
            for (k = 1; k < 24; ++k) {
                bid = Blocks.QUARTZ_BLOCK;
                this.FastSetBlock(world, cposx + i, cposy + 2, cposz + k, bid);
            }
        }
    }

    private void makewhwalls(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        for (int i = 0; i < 23; ++i) {
            for (int k = 0; k < 23; ++k) {
                for (int j = 0; j < 6; ++j) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == 22 || k == 22) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j != 0 && bid != Blocks.AIR) {
                        if (k == 22) {
                            if ((j & 1) == 1) {
                                if ((i & 1) == 0 || (k & 1) == 0) {
                                    bid = Blocks.GLASS_PANE;
                                }
                            } else if ((i & 1) == 1 || (k & 1) == 1) {
                                bid = Blocks.GLASS_PANE;
                            }
                        } else if (k != 0) {
                            if ((j & 1) == 1) {
                                if (i == 2 || k == 2 || i == 20 || k == 20) {
                                    bid = Blocks.GLASS_PANE;
                                }
                            } else if (i == 1 || k == 1 || i == 21 || k == 21) {
                                bid = Blocks.GLASS_PANE;
                            }
                            if (j > 0 && j < 5 && k > 7 && k < 15) {
                                bid = Blocks.GLASS_PANE;
                            }
                        } else if ((j & 1) == 1) {
                            if (i == 2 || k == 2 || i == 20 || k == 20) {
                                bid = Blocks.GLASS_PANE;
                            }
                        } else if (i == 1 || k == 1 || i == 21 || k == 21) {
                            bid = Blocks.GLASS_PANE;
                        }
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, bid);
                }
            }
        }
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 11), (int)cposy, (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 11), (int)(cposy + 1), (int)cposz, (Block)Blocks.AIR, (int)0, (int)2);
        this.placeDoor(world, new BlockPos(cposx + 11, cposy, cposz), Direction.SOUTH, (DoorBlock)Blocks.IRON_DOOR);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + 12), (int)(cposy + 1), (int)(cposz - 1), (Block)Blocks.STONE_BUTTON, (int)4, (int)2);
    }

    private void makewhroof(World world, int cposx, int cposy, int cposz) {
        Block bid = Blocks.AIR;
        for (int j = 0; j < 13; ++j) {
            for (int i = 0; i < 25 - 2 * j; ++i) {
                for (int k = 0; k < 25 - 2 * j; ++k) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == 24 - 2 * j || k == 24 - 2 * j) {
                        bid = Blocks.QUARTZ_BLOCK;
                    }
                    if (j == 0 && bid != Blocks.AIR && (i + k & 1) == 1) {
                        bid = Blocks.EMERALD_BLOCK;
                    }
                    if (j == 12) {
                        bid = Blocks.EMERALD_BLOCK;
                    }
                    this.FastSetBlock(world, cposx + i + j, cposy + 8 + j, cposz + k + j, bid);
                    if (i != 0 && i != 24 - 2 * j || k != 0 && k != 24 - 2 * j) continue;
                    this.FastSetBlock(world, cposx + i + j, cposy + 8 + j + 1, cposz + k + j, ChaosPersists.CrystalTorch);
                }
            }
        }
        bid = Blocks.OAK_FENCE;
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 11, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 10, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 9, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 8, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 7, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 6, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 5, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 4, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 3, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 2, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 1, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 0, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 11, cposy + 8 + 0, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 13, cposy + 8 + 0, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 0, cposz + 11, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 0, cposz + 13, bid);
        bid = ChaosPersists.CrystalTorch;
        this.FastSetBlock(world, cposx + 11, cposy + 8 + 1, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 13, cposy + 8 + 1, cposz + 12, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 1, cposz + 11, bid);
        this.FastSetBlock(world, cposx + 12, cposy + 8 + 1, cposz + 13, bid);
    }

    private void makewhinterior(World world, int cposx, int cposy, int cposz) {
        int i;
        int zoff = 1;
        int xoff = 0;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff), (Block)Blocks.QUARTZ_STAIRS, (int)3, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 1), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 2), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 3), (Block)Blocks.QUARTZ_STAIRS, (int)2, (int)2);
        }
        xoff = 11;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff), (Block)Blocks.QUARTZ_STAIRS, (int)3, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 1), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 2), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 3), (Block)Blocks.QUARTZ_STAIRS, (int)2, (int)2);
        }
        zoff = 7;
        xoff = 0;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff), (Block)Blocks.QUARTZ_STAIRS, (int)3, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 1), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 2), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 3), (Block)Blocks.QUARTZ_STAIRS, (int)2, (int)2);
        }
        xoff = 11;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff), (Block)Blocks.QUARTZ_STAIRS, (int)3, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 1), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 2), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 3), (Block)Blocks.QUARTZ_STAIRS, (int)2, (int)2);
        }
        zoff = 13;
        xoff = 0;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff), (Block)Blocks.QUARTZ_STAIRS, (int)3, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 1), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 2), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 3), (Block)Blocks.QUARTZ_STAIRS, (int)2, (int)2);
        }
        xoff = 11;
        for (i = 0; i < 8; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff), (Block)Blocks.QUARTZ_STAIRS, (int)3, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 1), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 2), (Block)Blocks.PISTON_HEAD, (int)1, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + xoff + i), (int)cposy, (int)(cposz + zoff + 3), (Block)Blocks.QUARTZ_STAIRS, (int)2, (int)2);
        }
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        chestContents = this.WhiteHouseContentsList;
        zoff = 18;
        xoff = 2;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy + 1, cposz + zoff), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff, cposy + 1, cposz + zoff);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "criminal"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy, cposz + zoff), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + xoff, cposy, cposz + zoff);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
        xoff = 6;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy + 1, cposz + zoff), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff, cposy + 1, cposz + zoff);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "criminal"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy, cposz + zoff), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + xoff, cposy, cposz + zoff);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
        xoff = 12;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy + 1, cposz + zoff), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff, cposy + 1, cposz + zoff);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "criminal"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy, cposz + zoff), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + xoff, cposy, cposz + zoff);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
        xoff = 16;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy + 1, cposz + zoff), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + xoff, cposy + 1, cposz + zoff);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "criminal"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + xoff, cposy, cposz + zoff), Blocks.CHEST.defaultBlockState(), 2);
        chest = this.getChestTileEntity(world, cposx + xoff, cposy, cposz + zoff);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(3 + world.random.nextInt(5)));
        }
    }

    public void makeQueenAltar(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        int j;
        Block bid = Blocks.AIR;
        int width = 51;
        int length = 51;
        int height = 48;
        if (world.isClientSide) {
            return;
        }
        for (j = 0; j <= height + 10; ++j) {
            for (i = -5; i < width + 5; ++i) {
                for (k = -5; k < length + 5; ++k) {
                    bid = Blocks.AIR;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        j = 0;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                bid = Blocks.GRASS_BLOCK;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                for (int v = 1; v < 10; ++v) {
                    bid = world.getBlockState(new net.minecraft.util.math.BlockPos(cposx + i, cposy + j - v, cposz + k)).getBlock();
                    if (bid != Blocks.AIR && bid != Blocks.GRASS_BLOCK && bid != Blocks.WATER) continue;
                    bid = Blocks.DIRT;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j - v), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                }
            }
        }
        this.makequeencolumn(world, cposx + 1, cposy + 1, cposz + 1);
        this.makequeencolumn(world, cposx + width - 8, cposy + 1, cposz + length - 8);
        this.makequeencolumn(world, cposx + 1, cposy + 1, cposz + length - 8);
        this.makequeencolumn(world, cposx + width - 8, cposy + 1, cposz + 1);
        j = height - 1;
        bid = Blocks.OBSIDIAN;
        for (i = 0; i < width; ++i) {
            for (k = 0; k < length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        j = height;
        bid = Blocks.OBSIDIAN;
        for (i = -1; i <= width; ++i) {
            for (k = -1; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        this.makequeenbackground(world, cposx + 4, cposy + 10, cposz + 9);
        this.makequeencenteraltar(world, cposx + width / 2, cposy, cposz + length / 2);
    }

    private void makequeencolumn(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block bid = Blocks.AIR;
        int meta = 0;
        int width = 5;
        int length = 5;
        int height = 44;
        if (world.isClientSide) {
            return;
        }
        int j = 0;
        bid = Blocks.OBSIDIAN;
        for (i = 0; i < width + 2; ++i) {
            for (k = 0; k < length + 2; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + height + 1), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
            }
        }
        ++cposx;
        ++cposz;
        ++cposy;
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                for (k = 0; k < length; ++k) {
                    bid = Blocks.AIR;
                    if (i == 0 || k == 0 || i == width - 1 || k == length - 1) {
                        bid = Blocks.OBSIDIAN;
                    }
                    if (j % 4 == 0 && bid != Blocks.AIR && (i == 2 || k == 2)) {
                        bid = Blocks.REDSTONE_BLOCK;
                    }
                    if (j % 4 == 1 && bid != Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                    }
                    if (j % 4 == 2 && bid != Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 2 || k == 2) {
                            bid = ChaosPersists.MyBlockAmethystBlock;
                        }
                    }
                    if (j % 4 == 3 && bid != Blocks.AIR) {
                        if (i == 1 || k == 1) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                        if (i == 3 || k == 3) {
                            bid = Blocks.REDSTONE_BLOCK;
                        }
                    }
                    meta = 0;
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)meta, (int)2);
                }
            }
        }
    }

    private void makequeenbackground(World world, int cposx, int cposy, int cposz) {
        int i;
        Block bid = Blocks.AIR;
        boolean meta = false;
        int curz = 0;
        int cury = 0;
        int height = 33;
        int width = 33;
        bid = Blocks.STONE;
        for (int m = 0; m < this.queen.length; ++m) {
            int v = this.queen[m];
            if (v < 0) {
                bid = Blocks.STONE;
                while (curz < width) {
                    ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + cury), (int)(cposz + curz), (Block)bid, (int)0, (int)2);
                    ++curz;
                }
                ++cury;
                curz = 0;
                continue;
            }
            for (int n = 0; n < v; ++n) {
                ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + cury), (int)(cposz + curz), (Block)bid, (int)0, (int)2);
                ++curz;
            }
            bid = bid == Blocks.STONE ? ChaosPersists.MyBlockRubyBlock : Blocks.STONE;
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 1), (int)(cposz + i), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        }
        for (i = 0; i < width; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height), (int)(cposz + i), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + i), (int)(cposz - 1), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        }
        for (i = -1; i <= height; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + i), (int)(cposz + width), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        }
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 2), (int)(cposz - 2), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 1), (int)(cposz + width + 1), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 2), (int)(cposz + width + 1), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 1), (int)(cposz - 2), (Block)Blocks.DIAMOND_BLOCK, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 1), (int)(cposz - 2), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 2), (int)(cposz + width + 1), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy - 1), (int)(cposz + width + 1), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)cposx, (int)(cposy + height + 2), (int)(cposz - 2), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
    }

    private void makequeencenteraltar(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block bid = Blocks.AIR;
        boolean meta = false;
        int width = 10;
        int length = 10;
        int j = 0;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 6;
        length = 20;
        j = 0;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 20;
        length = 6;
        j = 0;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 8;
        length = 8;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.OBSIDIAN;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 4;
        length = 18;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.OBSIDIAN;
                if (i == width && (k == - length || k == length)) {
                    bid = ChaosPersists.MyBlockAmethystBlock;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = ChaosPersists.MyBlockAmethystBlock;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 18;
        length = 4;
        j = 1;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                bid = Blocks.OBSIDIAN;
                if (i == width && (k == - length || k == length)) {
                    bid = ChaosPersists.MyBlockAmethystBlock;
                }
                if (i == - width && (k == - length || k == length)) {
                    bid = ChaosPersists.MyBlockAmethystBlock;
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 7;
        length = 7;
        j = 2;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
            }
        }
        width = 3;
        length = 17;
        j = 2;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 17;
        length = 3;
        j = 2;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 6;
        length = 6;
        j = 3;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 2;
        length = 16;
        j = 3;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 16;
        length = 2;
        j = 3;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
            }
        }
        width = 2;
        length = 2;
        j = 4;
        bid = Blocks.OBSIDIAN;
        for (i = - width; i <= width; ++i) {
            for (k = - length; k <= length; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)bid, (int)0, (int)2);
                if (i == width && (k == - length || k == length)) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
                }
                if (i != - width || k != - length && k != length) continue;
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + 1), (int)(cposz + k), (Block)ChaosPersists.CrystalTorch, (int)0, (int)2);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx, cposy + j, cposz, 2, 3);
        ChestTileEntity chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            chest.setItem(13, new ItemStack(ChaosPersists.TheQueenEgg));
        }
    }

    public void makeFrogPond(World world, int cposx, int cposy, int cposz) {
        MobSpawnerTileEntity tileentitymobspawner = null;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "frog"));
        }
        for (int i = -3; i <= 3; ++i) {
            for (int j = -3; j <= 3; ++j) {
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy, cposz + j), Blocks.WATER.defaultBlockState(), 3);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 1, cposz), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz - 1), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 1, cposz + 1), Blocks.WATER.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 2, cposz), Blocks.LILY_PAD.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 2, cposz), Blocks.LILY_PAD.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz - 1), Blocks.LILY_PAD.defaultBlockState(), 3);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 2, cposz + 1), Blocks.LILY_PAD.defaultBlockState(), 3);
    }

    public void makePumpkin(World world, int cposx, int cposy, int cposz) {
        int k;
        int j;
        int i;
        int width = 14;
        int depth = 12;
        int height = 14;
        int dark_green = 13;
        int orange = 1;
        int which_color = 0;
        Block blk = Blocks.AIR;
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (i = 0; i < width; ++i) {
            for (j = 0; j < height; ++j) {
                for (k = 0; k < depth; ++k) {
                    which_color = 0;
                    blk = Blocks.AIR;
                    if (j == 0 || j == height - 1) {
                        which_color = orange;
                        blk = ChaosPersists.legacyStainedHardenedClay(which_color);
                    }
                    if (i == 0 || i == width - 1) {
                        which_color = orange;
                        blk = ChaosPersists.legacyStainedHardenedClay(which_color);
                    }
                    if (k == 0 || k == depth - 1) {
                        which_color = orange;
                        blk = ChaosPersists.legacyStainedHardenedClay(which_color);
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)which_color, (int)2);
                }
            }
        }
        i = width / 2 - 1;
        k = 0;
        j = 11;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 5), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 10;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 5), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 9;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 5), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 8;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 7;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 4;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 1), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 3;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 1), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 2;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 1), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 1;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i + 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        i = width / 2;
        k = 0;
        j = 11;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 5), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 10;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 5), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 9;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 5), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 8;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 7;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 4;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 1), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 3;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 1), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 2;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 1), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 3), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 4), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        j = 1;
        ChaosPersists.setBlockFast((World)world, (int)(cposx + i - 2), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.AIR, (int)0, (int)2);
        k = depth / 2 - 1;
        for (j = 0; j < 4; ++j) {
            for (i = 0; i < 3; ++i) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 - i - j), (int)(cposy + height + j), (int)(cposz + k), (Block)ChaosPersists.legacyStainedHardenedClay(dark_green), (int)0, (int)2);
            }
        }
        for (j = 0; j < 5; ++j) {
            for (i = 0; i < 2; ++i) {
                for (k = 0; k < 2; ++k) {
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + i - 1), (int)(cposy + j + 1), (int)(cposz + depth / 2 + k - 1), (Block)Blocks.OAK_PLANKS, (int)0, (int)2);
                }
            }
        }
        j = 5;
        for (i = 0; i < 2; ++i) {
            for (k = 0; k < 2; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + i - 1), (int)(cposy + j + 1), (int)(cposz + depth / 2 + k - 1), (Block)Blocks.NETHERRACK, (int)0, (int)2);
            }
        }
        j = 6;
        k = 0;
        for (i = 0; i < 2; ++i) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + width / 2 + i - 1), (int)(cposy + j + 1), (int)(cposz + depth / 2 + k - 1), (Block)Blocks.FIRE, (int)0, (int)2);
        }
        j = 6;
        k = 1;
        i = 0;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost_pumpkin_skelly"));
        }
        i = 1;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + i - 1, cposy + j + 1, cposz + depth / 2 + k - 1);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "ghost_pumpkin_skelly"));
        }
    }

    public void makeRoundRotator(World world, int cposx, int cposy, int cposz) {
        float cury;
        float curx;
        float curdeg;
        Block blk;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        float radius = 6.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)radius * Math.cos(Math.toRadians(curdeg)));
            cury = (float)((double)radius * Math.sin(Math.toRadians(curdeg)));
            blk = Blocks.BEDROCK;
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), (int)((float)(cposy + 6) + cury + 0.5f), cposz, blk);
        }
        radius = 2.0f;
        for (curdeg = 0.0f; curdeg < 360.0f; curdeg += 5.0f) {
            curx = (float)((double)radius * Math.cos(Math.toRadians(curdeg)));
            cury = (float)((double)radius * Math.sin(Math.toRadians(curdeg)));
            blk = ChaosPersists.MyCrystalPinkBlock;
            this.FastSetBlock(world, (int)((float)cposx + curx + 0.5f), (int)((float)(cposy + 6) + cury + 0.5f), cposz, blk);
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 6 + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 6 + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 6 - 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 6 - 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 6 - 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 1, cposy + 6 - 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + 6 + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 1, cposy + 6 + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "rotator"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 5, cposy + 6, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 5, cposy + 6, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 5, cposy + 6, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 5, cposy + 6, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 6 - 5, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 6 - 5, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 6 + 5, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx, cposy + 6 + 5, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "dungeon_beast"));
        }
        blk = ChaosPersists.CrystalCoal;
        this.FastSetBlock(world, cposx + 1, cposy + 6, cposz, blk);
        this.FastSetBlock(world, cposx - 1, cposy + 6, cposz, blk);
        this.FastSetBlock(world, cposx, cposy + 6 + 1, cposz, blk);
        this.FastSetBlock(world, cposx, cposy + 6 - 1, cposz, blk);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + 6, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx, cposy + 6, cposz, 2, 3);
        chest = this.getChestTileEntity(world, cposx, cposy + 6, cposz);
        if (chest != null) {
            chestContents = this.CrystalBattleTowerVortexContentsList;
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(6 + world.random.nextInt(6)));
        }
    }

    public void makeRainbow(World world, int cposx, int cposy, int cposz) {
        int k;
        int i;
        Block blk;
        int width = 12;
        int depth = 1;
        int blk_color = 0;
        MobSpawnerTileEntity tileentitymobspawner = null;
        ChestTileEntity chest = null;
        WeightedRandomChestContent[] chestContents = null;
        blk_color = 0;
        int j = 35;
        width = 12;
        depth = 1;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)ChaosPersists.legacyStainedHardenedClay(blk_color), (int)0, (int)2);
            }
        }
        k = 0;
        for (i = - width + 1; i < width; i += 3) {
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)Blocks.WATER, (int)0, (int)2);
            ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j - 1), (int)(cposz + k), (Block)Blocks.WATER, (int)0, (int)2);
        }
        width = 13;
        depth = 2;
        j = 26;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                blk = Blocks.AIR;
                if (i == - width || i == width - 1) {
                    blk = ChaosPersists.legacyStainedHardenedClay(blk_color);
                }
                if (k == - depth || k == depth) {
                    blk = ChaosPersists.legacyStainedHardenedClay(blk_color);
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)blk_color, (int)2);
            }
        }
        width = 14;
        depth = 3;
        j = 27;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                blk = Blocks.AIR;
                if (i == - width || i == width - 1) {
                    blk = ChaosPersists.legacyStainedHardenedClay(blk_color);
                }
                if (k == - depth || k == depth) {
                    blk = ChaosPersists.legacyStainedHardenedClay(blk_color);
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)blk_color, (int)2);
            }
        }
        width = 13;
        depth = 2;
        j = 28;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                blk = Blocks.AIR;
                if (i == - width || i == width - 1) {
                    blk = ChaosPersists.legacyStainedHardenedClay(blk_color);
                }
                if (k == - depth || k == depth) {
                    blk = ChaosPersists.legacyStainedHardenedClay(blk_color);
                }
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)blk_color, (int)2);
            }
        }
        j = 29;
        width = 12;
        depth = 1;
        for (i = - width; i < width; ++i) {
            for (k = - depth; k <= depth; ++k) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)ChaosPersists.legacyStainedHardenedClay(blk_color), (int)0, (int)2);
            }
        }
        j = 30;
        for (int m = 3; m < 11; ++m) {
            blk_color = this.blkcolors[m - 3];
            for (i = 0; i < m; ++i) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + m), (int)(cposy + j + i), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(blk_color), (int)0, (int)2);
                ChaosPersists.setBlockFast((World)world, (int)(cposx - (m + 1)), (int)(cposy + j + i), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(blk_color), (int)0, (int)2);
            }
            for (i = - m + 1; i <= m; ++i) {
                ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j + m), (int)cposz, (Block)ChaosPersists.legacyStainedHardenedClay(blk_color), (int)0, (int)2);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + j, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 2, cposy + j, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + j, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + j, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 2, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + j + 1, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + j + 1, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 2, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + 2, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + j + 2, cposz), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + j + 2, cposz);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "cloud_shark"));
        }
        chestContents = this.RainbowContentsList;
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx, cposy + j, cposz, 2, 3);
        chest = this.getChestTileEntity(world, cposx, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 1, cposy + j, cposz), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx - 1, cposy + j, cposz, 2, 3);
        chest = this.getChestTileEntity(world, cposx - 1, cposy + j, cposz);
        if (chest != null) {
            WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(10 + world.random.nextInt(5)));
        }
    }

    public void makeEnormousCastleQ(World world, int cposx, int cposy, int cposz) {
        int j;
        int k;
        int i;
        int width = 28;
        int height = 16;
        int platformwidth = 11;
        int level = 0;
        if (world.isClientSide) {
            return;
        }
        level = 1 + world.random.nextInt(6);
        if (level <= 3 && world.random.nextInt(3) != 1) {
            level += 3;
        }
        for (i = -20; i < width + 4; ++i) {
            for (j = 1; j < height + 10; ++j) {
                for (k = -4; k < width + 4; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.OBSIDIAN);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                k = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                i = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
                i = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.IRON_BARS);
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz + 1), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz + width - 2), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + 1, cposz + 1), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + 1, cposz + width - 2), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
        for (i = -4; i < width + 4; ++i) {
            for (k = -4; k < width + 4; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(world, cposx + i, cposy, cposz + k, Blocks.OBSIDIAN);
                }
                if (i != -4 && k != -4 && i != width + 3 && k != width + 3) continue;
                this.FastSetBlock(world, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - 3, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - 3, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + 2, cposy + 1 + j, cposz - 3), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + 2, cposy + 1 + j, cposz - 3);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + 2, cposy + 1 + j, cposz + width + 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + 2, cposy + 1 + j, cposz + width + 2);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "lurking_terror"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
        tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 4, cposz + width / 2);
        if (tileentitymobspawner != null) {
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "emperor_scorpion"));
        }
        j = height;
        this.buildLevelQ(world, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Rotator", 1, -1, 5, 1, level);
        j += 10;
        if (level >= 2) {
            this.buildLevelQ(world, cposx + 1, cposy + j, cposz + 1, width - 2, 10, 4, "Bee", 0, 0, 4, 2, level);
        }
        j += 10;
        if (level >= 3) {
            this.buildLevelQ(world, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 4, "Mantis", 1, 1, 4, 3, level);
        }
        j += 9;
        if (level >= 4) {
            this.buildLevelQ(world, cposx + 2, cposy + j, cposz + 2, width - 4, 9, 3, "Mothra", 0, 0, 4, 4, level);
        }
        j += 9;
        if (level >= 5) {
            this.buildLevelQ(world, cposx + 3, cposy + j, cposz + 3, width - 6, 8, 3, "Brutalfly", 1, 1, 4, 5, level);
        }
        j += 8;
        if (level >= 6) {
            this.buildLevelQ(world, cposx + 3, cposy + j, cposz + 3, width - 6, 16, 3, "Vortex", 0, 0, 3, 6, level);
        }
        j += 16;
        for (i = 0; i < platformwidth; ++i) {
            j = height;
            for (k = - platformwidth / 2; k <= platformwidth / 2; ++k) {
                this.FastSetBlock(world, cposx + i - 20, cposy + j, cposz + k + width / 2, ChaosPersists.MyBlockAmethystBlock);
                if (i != 0 && i != platformwidth - 1 && k != - platformwidth / 2 && k != platformwidth / 2 || i == 0 && k >= -1 && k <= 1) continue;
                this.FastSetBlock(world, cposx + i - 20, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        for (i = -10; i <= -3; ++i) {
            j = height;
            for (k = -2; k < 3; ++k) {
                if (i == -3 || i == -10) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + width / 2, ChaosPersists.MyBlockAmethystBlock);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = -21;
        for (j = height; j >= 0; --j) {
            for (k = -2; k < 3; ++k) {
                for (int t = 0; t < 6; ++t) {
                    this.FastSetBlock(world, cposx + i, cposy + j + t + 1, cposz + k + width / 2, Blocks.AIR);
                }
                if (j == 0) {
                    if (k != -2 && k != 2) {
                        this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.AIR);
                        continue;
                    }
                    this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 2, cposz + k + width / 2, Blocks.NETHERRACK);
                    this.FastSetBlock(world, cposx + i, cposy + j + 3, cposz + k + width / 2, (Block)Blocks.FIRE);
                    continue;
                }
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k + width / 2, ChaosPersists.MyBlockAmethystBlock);
                if (k != -2 && k != 2) continue;
                this.FastSetBlock(world, cposx + i, cposy + j + 1, cposz + k + width / 2, Blocks.NETHER_BRICK_FENCE);
            }
            --i;
        }
        if (level >= 6) {
            int span = width * 3;
            for (int tries = 0; tries < 100; ++tries) {
                j = -1;
                i = world.random.nextInt(span);
                k = world.random.nextInt(span);
                if (i >= span / 4 && i <= span * 3 / 4 && k >= span / 4 && k <= span * 3 / 4) continue;
                this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + (i -= span / 2) + width / 2, cposy + j, cposz + (k -= span / 2) + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
                tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i + width / 2, cposy + j, cposz + k + width / 2);
                if (tileentitymobspawner == null) continue;
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
        }
    }

    public void buildLevelQ(World world, int cposx, int cposy, int cposz, int width, int height, int pw, String critter, int stepside, int stepoff, int holelen, int decor, int level) {
        int j;
        int i;
        int k;
        for (i = - pw; i < width + pw; ++i) {
            for (j = 1; j < height; ++j) {
                for (k = - pw; k < width + pw; ++k) {
                    this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (i = 0; i < width; ++i) {
            j = 0;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            j = height;
            for (k = 0; k < width; ++k) {
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (i = 0; i < width; ++i) {
            for (j = 1; j < height; ++j) {
                k = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
                k = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.BEDROCK);
            }
        }
        for (k = 0; k < width; ++k) {
            for (j = 1; j < height; ++j) {
                Block blk = Blocks.BEDROCK;
                if (k == 0 || k == width - 1) {
                    blk = ChaosPersists.MyBlockRubyBlock;
                }
                i = 0;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
                i = width - 1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, blk);
            }
        }
        for (i = - pw; i < width + pw; ++i) {
            for (k = - pw; k < width + pw; ++k) {
                if (i < 0 || k < 0 || i >= width || k >= width) {
                    this.FastSetBlock(world, cposx + i, cposy, cposz + k, Blocks.OBSIDIAN);
                }
                if (i != - pw && k != - pw && i != width + (pw - 1) && k != width + (pw - 1)) continue;
                this.FastSetBlock(world, cposx + i, cposy + 1, cposz + k, Blocks.NETHER_BRICK_FENCE);
            }
        }
        i = - height / 2;
        i += width / 2;
        for (j = 1; j < height; ++j) {
            if (stepside != 0) {
                k = -1;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.OBSIDIAN);
            } else {
                k = width;
                this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.OBSIDIAN);
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
                this.FastSetBlock(world, cposx + i + l, cposy + j, cposz + k, Blocks.AIR);
            }
        }
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (j = 0; j < 4; ++j) {
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - (pw - 1), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx - (pw - 1), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + (pw - 2), cposy + j + 1, cposz - (pw - 1));
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2)), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width + (pw - 2), cposy + j + 1, cposz + width + (pw - 2));
            if (tileentitymobspawner == null) continue;
            this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
        }
        this.addLevelDecorationsQ(world, cposx, cposy, cposz, width, height, decor, level);
    }

    public void addLevelDecorationsQ(World world, int cposx, int cposy, int cposz, int width, int height, int decor, int difficulty) {
        int j;
        MobSpawnerTileEntity tileentitymobspawner = null;
        int reward = 1;
        String critter = "T. Rex";
        if (decor == 6) {
            this.FastSetBlock(world, cposx, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx + width - 1, cposy + height, cposz, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx + width - 1, cposy + height + 1, cposz, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx + width - 1, cposy + height, cposz + width - 1, Blocks.NETHERRACK);
            this.FastSetBlock(world, cposx + width - 1, cposy + height + 1, cposz + width - 1, (Block)Blocks.FIRE);
            this.FastSetBlock(world, cposx + width / 2, cposy + height, cposz + width / 2, Blocks.AIR);
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 - 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2 + 1, cposy + height + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + height + 2, cposz + width / 2 - 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + height + 2, cposz + width / 2 + 1);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", "nightmare"));
            }
            for (int i = 1; i < width - 1; ++i) {
                for (j = 1; j < 5; ++j) {
                    for (int k = 1; k < width - 1; ++k) {
                        this.FastSetBlock(world, cposx + i, cposy + j, cposz + k, Blocks.DIRT);
                    }
                }
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 4, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 4, cposz + width / 2);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "large_worm"));
            }
            for (j = 0; j < 10; ++j) {
                this.FastSetBlock(world, cposx + 1, cposy + j, cposz + 1, Blocks.AIR);
            }
            this.fill_chests(world, cposx, cposy + 4, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(world, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(world, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + width - 2, cposy, cposz + width - 2, Blocks.AIR);
            this.FastSetBlock(world, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + 1, cposy, cposz + 1, Blocks.AIR);
            this.FastSetBlock(world, cposx + width - 2, cposy + height, cposz + width - 2, Blocks.AIR);
            this.fill_chests(world, cposx, cposy, cposz, width, height, decor, reward);
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
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 2, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 2, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 3, cposz + width / 2), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + width / 2, cposy + 3, cposz + width / 2);
            if (tileentitymobspawner != null) {
                this.setSpawnerEntityId(tileentitymobspawner, new net.minecraft.util.ResourceLocation("chaospersists", critter.toLowerCase().replace(' ', '_')));
            }
            for (j = 1; j < 5; ++j) {
                this.FastSetBlock(world, cposx + width / 2 - 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2 + 1, cposy + j, cposz + width / 2, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 - 1, Blocks.BEDROCK);
                this.FastSetBlock(world, cposx + width / 2, cposy + j, cposz + width / 2 + 1, Blocks.BEDROCK);
            }
            this.FastSetBlock(world, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + width / 2 + 1, cposy + 1, cposz + width / 2 - 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + width / 2 - 1, cposy + 1, cposz + width / 2 + 1, ChaosPersists.MyRTPBlock);
            this.FastSetBlock(world, cposx + 1, cposy + height, cposz + 1, Blocks.AIR);
            this.fill_chestsQ(world, cposx, cposy, cposz, width, height, decor, reward);
        }
    }

    private void fill_chestsQ(World world, int cposx, int cposy, int cposz, int width, int height, int decor, int reward) {
        ChestTileEntity chest = null;
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
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + 1, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + 1, cposy + 1, cposz + width / 2, 5, 3);
        chest = this.getChestTileEntity(world, cposx + 1, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.ThePrincessEgg, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width - 2, cposy + 1, cposz + width / 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width - 2, cposy + 1, cposz + width / 2, 4, 3);
        chest = this.getChestTileEntity(world, cposx + width - 2, cposy + 1, cposz + width / 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.QueenHelmet, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.QueenBody, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + 1), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + 1, cposz + 1, 3, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + 1, cposz + 1);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack((Item)ChaosPersists.QueenLegs, 1));
                chest.setItem(2, new ItemStack((Item)ChaosPersists.QueenBoots, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
        this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + width / 2, cposy + 1, cposz + width - 2), Blocks.CHEST.defaultBlockState(), 2);
        this.setBlockMeta(world,cposx + width / 2, cposy + 1, cposz + width - 2, 2, 3);
        chest = this.getChestTileEntity(world, cposx + width / 2, cposy + 1, cposz + width - 2);
        if (chest != null) {
            if (reward == 6) {
                chest.setItem(1, new ItemStack(ChaosPersists.MyRoyal, 1));
            } else {
                WeightedRandomChestContent.generateChestContents((Random)world.random, (WeightedRandomChestContent[])chestContents, (IInventory)chest, (int)(5 + world.random.nextInt(7)));
            }
        }
    }

    public void makeSpiderHangout(World world, int cposx, int cposy, int cposz) {
        int i;
        int k;
        int j;
        Entity var8 = null;
        MobSpawnerTileEntity tileentitymobspawner = null;
        for (i = 0; i < 20; ++i) {
            for (j = -1; j < 20; ++j) {
                for (k = 0; k < 20; ++k) {
                    Block blk = Blocks.AIR;
                    if (j == -1) {
                        blk = Blocks.STONE;
                    }
                    if (j == 0) {
                        blk = Blocks.GRAVEL;
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)0, (int)2);
                }
            }
        }
        for (j = 1; j < 4; ++j) {
            k = 0;
            i = 0;
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spider_driver"));
            }
            k = 19;
            i = 19;
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spider_driver"));
            }
            k = 0;
            i = 19;
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
            if (tileentitymobspawner != null) {
                SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spider_driver"));
            }
            k = 19;
            i = 0;
            this.setWorldBlockState(world, new net.minecraft.util.math.BlockPos(cposx + i, cposy + j, cposz + k), Blocks.SPAWNER.defaultBlockState(), 2);
            tileentitymobspawner = this.getSpawnerTileEntity(world, cposx + i, cposy + j, cposz + k);
            if (tileentitymobspawner == null) continue;
            SpawnerFixHelper.setSpawnerEntityId(tileentitymobspawner.getSpawner(), new net.minecraft.util.ResourceLocation("chaospersists", "spider_driver"));
        }
        var8 = ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", "robot_spider")).create(world);
        if (var8 != null) {
            var8.moveTo((double)(cposx + 10), (double)(cposy + 1), (double)(cposz + 10), world.random.nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(var8);
        }
    }

    public void makeRedAntHangout(World world, int cposx, int cposy, int cposz) {
        Entity var8 = null;
        for (int i = 0; i < 16; ++i) {
            for (int j = -1; j < 16; ++j) {
                for (int k = 0; k < 16; ++k) {
                    Block blk = Blocks.AIR;
                    if (j == -1) {
                        blk = Blocks.STONE;
                    }
                    if (j == 0) {
                        blk = Blocks.GRAVEL;
                        if (!(i >= 3 && i <= 12 || k >= 3 && k <= 12)) {
                            blk = ChaosPersists.MyRedAntBlock;
                        }
                    }
                    ChaosPersists.setBlockFast((World)world, (int)(cposx + i), (int)(cposy + j), (int)(cposz + k), (Block)blk, (int)0, (int)2);
                }
            }
        }
        var8 = ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", "robot_red_ant")).create(world);
        if (var8 != null) {
            var8.moveTo((double)(cposx + 8), (double)(cposy + 1), (double)(cposz + 8), world.random.nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(var8);
        }
    }
}

