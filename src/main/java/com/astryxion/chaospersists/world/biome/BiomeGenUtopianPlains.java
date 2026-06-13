package com.astryxion.chaospersists.world.biome;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Gazelle;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.entity.RedCow;
import com.astryxion.chaospersists.entity.GoldCow;
import com.astryxion.chaospersists.entity.EnchantedCow;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import com.astryxion.chaospersists.entity.Firefly;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Stinky;
import com.astryxion.chaospersists.entity.CliffRacer;
import com.astryxion.chaospersists.entity.CloudShark;
import com.astryxion.chaospersists.entity.GoldFish;
import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.Fairy;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.Urchin;
import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.entity.Rat;
import com.astryxion.chaospersists.entity.Whale;
import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.Frog;
import com.astryxion.chaospersists.entity.Robot1;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.SpiderDriver;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.Cricket;
import com.astryxion.chaospersists.item.Tshirt;
import com.astryxion.chaospersists.item.Coin;
import com.astryxion.chaospersists.item.BandP;
import com.astryxion.chaospersists.entity.Baryonyx;
import com.astryxion.chaospersists.entity.Bee;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.CaveFisher;
import com.astryxion.chaospersists.entity.Cryolophosaurus;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.EnderKnight;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.entity.Scorpion;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.Pointysaurus;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.Cassowary;
import com.astryxion.chaospersists.entity.Dragonfly;
import com.astryxion.chaospersists.entity.StinkBug;
import com.astryxion.chaospersists.entity.Ostrich;
import com.astryxion.chaospersists.entity.Beaver;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.Basilisk;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Utopia-family biomes ({@link Biome} is final in 1.16.5). Spawn tables match 1.12.2 {@code BiomeGenUtopianPlains}.
 */
public class BiomeGenUtopianPlains {

    public static void addSpawn(MobSpawnInfo.Builder spawns, EntityClassification classification, String path, int weight,
            int min, int max) {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", path));
        if (type != null) {
            spawns.addSpawn(classification, new MobSpawnInfo.Spawners(type, weight, min, max));
        }
    }

    private static BiomeAmbience ambience(int waterColor) {
        return new BiomeAmbience.Builder().waterColor(waterColor).waterFogColor(waterColor).fogColor(12638463).skyColor(7907327)
                .build();
    }

    public static Biome buildBiome(int waterColor, float temperature, float downfall, float depth, float scale,
            MobSpawnInfo.Builder spawns) {
        return buildBiome(waterColor, temperature, downfall, depth, scale, spawns, ChaosBiomeGeneration.plainsLike());
    }

    public static Biome buildBiome(int waterColor, float temperature, float downfall, float depth, float scale,
            MobSpawnInfo.Builder spawns, BiomeGenerationSettings generationSettings) {
        return new Biome.Builder().precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.PLAINS).depth(depth)
                .scale(scale).temperature(temperature).downfall(downfall).specialEffects(ambience(waterColor))
                .mobSpawnSettings(spawns.build()).generationSettings(generationSettings).build();
    }

    public Biome build() {
        MobSpawnInfo.Builder spawns = new MobSpawnInfo.Builder();
        addUtopiaPlainsSpawnEntries(spawns);
        return buildBiome(353825, 0.7f, 0.5f, 0.125f, 0.05f, spawns);
    }

    public static void addUtopiaPlainsSpawnEntries(MobSpawnInfo.Builder spawns) {
if (ChaosPersists.GazelleEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "gazelle", 10, 2, 4);
        }
        if (ChaosPersists.FireflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "firefly", 15, 3, 6);
        }
        if (ChaosPersists.GirlfriendEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "girlfriend", 5, 2, 3);
        }
        if (ChaosPersists.BoyfriendEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "boyfriend", 5, 2, 3);
        }
        if (ChaosPersists.CowEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "apple_cow", 10, 4, 8);
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "golden_apple_cow", 8, 2, 6);
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "enchanted_golden_apple_cow", 5, 2, 4);
        }
        if (ChaosPersists.ButterflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "butterfly", 20, 3, 6);
        }
        if (ChaosPersists.MothEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "moth", 10, 1, 5);
        }
        if (ChaosPersists.ChipmunkEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "chipmunk", 3, 1, 2);
        }
        if (ChaosPersists.CockateilEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "bird", 10, 2, 4);
        }
        if (ChaosPersists.GoldFishEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "gold_fish", 1, 1, 1);
        }
        if (ChaosPersists.WhaleEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "whale", 1, 1, 1);
        }
        if (ChaosPersists.FlounderEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "flounder", 2, 2, 4);
        }
        if (ChaosPersists.CoinEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "coin", 2, 1, 1);
        }
        if (ChaosPersists.CricketEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "cricket", 5, 4, 6);
        }
        if (ChaosPersists.FrogEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "frog", 5, 4, 6);
        }
    }

    public static void addIslandSpawnEntries(MobSpawnInfo.Builder spawns) {
if (ChaosPersists.ButterflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "butterfly", 5, 2, 6);
        }
        if (ChaosPersists.CockateilEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "bird", 4, 1, 2);
        }
        if (ChaosPersists.MothEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "moth", 5, 2, 4);
        }
        if (ChaosPersists.FireflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "firefly", 10, 4, 8);
        }
        if (ChaosPersists.DragonEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "dragon", 1, 1, 2);
        }
        if (ChaosPersists.StinkyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "stinky", 2, 1, 2);
        }
        if (ChaosPersists.CliffRacerEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "cliff_racer", 20, 3, 6);
        }
        if (ChaosPersists.CloudSharkEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "cloud_shark", 1, 1, 1);
        }
        if (ChaosPersists.GoldFishEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "gold_fish", 5, 2, 4);
        }
        if (ChaosPersists.CreepingHorrorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "creeping_horror", 60, 4, 8);
        }
        if (ChaosPersists.TerribleTerrorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "terrible_terror", 25, 3, 6);
        }
        if (ChaosPersists.LurkingTerrorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "lurking_terror", 1, 1, 1);
        }
        if (ChaosPersists.PitchBlackEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "nightmare", 15, 3, 6);
        }
        if (ChaosPersists.LeafMonsterEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "leaf_monster", 35, 2, 4);
        }
        if (ChaosPersists.EnderReaperEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "ender_reaper", 25, 2, 4);
        }
        if (ChaosPersists.HerculesBeetleEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "hercules_beetle", 5, 1, 2);
        }
    }

    public static void addCrystalSpawnEntries(MobSpawnInfo.Builder spawns) {
if (ChaosPersists.CowEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "crystal_apple_cow", 1, 1, 4);
        }
        if (ChaosPersists.FairyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "fairy", 10, 4, 8);
        }
        if (ChaosPersists.PeacockEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "peacock", 5, 4, 8);
        }
        if (ChaosPersists.MantisEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "mantis", 1, 1, 1);
        }
        if (ChaosPersists.RotatorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "rotator", 4, 1, 2);
        }
        if (ChaosPersists.VortexEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "vortex", 3, 1, 2);
        }
        if (ChaosPersists.UrchinEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "crystal_urchin", 15, 2, 4);
        }
        if (ChaosPersists.DungeonBeastEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "dungeon_beast", 30, 4, 6);
        }
        if (ChaosPersists.RatEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "rat", 40, 4, 6);
        }
        if (ChaosPersists.ButterflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "butterfly", 10, 2, 4);
        }
        if (ChaosPersists.CockateilEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "bird", 4, 1, 2);
        }
        if (ChaosPersists.MothEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "moth", 4, 1, 2);
        }
        if (ChaosPersists.WhaleEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "whale", 1, 1, 2);
        }
        if (ChaosPersists.CrabEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "crab", 1, 1, 2);
        }
        if (ChaosPersists.FlounderEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "flounder", 5, 6, 8);
        }
        if (ChaosPersists.IrukandjiEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "irukandji", 4, 2, 3);
        }
        if (ChaosPersists.SkateEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "skate", 2, 3, 6);
        }
        if (ChaosPersists.FrogEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.WATER_CREATURE, "frog", 1, 3, 5);
        }
    }

    /**
     * Village Mania dimension (1.7.10): same biome instance first gets {@link #addUtopiaPlainsSpawnEntries()} then these entries.
     */
    public static void addVillageSpawnEntries(MobSpawnInfo.Builder spawns) {
if (ChaosPersists.Robot1Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "bomb_omb", 25, 4, 8);
        }
        if (ChaosPersists.Robot2Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_pounder", 16, 2, 8);
        }
        if (ChaosPersists.Robot3Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_gunner", 12, 2, 4);
        }
        if (ChaosPersists.Robot4Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_warrior", 8, 1, 2);
        }
        if (ChaosPersists.Robot5Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_sniper", 20, 4, 8);
        }
        if (ChaosPersists.JefferyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "jeffery", 8, 1, 2);
        }
        if (ChaosPersists.SpiderDriverEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "spider_driver", 20, 3, 5);
        }
        if (ChaosPersists.GodzillaEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "mobzilla", 2, 1, 1);
        }
        if (ChaosPersists.FireflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "firefly", 10, 3, 6);
        }
        if (ChaosPersists.GirlfriendEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "girlfriend", 1, 2, 3);
        }
        if (ChaosPersists.BoyfriendEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "boyfriend", 1, 2, 3);
        }
        if (ChaosPersists.CowEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "apple_cow", 8, 4, 8);
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "golden_apple_cow", 6, 2, 6);
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "enchanted_golden_apple_cow", 4, 2, 4);
        }
        if (ChaosPersists.ButterflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "butterfly", 25, 3, 6);
        }
        if (ChaosPersists.MothEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "moth", 20, 1, 5);
        }
        if (ChaosPersists.ChipmunkEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "chipmunk", 5, 1, 2);
        }
        if (ChaosPersists.CockateilEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "bird", 15, 2, 4);
        }
        if (ChaosPersists.TshirtEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "tshirt", 2, 1, 1);
        }
        if (ChaosPersists.CoinEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "coin", 2, 1, 1);
        }
        if (ChaosPersists.CriminalEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "criminal", 15, 1, 2);
        }
    }

    public static void addChaosSpawnEntries(MobSpawnInfo.Builder spawns) {
if (ChaosPersists.ButterflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "butterfly", 20, 3, 6);
        }
        if (ChaosPersists.MothEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "moth", 10, 1, 5);
        }
        if (ChaosPersists.CockateilEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "bird", 10, 2, 4);
        }
        if (ChaosPersists.FireflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "firefly", 15, 3, 6);
        }
        if (ChaosPersists.CliffRacerEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "cliff_racer", 30, 3, 6);
        }
        if (ChaosPersists.CloudSharkEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "cloud_shark", 2, 1, 1);
        }
        if (ChaosPersists.GoldFishEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "gold_fish", 10, 2, 4);
        }
        if (ChaosPersists.FairyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "fairy", 5, 2, 4);
        }
        if (ChaosPersists.BaryonyxEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "baryonyx", 2, 2, 4);
        }
        if (ChaosPersists.BeeEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "bee", 2, 2, 4);
        }
        if (ChaosPersists.CassowaryEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "cassowary", 2, 2, 4);
        }
        if (ChaosPersists.DragonflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "dragonfly", 2, 2, 4);
        }
        if (ChaosPersists.PeacockEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "peacock", 2, 2, 4);
        }
        if (ChaosPersists.StinkBugEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "stink_bug", 3, 2, 4);
        }
        if (ChaosPersists.OstrichEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "ostrich", 1, 1, 2);
        }
        if (ChaosPersists.ChipmunkEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.AMBIENT, "chipmunk", 1, 1, 2);
        }
        if (ChaosPersists.BeaverEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "beaver", 1, 1, 2);
        }
        if (ChaosPersists.CowEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "apple_cow", 3, 2, 4);
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "golden_apple_cow", 2, 2, 4);
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.CREATURE, "enchanted_golden_apple_cow", 1, 2, 4);
        }
        if (ChaosPersists.VortexEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "vortex", 1, 1, 2);
        }
        if (ChaosPersists.PitchBlackEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "nightmare", 1, 1, 2);
        }
        if (ChaosPersists.TerribleTerrorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "terrible_terror", 4, 2, 6);
        }
        if (ChaosPersists.AlosaurusEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "alosaurus", 1, 1, 1);
        }
        if (ChaosPersists.BasiliskEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "basilisk", 1, 1, 1);
        }
        if (ChaosPersists.Robot1Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "bomb_omb", 5, 2, 8);
        }
        if (ChaosPersists.Robot2Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_pounder", 2, 1, 4);
        }
        if (ChaosPersists.Robot3Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_gunner", 2, 1, 4);
        }
        if (ChaosPersists.Robot4Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_warrior", 1, 1, 2);
        }
        if (ChaosPersists.Robot5Enable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "robo_sniper", 2, 3, 5);
        }
        if (ChaosPersists.CaterKillerEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "caterkiller", 1, 1, 1);
        }
        if (ChaosPersists.CaveFisherEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "cave_fisher", 5, 1, 5);
        }
        if (ChaosPersists.CreepingHorrorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "creeping_horror", 5, 1, 5);
        }
        if (ChaosPersists.CryolophosaurusEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "cryolophosaurus", 5, 1, 5);
        }
        if (ChaosPersists.UrchinEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "crystal_urchin", 2, 1, 5);
        }
        if (ChaosPersists.DungeonBeastEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "dungeon_beast", 2, 1, 5);
        }
        if (ChaosPersists.EmperorScorpionEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "emperor_scorpion", 1, 1, 1);
        }
        if (ChaosPersists.EnderKnightEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "ender_knight", 2, 1, 2);
        }
        if (ChaosPersists.EnderReaperEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "ender_reaper", 1, 1, 1);
        }
        if (ChaosPersists.HammerheadEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "hammerhead", 1, 1, 1);
        }
        if (ChaosPersists.HerculesBeetleEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "hercules_beetle", 1, 1, 1);
        }
        if (ChaosPersists.TrooperBugEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "jumpy_bug", 1, 1, 1);
        }
        if (ChaosPersists.MolenoidEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "molenoid", 1, 1, 1);
        }
        if (ChaosPersists.MothraEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "mothra", 1, 1, 1);
        }
        if (ChaosPersists.BrutalflyEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "brutalfly", 1, 1, 1);
        }
        if (ChaosPersists.RatEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "rat", 10, 1, 10);
        }
        if (ChaosPersists.RotatorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "rotator", 1, 1, 3);
        }
        if (ChaosPersists.ScorpionEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "scorpion", 2, 1, 3);
        }
        if (ChaosPersists.SpitBugEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "spit_bug", 2, 1, 3);
        }
        if (ChaosPersists.NastysaurusEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "nastysaurus", 1, 1, 1);
        }
        if (ChaosPersists.TRexEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "trex", 1, 1, 1);
        }
        if (ChaosPersists.LeafMonsterEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "leaf_monster", 2, 1, 4);
        }
        if (ChaosPersists.PointysaurusEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "pointysaurus", 2, 1, 4);
        }
        if (ChaosPersists.LeonEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "leonopteryx", 1, 1, 1);
        }
        if (ChaosPersists.MantisEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "mantis", 1, 1, 1);
        }
        if (ChaosPersists.LurkingTerrorEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "lurking_terror", 1, 1, 1);
        }
        if (ChaosPersists.GammaMetroidEnable != 0) {
            BiomeGenUtopianPlains.addSpawn(spawns, EntityClassification.MONSTER, "gamma_metroid", 1, 1, 1);
        }
    }


}
