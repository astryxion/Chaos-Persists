package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.legacy.forge.common.config.Configuration;
import com.astryxion.chaospersists.world.biome.LegacyBiomeMatcher;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.fml.loading.FMLPaths;

/**
 * Pack-facing worldgen / spawn / griefing options. 0/1 (and 0/1/2 for griefing) match the
 * existing {@code chaospersists.cfg} integer style.
 */
public final class ChaosPackConfig {
    public static final String CAT_OVERWORLD = "chaospersistsOVERWORLD";
    public static final String CAT_GRIEFING = "chaospersistsGRIEFING";
    public static final String CAT_STRUCTURES = "chaospersistsSTRUCTURES";
    public static final String CAT_TWEAKS = "chaospersistsTWEAKS";

    /** 0 = follow {@code mobGriefing}, 1 = always, 2 = never. */
    public static final int GRIEF_FOLLOW = 0;
    public static final int GRIEF_ALWAYS = 1;
    public static final int GRIEF_NEVER = 2;

    public static final String[] STRUCTURE_IDS = {
        "alien_wtf_dungeon",
        "basilisk_maze",
        "bee_hive",
        "bee_nest",
        "bouncy_castle",
        "cephadrome_altar",
        "cloud_shark",
        "crystal_battle_tower",
        "crystal_haunted_house",
        "damsel_in_distress",
        "danger_dungeon",
        "danger_ender_castle",
        "danger_ruby_dungeon",
        "dungeon",
        "ender_castle",
        "ender_dragon_hospital",
        "ender_knight_dungeon",
        "ender_reaper_graveyard",
        "enormous_castle",
        "frog_pond",
        "girlfriend_island",
        "goldfish_bowl",
        "greenhouse",
        "haunted_house",
        "igloo",
        "inca_pyramid",
        "king_altar",
        "king_tree",
        "kyuubi_dungeon",
        "leaf_monster",
        "leon_nest",
        "mini_dungeon",
        "monster_island",
        "nest",
        "nightmare_dungeon",
        "nightmare_rookery",
        "play_pool",
        "pumpkin",
        "queen_altar",
        "queen_tree",
        "rainbow",
        "red_ant_hangout",
        "robot_lab",
        "rotator_station",
        "round_rotator",
        "rubber_ducky_pond",
        "ruby_dungeon",
        "spider_hangout",
        "spit_bug",
        "stinky_house",
        "urchin_spawner",
        "village_mania_village",
        "water_dragon_lair",
        "white_house"
    };

    private static final Map<String, Boolean> STRUCTURE_ENABLE = new LinkedHashMap<>();

    private ChaosPackConfig() {}

    public static java.io.File configFile() {
        return FMLPaths.CONFIGDIR.get().resolve("chaospersists.cfg").toFile();
    }

    public static void load(Configuration config) {
        config.setCategoryComment(
                CAT_OVERWORLD,
                "Disable OreSpawn / Chaos Persists content in the vanilla Overworld. "
                        + "Chaos dimensions are unchanged. DisableOverworldFeatures=1 turns off all of the "
                        + "options below (spawns, ores, dried eggs, ants, dungeons, plants, overworld structures).");
        ChaosPersists.DisableOverworldFeatures =
                config.get(
                                CAT_OVERWORLD,
                                "DisableOverworldFeatures",
                                0,
                                "1 = ban ALL Chaos Persists Overworld content (worldgen, natural spawns, ores, dried eggs, ants, dungeons, plants, overworld structures). Dimensions still work after the End.")
                        .getInt();
        ChaosPersists.DisableOverworldNaturalSpawning =
                config.get(
                                CAT_OVERWORLD,
                                "DisableOverworldNaturalSpawning",
                                0,
                                "1 = OreSpawn mobs do not naturally spawn in the Overworld. Spawners, eggs, and Chaos dimensions still work.")
                        .getInt();
        ChaosPersists.DisableOverworldDriedOre =
                config.get(
                                CAT_OVERWORLD,
                                "DisableOverworldDriedOre",
                                0,
                                "1 = no Uranium / Titanium / Amethyst / Salt / Ruby / extra vanilla-ore boost in the Overworld.")
                        .getInt();
        ChaosPersists.DisableOverworldDriedEggs =
                config.get(
                                CAT_OVERWORLD,
                                "DisableOverworldDriedEggs",
                                0,
                                "1 = no dried spawn-egg ores (SpawnOres) in the Overworld. Same idea as DisableOverworldDungeons.")
                        .getInt();
        ChaosPersists.DisableOverworldAntNests =
                config.get(
                                CAT_OVERWORLD,
                                "DisableOverworldAntNests",
                                0,
                                "1 = no ant / termite nests in the Overworld.")
                        .getInt();

        config.setCategoryComment(
                CAT_GRIEFING,
                "Per-group block breaking. 0 = follow the mobGriefing gamerule, 1 = always grief even if the gamerule is false, 2 = never grief even if the gamerule is true. Example: RobotsGriefing=2 and BossesGriefing=1 lets Mobzilla flatten terrain while robot lasers do not.");
        ChaosPersists.RobotsGriefing =
                clampGrief(
                        config.get(
                                        CAT_GRIEFING,
                                        "RobotsGriefing",
                                        0,
                                        "Bomb-Omb, Robo-Pounder/Gunner/Warrior/Sniper, Spider Robot, and their lasers. 0 follow / 1 always / 2 never.")
                                .getInt());
        ChaosPersists.BossesGriefing =
                clampGrief(
                        config.get(
                                        CAT_GRIEFING,
                                        "BossesGriefing",
                                        0,
                                        "Mobzilla, King, Queen landscape destruction. 0 follow / 1 always / 2 never.")
                                .getInt());
        ChaosPersists.CrittersGriefing =
                clampGrief(
                        config.get(
                                        CAT_GRIEFING,
                                        "CrittersGriefing",
                                        0,
                                        "Chipmunk, Gazelle, Beaver, dinosaurs eating grass, Termites, etc. 0 follow / 1 always / 2 never.")
                                .getInt());
        ChaosPersists.OtherMobsGriefing =
                clampGrief(
                        config.get(
                                        CAT_GRIEFING,
                                        "OtherMobsGriefing",
                                        0,
                                        "Any other Chaos mob that breaks blocks. 0 follow / 1 always / 2 never.")
                                .getInt());

        config.setCategoryComment(
                CAT_STRUCTURES,
                "1 = structure may generate, 0 = never. Overworld structures are also skipped when DisableOverworldFeatures=1.");
        STRUCTURE_ENABLE.clear();
        for (String id : STRUCTURE_IDS) {
            int value = config.get(CAT_STRUCTURES, id, 1).getInt();
            STRUCTURE_ENABLE.put(id, value != 0);
        }
    }

    public static void writeCurrentValues(Configuration config) {
        config.get(CAT_OVERWORLD, "DisableOverworldFeatures", 0)
                .set(ChaosPersists.DisableOverworldFeatures);
        config.get(CAT_OVERWORLD, "DisableOverworldNaturalSpawning", 0)
                .set(ChaosPersists.DisableOverworldNaturalSpawning);
        config.get(CAT_OVERWORLD, "DisableOverworldDriedOre", 0)
                .set(ChaosPersists.DisableOverworldDriedOre);
        config.get(CAT_OVERWORLD, "DisableOverworldDriedEggs", 0)
                .set(ChaosPersists.DisableOverworldDriedEggs);
        config.get(CAT_OVERWORLD, "DisableOverworldAntNests", 0)
                .set(ChaosPersists.DisableOverworldAntNests);
        config.get(CAT_TWEAKS, "DisableOverworldDungeons", 0)
                .set(ChaosPersists.DisableOverworldDungeons);
        config.get(CAT_TWEAKS, "AllMobsDisable", 0).set(ChaosPersists.AllMobsDisable);
        config.get(CAT_TWEAKS, "IgnorePercentHealthDamage", 1)
                .set(ChaosPersists.IgnorePercentHealthDamage);
        config.get(CAT_TWEAKS, "LessOre", 0).set(ChaosPersists.LessOre);
        config.get(CAT_TWEAKS, "LessLag", 0).set(ChaosPersists.LessLag);
        config.get(CAT_TWEAKS, "PlayNicely", 0).set(ChaosPersists.PlayNicely);
        config.get(CAT_TWEAKS, "GuiOverlayEnable", 1).set(ChaosPersists.GuiOverlayEnable);
        config.get(CAT_TWEAKS, "UltimateSwordPvp", 0).set(ChaosPersists.ultimate_sword_pvp);
        config.get(CAT_TWEAKS, "BigBerthaPvp", 0).set(ChaosPersists.big_bertha_pvp);
        config.get(CAT_TWEAKS, "BoyfriendBroMode", 0).set(ChaosPersists.bro_mode);
        config.get(CAT_TWEAKS, "DuplicatorTreeEnable", 1).set(ChaosPersists.enableduplicatortree);
        config.get(CAT_TWEAKS, "RoyalGlideEnable", 1).set(ChaosPersists.RoyalGlideEnable);
        config.get(CAT_TWEAKS, "DragonflyHorseFriendly", 0).set(ChaosPersists.DragonflyHorseFriendly);
        config.get(CAT_TWEAKS, "MinersDreamExpensive", 0).set(ChaosPersists.MinersDreamExpensive);
        config.get(CAT_TWEAKS, "FullPowerKingEnable", 0).set(ChaosPersists.FullPowerKingEnable);
        config.get(CAT_TWEAKS, "PrinceAutoGrow", 1).set(ChaosPersists.PrinceAutoGrow);
        config.get(CAT_TWEAKS, "GinormousEmeraldTreeEnable", 1)
                .set(ChaosPersists.GinormousEmeraldTreeEnable);
        config.get(CAT_TWEAKS, "RatPlayerFriendly", 1).set(ChaosPersists.RatPlayerFriendly);
        config.get(CAT_TWEAKS, "RatPetFriendly", 1).set(ChaosPersists.RatPetFriendly);
        config.get(CAT_TWEAKS, "NightmareSize", 0).set(ChaosPersists.NightmareSize);
        config.get(CAT_TWEAKS, "IslandSizeFactor", 2).set(ChaosPersists.IslandSizeFactor);
        config.get(CAT_TWEAKS, "IslandSpeedFactor", 2).set(ChaosPersists.IslandSpeedFactor);
        config.get(CAT_GRIEFING, "RobotsGriefing", 0).set(ChaosPersists.RobotsGriefing);
        config.get(CAT_GRIEFING, "BossesGriefing", 0).set(ChaosPersists.BossesGriefing);
        config.get(CAT_GRIEFING, "CrittersGriefing", 0).set(ChaosPersists.CrittersGriefing);
        config.get(CAT_GRIEFING, "OtherMobsGriefing", 0).set(ChaosPersists.OtherMobsGriefing);
        for (Map.Entry<String, Boolean> entry : STRUCTURE_ENABLE.entrySet()) {
            config.get(CAT_STRUCTURES, entry.getKey(), 1).set(entry.getValue() ? 1 : 0);
        }
        for (ChaosMobToggles.Toggle toggle : ChaosMobToggles.ALL) {
            config.get(ChaosMobToggles.CAT_MOBS, toggle.configKey(), toggle.defaultValue())
                    .set(toggle.getter().getAsInt());
        }
        for (ChaosNumericStats.Field field : ChaosNumericStats.ALL) {
            config.get(field.cfgCategory(), field.cfgKey(), field.cfgDefault()).set(field.getter().getAsInt());
        }
    }

    public static void saveFromGui() {
        Configuration config = new Configuration(configFile());
        config.load();
        writeCurrentValues(config);
        config.save();
    }

    private static int clampGrief(int value) {
        if (value < 0) {
            return 0;
        }
        if (value > 2) {
            return 2;
        }
        return value;
    }

    public static boolean overworldFeaturesDisabled() {
        return ChaosPersists.DisableOverworldFeatures != 0;
    }

    public static boolean allowOverworldWorldGen() {
        return !overworldFeaturesDisabled();
    }

    public static boolean allowOverworldSpawns() {
        return !overworldFeaturesDisabled() && ChaosPersists.DisableOverworldNaturalSpawning == 0;
    }

    public static boolean allowOverworldDungeons() {
        return !overworldFeaturesDisabled() && ChaosPersists.DisableOverworldDungeons == 0;
    }

    public static boolean allowOverworldDriedOre() {
        return !overworldFeaturesDisabled() && ChaosPersists.DisableOverworldDriedOre == 0;
    }

    public static boolean allowOverworldDriedEggs() {
        return !overworldFeaturesDisabled() && ChaosPersists.DisableOverworldDriedEggs == 0;
    }

    public static boolean allowOverworldAntNests() {
        return !overworldFeaturesDisabled() && ChaosPersists.DisableOverworldAntNests == 0;
    }

    public static boolean isOverworld(@Nullable Level level) {
        return level != null && level.dimension() == Level.OVERWORLD;
    }

    public static boolean isChaosEntity(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        ResourceLocation key = EntityType.getKey(entity.getType());
        return key != null && ChaosPersists.MODID.equals(key.getNamespace());
    }

    public static boolean isChaosEntityType(@Nullable EntityType<?> type) {
        if (type == null) {
            return false;
        }
        ResourceLocation key = EntityType.getKey(type);
        return key != null && ChaosPersists.MODID.equals(key.getNamespace());
    }

    public static boolean shouldBlockOverworldNaturalSpawn(
            @Nullable EntityType<?> type, @Nullable Level level, @Nullable MobSpawnType spawnType) {
        if (!isOverworld(level) || !isChaosEntityType(type) || allowOverworldSpawns()) {
            return false;
        }
        if (spawnType == null) {
            return true;
        }
        return spawnType == MobSpawnType.NATURAL
                || spawnType == MobSpawnType.CHUNK_GENERATION
                || spawnType == MobSpawnType.REINFORCEMENT
                || spawnType == MobSpawnType.PATROL;
    }

    public static boolean isOverworldBiome(@Nullable Holder<Biome> biome) {
        if (biome == null) {
            return false;
        }
        if (LegacyBiomeMatcher.isModBiome(biome)) {
            return false;
        }
        return !biome.is(BiomeTags.IS_NETHER) && !biome.is(BiomeTags.IS_END);
    }

    public static boolean isStructureEnabled(String id) {
        if (id == null || id.isEmpty()) {
            return true;
        }
        Boolean enabled = STRUCTURE_ENABLE.get(id);
        return enabled == null || enabled;
    }

    public static void setStructureEnabled(String id, boolean enabled) {
        STRUCTURE_ENABLE.put(id, enabled);
    }

    public static void setAllStructuresEnabled(boolean enabled) {
        for (String id : STRUCTURE_IDS) {
            STRUCTURE_ENABLE.put(id, enabled);
        }
    }

    public static void setStructuresEnabled(Iterable<String> ids, boolean enabled) {
        for (String id : ids) {
            STRUCTURE_ENABLE.put(id, enabled);
        }
    }

    public static boolean shouldGenerateStructure(
            Structure structure, Structure.GenerationContext context) {
        ResourceLocation key =
                context.registryAccess().registryOrThrow(Registries.STRUCTURE).getKey(structure);
        if (key != null && ChaosPersists.MODID.equals(key.getNamespace()) && !isStructureEnabled(key.getPath())) {
            return false;
        }
        if (!overworldFeaturesDisabled()) {
            return true;
        }
        net.minecraft.world.level.ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getMiddleBlockX();
        int z = chunkPos.getMiddleBlockZ();
        int y = context.chunkGenerator().getSeaLevel();
        Holder<Biome> biome =
                context.chunkGenerator()
                        .getBiomeSource()
                        .getNoiseBiome(
                                net.minecraft.core.QuartPos.fromBlock(x),
                                net.minecraft.core.QuartPos.fromBlock(y),
                                net.minecraft.core.QuartPos.fromBlock(z),
                                context.randomState().sampler());
        return !isOverworldBiome(biome);
    }
}
