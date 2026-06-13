package com.astryxion.chaospersists.core;

import com.electronwill.nightconfig.core.file.FileConfig;
import org.apache.logging.log4j.LogManager;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.Difficulty;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.BowItem;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import com.astryxion.chaospersists.item.ItemSpawnEgg;
import net.minecraft.item.BlockItem;
import net.minecraft.item.SwordItem;
import com.astryxion.chaospersists.block.BlockDuctTape;
import com.astryxion.chaospersists.block.BlockPizza;
import net.minecraft.item.IItemTier;
import net.minecraft.item.UseAction;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.ItemLootEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tags.BlockTags;
import com.astryxion.chaospersists.container.ContainerCrystalWorkbench;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.Logger;
import com.astryxion.chaospersists.block.AntBlock;
import com.astryxion.chaospersists.block.BlockAppleLeaves;
import com.astryxion.chaospersists.block.BlockButterflyPlant;
import com.astryxion.chaospersists.block.BlockCorn;
import com.astryxion.chaospersists.block.BlockCrystal;
import com.astryxion.chaospersists.block.BlockCrystalLeaves;
import com.astryxion.chaospersists.block.BlockCrystalPlant;
import com.astryxion.chaospersists.block.BlockCrystalTorch;
import com.astryxion.chaospersists.block.BlockCrystalTreeLog;
import com.astryxion.chaospersists.block.BlockDuctTape;
import com.astryxion.chaospersists.block.BlockDuplicatorLog;
import com.astryxion.chaospersists.block.BlockExperienceLeaves;
import com.astryxion.chaospersists.block.BlockExperiencePlant;
import com.astryxion.chaospersists.block.BlockExtremeTorch;
import com.astryxion.chaospersists.block.BlockFireflyPlant;
import com.astryxion.chaospersists.block.BlockLettuce;
import com.astryxion.chaospersists.block.BlockMosquitoPlant;
import com.astryxion.chaospersists.block.BlockMothPlant;
import com.astryxion.chaospersists.block.BlockPizza;
import com.astryxion.chaospersists.block.BlockQuinoa;
import com.astryxion.chaospersists.block.BlockRadish;
import com.astryxion.chaospersists.block.BlockRice;
import com.astryxion.chaospersists.block.BlockRuby;
import com.astryxion.chaospersists.block.BlockScaryLeaves;
import com.astryxion.chaospersists.block.BlockSkyTreeLog;
import com.astryxion.chaospersists.block.BlockStrawberry;
import com.astryxion.chaospersists.block.BlockTitanium;
import com.astryxion.chaospersists.block.BlockTomato;
import com.astryxion.chaospersists.block.BlockUranium;
import com.astryxion.chaospersists.block.CrystalAntBlock;
import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.block.CrystalGrass;
import com.astryxion.chaospersists.block.CrystalWood;
import com.astryxion.chaospersists.block.CrystalWorkbench;
import com.astryxion.chaospersists.block.DungeonSpawnerBlock;
import com.astryxion.chaospersists.block.IslandBlock;
import com.astryxion.chaospersists.block.KingSpawnerBlock;
import com.astryxion.chaospersists.block.KrakenRepellent;
import com.astryxion.chaospersists.block.Lavafoam;
import com.astryxion.chaospersists.block.MoleDirtBlock;
import com.astryxion.chaospersists.block.QueenSpawnerBlock;
import com.astryxion.chaospersists.block.RTPBlock;
import com.astryxion.chaospersists.block.StepAccross;
import com.astryxion.chaospersists.block.StepDown;
import com.astryxion.chaospersists.block.StepUp;
import com.astryxion.chaospersists.core.ChaosGUIHandler;
import com.astryxion.chaospersists.command.CommandChaos;
import com.astryxion.chaospersists.command.CommandCrystal;
import com.astryxion.chaospersists.command.CommandDanger;
import com.astryxion.chaospersists.command.CommandMining;
import com.astryxion.chaospersists.command.CommandUtopia;
import com.astryxion.chaospersists.command.CommandVillageMania;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.Baryonyx;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.Beaver;
import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.entity.Bertha;
import com.astryxion.chaospersists.entity.BerthaHit;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.entity.Camarasaurus;
import com.astryxion.chaospersists.entity.Cassowary;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.CaveFisher;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.CliffRacer;
import com.astryxion.chaospersists.entity.CloudShark;
import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.entity.Cricket;
import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.DeadIrukandji;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Dragonfly;
import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.EasterBunny;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.EnchantedCow;
import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityCage;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import com.astryxion.chaospersists.entity.EntityMosquito;
import com.astryxion.chaospersists.entity.EntityRainbowAnt;
import com.astryxion.chaospersists.entity.EntityRedAnt;
import com.astryxion.chaospersists.entity.EntityThrownRock;
import com.astryxion.chaospersists.entity.EntityUnstableAnt;
import com.astryxion.chaospersists.entity.Fairy;
import com.astryxion.chaospersists.entity.Firefly;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.entity.Frog;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.Gazelle;
import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.GodzillaHead;
import com.astryxion.chaospersists.entity.GoldCow;
import com.astryxion.chaospersists.entity.GoldFish;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.Hydrolisc;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.Island;
import com.astryxion.chaospersists.entity.IslandToo;
import com.astryxion.chaospersists.entity.KingHead;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Lizard;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.Ostrich;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.Pointysaurus;
import com.astryxion.chaospersists.entity.QueenHead;
import com.astryxion.chaospersists.entity.Rat;
import com.astryxion.chaospersists.entity.RedCow;
import com.astryxion.chaospersists.entity.Robot1;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.RockBase;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.RubberDucky;
import com.astryxion.chaospersists.entity.RubyBird;
import com.astryxion.chaospersists.entity.Scorpion;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.SpiderDriver;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.StinkBug;
import com.astryxion.chaospersists.entity.Stinky;
import com.astryxion.chaospersists.entity.SunspotUrchin;
import com.astryxion.chaospersists.entity.Termite;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.entity.VelocityRaptor;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.Whale;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.entity.WormMedium;
import com.astryxion.chaospersists.entity.WormSmall;
import com.astryxion.chaospersists.item.Acid;
import com.astryxion.chaospersists.item.AmethystAxe;
import com.astryxion.chaospersists.item.AmethystHoe;
import com.astryxion.chaospersists.item.AmethystPickaxe;
import com.astryxion.chaospersists.item.AmethystShovel;
import com.astryxion.chaospersists.item.AmethystSword;
import com.astryxion.chaospersists.item.BandP;
import com.astryxion.chaospersists.item.BigHammer;
import com.astryxion.chaospersists.item.Coin;
import com.astryxion.chaospersists.item.CreeperRepellent;
import com.astryxion.chaospersists.item.CritterCage;
import com.astryxion.chaospersists.item.CrystalAxe;
import com.astryxion.chaospersists.item.CrystalHoe;
import com.astryxion.chaospersists.item.CrystalPickaxe;
import com.astryxion.chaospersists.item.CrystalShovel;
import com.astryxion.chaospersists.item.CrystalSword;
import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.item.EmeraldAxe;
import com.astryxion.chaospersists.item.EmeraldHoe;
import com.astryxion.chaospersists.item.EmeraldPickaxe;
import com.astryxion.chaospersists.item.EmeraldShovel;
import com.astryxion.chaospersists.item.EmeraldSword;
import com.astryxion.chaospersists.item.ExperienceCatcher;
import com.astryxion.chaospersists.item.ExperienceSword;
import com.astryxion.chaospersists.item.FairySword;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.item.IngotTitanium;
import com.astryxion.chaospersists.item.IngotUranium;
import com.astryxion.chaospersists.item.InkSack;
import com.astryxion.chaospersists.item.InstantGarden;
import com.astryxion.chaospersists.item.InstantShelter;
import com.astryxion.chaospersists.item.IrukandjiArrow;
import com.astryxion.chaospersists.item.ItemAcid;
import com.astryxion.chaospersists.item.ItemAppleSeed;
import com.astryxion.chaospersists.item.ItemButterflySeed;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.item.ItemCornCob;
import com.astryxion.chaospersists.item.ItemCreeperLauncher;
import com.astryxion.chaospersists.item.ItemCrystalSticks;
import com.astryxion.chaospersists.item.ItemDuctTape;
import com.astryxion.chaospersists.item.ItemElevator;
import com.astryxion.chaospersists.item.ItemExperienceTreeSeed;
import com.astryxion.chaospersists.item.ItemFireFish;
import com.astryxion.chaospersists.item.ItemFireflySeed;
import com.astryxion.chaospersists.item.ItemGenericFish;
import com.astryxion.chaospersists.item.ItemIceBall;
import com.astryxion.chaospersists.item.ItemIrukandji;
import com.astryxion.chaospersists.item.ItemIrukandjiArrow;
import com.astryxion.chaospersists.item.ItemLaserBall;
import com.astryxion.chaospersists.item.ItemLavaEel;
import com.astryxion.chaospersists.item.ItemLettuce;
import com.astryxion.chaospersists.item.ItemMagicApple;
import com.astryxion.chaospersists.item.ItemMinersDream;
import com.astryxion.chaospersists.item.ItemMosquitoSeed;
import com.astryxion.chaospersists.item.ItemMothSeed;
import com.astryxion.chaospersists.item.ItemNetherLost;
import com.astryxion.chaospersists.item.ItemPizza;
import com.astryxion.chaospersists.item.ItemPopcorn;
import com.astryxion.chaospersists.item.ItemRadish;
import com.astryxion.chaospersists.item.ItemRandomDungeon;
import com.astryxion.chaospersists.item.ItemRayGun;
import com.astryxion.chaospersists.item.ItemRock;
import com.astryxion.chaospersists.item.ItemSalt;
import com.astryxion.chaospersists.item.ItemShoes;
import com.astryxion.chaospersists.item.ItemSifter;
import com.astryxion.chaospersists.item.ItemSparkFish;
import com.astryxion.chaospersists.item.ItemSpiderRobotKit;
import com.astryxion.chaospersists.item.ItemSquidZooka;
import com.astryxion.chaospersists.item.ItemStrawberry;
import com.astryxion.chaospersists.item.ItemStrawberrySeed;
import com.astryxion.chaospersists.item.ItemSunFish;
import com.astryxion.chaospersists.item.ItemSunspotUrchin;
import com.astryxion.chaospersists.item.ItemThunderStaff;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.item.ItemTomato;
import com.astryxion.chaospersists.item.ItemWaterBall;
import com.astryxion.chaospersists.item.ItemWrench;
import com.astryxion.chaospersists.item.ItemZooKeeper;
import com.astryxion.chaospersists.item.LaserBall;
import com.astryxion.chaospersists.item.MantisClaw;
import com.astryxion.chaospersists.item.NightmareSword;
import com.astryxion.chaospersists.item.PoisonSword;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.item.RatSword;
import com.astryxion.chaospersists.item.RubyAxe;
import com.astryxion.chaospersists.item.RubyHoe;
import com.astryxion.chaospersists.item.RubyPickaxe;
import com.astryxion.chaospersists.item.RubyShovel;
import com.astryxion.chaospersists.item.RubySword;
import com.astryxion.chaospersists.item.Shoes;
import com.astryxion.chaospersists.item.SkateBow;
import com.astryxion.chaospersists.item.Tshirt;
import com.astryxion.chaospersists.item.UltimateArrow;
import com.astryxion.chaospersists.item.UltimateAxe;
import com.astryxion.chaospersists.item.UltimateBow;
import com.astryxion.chaospersists.item.UltimateFishHook;
import com.astryxion.chaospersists.item.UltimateFishingRod;
import com.astryxion.chaospersists.item.UltimateHoe;
import com.astryxion.chaospersists.item.UltimatePickaxe;
import com.astryxion.chaospersists.item.UltimateShovel;
import com.astryxion.chaospersists.item.UltimateSword;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.item.ZooCage;
import com.astryxion.chaospersists.proxy.ClientProxyChaos;
import com.astryxion.chaospersists.proxy.CommonProxyChaos;
import com.astryxion.chaospersists.tileentity.TileEntityCrystalFurnace;
import com.astryxion.chaospersists.util.ArmorStats;
import com.astryxion.chaospersists.util.DispenserBehaviorChaosEgg;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyBlockFlower;
import com.astryxion.chaospersists.util.MyDispenserBehaviorAcid;
import com.astryxion.chaospersists.util.MyDispenserBehaviorArrow;
import com.astryxion.chaospersists.util.MyDispenserBehaviorDeadIrukandji;
import com.astryxion.chaospersists.util.MyDispenserBehaviorIceball;
import com.astryxion.chaospersists.util.MyDispenserBehaviorLaserball;
import com.astryxion.chaospersists.util.MyDispenserBehaviorRock;
import com.astryxion.chaospersists.util.MyDispenserBehaviorSunspotUrchin;
import com.astryxion.chaospersists.util.MyDispenserBehaviorWDCharge;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.OreStats;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.util.Trees;
import com.astryxion.chaospersists.util.WeaponStats;
import com.astryxion.chaospersists.world.biome.BiomeChaosPlains;
import com.astryxion.chaospersists.world.biome.BiomeCrystalPlains;
import com.astryxion.chaospersists.world.biome.BiomeDangerPlains;
import com.astryxion.chaospersists.world.biome.BiomeGenUtopianPlains;
import com.astryxion.chaospersists.world.biome.BiomeMiningDimension;
import com.astryxion.chaospersists.world.biome.BiomeVillagePlains;
import com.astryxion.chaospersists.world.dimension.structure.BasiliskMaze;
import com.astryxion.chaospersists.world.dimension.structure.GenericDungeon;
import com.astryxion.chaospersists.world.dimension.structure.RubyBirdDungeon;
import com.astryxion.chaospersists.world.dimension.worldprovider.WorldProviderChaos;
import com.astryxion.chaospersists.world.dimension.worldprovider.WorldProviderChaos2;
import com.astryxion.chaospersists.world.dimension.worldprovider.WorldProviderChaos3;
import com.astryxion.chaospersists.world.dimension.worldprovider.WorldProviderChaos4;
import com.astryxion.chaospersists.world.dimension.worldprovider.WorldProviderChaos5;
import com.astryxion.chaospersists.world.dimension.worldprovider.WorldProviderChaos6;
import com.astryxion.chaospersists.world.ore.ChunkOreGenerator;
import com.astryxion.chaospersists.world.ore.OreAmethyst;
import com.astryxion.chaospersists.world.ore.OreBasicStone;
import com.astryxion.chaospersists.world.ore.OreCrystal;
import com.astryxion.chaospersists.world.ore.OreCrystalCrystal;
import com.astryxion.chaospersists.world.ore.OreGenericEgg;
import com.astryxion.chaospersists.world.ore.OreRuby;
import com.astryxion.chaospersists.world.ore.OreSalt;
import com.astryxion.chaospersists.world.ore.OreTitanium;
import com.astryxion.chaospersists.world.ore.OreUranium;

@Mod("chaospersists")
public class ChaosPersists
{
  public static com.astryxion.chaospersists.proxy.CommonProxyChaos proxy;
  public static ChaosPersists instance;
  private static final Logger LOGGER = LogManager.getLogger(ChaosPersists.class);
  private FMLCommonSetupEvent commonSetupEvent;
  private boolean chaosContentPrepared = false;
  private boolean chaosRegistryRegistered = false;
  private boolean chaosTileEntitiesRegistered = false;
  private boolean chaosContainersRegistered = false;

  public ChaosPersists() {
    instance = this;
    proxy = DistExecutor.runForDist(() -> com.astryxion.chaospersists.proxy.ClientProxyChaos::new, () -> com.astryxion.chaospersists.proxy.CommonProxyChaos::new);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, this::onRegisterBlocks);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(net.minecraft.tileentity.TileEntityType.class, this::onRegisterTileEntities);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(net.minecraft.inventory.container.ContainerType.class, this::onRegisterContainers);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Biome.class, this::onRegisterBiomes);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(EntityType.class, this::onRegisterEntities);
    net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onEntityAttributeCreation);
    MinecraftForge.EVENT_BUS.addListener(this::onServerStarting);
    MinecraftForge.EVENT_BUS.register(this);
  }
  public static com.astryxion.chaospersists.util.KeyHandler MyKeyhandler = null;
  public static int flyup_keystate = 0;

  public static int BaseBlockID = 2700;
  public static int BaseItemID = 9000;
  public static int BaseBiomeID = 120;
  public static int BaseDimensionID = 80;

  /** When true, logs resolved dimension numeric IDs at startup (see chaospersistsIDS). */
  public static boolean LogRegisteredDimensionIds = true;

  public static int BiomeUtopiaID = 0;
  public static int BiomeIslandsID = 0;
  public static int BiomeCrystalID = 0;
  public static int BiomeVillageID = 0;
  public static int BiomeChaosID = 0;
  public static int BiomeMiningID = 0;
  public static Biome UTOPIA_BIOME = null;
  public static Biome VILLAGE_BIOME = null;
  public static Biome DANGER_BIOME = null;
  public static Biome CRYSTAL_BIOME = null;
  public static Biome CHAOS_BIOME = null;
  public static Biome MINING_BIOME = null;
  private static final java.util.Map<Integer, DimensionType> chaosDimensionTypeByLegacyId = new java.util.HashMap<Integer, DimensionType>();
  public static int DimensionID = 0;
  public static int DimensionID2 = 0;
  public static int DimensionID3 = 0;
  public static int DimensionID4 = 0;
  public static int DimensionID5 = 0;
  public static int DimensionID6 = 0;

  private int nextEntityId = 0;

  /** Returns DimensionID for dimension index 1-6 (1=main chaospersists, 2-6=other dimensions). */
  public static int getDimension() { return DimensionID; }
  public static int getDimension(int n) {
    switch (n) {
      case 2: return DimensionID2;
      case 3: return DimensionID3;
      case 4: return DimensionID4;
      case 5: return DimensionID5;
      case 6: return DimensionID6;
      default: return DimensionID;
    }
  }

  public static ResourceLocation getDimensionRegistryName(int index) {
    return new ResourceLocation("chaospersists", index <= 1 ? "chaospersists" : "chaospersists" + index);
  }

  public static ServerWorld getServerWorldForDimensionIndex(MinecraftServer server, int index) {
    if (server == null) {
      return null;
    }
    ResourceLocation dimId = getDimensionRegistryName(index);
    RegistryKey<World> key = RegistryKey.create(Registry.DIMENSION_REGISTRY, dimId);
    ServerWorld world = server.getLevel(key);
    if (world != null) {
      return world;
    }
    for (ServerWorld loaded : server.getAllLevels()) {
      if (loaded.dimension().location().equals(dimId)) {
        return loaded;
      }
    }
    return null;
  }

  /** Logs whether each Chaos dimension world was created on the server (datapack registration). */
  public static void logDimensionWorldAvailability(MinecraftServer server) {
    if (server == null) {
      return;
    }
    String[] labels = { "Utopia", "Mining", "Village Mania", "Danger", "Crystal", "Chaos" };
    for (int i = 1; i <= 6; i++) {
      ResourceLocation dimId = getDimensionRegistryName(i);
      ServerWorld world = getServerWorldForDimensionIndex(server, i);
      if (world != null) {
        LOGGER.info("ChaosPersists dimension ready: {} ({})", labels[i - 1], dimId);
      } else {
        LOGGER.warn(
            "ChaosPersists dimension missing: {} ({}). Create a new world after updating the mod, or check logs for datapack errors.",
            labels[i - 1],
            dimId);
      }
    }
  }

  public static Enchantment legacyEnchantment(int legacyId) {
    switch (legacyId) {
      case 0: return Enchantments.ALL_DAMAGE_PROTECTION;
      case 1: return Enchantments.FIRE_PROTECTION;
      case 2: return Enchantments.FALL_PROTECTION;
      case 3: return Enchantments.BLAST_PROTECTION;
      case 4: return Enchantments.PROJECTILE_PROTECTION;
      case 5: return Enchantments.RESPIRATION;
      case 6: return Enchantments.AQUA_AFFINITY;
      case 16: return Enchantments.SHARPNESS;
      case 18: return Enchantments.BANE_OF_ARTHROPODS;
      case 19: return Enchantments.KNOCKBACK;
      case 20: return Enchantments.FIRE_ASPECT;
      case 21: return Enchantments.MOB_LOOTING;
      case 32: return Enchantments.BLOCK_EFFICIENCY;
      case 34: return Enchantments.UNBREAKING;
      case 35: return Enchantments.BLOCK_FORTUNE;
      default: return null;
    }
  }

  public static void enchantItemStack(ItemStack stack, Enchantment enchantment, int level) {
    if (stack == null || enchantment == null || level <= 0) {
      return;
    }
    Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
    enchantments.put(enchantment, level);
    EnchantmentHelper.setEnchantments(enchantments, stack);
  }

  private static final java.util.List<PendingChaosSpawn> pendingChaosSpawns = new java.util.ArrayList<PendingChaosSpawn>();

  private static final class PendingChaosSpawn {
    private final EntityType<?> entityType;
    private final int weight;
    private final int minCount;
    private final int maxCount;
    private final EntityClassification classification;
    private final RegistryKey<Biome> biome;

    private PendingChaosSpawn(EntityType<?> entityType, int weight, int minCount, int maxCount, EntityClassification classification, RegistryKey<Biome> biome) {
      this.entityType = entityType;
      this.weight = weight;
      this.minCount = minCount;
      this.maxCount = maxCount;
      this.classification = classification;
      this.biome = biome;
    }
  }

  public static void addChaosSpawn(EntityType<?> entityType, int weight, int minCount, int maxCount, EntityClassification classification, RegistryKey<Biome> biome) {
    if (entityType != null && biome != null) {
      pendingChaosSpawns.add(new PendingChaosSpawn(entityType, weight, minCount, maxCount, classification, biome));
    }
  }

  static void applyPendingChaosSpawns(BiomeLoadingEvent event) {
    RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
    for (PendingChaosSpawn pending : pendingChaosSpawns) {
      if (pending.biome.equals(biomeKey)) {
        event.getSpawns().getSpawner(pending.classification).add(new MobSpawnInfo.Spawners(pending.entityType, pending.weight, pending.minCount, pending.maxCount));
      }
    }
  }

  public static int getDimensionId(World world) {
    if (world == null) {
      return 0;
    }
    RegistryKey<World> dim = world.dimension();
    if (dim == World.OVERWORLD) {
      return 0;
    }
    if (dim.location().equals(getDimensionRegistryName(1))) {
      return getDimension();
    }
    if (dim.location().equals(getDimensionRegistryName(2))) {
      return getDimension(2);
    }
    if (dim.location().equals(getDimensionRegistryName(3))) {
      return getDimension(3);
    }
    if (dim.location().equals(getDimensionRegistryName(4))) {
      return getDimension(4);
    }
    if (dim.location().equals(getDimensionRegistryName(5))) {
      return getDimension(5);
    }
    if (dim.location().equals(getDimensionRegistryName(6))) {
      return getDimension(6);
    }
    return 0;
  }

  public static ServerWorld getServerWorldByDimensionId(int dimensionTypeId) {
    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    if (server == null) {
      return null;
    }
    if (dimensionTypeId == 0) {
      return server.getLevel(World.OVERWORLD);
    }
    if (dimensionTypeId == getDimension()) {
      return getServerWorldForDimensionIndex(server, 1);
    }
    if (dimensionTypeId == getDimension(2)) {
      return getServerWorldForDimensionIndex(server, 2);
    }
    if (dimensionTypeId == getDimension(3)) {
      return getServerWorldForDimensionIndex(server, 3);
    }
    if (dimensionTypeId == getDimension(4)) {
      return getServerWorldForDimensionIndex(server, 4);
    }
    if (dimensionTypeId == getDimension(5)) {
      return getServerWorldForDimensionIndex(server, 5);
    }
    if (dimensionTypeId == getDimension(6)) {
      return getServerWorldForDimensionIndex(server, 6);
    }
    return null;
  }

  public static Block legacyStainedHardenedClay(int meta) {
    switch (meta) {
      case 0: return Blocks.WHITE_TERRACOTTA;
      case 1: return Blocks.ORANGE_TERRACOTTA;
      case 2: return Blocks.MAGENTA_TERRACOTTA;
      case 3: return Blocks.LIGHT_BLUE_TERRACOTTA;
      case 4: return Blocks.YELLOW_TERRACOTTA;
      case 5: return Blocks.LIME_TERRACOTTA;
      case 6: return Blocks.PINK_TERRACOTTA;
      case 7: return Blocks.GRAY_TERRACOTTA;
      case 8: return Blocks.LIGHT_GRAY_TERRACOTTA;
      case 9: return Blocks.CYAN_TERRACOTTA;
      case 10: return Blocks.PURPLE_TERRACOTTA;
      case 11: return Blocks.BLUE_TERRACOTTA;
      case 12: return Blocks.BROWN_TERRACOTTA;
      case 13: return Blocks.GREEN_TERRACOTTA;
      case 14: return Blocks.RED_TERRACOTTA;
      case 15: return Blocks.BLACK_TERRACOTTA;
      default: return Blocks.WHITE_TERRACOTTA;
    }
  }

  public static int godzilla_has_spawned = 0;
  public static int current_dimension = 0;
  public static int valentines_day = 0;
  public static int easter_day = 0;
  public static int ultimate_sword_pvp = 0;
  public static int big_bertha_pvp = 0;
  public static int bro_mode = 0;
  public static int enableduplicatortree = 1;
  public static int RoyalGlideEnable = 1;
  public static int DragonflyHorseFriendly = 0;
  public static int PlayNicely = 0;
  public static int MinersDreamExpensive = 0;
  public static int DisableOverworldDungeons = 0;
  public static int FullPowerKingEnable = 0;

  public static ArmorStats Amethyst_armorstats = null;
  public static ArmorStats Emerald_armorstats = null;
  public static ArmorStats Experience_armorstats = null;
  public static ArmorStats MothScale_armorstats = null;
  public static ArmorStats LavaEel_armorstats = null;
  public static ArmorStats Ultimate_armorstats = null;
  public static ArmorStats Pink_armorstats = null;
  public static ArmorStats TigersEye_armorstats = null;
  public static ArmorStats Peacock_armorstats = null;
  public static ArmorStats Mobzilla_armorstats = null;
  public static ArmorStats Ruby_armorstats = null;
  public static ArmorStats Royal_armorstats = null;
  public static ArmorStats Lapis_armorstats = null;
  public static ArmorStats Queen_armorstats = null;

  public static int AllMobsDisable = 0;
  public static int MosquitoEnable = 1;
  public static int RockEnable = 1;
  public static int GhostEnable = 1;
  public static int GhostSkellyEnable = 1;
  public static int SpiderDriverEnable = 1;
  public static int JefferyEnable = 1;
  public static int MothraEnable = 1;
  public static int BrutalflyEnable = 1;
  public static int NastysaurusEnable = 1;
  public static int PointysaurusEnable = 1;
  public static int CricketEnable = 1;
  public static int FrogEnable = 1;
  public static int MothraPeaceful = 0;
  public static int BlackAntEnable = 1;
  public static int RedAntEnable = 1;
  public static int TermiteEnable = 1;
  public static int UnstableAntEnable = 1;
  public static int RainbowAntEnable = 1;
  public static int AlosaurusEnable = 1;
  public static int HammerheadEnable = 1;
  public static int LeonEnable = 1;
  public static int CaterKillerEnable = 1;
  public static int MolenoidEnable = 1;
  public static int TRexEnable = 1;
  public static int CriminalEnable = 1;
  public static int CryolophosaurusEnable = 1;
  public static int RatEnable = 1;
  public static int RatPlayerFriendly = 0;
  public static int RatPetFriendly = 0;
  public static int UrchinEnable = 1;
  public static int CamarasaurusEnable = 1;
  public static int ChipmunkEnable = 1;
  public static int OstrichEnable = 1;
  public static int GazelleEnable = 1;
  public static int VelocityRaptorEnable = 1;
  public static int HydroliscEnable = 1;
  public static int SpyroEnable = 1;
  public static int BaryonyxEnable = 1;
  public static int CockateilEnable = 1;
  public static int CassowaryEnable = 1;
  public static int EasterBunnyEnable = 1;
  public static int PeacockEnable = 1;
  public static int KyuubiEnable = 1;
  public static int CephadromeEnable = 1;
  public static int DragonEnable = 1;
  public static int GammaMetroidEnable = 1;
  public static int BasiliskEnable = 1;
  public static int DragonflyEnable = 1;
  public static int EmperorScorpionEnable = 1;
  public static int TrooperBugEnable = 1;
  public static int SpitBugEnable = 1;
  public static int StinkBugEnable = 1;
  public static int ScorpionEnable = 1;
  public static int CaveFisherEnable = 1;
  public static int AlienEnable = 1;
  public static int WaterDragonEnable = 1;
  public static int SeaMonsterEnable = 1;
  public static int SeaViperEnable = 1;
  public static int AttackSquidEnable = 1;
  public static int GodzillaEnable = 1;
  public static int LessOre = 0;
  public static int LessLag = 0;
  public static int Robot1Enable = 1;
  public static int Robot2Enable = 1;
  public static int Robot3Enable = 1;
  public static int Robot4Enable = 1;
  public static int Robot5Enable = 1;
  public static int RotatorEnable = 1;
  public static int VortexEnable = 1;
  public static int DungeonBeastEnable = 1;
  public static int KrakenEnable = 1;
  public static int LizardEnable = 1;
  public static int RubberDuckyEnable = 1;
  public static int GirlfriendEnable = 1;
  public static int BoyfriendEnable = 0;
  public static int FireflyEnable = 1;
  public static int FairyEnable = 1;
  public static int BeeEnable = 1;
  public static int TheKingEnable = 1;
  public static int TheQueenEnable = 1;
  public static int ThePrinceEnable = 1;
  public static int ThePrincessEnable = 1;
  public static int MantisEnable = 1;
  public static int StinkyEnable = 1;
  public static int HerculesBeetleEnable = 1;
  public static int CowEnable = 1;
  public static int ButterflyEnable = 1;
  public static int MothEnable = 1;
  public static int TshirtEnable = 1;
  public static int CoinEnable = 1;
  public static int CreepingHorrorEnable = 1;
  public static int TerribleTerrorEnable = 1;
  public static int CliffRacerEnable = 1;
  public static int TriffidEnable = 1;
  public static int UltimateSwordMagic = 10;
  public static int UltimateBowDamage = 10;
  public static int PitchBlackEnable = 1;
  public static int NightmareSize = 0;
  public static int LurkingTerrorEnable = 1;
  public static int IslandSpeedFactor = 2;
  public static int IslandSizeFactor = 2;
  public static int GinormousEmeraldTreeEnable = 1;
  public static int GuiOverlayEnable = 1;
  public static int FastGraphicsLeaves = 0;
  public static int WormEnable = 1;
  public static int CloudSharkEnable = 1;
  public static int GoldFishEnable = 1;
  public static int LeafMonsterEnable = 1;
  public static int EnderKnightEnable = 1;
  public static int EnderReaperEnable = 1;
  public static int BeaverEnable = 1;
  public static int IrukandjiEnable = 1;
  public static int SkateEnable = 1;
  public static int WhaleEnable = 1;
  public static int FlounderEnable = 1;
  public static int CrabEnable = 1;

  public ChaosWorld chaospersistsGen = new ChaosWorld();
  public static Random ChaosRand = new Random(151L);
  public static Trees chaospersistsTrees = null;
  public static BasiliskMaze BMaze;
  public static RubyBirdDungeon RubyDungeon;
  public static GenericDungeon MyDungeon;
  public static MyUtils chaospersistsUtils;
  public static ChunkOreGenerator Chunker;
  public static OreGenericEgg MySpiderSpawnBlock;
  public static OreGenericEgg MyBatSpawnBlock;
  public static OreGenericEgg MyCowSpawnBlock;
  public static OreGenericEgg MyPigSpawnBlock;
  public static OreGenericEgg MySquidSpawnBlock;
  public static OreGenericEgg MyChickenSpawnBlock;
  public static OreGenericEgg MyCreeperSpawnBlock;
  public static OreGenericEgg MySkeletonSpawnBlock;
  public static OreGenericEgg MyZombieSpawnBlock;
  public static OreGenericEgg MySlimeSpawnBlock;
  public static OreGenericEgg MyGhastSpawnBlock;
  public static OreGenericEgg MyZombiePigmanSpawnBlock;
  public static OreGenericEgg MyEndermanSpawnBlock;
  public static OreGenericEgg MyCaveSpiderSpawnBlock;
  public static OreGenericEgg MySilverfishSpawnBlock;
  public static OreGenericEgg MyMagmaCubeSpawnBlock;
  public static OreGenericEgg MyWitchSpawnBlock;
  public static OreGenericEgg MySheepSpawnBlock;
  public static OreGenericEgg MyWolfSpawnBlock;
  public static OreGenericEgg MyMooshroomSpawnBlock;
  public static OreGenericEgg MyOcelotSpawnBlock;
  public static OreGenericEgg MyBlazeSpawnBlock;
  public static OreGenericEgg MyWitherSkeletonSpawnBlock;
  public static OreGenericEgg MyEnderDragonSpawnBlock;
  public static OreGenericEgg MySnowGolemSpawnBlock;
  public static OreGenericEgg MyIronGolemSpawnBlock;
  public static OreGenericEgg MyWitherBossSpawnBlock;
  public static OreGenericEgg MyGirlfriendSpawnBlock;
  public static OreGenericEgg MyBoyfriendSpawnBlock;
  public static OreGenericEgg MyRedCowSpawnBlock;
  public static OreGenericEgg MyCrystalCowSpawnBlock;
  public static OreGenericEgg MyVillagerSpawnBlock;
  public static OreGenericEgg MyGoldCowSpawnBlock;
  public static OreGenericEgg MyEnchantedCowSpawnBlock;
  public static OreGenericEgg MyMOTHRASpawnBlock;
  public static OreGenericEgg MyAloSpawnBlock;
  public static OreGenericEgg MyCryoSpawnBlock;
  public static OreGenericEgg MyCamaSpawnBlock;
  public static OreGenericEgg MyVeloSpawnBlock;
  public static OreGenericEgg MyHydroSpawnBlock;
  public static OreGenericEgg MyBasilSpawnBlock;
  public static OreGenericEgg MyDragonflySpawnBlock;
  public static OreGenericEgg MyEmperorScorpionSpawnBlock;
  public static OreGenericEgg MyScorpionSpawnBlock;
  public static OreGenericEgg MyCaveFisherSpawnBlock;
  public static OreGenericEgg MySpyroSpawnBlock;
  public static OreGenericEgg MyBaryonyxSpawnBlock;
  public static OreGenericEgg MyGammaMetroidSpawnBlock;
  public static OreGenericEgg MyCockateilSpawnBlock;
  public static OreGenericEgg MyKyuubiSpawnBlock;
  public static OreGenericEgg MyAlienSpawnBlock;
  public static OreGenericEgg MyAttackSquidSpawnBlock;
  public static OreGenericEgg MyWaterDragonSpawnBlock;
  public static OreGenericEgg MyKrakenSpawnBlock;
  public static OreGenericEgg MyLizardSpawnBlock;
  public static OreGenericEgg MyCephadromeSpawnBlock;
  public static OreGenericEgg MyDragonSpawnBlock;
  public static OreGenericEgg MyBeeSpawnBlock;
  public static OreGenericEgg MyHorseSpawnBlock;
  public static OreGenericEgg MyTrooperBugSpawnBlock;
  public static OreGenericEgg MySpitBugSpawnBlock;
  public static OreGenericEgg MyStinkBugSpawnBlock;
  public static OreGenericEgg MyOstrichSpawnBlock;
  public static OreGenericEgg MyGazelleSpawnBlock;
  public static OreGenericEgg MyChipmunkSpawnBlock;
  public static OreGenericEgg MyCreepingHorrorSpawnBlock;
  public static OreGenericEgg MyTerribleTerrorSpawnBlock;
  public static OreGenericEgg MyCliffRacerSpawnBlock;
  public static OreGenericEgg MyTriffidSpawnBlock;
  public static OreGenericEgg MyPitchBlackSpawnBlock;
  public static OreGenericEgg MyLurkingTerrorSpawnBlock;
  public static OreGenericEgg MyGodzillaPartSpawnBlock;
  public static OreGenericEgg MyGodzillaSpawnBlock;
  public static OreGenericEgg MyTheKingPartSpawnBlock;
  public static OreGenericEgg MyTheQueenPartSpawnBlock;
  public static OreGenericEgg MyTheKingSpawnBlock;
  public static OreGenericEgg MyTheQueenSpawnBlock;
  public static OreGenericEgg MySmallWormSpawnBlock;
  public static OreGenericEgg MyMediumWormSpawnBlock;
  public static OreGenericEgg MyLargeWormSpawnBlock;
  public static OreGenericEgg MyCassowarySpawnBlock;
  public static OreGenericEgg MyCloudSharkSpawnBlock;
  public static OreGenericEgg MyGoldFishSpawnBlock;
  public static OreGenericEgg MyLeafMonsterSpawnBlock;
  public static OreGenericEgg MyTshirtSpawnBlock;
  public static OreGenericEgg MyEnderKnightSpawnBlock;
  public static OreGenericEgg MyEnderReaperSpawnBlock;
  public static OreGenericEgg MyBeaverSpawnBlock;
  public static OreGenericEgg MyUrchinSpawnBlock;
  public static OreGenericEgg MyFlounderSpawnBlock;
  public static OreGenericEgg MySkateSpawnBlock;
  public static OreGenericEgg MyRotatorSpawnBlock;
  public static OreGenericEgg MyPeacockSpawnBlock;
  public static OreGenericEgg MyFairySpawnBlock;
  public static OreGenericEgg MyDungeonBeastSpawnBlock;
  public static OreGenericEgg MyVortexSpawnBlock;
  public static OreGenericEgg MyRatSpawnBlock;
  public static OreGenericEgg MyWhaleSpawnBlock;
  public static OreGenericEgg MyIrukandjiSpawnBlock;
  public static OreGenericEgg MyTRexSpawnBlock;
  public static OreGenericEgg MyHerculesSpawnBlock;
  public static OreGenericEgg MyMantisSpawnBlock;
  public static OreGenericEgg MyStinkySpawnBlock;
  public static OreGenericEgg MyEasterBunnySpawnBlock;
  public static OreGenericEgg MyCaterKillerSpawnBlock;
  public static OreGenericEgg MyMolenoidSpawnBlock;
  public static OreGenericEgg MySeaMonsterSpawnBlock;
  public static OreGenericEgg MySeaViperSpawnBlock;
  public static OreGenericEgg MyLeonSpawnBlock;
  public static OreGenericEgg MyHammerheadSpawnBlock;
  public static OreGenericEgg MyRubberDuckySpawnBlock;
  public static OreGenericEgg MyCriminalSpawnBlock;
  public static OreGenericEgg MyBrutalflySpawnBlock;
  public static OreGenericEgg MyNastysaurusSpawnBlock;
  public static OreGenericEgg MyPointysaurusSpawnBlock;
  public static OreGenericEgg MyCricketSpawnBlock;
  public static OreGenericEgg MyFrogSpawnBlock;
  public static OreGenericEgg MySpiderDriverSpawnBlock;
  public static OreGenericEgg MyCrabSpawnBlock;
  public static Block MyOreUraniumBlock;
  public static Block MyOreTitaniumBlock;
  public static Item MyIngotUranium;
  public static Item MyIngotTitanium;
  public static Block MyBlockUraniumBlock;
  public static Block MyBlockTitaniumBlock;
  public static Block MyBlockMobzillaScaleBlock;
  public static Block MyBlockRubyBlock;
  public static Block MyBlockAmethystBlock;
  public static Block MyLavafoamBlock;
  public static Block MyPizzaBlock;
  public static Item MyPizzaItem;
  public static Block MyDuctTapeBlock;
  public static Item MyDuctTapeItem;
  public static Block MyAntBlock;
  public static Block MyRedAntBlock;
  public static Block TermiteBlock;
  public static Block CrystalTermiteBlock;
  public static Block MyRainbowAntBlock;
  public static Block MyUnstableAntBlock;
  public static Block MyFlowerPinkBlock;
  public static Block MyFlowerBlueBlock;
  public static Block MyFlowerBlackBlock;
  public static Block MyFlowerScaryBlock;
  public static Block CrystalFlowerRedBlock;
  public static Block CrystalFlowerGreenBlock;
  public static Block CrystalFlowerBlueBlock;
  public static Block CrystalFlowerYellowBlock;
  public static Block CrystalPlanksBlock;
  public static Block CrystalWorkbenchBlock;
  public static CrystalFurnace CrystalFurnaceBlock;
  public static Item MyUltimateSword;
  public static Item MyUltimatePickaxe;
  public static Item MyUltimateShovel;
  public static Item MyUltimateHoe;
  public static Item MyUltimateAxe;
  public static Item MyNightmareSword;
  public static Item MyBertha;
  public static Item MyHammy;
  public static Item MyBattleAxe;
  public static Item MyQueenBattleAxe;
  public static Item MyChainsaw;
  public static Item MySquidZooka;
  public static Item MySlice;
  public static Item MyRoyal;
  public static Item MyEmeraldSword;
  public static Item MyEmeraldPickaxe;
  public static Item MyEmeraldShovel;
  public static Item MyEmeraldHoe;
  public static Item MyEmeraldAxe;
  public static Item MyExperienceSword;
  public static Item MyPoisonSword;
  public static Item MyRatSword;
  public static Item MyFairySword;
  public static Item MyMantisClaw;
  public static Item MyBigHammer;
  public static Item MyRubySword;
  public static Item MyRubyPickaxe;
  public static Item MyRubyShovel;
  public static Item MyRubyHoe;
  public static Item MyRubyAxe;
  public static Item MyAmethystSword;
  public static Item MyAmethystPickaxe;
  public static Item MyAmethystShovel;
  public static Item MyAmethystHoe;
  public static Item MyAmethystAxe;
  public static Item MyRoseSword;
  static IItemTier toolULTIMATE;
  static IItemTier toolNIGHTMARE;
  static IItemTier toolBERTHA;
  static IItemTier toolCRYSTALWOOD;
  static IItemTier toolCRYSTALSTONE;
  static IItemTier toolCRYSTALPINK;
  static IItemTier toolTIGERSEYE;
  static IItemTier toolRUBY;
  static IItemTier toolAMETHYST;
  static IItemTier toolEMERALD;
  static IItemTier toolROYAL;
  static IItemTier toolHAMMY;
  static IItemTier toolBATTLE;
  static IItemTier toolCHAINSAW;
  static IItemTier toolQUEENBATTLE;
  public static WeaponStats ultimate_stats = null;
  public static WeaponStats nightmare_stats = null;
  public static WeaponStats bertha_stats = null;
  public static WeaponStats crystalwood_stats = null;
  public static WeaponStats crystalstone_stats = null;
  public static WeaponStats crystalpink_stats = null;
  public static WeaponStats tigerseye_stats = null;
  public static WeaponStats ruby_stats = null;
  public static WeaponStats amethyst_stats = null;
  public static WeaponStats emerald_stats = null;
  public static WeaponStats royal_stats = null;
  public static WeaponStats hammy_stats = null;
  public static WeaponStats battleaxe_stats = null;
  public static WeaponStats queenbattleaxe_stats = null;
  public static WeaponStats chainsaw_stats = null;
  public static Item MyCrystalWoodSword;
  public static Item MyCrystalWoodPickaxe;
  public static Item MyCrystalWoodShovel;
  public static Item MyCrystalWoodHoe;
  public static Item MyCrystalWoodAxe;
  public static Item MyCrystalPinkSword;
  public static Item MyCrystalPinkPickaxe;
  public static Item MyCrystalPinkShovel;
  public static Item MyCrystalPinkHoe;
  public static Item MyCrystalPinkAxe;
  public static Item MyTigersEyeSword;
  public static Item MyTigersEyePickaxe;
  public static Item MyTigersEyeShovel;
  public static Item MyTigersEyeHoe;
  public static Item MyTigersEyeAxe;
  public static Item MyCrystalStoneSword;
  public static Item MyCrystalStonePickaxe;
  public static Item MyCrystalStoneShovel;
  public static Item MyCrystalStoneHoe;
  public static Item MyCrystalStoneAxe;
  public static Item MyCrystalPinkIngot;
  public static Block MyCrystalPinkBlock;
  public static Item MyTigersEyeIngot;
  public static Block MyTigersEyeBlock;
  public static Item MyItemShoes;
  public static Item MyItemShoes_1;
  public static Item MyItemShoes_2;
  public static Item MyItemShoes_3;
  public static Item MyItemGameController;
  public static Item MyUltimateBow;
  public static Item MySkateBow;
  public static Item MyUltimateFishingRod;
  public static ItemStack UltimateFishingRod;
  public static Item MyFireFish;
  public static Item MySunFish;
  public static Item MyLavaEel;
  public static Item MyMothScale;
  public static Item MyQueenScale;
  public static Item MyNightmareScale;
  public static Item MyEmperorScorpionScale;
  public static Item MyBasiliskScale;
  public static Item MyWaterDragonScale;
  public static Item MyJumpyBugScale;
  public static Item MyKrakenTooth;
  public static Item MyGodzillaScale;
  public static Item GreenGoo;
  public static Item SpiderRobotKit;
  public static Item AntRobotKit;
  public static Item ZooKeeper;
  public static Item CreeperLauncher;
  public static Item NetherLost;
  public static Item CrystalSticks;
  public static Item Sifter;
  public static Item MySunspotUrchin;
  public static Item MySparkFish;
  public static Item MyWaterBall;
  public static Item MyLaserBall;
  public static Item MyRayGun;
  public static Item MyThunderStaff;
  public static Item MyWrench;
  public static Item MyIceBall;
  public static Item MySmallRock;
  public static Item MyRock;
  public static Item MyRedRock;
  public static Item MyCrystalRedRock;
  public static Item MyCrystalGreenRock;
  public static Item MyCrystalBlueRock;
  public static Item MyCrystalTNTRock;
  public static Item MyBlueRock;
  public static Item MyGreenRock;
  public static Item MyPurpleRock;
  public static Item MySpikeyRock;
  public static Item MyTNTRock;
  public static Item MyAcid;
  public static Item MyIrukandji;
  public static Item MyIrukandjiArrow;
  public static Item MyGreenFish;
  public static Item MyBlueFish;
  public static Item MyPinkFish;
  public static Item MyRockFish;
  public static Item MyWoodFish;
  public static Item MyGreyFish;
  public static Item BerthaHandle;
  public static Item BerthaGuard;
  public static Item BerthaBlade;
  public static Item MolenoidNose;
  public static Item SeaMonsterScale;
  public static Item WormTooth;
  public static Item TRexTooth;
  public static Item CaterKillerJaw;
  public static Item SeaViperTongue;
  public static Item VortexEye;
  public static Item MyStepUp;
  public static Item MyStepDown;
  public static Item MyStepAccross;
  public static IArmorMaterial armorULTIMATE;
  public static IArmorMaterial armorMOBZILLA;
  public static IArmorMaterial armorLAVAEEL;
  public static IArmorMaterial armorMOTHSCALE;
  public static IArmorMaterial armorEMERALD;
  public static IArmorMaterial armorEXPERIENCE;
  public static IArmorMaterial armorRUBY;
  public static IArmorMaterial armorAMETHYST;
  public static IArmorMaterial armorPINK;
  public static IArmorMaterial armorTIGERSEYE;
  public static IArmorMaterial armorPEACOCK;
  public static IArmorMaterial armorROYAL;
  public static IArmorMaterial armorLAPIS;
  public static IArmorMaterial armorQUEEN;
  public static ItemChaosArmor UltimateHelmet;
  public static ItemChaosArmor UltimateBody;
  public static ItemChaosArmor UltimateLegs;
  public static ItemChaosArmor UltimateBoots;
  public static ItemChaosArmor LavaEelHelmet;
  public static ItemChaosArmor LavaEelBody;
  public static ItemChaosArmor LavaEelLegs;
  public static ItemChaosArmor LavaEelBoots;
  public static ItemChaosArmor MothScaleHelmet;
  public static ItemChaosArmor MothScaleBody;
  public static ItemChaosArmor MothScaleLegs;
  public static ItemChaosArmor MothScaleBoots;
  public static ItemChaosArmor EmeraldHelmet;
  public static ItemChaosArmor EmeraldBody;
  public static ItemChaosArmor EmeraldLegs;
  public static ItemChaosArmor EmeraldBoots;
  public static ItemChaosArmor ExperienceHelmet;
  public static ItemChaosArmor ExperienceBody;
  public static ItemChaosArmor ExperienceLegs;
  public static ItemChaosArmor ExperienceBoots;
  public static ItemChaosArmor RubyHelmet;
  public static ItemChaosArmor RubyBody;
  public static ItemChaosArmor RubyLegs;
  public static ItemChaosArmor RubyBoots;
  public static ItemChaosArmor AmethystHelmet;
  public static ItemChaosArmor AmethystBody;
  public static ItemChaosArmor AmethystLegs;
  public static ItemChaosArmor AmethystBoots;
  public static ItemChaosArmor CrystalPinkHelmet;
  public static ItemChaosArmor CrystalPinkBody;
  public static ItemChaosArmor CrystalPinkLegs;
  public static ItemChaosArmor CrystalPinkBoots;
  public static ItemChaosArmor TigersEyeHelmet;
  public static ItemChaosArmor TigersEyeBody;
  public static ItemChaosArmor TigersEyeLegs;
  public static ItemChaosArmor TigersEyeBoots;
  public static Block TigersEye;
  public static ItemChaosArmor PeacockFeatherBoots;
  public static ItemChaosArmor PeacockFeatherHelmet;
  public static ItemChaosArmor PeacockFeatherBody;
  public static ItemChaosArmor PeacockFeatherLegs;
  public static ItemChaosArmor MobzillaHelmet;
  public static ItemChaosArmor MobzillaBody;
  public static ItemChaosArmor MobzillaLegs;
  public static ItemChaosArmor MobzillaBoots;
  public static ItemChaosArmor RoyalHelmet;
  public static ItemChaosArmor RoyalBody;
  public static ItemChaosArmor RoyalLegs;
  public static ItemChaosArmor RoyalBoots;
  public static ItemChaosArmor LapisHelmet;
  public static ItemChaosArmor LapisBody;
  public static ItemChaosArmor LapisLegs;
  public static ItemChaosArmor LapisBoots;
  public static ItemChaosArmor QueenHelmet;
  public static ItemChaosArmor QueenBody;
  public static ItemChaosArmor QueenLegs;
  public static ItemChaosArmor QueenBoots;
  public static Block MyOreSaltBlock;
  public static Block MyRTPBlock;
  public static Block MyMoleDirtBlock;
  public static Item MySalt;
  public static Item MyPopcorn;
  public static Item MyButteredPopcorn;
  public static Item MyButteredSaltedPopcorn;
  public static Item MyPopcornBag;
  public static Item MyButter;
  public static Item MyCornDog;
  public static Item MyRawCornDog;
  public static Item MyPeacock;
  public static Item MyRawPeacock;
  public static Item MyElevator;
  public static Block MyOreRubyBlock;
  public static Item MyRuby;
  public static Item MyBacon;
  public static Item MyRawBacon;
  public static Item MyCrabMeat;
  public static Item MyRawCrabMeat;
  public static Item MyButterCandy;
  public static Block MyOreAmethystBlock;
  public static Item MyAmethyst;
  public static Item UraniumNugget;
  public static Item TitaniumNugget;
  public static Item MySalad;
  public static Item MyBLT;
  public static Item MyCrabbyPatty;
  public static Block CrystalStone;
  public static Block CrystalRat;
  public static Block CrystalFairy;
  public static Block CrystalCoal;
  public static Block CrystalGrass;
  public static Block CrystalCrystal;
  public static Block RedAntTroll;
  public static Block TermiteTroll;
  public static Item CageEmpty;
  public static Item CagedSpider;
  public static Item CagedBat;
  public static Item CagedCow;
  public static Item CagedPig;
  public static Item CagedSquid;
  public static Item CagedChicken;
  public static Item CagedCreeper;
  public static Item CagedSkeleton;
  public static Item CagedZombie;
  public static Item CagedSlime;
  public static Item CagedGhast;
  public static Item CagedZombiePigman;
  public static Item CagedEnderman;
  public static Item CagedCaveSpider;
  public static Item CagedSilverfish;
  public static Item CagedMagmaCube;
  public static Item CagedWitch;
  public static Item CagedSheep;
  public static Item CagedWolf;
  public static Item CagedMooshroom;
  public static Item CagedOcelot;
  public static Item CagedBlaze;
  public static Item CagedGirlfriend;
  public static Item CagedBoyfriend;
  public static Item CagedWitherSkeleton;
  public static Item CagedEnderDragon;
  public static Item CagedSnowGolem;
  public static Item CagedIronGolem;
  public static Item CagedWitherBoss;
  public static Item CagedRedCow;
  public static Item CagedCrystalCow;
  public static Item CagedVillager;
  public static Item CagedGoldCow;
  public static Item CagedEnchantedCow;
  public static Item CagedMOTHRA;
  public static Item CagedAlo;
  public static Item CagedCryo;
  public static Item CagedCama;
  public static Item CagedVelo;
  public static Item CagedHydro;
  public static Item CagedBasil;
  public static Item CagedDragonfly;
  public static Item CagedEmperorScorpion;
  public static Item CagedScorpion;
  public static Item CagedCaveFisher;
  public static Item CagedSpyro;
  public static Item CagedBaryonyx;
  public static Item CagedGammaMetroid;
  public static Item CagedCockateil;
  public static Item CagedKyuubi;
  public static Item CagedAlien;
  public static Item CagedAttackSquid;
  public static Item CagedWaterDragon;
  public static Item CagedCephadrome;
  public static Item CagedDragon;
  public static Item CagedKraken;
  public static Item CagedLizard;
  public static Item CagedBee;
  public static Item CagedHorse;
  public static Item CagedFirefly;
  public static Item CagedChipmunk;
  public static Item CagedGazelle;
  public static Item CagedOstrich;
  public static Item CagedTrooper;
  public static Item CagedSpit;
  public static Item CagedStink;
  public static Item CagedCreepingHorror;
  public static Item CagedTerribleTerror;
  public static Item CagedCliffRacer;
  public static Item CagedTriffid;
  public static Item CagedPitchBlack;
  public static Item CagedLurkingTerror;
  public static Item CagedSmallWorm;
  public static Item CagedMediumWorm;
  public static Item CagedLargeWorm;
  public static Item CagedCassowary;
  public static Item CagedCloudShark;
  public static Item CagedGoldFish;
  public static Item CagedLeafMonster;
  public static Item CagedEnderKnight;
  public static Item CagedEnderReaper;
  public static Item CagedBeaver;
  public static Item CagedUrchin;
  public static Item CagedFlounder;
  public static Item CagedSkate;
  public static Item CagedRotator;
  public static Item CagedPeacock;
  public static Item CagedFairy;
  public static Item CagedDungeonBeast;
  public static Item CagedVortex;
  public static Item CagedRat;
  public static Item CagedWhale;
  public static Item CagedIrukandji;
  public static Item CagedTRex;
  public static Item CagedHercules;
  public static Item CagedMantis;
  public static Item CagedStinky;
  public static Item CagedEasterBunny;
  public static Item CagedCaterKiller;
  public static Item CagedMolenoid;
  public static Item CagedSeaMonster;
  public static Item CagedSeaViper;
  public static Item CagedLeon;
  public static Item CagedHammerhead;
  public static Item CagedRubberDucky;
  public static Item CagedCriminal;
  public static Item CagedBrutalfly;
  public static Item CagedNastysaurus;
  public static Item CagedPointysaurus;
  public static Item CagedCricket;
  public static Item CagedFrog;
  public static Item CagedSpiderDriver;
  public static Item CagedCrab;
  public static Item WitherSkeletonEgg;
  public static Item EnderDragonEgg;
  public static Item SnowGolemEgg;
  public static Item IronGolemEgg;
  public static Item WitherBossEgg;
  public static Item GirlfriendEgg;
  public static Item RedCowEgg;
  public static Item CrystalCowEgg;
  public static Item GoldCowEgg;
  public static Item EnchantedCowEgg;
  public static Item MOTHRAEgg;
  public static Item AloEgg;
  public static Item CryoEgg;
  public static Item CamaEgg;
  public static Item VeloEgg;
  public static Item HydroEgg;
  public static Item BasilEgg;
  public static Item DragonflyEgg;
  public static Item EmperorScorpionEgg;
  public static Item ScorpionEgg;
  public static Item CaveFisherEgg;
  public static Item SpyroEgg;
  public static Item BaryonyxEgg;
  public static Item GammaMetroidEgg;
  public static Item CockateilEgg;
  public static Item KyuubiEgg;
  public static Item AlienEgg;
  public static Item AttackSquidEgg;
  public static Item WaterDragonEgg;
  public static Item CephadromeEgg;
  public static Item DragonEgg;
  public static Item KrakenEgg;
  public static Item LizardEgg;
  public static Item BeeEgg;
  public static Item TrooperBugEgg;
  public static Item SpitBugEgg;
  public static Item StinkBugEgg;
  public static Item OstrichEgg;
  public static Item GazelleEgg;
  public static Item ChipmunkEgg;
  public static Item CreepingHorrorEgg;
  public static Item TerribleTerrorEgg;
  public static Item CliffRacerEgg;
  public static Item TriffidEgg;
  public static Item PitchBlackEgg;
  public static Item LurkingTerrorEgg;
  public static Item GodzillaEgg;
  public static Item SmallWormEgg;
  public static Item MediumWormEgg;
  public static Item LargeWormEgg;
  public static Item CassowaryEgg;
  public static Item CloudSharkEgg;
  public static Item GoldFishEgg;
  public static Item LeafMonsterEgg;
  public static Item TshirtEgg;
  public static Item EnderKnightEgg;
  public static Item EnderReaperEgg;
  public static Item BeaverEgg;
  public static Item RotatorEgg;
  public static Item VortexEgg;
  public static Item PeacockEgg;
  public static Item FairyEgg;
  public static Item DungeonBeastEgg;
  public static Item RatEgg;
  public static Item FlounderEgg;
  public static Item WhaleEgg;
  public static Item IrukandjiEgg;
  public static Item SkateEgg;
  public static Item UrchinEgg;
  public static Item Robot1Egg;
  public static Item Robot2Egg;
  public static Item Robot3Egg;
  public static Item Robot4Egg;
  public static Item GhostEgg;
  public static Item GhostSkellyEgg;
  public static Item BrownAntEgg;
  public static Item RedAntEgg;
  public static Item RainbowAntEgg;
  public static Item UnstableAntEgg;
  public static Item TermiteEgg;
  public static Item ButterflyEgg;
  public static Item MothEgg;
  public static Item MosquitoEgg;
  public static Item FireflyEgg;
  public static Item TRexEgg;
  public static Item HerculesEgg;
  public static Item MantisEgg;
  public static Item StinkyEgg;
  public static Item Robot5Egg;
  public static Item CoinEgg;
  public static Item BoyfriendEgg;
  public static Item TheKingEgg;
  public static Item TheQueenEgg;
  public static Item ThePrinceEgg;
  public static Item EasterBunnyEgg;
  public static Item MolenoidEgg;
  public static Item SeaMonsterEgg;
  public static Item SeaViperEgg;
  public static Item CaterKillerEgg;
  public static Item LeonEgg;
  public static Item HammerheadEgg;
  public static Item RubberDuckyEgg;
  public static Item CriminalEgg;
  public static Item BrutalflyEgg;
  public static Item NastysaurusEgg;
  public static Item PointysaurusEgg;
  public static Item CricketEgg;
  public static Item ThePrincessEgg;
  public static Item FrogEgg;
  public static Item JefferyEgg;
  public static Item AntRobotEgg;
  public static Item SpiderRobotEgg;
  public static Item SpiderDriverEgg;
  public static Item CrabEgg;
  public static Item MyStrawberry;
  public static Item MyCrystalApple;
  public static Item MyLove;
  public static Item MyCheese;
  public static Item MyCherry;
  public static Item MyPeach;
  public static Item MyStrawberrySeed;
  public static Block MyStrawberryPlant;
  public static Item MyButterflySeed;
  public static Block MyButterflyPlant;
  public static Item MyMothSeed;
  public static Block MyMothPlant;
  public static Item MyMosquitoSeed;
  public static Block MyMosquitoPlant;
  public static Item MyFireflySeed;
  public static Block MyFireflyPlant;
  public static Item MyRadish;
  public static Item MyRice;
  public static Block MyRadishPlant;
  public static Block MyRicePlant;
  public static Block MyCornPlant1;
  public static Block MyCornPlant2;
  public static Block MyCornPlant3;
  public static Block MyCornPlant4;
  public static Item MyCornCob;
  public static Block MyQuinoaPlant1;
  public static Block MyQuinoaPlant2;
  public static Block MyQuinoaPlant3;
  public static Block MyQuinoaPlant4;
  public static Item MyQuinoa;
  public static Block MyTomatoPlant1;
  public static Block MyTomatoPlant2;
  public static Block MyTomatoPlant3;
  public static Block MyTomatoPlant4;
  public static Item MyTomato;
  public static Block MyLettucePlant1;
  public static Block MyLettucePlant2;
  public static Block MyLettucePlant3;
  public static Block MyLettucePlant4;
  public static Item MyLettuce;
  public static Item MagicApple;
  public static Item RandomDungeon;
  public static Item MinersDream;
  public static Block ExtremeTorch;
  public static Block MyEnderPearlBlock;
  public static Block MyEyeOfEnderBlock;
  public static Block MyExperiencePlant;
  public static Block KrakenRepellent;
  public static Block MyIslandBlock;
  public static Block CreeperRepellent;
  public static Item ZooCage2;
  public static Item ZooCage4;
  public static Item ZooCage6;
  public static Item ZooCage8;
  public static Item ZooCage10;
  public static Item InstantShelter;
  public static Item InstantGarden;
  public static Block CrystalTorch;
  public static Item MyPeacockFeather;
  public static Block MyKingSpawnerBlock;
  public static Block MyQueenSpawnerBlock;
  public static Block MyDungeonSpawnerBlock;
  public static Block MyCrystalPlant;
  public static Block MyCrystalPlant2;
  public static Block MyCrystalPlant3;
  public static Block MyAppleLeaves;
  public static Item MyAppleSeed;
  public static Item MyCherrySeed;
  public static Item MyPeachSeed;
  public static Block MySkyTreeLog;
  public static Block MyDT;
  public static Block MyExperienceLeaves;
  public static Block MyScaryLeaves;
  public static Block MyCherryLeaves;
  public static Block MyPeachLeaves;
  public static Item MyExperienceCatcher;
  public static Item MyExperienceTreeSeed;
  public static Item MyDeadStinkBug;
  public static Block MyCrystalLeaves;
  public static Block MyCrystalLeaves2;
  public static Block MyCrystalLeaves3;
  public static Block MyCrystalTreeLog;
  public static int GirlfriendID = 0;
  public static int BoyfriendID = 0;
  public static int RedCowID = 0;
  public static int GoldCowID = 0;
  public static int CrystalCowID = 0;
  public static int ButterflyID = 0;
  public static int FireflyID = 0;
  public static int FairyID = 0;
  public static int BeeID = 0;
  public static int TheKingID = 0;
  public static int TheQueenID = 0;
  public static int ThePrinceID = 0;
  public static int ThePrincessID = 0;
  public static int ThePrinceTeenID = 0;
  public static int ThePrinceAdultID = 0;
  public static int MantisID = 0;
  public static int StinkyID = 0;
  public static int HerculesBeetleID = 0;
  public static int LunaMothID = 0;
  public static int MosquitoID = 0;
  public static int GhostID = 0;
  public static int GhostSkellyID = 0;
  public static int SpiderRobotID = 0;
  public static int AntRobotID = 0;
  public static int JefferyID = 0;
  public static int SpiderDriverID = 0;
  public static int MothraID = 0;
  public static int BrutalflyID = 0;
  public static int NastysaurusID = 0;
  public static int PointysaurusID = 0;
  public static int CricketID = 0;
  public static int FrogID = 0;
  public static int EnchantedCowID = 0;
  public static int AntID = 0;
  public static int UnstableAntID = 0;
  public static int RedAntID = 0;
  public static int TermiteID = 0;
  public static int RockBaseID = 0;
  public static int RainbowAntID = 0;
  public static int AlosaurusID = 0;
  public static int LeonID = 0;
  public static int CaterKillerID = 0;
  public static int MolenoidID = 0;
  public static int TRexID = 0;
  public static int BandPID = 0;
  public static int CryolophosaurusID = 0;
  public static int RatID = 0;
  public static int UrchinID = 0;
  public static int CamarasaurusID = 0;
  public static int VelocityRaptorID = 0;
  public static int HydroliscID = 0;
  public static int SpyroID = 0;
  public static int BaryonyxID = 0;
  public static int CassowaryID = 0;
  public static int EasterBunnyID = 0;
  public static int PeacockID = 0;
  public static int CockateilID = 0;
  public static int RubyBirdID = 0;
  public static int KyuubiID = 0;
  public static int CephadromeID = 0;
  public static int DragonID = 0;
  public static int GammaMetroidID = 0;
  public static int BasiliskID = 0;
  public static int DragonflyID = 0;
  public static int EmperorScorpionID = 0;
  public static int TrooperBugID = 0;
  public static int SpitBugID = 0;
  public static int StinkBugID = 0;
  public static int ScorpionID = 0;
  public static int CaveFisherID = 0;
  public static int AlienID = 0;
  public static int WaterDragonID = 0;
  public static int SeaMonsterID = 0;
  public static int SeaViperID = 0;
  public static int AttackSquidID = 0;
  public static int ElevatorID = 0;
  public static int Robot1ID = 0;
  public static int Robot2ID = 0;
  public static int Robot3ID = 0;
  public static int Robot4ID = 0;
  public static int Robot5ID = 0;
  public static int RotatorID = 0;
  public static int VortexID = 0;
  public static int DungeonBeastID = 0;
  public static int KrakenID = 0;
  public static int LizardID = 0;
  public static int RubberDuckyID = 0;
  public static int ChipmunkID = 0;
  public static int OstrichID = 0;
  public static int GazelleID = 0;
  public static int TshirtID = 0;
  public static int CoinID = 0;
  public static int IslandID = 0;
  public static int IslandTooID = 0;
  public static int CreepingHorrorID = 0;
  public static int TerribleTerrorID = 0;
  public static int CliffRacerID = 0;
  public static int TriffidID = 0;
  public static int PitchBlackID = 0;
  public static int LurkingTerrorID = 0;
  public static int GodzillaID = 0;
  public static int WormSmallID = 0;
  public static int WormMediumID = 0;
  public static int WormLargeID = 0;
  public static int CloudSharkID = 0;
  public static int GoldFishID = 0;
  public static int LeafMonsterID = 0;
  public static int GodzillaHeadID = 0;
  public static int KingHeadID = 0;
  public static int QueenHeadID = 0;
  public static int EnderKnightID = 0;
  public static int EnderReaperID = 0;
  public static int BeaverID = 0;
  public static int SkateID = 0;
  public static int IrukandjiID = 0;
  public static int FlounderID = 0;
  public static int WhaleID = 0;
  public static int HammerheadID = 0;
  public static int CrabID = 0;

  public static MobStats Bee_stats = null;
  public static MobStats Mantis_stats = null;
  public static MobStats HerculesBeetle_stats = null;
  public static MobStats Mothra_stats = null;
  public static MobStats Brutalfly_stats = null;
  public static MobStats Nastysaurus_stats = null;
  public static MobStats Pointysaurus_stats = null;
  public static MobStats Alosaurus_stats = null;
  public static MobStats SpiderRobot_stats = null;
  public static MobStats AntRobot_stats = null;
  public static MobStats Jeffery_stats = null;
  public static MobStats Hammerhead_stats = null;
  public static MobStats Leon_stats = null;
  public static MobStats CaterKiller_stats = null;
  public static MobStats Molenoid_stats = null;
  public static MobStats TRex_stats = null;
  public static MobStats BandP_stats = null;
  public static MobStats Cryolophosaurus_stats = null;
  public static MobStats Rat_stats = null;
  public static MobStats Urchin_stats = null;
  public static MobStats Kyuubi_stats = null;
  public static MobStats GammaMetroid_stats = null;
  public static MobStats Basilisk_stats = null;
  public static MobStats EmperorScorpion_stats = null;
  public static MobStats TrooperBug_stats = null;
  public static MobStats SpitBug_stats = null;
  public static MobStats Alien_stats = null;
  public static MobStats WaterDragon_stats = null;
  public static MobStats SeaMonster_stats = null;
  public static MobStats SeaViper_stats = null;
  public static MobStats Robot2_stats = null;
  public static MobStats Robot3_stats = null;
  public static MobStats Robot4_stats = null;
  public static MobStats Robot5_stats = null;
  public static MobStats Rotator_stats = null;
  public static MobStats Vortex_stats = null;
  public static MobStats DungeonBeast_stats = null;
  public static MobStats Triffid_stats = null;
  public static MobStats LurkingTerror_stats = null;
  public static MobStats WormSmall_stats = null;
  public static MobStats WormMedium_stats = null;
  public static MobStats WormLarge_stats = null;
  public static MobStats EnderKnight_stats = null;
  public static MobStats EnderReaper_stats = null;
  public static MobStats Irukandji_stats = null;
  public static MobStats AttackSquid_stats = null;
  public static MobStats CaveFisher_stats = null;
  public static MobStats CloudShark_stats = null;
  public static MobStats CreepingHorror_stats = null;
  public static MobStats Godzilla_stats = null;
  public static MobStats Kraken_stats = null;
  public static MobStats LeafMonster_stats = null;
  public static MobStats PitchBlack_stats = null;
  public static MobStats Crab_stats = null;
  public static MobStats Scorpion_stats = null;
  public static MobStats Skate_stats = null;
  public static MobStats TerribleTerror_stats = null;
  public static MobStats TheKing_stats = null;
  public static MobStats TheQueen_stats = null;

  public static OreStats Ruby_stats = null;
  public static OreStats BlkRuby_stats = null;
  public static OreStats Uranium_stats = null;
  public static OreStats Titanium_stats = null;
  public static OreStats Amethyst_stats = null;
  public static OreStats Salt_stats = null;
  public static OreStats SpawnOres_stats = null;
  public static OreStats Diamond_stats = null;
  public static OreStats BlkDiamond_stats = null;
  public static OreStats Emerald_stats = null;
  public static OreStats BlkEmerald_stats = null;
  public static OreStats Gold_stats = null;
  public static OreStats BlkGold_stats = null;

  /** Raises vanilla {@code generic.maxHealth} cap (1024 in 1.12.2) so 1.7.10-scale boss HP applies. */
  private static void raiseVanillaMaxHealthCap()
  {
    try
    {
      Attribute attr = Attributes.MAX_HEALTH;
      if (!(attr instanceof RangedAttribute))
      {
        return;
      }
      RangedAttribute ranged = (RangedAttribute)attr;
      Field target = null;
      for (Field f : RangedAttribute.class.getDeclaredFields())
      {
        if (f.getType() != double.class || !Modifier.isFinal(f.getModifiers()))
        {
          continue;
        }
        f.setAccessible(true);
        double v = f.getDouble(ranged);
        if (Math.abs(v - 1024.0D) < 1.0E-6D)
        {
          target = f;
          break;
        }
      }
      if (target == null)
      {
        return;
      }
      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);
      modifiers.setInt(target, target.getModifiers() & ~Modifier.FINAL);
      target.setDouble(ranged, 1.0E9D);
    }
    catch (Throwable t)
    {
      LOGGER.error("ChaosPersists: failed to raise generic.maxHealth cap; boss HP may stay capped at 1024", t);
    }
  }

    private void commonSetup(FMLCommonSetupEvent event)
  {
    this.commonSetupEvent = event;
    raiseVanillaMaxHealthCap();
    chaosPrepareContent();
    make_some_more_things_recipes();
    net.minecraftforge.fml.DistExecutor.runWhenOn(net.minecraftforge.api.distmarker.Dist.CLIENT,
        () -> () -> proxy.registerBlockModels());
  }

  private void chaosRunPreInitContent()
  {
    LegacyConfiguration config = new LegacyConfiguration(FMLPaths.CONFIGDIR.get().resolve("chaospersists.cfg").toFile());
    String ids = "chaospersistsIDS";
    String mobs = "chaospersistsMOBS";
    String tweaks = "chaospersistsTWEAKS";
    String weapons = "chaospersistsWEAPONS";
    String ores = "chaospersistsORES";

    config.load();

    config.setCategoryComment(ids,
        "Block / item / biome / dimension numeric IDs. Dimension conflicts: each mod needs a unique world ID. Vanilla uses Overworld 0, Nether -1, End 1. "
            + "Examples that often collide: AE2 spatial (2), Overworld Mirror (83), The Betweenlands (85). "
            + "Set BaseDimensionID to a free range, or set DimensionId_* entries explicitly (see each key). "
            + "Changing IDs after a world was created will strand dimension saves; backup before changing.");

    BaseBlockID = config.get(ids, "BaseBlockID", 2700).getInt();
    BaseItemID = config.get(ids, "BaseItemID", 9000).getInt();
    BaseBiomeID = config.get(ids, "BaseBiomeID", 120).getInt();

    configureDimensionIds(config, ids);

    getMobs(config, mobs);

    AllMobsDisable = config.get(tweaks, "AllMobsDisable", 0).getInt();
    LessOre = config.get(tweaks, "LessOre", 0).getInt();
    LessLag = config.get(tweaks, "LessLag", 0).getInt();
    RatPlayerFriendly = config.get(tweaks, "RatPlayerFriendly", 1).getInt();
    RatPetFriendly = config.get(tweaks, "RatPetFriendly", 1).getInt();
    NightmareSize = config.get(tweaks, "NightmareSize", 0).getInt();
    IslandSpeedFactor = config.get(tweaks, "IslandSpeedFactor", 2).getInt();
    IslandSizeFactor = config.get(tweaks, "IslandSizeFactor", 2).getInt();
    GinormousEmeraldTreeEnable = config.get(tweaks, "GinormousEmeraldTreeEnable", 1).getInt();
    GuiOverlayEnable = config.get(tweaks, "GuiOverlayEnable", 1).getInt();
    ultimate_sword_pvp = config.get(tweaks, "UltimateSwordPvp", 0).getInt();
    big_bertha_pvp = config.get(tweaks, "BigBerthaPvp", 0).getInt();
    bro_mode = config.get(tweaks, "BoyfriendBroMode", 0).getInt();
    enableduplicatortree = config.get(tweaks, "DuplicatorTreeEnable", 1).getInt();
    RoyalGlideEnable = config.get(tweaks, "RoyalGlideEnable", 1).getInt();
    DragonflyHorseFriendly = config.get(tweaks, "DragonflyHorseFriendly", 0).getInt();
    PlayNicely = config.get(tweaks, "PlayNicely", 0).getInt();
    MinersDreamExpensive = config.get(tweaks, "MinersDreamExpensive", 0).getInt();
    DisableOverworldDungeons = config.get(tweaks, "DisableOverworldDungeons", 0).getInt();
    FullPowerKingEnable = config.get(tweaks, "FullPowerKingEnable", 0).getInt();

    // 1.12.2 diamond armor is 3/6/8/3 with toughness 2; keep OreSpawn durability 100, match diamond protection + enchant tier.
    Amethyst_armorstats = get_armorstats(config, "Amethyst", 100, 3, 6, 8, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0);
    Emerald_armorstats = get_armorstats(config, "Emerald", 60, 3, 8, 6, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
    Experience_armorstats = get_armorstats(config, "Experience", 70, 5, 9, 7, 4, 50, 0, 0, 2, 0, 1, 0, 0, 1);
    MothScale_armorstats = get_armorstats(config, "MothScale", 50, 2, 7, 5, 2, 50, 0, 0, 3, 3, 3, 0, 0, 5);
    LavaEel_armorstats = get_armorstats(config, "LavaEel", 40, 2, 7, 5, 2, 35, 1, 2, 3, 2, 10, 0, 0, 2);
    Ultimate_armorstats = get_armorstats(config, "Ultimate", 200, 6, 12, 10, 6, 100, 2, 3, 5, 5, 5, 5, 0, 3);
    Pink_armorstats = get_armorstats(config, "Pink", 50, 3, 7, 5, 2, 40, 0, 0, 0, 0, 0, 0, 0, 0);
    TigersEye_armorstats = get_armorstats(config, "TigersEye", 80, 4, 8, 7, 4, 55, 0, 0, 0, 0, 0, 0, 0, 0);
    Peacock_armorstats = get_armorstats(config, "Peacock", 40, 2, 5, 4, 2, 30, 0, 0, 0, 0, 0, 0, 0, 10);
    Mobzilla_armorstats = get_armorstats(config, "Mobzilla", 1000, 7, 13, 11, 7, 150, 0, 0, 10, 10, 10, 10, 5, 10);
    Ruby_armorstats = get_armorstats(config, "Ruby", 90, 4, 9, 8, 4, 40, 0, 0, 0, 0, 0, 0, 0, 0);
    Royal_armorstats = get_armorstats(config, "Royal", 2000, 8, 14, 12, 8, 200, 1, 2, 10, 10, 10, 10, 5, 10);
    Lapis_armorstats = get_armorstats(config, "Lapis", 60, 2, 7, 5, 2, 60, 1, 1, 1, 0, 0, 1, 0, 0);
    Queen_armorstats = get_armorstats(config, "Queen", 1500, 9, 16, 14, 9, 150, 0, 0, 0, 0, 0, 0, 0, 0);

    ultimate_stats = get_weaponstats(config, weapons, "Ultimate", 10, 3000, 15, 36, 100);
    nightmare_stats = get_weaponstats(config, weapons, "Nightmare", 3, 1800, 12, 26, 60);
    bertha_stats = get_weaponstats(config, weapons, "Bertha", 3, 9000, 15, 496, 100);
    crystalwood_stats = get_weaponstats(config, weapons, "CrystalWood", 2, 300, 3, 2, 15);
    crystalstone_stats = get_weaponstats(config, weapons, "CrystalStone", 3, 800, 6, 5, 45);
    crystalpink_stats = get_weaponstats(config, weapons, "Pink", 4, 1100, 10, 7, 65);
    tigerseye_stats = get_weaponstats(config, weapons, "TigersEye", 4, 1600, 12, 8, 75);
    ruby_stats = get_weaponstats(config, weapons, "Ruby", 5, 1500, 11, 16, 85);
    amethyst_stats = get_weaponstats(config, weapons, "Amethyst", 4, 2000, 11, 11, 70);
    emerald_stats = get_weaponstats(config, weapons, "Emerald", 3, 1300, 10, 6, 75);
    royal_stats = get_weaponstats(config, weapons, "Royal", 3, 10000, 15, 746, 150);
    hammy_stats = get_weaponstats(config, weapons, "Attitude", 5, 2000, 15, 82, 100);
    battleaxe_stats = get_weaponstats(config, weapons, "BattleAxe", 3, 1500, 15, 46, 75);
    chainsaw_stats = get_weaponstats(config, weapons, "Chainsaw", 3, 1500, 10, 56, 75);
    queenbattleaxe_stats = get_weaponstats(config, weapons, "QueenBattleAxe", 3, 2200, 15, 662, 100);

    UltimateSwordMagic = config.get(weapons, "UltimateSwordEnchantmentLevel", 5).getInt();
    UltimateBowDamage = config.get(weapons, "UltimateBowDamage", 10).getInt();

    if (UltimateSwordMagic < 1) UltimateSwordMagic = 1;
    if (UltimateSwordMagic > 10) UltimateSwordMagic = 10;
    if (UltimateBowDamage < 2) UltimateBowDamage = 2;
    if (UltimateBowDamage > 20) UltimateBowDamage = 20;

    if (IslandSpeedFactor < 1) IslandSpeedFactor = 1;
    if (IslandSpeedFactor > 5) IslandSpeedFactor = 5;
    if (IslandSizeFactor < 1) IslandSizeFactor = 1;
    if (IslandSizeFactor > 5) IslandSizeFactor = 5;

    if (NightmareSize < 0) NightmareSize = 0;
    if (NightmareSize > 5) NightmareSize = 5;
    if (LessLag < 0) LessLag = 0;
    if (LessLag > 2) LessLag = 2;
    if (LessLag == 1) {
      if (IslandSizeFactor > 2) IslandSizeFactor = 2;
      if (IslandSpeedFactor > 2) IslandSpeedFactor = 2;
    }
    if (LessLag == 2) {
      if (IslandSizeFactor > 1) IslandSizeFactor = 1;
      if (IslandSpeedFactor > 1) IslandSpeedFactor = 1;
      LessOre = 1;
    }

    Ruby_stats = get_orestats(config, ores, "Ruby", 10, 1, 0, 50);
    BlkRuby_stats = get_orestats(config, ores, "BlockRuby", 1, 2, 0, 15);
    Uranium_stats = get_orestats(config, ores, "Uranium", 3, 4, 0, 30);
    Titanium_stats = get_orestats(config, ores, "Titanium", 3, 4, 0, 20);
    Amethyst_stats = get_orestats(config, ores, "Amethyst", 2, 6, 0, 25);
    Salt_stats = get_orestats(config, ores, "Salt", 5, 12, 50, 128);
    SpawnOres_stats = get_orestats(config, ores, "SpawnOres", 28, 4, 50, 128);
    Diamond_stats = get_orestats(config, ores, "Diamond", 4, 6, 0, 30);
    BlkDiamond_stats = get_orestats(config, ores, "BlockDiamond", 2, 4, 0, 20);
    Emerald_stats = get_orestats(config, ores, "Emerald", 4, 6, 0, 40);
    BlkEmerald_stats = get_orestats(config, ores, "BlockEmerald", 2, 4, 0, 20);
    Gold_stats = get_orestats(config, ores, "Gold", 4, 8, 0, 40);
    BlkGold_stats = get_orestats(config, ores, "BlockGold", 2, 4, 0, 25);

    config.save();

    if (AllMobsDisable != 0) {
      disableAllMobs();
    }

    BiomeUtopiaID = BaseBiomeID;
    BiomeIslandsID = BaseBiomeID + 1;
    BiomeCrystalID = BaseBiomeID + 2;
    BiomeVillageID = BaseBiomeID + 3;
    BiomeChaosID = BaseBiomeID + 4;
    BiomeMiningID = BaseBiomeID + 5;
    MinecraftForge.EVENT_BUS.register(chaospersistsGen);

    proxy.registerSoundThings();

    laySomeEggs();

    MyOreUraniumBlock = new OreUranium();

         MyOreUraniumBlock.setRegistryName(new ResourceLocation("chaospersists", "oreuranium"));
    MyOreTitaniumBlock = new OreTitanium();
         MyOreTitaniumBlock.setRegistryName(new ResourceLocation("chaospersists", "oretitanium"));
    MyIngotUranium = new IngotUranium();
         MyIngotUranium.setRegistryName(new ResourceLocation("chaospersists", "ingoturanium"));
    MyIngotTitanium = new IngotTitanium();
         MyIngotTitanium.setRegistryName(new ResourceLocation("chaospersists", "ingottitanium"));
    MyBlockUraniumBlock = new BlockUranium();
         MyBlockUraniumBlock.setRegistryName(new ResourceLocation("chaospersists", "blockuranium"));
    MyBlockTitaniumBlock = new BlockTitanium();
         MyBlockTitaniumBlock.setRegistryName(new ResourceLocation("chaospersists", "blocktitanium"));
    MyBlockMobzillaScaleBlock = new BlockRuby();
         MyBlockMobzillaScaleBlock.setRegistryName(new ResourceLocation("chaospersists", "blockmobzillascale"));
    MyLavafoamBlock = new Lavafoam();
         MyLavafoamBlock.setRegistryName(new ResourceLocation("chaospersists", "lavafoam"));
    MyBlockRubyBlock = new BlockRuby();
         MyBlockRubyBlock.setRegistryName(new ResourceLocation("chaospersists", "blockruby"));
    MyBlockAmethystBlock = new BlockRuby();
         MyBlockAmethystBlock.setRegistryName(new ResourceLocation("chaospersists", "blockamethyst"));
    MyCrystalPinkBlock = new BlockCrystal();
         MyCrystalPinkBlock.setRegistryName(new ResourceLocation("chaospersists", "crystalpink_block"));
    MyCrystalPinkIngot = new IngotUranium();
         MyCrystalPinkIngot.setRegistryName(new ResourceLocation("chaospersists", "crystalpink_ingot"));
    MyTigersEyeBlock = new BlockCrystal();
         MyTigersEyeBlock.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_block"));
    MyTigersEyeIngot = new IngotUranium();
         MyTigersEyeIngot.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_ingot"));

    MyPizzaBlock = new BlockPizza();

         MyPizzaBlock.setRegistryName(new ResourceLocation("chaospersists", "pizza"));
    MyPizzaItem = new ItemPizza(MyPizzaBlock).setRegistryName(new ResourceLocation("chaospersists", "pizza"));
    MyDuctTapeBlock = new BlockDuctTape();
         MyDuctTapeBlock.setRegistryName(new ResourceLocation("chaospersists", "ducttape"));
    MyDuctTapeItem = new ItemDuctTape(MyDuctTapeBlock).setRegistryName(new ResourceLocation("chaospersists", "ducttape"));

        toolULTIMATE = new IItemTier() { public int getUses() { return ultimate_stats.maxuses; } public float getSpeed() { return ultimate_stats.efficiency; } public float getAttackDamageBonus() { return ultimate_stats.damage; } public int getLevel() { return ultimate_stats.harvestlevel; } public int getEnchantmentValue() { return ultimate_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolNIGHTMARE = new IItemTier() { public int getUses() { return nightmare_stats.maxuses; } public float getSpeed() { return nightmare_stats.efficiency; } public float getAttackDamageBonus() { return nightmare_stats.damage; } public int getLevel() { return nightmare_stats.harvestlevel; } public int getEnchantmentValue() { return nightmare_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolEMERALD = new IItemTier() { public int getUses() { return emerald_stats.maxuses; } public float getSpeed() { return emerald_stats.efficiency; } public float getAttackDamageBonus() { return emerald_stats.damage; } public int getLevel() { return emerald_stats.harvestlevel; } public int getEnchantmentValue() { return emerald_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolRUBY = new IItemTier() { public int getUses() { return ruby_stats.maxuses; } public float getSpeed() { return ruby_stats.efficiency; } public float getAttackDamageBonus() { return ruby_stats.damage; } public int getLevel() { return ruby_stats.harvestlevel; } public int getEnchantmentValue() { return ruby_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolAMETHYST = new IItemTier() { public int getUses() { return amethyst_stats.maxuses; } public float getSpeed() { return amethyst_stats.efficiency; } public float getAttackDamageBonus() { return amethyst_stats.damage; } public int getLevel() { return amethyst_stats.harvestlevel; } public int getEnchantmentValue() { return amethyst_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolBERTHA = new IItemTier() { public int getUses() { return bertha_stats.maxuses; } public float getSpeed() { return bertha_stats.efficiency; } public float getAttackDamageBonus() { return bertha_stats.damage; } public int getLevel() { return bertha_stats.harvestlevel; } public int getEnchantmentValue() { return bertha_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolCRYSTALWOOD = new IItemTier() { public int getUses() { return crystalwood_stats.maxuses; } public float getSpeed() { return crystalwood_stats.efficiency; } public float getAttackDamageBonus() { return crystalwood_stats.damage; } public int getLevel() { return crystalwood_stats.harvestlevel; } public int getEnchantmentValue() { return crystalwood_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolCRYSTALSTONE = new IItemTier() { public int getUses() { return crystalstone_stats.maxuses; } public float getSpeed() { return crystalstone_stats.efficiency; } public float getAttackDamageBonus() { return crystalstone_stats.damage; } public int getLevel() { return crystalstone_stats.harvestlevel; } public int getEnchantmentValue() { return crystalstone_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolCRYSTALPINK = new IItemTier() { public int getUses() { return crystalpink_stats.maxuses; } public float getSpeed() { return crystalpink_stats.efficiency; } public float getAttackDamageBonus() { return crystalpink_stats.damage; } public int getLevel() { return crystalpink_stats.harvestlevel; } public int getEnchantmentValue() { return crystalpink_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolTIGERSEYE = new IItemTier() { public int getUses() { return tigerseye_stats.maxuses; } public float getSpeed() { return tigerseye_stats.efficiency; } public float getAttackDamageBonus() { return tigerseye_stats.damage; } public int getLevel() { return tigerseye_stats.harvestlevel; } public int getEnchantmentValue() { return tigerseye_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolROYAL = new IItemTier() { public int getUses() { return royal_stats.maxuses; } public float getSpeed() { return royal_stats.efficiency; } public float getAttackDamageBonus() { return royal_stats.damage; } public int getLevel() { return royal_stats.harvestlevel; } public int getEnchantmentValue() { return royal_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolHAMMY = new IItemTier() { public int getUses() { return hammy_stats.maxuses; } public float getSpeed() { return hammy_stats.efficiency; } public float getAttackDamageBonus() { return hammy_stats.damage; } public int getLevel() { return hammy_stats.harvestlevel; } public int getEnchantmentValue() { return hammy_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolBATTLE = new IItemTier() { public int getUses() { return battleaxe_stats.maxuses; } public float getSpeed() { return battleaxe_stats.efficiency; } public float getAttackDamageBonus() { return battleaxe_stats.damage; } public int getLevel() { return battleaxe_stats.harvestlevel; } public int getEnchantmentValue() { return battleaxe_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolCHAINSAW = new IItemTier() { public int getUses() { return chainsaw_stats.maxuses; } public float getSpeed() { return chainsaw_stats.efficiency; } public float getAttackDamageBonus() { return chainsaw_stats.damage; } public int getLevel() { return chainsaw_stats.harvestlevel; } public int getEnchantmentValue() { return chainsaw_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

        toolQUEENBATTLE = new IItemTier() { public int getUses() { return queenbattleaxe_stats.maxuses; } public float getSpeed() { return queenbattleaxe_stats.efficiency; } public float getAttackDamageBonus() { return queenbattleaxe_stats.damage; } public int getLevel() { return queenbattleaxe_stats.harvestlevel; } public int getEnchantmentValue() { return queenbattleaxe_stats.enchantability; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } };

    MyUltimateSword = new UltimateSword(toolULTIMATE);

         MyUltimateSword.setRegistryName(new ResourceLocation("chaospersists", "ultimatesword"));
    MyUltimatePickaxe = new UltimatePickaxe(toolULTIMATE);
         MyUltimatePickaxe.setRegistryName(new ResourceLocation("chaospersists", "ultimatepickaxe"));
    MyUltimateShovel = new UltimateShovel(toolULTIMATE);
         MyUltimateShovel.setRegistryName(new ResourceLocation("chaospersists", "ultimateshovel"));
    MyUltimateHoe = new UltimateHoe(toolULTIMATE);
         MyUltimateHoe.setRegistryName(new ResourceLocation("chaospersists", "ultimatehoe"));
    MyUltimateAxe = new UltimateAxe(toolULTIMATE);
         MyUltimateAxe.setRegistryName(new ResourceLocation("chaospersists", "ultimateaxe"));
    MyNightmareSword = new NightmareSword(toolNIGHTMARE);
         MyNightmareSword.setRegistryName(new ResourceLocation("chaospersists", "nightmaresword"));
    MyBertha = new Bertha(toolBERTHA);
         MyBertha.setRegistryName(new ResourceLocation("chaospersists", "berthasmall"));
    MySlice = new Bertha(toolBERTHA);
         MySlice.setRegistryName(new ResourceLocation("chaospersists", "slicesmall"));
    MyRoyal = new Bertha(toolROYAL);
         MyRoyal.setRegistryName(new ResourceLocation("chaospersists", "royalsmall"));
    MyHammy = new Bertha(toolHAMMY);
         MyHammy.setRegistryName(new ResourceLocation("chaospersists", "hammysmall"));
    MyBattleAxe = new UltimateSword(toolBATTLE);
         MyBattleAxe.setRegistryName(new ResourceLocation("chaospersists", "battleaxesmall"));
    MyChainsaw = new UltimateSword(toolCHAINSAW);
         MyChainsaw.setRegistryName(new ResourceLocation("chaospersists", "chainsawsmall"));
    MyQueenBattleAxe = new UltimateSword(toolQUEENBATTLE);
         MyQueenBattleAxe.setRegistryName(new ResourceLocation("chaospersists", "queenbattleaxesmall"));

    MyEmeraldSword = new EmeraldSword(toolEMERALD);

         MyEmeraldSword.setRegistryName(new ResourceLocation("chaospersists", "emeraldsword"));
    MyEmeraldPickaxe = new EmeraldPickaxe(toolEMERALD);
         MyEmeraldPickaxe.setRegistryName(new ResourceLocation("chaospersists", "emeraldpickaxe"));
    MyEmeraldShovel = new EmeraldShovel(toolEMERALD);
         MyEmeraldShovel.setRegistryName(new ResourceLocation("chaospersists", "emeraldshovel"));
    MyEmeraldHoe = new EmeraldHoe(toolEMERALD);
         MyEmeraldHoe.setRegistryName(new ResourceLocation("chaospersists", "emeraldhoe"));
    MyEmeraldAxe = new EmeraldAxe(toolEMERALD);
         MyEmeraldAxe.setRegistryName(new ResourceLocation("chaospersists", "emeraldaxe"));
    MyExperienceSword = new ExperienceSword(toolEMERALD);
         MyExperienceSword.setRegistryName(new ResourceLocation("chaospersists", "experiencesword"));
    MyPoisonSword = new PoisonSword(toolEMERALD);
         MyPoisonSword.setRegistryName(new ResourceLocation("chaospersists", "poisonsword"));
    MyRatSword = new RatSword(toolEMERALD);
         MyRatSword.setRegistryName(new ResourceLocation("chaospersists", "ratsword"));
    MyFairySword = new FairySword(toolEMERALD);
         MyFairySword.setRegistryName(new ResourceLocation("chaospersists", "fairysword"));
    MyMantisClaw = new MantisClaw(toolEMERALD);
         MyMantisClaw.setRegistryName(new ResourceLocation("chaospersists", "mantisclaw"));
    MyBigHammer = new BigHammer(toolAMETHYST);
         MyBigHammer.setRegistryName(new ResourceLocation("chaospersists", "bighammer"));
    MyRubySword = new RubySword(toolRUBY);
         MyRubySword.setRegistryName(new ResourceLocation("chaospersists", "rubysword"));
    MyRubyPickaxe = new RubyPickaxe(toolRUBY);
         MyRubyPickaxe.setRegistryName(new ResourceLocation("chaospersists", "rubypickaxe"));
    MyRubyShovel = new RubyShovel(toolRUBY);
         MyRubyShovel.setRegistryName(new ResourceLocation("chaospersists", "rubyshovel"));
    MyRubyHoe = new RubyHoe(toolRUBY);
         MyRubyHoe.setRegistryName(new ResourceLocation("chaospersists", "rubyhoe"));
    MyRubyAxe = new RubyAxe(toolRUBY);
         MyRubyAxe.setRegistryName(new ResourceLocation("chaospersists", "rubyaxe"));
    MyAmethystSword = new AmethystSword(toolAMETHYST);
         MyAmethystSword.setRegistryName(new ResourceLocation("chaospersists", "amethystsword"));
    MyAmethystPickaxe = new AmethystPickaxe(toolAMETHYST);
         MyAmethystPickaxe.setRegistryName(new ResourceLocation("chaospersists", "amethystpickaxe"));
    MyAmethystShovel = new AmethystShovel(toolAMETHYST);
         MyAmethystShovel.setRegistryName(new ResourceLocation("chaospersists", "amethystshovel"));
    MyAmethystHoe = new AmethystHoe(toolAMETHYST);
         MyAmethystHoe.setRegistryName(new ResourceLocation("chaospersists", "amethysthoe"));
    MyAmethystAxe = new AmethystAxe(toolAMETHYST);
         MyAmethystAxe.setRegistryName(new ResourceLocation("chaospersists", "amethystaxe"));
    MyCrystalWoodSword = new CrystalSword(toolCRYSTALWOOD);
         MyCrystalWoodSword.setRegistryName(new ResourceLocation("chaospersists", "crystalwoodsword"));
    MyCrystalWoodPickaxe = new CrystalPickaxe(toolCRYSTALWOOD);
         MyCrystalWoodPickaxe.setRegistryName(new ResourceLocation("chaospersists", "crystalwoodpickaxe"));
    MyCrystalWoodShovel = new CrystalShovel(toolCRYSTALWOOD);
         MyCrystalWoodShovel.setRegistryName(new ResourceLocation("chaospersists", "crystalwoodshovel"));
    MyCrystalWoodHoe = new CrystalHoe(toolCRYSTALWOOD);
         MyCrystalWoodHoe.setRegistryName(new ResourceLocation("chaospersists", "crystalwoodhoe"));
    MyCrystalWoodAxe = new CrystalAxe(toolCRYSTALWOOD);
         MyCrystalWoodAxe.setRegistryName(new ResourceLocation("chaospersists", "crystalwoodaxe"));
    MyCrystalPinkSword = new CrystalSword(toolCRYSTALPINK);
         MyCrystalPinkSword.setRegistryName(new ResourceLocation("chaospersists", "crystalpinksword"));
    MyCrystalPinkPickaxe = new CrystalPickaxe(toolCRYSTALPINK);
         MyCrystalPinkPickaxe.setRegistryName(new ResourceLocation("chaospersists", "crystalpinkpickaxe"));
    MyCrystalPinkShovel = new CrystalShovel(toolCRYSTALPINK);
         MyCrystalPinkShovel.setRegistryName(new ResourceLocation("chaospersists", "crystalpinkshovel"));
    MyCrystalPinkHoe = new CrystalHoe(toolCRYSTALPINK);
         MyCrystalPinkHoe.setRegistryName(new ResourceLocation("chaospersists", "crystalpinkhoe"));
    MyCrystalPinkAxe = new CrystalAxe(toolCRYSTALPINK);
         MyCrystalPinkAxe.setRegistryName(new ResourceLocation("chaospersists", "crystalpinkaxe"));
    MyCrystalStoneSword = new CrystalSword(toolCRYSTALSTONE);
         MyCrystalStoneSword.setRegistryName(new ResourceLocation("chaospersists", "crystalstonesword"));
    MyCrystalStonePickaxe = new CrystalPickaxe(toolCRYSTALSTONE);
         MyCrystalStonePickaxe.setRegistryName(new ResourceLocation("chaospersists", "crystalstonepickaxe"));
    MyCrystalStoneShovel = new CrystalShovel(toolCRYSTALSTONE);
         MyCrystalStoneShovel.setRegistryName(new ResourceLocation("chaospersists", "crystalstoneshovel"));
    MyCrystalStoneHoe = new CrystalHoe(toolCRYSTALSTONE);
         MyCrystalStoneHoe.setRegistryName(new ResourceLocation("chaospersists", "crystalstonehoe"));
    MyCrystalStoneAxe = new CrystalAxe(toolCRYSTALSTONE);
         MyCrystalStoneAxe.setRegistryName(new ResourceLocation("chaospersists", "crystalstoneaxe"));
    MyTigersEyeSword = new CrystalSword(toolTIGERSEYE);
         MyTigersEyeSword.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_sword"));
    MyTigersEyePickaxe = new CrystalPickaxe(toolTIGERSEYE);
         MyTigersEyePickaxe.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_pickaxe"));
    MyTigersEyeShovel = new CrystalShovel(toolTIGERSEYE);
         MyTigersEyeShovel.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_shovel"));
    MyTigersEyeHoe = new CrystalHoe(toolTIGERSEYE);
         MyTigersEyeHoe.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_hoe"));
    MyTigersEyeAxe = new CrystalAxe(toolTIGERSEYE);
         MyTigersEyeAxe.setRegistryName(new ResourceLocation("chaospersists", "tigerseye_axe"));
    MyRoseSword = new EmeraldSword(toolEMERALD);
         MyRoseSword.setRegistryName(new ResourceLocation("chaospersists", "rosesword"));

    MyItemShoes = new ItemShoes(2);

         MyItemShoes.setRegistryName(new ResourceLocation("chaospersists", "redheels"));
    MyItemShoes_1 = new ItemShoes(3);
         MyItemShoes_1.setRegistryName(new ResourceLocation("chaospersists", "blackheels"));
    MyItemShoes_2 = new ItemShoes(4);
         MyItemShoes_2.setRegistryName(new ResourceLocation("chaospersists", "slippers"));
    MyItemShoes_3 = new ItemShoes(5);
         MyItemShoes_3.setRegistryName(new ResourceLocation("chaospersists", "boots"));
    MyItemGameController = new ItemShoes(6);
         MyItemGameController.setRegistryName(new ResourceLocation("chaospersists", "gamecontroller"));

    MyUltimateBow = new UltimateBow(BaseItemID + 303);

         MyUltimateBow.setRegistryName(new ResourceLocation("chaospersists", "ultimatebow"));
    MySkateBow = new SkateBow(BaseItemID + 373);
         MySkateBow.setRegistryName(new ResourceLocation("chaospersists", "skatebow"));

    MyUltimateFishingRod = new UltimateFishingRod(BaseItemID + 304);

         MyUltimateFishingRod.setRegistryName(new ResourceLocation("chaospersists", "ultimatefishingrod"));
    UltimateFishingRod = new ItemStack(MyUltimateFishingRod);

    MyFireFish = new ItemFireFish(4, 0.6F);

         MyFireFish.setRegistryName(new ResourceLocation("chaospersists", "firefish"));
    MySunFish = new ItemSunFish(6, 0.6F);
         MySunFish.setRegistryName(new ResourceLocation("chaospersists", "sunfish"));
    MyLavaEel = new ItemLavaEel(2, 0.6F);
         MyLavaEel.setRegistryName(new ResourceLocation("chaospersists", "lavaeel"));
    MyMothScale = new ItemSalt(BaseItemID + 156);
         MyMothScale.setRegistryName(new ResourceLocation("chaospersists", "mothscale"));
    MyQueenScale = new ItemSalt(BaseItemID + 453);
         MyQueenScale.setRegistryName(new ResourceLocation("chaospersists", "queenscale"));
    MyNightmareScale = new ItemSalt(BaseItemID + 158);
         MyNightmareScale.setRegistryName(new ResourceLocation("chaospersists", "nightmarescale"));
    MyEmperorScorpionScale = new ItemSalt(BaseItemID + 159);
         MyEmperorScorpionScale.setRegistryName(new ResourceLocation("chaospersists", "emperorscorpionscale"));
    MyBasiliskScale = new ItemSalt(BaseItemID + 160);
         MyBasiliskScale.setRegistryName(new ResourceLocation("chaospersists", "basiliskscale"));
    MyWaterDragonScale = new ItemSalt(BaseItemID + 161);
         MyWaterDragonScale.setRegistryName(new ResourceLocation("chaospersists", "waterdragonscale"));
    MyPeacockFeather = new ItemSalt(BaseItemID + 255);
         MyPeacockFeather.setRegistryName(new ResourceLocation("chaospersists", "peacockfeather"));
    MyJumpyBugScale = new ItemSalt(BaseItemID + 162);
         MyJumpyBugScale.setRegistryName(new ResourceLocation("chaospersists", "jumpybugscale"));
    MyKrakenTooth = new ItemSalt(BaseItemID + 163);
         MyKrakenTooth.setRegistryName(new ResourceLocation("chaospersists", "krakentooth"));
    MyGodzillaScale = new ItemSalt(BaseItemID + 164);
         MyGodzillaScale.setRegistryName(new ResourceLocation("chaospersists", "godzillascale"));
    GreenGoo = new ItemSalt(BaseItemID + 154);
         GreenGoo.setRegistryName(new ResourceLocation("chaospersists", "greengoo"));
    SpiderRobotKit = new ItemSpiderRobotKit(BaseItemID + 471);
         SpiderRobotKit.setRegistryName(new ResourceLocation("chaospersists", "spiderrobotkit"));
    AntRobotKit = new ItemSpiderRobotKit(BaseItemID + 473);
         AntRobotKit.setRegistryName(new ResourceLocation("chaospersists", "antrobotkit"));
    ZooKeeper = new ItemZooKeeper(BaseItemID + 230);
         ZooKeeper.setRegistryName(new ResourceLocation("chaospersists", "zookeeper"));
    CreeperLauncher = new ItemCreeperLauncher(BaseItemID + 252);
         CreeperLauncher.setRegistryName(new ResourceLocation("chaospersists", "creeperlauncher"));
    NetherLost = new ItemNetherLost(BaseItemID + 253);
         NetherLost.setRegistryName(new ResourceLocation("chaospersists", "netherlost"));
    CrystalSticks = new ItemCrystalSticks(BaseItemID + 254);
         CrystalSticks.setRegistryName(new ResourceLocation("chaospersists", "crystalsticks"));
    MySunspotUrchin = new ItemSunspotUrchin(BaseItemID + 246);
         MySunspotUrchin.setRegistryName(new ResourceLocation("chaospersists", "sunspoturchin"));
    MySparkFish = new ItemSparkFish(1, 0.2F);
         MySparkFish.setRegistryName(new ResourceLocation("chaospersists", "sparkfish"));
    MyWaterBall = new ItemWaterBall(BaseItemID + 244);
         MyWaterBall.setRegistryName(new ResourceLocation("chaospersists", "waterball"));
    MyLaserBall = new ItemLaserBall(BaseItemID + 242);
         MyLaserBall.setRegistryName(new ResourceLocation("chaospersists", "laserball"));
    MyIceBall = new ItemIceBall(BaseItemID + 239);
         MyIceBall.setRegistryName(new ResourceLocation("chaospersists", "iceball"));
    MySmallRock = new ItemRock(BaseItemID + 436);
         MySmallRock.setRegistryName(new ResourceLocation("chaospersists", "rocksmall"));
    MyRock = new ItemRock(BaseItemID + 435);
         MyRock.setRegistryName(new ResourceLocation("chaospersists", "rock"));
    MyRedRock = new ItemRock(BaseItemID + 437);
         MyRedRock.setRegistryName(new ResourceLocation("chaospersists", "rockred"));
    MyCrystalRedRock = new ItemRock(BaseItemID + 443);
         MyCrystalRedRock.setRegistryName(new ResourceLocation("chaospersists", "rockcrystalred"));
    MyCrystalGreenRock = new ItemRock(BaseItemID + 444);
         MyCrystalGreenRock.setRegistryName(new ResourceLocation("chaospersists", "rockcrystalgreen"));
    MyCrystalBlueRock = new ItemRock(BaseItemID + 445);
         MyCrystalBlueRock.setRegistryName(new ResourceLocation("chaospersists", "rockcrystalblue"));
    MyCrystalTNTRock = new ItemRock(BaseItemID + 446);
         MyCrystalTNTRock.setRegistryName(new ResourceLocation("chaospersists", "rockcrystaltnt"));
    MyGreenRock = new ItemRock(BaseItemID + 438);
         MyGreenRock.setRegistryName(new ResourceLocation("chaospersists", "rockgreen"));
    MyBlueRock = new ItemRock(BaseItemID + 439);
         MyBlueRock.setRegistryName(new ResourceLocation("chaospersists", "rockblue"));
    MyPurpleRock = new ItemRock(BaseItemID + 440);
         MyPurpleRock.setRegistryName(new ResourceLocation("chaospersists", "rockpurple"));
    MySpikeyRock = new ItemRock(BaseItemID + 441);
         MySpikeyRock.setRegistryName(new ResourceLocation("chaospersists", "rockspikey"));
    MyTNTRock = new ItemRock(BaseItemID + 442);
         MyTNTRock.setRegistryName(new ResourceLocation("chaospersists", "rocktnt"));
    MyRayGun = new ItemRayGun(BaseItemID + 243);
         MyRayGun.setRegistryName(new ResourceLocation("chaospersists", "raygun"));
    MyThunderStaff = new ItemThunderStaff(BaseItemID + 240);
         MyThunderStaff.setRegistryName(new ResourceLocation("chaospersists", "thunderstaff"));
    MyWrench = new ItemWrench(BaseItemID + 472);
         MyWrench.setRegistryName(new ResourceLocation("chaospersists", "wrench"));
    MyAcid = new ItemAcid(BaseItemID + 247);
         MyAcid.setRegistryName(new ResourceLocation("chaospersists", "acid"));
    MyIrukandji = new ItemIrukandji(BaseItemID + 258);
         MyIrukandji.setRegistryName(new ResourceLocation("chaospersists", "deadirukandji"));
    MyIrukandjiArrow = new ItemIrukandjiArrow(BaseItemID + 372);
         MyIrukandjiArrow.setRegistryName(new ResourceLocation("chaospersists", "irukandjiarrow"));
    MyGreenFish = new ItemGenericFish(3, 0.5F);
         MyGreenFish.setRegistryName(new ResourceLocation("chaospersists", "greenfish"));
    MyBlueFish = new ItemGenericFish(4, 0.4F);
         MyBlueFish.setRegistryName(new ResourceLocation("chaospersists", "bluefish"));
    MyPinkFish = new ItemGenericFish(4, 0.6F);
         MyPinkFish.setRegistryName(new ResourceLocation("chaospersists", "pinkfish"));
    MyRockFish = new ItemGenericFish(3, 0.7F);
         MyRockFish.setRegistryName(new ResourceLocation("chaospersists", "rockfish"));
    MyWoodFish = new ItemGenericFish(5, 0.7F);
         MyWoodFish.setRegistryName(new ResourceLocation("chaospersists", "woodfish"));
    MyGreyFish = new ItemGenericFish(5, 0.5F);
         MyGreyFish.setRegistryName(new ResourceLocation("chaospersists", "greyfish"));
    Sifter = new ItemSifter(BaseItemID + 325);
         Sifter.setRegistryName(new ResourceLocation("chaospersists", "sifter"));
    MySquidZooka = new ItemSquidZooka(BaseItemID + 317);
         MySquidZooka.setRegistryName(new ResourceLocation("chaospersists", "squidzookasmall"));

    BerthaHandle = new ItemSalt(BaseItemID + 406);

         BerthaHandle.setRegistryName(new ResourceLocation("chaospersists", "bbhandle"));
    BerthaGuard = new ItemSalt(BaseItemID + 407);
         BerthaGuard.setRegistryName(new ResourceLocation("chaospersists", "bbguard"));
    BerthaBlade = new ItemSalt(BaseItemID + 408);
         BerthaBlade.setRegistryName(new ResourceLocation("chaospersists", "bbblade"));
    MolenoidNose = new ItemSalt(BaseItemID + 409);
         MolenoidNose.setRegistryName(new ResourceLocation("chaospersists", "molenoidnose"));
    SeaMonsterScale = new ItemSalt(BaseItemID + 410);
         SeaMonsterScale.setRegistryName(new ResourceLocation("chaospersists", "seamonsterscale"));
    WormTooth = new ItemSalt(BaseItemID + 411);
         WormTooth.setRegistryName(new ResourceLocation("chaospersists", "wormtooth"));
    TRexTooth = new ItemSalt(BaseItemID + 412);
         TRexTooth.setRegistryName(new ResourceLocation("chaospersists", "trextooth"));
    CaterKillerJaw = new ItemSalt(BaseItemID + 413);
         CaterKillerJaw.setRegistryName(new ResourceLocation("chaospersists", "caterkillerjaw"));
    SeaViperTongue = new ItemSalt(BaseItemID + 414);
         SeaViperTongue.setRegistryName(new ResourceLocation("chaospersists", "seavipertongue"));
    VortexEye = new ItemSalt(BaseItemID + 415);
         VortexEye.setRegistryName(new ResourceLocation("chaospersists", "vortexeye"));

        armorULTIMATE = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Ultimate_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Ultimate_armorstats.head_protection, Ultimate_armorstats.chest_protection, Ultimate_armorstats.leg_protection, Ultimate_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Ultimate_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "ultimate"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorMOBZILLA = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Mobzilla_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Mobzilla_armorstats.head_protection, Mobzilla_armorstats.chest_protection, Mobzilla_armorstats.leg_protection, Mobzilla_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Mobzilla_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "mobzilla"; } public float getToughness() { return 4.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorLAVAEEL = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return LavaEel_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { LavaEel_armorstats.head_protection, LavaEel_armorstats.chest_protection, LavaEel_armorstats.leg_protection, LavaEel_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return LavaEel_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "lavaeel"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorMOTHSCALE = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return MothScale_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { MothScale_armorstats.head_protection, MothScale_armorstats.chest_protection, MothScale_armorstats.leg_protection, MothScale_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return MothScale_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "mothscale"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorEMERALD = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Emerald_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Emerald_armorstats.head_protection, Emerald_armorstats.chest_protection, Emerald_armorstats.leg_protection, Emerald_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Emerald_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "emerald"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorEXPERIENCE = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Experience_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Experience_armorstats.head_protection, Experience_armorstats.chest_protection, Experience_armorstats.leg_protection, Experience_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Experience_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "experience"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorRUBY = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Ruby_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Ruby_armorstats.head_protection, Ruby_armorstats.chest_protection, Ruby_armorstats.leg_protection, Ruby_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Ruby_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "ruby"; } public float getToughness() { return 2.5f; } public float getKnockbackResistance() { return 0.0f; } };

        armorAMETHYST = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Amethyst_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Amethyst_armorstats.head_protection, Amethyst_armorstats.chest_protection, Amethyst_armorstats.leg_protection, Amethyst_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Amethyst_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "amethyst"; } public float getToughness() { return 2.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorPINK = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Pink_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Pink_armorstats.head_protection, Pink_armorstats.chest_protection, Pink_armorstats.leg_protection, Pink_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Pink_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "pink"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorTIGERSEYE = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return TigersEye_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { TigersEye_armorstats.head_protection, TigersEye_armorstats.chest_protection, TigersEye_armorstats.leg_protection, TigersEye_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return TigersEye_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "tigerseye"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorPEACOCK = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Peacock_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Peacock_armorstats.head_protection, Peacock_armorstats.chest_protection, Peacock_armorstats.leg_protection, Peacock_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Peacock_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "peacock"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorROYAL = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Royal_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Royal_armorstats.head_protection, Royal_armorstats.chest_protection, Royal_armorstats.leg_protection, Royal_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Royal_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "royal"; } public float getToughness() { return 5.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorLAPIS = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Lapis_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Lapis_armorstats.head_protection, Lapis_armorstats.chest_protection, Lapis_armorstats.leg_protection, Lapis_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Lapis_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "lapis"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

        armorQUEEN = new IArmorMaterial() { public int getDurabilityForSlot(net.minecraft.inventory.EquipmentSlotType t) { return Queen_armorstats.durability; } public int getDefenseForSlot(net.minecraft.inventory.EquipmentSlotType type) { int[] p = new int[] { Queen_armorstats.head_protection, Queen_armorstats.chest_protection, Queen_armorstats.leg_protection, Queen_armorstats.boot_protection }; return type == net.minecraft.inventory.EquipmentSlotType.FEET ? p[3] : type == net.minecraft.inventory.EquipmentSlotType.LEGS ? p[2] : type == net.minecraft.inventory.EquipmentSlotType.CHEST ? p[1] : p[0]; } public int getEnchantmentValue() { return Queen_armorstats.enchantability; } public SoundEvent getEquipSound() { return SoundEvents.ARMOR_EQUIP_GENERIC; } public Ingredient getRepairIngredient() { return Ingredient.EMPTY; } public String getName() { return "queen"; } public float getToughness() { return 0.0f; } public float getKnockbackResistance() { return 0.0f; } };

    UltimateHelmet = (ItemChaosArmor)new ItemChaosArmor(armorULTIMATE, proxy.setArmorPrefix("ultimate"), 0).setRegistryName(new ResourceLocation("chaospersists", "ultimate_helmet"));
    UltimateBody = (ItemChaosArmor)new ItemChaosArmor(armorULTIMATE, proxy.setArmorPrefix("ultimate"), 1).setRegistryName(new ResourceLocation("chaospersists", "ultimate_chest"));
    UltimateLegs = (ItemChaosArmor)new ItemChaosArmor(armorULTIMATE, proxy.setArmorPrefix("ultimate"), 2).setRegistryName(new ResourceLocation("chaospersists", "ultimate_leggings"));
    UltimateBoots = (ItemChaosArmor)new ItemChaosArmor(armorULTIMATE, proxy.setArmorPrefix("ultimate"), 3).setRegistryName(new ResourceLocation("chaospersists", "ultimate_boots"));
    LavaEelHelmet = (ItemChaosArmor)new ItemChaosArmor(armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 0).setRegistryName(new ResourceLocation("chaospersists", "lavaeel_helmet"));
    LavaEelBody = (ItemChaosArmor)new ItemChaosArmor(armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 1).setRegistryName(new ResourceLocation("chaospersists", "lavaeel_chest"));
    LavaEelLegs = (ItemChaosArmor)new ItemChaosArmor(armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 2).setRegistryName(new ResourceLocation("chaospersists", "lavaeel_leggings"));
    LavaEelBoots = (ItemChaosArmor)new ItemChaosArmor(armorLAVAEEL, proxy.setArmorPrefix("lavaeel"), 3).setRegistryName(new ResourceLocation("chaospersists", "lavaeel_boots"));
    MothScaleHelmet = (ItemChaosArmor)new ItemChaosArmor(armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 0).setRegistryName(new ResourceLocation("chaospersists", "mothscale_helmet"));
    MothScaleBody = (ItemChaosArmor)new ItemChaosArmor(armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 1).setRegistryName(new ResourceLocation("chaospersists", "mothscale_chest"));
    MothScaleLegs = (ItemChaosArmor)new ItemChaosArmor(armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 2).setRegistryName(new ResourceLocation("chaospersists", "mothscale_leggings"));
    MothScaleBoots = (ItemChaosArmor)new ItemChaosArmor(armorMOTHSCALE, proxy.setArmorPrefix("mothscale"), 3).setRegistryName(new ResourceLocation("chaospersists", "mothscale_boots"));
    EmeraldHelmet = (ItemChaosArmor)new ItemChaosArmor(armorEMERALD, proxy.setArmorPrefix("emerald"), 0).setRegistryName(new ResourceLocation("chaospersists", "emerald_helmet"));
    EmeraldBody = (ItemChaosArmor)new ItemChaosArmor(armorEMERALD, proxy.setArmorPrefix("emerald"), 1).setRegistryName(new ResourceLocation("chaospersists", "emerald_chest"));
    EmeraldLegs = (ItemChaosArmor)new ItemChaosArmor(armorEMERALD, proxy.setArmorPrefix("emerald"), 2).setRegistryName(new ResourceLocation("chaospersists", "emerald_leggings"));
    EmeraldBoots = (ItemChaosArmor)new ItemChaosArmor(armorEMERALD, proxy.setArmorPrefix("emerald"), 3).setRegistryName(new ResourceLocation("chaospersists", "emerald_boots"));
    ExperienceHelmet = (ItemChaosArmor)new ItemChaosArmor(armorEXPERIENCE, proxy.setArmorPrefix("experience"), 0).setRegistryName(new ResourceLocation("chaospersists", "experience_helmet"));
    ExperienceBody = (ItemChaosArmor)new ItemChaosArmor(armorEXPERIENCE, proxy.setArmorPrefix("experience"), 1).setRegistryName(new ResourceLocation("chaospersists", "experience_chest"));
    ExperienceLegs = (ItemChaosArmor)new ItemChaosArmor(armorEXPERIENCE, proxy.setArmorPrefix("experience"), 2).setRegistryName(new ResourceLocation("chaospersists", "experience_leggings"));
    ExperienceBoots = (ItemChaosArmor)new ItemChaosArmor(armorEXPERIENCE, proxy.setArmorPrefix("experience"), 3).setRegistryName(new ResourceLocation("chaospersists", "experience_boots"));
    RubyHelmet = (ItemChaosArmor)new ItemChaosArmor(armorRUBY, proxy.setArmorPrefix("ruby"), 0).setRegistryName(new ResourceLocation("chaospersists", "ruby_helmet"));
    RubyBody = (ItemChaosArmor)new ItemChaosArmor(armorRUBY, proxy.setArmorPrefix("ruby"), 1).setRegistryName(new ResourceLocation("chaospersists", "ruby_chest"));
    RubyLegs = (ItemChaosArmor)new ItemChaosArmor(armorRUBY, proxy.setArmorPrefix("ruby"), 2).setRegistryName(new ResourceLocation("chaospersists", "ruby_leggings"));
    RubyBoots = (ItemChaosArmor)new ItemChaosArmor(armorRUBY, proxy.setArmorPrefix("ruby"), 3).setRegistryName(new ResourceLocation("chaospersists", "ruby_boots"));
    AmethystHelmet = (ItemChaosArmor)new ItemChaosArmor(armorAMETHYST, proxy.setArmorPrefix("amethyst"), 0).setRegistryName(new ResourceLocation("chaospersists", "amethyst_helmet"));
    AmethystBody = (ItemChaosArmor)new ItemChaosArmor(armorAMETHYST, proxy.setArmorPrefix("amethyst"), 1).setRegistryName(new ResourceLocation("chaospersists", "amethyst_chest"));
    AmethystLegs = (ItemChaosArmor)new ItemChaosArmor(armorAMETHYST, proxy.setArmorPrefix("amethyst"), 2).setRegistryName(new ResourceLocation("chaospersists", "amethyst_leggings"));
    AmethystBoots = (ItemChaosArmor)new ItemChaosArmor(armorAMETHYST, proxy.setArmorPrefix("amethyst"), 3).setRegistryName(new ResourceLocation("chaospersists", "amethyst_boots"));
    CrystalPinkHelmet = (ItemChaosArmor)new ItemChaosArmor(armorPINK, proxy.setArmorPrefix("pink"), 0).setRegistryName(new ResourceLocation("chaospersists", "pink_helmet"));
    CrystalPinkBody = (ItemChaosArmor)new ItemChaosArmor(armorPINK, proxy.setArmorPrefix("pink"), 1).setRegistryName(new ResourceLocation("chaospersists", "pink_chest"));
    CrystalPinkLegs = (ItemChaosArmor)new ItemChaosArmor(armorPINK, proxy.setArmorPrefix("pink"), 2).setRegistryName(new ResourceLocation("chaospersists", "pink_leggings"));
    CrystalPinkBoots = (ItemChaosArmor)new ItemChaosArmor(armorPINK, proxy.setArmorPrefix("pink"), 3).setRegistryName(new ResourceLocation("chaospersists", "pink_boots"));
    TigersEyeHelmet = (ItemChaosArmor)new ItemChaosArmor(armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 0).setRegistryName(new ResourceLocation("chaospersists", "tigerseye_helmet"));
    TigersEyeBody = (ItemChaosArmor)new ItemChaosArmor(armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 1).setRegistryName(new ResourceLocation("chaospersists", "tigerseye_chest"));
    TigersEyeLegs = (ItemChaosArmor)new ItemChaosArmor(armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 2).setRegistryName(new ResourceLocation("chaospersists", "tigerseye_leggings"));
    TigersEyeBoots = (ItemChaosArmor)new ItemChaosArmor(armorTIGERSEYE, proxy.setArmorPrefix("tigerseye"), 3).setRegistryName(new ResourceLocation("chaospersists", "tigerseye_boots"));
    PeacockFeatherBoots = (ItemChaosArmor)new ItemChaosArmor(armorPEACOCK, proxy.setArmorPrefix("peacock"), 3).setRegistryName(new ResourceLocation("chaospersists", "peacock_boots"));
    PeacockFeatherHelmet = (ItemChaosArmor)new ItemChaosArmor(armorPEACOCK, proxy.setArmorPrefix("peacock"), 0).setRegistryName(new ResourceLocation("chaospersists", "peacock_helmet"));
    PeacockFeatherBody = (ItemChaosArmor)new ItemChaosArmor(armorPEACOCK, proxy.setArmorPrefix("peacock"), 1).setRegistryName(new ResourceLocation("chaospersists", "peacock_chest"));
    PeacockFeatherLegs = (ItemChaosArmor)new ItemChaosArmor(armorPEACOCK, proxy.setArmorPrefix("peacock"), 2).setRegistryName(new ResourceLocation("chaospersists", "peacock_leggings"));
    MobzillaHelmet = (ItemChaosArmor)new ItemChaosArmor(armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 0).setRegistryName(new ResourceLocation("chaospersists", "mobzilla_helmet"));
    MobzillaBody = (ItemChaosArmor)new ItemChaosArmor(armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 1).setRegistryName(new ResourceLocation("chaospersists", "mobzilla_chest"));
    MobzillaLegs = (ItemChaosArmor)new ItemChaosArmor(armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 2).setRegistryName(new ResourceLocation("chaospersists", "mobzilla_leggings"));
    MobzillaBoots = (ItemChaosArmor)new ItemChaosArmor(armorMOBZILLA, proxy.setArmorPrefix("mobzilla"), 3).setRegistryName(new ResourceLocation("chaospersists", "mobzilla_boots"));
    RoyalHelmet = (ItemChaosArmor)new ItemChaosArmor(armorROYAL, proxy.setArmorPrefix("royal"), 0).setRegistryName(new ResourceLocation("chaospersists", "royal_helmet"));
    RoyalBody = (ItemChaosArmor)new ItemChaosArmor(armorROYAL, proxy.setArmorPrefix("royal"), 1).setRegistryName(new ResourceLocation("chaospersists", "royal_chest"));
    RoyalLegs = (ItemChaosArmor)new ItemChaosArmor(armorROYAL, proxy.setArmorPrefix("royal"), 2).setRegistryName(new ResourceLocation("chaospersists", "royal_leggings"));
    RoyalBoots = (ItemChaosArmor)new ItemChaosArmor(armorROYAL, proxy.setArmorPrefix("royal"), 3).setRegistryName(new ResourceLocation("chaospersists", "royal_boots"));
    LapisHelmet = (ItemChaosArmor)new ItemChaosArmor(armorLAPIS, proxy.setArmorPrefix("lapis"), 0).setRegistryName(new ResourceLocation("chaospersists", "lapis_helmet"));
    LapisBody = (ItemChaosArmor)new ItemChaosArmor(armorLAPIS, proxy.setArmorPrefix("lapis"), 1).setRegistryName(new ResourceLocation("chaospersists", "lapis_chest"));
    LapisLegs = (ItemChaosArmor)new ItemChaosArmor(armorLAPIS, proxy.setArmorPrefix("lapis"), 2).setRegistryName(new ResourceLocation("chaospersists", "lapis_leggings"));
    LapisBoots = (ItemChaosArmor)new ItemChaosArmor(armorLAPIS, proxy.setArmorPrefix("lapis"), 3).setRegistryName(new ResourceLocation("chaospersists", "lapis_boots"));
    QueenHelmet = (ItemChaosArmor)new ItemChaosArmor(armorQUEEN, proxy.setArmorPrefix("queen"), 0).setRegistryName(new ResourceLocation("chaospersists", "queen_helmet"));
    QueenBody = (ItemChaosArmor)new ItemChaosArmor(armorQUEEN, proxy.setArmorPrefix("queen"), 1).setRegistryName(new ResourceLocation("chaospersists", "queen_chest"));
    QueenLegs = (ItemChaosArmor)new ItemChaosArmor(armorQUEEN, proxy.setArmorPrefix("queen"), 2).setRegistryName(new ResourceLocation("chaospersists", "queen_leggings"));
    QueenBoots = (ItemChaosArmor)new ItemChaosArmor(armorQUEEN, proxy.setArmorPrefix("queen"), 3).setRegistryName(new ResourceLocation("chaospersists", "queen_boots"));

    MyOreSaltBlock = new OreSalt();

         MyOreSaltBlock.setRegistryName(new ResourceLocation("chaospersists", "oresalt"));
    MySalt = new ItemSalt(BaseItemID + 178);
         MySalt.setRegistryName(new ResourceLocation("chaospersists", "salt"));
    MyPopcorn = new ItemPopcorn(1, 0.5F, false);
         MyPopcorn.setRegistryName(new ResourceLocation("chaospersists", "popcorn"));
    MyButteredPopcorn = new ItemPopcorn(2, 0.6F, false);
         MyButteredPopcorn.setRegistryName(new ResourceLocation("chaospersists", "popcorn_buttered"));
    MyButteredSaltedPopcorn = new ItemPopcorn(3, 0.75F, false);
         MyButteredSaltedPopcorn.setRegistryName(new ResourceLocation("chaospersists", "popcorn_buttered_salted"));
    MyPopcornBag = new ItemPopcorn(10, 1.25F, false);
         MyPopcornBag.setRegistryName(new ResourceLocation("chaospersists", "popcorn_bag"));
    MyButter = new ItemPopcorn(1, 0.5F, false);
         MyButter.setRegistryName(new ResourceLocation("chaospersists", "butter"));
    MyCornDog = new ItemPopcorn(16, 2.5F, false);
         MyCornDog.setRegistryName(new ResourceLocation("chaospersists", "corndog_cooked"));
    MyRawCornDog = new ItemPopcorn(4, 0.6F, false);
         MyRawCornDog.setRegistryName(new ResourceLocation("chaospersists", "corndog_raw"));
    MyButterCandy = new ItemSunFish(4, 0.5F);
         MyButterCandy.setRegistryName(new ResourceLocation("chaospersists", "buttercandy"));
    MyBacon = new ItemSunFish(14, 1.5F);
         MyBacon.setRegistryName(new ResourceLocation("chaospersists", "cookedbacon"));
    MyRawBacon = new ItemPopcorn(8, 1.0F, false);
         MyRawBacon.setRegistryName(new ResourceLocation("chaospersists", "bacon"));
    MyCrabMeat = new ItemSunFish(6, 0.75F);
         MyCrabMeat.setRegistryName(new ResourceLocation("chaospersists", "cookedcrabmeat"));
    MyRawCrabMeat = new ItemPopcorn(4, 0.25F, false);
         MyRawCrabMeat.setRegistryName(new ResourceLocation("chaospersists", "crabmeat"));
    MyCheese = new ItemPopcorn(4, 0.5F, false);
         MyCheese.setRegistryName(new ResourceLocation("chaospersists", "cheese"));
    MySalad = new ItemPopcorn(10, 0.95F, false);
         MySalad.setRegistryName(new ResourceLocation("chaospersists", "salad"));
    MyBLT = new ItemPopcorn(12, 0.95F, false);
         MyBLT.setRegistryName(new ResourceLocation("chaospersists", "blt_sandwich"));
    MyCrabbyPatty = new ItemPopcorn(16, 2.35F, false);
         MyCrabbyPatty.setRegistryName(new ResourceLocation("chaospersists", "crabbypatty"));
    MyOreRubyBlock = new OreRuby();
         MyOreRubyBlock.setRegistryName(new ResourceLocation("chaospersists", "oreruby"));
    MyRuby = new ItemSalt(BaseItemID + 270);
         MyRuby.setRegistryName(new ResourceLocation("chaospersists", "ruby"));
    MyOreAmethystBlock = new OreAmethyst();
         MyOreAmethystBlock.setRegistryName(new ResourceLocation("chaospersists", "oreamethyst"));
    MyAmethyst = new ItemSalt(BaseItemID + 260);
         MyAmethyst.setRegistryName(new ResourceLocation("chaospersists", "amethyst"));
    UraniumNugget = new ItemSalt(BaseItemID + 150);
         UraniumNugget.setRegistryName(new ResourceLocation("chaospersists", "uranium_nugget"));
    TitaniumNugget = new ItemSalt(BaseItemID + 151);
         TitaniumNugget.setRegistryName(new ResourceLocation("chaospersists", "titanium_nugget"));
    CrystalStone = new OreBasicStone(2.0F, 10.0F);
         CrystalStone.setRegistryName(new ResourceLocation("chaospersists", "crystalstone"));
    CrystalCoal = new OreCrystal(0.6F, 6.0F, 20.0F);
         CrystalCoal.setRegistryName(new ResourceLocation("chaospersists", "crystalcoal"));
    CrystalGrass = new CrystalGrass(0.6F, 2.0F);
         CrystalGrass.setRegistryName(new ResourceLocation("chaospersists", "crystalgrass"));
    CrystalCrystal = new OreCrystalCrystal(0.4F, 12.0F, 40.0F);
         CrystalCrystal.setRegistryName(new ResourceLocation("chaospersists", "crystalcrystal"));
    TigersEye = new OreCrystalCrystal(0.5F, 15.0F, 60.0F);
         TigersEye.setRegistryName(new ResourceLocation("chaospersists", "tigerseye"));
    CrystalPlanksBlock = new CrystalWood(1.5F, 4.0F);
         CrystalPlanksBlock.setRegistryName(new ResourceLocation("chaospersists", "crystalplanks"));
    CrystalWorkbenchBlock = new CrystalWorkbench(1.0F, 5.0F);
         CrystalWorkbenchBlock.setRegistryName(new ResourceLocation("chaospersists", "crystalworkbench"));
    CrystalFurnaceBlock = new CrystalFurnace(2.0F, 10.0F);
    CrystalFurnaceBlock.setRegistryName(new ResourceLocation("chaospersists", "crystalfurnace"));
    MyPeacock = new ItemPopcorn(12, 1.4F, false);
         MyPeacock.setRegistryName(new ResourceLocation("chaospersists", "cookedpeacock"));
    MyRawPeacock = new ItemPopcorn(6, 0.7F, false);
         MyRawPeacock.setRegistryName(new ResourceLocation("chaospersists", "rawpeacock"));
    CrystalRat = new OreBasicStone(2.5F, 14.0F);
         CrystalRat.setRegistryName(new ResourceLocation("chaospersists", "crystalrat"));
    CrystalFairy = new OreBasicStone(2.5F, 14.0F);
         CrystalFairy.setRegistryName(new ResourceLocation("chaospersists", "crystalfairy"));
    RedAntTroll = new OreBasicStone(2.5F, 14.0F);
         RedAntTroll.setRegistryName(new ResourceLocation("chaospersists", "redanttroll"));
    TermiteTroll = new OreBasicStone(2.5F, 14.0F);
         TermiteTroll.setRegistryName(new ResourceLocation("chaospersists", "termitetroll"));

    MyRTPBlock = new RTPBlock();

         MyRTPBlock.setRegistryName(new ResourceLocation("chaospersists", "blockteleport"));
    MyStepUp = new StepUp(BaseItemID + 232);
         MyStepUp.setRegistryName(new ResourceLocation("chaospersists", "step_up"));
    MyStepDown = new StepDown(BaseItemID + 233);
         MyStepDown.setRegistryName(new ResourceLocation("chaospersists", "step_down"));
    MyStepAccross = new StepAccross(BaseItemID + 234);
         MyStepAccross.setRegistryName(new ResourceLocation("chaospersists", "step_accross"));
    MyMoleDirtBlock = new MoleDirtBlock(0.6F).setRegistryName(new ResourceLocation("chaospersists", "moledirt"));

    initializeCagesAndEggs();

    MyStrawberry = new ItemStrawberry(2, 0.65F);

         MyStrawberry.setRegistryName(new ResourceLocation("chaospersists", "strawberry"));
    MyStrawberryPlant = new BlockStrawberry().setRegistryName(new ResourceLocation("chaospersists", "strawberry_plant"));
    MyStrawberrySeed = new ItemStrawberrySeed(MyStrawberryPlant, Blocks.FARMLAND);
         MyStrawberrySeed.setRegistryName(new ResourceLocation("chaospersists", "strawberry_seed"));
    MyButterflyPlant = new BlockButterflyPlant().setRegistryName(new ResourceLocation("chaospersists", "butterfly_plant"));
    MyButterflySeed = new ItemButterflySeed(MyButterflyPlant, Blocks.FARMLAND);
         MyButterflySeed.setRegistryName(new ResourceLocation("chaospersists", "butterfly_seed"));
    MyMothPlant = new BlockMothPlant().setRegistryName(new ResourceLocation("chaospersists", "moth_plant"));
    MyMothSeed = new ItemMothSeed(MyMothPlant, Blocks.FARMLAND);
         MyMothSeed.setRegistryName(new ResourceLocation("chaospersists", "moth_seed"));
    MyMosquitoPlant = new BlockMosquitoPlant().setRegistryName(new ResourceLocation("chaospersists", "mosquito_plant"));
    MyMosquitoSeed = new ItemMosquitoSeed(MyMosquitoPlant, Blocks.FARMLAND);
         MyMosquitoSeed.setRegistryName(new ResourceLocation("chaospersists", "mosquito_seed"));
    MyFireflyPlant = new BlockFireflyPlant().setRegistryName(new ResourceLocation("chaospersists", "firefly_plant"));
    MyFireflySeed = new ItemFireflySeed(MyFireflyPlant, Blocks.FARMLAND);
         MyFireflySeed.setRegistryName(new ResourceLocation("chaospersists", "firefly_seed"));
    MyRadishPlant = new BlockRadish();
         MyRadishPlant.setRegistryName(new ResourceLocation("chaospersists", "radish_plant"));
    MyRadish = new ItemRadish(2, 0.45F, MyRadishPlant, Blocks.FARMLAND);
         MyRadish.setRegistryName(new ResourceLocation("chaospersists", "radish"));
    MyCherry = new ItemStrawberry(3, 0.45F);
         MyCherry.setRegistryName(new ResourceLocation("chaospersists", "cherries"));
    MyPeach = new ItemStrawberry(4, 0.55F);
         MyPeach.setRegistryName(new ResourceLocation("chaospersists", "peach"));
    MyCrystalApple = new ItemSunFish(5, 0.85F);
         MyCrystalApple.setRegistryName(new ResourceLocation("chaospersists", "crystalapple"));
    MyLove = new ItemSunFish(8, 0.95F);
         MyLove.setRegistryName(new ResourceLocation("chaospersists", "heart"));
    MyRicePlant = new BlockRice();
         MyRicePlant.setRegistryName(new ResourceLocation("chaospersists", "rice_plant"));
    MyRice = new ItemRadish(5, 0.65F, MyRicePlant, CrystalGrass);
         MyRice.setRegistryName(new ResourceLocation("chaospersists", "rice"));

    MyElevator = new ItemElevator(BaseItemID + 235);

         MyElevator.setRegistryName(new ResourceLocation("chaospersists", "elevator"));

    MyCornPlant1 = new BlockCorn();

         MyCornPlant1.setRegistryName(new ResourceLocation("chaospersists", "corn_plant0"));
    MyCornPlant2 = new BlockCorn();
         MyCornPlant2.setRegistryName(new ResourceLocation("chaospersists", "corn_plant1"));
    MyCornPlant3 = new BlockCorn();
         MyCornPlant3.setRegistryName(new ResourceLocation("chaospersists", "corn_plant2"));
    MyCornPlant4 = new BlockCorn();
         MyCornPlant4.setRegistryName(new ResourceLocation("chaospersists", "corn_plant3"));
    MyCornCob = new ItemCornCob(6, 0.75F, MyCornPlant1, Blocks.FARMLAND);
         MyCornCob.setRegistryName(new ResourceLocation("chaospersists", "corn_seed"));
    MyQuinoaPlant1 = new BlockQuinoa();
         MyQuinoaPlant1.setRegistryName(new ResourceLocation("chaospersists", "quinoa_0"));
    MyQuinoaPlant2 = new BlockQuinoa();
         MyQuinoaPlant2.setRegistryName(new ResourceLocation("chaospersists", "quinoa_1"));
    MyQuinoaPlant3 = new BlockQuinoa();
         MyQuinoaPlant3.setRegistryName(new ResourceLocation("chaospersists", "quinoa_2"));
    MyQuinoaPlant4 = new BlockQuinoa();
         MyQuinoaPlant4.setRegistryName(new ResourceLocation("chaospersists", "quinoa_3"));
    MyQuinoa = new ItemCornCob(7, 0.85F, MyQuinoaPlant1, CrystalGrass);
         MyQuinoa.setRegistryName(new ResourceLocation("chaospersists", "quinoa"));

    MyTomatoPlant1 = new BlockTomato();

         MyTomatoPlant1.setRegistryName(new ResourceLocation("chaospersists", "tomato_plant0"));
    MyTomatoPlant2 = new BlockTomato();
         MyTomatoPlant2.setRegistryName(new ResourceLocation("chaospersists", "tomato_plant1"));
    MyTomatoPlant3 = new BlockTomato();
         MyTomatoPlant3.setRegistryName(new ResourceLocation("chaospersists", "tomato_plant2"));
    MyTomatoPlant4 = new BlockTomato();
         MyTomatoPlant4.setRegistryName(new ResourceLocation("chaospersists", "tomato_plant3"));
    MyTomato = new ItemTomato(4, 0.55F, MyTomatoPlant1, Blocks.FARMLAND);
         MyTomato.setRegistryName(new ResourceLocation("chaospersists", "tomato_seed"));
    MyLettucePlant1 = new BlockLettuce();
         MyLettucePlant1.setRegistryName(new ResourceLocation("chaospersists", "lettuce_0"));
    MyLettucePlant2 = new BlockLettuce();
         MyLettucePlant2.setRegistryName(new ResourceLocation("chaospersists", "lettuce_1"));
    MyLettucePlant3 = new BlockLettuce();
         MyLettucePlant3.setRegistryName(new ResourceLocation("chaospersists", "lettuce_2"));
    MyLettucePlant4 = new BlockLettuce();
         MyLettucePlant4.setRegistryName(new ResourceLocation("chaospersists", "lettuce_3"));
    MyLettuce = new ItemLettuce(3, 0.45F, MyLettucePlant1, Blocks.FARMLAND);
         MyLettuce.setRegistryName(new ResourceLocation("chaospersists", "lettuce_seed"));

    MagicApple = new ItemMagicApple(BaseItemID + 236);

         MagicApple.setRegistryName(new ResourceLocation("chaospersists", "magicapple"));
    MinersDream = new ItemMinersDream(BaseItemID + 237);
         MinersDream.setRegistryName(new ResourceLocation("chaospersists", "minersdream"));
    ExtremeTorch = new BlockExtremeTorch(1.0F).setRegistryName(new ResourceLocation("chaospersists", "extremetorch"));
    KrakenRepellent = new KrakenRepellent(0.8F).setRegistryName(new ResourceLocation("chaospersists", "krakenrepellent"));
    MyIslandBlock = new IslandBlock(0.9F).setRegistryName(new ResourceLocation("chaospersists", "island"));
    CreeperRepellent = new CreeperRepellent(0.8F).setRegistryName(new ResourceLocation("chaospersists", "creeperrepellent"));
    ZooCage2 = new ZooCage(0, 3);
         ZooCage2.setRegistryName(new ResourceLocation("chaospersists", "zoo2"));
    ZooCage4 = new ZooCage(0, 5);
         ZooCage4.setRegistryName(new ResourceLocation("chaospersists", "zoo4"));
    ZooCage6 = new ZooCage(0, 9);
         ZooCage6.setRegistryName(new ResourceLocation("chaospersists", "zoo6"));
    ZooCage8 = new ZooCage(0, 13);
         ZooCage8.setRegistryName(new ResourceLocation("chaospersists", "zoo8"));
    ZooCage10 = new ZooCage(0, 17);
         ZooCage10.setRegistryName(new ResourceLocation("chaospersists", "zoo10"));
    InstantShelter = new InstantShelter(BaseItemID + 327);
         InstantShelter.setRegistryName(new ResourceLocation("chaospersists", "instantshelter"));
    InstantGarden = new InstantGarden(BaseItemID + 328);
         InstantGarden.setRegistryName(new ResourceLocation("chaospersists", "instantgarden"));
    CrystalTorch = new BlockCrystalTorch(0.99F).setRegistryName(new ResourceLocation("chaospersists", "crystaltorch"));
    MyKingSpawnerBlock = new KingSpawnerBlock(0.9F).setRegistryName(new ResourceLocation("chaospersists", "kingspawner"));
    MyQueenSpawnerBlock = new QueenSpawnerBlock(0.9F).setRegistryName(new ResourceLocation("chaospersists", "queenspawner"));
    RandomDungeon = new ItemRandomDungeon(BaseItemID + 421);
         RandomDungeon.setRegistryName(new ResourceLocation("chaospersists", "randomdungeon"));
    MyDungeonSpawnerBlock = new DungeonSpawnerBlock(0.9F).setRegistryName(new ResourceLocation("chaospersists", "dungeonspawner"));

    MyAppleLeaves = (BlockAppleLeaves)new BlockAppleLeaves().setRegistryName(new ResourceLocation("chaospersists", "leaves_apple"));
    MyAppleSeed = new ItemAppleSeed(BaseItemID + 211);
         MyAppleSeed.setRegistryName(new ResourceLocation("chaospersists", "appletree_seed"));
    MySkyTreeLog = (BlockSkyTreeLog)new BlockSkyTreeLog(0.2F).setRegistryName(new ResourceLocation("chaospersists", "skytreelog"));

    MyDT = (BlockDuplicatorLog)new BlockDuplicatorLog(0.2F).setRegistryName(new ResourceLocation("chaospersists", "duplicatortreelog"));
    MyExperienceLeaves = (BlockExperienceLeaves)new BlockExperienceLeaves().setRegistryName(new ResourceLocation("chaospersists", "leaves_experience"));
    MyExperienceCatcher = new ExperienceCatcher(BaseItemID + 238);
         MyExperienceCatcher.setRegistryName(new ResourceLocation("chaospersists", "experiencecatcher"));
    MyExperienceTreeSeed = new ItemExperienceTreeSeed(BaseItemID + 216);
         MyExperienceTreeSeed.setRegistryName(new ResourceLocation("chaospersists", "experiencetree_seed"));
    MyExperiencePlant = new BlockExperiencePlant();
         MyExperiencePlant.setRegistryName(new ResourceLocation("chaospersists", "experiencesapling"));
    MyDeadStinkBug = new ItemSalt(BaseItemID + 155);
         MyDeadStinkBug.setRegistryName(new ResourceLocation("chaospersists", "deadstinkbug"));
    MyFlowerPinkBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "flower_pink"));
    MyFlowerBlueBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "flower_blue"));
    MyFlowerBlackBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "flower_black"));
    MyFlowerScaryBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "flower_scary"));
    MyScaryLeaves = (BlockScaryLeaves)new BlockScaryLeaves(0.2F).setRegistryName(new ResourceLocation("chaospersists", "leaves_scary"));
    MyCherryLeaves = (BlockScaryLeaves)new BlockScaryLeaves(0.15F).setRegistryName(new ResourceLocation("chaospersists", "leaves_cherry"));
    MyPeachLeaves = (BlockScaryLeaves)new BlockScaryLeaves(0.15F).setRegistryName(new ResourceLocation("chaospersists", "leaves_peach"));
    MyCherrySeed = new ItemAppleSeed(BaseItemID + 217);
         MyCherrySeed.setRegistryName(new ResourceLocation("chaospersists", "cherrytree_seed"));
    MyPeachSeed = new ItemAppleSeed(BaseItemID + 218);
         MyPeachSeed.setRegistryName(new ResourceLocation("chaospersists", "peachtree_seed"));
    CrystalFlowerRedBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "crystalflower_red"));
    CrystalFlowerGreenBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "crystalflower_green"));
    CrystalFlowerBlueBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "crystalflower_blue"));
    CrystalFlowerYellowBlock = (MyBlockFlower)new MyBlockFlower().setRegistryName(new ResourceLocation("chaospersists", "crystalflower_yellow"));
    MyCrystalLeaves = (BlockCrystalLeaves)new BlockCrystalLeaves(0.2F).setRegistryName(new ResourceLocation("chaospersists", "crystaltreeleaves"));
    MyCrystalTreeLog = (BlockCrystalTreeLog)new BlockCrystalTreeLog(0.2F).setRegistryName(new ResourceLocation("chaospersists", "crystaltreelog"));
    MyCrystalLeaves2 = (BlockCrystalLeaves)new BlockCrystalLeaves(0.25F).setRegistryName(new ResourceLocation("chaospersists", "crystaltreeleaves2"));
    MyCrystalLeaves3 = (BlockCrystalLeaves)new BlockCrystalLeaves(0.25F).setRegistryName(new ResourceLocation("chaospersists", "crystaltreeleaves3"));
    MyCrystalPlant = new BlockCrystalPlant();
         MyCrystalPlant.setRegistryName(new ResourceLocation("chaospersists", "crystalsapling"));
    MyCrystalPlant2 = new BlockCrystalPlant();
         MyCrystalPlant2.setRegistryName(new ResourceLocation("chaospersists", "crystalsapling2"));
    MyCrystalPlant3 = new BlockCrystalPlant();
         MyCrystalPlant3.setRegistryName(new ResourceLocation("chaospersists", "crystalsapling3"));

    MyEnderPearlBlock = new OreGenericEgg();

         MyEnderPearlBlock.setRegistryName(new ResourceLocation("chaospersists", "blockenderpearl"));
    MyEyeOfEnderBlock = new OreGenericEgg();
         MyEyeOfEnderBlock.setRegistryName(new ResourceLocation("chaospersists", "blockeyeofender"));
  }

  private final Map<String, Integer> recipeNameUseCounts = new HashMap<String, Integer>();
  private final java.util.List<IRecipe<?>> chaosPendingCraftingRecipes = new java.util.ArrayList<IRecipe<?>>();
  private final java.util.List<IRecipe<?>> chaosPendingSmeltingRecipes = new java.util.ArrayList<IRecipe<?>>();
  private int chaosPendingSmeltingRecipeIndex = 0;

  private Ingredient legacyRecipeIngredient(Object value) {
    if (value instanceof Ingredient) {
      return (Ingredient) value;
    }
    if (value instanceof ItemStack) {
      return Ingredient.of((ItemStack) value);
    }
    if (value instanceof Item) {
      return Ingredient.of((Item) value);
    }
    if (value instanceof Block) {
      return Ingredient.of((Block) value);
    }
    if (value instanceof net.minecraft.util.IItemProvider) {
      return Ingredient.of((net.minecraft.util.IItemProvider) value);
    }
    throw new IllegalArgumentException("Unsupported recipe ingredient: " + value);
  }

  /** 1.12.2 {@code GameRegistry.addShapedRecipe} / {@code addSmelting}; maps are immutable after datapack load in 1.16.5. */
  private void injectPendingRecipes(RecipeManager recipeManager) {
    Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> loaded = ObfuscationReflectionHelper.getPrivateValue(
            RecipeManager.class, recipeManager, "recipes");
    Map<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> mutableByType = new HashMap<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>>();
    for (Map.Entry<IRecipeType<?>, Map<ResourceLocation, IRecipe<?>>> entry : loaded.entrySet()) {
      mutableByType.put(entry.getKey(), new HashMap<ResourceLocation, IRecipe<?>>(entry.getValue()));
    }
    Map<ResourceLocation, IRecipe<?>> craftingRecipes = mutableByType.get(IRecipeType.CRAFTING);
    if (craftingRecipes == null) {
      craftingRecipes = new HashMap<ResourceLocation, IRecipe<?>>();
      mutableByType.put(IRecipeType.CRAFTING, craftingRecipes);
    }
    for (IRecipe<?> recipe : chaosPendingCraftingRecipes) {
      craftingRecipes.put(recipe.getId(), recipe);
    }
    Map<ResourceLocation, IRecipe<?>> smeltingRecipes = mutableByType.get(IRecipeType.SMELTING);
    if (smeltingRecipes == null) {
      smeltingRecipes = new HashMap<ResourceLocation, IRecipe<?>>();
      mutableByType.put(IRecipeType.SMELTING, smeltingRecipes);
    }
    for (IRecipe<?> recipe : chaosPendingSmeltingRecipes) {
      smeltingRecipes.put(recipe.getId(), recipe);
    }
    ObfuscationReflectionHelper.setPrivateValue(RecipeManager.class, recipeManager, mutableByType, "recipes");
  }

  private void addSmeltingLegacy(Object input, ItemStack output, float experience) {
    ResourceLocation id = new ResourceLocation("chaospersists", "legacy_smelt_" + (chaosPendingSmeltingRecipeIndex++));
    chaosPendingSmeltingRecipes.add(new AbstractCookingRecipe(IRecipeType.SMELTING, id, "chaospersists", legacyRecipeIngredient(input), output.copy(), experience, 200) {
      @Override
      public IRecipeSerializer<?> getSerializer() {
        return Registry.RECIPE_SERIALIZER.get(new ResourceLocation("smelting"));
      }
    });
  }

  @SubscribeEvent
  public void onServerStarting(FMLServerStartingEvent event) {
    injectPendingRecipes(event.getServer().getRecipeManager());
    logDimensionWorldAvailability(event.getServer());
    event.getServer().getCommands().getDispatcher().register(new CommandUtopia().register());
    event.getServer().getCommands().getDispatcher().register(new CommandVillageMania().register());
    event.getServer().getCommands().getDispatcher().register(new CommandChaos().register());
    event.getServer().getCommands().getDispatcher().register(new CommandCrystal().register());
    event.getServer().getCommands().getDispatcher().register(new CommandDanger().register());
    event.getServer().getCommands().getDispatcher().register(new CommandMining().register());
  }

  public static int[] getRegisteredChaosDimensionIds() {
    return new int[] { DimensionID, DimensionID2, DimensionID3, DimensionID4, DimensionID5, DimensionID6 };
  }

  public static DimensionType getDimensionTypeForLegacyId(int dimensionId) {
    DimensionType type = chaosDimensionTypeByLegacyId.get(Integer.valueOf(dimensionId));
    return type != null ? type : createChaosDimensionType(true);
  }

  private static DimensionType createChaosDimensionType(boolean hasSkyLight) {
    if (!hasSkyLight) {
      return ObfuscationReflectionHelper.getPrivateValue(DimensionType.class, null, "DEFAULT_OVERWORLD_CAVES");
    }
    return ObfuscationReflectionHelper.getPrivateValue(DimensionType.class, null, "DEFAULT_OVERWORLD");
  }

  private ResourceLocation nextRecipeId(ResourceLocation baseId)
  {
    String key = baseId.toString();
    Integer seen = recipeNameUseCounts.get(key);
    if (seen == null) {
      recipeNameUseCounts.put(key, Integer.valueOf(1));
      return baseId;
    }
    int suffix = seen.intValue();
    recipeNameUseCounts.put(key, Integer.valueOf(suffix + 1));
    return new ResourceLocation(baseId.getNamespace(), baseId.getPath() + "_" + suffix);
  }

  private void addShapedRecipe(ResourceLocation name, ResourceLocation group, ItemStack output, Object... params)
  {
    if (commonSetupEvent != null) {
      commonSetupEvent.enqueueWork(() -> {
        ResourceLocation id = nextRecipeId(name);
        java.util.List<String> pattern = new java.util.ArrayList<String>();
        int index = 0;
        while (index < params.length && params[index] instanceof String) {
          pattern.add((String) params[index]);
          ++index;
        }
        java.util.Map<Character, Ingredient> keys = new java.util.HashMap<Character, Ingredient>();
        while (index + 1 < params.length) {
          char key;
          if (params[index] instanceof Character) {
            key = ((Character) params[index]).charValue();
          } else {
            key = ((String) params[index]).charAt(0);
          }
          keys.put(key, legacyRecipeIngredient(params[index + 1]));
          index += 2;
        }
        int height = pattern.size();
        int width = height > 0 ? pattern.get(0).length() : 0;
        net.minecraft.util.NonNullList<Ingredient> ingredients = net.minecraft.util.NonNullList.withSize(width * height, Ingredient.EMPTY);
        for (int row = 0; row < height; ++row) {
          String line = pattern.get(row);
          for (int col = 0; col < width; ++col) {
            char symbol = line.charAt(col);
            if (symbol != ' ') {
              ingredients.set(row * width + col, keys.get(symbol));
            }
          }
        }
        chaosPendingCraftingRecipes.add(new ShapedRecipe(id, group.toString(), width, height, ingredients, output.copy()));
      });
    }
  }

  private void addShapelessRecipe(ResourceLocation name, ResourceLocation group, ItemStack output, Ingredient... ingredients)
  {
    if (commonSetupEvent != null) {
      commonSetupEvent.enqueueWork(() -> {
        ResourceLocation id = nextRecipeId(name);
        net.minecraft.util.NonNullList<Ingredient> recipeIngredients = net.minecraft.util.NonNullList.create();
        for (Ingredient ingredient : ingredients) {
          recipeIngredients.add(ingredient);
        }
        chaosPendingCraftingRecipes.add(new net.minecraft.item.crafting.ShapelessRecipe(id, group.toString(), output.copy(), recipeIngredients));
      });
    }
  }

  private ItemStack createVanillaSpawnEgg(String entityId)
  {
    if ("spider".equals(entityId)) {
      return new ItemStack(Items.SPIDER_SPAWN_EGG);
    }
    if ("bat".equals(entityId)) {
      return new ItemStack(Items.BAT_SPAWN_EGG);
    }
    if ("cow".equals(entityId)) {
      return new ItemStack(Items.COW_SPAWN_EGG);
    }
    if ("pig".equals(entityId)) {
      return new ItemStack(Items.PIG_SPAWN_EGG);
    }
    if ("SquidEntity".equals(entityId) || "squid".equals(entityId)) {
      return new ItemStack(Items.SQUID_SPAWN_EGG);
    }
    if ("chicken".equals(entityId)) {
      return new ItemStack(Items.CHICKEN_SPAWN_EGG);
    }
    if ("creeper".equals(entityId)) {
      return new ItemStack(Items.CREEPER_SPAWN_EGG);
    }
    if ("skeleton".equals(entityId)) {
      return new ItemStack(Items.SKELETON_SPAWN_EGG);
    }
    if ("zombie".equals(entityId)) {
      return new ItemStack(Items.ZOMBIE_SPAWN_EGG);
    }
    if ("slime".equals(entityId)) {
      return new ItemStack(Items.SLIME_SPAWN_EGG);
    }
    if ("ghast".equals(entityId)) {
      return new ItemStack(Items.GHAST_SPAWN_EGG);
    }
    if ("zombie_pigman".equals(entityId)) {
      return new ItemStack(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG);
    }
    if ("enderman".equals(entityId)) {
      return new ItemStack(Items.ENDERMAN_SPAWN_EGG);
    }
    if ("cave_spider".equals(entityId)) {
      return new ItemStack(Items.CAVE_SPIDER_SPAWN_EGG);
    }
    if ("silverfish".equals(entityId)) {
      return new ItemStack(Items.SILVERFISH_SPAWN_EGG);
    }
    if ("magma_cube".equals(entityId)) {
      return new ItemStack(Items.MAGMA_CUBE_SPAWN_EGG);
    }
    if ("witch".equals(entityId)) {
      return new ItemStack(Items.WITCH_SPAWN_EGG);
    }
    if ("sheep".equals(entityId)) {
      return new ItemStack(Items.SHEEP_SPAWN_EGG);
    }
    if ("wolf".equals(entityId)) {
      return new ItemStack(Items.WOLF_SPAWN_EGG);
    }
    if ("mooshroom".equals(entityId)) {
      return new ItemStack(Items.MOOSHROOM_SPAWN_EGG);
    }
    if ("ocelot".equals(entityId)) {
      return new ItemStack(Items.OCELOT_SPAWN_EGG);
    }
    if ("blaze".equals(entityId)) {
      return new ItemStack(Items.BLAZE_SPAWN_EGG);
    }
    if ("villager".equals(entityId)) {
      return new ItemStack(Items.VILLAGER_SPAWN_EGG);
    }
    return ItemStack.EMPTY;
  }

  private void onRegisterBlocks(net.minecraftforge.event.RegistryEvent.Register<Block> event) {
    chaosPrepareContent();
    if (!chaosRegistryRegistered) {
      make_some_more_things();
      chaosRegistryRegistered = true;
    }
  }

  private void onRegisterTileEntities(net.minecraftforge.event.RegistryEvent.Register<net.minecraft.tileentity.TileEntityType<?>> event) {
    chaosPrepareContent();
    if (!chaosTileEntitiesRegistered) {
      TileEntityCrystalFurnace.TYPE = TileEntityCrystalFurnace.createType();
      TileEntityCrystalFurnace.TYPE.setRegistryName(new ResourceLocation("chaospersists", "crystalfurnace"));
      event.getRegistry().register(TileEntityCrystalFurnace.TYPE);
      chaosTileEntitiesRegistered = true;
    }
  }

  private void onRegisterContainers(net.minecraftforge.event.RegistryEvent.Register<net.minecraft.inventory.container.ContainerType<?>> event) {
    chaosPrepareContent();
    if (!chaosContainersRegistered) {
      ContainerCrystalWorkbench.TYPE = ContainerCrystalWorkbench.createContainerType();
      ContainerCrystalWorkbench.TYPE.setRegistryName(new ResourceLocation("chaospersists", "crystalworkbench"));
      event.getRegistry().register(ContainerCrystalWorkbench.TYPE);
      chaosContainersRegistered = true;
    }
  }

  private void chaosPrepareContent() {
    if (chaosContentPrepared) {
      return;
    }
    chaosRunPreInitContent();
    chaosContentPrepared = true;
  }

  private void make_some_more_things()
  {
    recipeNameUseCounts.clear();
    make_some_more_things_part1();
    make_some_more_things_part2();
    make_some_more_things_part3();
    make_some_more_things_part4();
    make_some_more_things_part5();
    make_some_more_things_part6();
    make_some_more_things_part7();
    make_some_more_things_part8();
  }

  private void make_some_more_things_recipes()
  {
    make_some_more_things_part9();
  }

  private void make_some_more_things_part1()
  {
     ForgeRegistries.BLOCKS.register(MySpiderSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBatSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCowSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyPigSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySquidSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyChickenSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCreeperSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySkeletonSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyZombieSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySlimeSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGhastSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyZombiePigmanSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEndermanSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCaveSpiderSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySilverfishSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyMagmaCubeSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyWitchSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySheepSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyWolfSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyMooshroomSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyWitherBossSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGirlfriendSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBoyfriendSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyRedCowSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCrystalCowSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyVillagerSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGoldCowSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEnchantedCowSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyMOTHRASpawnBlock);
     ForgeRegistries.BLOCKS.register(MyAloSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCryoSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCamaSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyVeloSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyHydroSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBasilSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyDragonflySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEmperorScorpionSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyScorpionSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCaveFisherSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySpyroSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBaryonyxSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGammaMetroidSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCockateilSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyKyuubiSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyAlienSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyIronGolemSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySnowGolemSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEnderDragonSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyOcelotSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyWitherSkeletonSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBlazeSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyAttackSquidSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyWaterDragonSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCephadromeSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyKrakenSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyLizardSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyDragonSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBeeSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyHorseSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTrooperBugSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySpitBugSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyStinkBugSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyOstrichSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGazelleSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyChipmunkSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCreepingHorrorSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTerribleTerrorSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCliffRacerSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTriffidSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyPitchBlackSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyLurkingTerrorSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGodzillaPartSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGodzillaSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTheKingPartSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTheKingSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTheQueenPartSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTheQueenSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySmallWormSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyMediumWormSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyLargeWormSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCassowarySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCloudSharkSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyGoldFishSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyLeafMonsterSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTshirtSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEnderKnightSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEnderReaperSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBeaverSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyUrchinSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyFlounderSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySkateSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyRotatorSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyPeacockSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyFairySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyDungeonBeastSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyVortexSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyRatSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyWhaleSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyIrukandjiSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyTRexSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyHerculesSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyMantisSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyStinkySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyEasterBunnySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCaterKillerSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyMolenoidSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySeaMonsterSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySeaViperSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyLeonSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyHammerheadSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyRubberDuckySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCriminalSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyBrutalflySpawnBlock);
     ForgeRegistries.BLOCKS.register(MyNastysaurusSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyPointysaurusSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCricketSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyFrogSpawnBlock);
     ForgeRegistries.BLOCKS.register(MySpiderDriverSpawnBlock);
     ForgeRegistries.BLOCKS.register(MyCrabSpawnBlock);

     ForgeRegistries.BLOCKS.register(MyOreSaltBlock);
     ForgeRegistries.BLOCKS.register(MyRTPBlock);
     ForgeRegistries.BLOCKS.register(MyMoleDirtBlock);
     ForgeRegistries.BLOCKS.register(MyOreTitaniumBlock);
     ForgeRegistries.BLOCKS.register(MyOreUraniumBlock);
     ForgeRegistries.BLOCKS.register(MyBlockTitaniumBlock);
     ForgeRegistries.BLOCKS.register(MyBlockMobzillaScaleBlock);
     ForgeRegistries.BLOCKS.register(MyBlockUraniumBlock);
     ForgeRegistries.BLOCKS.register(MyLavafoamBlock);
     ForgeRegistries.BLOCKS.register(MyOreRubyBlock);
     ForgeRegistries.BLOCKS.register(MyBlockRubyBlock);
     ForgeRegistries.BLOCKS.register(MyOreAmethystBlock);
     ForgeRegistries.BLOCKS.register(MyBlockAmethystBlock);
     ForgeRegistries.BLOCKS.register(MyCrystalPinkBlock);
     ForgeRegistries.BLOCKS.register(MyTigersEyeBlock);
     ForgeRegistries.BLOCKS.register(MyPizzaBlock);
     ForgeRegistries.BLOCKS.register(MyDuctTapeBlock);
     ForgeRegistries.BLOCKS.register(CrystalStone);
     ForgeRegistries.BLOCKS.register(CrystalRat);
     ForgeRegistries.BLOCKS.register(RedAntTroll);
     ForgeRegistries.BLOCKS.register(TermiteTroll);
     ForgeRegistries.BLOCKS.register(CrystalFairy);
     ForgeRegistries.BLOCKS.register(CrystalCoal);
     ForgeRegistries.BLOCKS.register(CrystalGrass);
     ForgeRegistries.BLOCKS.register(CrystalCrystal);
     ForgeRegistries.BLOCKS.register(TigersEye);
     ForgeRegistries.BLOCKS.register(CrystalPlanksBlock);
     ForgeRegistries.BLOCKS.register(CrystalWorkbenchBlock);
     ForgeRegistries.BLOCKS.register(CrystalFurnaceBlock);

     ForgeRegistries.BLOCKS.register(MyStrawberryPlant);
     ForgeRegistries.BLOCKS.register(MyRadishPlant);
     ForgeRegistries.BLOCKS.register(MyRicePlant);
     ForgeRegistries.BLOCKS.register(MyButterflyPlant);
     ForgeRegistries.BLOCKS.register(MyMothPlant);
     ForgeRegistries.BLOCKS.register(MyMosquitoPlant);
     ForgeRegistries.BLOCKS.register(MyFireflyPlant);
     ForgeRegistries.BLOCKS.register(MyCornPlant1);
     ForgeRegistries.BLOCKS.register(MyCornPlant2);
     ForgeRegistries.BLOCKS.register(MyCornPlant3);
     ForgeRegistries.BLOCKS.register(MyCornPlant4);
     ForgeRegistries.BLOCKS.register(MyQuinoaPlant1);
     ForgeRegistries.BLOCKS.register(MyQuinoaPlant2);
     ForgeRegistries.BLOCKS.register(MyQuinoaPlant3);
     ForgeRegistries.BLOCKS.register(MyQuinoaPlant4);
     ForgeRegistries.BLOCKS.register(MyTomatoPlant1);
     ForgeRegistries.BLOCKS.register(MyTomatoPlant2);
     ForgeRegistries.BLOCKS.register(MyTomatoPlant3);
     ForgeRegistries.BLOCKS.register(MyTomatoPlant4);
     ForgeRegistries.BLOCKS.register(MyLettucePlant1);
     ForgeRegistries.BLOCKS.register(MyLettucePlant2);
     ForgeRegistries.BLOCKS.register(MyLettucePlant3);
     ForgeRegistries.BLOCKS.register(MyLettucePlant4);
     ForgeRegistries.BLOCKS.register(MyAppleLeaves);
     ForgeRegistries.BLOCKS.register(MyExperienceLeaves);
     ForgeRegistries.BLOCKS.register(MyScaryLeaves);
     ForgeRegistries.BLOCKS.register(MyCherryLeaves);
     ForgeRegistries.BLOCKS.register(MyPeachLeaves);
     ForgeRegistries.BLOCKS.register(MySkyTreeLog);
     ForgeRegistries.BLOCKS.register(MyDT);
     ForgeRegistries.BLOCKS.register(MyExperiencePlant);
     ForgeRegistries.BLOCKS.register(MyCrystalPlant);
     ForgeRegistries.BLOCKS.register(MyCrystalPlant2);
     ForgeRegistries.BLOCKS.register(MyCrystalPlant3);
     ForgeRegistries.BLOCKS.register(MyFlowerPinkBlock);
     ForgeRegistries.BLOCKS.register(MyFlowerBlueBlock);
     ForgeRegistries.BLOCKS.register(MyFlowerBlackBlock);
     ForgeRegistries.BLOCKS.register(MyFlowerScaryBlock);
     ForgeRegistries.BLOCKS.register(CrystalFlowerRedBlock);
     ForgeRegistries.BLOCKS.register(CrystalFlowerGreenBlock);
     ForgeRegistries.BLOCKS.register(CrystalFlowerBlueBlock);
     ForgeRegistries.BLOCKS.register(CrystalFlowerYellowBlock);
     ForgeRegistries.BLOCKS.register(MyCrystalLeaves);
     ForgeRegistries.BLOCKS.register(MyCrystalLeaves2);
     ForgeRegistries.BLOCKS.register(MyCrystalLeaves3);
     ForgeRegistries.BLOCKS.register(MyCrystalTreeLog);

     ForgeRegistries.BLOCKS.register(ExtremeTorch);
     ForgeRegistries.BLOCKS.register(CrystalTorch);
     ForgeRegistries.BLOCKS.register(KrakenRepellent);
     ForgeRegistries.BLOCKS.register(CreeperRepellent);
  }

  private void make_some_more_things_part2()
  {
     ForgeRegistries.BLOCKS.register(MyIslandBlock);
     ForgeRegistries.BLOCKS.register(MyKingSpawnerBlock);
     ForgeRegistries.BLOCKS.register(MyQueenSpawnerBlock);
     ForgeRegistries.BLOCKS.register(MyDungeonSpawnerBlock);

     ForgeRegistries.BLOCKS.register(MyEnderPearlBlock);
     ForgeRegistries.BLOCKS.register(MyEyeOfEnderBlock);
     ForgeRegistries.BLOCKS.register(MyAntBlock);
     ForgeRegistries.BLOCKS.register(MyRedAntBlock);
     ForgeRegistries.BLOCKS.register(TermiteBlock);
     ForgeRegistries.BLOCKS.register(CrystalTermiteBlock);
     ForgeRegistries.BLOCKS.register(MyRainbowAntBlock);
     ForgeRegistries.BLOCKS.register(MyUnstableAntBlock);

    for (Block block : ForgeRegistries.BLOCKS) {
        ResourceLocation rl = block.getRegistryName();
        if (rl != null && "chaospersists".equals(rl.getNamespace())) {
            String path = rl.getPath();
            if (!"pizza".equals(path) && !"ducttape".equals(path) && !"island".equals(path)) {
                net.minecraft.item.BlockItem itemBlock = new net.minecraft.item.BlockItem(block, new Item.Properties().tab(tabChaosBlocks));
                itemBlock.setRegistryName(rl);
                ForgeRegistries.ITEMS.register(itemBlock);
            }
        }
    }

     ForgeRegistries.ITEMS.register(MyPizzaItem);
     ForgeRegistries.ITEMS.register(MyDuctTapeItem);
    IslandBlock.ItemIslandBlock islandItem = new IslandBlock.ItemIslandBlock(MyIslandBlock, new Item.Properties().tab(tabChaosBlocks));
        islandItem.setRegistryName(MyIslandBlock.getRegistryName());
     ForgeRegistries.ITEMS.register(islandItem);
     ForgeRegistries.ITEMS.register(MyIngotUranium);
     ForgeRegistries.ITEMS.register(MyCrystalPinkIngot);
     ForgeRegistries.ITEMS.register(MyTigersEyeIngot);
     ForgeRegistries.ITEMS.register(MyIngotTitanium);
     ForgeRegistries.ITEMS.register(MyUltimateSword);
     ForgeRegistries.ITEMS.register(MyNightmareSword);
     ForgeRegistries.ITEMS.register(MyBertha);
     ForgeRegistries.ITEMS.register(MyHammy);
     ForgeRegistries.ITEMS.register(MySlice);
     ForgeRegistries.ITEMS.register(MyRoyal);
     ForgeRegistries.ITEMS.register(MyBattleAxe);
     ForgeRegistries.ITEMS.register(MyQueenBattleAxe);
     ForgeRegistries.ITEMS.register(MyChainsaw);
     ForgeRegistries.ITEMS.register(MyUltimatePickaxe);
     ForgeRegistries.ITEMS.register(MyUltimateShovel);
     ForgeRegistries.ITEMS.register(MyUltimateHoe);
     ForgeRegistries.ITEMS.register(MyUltimateAxe);
     ForgeRegistries.ITEMS.register(MyEmeraldSword);
     ForgeRegistries.ITEMS.register(MyRoseSword);
     ForgeRegistries.ITEMS.register(MyExperienceSword);
     ForgeRegistries.ITEMS.register(MyPoisonSword);
     ForgeRegistries.ITEMS.register(MyRatSword);
     ForgeRegistries.ITEMS.register(MyFairySword);
     ForgeRegistries.ITEMS.register(MyMantisClaw);
     ForgeRegistries.ITEMS.register(MyBigHammer);
     ForgeRegistries.ITEMS.register(MyEmeraldPickaxe);
     ForgeRegistries.ITEMS.register(MyEmeraldShovel);
     ForgeRegistries.ITEMS.register(MyEmeraldHoe);
     ForgeRegistries.ITEMS.register(MyEmeraldAxe);
     ForgeRegistries.ITEMS.register(MyCrystalWoodSword);
     ForgeRegistries.ITEMS.register(MyCrystalWoodPickaxe);
     ForgeRegistries.ITEMS.register(MyCrystalWoodShovel);
     ForgeRegistries.ITEMS.register(MyCrystalWoodHoe);
     ForgeRegistries.ITEMS.register(MyCrystalWoodAxe);
     ForgeRegistries.ITEMS.register(MyCrystalPinkSword);
     ForgeRegistries.ITEMS.register(MyCrystalPinkPickaxe);
     ForgeRegistries.ITEMS.register(MyCrystalPinkShovel);
     ForgeRegistries.ITEMS.register(MyCrystalPinkHoe);
     ForgeRegistries.ITEMS.register(MyCrystalPinkAxe);
     ForgeRegistries.ITEMS.register(MyTigersEyeSword);
     ForgeRegistries.ITEMS.register(MyTigersEyePickaxe);
     ForgeRegistries.ITEMS.register(MyTigersEyeShovel);
     ForgeRegistries.ITEMS.register(MyTigersEyeHoe);
     ForgeRegistries.ITEMS.register(MyTigersEyeAxe);
     ForgeRegistries.ITEMS.register(MyCrystalStoneSword);
     ForgeRegistries.ITEMS.register(MyCrystalStonePickaxe);
     ForgeRegistries.ITEMS.register(MyCrystalStoneShovel);
     ForgeRegistries.ITEMS.register(MyCrystalStoneHoe);
     ForgeRegistries.ITEMS.register(MyCrystalStoneAxe);
     ForgeRegistries.ITEMS.register(MyRubySword);
     ForgeRegistries.ITEMS.register(MyRubyPickaxe);
     ForgeRegistries.ITEMS.register(MyRubyShovel);
     ForgeRegistries.ITEMS.register(MyRubyHoe);
     ForgeRegistries.ITEMS.register(MyRubyAxe);
     ForgeRegistries.ITEMS.register(MyAmethystSword);
     ForgeRegistries.ITEMS.register(MyAmethystPickaxe);
     ForgeRegistries.ITEMS.register(MyAmethystShovel);
     ForgeRegistries.ITEMS.register(MyAmethystHoe);
     ForgeRegistries.ITEMS.register(MyAmethystAxe);
     ForgeRegistries.ITEMS.register(MyItemShoes);
     ForgeRegistries.ITEMS.register(MyItemShoes_1);
     ForgeRegistries.ITEMS.register(MyItemShoes_2);
     ForgeRegistries.ITEMS.register(MyItemShoes_3);
     ForgeRegistries.ITEMS.register(MyItemGameController);
     ForgeRegistries.ITEMS.register(MyUltimateBow);
     ForgeRegistries.ITEMS.register(MySkateBow);
     ForgeRegistries.ITEMS.register(MyUltimateFishingRod);
     ForgeRegistries.ITEMS.register(MyFireFish);
     ForgeRegistries.ITEMS.register(MySunFish);
     ForgeRegistries.ITEMS.register(MyLavaEel);
     ForgeRegistries.ITEMS.register(MyMothScale);
     ForgeRegistries.ITEMS.register(MyQueenScale);
     ForgeRegistries.ITEMS.register(MyNightmareScale);
     ForgeRegistries.ITEMS.register(MyEmperorScorpionScale);
     ForgeRegistries.ITEMS.register(MyBasiliskScale);
     ForgeRegistries.ITEMS.register(MyWaterDragonScale);
     ForgeRegistries.ITEMS.register(MyPeacockFeather);
     ForgeRegistries.ITEMS.register(MyJumpyBugScale);
     ForgeRegistries.ITEMS.register(MyKrakenTooth);
     ForgeRegistries.ITEMS.register(MyGodzillaScale);
     ForgeRegistries.ITEMS.register(GreenGoo);
     ForgeRegistries.ITEMS.register(SpiderRobotKit);
     ForgeRegistries.ITEMS.register(AntRobotKit);
     ForgeRegistries.ITEMS.register(ZooKeeper);
     ForgeRegistries.ITEMS.register(CreeperLauncher);
     ForgeRegistries.ITEMS.register(NetherLost);
     ForgeRegistries.ITEMS.register(CrystalSticks);
     ForgeRegistries.ITEMS.register(Sifter);
     ForgeRegistries.ITEMS.register(MySunspotUrchin);
     ForgeRegistries.ITEMS.register(MyWaterBall);
     ForgeRegistries.ITEMS.register(MyLaserBall);
     ForgeRegistries.ITEMS.register(MyIceBall);
     ForgeRegistries.ITEMS.register(MySmallRock);
     ForgeRegistries.ITEMS.register(MyRock);
     ForgeRegistries.ITEMS.register(MyRedRock);
     ForgeRegistries.ITEMS.register(MyCrystalRedRock);
     ForgeRegistries.ITEMS.register(MyCrystalGreenRock);
     ForgeRegistries.ITEMS.register(MyCrystalBlueRock);
     ForgeRegistries.ITEMS.register(MyCrystalTNTRock);
     ForgeRegistries.ITEMS.register(MyGreenRock);
     ForgeRegistries.ITEMS.register(MyBlueRock);
     ForgeRegistries.ITEMS.register(MyPurpleRock);
     ForgeRegistries.ITEMS.register(MySpikeyRock);
     ForgeRegistries.ITEMS.register(MyTNTRock);
     ForgeRegistries.ITEMS.register(MyAcid);
     ForgeRegistries.ITEMS.register(MyIrukandji);
     ForgeRegistries.ITEMS.register(MyIrukandjiArrow);
     ForgeRegistries.ITEMS.register(MyRayGun);
     ForgeRegistries.ITEMS.register(MySquidZooka);
     ForgeRegistries.ITEMS.register(MySparkFish);
     ForgeRegistries.ITEMS.register(MySalt);
     ForgeRegistries.ITEMS.register(MyPopcorn);
     ForgeRegistries.ITEMS.register(MyButteredPopcorn);
     ForgeRegistries.ITEMS.register(MyButteredSaltedPopcorn);
     ForgeRegistries.ITEMS.register(MyPopcornBag);
     ForgeRegistries.ITEMS.register(MyButter);
     ForgeRegistries.ITEMS.register(MyCornDog);
     ForgeRegistries.ITEMS.register(MyCheese);
     ForgeRegistries.ITEMS.register(MyRawCornDog);
     ForgeRegistries.ITEMS.register(MyPeacock);
     ForgeRegistries.ITEMS.register(MyRawPeacock);
     ForgeRegistries.ITEMS.register(MyRuby);
     ForgeRegistries.ITEMS.register(MyAmethyst);
     ForgeRegistries.ITEMS.register(MyThunderStaff);
     ForgeRegistries.ITEMS.register(MyWrench);
     ForgeRegistries.ITEMS.register(MyRawBacon);
     ForgeRegistries.ITEMS.register(MyBacon);
     ForgeRegistries.ITEMS.register(MyRawCrabMeat);
     ForgeRegistries.ITEMS.register(MyCrabMeat);
     ForgeRegistries.ITEMS.register(MyButterCandy);
     ForgeRegistries.ITEMS.register(UraniumNugget);
     ForgeRegistries.ITEMS.register(TitaniumNugget);
     ForgeRegistries.ITEMS.register(MyGreenFish);
     ForgeRegistries.ITEMS.register(MyBlueFish);
     ForgeRegistries.ITEMS.register(MyPinkFish);
     ForgeRegistries.ITEMS.register(MyRockFish);
     ForgeRegistries.ITEMS.register(MyWoodFish);
     ForgeRegistries.ITEMS.register(MyGreyFish);
     ForgeRegistries.ITEMS.register(MySalad);
     ForgeRegistries.ITEMS.register(MyBLT);
     ForgeRegistries.ITEMS.register(MyCrabbyPatty);

     ForgeRegistries.ITEMS.register(BerthaHandle);
     ForgeRegistries.ITEMS.register(BerthaGuard);
     ForgeRegistries.ITEMS.register(BerthaBlade);
     ForgeRegistries.ITEMS.register(MolenoidNose);
     ForgeRegistries.ITEMS.register(SeaMonsterScale);
     ForgeRegistries.ITEMS.register(WormTooth);
     ForgeRegistries.ITEMS.register(TRexTooth);
     ForgeRegistries.ITEMS.register(CaterKillerJaw);
     ForgeRegistries.ITEMS.register(SeaViperTongue);
     ForgeRegistries.ITEMS.register(VortexEye);

     ForgeRegistries.ITEMS.register(WitherSkeletonEgg);
     ForgeRegistries.ITEMS.register(EnderDragonEgg);
     ForgeRegistries.ITEMS.register(SnowGolemEgg);
     ForgeRegistries.ITEMS.register(IronGolemEgg);
     ForgeRegistries.ITEMS.register(WitherBossEgg);
     ForgeRegistries.ITEMS.register(GirlfriendEgg);
     ForgeRegistries.ITEMS.register(BoyfriendEgg);
     ForgeRegistries.ITEMS.register(TheKingEgg);
     ForgeRegistries.ITEMS.register(TheQueenEgg);
     ForgeRegistries.ITEMS.register(ThePrinceEgg);
     ForgeRegistries.ITEMS.register(RedCowEgg);
     ForgeRegistries.ITEMS.register(CrystalCowEgg);
     ForgeRegistries.ITEMS.register(GoldCowEgg);
     ForgeRegistries.ITEMS.register(EnchantedCowEgg);
     ForgeRegistries.ITEMS.register(MOTHRAEgg);
     ForgeRegistries.ITEMS.register(AloEgg);
     ForgeRegistries.ITEMS.register(CryoEgg);
     ForgeRegistries.ITEMS.register(CamaEgg);
     ForgeRegistries.ITEMS.register(VeloEgg);
     ForgeRegistries.ITEMS.register(HydroEgg);
     ForgeRegistries.ITEMS.register(BasilEgg);
     ForgeRegistries.ITEMS.register(DragonflyEgg);
     ForgeRegistries.ITEMS.register(EmperorScorpionEgg);
     ForgeRegistries.ITEMS.register(ScorpionEgg);
     ForgeRegistries.ITEMS.register(CaveFisherEgg);
  }

  private void make_some_more_things_part3()
  {
     ForgeRegistries.ITEMS.register(SpyroEgg);
     ForgeRegistries.ITEMS.register(BaryonyxEgg);
     ForgeRegistries.ITEMS.register(GammaMetroidEgg);
     ForgeRegistries.ITEMS.register(CockateilEgg);
     ForgeRegistries.ITEMS.register(KyuubiEgg);
     ForgeRegistries.ITEMS.register(AlienEgg);
     ForgeRegistries.ITEMS.register(AttackSquidEgg);
     ForgeRegistries.ITEMS.register(WaterDragonEgg);
     ForgeRegistries.ITEMS.register(CephadromeEgg);
     ForgeRegistries.ITEMS.register(KrakenEgg);
     ForgeRegistries.ITEMS.register(LizardEgg);
     ForgeRegistries.ITEMS.register(DragonEgg);
     ForgeRegistries.ITEMS.register(BeeEgg);
     ForgeRegistries.ITEMS.register(TrooperBugEgg);
     ForgeRegistries.ITEMS.register(SpitBugEgg);
     ForgeRegistries.ITEMS.register(StinkBugEgg);
     ForgeRegistries.ITEMS.register(OstrichEgg);
     ForgeRegistries.ITEMS.register(GazelleEgg);
     ForgeRegistries.ITEMS.register(ChipmunkEgg);
     ForgeRegistries.ITEMS.register(CreepingHorrorEgg);
     ForgeRegistries.ITEMS.register(TerribleTerrorEgg);
     ForgeRegistries.ITEMS.register(CliffRacerEgg);
     ForgeRegistries.ITEMS.register(TriffidEgg);
     ForgeRegistries.ITEMS.register(PitchBlackEgg);
     ForgeRegistries.ITEMS.register(LurkingTerrorEgg);
     ForgeRegistries.ITEMS.register(GodzillaEgg);
     ForgeRegistries.ITEMS.register(SmallWormEgg);
     ForgeRegistries.ITEMS.register(MediumWormEgg);
     ForgeRegistries.ITEMS.register(LargeWormEgg);
     ForgeRegistries.ITEMS.register(CassowaryEgg);
     ForgeRegistries.ITEMS.register(CloudSharkEgg);
     ForgeRegistries.ITEMS.register(GoldFishEgg);
     ForgeRegistries.ITEMS.register(LeafMonsterEgg);
     ForgeRegistries.ITEMS.register(TshirtEgg);
     ForgeRegistries.ITEMS.register(EnderKnightEgg);
     ForgeRegistries.ITEMS.register(EnderReaperEgg);
     ForgeRegistries.ITEMS.register(BeaverEgg);
     ForgeRegistries.ITEMS.register(DungeonBeastEgg);
     ForgeRegistries.ITEMS.register(RotatorEgg);
     ForgeRegistries.ITEMS.register(VortexEgg);
     ForgeRegistries.ITEMS.register(PeacockEgg);
     ForgeRegistries.ITEMS.register(FairyEgg);
     ForgeRegistries.ITEMS.register(RatEgg);
     ForgeRegistries.ITEMS.register(FlounderEgg);
     ForgeRegistries.ITEMS.register(WhaleEgg);
     ForgeRegistries.ITEMS.register(IrukandjiEgg);
     ForgeRegistries.ITEMS.register(SkateEgg);
     ForgeRegistries.ITEMS.register(UrchinEgg);
     ForgeRegistries.ITEMS.register(Robot1Egg);
     ForgeRegistries.ITEMS.register(Robot2Egg);
     ForgeRegistries.ITEMS.register(Robot3Egg);
     ForgeRegistries.ITEMS.register(Robot4Egg);
     ForgeRegistries.ITEMS.register(GhostEgg);
     ForgeRegistries.ITEMS.register(GhostSkellyEgg);
     ForgeRegistries.ITEMS.register(BrownAntEgg);
     ForgeRegistries.ITEMS.register(RedAntEgg);
     ForgeRegistries.ITEMS.register(RainbowAntEgg);
     ForgeRegistries.ITEMS.register(UnstableAntEgg);
     ForgeRegistries.ITEMS.register(TermiteEgg);
     ForgeRegistries.ITEMS.register(ButterflyEgg);
     ForgeRegistries.ITEMS.register(MothEgg);
     ForgeRegistries.ITEMS.register(MosquitoEgg);
     ForgeRegistries.ITEMS.register(FireflyEgg);
     ForgeRegistries.ITEMS.register(TRexEgg);
     ForgeRegistries.ITEMS.register(HerculesEgg);
     ForgeRegistries.ITEMS.register(MantisEgg);
     ForgeRegistries.ITEMS.register(StinkyEgg);
     ForgeRegistries.ITEMS.register(Robot5Egg);
     ForgeRegistries.ITEMS.register(CoinEgg);
     ForgeRegistries.ITEMS.register(EasterBunnyEgg);
     ForgeRegistries.ITEMS.register(MolenoidEgg);
     ForgeRegistries.ITEMS.register(SeaMonsterEgg);
     ForgeRegistries.ITEMS.register(SeaViperEgg);
     ForgeRegistries.ITEMS.register(CaterKillerEgg);
     ForgeRegistries.ITEMS.register(RubberDuckyEgg);
     ForgeRegistries.ITEMS.register(HammerheadEgg);
     ForgeRegistries.ITEMS.register(LeonEgg);
     ForgeRegistries.ITEMS.register(CriminalEgg);
     ForgeRegistries.ITEMS.register(BrutalflyEgg);
     ForgeRegistries.ITEMS.register(NastysaurusEgg);
     ForgeRegistries.ITEMS.register(PointysaurusEgg);
     ForgeRegistries.ITEMS.register(CricketEgg);
     ForgeRegistries.ITEMS.register(ThePrincessEgg);
     ForgeRegistries.ITEMS.register(FrogEgg);
     ForgeRegistries.ITEMS.register(JefferyEgg);
     ForgeRegistries.ITEMS.register(AntRobotEgg);
     ForgeRegistries.ITEMS.register(SpiderRobotEgg);
     ForgeRegistries.ITEMS.register(SpiderDriverEgg);
     ForgeRegistries.ITEMS.register(CrabEgg);

     ForgeRegistries.ITEMS.register(CageEmpty);
     ForgeRegistries.ITEMS.register(CagedSpider);
     ForgeRegistries.ITEMS.register(CagedBat);
     ForgeRegistries.ITEMS.register(CagedCow);
     ForgeRegistries.ITEMS.register(CagedPig);
     ForgeRegistries.ITEMS.register(CagedSquid);
     ForgeRegistries.ITEMS.register(CagedChicken);
     ForgeRegistries.ITEMS.register(CagedCreeper);
     ForgeRegistries.ITEMS.register(CagedSkeleton);
     ForgeRegistries.ITEMS.register(CagedZombie);
     ForgeRegistries.ITEMS.register(CagedSlime);
     ForgeRegistries.ITEMS.register(CagedGhast);
     ForgeRegistries.ITEMS.register(CagedZombiePigman);
     ForgeRegistries.ITEMS.register(CagedEnderman);
     ForgeRegistries.ITEMS.register(CagedCaveSpider);
     ForgeRegistries.ITEMS.register(CagedSilverfish);
     ForgeRegistries.ITEMS.register(CagedMagmaCube);
     ForgeRegistries.ITEMS.register(CagedWitch);
     ForgeRegistries.ITEMS.register(CagedSheep);
     ForgeRegistries.ITEMS.register(CagedWolf);
     ForgeRegistries.ITEMS.register(CagedMooshroom);
     ForgeRegistries.ITEMS.register(CagedOcelot);
     ForgeRegistries.ITEMS.register(CagedBlaze);
     ForgeRegistries.ITEMS.register(CagedGirlfriend);
     ForgeRegistries.ITEMS.register(CagedBoyfriend);
     ForgeRegistries.ITEMS.register(CagedWitherSkeleton);
     ForgeRegistries.ITEMS.register(CagedEnderDragon);
     ForgeRegistries.ITEMS.register(CagedSnowGolem);
     ForgeRegistries.ITEMS.register(CagedIronGolem);
     ForgeRegistries.ITEMS.register(CagedWitherBoss);
     ForgeRegistries.ITEMS.register(CagedRedCow);
     ForgeRegistries.ITEMS.register(CagedCrystalCow);
     ForgeRegistries.ITEMS.register(CagedVillager);
     ForgeRegistries.ITEMS.register(CagedGoldCow);
     ForgeRegistries.ITEMS.register(CagedEnchantedCow);
     ForgeRegistries.ITEMS.register(CagedMOTHRA);
     ForgeRegistries.ITEMS.register(CagedAlo);
     ForgeRegistries.ITEMS.register(CagedCryo);
     ForgeRegistries.ITEMS.register(CagedCama);
     ForgeRegistries.ITEMS.register(CagedVelo);
     ForgeRegistries.ITEMS.register(CagedHydro);
     ForgeRegistries.ITEMS.register(CagedBasil);
     ForgeRegistries.ITEMS.register(CagedDragonfly);
     ForgeRegistries.ITEMS.register(CagedEmperorScorpion);
     ForgeRegistries.ITEMS.register(CagedScorpion);
     ForgeRegistries.ITEMS.register(CagedCaveFisher);
     ForgeRegistries.ITEMS.register(CagedSpyro);
     ForgeRegistries.ITEMS.register(CagedBaryonyx);
     ForgeRegistries.ITEMS.register(CagedGammaMetroid);
     ForgeRegistries.ITEMS.register(CagedCockateil);
     ForgeRegistries.ITEMS.register(CagedKyuubi);
     ForgeRegistries.ITEMS.register(CagedAlien);
     ForgeRegistries.ITEMS.register(MyElevator);
     ForgeRegistries.ITEMS.register(CagedAttackSquid);
     ForgeRegistries.ITEMS.register(CagedWaterDragon);
     ForgeRegistries.ITEMS.register(CagedCephadrome);
     ForgeRegistries.ITEMS.register(CagedKraken);
     ForgeRegistries.ITEMS.register(CagedLizard);
     ForgeRegistries.ITEMS.register(CagedDragon);
     ForgeRegistries.ITEMS.register(CagedBee);
     ForgeRegistries.ITEMS.register(CagedHorse);
     ForgeRegistries.ITEMS.register(CagedFirefly);
     ForgeRegistries.ITEMS.register(CagedChipmunk);
     ForgeRegistries.ITEMS.register(CagedGazelle);
     ForgeRegistries.ITEMS.register(CagedOstrich);
     ForgeRegistries.ITEMS.register(CagedTrooper);
     ForgeRegistries.ITEMS.register(CagedSpit);
     ForgeRegistries.ITEMS.register(CagedStink);
     ForgeRegistries.ITEMS.register(CagedCreepingHorror);
     ForgeRegistries.ITEMS.register(CagedTerribleTerror);
     ForgeRegistries.ITEMS.register(CagedCliffRacer);
     ForgeRegistries.ITEMS.register(CagedTriffid);
     ForgeRegistries.ITEMS.register(CagedPitchBlack);
     ForgeRegistries.ITEMS.register(CagedLurkingTerror);
     ForgeRegistries.ITEMS.register(CagedSmallWorm);
     ForgeRegistries.ITEMS.register(CagedMediumWorm);
     ForgeRegistries.ITEMS.register(CagedLargeWorm);
     ForgeRegistries.ITEMS.register(CagedCassowary);
     ForgeRegistries.ITEMS.register(CagedCloudShark);
     ForgeRegistries.ITEMS.register(CagedGoldFish);
     ForgeRegistries.ITEMS.register(CagedLeafMonster);
     ForgeRegistries.ITEMS.register(CagedEnderKnight);
     ForgeRegistries.ITEMS.register(CagedEnderReaper);
     ForgeRegistries.ITEMS.register(CagedBeaver);
     ForgeRegistries.ITEMS.register(CagedUrchin);
     ForgeRegistries.ITEMS.register(CagedFlounder);
     ForgeRegistries.ITEMS.register(CagedSkate);
     ForgeRegistries.ITEMS.register(CagedRotator);
     ForgeRegistries.ITEMS.register(CagedPeacock);
     ForgeRegistries.ITEMS.register(CagedFairy);
     ForgeRegistries.ITEMS.register(CagedDungeonBeast);
     ForgeRegistries.ITEMS.register(CagedVortex);
     ForgeRegistries.ITEMS.register(CagedRat);
     ForgeRegistries.ITEMS.register(CagedWhale);
     ForgeRegistries.ITEMS.register(CagedIrukandji);
     ForgeRegistries.ITEMS.register(CagedTRex);
     ForgeRegistries.ITEMS.register(CagedHercules);
     ForgeRegistries.ITEMS.register(CagedMantis);
     ForgeRegistries.ITEMS.register(CagedStinky);
     ForgeRegistries.ITEMS.register(CagedEasterBunny);
     ForgeRegistries.ITEMS.register(CagedCaterKiller);
     ForgeRegistries.ITEMS.register(CagedMolenoid);
     ForgeRegistries.ITEMS.register(CagedSeaMonster);
     ForgeRegistries.ITEMS.register(CagedSeaViper);
     ForgeRegistries.ITEMS.register(CagedLeon);
     ForgeRegistries.ITEMS.register(CagedHammerhead);
     ForgeRegistries.ITEMS.register(CagedRubberDucky);
     ForgeRegistries.ITEMS.register(CagedCriminal);
     ForgeRegistries.ITEMS.register(CagedBrutalfly);
     ForgeRegistries.ITEMS.register(CagedNastysaurus);
     ForgeRegistries.ITEMS.register(CagedPointysaurus);
  }

  private void make_some_more_things_part4()
  {
     ForgeRegistries.ITEMS.register(CagedCricket);
     ForgeRegistries.ITEMS.register(CagedFrog);
     ForgeRegistries.ITEMS.register(CagedSpiderDriver);
     ForgeRegistries.ITEMS.register(CagedCrab);

     ForgeRegistries.ITEMS.register(MyStrawberry);
     ForgeRegistries.ITEMS.register(MyCrystalApple);
     ForgeRegistries.ITEMS.register(MyLove);
     ForgeRegistries.ITEMS.register(MyCherry);
     ForgeRegistries.ITEMS.register(MyPeach);
     ForgeRegistries.ITEMS.register(MyRadish);
     ForgeRegistries.ITEMS.register(MyRice);
     ForgeRegistries.ITEMS.register(MyCornCob);
     ForgeRegistries.ITEMS.register(MyQuinoa);
     ForgeRegistries.ITEMS.register(MyTomato);
     ForgeRegistries.ITEMS.register(MyLettuce);
     ForgeRegistries.ITEMS.register(MyStrawberrySeed);
     ForgeRegistries.ITEMS.register(MyButterflySeed);
     ForgeRegistries.ITEMS.register(MyMothSeed);
     ForgeRegistries.ITEMS.register(MyMosquitoSeed);
     ForgeRegistries.ITEMS.register(MyFireflySeed);
     ForgeRegistries.ITEMS.register(MagicApple);
     ForgeRegistries.ITEMS.register(RandomDungeon);
     ForgeRegistries.ITEMS.register(MinersDream);
     ForgeRegistries.ITEMS.register(UltimateHelmet);
     ForgeRegistries.ITEMS.register(UltimateBody);
     ForgeRegistries.ITEMS.register(UltimateLegs);
     ForgeRegistries.ITEMS.register(UltimateBoots);
     ForgeRegistries.ITEMS.register(LavaEelHelmet);
     ForgeRegistries.ITEMS.register(LavaEelBody);
     ForgeRegistries.ITEMS.register(LavaEelLegs);
     ForgeRegistries.ITEMS.register(LavaEelBoots);
     ForgeRegistries.ITEMS.register(MothScaleHelmet);
     ForgeRegistries.ITEMS.register(MothScaleBody);
     ForgeRegistries.ITEMS.register(MothScaleLegs);
     ForgeRegistries.ITEMS.register(MothScaleBoots);
     ForgeRegistries.ITEMS.register(MyAppleSeed);
     ForgeRegistries.ITEMS.register(MyCherrySeed);
     ForgeRegistries.ITEMS.register(MyPeachSeed);
     ForgeRegistries.ITEMS.register(MyStepUp);
     ForgeRegistries.ITEMS.register(MyStepDown);
     ForgeRegistries.ITEMS.register(MyStepAccross);
     ForgeRegistries.ITEMS.register(EmeraldHelmet);
     ForgeRegistries.ITEMS.register(EmeraldBody);
     ForgeRegistries.ITEMS.register(EmeraldLegs);
     ForgeRegistries.ITEMS.register(EmeraldBoots);
     ForgeRegistries.ITEMS.register(MyExperienceCatcher);
     ForgeRegistries.ITEMS.register(MyDeadStinkBug);
     ForgeRegistries.ITEMS.register(MyExperienceTreeSeed);
     ForgeRegistries.ITEMS.register(ExperienceHelmet);
     ForgeRegistries.ITEMS.register(ExperienceBody);
     ForgeRegistries.ITEMS.register(ExperienceLegs);
     ForgeRegistries.ITEMS.register(ExperienceBoots);
     ForgeRegistries.ITEMS.register(RubyHelmet);
     ForgeRegistries.ITEMS.register(RubyBody);
     ForgeRegistries.ITEMS.register(RubyLegs);
     ForgeRegistries.ITEMS.register(RubyBoots);
     ForgeRegistries.ITEMS.register(AmethystHelmet);
     ForgeRegistries.ITEMS.register(AmethystBody);
     ForgeRegistries.ITEMS.register(AmethystLegs);
     ForgeRegistries.ITEMS.register(AmethystBoots);
     ForgeRegistries.ITEMS.register(ZooCage2);
     ForgeRegistries.ITEMS.register(ZooCage4);
     ForgeRegistries.ITEMS.register(ZooCage6);
     ForgeRegistries.ITEMS.register(ZooCage8);
     ForgeRegistries.ITEMS.register(ZooCage10);
     ForgeRegistries.ITEMS.register(InstantShelter);
     ForgeRegistries.ITEMS.register(InstantGarden);
     ForgeRegistries.ITEMS.register(CrystalPinkHelmet);
     ForgeRegistries.ITEMS.register(CrystalPinkBody);
     ForgeRegistries.ITEMS.register(CrystalPinkLegs);
     ForgeRegistries.ITEMS.register(CrystalPinkBoots);
     ForgeRegistries.ITEMS.register(TigersEyeHelmet);
     ForgeRegistries.ITEMS.register(TigersEyeBody);
     ForgeRegistries.ITEMS.register(TigersEyeLegs);
     ForgeRegistries.ITEMS.register(TigersEyeBoots);
     ForgeRegistries.ITEMS.register(PeacockFeatherBoots);
     ForgeRegistries.ITEMS.register(PeacockFeatherHelmet);
     ForgeRegistries.ITEMS.register(PeacockFeatherBody);
     ForgeRegistries.ITEMS.register(PeacockFeatherLegs);
     ForgeRegistries.ITEMS.register(MobzillaHelmet);
     ForgeRegistries.ITEMS.register(MobzillaBody);
     ForgeRegistries.ITEMS.register(MobzillaLegs);
     ForgeRegistries.ITEMS.register(MobzillaBoots);
     ForgeRegistries.ITEMS.register(RoyalHelmet);
     ForgeRegistries.ITEMS.register(RoyalBody);
     ForgeRegistries.ITEMS.register(RoyalLegs);
     ForgeRegistries.ITEMS.register(RoyalBoots);
     ForgeRegistries.ITEMS.register(LapisHelmet);
     ForgeRegistries.ITEMS.register(LapisBody);
     ForgeRegistries.ITEMS.register(LapisLegs);
     ForgeRegistries.ITEMS.register(LapisBoots);
     ForgeRegistries.ITEMS.register(QueenHelmet);
     ForgeRegistries.ITEMS.register(QueenBody);
     ForgeRegistries.ITEMS.register(QueenLegs);
     ForgeRegistries.ITEMS.register(QueenBoots);

    ItemStack OreSpiderEggStack = new ItemStack(MySpiderSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_spider"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("spider"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpiderEggStack));

    ItemStack OreBatEggStack = new ItemStack(MyBatSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_bat"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("bat"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBatEggStack));

    ItemStack OreCowEggStack = new ItemStack(MyCowSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_cow"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("cow"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCowEggStack));

    ItemStack OrePigEggStack = new ItemStack(MyPigSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_pig"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("pig"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePigEggStack));

    ItemStack OreSquidEggStack = new ItemStack(MySquidSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_squid"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("SquidEntity"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSquidEggStack));

    ItemStack OreChickenEggStack = new ItemStack(MyChickenSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_chicken"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("chicken"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreChickenEggStack));

    ItemStack OreCreeperEggStack = new ItemStack(MyCreeperSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_creeper"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("creeper"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCreeperEggStack));

    ItemStack OreSkeletonEggStack = new ItemStack(MySkeletonSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_skeleton"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("skeleton"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSkeletonEggStack));

    ItemStack OreZombieEggStack = new ItemStack(MyZombieSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_zombie"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("zombie"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreZombieEggStack));

    ItemStack OreSlimeEggStack = new ItemStack(MySlimeSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_slime"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("slime"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSlimeEggStack));

    ItemStack OreGhastEggStack = new ItemStack(MyGhastSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_ghast"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("ghast"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGhastEggStack));

    ItemStack OreZombiePigmanEggStack = new ItemStack(MyZombiePigmanSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_zombie_pigman"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("zombie_pigman"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreZombiePigmanEggStack));

    ItemStack OreEndermanEggStack = new ItemStack(MyEndermanSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_enderman"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("enderman"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEndermanEggStack));

    ItemStack OreCaveSpiderEggStack = new ItemStack(MyCaveSpiderSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_cave_spider"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("cave_spider"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCaveSpiderEggStack));

    ItemStack OreSilverfishEggStack = new ItemStack(MySilverfishSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_silverfish"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("silverfish"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSilverfishEggStack));

    ItemStack OreMagmaCubeEggStack = new ItemStack(MyMagmaCubeSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_magma_cube"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("magma_cube"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMagmaCubeEggStack));

    ItemStack OreWitchEggStack = new ItemStack(MyWitchSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_witch"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("witch"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWitchEggStack));

    ItemStack OreSheepEggStack = new ItemStack(MySheepSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_sheep"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("sheep"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSheepEggStack));

    ItemStack OreWolfEggStack = new ItemStack(MyWolfSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_wolf"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("wolf"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWolfEggStack));

    ItemStack OreMooshroomEggStack = new ItemStack(MyMooshroomSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mooshroom"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("mooshroom"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMooshroomEggStack));

    ItemStack OreOcelotEggStack = new ItemStack(MyOcelotSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_ocelot"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("ocelot"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreOcelotEggStack));

    ItemStack OreBlazeEggStack = new ItemStack(MyBlazeSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_blaze"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("blaze"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBlazeEggStack));

    ItemStack OreWitherSkeletonEggStack = new ItemStack(MyWitherSkeletonSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_wither_skeleton"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(WitherSkeletonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWitherSkeletonEggStack));

    ItemStack OreEnderDragonEggStack = new ItemStack(MyEnderDragonSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_ender_dragon"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(EnderDragonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnderDragonEggStack));

    ItemStack OreSnowGolemEggStack = new ItemStack(MySnowGolemSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_snow_golem"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SnowGolemEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSnowGolemEggStack));

    ItemStack OreIronGolemEggStack = new ItemStack(MyIronGolemSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_iron_golem"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(IronGolemEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreIronGolemEggStack));

    ItemStack OreWitherBossEggStack = new ItemStack(MyWitherBossSpawnBlock);
    ItemStack witherBossEggRecipe = new ItemStack(WitherBossEgg, 1);
    witherBossEggRecipe.setDamageValue(64);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_wither_boss"), new ResourceLocation("chaospersists", "eggs"), witherBossEggRecipe, Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWitherBossEggStack));

    ItemStack OreGirlfriendEggStack = new ItemStack(MyGirlfriendSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_girlfriend"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(GirlfriendEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGirlfriendEggStack));

    ItemStack OreBoyfriendEggStack = new ItemStack(MyBoyfriendSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_boyfriend"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BoyfriendEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBoyfriendEggStack));

    ItemStack OreRedCowEggStack = new ItemStack(MyRedCowSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_red_cow"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(RedCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRedCowEggStack));

    ItemStack OreCrystalCowEggStack = new ItemStack(MyCrystalCowSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_crystal_cow"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CrystalCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCrystalCowEggStack));

    ItemStack OreVillagerEggStack = new ItemStack(MyVillagerSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_villager"), new ResourceLocation("chaospersists", "eggs"), createVanillaSpawnEgg("villager"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreVillagerEggStack));

    ItemStack OreGoldCowEggStack = new ItemStack(MyGoldCowSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_gold_cow"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(GoldCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGoldCowEggStack));

    ItemStack OreEnchantedCowEggStack = new ItemStack(MyEnchantedCowSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_enchanted_cow"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(EnchantedCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnchantedCowEggStack));

    ItemStack OreMOTHRAEggStack = new ItemStack(MyMOTHRASpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mothra"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MOTHRAEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMOTHRAEggStack));

    ItemStack OreAloEggStack = new ItemStack(MyAloSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_alo"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(AloEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreAloEggStack));

    ItemStack OreCryoEggStack = new ItemStack(MyCryoSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_cryo"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CryoEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCryoEggStack));

    ItemStack OreCamaEggStack = new ItemStack(MyCamaSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_cama"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CamaEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCamaEggStack));

    ItemStack OreVeloEggStack = new ItemStack(MyVeloSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_velo"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(VeloEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreVeloEggStack));

    ItemStack OreHydroEggStack = new ItemStack(MyHydroSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_hydro"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(HydroEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHydroEggStack));

    ItemStack OreBasilEggStack = new ItemStack(MyBasilSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_basil"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BasilEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBasilEggStack));

    ItemStack OreDragonflyEggStack = new ItemStack(MyDragonflySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_dragonfly"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(DragonflyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreDragonflyEggStack));

    ItemStack OreEmperorScorpionEggStack = new ItemStack(MyEmperorScorpionSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_emperor_scorpion"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(EmperorScorpionEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEmperorScorpionEggStack));

    ItemStack OreScorpionEggStack = new ItemStack(MyScorpionSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_scorpion"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ScorpionEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreScorpionEggStack));

    ItemStack OreCaveFisherEggStack = new ItemStack(MyCaveFisherSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_cave_fisher"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CaveFisherEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCaveFisherEggStack));

    ItemStack OreSpyroEggStack = new ItemStack(MySpyroSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_spyro"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SpyroEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpyroEggStack));

    ItemStack OreBaryonyxEggStack = new ItemStack(MyBaryonyxSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_baryonyx"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BaryonyxEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBaryonyxEggStack));

    ItemStack OreGammaMetroidEggStack = new ItemStack(MyGammaMetroidSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_gamma_metroid"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(GammaMetroidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGammaMetroidEggStack));

    ItemStack OreCockateilEggStack = new ItemStack(MyCockateilSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_cockateil"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CockateilEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCockateilEggStack));

    ItemStack OreKyuubiEggStack = new ItemStack(MyKyuubiSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_kyuubi"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(KyuubiEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreKyuubiEggStack));

    ItemStack OreAlienEggStack = new ItemStack(MyAlienSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_alien"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(AlienEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreAlienEggStack));

    ItemStack OreAttackSquidEggStack = new ItemStack(MyAttackSquidSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(AttackSquidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreAttackSquidEggStack));

    ItemStack OreWaterDragonEggStack = new ItemStack(MyWaterDragonSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(WaterDragonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWaterDragonEggStack));

    ItemStack OreKrakenEggStack = new ItemStack(MyKrakenSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(KrakenEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreKrakenEggStack));

    ItemStack OreLizardEggStack = new ItemStack(MyLizardSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(LizardEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLizardEggStack));

    ItemStack OreCephadromeEggStack = new ItemStack(MyCephadromeSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CephadromeEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCephadromeEggStack));

    ItemStack OreDragonEggStack = new ItemStack(MyDragonSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(DragonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreDragonEggStack));

    ItemStack OreBeeEggStack = new ItemStack(MyBeeSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BeeEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBeeEggStack));

    ItemStack OreHorseEggStack = new ItemStack(MyHorseSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_horse"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(Items.HORSE_SPAWN_EGG), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHorseEggStack));

    ItemStack OreTrooperBugEggStack = new ItemStack(MyTrooperBugSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TrooperBugEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTrooperBugEggStack));

    ItemStack OreSpitBugEggStack = new ItemStack(MySpitBugSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SpitBugEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpitBugEggStack));

    ItemStack OreStinkBugEggStack = new ItemStack(MyStinkBugSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(StinkBugEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreStinkBugEggStack));

    ItemStack OreOstrichEggStack = new ItemStack(MyOstrichSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(OstrichEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreOstrichEggStack));

    ItemStack OreGazelleEggStack = new ItemStack(MyGazelleSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(GazelleEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGazelleEggStack));

    ItemStack OreChipmunkEggStack = new ItemStack(MyChipmunkSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ChipmunkEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreChipmunkEggStack));
    ItemStack OreCreepingHorrorEggStack = new ItemStack(MyCreepingHorrorSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CreepingHorrorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCreepingHorrorEggStack));
    ItemStack OreTerribleTerrorEggStack = new ItemStack(MyTerribleTerrorSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TerribleTerrorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTerribleTerrorEggStack));
    ItemStack OreCliffRacerEggStack = new ItemStack(MyCliffRacerSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CliffRacerEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCliffRacerEggStack));
    ItemStack OreTriffidEggStack = new ItemStack(MyTriffidSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TriffidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTriffidEggStack));
    ItemStack OrePitchBlackEggStack = new ItemStack(MyPitchBlackSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(PitchBlackEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePitchBlackEggStack));
    ItemStack OreLurkingTerrorEggStack = new ItemStack(MyLurkingTerrorSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(LurkingTerrorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLurkingTerrorEggStack));
  }

  private void make_some_more_things_part5()
  {
    ItemStack OreEnderKnightEggStack = new ItemStack(MyEnderKnightSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(EnderKnightEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnderKnightEggStack));
    ItemStack OreEnderReaperEggStack = new ItemStack(MyEnderReaperSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(EnderReaperEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnderReaperEggStack));
    ItemStack OreGodzillaPartEggStack = new ItemStack(MyGodzillaPartSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "godzilla_spawn"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyGodzillaSpawnBlock), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack));
    ItemStack OreGodzillaEggStack = new ItemStack(MyGodzillaSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(GodzillaEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGodzillaEggStack));
    ItemStack OreTheKingPartEggStack = new ItemStack(MyTheKingPartSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "the_king_spawn"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyTheKingSpawnBlock), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack));
    ItemStack OreTheKingEggStack = new ItemStack(MyTheKingSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TheKingEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTheKingEggStack));
    ItemStack OreTheQueenPartEggStack = new ItemStack(MyTheQueenPartSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "the_queen_spawn"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyTheQueenSpawnBlock), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack));
    ItemStack OreTheQueenEggStack = new ItemStack(MyTheQueenSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TheQueenEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTheQueenEggStack));
    ItemStack OreSmallWormEggStack = new ItemStack(MySmallWormSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SmallWormEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSmallWormEggStack));
    ItemStack OreMediumWormEggStack = new ItemStack(MyMediumWormSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MediumWormEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMediumWormEggStack));
    ItemStack OreLargeWormEggStack = new ItemStack(MyLargeWormSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(LargeWormEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLargeWormEggStack));
    ItemStack OreCassowaryEggStack = new ItemStack(MyCassowarySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CassowaryEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCassowaryEggStack));
    ItemStack OreCloudSharkEggStack = new ItemStack(MyCloudSharkSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CloudSharkEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCloudSharkEggStack));
    ItemStack OreGoldFishEggStack = new ItemStack(MyGoldFishSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(GoldFishEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGoldFishEggStack));
    ItemStack OreLeafMonsterEggStack = new ItemStack(MyLeafMonsterSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(LeafMonsterEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLeafMonsterEggStack));
    ItemStack OreTshirtEggStack = new ItemStack(MyTshirtSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TshirtEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTshirtEggStack));
    ItemStack OreBeaverEggStack = new ItemStack(MyBeaverSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BeaverEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBeaverEggStack));
    ItemStack OreUrchinEggStack = new ItemStack(MyUrchinSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(UrchinEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreUrchinEggStack));
    ItemStack OreFlounderEggStack = new ItemStack(MyFlounderSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(FlounderEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreFlounderEggStack));
    ItemStack OreSkateEggStack = new ItemStack(MySkateSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SkateEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSkateEggStack));
    ItemStack OreRotatorEggStack = new ItemStack(MyRotatorSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(RotatorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRotatorEggStack));
    ItemStack OrePeacockEggStack = new ItemStack(MyPeacockSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(PeacockEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePeacockEggStack));
    ItemStack OreFairyEggStack = new ItemStack(MyFairySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(FairyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreFairyEggStack));
    ItemStack OreDungeonBeastEggStack = new ItemStack(MyDungeonBeastSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(DungeonBeastEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreDungeonBeastEggStack));
    ItemStack OreVortexEggStack = new ItemStack(MyVortexSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(VortexEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreVortexEggStack));
    ItemStack OreRatEggStack = new ItemStack(MyRatSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(RatEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRatEggStack));
    ItemStack OreWhaleEggStack = new ItemStack(MyWhaleSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(WhaleEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWhaleEggStack));
    ItemStack OreIrukandjiEggStack = new ItemStack(MyIrukandjiSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(IrukandjiEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreIrukandjiEggStack));
    ItemStack OreTRexEggStack = new ItemStack(MyTRexSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TRexEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTRexEggStack));
    ItemStack OreHerculesEggStack = new ItemStack(MyHerculesSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(HerculesEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHerculesEggStack));
    ItemStack OreMantisEggStack = new ItemStack(MyMantisSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MantisEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMantisEggStack));
    ItemStack OreStinkyEggStack = new ItemStack(MyStinkySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(StinkyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreStinkyEggStack));
    ItemStack OreEasterBunnyEggStack = new ItemStack(MyEasterBunnySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(EasterBunnyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEasterBunnyEggStack));
    ItemStack OreCriminalEggStack = new ItemStack(MyCriminalSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CriminalEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCriminalEggStack));
    ItemStack OreBrutalflyEggStack = new ItemStack(MyBrutalflySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BrutalflyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBrutalflyEggStack));
    ItemStack OreNastysaurusEggStack = new ItemStack(MyNastysaurusSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(NastysaurusEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreNastysaurusEggStack));
    ItemStack OrePointysaurusEggStack = new ItemStack(MyPointysaurusSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(PointysaurusEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePointysaurusEggStack));
    ItemStack OreCricketEggStack = new ItemStack(MyCricketSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CricketEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCricketEggStack));
    ItemStack OreFrogEggStack = new ItemStack(MyFrogSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(FrogEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreFrogEggStack));
    ItemStack OreSpiderDriverEggStack = new ItemStack(MySpiderDriverSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SpiderDriverEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpiderDriverEggStack));
    ItemStack OreCrabEggStack = new ItemStack(MyCrabSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CrabEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCrabEggStack));
    ItemStack OreCaterKillerEggStack = new ItemStack(MyCaterKillerSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CaterKillerEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCaterKillerEggStack));
    ItemStack OreMolenoidEggStack = new ItemStack(MyMolenoidSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MolenoidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMolenoidEggStack));
    ItemStack OreSeaMonsterEggStack = new ItemStack(MySeaMonsterSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SeaMonsterEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSeaMonsterEggStack));
    ItemStack OreSeaViperEggStack = new ItemStack(MySeaViperSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(SeaViperEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSeaViperEggStack));
    ItemStack OreRubberDuckyEggStack = new ItemStack(MyRubberDuckySpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(RubberDuckyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRubberDuckyEggStack));
    ItemStack OreHammerheadEggStack = new ItemStack(MyHammerheadSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(HammerheadEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHammerheadEggStack));
    ItemStack OreLeonEggStack = new ItemStack(MyLeonSpawnBlock);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(LeonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLeonEggStack));

    ItemStack OreUraniumStack = new ItemStack(MyOreUraniumBlock);

    ItemStack OreTitaniumStack = new ItemStack(MyOreTitaniumBlock);

    ItemStack OreSaltStack = new ItemStack(MyOreSaltBlock);

    ItemStack OreCrystalStoneStack = new ItemStack(CrystalStone);

    ItemStack OreCrystalRatStack = new ItemStack(CrystalRat);

    ItemStack OreRedAntTrollStack = new ItemStack(RedAntTroll);

    ItemStack OreTermiteTrollStack = new ItemStack(TermiteTroll);

    ItemStack OreCrystalFairyStack = new ItemStack(CrystalFairy);

    ItemStack OreCrystalCrystalStack = new ItemStack(CrystalCrystal);

    ItemStack OreTigersEyeStack = new ItemStack(TigersEye);

    ItemStack OreCrystalCoalStack = new ItemStack(CrystalCoal);

    ItemStack OreCrystalGrassStack = new ItemStack(CrystalGrass);

    ItemStack OreRubyStack = new ItemStack(MyOreRubyBlock);

    ItemStack OreAmethystStack = new ItemStack(MyOreAmethystBlock);

    ItemStack BlockUraniumStack = new ItemStack(MyBlockUraniumBlock);

    ItemStack LavafoamStack = new ItemStack(MyLavafoamBlock);

    ItemStack BlockTitaniumStack = new ItemStack(MyBlockTitaniumBlock);

    ItemStack BlockMobzillaScaleStack = new ItemStack(MyBlockMobzillaScaleBlock);

    ItemStack BlockRubyStack = new ItemStack(MyBlockRubyBlock);

    ItemStack BlockAmethystStack = new ItemStack(MyBlockAmethystBlock);

    ItemStack BlockCrystalPinkStack = new ItemStack(MyCrystalPinkBlock);

    ItemStack BlockTigersEyeStack = new ItemStack(MyTigersEyeBlock);

    ItemStack EnderPearlStack = new ItemStack(MyEnderPearlBlock);

    ItemStack EyeOfEnderStack = new ItemStack(MyEyeOfEnderBlock);

    ItemStack CrystalPlanksStack = new ItemStack(CrystalPlanksBlock);

    ItemStack CrystalWorkbenchStack = new ItemStack(CrystalWorkbenchBlock);

    ItemStack CrystalFurnaceStack = new ItemStack(CrystalFurnaceBlock);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CrystalPlanksBlock, 4), Ingredient.of(new ItemStack(MyCrystalTreeLog)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CrystalWorkbenchBlock), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_furnace"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CrystalFurnaceBlock), "FFF", "F F", "FFF", 'F', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_chest"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Blocks.CHEST), "FFF", "F F", "FFF", 'F', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_door_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Items.OAK_DOOR), "FF ", "FF ", "FF ", 'F', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_door_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Items.OAK_DOOR), " FF", " FF", " FF", 'F', CrystalPlanksBlock);

    addSmeltingLegacy(MyOreUraniumBlock, new ItemStack(UraniumNugget), 0.3F);
    addSmeltingLegacy(MyOreTitaniumBlock, new ItemStack(TitaniumNugget), 0.3F);
    addSmeltingLegacy(MyOreRubyBlock, new ItemStack(MyRuby, 1), 1.0F);
    addSmeltingLegacy(MyOreAmethystBlock, new ItemStack(MyAmethyst, 1), 1.0F);
    addSmeltingLegacy(MyOreSaltBlock, new ItemStack(MySalt, 8), 0.1F);
    addSmeltingLegacy(MyCornCob, new ItemStack(MyPopcorn), 0.1F);
    addSmeltingLegacy(MyRawCornDog, new ItemStack(MyCornDog), 0.4F);
    addSmeltingLegacy(MyRawBacon, new ItemStack(MyBacon), 0.2F);
    addSmeltingLegacy(CrystalCrystal, new ItemStack(MyCrystalPinkIngot), 0.3F);
    addSmeltingLegacy(TigersEye, new ItemStack(MyTigersEyeIngot), 0.3F);
    addSmeltingLegacy(MyRawPeacock, new ItemStack(MyPeacock), 0.4F);
    addSmeltingLegacy(MyRawCrabMeat, new ItemStack(MyCrabMeat), 0.2F);
    // 1.7.10 behavior: CrystalCoal is furnace fuel (20000 burn time). Smelting recipe is not required.

    addSmeltingLegacy(MyGreenFish, new ItemStack(Items.COOKED_COD), 0.2F);
    addSmeltingLegacy(MyBlueFish, new ItemStack(Items.COOKED_COD), 0.2F);
    addSmeltingLegacy(MyPinkFish, new ItemStack(Items.COOKED_COD), 0.2F);
    addSmeltingLegacy(MyRockFish, new ItemStack(Items.COOKED_COD), 0.2F);
    addSmeltingLegacy(MyWoodFish, new ItemStack(Items.COOKED_COD), 0.2F);
    addSmeltingLegacy(MyGreyFish, new ItemStack(Items.COOKED_COD), 0.2F);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateSword), " T ", " U ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateSword), "T  ", "U  ", "I  ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateSword), "  T", "  U", "  I", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_pickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimatePickaxe), "TUT", " U ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_shovel_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateShovel), " U ", " T ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_shovel_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateShovel), "U  ", "T  ", "I  ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_shovel_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateShovel), "  U", "  T", "  I", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_hoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateHoe), "TU ", " I ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_axe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateAxe), "TU ", "TI ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_bow"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateBow), " TS", "I S", " US", 'S', Items.STRING, 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "skate_bow"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MySkateBow), " TS", "T S", " TS", 'S', Items.STRING, 'T', CrystalSticks);

    addShapedRecipe(new ResourceLocation("chaospersists", "ultimate_fishing_rod"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyUltimateFishingRod), "  T", " US", "I S", 'S', Items.STRING, 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "nightmare_sword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyNightmareSword), "ODO", "RTR", "OIO", 'I', Items.IRON_INGOT, 'O', MyNightmareScale, 'D', Items.DIAMOND, 'R', Items.REDSTONE, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldSword), " E ", " E ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldSword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldSword), "  E", "  E", "  I", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "rose_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRoseSword), " E ", " E ", " I ", 'I', Items.STICK, 'E', Blocks.POPPY);

    addShapedRecipe(new ResourceLocation("chaospersists", "rose_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRoseSword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', Blocks.POPPY);

    addShapedRecipe(new ResourceLocation("chaospersists", "rose_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRoseSword), "  E", "  E", "  I", 'I', Items.STICK, 'E', Blocks.POPPY);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_pickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldPickaxe), "EEE", " I ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_shovel_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldShovel), " E ", " I ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_shovel_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldShovel), "E  ", "I  ", "I  ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_shovel_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldShovel), "  E", "  I", "  I", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_hoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldHoe), "EE ", " I ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "emerald_axe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEmeraldAxe), "EE ", "EI ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "experience_sword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyExperienceSword), "EEE", "EIE", "EEE", 'I', MyEmeraldSword, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(new ResourceLocation("chaospersists", "poison_sword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyPoisonSword), "EEE", "EIE", "EEE", 'I', MyEmeraldSword, 'E', MyDeadStinkBug);

    addShapedRecipe(new ResourceLocation("chaospersists", "rat_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRatSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalRat);

    addShapedRecipe(new ResourceLocation("chaospersists", "rat_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRatSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalRat);

    addShapedRecipe(new ResourceLocation("chaospersists", "rat_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRatSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalRat);

    addShapedRecipe(new ResourceLocation("chaospersists", "fairy_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyFairySword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalFairy);

    addShapedRecipe(new ResourceLocation("chaospersists", "fairy_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyFairySword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalFairy);

    addShapedRecipe(new ResourceLocation("chaospersists", "fairy_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyFairySword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalFairy);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_pickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodPickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_shovel_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_shovel_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_shovel_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_hoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_wood_axe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalWoodAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_chest_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Blocks.CHEST), "EEE", "E E", "EEE", 'E', CrystalPlanksBlock);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_sword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_sword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_sword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_pickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkPickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_shovel_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_shovel_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_shovel_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_hoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_pink_axe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "crystal_bucket"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Items.BUCKET), "   ", "I I", " I ", 'I', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyesword_1"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyesword_2"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyesword_3"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyepickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyePickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyeshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyeshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyeshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyehoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyeaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstonesword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstonesword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstonesword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstonepickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStonePickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstoneshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstoneshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstoneshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstonehoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalstoneaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalStoneAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubysword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubySword), " E ", " E ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubysword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubySword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubysword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubySword), "  E", "  E", "  I", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubypickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubyPickaxe), "EEE", " I ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubyshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubyShovel), " E ", " I ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubyshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubyShovel), "E  ", "I  ", "I  ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubyshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubyShovel), "  E", "  I", "  I", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubyhoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubyHoe), "EE ", " I ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myrubyaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyRubyAxe), "EE ", "EI ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystsword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystSword), " E ", " E ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystsword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystSword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystsword"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystSword), "  E", "  E", "  I", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystpickaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystPickaxe), "EEE", " I ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystShovel), " E ", " I ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystShovel), "E  ", "I  ", "I  ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystshovel"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystShovel), "  E", "  I", "  I", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethysthoe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystHoe), "EE ", " I ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myamethystaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyAmethystAxe), "EE ", "EI ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyHammy), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(MyBigHammer)), Ingredient.of(new ItemStack(GreenGoo)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyBattleAxe), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(MyUltimateAxe)), Ingredient.of(new ItemStack(GreenGoo)));
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mychainsaw"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyChainsaw), "EEE", "EIE", "EEE", 'I', MyUltimateAxe, 'E', Blocks.REDSTONE_BLOCK);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myqueenbattleaxe"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyQueenBattleAxe), "EIE", "EIE", " I ", 'I', Items.IRON_INGOT, 'E', MyQueenScale);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyBertha), Ingredient.of(new ItemStack(BerthaHandle)), Ingredient.of(new ItemStack(BerthaGuard)), Ingredient.of(new ItemStack(BerthaBlade)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BerthaHandle), Ingredient.of(new ItemStack(MyRayGun)), Ingredient.of(new ItemStack(MyBigHammer)), Ingredient.of(new ItemStack(MyMantisClaw)), Ingredient.of(new ItemStack(MyWaterDragonScale)), Ingredient.of(new ItemStack(GreenGoo)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BerthaGuard), Ingredient.of(new ItemStack(MolenoidNose)), Ingredient.of(new ItemStack(SeaMonsterScale)), Ingredient.of(new ItemStack(MyMothScale)), Ingredient.of(new ItemStack(MyBasiliskScale)), Ingredient.of(new ItemStack(MyNightmareScale)), Ingredient.of(new ItemStack(MyEmperorScorpionScale)), Ingredient.of(new ItemStack(MyJumpyBugScale)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(BerthaBlade), Ingredient.of(new ItemStack(MyKrakenTooth)), Ingredient.of(new ItemStack(WormTooth)), Ingredient.of(new ItemStack(TRexTooth)), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(CaterKillerJaw)), Ingredient.of(new ItemStack(SeaViperTongue)), Ingredient.of(new ItemStack(VortexEye)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MySlice), Ingredient.of(new ItemStack(MyBertha)), Ingredient.of(new ItemStack(Items.IRON_INGOT)));

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyIrukandjiArrow), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(MyIrukandji)), Ingredient.of(new ItemStack(CrystalSticks)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(Items.RED_BED), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(CrystalPlanksBlock)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MySquidZooka), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myingoturanium"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyIngotUranium), "UUU", "UUU", "UUU", 'U', UraniumNugget);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(UraniumNugget, 9), Ingredient.of(new ItemStack(MyIngotUranium)));
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myingottitanium"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyIngotTitanium), "UUU", "UUU", "UUU", 'U', TitaniumNugget);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(TitaniumNugget, 9), Ingredient.of(new ItemStack(MyIngotTitanium)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myblockuraniumblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyBlockUraniumBlock), "UUU", "UUU", "UUU", 'U', MyIngotUranium);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyIngotUranium, 9), Ingredient.of(new ItemStack(MyBlockUraniumBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myblocktitaniumblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyBlockTitaniumBlock), "TTT", "TTT", "TTT", 'T', MyIngotTitanium);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyIngotTitanium, 9), Ingredient.of(new ItemStack(MyBlockTitaniumBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myblockmobzillascaleblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyBlockMobzillaScaleBlock), "TTT", "TTT", "TTT", 'T', MyGodzillaScale);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyGodzillaScale, 9), Ingredient.of(new ItemStack(MyBlockMobzillaScaleBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myblockrubyblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyBlockRubyBlock), "TTT", "TTT", "TTT", 'T', MyRuby);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyRuby, 9), Ingredient.of(new ItemStack(MyBlockRubyBlock)));

  }

  private void make_some_more_things_part6()
  {
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myblockamethystblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyBlockAmethystBlock), "TTT", "TTT", "TTT", 'T', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mycrystalpinkblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyCrystalPinkBlock), "TTT", "TTT", "TTT", 'T', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mytigerseyeblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyTigersEyeBlock), "TTT", "TTT", "TTT", 'T', MyTigersEyeIngot);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyAmethyst, 9), Ingredient.of(new ItemStack(MyBlockAmethystBlock)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyCrystalPinkIngot, 9), Ingredient.of(new ItemStack(MyCrystalPinkBlock)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyTigersEyeIngot, 9), Ingredient.of(new ItemStack(MyTigersEyeBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myenderpearlblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEnderPearlBlock), "TTT", "TTT", "TTT", 'T', Items.ENDER_PEARL);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(Items.ENDER_PEARL, 9), Ingredient.of(new ItemStack(MyEnderPearlBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myeyeofenderblock"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyEyeOfEnderBlock), "TTT", "TTT", "TTT", 'T', Items.ENDER_EYE);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(Items.ENDER_EYE, 9), Ingredient.of(new ItemStack(MyEyeOfEnderBlock)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mythunderstaff"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyThunderStaff), "DR ", "RR ", "  R", 'D', Items.DIAMOND, 'R', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mywrench"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyWrench), "D D", " D ", " D ", 'D', Items.IRON_INGOT);

    ItemStack MilkBucket = new ItemStack(Items.MILK_BUCKET);
    ItemStack SomePaper = new ItemStack(Items.PAPER);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyButter, 4), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyCheese, 2), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyButteredPopcorn), Ingredient.of(new ItemStack(MyPopcorn)), Ingredient.of(new ItemStack(MyButter)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyButteredSaltedPopcorn), Ingredient.of(new ItemStack(MyButteredPopcorn)), Ingredient.of(new ItemStack(MySalt)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyButteredSaltedPopcorn), Ingredient.of(new ItemStack(MyPopcorn)), Ingredient.of(new ItemStack(MySalt)), Ingredient.of(new ItemStack(MyButter)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyPopcornBag), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(SomePaper), Ingredient.of(SomePaper), Ingredient.of(SomePaper));

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyRawCornDog, 4), Ingredient.of(new ItemStack(MyCornCob)), Ingredient.of(new ItemStack(Items.CHICKEN)), Ingredient.of(new ItemStack(Items.PORKCHOP)), Ingredient.of(new ItemStack(Items.STICK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyRawBacon, 2), Ingredient.of(new ItemStack(MySalt)), Ingredient.of(new ItemStack(Items.PORKCHOP)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyButterCandy, 4), Ingredient.of(new ItemStack(MyButter)), Ingredient.of(new ItemStack(Items.SUGAR)));

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MySalad, 1), Ingredient.of(new ItemStack(MyLettuce)), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(MyRadish)), Ingredient.of(new ItemStack(Items.CARROT)), Ingredient.of(new ItemStack(Items.BOWL)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyBLT, 1), Ingredient.of(new ItemStack(MyBacon)), Ingredient.of(new ItemStack(MyLettuce)), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(MyButter)), Ingredient.of(new ItemStack(Items.BREAD)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyPizzaItem, 1), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(MyCheese)), Ingredient.of(new ItemStack(MyBacon)), Ingredient.of(new ItemStack(Items.BREAD)));
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myducttapeitem"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyDuctTapeItem), "   ", "AAA", "RRR", 'R', Items.STRING, 'A', Items.SLIME_BALL);

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyCrabbyPatty, 1), Ingredient.of(new ItemStack(MyCrabMeat)), Ingredient.of(new ItemStack(MyLettuce)), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(Items.BREAD)));

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ZooCage2), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ZooCage4), Ingredient.of(new ItemStack(ZooCage2)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ZooCage6), Ingredient.of(new ItemStack(ZooCage4)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ZooCage8), Ingredient.of(new ItemStack(ZooCage6)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ZooCage10), Ingredient.of(new ItemStack(ZooCage8)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(InstantShelter), Ingredient.of(new ItemStack(Blocks.REDSTONE_BLOCK)), Ingredient.of(new ItemStack(Items.STICK)), Ingredient.of(new ItemStack(Blocks.COBBLESTONE)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(InstantGarden), Ingredient.of(new ItemStack(Blocks.REDSTONE_BLOCK)), Ingredient.of(new ItemStack(Items.WHEAT)), Ingredient.of(new ItemStack(Items.GUNPOWDER)));

    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CreeperLauncher, 4), Ingredient.of(new ItemStack(Items.PAPER)), Ingredient.of(new ItemStack(Items.REDSTONE)), Ingredient.of(new ItemStack(Items.STICK)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(NetherLost, 1), Ingredient.of(new ItemStack(Items.NETHER_STAR)), Ingredient.of(new ItemStack(Blocks.NETHERRACK)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_sifter"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Sifter), "RRR", "RAR", "RRR", 'R', Items.STICK, 'A', Items.STRING);
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_magicapple"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MagicApple), "RRR", "RAR", "RRR", 'R', Blocks.REDSTONE_BLOCK, 'A', Items.APPLE);
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_randomdungeon"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(RandomDungeon), "RRR", "RAR", "RRR", 'R', Blocks.REDSTONE_BLOCK, 'A', Items.COAL);

    if (MinersDreamExpensive == 0)
    {
      addShapedRecipe(new ResourceLocation("chaospersists", "recipe_minersdream"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MinersDream), "CCC", "RRR", "GGG", 'R', Blocks.REDSTONE_BLOCK, 'C', Blocks.CACTUS, 'G', Items.GUNPOWDER);
    }
    else
    {
      addShapedRecipe(new ResourceLocation("chaospersists", "recipe_minersdream"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MinersDream), "CCC", "RRR", "GGG", 'R', Blocks.REDSTONE_BLOCK, 'C', Blocks.CACTUS, 'G', Blocks.TNT);
    }
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_stepup"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyStepUp, 8), "GC ", " C ", " C ", 'C', Blocks.COBBLESTONE, 'G', Items.GUNPOWDER);
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_stepdown"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyStepDown, 8), " C ", " C ", "GC ", 'C', Blocks.COBBLESTONE, 'G', Items.GUNPOWDER);
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_stepaccross"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyStepAccross, 8), " C ", "GC ", " C ", 'C', Blocks.COBBLESTONE, 'G', Items.GUNPOWDER);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ExtremeTorch, 4), Ingredient.of(new ItemStack(Items.REDSTONE)), Ingredient.of(new ItemStack(Items.STICK)), Ingredient.of(new ItemStack(Items.COAL)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(ExtremeTorch, 1), Ingredient.of(new ItemStack(Items.REDSTONE)), Ingredient.of(new ItemStack(Blocks.TORCH)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CrystalSticks, 6), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(CrystalTorch, 6), Ingredient.of(new ItemStack(CrystalCoal)), Ingredient.of(new ItemStack(CrystalSticks)));
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_krakenrepellent"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(KrakenRepellent, 1), "D D", "STS", "D D", 'D', MyDeadStinkBug, 'T', ExtremeTorch, 'S', Items.STRING);
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_creeperrepellent"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CreeperRepellent, 1), "D D", "STS", "D D", 'D', GreenGoo, 'T', ExtremeTorch, 'S', Items.STRING);
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyAppleSeed, 6), Ingredient.of(new ItemStack(Items.APPLE)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyCherrySeed, 1), Ingredient.of(new ItemStack(MyCherry)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyPeachSeed, 1), Ingredient.of(new ItemStack(MyPeach)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "egg_mob"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyExperienceCatcher, 1), Ingredient.of(new ItemStack(Items.GLASS_BOTTLE)), Ingredient.of(new ItemStack(Items.STICK)), Ingredient.of(new ItemStack(Items.STRING)));
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_experiencetreeseed"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyExperienceTreeSeed, 1), "EEE", "EAE", "EEE", 'A', MyAppleSeed, 'E', Items.EXPERIENCE_BOTTLE);

    this.nextEntityId = 0;
    int hookid = nextEntityId++;

    int urchinid = nextEntityId++;

    int waterballid = nextEntityId++;

    int inksackid = nextEntityId++;

    int laserballid = nextEntityId++;

    int iceballid = nextEntityId++;

    int acidid = nextEntityId++;

    int Irukandjiid = nextEntityId++;

    int berthahitid = nextEntityId++;

    int purplepowerid = nextEntityId++;

    int rockid = nextEntityId++;

    int thunderboltid = nextEntityId++;

    ItemStack RayStack = new ItemStack(MyRayGun);
    RayStack.setDamageValue(32767);
    addShapelessRecipe(new ResourceLocation("chaospersists", "repair_raygun"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MyRayGun), Ingredient.of(new ItemStack(Blocks.REDSTONE_BLOCK)), Ingredient.of(RayStack));

    ItemStack SquidStack = new ItemStack(MySquidZooka);
    SquidStack.setDamageValue(32767);
    addShapelessRecipe(new ResourceLocation("chaospersists", "repair_squidzooka"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(MySquidZooka), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(SquidStack));

    GirlfriendID = nextEntityId++;

    RedCowID = nextEntityId++;

    GoldCowID = nextEntityId++;

    EnchantedCowID = nextEntityId++;

    ButterflyID = nextEntityId++;

    LunaMothID = nextEntityId++;

    MosquitoID = nextEntityId++;

    FireflyID = nextEntityId++;

    BeeID = nextEntityId++;

    MothraID = nextEntityId++;

    AntID = nextEntityId++;
    RedAntID = nextEntityId++;
    RainbowAntID = nextEntityId++;
    UnstableAntID = nextEntityId++;

    Robot1ID = nextEntityId++;
    Robot2ID = nextEntityId++;
    Robot3ID = nextEntityId++;
    Robot4ID = nextEntityId++;
    Robot5ID = nextEntityId++;

    AlosaurusID = nextEntityId++;
    CryolophosaurusID = nextEntityId++;
    BasiliskID = nextEntityId++;
    CamarasaurusID = nextEntityId++;
    HydroliscID = nextEntityId++;
    VelocityRaptorID = nextEntityId++;

    DragonflyID = nextEntityId++;

    EmperorScorpionID = nextEntityId++;

    ScorpionID = nextEntityId++;

    CaveFisherID = nextEntityId++;

    SpyroID = nextEntityId++;

    BaryonyxID = nextEntityId++;

    GammaMetroidID = nextEntityId++;

    CockateilID = nextEntityId++;

    RubyBirdID = nextEntityId++;

    KyuubiID = nextEntityId++;

    WaterDragonID = nextEntityId++;

    AttackSquidID = nextEntityId++;

    AlienID = nextEntityId++;

    ElevatorID = nextEntityId++;

    KrakenID = nextEntityId++;

    LizardID = nextEntityId++;

    CephadromeID = nextEntityId++;

    DragonID = nextEntityId++;

    ChipmunkID = nextEntityId++;

    GazelleID = nextEntityId++;

    OstrichID = nextEntityId++;

    TrooperBugID = nextEntityId++;

    SpitBugID = nextEntityId++;

    StinkBugID = nextEntityId++;

    TshirtID = nextEntityId++;

    IslandID = nextEntityId++;

    IslandTooID = nextEntityId++;

    CreepingHorrorID = nextEntityId++;

    TerribleTerrorID = nextEntityId++;

    CliffRacerID = nextEntityId++;

    TriffidID = nextEntityId++;

    PitchBlackID = nextEntityId++;

    LurkingTerrorID = nextEntityId++;

    GodzillaID = nextEntityId++;

    GhostID = nextEntityId++;

    GhostSkellyID = nextEntityId++;

    WormSmallID = nextEntityId++;

    WormMediumID = nextEntityId++;

    WormLargeID = nextEntityId++;

    CassowaryID = nextEntityId++;

    CloudSharkID = nextEntityId++;

    GoldFishID = nextEntityId++;

    LeafMonsterID = nextEntityId++;

    GodzillaHeadID = nextEntityId++;
  }

  private void make_some_more_things_part7()
  {

    EnderKnightID = nextEntityId++;

    EnderReaperID = nextEntityId++;

    BeaverID = nextEntityId++;

    TermiteID = nextEntityId++;

    FairyID = nextEntityId++;

    PeacockID = nextEntityId++;

    RotatorID = nextEntityId++;

    VortexID = nextEntityId++;

    DungeonBeastID = nextEntityId++;

    RatID = nextEntityId++;

    FlounderID = nextEntityId++;

    WhaleID = nextEntityId++;

    IrukandjiID = nextEntityId++;

    SkateID = nextEntityId++;

    UrchinID = nextEntityId++;

    MantisID = nextEntityId++;

    HerculesBeetleID = nextEntityId++;

    TRexID = nextEntityId++;

    StinkyID = nextEntityId++;

    CoinID = nextEntityId++;

    TheKingID = nextEntityId++;

    KingHeadID = nextEntityId++;

    TheQueenID = nextEntityId++;

    QueenHeadID = nextEntityId++;

    BoyfriendID = nextEntityId++;

    ThePrinceID = nextEntityId++;

    MolenoidID = nextEntityId++;

    SeaMonsterID = nextEntityId++;

    SeaViperID = nextEntityId++;

    EasterBunnyID = nextEntityId++;

    CaterKillerID = nextEntityId++;

    CrystalCowID = nextEntityId++;

    LeonID = nextEntityId++;

    HammerheadID = nextEntityId++;

    RubberDuckyID = nextEntityId++;

    ThePrinceTeenID = nextEntityId++;

    BandPID = nextEntityId++;

    RockBaseID = nextEntityId++;

    BrutalflyID = nextEntityId++;

    NastysaurusID = nextEntityId++;

    PointysaurusID = nextEntityId++;

    CricketID = nextEntityId++;

    ThePrincessID = nextEntityId++;

    FrogID = nextEntityId++;

    ThePrinceAdultID = nextEntityId++;

    SpiderRobotID = nextEntityId++;

    SpiderDriverID = nextEntityId++;

    JefferyID = nextEntityId++;

    AntRobotID = nextEntityId++;

    CrabID = nextEntityId++;

    GregorianCalendar gcalendar = new GregorianCalendar();

    int nowmonth = gcalendar.get(2);
    int nowday = gcalendar.get(5);

    if ((nowmonth == 9) && (nowday == 31)) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BEACH);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_BADLANDS_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BADLANDS_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BADLANDS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA_HILLS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.DARK_FOREST);

          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BEACH);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_BADLANDS_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BADLANDS_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BADLANDS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA_HILLS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    if ((nowmonth == 1) && (nowday == 14)) {
      valentines_day = 1;
    }

    if ((nowmonth == 3) && (nowday == 20)) {
      easter_day = 1;
    }

    if (GirlfriendEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 30, 8, 15, EntityClassification.CREATURE, Biomes.BEACH);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 10, 3, 6, EntityClassification.CREATURE, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 8, 2, 5, EntityClassification.CREATURE, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 5, 2, 3, EntityClassification.CREATURE, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 10, 3, 6, EntityClassification.CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 10, 3, 6, EntityClassification.CREATURE, Biomes.STONE_SHORE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 5, 2, 4, EntityClassification.CREATURE, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 5, 2, 5, EntityClassification.CREATURE, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 5, 2, 5, EntityClassification.CREATURE, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 5, 2, 5, EntityClassification.CREATURE, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 2, 1, 3, EntityClassification.CREATURE, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "girlfriend")), 2, 1, 3, EntityClassification.CREATURE, Biomes.SAVANNA_PLATEAU);
    }

    if (BoyfriendEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 30, 8, 15, EntityClassification.CREATURE, Biomes.BEACH);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 10, 3, 6, EntityClassification.CREATURE, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 8, 2, 5, EntityClassification.CREATURE, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 5, 2, 3, EntityClassification.CREATURE, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 10, 3, 6, EntityClassification.CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 10, 3, 6, EntityClassification.CREATURE, Biomes.STONE_SHORE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 5, 2, 4, EntityClassification.CREATURE, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 5, 2, 5, EntityClassification.CREATURE, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 5, 2, 5, EntityClassification.CREATURE, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 5, 2, 5, EntityClassification.CREATURE, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 2, 1, 3, EntityClassification.CREATURE, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "boyfriend")), 2, 1, 3, EntityClassification.CREATURE, Biomes.SAVANNA_PLATEAU);
    }

    if (BeaverEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "beaver")), 10, 2, 4, EntityClassification.CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "beaver")), 3, 2, 4, EntityClassification.CREATURE, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "beaver")), 2, 2, 4, EntityClassification.CREATURE, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "beaver")), 2, 2, 5, EntityClassification.CREATURE, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "beaver")), 5, 2, 5, EntityClassification.CREATURE, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "beaver")), 5, 2, 5, EntityClassification.CREATURE, Biomes.TAIGA);
    }

    if (CowEnable != 0)
    {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "apple_cow")), 8, 4, 8, EntityClassification.CREATURE, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "apple_cow")), 8, 4, 8, EntityClassification.CREATURE, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "apple_cow")), 5, 2, 5, EntityClassification.CREATURE, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "apple_cow")), 5, 2, 5, EntityClassification.CREATURE, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "apple_cow")), 8, 1, 3, EntityClassification.CREATURE, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "apple_cow")), 2, 1, 3, EntityClassification.CREATURE, Biomes.SAVANNA_PLATEAU);

          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "golden_apple_cow")), 5, 2, 6, EntityClassification.CREATURE, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "golden_apple_cow")), 5, 2, 6, EntityClassification.CREATURE, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "golden_apple_cow")), 5, 2, 5, EntityClassification.CREATURE, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "golden_apple_cow")), 5, 2, 5, EntityClassification.CREATURE, Biomes.TAIGA);

          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "enchanted_golden_apple_cow")), 3, 2, 4, EntityClassification.CREATURE, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "enchanted_golden_apple_cow")), 3, 2, 4, EntityClassification.CREATURE, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "enchanted_golden_apple_cow")), 5, 2, 5, EntityClassification.CREATURE, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "enchanted_golden_apple_cow")), 15, 3, 6, EntityClassification.CREATURE, net.minecraft.util.RegistryKey.create(net.minecraft.util.registry.Registry.BIOME_REGISTRY, new ResourceLocation("minecraft", "mushroom_fields")));
    }

    if (CriminalEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "criminal")), 20, 1, 2, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "criminal")), 20, 1, 2, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "criminal")), 20, 1, 2, EntityClassification.AMBIENT, Biomes.SAVANNA);
    }

    if (WormEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "large_worm")), 25, 1, 1, EntityClassification.CREATURE, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "large_worm")), 15, 1, 1, EntityClassification.CREATURE, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "large_worm")), 10, 1, 1, EntityClassification.CREATURE, Biomes.SAVANNA_PLATEAU);
    }

    if (ButterflyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 8, 5, 15, EntityClassification.AMBIENT, Biomes.BEACH);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 30, 3, 6, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 20, 2, 5, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 20, 2, 5, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 10, 2, 5, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 20, 3, 6, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 20, 2, 5, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 20, 4, 10, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 15, 2, 4, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 10, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "butterfly")), 10, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (MothEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 8, 1, 2, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 8, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 10, 2, 5, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 20, 3, 6, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 20, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 10, 2, 5, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 20, 2, 5, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 10, 1, 5, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 15, 2, 4, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 10, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "moth")), 10, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (CassowaryEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 5, 2, 4, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 5, 2, 5, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 3, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cassowary")), 10, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if ((EasterBunnyEnable != 0) && (easter_day != 0)) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 10, 1, 2, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 10, 1, 2, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 10, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "easter_bunny")), 8, 1, 2, EntityClassification.AMBIENT, Biomes.TAIGA);
    }

    if (FireflyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 5, 10, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 5, 10, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 10, 4, 8, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 5, 10, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 5, 10, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 10, 3, 6, EntityClassification.AMBIENT, Biomes.STONE_SHORE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 3, 10, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 3, 10, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 2, 10, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 2, 10, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 15, 2, 10, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 10, 2, 8, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "firefly")), 10, 2, 8, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (WhaleEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "whale")), 1, 1, 2, EntityClassification.WATER_CREATURE, Biomes.DEEP_OCEAN);
    }

    if (BeeEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 5, 3, 5, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 5, 2, 5, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 3, 2, 4, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 3, 2, 4, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 3, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bee")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (MantisEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 2, 4, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mantis")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (HerculesBeetleEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.TAIGA_HILLS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 5, 1, 1, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA_HILLS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hercules_beetle")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
    }

    if (MolenoidEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "molenoid")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "molenoid")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "molenoid")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (CaterKillerEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 4, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 4, 1, 2, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 6, 1, 2, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "caterkiller")), 10, 1, 2, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    if (ChipmunkEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 8, 3, 6, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 5, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 4, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 5, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 4, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 10, 2, 5, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 2, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "chipmunk")), 6, 2, 5, EntityClassification.AMBIENT, Biomes.TAIGA);
    }

    if (OstrichEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ostrich")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ostrich")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.STONE_SHORE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ostrich")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ostrich")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (CephadromeEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cephadrome")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.SNOWY_TUNDRA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cephadrome")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA);
    }

    if (MosquitoEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mosquito")), 30, 5, 10, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mosquito")), 20, 5, 10, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mosquito")), 20, 5, 10, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mosquito")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    if (GhostEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 5, 10, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 10, 5, 10, EntityClassification.AMBIENT, Biomes.TAIGA_HILLS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 6, 4, 6, EntityClassification.AMBIENT, Biomes.FROZEN_RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 2, 1, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    if (GhostSkellyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 5, 10, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 10, 5, 10, EntityClassification.AMBIENT, Biomes.TAIGA_HILLS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 6, 4, 6, EntityClassification.AMBIENT, Biomes.FROZEN_RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 2, 1, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ghost_pumpkin_skelly")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    if (DragonflyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "dragonfly")), 5, 3, 5, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "dragonfly")), 4, 1, 2, EntityClassification.AMBIENT, Biomes.RIVER);
    }

    if (KyuubiEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "kyuubi")), 10, 1, 1, EntityClassification.MONSTER, Biomes.NETHER_WASTES);
    }

    if (StinkyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stinky")), 2, 1, 1, EntityClassification.MONSTER, Biomes.NETHER_WASTES);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stinky")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.BADLANDS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stinky")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.BADLANDS_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stinky")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.WOODED_BADLANDS_PLATEAU);
    }

    if (CockateilEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 10, 2, 5, EntityClassification.AMBIENT, Biomes.BEACH);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 10, 1, 2, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 10, 2, 4, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 25, 5, 10, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 20, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 35, 5, 10, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 25, 5, 10, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 10, 2, 4, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 5, 3, 6, EntityClassification.AMBIENT, Biomes.STONE_SHORE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 5, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 15, 2, 5, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 11, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "bird")), 11, 1, 5, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }

    if (HydroliscEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hydrolisc")), 25, 3, 6, EntityClassification.CREATURE, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hydrolisc")), 15, 2, 5, EntityClassification.CREATURE, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hydrolisc")), 10, 1, 3, EntityClassification.CREATURE, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "hydrolisc")), 5, 3, 6, EntityClassification.CREATURE, Biomes.STONE_SHORE);
    }

    if (MothraEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mothra")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "mothra")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
    }
    if (BrutalflyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "brutalfly")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "brutalfly")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "brutalfly")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.BADLANDS_PLATEAU);
    }
    if (WaterDragonEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "water_dragon")), 5, 1, 1, EntityClassification.WATER_CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "water_dragon")), 3, 1, 1, EntityClassification.WATER_CREATURE, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "water_dragon")), 2, 1, 1, EntityClassification.WATER_CREATURE, Biomes.OCEAN);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "water_dragon")), 2, 1, 1, EntityClassification.WATER_CREATURE, Biomes.STONE_SHORE);
    }
    if (SeaMonsterEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "sea_monster")), 4, 1, 1, EntityClassification.WATER_CREATURE, Biomes.OCEAN);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "sea_monster")), 2, 1, 1, EntityClassification.WATER_CREATURE, Biomes.SWAMP);
    }
    if (SeaViperEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "sea_viper")), 3, 1, 1, EntityClassification.WATER_CREATURE, Biomes.OCEAN);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "sea_viper")), 2, 1, 1, EntityClassification.WATER_CREATURE, Biomes.STONE_SHORE);
    }
    if (CrabEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "crab")), 2, 3, 6, EntityClassification.WATER_CREATURE, Biomes.OCEAN);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "crab")), 1, 3, 6, EntityClassification.WATER_CREATURE, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "crab")), 1, 2, 4, EntityClassification.WATER_CREATURE, Biomes.STONE_SHORE);
    }
    if (AttackSquidEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "attack_squid")), 12, 6, 10, EntityClassification.WATER_CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "attack_squid")), 10, 5, 9, EntityClassification.WATER_CREATURE, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "attack_squid")), 7, 4, 8, EntityClassification.WATER_CREATURE, Biomes.OCEAN);
    }
    if (LizardEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "lizard")), 5, 2, 4, EntityClassification.WATER_CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "lizard")), 4, 2, 4, EntityClassification.WATER_CREATURE, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "lizard")), 2, 2, 4, EntityClassification.WATER_CREATURE, Biomes.OCEAN);
    }
    if (RubberDuckyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "rubber_ducky")), 10, 10, 20, EntityClassification.WATER_CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "rubber_ducky")), 4, 4, 6, EntityClassification.WATER_CREATURE, Biomes.STONE_SHORE);
    }
    if (BasiliskEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "basilisk")), 3, 1, 1, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "basilisk")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "basilisk")), 4, 1, 2, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "basilisk")), 15, 1, 2, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }
    if (EmperorScorpionEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "emperor_scorpion")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "emperor_scorpion")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.SAVANNA);
    }
    if (TrooperBugEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "jumpy_bug")), 3, 1, 2, EntityClassification.AMBIENT, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "jumpy_bug")), 1, 1, 1, EntityClassification.AMBIENT, Biomes.BADLANDS);
    }
    if (SpitBugEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "spit_bug")), 6, 1, 2, EntityClassification.AMBIENT, Biomes.SWAMP);
    }
    if (StinkBugEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stink_bug")), 10, 2, 4, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stink_bug")), 8, 2, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stink_bug")), 6, 2, 4, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stink_bug")), 4, 2, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "stink_bug")), 8, 2, 5, EntityClassification.AMBIENT, Biomes.SAVANNA);
    }
    if (ScorpionEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 15, 3, 6, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 28, 2, 4, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 15, 3, 5, EntityClassification.AMBIENT, Biomes.SAVANNA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 15, 2, 4, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 6, 1, 3, EntityClassification.AMBIENT, Biomes.BADLANDS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 4, 1, 3, EntityClassification.AMBIENT, Biomes.BADLANDS_PLATEAU);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "scorpion")), 5, 3, 6, EntityClassification.AMBIENT, Biomes.WOODED_BADLANDS_PLATEAU);
    }

    if (LeafMonsterEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 5, 2, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 5, 1, 2, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 3, 2, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 3, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 3, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 2, 3, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 2, 2, 5, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "leaf_monster")), 2, 2, 5, EntityClassification.AMBIENT, Biomes.TAIGA);
    }

    if (EnderKnightEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 4, 2, 4, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 4, 2, 4, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 4, 2, 4, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 4, 2, 4, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 4, 2, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 2, 2, 4, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 2, 2, 4, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 2, 2, 4, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_knight")), 20, 2, 4, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }
    if (EnderReaperEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 2, 1, 2, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 1, 1, 2, EntityClassification.AMBIENT, Biomes.DESERT);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ender_reaper")), 38, 2, 4, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    if (CoinEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "coin")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "coin")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "coin")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "coin")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "coin")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.SNOWY_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "coin")), 2, 1, 1, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
    }

    if (CricketEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 3, 2, 4, EntityClassification.AMBIENT, Biomes.FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 2, 2, 4, EntityClassification.AMBIENT, Biomes.WOODED_MOUNTAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 3, 2, 4, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 2, 3, 5, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 3, 4, 8, EntityClassification.AMBIENT, Biomes.PLAINS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 2, 2, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 2, 2, 6, EntityClassification.AMBIENT, Biomes.BIRCH_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 3, 1, 4, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 2, 1, 6, EntityClassification.AMBIENT, Biomes.GIANT_TREE_TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 2, 1, 6, EntityClassification.AMBIENT, Biomes.TAIGA);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "cricket")), 1, 1, 4, EntityClassification.AMBIENT, Biomes.SAVANNA_PLATEAU);
    }
    if (FrogEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "frog")), 20, 3, 6, EntityClassification.WATER_CREATURE, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "frog")), 3, 3, 6, EntityClassification.AMBIENT, Biomes.RIVER);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "frog")), 3, 3, 6, EntityClassification.AMBIENT, Biomes.JUNGLE);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "frog")), 20, 2, 6, EntityClassification.WATER_CREATURE, Biomes.SWAMP);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "frog")), 2, 2, 6, EntityClassification.AMBIENT, Biomes.SWAMP);
    }

    if (PeacockEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "peacock")), 1, 1, 3, EntityClassification.AMBIENT, Biomes.BADLANDS);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "peacock")), 1, 1, 3, EntityClassification.AMBIENT, Biomes.BADLANDS_PLATEAU);
    }

    if (FairyEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "fairy")), 25, 2, 4, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }
    if (RatEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "rat")), 35, 10, 20, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "rat")), 25, 2, 8, EntityClassification.AMBIENT, Biomes.TAIGA);
    }
    if (DungeonBeastEnable != 0) {
          addChaosSpawn(ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "dungeon_beast")), 20, 2, 4, EntityClassification.AMBIENT, Biomes.DARK_FOREST);
    }

    int shoeid = nextEntityId++;

  }

  private void make_some_more_things_part8()
  {
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_ultimatehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(UltimateHelmet), "   ", "TIT", "U U", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_ultimatehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(UltimateHelmet), "TIT", "U U", "   ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_ultimatebody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(UltimateBody), "I I", "TTT", "UUU", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_ultimatelegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(UltimateLegs), "III", "T T", "U U", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_ultimateboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(UltimateBoots), "   ", "T T", "U U", 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_ultimateboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(UltimateBoots), "T T", "U U", "   ", 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lavaeelhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LavaEelHelmet), "   ", "***", "* *", '*', MyLavaEel);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lavaeelhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LavaEelHelmet), "***", "* *", "   ", '*', MyLavaEel);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lavaeelbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LavaEelBody), "* *", "***", "***", '*', MyLavaEel);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lavaeellegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LavaEelLegs), "***", "* *", "* *", '*', MyLavaEel);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lavaeelboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LavaEelBoots), "   ", "* *", "* *", '*', MyLavaEel);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mothscalehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MothScaleHelmet), "   ", "***", "* *", '*', MyMothScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mothscalehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MothScaleHelmet), "***", "* *", "   ", '*', MyMothScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mothscalebody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MothScaleBody), "* *", "***", "***", '*', MyMothScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mothscalelegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MothScaleLegs), "***", "* *", "* *", '*', MyMothScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mothscaleboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MothScaleBoots), "   ", "* *", "* *", '*', MyMothScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_emeraldhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(EmeraldHelmet), "   ", "***", "* *", '*', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_emeraldhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(EmeraldHelmet), "***", "* *", "   ", '*', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_emeraldbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(EmeraldBody), "* *", "***", "***", '*', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_emeraldlegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(EmeraldLegs), "***", "* *", "* *", '*', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_emeraldboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(EmeraldBoots), "   ", "* *", "* *", '*', Items.EMERALD);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_rubyhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(RubyHelmet), "   ", "***", "* *", '*', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_rubyhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(RubyHelmet), "***", "* *", "   ", '*', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_rubybody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(RubyBody), "* *", "***", "***", '*', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_rubylegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(RubyLegs), "***", "* *", "* *", '*', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_rubyboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(RubyBoots), "   ", "* *", "* *", '*', MyRuby);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_amethysthelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(AmethystHelmet), "   ", "***", "* *", '*', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_amethysthelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(AmethystHelmet), "***", "* *", "   ", '*', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_amethystbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(AmethystBody), "* *", "***", "***", '*', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_amethystlegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(AmethystLegs), "***", "* *", "* *", '*', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_amethystboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(AmethystBoots), "   ", "* *", "* *", '*', MyAmethyst);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_crystalpinkhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CrystalPinkHelmet), "   ", "***", "* *", '*', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_crystalpinkhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CrystalPinkHelmet), "***", "* *", "   ", '*', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_crystalpinkbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CrystalPinkBody), "* *", "***", "***", '*', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_crystalpinklegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CrystalPinkLegs), "***", "* *", "* *", '*', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_crystalpinkboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CrystalPinkBoots), "   ", "* *", "* *", '*', MyCrystalPinkIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mobzillahelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MobzillaHelmet), "   ", "***", "* *", '*', MyGodzillaScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mobzillahelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MobzillaHelmet), "***", "* *", "   ", '*', MyGodzillaScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mobzillabody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MobzillaBody), "* *", "***", "***", '*', MyGodzillaScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mobzillalegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MobzillaLegs), "***", "* *", "* *", '*', MyGodzillaScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_mobzillaboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MobzillaBoots), "   ", "* *", "* *", '*', MyGodzillaScale);

  }

  private void make_some_more_things_part9()
  {
    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lapishelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LapisHelmet), "   ", "***", "* *", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lapishelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LapisHelmet), "***", "* *", "   ", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lapisbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LapisBody), "* *", "***", "***", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lapislegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LapisLegs), "***", "* *", "* *", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_lapisboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(LapisBoots), "   ", "* *", "* *", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_queenhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(QueenHelmet), "   ", "***", "* *", '*', MyQueenScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_queenhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(QueenHelmet), "***", "* *", "   ", '*', MyQueenScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_queenbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(QueenBody), "* *", "***", "***", '*', MyQueenScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_queenlegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(QueenLegs), "***", "* *", "* *", '*', MyQueenScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_queenboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(QueenBoots), "   ", "* *", "* *", '*', MyQueenScale);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_peacockfeatherhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(PeacockFeatherHelmet), "   ", "***", "* *", '*', MyPeacockFeather);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_peacockfeatherhelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(PeacockFeatherHelmet), "***", "* *", "   ", '*', MyPeacockFeather);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_peacockfeatherbody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(PeacockFeatherBody), "* *", "***", "***", '*', MyPeacockFeather);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_peacockfeatherlegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(PeacockFeatherLegs), "***", "* *", "* *", '*', MyPeacockFeather);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_peacockfeatherboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(PeacockFeatherBoots), "   ", "* *", "* *", '*', MyPeacockFeather);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_tigerseyehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(TigersEyeHelmet), "   ", "***", "* *", '*', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_tigerseyehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(TigersEyeHelmet), "***", "* *", "   ", '*', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_tigerseyebody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(TigersEyeBody), "* *", "***", "***", '*', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_tigerseyelegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(TigersEyeLegs), "***", "* *", "* *", '*', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_tigerseyeboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(TigersEyeBoots), "   ", "* *", "* *", '*', MyTigersEyeIngot);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_experiencehelmet"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(ExperienceHelmet), "EEE", "EAE", "EEE", 'A', EmeraldHelmet, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_experiencebody"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(ExperienceBody), "EEE", "EAE", "EEE", 'A', EmeraldBody, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_experiencelegs"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(ExperienceLegs), "EEE", "EAE", "EEE", 'A', EmeraldLegs, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_experienceboots"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(ExperienceBoots), "EEE", "EAE", "EEE", 'A', EmeraldBoots, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_blocks.web"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(Blocks.COBWEB), "***", "* *", "***", '*', Items.STRING);

    int cageid = nextEntityId++;

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_cageempty_iron"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CageEmpty, 2), "IWI", "W W", "IWI", 'W', Items.STICK, 'I', Items.IRON_INGOT);

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_cageempty_crystal"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(CageEmpty, 2), "IWI", "W W", "IWI", 'W', CrystalSticks, 'I', MyCrystalPinkIngot);

    int arrowid = nextEntityId++;

    int irukandiarrowid = nextEntityId++;
    addShapelessRecipe(new ResourceLocation("chaospersists", "planks_skytree"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(Blocks.OAK_PLANKS, 4), Ingredient.of(new ItemStack(MySkyTreeLog)));
    addShapelessRecipe(new ResourceLocation("chaospersists", "planks_duplicator"), new ResourceLocation("chaospersists", "eggs"), new ItemStack(Blocks.OAK_PLANKS, 4), Ingredient.of(new ItemStack(MyDT)));

    addShapedRecipe(new ResourceLocation("chaospersists", "recipe_myelevator"), new ResourceLocation("chaospersists", "chaospersists"), new ItemStack(MyElevator), "   ", "WWW", "DRD", 'W', Blocks.OAK_PLANKS, 'R', Items.REDSTONE, 'D', Items.DIAMOND);

    DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> proxy.registerKeyboardInput());

    proxy.registerNetworkStuff();

    MinecraftForge.EVENT_BUS.addListener((BiomeLoadingEvent e) -> {
      if (chaospersistsGen != null) {
        chaospersistsGen.onBiomeLoad(e);
      }
    });

    chaosDimensionTypeByLegacyId.put(Integer.valueOf(DimensionID), createChaosDimensionType(true));
    chaosDimensionTypeByLegacyId.put(Integer.valueOf(DimensionID2), createChaosDimensionType(true));
    chaosDimensionTypeByLegacyId.put(Integer.valueOf(DimensionID3), createChaosDimensionType(true));
    chaosDimensionTypeByLegacyId.put(Integer.valueOf(DimensionID4), createChaosDimensionType(true));
    chaosDimensionTypeByLegacyId.put(Integer.valueOf(DimensionID5), createChaosDimensionType(true));
    chaosDimensionTypeByLegacyId.put(Integer.valueOf(DimensionID6), createChaosDimensionType(true));

    DoDispenserRegistrations();
  }

  private void onRegisterEntities(net.minecraftforge.event.RegistryEvent.Register<EntityType<?>> event) {
    registerAllChaosEntities(event);
  }

  private void registerAllChaosEntities(net.minecraftforge.event.RegistryEvent.Register<EntityType<?>> event) {
    registerChaosEntityType(event, "ultimate_fish_hook", UltimateFishHook.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "sunspot_urchin", SunspotUrchin.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "water_ball", WaterBall.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "ink_sack", InkSack.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "laser_ball", LaserBall.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "ice_ball", IceBall.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "acid", Acid.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "dead_irukandji", DeadIrukandji.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "bertha_hit", BerthaHit.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "purple_power", PurplePower.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "thrown_rock", EntityThrownRock.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "thunder_bolt", ThunderBolt.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "girlfriend", Girlfriend.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "apple_cow", RedCow.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "golden_apple_cow", GoldCow.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "enchanted_golden_apple_cow", EnchantedCow.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "butterfly", EntityButterfly.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "moth", EntityLunaMoth.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "mosquito", EntityMosquito.class, EntityClassification.CREATURE, 16, 1, false);
    registerChaosEntityType(event, "firefly", Firefly.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "bee", Bee.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "mothra", Mothra.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "ant", EntityAnt.class, EntityClassification.CREATURE, 16, 1, false);
    registerChaosEntityType(event, "red_ant", EntityRedAnt.class, EntityClassification.CREATURE, 16, 1, false);
    registerChaosEntityType(event, "rainbow_ant", EntityRainbowAnt.class, EntityClassification.CREATURE, 16, 1, false);
    registerChaosEntityType(event, "unstable_ant", EntityUnstableAnt.class, EntityClassification.CREATURE, 16, 1, false);
    registerChaosEntityType(event, "bomb_omb", Robot1.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "robo_pounder", Robot2.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "robo_gunner", Robot3.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "robo_warrior", Robot4.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "robo_sniper", Robot5.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "alosaurus", Alosaurus.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "cryolophosaurus", Cryolophosaurus.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "basilisk", Basilisk.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "camarasaurus", Camarasaurus.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "hydrolisc", Hydrolisc.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "velocity_raptor", VelocityRaptor.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "dragonfly", Dragonfly.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "emperor_scorpion", EmperorScorpion.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "scorpion", Scorpion.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "cave_fisher", CaveFisher.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "baby_dragon", Spyro.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "baryonyx", Baryonyx.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "gamma_metroid", GammaMetroid.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "wtf", GammaMetroid.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "bird", Cockateil.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "ruby_bird", RubyBird.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "kyuubi", Kyuubi.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "water_dragon", WaterDragon.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "attack_squid", AttackSquid.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "alien", Alien.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "hoverboard", Elevator.class, EntityClassification.MISC, 128, 1, true);
    registerChaosEntityType(event, "the_kraken", Kraken.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "lizard", Lizard.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "cephadrome", Cephadrome.class, EntityClassification.MISC, 128, 1, true);
    registerChaosEntityType(event, "dragon", Dragon.class, EntityClassification.MISC, 128, 1, true);
    registerChaosEntityType(event, "chipmunk", Chipmunk.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "gazelle", Gazelle.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "ostrich", Ostrich.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "jumpy_bug", TrooperBug.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "spit_bug", SpitBug.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "stink_bug", StinkBug.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "tshirt", Tshirt.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "island", Island.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "island_too", IslandToo.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "creeping_horror", CreepingHorror.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "terrible_terror", TerribleTerror.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "cliff_racer", CliffRacer.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "triffid", Triffid.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "nightmare", PitchBlack.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "lurking_terror", LurkingTerror.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "mobzilla", Godzilla.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "ghost", Ghost.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "ghost_pumpkin_skelly", GhostSkelly.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "small_worm", WormSmall.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "medium_worm", WormMedium.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "large_worm", WormLarge.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "cassowary", Cassowary.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "cloud_shark", CloudShark.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "gold_fish", GoldFish.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "leaf_monster", LeafMonster.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "mobzilla_head", GodzillaHead.class, EntityClassification.MISC, 128, 10, true);
    registerChaosEntityType(event, "ender_knight", EnderKnight.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "ender_reaper", EnderReaper.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "beaver", Beaver.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "termite", Termite.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "fairy", Fairy.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "peacock", Peacock.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "rotator", Rotator.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "vortex", Vortex.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "dungeon_beast", DungeonBeast.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "rat", Rat.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "flounder", Flounder.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "whale", Whale.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "irukandji", Irukandji.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "skate", Skate.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "crystal_urchin", Urchin.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "mantis", Mantis.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "hercules_beetle", HerculesBeetle.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "trex", TRex.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "t._rex", TRex.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "stinky", Stinky.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "coin", Coin.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "the_king", TheKing.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "king_head", KingHead.class, EntityClassification.MISC, 128, 10, true);
    registerChaosEntityType(event, "the_queen", TheQueen.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "queen_head", QueenHead.class, EntityClassification.MISC, 128, 10, true);
    registerChaosEntityType(event, "boyfriend", Boyfriend.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "the_prince", ThePrince.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "molenoid", Molenoid.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "sea_monster", SeaMonster.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "sea_viper", SeaViper.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "easter_bunny", EasterBunny.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "caterkiller", CaterKiller.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "crystal_apple_cow", CrystalCow.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "leonopteryx", Leon.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "hammerhead", Hammerhead.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "rubber_ducky", RubberDucky.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "the_young_prince", ThePrinceTeen.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "criminal", BandP.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "rock", RockBase.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "brutalfly", Brutalfly.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "nastysaurus", Nastysaurus.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "pointysaurus", Pointysaurus.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "cricket", Cricket.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "the_princess", ThePrincess.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "frog", Frog.class, EntityClassification.CREATURE, 32, 1, false);
    registerChaosEntityType(event, "the_young_adult_prince", ThePrinceAdult.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "robot_spider", SpiderRobot.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "spider_driver", SpiderDriver.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "jeffery", GiantRobot.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "robot_red_ant", AntRobot.class, EntityClassification.CREATURE, 128, 1, false);
    registerChaosEntityType(event, "crab", Crab.class, EntityClassification.CREATURE, 64, 1, false);
    registerChaosEntityType(event, "shoes", Shoes.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "entity_cage", EntityCage.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "ultimate_arrow", UltimateArrow.class, EntityClassification.MISC, 64, 1, true);
    registerChaosEntityType(event, "irukandji_arrow", IrukandjiArrow.class, EntityClassification.MISC, 64, 1, true);
  }

  private static <T extends Entity> T constructChaosEntity(Class<T> entityClass, EntityType<T> entityType, World world) {
    try {
      return entityClass.getConstructor(EntityType.class, World.class).newInstance(entityType, world);
    } catch (ReflectiveOperationException ex) {
      throw new IllegalStateException("Failed constructing " + entityClass.getName(), ex);
    }
  }

  private static <T extends Entity> void registerChaosEntityType(
      net.minecraftforge.event.RegistryEvent.Register<EntityType<?>> event,
      String path, Class<T> entityClass, EntityClassification classification,
      int trackingRange, int updateInterval, boolean velocityUpdates) {
    ResourceLocation id = new ResourceLocation("chaospersists", path);
    EntityType<T> type = EntityType.Builder.<T>of(
        (entityType, world) -> constructChaosEntity(entityClass, entityType, world),
        classification)
        .setTrackingRange(trackingRange).setUpdateInterval(updateInterval)
        .setShouldReceiveVelocityUpdates(velocityUpdates)
        .build(id.toString());
    type.setRegistryName(id);
    event.getRegistry().register(type);
  }

  public static EntityType<?> getChaosEntityType(String path) {
    return ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", path));
  }

  @SuppressWarnings("unchecked")
  public static EntityType<? extends LivingEntity> getChaosLivingEntityType(String path) {
    return (EntityType<? extends LivingEntity>) getChaosEntityType(path);
  }

  public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
    event.put(getChaosLivingEntityType("purple_power"), PurplePower.createAttributes());
    event.put(getChaosLivingEntityType("girlfriend"), Girlfriend.createAttributes());
    event.put(getChaosLivingEntityType("butterfly"), EntityButterfly.createAttributes());
    event.put(getChaosLivingEntityType("moth"), EntityLunaMoth.createAttributes());
    event.put(getChaosLivingEntityType("mosquito"), EntityMosquito.createAttributes());
    event.put(getChaosLivingEntityType("firefly"), Firefly.createAttributes());
    event.put(getChaosLivingEntityType("bee"), Bee.createAttributes());
    event.put(getChaosLivingEntityType("mothra"), Mothra.createAttributes());
    event.put(getChaosLivingEntityType("ant"), EntityAnt.createAttributes());
    event.put(getChaosLivingEntityType("red_ant"), EntityRedAnt.createAttributes());
    event.put(getChaosLivingEntityType("rainbow_ant"), EntityRainbowAnt.createAttributes());
    event.put(getChaosLivingEntityType("unstable_ant"), EntityUnstableAnt.createAttributes());
    event.put(getChaosLivingEntityType("bomb_omb"), Robot1.createAttributes());
    event.put(getChaosLivingEntityType("robo_pounder"), Robot2.createAttributes());
    event.put(getChaosLivingEntityType("robo_gunner"), Robot3.createAttributes());
    event.put(getChaosLivingEntityType("robo_warrior"), Robot4.createAttributes());
    event.put(getChaosLivingEntityType("robo_sniper"), Robot5.createAttributes());
    event.put(getChaosLivingEntityType("alosaurus"), Alosaurus.createAttributes());
    event.put(getChaosLivingEntityType("cryolophosaurus"), Cryolophosaurus.createAttributes());
    event.put(getChaosLivingEntityType("basilisk"), Basilisk.createAttributes());
    event.put(getChaosLivingEntityType("camarasaurus"), Camarasaurus.createAttributes());
    event.put(getChaosLivingEntityType("hydrolisc"), Hydrolisc.createAttributes());
    event.put(getChaosLivingEntityType("velocity_raptor"), VelocityRaptor.createAttributes());
    event.put(getChaosLivingEntityType("dragonfly"), Dragonfly.createAttributes());
    event.put(getChaosLivingEntityType("emperor_scorpion"), EmperorScorpion.createAttributes());
    event.put(getChaosLivingEntityType("scorpion"), Scorpion.createAttributes());
    event.put(getChaosLivingEntityType("cave_fisher"), CaveFisher.createAttributes());
    event.put(getChaosLivingEntityType("baby_dragon"), Spyro.createAttributes());
    event.put(getChaosLivingEntityType("baryonyx"), Baryonyx.createAttributes());
    event.put(getChaosLivingEntityType("gamma_metroid"), GammaMetroid.createAttributes());
    event.put(getChaosLivingEntityType("wtf"), GammaMetroid.createAttributes());
    event.put(getChaosLivingEntityType("bird"), Cockateil.createAttributes());
    event.put(getChaosLivingEntityType("kyuubi"), Kyuubi.createAttributes());
    event.put(getChaosLivingEntityType("water_dragon"), WaterDragon.createAttributes());
    event.put(getChaosLivingEntityType("attack_squid"), AttackSquid.createAttributes());
    event.put(getChaosLivingEntityType("alien"), Alien.createAttributes());
    event.put(getChaosLivingEntityType("hoverboard"), Elevator.createAttributes());
    event.put(getChaosLivingEntityType("the_kraken"), Kraken.createAttributes());
    event.put(getChaosLivingEntityType("lizard"), Lizard.createAttributes());
    event.put(getChaosLivingEntityType("cephadrome"), Cephadrome.createAttributes());
    event.put(getChaosLivingEntityType("dragon"), Dragon.createAttributes());
    event.put(getChaosLivingEntityType("chipmunk"), Chipmunk.createAttributes());
    event.put(getChaosLivingEntityType("gazelle"), Gazelle.createAttributes());
    event.put(getChaosLivingEntityType("ostrich"), Ostrich.createAttributes());
    event.put(getChaosLivingEntityType("jumpy_bug"), TrooperBug.createAttributes());
    event.put(getChaosLivingEntityType("spit_bug"), SpitBug.createAttributes());
    event.put(getChaosLivingEntityType("stink_bug"), StinkBug.createAttributes());
    event.put(getChaosLivingEntityType("tshirt"), Tshirt.createAttributes());
    event.put(getChaosLivingEntityType("creeping_horror"), CreepingHorror.createAttributes());
    event.put(getChaosLivingEntityType("terrible_terror"), TerribleTerror.createAttributes());
    event.put(getChaosLivingEntityType("cliff_racer"), CliffRacer.createAttributes());
    event.put(getChaosLivingEntityType("triffid"), Triffid.createAttributes());
    event.put(getChaosLivingEntityType("nightmare"), PitchBlack.createAttributes());
    event.put(getChaosLivingEntityType("lurking_terror"), LurkingTerror.createAttributes());
    event.put(getChaosLivingEntityType("mobzilla"), Godzilla.createAttributes());
    event.put(getChaosLivingEntityType("ghost"), Ghost.createAttributes());
    event.put(getChaosLivingEntityType("ghost_pumpkin_skelly"), GhostSkelly.createAttributes());
    event.put(getChaosLivingEntityType("small_worm"), WormSmall.createAttributes());
    event.put(getChaosLivingEntityType("medium_worm"), WormMedium.createAttributes());
    event.put(getChaosLivingEntityType("large_worm"), WormLarge.createAttributes());
    event.put(getChaosLivingEntityType("cassowary"), Cassowary.createAttributes());
    event.put(getChaosLivingEntityType("cloud_shark"), CloudShark.createAttributes());
    event.put(getChaosLivingEntityType("gold_fish"), GoldFish.createAttributes());
    event.put(getChaosLivingEntityType("leaf_monster"), LeafMonster.createAttributes());
    event.put(getChaosLivingEntityType("mobzilla_head"), GodzillaHead.createAttributes());
    event.put(getChaosLivingEntityType("ender_knight"), EnderKnight.createAttributes());
    event.put(getChaosLivingEntityType("ender_reaper"), EnderReaper.createAttributes());
    event.put(getChaosLivingEntityType("beaver"), Beaver.createAttributes());
    event.put(getChaosLivingEntityType("termite"), Termite.createAttributes());
    event.put(getChaosLivingEntityType("fairy"), Fairy.createAttributes());
    event.put(getChaosLivingEntityType("peacock"), Peacock.createAttributes());
    event.put(getChaosLivingEntityType("rotator"), Rotator.createAttributes());
    event.put(getChaosLivingEntityType("vortex"), Vortex.createAttributes());
    event.put(getChaosLivingEntityType("dungeon_beast"), DungeonBeast.createAttributes());
    event.put(getChaosLivingEntityType("rat"), Rat.createAttributes());
    event.put(getChaosLivingEntityType("flounder"), Flounder.createAttributes());
    event.put(getChaosLivingEntityType("whale"), Whale.createAttributes());
    event.put(getChaosLivingEntityType("irukandji"), Irukandji.createAttributes());
    event.put(getChaosLivingEntityType("skate"), Skate.createAttributes());
    event.put(getChaosLivingEntityType("crystal_urchin"), Urchin.createAttributes());
    event.put(getChaosLivingEntityType("mantis"), Mantis.createAttributes());
    event.put(getChaosLivingEntityType("hercules_beetle"), HerculesBeetle.createAttributes());
    event.put(getChaosLivingEntityType("trex"), TRex.createAttributes());
    event.put(getChaosLivingEntityType("t._rex"), TRex.createAttributes());
    event.put(getChaosLivingEntityType("stinky"), Stinky.createAttributes());
    event.put(getChaosLivingEntityType("coin"), Coin.createAttributes());
    event.put(getChaosLivingEntityType("the_king"), TheKing.createAttributes());
    event.put(getChaosLivingEntityType("king_head"), KingHead.createAttributes());
    event.put(getChaosLivingEntityType("the_queen"), TheQueen.createAttributes());
    event.put(getChaosLivingEntityType("queen_head"), QueenHead.createAttributes());
    event.put(getChaosLivingEntityType("boyfriend"), Boyfriend.createAttributes());
    event.put(getChaosLivingEntityType("the_prince"), ThePrince.createAttributes());
    event.put(getChaosLivingEntityType("molenoid"), Molenoid.createAttributes());
    event.put(getChaosLivingEntityType("sea_monster"), SeaMonster.createAttributes());
    event.put(getChaosLivingEntityType("sea_viper"), SeaViper.createAttributes());
    event.put(getChaosLivingEntityType("easter_bunny"), EasterBunny.createAttributes());
    event.put(getChaosLivingEntityType("caterkiller"), CaterKiller.createAttributes());
    event.put(getChaosLivingEntityType("leonopteryx"), Leon.createAttributes());
    event.put(getChaosLivingEntityType("hammerhead"), Hammerhead.createAttributes());
    event.put(getChaosLivingEntityType("rubber_ducky"), RubberDucky.createAttributes());
    event.put(getChaosLivingEntityType("the_young_prince"), ThePrinceTeen.createAttributes());
    event.put(getChaosLivingEntityType("criminal"), BandP.createAttributes());
    event.put(getChaosLivingEntityType("rock"), RockBase.createAttributes());
    event.put(getChaosLivingEntityType("brutalfly"), Brutalfly.createAttributes());
    event.put(getChaosLivingEntityType("nastysaurus"), Nastysaurus.createAttributes());
    event.put(getChaosLivingEntityType("pointysaurus"), Pointysaurus.createAttributes());
    event.put(getChaosLivingEntityType("cricket"), Cricket.createAttributes());
    event.put(getChaosLivingEntityType("the_princess"), ThePrincess.createAttributes());
    event.put(getChaosLivingEntityType("frog"), Frog.createAttributes());
    event.put(getChaosLivingEntityType("the_young_adult_prince"), ThePrinceAdult.createAttributes());
    event.put(getChaosLivingEntityType("robot_spider"), SpiderRobot.createAttributes());
    event.put(getChaosLivingEntityType("jeffery"), GiantRobot.createAttributes());
    event.put(getChaosLivingEntityType("robot_red_ant"), AntRobot.createAttributes());
    event.put(getChaosLivingEntityType("crab"), Crab.createAttributes());
    event.put(getChaosLivingEntityType("apple_cow"), net.minecraft.entity.passive.CowEntity.createAttributes().build());
    event.put(getChaosLivingEntityType("golden_apple_cow"), net.minecraft.entity.passive.CowEntity.createAttributes().build());
    event.put(getChaosLivingEntityType("enchanted_golden_apple_cow"), net.minecraft.entity.passive.CowEntity.createAttributes().build());
    event.put(getChaosLivingEntityType("crystal_apple_cow"), net.minecraft.entity.passive.CowEntity.createAttributes().build());
    event.put(getChaosLivingEntityType("island"), net.minecraft.entity.MobEntity.createMobAttributes().build());
    event.put(getChaosLivingEntityType("island_too"), net.minecraft.entity.MobEntity.createMobAttributes().build());
  }

  private void onRegisterBiomes(net.minecraftforge.event.RegistryEvent.Register<Biome> event) {
    chaosPrepareContent();
    UTOPIA_BIOME = registerChaosBiome(event, new ResourceLocation("chaospersists", "utopia"), new BiomeGenUtopianPlains().build());
    VILLAGE_BIOME = registerChaosBiome(event, new ResourceLocation("chaospersists", "village_dimension"), new BiomeVillagePlains().build());
    DANGER_BIOME = registerChaosBiome(event, new ResourceLocation("chaospersists", "danger_dimension"), new BiomeDangerPlains().build());
    CRYSTAL_BIOME = registerChaosBiome(event, new ResourceLocation("chaospersists", "crystal_dimension"), new BiomeCrystalPlains().build());
    CHAOS_BIOME = registerChaosBiome(event, new ResourceLocation("chaospersists", "chaos_dimension"), new BiomeChaosPlains().build());
    MINING_BIOME = registerChaosBiome(event, new ResourceLocation("chaospersists", "mining_dimension"), new BiomeMiningDimension().build());
  }

  private static Biome registerChaosBiome(net.minecraftforge.event.RegistryEvent.Register<Biome> event, ResourceLocation id, Biome biome) {
    biome.setRegistryName(id);
    event.getRegistry().register(biome);
    return biome;
  }

  @SubscribeEvent
  public void onLootTableLoad(LootTableLoadEvent event) {
    if (event.getName().equals(new ResourceLocation("minecraft", "chests/simple_dungeon"))) {
      LootPool pool = event.getTable().getPool("main");
      if (pool != null) {
        addLegacyLootEntry(pool, MyRuby, 3, 1, 1, "chaospersists:ruby");
        addLegacyLootEntry(pool, MyAmethyst, 3, 1, 1, "chaospersists:amethyst");
        addLegacyLootEntry(pool, MyThunderStaff, 2, 1, 1, "chaospersists:thunderstaff");
      }
    } else if (event.getName().equals(new ResourceLocation("minecraft", "chests/jungle_temple"))) {
      LootPool pool = event.getTable().getPool("main");
      if (pool != null) {
        addLegacyLootEntry(pool, MyRuby, 3, 1, 1, "chaospersists:ruby");
        addLegacyLootEntry(pool, MyAmethyst, 3, 1, 1, "chaospersists:amethyst");
        addLegacyLootEntry(pool, AntRobotKit, 3, 1, 1, "chaospersists:antrobotkit");
      }
    } else if (event.getName().equals(new ResourceLocation("minecraft", "chests/desert_pyramid"))) {
      LootPool pool = event.getTable().getPool("main");
      if (pool != null) {
        addLegacyLootEntry(pool, MyRuby, 2, 1, 1, "chaospersists:ruby");
        addLegacyLootEntry(pool, MyAmethyst, 2, 1, 1, "chaospersists:amethyst");
        addLegacyLootEntry(pool, SpiderRobotKit, 2, 1, 1, "chaospersists:spiderrobotkit");
      }
    }
  }

  private static void addLegacyLootEntry(LootPool pool, Item item, int weight, int minCount, int maxCount, String entryName) {
    if (pool == null || item == null) {
      return;
    }
    @SuppressWarnings("unchecked")
    java.util.List<net.minecraft.loot.LootEntry> entries = ObfuscationReflectionHelper.getPrivateValue(LootPool.class, pool, "entries");
    if (entries != null) {
      entries.add(ItemLootEntry.lootTableItem(item).setWeight(weight).apply(SetCount.setCount(net.minecraft.loot.RandomValueRange.between((float) minCount, (float) maxCount))).build());
    }
  }

  @SubscribeEvent
  public void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
    if (event.getItemStack().isEmpty()) return;
    if (event.getItemStack().getItem() == Item.byBlock(CrystalCoal)) {
      event.setBurnTime(20000);
    }
  }

  /** Tamed Girlfriends and Boyfriends assist in combat like wolves when their owner damages a mob. */
  @SubscribeEvent
  public void onLivingHurtOwnerAssistGirlfriends(LivingHurtEvent event) {
    if (event == null || event.getEntityLiving() == null) {
      return;
    }
    LivingEntity victim = event.getEntityLiving();
    net.minecraft.util.DamageSource src = event.getSource();
    if (src == null) {
      return;
    }
    Entity attacker = src.getEntity();
    if (!(attacker instanceof PlayerEntity)) {
      return;
    }
    PlayerEntity player = (PlayerEntity) attacker;
    if (victim == player) {
      return;
    }
    if (victim.level == null || victim.level.isClientSide) {
      return;
    }
    if (victim instanceof TameableEntity) {
      TameableEntity te = (TameableEntity) victim;
      if (te.isTame() && player.getUUID().equals(te.getOwnerUUID())) {
        return;
      }
    }
    for (Girlfriend g : victim.level.getEntitiesOfClass(Girlfriend.class, player.getBoundingBox().inflate(16.0D))) {
      if (!g.isTame() || g.isOrderedToSit() || !g.isOwnedBy(player)) {
        continue;
      }
      g.setTarget(victim);
    }
    for (Boyfriend b : victim.level.getEntitiesOfClass(Boyfriend.class, player.getBoundingBox().inflate(16.0D))) {
      if (!b.isTame() || b.isOrderedToSit() || !b.isOwnedBy(player)) {
        continue;
      }
      b.setTarget(victim);
    }
  }

  /**
   * 1.7.10 parity: custom mobs used legacy armor reduction expectations.
   * In 1.12.2, high-damage hits penetrate armor more aggressively, making
   * high-defense mobs (e.g. Emperor Scorpion) take too much damage.
   *
   * This adjusts pre-armor incoming damage for ChaosPersists mobs so that
   * post-armor damage tracks the legacy model: damage * (1 - armor/25).
   */
  @SubscribeEvent
  public void onLivingHurtLegacyArmorParity(LivingHurtEvent event) {
    if (event == null || event.getEntityLiving() == null) {
      return;
    }
    LivingEntity living = event.getEntityLiving();
    if (living.level == null || living.level.isClientSide) {
      return;
    }
    ResourceLocation id = ForgeRegistries.ENTITIES.getKey(living.getType());
    if (id == null || !"chaospersists".equals(id.getNamespace())) {
      return;
    }
    DamageSource source = event.getSource();
    if (source == null || source.isBypassArmor()) {
      return;
    }
    float incoming = event.getAmount();
    if (incoming <= 0.0f) {
      return;
    }

    int armor = Math.max(0, Math.min(20, living.getArmorValue()));
    if (armor <= 0) {
      return;
    }

    // 1.7.10-style final damage expectation.
    float legacyFinal = incoming * (25.0f - (float)armor) / 25.0f;

    float toughness = 0.0f;
    ModifiableAttributeInstance toughAttr = living.getAttribute(Attributes.ARMOR_TOUGHNESS);
    if (toughAttr != null) {
      toughness = (float)toughAttr.getValue();
    }

    float vanillaFinalAtIncoming = net.minecraft.util.CombatRules.getDamageAfterAbsorb(incoming, (float)armor, toughness);
    if (vanillaFinalAtIncoming <= legacyFinal + 1.0e-4f) {
      return;
    }

    // Invert 1.12 armor curve by binary search for pre-armor amount.
    float low = 0.0f;
    float high = incoming;
    float cappedHigh = incoming * 8.0f + 40.0f;
    while (net.minecraft.util.CombatRules.getDamageAfterAbsorb(high, (float)armor, toughness) < legacyFinal && high < cappedHigh) {
      high *= 2.0f;
    }
    if (high > cappedHigh) {
      high = cappedHigh;
    }
    for (int i = 0; i < 14; ++i) {
      float mid = (low + high) * 0.5f;
      float out = net.minecraft.util.CombatRules.getDamageAfterAbsorb(mid, (float)armor, toughness);
      if (out < legacyFinal) {
        low = mid;
      } else {
        high = mid;
      }
    }

    event.setAmount(high);
  }

  private ResourceLocation getSpawnerEntityId(MobSpawnerTileEntity spawner) {
    if (spawner == null) {
      return null;
    }
    return SpawnerFixHelper.getMobSpawnerEntityId(spawner.getSpawner());
  }

  private void normalizeSpawnerId(MobSpawnerTileEntity spawner) {
    if (spawner == null) {
      return;
    }
    ResourceLocation current = SpawnerFixHelper.getMobSpawnerEntityId(spawner.getSpawner());
    ResourceLocation normalized = SpawnerFixHelper.normalizeSpawnerEntityId(current);
    if (normalized != null && (current == null || !normalized.equals(current))) {
      SpawnerFixHelper.setSpawnerEntityId(spawner.getSpawner(), normalized);
      spawner.setChanged();
    }
  }

  private boolean isEntityTypeInBiomeSpawnListsForDebug(Biome biome, EntityType<?> entityType) {
    return this.isEntityTypeInBiomeSpawnListForDebug(biome.getMobSettings().getMobs(EntityClassification.MONSTER), entityType)
            || this.isEntityTypeInBiomeSpawnListForDebug(biome.getMobSettings().getMobs(EntityClassification.CREATURE), entityType)
            || this.isEntityTypeInBiomeSpawnListForDebug(biome.getMobSettings().getMobs(EntityClassification.AMBIENT), entityType)
            || this.isEntityTypeInBiomeSpawnListForDebug(biome.getMobSettings().getMobs(EntityClassification.WATER_CREATURE), entityType);
  }

  private boolean isEntityTypeInBiomeSpawnListForDebug(List<MobSpawnInfo.Spawners> entries, EntityType<?> entityType) {
    if (entries == null || entries.isEmpty() || entityType == null) {
      return false;
    }
    for (MobSpawnInfo.Spawners entry : entries) {
      if (entry != null && entry.type == entityType) {
        return true;
      }
    }
    return false;
  }

  @SubscribeEvent
  public void onLivingSpawnCheckDebug(LivingSpawnEvent.CheckSpawn event) {
    // Debug logging removed — it fired on every spawn check and tanked TPS/log size.
  }

  @SubscribeEvent
  public void onChunkLoadNormalizeSpawners(ChunkEvent.Load event) {
    if (event == null || !(event.getWorld() instanceof ServerWorld) || event.getChunk() == null) {
      return;
    }
    ServerWorld chunkWorld = (ServerWorld) event.getWorld();
    ChunkPos pos = event.getChunk().getPos();
    ChaosWorld.queueNormalizeSpawnersInChunk(chunkWorld, pos);
  }

  void normalizeSpawnersInChunk(ServerWorld chunkWorld, ChunkPos pos) {
    if (!chunkWorld.hasChunk(pos.x, pos.z)) {
      return;
    }
    Chunk chunk = chunkWorld.getChunk(pos.x, pos.z);
    java.util.Map<BlockPos, TileEntity> map = ObfuscationReflectionHelper.getPrivateValue(
            Chunk.class, chunk, "blockEntities");
    if (map == null || map.isEmpty()) {
      return;
    }
    for (TileEntity te : map.values()) {
      if (te instanceof MobSpawnerTileEntity) {
        this.normalizeSpawnerId((MobSpawnerTileEntity) te);
      }
    }
  }

  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (event == null || !(event.getWorld() instanceof World)) {
      return;
    }
    World joinWorld = (World) event.getWorld();
    if (joinWorld.isClientSide) {
      return;
    }
    if (!(event.getEntity() instanceof PitchBlack)) {
      return;
    }

    PitchBlack nightmare = (PitchBlack) event.getEntity();
    BlockPos base = new BlockPos(nightmare.getX(), nightmare.getY(), nightmare.getZ());

    for (int dx = -8; dx <= 8; ++dx) {
      for (int dy = -4; dy <= 8; ++dy) {
        for (int dz = -8; dz <= 8; ++dz) {
          TileEntity te = joinWorld.getBlockEntity(base.offset(dx, dy, dz));
          if (!(te instanceof MobSpawnerTileEntity)) {
            continue;
          }

          String path = null;
          ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(((MobSpawnerTileEntity) te).getSpawner());
          if (id != null) {
            path = SpawnerFixHelper.normalizeSpawnerEntityId(id).getPath();
          }

          if (path != null && "nightmare".equalsIgnoreCase(path)) {
            nightmare.setSpawnedFromSpawner();
            return;
          }
        }
      }
    }
  }

  @SubscribeEvent
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (event == null || event.getEntityLiving() == null) {
      return;
    }
    LivingEntity base = event.getEntityLiving();
    if (base.level == null || base.level.isClientSide) {
      return;
    }
    if (!(base instanceof MobEntity)) {
      return;
    }
    ResourceLocation key = ForgeRegistries.ENTITIES.getKey(base.getType());
    boolean chaosHostileMob = key != null
            && "chaospersists".equals(key.getNamespace())
            && base instanceof IMob;
    if (!this.isAlwaysHostileInLegacy(base.getClass()) && !chaosHostileMob) {
      return;
    }
    if (PlayNicely != 0 || base.level.getDifficulty() == Difficulty.PEACEFUL) {
      return;
    }

    MobEntity mob = (MobEntity) base;
    if (mob.tickCount % 5 != 0) {
      return;
    }
    LivingEntity current = mob.getTarget();
    if (current != null && current.isAlive()) {
      return;
    }

    PlayerEntity target = base.level.getNearestPlayer(base.getX(), base.getY(), base.getZ(), 24.0, false);
    if (target == null || target.isCreative() || target.isSpectator()) {
      return;
    }

    mob.setTarget(target);
    mob.setLastHurtByMob(target);
    if (mob instanceof Bee) {
      ((Bee) mob).forceAttackTarget(target);
    }
  }

  private boolean isAlwaysHostileInLegacy(Class<?> c) {
    return c == PitchBlack.class
            || c == CreepingHorror.class
            || c == TerribleTerror.class
            || c == LurkingTerror.class
            || c == Basilisk.class
            || c == Scorpion.class
            || c == CaveFisher.class
            || c == Bee.class
            || c == Mantis.class
            || c == Rat.class
            || c == EnderKnight.class
            || c == EnderReaper.class;
  }

  public void initializeCagesAndEggs()
  {
    CageEmpty = new CritterCage(0, 160).setRegistryName(new ResourceLocation("chaospersists", "cageempty"));
    CagedSpider = new CritterCage(0, 161).setRegistryName(new ResourceLocation("chaospersists", "cagespider"));
    CagedBat = new CritterCage(0, 162).setRegistryName(new ResourceLocation("chaospersists", "cagebat"));
    CagedCow = new CritterCage(0, 163).setRegistryName(new ResourceLocation("chaospersists", "cagecow"));
    CagedPig = new CritterCage(0, 164).setRegistryName(new ResourceLocation("chaospersists", "cagepig"));
    CagedSquid = new CritterCage(0, 165).setRegistryName(new ResourceLocation("chaospersists", "cagesquid"));
    CagedChicken = new CritterCage(0, 166).setRegistryName(new ResourceLocation("chaospersists", "cagechicken"));
    CagedCreeper = new CritterCage(0, 167).setRegistryName(new ResourceLocation("chaospersists", "cagecreeper"));
    CagedSkeleton = new CritterCage(0, 168).setRegistryName(new ResourceLocation("chaospersists", "cageskeleton"));
    CagedZombie = new CritterCage(0, 169).setRegistryName(new ResourceLocation("chaospersists", "cagezombie"));
    CagedSlime = new CritterCage(0, 170).setRegistryName(new ResourceLocation("chaospersists", "cageslime"));
    CagedGhast = new CritterCage(0, 171).setRegistryName(new ResourceLocation("chaospersists", "cageghast"));
    CagedZombiePigman = new CritterCage(0, 172).setRegistryName(new ResourceLocation("chaospersists", "cagezombiepigman"));
    CagedEnderman = new CritterCage(0, 173).setRegistryName(new ResourceLocation("chaospersists", "cageenderman"));
    CagedCaveSpider = new CritterCage(0, 174).setRegistryName(new ResourceLocation("chaospersists", "cagecavespider"));
    CagedSilverfish = new CritterCage(0, 175).setRegistryName(new ResourceLocation("chaospersists", "cagesilverfish"));
    CagedMagmaCube = new CritterCage(0, 176).setRegistryName(new ResourceLocation("chaospersists", "cagemagmacube"));
    CagedWitch = new CritterCage(0, 177).setRegistryName(new ResourceLocation("chaospersists", "cagewitch"));
    CagedSheep = new CritterCage(0, 178).setRegistryName(new ResourceLocation("chaospersists", "cagesheep"));
    CagedWolf = new CritterCage(0, 179).setRegistryName(new ResourceLocation("chaospersists", "cagewolf"));
    CagedMooshroom = new CritterCage(0, 180).setRegistryName(new ResourceLocation("chaospersists", "cagemooshroom"));
    CagedOcelot = new CritterCage(0, 181).setRegistryName(new ResourceLocation("chaospersists", "cageocelot"));
    CagedBlaze = new CritterCage(0, 182).setRegistryName(new ResourceLocation("chaospersists", "cageblaze"));
    CagedGirlfriend = new CritterCage(0, 183).setRegistryName(new ResourceLocation("chaospersists", "cagegirlfriend"));
    CagedBoyfriend = new CritterCage(0, 215).setRegistryName(new ResourceLocation("chaospersists", "cageboyfriend"));
    CagedWitherSkeleton = new CritterCage(0, 188).setRegistryName(new ResourceLocation("chaospersists", "cagewitherskeleton"));
    CagedEnderDragon = new CritterCage(0, 184).setRegistryName(new ResourceLocation("chaospersists", "cageenderdragon"));
    CagedSnowGolem = new CritterCage(0, 185).setRegistryName(new ResourceLocation("chaospersists", "cagesnowgolem"));
    CagedIronGolem = new CritterCage(0, 186).setRegistryName(new ResourceLocation("chaospersists", "cageirongolem"));
    CagedWitherBoss = new CritterCage(0, 187).setRegistryName(new ResourceLocation("chaospersists", "cagewitherboss"));
    CagedRedCow = new CritterCage(0, 189).setRegistryName(new ResourceLocation("chaospersists", "cageredcow"));
    CagedGoldCow = new CritterCage(0, 190).setRegistryName(new ResourceLocation("chaospersists", "cagegoldcow"));
    CagedEnchantedCow = new CritterCage(0, 191).setRegistryName(new ResourceLocation("chaospersists", "cageenchantedcow"));
    CagedMOTHRA = new CritterCage(0, 208).setRegistryName(new ResourceLocation("chaospersists", "cagemothra"));
    CagedAlo = new CritterCage(0, 209).setRegistryName(new ResourceLocation("chaospersists", "cagealosaurus"));
    CagedCryo = new CritterCage(0, 210).setRegistryName(new ResourceLocation("chaospersists", "cagecryolophosaurus"));
    CagedCama = new CritterCage(0, 211).setRegistryName(new ResourceLocation("chaospersists", "cagecamarasaurus"));
    CagedVelo = new CritterCage(0, 212).setRegistryName(new ResourceLocation("chaospersists", "cagevelocityraptor"));
    CagedHydro = new CritterCage(0, 213).setRegistryName(new ResourceLocation("chaospersists", "cagehydrolisc"));
    CagedBasil = new CritterCage(0, 214).setRegistryName(new ResourceLocation("chaospersists", "cagebasilisc"));
    CagedDragonfly = new CritterCage(0, 220).setRegistryName(new ResourceLocation("chaospersists", "cagedragonfly"));
    CagedEmperorScorpion = new CritterCage(0, 222).setRegistryName(new ResourceLocation("chaospersists", "cageemperorscorpion"));
    CagedScorpion = new CritterCage(0, 224).setRegistryName(new ResourceLocation("chaospersists", "cagescorpion"));
    CagedCaveFisher = new CritterCage(0, 226).setRegistryName(new ResourceLocation("chaospersists", "cagecavefisher"));
    CagedSpyro = new CritterCage(0, 228).setRegistryName(new ResourceLocation("chaospersists", "cagespyro"));
    CagedBaryonyx = new CritterCage(0, 230).setRegistryName(new ResourceLocation("chaospersists", "cagebaryonyx"));
    CagedGammaMetroid = new CritterCage(0, 232).setRegistryName(new ResourceLocation("chaospersists", "cagegammametroid"));
    CagedCockateil = new CritterCage(0, 234).setRegistryName(new ResourceLocation("chaospersists", "cagecockateil"));
    CagedKyuubi = new CritterCage(0, 236).setRegistryName(new ResourceLocation("chaospersists", "cagekyuubi"));
    CagedAlien = new CritterCage(0, 238).setRegistryName(new ResourceLocation("chaospersists", "cagealien"));
    CagedAttackSquid = new CritterCage(0, 240).setRegistryName(new ResourceLocation("chaospersists", "cageattacksquid"));
    CagedWaterDragon = new CritterCage(0, 242).setRegistryName(new ResourceLocation("chaospersists", "cagewaterdragon"));
    CagedCephadrome = new CritterCage(0, 248).setRegistryName(new ResourceLocation("chaospersists", "cagecephadrome"));
    CagedKraken = new CritterCage(0, 244).setRegistryName(new ResourceLocation("chaospersists", "cagekraken"));
    CagedLizard = new CritterCage(0, 246).setRegistryName(new ResourceLocation("chaospersists", "cagelizard"));
    CagedDragon = new CritterCage(0, 250).setRegistryName(new ResourceLocation("chaospersists", "cagedragon"));
    CagedBee = new CritterCage(0, 252).setRegistryName(new ResourceLocation("chaospersists", "cagebee"));
    CagedHorse = new CritterCage(0, 253).setRegistryName(new ResourceLocation("chaospersists", "cagehorse"));
    CagedFirefly = new CritterCage(0, 255).setRegistryName(new ResourceLocation("chaospersists", "cagefirefly"));
    CagedChipmunk = new CritterCage(0, 256).setRegistryName(new ResourceLocation("chaospersists", "cagechipmunk"));
    CagedGazelle = new CritterCage(0, 257).setRegistryName(new ResourceLocation("chaospersists", "cagegazelle"));
    CagedOstrich = new CritterCage(0, 258).setRegistryName(new ResourceLocation("chaospersists", "cageostrich"));
    CagedTrooper = new CritterCage(0, 259).setRegistryName(new ResourceLocation("chaospersists", "cagetrooper"));
    CagedSpit = new CritterCage(0, 260).setRegistryName(new ResourceLocation("chaospersists", "cagespit"));
    CagedStink = new CritterCage(0, 261).setRegistryName(new ResourceLocation("chaospersists", "cagestink"));
    CagedCreepingHorror = new CritterCage(0, 268).setRegistryName(new ResourceLocation("chaospersists", "cagecreepinghorror"));
    CagedTerribleTerror = new CritterCage(0, 269).setRegistryName(new ResourceLocation("chaospersists", "cageterribleterror"));
    CagedCliffRacer = new CritterCage(0, 270).setRegistryName(new ResourceLocation("chaospersists", "cagecliffracer"));
    CagedTriffid = new CritterCage(0, 271).setRegistryName(new ResourceLocation("chaospersists", "cagetriffid"));
    CagedPitchBlack = new CritterCage(0, 272).setRegistryName(new ResourceLocation("chaospersists", "cagenightmare"));
    CagedLurkingTerror = new CritterCage(0, 273).setRegistryName(new ResourceLocation("chaospersists", "cagelurkingterror"));
    CagedSmallWorm = new CritterCage(0, 281).setRegistryName(new ResourceLocation("chaospersists", "cagesmallworm"));
    CagedMediumWorm = new CritterCage(0, 282).setRegistryName(new ResourceLocation("chaospersists", "cagemediumworm"));
    CagedLargeWorm = new CritterCage(0, 283).setRegistryName(new ResourceLocation("chaospersists", "cagelargeworm"));
    CagedCassowary = new CritterCage(0, 284).setRegistryName(new ResourceLocation("chaospersists", "cagecassowary"));
    CagedCloudShark = new CritterCage(0, 285).setRegistryName(new ResourceLocation("chaospersists", "cagecloudshark"));
    CagedGoldFish = new CritterCage(0, 286).setRegistryName(new ResourceLocation("chaospersists", "cagegoldfish"));
    CagedLeafMonster = new CritterCage(0, 287).setRegistryName(new ResourceLocation("chaospersists", "cageleafmonster"));
    CagedEnderKnight = new CritterCage(0, 296).setRegistryName(new ResourceLocation("chaospersists", "cageenderknight"));
    CagedEnderReaper = new CritterCage(0, 297).setRegistryName(new ResourceLocation("chaospersists", "cageenderreaper"));
    CagedBeaver = new CritterCage(0, 300).setRegistryName(new ResourceLocation("chaospersists", "cagebeaver"));
    CagedUrchin = new CritterCage(0, 323).setRegistryName(new ResourceLocation("chaospersists", "cageurchin"));
    CagedFlounder = new CritterCage(0, 319).setRegistryName(new ResourceLocation("chaospersists", "cageflounder"));
    CagedSkate = new CritterCage(0, 322).setRegistryName(new ResourceLocation("chaospersists", "cageskate"));
    CagedRotator = new CritterCage(0, 313).setRegistryName(new ResourceLocation("chaospersists", "cagerotator"));
    CagedPeacock = new CritterCage(0, 315).setRegistryName(new ResourceLocation("chaospersists", "cagepeacock"));
    CagedFairy = new CritterCage(0, 316).setRegistryName(new ResourceLocation("chaospersists", "cagefairy"));
    CagedDungeonBeast = new CritterCage(0, 317).setRegistryName(new ResourceLocation("chaospersists", "cagedungeonbeast"));
    CagedVortex = new CritterCage(0, 314).setRegistryName(new ResourceLocation("chaospersists", "cagevortex"));
    CagedRat = new CritterCage(0, 318).setRegistryName(new ResourceLocation("chaospersists", "cagerat"));
    CagedWhale = new CritterCage(0, 320).setRegistryName(new ResourceLocation("chaospersists", "cagewhale"));
    CagedIrukandji = new CritterCage(0, 321).setRegistryName(new ResourceLocation("chaospersists", "cageirukandji"));
    CagedTRex = new CritterCage(0, 345).setRegistryName(new ResourceLocation("chaospersists", "cagetrex"));
    CagedHercules = new CritterCage(0, 346).setRegistryName(new ResourceLocation("chaospersists", "cagehercules"));
    CagedMantis = new CritterCage(0, 347).setRegistryName(new ResourceLocation("chaospersists", "cagemantis"));
    CagedStinky = new CritterCage(0, 348).setRegistryName(new ResourceLocation("chaospersists", "cagestinky"));
    CagedEasterBunny = new CritterCage(0, 150).setRegistryName(new ResourceLocation("chaospersists", "cageeasterbunny"));
    CagedCaterKiller = new CritterCage(0, 151).setRegistryName(new ResourceLocation("chaospersists", "cagecaterkiller"));
    CagedMolenoid = new CritterCage(0, 152).setRegistryName(new ResourceLocation("chaospersists", "cagemolenoid"));
    CagedSeaMonster = new CritterCage(0, 153).setRegistryName(new ResourceLocation("chaospersists", "cageseamonster"));
    CagedSeaViper = new CritterCage(0, 154).setRegistryName(new ResourceLocation("chaospersists", "cageseaviper"));
    CagedLeon = new CritterCage(0, 357).setRegistryName(new ResourceLocation("chaospersists", "cageleon"));
    CagedHammerhead = new CritterCage(0, 359).setRegistryName(new ResourceLocation("chaospersists", "cagehammerhead"));
    CagedRubberDucky = new CritterCage(0, 361).setRegistryName(new ResourceLocation("chaospersists", "cagerubberducky"));
    CagedCrystalCow = new CritterCage(0, 216).setRegistryName(new ResourceLocation("chaospersists", "cagecrystalcow"));
    CagedVillager = new CritterCage(0, 217).setRegistryName(new ResourceLocation("chaospersists", "cagevillager"));
    CagedCriminal = new CritterCage(0, 218).setRegistryName(new ResourceLocation("chaospersists", "cagecriminal"));
    CagedBrutalfly = new CritterCage(0, 373).setRegistryName(new ResourceLocation("chaospersists", "cagebrutalfly"));
    CagedNastysaurus = new CritterCage(0, 374).setRegistryName(new ResourceLocation("chaospersists", "cagenastysaurus"));
    CagedPointysaurus = new CritterCage(0, 375).setRegistryName(new ResourceLocation("chaospersists", "cagepointysaurus"));
    CagedCricket = new CritterCage(0, 376).setRegistryName(new ResourceLocation("chaospersists", "cagecricket"));
    CagedFrog = new CritterCage(0, 377).setRegistryName(new ResourceLocation("chaospersists", "cagefrog"));
    CagedSpiderDriver = new CritterCage(0, 382).setRegistryName(new ResourceLocation("chaospersists", "cagespiderdriver"));
    CagedCrab = new CritterCage(0, 384).setRegistryName(new ResourceLocation("chaospersists", "cagecrab"));

    WitherSkeletonEgg = new ItemSpawnEgg(0,192).setRegistryName(new ResourceLocation("chaospersists", "eggwitherskeleton"));
    EnderDragonEgg = new ItemSpawnEgg(0,193).setRegistryName(new ResourceLocation("chaospersists", "eggenderdragon"));
    SnowGolemEgg = new ItemSpawnEgg(0,194).setRegistryName(new ResourceLocation("chaospersists", "eggsnowgolem"));
    IronGolemEgg = new ItemSpawnEgg(0,195).setRegistryName(new ResourceLocation("chaospersists", "eggirongolem"));
    WitherBossEgg = new ItemSpawnEgg(0,196).setRegistryName(new ResourceLocation("chaospersists", "eggwitherboss"));
    GirlfriendEgg = new ItemSpawnEgg(0,197).setRegistryName(new ResourceLocation("chaospersists", "egggirlfriend"));
    RedCowEgg = new ItemSpawnEgg(0,198).setRegistryName(new ResourceLocation("chaospersists", "eggredcow"));
    CrystalCowEgg = new ItemSpawnEgg(0,363).setRegistryName(new ResourceLocation("chaospersists", "eggcrystalcow"));
    GoldCowEgg = new ItemSpawnEgg(0,199).setRegistryName(new ResourceLocation("chaospersists", "egggoldcow"));
    EnchantedCowEgg = new ItemSpawnEgg(0,200).setRegistryName(new ResourceLocation("chaospersists", "eggenchantedcow"));
    MOTHRAEgg = new ItemSpawnEgg(0,201).setRegistryName(new ResourceLocation("chaospersists", "eggmothra"));
    AloEgg = new ItemSpawnEgg(0,202).setRegistryName(new ResourceLocation("chaospersists", "eggalosaurus"));
    CryoEgg = new ItemSpawnEgg(0,203).setRegistryName(new ResourceLocation("chaospersists", "eggcryolophosaurus"));
    CamaEgg = new ItemSpawnEgg(0,204).setRegistryName(new ResourceLocation("chaospersists", "eggcamarasaurus"));
    VeloEgg = new ItemSpawnEgg(0,205).setRegistryName(new ResourceLocation("chaospersists", "eggvelocityraptor"));
    HydroEgg = new ItemSpawnEgg(0,206).setRegistryName(new ResourceLocation("chaospersists", "egghydrolisc"));
    BasilEgg = new ItemSpawnEgg(0,207).setRegistryName(new ResourceLocation("chaospersists", "eggbasilisc"));
    DragonflyEgg = new ItemSpawnEgg(0,221).setRegistryName(new ResourceLocation("chaospersists", "eggdragonfly"));
    EmperorScorpionEgg = new ItemSpawnEgg(0,223).setRegistryName(new ResourceLocation("chaospersists", "eggemperorscorpion"));
    ScorpionEgg = new ItemSpawnEgg(0,225).setRegistryName(new ResourceLocation("chaospersists", "eggscorpion"));
    CaveFisherEgg = new ItemSpawnEgg(0,227).setRegistryName(new ResourceLocation("chaospersists", "eggcavefisher"));
    SpyroEgg = new ItemSpawnEgg(0,229).setRegistryName(new ResourceLocation("chaospersists", "eggspyro"));
    BaryonyxEgg = new ItemSpawnEgg(0,231).setRegistryName(new ResourceLocation("chaospersists", "eggbaryonyx"));
    GammaMetroidEgg = new ItemSpawnEgg(0,233).setRegistryName(new ResourceLocation("chaospersists", "egggammametroid"));
    CockateilEgg = new ItemSpawnEgg(0,235).setRegistryName(new ResourceLocation("chaospersists", "eggcockateil"));
    KyuubiEgg = new ItemSpawnEgg(0,237).setRegistryName(new ResourceLocation("chaospersists", "eggkyuubi"));
    AlienEgg = new ItemSpawnEgg(0,239).setRegistryName(new ResourceLocation("chaospersists", "eggalien"));
    AttackSquidEgg = new ItemSpawnEgg(0,241).setRegistryName(new ResourceLocation("chaospersists", "eggattacksquid"));
    WaterDragonEgg = new ItemSpawnEgg(0,243).setRegistryName(new ResourceLocation("chaospersists", "eggwaterdragon"));
    CephadromeEgg = new ItemSpawnEgg(0,249).setRegistryName(new ResourceLocation("chaospersists", "eggcephadrome"));
    KrakenEgg = new ItemSpawnEgg(0,245).setRegistryName(new ResourceLocation("chaospersists", "eggkraken"));
    LizardEgg = new ItemSpawnEgg(0,247).setRegistryName(new ResourceLocation("chaospersists", "egglizard"));
    DragonEgg = new ItemSpawnEgg(0,251).setRegistryName(new ResourceLocation("chaospersists", "eggdragon"));
    BeeEgg = new ItemSpawnEgg(0,254).setRegistryName(new ResourceLocation("chaospersists", "eggbee"));
    TrooperBugEgg = new ItemSpawnEgg(0,262).setRegistryName(new ResourceLocation("chaospersists", "eggtrooper"));
    SpitBugEgg = new ItemSpawnEgg(0,263).setRegistryName(new ResourceLocation("chaospersists", "eggspit"));
    StinkBugEgg = new ItemSpawnEgg(0,264).setRegistryName(new ResourceLocation("chaospersists", "eggstink"));
    OstrichEgg = new ItemSpawnEgg(0,265).setRegistryName(new ResourceLocation("chaospersists", "eggostrich"));
    GazelleEgg = new ItemSpawnEgg(0,266).setRegistryName(new ResourceLocation("chaospersists", "egggazelle"));
    ChipmunkEgg = new ItemSpawnEgg(0,267).setRegistryName(new ResourceLocation("chaospersists", "eggchipmunk"));
    CreepingHorrorEgg = new ItemSpawnEgg(0,274).setRegistryName(new ResourceLocation("chaospersists", "eggcreepinghorror"));
    TerribleTerrorEgg = new ItemSpawnEgg(0,275).setRegistryName(new ResourceLocation("chaospersists", "eggterribleterror"));
    CliffRacerEgg = new ItemSpawnEgg(0,276).setRegistryName(new ResourceLocation("chaospersists", "eggcliffracer"));
    TriffidEgg = new ItemSpawnEgg(0,277).setRegistryName(new ResourceLocation("chaospersists", "eggtriffid"));
    PitchBlackEgg = new ItemSpawnEgg(0,278).setRegistryName(new ResourceLocation("chaospersists", "eggnightmare"));
    LurkingTerrorEgg = new ItemSpawnEgg(0,279).setRegistryName(new ResourceLocation("chaospersists", "egglurkingterror"));
    GodzillaEgg = new ItemSpawnEgg(0,280).setRegistryName(new ResourceLocation("chaospersists", "egggodzilla"));
    SmallWormEgg = new ItemSpawnEgg(0,288).setRegistryName(new ResourceLocation("chaospersists", "eggsmallworm"));
    MediumWormEgg = new ItemSpawnEgg(0,289).setRegistryName(new ResourceLocation("chaospersists", "eggmediumworm"));
    LargeWormEgg = new ItemSpawnEgg(0,290).setRegistryName(new ResourceLocation("chaospersists", "egglargeworm"));
    CassowaryEgg = new ItemSpawnEgg(0,291).setRegistryName(new ResourceLocation("chaospersists", "eggcassowary"));
    CloudSharkEgg = new ItemSpawnEgg(0,292).setRegistryName(new ResourceLocation("chaospersists", "eggcloudshark"));
    GoldFishEgg = new ItemSpawnEgg(0,293).setRegistryName(new ResourceLocation("chaospersists", "egggoldfish"));
    LeafMonsterEgg = new ItemSpawnEgg(0,294).setRegistryName(new ResourceLocation("chaospersists", "eggleafmonster"));
    TshirtEgg = new ItemSpawnEgg(0,295).setRegistryName(new ResourceLocation("chaospersists", "eggtshirt"));
    EnderKnightEgg = new ItemSpawnEgg(0,298).setRegistryName(new ResourceLocation("chaospersists", "eggenderknight"));
    EnderReaperEgg = new ItemSpawnEgg(0,299).setRegistryName(new ResourceLocation("chaospersists", "eggenderreaper"));
    BeaverEgg = new ItemSpawnEgg(0,301).setRegistryName(new ResourceLocation("chaospersists", "eggbeaver"));
    RotatorEgg = new ItemSpawnEgg(0,302).setRegistryName(new ResourceLocation("chaospersists", "eggrotator"));
    VortexEgg = new ItemSpawnEgg(0,303).setRegistryName(new ResourceLocation("chaospersists", "eggvortex"));
    PeacockEgg = new ItemSpawnEgg(0,304).setRegistryName(new ResourceLocation("chaospersists", "eggpeacock"));
    FairyEgg = new ItemSpawnEgg(0,305).setRegistryName(new ResourceLocation("chaospersists", "eggfairy"));
    DungeonBeastEgg = new ItemSpawnEgg(0,306).setRegistryName(new ResourceLocation("chaospersists", "eggdungeonbeast"));
    RatEgg = new ItemSpawnEgg(0,307).setRegistryName(new ResourceLocation("chaospersists", "eggrat"));
    FlounderEgg = new ItemSpawnEgg(0,308).setRegistryName(new ResourceLocation("chaospersists", "eggflounder"));
    WhaleEgg = new ItemSpawnEgg(0,309).setRegistryName(new ResourceLocation("chaospersists", "eggwhale"));
    IrukandjiEgg = new ItemSpawnEgg(0,310).setRegistryName(new ResourceLocation("chaospersists", "eggirukandji"));
    SkateEgg = new ItemSpawnEgg(0,311).setRegistryName(new ResourceLocation("chaospersists", "eggskate"));
    UrchinEgg = new ItemSpawnEgg(0,312).setRegistryName(new ResourceLocation("chaospersists", "eggurchin"));
    Robot1Egg = new ItemSpawnEgg(0,324).setRegistryName(new ResourceLocation("chaospersists", "eggrobot1"));
    Robot2Egg = new ItemSpawnEgg(0,325).setRegistryName(new ResourceLocation("chaospersists", "eggrobot2"));
    Robot3Egg = new ItemSpawnEgg(0,326).setRegistryName(new ResourceLocation("chaospersists", "eggrobot3"));
    Robot4Egg = new ItemSpawnEgg(0,327).setRegistryName(new ResourceLocation("chaospersists", "eggrobot4"));
    GhostEgg = new ItemSpawnEgg(0,328).setRegistryName(new ResourceLocation("chaospersists", "eggghost"));
    GhostSkellyEgg = new ItemSpawnEgg(0,329).setRegistryName(new ResourceLocation("chaospersists", "eggghostskelly"));
    BrownAntEgg = new ItemSpawnEgg(0,330).setRegistryName(new ResourceLocation("chaospersists", "eggbrownant"));
    RedAntEgg = new ItemSpawnEgg(0,331).setRegistryName(new ResourceLocation("chaospersists", "eggredant"));
    RainbowAntEgg = new ItemSpawnEgg(0,332).setRegistryName(new ResourceLocation("chaospersists", "eggrainbowant"));
    UnstableAntEgg = new ItemSpawnEgg(0,333).setRegistryName(new ResourceLocation("chaospersists", "eggunstableant"));
    TermiteEgg = new ItemSpawnEgg(0,334).setRegistryName(new ResourceLocation("chaospersists", "eggtermite"));
    ButterflyEgg = new ItemSpawnEgg(0,335).setRegistryName(new ResourceLocation("chaospersists", "eggbutterfly"));
    MothEgg = new ItemSpawnEgg(0,336).setRegistryName(new ResourceLocation("chaospersists", "eggmoth"));
    MosquitoEgg = new ItemSpawnEgg(0,337).setRegistryName(new ResourceLocation("chaospersists", "eggmosquito"));
    FireflyEgg = new ItemSpawnEgg(0,338).setRegistryName(new ResourceLocation("chaospersists", "eggfirefly"));
    TRexEgg = new ItemSpawnEgg(0,339).setRegistryName(new ResourceLocation("chaospersists", "eggtrex"));
    HerculesEgg = new ItemSpawnEgg(0,340).setRegistryName(new ResourceLocation("chaospersists", "egghercules"));
    MantisEgg = new ItemSpawnEgg(0,341).setRegistryName(new ResourceLocation("chaospersists", "eggmantis"));
    StinkyEgg = new ItemSpawnEgg(0,342).setRegistryName(new ResourceLocation("chaospersists", "eggstinky"));
    Robot5Egg = new ItemSpawnEgg(0,343).setRegistryName(new ResourceLocation("chaospersists", "eggrobot5"));
    CoinEgg = new ItemSpawnEgg(0,344).setRegistryName(new ResourceLocation("chaospersists", "eggcoin"));
    BoyfriendEgg = new ItemSpawnEgg(0,349).setRegistryName(new ResourceLocation("chaospersists", "eggboyfriend"));
    TheKingEgg = new ItemSpawnEgg(0,350).setRegistryName(new ResourceLocation("chaospersists", "eggtheking"));
    TheQueenEgg = new ItemSpawnEgg(0,366).setRegistryName(new ResourceLocation("chaospersists", "eggthequeen"));
    ThePrinceEgg = new ItemSpawnEgg(0,351).setRegistryName(new ResourceLocation("chaospersists", "eggtheprince"));
    EasterBunnyEgg = new ItemSpawnEgg(0,352).setRegistryName(new ResourceLocation("chaospersists", "eggeasterbunny"));
    MolenoidEgg = new ItemSpawnEgg(0,353).setRegistryName(new ResourceLocation("chaospersists", "eggmolenoid"));
    SeaMonsterEgg = new ItemSpawnEgg(0,354).setRegistryName(new ResourceLocation("chaospersists", "eggseamonster"));
    SeaViperEgg = new ItemSpawnEgg(0,355).setRegistryName(new ResourceLocation("chaospersists", "eggseaviper"));
    CaterKillerEgg = new ItemSpawnEgg(0,356).setRegistryName(new ResourceLocation("chaospersists", "eggcaterkiller"));
    RubberDuckyEgg = new ItemSpawnEgg(0,362).setRegistryName(new ResourceLocation("chaospersists", "eggrubberducky"));
    HammerheadEgg = new ItemSpawnEgg(0,360).setRegistryName(new ResourceLocation("chaospersists", "egghammerhead"));
    LeonEgg = new ItemSpawnEgg(0,358).setRegistryName(new ResourceLocation("chaospersists", "eggleon"));
    CriminalEgg = new ItemSpawnEgg(0,365).setRegistryName(new ResourceLocation("chaospersists", "eggcriminal"));
    BrutalflyEgg = new ItemSpawnEgg(0,367).setRegistryName(new ResourceLocation("chaospersists", "eggbrutalfly"));
    NastysaurusEgg = new ItemSpawnEgg(0,368).setRegistryName(new ResourceLocation("chaospersists", "eggnastysaurus"));
    PointysaurusEgg = new ItemSpawnEgg(0,369).setRegistryName(new ResourceLocation("chaospersists", "eggpointysaurus"));
    CricketEgg = new ItemSpawnEgg(0,370).setRegistryName(new ResourceLocation("chaospersists", "eggcricket"));
    ThePrincessEgg = new ItemSpawnEgg(0,371).setRegistryName(new ResourceLocation("chaospersists", "eggtheprincess"));
    FrogEgg = new ItemSpawnEgg(0,372).setRegistryName(new ResourceLocation("chaospersists", "eggfrog"));
    JefferyEgg = new ItemSpawnEgg(0,378).setRegistryName(new ResourceLocation("chaospersists", "eggrobot6"));
    AntRobotEgg = new ItemSpawnEgg(0,379).setRegistryName(new ResourceLocation("chaospersists", "eggantrobot"));
    SpiderRobotEgg = new ItemSpawnEgg(0,380).setRegistryName(new ResourceLocation("chaospersists", "eggspiderrobot"));
    SpiderDriverEgg = new ItemSpawnEgg(0,381).setRegistryName(new ResourceLocation("chaospersists", "eggspiderdriver"));
    CrabEgg = new ItemSpawnEgg(0,383).setRegistryName(new ResourceLocation("chaospersists", "eggcrab"));
  }

  private void DoDispenserRegistrations()
  {
    DispenserBlock.registerBehavior(LizardEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(WitherSkeletonEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(EnderDragonEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SnowGolemEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(IronGolemEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(WitherBossEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GirlfriendEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BoyfriendEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TheKingEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TheQueenEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(ThePrinceEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(RedCowEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CrystalCowEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GoldCowEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(EnchantedCowEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(MOTHRAEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(AloEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CryoEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CamaEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(VeloEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(HydroEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BasilEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(DragonflyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(EmperorScorpionEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(ScorpionEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CaveFisherEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SpyroEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BaryonyxEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GammaMetroidEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CockateilEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(KyuubiEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(AlienEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(AttackSquidEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(WaterDragonEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CephadromeEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(DragonEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(KrakenEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(LizardEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BeeEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TrooperBugEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SpitBugEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(StinkBugEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(OstrichEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GazelleEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(ChipmunkEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CreepingHorrorEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TerribleTerrorEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CliffRacerEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TriffidEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(PitchBlackEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(LurkingTerrorEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GodzillaEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SmallWormEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(MediumWormEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(LargeWormEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CassowaryEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CloudSharkEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GoldFishEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(LeafMonsterEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TshirtEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(EnderKnightEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(EnderReaperEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BeaverEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(RotatorEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(VortexEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(PeacockEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(FairyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(DungeonBeastEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(RatEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(FlounderEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(WhaleEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(IrukandjiEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SkateEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(UrchinEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(Robot1Egg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(Robot2Egg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(Robot3Egg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(Robot4Egg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GhostEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(GhostSkellyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BrownAntEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(RedAntEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(RainbowAntEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(UnstableAntEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TermiteEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(ButterflyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(MothEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(MosquitoEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(FireflyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(TRexEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(HerculesEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(MantisEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(StinkyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(Robot5Egg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CoinEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(EasterBunnyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(MolenoidEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SeaMonsterEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SeaViperEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CaterKillerEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(LeonEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(HammerheadEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(RubberDuckyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CriminalEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(BrutalflyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(NastysaurusEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(PointysaurusEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CricketEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(ThePrincessEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(FrogEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(JefferyEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(AntRobotEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SpiderRobotEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(SpiderDriverEgg, new DispenserBehaviorChaosEgg());
    DispenserBlock.registerBehavior(CrabEgg, new DispenserBehaviorChaosEgg());

    DispenserBlock.registerBehavior(MyIrukandjiArrow, new MyDispenserBehaviorArrow());
    DispenserBlock.registerBehavior(MyWaterBall, new MyDispenserBehaviorWDCharge());
    DispenserBlock.registerBehavior(MySunspotUrchin, new MyDispenserBehaviorSunspotUrchin());
    DispenserBlock.registerBehavior(MyAcid, new MyDispenserBehaviorAcid());
    DispenserBlock.registerBehavior(MyIceBall, new MyDispenserBehaviorIceball());
    DispenserBlock.registerBehavior(MyIrukandji, new MyDispenserBehaviorDeadIrukandji());
    DispenserBlock.registerBehavior(MyLaserBall, new MyDispenserBehaviorLaserball());
    DispenserBlock.registerBehavior(MySmallRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyRedRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyCrystalRedRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyCrystalGreenRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyCrystalBlueRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyCrystalTNTRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyBlueRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyGreenRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyPurpleRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MySpikeyRock, new MyDispenserBehaviorRock());
    DispenserBlock.registerBehavior(MyTNTRock, new MyDispenserBehaviorRock());
  }

    private void clientSetup(FMLClientSetupEvent event)
  {
    event.enqueueWork(() -> {
      finishPostInit();
      if (proxy instanceof com.astryxion.chaospersists.proxy.ClientProxyChaos) {
        ((com.astryxion.chaospersists.proxy.ClientProxyChaos) proxy).registerRenderThings();
      }
    });
    applyChaosCreativeTabs();
    proxy.registerBlockColors();
    proxy.registerLeafColors();
    proxy.registerItemColors();
  }

  // ===== Creative Tabs Remap =====
  public static ItemGroup tabChaosItems;
  public static ItemGroup tabChaosBlocks;
  public static ItemGroup tabChaosFoods;
  public static ItemGroup tabChaosTools;
  public static ItemGroup tabChaosWeapons;
  public static ItemGroup tabChaosMobs;
  public static ItemGroup tabChaosArmor;

  private static BlockState blockStateFromMeta(Block block, int meta) {
    if (block == null) {
      return Blocks.AIR.defaultBlockState();
    }
    if (meta == 0) {
      return block.defaultBlockState();
    }
    if (block instanceof BlockDuctTape) {
      return ((BlockDuctTape) block).getStateFromMeta(meta);
    }
    if (block instanceof BlockPizza) {
      return ((BlockPizza) block).getStateFromMeta(meta);
    }
    return block.defaultBlockState();
  }

  private static void chaosSetItemCreativeTab(Item item, ItemGroup tab) {
    ObfuscationReflectionHelper.setPrivateValue(Item.class, item, tab, "category");
  }

  private static void chaosSetBlockCreativeTab(Block block, ItemGroup tab) {
    // 1.16.5: creative tab lives on Item.category; BlockItem instances get tab at registration in make_some_more_things.
  }

  private static void ensureChaosCreativeTabs()
  {
    if (tabChaosItems != null) {
      return;
    }

    tabChaosItems = new ItemGroup("chaos_items") {
      public ItemStack makeIcon() {
        return new ItemStack(MinersDream);
      }
    };
    tabChaosBlocks = new ItemGroup("chaos_blocks") {
      public ItemStack makeIcon() {
        return new ItemStack(MyAntBlock);
      }
    };
    tabChaosFoods = new ItemGroup("chaos_foods") {
      public ItemStack makeIcon() {
        return new ItemStack(MyCornCob);
      }
    };
    tabChaosTools = new ItemGroup("chaos_tools") {
      public ItemStack makeIcon() {
        return new ItemStack(MyUltimatePickaxe);
      }
    };
    tabChaosWeapons = new ItemGroup("chaos_weapons") {
      public ItemStack makeIcon() {
        return new ItemStack(MyUltimateSword);
      }
    };
    tabChaosMobs = new ItemGroup("chaos_mobs") {
      public ItemStack makeIcon() {
        return new ItemStack(TheKingEgg);
      }
    };
    tabChaosArmor = new ItemGroup("chaos_armor") {
      public ItemStack makeIcon() {
        return new ItemStack(RoyalBody);
      }
    };
  }

  /**
   * Growth-stage crop blocks (lettuce_0..3, quinoa_0..3, *plant*, experiencesapling, etc.)
   * should not appear in Chaos tabs - only seed items should.
   */
  private static boolean isHiddenCropGrowthChaosBlockPath(String pathLower)
  {
    if (pathLower == null || pathLower.isEmpty()) {
      return false;
    }
    return pathLower.contains("plant")
        || pathLower.contains("sapling")
        || pathLower.startsWith("lettuce_")
        || pathLower.startsWith("quinoa_");
  }

  /**
   * Remaps all mod items/blocks onto Chaos creative tabs. Safe to call more than once (idempotent).
   */
  public static void applyChaosCreativeTabs()
  {
    ensureChaosCreativeTabs();

    // Put all chaospersists blocks into Chaos Blocks.
    for (Block block : ForgeRegistries.BLOCKS) {
      if (block == null) {
        continue;
      }
      ResourceLocation rl = block.getRegistryName();
      if (rl != null && "chaospersists".equals(rl.getNamespace())) {
        String path = rl.getPath();
        String lower = path == null ? "" : path.toLowerCase();
        // Don't show crop/plant/sapling blocks in Chaos Blocks; keep only their seed items.
        if (isHiddenCropGrowthChaosBlockPath(lower)) {
          continue;
        }
        chaosSetBlockCreativeTab(block, tabChaosBlocks);
      }
    }

    // Put all chaospersists items into the requested tabs.
    for (Item item : ForgeRegistries.ITEMS) {
      if (item == null) {
        continue;
      }
      ResourceLocation rl = item.getRegistryName();
      if (rl == null || !"chaospersists".equals(rl.getNamespace())) {
        continue;
      }

      String path = rl.getPath();
      String lower = path == null ? "" : path.toLowerCase();

      if (item instanceof BlockItem) {
        // Hide plant/crop/sapling blocks from Chaos creative tabs.
        // Seeds remain because they are items, not block item forms.
        if (isHiddenCropGrowthChaosBlockPath(lower)) {
          chaosSetItemCreativeTab(item, null);
          continue;
        }

        chaosSetItemCreativeTab(item, tabChaosBlocks);
      } else if ("pizza".equals(lower)) {
        chaosSetItemCreativeTab(item, tabChaosFoods);
      } else if ("ducttape".equals(lower)) {
        chaosSetItemCreativeTab(item, tabChaosItems);
      } else if ("step_up".equals(lower) || "step_down".equals(lower) || "step_accross".equals(lower)) {
        chaosSetItemCreativeTab(item, tabChaosItems);
      } else if ("spiderrobotkit".equals(lower) || "antrobotkit".equals(lower)) {
        chaosSetItemCreativeTab(item, tabChaosItems);
      } else if (item instanceof ItemSpawnEgg) {
        chaosSetItemCreativeTab(item, tabChaosMobs);
      } else if (item.isEdible()) {
        chaosSetItemCreativeTab(item, tabChaosFoods);
      } else if (item instanceof ArmorItem) {
        chaosSetItemCreativeTab(item, tabChaosArmor);
      } else {
        // Map vanilla tabs and item classes to Chaos Tools / Weapons. Must be idempotent: JEI (and any
        // second caller) re-runs this after tabs are already tabChaosTools/tabChaosWeapons ÃƒÆ’Ã‚Â¢ÃƒÂ¢Ã¢â‚¬Å¡Ã‚Â¬ÃƒÂ¢Ã¢â€šÂ¬Ã‚Â comparing
        // only to ItemGroup.TOOLS/COMBAT would wrongly send everything to Chaos Items.
        ItemGroup oldTab = item.getItemCategory();
        ItemStack probe = new ItemStack(item);
        if (oldTab == tabChaosTools) {
          chaosSetItemCreativeTab(item, tabChaosTools);
        } else if (oldTab == tabChaosWeapons) {
          chaosSetItemCreativeTab(item, tabChaosWeapons);
        } else if (oldTab == ItemGroup.TAB_TOOLS) {
          chaosSetItemCreativeTab(item, tabChaosTools);
        } else if (oldTab == ItemGroup.TAB_COMBAT) {
          chaosSetItemCreativeTab(item, tabChaosWeapons);
        } else if (item instanceof SwordItem || item instanceof BowItem) {
          chaosSetItemCreativeTab(item, tabChaosWeapons);
        } else if (probe.getUseAnimation() == UseAction.BOW) {
          chaosSetItemCreativeTab(item, tabChaosWeapons);
        } else if (item instanceof HoeItem || item instanceof FishingRodItem) {
          chaosSetItemCreativeTab(item, tabChaosTools);
        } else {
          chaosSetItemCreativeTab(item, tabChaosItems);
        }
      }
    }
  }

  /** Chaos Items tab (for JEI sub-item enumeration when an item has {@code creativeTab == null}). */
  public static ItemGroup getChaosItemsCreativeTab()
  {
    ensureChaosCreativeTabs();
    return tabChaosItems;
  }

  /** Resolves which Chaos creative tab an item belongs on (same rules as {@link #applyChaosCreativeTabs}). */
  public static ItemGroup resolveChaosCreativeTab(Item item)
  {
    if (item == null) {
      return null;
    }
    ensureChaosCreativeTabs();
    ResourceLocation rl = item.getRegistryName();
    if (rl == null || !"chaospersists".equals(rl.getNamespace())) {
      return null;
    }
    String path = rl.getPath();
    String lower = path == null ? "" : path.toLowerCase();
    if (item instanceof BlockItem) {
      if (isHiddenCropGrowthChaosBlockPath(lower)) {
        return null;
      }
      return tabChaosBlocks;
    }
    if ("pizza".equals(lower)) {
      return tabChaosFoods;
    }
    if ("ducttape".equals(lower)) {
      return tabChaosItems;
    }
    if ("step_up".equals(lower) || "step_down".equals(lower) || "step_accross".equals(lower)) {
      return tabChaosItems;
    }
    if ("spiderrobotkit".equals(lower) || "antrobotkit".equals(lower)) {
      return tabChaosItems;
    }
    if (item instanceof ItemSpawnEgg) {
      return tabChaosMobs;
    }
    if (item.isEdible()) {
      return tabChaosFoods;
    }
    if (item instanceof ArmorItem) {
      return tabChaosArmor;
    }
    ItemGroup oldTab = item.getItemCategory();
    ItemStack probe = new ItemStack(item);
    if (oldTab == tabChaosTools) {
      return tabChaosTools;
    }
    if (oldTab == tabChaosWeapons) {
      return tabChaosWeapons;
    }
    if (oldTab == ItemGroup.TAB_TOOLS) {
      return tabChaosTools;
    }
    if (oldTab == ItemGroup.TAB_COMBAT) {
      return tabChaosWeapons;
    }
    if (item instanceof SwordItem || item instanceof BowItem) {
      return tabChaosWeapons;
    }
    if (probe.getUseAnimation() == UseAction.BOW) {
      return tabChaosWeapons;
    }
    if (item instanceof HoeItem || item instanceof FishingRodItem) {
      return tabChaosTools;
    }
    return tabChaosItems;
  }

    private void finishPostInit()
  {
    BMaze = new BasiliskMaze();
    RubyDungeon = new RubyBirdDungeon();
    MyDungeon = new GenericDungeon();
    chaospersistsTrees = new Trees();
    chaospersistsUtils = new MyUtils();
    Chunker = new ChunkOreGenerator();
  }

  @OnlyIn(Dist.CLIENT)
  public Entity spawnEntity(int entityId, World world, double scaledX, double scaledY, double scaledZ)
  {
    return null;
  }

  public static Entity getPointedAtEntity(World world, PlayerEntity player, double dist) {
    Entity pointedAt = null;
    if (player != null)
    {
      if (world != null)
      {
        double d0 = dist;
        double d1 = dist;
        Vector3d vec3 = player.getEyePosition(1.0F);
        Vector3d vec31 = player.getViewVector(1.0F);
        Vector3d vec32 = vec3.add(vec31.x * d0, vec31.y * d0, vec31.z * d0);
        pointedAt = null;
        float f1 = 1.0F;
        AxisAlignedBB pickBox = player.getBoundingBox().inflate(vec31.x * d0, vec31.y * d0, vec31.z * d0).inflate(f1, f1, f1);
        List<Entity> list = world.getEntitiesOfClass(Entity.class, pickBox);
        double d2 = d1;

        for (int i = 0; i < list.size(); i++)
        {
          Entity entity = list.get(i);

          if (entity == player || !entity.canBeCollidedWith())
            continue;
          float f2 = entity.getPickRadius();
          AxisAlignedBB axisalignedbb = entity.getBoundingBox().inflate(f2, f2, f2);
          java.util.Optional<Vector3d> hitVec = axisalignedbb.clip(vec3, vec32);

          if (axisalignedbb.contains(vec3))
          {
            if ((0.0D >= d2) && (d2 != 0.0D))
              continue;
            pointedAt = entity;
            d2 = 0.0D;
          }
          else {
            if (!hitVec.isPresent())
              continue;
            double d3 = vec3.distanceTo(hitVec.get());

            if ((d3 >= d2) && (d2 != 0.0D))
              continue;
            if ((entity == player.getVehicle()) && (!entity.canRiderInteract()))
            {
              if (d2 != 0.0D)
                continue;
              pointedAt = entity;
            }
            else
            {
              pointedAt = entity;
              d2 = d3;
            }
          }
        }
      }

    }

    return pointedAt;
  }

  /** Chunk/world being decorated by {@link ChaosWorld}; avoids blocking {@code world.getChunk} during load. */
  private static Chunk worldGenChunkContext = null;
  private static World worldGenWorldContext = null;

  public static void setWorldGenChunkContext(Chunk chunk) {
    setWorldGenChunkContext(chunk, null);
  }

  public static void setWorldGenChunkContext(Chunk chunk, World world) {
    worldGenChunkContext = chunk;
    worldGenWorldContext = world;
  }

  private static Chunk resolveChunkForBlock(World world, int blockX, int blockZ, Chunk refChunk) {
    int chunkX = blockX >> 4;
    int chunkZ = blockZ >> 4;
    if (refChunk != null && refChunk.getPos().x == chunkX && refChunk.getPos().z == chunkZ) {
      return refChunk;
    }
    if (worldGenChunkContext != null
            && worldGenChunkContext.getPos().x == chunkX
            && worldGenChunkContext.getPos().z == chunkZ) {
      return worldGenChunkContext;
    }
    Chunk chunk = world.getChunkSource().getChunkNow(chunkX, chunkZ);
    if (chunk != null) {
      return chunk;
    }
    if (!world.isClientSide) {
      return null;
    }
    return world.getChunk(chunkX, chunkZ);
  }

  public static boolean setBlockFast(World world, int par1, int par2, int par3, Block par4, int par5, int par6)
  {
    if ((par1 >= -30000000) && (par3 >= -30000000) && (par1 < 30000000) && (par3 < 30000000))
    {
      if (par2 < 0 || par2 >= 256 || par4 == null) {
        return false;
      }
      // Vanilla world.setBlock keeps chunk change packets valid (direct chunk writes caused SMultiBlockChangePacket NPEs).
      return world.setBlock(new BlockPos(par1, par2, par3), blockStateFromMeta(par4, par5), par6);
    }

    return false;
  }

  public static boolean setBlockSuperFast(World world, int par1, int par2, int par3, Block par4, int par5, int par6, Chunk refChunk)
  {
    if ((par1 >= -30000000) && (par3 >= -30000000) && (par1 < 30000000) && (par3 < 30000000))
    {
      if (par2 < 0)
      {
        return false;
      }
      if (par2 >= 256)
      {
        return false;
      }

      Chunk chunk = resolveChunkForBlock(world, par1, par3, refChunk);
      BlockPos pos = new BlockPos(par1, par2, par3);
      boolean flag = true;
      if (chunk != refChunk)
      {
        BlockState oldState = Blocks.AIR.defaultBlockState();
        if ((par6 & 0x1) != 0)
        {
          oldState = chunk.getBlockState(new BlockPos(par1 & 0xF, par2, par3 & 0xF));
        }

        flag = setBlockIDWithMetadataFast(chunk, par1 & 0xF, par2, par3 & 0xF, par4, par5);

        if (flag)
        {
          if (((par6 & 0x2) != 0) && ((!world.isClientSide) || ((par6 & 0x4) == 0)))
          {
            BlockState newState = blockStateFromMeta(par4, par5);
            world.sendBlockUpdated(pos, oldState, newState, 3);
          }

          if ((!world.isClientSide) && ((par6 & 0x1) != 0))
          {
            world.updateNeighborsAt(pos, par4);
          }

          if (!world.isClientSide)
          {
            if (world.dimensionType().hasSkyLight())
            {
              world.getLightEngine().checkBlock(pos);
            }
            world.getLightEngine().checkBlock(pos);
          }
        }
      }
      else {
        setBlockIDWithMetadataFast(chunk, par1 & 0xF, par2, par3 & 0xF, par4, par5);
        if (!world.isClientSide)
        {
          if (world.dimensionType().hasSkyLight())
          {
            world.getLightEngine().checkBlock(pos);
          }
          world.getLightEngine().checkBlock(pos);
        }
      }

      return flag;
    }

    return false;
  }

  public static boolean setBlockIDWithMetadataFast(Chunk chunk, int par1, int par2, int par3, Block par4, int par5) {
      if (chunk == null || par4 == null) {
          return false;
      }
      if (par2 < 0 || par2 >= 256) {
          return false;
      }
      chunk.setBlockState(new BlockPos(par1 & 15, par2, par3 & 15), blockStateFromMeta(par4, par5), false);
      return true;
  }

  public static Block getBlockIDInChunk(Chunk chunk, int par1, int par2, int par3)
  {
    if ((par1 >= -30000000) && (par3 >= -30000000) && (par1 < 30000000) && (par3 < 30000000)) {
      if (par1 >> 4 != chunk.getPos().x) return Blocks.AIR;
      if (par3 >> 4 != chunk.getPos().z) return Blocks.AIR;
      if ((par2 < 0) || (par2 > 255)) return Blocks.AIR;
      return chunk.getBlockState(new BlockPos(par1 & 0xF, par2, par3 & 0xF)).getBlock();
    }
    return Blocks.AIR;
  }

  public static boolean setBlockIDWithMetadataInChunk(Chunk chunk, int par1, int par2, int par3, Block par4, int par5) {
      if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
          if (par1 >> 4 != chunk.getPos().x) {
              return false;
          }
          if (par3 >> 4 != chunk.getPos().z) {
              return false;
          }
          if (par2 < 0 || par2 >= 256) {
              return false;
          }
          if (par4 == null) {
              return false;
          }
          BlockState newState = blockStateFromMeta(par4, par5);
          BlockPos worldPos = new BlockPos(par1, par2, par3);
          if (worldGenWorldContext != null && worldGenChunkContext == chunk && !worldGenWorldContext.isClientSide) {
              return worldGenWorldContext.setBlock(worldPos, newState, 2);
          }
          BlockPos localPos = new BlockPos(par1 & 15, par2, par3 & 15);
          BlockState oldState = chunk.getBlockState(localPos);
          chunk.setBlockState(localPos, newState, false);
          return true;
      }
      return false;
  }

  /**
   * Reads BaseDimensionID, optional per-world DimensionId_* overrides (-1 = BaseDimensionID + offset),
   * validates uniqueness, assigns DimensionID..DimensionID6, and optionally logs resolved IDs.
   */
  private static void configureDimensionIds(LegacyConfiguration config, String ids) {
    LegacyProperty logProp = config.get(ids, "LogRegisteredDimensionIds", true);
    logProp.setComment(
        "If true, logs every Chaos Persists dimension numeric ID at startup (INFO) so you can compare with other mods and fix collisions.");
    LogRegisteredDimensionIds = logProp.getBoolean();

    LegacyProperty baseProp = config.get(ids, "BaseDimensionID", 80);
    baseProp.setComment(
        "First ID of the default contiguous block when a DimensionId_* entry is -1. Offsets: +0 Utopia, +1 Mining, +2 Village Mania, +3 Islands (danger), +4 Crystal, +5 Chaos. "
            + "Default 80 gives 80-85. If another mod already uses one of these numbers, raise BaseDimensionID (e.g. 100) or set explicit DimensionId_* below.");
    BaseDimensionID = baseProp.getInt();

    final String[] dimKeys = new String[] {
        "DimensionId_Utopia",
        "DimensionId_Mining",
        "DimensionId_VillageMania",
        "DimensionId_Islands",
        "DimensionId_Crystal",
        "DimensionId_Chaos"
    };
    final String[] dimLabels = new String[] {
        "Utopia (WorldProviderChaos)",
        "Mining (WorldProviderChaos2)",
        "Village Mania (WorldProviderChaos3)",
        "Islands / danger (WorldProviderChaos4)",
        "Crystal (WorldProviderChaos5)",
        "Chaos (WorldProviderChaos6)"
    };

    int[] resolved = new int[6];
    for (int i = 0; i < 6; i++) {
      LegacyProperty p = config.get(ids, dimKeys[i], -1);
      p.setComment(
          "Numeric world ID for " + dimLabels[i] + ". Use -1 for automatic: BaseDimensionID+" + i + ". "
              + "Set a specific free ID to avoid conflicts (each Chaos Persists dimension must differ from every other mod).");
      int raw = p.getInt();
      resolved[i] = raw >= 0 ? raw : BaseDimensionID + i;
    }

    HashSet<Integer> seen = new HashSet<Integer>();
    for (int i = 0; i < 6; i++) {
      if (!seen.add(Integer.valueOf(resolved[i]))) {
        throw new IllegalStateException(
            "ChaosPersists: duplicate dimension ID "
                + resolved[i]
                + " in config category ["
                + ids
                + "]. Keys "
                + java.util.Arrays.toString(dimKeys)
                + " must all be unique (or -1 with distinct BaseDimensionID offsets).");
      }
    }

    DimensionID = resolved[0];
    DimensionID2 = resolved[1];
    DimensionID3 = resolved[2];
    DimensionID4 = resolved[3];
    DimensionID5 = resolved[4];
    DimensionID6 = resolved[5];

    if (LogRegisteredDimensionIds) {
      LOGGER.info("ChaosPersists dimension IDs (change in chaospersists.cfg -> [{}] if a mod conflicts):", ids);
      for (int i = 0; i < 6; i++) {
        LOGGER.info("  [{}] = {}  ({})", dimKeys[i], Integer.valueOf(resolved[i]), dimLabels[i]);
      }
    }
  }

  private ArmorStats get_armorstats(LegacyConfiguration config, String s, int dura, int head, int chest, int leg, int boots, int enchant, int e_resp, int e_aqua, int e_prot, int e_fire, int e_blast, int e_proj, int e_unbreak, int e_feather)
  {
    ArmorStats a = new ArmorStats();
    String arm = "chaospersistsARMOR";

    a.durability = config.get(arm, s + "_durability", dura).getInt();
    if (a.durability < dura / 2) a.durability = (dura / 2);
    if (a.durability > dura * 2) a.durability = (dura * 2);
    a.head_protection = config.get(arm, s + "_head_damage_reduce", head).getInt();
    if (a.head_protection < head - 2) a.head_protection = (head - 2);
    a.chest_protection = config.get(arm, s + "_chest_damage_reduce", chest).getInt();
    if (a.chest_protection < chest - 2) a.chest_protection = (chest - 2);
    a.leg_protection = config.get(arm, s + "_leggings_damage_reduce", leg).getInt();
    if (a.leg_protection < leg - 2) a.leg_protection = (leg - 2);
    a.boot_protection = config.get(arm, s + "_boots_damage_reduce", boots).getInt();
    if (a.boot_protection < boots - 2) a.boot_protection = (boots - 2);
    a.enchantability = config.get(arm, s + "_enchantability", enchant).getInt();
    if (a.enchantability < enchant / 2) a.enchantability = (enchant / 2);
    if (a.enchantability > enchant * 2) a.enchantability = (enchant * 2);

    a.e_respiration = config.get(arm, s + "_enchant_respiration", e_resp).getInt();
    if (a.e_respiration < e_resp / 2) a.e_respiration = (e_resp / 2);
    a.e_aquaaffinity = config.get(arm, s + "_enchant_aquaaffinity", e_aqua).getInt();
    if (a.e_aquaaffinity < e_aqua / 2) a.e_aquaaffinity = (e_aqua / 2);
    a.e_protection = config.get(arm, s + "_enchant_protection", e_prot).getInt();
    if (a.e_protection < e_prot / 2) a.e_protection = (e_prot / 2);
    a.e_fireprotection = config.get(arm, s + "_enchant_fireprotection", e_fire).getInt();
    if (a.e_fireprotection < e_fire / 2) a.e_fireprotection = (e_fire / 2);
    a.e_blastprotection = config.get(arm, s + "_enchant_blastprotection", e_blast).getInt();
    if (a.e_blastprotection < e_blast / 2) a.e_blastprotection = (e_blast / 2);
    a.e_projectileprotection = config.get(arm, s + "_enchant_projectileprotection", e_proj).getInt();
    if (a.e_projectileprotection < e_proj / 2) a.e_projectileprotection = (e_proj / 2);
    a.e_unbreaking = config.get(arm, s + "_enchant_unbreaking", e_unbreak).getInt();
    if (a.e_unbreaking < e_unbreak / 2) a.e_unbreaking = (e_unbreak / 2);
    a.e_featherfalling = config.get(arm, s + "_enchant_featherfalling", e_feather).getInt();
    if (a.e_featherfalling < e_feather / 2) a.e_featherfalling = (e_feather / 2);

    return a;
  }

  private WeaponStats get_weaponstats(LegacyConfiguration config, String arm, String s, int harvest, int maxuses, int efficiency, int damage, int enchantability)
  {
    WeaponStats w = new WeaponStats();

    w.harvestlevel = config.get(arm, s + "_harvestlevel", harvest).getInt();
    if (w.harvestlevel < harvest - 1) w.harvestlevel = harvest;
    w.maxuses = config.get(arm, s + "_maxuses", maxuses).getInt();
    if (w.maxuses < maxuses / 2) w.maxuses = (maxuses / 2);
    if (w.maxuses > maxuses * 2) w.maxuses = (maxuses * 2);
    w.efficiency = config.get(arm, s + "_efficiency", efficiency).getInt();
    if (w.efficiency < efficiency / 2) w.efficiency = (efficiency / 2);
    if (w.efficiency > efficiency * 2) w.efficiency = (efficiency * 2);
    w.damage = config.get(arm, s + "_damage", damage).getInt();
    if (w.damage < damage / 2) w.damage = (damage / 2);
    if (w.damage > damage * 2) w.damage = (damage * 2);
    w.enchantability = config.get(arm, s + "_enchantability", enchantability).getInt();
    if (w.enchantability < enchantability / 2) w.enchantability = (enchantability / 2);
    if (w.enchantability > enchantability * 2) w.enchantability = (enchantability * 2);

    return w;
  }

  private MobStats get_mobstats(LegacyConfiguration config, String arm, String s, int health, int attack, int defense)
  {
    MobStats m = new MobStats();

    m.health = config.get(arm, s + "_health", health).getInt();
    if (m.health < health / 2) m.health = (health / 2);
    if (m.health > health * 2) m.health = (health * 2);
    m.attack = config.get(arm, s + "_attack", attack).getInt();
    if (m.attack < attack / 2) m.attack = (attack / 2);
    if (m.attack > attack * 2) m.attack = (attack * 2);
    m.defense = config.get(arm, s + "_defense", defense).getInt();
    if (m.defense < defense - 4) m.defense = (defense - 4);
    if (m.defense > defense + 4) m.defense = (defense + 4);
    if (m.defense > 22) m.defense = 22;
    if (m.defense < 0) m.defense = 0;

    return m;
  }

  private OreStats get_orestats(LegacyConfiguration config, String arm, String s, int rate, int clumpsize, int min, int max)
  {
    OreStats o = new OreStats();

    o.rate = config.get(arm, s + "_rate", rate).getInt();
    if (o.rate < rate / 2) o.rate = (rate / 2);
    if (o.rate > rate * 2) o.rate = (rate * 2);
    o.clumpsize = config.get(arm, s + "_clumpsize", clumpsize).getInt();
    if (o.clumpsize < clumpsize / 2) o.clumpsize = (clumpsize / 2);
    if (o.clumpsize > clumpsize * 2) o.clumpsize = (clumpsize * 2);
    if (o.clumpsize < 1) o.clumpsize = 1;
    o.mindepth = config.get(arm, s + "_mindepth", min).getInt();
    if (o.mindepth < 0) o.mindepth = 0;
    o.maxdepth = config.get(arm, s + "_maxdepth", max).getInt();
    if (o.maxdepth < 0) o.maxdepth = 0;
    if (o.maxdepth - o.mindepth < 10) {
      o.mindepth = min;
      o.maxdepth = max;
    }
    return o;
  }

  private void disableAllMobs()
  {
    MosquitoEnable = 0;
    GhostEnable = 0;
    GhostSkellyEnable = 0;
    SpiderDriverEnable = 0;
    CrabEnable = 0;
    JefferyEnable = 0;
    MothraEnable = 0;
    BrutalflyEnable = 0;
    NastysaurusEnable = 0;
    PointysaurusEnable = 0;
    MothraPeaceful = 0;
    BlackAntEnable = 0;
    RedAntEnable = 0;
    TermiteEnable = 0;
    UnstableAntEnable = 0;
    RainbowAntEnable = 0;
    AlosaurusEnable = 0;
    HammerheadEnable = 0;
    LeonEnable = 0;
    CaterKillerEnable = 0;
    MolenoidEnable = 0;
    TRexEnable = 0;
    CriminalEnable = 0;
    CryolophosaurusEnable = 0;
    RatEnable = 0;
    UrchinEnable = 0;
    CamarasaurusEnable = 0;
    VelocityRaptorEnable = 0;
    HydroliscEnable = 0;
    SpyroEnable = 0;
    BaryonyxEnable = 0;
    CockateilEnable = 0;
    CassowaryEnable = 0;
    EasterBunnyEnable = 0;
    PeacockEnable = 0;
    KyuubiEnable = 0;
    CephadromeEnable = 0;
    DragonEnable = 0;
    GammaMetroidEnable = 0;
    BasiliskEnable = 0;
    DragonflyEnable = 0;
    EmperorScorpionEnable = 0;
    TrooperBugEnable = 0;
    SpitBugEnable = 0;
    StinkBugEnable = 0;
    ScorpionEnable = 0;
    CaveFisherEnable = 0;
    AlienEnable = 0;
    WaterDragonEnable = 0;
    SeaMonsterEnable = 0;
    SeaViperEnable = 0;
    AttackSquidEnable = 0;
    Robot1Enable = 0;
    Robot2Enable = 0;
    Robot3Enable = 0;
    Robot4Enable = 0;
    Robot5Enable = 0;
    RotatorEnable = 0;
    VortexEnable = 0;
    DungeonBeastEnable = 0;
    KrakenEnable = 0;
    LizardEnable = 0;
    RubberDuckyEnable = 0;
    GirlfriendEnable = 0;
    BoyfriendEnable = 0;
    FireflyEnable = 0;
    FairyEnable = 0;
    BeeEnable = 0;
    TheKingEnable = 0;
    TheQueenEnable = 0;
    MantisEnable = 0;
    StinkyEnable = 0;
    HerculesBeetleEnable = 0;
    ChipmunkEnable = 0;
    OstrichEnable = 0;
    GazelleEnable = 0;
    CowEnable = 0;
    ButterflyEnable = 0;
    MothEnable = 0;
    TshirtEnable = 0;
    CoinEnable = 0;
    CreepingHorrorEnable = 0;
    TerribleTerrorEnable = 0;
    CliffRacerEnable = 0;
    TriffidEnable = 0;
    WormEnable = 0;
    CloudSharkEnable = 0;
    GoldFishEnable = 0;
    LeafMonsterEnable = 0;
    EnderKnightEnable = 0;
    EnderReaperEnable = 0;
    BeaverEnable = 0;
    IrukandjiEnable = 0;
    SkateEnable = 0;
    WhaleEnable = 0;
    FlounderEnable = 0;
    PitchBlackEnable = 0;
    LurkingTerrorEnable = 0;
    GodzillaEnable = 0;
    CrabEnable = 0;
  }

  private void laySomeEggs()
  {
    MySpiderSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orespider"));
    MyBatSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orebat"));
    MyCowSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecow"));
    MyPigSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orepig"));
    MySquidSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oresquid"));
    MyChickenSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orechicken"));
    MyCreeperSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecreeper"));
    MySkeletonSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreskeleton"));
    MyZombieSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orezombie"));
    MySlimeSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreslime"));
    MyGhastSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreghast"));
    MyZombiePigmanSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orezombiepigman"));
    MyEndermanSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreenderman"));
    MyCaveSpiderSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecavespider"));
    MySilverfishSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oresilverfish"));
    MyMagmaCubeSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oremagmacube"));
    MyWitchSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orewitch"));
    MySheepSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oresheep"));
    MyWolfSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orewolf"));
    MyMooshroomSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oremooshroom"));
    MyOcelotSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreocelot"));
    MyBlazeSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreblaze"));
    MyWitherSkeletonSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orewitherskeleton"));
    MyEnderDragonSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreenderdragon"));
    MySnowGolemSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oresnowgolem"));
    MyIronGolemSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreirongolem"));
    MyWitherBossSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orewitherboss"));
    MyGirlfriendSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregirlfriend"));
    MyBoyfriendSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreboyfriend"));
    MyRedCowSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreredcow"));
    MyCrystalCowSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecrystalcow"));
    MyVillagerSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orevillager"));
    MyGoldCowSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregoldcow"));
    MyEnchantedCowSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreenchantedcow"));
    MyMOTHRASpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oremothra"));
    MyAntBlock = new AntBlock(0).setRegistryName(new ResourceLocation("chaospersists", "antblock"));
    MyRedAntBlock = new AntBlock(0).setRegistryName(new ResourceLocation("chaospersists", "redantblock"));
    TermiteBlock = new AntBlock(0).setRegistryName(new ResourceLocation("chaospersists", "termiteblock"));
    CrystalTermiteBlock = new CrystalAntBlock(0).setRegistryName(new ResourceLocation("chaospersists", "crystaltermiteblock"));
    MyRainbowAntBlock = new AntBlock(0).setRegistryName(new ResourceLocation("chaospersists", "rainbowantblock"));
    MyUnstableAntBlock = new AntBlock(0).setRegistryName(new ResourceLocation("chaospersists", "unstableantblock"));
    MyAloSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orealosaurus"));
    MyCryoSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecryolophosaurus"));
    MyCamaSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecamarasaurus"));
    MyVeloSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orevelocityraptor"));
    MyHydroSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orehydrolisc"));
    MyBasilSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orebasilisc"));
    MyDragonflySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oredragonfly"));
    MyEmperorScorpionSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreemperorscorpion"));
    MyScorpionSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orescorpion"));
    MyCaveFisherSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecavefisher"));
    MySpyroSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orespyro"));
    MyBaryonyxSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orebaryonyx"));
    MyGammaMetroidSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregammametroid"));
    MyCockateilSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecockateil"));
    MyKyuubiSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orekyuubi"));
    MyAlienSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orealien"));
    MyAttackSquidSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreattacksquid"));
    MyWaterDragonSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orewaterdragon"));
    MyCephadromeSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecephadrome"));
    MyDragonSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oredragon"));
    MyKrakenSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orekraken"));
    MyLizardSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orelizard"));
    MyBeeSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orebee"));
    MyHorseSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orehorse"));
    MyTrooperBugSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oretrooper"));
    MySpitBugSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orespit"));
    MyStinkBugSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orestink"));
    MyOstrichSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreostrich"));
    MyGazelleSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregazelle"));
    MyChipmunkSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orechipmunk"));
    MyCreepingHorrorSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecreepinghorror"));
    MyTerribleTerrorSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreterribleterror"));
    MyCliffRacerSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecliffracer"));
    MyTriffidSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oretriffid"));
    MyPitchBlackSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orenightmare"));
    MyLurkingTerrorSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orelurkingterror"));
    MyGodzillaPartSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregodzillapart"));
    MyGodzillaSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregodzilla"));
    MySmallWormSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oresmallworm"));
    MyMediumWormSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oremediumworm"));
    MyLargeWormSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orelargeworm"));
    MyCassowarySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecassowary"));
    MyCloudSharkSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecloudshark"));
    MyGoldFishSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oregoldfish"));
    MyLeafMonsterSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreleafmonster"));
    MyTshirtSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oretshirt"));
    MyEnderKnightSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreenderknight"));
    MyEnderReaperSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreenderreaper"));
    MyBeaverSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orebeaver"));
    MyUrchinSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreurchin"));
    MyFlounderSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreflounder"));
    MySkateSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreskate"));
    MyRotatorSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orerotator"));
    MyPeacockSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orepeacock"));
    MyFairySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orefairy"));
    MyDungeonBeastSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oredungeonbeast"));
    MyVortexSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orevortex"));
    MyRatSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orerat"));
    MyWhaleSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orewhale"));
    MyIrukandjiSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreirukandji"));
    MyTRexSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oretrex"));
    MyHerculesSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orehercules"));
    MyMantisSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oremantis"));
    MyStinkySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orestinky"));
    MyTheKingPartSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orethekingpart"));
    MyTheKingSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oretheking"));
    MyTheQueenPartSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orethequeenpart"));
    MyTheQueenSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orethequeen"));
    MyEasterBunnySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreeasterbunny"));
    MyCaterKillerSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecaterkiller"));
    MyMolenoidSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oremolenoid"));
    MySeaMonsterSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreseamonster"));
    MySeaViperSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreseaviper"));
    MyLeonSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "oreleon"));
    MyHammerheadSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orehammerhead"));
    MyRubberDuckySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orerubberducky"));
    MyCriminalSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecriminal"));
    MyBrutalflySpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orebrutalfly"));
    MyNastysaurusSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orenastysaurus"));
    MyPointysaurusSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orepointysaurus"));
    MyCricketSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecricket"));
    MyFrogSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orefrog"));
    MySpiderDriverSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orespiderdriver"));
    MyCrabSpawnBlock = (OreGenericEgg)(OreGenericEgg)new OreGenericEgg().setRegistryName(new ResourceLocation("chaospersists", "orecrab"));
  }

  private void getMobs(LegacyConfiguration config, String mobs)
  {
    MosquitoEnable = config.get(mobs, "MosquitoEnable", 1).getInt();
    RockEnable = config.get(mobs, "RockEnable", 1).getInt();
    GhostEnable = config.get(mobs, "GhostEnable", 1).getInt();
    GhostSkellyEnable = config.get(mobs, "GhostSkellyEnable", 1).getInt();
    SpiderDriverEnable = config.get(mobs, "SpiderDriverEnable", 1).getInt();
    JefferyEnable = config.get(mobs, "JefferyEnable", 1).getInt();
    MothraEnable = config.get(mobs, "MothraEnable", 1).getInt();
    BrutalflyEnable = config.get(mobs, "BrutalflyEnable", 1).getInt();
    NastysaurusEnable = config.get(mobs, "NastysaurusEnable", 1).getInt();
    PointysaurusEnable = config.get(mobs, "PointysaurusEnable", 1).getInt();
    CricketEnable = config.get(mobs, "CricketEnable", 1).getInt();
    FrogEnable = config.get(mobs, "FrogEnable", 1).getInt();
    MothraPeaceful = config.get(mobs, "MothraPeaceful", 0).getInt();
    BlackAntEnable = config.get(mobs, "BlackAntEnable", 1).getInt();
    RedAntEnable = config.get(mobs, "RedAntEnable", 1).getInt();
    TermiteEnable = config.get(mobs, "TermiteEnable", 1).getInt();
    UnstableAntEnable = config.get(mobs, "UnstableAntEnable", 1).getInt();
    RainbowAntEnable = config.get(mobs, "RainbowedAntEnable", 1).getInt();
    AlosaurusEnable = config.get(mobs, "AlosaurusEnable", 1).getInt();
    HammerheadEnable = config.get(mobs, "HammerheadEnable", 1).getInt();
    LeonEnable = config.get(mobs, "LeonEnable", 1).getInt();
    CaterKillerEnable = config.get(mobs, "CaterKillerEnable", 1).getInt();
    MolenoidEnable = config.get(mobs, "MolenoidEnable", 1).getInt();
    TRexEnable = config.get(mobs, "TRexEnable", 1).getInt();
    CriminalEnable = config.get(mobs, "CriminalEnable", 1).getInt();
    CryolophosaurusEnable = config.get(mobs, "CryolophosaurusEnable", 1).getInt();
    RatEnable = config.get(mobs, "RatEnable", 1).getInt();
    UrchinEnable = config.get(mobs, "UrchinEnable", 1).getInt();
    CamarasaurusEnable = config.get(mobs, "CamarasaurusEnable", 1).getInt();
    VelocityRaptorEnable = config.get(mobs, "VelocityRaptorEnable", 1).getInt();
    HydroliscEnable = config.get(mobs, "HydroliscEnable", 1).getInt();
    SpyroEnable = config.get(mobs, "SpyroEnable", 1).getInt();
    BaryonyxEnable = config.get(mobs, "BaryonyxEnable", 1).getInt();
    CockateilEnable = config.get(mobs, "BirdEnable", 1).getInt();
    CassowaryEnable = config.get(mobs, "CassowaryEnable", 1).getInt();
    EasterBunnyEnable = config.get(mobs, "EasterBunnyEnable", 1).getInt();
    PeacockEnable = config.get(mobs, "PeacockEnable", 1).getInt();
    KyuubiEnable = config.get(mobs, "KyuubiEnable", 1).getInt();
    CephadromeEnable = config.get(mobs, "CephadromeEnable", 1).getInt();
    DragonEnable = config.get(mobs, "DragonEnable", 1).getInt();
    GammaMetroidEnable = config.get(mobs, "GammaMetroidEnable", 1).getInt();
    BasiliskEnable = config.get(mobs, "BasiliskEnable", 1).getInt();
    DragonflyEnable = config.get(mobs, "DragonflyEnable", 1).getInt();
    EmperorScorpionEnable = config.get(mobs, "EmperorScorpionEnable", 1).getInt();
    TrooperBugEnable = config.get(mobs, "TrooperBugEnable", 1).getInt();
    SpitBugEnable = config.get(mobs, "SpitBugEnable", 1).getInt();
    StinkBugEnable = config.get(mobs, "StinkBugEnable", 1).getInt();
    ScorpionEnable = config.get(mobs, "ScorpionEnable", 1).getInt();
    CaveFisherEnable = config.get(mobs, "CaveFisherEnable", 1).getInt();
    AlienEnable = config.get(mobs, "AlienEnable", 1).getInt();
    WaterDragonEnable = config.get(mobs, "WaterDragonEnable", 1).getInt();
    SeaMonsterEnable = config.get(mobs, "SeaMonsterEnable", 1).getInt();
    SeaViperEnable = config.get(mobs, "SeaViperEnable", 1).getInt();
    AttackSquidEnable = config.get(mobs, "AttackSquidEnable", 1).getInt();
    Robot1Enable = config.get(mobs, "Robot1Enable", 1).getInt();
    Robot2Enable = config.get(mobs, "Robot2Enable", 1).getInt();
    Robot3Enable = config.get(mobs, "Robot3Enable", 1).getInt();
    Robot4Enable = config.get(mobs, "Robot4Enable", 1).getInt();
    Robot5Enable = config.get(mobs, "Robot5Enable", 1).getInt();
    RotatorEnable = config.get(mobs, "RotatorEnable", 1).getInt();
    VortexEnable = config.get(mobs, "VortexEnable", 1).getInt();
    DungeonBeastEnable = config.get(mobs, "DungeonBeastEnable", 1).getInt();
    KrakenEnable = config.get(mobs, "KrakenEnable", 1).getInt();
    LizardEnable = config.get(mobs, "LizardEnable", 1).getInt();
    RubberDuckyEnable = config.get(mobs, "RubberDuckyEnable", 1).getInt();
    GirlfriendEnable = config.get(mobs, "GirlfriendEnable", 1).getInt();
    BoyfriendEnable = config.get(mobs, "BoyfriendEnable", 0).getInt();
    FireflyEnable = config.get(mobs, "FireflyEnable", 1).getInt();
    FairyEnable = config.get(mobs, "FairyEnable", 1).getInt();
    BeeEnable = config.get(mobs, "BeeEnable", 1).getInt();
    TheKingEnable = config.get(mobs, "TheKingEnable", 1).getInt();
    TheQueenEnable = config.get(mobs, "TheQueenEnable", 1).getInt();
    MantisEnable = config.get(mobs, "MantisEnable", 1).getInt();
    StinkyEnable = config.get(mobs, "StinkyEnable", 1).getInt();
    HerculesBeetleEnable = config.get(mobs, "HerculesBeetleEnable", 1).getInt();
    ChipmunkEnable = config.get(mobs, "ChipmunkEnable", 1).getInt();
    OstrichEnable = config.get(mobs, "OstrichEnable", 1).getInt();
    GazelleEnable = config.get(mobs, "GazelleEnable", 1).getInt();
    CowEnable = config.get(mobs, "CowEnable", 1).getInt();
    ButterflyEnable = config.get(mobs, "ButterflyEnable", 1).getInt();
    MothEnable = config.get(mobs, "MothEnable", 1).getInt();
    TshirtEnable = config.get(mobs, "TshirtEnable", 1).getInt();
    CoinEnable = config.get(mobs, "CoinEnable", 1).getInt();
    CreepingHorrorEnable = config.get(mobs, "CreepingHorrorEnable", 1).getInt();
    TerribleTerrorEnable = config.get(mobs, "TerribleTerrorEnable", 1).getInt();
    CliffRacerEnable = config.get(mobs, "CliffRacerEnable", 1).getInt();
    TriffidEnable = config.get(mobs, "TriffidEnable", 1).getInt();
    WormEnable = config.get(mobs, "WormEnable", 1).getInt();
    CloudSharkEnable = config.get(mobs, "CloudSharkEnable", 1).getInt();
    GoldFishEnable = config.get(mobs, "GoldFishEnable", 1).getInt();
    LeafMonsterEnable = config.get(mobs, "LeafMonsterEnable", 1).getInt();
    EnderKnightEnable = config.get(mobs, "EnderKnightEnable", 1).getInt();
    EnderReaperEnable = config.get(mobs, "EnderReaperEnable", 1).getInt();
    BeaverEnable = config.get(mobs, "BeaverEnable", 1).getInt();
    IrukandjiEnable = config.get(mobs, "IrukandjiEnable", 1).getInt();
    SkateEnable = config.get(mobs, "SkateEnable", 1).getInt();
    WhaleEnable = config.get(mobs, "WhaleEnable", 1).getInt();
    FlounderEnable = config.get(mobs, "FlounderEnable", 1).getInt();
    PitchBlackEnable = config.get(mobs, "NightmareEnable", 1).getInt();
    LurkingTerrorEnable = config.get(mobs, "LurkingTerrorEnable", 1).getInt();
    GodzillaEnable = config.get(mobs, "GodzillaEnable", 1).getInt();
    CrabEnable = config.get(mobs, "CrabEnable", 1).getInt();

    Bee_stats = get_mobstats(config, mobs, "Bee", 80, 12, 5);
    Mantis_stats = get_mobstats(config, mobs, "Mantis", 120, 16, 10);
    HerculesBeetle_stats = get_mobstats(config, mobs, "HerculesBeetle", 250, 30, 19);
    Mothra_stats = get_mobstats(config, mobs, "Mothra", 150, 12, 8);
    Brutalfly_stats = get_mobstats(config, mobs, "Brutalfly", 110, 10, 6);
    Nastysaurus_stats = get_mobstats(config, mobs, "Nastysaurus", 200, 32, 17);
    Pointysaurus_stats = get_mobstats(config, mobs, "Pointysaurus", 80, 10, 16);
    Alosaurus_stats = get_mobstats(config, mobs, "Alosaurus", 110, 18, 8);
    SpiderRobot_stats = get_mobstats(config, mobs, "SpiderRobot", 1500, 100, 16);
    AntRobot_stats = get_mobstats(config, mobs, "AntRobot", 300, 30, 16);
    Jeffery_stats = get_mobstats(config, mobs, "Jeffery", 550, 40, 18);
    Hammerhead_stats = get_mobstats(config, mobs, "Hammerhead", 240, 75, 20);
    Molenoid_stats = get_mobstats(config, mobs, "Molenoid", 200, 18, 12);
    TRex_stats = get_mobstats(config, mobs, "TRex", 160, 22, 14);
    BandP_stats = get_mobstats(config, mobs, "BandP", 100, 1, 18);
    CaterKiller_stats = get_mobstats(config, mobs, "CaterKiller", 450, 32, 19);
    Cryolophosaurus_stats = get_mobstats(config, mobs, "Cryolophosaurus", 10, 3, 1);
    Rat_stats = get_mobstats(config, mobs, "Rat", 5, 3, 1);
    Urchin_stats = get_mobstats(config, mobs, "Urchin", 25, 10, 4);
    Kyuubi_stats = get_mobstats(config, mobs, "Kyuubi", 125, 10, 10);
    GammaMetroid_stats = get_mobstats(config, mobs, "GammaMetroid", 100, 10, 12);
    Basilisk_stats = get_mobstats(config, mobs, "Basilisk", 200, 24, 15);
    EmperorScorpion_stats = get_mobstats(config, mobs, "EmperorScorpion", 350, 35, 20);
    TrooperBug_stats = get_mobstats(config, mobs, "TrooperBug", 200, 20, 15);
    SpitBug_stats = get_mobstats(config, mobs, "SpitBug", 100, 10, 12);
    Alien_stats = get_mobstats(config, mobs, "Alien", 100, 12, 8);
    WaterDragon_stats = get_mobstats(config, mobs, "WaterDragon", 150, 20, 8);
    SeaMonster_stats = get_mobstats(config, mobs, "SeaMonster", 110, 14, 8);
    SeaViper_stats = get_mobstats(config, mobs, "SeaViper", 160, 22, 12);
    Robot2_stats = get_mobstats(config, mobs, "Robot2", 200, 22, 18);
    Robot3_stats = get_mobstats(config, mobs, "Robot3", 80, 16, 14);
    Robot4_stats = get_mobstats(config, mobs, "Robot4", 170, 12, 18);
    Robot5_stats = get_mobstats(config, mobs, "Robot5", 20, 5, 6);
    Rotator_stats = get_mobstats(config, mobs, "Rotator", 35, 10, 8);
    Vortex_stats = get_mobstats(config, mobs, "Vortex", 150, 26, 10);
    DungeonBeast_stats = get_mobstats(config, mobs, "DungeonBeast", 65, 12, 6);
    Triffid_stats = get_mobstats(config, mobs, "Triffid", 100, 20, 12);
    LurkingTerror_stats = get_mobstats(config, mobs, "LurkingTerror", 30, 6, 5);
    WormSmall_stats = get_mobstats(config, mobs, "WormSmall", 10, 3, 0);
    WormMedium_stats = get_mobstats(config, mobs, "WormMedium", 30, 10, 8);
    WormLarge_stats = get_mobstats(config, mobs, "WormLarge", 90, 18, 14);
    EnderKnight_stats = get_mobstats(config, mobs, "EnderKnight", 60, 12, 6);
    EnderReaper_stats = get_mobstats(config, mobs, "EnderReaper", 90, 18, 8);
    Irukandji_stats = get_mobstats(config, mobs, "Irukandji", 1, 20, 0);
    AttackSquid_stats = get_mobstats(config, mobs, "AttackSquid", 10, 8, 0);
    CaveFisher_stats = get_mobstats(config, mobs, "CaveFisher", 10, 4, 4);
    CloudShark_stats = get_mobstats(config, mobs, "CloudShark", 15, 6, 5);
    CreepingHorror_stats = get_mobstats(config, mobs, "CreepingHorror", 10, 3, 2);
    Godzilla_stats = get_mobstats(config, mobs, "Mobzilla", 4000, 175, 21);
    Kraken_stats = get_mobstats(config, mobs, "Kraken", 1000, 40, 10);
    LeafMonster_stats = get_mobstats(config, mobs, "LeafMonster", 6, 2, 1);
    PitchBlack_stats = get_mobstats(config, mobs, "Nightmare", 250, 30, 10);
    Scorpion_stats = get_mobstats(config, mobs, "Scorpion", 15, 4, 10);
    Skate_stats = get_mobstats(config, mobs, "Skate", 8, 8, 4);
    TerribleTerror_stats = get_mobstats(config, mobs, "TerribleTerror", 10, 5, 3);
    TheKing_stats = get_mobstats(config, mobs, "TheKing", 7000, 350, 21);
    TheQueen_stats = get_mobstats(config, mobs, "TheQueen", 6000, 225, 21);
    Leon_stats = get_mobstats(config, mobs, "Leonopteryx", 150, 20, 8);
    Crab_stats = get_mobstats(config, mobs, "Crab", 180, 24, 16);
  }

  public String getVersion()
  {
    return "1.7.10.20.3";
  }

  /** 1.12.2 {@code net.minecraftforge.common.config.Configuration} compatibility for chaospersists.cfg. */
  static final class LegacyConfiguration {
    private final com.electronwill.nightconfig.core.Config config;
    private final File file;

    LegacyConfiguration(File configFile) {
      this.file = configFile;
      this.config = com.electronwill.nightconfig.core.Config.inMemory();
      if (configFile.exists()) {
        loadForgeLegacyCfg(configFile, this.config);
      }
    }

    void load() {
      if (this.file.exists()) {
        this.config.clear();
        loadForgeLegacyCfg(this.file, this.config);
      }
    }

    void save() {
      try {
        if (this.file.getParentFile() != null) {
          this.file.getParentFile().mkdirs();
        }
        saveForgeLegacyCfg(this.file, this.config);
      } catch (java.io.IOException e) {
        LOGGER.error("Failed to save {}", this.file, e);
      }
    }

    private static void loadForgeLegacyCfg(File configFile, com.electronwill.nightconfig.core.Config config) {
      try {
        String category = "";
        for (String rawLine : java.nio.file.Files.readAllLines(configFile.toPath())) {
          String line = rawLine.trim();
          if (line.isEmpty() || line.startsWith("#")) {
            continue;
          }
          if (line.endsWith("{")) {
            category = line.substring(0, line.length() - 1).trim();
            if (!config.contains(category)) {
              config.set(category, new java.util.LinkedHashMap<String, Object>());
            }
            continue;
          }
          if ("}".equals(line)) {
            continue;
          }
          int eq = line.indexOf('=');
          if (eq < 0 || category.isEmpty()) {
            continue;
          }
          String keyPart = line.substring(0, eq).trim();
          String valuePart = line.substring(eq + 1).trim();
          int colon = keyPart.indexOf(':');
          String key = colon >= 0 ? keyPart.substring(colon + 1) : keyPart;
          java.util.Map<String, Object> section = config.get(category);
          section.put(key, parseForgeLegacyValue(valuePart));
        }
      } catch (java.io.IOException e) {
        LOGGER.error("Failed to load {}", configFile, e);
      }
    }

    private static Object parseForgeLegacyValue(String valuePart) {
      if ("true".equalsIgnoreCase(valuePart) || "false".equalsIgnoreCase(valuePart)) {
        return Boolean.valueOf(valuePart);
      }
      if (valuePart.startsWith("\"") && valuePart.endsWith("\"") && valuePart.length() >= 2) {
        return valuePart.substring(1, valuePart.length() - 1);
      }
      try {
        if (valuePart.contains(".")) {
          return Double.valueOf(valuePart);
        }
        return Integer.valueOf(valuePart);
      } catch (NumberFormatException ignored) {
        return valuePart;
      }
    }

    private static void saveForgeLegacyCfg(File configFile, com.electronwill.nightconfig.core.Config config) throws java.io.IOException {
      java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(configFile.toPath());
      try {
        writer.write("# Configuration file");
        writer.newLine();
        writer.newLine();
        for (Object categoryObj : config.valueMap().keySet()) {
          String category = String.valueOf(categoryObj);
          writer.write(category);
          writer.write(" {");
          writer.newLine();
          java.util.Map<String, Object> section = config.get(category);
          for (java.util.Map.Entry<String, Object> entry : section.entrySet()) {
            writer.write("    ");
            writer.write(formatForgeLegacyKey(entry.getKey(), entry.getValue()));
            writer.write("=");
            writer.write(formatForgeLegacyValue(entry.getValue()));
            writer.newLine();
          }
          writer.write("}");
          writer.newLine();
          writer.newLine();
        }
      } finally {
        writer.close();
      }
    }

    private static String formatForgeLegacyKey(String key, Object value) {
      if (value instanceof Boolean) {
        return "B:" + key;
      }
      if (value instanceof Integer) {
        return "I:" + key;
      }
      if (value instanceof Double || value instanceof Float) {
        return "D:" + key;
      }
      return "S:" + key;
    }

    private static String formatForgeLegacyValue(Object value) {
      if (value instanceof String) {
        return "\"" + value + "\"";
      }
      return String.valueOf(value);
    }

    void setCategoryComment(String category, String comment) {
    }

    LegacyProperty get(String category, String key, boolean defaultValue) {
      return new LegacyProperty(this.config, category, key, Boolean.valueOf(defaultValue));
    }

    LegacyProperty get(String category, String key, int defaultValue) {
      return new LegacyProperty(this.config, category, key, Integer.valueOf(defaultValue));
    }
  }

  static final class LegacyProperty {
    private final com.electronwill.nightconfig.core.Config config;
    private final String category;
    private final String key;
    private final Object defaultValue;
    private String comment;

    LegacyProperty(com.electronwill.nightconfig.core.Config config, String category, String key, Object defaultValue) {
      this.config = config;
      this.category = category;
      this.key = key;
      this.defaultValue = defaultValue;
      if (!this.config.contains(this.category)) {
        this.config.set(this.category, new java.util.LinkedHashMap<String, Object>());
      }
      java.util.Map<String, Object> section = this.config.get(this.category);
      if (!section.containsKey(this.key)) {
        section.put(this.key, defaultValue);
      }
    }

    void setComment(String comment) {
      this.comment = comment;
    }

    boolean getBoolean() {
      Object value = this.getValue();
      if (value instanceof Boolean) {
        return ((Boolean) value).booleanValue();
      }
      if (value instanceof Number) {
        return ((Number) value).intValue() != 0;
      }
      return this.defaultValue instanceof Boolean ? ((Boolean) this.defaultValue).booleanValue() : false;
    }

    int getInt() {
      Object value = this.getValue();
      if (value instanceof Number) {
        return ((Number) value).intValue();
      }
      return this.defaultValue instanceof Number ? ((Number) this.defaultValue).intValue() : 0;
    }

    private Object getValue() {
      if (!this.config.contains(this.category)) {
        return this.defaultValue;
      }
      java.util.Map<String, Object> section = this.config.get(this.category);
      return section.containsKey(this.key) ? section.get(this.key) : this.defaultValue;
    }
  }
}
