package com.astryxion.chaospersists.world.dimension.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;

/**
 * Empty structure piece so vanilla {@code /locate structure} can see a valid start. Java still
 * places the real OreSpawn building during chunk decoration.
 */
public class LocateMarkerPiece extends StructurePiece {

    public LocateMarkerPiece(BlockPos pos) {
        super(
                ChaosLocateStructures.LOCATE_MARKER_PIECE.get(),
                0,
                new BoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ()));
    }

    public LocateMarkerPiece(CompoundTag tag) {
        super(ChaosLocateStructures.LOCATE_MARKER_PIECE.get(), tag);
    }

    @Override
    protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag) {}

    @Override
    public void postProcess(
            WorldGenLevel level,
            StructureManager structureManager,
            ChunkGenerator generator,
            RandomSource random,
            BoundingBox box,
            ChunkPos chunkPos,
            BlockPos pivot) {}
}
