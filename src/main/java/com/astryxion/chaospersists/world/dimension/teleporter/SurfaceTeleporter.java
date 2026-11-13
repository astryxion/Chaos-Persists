package com.astryxion.chaospersists.world.dimension.teleporter;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;


/** Lands travelers on a standable surface in a Chaos Persists dimension. */
public class SurfaceTeleporter {
    private final double targetX;
    private final double targetZ;

    public SurfaceTeleporter(double targetX, double targetZ) {
        this.targetX = targetX;
        this.targetZ = targetZ;
    }

    public DimensionTransition transition(Entity entity, ServerLevel destWorld) {
        int blockX = (int) Math.floor(this.targetX);
        int blockZ = (int) Math.floor(this.targetZ);
        ChunkPos chunkPos = new ChunkPos(blockX >> 4, blockZ >> 4);
        destWorld.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkPos, 1, entity.getId());
        destWorld.getChunk(chunkPos.x, chunkPos.z);

        Vec3 spawn = OreSpawnSurfaceFinder.findSafeSpawn(destWorld, this.targetX, this.targetZ);
        return new DimensionTransition(destWorld, spawn, Vec3.ZERO,
                entity.getYRot(), entity.getXRot(), moved -> {
                    moved.fallDistance = 0.0F;
                    moved.setRemainingFireTicks(0);
                });
    }
}
