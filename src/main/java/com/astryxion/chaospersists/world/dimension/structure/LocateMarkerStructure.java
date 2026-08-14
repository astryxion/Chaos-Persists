package com.astryxion.chaospersists.world.dimension.structure;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

/**
 * Datapack structure used only as a locate/slot marker. Places no blocks.
 */
public class LocateMarkerStructure extends Structure {

    public static final Codec<LocateMarkerStructure> CODEC = simpleCodec(LocateMarkerStructure::new);

    public LocateMarkerStructure(Structure.StructureSettings settings) {
        super(settings);
    }

    @Override
    protected Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        BlockPos pos = findLandPos(context);
        if (pos == null) {
            return Optional.empty();
        }
        Holder<Biome> biome =
                context.chunkGenerator()
                        .getBiomeSource()
                        .getNoiseBiome(
                                QuartPos.fromBlock(pos.getX()),
                                QuartPos.fromBlock(pos.getY()),
                                QuartPos.fromBlock(pos.getZ()),
                                context.randomState().sampler());
        if (!context.validBiome().test(biome)) {
            return Optional.empty();
        }
        if (skipOverworldHillsOverlap(context, biome)) {
            return Optional.empty();
        }
        if (skipWrongEnderKnightSet(context)) {
            return Optional.empty();
        }
        return Optional.of(
                new Structure.GenerationStub(
                        pos, (StructurePiecesBuilder builder) -> builder.addPiece(new LocateMarkerPiece(pos))));
    }

    /**
     * {@code /locate} teleports to this stub. Chunk-center height in The End is often 0 (void)
     * even when the chunk still has a scrap of island, so pick a column that actually has land.
     */
    private static BlockPos findLandPos(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        int min = context.heightAccessor().getMinBuildHeight();
        int bestX = chunkPos.getMiddleBlockX();
        int bestZ = chunkPos.getMiddleBlockZ();
        int bestY = occupiedHeight(context, bestX, bestZ);
        if (bestY > min) {
            return new BlockPos(bestX, bestY, bestZ);
        }
        for (int dx = 0; dx < 16; dx += 4) {
            for (int dz = 0; dz < 16; dz += 4) {
                int x = chunkPos.getMinBlockX() + dx;
                int z = chunkPos.getMinBlockZ() + dz;
                int y = occupiedHeight(context, x, z);
                if (y > min) {
                    return new BlockPos(x, y, z);
                }
            }
        }
        return null;
    }

    private static int occupiedHeight(Structure.GenerationContext context, int x, int z) {
        return context.chunkGenerator()
                .getFirstOccupiedHeight(
                        x,
                        z,
                        Heightmap.Types.WORLD_SURFACE_WG,
                        context.heightAccessor(),
                        context.randomState());
    }

    /**
     * Mining uses windswept hills. Structures that also spawn in other biomes must not create
     * overworld-hills markers that Java will never build.
     */
    private boolean skipOverworldHillsOverlap(Structure.GenerationContext context, Holder<Biome> biome) {
        if (!biome.is(Biomes.WINDSWEPT_HILLS)) {
            return false;
        }
        if (context.biomeSource().possibleBiomes().size() <= 1) {
            return false;
        }
        for (Holder<Biome> allowed : this.biomes()) {
            if (!allowed.is(Biomes.WINDSWEPT_HILLS)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@code ender_knight_dungeon} lives in both mining (spacing 26) and The End (spacing 10).
     * Vanilla will try both sets; keep only the set for this dimension.
     */
    private boolean skipWrongEnderKnightSet(Structure.GenerationContext context) {
        boolean allowsEnd = false;
        boolean allowsHills = false;
        for (Holder<Biome> allowed : this.biomes()) {
            if (allowed.is(Biomes.WINDSWEPT_HILLS)) {
                allowsHills = true;
            }
            if (allowed.is(BiomeTags.IS_END)) {
                allowsEnd = true;
            }
        }
        if (!allowsEnd || !allowsHills) {
            return false;
        }
        boolean miningDimension = context.biomeSource().possibleBiomes().size() <= 1;
        RandomSpreadStructurePlacement placement =
                miningDimension
                        ? new RandomSpreadStructurePlacement(
                                ChaosLocateStructures.MINING_ENDER_KNIGHT_SPACING,
                                ChaosLocateStructures.MINING_ENDER_KNIGHT_SEPARATION,
                                RandomSpreadType.LINEAR,
                                ChaosLocateStructures.MINING_ENDER_KNIGHT_SALT)
                        : new RandomSpreadStructurePlacement(
                                ChaosLocateStructures.END_ENDER_KNIGHT_SPACING,
                                ChaosLocateStructures.END_ENDER_KNIGHT_SEPARATION,
                                RandomSpreadType.LINEAR,
                                ChaosLocateStructures.END_ENDER_KNIGHT_SALT);
        ChunkPos chunkPos = context.chunkPos();
        ChunkPos potential =
                placement.getPotentialStructureChunk(context.seed(), chunkPos.x, chunkPos.z);
        return potential.x != chunkPos.x || potential.z != chunkPos.z;
    }

    @Override
    public StructureType<?> type() {
        return ChaosLocateStructures.LOCATE_MARKER.get();
    }
}
