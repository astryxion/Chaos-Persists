package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.block.KingSpawnerBlock;
import com.astryxion.chaospersists.block.QueenSpawnerBlock;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.ItemSpawnEgg;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntSupplier;
import javax.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Per-mob {@code Enable} flags fully disable a mob. {@code AllMobsDisable} only stops natural
 * spawning.
 */
public final class ChaosMobAccess {
    public static final Component ITEM_DISABLED = Component.literal("This item is currently disabled");
    public static final Component MOB_DISABLED = Component.literal("This mob is currently disabled");
    public static final Component BLOCK_DISABLED = Component.literal("This block is currently disabled");

    private static final Map<String, IntSupplier> BY_PATH = new HashMap<>();

    static {
        put("alien", () -> ChaosPersists.AlienEnable);
        put("alosaurus", () -> ChaosPersists.AlosaurusEnable);
        put("attack_squid", () -> ChaosPersists.AttackSquidEnable);
        put("baryonyx", () -> ChaosPersists.BaryonyxEnable);
        put("basilisk", () -> ChaosPersists.BasiliskEnable);
        put("beaver", () -> ChaosPersists.BeaverEnable);
        put("bee", () -> ChaosPersists.BeeEnable);
        put("bird", () -> ChaosPersists.CockateilEnable);
        put("ruby_bird", () -> ChaosPersists.CockateilEnable);
        put("ant", () -> ChaosPersists.BlackAntEnable);
        put("boyfriend", () -> ChaosPersists.BoyfriendEnable);
        put("brutalfly", () -> ChaosPersists.BrutalflyEnable);
        put("butterfly", () -> ChaosPersists.ButterflyEnable);
        put("camarasaurus", () -> ChaosPersists.CamarasaurusEnable);
        put("cassowary", () -> ChaosPersists.CassowaryEnable);
        put("caterkiller", () -> ChaosPersists.CaterKillerEnable);
        put("cave_fisher", () -> ChaosPersists.CaveFisherEnable);
        put("cephadrome", () -> ChaosPersists.CephadromeEnable);
        put("chipmunk", () -> ChaosPersists.ChipmunkEnable);
        put("cliff_racer", () -> ChaosPersists.CliffRacerEnable);
        put("cloud_shark", () -> ChaosPersists.CloudSharkEnable);
        put("coin", () -> ChaosPersists.CoinEnable);
        put("apple_cow", () -> ChaosPersists.CowEnable);
        put("golden_apple_cow", () -> ChaosPersists.CowEnable);
        put("enchanted_golden_apple_cow", () -> ChaosPersists.CowEnable);
        put("crystal_apple_cow", () -> ChaosPersists.CowEnable);
        put("crab", () -> ChaosPersists.CrabEnable);
        put("creeping_horror", () -> ChaosPersists.CreepingHorrorEnable);
        put("cricket", () -> ChaosPersists.CricketEnable);
        put("criminal", () -> ChaosPersists.CriminalEnable);
        put("cryolophosaurus", () -> ChaosPersists.CryolophosaurusEnable);
        put("dragon", () -> ChaosPersists.DragonEnable);
        put("dragonfly", () -> ChaosPersists.DragonflyEnable);
        put("dungeon_beast", () -> ChaosPersists.DungeonBeastEnable);
        put("easter_bunny", () -> ChaosPersists.EasterBunnyEnable);
        put("emperor_scorpion", () -> ChaosPersists.EmperorScorpionEnable);
        put("ender_knight", () -> ChaosPersists.EnderKnightEnable);
        put("ender_reaper", () -> ChaosPersists.EnderReaperEnable);
        put("fairy", () -> ChaosPersists.FairyEnable);
        put("firefly", () -> ChaosPersists.FireflyEnable);
        put("flounder", () -> ChaosPersists.FlounderEnable);
        put("frog", () -> ChaosPersists.FrogEnable);
        put("gamma_metroid", () -> ChaosPersists.GammaMetroidEnable);
        put("wtf", () -> ChaosPersists.GammaMetroidEnable);
        put("gazelle", () -> ChaosPersists.GazelleEnable);
        put("ghost", () -> ChaosPersists.GhostEnable);
        put("ghost_pumpkin_skelly", () -> ChaosPersists.GhostSkellyEnable);
        put("girlfriend", () -> ChaosPersists.GirlfriendEnable);
        put("mobzilla", () -> ChaosPersists.GodzillaEnable);
        put("mobzilla_head", () -> ChaosPersists.GodzillaEnable);
        put("gold_fish", () -> ChaosPersists.GoldFishEnable);
        put("hammerhead", () -> ChaosPersists.HammerheadEnable);
        put("hercules_beetle", () -> ChaosPersists.HerculesBeetleEnable);
        put("hydrolisc", () -> ChaosPersists.HydroliscEnable);
        put("irukandji", () -> ChaosPersists.IrukandjiEnable);
        put("jeffery", () -> ChaosPersists.JefferyEnable);
        put("the_kraken", () -> ChaosPersists.KrakenEnable);
        put("kyuubi", () -> ChaosPersists.KyuubiEnable);
        put("leaf_monster", () -> ChaosPersists.LeafMonsterEnable);
        put("leonopteryx", () -> ChaosPersists.LeonEnable);
        put("lizard", () -> ChaosPersists.LizardEnable);
        put("lurking_terror", () -> ChaosPersists.LurkingTerrorEnable);
        put("mantis", () -> ChaosPersists.MantisEnable);
        put("molenoid", () -> ChaosPersists.MolenoidEnable);
        put("mosquito", () -> ChaosPersists.MosquitoEnable);
        put("moth", () -> ChaosPersists.MothEnable);
        put("mothra", () -> ChaosPersists.MothraEnable);
        put("nastysaurus", () -> ChaosPersists.NastysaurusEnable);
        put("nightmare", () -> ChaosPersists.PitchBlackEnable);
        put("ostrich", () -> ChaosPersists.OstrichEnable);
        put("peacock", () -> ChaosPersists.PeacockEnable);
        put("pointysaurus", () -> ChaosPersists.PointysaurusEnable);
        put("rainbow_ant", () -> ChaosPersists.RainbowAntEnable);
        put("rat", () -> ChaosPersists.RatEnable);
        put("red_ant", () -> ChaosPersists.RedAntEnable);
        put("bomb_omb", () -> ChaosPersists.Robot1Enable);
        put("robo_pounder", () -> ChaosPersists.Robot2Enable);
        put("robo_gunner", () -> ChaosPersists.Robot3Enable);
        put("robo_warrior", () -> ChaosPersists.Robot4Enable);
        put("robo_sniper", () -> ChaosPersists.Robot5Enable);
        put("rock", () -> ChaosPersists.RockEnable);
        put("rotator", () -> ChaosPersists.RotatorEnable);
        put("rubber_ducky", () -> ChaosPersists.RubberDuckyEnable);
        put("scorpion", () -> ChaosPersists.ScorpionEnable);
        put("sea_monster", () -> ChaosPersists.SeaMonsterEnable);
        put("sea_viper", () -> ChaosPersists.SeaViperEnable);
        put("skate", () -> ChaosPersists.SkateEnable);
        put("spider_driver", () -> ChaosPersists.SpiderDriverEnable);
        put("spit_bug", () -> ChaosPersists.SpitBugEnable);
        put("baby_dragon", () -> ChaosPersists.SpyroEnable);
        put("stink_bug", () -> ChaosPersists.StinkBugEnable);
        put("stinky", () -> ChaosPersists.StinkyEnable);
        put("trex", () -> ChaosPersists.TRexEnable);
        put("t._rex", () -> ChaosPersists.TRexEnable);
        put("termite", () -> ChaosPersists.TermiteEnable);
        put("terrible_terror", () -> ChaosPersists.TerribleTerrorEnable);
        put("the_king", () -> ChaosPersists.TheKingEnable);
        put("king_head", () -> ChaosPersists.TheKingEnable);
        put("the_queen", () -> ChaosPersists.TheQueenEnable);
        put("queen_head", () -> ChaosPersists.TheQueenEnable);
        put("the_prince", () -> ChaosPersists.ThePrinceEnable);
        put("the_young_prince", () -> ChaosPersists.ThePrinceEnable);
        put("the_young_adult_prince", () -> ChaosPersists.ThePrinceEnable);
        put("the_princess", () -> ChaosPersists.ThePrincessEnable);
        put("triffid", () -> ChaosPersists.TriffidEnable);
        put("jumpy_bug", () -> ChaosPersists.TrooperBugEnable);
        put("tshirt", () -> ChaosPersists.TshirtEnable);
        put("unstable_ant", () -> ChaosPersists.UnstableAntEnable);
        put("crystal_urchin", () -> ChaosPersists.UrchinEnable);
        put("velocity_raptor", () -> ChaosPersists.VelocityRaptorEnable);
        put("vortex", () -> ChaosPersists.VortexEnable);
        put("water_dragon", () -> ChaosPersists.WaterDragonEnable);
        put("whale", () -> ChaosPersists.WhaleEnable);
        put("small_worm", () -> ChaosPersists.WormEnable);
        put("medium_worm", () -> ChaosPersists.WormEnable);
        put("large_worm", () -> ChaosPersists.WormEnable);
    }

    private ChaosMobAccess() {}

    private static void put(String path, IntSupplier enable) {
        BY_PATH.put(path, enable);
    }

    public static boolean allNaturalSpawnsDisabled() {
        return ChaosPersists.AllMobsDisable != 0;
    }

    public static boolean isNaturalSpawnType(@Nullable MobSpawnType spawnType) {
        if (spawnType == null) {
            return true;
        }
        return spawnType == MobSpawnType.NATURAL
                || spawnType == MobSpawnType.CHUNK_GENERATION
                || spawnType == MobSpawnType.REINFORCEMENT
                || spawnType == MobSpawnType.PATROL;
    }

    public static boolean isEntityEnabled(@Nullable EntityType<?> type) {
        if (type == null) {
            return true;
        }
        ResourceLocation key = ForgeRegistries.ENTITY_TYPES.getKey(type);
        if (key == null || !ChaosPersists.MODID.equals(key.getNamespace())) {
            return true;
        }
        IntSupplier enable = BY_PATH.get(key.getPath());
        return enable == null || enable.getAsInt() != 0;
    }

    public static boolean isEntityEnabled(@Nullable ResourceLocation id) {
        if (id == null || !ChaosPersists.MODID.equals(id.getNamespace())) {
            return true;
        }
        IntSupplier enable = BY_PATH.get(id.getPath());
        return enable == null || enable.getAsInt() != 0;
    }

    public static boolean shouldCancelSpawn(@Nullable EntityType<?> type, @Nullable MobSpawnType spawnType) {
        if (!isEntityEnabled(type)) {
            return true;
        }
        return allNaturalSpawnsDisabled()
                && ChaosPackConfig.isChaosEntityType(type)
                && isNaturalSpawnType(spawnType);
    }

    public static boolean isItemAllowed(@Nullable Item item) {
        return disabledItemMessage(item) == null;
    }

    @Nullable
    public static Component disabledItemMessage(@Nullable Item item) {
        if (item == null) {
            return null;
        }
        if (item instanceof ItemSpawnEgg egg) {
            EntityType<?> type = ItemSpawnEgg.resolveEggEntityType(egg.my_id);
            if (type != null && !isEntityEnabled(type)) {
                return ITEM_DISABLED;
            }
            return null;
        }
        if (item instanceof ForgeSpawnEggItem forgeEgg) {
            EntityType<?> type = forgeEgg.getType(null);
            if (type != null && !isEntityEnabled(type)) {
                return ITEM_DISABLED;
            }
        }
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof KingSpawnerBlock && ChaosPersists.TheKingEnable == 0) {
                return BLOCK_DISABLED;
            }
            if (block instanceof QueenSpawnerBlock && ChaosPersists.TheQueenEnable == 0) {
                return BLOCK_DISABLED;
            }
        }
        return null;
    }

    @Nullable
    public static Component disabledGiveMessage(@Nullable ResourceLocation itemId) {
        if (itemId == null) {
            return null;
        }
        Item item = ForgeRegistries.ITEMS.getValue(itemId);
        return disabledItemMessage(item);
    }

    @Nullable
    public static Component disabledSummonMessage(@Nullable ResourceLocation entityId) {
        if (entityId == null || isEntityEnabled(entityId)) {
            return null;
        }
        return MOB_DISABLED;
    }
}
