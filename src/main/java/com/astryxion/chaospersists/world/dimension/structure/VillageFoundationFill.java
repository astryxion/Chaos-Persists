package com.astryxion.chaospersists.world.dimension.structure;

import it.unimi.dsi.fastutil.longs.LongIterator;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

/**
 * 1.12 village foundation pass: after houses/farms/pens place, replace air and water under them
 * with dirt down to the real ground. 1.7 {@code StructureVillagePieces.func_151554_b} did the same
 * through lakes so waterfront buildings stayed connected.
 */
public final class VillageFoundationFill {

    private static final int MAX_FILL_DEPTH = 32;

    private VillageFoundationFill() {}

    public static void fillChunk(WorldGenLevel level, ChunkPos chunkPos) {
        ChunkAccess chunk = level.getChunk(chunkPos.x, chunkPos.z);
        for (var entry : chunk.getAllReferences().entrySet()) {
            Structure structure = entry.getKey();
            if (!(structure instanceof GroundedJigsawStructure)) {
                continue;
            }
            LongIterator starts = entry.getValue().iterator();
            while (starts.hasNext()) {
                ChunkPos startPos = new ChunkPos(starts.nextLong());
                ChunkAccess startChunk = level.getChunk(startPos.x, startPos.z, ChunkStatus.STRUCTURE_STARTS, false);
                if (startChunk == null) {
                    continue;
                }
                StructureStart start = startChunk.getStartForStructure(structure);
                if (start == null || !start.isValid()) {
                    continue;
                }
                for (StructurePiece piece : start.getPieces()) {
                    fillPiece(level, piece, chunkPos);
                }
            }
        }
    }

    public static void fillChunk(Level level, int chunkX, int chunkZ) {
        if (level instanceof WorldGenLevel gen) {
            fillChunk(gen, new ChunkPos(chunkX, chunkZ));
        }
    }

    private static void fillPiece(WorldGenLevel level, StructurePiece piece, ChunkPos chunkPos) {
        if (!(piece instanceof PoolElementStructurePiece poolPiece)) {
            return;
        }
        if (poolPiece.getElement().getProjection() != StructureTemplatePool.Projection.RIGID) {
            return;
        }
        if (isDecorOrTreePiece(poolPiece)) {
            return;
        }
        BoundingBox box = poolPiece.getBoundingBox();
        int minX = Math.max(box.minX(), chunkPos.getMinBlockX());
        int maxX = Math.min(box.maxX(), chunkPos.getMaxBlockX());
        int minZ = Math.max(box.minZ(), chunkPos.getMinBlockZ());
        int maxZ = Math.min(box.maxZ(), chunkPos.getMaxBlockZ());
        if (minX > maxX || minZ > maxZ) {
            return;
        }
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                int floorY = findBuildingFloorY(level, box, poolPiece.getGroundLevelDelta(), x, z, cursor);
                if (floorY == Integer.MIN_VALUE) {
                    continue;
                }
                fillColumn(level, x, z, floorY - 1, cursor);
            }
        }
    }

    /**
     * Only the real floor layer ({@code minY} / {@code minY+1}), not roof stairs, leaves, or lamp
     * posts a few blocks up.
     */
    private static int findBuildingFloorY(
            WorldGenLevel level,
            BoundingBox box,
            int groundLevelDelta,
            int x,
            int z,
            BlockPos.MutableBlockPos cursor) {
        int floorY = box.minY() + Math.max(0, groundLevelDelta);
        int limit = Math.min(floorY + 1, box.maxY());
        for (int y = box.minY(); y <= limit; y++) {
            BlockState state = level.getBlockState(cursor.set(x, y, z));
            if (isFloorBlock(level, cursor, state)) {
                return y;
            }
        }
        return Integer.MIN_VALUE;
    }

    /** Trees, lamps, and village decor are rigid but should not grow dirt stilts. */
    private static boolean isDecorOrTreePiece(PoolElementStructurePiece piece) {
        String name = piece.getElement().toString().toLowerCase();
        return name.contains("tree")
                || name.contains("lamp")
                || name.contains("/decor")
                || name.contains("\\decor");
    }

    private static void fillColumn(WorldGenLevel level, int x, int z, int startY, BlockPos.MutableBlockPos cursor) {
        int minY = Math.max(level.getMinBuildHeight(), startY - MAX_FILL_DEPTH + 1);
        for (int y = startY; y >= minY; y--) {
            BlockState state = level.getBlockState(cursor.set(x, y, z));
            if (shouldReplaceWithFoundation(state)) {
                level.setBlock(cursor, Blocks.DIRT.defaultBlockState(), 2);
                continue;
            }
            break;
        }
    }

    /** Air, water, and other fluids — 1.7 kept filling through lakes until it hit solid ground. */
    private static boolean shouldReplaceWithFoundation(BlockState state) {
        if (state.isAir() || !state.getFluidState().isEmpty()) {
            return true;
        }
        return state.canBeReplaced();
    }

    private static boolean isFloorBlock(WorldGenLevel level, BlockPos pos, BlockState state) {
        if (state.isAir() || state.canBeReplaced()) {
            return false;
        }
        if (state.is(Blocks.FARMLAND)
                || state.is(Blocks.DIRT)
                || state.is(Blocks.COARSE_DIRT)
                || state.is(Blocks.ROOTED_DIRT)) {
            return true;
        }
        if (state.is(BlockTags.STAIRS)
                || state.is(BlockTags.SLABS)
                || state.is(BlockTags.FENCES)
                || state.is(BlockTags.FENCE_GATES)
                || state.is(BlockTags.WALLS)
                || state.is(BlockTags.LEAVES)
                || state.is(BlockTags.DOORS)
                || state.is(BlockTags.TRAPDOORS)
                || state.is(BlockTags.BUTTONS)
                || state.is(Blocks.GLASS)
                || state.is(Blocks.GLASS_PANE)
                || state.is(Blocks.LANTERN)
                || state.is(Blocks.TORCH)
                || state.is(Blocks.WALL_TORCH)) {
            return false;
        }
        if (state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.PODZOL)
                || state.is(Blocks.MYCELIUM)
                || state.is(Blocks.STONE)
                || state.is(Blocks.DEEPSLATE)
                || state.is(Blocks.ANDESITE)
                || state.is(Blocks.DIORITE)
                || state.is(Blocks.GRANITE)
                || state.is(Blocks.TUFF)
                || state.is(Blocks.GRAVEL)
                || state.is(Blocks.SAND)) {
            return false;
        }
        return state.isCollisionShapeFullBlock(level, pos);
    }
}
