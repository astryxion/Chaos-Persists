package com.astryxion.chaospersists.core;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.world.BiomeModifier;
import com.astryxion.chaospersists.world.biome.BiomeMiningDimension;
import com.mojang.serialization.Codec;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ThreadedLevelLightEngine;
import com.astryxion.chaospersists.compat.minecraft.init.Biomes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundLightUpdatePacket;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.UseAnim;
import com.astryxion.chaospersists.compat.forge.common.CreativeTabCompat;
import com.astryxion.chaospersists.compat.forge.common.RegistryCompat;
import com.astryxion.chaospersists.compat.forge.fml.common.event.FMLInitializationEvent;
import com.astryxion.chaospersists.compat.forge.fml.common.event.FMLPostInitializationEvent;
import com.astryxion.chaospersists.compat.forge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import com.astryxion.chaospersists.compat.minecraft.block.BlockDispenser;
import com.astryxion.chaospersists.compat.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import com.astryxion.chaospersists.container.ContainerCrystalWorkbench;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import com.astryxion.chaospersists.compat.forge.common.config.Configuration;
import com.astryxion.chaospersists.compat.forge.fml.common.network.NetworkRegistry;
import com.astryxion.chaospersists.compat.forge.common.config.Property;
import com.astryxion.chaospersists.compat.forge.common.util.EnumHelper;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.level.ChunkEvent;
import net.minecraftforge.event.level.LevelEvent;
import com.astryxion.chaospersists.util.RoyalPetFollowHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import com.astryxion.chaospersists.compat.forge.fml.common.registry.EntityRegistry;
import com.astryxion.chaospersists.compat.forge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import com.astryxion.chaospersists.model.*;
import com.astryxion.chaospersists.render.RenderAlien;
import com.astryxion.chaospersists.render.RenderBoyfriend;
import com.astryxion.chaospersists.render.RenderCage;
import com.astryxion.chaospersists.render.RenderElevator;
import com.astryxion.chaospersists.render.RenderGirlfriend;
import com.astryxion.chaospersists.render.RenderItemUrchin;
import com.astryxion.chaospersists.render.RenderPurplePower;
import com.astryxion.chaospersists.render.RenderShoe;
import com.astryxion.chaospersists.render.RenderSpiderDriver;
import com.astryxion.chaospersists.render.RenderThrowableBillboard;
import com.astryxion.chaospersists.render.RenderUltimateArrow;
import com.astryxion.chaospersists.render.RenderUltimateFishHook;
import com.astryxion.chaospersists.render.RenderAlosaurus;
import com.astryxion.chaospersists.render.RenderAnt;
import com.astryxion.chaospersists.render.RenderAntRobot;
import com.astryxion.chaospersists.render.RenderAttackSquid;
import com.astryxion.chaospersists.render.RenderBandP;
import com.astryxion.chaospersists.render.RenderBaryonyx;
import com.astryxion.chaospersists.render.RenderBasilisk;
import com.astryxion.chaospersists.render.RenderBeaver;
import com.astryxion.chaospersists.render.RenderBee;
import com.astryxion.chaospersists.render.RenderBrutalfly;
import com.astryxion.chaospersists.render.RenderButterfly;
import com.astryxion.chaospersists.render.RenderCamarasaurus;
import com.astryxion.chaospersists.render.RenderCassowary;
import com.astryxion.chaospersists.render.RenderCaterKiller;
import com.astryxion.chaospersists.render.RenderCaveFisher;
import com.astryxion.chaospersists.render.RenderCephadrome;
import com.astryxion.chaospersists.render.RenderChipmunk;
import com.astryxion.chaospersists.render.RenderCliffRacer;
import com.astryxion.chaospersists.render.RenderCloudShark;
import com.astryxion.chaospersists.render.RenderCockateil;
import com.astryxion.chaospersists.render.RenderCoin;
import com.astryxion.chaospersists.render.RenderCrab;
import com.astryxion.chaospersists.render.RenderCreepingHorror;
import com.astryxion.chaospersists.render.RenderCricket;
import com.astryxion.chaospersists.render.RenderCryolophosaurus;
import com.astryxion.chaospersists.render.RenderDragon;
import com.astryxion.chaospersists.render.RenderDragonfly;
import com.astryxion.chaospersists.render.RenderDungeonBeast;
import com.astryxion.chaospersists.render.RenderEasterBunny;
import com.astryxion.chaospersists.render.RenderEmperorScorpion;
import com.astryxion.chaospersists.render.RenderEnchantedCow;
import com.astryxion.chaospersists.render.RenderEnderKnight;
import com.astryxion.chaospersists.render.RenderEnderReaper;
import com.astryxion.chaospersists.render.RenderFairy;
import com.astryxion.chaospersists.render.RenderFirefly;
import com.astryxion.chaospersists.render.RenderFlounder;
import com.astryxion.chaospersists.render.RenderFrog;
import com.astryxion.chaospersists.render.RenderGammaMetroid;
import com.astryxion.chaospersists.render.RenderGazelle;
import com.astryxion.chaospersists.render.RenderGhost;
import com.astryxion.chaospersists.render.RenderGhostSkelly;
import com.astryxion.chaospersists.render.RenderGiantRobot;
import com.astryxion.chaospersists.render.RenderGodzilla;
import com.astryxion.chaospersists.render.RenderGodzillaHead;
import com.astryxion.chaospersists.render.RenderKingHead;
import com.astryxion.chaospersists.render.RenderQueenHead;
import com.astryxion.chaospersists.render.RenderThrownRock;
import com.astryxion.chaospersists.render.RenderGoldFish;
import com.astryxion.chaospersists.render.RenderHammerhead;
import com.astryxion.chaospersists.render.RenderHerculesBeetle;
import com.astryxion.chaospersists.render.RenderHydrolisc;
import com.astryxion.chaospersists.render.RenderIrukandji;
import com.astryxion.chaospersists.render.RenderIsland;
import com.astryxion.chaospersists.render.RenderIslandToo;
import com.astryxion.chaospersists.render.RenderKraken;
import com.astryxion.chaospersists.render.RenderKyuubi;
import com.astryxion.chaospersists.render.RenderLeafMonster;
import com.astryxion.chaospersists.render.RenderLeon;
import com.astryxion.chaospersists.render.RenderLizard;
import com.astryxion.chaospersists.render.RenderLurkingTerror;
import com.astryxion.chaospersists.render.RenderMantis;
import com.astryxion.chaospersists.render.RenderMolenoid;
import com.astryxion.chaospersists.render.RenderMosquito;
import com.astryxion.chaospersists.render.RenderNastysaurus;
import com.astryxion.chaospersists.render.RenderOstrich;
import com.astryxion.chaospersists.render.RenderPeacock;
import com.astryxion.chaospersists.render.RenderPitchBlack;
import com.astryxion.chaospersists.render.RenderPointysaurus;
import com.astryxion.chaospersists.render.RenderRat;
import com.astryxion.chaospersists.render.RenderRobot1;
import com.astryxion.chaospersists.render.RenderRobot2;
import com.astryxion.chaospersists.render.RenderRobot3;
import com.astryxion.chaospersists.render.RenderRobot4;
import com.astryxion.chaospersists.render.RenderRobot5;
import com.astryxion.chaospersists.render.RenderRockBase;
import com.astryxion.chaospersists.render.RenderRotator;
import com.astryxion.chaospersists.render.RenderRubberDucky;
import com.astryxion.chaospersists.render.RenderScorpion;
import com.astryxion.chaospersists.render.RenderSeaMonster;
import com.astryxion.chaospersists.render.RenderSeaViper;
import com.astryxion.chaospersists.render.RenderSkate;
import com.astryxion.chaospersists.render.RenderSpiderRobot;
import com.astryxion.chaospersists.render.RenderSpitBug;
import com.astryxion.chaospersists.render.RenderSpyro;
import com.astryxion.chaospersists.render.RenderStinkBug;
import com.astryxion.chaospersists.render.RenderStinky;
import com.astryxion.chaospersists.render.RenderTRex;
import com.astryxion.chaospersists.render.RenderTerribleTerror;
import com.astryxion.chaospersists.render.RenderTheKing;
import com.astryxion.chaospersists.render.RenderThePrince;
import com.astryxion.chaospersists.render.RenderThePrinceAdult;
import com.astryxion.chaospersists.render.RenderThePrinceTeen;
import com.astryxion.chaospersists.render.RenderThePrincess;
import com.astryxion.chaospersists.render.RenderTheQueen;
import com.astryxion.chaospersists.render.RenderTriffid;
import com.astryxion.chaospersists.render.RenderTrooperBug;
import com.astryxion.chaospersists.render.RenderTshirt;
import com.astryxion.chaospersists.render.RenderUrchin;
import com.astryxion.chaospersists.render.RenderVelocityRaptor;
import com.astryxion.chaospersists.render.RenderVortex;
import com.astryxion.chaospersists.render.RenderWaterDragon;
import com.astryxion.chaospersists.render.RenderWhale;
import com.astryxion.chaospersists.render.RenderWormLarge;
import com.astryxion.chaospersists.render.RenderWormMedium;
import com.astryxion.chaospersists.render.RenderWormSmall;

import com.astryxion.chaospersists.util.ArmorStats;
import com.astryxion.chaospersists.util.Trees;
import com.astryxion.chaospersists.world.biome.BiomeUtopia;
import com.astryxion.chaospersists.world.dimension.structure.BasiliskMaze;
import com.astryxion.chaospersists.world.dimension.structure.RubyBirdDungeon;
import com.astryxion.chaospersists.world.dimension.structure.GenericDungeon;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.world.ore.ChunkOreGenerator;
import com.astryxion.chaospersists.world.ore.OreGenericEgg;
import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.util.WeaponStats;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.OreStats;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.entity.RedCow;
import com.astryxion.chaospersists.world.ore.OreUranium;
import com.astryxion.chaospersists.world.ore.OreTitanium;
import com.astryxion.chaospersists.block.BlockUranium;
import com.astryxion.chaospersists.block.BlockTitanium;
import com.astryxion.chaospersists.block.BlockRuby;
import com.astryxion.chaospersists.block.BlockCrystal;
import com.astryxion.chaospersists.block.BlockPizza;
import com.astryxion.chaospersists.block.BlockDuctTape;
import com.astryxion.chaospersists.item.IngotUranium;
import com.astryxion.chaospersists.item.IngotTitanium;
import com.astryxion.chaospersists.block.Lavafoam;
import com.astryxion.chaospersists.item.ItemPizza;
import com.astryxion.chaospersists.item.ItemDuctTape;
import com.astryxion.chaospersists.item.UltimateSword;
import com.astryxion.chaospersists.item.UltimatePickaxe;
import com.astryxion.chaospersists.item.UltimateShovel;
import com.astryxion.chaospersists.item.UltimateHoe;
import com.astryxion.chaospersists.item.UltimateAxe;
import com.astryxion.chaospersists.item.UltimateBow;
import com.astryxion.chaospersists.item.NightmareSword;
import com.astryxion.chaospersists.item.PoisonSword;
import com.astryxion.chaospersists.item.RatSword;
import com.astryxion.chaospersists.item.FairySword;
import com.astryxion.chaospersists.item.MantisClaw;
import com.astryxion.chaospersists.item.BigHammer;
import com.astryxion.chaospersists.item.RubySword;
import com.astryxion.chaospersists.item.RubyPickaxe;
import com.astryxion.chaospersists.item.RubyShovel;
import com.astryxion.chaospersists.item.RubyHoe;
import com.astryxion.chaospersists.item.RubyAxe;
import com.astryxion.chaospersists.item.AmethystSword;
import com.astryxion.chaospersists.item.AmethystPickaxe;
import com.astryxion.chaospersists.item.AmethystShovel;
import com.astryxion.chaospersists.item.AmethystHoe;
import com.astryxion.chaospersists.item.AmethystAxe;
import com.astryxion.chaospersists.item.CrystalSword;
import com.astryxion.chaospersists.item.CrystalPickaxe;
import com.astryxion.chaospersists.item.CrystalShovel;
import com.astryxion.chaospersists.item.CrystalHoe;
import com.astryxion.chaospersists.item.CrystalAxe;
import com.astryxion.chaospersists.item.ExperienceSword;
import com.astryxion.chaospersists.item.ItemShoes;
import com.astryxion.chaospersists.entity.Bertha;
import com.astryxion.chaospersists.item.EmeraldSword;
import com.astryxion.chaospersists.item.EmeraldPickaxe;
import com.astryxion.chaospersists.item.EmeraldShovel;
import com.astryxion.chaospersists.item.EmeraldHoe;
import com.astryxion.chaospersists.item.EmeraldAxe;
import com.astryxion.chaospersists.item.SkateBow;
import com.astryxion.chaospersists.item.UltimateFishingRod;
import com.astryxion.chaospersists.item.ItemFireFish;
import com.astryxion.chaospersists.item.ItemSunFish;
import com.astryxion.chaospersists.item.ItemLavaEel;
import com.astryxion.chaospersists.item.ItemSalt;
import com.astryxion.chaospersists.item.ItemSpiderRobotKit;
import com.astryxion.chaospersists.item.ItemZooKeeper;
import com.astryxion.chaospersists.item.ItemCreeperLauncher;
import com.astryxion.chaospersists.item.ItemNetherLost;
import com.astryxion.chaospersists.item.ItemCrystalSticks;
import com.astryxion.chaospersists.item.ItemSunspotUrchin;
import com.astryxion.chaospersists.item.ItemSparkFish;
import com.astryxion.chaospersists.item.ItemWaterBall;
import com.astryxion.chaospersists.item.ItemLaserBall;
import com.astryxion.chaospersists.item.ItemIceBall;
import com.astryxion.chaospersists.item.ItemRock;
import com.astryxion.chaospersists.item.ItemRayGun;
import com.astryxion.chaospersists.item.ItemThunderStaff;
import com.astryxion.chaospersists.item.ItemWrench;
import com.astryxion.chaospersists.item.ItemAcid;
import com.astryxion.chaospersists.item.ItemIrukandji;
import com.astryxion.chaospersists.item.ItemIrukandjiArrow;
import com.astryxion.chaospersists.item.ItemGenericFish;
import com.astryxion.chaospersists.item.ItemSifter;
import com.astryxion.chaospersists.item.ItemSquidZooka;
import com.astryxion.chaospersists.item.ItemPopcorn;
import com.astryxion.chaospersists.item.ItemStrawberry;
import com.astryxion.chaospersists.item.ItemStrawberrySeed;
import com.astryxion.chaospersists.item.ItemButterflySeed;
import com.astryxion.chaospersists.item.ItemMothSeed;
import com.astryxion.chaospersists.item.ItemMosquitoSeed;
import com.astryxion.chaospersists.item.ItemFireflySeed;
import com.astryxion.chaospersists.item.ItemRadish;
import com.astryxion.chaospersists.item.ItemElevator;
import com.astryxion.chaospersists.item.ItemCornCob;
import com.astryxion.chaospersists.world.ore.OreSalt;
import com.astryxion.chaospersists.world.ore.OreRuby;
import com.astryxion.chaospersists.world.ore.OreAmethyst;
import com.astryxion.chaospersists.world.ore.OreBasicStone;
import com.astryxion.chaospersists.world.ore.OreCrystal;
import com.astryxion.chaospersists.world.ore.OreCrystalCrystal;
import com.astryxion.chaospersists.block.CrystalGrass;
import com.astryxion.chaospersists.block.CrystalWood;
import com.astryxion.chaospersists.block.CrystalWorkbench;
import com.astryxion.chaospersists.block.RTPBlock;
import com.astryxion.chaospersists.block.StepUp;
import com.astryxion.chaospersists.block.StepDown;
import com.astryxion.chaospersists.block.StepAccross;
import com.astryxion.chaospersists.block.MoleDirtBlock;
import com.astryxion.chaospersists.block.BlockStrawberry;
import com.astryxion.chaospersists.block.BlockButterflyPlant;
import com.astryxion.chaospersists.block.BlockMothPlant;
import com.astryxion.chaospersists.block.BlockMosquitoPlant;
import com.astryxion.chaospersists.block.BlockFireflyPlant;
import com.astryxion.chaospersists.block.BlockRadish;
import com.astryxion.chaospersists.block.BlockRice;
import com.astryxion.chaospersists.block.BlockCorn;
import com.astryxion.chaospersists.block.BlockQuinoa;
import com.astryxion.chaospersists.block.BlockTomato;
import com.astryxion.chaospersists.item.ItemTomato;
import com.astryxion.chaospersists.block.BlockLettuce;
import com.astryxion.chaospersists.item.ItemLettuce;
import com.astryxion.chaospersists.item.ItemMagicApple;
import com.astryxion.chaospersists.item.ItemMinersDream;
import com.astryxion.chaospersists.block.BlockExtremeTorch;
import com.astryxion.chaospersists.block.KrakenRepellent;
import com.astryxion.chaospersists.block.IslandBlock;
import com.astryxion.chaospersists.item.CreeperRepellent;
import com.astryxion.chaospersists.item.ZooCage;
import com.astryxion.chaospersists.item.InstantShelter;
import com.astryxion.chaospersists.item.InstantGarden;
import com.astryxion.chaospersists.block.BlockCrystalTorch;
import com.astryxion.chaospersists.block.KingSpawnerBlock;
import com.astryxion.chaospersists.block.QueenSpawnerBlock;
import com.astryxion.chaospersists.item.ItemRandomDungeon;
import com.astryxion.chaospersists.block.DungeonSpawnerBlock;
import com.astryxion.chaospersists.block.BlockAppleLeaves;
import com.astryxion.chaospersists.item.ItemAppleSeed;
import com.astryxion.chaospersists.block.BlockSkyTreeLog;
import com.astryxion.chaospersists.block.BlockDuplicatorLog;
import com.astryxion.chaospersists.block.BlockExperienceLeaves;
import com.astryxion.chaospersists.item.ExperienceCatcher;
import com.astryxion.chaospersists.item.ItemExperienceTreeSeed;
import com.astryxion.chaospersists.block.BlockExperiencePlant;
import com.astryxion.chaospersists.util.MyBlockFlower;
import com.astryxion.chaospersists.block.BlockScaryLeaves;
import com.astryxion.chaospersists.block.BlockCrystalLeaves;
import com.astryxion.chaospersists.block.BlockCrystalTreeLog;
import com.astryxion.chaospersists.block.BlockCrystalPlant;
import com.astryxion.chaospersists.item.UltimateFishHook;
import com.astryxion.chaospersists.entity.SunspotUrchin;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.item.InkSack;
import com.astryxion.chaospersists.item.LaserBall;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.item.Acid;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.entity.DeadIrukandji;
import com.astryxion.chaospersists.entity.BerthaHit;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.entity.EntityThrownRock;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.GoldCow;
import com.astryxion.chaospersists.entity.EnchantedCow;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import com.astryxion.chaospersists.entity.EntityMosquito;
import com.astryxion.chaospersists.entity.Firefly;
import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.EntityRedAnt;
import com.astryxion.chaospersists.entity.EntityRainbowAnt;
import com.astryxion.chaospersists.entity.EntityUnstableAnt;
import com.astryxion.chaospersists.entity.Robot1;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.Camarasaurus;
import com.astryxion.chaospersists.entity.Hydrolisc;
import com.astryxion.chaospersists.entity.VelocityRaptor;
import com.astryxion.chaospersists.entity.Dragonfly;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.Scorpion;
import com.astryxion.chaospersists.entity.CaveFisher;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.Baryonyx;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.entity.RubyBird;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Lizard;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.Gazelle;
import com.astryxion.chaospersists.entity.Ostrich;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.StinkBug;
import com.astryxion.chaospersists.item.Tshirt;
import com.astryxion.chaospersists.entity.Island;
import com.astryxion.chaospersists.entity.IslandToo;
import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.CliffRacer;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.entity.WormSmall;
import com.astryxion.chaospersists.entity.WormMedium;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.entity.Cassowary;
import com.astryxion.chaospersists.entity.ChaosSpawnPlacements;
import com.astryxion.chaospersists.entity.CloudShark;
import com.astryxion.chaospersists.entity.GoldFish;
import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.entity.GodzillaHead;
import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.entity.Beaver;
import com.astryxion.chaospersists.entity.Termite;
import com.astryxion.chaospersists.entity.Fairy;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.Rat;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.entity.Whale;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.Stinky;
import com.astryxion.chaospersists.item.Coin;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.KingHead;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.QueenHead;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.EasterBunny;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.RubberDucky;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.item.BandP;
import com.astryxion.chaospersists.entity.RockBase;
import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.Pointysaurus;
import com.astryxion.chaospersists.entity.Cricket;
import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.entity.Frog;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.SpiderDriver;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.item.Shoes;
import com.astryxion.chaospersists.entity.EntityCage;
import com.astryxion.chaospersists.item.UltimateArrow;
import com.astryxion.chaospersists.item.IrukandjiArrow;
import com.astryxion.chaospersists.tileentity.TileEntityCrystalFurnace;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.item.CritterCage;
import com.astryxion.chaospersists.item.ItemSpawnEgg;
import com.astryxion.chaospersists.util.DispenserBehaviorChaosEgg;
import com.astryxion.chaospersists.util.MyDispenserBehaviorArrow;
import com.astryxion.chaospersists.util.MyDispenserBehaviorWDCharge;
import com.astryxion.chaospersists.util.MyDispenserBehaviorSunspotUrchin;
import com.astryxion.chaospersists.util.MyDispenserBehaviorAcid;
import com.astryxion.chaospersists.util.MyDispenserBehaviorIceball;
import com.astryxion.chaospersists.util.MyDispenserBehaviorDeadIrukandji;
import com.astryxion.chaospersists.util.MyDispenserBehaviorLaserball;
import com.astryxion.chaospersists.util.MyDispenserBehaviorRock;
import com.astryxion.chaospersists.command.CommandChaos;
import com.astryxion.chaospersists.command.CommandChaosStructures;
import com.astryxion.chaospersists.command.CommandCrystal;
import com.astryxion.chaospersists.command.CommandDanger;
import com.astryxion.chaospersists.command.CommandMining;
import com.astryxion.chaospersists.command.CommandOverworld;
import com.astryxion.chaospersists.command.CommandUtopia;
import com.astryxion.chaospersists.command.CommandVillageMania;
import com.astryxion.chaospersists.block.AntBlock;
import com.astryxion.chaospersists.block.CrystalAntBlock;
import com.astryxion.chaospersists.item.ThunderBolt;

@Mod(ChaosPersists.MODID)
public class ChaosPersists
{
  private static final org.apache.logging.log4j.Logger LOGGER =
      org.apache.logging.log4j.LogManager.getLogger(ChaosPersists.class);

  public static final String MODID = "chaospersists";

  private static ItemStack creativeTabIconItem(String itemPath, Item fallback) {
    Item item = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, itemPath));
    return new ItemStack(item != null && item != Items.AIR ? item : fallback);
  }

  private static ItemStack creativeTabIconBlock(String blockPath, Item fallback) {
    Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, blockPath));
    return block != null ? new ItemStack(block) : new ItemStack(fallback);
  }

  /** Keys for {@link CreativeModeTab.Builder#withTabsBefore} — matches 1.12 tab page layout. */
  private static ResourceKey<CreativeModeTab> chaosTabKey(String path) {
    return ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MODID, path));
  }

  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
      DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
      DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
  public static final DeferredRegister<MenuType<?>> MENU_TYPES =
      DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
  public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
      DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
  public static final RegistryObject<Codec<BiomeMiningDimension.MiningDimensionAddSpawnsBiomeModifier>>
      ADD_MINING_DIMENSION_SPAWNS =
          BIOME_MODIFIER_SERIALIZERS.register(
                  "add_mining_dimension_spawns", BiomeMiningDimension::makeCodec);
  public static final RegistryObject<Codec<com.astryxion.chaospersists.world.biome.LegacyOverworldSpawnBiomeModifier>>
      LEGACY_OVERWORLD_SPAWNS =
          BIOME_MODIFIER_SERIALIZERS.register(
                  "legacy_overworld_spawns",
                  com.astryxion.chaospersists.world.biome.LegacyOverworldSpawnBiomeModifier::makeCodec);

  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_ITEMS =
      CREATIVE_MODE_TABS.register(
          "chaos_items",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_items"))
                  .icon(() -> creativeTabIconItem("minersdream", Items.AIR))
                  .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                  .build());
  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_BLOCKS =
      CREATIVE_MODE_TABS.register(
          "chaos_blocks",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_blocks"))
                  .icon(() -> creativeTabIconBlock("antblock", Items.STONE))
                  .withTabsBefore(chaosTabKey("chaos_items"))
                  .build());
  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_FOODS =
      CREATIVE_MODE_TABS.register(
          "chaos_foods",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_foods"))
                  .icon(() -> creativeTabIconItem("corn_seed", Items.BREAD))
                  .withTabsBefore(chaosTabKey("chaos_blocks"))
                  .build());
  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_TOOLS =
      CREATIVE_MODE_TABS.register(
          "chaos_tools",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_tools"))
                  .icon(() -> creativeTabIconItem("ultimatepickaxe", Items.IRON_PICKAXE))
                  .withTabsBefore(chaosTabKey("chaos_foods"))
                  .build());
  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_WEAPONS =
      CREATIVE_MODE_TABS.register(
          "chaos_weapons",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_weapons"))
                  .icon(() -> creativeTabIconItem("ultimatesword", Items.IRON_SWORD))
                  .withTabsBefore(chaosTabKey("chaos_tools"))
                  .build());
  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_MOBS =
      CREATIVE_MODE_TABS.register(
          "chaos_mobs",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_mobs"))
                  .icon(() -> creativeTabIconItem("eggtheking", Items.EGG))
                  .withTabsBefore(chaosTabKey("chaos_weapons"))
                  .build());
  public static final RegistryObject<CreativeModeTab> TAB_CHAOS_ARMOR =
      CREATIVE_MODE_TABS.register(
          "chaos_armor",
          () ->
              CreativeModeTab.builder()
                  .title(Component.translatable("itemGroup.chaospersists.chaos_armor"))
                  .icon(() -> creativeTabIconItem("royal_chest", Items.IRON_CHESTPLATE))
                  .withTabsBefore(chaosTabKey("chaos_mobs"))
                  .build());

  public static final RegistryObject<MenuType<ContainerCrystalWorkbench>> MENU_CRYSTAL_WORKBENCH =
      MENU_TYPES.register(
          "crystal_workbench",
          () -> IForgeMenuType.create(ContainerCrystalWorkbench::new));

  public static final RegistryObject<BlockEntityType<TileEntityCrystalFurnace>> BLOCK_ENTITY_CRYSTAL_FURNACE =
      BLOCK_ENTITY_TYPES.register(
          "crystalfurnace",
          () ->
              BlockEntityType.Builder.of(
                      TileEntityCrystalFurnace::new,
                      BuiltInRegistries.BLOCK
                          .getOptional(ResourceLocation.fromNamespaceAndPath(MODID, "crystalfurnace"))
                          .orElse(Blocks.FURNACE))
                  .build(null));

  static {
    raiseVanillaMaxHealthCap();
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    BLOCKS.register(modBus);
    ITEMS.register(modBus);
    ENTITY_TYPES.register(modBus);
    BLOCK_ENTITY_TYPES.register(modBus);
    MENU_TYPES.register(modBus);
    CREATIVE_MODE_TABS.register(modBus);
    BIOME_MODIFIER_SERIALIZERS.register(modBus);
    com.astryxion.chaospersists.world.dimension.structure.ChaosLocateStructures.STRUCTURE_TYPES.register(modBus);
    com.astryxion.chaospersists.world.dimension.structure.ChaosLocateStructures.STRUCTURE_PIECES.register(modBus);
    registerAllCritterCages();
    registerAllWeaponsAndArmor();
    registerAllFoodItems();
    registerAllSaltMaterialItems();
    registerAllSpawnEggs();
    registerAllPreInitBlocks();
    registerAllPreInitItems();
  }

  public static final RegistryObject<EntityType<UltimateFishHook>> ENTITY_TYPE_ULTIMATE_FISH_HOOK = ENTITY_TYPES.register("ultimate_fish_hook",
      () -> EntityType.Builder.<UltimateFishHook>of(UltimateFishHook::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("ultimate_fish_hook"));
  public static final RegistryObject<EntityType<SunspotUrchin>> ENTITY_TYPE_SUNSPOT_URCHIN = ENTITY_TYPES.register("sunspot_urchin",
      () -> EntityType.Builder.<SunspotUrchin>of(SunspotUrchin::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("sunspot_urchin"));
  public static final RegistryObject<EntityType<WaterBall>> ENTITY_TYPE_WATER_BALL = ENTITY_TYPES.register("water_ball",
      () -> EntityType.Builder.<WaterBall>of(WaterBall::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("water_ball"));
  public static final RegistryObject<EntityType<InkSack>> ENTITY_TYPE_INK_SACK = ENTITY_TYPES.register("ink_sack",
      () -> EntityType.Builder.<InkSack>of(InkSack::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("ink_sack"));
  public static final RegistryObject<EntityType<LaserBall>> ENTITY_TYPE_LASER_BALL = ENTITY_TYPES.register("laser_ball",
      () -> EntityType.Builder.<LaserBall>of(LaserBall::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("laser_ball"));
  public static final RegistryObject<EntityType<IceBall>> ENTITY_TYPE_ICE_BALL = ENTITY_TYPES.register("ice_ball",
      () -> EntityType.Builder.<IceBall>of(IceBall::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("ice_ball"));
  public static final RegistryObject<EntityType<Acid>> ENTITY_TYPE_ACID = ENTITY_TYPES.register("acid",
      () -> EntityType.Builder.<Acid>of(Acid::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("acid"));
  public static final RegistryObject<EntityType<BetterFireball>> ENTITY_TYPE_BETTER_FIREBALL = ENTITY_TYPES.register("better_fireball",
      () -> EntityType.Builder.<BetterFireball>of(BetterFireball::new, MobCategory.MISC).sized(1.0f, 1.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("better_fireball"));
  public static final RegistryObject<EntityType<DeadIrukandji>> ENTITY_TYPE_DEAD_IRUKANDJI = ENTITY_TYPES.register("dead_irukandji",
      () -> EntityType.Builder.<DeadIrukandji>of(DeadIrukandji::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("dead_irukandji"));
  public static final RegistryObject<EntityType<BerthaHit>> ENTITY_TYPE_BERTHA_HIT = ENTITY_TYPES.register("bertha_hit",
      () -> EntityType.Builder.<BerthaHit>of(BerthaHit::new, MobCategory.MISC).sized(0.33f, 0.33f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("bertha_hit"));
  public static final RegistryObject<EntityType<PurplePower>> ENTITY_TYPE_PURPLE_POWER = ENTITY_TYPES.register("purple_power",
      () -> EntityType.Builder.<PurplePower>of(PurplePower::new, MobCategory.MISC).sized(0.75f, 0.75f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("purple_power"));
  public static final RegistryObject<EntityType<EntityThrownRock>> ENTITY_TYPE_THROWN_ROCK = ENTITY_TYPES.register("thrown_rock",
      () -> EntityType.Builder.<EntityThrownRock>of(EntityThrownRock::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("thrown_rock"));
  public static final RegistryObject<EntityType<ThunderBolt>> ENTITY_TYPE_THUNDER_BOLT = ENTITY_TYPES.register("thunder_bolt",
      () -> EntityType.Builder.<ThunderBolt>of(ThunderBolt::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("thunder_bolt"));
  public static final RegistryObject<EntityType<Girlfriend>> ENTITY_TYPE_GIRLFRIEND = ENTITY_TYPES.register("girlfriend",
      () -> EntityType.Builder.<Girlfriend>of(Girlfriend::new, MobCategory.CREATURE).sized(0.6f, 1.4f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("girlfriend"));
  public static final RegistryObject<EntityType<RedCow>> ENTITY_TYPE_RED_COW = ENTITY_TYPES.register("apple_cow",
      () -> EntityType.Builder.<RedCow>of(RedCow::new, MobCategory.CREATURE).sized(0.6f, 1.4f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("apple_cow"));
  public static final RegistryObject<EntityType<GoldCow>> ENTITY_TYPE_GOLD_COW = ENTITY_TYPES.register("golden_apple_cow",
      () -> EntityType.Builder.<GoldCow>of(GoldCow::new, MobCategory.CREATURE).sized(0.6f, 1.4f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("golden_apple_cow"));
  public static final RegistryObject<EntityType<EnchantedCow>> ENTITY_TYPE_ENCHANTED_COW = ENTITY_TYPES.register("enchanted_golden_apple_cow",
      () -> EntityType.Builder.<EnchantedCow>of(EnchantedCow::new, MobCategory.CREATURE).sized(0.6f, 1.4f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("enchanted_golden_apple_cow"));
  public static final RegistryObject<EntityType<EntityButterfly>> ENTITY_TYPE_BUTTERFLY = ENTITY_TYPES.register("butterfly",
      () -> EntityType.Builder.<EntityButterfly>of(EntityButterfly::new, MobCategory.AMBIENT).sized(0.4f, 0.4f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("butterfly"));
  public static final RegistryObject<EntityType<EntityLunaMoth>> ENTITY_TYPE_MOTH = ENTITY_TYPES.register("moth",
      () -> EntityType.Builder.<EntityLunaMoth>of(EntityLunaMoth::new, MobCategory.AMBIENT).sized(0.5f, 0.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("moth"));
  public static final RegistryObject<EntityType<EntityMosquito>> ENTITY_TYPE_MOSQUITO = ENTITY_TYPES.register("mosquito",
      () -> EntityType.Builder.<EntityMosquito>of(EntityMosquito::new, MobCategory.AMBIENT).sized(0.2f, 0.2f).clientTrackingRange(16).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("mosquito"));
  public static final RegistryObject<EntityType<Firefly>> ENTITY_TYPE_FIREFLY = ENTITY_TYPES.register("firefly",
      () -> EntityType.Builder.<Firefly>of(Firefly::new, MobCategory.AMBIENT).sized(0.4f, 0.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("firefly"));
  public static final RegistryObject<EntityType<Bee>> ENTITY_TYPE_BEE = ENTITY_TYPES.register("bee",
      () -> EntityType.Builder.<Bee>of(Bee::new, MobCategory.MONSTER).sized(1.5f, 2.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("bee"));
  public static final RegistryObject<EntityType<Mothra>> ENTITY_TYPE_MOTHRA = ENTITY_TYPES.register("mothra",
      () -> EntityType.Builder.<Mothra>of(Mothra::new, MobCategory.MONSTER).sized(5.0f, 2.0f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("mothra"));
  public static final RegistryObject<EntityType<EntityAnt>> ENTITY_TYPE_ANT = ENTITY_TYPES.register("ant",
      () -> EntityType.Builder.<EntityAnt>of(EntityAnt::new, MobCategory.MONSTER).sized(0.1f, 0.1f).clientTrackingRange(16).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("ant"));
  public static final RegistryObject<EntityType<EntityRedAnt>> ENTITY_TYPE_RED_ANT = ENTITY_TYPES.register("red_ant",
      () -> EntityType.Builder.<EntityRedAnt>of(EntityRedAnt::new, MobCategory.MONSTER).sized(0.2f, 0.2f).clientTrackingRange(16).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("red_ant"));
  public static final RegistryObject<EntityType<EntityRainbowAnt>> ENTITY_TYPE_RAINBOW_ANT = ENTITY_TYPES.register("rainbow_ant",
      () -> EntityType.Builder.<EntityRainbowAnt>of(EntityRainbowAnt::new, MobCategory.MONSTER).sized(0.1f, 0.1f).clientTrackingRange(16).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("rainbow_ant"));
  public static final RegistryObject<EntityType<EntityUnstableAnt>> ENTITY_TYPE_UNSTABLE_ANT = ENTITY_TYPES.register("unstable_ant",
      () -> EntityType.Builder.<EntityUnstableAnt>of(EntityUnstableAnt::new, MobCategory.MONSTER).sized(0.1f, 0.1f).clientTrackingRange(16).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("unstable_ant"));
  public static final RegistryObject<EntityType<Robot1>> ENTITY_TYPE_ROBOT1 = ENTITY_TYPES.register("bomb_omb",
      () -> EntityType.Builder.<Robot1>of(Robot1::new, MobCategory.MONSTER).sized(0.5f, 0.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("bomb_omb"));
  public static final RegistryObject<EntityType<Robot2>> ENTITY_TYPE_ROBOT2 = ENTITY_TYPES.register("robo_pounder",
      () -> EntityType.Builder.<Robot2>of(Robot2::new, MobCategory.MONSTER).sized(3.0f, 6.2f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("robo_pounder"));
  public static final RegistryObject<EntityType<Robot3>> ENTITY_TYPE_ROBOT3 = ENTITY_TYPES.register("robo_gunner",
      () -> EntityType.Builder.<Robot3>of(Robot3::new, MobCategory.MONSTER).sized(2.5f, 5.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("robo_gunner"));
  public static final RegistryObject<EntityType<Robot4>> ENTITY_TYPE_ROBOT4 = ENTITY_TYPES.register("robo_warrior",
      () -> EntityType.Builder.<Robot4>of(Robot4::new, MobCategory.MONSTER).sized(2.5f, 4.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("robo_warrior"));
  public static final RegistryObject<EntityType<Robot5>> ENTITY_TYPE_ROBOT5 = ENTITY_TYPES.register("robo_sniper",
      () -> EntityType.Builder.<Robot5>of(Robot5::new, MobCategory.MONSTER).sized(1.0f, 2.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("robo_sniper"));
  public static final RegistryObject<EntityType<Alosaurus>> ENTITY_TYPE_ALOSAURUS = ENTITY_TYPES.register("alosaurus",
      () -> EntityType.Builder.<Alosaurus>of(Alosaurus::new, MobCategory.MONSTER).sized(1.9f, 3.6f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("alosaurus"));
  public static final RegistryObject<EntityType<Cryolophosaurus>> ENTITY_TYPE_CRYOLOPHOSAURUS = ENTITY_TYPES.register("cryolophosaurus",
      () -> EntityType.Builder.<Cryolophosaurus>of(Cryolophosaurus::new, MobCategory.MONSTER).sized(0.75f, 0.75f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("cryolophosaurus"));
  public static final RegistryObject<EntityType<Basilisk>> ENTITY_TYPE_BASILISK = ENTITY_TYPES.register("basilisk",
      () -> EntityType.Builder.<Basilisk>of(Basilisk::new, MobCategory.MONSTER).sized(1.6f, 3.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("basilisk"));
  public static final RegistryObject<EntityType<Camarasaurus>> ENTITY_TYPE_CAMARASAURUS = ENTITY_TYPES.register("camarasaurus",
      () -> EntityType.Builder.<Camarasaurus>of(Camarasaurus::new, MobCategory.MONSTER).sized(0.5f, 1.2f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("camarasaurus"));
  public static final RegistryObject<EntityType<Hydrolisc>> ENTITY_TYPE_HYDROLISC = ENTITY_TYPES.register("hydrolisc",
      () -> EntityType.Builder.<Hydrolisc>of(Hydrolisc::new, MobCategory.MONSTER).sized(0.5f, 0.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("hydrolisc"));
  public static final RegistryObject<EntityType<VelocityRaptor>> ENTITY_TYPE_VELOCITY_RAPTOR = ENTITY_TYPES.register("velocity_raptor",
      () -> EntityType.Builder.<VelocityRaptor>of(VelocityRaptor::new, MobCategory.MONSTER).sized(0.5f, 0.6f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("velocity_raptor"));
  public static final RegistryObject<EntityType<Dragonfly>> ENTITY_TYPE_DRAGONFLY = ENTITY_TYPES.register("dragonfly",
      () -> EntityType.Builder.<Dragonfly>of(Dragonfly::new, MobCategory.AMBIENT).sized(1.5f, 0.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("dragonfly"));
  public static final RegistryObject<EntityType<EmperorScorpion>> ENTITY_TYPE_EMPEROR_SCORPION = ENTITY_TYPES.register("emperor_scorpion",
      () -> EntityType.Builder.<EmperorScorpion>of(EmperorScorpion::new, MobCategory.MONSTER).sized(3.5f, 3.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("emperor_scorpion"));
  public static final RegistryObject<EntityType<Scorpion>> ENTITY_TYPE_SCORPION = ENTITY_TYPES.register("scorpion",
      () -> EntityType.Builder.<Scorpion>of(Scorpion::new, MobCategory.MONSTER).sized(0.85f, 0.55f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("scorpion"));
  public static final RegistryObject<EntityType<CaveFisher>> ENTITY_TYPE_CAVE_FISHER = ENTITY_TYPES.register("cave_fisher",
      () -> EntityType.Builder.<CaveFisher>of(CaveFisher::new, MobCategory.MONSTER).sized(1.35f, 0.75f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("cave_fisher"));
  public static final RegistryObject<EntityType<Spyro>> ENTITY_TYPE_BABY_DRAGON = ENTITY_TYPES.register("baby_dragon",
      () -> EntityType.Builder.<Spyro>of(Spyro::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("baby_dragon"));
  public static final RegistryObject<EntityType<Baryonyx>> ENTITY_TYPE_BARYONYX = ENTITY_TYPES.register("baryonyx",
      () -> EntityType.Builder.<Baryonyx>of(Baryonyx::new, MobCategory.CREATURE).sized(1.5f, 2.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("baryonyx"));
  public static final RegistryObject<EntityType<GammaMetroid>> ENTITY_TYPE_GAMMA_METROID = ENTITY_TYPES.register("gamma_metroid",
      () -> EntityType.Builder.<GammaMetroid>of(GammaMetroid::new, MobCategory.MONSTER).sized(1.5f, 1.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("gamma_metroid"));
  public static final RegistryObject<EntityType<GammaMetroid>> ENTITY_TYPE_WTF = ENTITY_TYPES.register("wtf",
      () -> EntityType.Builder.<GammaMetroid>of(GammaMetroid::new, MobCategory.MONSTER).sized(1.5f, 1.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("wtf"));
  public static final RegistryObject<EntityType<Cockateil>> ENTITY_TYPE_BIRD = ENTITY_TYPES.register("bird",
      () -> EntityType.Builder.<Cockateil>of(Cockateil::new, MobCategory.CREATURE).sized(0.5f, 0.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("bird"));
  public static final RegistryObject<EntityType<RubyBird>> ENTITY_TYPE_RUBY_BIRD = ENTITY_TYPES.register("ruby_bird",
      () -> EntityType.Builder.<RubyBird>of(RubyBird::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("ruby_bird"));
  public static final RegistryObject<EntityType<Kyuubi>> ENTITY_TYPE_KYUUBI = ENTITY_TYPES.register("kyuubi",
      () -> EntityType.Builder.<Kyuubi>of(Kyuubi::new, MobCategory.MONSTER).sized(0.5f, 1.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("kyuubi"));
  public static final RegistryObject<EntityType<WaterDragon>> ENTITY_TYPE_WATER_DRAGON = ENTITY_TYPES.register("water_dragon",
      () -> EntityType.Builder.<WaterDragon>of(WaterDragon::new, MobCategory.MONSTER).sized(1.25f, 1.9f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("water_dragon"));
  public static final RegistryObject<EntityType<AttackSquid>> ENTITY_TYPE_ATTACK_SQUID = ENTITY_TYPES.register("attack_squid",
      () -> EntityType.Builder.<AttackSquid>of(AttackSquid::new, MobCategory.MONSTER).sized(1.0f, 1.25f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("attack_squid"));
  public static final RegistryObject<EntityType<Alien>> ENTITY_TYPE_ALIEN = ENTITY_TYPES.register("alien",
      () -> EntityType.Builder.<Alien>of(Alien::new, MobCategory.MONSTER).sized(1.1f, 3.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("alien"));
  public static final RegistryObject<EntityType<Elevator>> ENTITY_TYPE_ELEVATOR = ENTITY_TYPES.register("hoverboard",
      () -> EntityType.Builder.<Elevator>of(Elevator::new, MobCategory.MISC).sized(1.25f, 1.0f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("hoverboard"));
  public static final RegistryObject<EntityType<Kraken>> ENTITY_TYPE_THE_KRAKEN = ENTITY_TYPES.register("the_kraken",
      () -> EntityType.Builder.<Kraken>of(Kraken::new, MobCategory.MONSTER).sized(2f, 2f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_kraken"));
  public static final RegistryObject<EntityType<Lizard>> ENTITY_TYPE_LIZARD = ENTITY_TYPES.register("lizard",
      () -> EntityType.Builder.<Lizard>of(Lizard::new, MobCategory.MONSTER).sized(1.5f, 1.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("lizard"));
  public static final RegistryObject<EntityType<Cephadrome>> ENTITY_TYPE_CEPHADROME = ENTITY_TYPES.register("cephadrome",
      () -> EntityType.Builder.<Cephadrome>of(Cephadrome::new, MobCategory.MISC).sized(2.5f, 2.25f).clientTrackingRange(128).updateInterval(3).setShouldReceiveVelocityUpdates(true).build("cephadrome"));
  public static final RegistryObject<EntityType<Dragon>> ENTITY_TYPE_DRAGON = ENTITY_TYPES.register("dragon",
      () -> EntityType.Builder.<Dragon>of(Dragon::new, MobCategory.CREATURE).sized(1.5f, 1.25f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("dragon"));
  public static final RegistryObject<EntityType<Chipmunk>> ENTITY_TYPE_CHIPMUNK = ENTITY_TYPES.register("chipmunk",
      () -> EntityType.Builder.<Chipmunk>of(Chipmunk::new, MobCategory.CREATURE).sized(0.35f, 0.35f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("chipmunk"));
  public static final RegistryObject<EntityType<Gazelle>> ENTITY_TYPE_GAZELLE = ENTITY_TYPES.register("gazelle",
      () -> EntityType.Builder.<Gazelle>of(Gazelle::new, MobCategory.CREATURE).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("gazelle"));
  public static final RegistryObject<EntityType<Ostrich>> ENTITY_TYPE_OSTRICH = ENTITY_TYPES.register("ostrich",
      () -> EntityType.Builder.<Ostrich>of(Ostrich::new, MobCategory.CREATURE).sized(0.85f, 2.1f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("ostrich"));
  public static final RegistryObject<EntityType<TrooperBug>> ENTITY_TYPE_TROOPER_BUG = ENTITY_TYPES.register("jumpy_bug",
      () -> EntityType.Builder.<TrooperBug>of(TrooperBug::new, MobCategory.MONSTER).sized(3.0f, 3.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("jumpy_bug"));
  public static final RegistryObject<EntityType<SpitBug>> ENTITY_TYPE_SPIT_BUG = ENTITY_TYPES.register("spit_bug",
      () -> EntityType.Builder.<SpitBug>of(SpitBug::new, MobCategory.MONSTER).sized(2.0f, 2.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("spit_bug"));
  public static final RegistryObject<EntityType<StinkBug>> ENTITY_TYPE_STINK_BUG = ENTITY_TYPES.register("stink_bug",
      () -> EntityType.Builder.<StinkBug>of(StinkBug::new, MobCategory.CREATURE).sized(0.55f, 0.55f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("stink_bug"));
  public static final RegistryObject<EntityType<Tshirt>> ENTITY_TYPE_TSHIRT = ENTITY_TYPES.register("tshirt",
      () -> EntityType.Builder.<Tshirt>of(Tshirt::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("tshirt"));
  public static final RegistryObject<EntityType<Island>> ENTITY_TYPE_ISLAND = ENTITY_TYPES.register("island",
      () -> EntityType.Builder.<Island>of(Island::new, MobCategory.MONSTER).sized(0.5f, 0.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("island"));
  public static final RegistryObject<EntityType<IslandToo>> ENTITY_TYPE_ISLAND_TOO = ENTITY_TYPES.register("island_too",
      () -> EntityType.Builder.<IslandToo>of(IslandToo::new, MobCategory.MONSTER).sized(0.5f, 0.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("island_too"));
  public static final RegistryObject<EntityType<CreepingHorror>> ENTITY_TYPE_CREEPING_HORROR = ENTITY_TYPES.register("creeping_horror",
      () -> EntityType.Builder.<CreepingHorror>of(CreepingHorror::new, MobCategory.MONSTER).sized(0.75f, 0.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("creeping_horror"));
  public static final RegistryObject<EntityType<TerribleTerror>> ENTITY_TYPE_TERRIBLE_TERROR = ENTITY_TYPES.register("terrible_terror",
      () -> EntityType.Builder.<TerribleTerror>of(TerribleTerror::new, MobCategory.MONSTER).sized(1.0f, 0.75f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("terrible_terror"));
  public static final RegistryObject<EntityType<CliffRacer>> ENTITY_TYPE_CLIFF_RACER = ENTITY_TYPES.register("cliff_racer",
      () -> EntityType.Builder.<CliffRacer>of(CliffRacer::new, MobCategory.AMBIENT).sized(0.75f, 0.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("cliff_racer"));
  public static final RegistryObject<EntityType<Triffid>> ENTITY_TYPE_TRIFFID = ENTITY_TYPES.register("triffid",
      () -> EntityType.Builder.<Triffid>of(Triffid::new, MobCategory.MONSTER).sized(2.0f, 4.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("triffid"));
  public static final RegistryObject<EntityType<PitchBlack>> ENTITY_TYPE_NIGHTMARE = ENTITY_TYPES.register("nightmare",
      () -> EntityType.Builder.<PitchBlack>of(PitchBlack::new, MobCategory.MONSTER).sized(2.5f, 3.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("nightmare"));
  public static final RegistryObject<EntityType<LurkingTerror>> ENTITY_TYPE_LURKING_TERROR = ENTITY_TYPES.register("lurking_terror",
      () -> EntityType.Builder.<LurkingTerror>of(LurkingTerror::new, MobCategory.MONSTER).sized(1.75f, 1.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("lurking_terror"));
  public static final RegistryObject<EntityType<Godzilla>> ENTITY_TYPE_MOBZILLA = ENTITY_TYPES.register("mobzilla",
      () -> EntityType.Builder.<Godzilla>of(Godzilla::new, MobCategory.MONSTER).sized(2f, 2f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("mobzilla"));
  public static final RegistryObject<EntityType<Ghost>> ENTITY_TYPE_GHOST = ENTITY_TYPES.register("ghost",
      () -> EntityType.Builder.<Ghost>of(Ghost::new, MobCategory.AMBIENT).sized(0.5f, 1.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("ghost"));
  public static final RegistryObject<EntityType<GhostSkelly>> ENTITY_TYPE_GHOST_PUMPKIN_SKELLY = ENTITY_TYPES.register("ghost_pumpkin_skelly",
      () -> EntityType.Builder.<GhostSkelly>of(GhostSkelly::new, MobCategory.AMBIENT).sized(1.5f, 2.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("ghost_pumpkin_skelly"));
  public static final RegistryObject<EntityType<WormSmall>> ENTITY_TYPE_SMALL_WORM = ENTITY_TYPES.register("small_worm",
      () -> EntityType.Builder.<WormSmall>of(WormSmall::new, MobCategory.MONSTER).sized(0.25f, 1.0f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("small_worm"));
  public static final RegistryObject<EntityType<WormMedium>> ENTITY_TYPE_MEDIUM_WORM = ENTITY_TYPES.register("medium_worm",
      () -> EntityType.Builder.<WormMedium>of(WormMedium::new, MobCategory.MONSTER).sized(0.5f, 2.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("medium_worm"));
  public static final RegistryObject<EntityType<WormLarge>> ENTITY_TYPE_LARGE_WORM = ENTITY_TYPES.register("large_worm",
      () -> EntityType.Builder.<WormLarge>of(WormLarge::new, MobCategory.MONSTER).sized(1.55f, 2.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("large_worm"));
  public static final RegistryObject<EntityType<Cassowary>> ENTITY_TYPE_CASSOWARY = ENTITY_TYPES.register("cassowary",
      () -> EntityType.Builder.<Cassowary>of(Cassowary::new, MobCategory.CREATURE).sized(0.5f, 1.2f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("cassowary"));
  public static final RegistryObject<EntityType<CloudShark>> ENTITY_TYPE_CLOUD_SHARK = ENTITY_TYPES.register("cloud_shark",
      () -> EntityType.Builder.<CloudShark>of(CloudShark::new, MobCategory.MONSTER).sized(1.0f, 0.75f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("cloud_shark"));
  public static final RegistryObject<EntityType<GoldFish>> ENTITY_TYPE_GOLD_FISH = ENTITY_TYPES.register("gold_fish",
      () -> EntityType.Builder.<GoldFish>of(GoldFish::new, MobCategory.CREATURE).sized(0.75f, 0.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("gold_fish"));
  public static final RegistryObject<EntityType<LeafMonster>> ENTITY_TYPE_LEAF_MONSTER = ENTITY_TYPES.register("leaf_monster",
      () -> EntityType.Builder.<LeafMonster>of(LeafMonster::new, MobCategory.MONSTER).sized(1.0f, 2.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("leaf_monster"));
  public static final RegistryObject<EntityType<GodzillaHead>> ENTITY_TYPE_MOBZILLA_HEAD = ENTITY_TYPES.register("mobzilla_head",
      () -> EntityType.Builder.<GodzillaHead>of(GodzillaHead::new, MobCategory.MISC).sized(9.9f, 10.0f).clientTrackingRange(128).updateInterval(10).setShouldReceiveVelocityUpdates(false).build("mobzilla_head"));
  public static final RegistryObject<EntityType<EnderKnight>> ENTITY_TYPE_ENDER_KNIGHT = ENTITY_TYPES.register("ender_knight",
      () -> EntityType.Builder.<EnderKnight>of(EnderKnight::new, MobCategory.MONSTER).sized(0.6f, 2.9f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("ender_knight"));
  public static final RegistryObject<EntityType<EnderReaper>> ENTITY_TYPE_ENDER_REAPER = ENTITY_TYPES.register("ender_reaper",
      () -> EntityType.Builder.<EnderReaper>of(EnderReaper::new, MobCategory.MONSTER).sized(0.7f, 2.9f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("ender_reaper"));
  public static final RegistryObject<EntityType<Beaver>> ENTITY_TYPE_BEAVER = ENTITY_TYPES.register("beaver",
      () -> EntityType.Builder.<Beaver>of(Beaver::new, MobCategory.CREATURE).sized(0.6f, 0.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("beaver"));
  public static final RegistryObject<EntityType<Termite>> ENTITY_TYPE_TERMITE = ENTITY_TYPES.register("termite",
      () -> EntityType.Builder.<Termite>of(Termite::new, MobCategory.MONSTER).sized(0.2f, 0.2f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("termite"));
  public static final RegistryObject<EntityType<Fairy>> ENTITY_TYPE_FAIRY = ENTITY_TYPES.register("fairy",
      () -> EntityType.Builder.<Fairy>of(Fairy::new, MobCategory.AMBIENT).sized(0.4f, 0.8f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("fairy"));
  public static final RegistryObject<EntityType<Peacock>> ENTITY_TYPE_PEACOCK = ENTITY_TYPES.register("peacock",
      () -> EntityType.Builder.<Peacock>of(Peacock::new, MobCategory.CREATURE).sized(0.65f, 1.2f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("peacock"));
  public static final RegistryObject<EntityType<Rotator>> ENTITY_TYPE_ROTATOR = ENTITY_TYPES.register("rotator",
      () -> EntityType.Builder.<Rotator>of(Rotator::new, MobCategory.MONSTER).sized(1.0f, 2.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("rotator"));
  public static final RegistryObject<EntityType<Vortex>> ENTITY_TYPE_VORTEX = ENTITY_TYPES.register("vortex",
      () -> EntityType.Builder.<Vortex>of(Vortex::new, MobCategory.MONSTER).sized(2.0f, 4.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("vortex"));
  public static final RegistryObject<EntityType<DungeonBeast>> ENTITY_TYPE_DUNGEON_BEAST = ENTITY_TYPES.register("dungeon_beast",
      () -> EntityType.Builder.<DungeonBeast>of(DungeonBeast::new, MobCategory.MONSTER).sized(1.15f, 1.1f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("dungeon_beast"));
  public static final RegistryObject<EntityType<Rat>> ENTITY_TYPE_RAT = ENTITY_TYPES.register("rat",
      () -> EntityType.Builder.<Rat>of(Rat::new, MobCategory.MONSTER).sized(0.25f, 0.5f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("rat"));
  public static final RegistryObject<EntityType<Flounder>> ENTITY_TYPE_FLOUNDER = ENTITY_TYPES.register("flounder",
      () -> EntityType.Builder.<Flounder>of(Flounder::new, MobCategory.WATER_CREATURE).sized(0.55f, 0.25f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("flounder"));
  public static final RegistryObject<EntityType<Whale>> ENTITY_TYPE_WHALE = ENTITY_TYPES.register("whale",
      () -> EntityType.Builder.<Whale>of(Whale::new, MobCategory.WATER_CREATURE).sized(1.5f, 2.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("whale"));
  public static final RegistryObject<EntityType<Irukandji>> ENTITY_TYPE_IRUKANDJI = ENTITY_TYPES.register("irukandji",
      () -> EntityType.Builder.<Irukandji>of(Irukandji::new, MobCategory.MONSTER).sized(0.25f, 0.25f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("irukandji"));
  public static final RegistryObject<EntityType<Skate>> ENTITY_TYPE_SKATE = ENTITY_TYPES.register("skate",
      () -> EntityType.Builder.<Skate>of(Skate::new, MobCategory.MONSTER).sized(0.75f, 0.25f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("skate"));
  public static final RegistryObject<EntityType<Urchin>> ENTITY_TYPE_URCHIN = ENTITY_TYPES.register("crystal_urchin",
      () -> EntityType.Builder.<Urchin>of(Urchin::new, MobCategory.MONSTER).sized(1.35f, 2.1f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("crystal_urchin"));
  public static final RegistryObject<EntityType<Mantis>> ENTITY_TYPE_MANTIS = ENTITY_TYPES.register("mantis",
      () -> EntityType.Builder.<Mantis>of(Mantis::new, MobCategory.MONSTER).sized(2.5f, 3.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("mantis"));
  public static final RegistryObject<EntityType<HerculesBeetle>> ENTITY_TYPE_HERCULES_BEETLE = ENTITY_TYPES.register("hercules_beetle",
      () -> EntityType.Builder.<HerculesBeetle>of(HerculesBeetle::new, MobCategory.MONSTER).sized(3.25f, 2.75f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("hercules_beetle"));
  public static final RegistryObject<EntityType<TRex>> ENTITY_TYPE_TREX = ENTITY_TYPES.register("trex",
      () -> EntityType.Builder.<TRex>of(TRex::new, MobCategory.MONSTER).sized(2.0f, 4.2f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("trex"));
  public static final RegistryObject<EntityType<TRex>> ENTITY_TYPE_T_REX = ENTITY_TYPES.register("t._rex",
      () -> EntityType.Builder.<TRex>of(TRex::new, MobCategory.MONSTER).sized(2.0f, 4.2f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("t._rex"));
  public static final RegistryObject<EntityType<Stinky>> ENTITY_TYPE_STINKY = ENTITY_TYPES.register("stinky",
      () -> EntityType.Builder.<Stinky>of(Stinky::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("stinky"));
  public static final RegistryObject<EntityType<Coin>> ENTITY_TYPE_COIN = ENTITY_TYPES.register("coin",
      () -> EntityType.Builder.<Coin>of(Coin::new, MobCategory.CREATURE).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("coin"));
  public static final RegistryObject<EntityType<TheKing>> ENTITY_TYPE_THE_KING = ENTITY_TYPES.register("the_king",
      () -> EntityType.Builder.<TheKing>of(TheKing::new, MobCategory.MONSTER).sized(2f, 2f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_king"));
  public static final RegistryObject<EntityType<KingHead>> ENTITY_TYPE_KING_HEAD = ENTITY_TYPES.register("king_head",
      () -> EntityType.Builder.<KingHead>of(KingHead::new, MobCategory.MISC).sized(19.9f, 10.0f).clientTrackingRange(128).updateInterval(10).setShouldReceiveVelocityUpdates(false).build("king_head"));
  public static final RegistryObject<EntityType<TheQueen>> ENTITY_TYPE_THE_QUEEN = ENTITY_TYPES.register("the_queen",
      () -> EntityType.Builder.<TheQueen>of(TheQueen::new, MobCategory.MONSTER).sized(2f, 2f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_queen"));
  public static final RegistryObject<EntityType<QueenHead>> ENTITY_TYPE_QUEEN_HEAD = ENTITY_TYPES.register("queen_head",
      () -> EntityType.Builder.<QueenHead>of(QueenHead::new, MobCategory.MISC).sized(19.9f, 10.0f).clientTrackingRange(128).updateInterval(10).setShouldReceiveVelocityUpdates(false).build("queen_head"));
  public static final RegistryObject<EntityType<Boyfriend>> ENTITY_TYPE_BOYFRIEND = ENTITY_TYPES.register("boyfriend",
      () -> EntityType.Builder.<Boyfriend>of(Boyfriend::new, MobCategory.CREATURE).sized(0.5f, 1.6f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("boyfriend"));
  public static final RegistryObject<EntityType<ThePrince>> ENTITY_TYPE_THE_PRINCE = ENTITY_TYPES.register("the_prince",
      () -> EntityType.Builder.<ThePrince>of(ThePrince::new, MobCategory.MONSTER).sized(0.75f, 1.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_prince"));
  public static final RegistryObject<EntityType<Molenoid>> ENTITY_TYPE_MOLENOID = ENTITY_TYPES.register("molenoid",
      () -> EntityType.Builder.<Molenoid>of(Molenoid::new, MobCategory.MONSTER).sized(3.9f, 2.6f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("molenoid"));
  public static final RegistryObject<EntityType<SeaMonster>> ENTITY_TYPE_SEA_MONSTER = ENTITY_TYPES.register("sea_monster",
      () -> EntityType.Builder.<SeaMonster>of(SeaMonster::new, MobCategory.MONSTER).sized(1.25f, 2.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("sea_monster"));
  public static final RegistryObject<EntityType<SeaViper>> ENTITY_TYPE_SEA_VIPER = ENTITY_TYPES.register("sea_viper",
      () -> EntityType.Builder.<SeaViper>of(SeaViper::new, MobCategory.MONSTER).sized(1.5f, 2.5f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("sea_viper"));
  public static final RegistryObject<EntityType<EasterBunny>> ENTITY_TYPE_EASTER_BUNNY = ENTITY_TYPES.register("easter_bunny",
      () -> EntityType.Builder.<EasterBunny>of(EasterBunny::new, MobCategory.MONSTER).sized(0.5f, 0.75f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("easter_bunny"));
  public static final RegistryObject<EntityType<CaterKiller>> ENTITY_TYPE_CATERKILLER = ENTITY_TYPES.register("caterkiller",
      () -> EntityType.Builder.<CaterKiller>of(CaterKiller::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("caterkiller"));
  public static final RegistryObject<EntityType<CrystalCow>> ENTITY_TYPE_CRYSTAL_COW = ENTITY_TYPES.register("crystal_apple_cow",
      () -> EntityType.Builder.<CrystalCow>of(CrystalCow::new, MobCategory.CREATURE).sized(0.6f, 1.4f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("crystal_apple_cow"));
  public static final RegistryObject<EntityType<Leon>> ENTITY_TYPE_LEONOPTERYX = ENTITY_TYPES.register("leonopteryx",
      () -> EntityType.Builder.<Leon>of(Leon::new, MobCategory.MONSTER).sized(3.5f, 8.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("leonopteryx"));
  public static final RegistryObject<EntityType<Hammerhead>> ENTITY_TYPE_HAMMERHEAD = ENTITY_TYPES.register("hammerhead",
      () -> EntityType.Builder.<Hammerhead>of(Hammerhead::new, MobCategory.MONSTER).sized(3.0f, 5.0f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("hammerhead"));
  public static final RegistryObject<EntityType<RubberDucky>> ENTITY_TYPE_RUBBER_DUCKY = ENTITY_TYPES.register("rubber_ducky",
      () -> EntityType.Builder.<RubberDucky>of(RubberDucky::new, MobCategory.CREATURE).sized(0.6f, 1.4f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("rubber_ducky"));
  public static final RegistryObject<EntityType<ThePrinceTeen>> ENTITY_TYPE_THE_YOUNG_PRINCE = ENTITY_TYPES.register("the_young_prince",
      () -> EntityType.Builder.<ThePrinceTeen>of(ThePrinceTeen::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_young_prince"));
  public static final RegistryObject<EntityType<BandP>> ENTITY_TYPE_CRIMINAL = ENTITY_TYPES.register("criminal",
      () -> EntityType.Builder.<BandP>of(BandP::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("criminal"));
  public static final RegistryObject<EntityType<RockBase>> ENTITY_TYPE_ROCK = ENTITY_TYPES.register("rock",
      () -> EntityType.Builder.<RockBase>of(RockBase::new, MobCategory.MONSTER).sized(0.25f, 0.15f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("rock"));
  public static final RegistryObject<EntityType<Brutalfly>> ENTITY_TYPE_BRUTALFLY = ENTITY_TYPES.register("brutalfly",
      () -> EntityType.Builder.<Brutalfly>of(Brutalfly::new, MobCategory.MONSTER).sized(5.0f, 2.0f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("brutalfly"));
  public static final RegistryObject<EntityType<Nastysaurus>> ENTITY_TYPE_NASTYSAURUS = ENTITY_TYPES.register("nastysaurus",
      () -> EntityType.Builder.<Nastysaurus>of(Nastysaurus::new, MobCategory.MONSTER).sized(2.2f, 4.6f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("nastysaurus"));
  public static final RegistryObject<EntityType<Pointysaurus>> ENTITY_TYPE_POINTYSAURUS = ENTITY_TYPES.register("pointysaurus",
      () -> EntityType.Builder.<Pointysaurus>of(Pointysaurus::new, MobCategory.MONSTER).sized(2.9f, 2.9f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("pointysaurus"));
  public static final RegistryObject<EntityType<Cricket>> ENTITY_TYPE_CRICKET = ENTITY_TYPES.register("cricket",
      () -> EntityType.Builder.<Cricket>of(Cricket::new, MobCategory.CREATURE).sized(0.1f, 0.1f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("cricket"));
  public static final RegistryObject<EntityType<ThePrincess>> ENTITY_TYPE_THE_PRINCESS = ENTITY_TYPES.register("the_princess",
      () -> EntityType.Builder.<ThePrincess>of(ThePrincess::new, MobCategory.MONSTER).sized(0.75f, 1.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_princess"));
  public static final RegistryObject<EntityType<Frog>> ENTITY_TYPE_FROG = ENTITY_TYPES.register("frog",
      () -> EntityType.Builder.<Frog>of(Frog::new, MobCategory.WATER_CREATURE).sized(0.75f, 0.75f).clientTrackingRange(32).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("frog"));
  public static final RegistryObject<EntityType<ThePrinceAdult>> ENTITY_TYPE_THE_YOUNG_ADULT_PRINCE = ENTITY_TYPES.register("the_young_adult_prince",
      () -> EntityType.Builder.<ThePrinceAdult>of(ThePrinceAdult::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("the_young_adult_prince"));
  public static final RegistryObject<EntityType<SpiderRobot>> ENTITY_TYPE_SPIDER_ROBOT = ENTITY_TYPES.register("robot_spider",
      () -> EntityType.Builder.<SpiderRobot>of(SpiderRobot::new, MobCategory.MONSTER).sized(3.25f, 2.25f).clientTrackingRange(128).updateInterval(3).setShouldReceiveVelocityUpdates(true).build("robot_spider"));
  public static final RegistryObject<EntityType<SpiderDriver>> ENTITY_TYPE_SPIDER_DRIVER = ENTITY_TYPES.register("spider_driver",
      () -> EntityType.Builder.<SpiderDriver>of(SpiderDriver::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("spider_driver"));
  public static final RegistryObject<EntityType<GiantRobot>> ENTITY_TYPE_GIANT_ROBOT = ENTITY_TYPES.register("jeffery",
      () -> EntityType.Builder.<GiantRobot>of(GiantRobot::new, MobCategory.MONSTER).sized(3.0f, 9.75f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("jeffery"));
  public static final RegistryObject<EntityType<AntRobot>> ENTITY_TYPE_ANT_ROBOT = ENTITY_TYPES.register("robot_red_ant",
      () -> EntityType.Builder.<AntRobot>of(AntRobot::new, MobCategory.MONSTER).sized(4.0f, 2.5f).clientTrackingRange(128).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("robot_red_ant"));
  public static final RegistryObject<EntityType<Crab>> ENTITY_TYPE_CRAB = ENTITY_TYPES.register("crab",
      () -> EntityType.Builder.<Crab>of(Crab::new, MobCategory.MONSTER).sized(0.6f, 1.8f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(false).build("crab"));
  public static final RegistryObject<EntityType<Shoes>> ENTITY_TYPE_SHOES = ENTITY_TYPES.register("shoes",
      () -> EntityType.Builder.<Shoes>of(Shoes::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("shoes"));
  public static final RegistryObject<EntityType<EntityCage>> ENTITY_TYPE_CAGE = ENTITY_TYPES.register("entity_cage",
      () -> EntityType.Builder.<EntityCage>of(EntityCage::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("entity_cage"));
  public static final RegistryObject<EntityType<UltimateArrow>> ENTITY_TYPE_ULTIMATE_ARROW = ENTITY_TYPES.register("ultimate_arrow",
      () -> EntityType.Builder.<UltimateArrow>of(UltimateArrow::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("ultimate_arrow"));
  public static final RegistryObject<EntityType<IrukandjiArrow>> ENTITY_TYPE_IRUKANDJI_ARROW = ENTITY_TYPES.register("irukandji_arrow",
      () -> EntityType.Builder.<IrukandjiArrow>of(IrukandjiArrow::new, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(64).updateInterval(1).setShouldReceiveVelocityUpdates(true).build("irukandji_arrow"));

private static void registerAllCritterCages() {
    ITEMS.register("cageempty", () -> new CritterCage(0, 160));
    ITEMS.register("cagespider", () -> new CritterCage(0, 161));
    ITEMS.register("cagebat", () -> new CritterCage(0, 162));
    ITEMS.register("cagecow", () -> new CritterCage(0, 163));
    ITEMS.register("cagepig", () -> new CritterCage(0, 164));
    ITEMS.register("cagesquid", () -> new CritterCage(0, 165));
    ITEMS.register("cagechicken", () -> new CritterCage(0, 166));
    ITEMS.register("cagecreeper", () -> new CritterCage(0, 167));
    ITEMS.register("cageskeleton", () -> new CritterCage(0, 168));
    ITEMS.register("cagezombie", () -> new CritterCage(0, 169));
    ITEMS.register("cageslime", () -> new CritterCage(0, 170));
    ITEMS.register("cageghast", () -> new CritterCage(0, 171));
    ITEMS.register("cagezombiepigman", () -> new CritterCage(0, 172));
    ITEMS.register("cageenderman", () -> new CritterCage(0, 173));
    ITEMS.register("cagecavespider", () -> new CritterCage(0, 174));
    ITEMS.register("cagesilverfish", () -> new CritterCage(0, 175));
    ITEMS.register("cagemagmacube", () -> new CritterCage(0, 176));
    ITEMS.register("cagewitch", () -> new CritterCage(0, 177));
    ITEMS.register("cagesheep", () -> new CritterCage(0, 178));
    ITEMS.register("cagewolf", () -> new CritterCage(0, 179));
    ITEMS.register("cagemooshroom", () -> new CritterCage(0, 180));
    ITEMS.register("cageocelot", () -> new CritterCage(0, 181));
    ITEMS.register("cageblaze", () -> new CritterCage(0, 182));
    ITEMS.register("cagegirlfriend", () -> new CritterCage(0, 183));
    ITEMS.register("cageboyfriend", () -> new CritterCage(0, 215));
    ITEMS.register("cagewitherskeleton", () -> new CritterCage(0, 188));
    ITEMS.register("cageenderdragon", () -> new CritterCage(0, 184));
    ITEMS.register("cagesnowgolem", () -> new CritterCage(0, 185));
    ITEMS.register("cageirongolem", () -> new CritterCage(0, 186));
    ITEMS.register("cagewitherboss", () -> new CritterCage(0, 187));
    ITEMS.register("cageredcow", () -> new CritterCage(0, 189));
    ITEMS.register("cagegoldcow", () -> new CritterCage(0, 190));
    ITEMS.register("cageenchantedcow", () -> new CritterCage(0, 191));
    ITEMS.register("cagemothra", () -> new CritterCage(0, 208));
    ITEMS.register("cagealosaurus", () -> new CritterCage(0, 209));
    ITEMS.register("cagecryolophosaurus", () -> new CritterCage(0, 210));
    ITEMS.register("cagecamarasaurus", () -> new CritterCage(0, 211));
    ITEMS.register("cagevelocityraptor", () -> new CritterCage(0, 212));
    ITEMS.register("cagehydrolisc", () -> new CritterCage(0, 213));
    ITEMS.register("cagebasilisc", () -> new CritterCage(0, 214));
    ITEMS.register("cagedragonfly", () -> new CritterCage(0, 220));
    ITEMS.register("cageemperorscorpion", () -> new CritterCage(0, 222));
    ITEMS.register("cagescorpion", () -> new CritterCage(0, 224));
    ITEMS.register("cagecavefisher", () -> new CritterCage(0, 226));
    ITEMS.register("cagespyro", () -> new CritterCage(0, 228));
    ITEMS.register("cagebaryonyx", () -> new CritterCage(0, 230));
    ITEMS.register("cagegammametroid", () -> new CritterCage(0, 232));
    ITEMS.register("cagecockateil", () -> new CritterCage(0, 234));
    ITEMS.register("cagekyuubi", () -> new CritterCage(0, 236));
    ITEMS.register("cagealien", () -> new CritterCage(0, 238));
    ITEMS.register("cageattacksquid", () -> new CritterCage(0, 240));
    ITEMS.register("cagewaterdragon", () -> new CritterCage(0, 242));
    ITEMS.register("cagecephadrome", () -> new CritterCage(0, 248));
    ITEMS.register("cagekraken", () -> new CritterCage(0, 244));
    ITEMS.register("cagelizard", () -> new CritterCage(0, 246));
    ITEMS.register("cagedragon", () -> new CritterCage(0, 250));
    ITEMS.register("cagebee", () -> new CritterCage(0, 252));
    ITEMS.register("cagehorse", () -> new CritterCage(0, 253));
    ITEMS.register("cagefirefly", () -> new CritterCage(0, 255));
    ITEMS.register("cagechipmunk", () -> new CritterCage(0, 256));
    ITEMS.register("cagegazelle", () -> new CritterCage(0, 257));
    ITEMS.register("cageostrich", () -> new CritterCage(0, 258));
    ITEMS.register("cagetrooper", () -> new CritterCage(0, 259));
    ITEMS.register("cagespit", () -> new CritterCage(0, 260));
    ITEMS.register("cagestink", () -> new CritterCage(0, 261));
    ITEMS.register("cagecreepinghorror", () -> new CritterCage(0, 268));
    ITEMS.register("cageterribleterror", () -> new CritterCage(0, 269));
    ITEMS.register("cagecliffracer", () -> new CritterCage(0, 270));
    ITEMS.register("cagetriffid", () -> new CritterCage(0, 271));
    ITEMS.register("cagenightmare", () -> new CritterCage(0, 272));
    ITEMS.register("cagelurkingterror", () -> new CritterCage(0, 273));
    ITEMS.register("cagesmallworm", () -> new CritterCage(0, 281));
    ITEMS.register("cagemediumworm", () -> new CritterCage(0, 282));
    ITEMS.register("cagelargeworm", () -> new CritterCage(0, 283));
    ITEMS.register("cagecassowary", () -> new CritterCage(0, 284));
    ITEMS.register("cagecloudshark", () -> new CritterCage(0, 285));
    ITEMS.register("cagegoldfish", () -> new CritterCage(0, 286));
    ITEMS.register("cageleafmonster", () -> new CritterCage(0, 287));
    ITEMS.register("cageenderknight", () -> new CritterCage(0, 296));
    ITEMS.register("cageenderreaper", () -> new CritterCage(0, 297));
    ITEMS.register("cagebeaver", () -> new CritterCage(0, 300));
    ITEMS.register("cageurchin", () -> new CritterCage(0, 323));
    ITEMS.register("cageflounder", () -> new CritterCage(0, 319));
    ITEMS.register("cageskate", () -> new CritterCage(0, 322));
    ITEMS.register("cagerotator", () -> new CritterCage(0, 313));
    ITEMS.register("cagepeacock", () -> new CritterCage(0, 315));
    ITEMS.register("cagefairy", () -> new CritterCage(0, 316));
    ITEMS.register("cagedungeonbeast", () -> new CritterCage(0, 317));
    ITEMS.register("cagevortex", () -> new CritterCage(0, 314));
    ITEMS.register("cagerat", () -> new CritterCage(0, 318));
    ITEMS.register("cagewhale", () -> new CritterCage(0, 320));
    ITEMS.register("cageirukandji", () -> new CritterCage(0, 321));
    ITEMS.register("cagetrex", () -> new CritterCage(0, 345));
    ITEMS.register("cagehercules", () -> new CritterCage(0, 346));
    ITEMS.register("cagemantis", () -> new CritterCage(0, 347));
    ITEMS.register("cagestinky", () -> new CritterCage(0, 348));
    ITEMS.register("cageeasterbunny", () -> new CritterCage(0, 150));
    ITEMS.register("cagecaterkiller", () -> new CritterCage(0, 151));
    ITEMS.register("cagemolenoid", () -> new CritterCage(0, 152));
    ITEMS.register("cageseamonster", () -> new CritterCage(0, 153));
    ITEMS.register("cageseaviper", () -> new CritterCage(0, 154));
    ITEMS.register("cageleon", () -> new CritterCage(0, 357));
    ITEMS.register("cagehammerhead", () -> new CritterCage(0, 359));
    ITEMS.register("cagerubberducky", () -> new CritterCage(0, 361));
    ITEMS.register("cagecrystalcow", () -> new CritterCage(0, 216));
    ITEMS.register("cagevillager", () -> new CritterCage(0, 217));
    ITEMS.register("cagecriminal", () -> new CritterCage(0, 218));
    ITEMS.register("cagebrutalfly", () -> new CritterCage(0, 373));
    ITEMS.register("cagenastysaurus", () -> new CritterCage(0, 374));
    ITEMS.register("cagepointysaurus", () -> new CritterCage(0, 375));
    ITEMS.register("cagecricket", () -> new CritterCage(0, 376));
    ITEMS.register("cagefrog", () -> new CritterCage(0, 377));
    ITEMS.register("cagespiderdriver", () -> new CritterCage(0, 382));
    ITEMS.register("cagecrab", () -> new CritterCage(0, 384));
  }


    private static void registerAllPreInitBlocks() {
    BLOCKS.register("antblock", () -> new AntBlock(0));
    BLOCKS.register("blockamethyst", BlockRuby::new);
    BLOCKS.register("blockenderpearl", OreGenericEgg::new);
    BLOCKS.register("blockeyeofender", OreGenericEgg::new);
    BLOCKS.register("blockmobzillascale", BlockRuby::new);
    BLOCKS.register("blockruby", BlockRuby::new);
    BLOCKS.register("blockteleport", RTPBlock::new);
    BLOCKS.register("blocktitanium", BlockTitanium::new);
    BLOCKS.register("blockuranium", BlockUranium::new);
    BLOCKS.register("corn_plant0", BlockCorn::new);
    BLOCKS.register("corn_plant1", BlockCorn::new);
    BLOCKS.register("corn_plant2", BlockCorn::new);
    BLOCKS.register("corn_plant3", BlockCorn::new);
    BLOCKS.register("creeperrepellent", () -> new CreeperRepellent());
    BLOCKS.register("crystalcoal", () -> new OreCrystal(0.6F, 6.0F, 20.0F));
    BLOCKS.register("crystalcrystal", () -> new OreCrystalCrystal(0.4F, 12.0F, 40.0F));
    BLOCKS.register("crystalfairy", () -> new OreBasicStone(2.5F, 14.0F, true));
    BLOCKS.register("crystalpink_block", BlockCrystal::new);
    BLOCKS.register("crystalrat", () -> new OreBasicStone(2.5F, 14.0F, true));
    BLOCKS.register("crystalsapling", BlockCrystalPlant::new);
    BLOCKS.register("crystalsapling2", BlockCrystalPlant::new);
    BLOCKS.register("crystalsapling3", BlockCrystalPlant::new);
    BLOCKS.register("crystalstone", () -> new OreBasicStone(2.0F, 10.0F, true));
    BLOCKS.register("crystaltorch", () -> new BlockCrystalTorch());
    BLOCKS.register("ducttape", BlockDuctTape::new);
    BLOCKS.register("dungeonspawner", () -> new DungeonSpawnerBlock());
    BLOCKS.register("experiencesapling", BlockExperiencePlant::new);
    BLOCKS.register("extremetorch", () -> new BlockExtremeTorch());
    BLOCKS.register("island", () -> new IslandBlock());
    BLOCKS.register("kingspawner", () -> new KingSpawnerBlock());
    BLOCKS.register("krakenrepellent", () -> new KrakenRepellent());
    BLOCKS.register("lavafoam", Lavafoam::new);
    BLOCKS.register("lettuce_0", BlockLettuce::new);
    BLOCKS.register("lettuce_1", BlockLettuce::new);
    BLOCKS.register("lettuce_2", BlockLettuce::new);
    BLOCKS.register("lettuce_3", BlockLettuce::new);
    BLOCKS.register("moledirt", () -> new MoleDirtBlock());
    BLOCKS.register("oreamethyst", OreAmethyst::new);
    BLOCKS.register("oreruby", OreRuby::new);
    BLOCKS.register("oresalt", OreSalt::new);
    BLOCKS.register("oretitanium", OreTitanium::new);
    BLOCKS.register("oreuranium", OreUranium::new);
    BLOCKS.register("deepslate_oreamethyst", () -> new OreAmethyst(SoundType.DEEPSLATE));
    BLOCKS.register("deepslate_oreruby", () -> new OreRuby(SoundType.DEEPSLATE));
    BLOCKS.register("deepslate_oresalt", () -> new OreSalt(SoundType.DEEPSLATE));
    BLOCKS.register("deepslate_oretitanium", () -> new OreTitanium(SoundType.DEEPSLATE));
    BLOCKS.register("deepslate_oreuranium", () -> new OreUranium(SoundType.DEEPSLATE));
    BLOCKS.register("pizza", BlockPizza::new);
    BLOCKS.register("queenspawner", () -> new QueenSpawnerBlock());
    BLOCKS.register("quinoa_0", BlockQuinoa::new);
    BLOCKS.register("quinoa_1", BlockQuinoa::new);
    BLOCKS.register("quinoa_2", BlockQuinoa::new);
    BLOCKS.register("quinoa_3", BlockQuinoa::new);
    BLOCKS.register("radish_plant", BlockRadish::new);
    BLOCKS.register("rainbowantblock", () -> new AntBlock(0));
    BLOCKS.register("redantblock", () -> new AntBlock(0));
    BLOCKS.register("redanttroll", () -> new OreBasicStone(2.5F, 14.0F));
    BLOCKS.register("deepslate_redanttroll", () -> new OreBasicStone(4.5F, 14.0F));
    BLOCKS.register("rice_plant", BlockRice::new);
    BLOCKS.register("termiteblock", () -> new AntBlock(0));
    BLOCKS.register("termitetroll", () -> new OreBasicStone(2.5F, 14.0F));
    BLOCKS.register("deepslate_termitetroll", () -> new OreBasicStone(4.5F, 14.0F));
    BLOCKS.register("tigerseye", () -> new OreCrystalCrystal(0.5F, 15.0F, 60.0F));
    BLOCKS.register("tigerseye_block", BlockCrystal::new);
    BLOCKS.register("tomato_plant0", BlockTomato::new);
    BLOCKS.register("tomato_plant1", BlockTomato::new);
    BLOCKS.register("tomato_plant2", BlockTomato::new);
    BLOCKS.register("tomato_plant3", BlockTomato::new);
    BLOCKS.register("unstableantblock", () -> new AntBlock(0));
    BLOCKS.register("butterfly_plant", BlockButterflyPlant::new);
    BLOCKS.register("crystalflower_blue", MyBlockFlower::new);
    BLOCKS.register("crystalflower_green", MyBlockFlower::new);
    BLOCKS.register("crystalflower_red", MyBlockFlower::new);
    BLOCKS.register("crystalflower_yellow", MyBlockFlower::new);
    BLOCKS.register("crystalfurnace", () -> new CrystalFurnace(2.0F, 10.0F));
    BLOCKS.register("crystaltreeleaves", BlockCrystalLeaves::new);
    BLOCKS.register("crystaltreeleaves2", BlockCrystalLeaves::new);
    BLOCKS.register("crystaltreeleaves3", BlockCrystalLeaves::new);
    BLOCKS.register("crystaltreelog", BlockCrystalTreeLog::new);
    BLOCKS.register("duplicatortreelog", BlockDuplicatorLog::new);
    BLOCKS.register("firefly_plant", BlockFireflyPlant::new);
    BLOCKS.register("flower_black", MyBlockFlower::new);
    BLOCKS.register("flower_blue", MyBlockFlower::new);
    BLOCKS.register("flower_pink", MyBlockFlower::new);
    BLOCKS.register("flower_scary", MyBlockFlower::new);
    BLOCKS.register("leaves_apple", BlockAppleLeaves::new);
    BLOCKS.register("leaves_cherry", BlockScaryLeaves::new);
    BLOCKS.register("leaves_experience", BlockExperienceLeaves::new);
    BLOCKS.register("leaves_peach", BlockScaryLeaves::new);
    BLOCKS.register("leaves_scary", BlockScaryLeaves::new);
    BLOCKS.register("mosquito_plant", BlockMosquitoPlant::new);
    BLOCKS.register("moth_plant", BlockMothPlant::new);
    BLOCKS.register("skytreelog", BlockSkyTreeLog::new);
    BLOCKS.register("strawberry_plant", BlockStrawberry::new);
    BLOCKS.register("crystalgrass", () -> new CrystalGrass(0.6F, 2.0F));
    BLOCKS.register("crystalplanks", () -> new CrystalWood(1.5F, 4.0F));
    BLOCKS.register("crystalworkbench", () -> new CrystalWorkbench(1.0F, 5.0F));
    BLOCKS.register("crystaltermiteblock", () -> new CrystalAntBlock(0));
    BLOCKS.register("orealien", OreGenericEgg::new);
    BLOCKS.register("orealosaurus", OreGenericEgg::new);
    BLOCKS.register("oreattacksquid", OreGenericEgg::new);
    BLOCKS.register("orebaryonyx", OreGenericEgg::new);
    BLOCKS.register("orebasilisc", OreGenericEgg::new);
    BLOCKS.register("orebat", OreGenericEgg::new);
    BLOCKS.register("orebeaver", OreGenericEgg::new);
    BLOCKS.register("orebee", OreGenericEgg::new);
    BLOCKS.register("oreblaze", OreGenericEgg::new);
    BLOCKS.register("oreboyfriend", OreGenericEgg::new);
    BLOCKS.register("orebrutalfly", OreGenericEgg::new);
    BLOCKS.register("orecamarasaurus", OreGenericEgg::new);
    BLOCKS.register("orecassowary", OreGenericEgg::new);
    BLOCKS.register("orecaterkiller", OreGenericEgg::new);
    BLOCKS.register("orecavefisher", OreGenericEgg::new);
    BLOCKS.register("orecavespider", OreGenericEgg::new);
    BLOCKS.register("orecephadrome", OreGenericEgg::new);
    BLOCKS.register("orechicken", OreGenericEgg::new);
    BLOCKS.register("orechipmunk", OreGenericEgg::new);
    BLOCKS.register("orecliffracer", OreGenericEgg::new);
    BLOCKS.register("orecloudshark", OreGenericEgg::new);
    BLOCKS.register("orecockateil", OreGenericEgg::new);
    BLOCKS.register("orecow", OreGenericEgg::new);
    BLOCKS.register("orecrab", OreGenericEgg::new);
    BLOCKS.register("orecreeper", OreGenericEgg::new);
    BLOCKS.register("orecreepinghorror", OreGenericEgg::new);
    BLOCKS.register("orecricket", OreGenericEgg::new);
    BLOCKS.register("orecriminal", OreGenericEgg::new);
    BLOCKS.register("orecryolophosaurus", OreGenericEgg::new);
    BLOCKS.register("orecrystalcow", OreGenericEgg::new);
    BLOCKS.register("oredragon", OreGenericEgg::new);
    BLOCKS.register("oredragonfly", OreGenericEgg::new);
    BLOCKS.register("oredungeonbeast", OreGenericEgg::new);
    BLOCKS.register("oreeasterbunny", OreGenericEgg::new);
    BLOCKS.register("oreemperorscorpion", OreGenericEgg::new);
    BLOCKS.register("oreenchantedcow", OreGenericEgg::new);
    BLOCKS.register("oreenderdragon", OreGenericEgg::new);
    BLOCKS.register("oreenderknight", OreGenericEgg::new);
    BLOCKS.register("oreenderman", OreGenericEgg::new);
    BLOCKS.register("oreenderreaper", OreGenericEgg::new);
    BLOCKS.register("orefairy", OreGenericEgg::new);
    BLOCKS.register("oreflounder", OreGenericEgg::new);
    BLOCKS.register("orefrog", OreGenericEgg::new);
    BLOCKS.register("oregammametroid", OreGenericEgg::new);
    BLOCKS.register("oregazelle", OreGenericEgg::new);
    BLOCKS.register("oreghast", OreGenericEgg::new);
    BLOCKS.register("oregirlfriend", OreGenericEgg::new);
    BLOCKS.register("oregodzilla", OreGenericEgg::new);
    BLOCKS.register("oregodzillapart", OreGenericEgg::new);
    BLOCKS.register("oregoldcow", OreGenericEgg::new);
    BLOCKS.register("oregoldfish", OreGenericEgg::new);
    BLOCKS.register("orehammerhead", OreGenericEgg::new);
    BLOCKS.register("orehercules", OreGenericEgg::new);
    BLOCKS.register("orehorse", OreGenericEgg::new);
    BLOCKS.register("orehydrolisc", OreGenericEgg::new);
    BLOCKS.register("oreirongolem", OreGenericEgg::new);
    BLOCKS.register("oreirukandji", OreGenericEgg::new);
    BLOCKS.register("orekraken", OreGenericEgg::new);
    BLOCKS.register("orekyuubi", OreGenericEgg::new);
    BLOCKS.register("orelargeworm", OreGenericEgg::new);
    BLOCKS.register("oreleafmonster", OreGenericEgg::new);
    BLOCKS.register("oreleon", OreGenericEgg::new);
    BLOCKS.register("orelizard", OreGenericEgg::new);
    BLOCKS.register("orelurkingterror", OreGenericEgg::new);
    BLOCKS.register("oremagmacube", OreGenericEgg::new);
    BLOCKS.register("oremantis", OreGenericEgg::new);
    BLOCKS.register("oremediumworm", OreGenericEgg::new);
    BLOCKS.register("oremolenoid", OreGenericEgg::new);
    BLOCKS.register("oremooshroom", OreGenericEgg::new);
    BLOCKS.register("oremothra", OreGenericEgg::new);
    BLOCKS.register("orenastysaurus", OreGenericEgg::new);
    BLOCKS.register("orenightmare", OreGenericEgg::new);
    BLOCKS.register("oreocelot", OreGenericEgg::new);
    BLOCKS.register("oreostrich", OreGenericEgg::new);
    BLOCKS.register("orepeacock", OreGenericEgg::new);
    BLOCKS.register("orepig", OreGenericEgg::new);
    BLOCKS.register("orepointysaurus", OreGenericEgg::new);
    BLOCKS.register("orerat", OreGenericEgg::new);
    BLOCKS.register("oreredcow", OreGenericEgg::new);
    BLOCKS.register("orerotator", OreGenericEgg::new);
    BLOCKS.register("orerubberducky", OreGenericEgg::new);
    BLOCKS.register("orescorpion", OreGenericEgg::new);
    BLOCKS.register("oreseamonster", OreGenericEgg::new);
    BLOCKS.register("oreseaviper", OreGenericEgg::new);
    BLOCKS.register("oresheep", OreGenericEgg::new);
    BLOCKS.register("oresilverfish", OreGenericEgg::new);
    BLOCKS.register("oreskate", OreGenericEgg::new);
    BLOCKS.register("oreskeleton", OreGenericEgg::new);
    BLOCKS.register("oreslime", OreGenericEgg::new);
    BLOCKS.register("oresmallworm", OreGenericEgg::new);
    BLOCKS.register("oresnowgolem", OreGenericEgg::new);
    BLOCKS.register("orespider", OreGenericEgg::new);
    BLOCKS.register("orespiderdriver", OreGenericEgg::new);
    BLOCKS.register("orespit", OreGenericEgg::new);
    BLOCKS.register("orespyro", OreGenericEgg::new);
    BLOCKS.register("oresquid", OreGenericEgg::new);
    BLOCKS.register("orestink", OreGenericEgg::new);
    BLOCKS.register("orestinky", OreGenericEgg::new);
    BLOCKS.register("oreterribleterror", OreGenericEgg::new);
    BLOCKS.register("oretheking", OreGenericEgg::new);
    BLOCKS.register("orethekingpart", OreGenericEgg::new);
    BLOCKS.register("orethequeen", OreGenericEgg::new);
    BLOCKS.register("orethequeenpart", OreGenericEgg::new);
    BLOCKS.register("oretrex", OreGenericEgg::new);
    BLOCKS.register("oretriffid", OreGenericEgg::new);
    BLOCKS.register("oretrooper", OreGenericEgg::new);
    BLOCKS.register("oretshirt", OreGenericEgg::new);
    BLOCKS.register("oreurchin", OreGenericEgg::new);
    BLOCKS.register("orevelocityraptor", OreGenericEgg::new);
    BLOCKS.register("orevillager", OreGenericEgg::new);
    BLOCKS.register("orevortex", OreGenericEgg::new);
    BLOCKS.register("orewaterdragon", OreGenericEgg::new);
    BLOCKS.register("orewhale", OreGenericEgg::new);
    BLOCKS.register("orewitch", OreGenericEgg::new);
    BLOCKS.register("orewitherboss", OreGenericEgg::new);
    BLOCKS.register("orewitherskeleton", OreGenericEgg::new);
    BLOCKS.register("orewolf", OreGenericEgg::new);
    BLOCKS.register("orezombie", OreGenericEgg::new);
    BLOCKS.register("orezombiepigman", OreGenericEgg::new);
  }

  private static void registerAllPreInitItems() {
    ITEMS.register(
        "pizza",
        () ->
            new ItemPizza(
                (BlockPizza)
                    BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "pizza"))));
    ITEMS.register(
        "ducttape",
        () ->
            new ItemDuctTape(
                (BlockDuctTape)
                    BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "ducttape"))));
    ITEMS.register(
        "island",
        () ->
            new IslandBlock.ItemIslandBlock(
                (IslandBlock)
                    BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "island")),
                new Item.Properties()));
    ITEMS.register("acid", () -> new ItemAcid(BaseItemID + 247));
    ITEMS.register("antrobotkit", () -> new ItemSpiderRobotKit(BaseItemID + 473));
    ITEMS.register("appletree_seed", () -> new ItemAppleSeed(BaseItemID + 211));
    ITEMS.register("bluefish", () -> new ItemGenericFish(4, 0.4F, false));
    ITEMS.register(
        "butterfly_seed",
        () ->
            new ItemButterflySeed(
                (BlockButterflyPlant)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "butterfly_plant")),
                Blocks.FARMLAND));
    ITEMS.register("cherrytree_seed", () -> new ItemAppleSeed(BaseItemID + 217));
    ITEMS.register(
        "corn_seed",
        () ->
            new ItemCornCob(
                6,
                0.75F,
                (BlockCorn)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "corn_plant0")),
                Blocks.FARMLAND));
    ITEMS.register("creeperlauncher", () -> new ItemCreeperLauncher(BaseItemID + 252));
    ITEMS.register("crystalsticks", () -> new ItemCrystalSticks(BaseItemID + 254));
    ITEMS.register("deadirukandji", () -> new ItemIrukandji(BaseItemID + 258));
    ITEMS.register("elevator", () -> new ItemElevator(BaseItemID + 235));
    ITEMS.register("experiencetree_seed", () -> new ItemExperienceTreeSeed(BaseItemID + 216));
    ITEMS.register(
        "firefly_seed",
        () ->
            new ItemFireflySeed(
                (BlockFireflyPlant)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "firefly_plant")),
                Blocks.FARMLAND));
    ITEMS.register("greenfish", () -> new ItemGenericFish(3, 0.5F, false));
    ITEMS.register("greyfish", () -> new ItemGenericFish(5, 0.5F, false));
    ITEMS.register("iceball", () -> new ItemIceBall(BaseItemID + 239));
    ITEMS.register("instantgarden", () -> new InstantGarden(BaseItemID + 328));
    ITEMS.register("instantshelter", () -> new InstantShelter(BaseItemID + 327));
    ITEMS.register("irukandjiarrow", () -> new ItemIrukandjiArrow(BaseItemID + 372));
    ITEMS.register("laserball", () -> new ItemLaserBall(BaseItemID + 242));
    ITEMS.register(
        "lettuce_seed",
        () ->
            new ItemLettuce(
                3,
                0.45F,
                (BlockLettuce)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "lettuce_0")),
                Blocks.FARMLAND));
    ITEMS.register("magicapple", () -> new ItemMagicApple(BaseItemID + 236));
    ITEMS.register("minersdream", () -> new ItemMinersDream(BaseItemID + 237));
    ITEMS.register(
        "mosquito_seed",
        () ->
            new ItemMosquitoSeed(
                (BlockMosquitoPlant)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "mosquito_plant")),
                Blocks.FARMLAND));
    ITEMS.register(
        "moth_seed",
        () ->
            new ItemMothSeed(
                (BlockMothPlant)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "moth_plant")),
                Blocks.FARMLAND));
    ITEMS.register("netherlost", () -> new ItemNetherLost(BaseItemID + 253));
    ITEMS.register("peachtree_seed", () -> new ItemAppleSeed(BaseItemID + 218));
    ITEMS.register("pinkfish", () -> new ItemGenericFish(4, 0.6F, false));
    ITEMS.register(
        "quinoa",
        () ->
            new ItemCornCob(
                7,
                0.85F,
                (BlockQuinoa)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "quinoa_0")),
                (CrystalGrass)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "crystalgrass"))));
    ITEMS.register(
        "radish",
        () ->
            new ItemRadish(
                2,
                0.45F,
                (BlockRadish)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "radish_plant")),
                Blocks.FARMLAND));
    ITEMS.register("randomdungeon", () -> new ItemRandomDungeon(BaseItemID + 421));
    ITEMS.register("raygun", () -> new ItemRayGun(BaseItemID + 243));
    ITEMS.register(
        "rice",
        () ->
            new ItemRadish(
                5,
                0.65F,
                (BlockRice)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "rice_plant")),
                (CrystalGrass)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "crystalgrass"))));
    ITEMS.register("rock", () -> new ItemRock(BaseItemID + 435));
    ITEMS.register("rockblue", () -> new ItemRock(BaseItemID + 439));
    ITEMS.register("rockcrystalblue", () -> new ItemRock(BaseItemID + 445));
    ITEMS.register("rockcrystalgreen", () -> new ItemRock(BaseItemID + 444));
    ITEMS.register("rockcrystalred", () -> new ItemRock(BaseItemID + 443));
    ITEMS.register("rockcrystaltnt", () -> new ItemRock(BaseItemID + 446));
    ITEMS.register("rockfish", () -> new ItemGenericFish(3, 0.7F, false));
    ITEMS.register("rockgreen", () -> new ItemRock(BaseItemID + 438));
    ITEMS.register("rockpurple", () -> new ItemRock(BaseItemID + 440));
    ITEMS.register("rockred", () -> new ItemRock(BaseItemID + 437));
    ITEMS.register("rocksmall", () -> new ItemRock(BaseItemID + 436));
    ITEMS.register("rockspikey", () -> new ItemRock(BaseItemID + 441));
    ITEMS.register("rocktnt", () -> new ItemRock(BaseItemID + 442));
    ITEMS.register("sifter", () -> new ItemSifter(BaseItemID + 325));
    ITEMS.register("sparkfish", () -> new ItemSparkFish(1, 0.2F, false));
    ITEMS.register("spiderrobotkit", () -> new ItemSpiderRobotKit(BaseItemID + 471));
    ITEMS.register("squidzookasmall", () -> new ItemSquidZooka(BaseItemID + 317));
    ITEMS.register("step_accross", () -> new StepAccross(BaseItemID + 234));
    ITEMS.register("step_down", () -> new StepDown(BaseItemID + 233));
    ITEMS.register("step_up", () -> new StepUp(BaseItemID + 232));
    ITEMS.register(
        "strawberry_seed",
        () ->
            new ItemStrawberrySeed(
                (BlockStrawberry)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "strawberry_plant")),
                Blocks.FARMLAND));
    ITEMS.register("thunderstaff", () -> new ItemThunderStaff(BaseItemID + 240));
    ITEMS.register(
        "tomato_seed",
        () ->
            new ItemTomato(
                4,
                0.55F,
                (BlockTomato)
                    BuiltInRegistries.BLOCK.get(
                        ResourceLocation.fromNamespaceAndPath(MODID, "tomato_plant0")),
                Blocks.FARMLAND));
    ITEMS.register("waterball", () -> new ItemWaterBall(BaseItemID + 244));
    ITEMS.register("woodfish", () -> new ItemGenericFish(5, 0.7F, false));
    ITEMS.register("wrench", () -> new ItemWrench(BaseItemID + 472));
    ITEMS.register("zoo10", () -> new ZooCage(0, 17));
    ITEMS.register("zoo2", () -> new ZooCage(0, 3));
    ITEMS.register("zoo4", () -> new ZooCage(0, 5));
    ITEMS.register("zoo6", () -> new ZooCage(0, 9));
    ITEMS.register("zoo8", () -> new ZooCage(0, 13));
    ITEMS.register("zookeeper", () -> new ItemZooKeeper(BaseItemID + 230));
  }

  private static void registerAllSpawnEggs() {
    ITEMS.register("eggenderdragon", () -> new ItemSpawnEgg(0, 193));
    ITEMS.register("eggwitherboss", () -> new ItemSpawnEgg(0, 196));
    ITEMS.register("egggirlfriend", () -> new ItemSpawnEgg(0, 197));
    ITEMS.register("eggredcow", () -> new ItemSpawnEgg(0, 198));
    ITEMS.register("eggcrystalcow", () -> new ItemSpawnEgg(0, 363));
    ITEMS.register("egggoldcow", () -> new ItemSpawnEgg(0, 199));
    ITEMS.register("eggenchantedcow", () -> new ItemSpawnEgg(0, 200));
    ITEMS.register("eggmothra", () -> new ItemSpawnEgg(0, 201));
    ITEMS.register("eggalosaurus", () -> new ItemSpawnEgg(0, 202));
    ITEMS.register("eggcryolophosaurus", () -> new ItemSpawnEgg(0, 203));
    ITEMS.register("eggcamarasaurus", () -> new ItemSpawnEgg(0, 204));
    ITEMS.register("eggvelocityraptor", () -> new ItemSpawnEgg(0, 205));
    ITEMS.register("egghydrolisc", () -> new ItemSpawnEgg(0, 206));
    ITEMS.register("eggbasilisc", () -> new ItemSpawnEgg(0, 207));
    ITEMS.register("eggdragonfly", () -> new ItemSpawnEgg(0, 221));
    ITEMS.register("eggemperorscorpion", () -> new ItemSpawnEgg(0, 223));
    ITEMS.register("eggscorpion", () -> new ItemSpawnEgg(0, 225));
    ITEMS.register("eggcavefisher", () -> new ItemSpawnEgg(0, 227));
    ITEMS.register("eggspyro", () -> new ItemSpawnEgg(0, 229));
    ITEMS.register("eggbaryonyx", () -> new ItemSpawnEgg(0, 231));
    ITEMS.register("egggammametroid", () -> new ItemSpawnEgg(0, 233));
    ITEMS.register("eggcockateil", () -> new ItemSpawnEgg(0, 235));
    ITEMS.register("eggkyuubi", () -> new ItemSpawnEgg(0, 237));
    ITEMS.register("eggalien", () -> new ItemSpawnEgg(0, 239));
    ITEMS.register("eggattacksquid", () -> new ItemSpawnEgg(0, 241));
    ITEMS.register("eggwaterdragon", () -> new ItemSpawnEgg(0, 243));
    ITEMS.register("eggcephadrome", () -> new ItemSpawnEgg(0, 249));
    ITEMS.register("eggkraken", () -> new ItemSpawnEgg(0, 245));
    ITEMS.register("egglizard", () -> new ItemSpawnEgg(0, 247));
    ITEMS.register("eggdragon", () -> new ItemSpawnEgg(0, 251));
    ITEMS.register("eggbee", () -> new ItemSpawnEgg(0, 254));
    ITEMS.register("eggtrooper", () -> new ItemSpawnEgg(0, 262));
    ITEMS.register("eggspit", () -> new ItemSpawnEgg(0, 263));
    ITEMS.register("eggstink", () -> new ItemSpawnEgg(0, 264));
    ITEMS.register("eggostrich", () -> new ItemSpawnEgg(0, 265));
    ITEMS.register("egggazelle", () -> new ItemSpawnEgg(0, 266));
    ITEMS.register("eggchipmunk", () -> new ItemSpawnEgg(0, 267));
    ITEMS.register("eggcreepinghorror", () -> new ItemSpawnEgg(0, 274));
    ITEMS.register("eggterribleterror", () -> new ItemSpawnEgg(0, 275));
    ITEMS.register("eggcliffracer", () -> new ItemSpawnEgg(0, 276));
    ITEMS.register("eggtriffid", () -> new ItemSpawnEgg(0, 277));
    ITEMS.register("eggnightmare", () -> new ItemSpawnEgg(0, 278));
    ITEMS.register("egglurkingterror", () -> new ItemSpawnEgg(0, 279));
    ITEMS.register("egggodzilla", () -> new ItemSpawnEgg(0, 280));
    ITEMS.register("eggsmallworm", () -> new ItemSpawnEgg(0, 288));
    ITEMS.register("eggmediumworm", () -> new ItemSpawnEgg(0, 289));
    ITEMS.register("egglargeworm", () -> new ItemSpawnEgg(0, 290));
    ITEMS.register("eggcassowary", () -> new ItemSpawnEgg(0, 291));
    ITEMS.register("eggcloudshark", () -> new ItemSpawnEgg(0, 292));
    ITEMS.register("egggoldfish", () -> new ItemSpawnEgg(0, 293));
    ITEMS.register("eggleafmonster", () -> new ItemSpawnEgg(0, 294));
    ITEMS.register("eggtshirt", () -> new ItemSpawnEgg(0, 295));
    ITEMS.register("eggenderknight", () -> new ItemSpawnEgg(0, 298));
    ITEMS.register("eggenderreaper", () -> new ItemSpawnEgg(0, 299));
    ITEMS.register("eggbeaver", () -> new ItemSpawnEgg(0, 301));
    ITEMS.register("eggrotator", () -> new ItemSpawnEgg(0, 302));
    ITEMS.register("eggvortex", () -> new ItemSpawnEgg(0, 303));
    ITEMS.register("eggpeacock", () -> new ItemSpawnEgg(0, 304));
    ITEMS.register("eggfairy", () -> new ItemSpawnEgg(0, 305));
    ITEMS.register("eggdungeonbeast", () -> new ItemSpawnEgg(0, 306));
    ITEMS.register("eggrat", () -> new ItemSpawnEgg(0, 307));
    ITEMS.register("eggflounder", () -> new ItemSpawnEgg(0, 308));
    ITEMS.register("eggwhale", () -> new ItemSpawnEgg(0, 309));
    ITEMS.register("eggirukandji", () -> new ItemSpawnEgg(0, 310));
    ITEMS.register("eggskate", () -> new ItemSpawnEgg(0, 311));
    ITEMS.register("eggurchin", () -> new ItemSpawnEgg(0, 312));
    ITEMS.register("eggrobot1", () -> new ItemSpawnEgg(0, 324));
    ITEMS.register("eggrobot2", () -> new ItemSpawnEgg(0, 325));
    ITEMS.register("eggrobot3", () -> new ItemSpawnEgg(0, 326));
    ITEMS.register("eggrobot4", () -> new ItemSpawnEgg(0, 327));
    ITEMS.register("eggghost", () -> new ItemSpawnEgg(0, 328));
    ITEMS.register("eggghostskelly", () -> new ItemSpawnEgg(0, 329));
    ITEMS.register("eggbrownant", () -> new ItemSpawnEgg(0, 330));
    ITEMS.register("eggredant", () -> new ItemSpawnEgg(0, 331));
    ITEMS.register("eggrainbowant", () -> new ItemSpawnEgg(0, 332));
    ITEMS.register("eggunstableant", () -> new ItemSpawnEgg(0, 333));
    ITEMS.register("eggtermite", () -> new ItemSpawnEgg(0, 334));
    ITEMS.register("eggbutterfly", () -> new ItemSpawnEgg(0, 335));
    ITEMS.register("eggmoth", () -> new ItemSpawnEgg(0, 336));
    ITEMS.register("eggmosquito", () -> new ItemSpawnEgg(0, 337));
    ITEMS.register("eggfirefly", () -> new ItemSpawnEgg(0, 338));
    ITEMS.register("eggtrex", () -> new ItemSpawnEgg(0, 339));
    ITEMS.register("egghercules", () -> new ItemSpawnEgg(0, 340));
    ITEMS.register("eggmantis", () -> new ItemSpawnEgg(0, 341));
    ITEMS.register("eggstinky", () -> new ItemSpawnEgg(0, 342));
    ITEMS.register("eggrobot5", () -> new ItemSpawnEgg(0, 343));
    ITEMS.register("eggcoin", () -> new ItemSpawnEgg(0, 344));
    ITEMS.register("eggboyfriend", () -> new ItemSpawnEgg(0, 349));
    ITEMS.register("eggtheking", () -> new ItemSpawnEgg(0, 350));
    ITEMS.register("eggthequeen", () -> new ItemSpawnEgg(0, 366));
    ITEMS.register("eggtheprince", () -> new ItemSpawnEgg(0, 351));
    ITEMS.register("eggeasterbunny", () -> new ItemSpawnEgg(0, 352));
    ITEMS.register("eggmolenoid", () -> new ItemSpawnEgg(0, 353));
    ITEMS.register("eggseamonster", () -> new ItemSpawnEgg(0, 354));
    ITEMS.register("eggseaviper", () -> new ItemSpawnEgg(0, 355));
    ITEMS.register("eggcaterkiller", () -> new ItemSpawnEgg(0, 356));
    ITEMS.register("eggrubberducky", () -> new ItemSpawnEgg(0, 362));
    ITEMS.register("egghammerhead", () -> new ItemSpawnEgg(0, 360));
    ITEMS.register("eggleon", () -> new ItemSpawnEgg(0, 358));
    ITEMS.register("eggcriminal", () -> new ItemSpawnEgg(0, 365));
    ITEMS.register("eggbrutalfly", () -> new ItemSpawnEgg(0, 367));
    ITEMS.register("eggnastysaurus", () -> new ItemSpawnEgg(0, 368));
    ITEMS.register("eggpointysaurus", () -> new ItemSpawnEgg(0, 369));
    ITEMS.register("eggcricket", () -> new ItemSpawnEgg(0, 370));
    ITEMS.register("eggtheprincess", () -> new ItemSpawnEgg(0, 371));
    ITEMS.register("eggfrog", () -> new ItemSpawnEgg(0, 372));
    ITEMS.register("eggrobot6", () -> new ItemSpawnEgg(0, 378));
    ITEMS.register("eggantrobot", () -> new ItemSpawnEgg(0, 379));
    ITEMS.register("eggspiderrobot", () -> new ItemSpawnEgg(0, 380));
    ITEMS.register("eggspiderdriver", () -> new ItemSpawnEgg(0, 381));
    ITEMS.register("eggcrab", () -> new ItemSpawnEgg(0, 383));
    ITEMS.register("eggrock", () -> new ItemSpawnEgg(0, 385, 1118481, 16777215));
  }

  private static void registerAllWeaponsAndArmor() {
    ITEMS.register("ingoturanium", () -> new IngotUranium());
    ITEMS.register("ingottitanium", () -> new IngotTitanium());
    ITEMS.register("crystalpink_ingot", () -> new IngotUranium());
    ITEMS.register("tigerseye_ingot", () -> new IngotUranium());
    ITEMS.register("ultimatesword", () -> new UltimateSword(toolULTIMATE));
    ITEMS.register("ultimatepickaxe", () -> new UltimatePickaxe(toolULTIMATE));
    ITEMS.register("ultimateshovel", () -> new UltimateShovel(toolULTIMATE));
    ITEMS.register("ultimatehoe", () -> new UltimateHoe(toolULTIMATE));
    ITEMS.register("ultimateaxe", () -> new UltimateAxe(toolULTIMATE));
    ITEMS.register("nightmaresword", () -> new NightmareSword(toolNIGHTMARE));
    ITEMS.register("berthasmall", () -> new Bertha(toolBERTHA));
    ITEMS.register("slicesmall", () -> new Bertha(toolBERTHA));
    ITEMS.register("royalsmall", () -> new Bertha(toolROYAL));
    ITEMS.register("hammysmall", () -> new Bertha(toolHAMMY));
    ITEMS.register("battleaxesmall", () -> new UltimateSword(toolBATTLE));
    ITEMS.register("chainsawsmall", () -> new UltimateSword(toolCHAINSAW));
    ITEMS.register("queenbattleaxesmall", () -> new UltimateSword(toolQUEENBATTLE));
    ITEMS.register("emeraldsword", () -> new EmeraldSword(toolEMERALD));
    ITEMS.register("emeraldpickaxe", () -> new EmeraldPickaxe(toolEMERALD));
    ITEMS.register("emeraldshovel", () -> new EmeraldShovel(toolEMERALD));
    ITEMS.register("emeraldhoe", () -> new EmeraldHoe(toolEMERALD));
    ITEMS.register("emeraldaxe", () -> new EmeraldAxe(toolEMERALD));
    ITEMS.register("experiencesword", () -> new ExperienceSword(toolEMERALD));
    ITEMS.register("poisonsword", () -> new PoisonSword(toolEMERALD));
    ITEMS.register("ratsword", () -> new RatSword(toolEMERALD));
    ITEMS.register("fairysword", () -> new FairySword(toolEMERALD));
    ITEMS.register("mantisclaw", () -> new MantisClaw(toolEMERALD));
    ITEMS.register("bighammer", () -> new BigHammer(toolAMETHYST));
    ITEMS.register("rubysword", () -> new RubySword(toolRUBY));
    ITEMS.register("rubypickaxe", () -> new RubyPickaxe(toolRUBY));
    ITEMS.register("rubyshovel", () -> new RubyShovel(toolRUBY));
    ITEMS.register("rubyhoe", () -> new RubyHoe(toolRUBY));
    ITEMS.register("rubyaxe", () -> new RubyAxe(toolRUBY));
    ITEMS.register("amethystsword", () -> new AmethystSword(toolAMETHYST));
    ITEMS.register("amethystpickaxe", () -> new AmethystPickaxe(toolAMETHYST));
    ITEMS.register("amethystshovel", () -> new AmethystShovel(toolAMETHYST));
    ITEMS.register("amethysthoe", () -> new AmethystHoe(toolAMETHYST));
    ITEMS.register("amethystaxe", () -> new AmethystAxe(toolAMETHYST));
    ITEMS.register("crystalwoodsword", () -> new CrystalSword(toolCRYSTALWOOD, 6));
    ITEMS.register("crystalwoodpickaxe", () -> new CrystalPickaxe(toolCRYSTALWOOD, 4));
    ITEMS.register("crystalwoodshovel", () -> new CrystalShovel(toolCRYSTALWOOD, 3));
    ITEMS.register("crystalwoodhoe", () -> new CrystalHoe(toolCRYSTALWOOD, 1));
    ITEMS.register("crystalwoodaxe", () -> new CrystalAxe(toolCRYSTALWOOD, 5));
    ITEMS.register("crystalpinksword", () -> new CrystalSword(toolCRYSTALPINK, 11));
    ITEMS.register("crystalpinkpickaxe", () -> new CrystalPickaxe(toolCRYSTALPINK, 9));
    ITEMS.register("crystalpinkshovel", () -> new CrystalShovel(toolCRYSTALPINK, 8));
    ITEMS.register("crystalpinkhoe", () -> new CrystalHoe(toolCRYSTALPINK, 1));
    ITEMS.register("crystalpinkaxe", () -> new CrystalAxe(toolCRYSTALPINK, 10));
    ITEMS.register("crystalstonesword", () -> new CrystalSword(toolCRYSTALSTONE, 9));
    ITEMS.register("crystalstonepickaxe", () -> new CrystalPickaxe(toolCRYSTALSTONE, 7));
    ITEMS.register("crystalstoneshovel", () -> new CrystalShovel(toolCRYSTALSTONE, 6));
    ITEMS.register("crystalstonehoe", () -> new CrystalHoe(toolCRYSTALSTONE, 1));
    ITEMS.register("crystalstoneaxe", () -> new CrystalAxe(toolCRYSTALSTONE, 8));
    ITEMS.register("tigerseye_sword", () -> new CrystalSword(toolTIGERSEYE, 12));
    ITEMS.register("tigerseye_pickaxe", () -> new CrystalPickaxe(toolTIGERSEYE, 10));
    ITEMS.register("tigerseye_shovel", () -> new CrystalShovel(toolTIGERSEYE, 9));
    ITEMS.register("tigerseye_hoe", () -> new CrystalHoe(toolTIGERSEYE, 1));
    ITEMS.register("tigerseye_axe", () -> new CrystalAxe(toolTIGERSEYE, 11));
    ITEMS.register("rosesword", () -> new EmeraldSword(toolEMERALD));
    ITEMS.register("redheels", () -> new ItemShoes(2));
    ITEMS.register("blackheels", () -> new ItemShoes(3));
    ITEMS.register("slippers", () -> new ItemShoes(4));
    ITEMS.register("boots", () -> new ItemShoes(5));
    ITEMS.register("gamecontroller", () -> new ItemShoes(6));
    ITEMS.register("ultimatebow", () -> new UltimateBow(BaseItemID + 303));
    ITEMS.register("skatebow", () -> new SkateBow(BaseItemID + 373));
    ITEMS.register("ultimatefishingrod", () -> new UltimateFishingRod(BaseItemID + 304));
    ITEMS.register("experiencecatcher", () -> new ExperienceCatcher(BaseItemID + 238));
    ITEMS.register("ultimate_helmet", () -> new ItemChaosArmor(armorULTIMATE, 0, 0));
    ITEMS.register("ultimate_chest", () -> new ItemChaosArmor(armorULTIMATE, 0, 1));
    ITEMS.register("ultimate_leggings", () -> new ItemChaosArmor(armorULTIMATE, 0, 2));
    ITEMS.register("ultimate_boots", () -> new ItemChaosArmor(armorULTIMATE, 0, 3));
    ITEMS.register("lavaeel_helmet", () -> new ItemChaosArmor(armorLAVAEEL, 0, 0));
    ITEMS.register("lavaeel_chest", () -> new ItemChaosArmor(armorLAVAEEL, 0, 1));
    ITEMS.register("lavaeel_leggings", () -> new ItemChaosArmor(armorLAVAEEL, 0, 2));
    ITEMS.register("lavaeel_boots", () -> new ItemChaosArmor(armorLAVAEEL, 0, 3));
    ITEMS.register("mothscale_helmet", () -> new ItemChaosArmor(armorMOTHSCALE, 0, 0));
    ITEMS.register("mothscale_chest", () -> new ItemChaosArmor(armorMOTHSCALE, 0, 1));
    ITEMS.register("mothscale_leggings", () -> new ItemChaosArmor(armorMOTHSCALE, 0, 2));
    ITEMS.register("mothscale_boots", () -> new ItemChaosArmor(armorMOTHSCALE, 0, 3));
    ITEMS.register("emerald_helmet", () -> new ItemChaosArmor(armorEMERALD, 0, 0));
    ITEMS.register("emerald_chest", () -> new ItemChaosArmor(armorEMERALD, 0, 1));
    ITEMS.register("emerald_leggings", () -> new ItemChaosArmor(armorEMERALD, 0, 2));
    ITEMS.register("emerald_boots", () -> new ItemChaosArmor(armorEMERALD, 0, 3));
    ITEMS.register("experience_helmet", () -> new ItemChaosArmor(armorEXPERIENCE, 0, 0));
    ITEMS.register("experience_chest", () -> new ItemChaosArmor(armorEXPERIENCE, 0, 1));
    ITEMS.register("experience_leggings", () -> new ItemChaosArmor(armorEXPERIENCE, 0, 2));
    ITEMS.register("experience_boots", () -> new ItemChaosArmor(armorEXPERIENCE, 0, 3));
    ITEMS.register("ruby_helmet", () -> new ItemChaosArmor(armorRUBY, 0, 0));
    ITEMS.register("ruby_chest", () -> new ItemChaosArmor(armorRUBY, 0, 1));
    ITEMS.register("ruby_leggings", () -> new ItemChaosArmor(armorRUBY, 0, 2));
    ITEMS.register("ruby_boots", () -> new ItemChaosArmor(armorRUBY, 0, 3));
    ITEMS.register("amethyst_helmet", () -> new ItemChaosArmor(armorAMETHYST, 0, 0));
    ITEMS.register("amethyst_chest", () -> new ItemChaosArmor(armorAMETHYST, 0, 1));
    ITEMS.register("amethyst_leggings", () -> new ItemChaosArmor(armorAMETHYST, 0, 2));
    ITEMS.register("amethyst_boots", () -> new ItemChaosArmor(armorAMETHYST, 0, 3));
    ITEMS.register("pink_helmet", () -> new ItemChaosArmor(armorPINK, 0, 0));
    ITEMS.register("pink_chest", () -> new ItemChaosArmor(armorPINK, 0, 1));
    ITEMS.register("pink_leggings", () -> new ItemChaosArmor(armorPINK, 0, 2));
    ITEMS.register("pink_boots", () -> new ItemChaosArmor(armorPINK, 0, 3));
    ITEMS.register("tigerseye_helmet", () -> new ItemChaosArmor(armorTIGERSEYE, 0, 0));
    ITEMS.register("tigerseye_chest", () -> new ItemChaosArmor(armorTIGERSEYE, 0, 1));
    ITEMS.register("tigerseye_leggings", () -> new ItemChaosArmor(armorTIGERSEYE, 0, 2));
    ITEMS.register("tigerseye_boots", () -> new ItemChaosArmor(armorTIGERSEYE, 0, 3));
    ITEMS.register("peacock_boots", () -> new ItemChaosArmor(armorPEACOCK, 0, 3));
    ITEMS.register("peacock_helmet", () -> new ItemChaosArmor(armorPEACOCK, 0, 0));
    ITEMS.register("peacock_chest", () -> new ItemChaosArmor(armorPEACOCK, 0, 1));
    ITEMS.register("peacock_leggings", () -> new ItemChaosArmor(armorPEACOCK, 0, 2));
    ITEMS.register("mobzilla_helmet", () -> new ItemChaosArmor(armorMOBZILLA, 0, 0));
    ITEMS.register("mobzilla_chest", () -> new ItemChaosArmor(armorMOBZILLA, 0, 1));
    ITEMS.register("mobzilla_leggings", () -> new ItemChaosArmor(armorMOBZILLA, 0, 2));
    ITEMS.register("mobzilla_boots", () -> new ItemChaosArmor(armorMOBZILLA, 0, 3));
    ITEMS.register("royal_helmet", () -> new ItemChaosArmor(armorROYAL, 0, 0));
    ITEMS.register("royal_chest", () -> new ItemChaosArmor(armorROYAL, 0, 1));
    ITEMS.register("royal_leggings", () -> new ItemChaosArmor(armorROYAL, 0, 2));
    ITEMS.register("royal_boots", () -> new ItemChaosArmor(armorROYAL, 0, 3));
    ITEMS.register("lapis_helmet", () -> new ItemChaosArmor(armorLAPIS, 0, 0));
    ITEMS.register("lapis_chest", () -> new ItemChaosArmor(armorLAPIS, 0, 1));
    ITEMS.register("lapis_leggings", () -> new ItemChaosArmor(armorLAPIS, 0, 2));
    ITEMS.register("lapis_boots", () -> new ItemChaosArmor(armorLAPIS, 0, 3));
    ITEMS.register("queen_helmet", () -> new ItemChaosArmor(armorQUEEN, 0, 0));
    ITEMS.register("queen_chest", () -> new ItemChaosArmor(armorQUEEN, 0, 1));
    ITEMS.register("queen_leggings", () -> new ItemChaosArmor(armorQUEEN, 0, 2));
    ITEMS.register("queen_boots", () -> new ItemChaosArmor(armorQUEEN, 0, 3));
  }

  private static void registerAllFoodItems() {
    ITEMS.register("firefish", () -> new ItemFireFish(4, 0.6F, false));
    ITEMS.register("sunfish", () -> new ItemSunFish(6, 0.6F, false));
    ITEMS.register("lavaeel", () -> new ItemLavaEel(2, 0.6F, false));
    ITEMS.register("sunspoturchin", () -> new ItemSunspotUrchin(BaseItemID + 246));
    ITEMS.register("popcorn", () -> new ItemPopcorn(1, 0.5F, false));
    ITEMS.register("popcorn_buttered", () -> new ItemPopcorn(2, 0.6F, false));
    ITEMS.register("popcorn_buttered_salted", () -> new ItemPopcorn(3, 0.75F, false));
    ITEMS.register("popcorn_bag", () -> new ItemPopcorn(10, 1.25F, false));
    ITEMS.register("butter", () -> new ItemPopcorn(1, 0.5F, false));
    ITEMS.register("corndog_cooked", () -> new ItemPopcorn(16, 2.5F, false));
    ITEMS.register("corndog_raw", () -> new ItemPopcorn(4, 0.6F, false));
    ITEMS.register("buttercandy", () -> new ItemSunFish(4, 0.5F, false));
    ITEMS.register("cookedbacon", () -> new ItemSunFish(14, 1.5F, false));
    ITEMS.register("bacon", () -> new ItemPopcorn(8, 1.0F, false));
    ITEMS.register("cookedcrabmeat", () -> new ItemSunFish(6, 0.75F, false));
    ITEMS.register("crabmeat", () -> new ItemPopcorn(4, 0.25F, false));
    ITEMS.register("cheese", () -> new ItemPopcorn(4, 0.5F, false));
    ITEMS.register("salad", () -> new ItemPopcorn(10, 0.95F, false));
    ITEMS.register("blt_sandwich", () -> new ItemPopcorn(12, 0.95F, false));
    ITEMS.register("crabbypatty", () -> new ItemPopcorn(16, 2.35F, false));
    ITEMS.register("cookedpeacock", () -> new ItemPopcorn(12, 1.4F, false));
    ITEMS.register("rawpeacock", () -> new ItemPopcorn(6, 0.7F, false));
    ITEMS.register("strawberry", () -> new ItemStrawberry(2, 0.65F, false));
    ITEMS.register("cherries", () -> new ItemStrawberry(3, 0.45F, false));
    ITEMS.register("peach", () -> new ItemStrawberry(4, 0.55F, false));
    ITEMS.register("crystalapple", () -> new ItemSunFish(5, 0.85F, false));
    ITEMS.register("heart", () -> new ItemSunFish(8, 0.95F, false));
  }

  private static void registerAllSaltMaterialItems() {
    ITEMS.register("mothscale", () -> new ItemSalt(BaseItemID + 156));
    ITEMS.register("queenscale", () -> new ItemSalt(BaseItemID + 453));
    ITEMS.register("nightmarescale", () -> new ItemSalt(BaseItemID + 158));
    ITEMS.register("emperorscorpionscale", () -> new ItemSalt(BaseItemID + 159));
    ITEMS.register("basiliskscale", () -> new ItemSalt(BaseItemID + 160));
    ITEMS.register("waterdragonscale", () -> new ItemSalt(BaseItemID + 161));
    ITEMS.register("peacockfeather", () -> new ItemSalt(BaseItemID + 255));
    ITEMS.register("jumpybugscale", () -> new ItemSalt(BaseItemID + 162));
    ITEMS.register("krakentooth", () -> new ItemSalt(BaseItemID + 163));
    ITEMS.register("godzillascale", () -> new ItemSalt(BaseItemID + 164));
    ITEMS.register("greengoo", () -> new ItemSalt(BaseItemID + 154));
    ITEMS.register("bbhandle", () -> new ItemSalt(BaseItemID + 406));
    ITEMS.register("bbguard", () -> new ItemSalt(BaseItemID + 407));
    ITEMS.register("bbblade", () -> new ItemSalt(BaseItemID + 408));
    ITEMS.register("molenoidnose", () -> new ItemSalt(BaseItemID + 409));
    ITEMS.register("seamonsterscale", () -> new ItemSalt(BaseItemID + 410));
    ITEMS.register("wormtooth", () -> new ItemSalt(BaseItemID + 411));
    ITEMS.register("trextooth", () -> new ItemSalt(BaseItemID + 412));
    ITEMS.register("caterkillerjaw", () -> new ItemSalt(BaseItemID + 413));
    ITEMS.register("seavipertongue", () -> new ItemSalt(BaseItemID + 414));
    ITEMS.register("vortexeye", () -> new ItemSalt(BaseItemID + 415));
    ITEMS.register("salt", () -> new ItemSalt(BaseItemID + 178));
    ITEMS.register("ruby", () -> new ItemSalt(BaseItemID + 270));
    ITEMS.register("amethyst", () -> new ItemSalt(BaseItemID + 260));
    ITEMS.register("uranium_nugget", () -> new ItemSalt(BaseItemID + 150));
    ITEMS.register("titanium_nugget", () -> new ItemSalt(BaseItemID + 151));
    ITEMS.register("deadstinkbug", () -> new ItemSalt(BaseItemID + 155));
  }

  public ChaosPersists() {
    instance = this;
    proxy =
        net.minecraftforge.fml.loading.FMLEnvironment.dist == net.minecraftforge.api.distmarker.Dist.CLIENT
            ? new com.astryxion.chaospersists.proxy.ClientProxyChaos()
            : new com.astryxion.chaospersists.proxy.CommonProxyChaos();
    IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    modBus.addListener(this::commonSetup);
    modBus.addListener(this::onEntityAttributeCreation);
    modBus.addListener(this::buildCreativeModeTabContents);
    // Never bind client-only listeners on dedicated server — @OnlyIn methods are stripped and
    // resolving them causes NoSuchMethodError (registerEntityRenderers crash).
    if (net.minecraftforge.fml.loading.FMLEnvironment.dist.isClient()) {
      com.astryxion.chaospersists.client.ClientModBusEvents.register(modBus, this);
    }
    MinecraftForge.EVENT_BUS.register(this);
    ensureEarlyConfigLoaded();
  }

  private static boolean earlyConfigLoaded = false;

  private static synchronized void ensureEarlyConfigLoaded() {
    if (earlyConfigLoaded) {
      return;
    }
    earlyConfigLoaded = true;
    loadEarlyMobConfig();
    loadEarlyWeaponArmorConfig();
    initToolAndArmorMaterialsIfNeeded();
  }

  private static void loadEarlyMobConfig() {
    Configuration config =
        new Configuration(
            net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR.get()
                .resolve("chaospersists.cfg")
                .toFile());
    config.load();
    getMobs(config, "chaospersistsMOBS");
    config.save();
  }

  private static void loadEarlyWeaponArmorConfig() {
    Configuration config =
        new Configuration(
            net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR.get()
                .resolve("chaospersists.cfg")
                .toFile());
    config.load();
    migrateLegacyAmethystArmorConfig(config);
    String weapons = "chaospersistsWEAPONS";
    Amethyst_armorstats = get_armorstats(config, "Amethyst", 100, 4, 8, 7, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
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
    config.save();
  }

  /** 1.20.1 armor toughness scaled by set tier (vanilla netherite = 3.0). */
  private static float armorToughnessFor(String set) {
    return switch (set) {
      case "PEACOCK" -> 0.5f;
      case "LAPIS" -> 0.5f;
      case "MOTHSCALE" -> 0.5f;
      case "LAVAEEL" -> 1.0f;
      case "PINK" -> 1.0f;
      case "EMERALD" -> 1.5f;
      case "EXPERIENCE" -> 2.0f;
      case "AMETHYST" -> 2.0f;
      case "TIGERSEYE" -> 2.0f;
      case "RUBY" -> 2.5f;
      case "ULTIMATE" -> 3.5f;
      case "MOBZILLA" -> 4.0f;
      case "ROYAL" -> 5.0f;
      case "QUEEN" -> 6.0f;
      default -> 0.0f;
    };
  }

  @SuppressWarnings("unchecked")
  private static void initToolAndArmorMaterialsIfNeeded() {
    if (toolULTIMATE != null) {
      return;
    }
    toolULTIMATE =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "ULTIMATE",
                    ultimate_stats.harvestlevel,
                    ultimate_stats.maxuses,
                    ultimate_stats.efficiency,
                    ultimate_stats.damage,
                    ultimate_stats.enchantability);
    toolNIGHTMARE =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "NIGHTMARE",
                    nightmare_stats.harvestlevel,
                    nightmare_stats.maxuses,
                    nightmare_stats.efficiency,
                    nightmare_stats.damage,
                    nightmare_stats.enchantability);
    toolEMERALD =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "REALEMERALD",
                    emerald_stats.harvestlevel,
                    emerald_stats.maxuses,
                    emerald_stats.efficiency,
                    emerald_stats.damage,
                    emerald_stats.enchantability);
    toolRUBY =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "RUBY",
                    ruby_stats.harvestlevel,
                    ruby_stats.maxuses,
                    ruby_stats.efficiency,
                    ruby_stats.damage,
                    ruby_stats.enchantability);
    toolAMETHYST =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "AMETHYST",
                    amethyst_stats.harvestlevel,
                    amethyst_stats.maxuses,
                    amethyst_stats.efficiency,
                    amethyst_stats.damage,
                    amethyst_stats.enchantability);
    toolBERTHA =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "BERTHA",
                    bertha_stats.harvestlevel,
                    bertha_stats.maxuses,
                    bertha_stats.efficiency,
                    bertha_stats.damage,
                    bertha_stats.enchantability);
    toolCRYSTALWOOD =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "CRYSTALWOOD",
                    crystalwood_stats.harvestlevel,
                    crystalwood_stats.maxuses,
                    crystalwood_stats.efficiency,
                    crystalwood_stats.damage,
                    crystalwood_stats.enchantability);
    toolCRYSTALSTONE =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "CRYSTALSTONE",
                    crystalstone_stats.harvestlevel,
                    crystalstone_stats.maxuses,
                    crystalstone_stats.efficiency,
                    crystalstone_stats.damage,
                    crystalstone_stats.enchantability);
    toolCRYSTALPINK =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "CRYSTALPINK",
                    crystalpink_stats.harvestlevel,
                    crystalpink_stats.maxuses,
                    crystalpink_stats.efficiency,
                    crystalpink_stats.damage,
                    crystalpink_stats.enchantability);
    toolTIGERSEYE =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "TIGERSEYE",
                    tigerseye_stats.harvestlevel,
                    tigerseye_stats.maxuses,
                    tigerseye_stats.efficiency,
                    tigerseye_stats.damage,
                    tigerseye_stats.enchantability);
    toolROYAL =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "ROYAL",
                    royal_stats.harvestlevel,
                    royal_stats.maxuses,
                    royal_stats.efficiency,
                    royal_stats.damage,
                    royal_stats.enchantability);
    toolHAMMY =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "HAMMY",
                    hammy_stats.harvestlevel,
                    hammy_stats.maxuses,
                    hammy_stats.efficiency,
                    hammy_stats.damage,
                    hammy_stats.enchantability);
    toolBATTLE =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "BATTLE",
                    battleaxe_stats.harvestlevel,
                    battleaxe_stats.maxuses,
                    battleaxe_stats.efficiency,
                    battleaxe_stats.damage,
                    battleaxe_stats.enchantability);
    toolCHAINSAW =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "CHAINSAW",
                    chainsaw_stats.harvestlevel,
                    chainsaw_stats.maxuses,
                    chainsaw_stats.efficiency,
                    chainsaw_stats.damage,
                    chainsaw_stats.enchantability);
    toolQUEENBATTLE =
        (Tier)
            (Object)
                EnumHelper.addToolMaterial(
                    "QUEENBATTLE",
                    queenbattleaxe_stats.harvestlevel,
                    queenbattleaxe_stats.maxuses,
                    queenbattleaxe_stats.efficiency,
                    queenbattleaxe_stats.damage,
                    queenbattleaxe_stats.enchantability);
    armorULTIMATE =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "ULTIMATE",
                    MODID,
                    Ultimate_armorstats.durability,
                    new int[] {
                      Ultimate_armorstats.head_protection,
                      Ultimate_armorstats.chest_protection,
                      Ultimate_armorstats.leg_protection,
                      Ultimate_armorstats.boot_protection
                    },
                    Ultimate_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("ULTIMATE"));
    armorMOBZILLA =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "MOBZILLA",
                    MODID,
                    Mobzilla_armorstats.durability,
                    new int[] {
                      Mobzilla_armorstats.head_protection,
                      Mobzilla_armorstats.chest_protection,
                      Mobzilla_armorstats.leg_protection,
                      Mobzilla_armorstats.boot_protection
                    },
                    Mobzilla_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("MOBZILLA"));
    armorLAVAEEL =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "LAVAEEL",
                    MODID,
                    LavaEel_armorstats.durability,
                    new int[] {
                      LavaEel_armorstats.head_protection,
                      LavaEel_armorstats.chest_protection,
                      LavaEel_armorstats.leg_protection,
                      LavaEel_armorstats.boot_protection
                    },
                    LavaEel_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("LAVAEEL"));
    armorMOTHSCALE =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "MOTHSCALE",
                    MODID,
                    MothScale_armorstats.durability,
                    new int[] {
                      MothScale_armorstats.head_protection,
                      MothScale_armorstats.chest_protection,
                      MothScale_armorstats.leg_protection,
                      MothScale_armorstats.boot_protection
                    },
                    MothScale_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("MOTHSCALE"));
    armorEMERALD =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "EMERALD",
                    MODID,
                    Emerald_armorstats.durability,
                    new int[] {
                      Emerald_armorstats.head_protection,
                      Emerald_armorstats.chest_protection,
                      Emerald_armorstats.leg_protection,
                      Emerald_armorstats.boot_protection
                    },
                    Emerald_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("EMERALD"));
    armorEXPERIENCE =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "EXPERIENCE",
                    MODID,
                    Experience_armorstats.durability,
                    new int[] {
                      Experience_armorstats.head_protection,
                      Experience_armorstats.chest_protection,
                      Experience_armorstats.leg_protection,
                      Experience_armorstats.boot_protection
                    },
                    Experience_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("EXPERIENCE"));
    armorRUBY =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "RUBY",
                    MODID,
                    Ruby_armorstats.durability,
                    new int[] {
                      Ruby_armorstats.head_protection,
                      Ruby_armorstats.chest_protection,
                      Ruby_armorstats.leg_protection,
                      Ruby_armorstats.boot_protection
                    },
                    Ruby_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("RUBY"));
    armorAMETHYST =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "AMETHYST",
                    MODID,
                    Amethyst_armorstats.durability,
                    new int[] {
                      Amethyst_armorstats.head_protection,
                      Amethyst_armorstats.chest_protection,
                      Amethyst_armorstats.leg_protection,
                      Amethyst_armorstats.boot_protection
                    },
                    Amethyst_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("AMETHYST"));
    armorPINK =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "PINK",
                    MODID,
                    Pink_armorstats.durability,
                    new int[] {
                      Pink_armorstats.head_protection,
                      Pink_armorstats.chest_protection,
                      Pink_armorstats.leg_protection,
                      Pink_armorstats.boot_protection
                    },
                    Pink_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("PINK"));
    armorTIGERSEYE =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "TIGERSEYE",
                    MODID,
                    TigersEye_armorstats.durability,
                    new int[] {
                      TigersEye_armorstats.head_protection,
                      TigersEye_armorstats.chest_protection,
                      TigersEye_armorstats.leg_protection,
                      TigersEye_armorstats.boot_protection
                    },
                    TigersEye_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("TIGERSEYE"));
    armorPEACOCK =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "PEACOCK",
                    MODID,
                    Peacock_armorstats.durability,
                    new int[] {
                      Peacock_armorstats.head_protection,
                      Peacock_armorstats.chest_protection,
                      Peacock_armorstats.leg_protection,
                      Peacock_armorstats.boot_protection
                    },
                    Peacock_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("PEACOCK"));
    armorROYAL =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "ROYAL",
                    MODID,
                    Royal_armorstats.durability,
                    new int[] {
                      Royal_armorstats.head_protection,
                      Royal_armorstats.chest_protection,
                      Royal_armorstats.leg_protection,
                      Royal_armorstats.boot_protection
                    },
                    Royal_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("ROYAL"));
    armorLAPIS =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "LAPIS",
                    MODID,
                    Lapis_armorstats.durability,
                    new int[] {
                      Lapis_armorstats.head_protection,
                      Lapis_armorstats.chest_protection,
                      Lapis_armorstats.leg_protection,
                      Lapis_armorstats.boot_protection
                    },
                    Lapis_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("LAPIS"));
    armorQUEEN =
        (ArmorMaterial)
            (Object)
                EnumHelper.addArmorMaterial(
                    "QUEEN",
                    MODID,
                    Queen_armorstats.durability,
                    new int[] {
                      Queen_armorstats.head_protection,
                      Queen_armorstats.chest_protection,
                      Queen_armorstats.leg_protection,
                      Queen_armorstats.boot_protection
                    },
                    Queen_armorstats.enchantability,
                    net.minecraft.sounds.SoundEvents.ARMOR_EQUIP_GENERIC,
                    armorToughnessFor("QUEEN"));
  }

  private void commonSetup(final FMLCommonSetupEvent event) {
    preInit(event);
    postInit(new FMLPostInitializationEvent());
  }

  /** Client-only FMLClientSetup; bound from {@link com.astryxion.chaospersists.client.ClientModBusEvents}. */
  @OnlyIn(Dist.CLIENT)
  public void clientInit(final net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent event) {
    event.enqueueWork(
        () -> {
          proxy.registerBlockRenderLayers();
          net.minecraft.client.gui.screens.MenuScreens.register(
              MENU_CRYSTAL_WORKBENCH.get(),
              com.astryxion.chaospersists.util.CrystalWorkbenchGUI::new);
          load(new FMLInitializationEvent());
        });
  }

  @SubscribeEvent
  public void onRegisterCommands(RegisterCommandsEvent event) {
    CommandUtopia.register(event.getDispatcher());
    CommandMining.register(event.getDispatcher());
    CommandVillageMania.register(event.getDispatcher());
    CommandDanger.register(event.getDispatcher());
    CommandCrystal.register(event.getDispatcher());
    CommandChaos.register(event.getDispatcher());
    CommandOverworld.register(event.getDispatcher());
    CommandChaosStructures.register(event.getDispatcher());
    serverStarting(new FMLServerStartingEvent(event));
  }

  private void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
    applyChaosCreativeTabs();
    ResourceKey<CreativeModeTab> tabKey = event.getTabKey();
    FeatureFlagSet flags = event.getFlags();
    java.util.List<Item> tabItems = new java.util.ArrayList<>();
    for (Item item : BuiltInRegistries.ITEM) {
      CreativeModeTab assigned = CreativeTabCompat.getCreativeTab(item);
      if (assigned == null) {
        continue;
      }
      if (BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(assigned).orElse(null) != tabKey) {
        continue;
      }
      if (!isValidCreativeTabItem(item, flags)) {
        continue;
      }
      tabItems.add(item);
    }
    if (chaosTabKey("chaos_weapons").equals(tabKey)) {
      tabItems.sort(chaosWeaponTabComparator());
    } else if (chaosTabKey("chaos_armor").equals(tabKey)) {
      tabItems.sort(chaosArmorTabComparator());
    } else {
      tabItems.sort(
          Comparator.comparing(
              item -> BuiltInRegistries.ITEM.getKey(item).toString(), Comparator.naturalOrder()));
    }
    for (Item item : tabItems) {
      event.accept(new ItemStack(item, 1));
    }
    normalizeCreativeTabEntryStacks(event);
  }

  private static boolean isValidCreativeTabItem(Item item, FeatureFlagSet flags) {
    if (item == null || item == Items.AIR) {
      return false;
    }
    if (item instanceof BlockItem blockItem) {
      Block block = blockItem.getBlock();
      if (block == null || block == Blocks.AIR) {
        return false;
      }
    }
    return item.isEnabled(flags);
  }

  private static boolean isValidCreativeTabStack(ItemStack stack, FeatureFlagSet flags) {
    if (stack.isEmpty() || stack.getCount() != 1) {
      return false;
    }
    return isValidCreativeTabItem(stack.getItem(), flags);
  }

  /** Forge creative tabs require stack size 1; drop broken BlockItems and fix counts. */
  private static void normalizeCreativeTabEntryStacks(BuildCreativeModeTabContentsEvent event) {
    var entries = event.getEntries();
    FeatureFlagSet flags = event.getFlags();
    java.util.List<java.util.Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> snapshot =
        new java.util.ArrayList<>();
    for (java.util.Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : entries) {
      snapshot.add(entry);
    }
    for (java.util.Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry : snapshot) {
      ItemStack stack = entry.getKey();
      if (!isValidCreativeTabStack(stack, flags)) {
        entries.remove(stack);
        continue;
      }
      if (stack.getCount() == 1) {
        continue;
      }
      entries.remove(stack);
      ItemStack fixed = stack.copy();
      fixed.setCount(1);
      entries.put(fixed, entry.getValue());
    }
  }

  public static ResourceKey<Level> getUtopiaDimensionKey() {
    return ResourceKey.create(
        Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "utopia"));
  }

  public static ResourceKey<Level> getMiningDimensionKey() {
    return ResourceKey.create(
        Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "mining"));
  }

  public static ResourceKey<Level> getVillageDimensionKey() {
    return ResourceKey.create(
        Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "village"));
  }

  public static ResourceKey<Level> getDangerDimensionKey() {
    return ResourceKey.create(
        Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "danger"));
  }

  public static ResourceKey<Level> getCrystalDimensionKey() {
    return ResourceKey.create(
        Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "crystal"));
  }

  public static ResourceKey<Level> getChaosDimensionKey() {
    return ResourceKey.create(
        Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(MODID, "chaos"));
  }

  /** @deprecated Use {@link #getDangerDimensionKey()}; stem renamed from {@code islands} to {@code danger}. */
  @Deprecated
  public static ResourceKey<Level> getIslandsDimensionKey() {
    return getDangerDimensionKey();
  }

  /** Legacy dimension index (1=Utopia, 2=Mining, 3=Village Mania, 4=Danger, 5=Crystal, 6=Chaos). */
  public static ResourceKey<Level> getDimensionKey(int n) {
    if (n == 2) {
      return getMiningDimensionKey();
    }
    if (n == 3) {
      return getVillageDimensionKey();
    }
    if (n == 4) {
      return getDangerDimensionKey();
    }
    if (n == 5) {
      return getCrystalDimensionKey();
    }
    if (n == 6) {
      return getChaosDimensionKey();
    }
    return getUtopiaDimensionKey();
  }

  public static int getDimension() {
    return DimensionID;
  }

  public static int getDimension(int n) {
    if (n == 2) {
      return DimensionID2;
    }
    if (n == 3) {
      return DimensionID3;
    }
    if (n == 4) {
      return DimensionID4;
    }
    if (n == 5) {
      return DimensionID5;
    }
    if (n == 6) {
      return DimensionID6;
    }
    return DimensionID;
  }

      public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
    event.put(ENTITY_TYPE_PURPLE_POWER.get(), PurplePower.createAttributes().build());
    event.put(ENTITY_TYPE_GIRLFRIEND.get(), Girlfriend.createAttributes().build());
    event.put(ENTITY_TYPE_RED_COW.get(), RedCow.createAttributes().build());
    event.put(ENTITY_TYPE_GOLD_COW.get(), RedCow.createAttributes().build());
    event.put(ENTITY_TYPE_ENCHANTED_COW.get(), RedCow.createAttributes().build());
    event.put(ENTITY_TYPE_CRYSTAL_COW.get(), RedCow.createAttributes().build());
    event.put(ENTITY_TYPE_BUTTERFLY.get(), EntityButterfly.createAttributes().build());
    event.put(ENTITY_TYPE_MOTH.get(), EntityLunaMoth.createAttributes().build());
    event.put(ENTITY_TYPE_MOSQUITO.get(), EntityMosquito.createAttributes().build());
    event.put(ENTITY_TYPE_FIREFLY.get(), Firefly.createAttributes().build());
    event.put(ENTITY_TYPE_BEE.get(), Bee.createAttributes().build());
    event.put(ENTITY_TYPE_MOTHRA.get(), Mothra.createAttributes().build());
    event.put(ENTITY_TYPE_ANT.get(), EntityAnt.createAttributes().build());
    event.put(ENTITY_TYPE_RED_ANT.get(), EntityRedAnt.createAttributes().build());
    event.put(ENTITY_TYPE_RAINBOW_ANT.get(), EntityRainbowAnt.createAttributes().build());
    event.put(ENTITY_TYPE_UNSTABLE_ANT.get(), EntityUnstableAnt.createAttributes().build());
    event.put(ENTITY_TYPE_ROBOT1.get(), Robot1.createAttributes().build());
    event.put(ENTITY_TYPE_ROBOT2.get(), Robot2.createAttributes().build());
    event.put(ENTITY_TYPE_ROBOT3.get(), Robot3.createAttributes().build());
    event.put(ENTITY_TYPE_ROBOT4.get(), Robot4.createAttributes().build());
    event.put(ENTITY_TYPE_ROBOT5.get(), Robot5.createAttributes().build());
    event.put(ENTITY_TYPE_ALOSAURUS.get(), Alosaurus.createAttributes().build());
    event.put(ENTITY_TYPE_CRYOLOPHOSAURUS.get(), Cryolophosaurus.createAttributes().build());
    event.put(ENTITY_TYPE_BASILISK.get(), Basilisk.createAttributes().build());
    event.put(ENTITY_TYPE_CAMARASAURUS.get(), Camarasaurus.createAttributes().build());
    event.put(ENTITY_TYPE_HYDROLISC.get(), Hydrolisc.createAttributes().build());
    event.put(ENTITY_TYPE_VELOCITY_RAPTOR.get(), VelocityRaptor.createAttributes().build());
    event.put(ENTITY_TYPE_DRAGONFLY.get(), Dragonfly.createAttributes().build());
    event.put(ENTITY_TYPE_EMPEROR_SCORPION.get(), EmperorScorpion.createAttributes().build());
    event.put(ENTITY_TYPE_SCORPION.get(), Scorpion.createAttributes().build());
    event.put(ENTITY_TYPE_CAVE_FISHER.get(), CaveFisher.createAttributes().build());
    event.put(ENTITY_TYPE_BABY_DRAGON.get(), Spyro.createAttributes().build());
    event.put(ENTITY_TYPE_BARYONYX.get(), Baryonyx.createAttributes().build());
    event.put(ENTITY_TYPE_GAMMA_METROID.get(), GammaMetroid.createAttributes().build());
    event.put(ENTITY_TYPE_WTF.get(), GammaMetroid.createAttributes().build());
    event.put(ENTITY_TYPE_BIRD.get(), Cockateil.createAttributes().build());
    event.put(ENTITY_TYPE_RUBY_BIRD.get(), RubyBird.createAttributes().build());
    event.put(ENTITY_TYPE_KYUUBI.get(), Kyuubi.createAttributes().build());
    event.put(ENTITY_TYPE_WATER_DRAGON.get(), WaterDragon.createAttributes().build());
    event.put(ENTITY_TYPE_ATTACK_SQUID.get(), AttackSquid.createAttributes().build());
    event.put(ENTITY_TYPE_ALIEN.get(), Alien.createAttributes().build());
    event.put(ENTITY_TYPE_ELEVATOR.get(), Elevator.createAttributes().build());
    event.put(ENTITY_TYPE_THE_KRAKEN.get(), Kraken.createAttributes().build());
    event.put(ENTITY_TYPE_LIZARD.get(), Lizard.createAttributes().build());
    event.put(ENTITY_TYPE_CEPHADROME.get(), Cephadrome.createAttributes().build());
    event.put(ENTITY_TYPE_DRAGON.get(), Dragon.createAttributes().build());
    event.put(ENTITY_TYPE_CHIPMUNK.get(), Chipmunk.createAttributes().build());
    event.put(ENTITY_TYPE_GAZELLE.get(), Gazelle.createAttributes().build());
    event.put(ENTITY_TYPE_OSTRICH.get(), Ostrich.createAttributes().build());
    event.put(ENTITY_TYPE_TROOPER_BUG.get(), TrooperBug.createAttributes().build());
    event.put(ENTITY_TYPE_SPIT_BUG.get(), SpitBug.createAttributes().build());
    event.put(ENTITY_TYPE_STINK_BUG.get(), StinkBug.createAttributes().build());
    event.put(ENTITY_TYPE_TSHIRT.get(), Tshirt.createAttributes().build());
    event.put(ENTITY_TYPE_ISLAND.get(), Island.createAttributes().build());
    event.put(ENTITY_TYPE_ISLAND_TOO.get(), IslandToo.createAttributes().build());
    event.put(ENTITY_TYPE_CREEPING_HORROR.get(), CreepingHorror.createAttributes().build());
    event.put(ENTITY_TYPE_TERRIBLE_TERROR.get(), TerribleTerror.createAttributes().build());
    event.put(ENTITY_TYPE_CLIFF_RACER.get(), CliffRacer.createAttributes().build());
    event.put(ENTITY_TYPE_TRIFFID.get(), Triffid.createAttributes().build());
    event.put(ENTITY_TYPE_NIGHTMARE.get(), PitchBlack.createAttributes().build());
    event.put(ENTITY_TYPE_LURKING_TERROR.get(), LurkingTerror.createAttributes().build());
    event.put(ENTITY_TYPE_MOBZILLA.get(), Godzilla.createAttributes().build());
    event.put(ENTITY_TYPE_GHOST.get(), Ghost.createAttributes().build());
    event.put(ENTITY_TYPE_GHOST_PUMPKIN_SKELLY.get(), GhostSkelly.createAttributes().build());
    event.put(ENTITY_TYPE_SMALL_WORM.get(), WormSmall.createAttributes().build());
    event.put(ENTITY_TYPE_MEDIUM_WORM.get(), WormMedium.createAttributes().build());
    event.put(ENTITY_TYPE_LARGE_WORM.get(), WormLarge.createAttributes().build());
    event.put(ENTITY_TYPE_CASSOWARY.get(), Cassowary.createAttributes().build());
    event.put(ENTITY_TYPE_CLOUD_SHARK.get(), CloudShark.createAttributes().build());
    event.put(ENTITY_TYPE_GOLD_FISH.get(), GoldFish.createAttributes().build());
    event.put(ENTITY_TYPE_LEAF_MONSTER.get(), LeafMonster.createAttributes().build());
    event.put(ENTITY_TYPE_MOBZILLA_HEAD.get(), GodzillaHead.createAttributes().build());
    event.put(ENTITY_TYPE_ENDER_KNIGHT.get(), EnderKnight.createAttributes().build());
    event.put(ENTITY_TYPE_ENDER_REAPER.get(), EnderReaper.createAttributes().build());
    event.put(ENTITY_TYPE_BEAVER.get(), Beaver.createAttributes().build());
    event.put(ENTITY_TYPE_TERMITE.get(), Termite.createAttributes().build());
    event.put(ENTITY_TYPE_FAIRY.get(), Fairy.createAttributes().build());
    event.put(ENTITY_TYPE_PEACOCK.get(), Peacock.createAttributes().build());
    event.put(ENTITY_TYPE_ROTATOR.get(), Rotator.createAttributes().build());
    event.put(ENTITY_TYPE_VORTEX.get(), Vortex.createAttributes().build());
    event.put(ENTITY_TYPE_DUNGEON_BEAST.get(), DungeonBeast.createAttributes().build());
    event.put(ENTITY_TYPE_RAT.get(), Rat.createAttributes().build());
    event.put(ENTITY_TYPE_FLOUNDER.get(), Flounder.createAttributes().build());
    event.put(ENTITY_TYPE_WHALE.get(), Whale.createAttributes().build());
    event.put(ENTITY_TYPE_IRUKANDJI.get(), Irukandji.createAttributes().build());
    event.put(ENTITY_TYPE_SKATE.get(), Skate.createAttributes().build());
    event.put(ENTITY_TYPE_URCHIN.get(), Urchin.createAttributes().build());
    event.put(ENTITY_TYPE_MANTIS.get(), Mantis.createAttributes().build());
    event.put(ENTITY_TYPE_HERCULES_BEETLE.get(), HerculesBeetle.createAttributes().build());
    event.put(ENTITY_TYPE_TREX.get(), TRex.createAttributes().build());
    event.put(ENTITY_TYPE_T_REX.get(), TRex.createAttributes().build());
    event.put(ENTITY_TYPE_STINKY.get(), Stinky.createAttributes().build());
    event.put(ENTITY_TYPE_COIN.get(), Coin.createAttributes().build());
    event.put(ENTITY_TYPE_THE_KING.get(), TheKing.createAttributes().build());
    event.put(ENTITY_TYPE_KING_HEAD.get(), KingHead.createAttributes().build());
    event.put(ENTITY_TYPE_THE_QUEEN.get(), TheQueen.createAttributes().build());
    event.put(ENTITY_TYPE_QUEEN_HEAD.get(), QueenHead.createAttributes().build());
    event.put(ENTITY_TYPE_BOYFRIEND.get(), Boyfriend.createAttributes().build());
    event.put(ENTITY_TYPE_THE_PRINCE.get(), ThePrince.createAttributes().build());
    event.put(ENTITY_TYPE_MOLENOID.get(), Molenoid.createAttributes().build());
    event.put(ENTITY_TYPE_SEA_MONSTER.get(), SeaMonster.createAttributes().build());
    event.put(ENTITY_TYPE_SEA_VIPER.get(), SeaViper.createAttributes().build());
    event.put(ENTITY_TYPE_EASTER_BUNNY.get(), EasterBunny.createAttributes().build());
    event.put(ENTITY_TYPE_CATERKILLER.get(), CaterKiller.createAttributes().build());
    event.put(ENTITY_TYPE_LEONOPTERYX.get(), Leon.createAttributes().build());
    event.put(ENTITY_TYPE_HAMMERHEAD.get(), Hammerhead.createAttributes().build());
    event.put(ENTITY_TYPE_RUBBER_DUCKY.get(), RubberDucky.createAttributes().build());
    event.put(ENTITY_TYPE_THE_YOUNG_PRINCE.get(), ThePrinceTeen.createAttributes().build());
    event.put(ENTITY_TYPE_CRIMINAL.get(), BandP.createAttributes().build());
    event.put(ENTITY_TYPE_ROCK.get(), RockBase.createAttributes().build());
    event.put(ENTITY_TYPE_BRUTALFLY.get(), Brutalfly.createAttributes().build());
    event.put(ENTITY_TYPE_NASTYSAURUS.get(), Nastysaurus.createAttributes().build());
    event.put(ENTITY_TYPE_POINTYSAURUS.get(), Pointysaurus.createAttributes().build());
    event.put(ENTITY_TYPE_CRICKET.get(), Cricket.createAttributes().build());
    event.put(ENTITY_TYPE_THE_PRINCESS.get(), ThePrincess.createAttributes().build());
    event.put(ENTITY_TYPE_FROG.get(), Frog.createAttributes().build());
    event.put(ENTITY_TYPE_THE_YOUNG_ADULT_PRINCE.get(), ThePrinceAdult.createAttributes().build());
    event.put(ENTITY_TYPE_SPIDER_ROBOT.get(), SpiderRobot.createAttributes().build());
    event.put(ENTITY_TYPE_SPIDER_DRIVER.get(), net.minecraft.world.entity.monster.Spider.createAttributes().build());
    event.put(ENTITY_TYPE_GIANT_ROBOT.get(), GiantRobot.createAttributes().build());
    event.put(ENTITY_TYPE_ANT_ROBOT.get(), AntRobot.createAttributes().build());
    event.put(ENTITY_TYPE_CRAB.get(), Crab.createAttributes().build());
  }

    @OnlyIn(Dist.CLIENT)
  public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    final ResourceLocation texLaserBall = ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/item/laserball.png");
    final ResourceLocation texIceBall = ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/item/iceball.png");
    final ResourceLocation texAcid = ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/item/acid.png");
    final ResourceLocation texDeadIruk = ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/item/deadirukandji.png");
    final ResourceLocation texThunderBolt =
            ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/entity/thunder_bolt.png");
    final ResourceLocation texArrow =
            ResourceLocation.withDefaultNamespace("textures/entity/projectiles/arrow.png");
    event.registerEntityRenderer(ENTITY_TYPE_ACID.get(), ctx -> new RenderThrowableBillboard(ctx, texAcid));
    event.registerEntityRenderer(ENTITY_TYPE_BETTER_FIREBALL.get(), ctx -> new ThrownItemRenderer<>(ctx, 3.0F, true));
    event.registerEntityRenderer(ENTITY_TYPE_ALIEN.get(), ctx -> new RenderAlien(ctx, new ModelAlien(0.22f), 0.35f, 1.1f));
    event.registerEntityRenderer(ENTITY_TYPE_ALOSAURUS.get(), ctx -> new RenderAlosaurus(ctx, new ModelAlosaurus(0.22f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ANT.get(), ctx -> new RenderAnt(ctx, new ModelAnt(), 0.1f, 0.25f));
    event.registerEntityRenderer(ENTITY_TYPE_ANT_ROBOT.get(), ctx -> new RenderAntRobot(ctx, new ModelAntRobot(1.0f), 0.99f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ATTACK_SQUID.get(), ctx -> new RenderAttackSquid(ctx, new ModelAttackSquid(1.0f), 0.25f, 0.9f));
    event.registerEntityRenderer(ENTITY_TYPE_BABY_DRAGON.get(), ctx -> new RenderSpyro(ctx, new ModelSpyro(0.65f), 0.65f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_BARYONYX.get(), ctx -> new RenderBaryonyx(ctx, new ModelBaryonyx(0.25f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_BASILISK.get(), ctx -> new RenderBasilisk(ctx, new ModelBasilisk(0.3f), 0.5f, 1.25f));
    event.registerEntityRenderer(ENTITY_TYPE_BEAVER.get(), ctx -> new RenderBeaver(ctx, new ModelBeaver(0.5f), 0.15f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_BEE.get(), ctx -> new RenderBee(ctx, new ModelBee(2.0f), 0.9f, 1.1f));
    event.registerEntityRenderer(ENTITY_TYPE_BERTHA_HIT.get(), ctx -> new RenderItemUrchin(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_BIRD.get(), ctx -> new RenderCockateil(ctx, new ModelCockateil(1.0f), 0.3f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_BOYFRIEND.get(), ctx -> new RenderBoyfriend(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_BRUTALFLY.get(), ctx -> new RenderBrutalfly(ctx, new ModelBrutalfly(0.2f), 0.75f, 9.0f));
    event.registerEntityRenderer(ENTITY_TYPE_BUTTERFLY.get(), ctx -> new RenderButterfly(ctx, new ModelButterfly(1.0f), 0.3f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_CAGE.get(), ctx -> new RenderCage(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_CAMARASAURUS.get(), ctx -> new RenderCamarasaurus(ctx, new ModelCamarasaurus(0.65f), 0.65f, 0.65f));
    event.registerEntityRenderer(ENTITY_TYPE_CASSOWARY.get(), ctx -> new RenderCassowary(ctx, new ModelCassowary(0.55f), 0.5f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_CATERKILLER.get(), ctx -> new RenderCaterKiller(ctx, new ModelCaterKiller(0.22f), 1.0f, 1.25f));
    event.registerEntityRenderer(ENTITY_TYPE_CAVE_FISHER.get(), ctx -> new RenderCaveFisher(ctx, new ModelCaveFisher(), 0.35f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_CEPHADROME.get(), ctx -> new RenderCephadrome(ctx, new ModelCephadrome(0.55f), 1.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_CHIPMUNK.get(), ctx -> new RenderChipmunk(ctx, new ModelChipmunk(1.0f), 0.15f, 0.9f));
    event.registerEntityRenderer(ENTITY_TYPE_CLIFF_RACER.get(), ctx -> new RenderCliffRacer(ctx, new ModelCliffRacer(1.0f), 0.3f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_CLOUD_SHARK.get(), ctx -> new RenderCloudShark(ctx, new ModelCloudShark(1.0f), 0.5f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_COIN.get(), ctx -> new RenderCoin(ctx, new ModelCoin(0.22f), 0.75f, 0.125f));
    event.registerEntityRenderer(ENTITY_TYPE_CRAB.get(), ctx -> new RenderCrab(ctx, new ModelCrab(), 0.99f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_CREEPING_HORROR.get(), ctx -> new RenderCreepingHorror(ctx, new ModelCreepingHorror(0.75f), 0.45f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_CRICKET.get(), ctx -> new RenderCricket(ctx, new ModelCricket(2.5f), 0.15f, 0.5f));
    event.registerEntityRenderer(ENTITY_TYPE_CRIMINAL.get(), ctx -> new RenderBandP(ctx, new ModelBandP(0.4f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_CRYOLOPHOSAURUS.get(), ctx -> new RenderCryolophosaurus(ctx, new ModelCryolophosaurus(0.75f), 0.75f, 0.5f));
    event.registerEntityRenderer(ENTITY_TYPE_CRYSTAL_COW.get(), ctx -> new RenderEnchantedCow(ctx, new net.minecraft.client.model.CowModel<>(ctx.bakeLayer(net.minecraft.client.model.geom.ModelLayers.COW)), 0.7f));
    event.registerEntityRenderer(ENTITY_TYPE_DEAD_IRUKANDJI.get(), ctx -> new RenderThrowableBillboard(ctx, texDeadIruk));
    event.registerEntityRenderer(
            ENTITY_TYPE_IRUKANDJI_ARROW.get(),
            ctx ->
                    new ArrowRenderer<IrukandjiArrow>(ctx) {
                        @Override
                        public ResourceLocation getTextureLocation(IrukandjiArrow entity) {
                            return texArrow;
                        }
                    });
    event.registerEntityRenderer(ENTITY_TYPE_DRAGON.get(), ctx -> new RenderDragon(ctx, new ModelDragon(0.65f), 1.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_DRAGONFLY.get(), ctx -> new RenderDragonfly(ctx, new ModelDragonfly(2.0f), 0.3f, 1.5f));
    event.registerEntityRenderer(ENTITY_TYPE_DUNGEON_BEAST.get(), ctx -> new RenderDungeonBeast(ctx, new ModelDungeonBeast(0.62f), 0.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_EASTER_BUNNY.get(), ctx -> new RenderEasterBunny(ctx, new ModelEasterBunny(0.55f), 0.5f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ELEVATOR.get(), ctx -> new RenderElevator(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_EMPEROR_SCORPION.get(), ctx -> new RenderEmperorScorpion(ctx, new ModelEmperorScorpion(0.22f), 0.95f, 1.5f));
    event.registerEntityRenderer(ENTITY_TYPE_ENCHANTED_COW.get(), ctx -> new RenderEnchantedCow(ctx, new net.minecraft.client.model.CowModel<>(ctx.bakeLayer(net.minecraft.client.model.geom.ModelLayers.COW)), 0.7f));
    event.registerEntityRenderer(ENTITY_TYPE_ENDER_KNIGHT.get(), ctx -> new RenderEnderKnight(ctx, new ModelEnderKnight(0.21f), 0.3f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ENDER_REAPER.get(), ctx -> new RenderEnderReaper(ctx, new ModelEnderReaper(0.23f), 0.2f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_FAIRY.get(), ctx -> new RenderFairy(ctx, new ModelFairy(1.5f), 0.1f, 0.35f));
    event.registerEntityRenderer(ENTITY_TYPE_FIREFLY.get(), ctx -> new RenderFirefly(ctx, new ModelFirefly(2.5f), 0.2f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_FLOUNDER.get(), ctx -> new RenderFlounder(ctx, new ModelFlounder(), 0.1f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_FROG.get(), ctx -> new RenderFrog(ctx, new ModelFrog(1.0f), 0.35f, 1.0f));
    event.registerEntityRenderer(
            ENTITY_TYPE_GAMMA_METROID.get(),
            ctx -> new RenderGammaMetroid(ctx, new ModelGammaMetroid(0.45f), 0.75f, 0.9f));
    event.registerEntityRenderer(ENTITY_TYPE_GAZELLE.get(), ctx -> new RenderGazelle(ctx, new ModelGazelle(0.65f), 0.45f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_GHOST.get(), ctx -> new RenderGhost(ctx, new ModelGhost(), 0.0f, 0.65f));
    event.registerEntityRenderer(ENTITY_TYPE_GHOST_PUMPKIN_SKELLY.get(), ctx -> new RenderGhostSkelly(ctx, new ModelGhostSkelly(), 0.0f, 1.05f));
    event.registerEntityRenderer(ENTITY_TYPE_GIANT_ROBOT.get(), ctx -> new RenderGiantRobot(ctx, new ModelGiantRobot(0.25f), 0.99f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_GIRLFRIEND.get(), ctx -> new RenderGirlfriend(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_GOLD_COW.get(), ctx -> new RenderEnchantedCow(ctx, new net.minecraft.client.model.CowModel<>(ctx.bakeLayer(net.minecraft.client.model.geom.ModelLayers.COW)), 0.7f));
    event.registerEntityRenderer(ENTITY_TYPE_GOLD_FISH.get(), ctx -> new RenderGoldFish(ctx, new ModelGoldFish(0.7f), 0.2f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_HAMMERHEAD.get(), ctx -> new RenderHammerhead(ctx, new ModelHammerhead(0.33f), 1.0f, 2.5f));
    event.registerEntityRenderer(ENTITY_TYPE_HERCULES_BEETLE.get(), ctx -> new RenderHerculesBeetle(ctx, new ModelHerculesBeetle(1.0f), 0.99f, 1.1f));
    event.registerEntityRenderer(ENTITY_TYPE_HYDROLISC.get(), ctx -> new RenderHydrolisc(ctx, new ModelHydrolisc(0.65f), 0.65f, 0.65f));
    event.registerEntityRenderer(ENTITY_TYPE_ICE_BALL.get(), ctx -> new RenderThrowableBillboard(ctx, texIceBall));
    event.registerEntityRenderer(ENTITY_TYPE_INK_SACK.get(), ctx -> new RenderItemUrchin(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_IRUKANDJI.get(), ctx -> new RenderIrukandji(ctx, new ModelIrukandji(), 0.1f, 0.25f));
    event.registerEntityRenderer(ENTITY_TYPE_ISLAND.get(), ctx -> new RenderIsland(ctx, new ModelIsland(1.0f), 0.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ISLAND_TOO.get(), ctx -> new RenderIslandToo(ctx, new ModelIsland(1.0f), 0.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_KING_HEAD.get(), ctx -> new RenderKingHead(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_KYUUBI.get(), ctx -> new RenderKyuubi(ctx, new ModelKyuubi(0.5f), 0.1f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_LARGE_WORM.get(), ctx -> new RenderWormLarge(ctx, new ModelWormLarge(), 0.9f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_LASER_BALL.get(), ctx -> new RenderThrowableBillboard(ctx, texLaserBall));
    event.registerEntityRenderer(ENTITY_TYPE_LEAF_MONSTER.get(), ctx -> new RenderLeafMonster(ctx, new ModelLeafMonster(1.0f), 0.65f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_LEONOPTERYX.get(), ctx -> new RenderLeon(ctx, new ModelLeon(0.22f), 1.0f, 1.75f));
    event.registerEntityRenderer(ENTITY_TYPE_LIZARD.get(), ctx -> new RenderLizard(ctx, new ModelLizard(0.65f), 0.75f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_LURKING_TERROR.get(), ctx -> new RenderLurkingTerror(ctx, new ModelLurkingTerror(0.85f), 0.45f, 0.85f));
    event.registerEntityRenderer(ENTITY_TYPE_MANTIS.get(), ctx -> new RenderMantis(ctx, new ModelMantis(2.0f), 0.9f, 1.1f));
    event.registerEntityRenderer(ENTITY_TYPE_MEDIUM_WORM.get(), ctx -> new RenderWormMedium(ctx, new ModelWormMedium(), 0.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_MOBZILLA.get(), ctx -> new RenderGodzilla(ctx, new ModelGodzilla(0.2f), 1.0f, 2.0f));
    event.registerEntityRenderer(ENTITY_TYPE_MOBZILLA_HEAD.get(), ctx -> new RenderGodzillaHead(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_MOLENOID.get(), ctx -> new RenderMolenoid(ctx, new ModelMolenoid(0.5f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_MOSQUITO.get(), ctx -> new RenderMosquito(ctx, new ModelMosquito(), 0.3f, 0.5f));
    event.registerEntityRenderer(ENTITY_TYPE_MOTH.get(), ctx -> new RenderButterfly(ctx, new ModelButterfly(0.75f), 0.4f, 1.5f));
    event.registerEntityRenderer(ENTITY_TYPE_MOTHRA.get(), ctx -> new RenderButterfly(ctx, new ModelButterfly(0.2f), 0.75f, 10.0f));
    event.registerEntityRenderer(ENTITY_TYPE_NASTYSAURUS.get(), ctx -> new RenderNastysaurus(ctx, new ModelNastysaurus(0.65f), 1.0f, 1.5f));
    event.registerEntityRenderer(ENTITY_TYPE_NIGHTMARE.get(), ctx -> new RenderPitchBlack(ctx, new ModelPitchBlack(0.65f), 1.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_OSTRICH.get(), ctx -> new RenderOstrich(ctx, new ModelOstrich(0.65f), 0.55f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_PEACOCK.get(), ctx -> new RenderPeacock(ctx, new ModelPeacock(0.75f), 0.25f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_POINTYSAURUS.get(), ctx -> new RenderPointysaurus(ctx, new ModelPointysaurus(1.0f), 1.0f, 1.0f));
    // 1.7 registered 2.75 but never wired preRenderCallback, so beams drew at scale 1.0.
    event.registerEntityRenderer(ENTITY_TYPE_PURPLE_POWER.get(), ctx -> new RenderPurplePower(ctx, new ModelPurplePower(1.0f), 0.3f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_QUEEN_HEAD.get(), ctx -> new RenderQueenHead(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_RAINBOW_ANT.get(), ctx -> new RenderAnt(ctx, new ModelAnt(), 0.1f, 0.25f));
    event.registerEntityRenderer(ENTITY_TYPE_RAT.get(), ctx -> new RenderRat(ctx, new ModelRat(1.0f), 0.1f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_RED_ANT.get(), ctx -> new RenderAnt(ctx, new ModelAnt(), 0.15f, 0.35f));
    event.registerEntityRenderer(ENTITY_TYPE_RED_COW.get(), ctx -> new RenderEnchantedCow(ctx, new net.minecraft.client.model.CowModel<>(ctx.bakeLayer(net.minecraft.client.model.geom.ModelLayers.COW)), 0.7f));
    event.registerEntityRenderer(ENTITY_TYPE_ROBOT1.get(), ctx -> new RenderRobot1(ctx, new ModelRobot1(2.0f), 0.3f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ROBOT2.get(), ctx -> new RenderRobot2(ctx, new ModelRobot2(1.0f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ROBOT3.get(), ctx -> new RenderRobot3(ctx, new ModelRobot3(1.0f), 1.0f, 0.5f));
    event.registerEntityRenderer(ENTITY_TYPE_ROBOT4.get(), ctx -> new RenderRobot4(ctx, new ModelRobot4(1.0f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ROBOT5.get(), ctx -> new RenderRobot5(ctx, new ModelRobot5(1.0f), 0.5f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ROCK.get(), ctx -> new RenderRockBase(ctx, new ModelRockBase(1.0f), 0.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_ROTATOR.get(), ctx -> new RenderRotator(ctx, new ModelRotator(0.25f), 0.1f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_RUBBER_DUCKY.get(), ctx -> new RenderRubberDucky(ctx, new ModelRubberDucky(1.0f), 0.15f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_RUBY_BIRD.get(), ctx -> new RenderCockateil(ctx, new ModelCockateil(1.0f), 0.3f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_SCORPION.get(), ctx -> new RenderScorpion(ctx, new ModelScorpion(), 0.35f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_SEA_MONSTER.get(), ctx -> new RenderSeaMonster(ctx, new ModelSeaMonster(0.5f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_SEA_VIPER.get(), ctx -> new RenderSeaViper(ctx, new ModelSeaViper(0.5f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_SHOES.get(), ctx -> new RenderShoe(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_SKATE.get(), ctx -> new RenderSkate(ctx, new ModelSkate(), 0.1f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_SMALL_WORM.get(), ctx -> new RenderWormSmall(ctx, new ModelWormSmall(), 0.1f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_SPIDER_DRIVER.get(), ctx -> new RenderSpiderDriver(ctx, 0.5f));
    event.registerEntityRenderer(ENTITY_TYPE_SPIDER_ROBOT.get(), ctx -> new RenderSpiderRobot(ctx, new ModelSpiderRobot(1.0f), 0.99f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_SPIT_BUG.get(), ctx -> new RenderSpitBug(ctx, new ModelSpitBug(0.55f), 0.55f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_STINK_BUG.get(), ctx -> new RenderStinkBug(ctx, new ModelStinkBug(0.75f), 0.35f, 0.85f));
    event.registerEntityRenderer(ENTITY_TYPE_STINKY.get(), ctx -> new RenderStinky(ctx, new ModelStinky(0.65f), 0.75f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_SUNSPOT_URCHIN.get(), ctx -> new RenderItemUrchin(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_T_REX.get(), ctx -> new RenderTRex(ctx, new ModelTRex(0.2f), 1.0f, 1.2f));
    event.registerEntityRenderer(ENTITY_TYPE_TREX.get(), ctx -> new RenderTRex(ctx, new ModelTRex(0.2f), 1.0f, 1.2f));
    event.registerEntityRenderer(ENTITY_TYPE_TERMITE.get(), ctx -> new RenderAnt(ctx, new ModelAnt(), 0.15f, 0.35f));
    event.registerEntityRenderer(ENTITY_TYPE_TERRIBLE_TERROR.get(), ctx -> new RenderTerribleTerror(ctx, new ModelTerribleTerror(0.75f), 0.45f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_KING.get(), ctx -> new RenderTheKing(ctx, new ModelTheKing(0.65f), 1.9f, 2.1f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_KRAKEN.get(), ctx -> new RenderKraken(ctx, new ModelKraken(1.0f), 1.0f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_PRINCE.get(), ctx -> new RenderThePrince(ctx, new ModelThePrince(0.65f), 0.75f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_PRINCESS.get(), ctx -> new RenderThePrincess(ctx, new ModelThePrincess(0.65f), 0.7f, 0.7f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_QUEEN.get(), ctx -> new RenderTheQueen(ctx, new ModelTheQueen(0.65f), 1.9f, 2.0f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_YOUNG_ADULT_PRINCE.get(), ctx -> new RenderThePrinceAdult(ctx, new ModelThePrinceAdult(0.65f), 1.2f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_THE_YOUNG_PRINCE.get(), ctx -> new RenderThePrinceTeen(ctx, new ModelThePrinceTeen(0.65f), 1.0f, 1.25f));
    event.registerEntityRenderer(ENTITY_TYPE_THROWN_ROCK.get(), ctx -> new RenderThrownRock(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_THUNDER_BOLT.get(), ctx -> new RenderThrowableBillboard(ctx, texThunderBolt));
    event.registerEntityRenderer(ENTITY_TYPE_TRIFFID.get(), ctx -> new RenderTriffid(ctx, new ModelTriffid(1.0f), 0.3f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_TROOPER_BUG.get(), ctx -> new RenderTrooperBug(ctx, new ModelTrooperBug(0.22f), 0.95f, 1.1f));
    event.registerEntityRenderer(ENTITY_TYPE_TSHIRT.get(), ctx -> new RenderTshirt(ctx, new ModelTshirt(0.22f), 1.0f, 0.33f));
    event.registerEntityRenderer(ENTITY_TYPE_ULTIMATE_ARROW.get(), ctx -> new RenderUltimateArrow(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_ULTIMATE_FISH_HOOK.get(), ctx -> new com.astryxion.chaospersists.render.RenderUltimateFishHook(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_UNSTABLE_ANT.get(), ctx -> new RenderAnt(ctx, new ModelAnt(), 0.1f, 0.25f));
    event.registerEntityRenderer(ENTITY_TYPE_URCHIN.get(), ctx -> new RenderUrchin(ctx, new ModelUrchin(1.0f), 0.35f, 1.25f));
    event.registerEntityRenderer(ENTITY_TYPE_VELOCITY_RAPTOR.get(), ctx -> new RenderVelocityRaptor(ctx, new ModelVelocityRaptor(1.25f), 0.55f, 0.75f));
    event.registerEntityRenderer(ENTITY_TYPE_VORTEX.get(), ctx -> new RenderVortex(ctx, new ModelVortex(0.25f), 0.1f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_WATER_BALL.get(), ctx -> new RenderItemUrchin(ctx));
    event.registerEntityRenderer(ENTITY_TYPE_WATER_DRAGON.get(), ctx -> new RenderWaterDragon(ctx, new ModelWaterDragon(0.5f), 0.85f, 1.1f));
    event.registerEntityRenderer(ENTITY_TYPE_WHALE.get(), ctx -> new RenderWhale(ctx, new ModelWhale(), 0.1f, 1.0f));
    event.registerEntityRenderer(ENTITY_TYPE_WTF.get(), ctx -> new RenderGammaMetroid(ctx, new ModelGammaMetroid(0.45f), 0.75f, 0.9f));
  }


    public static com.astryxion.chaospersists.proxy.CommonProxyChaos proxy;

  public static ChaosPersists instance;
  public static com.astryxion.chaospersists.util.KeyHandler MyKeyhandler = null;
  public static int flyup_keystate = 0;

  public static int BaseBlockID = 2700;
  public static int BaseItemID = 9000;
  public static int BaseBiomeID = 120;
  public static int BiomeUtopiaID = 0;
  public static int BiomeIslandsID = 0;
  public static int BiomeCrystalID = 0;
  public static int BiomeVillageID = 0;
  public static int BiomeChaosID = 0;
  public static int BiomeMiningID = 0;
  public static int DimensionID = 0;
  public static int DimensionID2 = 0;
  public static int DimensionID3 = 0;
  public static int DimensionID4 = 0;
  public static int DimensionID5 = 0;
  public static int DimensionID6 = 0;

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
  public static Block MyDeepslateOreUraniumBlock;
  public static Block MyOreTitaniumBlock;
  public static Block MyDeepslateOreTitaniumBlock;
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
  static Tier toolULTIMATE;
  static Tier toolNIGHTMARE;
  static Tier toolBERTHA;
  static Tier toolCRYSTALWOOD;
  static Tier toolCRYSTALSTONE;
  static Tier toolCRYSTALPINK;
  static Tier toolTIGERSEYE;
  static Tier toolRUBY;
  static Tier toolAMETHYST;
  static Tier toolEMERALD;
  static Tier toolROYAL;
  static Tier toolHAMMY;
  static Tier toolBATTLE;
  static Tier toolCHAINSAW;
  static Tier toolQUEENBATTLE;
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
  public static ArmorMaterial armorULTIMATE;
  public static ArmorMaterial armorMOBZILLA;
  public static ArmorMaterial armorLAVAEEL;
  public static ArmorMaterial armorMOTHSCALE;
  public static ArmorMaterial armorEMERALD;
  public static ArmorMaterial armorEXPERIENCE;
  public static ArmorMaterial armorRUBY;
  public static ArmorMaterial armorAMETHYST;
  public static ArmorMaterial armorPINK;
  public static ArmorMaterial armorTIGERSEYE;
  public static ArmorMaterial armorPEACOCK;
  public static ArmorMaterial armorROYAL;
  public static ArmorMaterial armorLAPIS;
  public static ArmorMaterial armorQUEEN;
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
  public static Block MyDeepslateOreSaltBlock;
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
  public static Block MyDeepslateOreRubyBlock;
  public static Item MyRuby;
  public static Item MyBacon;
  public static Item MyRawBacon;
  public static Item MyCrabMeat;
  public static Item MyRawCrabMeat;
  public static Item MyButterCandy;
  public static Block MyOreAmethystBlock;
  public static Block MyDeepslateOreAmethystBlock;
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
  public static Block DeepslateRedAntTroll;
  public static Block DeepslateTermiteTroll;
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
  public static Item RockEgg;
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
  public static final java.util.function.Supplier<BlockExtremeTorch> BLOCK_EXTREME_TORCH = () -> (BlockExtremeTorch) ExtremeTorch;
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

  /** Raises vanilla {@code generic.maxHealth} cap (1024 in 1.20.1) so 1.7.10-scale boss HP applies. */
  private static void raiseVanillaMaxHealthCap()
  {
    try
    {
      Attribute attr = net.minecraft.world.entity.ai.attributes.Attributes.MAX_HEALTH;
      if (!(attr instanceof RangedAttribute))
      {
        return;
      }
      RangedAttribute ranged = (RangedAttribute)attr;
      if (ranged.getMaxValue() >= 1.0E8D)
      {
        return;
      }
      if (!setRangedAttributeMaxValue(ranged, 1.0E9D))
      {
        LOGGER.error("ChaosPersists: failed to raise generic.maxHealth cap; boss HP may stay capped at 1024");
        return;
      }
      LOGGER.info("ChaosPersists: raised generic.maxHealth cap to {}", ranged.getMaxValue());
    }
    catch (Throwable t)
    {
      LOGGER.error("ChaosPersists: failed to raise generic.maxHealth cap; boss HP may stay capped at 1024", t);
    }
  }

  private static boolean setRangedAttributeMaxValue(RangedAttribute ranged, double newMax)
  {
    try
    {
      Field unsafeField = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
      unsafeField.setAccessible(true);
      sun.misc.Unsafe unsafe = (sun.misc.Unsafe)unsafeField.get(null);
      Field maxValueField = RangedAttribute.class.getDeclaredField("maxValue");
      unsafe.putDouble(ranged, unsafe.objectFieldOffset(maxValueField), newMax);
      return Math.abs(ranged.getMaxValue() - newMax) < 1.0E-3D;
    }
    catch (Throwable ignored)
    {
    }
    try
    {
      MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(RangedAttribute.class, MethodHandles.lookup());
      VarHandle maxHealthCap = lookup.findVarHandle(RangedAttribute.class, "maxValue", double.class);
      maxHealthCap.set(ranged, newMax);
      return Math.abs(ranged.getMaxValue() - newMax) < 1.0E-3D;
    }
    catch (Throwable ignored)
    {
    }
    try
    {
      Field target = null;
      for (Field f : RangedAttribute.class.getDeclaredFields())
      {
        if (f.getType() != double.class || !Modifier.isFinal(f.getModifiers()))
        {
          continue;
        }
        f.setAccessible(true);
        double v = f.getDouble(ranged);
        if (Math.abs(v - 1024.0D) < 1.0E-6D || Math.abs(v - ranged.getMaxValue()) < 1.0E-6D)
        {
          target = f;
          break;
        }
      }
      if (target == null)
      {
        return false;
      }
      try
      {
        Field modifiers = Field.class.getDeclaredField("modifiers");
        modifiers.setAccessible(true);
        modifiers.setInt(target, target.getModifiers() & ~Modifier.FINAL);
      }
      catch (Throwable ignored)
      {
      }
      target.setDouble(ranged, newMax);
      return Math.abs(ranged.getMaxValue() - newMax) < 1.0E-3D;
    }
    catch (Throwable ignored)
    {
      return false;
    }
  }

  public void preInit(FMLCommonSetupEvent event)
  {
    raiseVanillaMaxHealthCap();
    Configuration config = new Configuration(FMLPaths.CONFIGDIR.get().resolve("chaospersists.cfg").toFile());
    String ids = "chaospersistsIDS";
    String mobs = "chaospersistsMOBS";
    String tweaks = "chaospersistsTWEAKS";
    String weapons = "chaospersistsWEAPONS";
    String ores = "chaospersistsORES";

    config.load();

    migrateLegacyAmethystArmorConfig(config);

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

    // OreSpawn wiki / 1.7.10 Amethyst defaults: 4/8/7/3 defense, durability 100, enchantability 40.
    Amethyst_armorstats = get_armorstats(config, "Amethyst", 100, 4, 8, 7, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
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
    MinecraftForge.EVENT_BUS.register(instance);
    MinecraftForge.EVENT_BUS.register(chaospersistsGen);

    proxy.registerSoundThings();

    laySomeEggs();

    MyOreUraniumBlock = (OreUranium) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "oreuranium"));
    MyDeepslateOreUraniumBlock =
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oreuranium"));
    MyOreTitaniumBlock = (OreTitanium) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "oretitanium"));
    MyDeepslateOreTitaniumBlock =
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oretitanium"));
    MyIngotUranium = (IngotUranium) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ingoturanium"));
    MyIngotTitanium = (IngotTitanium) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ingottitanium"));
    MyBlockUraniumBlock = (BlockUranium) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockuranium"));
    MyBlockTitaniumBlock = (BlockTitanium) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blocktitanium"));
    MyBlockMobzillaScaleBlock = (BlockRuby) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockmobzillascale"));
    MyLavafoamBlock = (Lavafoam) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "lavafoam"));
    MyBlockRubyBlock = (BlockRuby) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockruby"));
    MyBlockAmethystBlock = (BlockRuby) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockamethyst"));
    MyCrystalPinkBlock = (BlockCrystal) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpink_block"));
    MyCrystalPinkIngot = (IngotUranium) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpink_ingot"));
    MyTigersEyeBlock = (BlockCrystal) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_block"));
    MyTigersEyeIngot = (IngotUranium) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_ingot"));

    MyPizzaBlock = (BlockPizza) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "pizza"));
    MyPizzaItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "pizza"));
    MyDuctTapeBlock = (BlockDuctTape) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "ducttape"));
    MyDuctTapeItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ducttape"));

    toolULTIMATE = EnumHelper.addToolMaterial("ULTIMATE", ultimate_stats.harvestlevel, ultimate_stats.maxuses, ultimate_stats.efficiency, ultimate_stats.damage, ultimate_stats.enchantability);

    toolNIGHTMARE = EnumHelper.addToolMaterial("NIGHTMARE", nightmare_stats.harvestlevel, nightmare_stats.maxuses, nightmare_stats.efficiency, nightmare_stats.damage, nightmare_stats.enchantability);

    toolEMERALD = EnumHelper.addToolMaterial("REALEMERALD", emerald_stats.harvestlevel, emerald_stats.maxuses, emerald_stats.efficiency, emerald_stats.damage, emerald_stats.enchantability);

    toolRUBY = EnumHelper.addToolMaterial("RUBY", ruby_stats.harvestlevel, ruby_stats.maxuses, ruby_stats.efficiency, ruby_stats.damage, ruby_stats.enchantability);

    toolAMETHYST = EnumHelper.addToolMaterial("AMETHYST", amethyst_stats.harvestlevel, amethyst_stats.maxuses, amethyst_stats.efficiency, amethyst_stats.damage, amethyst_stats.enchantability);

    toolBERTHA = EnumHelper.addToolMaterial("BERTHA", bertha_stats.harvestlevel, bertha_stats.maxuses, bertha_stats.efficiency, bertha_stats.damage, bertha_stats.enchantability);

    toolCRYSTALWOOD = EnumHelper.addToolMaterial("CRYSTALWOOD", crystalwood_stats.harvestlevel, crystalwood_stats.maxuses, crystalwood_stats.efficiency, crystalwood_stats.damage, crystalwood_stats.enchantability);

    toolCRYSTALSTONE = EnumHelper.addToolMaterial("CRYSTALSTONE", crystalstone_stats.harvestlevel, crystalstone_stats.maxuses, crystalstone_stats.efficiency, crystalstone_stats.damage, crystalstone_stats.enchantability);

    toolCRYSTALPINK = EnumHelper.addToolMaterial("CRYSTALPINK", crystalpink_stats.harvestlevel, crystalpink_stats.maxuses, crystalpink_stats.efficiency, crystalpink_stats.damage, crystalpink_stats.enchantability);

    toolTIGERSEYE = EnumHelper.addToolMaterial("TIGERSEYE", tigerseye_stats.harvestlevel, tigerseye_stats.maxuses, tigerseye_stats.efficiency, tigerseye_stats.damage, tigerseye_stats.enchantability);

    toolROYAL = EnumHelper.addToolMaterial("ROYAL", royal_stats.harvestlevel, royal_stats.maxuses, royal_stats.efficiency, royal_stats.damage, royal_stats.enchantability);

    toolHAMMY = EnumHelper.addToolMaterial("HAMMY", hammy_stats.harvestlevel, hammy_stats.maxuses, hammy_stats.efficiency, hammy_stats.damage, hammy_stats.enchantability);

    toolBATTLE = EnumHelper.addToolMaterial("BATTLE", battleaxe_stats.harvestlevel, battleaxe_stats.maxuses, battleaxe_stats.efficiency, battleaxe_stats.damage, battleaxe_stats.enchantability);

    toolCHAINSAW = EnumHelper.addToolMaterial("CHAINSAW", chainsaw_stats.harvestlevel, chainsaw_stats.maxuses, chainsaw_stats.efficiency, chainsaw_stats.damage, chainsaw_stats.enchantability);

    toolQUEENBATTLE = EnumHelper.addToolMaterial("QUEENBATTLE", queenbattleaxe_stats.harvestlevel, queenbattleaxe_stats.maxuses, queenbattleaxe_stats.efficiency, queenbattleaxe_stats.damage, queenbattleaxe_stats.enchantability);

    MyUltimateSword = (UltimateSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimatesword"));
    MyUltimatePickaxe = (UltimatePickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimatepickaxe"));
    MyUltimateShovel = (UltimateShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimateshovel"));
    MyUltimateHoe = (UltimateHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimatehoe"));
    MyUltimateAxe = (UltimateAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimateaxe"));
    MyNightmareSword = (NightmareSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "nightmaresword"));
    MyBertha = (Bertha) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "berthasmall"));
    MySlice = (Bertha) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "slicesmall"));
    MyRoyal = (Bertha) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "royalsmall"));
    MyHammy = (Bertha) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "hammysmall"));
    MyBattleAxe = (UltimateSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "battleaxesmall"));
    MyChainsaw = (UltimateSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "chainsawsmall"));
    MyQueenBattleAxe = (UltimateSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "queenbattleaxesmall"));

    MyEmeraldSword = (EmeraldSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emeraldsword"));
    MyEmeraldPickaxe = (EmeraldPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emeraldpickaxe"));
    MyEmeraldShovel = (EmeraldShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emeraldshovel"));
    MyEmeraldHoe = (EmeraldHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emeraldhoe"));
    MyEmeraldAxe = (EmeraldAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emeraldaxe"));
    MyExperienceSword = (ExperienceSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experiencesword"));
    MyPoisonSword = (PoisonSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "poisonsword"));
    MyRatSword = (RatSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ratsword"));
    MyFairySword = (FairySword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "fairysword"));
    MyMantisClaw = (MantisClaw) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mantisclaw"));
    MyBigHammer = (BigHammer) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bighammer"));
    MyRubySword = (RubySword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rubysword"));
    MyRubyPickaxe = (RubyPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rubypickaxe"));
    MyRubyShovel = (RubyShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rubyshovel"));
    MyRubyHoe = (RubyHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rubyhoe"));
    MyRubyAxe = (RubyAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rubyaxe"));
    MyAmethystSword = (AmethystSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethystsword"));
    MyAmethystPickaxe = (AmethystPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethystpickaxe"));
    MyAmethystShovel = (AmethystShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethystshovel"));
    MyAmethystHoe = (AmethystHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethysthoe"));
    MyAmethystAxe = (AmethystAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethystaxe"));
    MyCrystalWoodSword = (CrystalSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalwoodsword"));
    MyCrystalWoodPickaxe = (CrystalPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalwoodpickaxe"));
    MyCrystalWoodShovel = (CrystalShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalwoodshovel"));
    MyCrystalWoodHoe = (CrystalHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalwoodhoe"));
    MyCrystalWoodAxe = (CrystalAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalwoodaxe"));
    MyCrystalPinkSword = (CrystalSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpinksword"));
    MyCrystalPinkPickaxe = (CrystalPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpinkpickaxe"));
    MyCrystalPinkShovel = (CrystalShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpinkshovel"));
    MyCrystalPinkHoe = (CrystalHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpinkhoe"));
    MyCrystalPinkAxe = (CrystalAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalpinkaxe"));
    MyCrystalStoneSword = (CrystalSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalstonesword"));
    MyCrystalStonePickaxe = (CrystalPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalstonepickaxe"));
    MyCrystalStoneShovel = (CrystalShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalstoneshovel"));
    MyCrystalStoneHoe = (CrystalHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalstonehoe"));
    MyCrystalStoneAxe = (CrystalAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalstoneaxe"));
    MyTigersEyeSword = (CrystalSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_sword"));
    MyTigersEyePickaxe = (CrystalPickaxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_pickaxe"));
    MyTigersEyeShovel = (CrystalShovel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_shovel"));
    MyTigersEyeHoe = (CrystalHoe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_hoe"));
    MyTigersEyeAxe = (CrystalAxe) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_axe"));
    MyRoseSword = (EmeraldSword) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rosesword"));

    MyItemShoes = (ItemShoes) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "redheels"));
    MyItemShoes_1 = (ItemShoes) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "blackheels"));
    MyItemShoes_2 = (ItemShoes) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "slippers"));
    MyItemShoes_3 = (ItemShoes) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "boots"));
    MyItemGameController = (ItemShoes) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "gamecontroller"));

    MyUltimateBow = (UltimateBow) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimatebow"));
    MySkateBow = (SkateBow) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "skatebow"));

    MyUltimateFishingRod = (UltimateFishingRod) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimatefishingrod"));
    UltimateFishingRod = new ItemStack(MyUltimateFishingRod);

    MyFireFish = (ItemFireFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "firefish"));
    MySunFish = (ItemSunFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "sunfish"));
    MyLavaEel = (ItemLavaEel) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lavaeel"));
    MyMothScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mothscale"));
    MyQueenScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "queenscale"));
    MyNightmareScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "nightmarescale"));
    MyEmperorScorpionScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emperorscorpionscale"));
    MyBasiliskScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "basiliskscale"));
    MyWaterDragonScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "waterdragonscale"));
    MyPeacockFeather = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peacockfeather"));
    MyJumpyBugScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "jumpybugscale"));
    MyKrakenTooth = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "krakentooth"));
    MyGodzillaScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "godzillascale"));
    GreenGoo = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "greengoo"));
    SpiderRobotKit = (ItemSpiderRobotKit) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "spiderrobotkit"));
    AntRobotKit = (ItemSpiderRobotKit) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "antrobotkit"));
    ZooKeeper = (ItemZooKeeper) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "zookeeper"));
    CreeperLauncher = (ItemCreeperLauncher) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "creeperlauncher"));
    NetherLost = (ItemNetherLost) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "netherlost"));
    CrystalSticks = (ItemCrystalSticks) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalsticks"));
    MySunspotUrchin = (ItemSunspotUrchin) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "sunspoturchin"));
    MySparkFish = (ItemSparkFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "sparkfish"));
    MyWaterBall = (ItemWaterBall) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "waterball"));
    MyLaserBall = (ItemLaserBall) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "laserball"));
    MyIceBall = (ItemIceBall) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "iceball"));
    MySmallRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rocksmall"));
    MyRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rock"));
    MyRedRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockred"));
    MyCrystalRedRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockcrystalred"));
    MyCrystalGreenRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockcrystalgreen"));
    MyCrystalBlueRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockcrystalblue"));
    MyCrystalTNTRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockcrystaltnt"));
    MyGreenRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockgreen"));
    MyBlueRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockblue"));
    MyPurpleRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockpurple"));
    MySpikeyRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockspikey"));
    MyTNTRock = (ItemRock) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rocktnt"));
    MyRayGun = (ItemRayGun) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "raygun"));
    MyThunderStaff = (ItemThunderStaff) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "thunderstaff"));
    MyWrench = (ItemWrench) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "wrench"));
    MyAcid = (ItemAcid) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "acid"));
    MyIrukandji = (ItemIrukandji) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "deadirukandji"));
    MyIrukandjiArrow = (ItemIrukandjiArrow) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "irukandjiarrow"));
    MyGreenFish = (ItemGenericFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "greenfish"));
    MyBlueFish = (ItemGenericFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bluefish"));
    MyPinkFish = (ItemGenericFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "pinkfish"));
    MyRockFish = (ItemGenericFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rockfish"));
    MyWoodFish = (ItemGenericFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "woodfish"));
    MyGreyFish = (ItemGenericFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "greyfish"));
    Sifter = (ItemSifter) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "sifter"));
    MySquidZooka = (ItemSquidZooka) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "squidzookasmall"));

    BerthaHandle = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bbhandle"));
    BerthaGuard = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bbguard"));
    BerthaBlade = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bbblade"));
    MolenoidNose = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "molenoidnose"));
    SeaMonsterScale = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "seamonsterscale"));
    WormTooth = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "wormtooth"));
    TRexTooth = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "trextooth"));
    CaterKillerJaw = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "caterkillerjaw"));
    SeaViperTongue = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "seavipertongue"));
    VortexEye = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "vortexeye"));

    armorULTIMATE = EnumHelper.addArmorMaterial("ULTIMATE", "chaospersists", Ultimate_armorstats.durability, new int[] { Ultimate_armorstats.head_protection, Ultimate_armorstats.chest_protection, Ultimate_armorstats.leg_protection, Ultimate_armorstats.boot_protection }, Ultimate_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("ULTIMATE"));

    armorMOBZILLA = EnumHelper.addArmorMaterial("MOBZILLA", "chaospersists", Mobzilla_armorstats.durability, new int[] { Mobzilla_armorstats.head_protection, Mobzilla_armorstats.chest_protection, Mobzilla_armorstats.leg_protection, Mobzilla_armorstats.boot_protection }, Mobzilla_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("MOBZILLA"));

    armorLAVAEEL = EnumHelper.addArmorMaterial("LAVAEEL", "chaospersists", LavaEel_armorstats.durability, new int[] { LavaEel_armorstats.head_protection, LavaEel_armorstats.chest_protection, LavaEel_armorstats.leg_protection, LavaEel_armorstats.boot_protection }, LavaEel_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("LAVAEEL"));

    armorMOTHSCALE = EnumHelper.addArmorMaterial("MOTHSCALE", "chaospersists", MothScale_armorstats.durability, new int[] { MothScale_armorstats.head_protection, MothScale_armorstats.chest_protection, MothScale_armorstats.leg_protection, MothScale_armorstats.boot_protection }, MothScale_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("MOTHSCALE"));

    armorEMERALD = EnumHelper.addArmorMaterial("EMERALD", "chaospersists", Emerald_armorstats.durability, new int[] { Emerald_armorstats.head_protection, Emerald_armorstats.chest_protection, Emerald_armorstats.leg_protection, Emerald_armorstats.boot_protection }, Emerald_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("EMERALD"));

    armorEXPERIENCE = EnumHelper.addArmorMaterial("EXPERIENCE", "chaospersists", Experience_armorstats.durability, new int[] { Experience_armorstats.head_protection, Experience_armorstats.chest_protection, Experience_armorstats.leg_protection, Experience_armorstats.boot_protection }, Experience_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("EXPERIENCE"));

    armorRUBY = EnumHelper.addArmorMaterial("RUBY", "chaospersists", Ruby_armorstats.durability, new int[] { Ruby_armorstats.head_protection, Ruby_armorstats.chest_protection, Ruby_armorstats.leg_protection, Ruby_armorstats.boot_protection }, Ruby_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("RUBY"));

    armorAMETHYST = EnumHelper.addArmorMaterial("AMETHYST", "chaospersists", Amethyst_armorstats.durability, new int[] { Amethyst_armorstats.head_protection, Amethyst_armorstats.chest_protection, Amethyst_armorstats.leg_protection, Amethyst_armorstats.boot_protection }, Amethyst_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("AMETHYST"));

    armorPINK = EnumHelper.addArmorMaterial("PINK", "chaospersists", Pink_armorstats.durability, new int[] { Pink_armorstats.head_protection, Pink_armorstats.chest_protection, Pink_armorstats.leg_protection, Pink_armorstats.boot_protection }, Pink_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("PINK"));

    armorTIGERSEYE = EnumHelper.addArmorMaterial("TIGERSEYE", "chaospersists", TigersEye_armorstats.durability, new int[] { TigersEye_armorstats.head_protection, TigersEye_armorstats.chest_protection, TigersEye_armorstats.leg_protection, TigersEye_armorstats.boot_protection }, TigersEye_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("TIGERSEYE"));

    armorPEACOCK = EnumHelper.addArmorMaterial("PEACOCK", "chaospersists", Peacock_armorstats.durability, new int[] { Peacock_armorstats.head_protection, Peacock_armorstats.chest_protection, Peacock_armorstats.leg_protection, Peacock_armorstats.boot_protection }, Peacock_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("PEACOCK"));

    armorROYAL = EnumHelper.addArmorMaterial("ROYAL", "chaospersists", Royal_armorstats.durability, new int[] { Royal_armorstats.head_protection, Royal_armorstats.chest_protection, Royal_armorstats.leg_protection, Royal_armorstats.boot_protection }, Royal_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("ROYAL"));

    armorLAPIS = EnumHelper.addArmorMaterial("LAPIS", "chaospersists", Lapis_armorstats.durability, new int[] { Lapis_armorstats.head_protection, Lapis_armorstats.chest_protection, Lapis_armorstats.leg_protection, Lapis_armorstats.boot_protection }, Lapis_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("LAPIS"));

    armorQUEEN = EnumHelper.addArmorMaterial("QUEEN", "chaospersists", Queen_armorstats.durability, new int[] { Queen_armorstats.head_protection, Queen_armorstats.chest_protection, Queen_armorstats.leg_protection, Queen_armorstats.boot_protection }, Queen_armorstats.enchantability, SoundEvents.ARMOR_EQUIP_GENERIC, armorToughnessFor("QUEEN"));

    UltimateHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimate_helmet"));
    UltimateBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimate_chest"));
    UltimateLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimate_leggings"));
    UltimateBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ultimate_boots"));
    LavaEelHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lavaeel_helmet"));
    LavaEelBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lavaeel_chest"));
    LavaEelLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lavaeel_leggings"));
    LavaEelBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lavaeel_boots"));
    MothScaleHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mothscale_helmet"));
    MothScaleBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mothscale_chest"));
    MothScaleLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mothscale_leggings"));
    MothScaleBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mothscale_boots"));
    EmeraldHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emerald_helmet"));
    EmeraldBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emerald_chest"));
    EmeraldLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emerald_leggings"));
    EmeraldBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "emerald_boots"));
    ExperienceHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experience_helmet"));
    ExperienceBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experience_chest"));
    ExperienceLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experience_leggings"));
    ExperienceBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experience_boots"));
    RubyHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ruby_helmet"));
    RubyBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ruby_chest"));
    RubyLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ruby_leggings"));
    RubyBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ruby_boots"));
    AmethystHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethyst_helmet"));
    AmethystBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethyst_chest"));
    AmethystLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethyst_leggings"));
    AmethystBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethyst_boots"));
    CrystalPinkHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "pink_helmet"));
    CrystalPinkBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "pink_chest"));
    CrystalPinkLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "pink_leggings"));
    CrystalPinkBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "pink_boots"));
    TigersEyeHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_helmet"));
    TigersEyeBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_chest"));
    TigersEyeLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_leggings"));
    TigersEyeBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye_boots"));
    PeacockFeatherBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peacock_boots"));
    PeacockFeatherHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peacock_helmet"));
    PeacockFeatherBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peacock_chest"));
    PeacockFeatherLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peacock_leggings"));
    MobzillaHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mobzilla_helmet"));
    MobzillaBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mobzilla_chest"));
    MobzillaLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mobzilla_leggings"));
    MobzillaBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mobzilla_boots"));
    RoyalHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "royal_helmet"));
    RoyalBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "royal_chest"));
    RoyalLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "royal_leggings"));
    RoyalBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "royal_boots"));
    LapisHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lapis_helmet"));
    LapisBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lapis_chest"));
    LapisLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lapis_leggings"));
    LapisBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lapis_boots"));
    QueenHelmet = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "queen_helmet"));
    QueenBody = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "queen_chest"));
    QueenLegs = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "queen_leggings"));
    QueenBoots = (ItemChaosArmor) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "queen_boots"));

    MyOreSaltBlock = (OreSalt) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "oresalt"));
    MyDeepslateOreSaltBlock =
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oresalt"));
    MySalt = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "salt"));
    MyPopcorn = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "popcorn"));
    MyButteredPopcorn = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "popcorn_buttered"));
    MyButteredSaltedPopcorn = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "popcorn_buttered_salted"));
    MyPopcornBag = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "popcorn_bag"));
    MyButter = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "butter"));
    MyCornDog = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "corndog_cooked"));
    MyRawCornDog = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "corndog_raw"));
    MyButterCandy = (ItemSunFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "buttercandy"));
    MyBacon = (ItemSunFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cookedbacon"));
    MyRawBacon = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "bacon"));
    MyCrabMeat = (ItemSunFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cookedcrabmeat"));
    MyRawCrabMeat = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crabmeat"));
    MyCheese = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cheese"));
    MySalad = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "salad"));
    MyBLT = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "blt_sandwich"));
    MyCrabbyPatty = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crabbypatty"));
    MyOreRubyBlock = (OreRuby) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "oreruby"));
    MyDeepslateOreRubyBlock =
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oreruby"));
    MyRuby = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "ruby"));
    MyOreAmethystBlock = (OreAmethyst) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "oreamethyst"));
    MyDeepslateOreAmethystBlock =
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oreamethyst"));
    MyAmethyst = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "amethyst"));
    UraniumNugget = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "uranium_nugget"));
    TitaniumNugget = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "titanium_nugget"));
    CrystalStone = (OreBasicStone) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalstone"));
    CrystalCoal = (OreCrystal) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalcoal"));
    CrystalGrass = (CrystalGrass) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalgrass"));
    CrystalCrystal = (OreCrystalCrystal) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalcrystal"));
    TigersEye = (OreCrystalCrystal) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "tigerseye"));
    CrystalPlanksBlock = (CrystalWood) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalplanks"));
    CrystalWorkbenchBlock = (CrystalWorkbench) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalworkbench"));
    CrystalFurnaceBlock = (CrystalFurnace) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalfurnace"));
    MyPeacock = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cookedpeacock"));
    MyRawPeacock = (ItemPopcorn) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rawpeacock"));
    CrystalRat = (OreBasicStone) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalrat"));
    CrystalFairy = (OreBasicStone) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalfairy"));
    RedAntTroll = (OreBasicStone) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "redanttroll"));
    TermiteTroll = (OreBasicStone) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "termitetroll"));
    DeepslateRedAntTroll =
        (OreBasicStone)
            BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_redanttroll"));
    DeepslateTermiteTroll =
        (OreBasicStone)
            BuiltInRegistries.BLOCK.get(
                ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_termitetroll"));

    MyRTPBlock = (RTPBlock) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockteleport"));
    MyStepUp = (StepUp) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "step_up"));
    MyStepDown = (StepDown) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "step_down"));
    MyStepAccross = (StepAccross) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "step_accross"));
    MyMoleDirtBlock = (MoleDirtBlock) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "moledirt"));

    initializeCagesAndEggs();

    MyStrawberry = (ItemStrawberry) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "strawberry"));
    MyStrawberryPlant = (BlockStrawberry) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "strawberry_plant"));
    MyStrawberrySeed = (ItemStrawberrySeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "strawberry_seed"));
    MyButterflyPlant = (BlockButterflyPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "butterfly_plant"));
    MyButterflySeed = (ItemButterflySeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "butterfly_seed"));
    MyMothPlant = (BlockMothPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "moth_plant"));
    MyMothSeed = (ItemMothSeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "moth_seed"));
    MyMosquitoPlant = (BlockMosquitoPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "mosquito_plant"));
    MyMosquitoSeed = (ItemMosquitoSeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "mosquito_seed"));
    MyFireflyPlant = (BlockFireflyPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "firefly_plant"));
    MyFireflySeed = (ItemFireflySeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "firefly_seed"));
    MyRadishPlant = (BlockRadish) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "radish_plant"));
    MyRadish = (ItemRadish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "radish"));
    MyCherry = (ItemStrawberry) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cherries"));
    MyPeach = (ItemStrawberry) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peach"));
    MyCrystalApple = (ItemSunFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalapple"));
    MyLove = (ItemSunFish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "heart"));
    MyRicePlant = (BlockRice) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "rice_plant"));
    MyRice = (ItemRadish) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "rice"));

    MyElevator = (ItemElevator) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "elevator"));

    MyCornPlant1 = (BlockCorn) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "corn_plant0"));
    MyCornPlant2 = (BlockCorn) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "corn_plant1"));
    MyCornPlant3 = (BlockCorn) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "corn_plant2"));
    MyCornPlant4 = (BlockCorn) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "corn_plant3"));
    MyCornCob = (ItemCornCob) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "corn_seed"));
    MyQuinoaPlant1 = (BlockQuinoa) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "quinoa_0"));
    MyQuinoaPlant2 = (BlockQuinoa) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "quinoa_1"));
    MyQuinoaPlant3 = (BlockQuinoa) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "quinoa_2"));
    MyQuinoaPlant4 = (BlockQuinoa) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "quinoa_3"));
    MyQuinoa = (ItemCornCob) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "quinoa"));

    MyTomatoPlant1 = (BlockTomato) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "tomato_plant0"));
    MyTomatoPlant2 = (BlockTomato) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "tomato_plant1"));
    MyTomatoPlant3 = (BlockTomato) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "tomato_plant2"));
    MyTomatoPlant4 = (BlockTomato) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "tomato_plant3"));
    MyTomato = (ItemTomato) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "tomato_seed"));
    MyLettucePlant1 = (BlockLettuce) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "lettuce_0"));
    MyLettucePlant2 = (BlockLettuce) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "lettuce_1"));
    MyLettucePlant3 = (BlockLettuce) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "lettuce_2"));
    MyLettucePlant4 = (BlockLettuce) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "lettuce_3"));
    MyLettuce = (ItemLettuce) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "lettuce_seed"));

    MagicApple = (ItemMagicApple) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "magicapple"));
    MinersDream = (ItemMinersDream) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "minersdream"));
    ExtremeTorch = (BlockExtremeTorch) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "extremetorch"));
    KrakenRepellent = (KrakenRepellent) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "krakenrepellent"));
    MyIslandBlock = (IslandBlock) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "island"));
    CreeperRepellent = (CreeperRepellent) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "creeperrepellent"));
    ZooCage2 = (ZooCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "zoo2"));
    ZooCage4 = (ZooCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "zoo4"));
    ZooCage6 = (ZooCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "zoo6"));
    ZooCage8 = (ZooCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "zoo8"));
    ZooCage10 = (ZooCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "zoo10"));
    InstantShelter = (InstantShelter) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "instantshelter"));
    InstantGarden = (InstantGarden) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "instantgarden"));
    CrystalTorch = (BlockCrystalTorch) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystaltorch"));
    MyKingSpawnerBlock = (KingSpawnerBlock) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "kingspawner"));
    MyQueenSpawnerBlock = (QueenSpawnerBlock) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "queenspawner"));
    RandomDungeon = (ItemRandomDungeon) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "randomdungeon"));
    MyDungeonSpawnerBlock = (DungeonSpawnerBlock) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "dungeonspawner"));

    MyAppleLeaves = (BlockAppleLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "leaves_apple"));
    MyAppleSeed = (ItemAppleSeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "appletree_seed"));
    MySkyTreeLog = (BlockSkyTreeLog) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "skytreelog"));

    MyDT = (BlockDuplicatorLog) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "duplicatortreelog"));
    MyExperienceLeaves = (BlockExperienceLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "leaves_experience"));
    MyExperienceCatcher = (ExperienceCatcher) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experiencecatcher"));
    MyExperienceTreeSeed = (ItemExperienceTreeSeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "experiencetree_seed"));
    MyExperiencePlant = (BlockExperiencePlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "experiencesapling"));
    MyDeadStinkBug = (ItemSalt) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "deadstinkbug"));
    MyFlowerPinkBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "flower_pink"));
    MyFlowerBlueBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "flower_blue"));
    MyFlowerBlackBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "flower_black"));
    MyFlowerScaryBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "flower_scary"));
    MyScaryLeaves = (BlockScaryLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "leaves_scary"));
    MyCherryLeaves = (BlockScaryLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "leaves_cherry"));
    MyPeachLeaves = (BlockScaryLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "leaves_peach"));
    MyCherrySeed = (ItemAppleSeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cherrytree_seed"));
    MyPeachSeed = (ItemAppleSeed) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "peachtree_seed"));
    CrystalFlowerRedBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalflower_red"));
    CrystalFlowerGreenBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalflower_green"));
    CrystalFlowerBlueBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalflower_blue"));
    CrystalFlowerYellowBlock = (MyBlockFlower) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalflower_yellow"));
    MyCrystalLeaves = (BlockCrystalLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystaltreeleaves"));
    MyCrystalTreeLog = (BlockCrystalTreeLog) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystaltreelog"));
    MyCrystalLeaves2 = (BlockCrystalLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystaltreeleaves2"));
    MyCrystalLeaves3 = (BlockCrystalLeaves) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystaltreeleaves3"));
    MyCrystalPlant = (BlockCrystalPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalsapling"));
    MyCrystalPlant2 = (BlockCrystalPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalsapling2"));
    MyCrystalPlant3 = (BlockCrystalPlant) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "crystalsapling3"));

    MyEnderPearlBlock = (OreGenericEgg) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockenderpearl"));
    MyEyeOfEnderBlock = (OreGenericEgg) BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "blockeyeofender"));

    make_some_more_things();
    proxy.registerBlockModels();
  }

  private final Map<String, Integer> recipeNameUseCounts = new HashMap<String, Integer>();

  /** 1.20.1 registry paths must be [a-z0-9/._-]; 1.12 ids used PascalCase. */
  private static ResourceLocation cpId(String path) {
    return ResourceLocation.fromNamespaceAndPath(MODID, path.toLowerCase(Locale.ROOT));
  }

  private static ResourceLocation normalizeRegistryPath(ResourceLocation id) {
    String path = id.getPath().toLowerCase(Locale.ROOT);
    return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), path);
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
    return ResourceLocation.fromNamespaceAndPath(baseId.getNamespace(), baseId.getPath() + "_" + suffix);
  }

  private void addShapedRecipe(ResourceLocation name, ResourceLocation group, ItemStack output, Object... params)
  {
    GameRegistry.addShapedRecipe(
        nextRecipeId(normalizeRegistryPath(name)), normalizeRegistryPath(group), output, params);
  }

  private void addShapelessRecipe(ResourceLocation name, ResourceLocation group, ItemStack output, Ingredient... ingredients)
  {
    GameRegistry.addShapelessRecipe(
        nextRecipeId(normalizeRegistryPath(name)), normalizeRegistryPath(group), output, ingredients);
  }

  private ItemStack createVanillaSpawnEgg(String entityId)
  {
    Item spawnEgg =
        switch (entityId) {
          case "spider" -> Items.SPIDER_SPAWN_EGG;
          case "bat" -> Items.BAT_SPAWN_EGG;
          case "cow" -> Items.COW_SPAWN_EGG;
          case "pig" -> Items.PIG_SPAWN_EGG;
          case "squid" -> Items.SQUID_SPAWN_EGG;
          case "chicken" -> Items.CHICKEN_SPAWN_EGG;
          case "creeper" -> Items.CREEPER_SPAWN_EGG;
          case "skeleton" -> Items.SKELETON_SPAWN_EGG;
          case "zombie" -> Items.ZOMBIE_SPAWN_EGG;
          case "slime" -> Items.SLIME_SPAWN_EGG;
          case "ghast" -> Items.GHAST_SPAWN_EGG;
          case "zombie_pigman", "zombified_piglin" -> Items.ZOMBIFIED_PIGLIN_SPAWN_EGG;
          case "enderman" -> Items.ENDERMAN_SPAWN_EGG;
          case "cave_spider" -> Items.CAVE_SPIDER_SPAWN_EGG;
          case "silverfish" -> Items.SILVERFISH_SPAWN_EGG;
          case "magma_cube" -> Items.MAGMA_CUBE_SPAWN_EGG;
          case "witch" -> Items.WITCH_SPAWN_EGG;
          case "sheep" -> Items.SHEEP_SPAWN_EGG;
          case "wolf" -> Items.WOLF_SPAWN_EGG;
          case "mooshroom" -> Items.MOOSHROOM_SPAWN_EGG;
          case "ocelot" -> Items.OCELOT_SPAWN_EGG;
          case "blaze" -> Items.BLAZE_SPAWN_EGG;
          case "villager" -> Items.VILLAGER_SPAWN_EGG;
          case "horse" -> Items.HORSE_SPAWN_EGG;
          case "iron_golem" -> Items.IRON_GOLEM_SPAWN_EGG;
          case "snow_golem" -> Items.SNOW_GOLEM_SPAWN_EGG;
          case "wither_skeleton" -> Items.WITHER_SKELETON_SPAWN_EGG;
          default -> null;
        };
    if (spawnEgg == null) {
      throw new IllegalArgumentException("Unknown vanilla spawn egg entity id: " + entityId);
    }
    return new ItemStack(spawnEgg);
  }

  private void make_some_more_things()
  {
    recipeNameUseCounts.clear();
    GameRegistry.findRegistry(Block.class).register(MySpiderSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBatSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCowSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyPigSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySquidSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyChickenSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCreeperSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySkeletonSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyZombieSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySlimeSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGhastSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyZombiePigmanSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEndermanSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCaveSpiderSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySilverfishSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyMagmaCubeSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyWitchSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySheepSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyWolfSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyMooshroomSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyWitherBossSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGirlfriendSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBoyfriendSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyRedCowSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCrystalCowSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyVillagerSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGoldCowSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEnchantedCowSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyMOTHRASpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyAloSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCryoSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCamaSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyVeloSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyHydroSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBasilSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyDragonflySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEmperorScorpionSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyScorpionSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCaveFisherSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySpyroSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBaryonyxSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGammaMetroidSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCockateilSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyKyuubiSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyAlienSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyIronGolemSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySnowGolemSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEnderDragonSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyOcelotSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyWitherSkeletonSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBlazeSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyAttackSquidSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyWaterDragonSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCephadromeSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyKrakenSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyLizardSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyDragonSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBeeSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyHorseSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTrooperBugSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySpitBugSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyStinkBugSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyOstrichSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGazelleSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyChipmunkSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCreepingHorrorSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTerribleTerrorSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCliffRacerSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTriffidSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyPitchBlackSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyLurkingTerrorSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGodzillaPartSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGodzillaSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTheKingPartSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTheKingSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTheQueenPartSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTheQueenSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySmallWormSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyMediumWormSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyLargeWormSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCassowarySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCloudSharkSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyGoldFishSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyLeafMonsterSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTshirtSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEnderKnightSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEnderReaperSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBeaverSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyUrchinSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyFlounderSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySkateSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyRotatorSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyPeacockSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyFairySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyDungeonBeastSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyVortexSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyRatSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyWhaleSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyIrukandjiSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyTRexSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyHerculesSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyMantisSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyStinkySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyEasterBunnySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCaterKillerSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyMolenoidSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySeaMonsterSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySeaViperSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyLeonSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyHammerheadSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyRubberDuckySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCriminalSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyBrutalflySpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyNastysaurusSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyPointysaurusSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCricketSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyFrogSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MySpiderDriverSpawnBlock);
    GameRegistry.findRegistry(Block.class).register(MyCrabSpawnBlock);

    GameRegistry.findRegistry(Block.class).register(MyOreSaltBlock);
    GameRegistry.findRegistry(Block.class).register(MyRTPBlock);
    GameRegistry.findRegistry(Block.class).register(MyMoleDirtBlock);
    GameRegistry.findRegistry(Block.class).register(MyOreTitaniumBlock);
    GameRegistry.findRegistry(Block.class).register(MyOreUraniumBlock);
    GameRegistry.findRegistry(Block.class).register(MyBlockTitaniumBlock);
    GameRegistry.findRegistry(Block.class).register(MyBlockMobzillaScaleBlock);
    GameRegistry.findRegistry(Block.class).register(MyBlockUraniumBlock);
    GameRegistry.findRegistry(Block.class).register(MyLavafoamBlock);
    GameRegistry.findRegistry(Block.class).register(MyOreRubyBlock);
    GameRegistry.findRegistry(Block.class).register(MyBlockRubyBlock);
    GameRegistry.findRegistry(Block.class).register(MyOreAmethystBlock);
    GameRegistry.findRegistry(Block.class).register(MyBlockAmethystBlock);
    GameRegistry.findRegistry(Block.class).register(MyCrystalPinkBlock);
    GameRegistry.findRegistry(Block.class).register(MyTigersEyeBlock);
    GameRegistry.findRegistry(Block.class).register(MyPizzaBlock);
    GameRegistry.findRegistry(Block.class).register(MyDuctTapeBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalStone);
    GameRegistry.findRegistry(Block.class).register(CrystalRat);
    GameRegistry.findRegistry(Block.class).register(RedAntTroll);
    GameRegistry.findRegistry(Block.class).register(TermiteTroll);
    GameRegistry.findRegistry(Block.class).register(DeepslateRedAntTroll);
    GameRegistry.findRegistry(Block.class).register(DeepslateTermiteTroll);
    GameRegistry.findRegistry(Block.class).register(CrystalFairy);
    GameRegistry.findRegistry(Block.class).register(CrystalCoal);
    GameRegistry.findRegistry(Block.class).register(CrystalGrass);
    GameRegistry.findRegistry(Block.class).register(CrystalCrystal);
    GameRegistry.findRegistry(Block.class).register(TigersEye);
    GameRegistry.findRegistry(Block.class).register(CrystalPlanksBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalWorkbenchBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalFurnaceBlock);

    GameRegistry.findRegistry(Block.class).register(MyStrawberryPlant);
    GameRegistry.findRegistry(Block.class).register(MyRadishPlant);
    GameRegistry.findRegistry(Block.class).register(MyRicePlant);
    GameRegistry.findRegistry(Block.class).register(MyButterflyPlant);
    GameRegistry.findRegistry(Block.class).register(MyMothPlant);
    GameRegistry.findRegistry(Block.class).register(MyMosquitoPlant);
    GameRegistry.findRegistry(Block.class).register(MyFireflyPlant);
    GameRegistry.findRegistry(Block.class).register(MyCornPlant1);
    GameRegistry.findRegistry(Block.class).register(MyCornPlant2);
    GameRegistry.findRegistry(Block.class).register(MyCornPlant3);
    GameRegistry.findRegistry(Block.class).register(MyCornPlant4);
    GameRegistry.findRegistry(Block.class).register(MyQuinoaPlant1);
    GameRegistry.findRegistry(Block.class).register(MyQuinoaPlant2);
    GameRegistry.findRegistry(Block.class).register(MyQuinoaPlant3);
    GameRegistry.findRegistry(Block.class).register(MyQuinoaPlant4);
    GameRegistry.findRegistry(Block.class).register(MyTomatoPlant1);
    GameRegistry.findRegistry(Block.class).register(MyTomatoPlant2);
    GameRegistry.findRegistry(Block.class).register(MyTomatoPlant3);
    GameRegistry.findRegistry(Block.class).register(MyTomatoPlant4);
    GameRegistry.findRegistry(Block.class).register(MyLettucePlant1);
    GameRegistry.findRegistry(Block.class).register(MyLettucePlant2);
    GameRegistry.findRegistry(Block.class).register(MyLettucePlant3);
    GameRegistry.findRegistry(Block.class).register(MyLettucePlant4);
    GameRegistry.findRegistry(Block.class).register(MyAppleLeaves);
    GameRegistry.findRegistry(Block.class).register(MyExperienceLeaves);
    GameRegistry.findRegistry(Block.class).register(MyScaryLeaves);
    GameRegistry.findRegistry(Block.class).register(MyCherryLeaves);
    GameRegistry.findRegistry(Block.class).register(MyPeachLeaves);
    GameRegistry.findRegistry(Block.class).register(MySkyTreeLog);
    GameRegistry.findRegistry(Block.class).register(MyDT);
    GameRegistry.findRegistry(Block.class).register(MyExperiencePlant);
    GameRegistry.findRegistry(Block.class).register(MyCrystalPlant);
    GameRegistry.findRegistry(Block.class).register(MyCrystalPlant2);
    GameRegistry.findRegistry(Block.class).register(MyCrystalPlant3);
    GameRegistry.findRegistry(Block.class).register(MyFlowerPinkBlock);
    GameRegistry.findRegistry(Block.class).register(MyFlowerBlueBlock);
    GameRegistry.findRegistry(Block.class).register(MyFlowerBlackBlock);
    GameRegistry.findRegistry(Block.class).register(MyFlowerScaryBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalFlowerRedBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalFlowerGreenBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalFlowerBlueBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalFlowerYellowBlock);
    GameRegistry.findRegistry(Block.class).register(MyCrystalLeaves);
    GameRegistry.findRegistry(Block.class).register(MyCrystalLeaves2);
    GameRegistry.findRegistry(Block.class).register(MyCrystalLeaves3);
    GameRegistry.findRegistry(Block.class).register(MyCrystalTreeLog);

    GameRegistry.findRegistry(Block.class).register(ExtremeTorch);
    GameRegistry.findRegistry(Block.class).register(CrystalTorch);
    GameRegistry.findRegistry(Block.class).register(KrakenRepellent);
    GameRegistry.findRegistry(Block.class).register(CreeperRepellent);
    GameRegistry.findRegistry(Block.class).register(MyIslandBlock);
    GameRegistry.findRegistry(Block.class).register(MyKingSpawnerBlock);
    GameRegistry.findRegistry(Block.class).register(MyQueenSpawnerBlock);
    GameRegistry.findRegistry(Block.class).register(MyDungeonSpawnerBlock);

    GameRegistry.findRegistry(Block.class).register(MyEnderPearlBlock);
    GameRegistry.findRegistry(Block.class).register(MyEyeOfEnderBlock);
    GameRegistry.findRegistry(Block.class).register(MyAntBlock);
    GameRegistry.findRegistry(Block.class).register(MyRedAntBlock);
    GameRegistry.findRegistry(Block.class).register(TermiteBlock);
    GameRegistry.findRegistry(Block.class).register(CrystalTermiteBlock);
    GameRegistry.findRegistry(Block.class).register(MyRainbowAntBlock);
    GameRegistry.findRegistry(Block.class).register(MyUnstableAntBlock);

    GameRegistry.findRegistry(Item.class).register(MyPizzaItem);
    GameRegistry.findRegistry(Item.class).register(MyDuctTapeItem);
    GameRegistry.findRegistry(Item.class).register(BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "island")));
    GameRegistry.findRegistry(Item.class).register(MyIngotUranium);
    GameRegistry.findRegistry(Item.class).register(MyCrystalPinkIngot);
    GameRegistry.findRegistry(Item.class).register(MyTigersEyeIngot);
    GameRegistry.findRegistry(Item.class).register(MyIngotTitanium);
    GameRegistry.findRegistry(Item.class).register(MyUltimateSword);
    GameRegistry.findRegistry(Item.class).register(MyNightmareSword);
    GameRegistry.findRegistry(Item.class).register(MyBertha);
    GameRegistry.findRegistry(Item.class).register(MyHammy);
    GameRegistry.findRegistry(Item.class).register(MySlice);
    GameRegistry.findRegistry(Item.class).register(MyRoyal);
    GameRegistry.findRegistry(Item.class).register(MyBattleAxe);
    GameRegistry.findRegistry(Item.class).register(MyQueenBattleAxe);
    GameRegistry.findRegistry(Item.class).register(MyChainsaw);
    GameRegistry.findRegistry(Item.class).register(MyUltimatePickaxe);
    GameRegistry.findRegistry(Item.class).register(MyUltimateShovel);
    GameRegistry.findRegistry(Item.class).register(MyUltimateHoe);
    GameRegistry.findRegistry(Item.class).register(MyUltimateAxe);
    GameRegistry.findRegistry(Item.class).register(MyEmeraldSword);
    GameRegistry.findRegistry(Item.class).register(MyRoseSword);
    GameRegistry.findRegistry(Item.class).register(MyExperienceSword);
    GameRegistry.findRegistry(Item.class).register(MyPoisonSword);
    GameRegistry.findRegistry(Item.class).register(MyRatSword);
    GameRegistry.findRegistry(Item.class).register(MyFairySword);
    GameRegistry.findRegistry(Item.class).register(MyMantisClaw);
    GameRegistry.findRegistry(Item.class).register(MyBigHammer);
    GameRegistry.findRegistry(Item.class).register(MyEmeraldPickaxe);
    GameRegistry.findRegistry(Item.class).register(MyEmeraldShovel);
    GameRegistry.findRegistry(Item.class).register(MyEmeraldHoe);
    GameRegistry.findRegistry(Item.class).register(MyEmeraldAxe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalWoodSword);
    GameRegistry.findRegistry(Item.class).register(MyCrystalWoodPickaxe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalWoodShovel);
    GameRegistry.findRegistry(Item.class).register(MyCrystalWoodHoe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalWoodAxe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalPinkSword);
    GameRegistry.findRegistry(Item.class).register(MyCrystalPinkPickaxe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalPinkShovel);
    GameRegistry.findRegistry(Item.class).register(MyCrystalPinkHoe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalPinkAxe);
    GameRegistry.findRegistry(Item.class).register(MyTigersEyeSword);
    GameRegistry.findRegistry(Item.class).register(MyTigersEyePickaxe);
    GameRegistry.findRegistry(Item.class).register(MyTigersEyeShovel);
    GameRegistry.findRegistry(Item.class).register(MyTigersEyeHoe);
    GameRegistry.findRegistry(Item.class).register(MyTigersEyeAxe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalStoneSword);
    GameRegistry.findRegistry(Item.class).register(MyCrystalStonePickaxe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalStoneShovel);
    GameRegistry.findRegistry(Item.class).register(MyCrystalStoneHoe);
    GameRegistry.findRegistry(Item.class).register(MyCrystalStoneAxe);
    GameRegistry.findRegistry(Item.class).register(MyRubySword);
    GameRegistry.findRegistry(Item.class).register(MyRubyPickaxe);
    GameRegistry.findRegistry(Item.class).register(MyRubyShovel);
    GameRegistry.findRegistry(Item.class).register(MyRubyHoe);
    GameRegistry.findRegistry(Item.class).register(MyRubyAxe);
    GameRegistry.findRegistry(Item.class).register(MyAmethystSword);
    GameRegistry.findRegistry(Item.class).register(MyAmethystPickaxe);
    GameRegistry.findRegistry(Item.class).register(MyAmethystShovel);
    GameRegistry.findRegistry(Item.class).register(MyAmethystHoe);
    GameRegistry.findRegistry(Item.class).register(MyAmethystAxe);
    GameRegistry.findRegistry(Item.class).register(MyItemShoes);
    GameRegistry.findRegistry(Item.class).register(MyItemShoes_1);
    GameRegistry.findRegistry(Item.class).register(MyItemShoes_2);
    GameRegistry.findRegistry(Item.class).register(MyItemShoes_3);
    GameRegistry.findRegistry(Item.class).register(MyItemGameController);
    GameRegistry.findRegistry(Item.class).register(MyUltimateBow);
    GameRegistry.findRegistry(Item.class).register(MySkateBow);
    GameRegistry.findRegistry(Item.class).register(MyUltimateFishingRod);
    GameRegistry.findRegistry(Item.class).register(MyFireFish);
    GameRegistry.findRegistry(Item.class).register(MySunFish);
    GameRegistry.findRegistry(Item.class).register(MyLavaEel);
    GameRegistry.findRegistry(Item.class).register(MyMothScale);
    GameRegistry.findRegistry(Item.class).register(MyQueenScale);
    GameRegistry.findRegistry(Item.class).register(MyNightmareScale);
    GameRegistry.findRegistry(Item.class).register(MyEmperorScorpionScale);
    GameRegistry.findRegistry(Item.class).register(MyBasiliskScale);
    GameRegistry.findRegistry(Item.class).register(MyWaterDragonScale);
    GameRegistry.findRegistry(Item.class).register(MyPeacockFeather);
    GameRegistry.findRegistry(Item.class).register(MyJumpyBugScale);
    GameRegistry.findRegistry(Item.class).register(MyKrakenTooth);
    GameRegistry.findRegistry(Item.class).register(MyGodzillaScale);
    GameRegistry.findRegistry(Item.class).register(GreenGoo);
    GameRegistry.findRegistry(Item.class).register(SpiderRobotKit);
    GameRegistry.findRegistry(Item.class).register(AntRobotKit);
    GameRegistry.findRegistry(Item.class).register(ZooKeeper);
    GameRegistry.findRegistry(Item.class).register(CreeperLauncher);
    GameRegistry.findRegistry(Item.class).register(NetherLost);
    GameRegistry.findRegistry(Item.class).register(CrystalSticks);
    GameRegistry.findRegistry(Item.class).register(Sifter);
    GameRegistry.findRegistry(Item.class).register(MySunspotUrchin);
    GameRegistry.findRegistry(Item.class).register(MyWaterBall);
    GameRegistry.findRegistry(Item.class).register(MyLaserBall);
    GameRegistry.findRegistry(Item.class).register(MyIceBall);
    GameRegistry.findRegistry(Item.class).register(MySmallRock);
    GameRegistry.findRegistry(Item.class).register(MyRock);
    GameRegistry.findRegistry(Item.class).register(MyRedRock);
    GameRegistry.findRegistry(Item.class).register(MyCrystalRedRock);
    GameRegistry.findRegistry(Item.class).register(MyCrystalGreenRock);
    GameRegistry.findRegistry(Item.class).register(MyCrystalBlueRock);
    GameRegistry.findRegistry(Item.class).register(MyCrystalTNTRock);
    GameRegistry.findRegistry(Item.class).register(MyGreenRock);
    GameRegistry.findRegistry(Item.class).register(MyBlueRock);
    GameRegistry.findRegistry(Item.class).register(MyPurpleRock);
    GameRegistry.findRegistry(Item.class).register(MySpikeyRock);
    GameRegistry.findRegistry(Item.class).register(MyTNTRock);
    GameRegistry.findRegistry(Item.class).register(MyAcid);
    GameRegistry.findRegistry(Item.class).register(MyIrukandji);
    GameRegistry.findRegistry(Item.class).register(MyIrukandjiArrow);
    GameRegistry.findRegistry(Item.class).register(MyRayGun);
    GameRegistry.findRegistry(Item.class).register(MySquidZooka);
    GameRegistry.findRegistry(Item.class).register(MySparkFish);
    GameRegistry.findRegistry(Item.class).register(MySalt);
    GameRegistry.findRegistry(Item.class).register(MyPopcorn);
    GameRegistry.findRegistry(Item.class).register(MyButteredPopcorn);
    GameRegistry.findRegistry(Item.class).register(MyButteredSaltedPopcorn);
    GameRegistry.findRegistry(Item.class).register(MyPopcornBag);
    GameRegistry.findRegistry(Item.class).register(MyButter);
    GameRegistry.findRegistry(Item.class).register(MyCornDog);
    GameRegistry.findRegistry(Item.class).register(MyCheese);
    GameRegistry.findRegistry(Item.class).register(MyRawCornDog);
    GameRegistry.findRegistry(Item.class).register(MyPeacock);
    GameRegistry.findRegistry(Item.class).register(MyRawPeacock);
    GameRegistry.findRegistry(Item.class).register(MyRuby);
    GameRegistry.findRegistry(Item.class).register(MyAmethyst);
    GameRegistry.findRegistry(Item.class).register(MyThunderStaff);
    GameRegistry.findRegistry(Item.class).register(MyWrench);
    GameRegistry.findRegistry(Item.class).register(MyRawBacon);
    GameRegistry.findRegistry(Item.class).register(MyBacon);
    GameRegistry.findRegistry(Item.class).register(MyRawCrabMeat);
    GameRegistry.findRegistry(Item.class).register(MyCrabMeat);
    GameRegistry.findRegistry(Item.class).register(MyButterCandy);
    GameRegistry.findRegistry(Item.class).register(UraniumNugget);
    GameRegistry.findRegistry(Item.class).register(TitaniumNugget);
    GameRegistry.findRegistry(Item.class).register(MyGreenFish);
    GameRegistry.findRegistry(Item.class).register(MyBlueFish);
    GameRegistry.findRegistry(Item.class).register(MyPinkFish);
    GameRegistry.findRegistry(Item.class).register(MyRockFish);
    GameRegistry.findRegistry(Item.class).register(MyWoodFish);
    GameRegistry.findRegistry(Item.class).register(MyGreyFish);
    GameRegistry.findRegistry(Item.class).register(MySalad);
    GameRegistry.findRegistry(Item.class).register(MyBLT);
    GameRegistry.findRegistry(Item.class).register(MyCrabbyPatty);

    GameRegistry.findRegistry(Item.class).register(BerthaHandle);
    GameRegistry.findRegistry(Item.class).register(BerthaGuard);
    GameRegistry.findRegistry(Item.class).register(BerthaBlade);
    GameRegistry.findRegistry(Item.class).register(MolenoidNose);
    GameRegistry.findRegistry(Item.class).register(SeaMonsterScale);
    GameRegistry.findRegistry(Item.class).register(WormTooth);
    GameRegistry.findRegistry(Item.class).register(TRexTooth);
    GameRegistry.findRegistry(Item.class).register(CaterKillerJaw);
    GameRegistry.findRegistry(Item.class).register(SeaViperTongue);
    GameRegistry.findRegistry(Item.class).register(VortexEye);

    GameRegistry.findRegistry(Item.class).register(EnderDragonEgg);
    GameRegistry.findRegistry(Item.class).register(WitherBossEgg);
    GameRegistry.findRegistry(Item.class).register(GirlfriendEgg);
    GameRegistry.findRegistry(Item.class).register(BoyfriendEgg);
    GameRegistry.findRegistry(Item.class).register(TheKingEgg);
    GameRegistry.findRegistry(Item.class).register(TheQueenEgg);
    GameRegistry.findRegistry(Item.class).register(ThePrinceEgg);
    GameRegistry.findRegistry(Item.class).register(RedCowEgg);
    GameRegistry.findRegistry(Item.class).register(CrystalCowEgg);
    GameRegistry.findRegistry(Item.class).register(GoldCowEgg);
    GameRegistry.findRegistry(Item.class).register(EnchantedCowEgg);
    GameRegistry.findRegistry(Item.class).register(MOTHRAEgg);
    GameRegistry.findRegistry(Item.class).register(AloEgg);
    GameRegistry.findRegistry(Item.class).register(CryoEgg);
    GameRegistry.findRegistry(Item.class).register(CamaEgg);
    GameRegistry.findRegistry(Item.class).register(VeloEgg);
    GameRegistry.findRegistry(Item.class).register(HydroEgg);
    GameRegistry.findRegistry(Item.class).register(BasilEgg);
    GameRegistry.findRegistry(Item.class).register(DragonflyEgg);
    GameRegistry.findRegistry(Item.class).register(EmperorScorpionEgg);
    GameRegistry.findRegistry(Item.class).register(ScorpionEgg);
    GameRegistry.findRegistry(Item.class).register(CaveFisherEgg);
    GameRegistry.findRegistry(Item.class).register(SpyroEgg);
    GameRegistry.findRegistry(Item.class).register(BaryonyxEgg);
    GameRegistry.findRegistry(Item.class).register(GammaMetroidEgg);
    GameRegistry.findRegistry(Item.class).register(CockateilEgg);
    GameRegistry.findRegistry(Item.class).register(KyuubiEgg);
    GameRegistry.findRegistry(Item.class).register(AlienEgg);
    GameRegistry.findRegistry(Item.class).register(AttackSquidEgg);
    GameRegistry.findRegistry(Item.class).register(WaterDragonEgg);
    GameRegistry.findRegistry(Item.class).register(CephadromeEgg);
    GameRegistry.findRegistry(Item.class).register(KrakenEgg);
    GameRegistry.findRegistry(Item.class).register(LizardEgg);
    GameRegistry.findRegistry(Item.class).register(DragonEgg);
    GameRegistry.findRegistry(Item.class).register(BeeEgg);
    GameRegistry.findRegistry(Item.class).register(TrooperBugEgg);
    GameRegistry.findRegistry(Item.class).register(SpitBugEgg);
    GameRegistry.findRegistry(Item.class).register(StinkBugEgg);
    GameRegistry.findRegistry(Item.class).register(OstrichEgg);
    GameRegistry.findRegistry(Item.class).register(GazelleEgg);
    GameRegistry.findRegistry(Item.class).register(ChipmunkEgg);
    GameRegistry.findRegistry(Item.class).register(CreepingHorrorEgg);
    GameRegistry.findRegistry(Item.class).register(TerribleTerrorEgg);
    GameRegistry.findRegistry(Item.class).register(CliffRacerEgg);
    GameRegistry.findRegistry(Item.class).register(TriffidEgg);
    GameRegistry.findRegistry(Item.class).register(PitchBlackEgg);
    GameRegistry.findRegistry(Item.class).register(LurkingTerrorEgg);
    GameRegistry.findRegistry(Item.class).register(GodzillaEgg);
    GameRegistry.findRegistry(Item.class).register(SmallWormEgg);
    GameRegistry.findRegistry(Item.class).register(MediumWormEgg);
    GameRegistry.findRegistry(Item.class).register(LargeWormEgg);
    GameRegistry.findRegistry(Item.class).register(CassowaryEgg);
    GameRegistry.findRegistry(Item.class).register(CloudSharkEgg);
    GameRegistry.findRegistry(Item.class).register(GoldFishEgg);
    GameRegistry.findRegistry(Item.class).register(LeafMonsterEgg);
    GameRegistry.findRegistry(Item.class).register(TshirtEgg);
    GameRegistry.findRegistry(Item.class).register(EnderKnightEgg);
    GameRegistry.findRegistry(Item.class).register(EnderReaperEgg);
    GameRegistry.findRegistry(Item.class).register(BeaverEgg);
    GameRegistry.findRegistry(Item.class).register(DungeonBeastEgg);
    GameRegistry.findRegistry(Item.class).register(RotatorEgg);
    GameRegistry.findRegistry(Item.class).register(VortexEgg);
    GameRegistry.findRegistry(Item.class).register(PeacockEgg);
    GameRegistry.findRegistry(Item.class).register(FairyEgg);
    GameRegistry.findRegistry(Item.class).register(RatEgg);
    GameRegistry.findRegistry(Item.class).register(FlounderEgg);
    GameRegistry.findRegistry(Item.class).register(WhaleEgg);
    GameRegistry.findRegistry(Item.class).register(IrukandjiEgg);
    GameRegistry.findRegistry(Item.class).register(SkateEgg);
    GameRegistry.findRegistry(Item.class).register(UrchinEgg);
    GameRegistry.findRegistry(Item.class).register(Robot1Egg);
    GameRegistry.findRegistry(Item.class).register(Robot2Egg);
    GameRegistry.findRegistry(Item.class).register(Robot3Egg);
    GameRegistry.findRegistry(Item.class).register(Robot4Egg);
    GameRegistry.findRegistry(Item.class).register(GhostEgg);
    GameRegistry.findRegistry(Item.class).register(GhostSkellyEgg);
    GameRegistry.findRegistry(Item.class).register(BrownAntEgg);
    GameRegistry.findRegistry(Item.class).register(RedAntEgg);
    GameRegistry.findRegistry(Item.class).register(RainbowAntEgg);
    GameRegistry.findRegistry(Item.class).register(UnstableAntEgg);
    GameRegistry.findRegistry(Item.class).register(TermiteEgg);
    GameRegistry.findRegistry(Item.class).register(ButterflyEgg);
    GameRegistry.findRegistry(Item.class).register(MothEgg);
    GameRegistry.findRegistry(Item.class).register(MosquitoEgg);
    GameRegistry.findRegistry(Item.class).register(FireflyEgg);
    GameRegistry.findRegistry(Item.class).register(TRexEgg);
    GameRegistry.findRegistry(Item.class).register(HerculesEgg);
    GameRegistry.findRegistry(Item.class).register(MantisEgg);
    GameRegistry.findRegistry(Item.class).register(StinkyEgg);
    GameRegistry.findRegistry(Item.class).register(Robot5Egg);
    GameRegistry.findRegistry(Item.class).register(CoinEgg);
    GameRegistry.findRegistry(Item.class).register(EasterBunnyEgg);
    GameRegistry.findRegistry(Item.class).register(MolenoidEgg);
    GameRegistry.findRegistry(Item.class).register(SeaMonsterEgg);
    GameRegistry.findRegistry(Item.class).register(SeaViperEgg);
    GameRegistry.findRegistry(Item.class).register(CaterKillerEgg);
    GameRegistry.findRegistry(Item.class).register(RubberDuckyEgg);
    GameRegistry.findRegistry(Item.class).register(HammerheadEgg);
    GameRegistry.findRegistry(Item.class).register(LeonEgg);
    GameRegistry.findRegistry(Item.class).register(CriminalEgg);
    GameRegistry.findRegistry(Item.class).register(BrutalflyEgg);
    GameRegistry.findRegistry(Item.class).register(NastysaurusEgg);
    GameRegistry.findRegistry(Item.class).register(PointysaurusEgg);
    GameRegistry.findRegistry(Item.class).register(CricketEgg);
    GameRegistry.findRegistry(Item.class).register(ThePrincessEgg);
    GameRegistry.findRegistry(Item.class).register(FrogEgg);
    GameRegistry.findRegistry(Item.class).register(JefferyEgg);
    GameRegistry.findRegistry(Item.class).register(AntRobotEgg);
    GameRegistry.findRegistry(Item.class).register(SpiderRobotEgg);
    GameRegistry.findRegistry(Item.class).register(SpiderDriverEgg);
    GameRegistry.findRegistry(Item.class).register(CrabEgg);
    GameRegistry.findRegistry(Item.class).register(RockEgg);

    GameRegistry.findRegistry(Item.class).register(CageEmpty);
    GameRegistry.findRegistry(Item.class).register(CagedSpider);
    GameRegistry.findRegistry(Item.class).register(CagedBat);
    GameRegistry.findRegistry(Item.class).register(CagedCow);
    GameRegistry.findRegistry(Item.class).register(CagedPig);
    GameRegistry.findRegistry(Item.class).register(CagedSquid);
    GameRegistry.findRegistry(Item.class).register(CagedChicken);
    GameRegistry.findRegistry(Item.class).register(CagedCreeper);
    GameRegistry.findRegistry(Item.class).register(CagedSkeleton);
    GameRegistry.findRegistry(Item.class).register(CagedZombie);
    GameRegistry.findRegistry(Item.class).register(CagedSlime);
    GameRegistry.findRegistry(Item.class).register(CagedGhast);
    GameRegistry.findRegistry(Item.class).register(CagedZombiePigman);
    GameRegistry.findRegistry(Item.class).register(CagedEnderman);
    GameRegistry.findRegistry(Item.class).register(CagedCaveSpider);
    GameRegistry.findRegistry(Item.class).register(CagedSilverfish);
    GameRegistry.findRegistry(Item.class).register(CagedMagmaCube);
    GameRegistry.findRegistry(Item.class).register(CagedWitch);
    GameRegistry.findRegistry(Item.class).register(CagedSheep);
    GameRegistry.findRegistry(Item.class).register(CagedWolf);
    GameRegistry.findRegistry(Item.class).register(CagedMooshroom);
    GameRegistry.findRegistry(Item.class).register(CagedOcelot);
    GameRegistry.findRegistry(Item.class).register(CagedBlaze);
    GameRegistry.findRegistry(Item.class).register(CagedGirlfriend);
    GameRegistry.findRegistry(Item.class).register(CagedBoyfriend);
    GameRegistry.findRegistry(Item.class).register(CagedWitherSkeleton);
    GameRegistry.findRegistry(Item.class).register(CagedEnderDragon);
    GameRegistry.findRegistry(Item.class).register(CagedSnowGolem);
    GameRegistry.findRegistry(Item.class).register(CagedIronGolem);
    GameRegistry.findRegistry(Item.class).register(CagedWitherBoss);
    GameRegistry.findRegistry(Item.class).register(CagedRedCow);
    GameRegistry.findRegistry(Item.class).register(CagedCrystalCow);
    GameRegistry.findRegistry(Item.class).register(CagedVillager);
    GameRegistry.findRegistry(Item.class).register(CagedGoldCow);
    GameRegistry.findRegistry(Item.class).register(CagedEnchantedCow);
    GameRegistry.findRegistry(Item.class).register(CagedMOTHRA);
    GameRegistry.findRegistry(Item.class).register(CagedAlo);
    GameRegistry.findRegistry(Item.class).register(CagedCryo);
    GameRegistry.findRegistry(Item.class).register(CagedCama);
    GameRegistry.findRegistry(Item.class).register(CagedVelo);
    GameRegistry.findRegistry(Item.class).register(CagedHydro);
    GameRegistry.findRegistry(Item.class).register(CagedBasil);
    GameRegistry.findRegistry(Item.class).register(CagedDragonfly);
    GameRegistry.findRegistry(Item.class).register(CagedEmperorScorpion);
    GameRegistry.findRegistry(Item.class).register(CagedScorpion);
    GameRegistry.findRegistry(Item.class).register(CagedCaveFisher);
    GameRegistry.findRegistry(Item.class).register(CagedSpyro);
    GameRegistry.findRegistry(Item.class).register(CagedBaryonyx);
    GameRegistry.findRegistry(Item.class).register(CagedGammaMetroid);
    GameRegistry.findRegistry(Item.class).register(CagedCockateil);
    GameRegistry.findRegistry(Item.class).register(CagedKyuubi);
    GameRegistry.findRegistry(Item.class).register(CagedAlien);
    GameRegistry.findRegistry(Item.class).register(MyElevator);
    GameRegistry.findRegistry(Item.class).register(CagedAttackSquid);
    GameRegistry.findRegistry(Item.class).register(CagedWaterDragon);
    GameRegistry.findRegistry(Item.class).register(CagedCephadrome);
    GameRegistry.findRegistry(Item.class).register(CagedKraken);
    GameRegistry.findRegistry(Item.class).register(CagedLizard);
    GameRegistry.findRegistry(Item.class).register(CagedDragon);
    GameRegistry.findRegistry(Item.class).register(CagedBee);
    GameRegistry.findRegistry(Item.class).register(CagedHorse);
    GameRegistry.findRegistry(Item.class).register(CagedFirefly);
    GameRegistry.findRegistry(Item.class).register(CagedChipmunk);
    GameRegistry.findRegistry(Item.class).register(CagedGazelle);
    GameRegistry.findRegistry(Item.class).register(CagedOstrich);
    GameRegistry.findRegistry(Item.class).register(CagedTrooper);
    GameRegistry.findRegistry(Item.class).register(CagedSpit);
    GameRegistry.findRegistry(Item.class).register(CagedStink);
    GameRegistry.findRegistry(Item.class).register(CagedCreepingHorror);
    GameRegistry.findRegistry(Item.class).register(CagedTerribleTerror);
    GameRegistry.findRegistry(Item.class).register(CagedCliffRacer);
    GameRegistry.findRegistry(Item.class).register(CagedTriffid);
    GameRegistry.findRegistry(Item.class).register(CagedPitchBlack);
    GameRegistry.findRegistry(Item.class).register(CagedLurkingTerror);
    GameRegistry.findRegistry(Item.class).register(CagedSmallWorm);
    GameRegistry.findRegistry(Item.class).register(CagedMediumWorm);
    GameRegistry.findRegistry(Item.class).register(CagedLargeWorm);
    GameRegistry.findRegistry(Item.class).register(CagedCassowary);
    GameRegistry.findRegistry(Item.class).register(CagedCloudShark);
    GameRegistry.findRegistry(Item.class).register(CagedGoldFish);
    GameRegistry.findRegistry(Item.class).register(CagedLeafMonster);
    GameRegistry.findRegistry(Item.class).register(CagedEnderKnight);
    GameRegistry.findRegistry(Item.class).register(CagedEnderReaper);
    GameRegistry.findRegistry(Item.class).register(CagedBeaver);
    GameRegistry.findRegistry(Item.class).register(CagedUrchin);
    GameRegistry.findRegistry(Item.class).register(CagedFlounder);
    GameRegistry.findRegistry(Item.class).register(CagedSkate);
    GameRegistry.findRegistry(Item.class).register(CagedRotator);
    GameRegistry.findRegistry(Item.class).register(CagedPeacock);
    GameRegistry.findRegistry(Item.class).register(CagedFairy);
    GameRegistry.findRegistry(Item.class).register(CagedDungeonBeast);
    GameRegistry.findRegistry(Item.class).register(CagedVortex);
    GameRegistry.findRegistry(Item.class).register(CagedRat);
    GameRegistry.findRegistry(Item.class).register(CagedWhale);
    GameRegistry.findRegistry(Item.class).register(CagedIrukandji);
    GameRegistry.findRegistry(Item.class).register(CagedTRex);
    GameRegistry.findRegistry(Item.class).register(CagedHercules);
    GameRegistry.findRegistry(Item.class).register(CagedMantis);
    GameRegistry.findRegistry(Item.class).register(CagedStinky);
    GameRegistry.findRegistry(Item.class).register(CagedEasterBunny);
    GameRegistry.findRegistry(Item.class).register(CagedCaterKiller);
    GameRegistry.findRegistry(Item.class).register(CagedMolenoid);
    GameRegistry.findRegistry(Item.class).register(CagedSeaMonster);
    GameRegistry.findRegistry(Item.class).register(CagedSeaViper);
    GameRegistry.findRegistry(Item.class).register(CagedLeon);
    GameRegistry.findRegistry(Item.class).register(CagedHammerhead);
    GameRegistry.findRegistry(Item.class).register(CagedRubberDucky);
    GameRegistry.findRegistry(Item.class).register(CagedCriminal);
    GameRegistry.findRegistry(Item.class).register(CagedBrutalfly);
    GameRegistry.findRegistry(Item.class).register(CagedNastysaurus);
    GameRegistry.findRegistry(Item.class).register(CagedPointysaurus);
    GameRegistry.findRegistry(Item.class).register(CagedCricket);
    GameRegistry.findRegistry(Item.class).register(CagedFrog);
    GameRegistry.findRegistry(Item.class).register(CagedSpiderDriver);
    GameRegistry.findRegistry(Item.class).register(CagedCrab);

    GameRegistry.findRegistry(Item.class).register(MyStrawberry);
    GameRegistry.findRegistry(Item.class).register(MyCrystalApple);
    GameRegistry.findRegistry(Item.class).register(MyLove);
    GameRegistry.findRegistry(Item.class).register(MyCherry);
    GameRegistry.findRegistry(Item.class).register(MyPeach);
    GameRegistry.findRegistry(Item.class).register(MyRadish);
    GameRegistry.findRegistry(Item.class).register(MyRice);
    GameRegistry.findRegistry(Item.class).register(MyCornCob);
    GameRegistry.findRegistry(Item.class).register(MyQuinoa);
    GameRegistry.findRegistry(Item.class).register(MyTomato);
    GameRegistry.findRegistry(Item.class).register(MyLettuce);
    GameRegistry.findRegistry(Item.class).register(MyStrawberrySeed);
    GameRegistry.findRegistry(Item.class).register(MyButterflySeed);
    GameRegistry.findRegistry(Item.class).register(MyMothSeed);
    GameRegistry.findRegistry(Item.class).register(MyMosquitoSeed);
    GameRegistry.findRegistry(Item.class).register(MyFireflySeed);
    GameRegistry.findRegistry(Item.class).register(MagicApple);
    GameRegistry.findRegistry(Item.class).register(RandomDungeon);
    GameRegistry.findRegistry(Item.class).register(MinersDream);
    GameRegistry.findRegistry(Item.class).register(UltimateHelmet);
    GameRegistry.findRegistry(Item.class).register(UltimateBody);
    GameRegistry.findRegistry(Item.class).register(UltimateLegs);
    GameRegistry.findRegistry(Item.class).register(UltimateBoots);
    GameRegistry.findRegistry(Item.class).register(LavaEelHelmet);
    GameRegistry.findRegistry(Item.class).register(LavaEelBody);
    GameRegistry.findRegistry(Item.class).register(LavaEelLegs);
    GameRegistry.findRegistry(Item.class).register(LavaEelBoots);
    GameRegistry.findRegistry(Item.class).register(MothScaleHelmet);
    GameRegistry.findRegistry(Item.class).register(MothScaleBody);
    GameRegistry.findRegistry(Item.class).register(MothScaleLegs);
    GameRegistry.findRegistry(Item.class).register(MothScaleBoots);
    GameRegistry.findRegistry(Item.class).register(MyAppleSeed);
    GameRegistry.findRegistry(Item.class).register(MyCherrySeed);
    GameRegistry.findRegistry(Item.class).register(MyPeachSeed);
    GameRegistry.findRegistry(Item.class).register(MyStepUp);
    GameRegistry.findRegistry(Item.class).register(MyStepDown);
    GameRegistry.findRegistry(Item.class).register(MyStepAccross);
    GameRegistry.findRegistry(Item.class).register(EmeraldHelmet);
    GameRegistry.findRegistry(Item.class).register(EmeraldBody);
    GameRegistry.findRegistry(Item.class).register(EmeraldLegs);
    GameRegistry.findRegistry(Item.class).register(EmeraldBoots);
    GameRegistry.findRegistry(Item.class).register(MyExperienceCatcher);
    GameRegistry.findRegistry(Item.class).register(MyDeadStinkBug);
    GameRegistry.findRegistry(Item.class).register(MyExperienceTreeSeed);
    GameRegistry.findRegistry(Item.class).register(ExperienceHelmet);
    GameRegistry.findRegistry(Item.class).register(ExperienceBody);
    GameRegistry.findRegistry(Item.class).register(ExperienceLegs);
    GameRegistry.findRegistry(Item.class).register(ExperienceBoots);
    GameRegistry.findRegistry(Item.class).register(RubyHelmet);
    GameRegistry.findRegistry(Item.class).register(RubyBody);
    GameRegistry.findRegistry(Item.class).register(RubyLegs);
    GameRegistry.findRegistry(Item.class).register(RubyBoots);
    GameRegistry.findRegistry(Item.class).register(AmethystHelmet);
    GameRegistry.findRegistry(Item.class).register(AmethystBody);
    GameRegistry.findRegistry(Item.class).register(AmethystLegs);
    GameRegistry.findRegistry(Item.class).register(AmethystBoots);
    GameRegistry.findRegistry(Item.class).register(ZooCage2);
    GameRegistry.findRegistry(Item.class).register(ZooCage4);
    GameRegistry.findRegistry(Item.class).register(ZooCage6);
    GameRegistry.findRegistry(Item.class).register(ZooCage8);
    GameRegistry.findRegistry(Item.class).register(ZooCage10);
    GameRegistry.findRegistry(Item.class).register(InstantShelter);
    GameRegistry.findRegistry(Item.class).register(InstantGarden);
    GameRegistry.findRegistry(Item.class).register(CrystalPinkHelmet);
    GameRegistry.findRegistry(Item.class).register(CrystalPinkBody);
    GameRegistry.findRegistry(Item.class).register(CrystalPinkLegs);
    GameRegistry.findRegistry(Item.class).register(CrystalPinkBoots);
    GameRegistry.findRegistry(Item.class).register(TigersEyeHelmet);
    GameRegistry.findRegistry(Item.class).register(TigersEyeBody);
    GameRegistry.findRegistry(Item.class).register(TigersEyeLegs);
    GameRegistry.findRegistry(Item.class).register(TigersEyeBoots);
    GameRegistry.findRegistry(Item.class).register(PeacockFeatherBoots);
    GameRegistry.findRegistry(Item.class).register(PeacockFeatherHelmet);
    GameRegistry.findRegistry(Item.class).register(PeacockFeatherBody);
    GameRegistry.findRegistry(Item.class).register(PeacockFeatherLegs);
    GameRegistry.findRegistry(Item.class).register(MobzillaHelmet);
    GameRegistry.findRegistry(Item.class).register(MobzillaBody);
    GameRegistry.findRegistry(Item.class).register(MobzillaLegs);
    GameRegistry.findRegistry(Item.class).register(MobzillaBoots);
    GameRegistry.findRegistry(Item.class).register(RoyalHelmet);
    GameRegistry.findRegistry(Item.class).register(RoyalBody);
    GameRegistry.findRegistry(Item.class).register(RoyalLegs);
    GameRegistry.findRegistry(Item.class).register(RoyalBoots);
    GameRegistry.findRegistry(Item.class).register(LapisHelmet);
    GameRegistry.findRegistry(Item.class).register(LapisBody);
    GameRegistry.findRegistry(Item.class).register(LapisLegs);
    GameRegistry.findRegistry(Item.class).register(LapisBoots);
    GameRegistry.findRegistry(Item.class).register(QueenHelmet);
    GameRegistry.findRegistry(Item.class).register(QueenBody);
    GameRegistry.findRegistry(Item.class).register(QueenLegs);
    GameRegistry.findRegistry(Item.class).register(QueenBoots);

    ItemStack OreSpiderEggStack = new ItemStack(MySpiderSpawnBlock);
    addShapelessRecipe(cpId("egg_spider"), cpId("eggs"), createVanillaSpawnEgg("spider"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpiderEggStack));

    ItemStack OreBatEggStack = new ItemStack(MyBatSpawnBlock);
    addShapelessRecipe(cpId("egg_bat"), cpId("eggs"), createVanillaSpawnEgg("bat"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBatEggStack));

    ItemStack OreCowEggStack = new ItemStack(MyCowSpawnBlock);
    addShapelessRecipe(cpId("egg_cow"), cpId("eggs"), createVanillaSpawnEgg("cow"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCowEggStack));

    ItemStack OrePigEggStack = new ItemStack(MyPigSpawnBlock);
    addShapelessRecipe(cpId("egg_pig"), cpId("eggs"), createVanillaSpawnEgg("pig"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePigEggStack));

    ItemStack OreSquidEggStack = new ItemStack(MySquidSpawnBlock);
    addShapelessRecipe(cpId("egg_squid"), cpId("eggs"), createVanillaSpawnEgg("squid"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSquidEggStack));

    ItemStack OreChickenEggStack = new ItemStack(MyChickenSpawnBlock);
    addShapelessRecipe(cpId("egg_chicken"), cpId("eggs"), createVanillaSpawnEgg("chicken"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreChickenEggStack));

    ItemStack OreCreeperEggStack = new ItemStack(MyCreeperSpawnBlock);
    addShapelessRecipe(cpId("egg_creeper"), cpId("eggs"), createVanillaSpawnEgg("creeper"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCreeperEggStack));

    ItemStack OreSkeletonEggStack = new ItemStack(MySkeletonSpawnBlock);
    addShapelessRecipe(cpId("egg_skeleton"), cpId("eggs"), createVanillaSpawnEgg("skeleton"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSkeletonEggStack));

    ItemStack OreZombieEggStack = new ItemStack(MyZombieSpawnBlock);
    addShapelessRecipe(cpId("egg_zombie"), cpId("eggs"), createVanillaSpawnEgg("zombie"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreZombieEggStack));

    ItemStack OreSlimeEggStack = new ItemStack(MySlimeSpawnBlock);
    addShapelessRecipe(cpId("egg_slime"), cpId("eggs"), createVanillaSpawnEgg("slime"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSlimeEggStack));

    ItemStack OreGhastEggStack = new ItemStack(MyGhastSpawnBlock);
    addShapelessRecipe(cpId("egg_ghast"), cpId("eggs"), createVanillaSpawnEgg("ghast"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGhastEggStack));

    ItemStack OreZombiePigmanEggStack = new ItemStack(MyZombiePigmanSpawnBlock);
    addShapelessRecipe(cpId("egg_zombie_pigman"), cpId("eggs"), createVanillaSpawnEgg("zombie_pigman"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreZombiePigmanEggStack));

    ItemStack OreEndermanEggStack = new ItemStack(MyEndermanSpawnBlock);
    addShapelessRecipe(cpId("egg_enderman"), cpId("eggs"), createVanillaSpawnEgg("enderman"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEndermanEggStack));

    ItemStack OreCaveSpiderEggStack = new ItemStack(MyCaveSpiderSpawnBlock);
    addShapelessRecipe(cpId("egg_cave_spider"), cpId("eggs"), createVanillaSpawnEgg("cave_spider"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCaveSpiderEggStack));

    ItemStack OreSilverfishEggStack = new ItemStack(MySilverfishSpawnBlock);
    addShapelessRecipe(cpId("egg_silverfish"), cpId("eggs"), createVanillaSpawnEgg("silverfish"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSilverfishEggStack));

    ItemStack OreMagmaCubeEggStack = new ItemStack(MyMagmaCubeSpawnBlock);
    addShapelessRecipe(cpId("egg_magma_cube"), cpId("eggs"), createVanillaSpawnEgg("magma_cube"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMagmaCubeEggStack));

    ItemStack OreWitchEggStack = new ItemStack(MyWitchSpawnBlock);
    addShapelessRecipe(cpId("egg_witch"), cpId("eggs"), createVanillaSpawnEgg("witch"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWitchEggStack));

    ItemStack OreSheepEggStack = new ItemStack(MySheepSpawnBlock);
    addShapelessRecipe(cpId("egg_sheep"), cpId("eggs"), createVanillaSpawnEgg("sheep"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSheepEggStack));

    ItemStack OreWolfEggStack = new ItemStack(MyWolfSpawnBlock);
    addShapelessRecipe(cpId("egg_wolf"), cpId("eggs"), createVanillaSpawnEgg("wolf"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWolfEggStack));

    ItemStack OreMooshroomEggStack = new ItemStack(MyMooshroomSpawnBlock);
    addShapelessRecipe(cpId("egg_mooshroom"), cpId("eggs"), createVanillaSpawnEgg("mooshroom"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMooshroomEggStack));

    ItemStack OreOcelotEggStack = new ItemStack(MyOcelotSpawnBlock);
    addShapelessRecipe(cpId("egg_ocelot"), cpId("eggs"), createVanillaSpawnEgg("ocelot"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreOcelotEggStack));

    ItemStack OreBlazeEggStack = new ItemStack(MyBlazeSpawnBlock);
    addShapelessRecipe(cpId("egg_blaze"), cpId("eggs"), createVanillaSpawnEgg("blaze"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBlazeEggStack));

    ItemStack OreWitherSkeletonEggStack = new ItemStack(MyWitherSkeletonSpawnBlock);
    addShapelessRecipe(cpId("egg_wither_skeleton"), cpId("eggs"), createVanillaSpawnEgg("wither_skeleton"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWitherSkeletonEggStack));

    ItemStack OreSnowGolemEggStack = new ItemStack(MySnowGolemSpawnBlock);
    addShapelessRecipe(cpId("egg_snow_golem"), cpId("eggs"), createVanillaSpawnEgg("snow_golem"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSnowGolemEggStack));

    ItemStack OreIronGolemEggStack = new ItemStack(MyIronGolemSpawnBlock);
    addShapelessRecipe(cpId("egg_iron_golem"), cpId("eggs"), createVanillaSpawnEgg("iron_golem"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreIronGolemEggStack));

    ItemStack OreEnderDragonEggStack = new ItemStack(MyEnderDragonSpawnBlock);
    addShapelessRecipe(cpId("egg_ender_dragon"), cpId("eggs"), new ItemStack(EnderDragonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnderDragonEggStack));

    ItemStack OreWitherBossEggStack = new ItemStack(MyWitherBossSpawnBlock);
    addShapelessRecipe(cpId("egg_wither_boss"), cpId("eggs"), new ItemStack(WitherBossEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWitherBossEggStack));

    ItemStack OreGirlfriendEggStack = new ItemStack(MyGirlfriendSpawnBlock);
    addShapelessRecipe(cpId("egg_girlfriend"), cpId("eggs"), new ItemStack(GirlfriendEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGirlfriendEggStack));

    ItemStack OreBoyfriendEggStack = new ItemStack(MyBoyfriendSpawnBlock);
    addShapelessRecipe(cpId("egg_boyfriend"), cpId("eggs"), new ItemStack(BoyfriendEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBoyfriendEggStack));

    ItemStack OreRedCowEggStack = new ItemStack(MyRedCowSpawnBlock);
    addShapelessRecipe(cpId("egg_red_cow"), cpId("eggs"), new ItemStack(RedCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRedCowEggStack));

    ItemStack OreCrystalCowEggStack = new ItemStack(MyCrystalCowSpawnBlock);
    addShapelessRecipe(cpId("egg_crystal_cow"), cpId("eggs"), new ItemStack(CrystalCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCrystalCowEggStack));

    ItemStack OreVillagerEggStack = new ItemStack(MyVillagerSpawnBlock);
    addShapelessRecipe(cpId("egg_villager"), cpId("eggs"), createVanillaSpawnEgg("villager"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreVillagerEggStack));

    ItemStack OreGoldCowEggStack = new ItemStack(MyGoldCowSpawnBlock);
    addShapelessRecipe(cpId("egg_gold_cow"), cpId("eggs"), new ItemStack(GoldCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGoldCowEggStack));

    ItemStack OreEnchantedCowEggStack = new ItemStack(MyEnchantedCowSpawnBlock);
    addShapelessRecipe(cpId("egg_enchanted_cow"), cpId("eggs"), new ItemStack(EnchantedCowEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnchantedCowEggStack));

    ItemStack OreMOTHRAEggStack = new ItemStack(MyMOTHRASpawnBlock);
    addShapelessRecipe(cpId("egg_mothra"), cpId("eggs"), new ItemStack(MOTHRAEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMOTHRAEggStack));

    ItemStack OreAloEggStack = new ItemStack(MyAloSpawnBlock);
    addShapelessRecipe(cpId("egg_alo"), cpId("eggs"), new ItemStack(AloEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreAloEggStack));

    ItemStack OreCryoEggStack = new ItemStack(MyCryoSpawnBlock);
    addShapelessRecipe(cpId("egg_cryo"), cpId("eggs"), new ItemStack(CryoEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCryoEggStack));

    ItemStack OreCamaEggStack = new ItemStack(MyCamaSpawnBlock);
    addShapelessRecipe(cpId("egg_cama"), cpId("eggs"), new ItemStack(CamaEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCamaEggStack));

    ItemStack OreVeloEggStack = new ItemStack(MyVeloSpawnBlock);
    addShapelessRecipe(cpId("egg_velo"), cpId("eggs"), new ItemStack(VeloEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreVeloEggStack));

    ItemStack OreHydroEggStack = new ItemStack(MyHydroSpawnBlock);
    addShapelessRecipe(cpId("egg_hydro"), cpId("eggs"), new ItemStack(HydroEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHydroEggStack));

    ItemStack OreBasilEggStack = new ItemStack(MyBasilSpawnBlock);
    addShapelessRecipe(cpId("egg_basil"), cpId("eggs"), new ItemStack(BasilEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBasilEggStack));

    ItemStack OreDragonflyEggStack = new ItemStack(MyDragonflySpawnBlock);
    addShapelessRecipe(cpId("egg_dragonfly"), cpId("eggs"), new ItemStack(DragonflyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreDragonflyEggStack));

    ItemStack OreEmperorScorpionEggStack = new ItemStack(MyEmperorScorpionSpawnBlock);
    addShapelessRecipe(cpId("egg_emperor_scorpion"), cpId("eggs"), new ItemStack(EmperorScorpionEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEmperorScorpionEggStack));

    ItemStack OreScorpionEggStack = new ItemStack(MyScorpionSpawnBlock);
    addShapelessRecipe(cpId("egg_scorpion"), cpId("eggs"), new ItemStack(ScorpionEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreScorpionEggStack));

    ItemStack OreCaveFisherEggStack = new ItemStack(MyCaveFisherSpawnBlock);
    addShapelessRecipe(cpId("egg_cave_fisher"), cpId("eggs"), new ItemStack(CaveFisherEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCaveFisherEggStack));

    ItemStack OreSpyroEggStack = new ItemStack(MySpyroSpawnBlock);
    addShapelessRecipe(cpId("egg_spyro"), cpId("eggs"), new ItemStack(SpyroEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpyroEggStack));

    ItemStack OreBaryonyxEggStack = new ItemStack(MyBaryonyxSpawnBlock);
    addShapelessRecipe(cpId("egg_baryonyx"), cpId("eggs"), new ItemStack(BaryonyxEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBaryonyxEggStack));

    ItemStack OreGammaMetroidEggStack = new ItemStack(MyGammaMetroidSpawnBlock);
    addShapelessRecipe(cpId("egg_gamma_metroid"), cpId("eggs"), new ItemStack(GammaMetroidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGammaMetroidEggStack));

    ItemStack OreCockateilEggStack = new ItemStack(MyCockateilSpawnBlock);
    addShapelessRecipe(cpId("egg_cockateil"), cpId("eggs"), new ItemStack(CockateilEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCockateilEggStack));

    ItemStack OreKyuubiEggStack = new ItemStack(MyKyuubiSpawnBlock);
    addShapelessRecipe(cpId("egg_kyuubi"), cpId("eggs"), new ItemStack(KyuubiEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreKyuubiEggStack));

    ItemStack OreAlienEggStack = new ItemStack(MyAlienSpawnBlock);
    addShapelessRecipe(cpId("egg_alien"), cpId("eggs"), new ItemStack(AlienEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreAlienEggStack));

    ItemStack OreAttackSquidEggStack = new ItemStack(MyAttackSquidSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(AttackSquidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreAttackSquidEggStack));

    ItemStack OreWaterDragonEggStack = new ItemStack(MyWaterDragonSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(WaterDragonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWaterDragonEggStack));

    ItemStack OreKrakenEggStack = new ItemStack(MyKrakenSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(KrakenEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreKrakenEggStack));

    ItemStack OreLizardEggStack = new ItemStack(MyLizardSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(LizardEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLizardEggStack));

    ItemStack OreCephadromeEggStack = new ItemStack(MyCephadromeSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CephadromeEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCephadromeEggStack));

    ItemStack OreDragonEggStack = new ItemStack(MyDragonSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(DragonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreDragonEggStack));

    ItemStack OreBeeEggStack = new ItemStack(MyBeeSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(BeeEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBeeEggStack));

    ItemStack OreHorseEggStack = new ItemStack(MyHorseSpawnBlock);
    addShapelessRecipe(cpId("egg_horse"), cpId("eggs"), createVanillaSpawnEgg("horse"), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHorseEggStack));

    ItemStack OreTrooperBugEggStack = new ItemStack(MyTrooperBugSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TrooperBugEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTrooperBugEggStack));

    ItemStack OreSpitBugEggStack = new ItemStack(MySpitBugSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(SpitBugEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpitBugEggStack));

    ItemStack OreStinkBugEggStack = new ItemStack(MyStinkBugSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(StinkBugEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreStinkBugEggStack));

    ItemStack OreOstrichEggStack = new ItemStack(MyOstrichSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(OstrichEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreOstrichEggStack));

    ItemStack OreGazelleEggStack = new ItemStack(MyGazelleSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(GazelleEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGazelleEggStack));

    ItemStack OreChipmunkEggStack = new ItemStack(MyChipmunkSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ChipmunkEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreChipmunkEggStack));
    ItemStack OreCreepingHorrorEggStack = new ItemStack(MyCreepingHorrorSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CreepingHorrorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCreepingHorrorEggStack));
    ItemStack OreTerribleTerrorEggStack = new ItemStack(MyTerribleTerrorSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TerribleTerrorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTerribleTerrorEggStack));
    ItemStack OreCliffRacerEggStack = new ItemStack(MyCliffRacerSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CliffRacerEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCliffRacerEggStack));
    ItemStack OreTriffidEggStack = new ItemStack(MyTriffidSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TriffidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTriffidEggStack));
    ItemStack OrePitchBlackEggStack = new ItemStack(MyPitchBlackSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(PitchBlackEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePitchBlackEggStack));
    ItemStack OreLurkingTerrorEggStack = new ItemStack(MyLurkingTerrorSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(LurkingTerrorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLurkingTerrorEggStack));
    ItemStack OreEnderKnightEggStack = new ItemStack(MyEnderKnightSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(EnderKnightEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnderKnightEggStack));
    ItemStack OreEnderReaperEggStack = new ItemStack(MyEnderReaperSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(EnderReaperEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEnderReaperEggStack));
    ItemStack OreGodzillaPartEggStack = new ItemStack(MyGodzillaPartSpawnBlock);
    addShapelessRecipe(cpId("godzilla_spawn"), cpId("eggs"), new ItemStack(MyGodzillaSpawnBlock), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack), Ingredient.of(OreGodzillaPartEggStack));
    ItemStack OreGodzillaEggStack = new ItemStack(MyGodzillaSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(GodzillaEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGodzillaEggStack));
    ItemStack OreTheKingPartEggStack = new ItemStack(MyTheKingPartSpawnBlock);
    addShapelessRecipe(cpId("the_king_spawn"), cpId("eggs"), new ItemStack(MyTheKingSpawnBlock), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack), Ingredient.of(OreTheKingPartEggStack));
    ItemStack OreTheKingEggStack = new ItemStack(MyTheKingSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TheKingEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTheKingEggStack));
    ItemStack OreTheQueenPartEggStack = new ItemStack(MyTheQueenPartSpawnBlock);
    addShapelessRecipe(cpId("the_queen_spawn"), cpId("eggs"), new ItemStack(MyTheQueenSpawnBlock), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack), Ingredient.of(OreTheQueenPartEggStack));
    ItemStack OreTheQueenEggStack = new ItemStack(MyTheQueenSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TheQueenEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTheQueenEggStack));
    ItemStack OreSmallWormEggStack = new ItemStack(MySmallWormSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(SmallWormEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSmallWormEggStack));
    ItemStack OreMediumWormEggStack = new ItemStack(MyMediumWormSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MediumWormEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMediumWormEggStack));
    ItemStack OreLargeWormEggStack = new ItemStack(MyLargeWormSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(LargeWormEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLargeWormEggStack));
    ItemStack OreCassowaryEggStack = new ItemStack(MyCassowarySpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CassowaryEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCassowaryEggStack));
    ItemStack OreCloudSharkEggStack = new ItemStack(MyCloudSharkSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CloudSharkEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCloudSharkEggStack));
    ItemStack OreGoldFishEggStack = new ItemStack(MyGoldFishSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(GoldFishEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreGoldFishEggStack));
    ItemStack OreLeafMonsterEggStack = new ItemStack(MyLeafMonsterSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(LeafMonsterEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLeafMonsterEggStack));
    ItemStack OreTshirtEggStack = new ItemStack(MyTshirtSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TshirtEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTshirtEggStack));
    ItemStack OreBeaverEggStack = new ItemStack(MyBeaverSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(BeaverEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBeaverEggStack));
    ItemStack OreUrchinEggStack = new ItemStack(MyUrchinSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(UrchinEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreUrchinEggStack));
    ItemStack OreFlounderEggStack = new ItemStack(MyFlounderSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(FlounderEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreFlounderEggStack));
    ItemStack OreSkateEggStack = new ItemStack(MySkateSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(SkateEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSkateEggStack));
    ItemStack OreRotatorEggStack = new ItemStack(MyRotatorSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(RotatorEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRotatorEggStack));
    ItemStack OrePeacockEggStack = new ItemStack(MyPeacockSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(PeacockEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePeacockEggStack));
    ItemStack OreFairyEggStack = new ItemStack(MyFairySpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(FairyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreFairyEggStack));
    ItemStack OreDungeonBeastEggStack = new ItemStack(MyDungeonBeastSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(DungeonBeastEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreDungeonBeastEggStack));
    ItemStack OreVortexEggStack = new ItemStack(MyVortexSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(VortexEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreVortexEggStack));
    ItemStack OreRatEggStack = new ItemStack(MyRatSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(RatEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRatEggStack));
    ItemStack OreWhaleEggStack = new ItemStack(MyWhaleSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(WhaleEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreWhaleEggStack));
    ItemStack OreIrukandjiEggStack = new ItemStack(MyIrukandjiSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(IrukandjiEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreIrukandjiEggStack));
    ItemStack OreTRexEggStack = new ItemStack(MyTRexSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TRexEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreTRexEggStack));
    ItemStack OreHerculesEggStack = new ItemStack(MyHerculesSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(HerculesEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHerculesEggStack));
    ItemStack OreMantisEggStack = new ItemStack(MyMantisSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MantisEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMantisEggStack));
    ItemStack OreStinkyEggStack = new ItemStack(MyStinkySpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(StinkyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreStinkyEggStack));
    ItemStack OreEasterBunnyEggStack = new ItemStack(MyEasterBunnySpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(EasterBunnyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreEasterBunnyEggStack));
    ItemStack OreCriminalEggStack = new ItemStack(MyCriminalSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CriminalEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCriminalEggStack));
    ItemStack OreBrutalflyEggStack = new ItemStack(MyBrutalflySpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(BrutalflyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreBrutalflyEggStack));
    ItemStack OreNastysaurusEggStack = new ItemStack(MyNastysaurusSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(NastysaurusEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreNastysaurusEggStack));
    ItemStack OrePointysaurusEggStack = new ItemStack(MyPointysaurusSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(PointysaurusEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OrePointysaurusEggStack));
    ItemStack OreCricketEggStack = new ItemStack(MyCricketSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CricketEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCricketEggStack));
    ItemStack OreFrogEggStack = new ItemStack(MyFrogSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(FrogEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreFrogEggStack));
    ItemStack OreSpiderDriverEggStack = new ItemStack(MySpiderDriverSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(SpiderDriverEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSpiderDriverEggStack));
    ItemStack OreCrabEggStack = new ItemStack(MyCrabSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CrabEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCrabEggStack));
    ItemStack OreCaterKillerEggStack = new ItemStack(MyCaterKillerSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CaterKillerEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreCaterKillerEggStack));
    ItemStack OreMolenoidEggStack = new ItemStack(MyMolenoidSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MolenoidEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreMolenoidEggStack));
    ItemStack OreSeaMonsterEggStack = new ItemStack(MySeaMonsterSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(SeaMonsterEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSeaMonsterEggStack));
    ItemStack OreSeaViperEggStack = new ItemStack(MySeaViperSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(SeaViperEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreSeaViperEggStack));
    ItemStack OreRubberDuckyEggStack = new ItemStack(MyRubberDuckySpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(RubberDuckyEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreRubberDuckyEggStack));
    ItemStack OreHammerheadEggStack = new ItemStack(MyHammerheadSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(HammerheadEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreHammerheadEggStack));
    ItemStack OreLeonEggStack = new ItemStack(MyLeonSpawnBlock);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(LeonEgg), Ingredient.of(new ItemStack(Items.WATER_BUCKET)), Ingredient.of(OreLeonEggStack));

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

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CrystalPlanksBlock, 4), Ingredient.of(new ItemStack(MyCrystalTreeLog)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CrystalWorkbenchBlock), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)));

    addShapedRecipe(cpId("crystal_furnace"), cpId("chaospersists"), new ItemStack(CrystalFurnaceBlock), "FFF", "F F", "FFF", 'F', CrystalStone);

    addShapedRecipe(cpId("crystal_chest"), cpId("chaospersists"), new ItemStack(Blocks.CHEST), "FFF", "F F", "FFF", 'F', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_door_1"), cpId("chaospersists"), new ItemStack(Items.OAK_DOOR), "FF ", "FF ", "FF ", 'F', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_door_2"), cpId("chaospersists"), new ItemStack(Items.OAK_DOOR), " FF", " FF", " FF", 'F', CrystalPlanksBlock);

    GameRegistry.addSmelting(MyOreUraniumBlock, new ItemStack(UraniumNugget), 0.3F);
    GameRegistry.addSmelting(MyOreTitaniumBlock, new ItemStack(TitaniumNugget), 0.3F);
    GameRegistry.addSmelting(MyOreRubyBlock, new ItemStack(MyRuby, 1), 1.0F);
    GameRegistry.addSmelting(MyOreAmethystBlock, new ItemStack(MyAmethyst, 1), 1.0F);
    GameRegistry.addSmelting(MyOreSaltBlock, new ItemStack(MySalt, 8), 0.1F);
    GameRegistry.addSmelting(
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oreuranium")),
        new ItemStack(UraniumNugget),
        0.3F);
    GameRegistry.addSmelting(
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oretitanium")),
        new ItemStack(TitaniumNugget),
        0.3F);
    GameRegistry.addSmelting(
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oreruby")),
        new ItemStack(MyRuby, 1),
        1.0F);
    GameRegistry.addSmelting(
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oreamethyst")),
        new ItemStack(MyAmethyst, 1),
        1.0F);
    GameRegistry.addSmelting(
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, "deepslate_oresalt")),
        new ItemStack(MySalt, 8),
        0.1F);
    GameRegistry.addSmelting(MyCornCob, new ItemStack(MyPopcorn), 0.1F);
    GameRegistry.addSmelting(MyRawCornDog, new ItemStack(MyCornDog), 0.4F);
    GameRegistry.addSmelting(MyRawBacon, new ItemStack(MyBacon), 0.2F);
    GameRegistry.addSmelting(CrystalCrystal, new ItemStack(MyCrystalPinkIngot), 0.3F);
    GameRegistry.addSmelting(TigersEye, new ItemStack(MyTigersEyeIngot), 0.3F);
    GameRegistry.addSmelting(MyRawPeacock, new ItemStack(MyPeacock), 0.4F);
    GameRegistry.addSmelting(MyRawCrabMeat, new ItemStack(MyCrabMeat), 0.2F);
    // 1.7.10 behavior: CrystalCoal is furnace fuel (20000 burn time). Smelting recipe is not required.

    GameRegistry.addSmelting(MyGreenFish, new ItemStack(Items.COOKED_COD), 0.2F);
    GameRegistry.addSmelting(MyBlueFish, new ItemStack(Items.COOKED_COD), 0.2F);
    GameRegistry.addSmelting(MyPinkFish, new ItemStack(Items.COOKED_COD), 0.2F);
    GameRegistry.addSmelting(MyRockFish, new ItemStack(Items.COOKED_COD), 0.2F);
    GameRegistry.addSmelting(MyWoodFish, new ItemStack(Items.COOKED_COD), 0.2F);
    GameRegistry.addSmelting(MyGreyFish, new ItemStack(Items.COOKED_COD), 0.2F);

    addShapedRecipe(cpId("ultimate_sword_1"), cpId("chaospersists"), new ItemStack(MyUltimateSword), " T ", " U ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_sword_2"), cpId("chaospersists"), new ItemStack(MyUltimateSword), "T  ", "U  ", "I  ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_sword_3"), cpId("chaospersists"), new ItemStack(MyUltimateSword), "  T", "  U", "  I", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_pickaxe"), cpId("chaospersists"), new ItemStack(MyUltimatePickaxe), "TUT", " U ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_shovel_1"), cpId("chaospersists"), new ItemStack(MyUltimateShovel), " U ", " T ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_shovel_2"), cpId("chaospersists"), new ItemStack(MyUltimateShovel), "U  ", "T  ", "I  ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_shovel_3"), cpId("chaospersists"), new ItemStack(MyUltimateShovel), "  U", "  T", "  I", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_hoe"), cpId("chaospersists"), new ItemStack(MyUltimateHoe), "TU ", " I ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_axe"), cpId("chaospersists"), new ItemStack(MyUltimateAxe), "TU ", "TI ", " I ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("ultimate_bow"), cpId("chaospersists"), new ItemStack(MyUltimateBow), " TS", "I S", " US", 'S', Items.STRING, 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("skate_bow"), cpId("chaospersists"), new ItemStack(MySkateBow), " TS", "T S", " TS", 'S', Items.STRING, 'T', CrystalSticks);

    addShapedRecipe(cpId("ultimate_fishing_rod"), cpId("chaospersists"), new ItemStack(MyUltimateFishingRod), "  T", " US", "I S", 'S', Items.STRING, 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("nightmare_sword"), cpId("chaospersists"), new ItemStack(MyNightmareSword), "ODO", "RTR", "OIO", 'I', Items.IRON_INGOT, 'O', MyNightmareScale, 'D', Items.DIAMOND, 'R', Items.REDSTONE, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("emerald_sword_1"), cpId("chaospersists"), new ItemStack(MyEmeraldSword), " E ", " E ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_sword_2"), cpId("chaospersists"), new ItemStack(MyEmeraldSword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_sword_3"), cpId("chaospersists"), new ItemStack(MyEmeraldSword), "  E", "  E", "  I", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("rose_sword_1"), cpId("chaospersists"), new ItemStack(MyRoseSword), " E ", " E ", " I ", 'I', Items.STICK, 'E', Blocks.POPPY);

    addShapedRecipe(cpId("rose_sword_2"), cpId("chaospersists"), new ItemStack(MyRoseSword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', Blocks.POPPY);

    addShapedRecipe(cpId("rose_sword_3"), cpId("chaospersists"), new ItemStack(MyRoseSword), "  E", "  E", "  I", 'I', Items.STICK, 'E', Blocks.POPPY);

    addShapedRecipe(cpId("emerald_pickaxe"), cpId("chaospersists"), new ItemStack(MyEmeraldPickaxe), "EEE", " I ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_shovel_1"), cpId("chaospersists"), new ItemStack(MyEmeraldShovel), " E ", " I ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_shovel_2"), cpId("chaospersists"), new ItemStack(MyEmeraldShovel), "E  ", "I  ", "I  ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_shovel_3"), cpId("chaospersists"), new ItemStack(MyEmeraldShovel), "  E", "  I", "  I", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_hoe"), cpId("chaospersists"), new ItemStack(MyEmeraldHoe), "EE ", " I ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("emerald_axe"), cpId("chaospersists"), new ItemStack(MyEmeraldAxe), "EE ", "EI ", " I ", 'I', Items.STICK, 'E', Items.EMERALD);

    addShapedRecipe(cpId("experience_sword"), cpId("chaospersists"), new ItemStack(MyExperienceSword), "EEE", "EIE", "EEE", 'I', MyEmeraldSword, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(cpId("poison_sword"), cpId("chaospersists"), new ItemStack(MyPoisonSword), "EEE", "EIE", "EEE", 'I', MyEmeraldSword, 'E', MyDeadStinkBug);

    addShapedRecipe(cpId("rat_sword_1"), cpId("chaospersists"), new ItemStack(MyRatSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalRat);

    addShapedRecipe(cpId("rat_sword_2"), cpId("chaospersists"), new ItemStack(MyRatSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalRat);

    addShapedRecipe(cpId("rat_sword_3"), cpId("chaospersists"), new ItemStack(MyRatSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalRat);

    addShapedRecipe(cpId("fairy_sword_1"), cpId("chaospersists"), new ItemStack(MyFairySword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalFairy);

    addShapedRecipe(cpId("fairy_sword_2"), cpId("chaospersists"), new ItemStack(MyFairySword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalFairy);

    addShapedRecipe(cpId("fairy_sword_3"), cpId("chaospersists"), new ItemStack(MyFairySword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalFairy);

    addShapedRecipe(cpId("crystal_wood_sword_1"), cpId("chaospersists"), new ItemStack(MyCrystalWoodSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_sword_2"), cpId("chaospersists"), new ItemStack(MyCrystalWoodSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_sword_3"), cpId("chaospersists"), new ItemStack(MyCrystalWoodSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_pickaxe"), cpId("chaospersists"), new ItemStack(MyCrystalWoodPickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_shovel_1"), cpId("chaospersists"), new ItemStack(MyCrystalWoodShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_shovel_2"), cpId("chaospersists"), new ItemStack(MyCrystalWoodShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_shovel_3"), cpId("chaospersists"), new ItemStack(MyCrystalWoodShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_hoe"), cpId("chaospersists"), new ItemStack(MyCrystalWoodHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_wood_axe"), cpId("chaospersists"), new ItemStack(MyCrystalWoodAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_chest_2"), cpId("chaospersists"), new ItemStack(Blocks.CHEST), "EEE", "E E", "EEE", 'E', CrystalPlanksBlock);

    addShapedRecipe(cpId("crystal_pink_sword_1"), cpId("chaospersists"), new ItemStack(MyCrystalPinkSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_sword_2"), cpId("chaospersists"), new ItemStack(MyCrystalPinkSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_sword_3"), cpId("chaospersists"), new ItemStack(MyCrystalPinkSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_pickaxe"), cpId("chaospersists"), new ItemStack(MyCrystalPinkPickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_shovel_1"), cpId("chaospersists"), new ItemStack(MyCrystalPinkShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_shovel_2"), cpId("chaospersists"), new ItemStack(MyCrystalPinkShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_shovel_3"), cpId("chaospersists"), new ItemStack(MyCrystalPinkShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_hoe"), cpId("chaospersists"), new ItemStack(MyCrystalPinkHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_pink_axe"), cpId("chaospersists"), new ItemStack(MyCrystalPinkAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', MyCrystalPinkIngot);

    addShapedRecipe(cpId("crystal_bucket"), cpId("chaospersists"), new ItemStack(Items.BUCKET), "   ", "I I", " I ", 'I', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeSword_1"), cpId("chaospersists"), new ItemStack(MyTigersEyeSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeSword_2"), cpId("chaospersists"), new ItemStack(MyTigersEyeSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeSword_3"), cpId("chaospersists"), new ItemStack(MyTigersEyeSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyePickaxe"), cpId("chaospersists"), new ItemStack(MyTigersEyePickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeShovel"), cpId("chaospersists"), new ItemStack(MyTigersEyeShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeShovel"), cpId("chaospersists"), new ItemStack(MyTigersEyeShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeShovel"), cpId("chaospersists"), new ItemStack(MyTigersEyeShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeHoe"), cpId("chaospersists"), new ItemStack(MyTigersEyeHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeAxe"), cpId("chaospersists"), new ItemStack(MyTigersEyeAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_MyCrystalStoneSword"), cpId("chaospersists"), new ItemStack(MyCrystalStoneSword), " E ", " E ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneSword"), cpId("chaospersists"), new ItemStack(MyCrystalStoneSword), "E  ", "E  ", "I  ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneSword"), cpId("chaospersists"), new ItemStack(MyCrystalStoneSword), "  E", "  E", "  I", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStonePickaxe"), cpId("chaospersists"), new ItemStack(MyCrystalStonePickaxe), "EEE", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneShovel"), cpId("chaospersists"), new ItemStack(MyCrystalStoneShovel), " E ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneShovel"), cpId("chaospersists"), new ItemStack(MyCrystalStoneShovel), "E  ", "I  ", "I  ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneShovel"), cpId("chaospersists"), new ItemStack(MyCrystalStoneShovel), "  E", "  I", "  I", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneHoe"), cpId("chaospersists"), new ItemStack(MyCrystalStoneHoe), "EE ", " I ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyCrystalStoneAxe"), cpId("chaospersists"), new ItemStack(MyCrystalStoneAxe), "EE ", "EI ", " I ", 'I', CrystalSticks, 'E', CrystalStone);

    addShapedRecipe(cpId("recipe_MyRubySword"), cpId("chaospersists"), new ItemStack(MyRubySword), " E ", " E ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubySword"), cpId("chaospersists"), new ItemStack(MyRubySword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubySword"), cpId("chaospersists"), new ItemStack(MyRubySword), "  E", "  E", "  I", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubyPickaxe"), cpId("chaospersists"), new ItemStack(MyRubyPickaxe), "EEE", " I ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubyShovel"), cpId("chaospersists"), new ItemStack(MyRubyShovel), " E ", " I ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubyShovel"), cpId("chaospersists"), new ItemStack(MyRubyShovel), "E  ", "I  ", "I  ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubyShovel"), cpId("chaospersists"), new ItemStack(MyRubyShovel), "  E", "  I", "  I", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubyHoe"), cpId("chaospersists"), new ItemStack(MyRubyHoe), "EE ", " I ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyRubyAxe"), cpId("chaospersists"), new ItemStack(MyRubyAxe), "EE ", "EI ", " I ", 'I', Items.STICK, 'E', MyRuby);

    addShapedRecipe(cpId("recipe_MyAmethystSword"), cpId("chaospersists"), new ItemStack(MyAmethystSword), " E ", " E ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystSword"), cpId("chaospersists"), new ItemStack(MyAmethystSword), "E  ", "E  ", "I  ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystSword"), cpId("chaospersists"), new ItemStack(MyAmethystSword), "  E", "  E", "  I", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystPickaxe"), cpId("chaospersists"), new ItemStack(MyAmethystPickaxe), "EEE", " I ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystShovel"), cpId("chaospersists"), new ItemStack(MyAmethystShovel), " E ", " I ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystShovel"), cpId("chaospersists"), new ItemStack(MyAmethystShovel), "E  ", "I  ", "I  ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystShovel"), cpId("chaospersists"), new ItemStack(MyAmethystShovel), "  E", "  I", "  I", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystHoe"), cpId("chaospersists"), new ItemStack(MyAmethystHoe), "EE ", " I ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyAmethystAxe"), cpId("chaospersists"), new ItemStack(MyAmethystAxe), "EE ", "EI ", " I ", 'I', Items.STICK, 'E', MyAmethyst);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyHammy), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(MyBigHammer)), Ingredient.of(new ItemStack(GreenGoo)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyBattleAxe), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(MyUltimateAxe)), Ingredient.of(new ItemStack(GreenGoo)));
    addShapedRecipe(cpId("recipe_MyChainsaw"), cpId("chaospersists"), new ItemStack(MyChainsaw), "EEE", "EIE", "EEE", 'I', MyUltimateAxe, 'E', Blocks.REDSTONE_BLOCK);

    addShapedRecipe(cpId("recipe_MyQueenBattleAxe"), cpId("chaospersists"), new ItemStack(MyQueenBattleAxe), "EIE", "EIE", " I ", 'I', Items.IRON_INGOT, 'E', MyQueenScale);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyBertha), Ingredient.of(new ItemStack(BerthaHandle)), Ingredient.of(new ItemStack(BerthaGuard)), Ingredient.of(new ItemStack(BerthaBlade)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(BerthaHandle), Ingredient.of(new ItemStack(MyRayGun)), Ingredient.of(new ItemStack(MyBigHammer)), Ingredient.of(new ItemStack(MyMantisClaw)), Ingredient.of(new ItemStack(MyWaterDragonScale)), Ingredient.of(new ItemStack(GreenGoo)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(BerthaGuard), Ingredient.of(new ItemStack(MolenoidNose)), Ingredient.of(new ItemStack(SeaMonsterScale)), Ingredient.of(new ItemStack(MyMothScale)), Ingredient.of(new ItemStack(MyBasiliskScale)), Ingredient.of(new ItemStack(MyNightmareScale)), Ingredient.of(new ItemStack(MyEmperorScorpionScale)), Ingredient.of(new ItemStack(MyJumpyBugScale)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(BerthaBlade), Ingredient.of(new ItemStack(MyKrakenTooth)), Ingredient.of(new ItemStack(WormTooth)), Ingredient.of(new ItemStack(TRexTooth)), Ingredient.of(new ItemStack(MyUltimateSword)), Ingredient.of(new ItemStack(CaterKillerJaw)), Ingredient.of(new ItemStack(SeaViperTongue)), Ingredient.of(new ItemStack(VortexEye)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MySlice), Ingredient.of(new ItemStack(MyBertha)), Ingredient.of(new ItemStack(Items.IRON_INGOT)));

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyIrukandjiArrow), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(MyIrukandji)), Ingredient.of(new ItemStack(CrystalSticks)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(Items.RED_BED), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(MyPeacockFeather)), Ingredient.of(new ItemStack(CrystalPlanksBlock)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MySquidZooka), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)), Ingredient.of(new ItemStack(Items.IRON_INGOT)));

    addShapedRecipe(cpId("recipe_MyIngotUranium"), cpId("chaospersists"), new ItemStack(MyIngotUranium), "UUU", "UUU", "UUU", 'U', UraniumNugget);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(UraniumNugget, 9), Ingredient.of(new ItemStack(MyIngotUranium)));
    addShapedRecipe(cpId("recipe_MyIngotTitanium"), cpId("chaospersists"), new ItemStack(MyIngotTitanium), "UUU", "UUU", "UUU", 'U', TitaniumNugget);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(TitaniumNugget, 9), Ingredient.of(new ItemStack(MyIngotTitanium)));

    addShapedRecipe(cpId("recipe_MyBlockUraniumBlock"), cpId("chaospersists"), new ItemStack(MyBlockUraniumBlock), "UUU", "UUU", "UUU", 'U', MyIngotUranium);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyIngotUranium, 9), Ingredient.of(new ItemStack(MyBlockUraniumBlock)));

    addShapedRecipe(cpId("recipe_MyBlockTitaniumBlock"), cpId("chaospersists"), new ItemStack(MyBlockTitaniumBlock), "TTT", "TTT", "TTT", 'T', MyIngotTitanium);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyIngotTitanium, 9), Ingredient.of(new ItemStack(MyBlockTitaniumBlock)));

    addShapedRecipe(cpId("recipe_MyBlockMobzillaScaleBlock"), cpId("chaospersists"), new ItemStack(MyBlockMobzillaScaleBlock), "TTT", "TTT", "TTT", 'T', MyGodzillaScale);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyGodzillaScale, 9), Ingredient.of(new ItemStack(MyBlockMobzillaScaleBlock)));

    addShapedRecipe(cpId("recipe_MyBlockRubyBlock"), cpId("chaospersists"), new ItemStack(MyBlockRubyBlock), "TTT", "TTT", "TTT", 'T', MyRuby);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyRuby, 9), Ingredient.of(new ItemStack(MyBlockRubyBlock)));

    addShapedRecipe(cpId("recipe_MyBlockAmethystBlock"), cpId("chaospersists"), new ItemStack(MyBlockAmethystBlock), "TTT", "TTT", "TTT", 'T', MyAmethyst);

    addShapedRecipe(cpId("recipe_MyCrystalPinkBlock"), cpId("chaospersists"), new ItemStack(MyCrystalPinkBlock), "TTT", "TTT", "TTT", 'T', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_MyTigersEyeBlock"), cpId("chaospersists"), new ItemStack(MyTigersEyeBlock), "TTT", "TTT", "TTT", 'T', MyTigersEyeIngot);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyAmethyst, 9), Ingredient.of(new ItemStack(MyBlockAmethystBlock)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyCrystalPinkIngot, 9), Ingredient.of(new ItemStack(MyCrystalPinkBlock)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyTigersEyeIngot, 9), Ingredient.of(new ItemStack(MyTigersEyeBlock)));

    addShapedRecipe(cpId("recipe_MyEnderPearlBlock"), cpId("chaospersists"), new ItemStack(MyEnderPearlBlock), "TTT", "TTT", "TTT", 'T', Items.ENDER_PEARL);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(Items.ENDER_PEARL, 9), Ingredient.of(new ItemStack(MyEnderPearlBlock)));

    addShapedRecipe(cpId("recipe_MyEyeOfEnderBlock"), cpId("chaospersists"), new ItemStack(MyEyeOfEnderBlock), "TTT", "TTT", "TTT", 'T', Items.ENDER_EYE);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(Items.ENDER_EYE, 9), Ingredient.of(new ItemStack(MyEyeOfEnderBlock)));

    addShapedRecipe(cpId("recipe_MyThunderStaff"), cpId("chaospersists"), new ItemStack(MyThunderStaff), "DR ", "RR ", "  R", 'D', Items.DIAMOND, 'R', MyRuby);

    addShapedRecipe(cpId("recipe_MyWrench"), cpId("chaospersists"), new ItemStack(MyWrench), "D D", " D ", " D ", 'D', Items.IRON_INGOT);

    ItemStack MilkBucket = new ItemStack(Items.MILK_BUCKET);
    ItemStack SomePaper = new ItemStack(Items.PAPER);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyButter, 4), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyCheese, 2), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket), Ingredient.of(MilkBucket));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyButteredPopcorn), Ingredient.of(new ItemStack(MyPopcorn)), Ingredient.of(new ItemStack(MyButter)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyButteredSaltedPopcorn), Ingredient.of(new ItemStack(MyButteredPopcorn)), Ingredient.of(new ItemStack(MySalt)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyButteredSaltedPopcorn), Ingredient.of(new ItemStack(MyPopcorn)), Ingredient.of(new ItemStack(MySalt)), Ingredient.of(new ItemStack(MyButter)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyPopcornBag), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(new ItemStack(MyButteredSaltedPopcorn)), Ingredient.of(SomePaper), Ingredient.of(SomePaper), Ingredient.of(SomePaper));

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyRawCornDog, 4), Ingredient.of(new ItemStack(MyCornCob)), Ingredient.of(new ItemStack(Items.CHICKEN)), Ingredient.of(new ItemStack(Items.PORKCHOP)), Ingredient.of(new ItemStack(Items.STICK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyRawBacon, 2), Ingredient.of(new ItemStack(MySalt)), Ingredient.of(new ItemStack(Items.PORKCHOP)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyButterCandy, 4), Ingredient.of(new ItemStack(MyButter)), Ingredient.of(new ItemStack(Items.SUGAR)));

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MySalad, 1), Ingredient.of(new ItemStack(MyLettuce)), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(MyRadish)), Ingredient.of(new ItemStack(Items.CARROT)), Ingredient.of(new ItemStack(Items.BOWL)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyBLT, 1), Ingredient.of(new ItemStack(MyBacon)), Ingredient.of(new ItemStack(MyLettuce)), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(MyButter)), Ingredient.of(new ItemStack(Items.BREAD)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyPizzaItem, 1), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(MyCheese)), Ingredient.of(new ItemStack(MyBacon)), Ingredient.of(new ItemStack(Items.BREAD)));
    addShapedRecipe(cpId("recipe_MyDuctTapeItem"), cpId("chaospersists"), new ItemStack(MyDuctTapeItem), "   ", "AAA", "RRR", 'R', Items.STRING, 'A', Items.SLIME_BALL);

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyCrabbyPatty, 1), Ingredient.of(new ItemStack(MyCrabMeat)), Ingredient.of(new ItemStack(MyLettuce)), Ingredient.of(new ItemStack(MyTomato)), Ingredient.of(new ItemStack(Items.BREAD)));

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ZooCage2), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ZooCage4), Ingredient.of(new ItemStack(ZooCage2)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ZooCage6), Ingredient.of(new ItemStack(ZooCage4)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ZooCage8), Ingredient.of(new ItemStack(ZooCage6)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ZooCage10), Ingredient.of(new ItemStack(ZooCage8)), Ingredient.of(new ItemStack(Blocks.IRON_BLOCK)), Ingredient.of(new ItemStack(Blocks.GLASS)), Ingredient.of(new ItemStack(Blocks.QUARTZ_BLOCK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(InstantShelter), Ingredient.of(new ItemStack(Blocks.REDSTONE_BLOCK)), Ingredient.of(new ItemStack(Items.STICK)), Ingredient.of(new ItemStack(Blocks.COBBLESTONE)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(InstantGarden), Ingredient.of(new ItemStack(Blocks.REDSTONE_BLOCK)), Ingredient.of(new ItemStack(Items.WHEAT)), Ingredient.of(new ItemStack(Items.GUNPOWDER)));

    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CreeperLauncher, 4), Ingredient.of(new ItemStack(Items.PAPER)), Ingredient.of(new ItemStack(Items.REDSTONE)), Ingredient.of(new ItemStack(Items.STICK)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(NetherLost, 1), Ingredient.of(new ItemStack(Items.NETHER_STAR)), Ingredient.of(new ItemStack(Blocks.NETHERRACK)));

    addShapedRecipe(cpId("recipe_Sifter"), cpId("chaospersists"), new ItemStack(Sifter), "RRR", "RAR", "RRR", 'R', Items.STICK, 'A', Items.STRING);
    addShapedRecipe(cpId("recipe_MagicApple"), cpId("chaospersists"), new ItemStack(MagicApple), "RRR", "RAR", "RRR", 'R', Blocks.REDSTONE_BLOCK, 'A', Items.APPLE);
    addShapedRecipe(cpId("recipe_RandomDungeon"), cpId("chaospersists"), new ItemStack(RandomDungeon), "RRR", "RAR", "RRR", 'R', Blocks.REDSTONE_BLOCK, 'A', Items.COAL);

    if (MinersDreamExpensive == 0)
    {
      addShapedRecipe(cpId("recipe_MinersDream"), cpId("chaospersists"), new ItemStack(MinersDream), "CCC", "RRR", "GGG", 'R', Blocks.REDSTONE_BLOCK, 'C', Blocks.CACTUS, 'G', Items.GUNPOWDER);
    }
    else
    {
      addShapedRecipe(cpId("recipe_MinersDream"), cpId("chaospersists"), new ItemStack(MinersDream), "CCC", "RRR", "GGG", 'R', Blocks.REDSTONE_BLOCK, 'C', Blocks.CACTUS, 'G', Blocks.TNT);
    }
    addShapedRecipe(cpId("recipe_stepup"), cpId("chaospersists"), new ItemStack(MyStepUp, 8), "GC ", " C ", " C ", 'C', Blocks.COBBLESTONE, 'G', Items.GUNPOWDER);
    addShapedRecipe(cpId("recipe_stepdown"), cpId("chaospersists"), new ItemStack(MyStepDown, 8), " C ", " C ", "GC ", 'C', Blocks.COBBLESTONE, 'G', Items.GUNPOWDER);
    addShapedRecipe(cpId("recipe_stepaccross"), cpId("chaospersists"), new ItemStack(MyStepAccross, 8), " C ", "GC ", " C ", 'C', Blocks.COBBLESTONE, 'G', Items.GUNPOWDER);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ExtremeTorch, 4), Ingredient.of(new ItemStack(Items.REDSTONE)), Ingredient.of(new ItemStack(Items.STICK)), Ingredient.of(new ItemStack(Items.COAL)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(ExtremeTorch, 1), Ingredient.of(new ItemStack(Items.REDSTONE)), Ingredient.of(new ItemStack(Blocks.TORCH)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CrystalSticks, 6), Ingredient.of(new ItemStack(CrystalPlanksBlock)), Ingredient.of(new ItemStack(CrystalPlanksBlock)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(CrystalTorch, 6), Ingredient.of(new ItemStack(CrystalCoal)), Ingredient.of(new ItemStack(CrystalSticks)));
    addShapedRecipe(cpId("recipe_krakenrepellent"), cpId("chaospersists"), new ItemStack(KrakenRepellent, 1), "D D", "STS", "D D", 'D', MyDeadStinkBug, 'T', ExtremeTorch, 'S', Items.STRING);
    addShapedRecipe(cpId("recipe_creeperrepellent"), cpId("chaospersists"), new ItemStack(CreeperRepellent, 1), "D D", "STS", "D D", 'D', GreenGoo, 'T', ExtremeTorch, 'S', Items.STRING);
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyAppleSeed, 6), Ingredient.of(new ItemStack(Items.APPLE)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyCherrySeed, 1), Ingredient.of(new ItemStack(MyCherry)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyPeachSeed, 1), Ingredient.of(new ItemStack(MyPeach)));
    addShapelessRecipe(cpId("egg_mob"), cpId("eggs"), new ItemStack(MyExperienceCatcher, 1), Ingredient.of(new ItemStack(Items.GLASS_BOTTLE)), Ingredient.of(new ItemStack(Items.STICK)), Ingredient.of(new ItemStack(Items.STRING)));
    addShapedRecipe(cpId("recipe_experiencetreeseed"), cpId("chaospersists"), new ItemStack(MyExperienceTreeSeed, 1), "EEE", "EAE", "EEE", 'A', MyAppleSeed, 'E', Items.EXPERIENCE_BOTTLE);

    /* entity types registered via ENTITY_TYPES DeferredRegister (see class fields) */

    ItemStack RayStack = new ItemStack(MyRayGun);
    RayStack.setDamageValue(32767);
    addShapelessRecipe(cpId("repair_raygun"), cpId("eggs"), new ItemStack(MyRayGun), Ingredient.of(new ItemStack(Blocks.REDSTONE_BLOCK)), Ingredient.of(RayStack));

    ItemStack SquidStack = new ItemStack(MySquidZooka);
    SquidStack.setDamageValue(32767);
    addShapelessRecipe(cpId("repair_squidzooka"), cpId("eggs"), new ItemStack(MySquidZooka), Ingredient.of(new ItemStack(Items.INK_SAC)), Ingredient.of(SquidStack));

    int nextEntityId = 0;
    GirlfriendID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("girlfriend"), Girlfriend.class, "Girlfriend", GirlfriendID, this, 64, 1, false);

    RedCowID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("apple_cow"), RedCow.class, "Apple Cow", RedCowID, this, 64, 1, false);

    GoldCowID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("golden_apple_cow"), GoldCow.class, "Golden Apple Cow", GoldCowID, this, 64, 1, false);

    EnchantedCowID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("enchanted_golden_apple_cow"), EnchantedCow.class, "Enchanted Golden Apple Cow", EnchantedCowID, this, 64, 1, false);

    ButterflyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("butterfly"), EntityButterfly.class, "Butterfly", ButterflyID, this, 32, 1, false);

    LunaMothID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("moth"), EntityLunaMoth.class, "Moth", LunaMothID, this, 32, 1, false);

    MosquitoID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("mosquito"), EntityMosquito.class, "Mosquito", MosquitoID, this, 16, 1, false);

    FireflyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("firefly"), Firefly.class, "Firefly", FireflyID, this, 64, 1, false);

    BeeID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("bee"), Bee.class, "Bee", BeeID, this, 64, 1, false);

    MothraID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("mothra"), Mothra.class, "Mothra", MothraID, this, 128, 1, false);

    AntID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ant"), EntityAnt.class, "Ant", AntID, this, 16, 1, false);
    RedAntID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("red_ant"), EntityRedAnt.class, "Red Ant", RedAntID, this, 16, 1, false);
    RainbowAntID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("rainbow_ant"), EntityRainbowAnt.class, "Rainbow Ant", RainbowAntID, this, 16, 1, false);
    UnstableAntID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("unstable_ant"), EntityUnstableAnt.class, "Unstable Ant", UnstableAntID, this, 16, 1, false);

    Robot1ID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("bomb_omb"), Robot1.class, "Bomb-Omb", Robot1ID, this, 32, 1, false);
    Robot2ID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("robo_pounder"), Robot2.class, "Robo-Pounder", Robot2ID, this, 64, 1, false);
    Robot3ID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("robo_gunner"), Robot3.class, "Robo-Gunner", Robot3ID, this, 64, 1, false);
    Robot4ID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("robo_warrior"), Robot4.class, "Robo-Warrior", Robot4ID, this, 64, 1, false);
    Robot5ID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("robo_sniper"), Robot5.class, "Robo-Sniper", Robot5ID, this, 64, 1, false);

    AlosaurusID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("alosaurus"), Alosaurus.class, "Alosaurus", AlosaurusID, this, 64, 1, false);
    CryolophosaurusID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cryolophosaurus"), Cryolophosaurus.class, "Cryolophosaurus", CryolophosaurusID, this, 64, 1, false);
    BasiliskID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("basilisk"), Basilisk.class, "Basilisk", BasiliskID, this, 64, 1, false);
    CamarasaurusID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("camarasaurus"), Camarasaurus.class, "Camarasaurus", CamarasaurusID, this, 64, 1, false);
    HydroliscID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("hydrolisc"), Hydrolisc.class, "Hydrolisc", HydroliscID, this, 64, 1, false);
    VelocityRaptorID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("velocity_raptor"), VelocityRaptor.class, "Velocity Raptor", VelocityRaptorID, this, 64, 1, false);

    DragonflyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("dragonfly"), Dragonfly.class, "Dragonfly", DragonflyID, this, 64, 1, false);

    EmperorScorpionID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("emperor_scorpion"), EmperorScorpion.class, "Emperor Scorpion", EmperorScorpionID, this, 64, 1, false);

    ScorpionID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("scorpion"), Scorpion.class, "Scorpion", ScorpionID, this, 32, 1, false);

    CaveFisherID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cave_fisher"), CaveFisher.class, "CaveFisher", CaveFisherID, this, 32, 1, false);

    SpyroID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("baby_dragon"), Spyro.class, "Baby Dragon", SpyroID, this, 64, 1, false);

    BaryonyxID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("baryonyx"), Baryonyx.class, "Baryonyx", BaryonyxID, this, 64, 1, false);

    GammaMetroidID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("gamma_metroid"), GammaMetroid.class, "WTF?", GammaMetroidID, this, 64, 1, false);
    EntityRegistry.registerModEntity(cpId("wtf"), GammaMetroid.class, "WTF? Legacy", nextEntityId++, this, 64, 1, false);

    CockateilID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("bird"), Cockateil.class, "Bird", CockateilID, this, 32, 1, false);

    RubyBirdID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ruby_bird"), RubyBird.class, "Ruby Bird", RubyBirdID, this, 32, 1, false);

    KyuubiID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("kyuubi"), Kyuubi.class, "Kyuubi", KyuubiID, this, 64, 1, false);

    WaterDragonID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("water_dragon"), WaterDragon.class, "Water Dragon", WaterDragonID, this, 64, 1, false);

    AttackSquidID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("attack_squid"), AttackSquid.class, "Attack Squid", AttackSquidID, this, 32, 1, false);

    AlienID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("alien"), Alien.class, "Alien", AlienID, this, 64, 1, false);

    ElevatorID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("hoverboard"), Elevator.class, "Hoverboard", ElevatorID, this, 128, 1, true);

    KrakenID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_kraken"), Kraken.class, "The Kraken", KrakenID, this, 128, 1, false);

    LizardID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("lizard"), Lizard.class, "Lizard", LizardID, this, 64, 1, false);

    CephadromeID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cephadrome"), Cephadrome.class, "Cephadrome", CephadromeID, this, 128, 1, true);

    DragonID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("dragon"), Dragon.class, "Dragon", DragonID, this, 128, 1, true);

    ChipmunkID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("chipmunk"), Chipmunk.class, "Chipmunk", ChipmunkID, this, 32, 1, false);

    GazelleID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("gazelle"), Gazelle.class, "Gazelle", GazelleID, this, 64, 1, false);

    OstrichID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ostrich"), Ostrich.class, "Ostrich", OstrichID, this, 64, 1, true);

    TrooperBugID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("jumpy_bug"), TrooperBug.class, "Jumpy Bug", TrooperBugID, this, 64, 1, false);

    SpitBugID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("spit_bug"), SpitBug.class, "Spit Bug", SpitBugID, this, 64, 1, false);

    StinkBugID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("stink_bug"), StinkBug.class, "Stink Bug", StinkBugID, this, 32, 1, false);

    TshirtID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("tshirt"), Tshirt.class, "T-Shirt", TshirtID, this, 32, 1, false);

    IslandID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("island"), Island.class, "Island", IslandID, this, 64, 1, false);

    IslandTooID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("island_too"), IslandToo.class, "IslandToo", IslandTooID, this, 64, 1, false);

    CreepingHorrorID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("creeping_horror"), CreepingHorror.class, "Creeping Horror", CreepingHorrorID, this, 64, 1, false);

    TerribleTerrorID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("terrible_terror"), TerribleTerror.class, "Terrible Terror", TerribleTerrorID, this, 64, 1, false);

    CliffRacerID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cliff_racer"), CliffRacer.class, "Cliff Racer", CliffRacerID, this, 32, 1, false);

    TriffidID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("triffid"), Triffid.class, "Triffid", TriffidID, this, 64, 1, false);

    PitchBlackID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("nightmare"), PitchBlack.class, "Nightmare", PitchBlackID, this, 64, 1, false);

    LurkingTerrorID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("lurking_terror"), LurkingTerror.class, "Lurking Terror", LurkingTerrorID, this, 64, 1, false);

    GodzillaID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("mobzilla"), Godzilla.class, "Mobzilla", GodzillaID, this, 128, 1, false);

    GhostID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ghost"), Ghost.class, "Ghost", GhostID, this, 32, 1, false);

    GhostSkellyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ghost_pumpkin_skelly"), GhostSkelly.class, "Ghost Pumpkin Skelly", GhostSkellyID, this, 64, 1, false);

    WormSmallID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("small_worm"), WormSmall.class, "Small Worm", WormSmallID, this, 32, 1, false);

    WormMediumID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("medium_worm"), WormMedium.class, "Medium Worm", WormMediumID, this, 64, 1, false);

    WormLargeID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("large_worm"), WormLarge.class, "Large Worm", WormLargeID, this, 64, 1, false);

    CassowaryID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cassowary"), Cassowary.class, "Cassowary", CassowaryID, this, 64, 1, false);

    CloudSharkID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cloud_shark"), CloudShark.class, "Cloud Shark", CloudSharkID, this, 64, 1, false);

    GoldFishID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("gold_fish"), GoldFish.class, "Gold Fish", GoldFishID, this, 32, 1, false);

    LeafMonsterID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("leaf_monster"), LeafMonster.class, "Leaf Monster", LeafMonsterID, this, 64, 1, false);

    GodzillaHeadID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("mobzilla_head"), GodzillaHead.class, "MobzillaHead", GodzillaHeadID, this, 128, 10, true);

    EnderKnightID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ender_knight"), EnderKnight.class, "Ender Knight", EnderKnightID, this, 64, 1, false);

    EnderReaperID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ender_reaper"), EnderReaper.class, "Ender Reaper", EnderReaperID, this, 64, 1, false);

    BeaverID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("beaver"), Beaver.class, "Beaver", BeaverID, this, 64, 1, false);

    TermiteID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("termite"), Termite.class, "Termite", TermiteID, this, 32, 1, false);

    FairyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("fairy"), Fairy.class, "Fairy", FairyID, this, 32, 1, false);

    PeacockID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("peacock"), Peacock.class, "Peacock", PeacockID, this, 64, 1, false);

    RotatorID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("rotator"), Rotator.class, "Rotator", RotatorID, this, 64, 1, false);

    VortexID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("vortex"), Vortex.class, "Vortex", VortexID, this, 64, 1, false);

    DungeonBeastID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("dungeon_beast"), DungeonBeast.class, "Dungeon Beast", DungeonBeastID, this, 64, 1, false);

    RatID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("rat"), Rat.class, "Rat", RatID, this, 32, 1, false);

    FlounderID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("flounder"), Flounder.class, "Flounder", FlounderID, this, 32, 1, false);

    WhaleID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("whale"), Whale.class, "Whale", WhaleID, this, 64, 1, false);

    IrukandjiID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("irukandji"), Irukandji.class, "Irukandji", IrukandjiID, this, 32, 1, false);

    SkateID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("skate"), Skate.class, "Skate", SkateID, this, 32, 1, false);

    UrchinID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("crystal_urchin"), Urchin.class, "Crystal Urchin", UrchinID, this, 64, 1, false);

    MantisID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("mantis"), Mantis.class, "Mantis", MantisID, this, 64, 1, false);

    HerculesBeetleID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("hercules_beetle"), HerculesBeetle.class, "Hercules Beetle", HerculesBeetleID, this, 64, 1, false);

    TRexID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("trex"), TRex.class, "T. Rex", TRexID, this, 64, 1, false);
    EntityRegistry.registerModEntity(cpId("t._rex"), TRex.class, "T. Rex Legacy", nextEntityId++, this, 64, 1, false);

    StinkyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("stinky"), Stinky.class, "Stinky", StinkyID, this, 64, 1, false);

    CoinID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("coin"), Coin.class, "Coin", CoinID, this, 64, 1, false);

    TheKingID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_king"), TheKing.class, "The King", TheKingID, this, 128, 1, false);

    KingHeadID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("king_head"), KingHead.class, "KingHead", KingHeadID, this, 128, 10, true);

    TheQueenID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_queen"), TheQueen.class, "The Queen", TheQueenID, this, 128, 1, false);

    QueenHeadID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("queen_head"), QueenHead.class, "QueenHead", QueenHeadID, this, 128, 10, true);

    BoyfriendID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("boyfriend"), Boyfriend.class, "Boyfriend", BoyfriendID, this, 64, 1, false);

    ThePrinceID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_prince"), ThePrince.class, "The Prince", ThePrinceID, this, 64, 1, false);

    MolenoidID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("molenoid"), Molenoid.class, "Molenoid", MolenoidID, this, 64, 1, false);

    SeaMonsterID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("sea_monster"), SeaMonster.class, "Sea Monster", SeaMonsterID, this, 64, 1, false);

    SeaViperID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("sea_viper"), SeaViper.class, "Sea Viper", SeaViperID, this, 64, 1, false);

    EasterBunnyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("easter_bunny"), EasterBunny.class, "Easter Bunny", EasterBunnyID, this, 64, 1, false);

    CaterKillerID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("caterkiller"), CaterKiller.class, "CaterKiller", CaterKillerID, this, 64, 1, false);

    CrystalCowID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("crystal_apple_cow"), CrystalCow.class, "Crystal Apple Cow", CrystalCowID, this, 64, 1, false);

    LeonID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("leonopteryx"), Leon.class, "Leonopteryx", LeonID, this, 64, 1, false);

    HammerheadID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("hammerhead"), Hammerhead.class, "Hammerhead", HammerheadID, this, 64, 1, false);

    RubberDuckyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("rubber_ducky"), RubberDucky.class, "Rubber Ducky", RubberDuckyID, this, 64, 1, false);

    ThePrinceTeenID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_young_prince"), ThePrinceTeen.class, "The Young Prince", ThePrinceTeenID, this, 64, 1, false);

    BandPID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("criminal"), BandP.class, "Criminal", BandPID, this, 64, 1, false);

    RockBaseID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("rock"), RockBase.class, "Rock", RockBaseID, this, 32, 1, false);

    BrutalflyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("brutalfly"), Brutalfly.class, "Brutalfly", BrutalflyID, this, 128, 1, false);

    NastysaurusID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("nastysaurus"), Nastysaurus.class, "Nastysaurus", NastysaurusID, this, 128, 1, false);

    PointysaurusID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("pointysaurus"), Pointysaurus.class, "Pointysaurus", PointysaurusID, this, 64, 1, false);

    CricketID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("cricket"), Cricket.class, "Cricket", CricketID, this, 32, 1, false);

    ThePrincessID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_princess"), ThePrincess.class, "The Princess", ThePrincessID, this, 64, 1, false);

    FrogID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("frog"), Frog.class, "Frog", FrogID, this, 32, 1, false);

    ThePrinceAdultID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("the_young_adult_prince"), ThePrinceAdult.class, "The Young Adult Prince", ThePrinceAdultID, this, 128, 1, false);

    SpiderRobotID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("robot_spider"), SpiderRobot.class, "Robot Spider", SpiderRobotID, this, 128, 1, false);

    SpiderDriverID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("spider_driver"), SpiderDriver.class, "Spider Driver", SpiderDriverID, this, 64, 1, false);

    JefferyID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("jeffery"), GiantRobot.class, "Jeffery", JefferyID, this, 128, 1, false);

    AntRobotID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("robot_red_ant"), AntRobot.class, "Robot Red Ant", AntRobotID, this, 128, 1, false);

    CrabID = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("crab"), Crab.class, "Crab", CrabID, this, 64, 1, false);

    GregorianCalendar gcalendar = new GregorianCalendar();

    int nowmonth = gcalendar.get(2);
    int nowday = gcalendar.get(5);

    if ((nowmonth == 9) && (nowday == 31)) {
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BEACH });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_ROCK });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_CLEAR_ROCK });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA_HILLS });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });

      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BEACH });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_ROCK });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_CLEAR_ROCK });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA_HILLS });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA });
      EntityRegistry.addSpawn(Ghost.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    if ((nowmonth == 1) && (nowday == 14)) {
      valentines_day = 1;
    }

    if ((nowmonth == 3) && (nowday == 20)) {
      easter_day = 1;
    }

    if (GirlfriendEnable != 0) {
      EntityRegistry.addSpawn(Girlfriend.class, 30, 8, 15, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BEACH });
      EntityRegistry.addSpawn(Girlfriend.class, 10, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Girlfriend.class, 8, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Girlfriend.class, 10, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Girlfriend.class, 10, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
      EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Girlfriend.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Girlfriend.class, 2, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Girlfriend.class, 2, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (BoyfriendEnable != 0) {
      EntityRegistry.addSpawn(Boyfriend.class, 30, 8, 15, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BEACH });
      EntityRegistry.addSpawn(Boyfriend.class, 10, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Boyfriend.class, 8, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Boyfriend.class, 10, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Boyfriend.class, 10, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
      EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Boyfriend.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Boyfriend.class, 2, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Boyfriend.class, 2, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (BeaverEnable != 0) {
      EntityRegistry.addSpawn(Beaver.class, 10, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Beaver.class, 3, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Beaver.class, 2, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Beaver.class, 2, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Beaver.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Beaver.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
    }

    if (CowEnable != 0)
    {
      EntityRegistry.addSpawn(RedCow.class, 8, 4, 8, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(RedCow.class, 8, 4, 8, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(RedCow.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(RedCow.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(RedCow.class, 8, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(RedCow.class, 2, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });

      EntityRegistry.addSpawn(GoldCow.class, 5, 2, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(GoldCow.class, 5, 2, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(GoldCow.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(GoldCow.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });

      EntityRegistry.addSpawn(EnchantedCow.class, 3, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(EnchantedCow.class, 3, 2, 4, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(EnchantedCow.class, 5, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(EnchantedCow.class, 15, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MUSHROOM_ISLAND });
    }

    if (CriminalEnable != 0) {
      EntityRegistry.addSpawn(BandP.class, 20, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(BandP.class, 20, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(BandP.class, 20, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
    }

    if (WormEnable != 0) {
      EntityRegistry.addSpawn(WormLarge.class, 25, 1, 1, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(WormLarge.class, 15, 1, 1, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(WormLarge.class, 10, 1, 1, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (ButterflyEnable != 0) {
      EntityRegistry.addSpawn(EntityButterfly.class, 8, 5, 15, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BEACH });
      EntityRegistry.addSpawn(EntityButterfly.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(EntityButterfly.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(EntityButterfly.class, 30, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(EntityButterfly.class, 20, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(EntityButterfly.class, 20, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(EntityButterfly.class, 10, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(EntityButterfly.class, 20, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(EntityButterfly.class, 20, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(EntityButterfly.class, 20, 4, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(EntityButterfly.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(EntityButterfly.class, 10, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(EntityButterfly.class, 10, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (MothEnable != 0) {
      EntityRegistry.addSpawn(EntityLunaMoth.class, 8, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 8, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 20, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 20, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 20, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(EntityLunaMoth.class, 10, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (CassowaryEnable != 0) {
      EntityRegistry.addSpawn(Cassowary.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(Cassowary.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(Cassowary.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_WITH_TREES });
      EntityRegistry.addSpawn(Cassowary.class, 5, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Cassowary.class, 5, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Cassowary.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Cassowary.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA_HILLS });
      EntityRegistry.addSpawn(Cassowary.class, 3, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Cassowary.class, 10, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if ((EasterBunnyEnable != 0) && (easter_day != 0)) {
      EntityRegistry.addSpawn(EasterBunny.class, 10, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(EasterBunny.class, 10, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(EasterBunny.class, 10, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(EasterBunny.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(EasterBunny.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(EasterBunny.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(EasterBunny.class, 8, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
    }

    if (FireflyEnable != 0) {
      EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Firefly.class, 10, 4, 8, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Firefly.class, 15, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Firefly.class, 10, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
      EntityRegistry.addSpawn(Firefly.class, 15, 3, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Firefly.class, 15, 3, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Firefly.class, 15, 2, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Firefly.class, 15, 2, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Firefly.class, 15, 2, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA_HILLS });
      EntityRegistry.addSpawn(Firefly.class, 10, 2, 8, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Firefly.class, 10, 2, 8, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (WhaleEnable != 0) {
      EntityRegistry.addSpawn(Whale.class, 1, 1, 2, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DEEP_OCEAN });
    }

    if (BeeEnable != 0) {
      EntityRegistry.addSpawn(Bee.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Bee.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Bee.class, 5, 3, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Bee.class, 5, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Bee.class, 3, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Bee.class, 3, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Bee.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Bee.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Bee.class, 3, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Bee.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (MantisEnable != 0) {
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Mantis.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Mantis.class, 1, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Mantis.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (HerculesBeetleEnable != 0) {
      EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(HerculesBeetle.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA_HILLS });
      EntityRegistry.addSpawn(HerculesBeetle.class, 5, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA_HILLS });
      EntityRegistry.addSpawn(HerculesBeetle.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA_HILLS });
    }

    if (MolenoidEnable != 0) {
      EntityRegistry.addSpawn(Molenoid.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Molenoid.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Molenoid.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (CaterKillerEnable != 0) {
      EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(CaterKiller.class, 4, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(CaterKiller.class, 4, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(CaterKiller.class, 6, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(CaterKiller.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(CaterKiller.class, 10, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    if (ChipmunkEnable != 0) {
      EntityRegistry.addSpawn(Chipmunk.class, 8, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Chipmunk.class, 5, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Chipmunk.class, 4, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Chipmunk.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Chipmunk.class, 5, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Chipmunk.class, 4, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Chipmunk.class, 10, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
      EntityRegistry.addSpawn(Chipmunk.class, 2, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Chipmunk.class, 6, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
    }

    if (OstrichEnable != 0) {
      EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
      EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Ostrich.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (CephadromeEnable != 0) {
      EntityRegistry.addSpawn(Cephadrome.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ICE_PLAINS });
      EntityRegistry.addSpawn(Cephadrome.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA });
    }

    if (MosquitoEnable != 0) {
      EntityRegistry.addSpawn(EntityMosquito.class, 30, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(EntityMosquito.class, 20, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(EntityMosquito.class, 20, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(EntityMosquito.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    if (GhostEnable != 0) {
      EntityRegistry.addSpawn(Ghost.class, 15, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA });
      EntityRegistry.addSpawn(Ghost.class, 10, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA_HILLS });
      EntityRegistry.addSpawn(Ghost.class, 6, 4, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FROZEN_RIVER });
      EntityRegistry.addSpawn(Ghost.class, 2, 1, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Ghost.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    if (GhostSkellyEnable != 0) {
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA });
      EntityRegistry.addSpawn(GhostSkelly.class, 10, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA_HILLS });
      EntityRegistry.addSpawn(GhostSkelly.class, 6, 4, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FROZEN_RIVER });
      EntityRegistry.addSpawn(GhostSkelly.class, 2, 1, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(GhostSkelly.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    if (DragonflyEnable != 0) {
      EntityRegistry.addSpawn(Dragonfly.class, 5, 3, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Dragonfly.class, 4, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
    }

    if (KyuubiEnable != 0) {
      EntityRegistry.addSpawn(Kyuubi.class, 10, 1, 1, MobCategory.MONSTER, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.HELL });
    }

    if (StinkyEnable != 0) {
      EntityRegistry.addSpawn(Stinky.class, 2, 1, 1, MobCategory.MONSTER, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.HELL });
      EntityRegistry.addSpawn(Stinky.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA });
      EntityRegistry.addSpawn(Stinky.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_CLEAR_ROCK });
      EntityRegistry.addSpawn(Stinky.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_ROCK });
    }

    if (CockateilEnable != 0) {
      EntityRegistry.addSpawn(Cockateil.class, 10, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BEACH });
      EntityRegistry.addSpawn(Cockateil.class, 10, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(Cockateil.class, 10, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(Cockateil.class, 25, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Cockateil.class, 20, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Cockateil.class, 35, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Cockateil.class, 25, 5, 10, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Cockateil.class, 10, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Cockateil.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Cockateil.class, 5, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
      EntityRegistry.addSpawn(Cockateil.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Cockateil.class, 5, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Cockateil.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Cockateil.class, 15, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Cockateil.class, 11, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Cockateil.class, 11, 1, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }

    if (HydroliscEnable != 0) {
      EntityRegistry.addSpawn(Hydrolisc.class, 25, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Hydrolisc.class, 15, 2, 5, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Hydrolisc.class, 10, 1, 3, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Hydrolisc.class, 5, 3, 6, MobCategory.CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
    }

    if (MothraEnable != 0) {
      EntityRegistry.addSpawn(Mothra.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(Mothra.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_WITH_TREES });
    }
    if (BrutalflyEnable != 0) {
      EntityRegistry.addSpawn(Brutalfly.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA_HILLS });
      EntityRegistry.addSpawn(Brutalfly.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_WITH_TREES });
      EntityRegistry.addSpawn(Brutalfly.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_CLEAR_ROCK });
    }
    if (WaterDragonEnable != 0) {
      EntityRegistry.addSpawn(WaterDragon.class, 5, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(WaterDragon.class, 3, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(WaterDragon.class, 2, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.OCEAN });
      EntityRegistry.addSpawn(WaterDragon.class, 2, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
    }
    if (SeaMonsterEnable != 0) {
      EntityRegistry.addSpawn(SeaMonster.class, 4, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.OCEAN });
      EntityRegistry.addSpawn(SeaMonster.class, 2, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
    }
    if (SeaViperEnable != 0) {
      EntityRegistry.addSpawn(SeaViper.class, 3, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.OCEAN });
      EntityRegistry.addSpawn(SeaViper.class, 2, 1, 1, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
    }
    if (CrabEnable != 0) {
      EntityRegistry.addSpawn(Crab.class, 2, 3, 6, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.OCEAN });
      EntityRegistry.addSpawn(Crab.class, 1, 3, 6, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Crab.class, 1, 2, 4, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
    }
    if (AttackSquidEnable != 0) {
      EntityRegistry.addSpawn(AttackSquid.class, 12, 6, 10, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(AttackSquid.class, 10, 5, 9, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(AttackSquid.class, 7, 4, 8, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.OCEAN });
    }
    if (LizardEnable != 0) {
      EntityRegistry.addSpawn(Lizard.class, 5, 2, 4, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Lizard.class, 4, 2, 4, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Lizard.class, 2, 2, 4, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.OCEAN });
    }
    if (RubberDuckyEnable != 0) {
      EntityRegistry.addSpawn(RubberDucky.class, 10, 10, 20, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(RubberDucky.class, 4, 4, 6, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.STONE_BEACH });
    }
    if (BasiliskEnable != 0) {
      EntityRegistry.addSpawn(Basilisk.class, 3, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Basilisk.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Basilisk.class, 4, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Basilisk.class, 15, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }
    if (EmperorScorpionEnable != 0) {
      EntityRegistry.addSpawn(EmperorScorpion.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(EmperorScorpion.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
    }
    if (TrooperBugEnable != 0) {
      EntityRegistry.addSpawn(TrooperBug.class, 3, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(TrooperBug.class, 1, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA });
    }
    if (SpitBugEnable != 0) {
      EntityRegistry.addSpawn(SpitBug.class, 6, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
    }
    if (StinkBugEnable != 0) {
      EntityRegistry.addSpawn(StinkBug.class, 10, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(StinkBug.class, 8, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(StinkBug.class, 6, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(StinkBug.class, 4, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(StinkBug.class, 8, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
    }
    if (ScorpionEnable != 0) {
      EntityRegistry.addSpawn(Scorpion.class, 15, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(Scorpion.class, 28, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
      EntityRegistry.addSpawn(Scorpion.class, 15, 3, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA });
      EntityRegistry.addSpawn(Scorpion.class, 15, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
      EntityRegistry.addSpawn(Scorpion.class, 6, 1, 3, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA });
      EntityRegistry.addSpawn(Scorpion.class, 4, 1, 3, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_CLEAR_ROCK });
      EntityRegistry.addSpawn(Scorpion.class, 5, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_ROCK });
    }

    if (LeafMonsterEnable != 0) {
      EntityRegistry.addSpawn(LeafMonster.class, 5, 2, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(LeafMonster.class, 5, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(LeafMonster.class, 3, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(LeafMonster.class, 3, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(LeafMonster.class, 3, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(LeafMonster.class, 2, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(LeafMonster.class, 2, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(LeafMonster.class, 2, 2, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
    }

    if (EnderKnightEnable != 0) {
      EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(EnderKnight.class, 4, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(EnderKnight.class, 2, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(EnderKnight.class, 2, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(EnderKnight.class, 2, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(EnderKnight.class, 20, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }
    if (EnderReaperEnable != 0) {
      EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS });
      EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.EXTREME_HILLS_EDGE });
      EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(EnderReaper.class, 2, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(EnderReaper.class, 1, 1, 2, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.DESERT });
      EntityRegistry.addSpawn(EnderReaper.class, 38, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    if (CoinEnable != 0) {
      EntityRegistry.addSpawn(Coin.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Coin.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Coin.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Coin.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Coin.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.COLD_TAIGA });
      EntityRegistry.addSpawn(Coin.class, 2, 1, 1, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
    }

    if (CricketEnable != 0) {
      EntityRegistry.addSpawn(Cricket.class, 3, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST });
      EntityRegistry.addSpawn(Cricket.class, 2, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.FOREST_HILLS });
      EntityRegistry.addSpawn(Cricket.class, 3, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Cricket.class, 2, 3, 5, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE_HILLS });
      EntityRegistry.addSpawn(Cricket.class, 3, 4, 8, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.PLAINS });
      EntityRegistry.addSpawn(Cricket.class, 2, 2, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST });
      EntityRegistry.addSpawn(Cricket.class, 2, 2, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.BIRCH_FOREST_HILLS });
      EntityRegistry.addSpawn(Cricket.class, 3, 1, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
      EntityRegistry.addSpawn(Cricket.class, 2, 1, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.REDWOOD_TAIGA });
      EntityRegistry.addSpawn(Cricket.class, 2, 1, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
      EntityRegistry.addSpawn(Cricket.class, 1, 1, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SAVANNA_PLATEAU });
    }
    if (FrogEnable != 0) {
      EntityRegistry.addSpawn(Frog.class, 20, 3, 6, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Frog.class, 3, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.RIVER });
      EntityRegistry.addSpawn(Frog.class, 3, 3, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.JUNGLE });
      EntityRegistry.addSpawn(Frog.class, 20, 2, 6, MobCategory.WATER_CREATURE, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
      EntityRegistry.addSpawn(Frog.class, 2, 2, 6, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.SWAMPLAND });
    }

    if (PeacockEnable != 0) {
      EntityRegistry.addSpawn(Peacock.class, 1, 1, 3, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA });
      EntityRegistry.addSpawn(Peacock.class, 1, 1, 3, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.MESA_CLEAR_ROCK });
    }

    if (FairyEnable != 0) {
      EntityRegistry.addSpawn(Fairy.class, 25, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }
    if (RatEnable != 0) {
      EntityRegistry.addSpawn(Rat.class, 35, 10, 20, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
      EntityRegistry.addSpawn(Rat.class, 25, 2, 8, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.TAIGA });
    }
    if (DungeonBeastEnable != 0) {
      EntityRegistry.addSpawn(DungeonBeast.class, 20, 2, 4, MobCategory.AMBIENT, new com.astryxion.chaospersists.compat.minecraft.world.biome.Biome[] { Biomes.ROOFED_FOREST });
    }

    int shoeid = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("shoes"), Shoes.class, "Shoes", shoeid, this, 64, 1, true);

    addShapedRecipe(cpId("recipe_UltimateHelmet"), cpId("chaospersists"), new ItemStack(UltimateHelmet), "   ", "TIT", "U U", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("recipe_UltimateHelmet"), cpId("chaospersists"), new ItemStack(UltimateHelmet), "TIT", "U U", "   ", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("recipe_UltimateBody"), cpId("chaospersists"), new ItemStack(UltimateBody), "I I", "TTT", "UUU", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("recipe_UltimateLegs"), cpId("chaospersists"), new ItemStack(UltimateLegs), "III", "T T", "U U", 'I', Items.IRON_INGOT, 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("recipe_UltimateBoots"), cpId("chaospersists"), new ItemStack(UltimateBoots), "   ", "T T", "U U", 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("recipe_UltimateBoots"), cpId("chaospersists"), new ItemStack(UltimateBoots), "T T", "U U", "   ", 'U', MyIngotUranium, 'T', MyIngotTitanium);

    addShapedRecipe(cpId("recipe_LavaEelHelmet"), cpId("chaospersists"), new ItemStack(LavaEelHelmet), "   ", "***", "* *", '*', MyLavaEel);

    addShapedRecipe(cpId("recipe_LavaEelHelmet"), cpId("chaospersists"), new ItemStack(LavaEelHelmet), "***", "* *", "   ", '*', MyLavaEel);

    addShapedRecipe(cpId("recipe_LavaEelBody"), cpId("chaospersists"), new ItemStack(LavaEelBody), "* *", "***", "***", '*', MyLavaEel);

    addShapedRecipe(cpId("recipe_LavaEelLegs"), cpId("chaospersists"), new ItemStack(LavaEelLegs), "***", "* *", "* *", '*', MyLavaEel);

    addShapedRecipe(cpId("recipe_LavaEelBoots"), cpId("chaospersists"), new ItemStack(LavaEelBoots), "   ", "* *", "* *", '*', MyLavaEel);

    addShapedRecipe(cpId("recipe_MothScaleHelmet"), cpId("chaospersists"), new ItemStack(MothScaleHelmet), "   ", "***", "* *", '*', MyMothScale);

    addShapedRecipe(cpId("recipe_MothScaleHelmet"), cpId("chaospersists"), new ItemStack(MothScaleHelmet), "***", "* *", "   ", '*', MyMothScale);

    addShapedRecipe(cpId("recipe_MothScaleBody"), cpId("chaospersists"), new ItemStack(MothScaleBody), "* *", "***", "***", '*', MyMothScale);

    addShapedRecipe(cpId("recipe_MothScaleLegs"), cpId("chaospersists"), new ItemStack(MothScaleLegs), "***", "* *", "* *", '*', MyMothScale);

    addShapedRecipe(cpId("recipe_MothScaleBoots"), cpId("chaospersists"), new ItemStack(MothScaleBoots), "   ", "* *", "* *", '*', MyMothScale);

    addShapedRecipe(cpId("recipe_EmeraldHelmet"), cpId("chaospersists"), new ItemStack(EmeraldHelmet), "   ", "***", "* *", '*', Items.EMERALD);

    addShapedRecipe(cpId("recipe_EmeraldHelmet"), cpId("chaospersists"), new ItemStack(EmeraldHelmet), "***", "* *", "   ", '*', Items.EMERALD);

    addShapedRecipe(cpId("recipe_EmeraldBody"), cpId("chaospersists"), new ItemStack(EmeraldBody), "* *", "***", "***", '*', Items.EMERALD);

    addShapedRecipe(cpId("recipe_EmeraldLegs"), cpId("chaospersists"), new ItemStack(EmeraldLegs), "***", "* *", "* *", '*', Items.EMERALD);

    addShapedRecipe(cpId("recipe_EmeraldBoots"), cpId("chaospersists"), new ItemStack(EmeraldBoots), "   ", "* *", "* *", '*', Items.EMERALD);

    addShapedRecipe(cpId("recipe_RubyHelmet"), cpId("chaospersists"), new ItemStack(RubyHelmet), "   ", "***", "* *", '*', MyRuby);

    addShapedRecipe(cpId("recipe_RubyHelmet"), cpId("chaospersists"), new ItemStack(RubyHelmet), "***", "* *", "   ", '*', MyRuby);

    addShapedRecipe(cpId("recipe_RubyBody"), cpId("chaospersists"), new ItemStack(RubyBody), "* *", "***", "***", '*', MyRuby);

    addShapedRecipe(cpId("recipe_RubyLegs"), cpId("chaospersists"), new ItemStack(RubyLegs), "***", "* *", "* *", '*', MyRuby);

    addShapedRecipe(cpId("recipe_RubyBoots"), cpId("chaospersists"), new ItemStack(RubyBoots), "   ", "* *", "* *", '*', MyRuby);

    addShapedRecipe(cpId("recipe_AmethystHelmet"), cpId("chaospersists"), new ItemStack(AmethystHelmet), "   ", "***", "* *", '*', MyAmethyst);

    addShapedRecipe(cpId("recipe_AmethystHelmet"), cpId("chaospersists"), new ItemStack(AmethystHelmet), "***", "* *", "   ", '*', MyAmethyst);

    addShapedRecipe(cpId("recipe_AmethystBody"), cpId("chaospersists"), new ItemStack(AmethystBody), "* *", "***", "***", '*', MyAmethyst);

    addShapedRecipe(cpId("recipe_AmethystLegs"), cpId("chaospersists"), new ItemStack(AmethystLegs), "***", "* *", "* *", '*', MyAmethyst);

    addShapedRecipe(cpId("recipe_AmethystBoots"), cpId("chaospersists"), new ItemStack(AmethystBoots), "   ", "* *", "* *", '*', MyAmethyst);

    addShapedRecipe(cpId("recipe_CrystalPinkHelmet"), cpId("chaospersists"), new ItemStack(CrystalPinkHelmet), "   ", "***", "* *", '*', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_CrystalPinkHelmet"), cpId("chaospersists"), new ItemStack(CrystalPinkHelmet), "***", "* *", "   ", '*', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_CrystalPinkBody"), cpId("chaospersists"), new ItemStack(CrystalPinkBody), "* *", "***", "***", '*', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_CrystalPinkLegs"), cpId("chaospersists"), new ItemStack(CrystalPinkLegs), "***", "* *", "* *", '*', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_CrystalPinkBoots"), cpId("chaospersists"), new ItemStack(CrystalPinkBoots), "   ", "* *", "* *", '*', MyCrystalPinkIngot);

    addShapedRecipe(cpId("recipe_MobzillaHelmet"), cpId("chaospersists"), new ItemStack(MobzillaHelmet), "   ", "***", "* *", '*', MyGodzillaScale);

    addShapedRecipe(cpId("recipe_MobzillaHelmet"), cpId("chaospersists"), new ItemStack(MobzillaHelmet), "***", "* *", "   ", '*', MyGodzillaScale);

    addShapedRecipe(cpId("recipe_MobzillaBody"), cpId("chaospersists"), new ItemStack(MobzillaBody), "* *", "***", "***", '*', MyGodzillaScale);

    addShapedRecipe(cpId("recipe_MobzillaLegs"), cpId("chaospersists"), new ItemStack(MobzillaLegs), "***", "* *", "* *", '*', MyGodzillaScale);

    addShapedRecipe(cpId("recipe_MobzillaBoots"), cpId("chaospersists"), new ItemStack(MobzillaBoots), "   ", "* *", "* *", '*', MyGodzillaScale);

    addShapedRecipe(cpId("recipe_LapisHelmet"), cpId("chaospersists"), new ItemStack(LapisHelmet), "   ", "***", "* *", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(cpId("recipe_LapisHelmet"), cpId("chaospersists"), new ItemStack(LapisHelmet), "***", "* *", "   ", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(cpId("recipe_LapisBody"), cpId("chaospersists"), new ItemStack(LapisBody), "* *", "***", "***", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(cpId("recipe_LapisLegs"), cpId("chaospersists"), new ItemStack(LapisLegs), "***", "* *", "* *", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(cpId("recipe_LapisBoots"), cpId("chaospersists"), new ItemStack(LapisBoots), "   ", "* *", "* *", '*', Blocks.LAPIS_BLOCK);

    addShapedRecipe(cpId("recipe_QueenHelmet"), cpId("chaospersists"), new ItemStack(QueenHelmet), "   ", "***", "* *", '*', MyQueenScale);

    addShapedRecipe(cpId("recipe_QueenHelmet"), cpId("chaospersists"), new ItemStack(QueenHelmet), "***", "* *", "   ", '*', MyQueenScale);

    addShapedRecipe(cpId("recipe_QueenBody"), cpId("chaospersists"), new ItemStack(QueenBody), "* *", "***", "***", '*', MyQueenScale);

    addShapedRecipe(cpId("recipe_QueenLegs"), cpId("chaospersists"), new ItemStack(QueenLegs), "***", "* *", "* *", '*', MyQueenScale);

    addShapedRecipe(cpId("recipe_QueenBoots"), cpId("chaospersists"), new ItemStack(QueenBoots), "   ", "* *", "* *", '*', MyQueenScale);

    addShapedRecipe(cpId("recipe_PeacockFeatherHelmet"), cpId("chaospersists"), new ItemStack(PeacockFeatherHelmet), "   ", "***", "* *", '*', MyPeacockFeather);

    addShapedRecipe(cpId("recipe_PeacockFeatherHelmet"), cpId("chaospersists"), new ItemStack(PeacockFeatherHelmet), "***", "* *", "   ", '*', MyPeacockFeather);

    addShapedRecipe(cpId("recipe_PeacockFeatherBody"), cpId("chaospersists"), new ItemStack(PeacockFeatherBody), "* *", "***", "***", '*', MyPeacockFeather);

    addShapedRecipe(cpId("recipe_PeacockFeatherLegs"), cpId("chaospersists"), new ItemStack(PeacockFeatherLegs), "***", "* *", "* *", '*', MyPeacockFeather);

    addShapedRecipe(cpId("recipe_PeacockFeatherBoots"), cpId("chaospersists"), new ItemStack(PeacockFeatherBoots), "   ", "* *", "* *", '*', MyPeacockFeather);

    addShapedRecipe(cpId("recipe_TigersEyeHelmet"), cpId("chaospersists"), new ItemStack(TigersEyeHelmet), "   ", "***", "* *", '*', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_TigersEyeHelmet"), cpId("chaospersists"), new ItemStack(TigersEyeHelmet), "***", "* *", "   ", '*', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_TigersEyeBody"), cpId("chaospersists"), new ItemStack(TigersEyeBody), "* *", "***", "***", '*', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_TigersEyeLegs"), cpId("chaospersists"), new ItemStack(TigersEyeLegs), "***", "* *", "* *", '*', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_TigersEyeBoots"), cpId("chaospersists"), new ItemStack(TigersEyeBoots), "   ", "* *", "* *", '*', MyTigersEyeIngot);

    addShapedRecipe(cpId("recipe_ExperienceHelmet"), cpId("chaospersists"), new ItemStack(ExperienceHelmet), "EEE", "EAE", "EEE", 'A', EmeraldHelmet, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(cpId("recipe_ExperienceBody"), cpId("chaospersists"), new ItemStack(ExperienceBody), "EEE", "EAE", "EEE", 'A', EmeraldBody, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(cpId("recipe_ExperienceLegs"), cpId("chaospersists"), new ItemStack(ExperienceLegs), "EEE", "EAE", "EEE", 'A', EmeraldLegs, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(cpId("recipe_ExperienceBoots"), cpId("chaospersists"), new ItemStack(ExperienceBoots), "EEE", "EAE", "EEE", 'A', EmeraldBoots, 'E', Items.EXPERIENCE_BOTTLE);

    addShapedRecipe(cpId("recipe_Blocks.WEB"), cpId("chaospersists"), new ItemStack(Blocks.COBWEB), "***", "* *", "***", '*', Items.STRING);

    int cageid = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("entity_cage"), EntityCage.class, "EntityCage", cageid, this, 64, 1, true);

    addShapedRecipe(cpId("recipe_cageempty_iron"), cpId("chaospersists"), new ItemStack(CageEmpty, 2), "IWI", "W W", "IWI", 'W', Items.STICK, 'I', Items.IRON_INGOT);

    addShapedRecipe(cpId("recipe_cageempty_crystal"), cpId("chaospersists"), new ItemStack(CageEmpty, 2), "IWI", "W W", "IWI", 'W', CrystalSticks, 'I', MyCrystalPinkIngot);

    int arrowid = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("ultimate_arrow"), UltimateArrow.class, "UltimateArrow", arrowid, this, 64, 1, true);

    int irukandiarrowid = nextEntityId++;
    EntityRegistry.registerModEntity(cpId("irukandji_arrow"), IrukandjiArrow.class, "IrukandjiArrow", irukandiarrowid, this, 64, 1, true);
    addShapelessRecipe(cpId("planks_skytree"), cpId("eggs"), new ItemStack(Blocks.OAK_PLANKS, 4), Ingredient.of(new ItemStack(MySkyTreeLog)));
    addShapelessRecipe(cpId("planks_duplicator"), cpId("eggs"), new ItemStack(Blocks.OAK_PLANKS, 4), Ingredient.of(new ItemStack(MyDT)));

    addShapedRecipe(cpId("recipe_MyElevator"), cpId("chaospersists"), new ItemStack(MyElevator), "   ", "WWW", "DRD", 'W', Blocks.OAK_PLANKS, 'R', Items.REDSTONE, 'D', Items.DIAMOND);

    GameRegistry.registerWorldGenerator(this.chaospersistsGen, 10);

    proxy.registerRenderThings();

    proxy.registerKeyboardInput();

    proxy.registerNetworkStuff();

    NetworkRegistry.INSTANCE.registerGuiHandler(this, new ChaosGUIHandler());

    DoDispenserRegistrations();

    ChaosSpawnPlacements.registerAllAfterLegacySpawns();
  }

  @SubscribeEvent
  public void onLootTableLoad(LootTableLoadEvent event) {
    if (event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
      LootPool pool = event.getTable().getPool("main");
      if (pool != null) {
        addLegacyLootEntry(pool, MyRuby, 3, 1, 1, "chaospersists:ruby");
        addLegacyLootEntry(pool, MyAmethyst, 3, 1, 1, "chaospersists:amethyst");
        addLegacyLootEntry(pool, MyThunderStaff, 2, 1, 1, "chaospersists:thunderstaff");
      }
    } else if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)) {
      LootPool pool = event.getTable().getPool("main");
      if (pool != null) {
        addLegacyLootEntry(pool, MyRuby, 3, 1, 1, "chaospersists:ruby");
        addLegacyLootEntry(pool, MyAmethyst, 3, 1, 1, "chaospersists:amethyst");
        addLegacyLootEntry(pool, AntRobotKit, 3, 1, 1, "chaospersists:antrobotkit");
      }
    } else if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)) {
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
    net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer entry =
        LootItem.lootTableItem(item)
            .setWeight(weight)
            .setQuality(0)
            .apply(
                SetItemCountFunction.setCount(
                    UniformGenerator.between((float) minCount, (float) maxCount)))
            .build();
    LootPoolEntryContainer[] entries =
        ObfuscationReflectionHelper.getPrivateValue(LootPool.class, pool, "entries");
    LootPoolEntryContainer[] merged = java.util.Arrays.copyOf(entries, entries.length + 1);
    merged[entries.length] = entry;
    ObfuscationReflectionHelper.setPrivateValue(LootPool.class, pool, merged, "entries");
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
    if (event == null || event.getEntity() == null) {
      return;
    }
    LivingEntity victim = event.getEntity();
    DamageSource src = event.getSource();
    if (src == null) {
      return;
    }
    Entity attacker = src.getEntity();
    if (!(attacker instanceof Player)) {
      return;
    }
    Player player = (Player) attacker;
    if (victim == player) {
      return;
    }
    if (victim.level() == null || victim.level().isClientSide()) {
      return;
    }
    if (victim instanceof TamableAnimal) {
      TamableAnimal te = (TamableAnimal) victim;
      if (te.isTame() && player.getUUID().equals(te.getOwnerUUID())) {
        return;
      }
    }
    for (Girlfriend g :
        victim.level().getEntitiesOfClass(Girlfriend.class, player.getBoundingBox().inflate(16.0D))) {
      if (!g.isTame() || g.isOrderedToSit() || !g.isOwnedBy(player)) {
        continue;
      }
      g.setTarget(victim);
    }
    for (Boyfriend b :
        victim.level().getEntitiesOfClass(Boyfriend.class, player.getBoundingBox().inflate(16.0D))) {
      if (!b.isTame() || b.isOrderedToSit() || !b.isOwnedBy(player)) {
        continue;
      }
      b.setTarget(victim);
    }
  }

  /**
   * 1.7.10 parity: OreSpawn armor used legacy linear reduction (damage * (1 - armor/25)).
   * In 1.12+/1.20+, high-damage hits penetrate armor more aggressively via toughness,
   * so players in Ultimate/Mobzilla/etc. and high-defense mobs take far too much damage.
   */
  @SubscribeEvent
  public void onLivingHurtLegacyArmorParity(LivingHurtEvent event) {
    if (event == null || event.getEntity() == null) {
      return;
    }
    LivingEntity living = event.getEntity();
    if (living.level() == null || living.level().isClientSide()) {
      return;
    }
    ResourceLocation id = EntityType.getKey(living.getType());
    boolean chaosMob = id != null && "chaospersists".equals(id.getNamespace());
    boolean playerInChaosArmor = living instanceof Player && hasChaosArmorEquipped(living);
    if (!chaosMob && !playerInChaosArmor) {
      return;
    }
    DamageSource source = event.getSource();
    if (source == null || source.is(DamageTypeTags.BYPASSES_ARMOR)) {
      return;
    }
    float incoming = event.getAmount();
    if (incoming <= 0.0f) {
      return;
    }

    int armor = Math.max(0, living.getArmorValue());
    if (armor <= 0) {
      return;
    }

    // 1.7.10-style final damage expectation (armor above 20 still helps).
    float legacyFinal = Math.max(0.0f, incoming * (25.0f - (float)armor) / 25.0f);

    float toughness = 0.0f;
    AttributeInstance toughAttr = living.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.ARMOR_TOUGHNESS);
    if (toughAttr != null) {
      toughness = (float)toughAttr.getValue();
    }

    float vanillaFinalAtIncoming = net.minecraft.world.damagesource.CombatRules.getDamageAfterAbsorb(incoming, (float)armor, toughness);
    if (vanillaFinalAtIncoming <= legacyFinal + 1.0e-4f) {
      return;
    }

    // Invert modern armor curve by binary search for pre-armor amount.
    float low = 0.0f;
    float high = incoming;
    float cappedHigh = incoming * 8.0f + 40.0f;
    while (net.minecraft.world.damagesource.CombatRules.getDamageAfterAbsorb(high, (float)armor, toughness) < legacyFinal && high < cappedHigh) {
      high *= 2.0f;
    }
    if (high > cappedHigh) {
      high = cappedHigh;
    }
    for (int i = 0; i < 14; ++i) {
      float mid = (low + high) * 0.5f;
      float out = net.minecraft.world.damagesource.CombatRules.getDamageAfterAbsorb(mid, (float)armor, toughness);
      if (out < legacyFinal) {
        low = mid;
      } else {
        high = mid;
      }
    }

    event.setAmount(high);
  }

  private static boolean hasChaosArmorEquipped(LivingEntity living) {
    for (EquipmentSlot slot : EquipmentSlot.values()) {
      if (!slot.isArmor()) {
        continue;
      }
      ItemStack stack = living.getItemBySlot(slot);
      if (!stack.isEmpty() && stack.getItem() instanceof ItemChaosArmor) {
        return true;
      }
    }
    return false;
  }

  private ResourceLocation getSpawnerEntityId(SpawnerBlockEntity spawner) {
    if (spawner == null) {
      return null;
    }
    return SpawnerFixHelper.getMobSpawnerEntityId(spawner.getSpawner());
  }

  private void normalizeSpawnerId(SpawnerBlockEntity spawner) {
    if (spawner == null) {
      return;
    }
    try {
      ResourceLocation current = SpawnerFixHelper.getMobSpawnerEntityId(spawner.getSpawner());
      ResourceLocation normalized = SpawnerFixHelper.normalizeSpawnerEntityId(current);
      if (normalized != null && (current == null || !normalized.equals(current))) {
        SpawnerFixHelper.setMobSpawnerEntityId(spawner.getSpawner(), normalized);
        spawner.setChanged();
      }
    } catch (Exception ex) {
      LOGGER.warn(
          "ChaosPersists: failed to normalize spawner at {} (skipping)",
          spawner.getBlockPos(),
          ex);
    }
  }

  private boolean isEntityTypeInBiomeSpawnListsForDebug(Biome biome, EntityType<?> entityType) {
    return this.isEntityTypeInBiomeSpawnListForDebug(
            biome.getMobSettings().getMobs(MobCategory.MONSTER), entityType)
        || this.isEntityTypeInBiomeSpawnListForDebug(
            biome.getMobSettings().getMobs(MobCategory.CREATURE), entityType)
        || this.isEntityTypeInBiomeSpawnListForDebug(
            biome.getMobSettings().getMobs(MobCategory.AMBIENT), entityType)
        || this.isEntityTypeInBiomeSpawnListForDebug(
            biome.getMobSettings().getMobs(MobCategory.WATER_CREATURE), entityType);
  }

  private boolean isEntityTypeInBiomeSpawnListForDebug(
      net.minecraft.util.random.WeightedRandomList<MobSpawnSettings.SpawnerData> entries,
      EntityType<?> entityType) {
    if (entries == null || entries.isEmpty() || entityType == null) {
      return false;
    }
    for (MobSpawnSettings.SpawnerData entry : entries.unwrap()) {
      if (entry != null && entry.type == entityType) {
        return true;
      }
    }
    return false;
  }

  @SubscribeEvent
  public void onSpawnerSpawnPlacementCheck(MobSpawnEvent.SpawnPlacementCheck event) {
    if (event.getSpawnType() != MobSpawnType.SPAWNER) {
      return;
    }
    ResourceLocation mobId = net.minecraft.core.registries.BuiltInRegistries.ENTITY_TYPE.getKey(event.getEntityType());
    if (mobId == null || !"chaospersists".equals(mobId.getNamespace())) {
      return;
    }
    if (SpawnerFixHelper.isNearMatchingSpawnerAt(event.getPos(), event.getLevel(), mobId)) {
      event.setResult(Event.Result.ALLOW);
    }
  }

  @SubscribeEvent
  public void onSpawnerMobPositionCheck(MobSpawnEvent.PositionCheck event) {
    if (event.getSpawnType() != MobSpawnType.SPAWNER) {
      return;
    }
    Mob mob = event.getEntity();
    if (mob == null || !SpawnerFixHelper.isChaosEntityFirstTick(mob)) {
      return;
    }
    if (!SpawnerFixHelper.isNearMatchingSpawnerForMob(mob, event.getLevel())) {
      return;
    }
    // Allow only when the mob still fits the space; bypass legacy display-name spawn rules only.
    if (mob.checkSpawnObstruction(event.getLevel())) {
      event.setResult(Event.Result.ALLOW);
    }
  }

  /** OMG apple trees save with stale skylight; rebuild lighting near the player after relog only. */
  @SubscribeEvent
  public void onPlayerLoggedInRelightNearbyChunks(PlayerEvent.PlayerLoggedInEvent event) {
    if (!(event.getEntity() instanceof ServerPlayer player)) {
      return;
    }
    ServerLevel level = player.serverLevel();
    if (!level.dimensionType().hasSkyLight()) {
      return;
    }
    level.getServer().tell(new TickTask(level.getServer().getTickCount() + 20, () -> {
      if (!player.isAlive()) {
        return;
      }
      scheduleSkylightRelight(level, chunkKeysAround(player.chunkPosition(), 8), player);
    }));
  }

  @SubscribeEvent
  public void onPlayerChangedDimensionBringRoyalPets(PlayerEvent.PlayerChangedDimensionEvent event) {
    if (!(event.getEntity() instanceof ServerPlayer player)) {
      return;
    }
    RoyalPetFollowHelper.bringRoyalPetsToPlayer(player);
  }

  /** Keep loaded ground pets catching up onto solid ground (never midair) when the owner moves far. */
  @SubscribeEvent
  public void onPlayerTickBringGroundPets(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || event.player.level().isClientSide) {
      return;
    }
    if (!(event.player instanceof ServerPlayer player)) {
      return;
    }
    if (player.tickCount % 20 != 0) {
      return;
    }
    RoyalPetFollowHelper.bringGroundFollowPetsToPlayer(player);
  }

  private static List<Long> chunkKeysAround(ChunkPos center, int radius) {
    List<Long> keys = new ArrayList<>((radius * 2 + 1) * (radius * 2 + 1));
    for (int dx = -radius; dx <= radius; dx++) {
      for (int dz = -radius; dz <= radius; dz++) {
        keys.add(ChunkPos.asLong(center.x + dx, center.z + dz));
      }
    }
    return keys;
  }

  /**
   * Deferred skylight rebuild for fast-placed structures. Runs on later ticks so it never blocks
   * item use; optionally pushes corrected light packets to one player.
   */
  public static void scheduleSkylightRelight(
      ServerLevel level, Iterable<Long> chunkKeys, @Nullable ServerPlayer syncTo) {
    if (!level.dimensionType().hasSkyLight()) {
      return;
    }
    List<Long> chunks = new ArrayList<>();
    for (long chunkKey : chunkKeys) {
      chunks.add(chunkKey);
    }
    if (chunks.isEmpty()) {
      return;
    }
    ThreadedLevelLightEngine lightEngine = level.getChunkSource().getLightEngine();
    int baseTick = level.getServer().getTickCount();
    int[] delays = {10, 50, 100};
    for (int pass = 0; pass < delays.length; pass++) {
      boolean finalPass = pass == delays.length - 1;
      int delay = delays[pass];
      level.getServer().tell(new TickTask(baseTick + delay, () -> {
        CompletableFuture<?>[] futures = new CompletableFuture<?>[chunks.size()];
        int index = 0;
        for (long chunkKey : chunks) {
          ChunkPos chunkPos = new ChunkPos(chunkKey);
          ChunkAccess chunk = level.getChunk(chunkPos.x, chunkPos.z);
          chunk.setLightCorrect(false);
          futures[index++] = lightEngine.lightChunk(chunk, false);
        }
        lightEngine.tryScheduleUpdate();
        if (finalPass && syncTo != null) {
          CompletableFuture.allOf(futures).thenRun(() -> level.getServer().execute(() -> {
            if (!syncTo.isAlive()) {
              return;
            }
            for (long chunkKey : chunks) {
              ChunkPos chunkPos = new ChunkPos(chunkKey);
              syncTo.connection.send(
                  new ClientboundLightUpdatePacket(chunkPos, lightEngine, null, null));
              level.getChunk(chunkPos.x, chunkPos.z).setUnsaved(true);
            }
          }));
        }
      }));
    }
  }

  @SubscribeEvent
  public void onChunkLoadNormalizeSpawners(ChunkEvent.Load event) {
    if (event == null || event.getLevel() == null || event.getLevel().isClientSide() || event.getChunk() == null) {
      return;
    }
    if (event.isNewChunk()) {
      return;
    }
    if (!(event.getChunk() instanceof LevelChunk levelChunk)) {
      return;
    }
    for (BlockEntity te : levelChunk.getBlockEntities().values()) {
      if (te instanceof SpawnerBlockEntity spawner) {
        this.normalizeSpawnerId(spawner);
      }
    }
  }

  @SubscribeEvent
  public void onEntityJoinWorld(EntityJoinLevelEvent event) {
    if (event == null || event.getLevel() == null || event.getLevel().isClientSide()) {
      return;
    }
    if (!(event.getEntity() instanceof PitchBlack nightmare)) {
      return;
    }

    BlockPos base = nightmare.blockPosition();

    for (int dx = -8; dx <= 8; ++dx) {
      for (int dy = -4; dy <= 8; ++dy) {
        for (int dz = -8; dz <= 8; ++dz) {
          BlockEntity te = event.getLevel().getBlockEntity(base.offset(dx, dy, dz));
          if (!(te instanceof SpawnerBlockEntity spawner)) {
            continue;
          }

          String path = null;
          ResourceLocation spawnerId = SpawnerFixHelper.getMobSpawnerEntityId(spawner.getSpawner());
          if (spawnerId != null) {
            path = SpawnerFixHelper.normalizeSpawnerEntityId(spawnerId).getPath();
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
  public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
    if (event == null || event.getEntity() == null) {
      return;
    }
    LivingEntity base = event.getEntity();
    if (base.level() == null || base.level().isClientSide()) {
      return;
    }
    if (!(base instanceof Mob mob)) {
      return;
    }
    ResourceLocation key = EntityType.getKey(base.getType());
    if (key != null && MODID.equals(key.getNamespace())) {
      LivingEntity currentTarget = mob.getTarget();
      if (currentTarget != null && !MyUtils.isValidAggroTarget(currentTarget)) {
        mob.setTarget(null);
      }
    }
    boolean chaosHostileMob =
        key != null && MODID.equals(key.getNamespace()) && base instanceof Enemy;
    if (!this.isAlwaysHostileInLegacy(base.getClass()) && !chaosHostileMob) {
      return;
    }
    if (PlayNicely != 0 || base.level().getDifficulty() == Difficulty.PEACEFUL) {
      return;
    }

    if (mob.tickCount % 5 != 0) {
      return;
    }
    LivingEntity current = mob.getTarget();
    if (current != null && current.isAlive()) {
      return;
    }

    Player target = base.level().getNearestPlayer(base.getX(), base.getY(), base.getZ(), 24.0, false);
    if (target == null || target.isCreative() || target.isSpectator()) {
      return;
    }

    mob.setTarget(target);
    mob.setLastHurtByMob(target);
    if (mob instanceof Bee bee) {
      bee.forceAttackTarget(target);
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
    CageEmpty = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageempty"));
    CagedSpider = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagespider"));
    CagedBat = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagebat"));
    CagedCow = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecow"));
    CagedPig = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagepig"));
    CagedSquid = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagesquid"));
    CagedChicken = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagechicken"));
    CagedCreeper = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecreeper"));
    CagedSkeleton = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageskeleton"));
    CagedZombie = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagezombie"));
    CagedSlime = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageslime"));
    CagedGhast = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageghast"));
    CagedZombiePigman = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagezombiepigman"));
    CagedEnderman = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageenderman"));
    CagedCaveSpider = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecavespider"));
    CagedSilverfish = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagesilverfish"));
    CagedMagmaCube = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagemagmacube"));
    CagedWitch = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagewitch"));
    CagedSheep = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagesheep"));
    CagedWolf = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagewolf"));
    CagedMooshroom = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagemooshroom"));
    CagedOcelot = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageocelot"));
    CagedBlaze = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageblaze"));
    CagedGirlfriend = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagegirlfriend"));
    CagedBoyfriend = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageboyfriend"));
    CagedWitherSkeleton = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagewitherskeleton"));
    CagedEnderDragon = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageenderdragon"));
    CagedSnowGolem = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagesnowgolem"));
    CagedIronGolem = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageirongolem"));
    CagedWitherBoss = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagewitherboss"));
    CagedRedCow = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageredcow"));
    CagedGoldCow = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagegoldcow"));
    CagedEnchantedCow = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageenchantedcow"));
    CagedMOTHRA = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagemothra"));
    CagedAlo = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagealosaurus"));
    CagedCryo = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecryolophosaurus"));
    CagedCama = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecamarasaurus"));
    CagedVelo = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagevelocityraptor"));
    CagedHydro = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagehydrolisc"));
    CagedBasil = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagebasilisc"));
    CagedDragonfly = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagedragonfly"));
    CagedEmperorScorpion = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageemperorscorpion"));
    CagedScorpion = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagescorpion"));
    CagedCaveFisher = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecavefisher"));
    CagedSpyro = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagespyro"));
    CagedBaryonyx = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagebaryonyx"));
    CagedGammaMetroid = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagegammametroid"));
    CagedCockateil = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecockateil"));
    CagedKyuubi = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagekyuubi"));
    CagedAlien = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagealien"));
    CagedAttackSquid = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageattacksquid"));
    CagedWaterDragon = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagewaterdragon"));
    CagedCephadrome = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecephadrome"));
    CagedKraken = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagekraken"));
    CagedLizard = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagelizard"));
    CagedDragon = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagedragon"));
    CagedBee = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagebee"));
    CagedHorse = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagehorse"));
    CagedFirefly = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagefirefly"));
    CagedChipmunk = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagechipmunk"));
    CagedGazelle = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagegazelle"));
    CagedOstrich = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageostrich"));
    CagedTrooper = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagetrooper"));
    CagedSpit = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagespit"));
    CagedStink = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagestink"));
    CagedCreepingHorror = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecreepinghorror"));
    CagedTerribleTerror = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageterribleterror"));
    CagedCliffRacer = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecliffracer"));
    CagedTriffid = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagetriffid"));
    CagedPitchBlack = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagenightmare"));
    CagedLurkingTerror = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagelurkingterror"));
    CagedSmallWorm = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagesmallworm"));
    CagedMediumWorm = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagemediumworm"));
    CagedLargeWorm = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagelargeworm"));
    CagedCassowary = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecassowary"));
    CagedCloudShark = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecloudshark"));
    CagedGoldFish = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagegoldfish"));
    CagedLeafMonster = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageleafmonster"));
    CagedEnderKnight = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageenderknight"));
    CagedEnderReaper = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageenderreaper"));
    CagedBeaver = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagebeaver"));
    CagedUrchin = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageurchin"));
    CagedFlounder = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageflounder"));
    CagedSkate = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageskate"));
    CagedRotator = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagerotator"));
    CagedPeacock = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagepeacock"));
    CagedFairy = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagefairy"));
    CagedDungeonBeast = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagedungeonbeast"));
    CagedVortex = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagevortex"));
    CagedRat = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagerat"));
    CagedWhale = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagewhale"));
    CagedIrukandji = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageirukandji"));
    CagedTRex = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagetrex"));
    CagedHercules = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagehercules"));
    CagedMantis = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagemantis"));
    CagedStinky = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagestinky"));
    CagedEasterBunny = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageeasterbunny"));
    CagedCaterKiller = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecaterkiller"));
    CagedMolenoid = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagemolenoid"));
    CagedSeaMonster = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageseamonster"));
    CagedSeaViper = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageseaviper"));
    CagedLeon = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cageleon"));
    CagedHammerhead = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagehammerhead"));
    CagedRubberDucky = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagerubberducky"));
    CagedCrystalCow = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecrystalcow"));
    CagedVillager = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagevillager"));
    CagedCriminal = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecriminal"));
    CagedBrutalfly = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagebrutalfly"));
    CagedNastysaurus = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagenastysaurus"));
    CagedPointysaurus = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagepointysaurus"));
    CagedCricket = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecricket"));
    CagedFrog = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagefrog"));
    CagedSpiderDriver = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagespiderdriver"));
    CagedCrab = (CritterCage) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "cagecrab"));

    EnderDragonEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggenderdragon"));
    WitherBossEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggwitherboss"));
    GirlfriendEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egggirlfriend"));
    RedCowEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggredcow"));
    CrystalCowEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcrystalcow"));
    GoldCowEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egggoldcow"));
    EnchantedCowEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggenchantedcow"));
    MOTHRAEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggmothra"));
    AloEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggalosaurus"));
    CryoEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcryolophosaurus"));
    CamaEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcamarasaurus"));
    VeloEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggvelocityraptor"));
    HydroEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egghydrolisc"));
    BasilEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbasilisc"));
    DragonflyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggdragonfly"));
    EmperorScorpionEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggemperorscorpion"));
    ScorpionEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggscorpion"));
    CaveFisherEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcavefisher"));
    SpyroEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggspyro"));
    BaryonyxEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbaryonyx"));
    GammaMetroidEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egggammametroid"));
    CockateilEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcockateil"));
    KyuubiEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggkyuubi"));
    AlienEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggalien"));
    AttackSquidEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggattacksquid"));
    WaterDragonEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggwaterdragon"));
    CephadromeEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcephadrome"));
    KrakenEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggkraken"));
    LizardEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egglizard"));
    DragonEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggdragon"));
    BeeEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbee"));
    TrooperBugEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtrooper"));
    SpitBugEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggspit"));
    StinkBugEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggstink"));
    OstrichEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggostrich"));
    GazelleEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egggazelle"));
    ChipmunkEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggchipmunk"));
    CreepingHorrorEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcreepinghorror"));
    TerribleTerrorEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggterribleterror"));
    CliffRacerEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcliffracer"));
    TriffidEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtriffid"));
    PitchBlackEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggnightmare"));
    LurkingTerrorEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egglurkingterror"));
    GodzillaEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egggodzilla"));
    SmallWormEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggsmallworm"));
    MediumWormEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggmediumworm"));
    LargeWormEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egglargeworm"));
    CassowaryEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcassowary"));
    CloudSharkEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcloudshark"));
    GoldFishEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egggoldfish"));
    LeafMonsterEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggleafmonster"));
    TshirtEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtshirt"));
    EnderKnightEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggenderknight"));
    EnderReaperEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggenderreaper"));
    BeaverEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbeaver"));
    RotatorEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrotator"));
    VortexEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggvortex"));
    PeacockEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggpeacock"));
    FairyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggfairy"));
    DungeonBeastEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggdungeonbeast"));
    RatEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrat"));
    FlounderEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggflounder"));
    WhaleEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggwhale"));
    IrukandjiEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggirukandji"));
    SkateEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggskate"));
    UrchinEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggurchin"));
    Robot1Egg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrobot1"));
    Robot2Egg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrobot2"));
    Robot3Egg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrobot3"));
    Robot4Egg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrobot4"));
    GhostEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggghost"));
    GhostSkellyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggghostskelly"));
    BrownAntEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbrownant"));
    RedAntEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggredant"));
    RainbowAntEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrainbowant"));
    UnstableAntEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggunstableant"));
    TermiteEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtermite"));
    ButterflyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbutterfly"));
    MothEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggmoth"));
    MosquitoEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggmosquito"));
    FireflyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggfirefly"));
    TRexEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtrex"));
    HerculesEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egghercules"));
    MantisEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggmantis"));
    StinkyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggstinky"));
    Robot5Egg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrobot5"));
    CoinEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcoin"));
    BoyfriendEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggboyfriend"));
    TheKingEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtheking"));
    TheQueenEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggthequeen"));
    ThePrinceEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtheprince"));
    EasterBunnyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggeasterbunny"));
    MolenoidEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggmolenoid"));
    SeaMonsterEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggseamonster"));
    SeaViperEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggseaviper"));
    CaterKillerEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcaterkiller"));
    RubberDuckyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrubberducky"));
    HammerheadEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "egghammerhead"));
    LeonEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggleon"));
    CriminalEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcriminal"));
    BrutalflyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggbrutalfly"));
    NastysaurusEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggnastysaurus"));
    PointysaurusEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggpointysaurus"));
    CricketEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcricket"));
    ThePrincessEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggtheprincess"));
    FrogEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggfrog"));
    JefferyEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrobot6"));
    AntRobotEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggantrobot"));
    SpiderRobotEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggspiderrobot"));
    SpiderDriverEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggspiderdriver"));
    CrabEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggcrab"));
    RockEgg = (ItemSpawnEgg) BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(MODID, "eggrock"));
  }

  private void DoDispenserRegistrations()
  {
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(LizardEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(EnderDragonEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WitherBossEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GirlfriendEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BoyfriendEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TheKingEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TheQueenEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ThePrinceEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RedCowEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CrystalCowEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GoldCowEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(EnchantedCowEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MOTHRAEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(AloEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CryoEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CamaEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(VeloEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(HydroEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BasilEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(DragonflyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(EmperorScorpionEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ScorpionEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CaveFisherEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SpyroEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BaryonyxEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GammaMetroidEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CockateilEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(KyuubiEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(AlienEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(AttackSquidEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WaterDragonEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CephadromeEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(DragonEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(KrakenEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(LizardEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BeeEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TrooperBugEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SpitBugEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(StinkBugEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(OstrichEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GazelleEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ChipmunkEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CreepingHorrorEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TerribleTerrorEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CliffRacerEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TriffidEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(PitchBlackEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(LurkingTerrorEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GodzillaEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SmallWormEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MediumWormEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(LargeWormEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CassowaryEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CloudSharkEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GoldFishEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(LeafMonsterEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TshirtEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(EnderKnightEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(EnderReaperEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BeaverEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RotatorEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(VortexEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(PeacockEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(FairyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(DungeonBeastEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RatEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(FlounderEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(WhaleEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(IrukandjiEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SkateEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(UrchinEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Robot1Egg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Robot2Egg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Robot3Egg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Robot4Egg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GhostEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(GhostSkellyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BrownAntEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RedAntEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RainbowAntEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(UnstableAntEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TermiteEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ButterflyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MothEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MosquitoEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(FireflyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(TRexEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(HerculesEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MantisEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(StinkyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Robot5Egg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CoinEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(EasterBunnyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MolenoidEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SeaMonsterEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SeaViperEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CaterKillerEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(LeonEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(HammerheadEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RubberDuckyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CriminalEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(BrutalflyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(NastysaurusEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(PointysaurusEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CricketEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(ThePrincessEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(FrogEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(JefferyEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(AntRobotEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SpiderRobotEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(SpiderDriverEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(CrabEgg, new DispenserBehaviorChaosEgg());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RockEgg, new DispenserBehaviorChaosEgg());

    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyIrukandjiArrow, new MyDispenserBehaviorArrow());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyWaterBall, new MyDispenserBehaviorWDCharge());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MySunspotUrchin, new MyDispenserBehaviorSunspotUrchin());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyAcid, new MyDispenserBehaviorAcid());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyIceBall, new MyDispenserBehaviorIceball());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyIrukandji, new MyDispenserBehaviorDeadIrukandji());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyLaserBall, new MyDispenserBehaviorLaserball());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MySmallRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyRedRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyCrystalRedRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyCrystalGreenRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyCrystalBlueRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyCrystalTNTRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyBlueRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyGreenRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyPurpleRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MySpikeyRock, new MyDispenserBehaviorRock());
    BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(MyTNTRock, new MyDispenserBehaviorRock());
  }

  public void load(FMLInitializationEvent event)
  {
    proxy.registerBlockColors();
    proxy.registerLeafColors();
    proxy.registerItemColors();
  }

  // ===== Creative Tabs Remap =====
  public static CreativeModeTab tabChaosItems;
  public static CreativeModeTab tabChaosBlocks;
  public static CreativeModeTab tabChaosFoods;
  public static CreativeModeTab tabChaosTools;
  public static CreativeModeTab tabChaosWeapons;
  public static CreativeModeTab tabChaosMobs;
  public static CreativeModeTab tabChaosArmor;

  private static void ensureChaosCreativeTabs()
  {
    if (tabChaosItems != null) {
      return;
    }

    tabChaosItems = TAB_CHAOS_ITEMS.get();
    tabChaosBlocks = TAB_CHAOS_BLOCKS.get();
    tabChaosFoods = TAB_CHAOS_FOODS.get();
    tabChaosTools = TAB_CHAOS_TOOLS.get();
    tabChaosWeapons = TAB_CHAOS_WEAPONS.get();
    tabChaosMobs = TAB_CHAOS_MOBS.get();
    tabChaosArmor = TAB_CHAOS_ARMOR.get();
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
        || pathLower.startsWith("quinoa_")
        || "dungeonspawner".equals(pathLower);
  }

  /**
   * 1.12 {@code GameRegistry} registration order for combat-tab weapons. Creative tabs showed items in
   * registry order; alphabetical sorting broke sword/bow/ranged grouping.
   */
  private static final List<String> CHAOS_WEAPON_TAB_ORDER =
      List.of(
          "ultimatesword",
          "nightmaresword",
          "berthasmall",
          "hammysmall",
          "slicesmall",
          "royalsmall",
          "battleaxesmall",
          "queenbattleaxesmall",
          "chainsawsmall",
          "emeraldsword",
          "rosesword",
          "experiencesword",
          "poisonsword",
          "ratsword",
          "fairysword",
          "mantisclaw",
          "bighammer",
          "crystalwoodsword",
          "crystalpinksword",
          "tigerseye_sword",
          "crystalstonesword",
          "rubysword",
          "amethystsword",
          "ultimatebow",
          "skatebow",
          "sunspoturchin",
          "waterball",
          "laserball",
          "iceball",
          "rocksmall",
          "rock",
          "rockred",
          "rockcrystalred",
          "rockcrystalgreen",
          "rockcrystalblue",
          "rockcrystaltnt",
          "rockgreen",
          "rockblue",
          "rockpurple",
          "rockspikey",
          "rocktnt",
          "acid",
          "deadirukandji",
          "irukandjiarrow",
          "raygun",
          "squidzookasmall",
          "thunderstaff");

  private static final Map<String, Integer> CHAOS_WEAPON_TAB_ORDER_INDEX = new HashMap<>();

  static {
    for (int i = 0; i < CHAOS_WEAPON_TAB_ORDER.size(); i++) {
      CHAOS_WEAPON_TAB_ORDER_INDEX.put(CHAOS_WEAPON_TAB_ORDER.get(i), i);
    }
  }

  private static Comparator<Item> chaosWeaponTabComparator() {
    return Comparator.comparingInt(ChaosPersists::getChaosWeaponTabSortKey)
        .thenComparingInt(item -> BuiltInRegistries.ITEM.getId(item));
  }

  /**
   * 1.12 {@code GameRegistry} armor order: each set helmet, chestplate, leggings, boots.
   */
  private static final List<String> CHAOS_ARMOR_TAB_ORDER =
      List.of(
          "ultimate_helmet",
          "ultimate_chest",
          "ultimate_leggings",
          "ultimate_boots",
          "lavaeel_helmet",
          "lavaeel_chest",
          "lavaeel_leggings",
          "lavaeel_boots",
          "mothscale_helmet",
          "mothscale_chest",
          "mothscale_leggings",
          "mothscale_boots",
          "emerald_helmet",
          "emerald_chest",
          "emerald_leggings",
          "emerald_boots",
          "experience_helmet",
          "experience_chest",
          "experience_leggings",
          "experience_boots",
          "ruby_helmet",
          "ruby_chest",
          "ruby_leggings",
          "ruby_boots",
          "amethyst_helmet",
          "amethyst_chest",
          "amethyst_leggings",
          "amethyst_boots",
          "pink_helmet",
          "pink_chest",
          "pink_leggings",
          "pink_boots",
          "tigerseye_helmet",
          "tigerseye_chest",
          "tigerseye_leggings",
          "tigerseye_boots",
          "peacock_helmet",
          "peacock_chest",
          "peacock_leggings",
          "peacock_boots",
          "mobzilla_helmet",
          "mobzilla_chest",
          "mobzilla_leggings",
          "mobzilla_boots",
          "royal_helmet",
          "royal_chest",
          "royal_leggings",
          "royal_boots",
          "lapis_helmet",
          "lapis_chest",
          "lapis_leggings",
          "lapis_boots",
          "queen_helmet",
          "queen_chest",
          "queen_leggings",
          "queen_boots");

  private static final Map<String, Integer> CHAOS_ARMOR_TAB_ORDER_INDEX = new HashMap<>();

  static {
    for (int i = 0; i < CHAOS_ARMOR_TAB_ORDER.size(); i++) {
      CHAOS_ARMOR_TAB_ORDER_INDEX.put(CHAOS_ARMOR_TAB_ORDER.get(i), i);
    }
  }

  private static Comparator<Item> chaosArmorTabComparator() {
    return Comparator.comparingInt(ChaosPersists::getChaosArmorTabSortKey)
        .thenComparingInt(item -> BuiltInRegistries.ITEM.getId(item));
  }

  private static int getChaosArmorTabSortKey(Item item) {
    ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
    if (rl != null) {
      Integer index = CHAOS_ARMOR_TAB_ORDER_INDEX.get(rl.getPath());
      if (index != null) {
        return index;
      }
    }
    return CHAOS_ARMOR_TAB_ORDER.size() + getChaosArmorFallbackSlot(item);
  }

  private static int getChaosArmorFallbackSlot(Item item) {
    if (!(item instanceof ArmorItem armorItem)) {
      return 99;
    }
    return switch (armorItem.getType()) {
      case HELMET -> 0;
      case CHESTPLATE -> 1;
      case LEGGINGS -> 2;
      case BOOTS -> 3;
    };
  }

  private static int getChaosWeaponTabSortKey(Item item) {
    ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
    if (rl != null) {
      Integer index = CHAOS_WEAPON_TAB_ORDER_INDEX.get(rl.getPath());
      if (index != null) {
        return index;
      }
    }
    return CHAOS_WEAPON_TAB_ORDER.size() + getChaosWeaponFallbackCategory(item) * 100;
  }

  /** Groups any future combat items after the legacy list, by weapon type. */
  private static int getChaosWeaponFallbackCategory(Item item) {
    if (item instanceof Bertha) {
      return 0;
    }
    if (item instanceof UltimateSword) {
      return 1;
    }
    if (item instanceof BigHammer) {
      return 2;
    }
    if (item instanceof SwordItem) {
      return 3;
    }
    if (item instanceof BowItem || item instanceof UltimateBow || item instanceof SkateBow) {
      return 4;
    }
    return 5;
  }

  /** 1.12 items that used {@code CreativeTabs.COMBAT} but are plain {@link Item}, not {@link SwordItem}. */
  private static boolean isLegacyChaosWeaponItem(Item item) {
    return item instanceof SwordItem
        || item instanceof BowItem
        || item instanceof ItemAcid
        || item instanceof ItemLaserBall
        || item instanceof ItemIceBall
        || item instanceof ItemWaterBall
        || item instanceof ItemThunderStaff
        || item instanceof ItemSunspotUrchin
        || item instanceof ItemSquidZooka
        || item instanceof ItemRock
        || item instanceof ItemRayGun
        || item instanceof ItemIrukandji
        || item instanceof ItemIrukandjiArrow
        || item instanceof UltimateBow
        || item instanceof SkateBow;
  }

  /** 1.12 items that used {@code CreativeTabs.TOOLS} but are not pickaxes/axes/shovels/hoes. */
  private static boolean isLegacyChaosToolItem(Item item) {
    return item instanceof DiggerItem
        || item instanceof HoeItem
        || item instanceof FishingRodItem
        || item instanceof ItemWrench
        || item instanceof ExperienceCatcher
        || item instanceof ItemSpiderRobotKit;
  }

  /**
   * Remaps all mod items/blocks onto Chaos creative tabs. Safe to call more than once (idempotent).
   */
  public static void applyChaosCreativeTabs()
  {
    ensureChaosCreativeTabs();

    // Put all chaospersists blocks into Chaos Blocks.
    for (Block block : BuiltInRegistries.BLOCK) {
      if (block == null) {
        continue;
      }
      ResourceLocation rl = BuiltInRegistries.BLOCK.getKey(block);
      if (rl != null && MODID.equals(rl.getNamespace())) {
        String path = rl.getPath();
        String lower = path == null ? "" : path.toLowerCase();
        // Don't show crop/plant/sapling blocks in Chaos Blocks; keep only their seed items.
        if (isHiddenCropGrowthChaosBlockPath(lower)) {
          continue;
        }
        Item blockItem = block.asItem();
        if (blockItem instanceof BlockItem bi
            && (bi.getBlock() == null || bi.getBlock() == Blocks.AIR)) {
          continue;
        }
        CreativeTabCompat.setCreativeTab(block, tabChaosBlocks);
      }
    }

    // Put all chaospersists items into the requested tabs.
    for (Item item : BuiltInRegistries.ITEM) {
      if (item == null) {
        continue;
      }
      ResourceLocation rl = BuiltInRegistries.ITEM.getKey(item);
      if (rl == null || !MODID.equals(rl.getNamespace())) {
        continue;
      }
      if (item instanceof BlockItem blockItem
          && (blockItem.getBlock() == null || blockItem.getBlock() == Blocks.AIR)) {
        CreativeTabCompat.setCreativeTab(item, null);
        continue;
      }

      String path = rl.getPath();
      String lower = path == null ? "" : path.toLowerCase();

      if (item instanceof BlockItem) {
        // Hide plant/crop/sapling blocks from Chaos creative tabs.
        // Seeds remain because they are items, not block item forms.
        if (isHiddenCropGrowthChaosBlockPath(lower)) {
          CreativeTabCompat.setCreativeTab(item, null);
          continue;
        }

        CreativeTabCompat.setCreativeTab(item, tabChaosBlocks);
      } else if ("pizza".equals(lower)) {
        CreativeTabCompat.setCreativeTab(item, tabChaosFoods);
      } else if ("ducttape".equals(lower)) {
        CreativeTabCompat.setCreativeTab(item, tabChaosTools);
      } else if ("step_up".equals(lower) || "step_down".equals(lower) || "step_accross".equals(lower)) {
        CreativeTabCompat.setCreativeTab(item, tabChaosTools);
      } else if ("spiderrobotkit".equals(lower) || "antrobotkit".equals(lower)) {
        CreativeTabCompat.setCreativeTab(item, tabChaosTools);
      } else if (item instanceof ItemSpawnEgg) {
        CreativeTabCompat.setCreativeTab(item, tabChaosMobs);
      } else if (item.isEdible()) {
        CreativeTabCompat.setCreativeTab(item, tabChaosFoods);
      } else if (item instanceof ArmorItem) {
        CreativeTabCompat.setCreativeTab(item, tabChaosArmor);
      } else {
        // Map vanilla tabs and item classes to Chaos Tools / Weapons. Must be idempotent: JEI (and any
        // second caller) re-runs this after tabs are already tabChaosTools/tabChaosWeapons — comparing
        // only to CreativeModeTabs.TOOLS/COMBAT would wrongly send everything to Chaos Items.
        CreativeModeTab oldTab = CreativeTabCompat.getCreativeTab(item);
        ItemStack probe = new ItemStack(item);
        if (oldTab == tabChaosTools) {
          CreativeTabCompat.setCreativeTab(item, tabChaosTools);
        } else if (oldTab == tabChaosWeapons) {
          CreativeTabCompat.setCreativeTab(item, tabChaosWeapons);
        } else if (BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(oldTab).orElse(null)
            == CreativeModeTabs.TOOLS_AND_UTILITIES) {
          CreativeTabCompat.setCreativeTab(item, tabChaosTools);
        } else if (BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(oldTab).orElse(null)
            == CreativeModeTabs.COMBAT) {
          CreativeTabCompat.setCreativeTab(item, tabChaosWeapons);
        } else if (isLegacyChaosWeaponItem(item) || item.getUseAnimation(probe) == UseAnim.BOW) {
          CreativeTabCompat.setCreativeTab(item, tabChaosWeapons);
        } else if (isLegacyChaosToolItem(item)) {
          CreativeTabCompat.setCreativeTab(item, tabChaosTools);
        } else {
          CreativeTabCompat.setCreativeTab(item, tabChaosItems);
        }
      }
    }
  }

  /** Chaos Items tab (for JEI sub-item enumeration when an item has {@code creativeTab == null}). */
  public static CreativeModeTab getChaosItemsCreativeTab()
  {
    ensureChaosCreativeTabs();
    return tabChaosItems;
  }

  public void serverStarting(FMLServerStartingEvent event) {}

  public static void postInit(FMLPostInitializationEvent event)
  {
    BMaze = new BasiliskMaze();
    RubyDungeon = new RubyBirdDungeon();
    MyDungeon = new GenericDungeon();
    chaospersistsTrees = new Trees();
    chaospersistsUtils = new MyUtils();
    Chunker = new ChunkOreGenerator();
  }

  @OnlyIn(Dist.CLIENT)
  public Entity spawnEntity(int entityId, Level world, double scaledX, double scaledY, double scaledZ)
  {
    return null;
  }

  public static Entity getPointedAtEntity(Level world, Player player, double dist) {
    Entity pointedAt = null;
    if (player != null && world != null) {
      double d0 = dist;
      double d1 = dist;
      Vec3 vec3 = player.getEyePosition(1.0F);
      Vec3 vec31 = player.getViewVector(1.0F);
      Vec3 vec32 = vec3.add(vec31.x * d0, vec31.y * d0, vec31.z * d0);
      double f1 = 1.0D;
      AABB searchBox =
          player.getBoundingBox()
              .inflate(vec31.x * d0, vec31.y * d0, vec31.z * d0)
              .inflate(f1, f1, f1);
      List<Entity> list =
          world.getEntities(
              player,
              searchBox,
              entity -> !entity.isSpectator() && entity.isPickable() && entity != player);
      double d2 = d1;

      for (Entity entity : list) {
        if (!entity.isPickable()) {
          continue;
        }
        double f2 = entity.getPickRadius();
        AABB axisalignedbb = entity.getBoundingBox().inflate(f2, f2, f2);
        java.util.Optional<Vec3> intercept = axisalignedbb.clip(vec3, vec32);

        if (axisalignedbb.contains(vec3)) {
          if ((0.0D >= d2) && (d2 != 0.0D)) {
            continue;
          }
          pointedAt = entity;
          d2 = 0.0D;
        } else if (intercept.isPresent()) {
          double d3 = vec3.distanceTo(intercept.get());

          if ((d3 >= d2) && (d2 != 0.0D)) {
            continue;
          }
          if ((entity == player.getVehicle()) && (!entity.canRiderInteract())) {
            if (d2 != 0.0D) {
              continue;
            }
            pointedAt = entity;
          } else {
            pointedAt = entity;
            d2 = d3;
          }
        }
      }
    }

    return pointedAt;
  }

  /**
   * Fast worldgen/structure write via direct chunk setBlockState. Skips full {@link Level#setBlock}
   * (lighting + neighbor storms) during populate — that path melted TPS when used for every tree/ore.
   * Client sync uses {@link Level#sendBlockUpdated} only when not mid-populate feature.
   */
  public static boolean setBlockFast(Level world, int par1, int par2, int par3, Block par4, int par5, int par6)
  {
    if ((par1 >= -30000000) && (par3 >= -30000000) && (par1 < 30000000) && (par3 < 30000000))
    {
      if (par2 < world.getMinBuildHeight() || par2 >= world.getMaxBuildHeight())
      {
        return false;
      }

      LevelChunk chunk = world.getChunk(par1 >> 4, par3 >> 4);
      BlockPos pos = new BlockPos(par1, par2, par3);

      BlockState oldState = Blocks.AIR.defaultBlockState();
      if ((par6 & 0x1) != 0)
      {
        oldState = chunk.getBlockState(new BlockPos(par1 & 0xF, par2, par3 & 0xF));
      }

      boolean flag = setBlockIDWithMetadataFast(chunk, par1 & 0xF, par2, par3 & 0xF, par4, par5);

      if (flag)
      {
        if (!ChaosWorld.isDuringPopulateFeature())
        {
          if (((par6 & 0x2) != 0) && ((!world.isClientSide()) || ((par6 & 0x4) == 0)))
          {
            BlockState newState = prepareBlockStateForWorldGen(RegistryCompat.getStateFromMeta(par4, par5));
            world.sendBlockUpdated(pos, oldState, newState, 3);
          }

          if (!world.isClientSide())
          {
            notifyFastBlockPlacement(world, chunk, pos, par4, par6);
          }

          // Direct chunk writes skip vanilla lighting; without this, tall structures often render half-black (stale sky/block light).
          if (!world.isClientSide())
          {
            if (world.dimensionType().hasSkyLight())
            {
              world.getLightEngine().checkBlock(pos);
            }
            world.getLightEngine().checkBlock(pos);
          }
        }

      }

      return flag;
    }

    return false;
  }

  public static boolean setBlockSuperFast(Level world, int par1, int par2, int par3, Block par4, int par5, int par6, LevelChunk refChunk)
  {
    if ((par1 >= -30000000) && (par3 >= -30000000) && (par1 < 30000000) && (par3 < 30000000))
    {
      if (par2 < world.getMinBuildHeight() || par2 >= world.getMaxBuildHeight())
      {
        return false;
      }

      LevelChunk chunk = world.getChunk(par1 >> 4, par3 >> 4);
      BlockPos pos = new BlockPos(par1, par2, par3);
      int localX = par1 & 15;
      int localZ = par3 & 15;

      BlockState oldState = Blocks.AIR.defaultBlockState();
      if ((par6 & 0x1) != 0)
      {
        oldState = chunk.getBlockState(new BlockPos(localX, par2, localZ));
      }

      boolean flag = setBlockIDWithMetadataFast(chunk, localX, par2, localZ, par4, par5);

      if (flag && !ChaosWorld.isDuringPopulateFeature())
      {
        if (((par6 & 0x2) != 0) && ((!world.isClientSide()) || ((par6 & 0x4) == 0)))
        {
          BlockState newState = prepareBlockStateForWorldGen(RegistryCompat.getStateFromMeta(par4, par5));
          world.sendBlockUpdated(pos, oldState, newState, 3);
        }

        if (!world.isClientSide())
        {
          notifyFastBlockPlacement(world, chunk, pos, par4, par6);
        }

        if (!world.isClientSide())
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

  private static boolean isCrossConnectBlock(Block block) {
    return block instanceof net.minecraft.world.level.block.IronBarsBlock
        || block instanceof net.minecraft.world.level.block.FenceBlock
        || block instanceof net.minecraft.world.level.block.WallBlock
        || block instanceof net.minecraft.world.level.block.ChainBlock;
  }

  /**
   * Direct chunk writes skip vanilla neighbor/shape updates; panes, bars, fences, and walls stay
   * disconnected unless shapes are refreshed after each placement (1.12 {@code setBlock} flag 3).
   */
  private static void notifyFastBlockPlacement(
      Level world, LevelChunk chunk, BlockPos pos, Block placedBlock, int par6) {
    if ((par6 & 0x2) != 0 || isCrossConnectBlock(placedBlock)) {
      world.updateNeighborsAt(pos, placedBlock);
    }
    if (isCrossConnectBlock(placedBlock)) {
      refreshCrossConnectBlockState(world, pos);
      // Earlier fast-placed bars miss deferred neighbor updates; refresh adjacent panes/fences now.
      for (net.minecraft.core.Direction direction : net.minecraft.core.Direction.values()) {
        BlockPos neighborPos = pos.relative(direction);
        if (isCrossConnectBlock(world.getBlockState(neighborPos).getBlock())) {
          refreshCrossConnectBlockState(world, neighborPos);
        }
      }
    }
  }

  private static void refreshCrossConnectBlockState(Level world, BlockPos pos) {
    BlockState state = world.getBlockState(pos);
    BlockState newState = Block.updateFromNeighbourShapes(state, world, pos);
    if (newState != state) {
      LevelChunk chunk = world.getChunk(pos.getX() >> 4, pos.getZ() >> 4);
      chunk.setBlockState(
          new BlockPos(pos.getX() & 15, pos.getY(), pos.getZ() & 15),
          prepareBlockStateForWorldGen(newState),
          false);
      world.sendBlockUpdated(pos, state, newState, 3);
    }
  }

  /**
   * 1.12 placed blocks in air/water without fluid properties; 1.20 leaves/fences/etc. default to
   * waterlogged when set inside water during structure worldgen.
   */
  public static BlockState prepareBlockStateForWorldGen(BlockState state) {
    if (state.hasProperty(BlockStateProperties.WATERLOGGED)) {
      state = state.setValue(BlockStateProperties.WATERLOGGED, false);
    }
    if (state.hasProperty(BlockStateProperties.SNOWY)) {
      state = state.setValue(BlockStateProperties.SNOWY, false);
    }
    if (state.getBlock() instanceof com.astryxion.chaospersists.block.ChaosDirectionalTorchBlock) {
      net.minecraft.core.Direction facing =
          state.getValue(com.astryxion.chaospersists.block.ChaosDirectionalTorchBlock.FACING);
      if (facing == net.minecraft.core.Direction.DOWN) {
        state =
            state.setValue(
                com.astryxion.chaospersists.block.ChaosDirectionalTorchBlock.FACING,
                net.minecraft.core.Direction.UP);
      }
    }
    if (state.getBlock() instanceof net.minecraft.world.level.block.LeavesBlock leaves) {
      state = state.setValue(leaves.PERSISTENT, true);
    }
    return state;
  }

  /** Clears terrain so structure worldgen is not clipped by trees/terrain (1.12 pre-clear behavior). */
  public static void clearStructureVolume(
      Level world, int x1, int y1, int z1, int x2, int y2, int z2) {
    if (world == null || world.isClientSide()) {
      return;
    }
    int minX = Math.min(x1, x2);
    int maxX = Math.max(x1, x2);
    int minY = Math.max(world.getMinBuildHeight(), Math.min(y1, y2));
    int maxY = Math.min(world.getMaxBuildHeight() - 1, Math.max(y1, y2));
    int minZ = Math.min(z1, z2);
    int maxZ = Math.max(z1, z2);
    BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
    for (int x = minX; x <= maxX; ++x) {
      for (int y = minY; y <= maxY; ++y) {
        for (int z = minZ; z <= maxZ; ++z) {
          pos.set(x, y, z);
          world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        }
      }
    }
  }

  public static boolean setBlockIDWithMetadataFast(
      LevelChunk chunk, int par1, int par2, int par3, Block par4, int par5) {
    if (par1 < 0 || par1 > 15 || par3 < 0 || par3 > 15) {
      return false;
    }
    if (par2 < chunk.getMinBuildHeight() || par2 >= chunk.getMaxBuildHeight()) {
      return false;
    }
    if (par4 == null) {
      return false;
    }
    BlockPos pos = new BlockPos(par1, par2, par3);
    if (par4 == Blocks.AIR) {
      chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
      return true;
    }
    chunk.setBlockState(pos, prepareBlockStateForWorldGen(RegistryCompat.getStateFromMeta(par4, par5)), false);
    return true;
  }

  public static Block getBlockIDInChunk(LevelChunk chunk, int par1, int par2, int par3)
  {
    if ((par1 >= -30000000) && (par3 >= -30000000) && (par1 < 30000000) && (par3 < 30000000)) {
      if (par1 >> 4 != chunk.getPos().x) return Blocks.AIR;
      if (par3 >> 4 != chunk.getPos().z) return Blocks.AIR;
      if ((par2 < chunk.getMinBuildHeight()) || (par2 >= chunk.getMaxBuildHeight())) return Blocks.AIR;
      return chunk.getBlockState(new BlockPos(par1 & 0xF, par2, par3 & 0xF)).getBlock();
    }
    return Blocks.AIR;
  }

  /**
   * In-chunk worldgen write used by {@link com.astryxion.chaospersists.block.CrystalMaze} and ores.
   * Direct {@link LevelChunk#setBlockState} — do not route through {@link Level#setBlock} (TPS killer).
   */
  public static boolean setBlockIDWithMetadataInChunk(
      LevelChunk chunk, int par1, int par2, int par3, Block par4, int par5) {
    if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
      if (par1 >> 4 != chunk.getPos().x) {
        return false;
      }
      if (par3 >> 4 != chunk.getPos().z) {
        return false;
      }
      if (par2 < chunk.getMinBuildHeight() || par2 >= chunk.getMaxBuildHeight()) {
        return false;
      }
      par1 &= 15;
      par3 &= 15;
      if (par4 == null) {
        return false;
      }
      BlockPos pos = new BlockPos(par1, par2, par3);
      if (par4 == Blocks.AIR) {
        chunk.setBlockState(pos, Blocks.AIR.defaultBlockState(), false);
        return true;
      }
      chunk.setBlockState(
          pos,
          prepareBlockStateForWorldGen(RegistryCompat.getStateFromMeta(par4, par5)),
          false);
      return true;
    }
    return false;
  }

  /** Legacy numeric id kept for 1.12 teleporter/item references; Utopia level stem is {@code chaospersists:utopia}. */
  private static void configureDimensionIds(Configuration config, String ids) {
    Property baseProp = config.get(ids, "BaseDimensionID", 80);
    baseProp.setComment(
        "Legacy numeric dimension ids (1.12 compat). Datapack stems: chaospersists:utopia, chaospersists:mining, chaospersists:village, chaospersists:danger, chaospersists:crystal, chaospersists:chaos.");
    int base = baseProp.getInt();
    Property utopiaProp = config.get(ids, "DimensionId_Utopia", -1);
    utopiaProp.setComment("Numeric id for Utopia. -1 uses BaseDimensionID.");
    DimensionID = utopiaProp.getInt() >= 0 ? utopiaProp.getInt() : base;
    Property miningProp = config.get(ids, "DimensionId_Mining", -1);
    miningProp.setComment("Numeric id for Mining. -1 uses BaseDimensionID + 1.");
    DimensionID2 = miningProp.getInt() >= 0 ? miningProp.getInt() : base + 1;
    Property villageProp = config.get(ids, "DimensionId_VillageMania", -1);
    villageProp.setComment("Numeric id for Village Mania. -1 uses BaseDimensionID + 2.");
    DimensionID3 = villageProp.getInt() >= 0 ? villageProp.getInt() : base + 2;
    Property islandsProp = config.get(ids, "DimensionId_Islands", -1);
    islandsProp.setComment("Numeric id for Danger dimension. -1 uses BaseDimensionID + 3.");
    DimensionID4 = islandsProp.getInt() >= 0 ? islandsProp.getInt() : base + 3;
    Property crystalProp = config.get(ids, "DimensionId_Crystal", -1);
    crystalProp.setComment("Numeric id for Crystal dimension. -1 uses BaseDimensionID + 4.");
    DimensionID5 = crystalProp.getInt() >= 0 ? crystalProp.getInt() : base + 4;
    Property chaosProp = config.get(ids, "DimensionId_Chaos", -1);
    chaosProp.setComment("Numeric id for Chaos sky-islands dimension. -1 uses BaseDimensionID + 5.");
    DimensionID6 = chaosProp.getInt() >= 0 ? chaosProp.getInt() : base + 5;
  }

  private static ArmorStats get_armorstats(
      Configuration config,
      String s,
      int dura,
      int head,
      int chest,
      int leg,
      int boots,
      int enchant,
      int e_resp,
      int e_aqua,
      int e_prot,
      int e_fire,
      int e_blast,
      int e_proj,
      int e_unbreak,
      int e_feather) {
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

  /** Fixes persisted 1.12 diamond-copy Amethyst stats (3/6/8/3) to OreSpawn wiki values (4/8/7/3). */
  private static void migrateLegacyAmethystArmorConfig(Configuration config) {
    String arm = "chaospersistsARMOR";
    int head = config.get(arm, "Amethyst_head_damage_reduce", 4).getInt();
    int chest = config.get(arm, "Amethyst_chest_damage_reduce", 8).getInt();
    int leg = config.get(arm, "Amethyst_leggings_damage_reduce", 7).getInt();
    int boots = config.get(arm, "Amethyst_boots_damage_reduce", 3).getInt();
    int enchant = config.get(arm, "Amethyst_enchantability", 40).getInt();
    if (head == 3 && chest == 6 && leg == 8 && boots == 3 && enchant == 10) {
      config.get(arm, "Amethyst_head_damage_reduce", 4).set(4);
      config.get(arm, "Amethyst_chest_damage_reduce", 8).set(8);
      config.get(arm, "Amethyst_leggings_damage_reduce", 7).set(7);
      config.get(arm, "Amethyst_boots_damage_reduce", 3).set(3);
      config.get(arm, "Amethyst_enchantability", 40).set(40);
      config.save();
    }
  }

  private static WeaponStats get_weaponstats(
      Configuration config,
      String arm,
      String s,
      int harvest,
      int maxuses,
      int efficiency,
      int damage,
      int enchantability) {
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

  private static MobStats get_mobstats(
      Configuration config, String arm, String s, int health, int attack, int defense) {
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

  private static OreStats get_orestats(
      Configuration config, String arm, String s, int rate, int clumpsize, int min, int max) {
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

  private static void getMobs(Configuration config, String mobs)
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
  }

  private OreGenericEgg oreEggBlock(String path) {
    return (OreGenericEgg)
        BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, path));
  }

  private Block antBlock(String path) {
    return BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath(MODID, path));
  }

  private void laySomeEggs()
  {
    MySpiderSpawnBlock = oreEggBlock("orespider");
    MyBatSpawnBlock = oreEggBlock("orebat");
    MyCowSpawnBlock = oreEggBlock("orecow");
    MyPigSpawnBlock = oreEggBlock("orepig");
    MySquidSpawnBlock = oreEggBlock("oresquid");
    MyChickenSpawnBlock = oreEggBlock("orechicken");
    MyCreeperSpawnBlock = oreEggBlock("orecreeper");
    MySkeletonSpawnBlock = oreEggBlock("oreskeleton");
    MyZombieSpawnBlock = oreEggBlock("orezombie");
    MySlimeSpawnBlock = oreEggBlock("oreslime");
    MyGhastSpawnBlock = oreEggBlock("oreghast");
    MyZombiePigmanSpawnBlock = oreEggBlock("orezombiepigman");
    MyEndermanSpawnBlock = oreEggBlock("oreenderman");
    MyCaveSpiderSpawnBlock = oreEggBlock("orecavespider");
    MySilverfishSpawnBlock = oreEggBlock("oresilverfish");
    MyMagmaCubeSpawnBlock = oreEggBlock("oremagmacube");
    MyWitchSpawnBlock = oreEggBlock("orewitch");
    MySheepSpawnBlock = oreEggBlock("oresheep");
    MyWolfSpawnBlock = oreEggBlock("orewolf");
    MyMooshroomSpawnBlock = oreEggBlock("oremooshroom");
    MyOcelotSpawnBlock = oreEggBlock("oreocelot");
    MyBlazeSpawnBlock = oreEggBlock("oreblaze");
    MyWitherSkeletonSpawnBlock = oreEggBlock("orewitherskeleton");
    MyEnderDragonSpawnBlock = oreEggBlock("oreenderdragon");
    MySnowGolemSpawnBlock = oreEggBlock("oresnowgolem");
    MyIronGolemSpawnBlock = oreEggBlock("oreirongolem");
    MyWitherBossSpawnBlock = oreEggBlock("orewitherboss");
    MyGirlfriendSpawnBlock = oreEggBlock("oregirlfriend");
    MyBoyfriendSpawnBlock = oreEggBlock("oreboyfriend");
    MyRedCowSpawnBlock = oreEggBlock("oreredcow");
    MyCrystalCowSpawnBlock = oreEggBlock("orecrystalcow");
    MyVillagerSpawnBlock = oreEggBlock("orevillager");
    MyGoldCowSpawnBlock = oreEggBlock("oregoldcow");
    MyEnchantedCowSpawnBlock = oreEggBlock("oreenchantedcow");
    MyMOTHRASpawnBlock = oreEggBlock("oremothra");
    MyAntBlock = antBlock("antblock");
    MyRedAntBlock = antBlock("redantblock");
    TermiteBlock = antBlock("termiteblock");
    CrystalTermiteBlock = antBlock("crystaltermiteblock");
    MyRainbowAntBlock = antBlock("rainbowantblock");
    MyUnstableAntBlock = antBlock("unstableantblock");
    MyAloSpawnBlock = oreEggBlock("orealosaurus");
    MyCryoSpawnBlock = oreEggBlock("orecryolophosaurus");
    MyCamaSpawnBlock = oreEggBlock("orecamarasaurus");
    MyVeloSpawnBlock = oreEggBlock("orevelocityraptor");
    MyHydroSpawnBlock = oreEggBlock("orehydrolisc");
    MyBasilSpawnBlock = oreEggBlock("orebasilisc");
    MyDragonflySpawnBlock = oreEggBlock("oredragonfly");
    MyEmperorScorpionSpawnBlock = oreEggBlock("oreemperorscorpion");
    MyScorpionSpawnBlock = oreEggBlock("orescorpion");
    MyCaveFisherSpawnBlock = oreEggBlock("orecavefisher");
    MySpyroSpawnBlock = oreEggBlock("orespyro");
    MyBaryonyxSpawnBlock = oreEggBlock("orebaryonyx");
    MyGammaMetroidSpawnBlock = oreEggBlock("oregammametroid");
    MyCockateilSpawnBlock = oreEggBlock("orecockateil");
    MyKyuubiSpawnBlock = oreEggBlock("orekyuubi");
    MyAlienSpawnBlock = oreEggBlock("orealien");
    MyAttackSquidSpawnBlock = oreEggBlock("oreattacksquid");
    MyWaterDragonSpawnBlock = oreEggBlock("orewaterdragon");
    MyCephadromeSpawnBlock = oreEggBlock("orecephadrome");
    MyDragonSpawnBlock = oreEggBlock("oredragon");
    MyKrakenSpawnBlock = oreEggBlock("orekraken");
    MyLizardSpawnBlock = oreEggBlock("orelizard");
    MyBeeSpawnBlock = oreEggBlock("orebee");
    MyHorseSpawnBlock = oreEggBlock("orehorse");
    MyTrooperBugSpawnBlock = oreEggBlock("oretrooper");
    MySpitBugSpawnBlock = oreEggBlock("orespit");
    MyStinkBugSpawnBlock = oreEggBlock("orestink");
    MyOstrichSpawnBlock = oreEggBlock("oreostrich");
    MyGazelleSpawnBlock = oreEggBlock("oregazelle");
    MyChipmunkSpawnBlock = oreEggBlock("orechipmunk");
    MyCreepingHorrorSpawnBlock = oreEggBlock("orecreepinghorror");
    MyTerribleTerrorSpawnBlock = oreEggBlock("oreterribleterror");
    MyCliffRacerSpawnBlock = oreEggBlock("orecliffracer");
    MyTriffidSpawnBlock = oreEggBlock("oretriffid");
    MyPitchBlackSpawnBlock = oreEggBlock("orenightmare");
    MyLurkingTerrorSpawnBlock = oreEggBlock("orelurkingterror");
    MyGodzillaPartSpawnBlock = oreEggBlock("oregodzillapart");
    MyGodzillaSpawnBlock = oreEggBlock("oregodzilla");
    MySmallWormSpawnBlock = oreEggBlock("oresmallworm");
    MyMediumWormSpawnBlock = oreEggBlock("oremediumworm");
    MyLargeWormSpawnBlock = oreEggBlock("orelargeworm");
    MyCassowarySpawnBlock = oreEggBlock("orecassowary");
    MyCloudSharkSpawnBlock = oreEggBlock("orecloudshark");
    MyGoldFishSpawnBlock = oreEggBlock("oregoldfish");
    MyLeafMonsterSpawnBlock = oreEggBlock("oreleafmonster");
    MyTshirtSpawnBlock = oreEggBlock("oretshirt");
    MyEnderKnightSpawnBlock = oreEggBlock("oreenderknight");
    MyEnderReaperSpawnBlock = oreEggBlock("oreenderreaper");
    MyBeaverSpawnBlock = oreEggBlock("orebeaver");
    MyUrchinSpawnBlock = oreEggBlock("oreurchin");
    MyFlounderSpawnBlock = oreEggBlock("oreflounder");
    MySkateSpawnBlock = oreEggBlock("oreskate");
    MyRotatorSpawnBlock = oreEggBlock("orerotator");
    MyPeacockSpawnBlock = oreEggBlock("orepeacock");
    MyFairySpawnBlock = oreEggBlock("orefairy");
    MyDungeonBeastSpawnBlock = oreEggBlock("oredungeonbeast");
    MyVortexSpawnBlock = oreEggBlock("orevortex");
    MyRatSpawnBlock = oreEggBlock("orerat");
    MyWhaleSpawnBlock = oreEggBlock("orewhale");
    MyIrukandjiSpawnBlock = oreEggBlock("oreirukandji");
    MyTRexSpawnBlock = oreEggBlock("oretrex");
    MyHerculesSpawnBlock = oreEggBlock("orehercules");
    MyMantisSpawnBlock = oreEggBlock("oremantis");
    MyStinkySpawnBlock = oreEggBlock("orestinky");
    MyTheKingPartSpawnBlock = oreEggBlock("orethekingpart");
    MyTheKingSpawnBlock = oreEggBlock("oretheking");
    MyTheQueenPartSpawnBlock = oreEggBlock("orethequeenpart");
    MyTheQueenSpawnBlock = oreEggBlock("orethequeen");
    MyEasterBunnySpawnBlock = oreEggBlock("oreeasterbunny");
    MyCaterKillerSpawnBlock = oreEggBlock("orecaterkiller");
    MyMolenoidSpawnBlock = oreEggBlock("oremolenoid");
    MySeaMonsterSpawnBlock = oreEggBlock("oreseamonster");
    MySeaViperSpawnBlock = oreEggBlock("oreseaviper");
    MyLeonSpawnBlock = oreEggBlock("oreleon");
    MyHammerheadSpawnBlock = oreEggBlock("orehammerhead");
    MyRubberDuckySpawnBlock = oreEggBlock("orerubberducky");
    MyCriminalSpawnBlock = oreEggBlock("orecriminal");
    MyBrutalflySpawnBlock = oreEggBlock("orebrutalfly");
    MyNastysaurusSpawnBlock = oreEggBlock("orenastysaurus");
    MyPointysaurusSpawnBlock = oreEggBlock("orepointysaurus");
    MyCricketSpawnBlock = oreEggBlock("orecricket");
    MyFrogSpawnBlock = oreEggBlock("orefrog");
    MySpiderDriverSpawnBlock = oreEggBlock("orespiderdriver");
    MyCrabSpawnBlock = oreEggBlock("orecrab");
  }

  public String getVersion()
  {
    return "1.7.10.20.3";
  }
}
