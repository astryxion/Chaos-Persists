package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Seed+chunk slots for OreSpawn Java structures so {@code /locate structure} can find them
 * before the chunk exists. Buildings are still placed by {@code GenericDungeon}.
 */
public final class ChaosLocateStructures {

    private ChaosLocateStructures() {}

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, ChaosPersists.MODID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, ChaosPersists.MODID);

    public static final RegistryObject<StructureType<LocateMarkerStructure>> LOCATE_MARKER =
            STRUCTURE_TYPES.register("locate_marker", () -> () -> LocateMarkerStructure.CODEC);
    public static final RegistryObject<StructureType<GroundedJigsawStructure>> GROUNDED_JIGSAW =
            STRUCTURE_TYPES.register("grounded_jigsaw", () -> () -> GroundedJigsawStructure.CODEC);
    public static final RegistryObject<StructurePieceType> LOCATE_MARKER_PIECE =
            STRUCTURE_PIECES.register(
                    "locate_marker", () -> (StructurePieceType.ContextlessType) LocateMarkerPiece::new);

    public static final ResourceKey<Structure> ROBOT_LAB = key("robot_lab");
    public static final ResourceKey<Structure> GREENHOUSE = key("greenhouse");
    public static final ResourceKey<Structure> INCA_PYRAMID = key("inca_pyramid");
    public static final ResourceKey<Structure> CEPHADROME_ALTAR = key("cephadrome_altar");
    public static final ResourceKey<Structure> NIGHTMARE_ROOKERY = key("nightmare_rookery");
    public static final ResourceKey<Structure> STINKY_HOUSE = key("stinky_house");
    public static final ResourceKey<Structure> WHITE_HOUSE = key("white_house");
    public static final ResourceKey<Structure> MINI_DUNGEON = key("mini_dungeon");
    public static final ResourceKey<Structure> PUMPKIN = key("pumpkin");
    public static final ResourceKey<Structure> RAINBOW = key("rainbow");
    public static final ResourceKey<Structure> CLOUD_SHARK = key("cloud_shark");
    public static final ResourceKey<Structure> ENORMOUS_CASTLE = key("enormous_castle");
    public static final ResourceKey<Structure> DANGER_DUNGEON = key("danger_dungeon");
    public static final ResourceKey<Structure> DANGER_ENDER_CASTLE = key("danger_ender_castle");
    public static final ResourceKey<Structure> DANGER_RUBY_DUNGEON = key("danger_ruby_dungeon");

    public static final ResourceKey<Structure> BASILISK_MAZE = key("basilisk_maze");
    public static final ResourceKey<Structure> KYUUBI_DUNGEON = key("kyuubi_dungeon");
    public static final ResourceKey<Structure> BEE_HIVE = key("bee_hive");
    public static final ResourceKey<Structure> BEE_NEST = key("bee_nest");
    public static final ResourceKey<Structure> NIGHTMARE_DUNGEON = key("nightmare_dungeon");
    public static final ResourceKey<Structure> ALIEN_WTF_DUNGEON = key("alien_wtf_dungeon");
    public static final ResourceKey<Structure> LEON_NEST = key("leon_nest");
    /** Salt from {@code structure_set/mining_ender_knight.json} (mining slots for ender knight). */
    public static final int MINING_ENDER_KNIGHT_SALT = 1548293760;
    public static final int MINING_ENDER_KNIGHT_SPACING = 26;
    public static final int MINING_ENDER_KNIGHT_SEPARATION = 6;
    /** Salt from {@code structure_set/ender_knight_dungeon.json} (The End). */
    public static final int END_ENDER_KNIGHT_SALT = 660497141;
    public static final int END_ENDER_KNIGHT_SPACING = 10;
    public static final int END_ENDER_KNIGHT_SEPARATION = 2;
    public static final ResourceKey<Structure> DUNGEON = key("dungeon");

    public static final ResourceKey<Structure> RUBY_DUNGEON = key("ruby_dungeon");
    public static final ResourceKey<Structure> KING_ALTAR = key("king_altar");
    public static final ResourceKey<Structure> QUEEN_ALTAR = key("queen_altar");
    public static final ResourceKey<Structure> KING_TREE = key("king_tree");
    public static final ResourceKey<Structure> QUEEN_TREE = key("queen_tree");

    public static final ResourceKey<Structure> DAMSEL_IN_DISTRESS = key("damsel_in_distress");
    public static final ResourceKey<Structure> SPIDER_HANGOUT = key("spider_hangout");
    public static final ResourceKey<Structure> RED_ANT_HANGOUT = key("red_ant_hangout");

    public static final ResourceKey<Structure> ROTATOR_STATION = key("rotator_station");
    public static final ResourceKey<Structure> ROUND_ROTATOR = key("round_rotator");
    public static final ResourceKey<Structure> URCHIN_SPAWNER = key("urchin_spawner");
    public static final ResourceKey<Structure> CRYSTAL_HAUNTED_HOUSE = key("crystal_haunted_house");
    public static final ResourceKey<Structure> CRYSTAL_BATTLE_TOWER = key("crystal_battle_tower");

    public static final ResourceKey<Structure> PLAY_POOL = key("play_pool");
    public static final ResourceKey<Structure> WATER_DRAGON_LAIR = key("water_dragon_lair");
    public static final ResourceKey<Structure> GOLDFISH_BOWL = key("goldfish_bowl");
    public static final ResourceKey<Structure> GIRLFRIEND_ISLAND = key("girlfriend_island");
    public static final ResourceKey<Structure> MONSTER_ISLAND = key("monster_island");
    public static final ResourceKey<Structure> FROG_POND = key("frog_pond");
    public static final ResourceKey<Structure> NEST = key("nest");
    public static final ResourceKey<Structure> HAUNTED_HOUSE = key("haunted_house");
    public static final ResourceKey<Structure> LEAF_MONSTER = key("leaf_monster");
    public static final ResourceKey<Structure> SPIT_BUG = key("spit_bug");
    public static final ResourceKey<Structure> IGLOO = key("igloo");
    public static final ResourceKey<Structure> BOUNCY_CASTLE = key("bouncy_castle");
    public static final ResourceKey<Structure> RUBBER_DUCKY_POND = key("rubber_ducky_pond");

    public static final ResourceKey<Structure> ENDER_KNIGHT_DUNGEON = key("ender_knight_dungeon");
    public static final ResourceKey<Structure> ENDER_REAPER_GRAVEYARD = key("ender_reaper_graveyard");
    public static final ResourceKey<Structure> ENDER_DRAGON_HOSPITAL = key("ender_dragon_hospital");
    public static final ResourceKey<Structure> ENDER_CASTLE = key("ender_castle");

    public static List<ResourceKey<Structure>> allLocateKeys() {
        return List.of(
                PLAY_POOL,
                RUBBER_DUCKY_POND,
                FROG_POND,
                LEAF_MONSTER,
                GOLDFISH_BOWL,
                WATER_DRAGON_LAIR,
                GIRLFRIEND_ISLAND,
                MONSTER_ISLAND,
                NEST,
                BEE_HIVE,
                BEE_NEST,
                HAUNTED_HOUSE,
                SPIT_BUG,
                IGLOO,
                BOUNCY_CASTLE,
                KING_ALTAR,
                QUEEN_ALTAR,
                KING_TREE,
                QUEEN_TREE,
                DAMSEL_IN_DISTRESS,
                SPIDER_HANGOUT,
                RED_ANT_HANGOUT,
                BASILISK_MAZE,
                KYUUBI_DUNGEON,
                NIGHTMARE_DUNGEON,
                ALIEN_WTF_DUNGEON,
                ENDER_KNIGHT_DUNGEON,
                LEON_NEST,
                ROBOT_LAB,
                GREENHOUSE,
                INCA_PYRAMID,
                CEPHADROME_ALTAR,
                NIGHTMARE_ROOKERY,
                STINKY_HOUSE,
                WHITE_HOUSE,
                MINI_DUNGEON,
                PUMPKIN,
                RAINBOW,
                CLOUD_SHARK,
                ENORMOUS_CASTLE,
                DANGER_DUNGEON,
                DANGER_ENDER_CASTLE,
                DANGER_RUBY_DUNGEON,
                ROTATOR_STATION,
                ROUND_ROTATOR,
                URCHIN_SPAWNER,
                CRYSTAL_HAUNTED_HOUSE,
                CRYSTAL_BATTLE_TOWER,
                ENDER_REAPER_GRAVEYARD,
                ENDER_DRAGON_HOSPITAL,
                ENDER_CASTLE);
    }

    public static ResourceKey<Structure> key(String path) {
        return ResourceKey.create(
                Registries.STRUCTURE, new ResourceLocation(ChaosPersists.MODID, path));
    }

    /** True when this chunk is the seed slot for {@code key} in the current dimension. */
    public static boolean isChunk(Level level, int chunkX, int chunkZ, ResourceKey<Structure> key) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return false;
        }
        Holder<Structure> holder =
                serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).getHolder(key).orElse(null);
        if (holder == null) {
            return false;
        }
        if (!isAllowedDimension(serverLevel, key)) {
            return false;
        }
        if (!biomeMatches(serverLevel, chunkX, chunkZ, holder.value())) {
            return false;
        }
        var state = serverLevel.getChunkSource().getGeneratorState();
        for (StructurePlacement placement : placementsFor(serverLevel, state, holder, key)) {
            if (placement.isStructureChunk(state, chunkX, chunkZ)) {
                return true;
            }
        }
        return false;
    }

    private static List<StructurePlacement> placementsFor(
            ServerLevel level,
            ChunkGeneratorStructureState state,
            Holder<Structure> holder,
            ResourceKey<Structure> key) {
        List<StructurePlacement> found = new ArrayList<>();
        for (StructurePlacement placement : state.getPlacementsForStructure(holder)) {
            found.add(placement);
        }
        if (found.isEmpty()) {
            var sets = level.registryAccess().registryOrThrow(Registries.STRUCTURE_SET);
            var set = sets.get(ResourceKey.create(Registries.STRUCTURE_SET, key.location()));
            if (set != null) {
                found.add(set.placement());
            }
        }
        return filterPlacementsForDimension(level, key, found);
    }

    /**
     * {@code ender_knight_dungeon} is in two structure sets (mining 26/6 and End 10/2). Only the
     * set for the current dimension should count as a slot.
     */
    private static List<StructurePlacement> filterPlacementsForDimension(
            ServerLevel level, ResourceKey<Structure> key, List<StructurePlacement> found) {
        if (!ENDER_KNIGHT_DUNGEON.equals(key) || found.size() <= 1) {
            return found;
        }
        ResourceLocation setId;
        if (level.dimension().equals(ChaosPersists.getMiningDimensionKey())) {
            setId = new ResourceLocation(ChaosPersists.MODID, "mining_ender_knight");
        } else if (Level.END.equals(level.dimension())) {
            setId = new ResourceLocation(ChaosPersists.MODID, "ender_knight_dungeon");
        } else {
            return found;
        }
        StructureSet set =
                level.registryAccess()
                        .registryOrThrow(Registries.STRUCTURE_SET)
                        .get(ResourceKey.create(Registries.STRUCTURE_SET, setId));
        if (set == null) {
            return found;
        }
        return List.of(set.placement());
    }

    /** {@code blockX}/{@code blockZ} are chunk-origin block coords ({@code chunk * 16}). */
    public static boolean isBlockChunk(Level level, int blockX, int blockZ, ResourceKey<Structure> key) {
        return isChunk(level, blockX >> 4, blockZ >> 4, key);
    }

    private static boolean biomeMatches(ServerLevel level, int chunkX, int chunkZ, Structure structure) {
        int x = (chunkX << 4) + 8;
        int z = (chunkZ << 4) + 8;
        int y =
                Mth.clamp(
                        level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z),
                        level.getMinBuildHeight(),
                        level.getMaxBuildHeight() - 1);
        Holder<Biome> biome = level.getBiome(new BlockPos(x, y, z));
        return structure.biomes().contains(biome);
    }

    private static boolean isAllowedDimension(ServerLevel level, ResourceKey<Structure> key) {
        return isAllowedDimension(level.dimension(), key);
    }

    public static boolean isAllowedDimension(ResourceKey<Level> dim, ResourceKey<Structure> key) {
        List<ResourceKey<Level>> dims = dimensionsOf(key);
        if (dims.isEmpty()) {
            return true;
        }
        return dims.contains(dim);
    }

    /** Display names for {@code /locate} when the player is in the wrong dimension. */
    public static String dimensionDisplayNames(ResourceKey<Structure> key) {
        List<String> names = new ArrayList<>();
        for (ResourceKey<Level> dim : dimensionsOf(key)) {
            names.add(dimensionDisplayName(dim));
        }
        return String.join(", ", names);
    }

    public static String dimensionDisplayName(ResourceKey<Level> dim) {
        if (Level.OVERWORLD.equals(dim)) {
            return "Overworld";
        }
        if (Level.END.equals(dim)) {
            return "The End";
        }
        if (dim.equals(ChaosPersists.getUtopiaDimensionKey())) {
            return "Utopia";
        }
        if (dim.equals(ChaosPersists.getMiningDimensionKey())) {
            return "Mining";
        }
        if (dim.equals(ChaosPersists.getVillageDimensionKey())) {
            return "Village";
        }
        if (dim.equals(ChaosPersists.getDangerDimensionKey())) {
            return "Danger";
        }
        if (dim.equals(ChaosPersists.getCrystalDimensionKey())) {
            return "Crystal";
        }
        return dim.location().getPath();
    }

    static List<ResourceKey<Level>> dimensionsOf(ResourceKey<Structure> key) {
        if (KING_ALTAR.equals(key)
                || QUEEN_ALTAR.equals(key)
                || KING_TREE.equals(key)
                || QUEEN_TREE.equals(key)
                || RUBY_DUNGEON.equals(key)) {
            return List.of(ChaosPersists.getUtopiaDimensionKey());
        }
        if (BEE_HIVE.equals(key)) {
            return List.of(Level.OVERWORLD);
        }
        if (isMiningOnly(key)) {
            return List.of(ChaosPersists.getMiningDimensionKey());
        }
        if (DUNGEON.equals(key)) {
            return List.of(
                    ChaosPersists.getUtopiaDimensionKey(),
                    ChaosPersists.getVillageDimensionKey(),
                    ChaosPersists.getMiningDimensionKey());
        }
        if (DAMSEL_IN_DISTRESS.equals(key) || SPIDER_HANGOUT.equals(key) || RED_ANT_HANGOUT.equals(key)) {
            return List.of(ChaosPersists.getVillageDimensionKey());
        }
        if (ROTATOR_STATION.equals(key)
                || ROUND_ROTATOR.equals(key)
                || URCHIN_SPAWNER.equals(key)
                || CRYSTAL_HAUNTED_HOUSE.equals(key)
                || CRYSTAL_BATTLE_TOWER.equals(key)) {
            return List.of(ChaosPersists.getCrystalDimensionKey());
        }
        if (ENDER_KNIGHT_DUNGEON.equals(key)) {
            return List.of(ChaosPersists.getMiningDimensionKey(), Level.END);
        }
        if (ENDER_REAPER_GRAVEYARD.equals(key)
                || ENDER_DRAGON_HOSPITAL.equals(key)
                || ENDER_CASTLE.equals(key)) {
            return List.of(Level.END);
        }
        if (ROBOT_LAB.equals(key)
                || GREENHOUSE.equals(key)
                || INCA_PYRAMID.equals(key)
                || CEPHADROME_ALTAR.equals(key)
                || NIGHTMARE_ROOKERY.equals(key)
                || STINKY_HOUSE.equals(key)
                || WHITE_HOUSE.equals(key)
                || MINI_DUNGEON.equals(key)
                || PUMPKIN.equals(key)
                || RAINBOW.equals(key)
                || CLOUD_SHARK.equals(key)
                || ENORMOUS_CASTLE.equals(key)
                || DANGER_DUNGEON.equals(key)
                || DANGER_ENDER_CASTLE.equals(key)
                || DANGER_RUBY_DUNGEON.equals(key)) {
            return List.of(ChaosPersists.getDangerDimensionKey());
        }
        if (PLAY_POOL.equals(key)
                || WATER_DRAGON_LAIR.equals(key)
                || GOLDFISH_BOWL.equals(key)
                || GIRLFRIEND_ISLAND.equals(key)
                || MONSTER_ISLAND.equals(key)
                || FROG_POND.equals(key)
                || NEST.equals(key)
                || HAUNTED_HOUSE.equals(key)
                || LEAF_MONSTER.equals(key)
                || SPIT_BUG.equals(key)
                || IGLOO.equals(key)
                || BOUNCY_CASTLE.equals(key)
                || RUBBER_DUCKY_POND.equals(key)) {
            return List.of(Level.OVERWORLD);
        }
        return List.of();
    }

    private static boolean isMiningOnly(ResourceKey<Structure> key) {
        return BASILISK_MAZE.equals(key)
                || KYUUBI_DUNGEON.equals(key)
                || BEE_NEST.equals(key)
                || NIGHTMARE_DUNGEON.equals(key)
                || ALIEN_WTF_DUNGEON.equals(key)
                || LEON_NEST.equals(key);
    }
}
