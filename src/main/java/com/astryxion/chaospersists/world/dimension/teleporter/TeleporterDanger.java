package com.astryxion.chaospersists.world.dimension.teleporter;

import java.util.function.Function;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

/**
 * Teleporter for /danger command. Danger dimension is superflat with ground at Y=8.
 */
public class TeleporterDanger implements ITeleporter {
    private final double targetX;
    private final double targetZ;
    private static final double TARGET_Y = 8.0;

    public TeleporterDanger(ServerWorld worldIn, double targetX, double targetZ) {
        this.targetX = targetX;
        this.targetZ = targetZ;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity placed = repositionEntity.apply(false);
        placed.moveTo(this.targetX, TARGET_Y, this.targetZ, yaw, placed.xRot);
        placed.setDeltaMovement(Vector3d.ZERO);
        return placed;
    }
}
