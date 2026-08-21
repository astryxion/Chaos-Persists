package com.astryxion.chaospersists.world.dimension.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawJunction;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

/**
 * Vanilla jigsaw villages, then 1.12 {@code StructureVillagePieces} ground snap: each rigid
 * house/farm/pen moves to the average surface under its own footprint. Terrain-matching
 * streets already follow the heightmap and are left alone.
 */
public class GroundedJigsawStructure extends Structure {

    public static final Codec<GroundedJigsawStructure> CODEC =
            RecordCodecBuilder.create(
                    instance ->
                            instance.group(
                                            settingsCodec(instance),
                                            StructureTemplatePool.CODEC
                                                    .fieldOf("start_pool")
                                                    .forGetter(structure -> structure.startPool),
                                            ResourceLocation.CODEC
                                                    .optionalFieldOf("start_jigsaw_name")
                                                    .forGetter(structure -> structure.startJigsawName),
                                            Codec.intRange(0, 7)
                                                    .fieldOf("size")
                                                    .forGetter(structure -> structure.maxDepth),
                                            HeightProvider.CODEC
                                                    .fieldOf("start_height")
                                                    .forGetter(structure -> structure.startHeight),
                                            Codec.BOOL
                                                    .fieldOf("use_expansion_hack")
                                                    .forGetter(structure -> structure.useExpansionHack),
                                            Heightmap.Types.CODEC
                                                    .optionalFieldOf("project_start_to_heightmap")
                                                    .forGetter(structure -> structure.projectStartToHeightmap),
                                            Codec.intRange(1, 128)
                                                    .fieldOf("max_distance_from_center")
                                                    .forGetter(structure -> structure.maxDistanceFromCenter))
                                    .apply(instance, GroundedJigsawStructure::new));

    /** Ignore ravine/cave columns more than this many blocks below the piece's high point. */
    private static final int RAVINE_IGNORE_DEPTH = 8;

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int maxDepth;
    private final HeightProvider startHeight;
    private final boolean useExpansionHack;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;

    public GroundedJigsawStructure(
            Structure.StructureSettings settings,
            Holder<StructureTemplatePool> startPool,
            Optional<ResourceLocation> startJigsawName,
            int maxDepth,
            HeightProvider startHeight,
            boolean useExpansionHack,
            Optional<Heightmap.Types> projectStartToHeightmap,
            int maxDistanceFromCenter) {
        super(settings);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.maxDepth = maxDepth;
        this.startHeight = startHeight;
        this.useExpansionHack = useExpansionHack;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        int height =
                this.startHeight.sample(
                        context.random(),
                        new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
        BlockPos startPos = new BlockPos(chunkPos.getMinBlockX(), height, chunkPos.getMinBlockZ());
        Optional<Structure.GenerationStub> stub =
                JigsawPlacement.addPieces(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.maxDepth,
                        startPos,
                        this.useExpansionHack,
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter);
        return stub.map(
                original ->
                        new Structure.GenerationStub(
                                original.position(),
                                builder -> {
                                    original.generator()
                                            .ifLeft(generator -> generator.accept(builder))
                                            .ifRight(
                                                    existing -> {
                                                        for (StructurePiece piece :
                                                                existing.build().pieces()) {
                                                            builder.addPiece(piece);
                                                        }
                                                    });
                                    snapRigidPiecesToGround(builder, context);
                                }));
    }

    private static void snapRigidPiecesToGround(
            StructurePiecesBuilder builder, Structure.GenerationContext context) {
        for (StructurePiece piece : builder.build().pieces()) {
            if (!(piece instanceof PoolElementStructurePiece poolPiece)) {
                continue;
            }
            if (poolPiece.getElement().getProjection()
                    != StructureTemplatePool.Projection.RIGID) {
                continue;
            }
            int groundY = averageGroundY(context, poolPiece.getBoundingBox());
            if (groundY == Integer.MIN_VALUE) {
                continue;
            }
            int floorY = poolPiece.getBoundingBox().minY() + poolPiece.getGroundLevelDelta();
            int deltaY = groundY - floorY;
            if (deltaY == 0) {
                continue;
            }
            poolPiece.move(0, deltaY, 0);
            offsetJunctions(poolPiece, deltaY);
        }
    }

    /**
     * 1.12 {@code getAverageGroundLevel}: mean surface under the piece. Deep outliers (ravines)
     * are skipped so a house on a cliff stays on the cliff instead of dropping into the gap.
     */
    private static int averageGroundY(Structure.GenerationContext context, BoundingBox box) {
        int max = Integer.MIN_VALUE;
        List<Integer> samples = new ArrayList<>();
        for (int x = box.minX(); x <= box.maxX(); x += 2) {
            for (int z = box.minZ(); z <= box.maxZ(); z += 2) {
                int y =
                        context.chunkGenerator()
                                .getBaseHeight(
                                        x,
                                        z,
                                        Heightmap.Types.WORLD_SURFACE_WG,
                                        context.heightAccessor(),
                                        context.randomState());
                samples.add(y);
                if (y > max) {
                    max = y;
                }
            }
        }
        if (samples.isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int floor = max - RAVINE_IGNORE_DEPTH;
        int sum = 0;
        int count = 0;
        for (int y : samples) {
            if (y >= floor) {
                sum += y;
                count++;
            }
        }
        if (count == 0) {
            return max;
        }
        return Math.round(sum / (float) count);
    }

    private static void offsetJunctions(PoolElementStructurePiece piece, int deltaY) {
        List<JigsawJunction> old = new ArrayList<>(piece.getJunctions());
        if (old.isEmpty()) {
            return;
        }
        piece.getJunctions().clear();
        for (JigsawJunction junction : old) {
            piece.addJunction(
                    new JigsawJunction(
                            junction.getSourceX(),
                            junction.getSourceGroundY() + deltaY,
                            junction.getSourceZ(),
                            junction.getDeltaY(),
                            junction.getDestProjection()));
        }
    }

    @Override
    public StructureType<?> type() {
        return ChaosLocateStructures.GROUNDED_JIGSAW.get();
    }
}
