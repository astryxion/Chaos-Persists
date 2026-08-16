package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.entity.ThePrincess;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

/** Prince/princess follow helpers modeled on Mythic Mounts {@code MountFollowOwnerGoal}. */
public final class RoyalPetFollowHelper {

    private RoyalPetFollowHelper() {}

    public static boolean isStayingPut(TamableAnimal pet) {
        return pet.isOrderedToSit() || pet.isInSittingPose();
    }

    public static boolean isRoyalPet(TamableAnimal pet) {
        return pet instanceof ThePrince
                || pet instanceof ThePrincess
                || pet instanceof ThePrinceTeen
                || pet instanceof ThePrinceAdult;
    }

    /** Move tamed royal pets to the player's dimension when they change worlds. */
    public static void bringRoyalPetsToPlayer(ServerPlayer player) {
        if (player.level().isClientSide) {
            return;
        }
        UUID ownerId = player.getUUID();
        for (ServerLevel level : player.server.getAllLevels()) {
            List<TamableAnimal> pets = new ArrayList<>();
            for (Entity entity : level.getAllEntities()) {
                if (!(entity instanceof TamableAnimal pet)) {
                    continue;
                }
                if (!isRoyalPet(pet) || !pet.isTame() || !ownerId.equals(pet.getOwnerUUID())) {
                    continue;
                }
                if (isStayingPut(pet)) {
                    continue;
                }
                if (pet.getVehicle() == player) {
                    continue;
                }
                pets.add(pet);
            }
            for (TamableAnimal pet : pets) {
                teleportNearOwner(pet, player);
            }
        }
    }

    /**
     * Catch-up for loaded non-staying pets (girlfriends, water dragons, etc.). Places them on solid
     * ground near/under the owner — never midair — so creative flight does not kill them with fall damage.
     */
    public static void bringGroundFollowPetsToPlayer(ServerPlayer player) {
        if (player.level().isClientSide) {
            return;
        }
        UUID ownerId = player.getUUID();
        for (ServerLevel level : player.server.getAllLevels()) {
            List<TamableAnimal> pets = new ArrayList<>();
            for (Entity entity : level.getAllEntities()) {
                if (!(entity instanceof TamableAnimal pet)) {
                    continue;
                }
                if (!pet.isTame() || !ownerId.equals(pet.getOwnerUUID())) {
                    continue;
                }
                if (isRoyalPet(pet) || pet instanceof Dragon) {
                    continue;
                }
                if (isStayingPut(pet)) {
                    continue;
                }
                if (pet.getVehicle() == player || !pet.getPassengers().isEmpty()) {
                    continue;
                }
                boolean far =
                        pet.level() != player.level() || pet.distanceToSqr(player) >= 144.0;
                if (!far) {
                    continue;
                }
                pets.add(pet);
            }
            for (TamableAnimal pet : pets) {
                teleportToOwnerOnGround(pet, player);
            }
        }
    }

    /** Teleport only when the owner is in another dimension (does not affect in-flight movement). */
    public static void syncDimensionOnly(TamableAnimal pet) {
        if (pet.level().isClientSide || !pet.isTame() || isStayingPut(pet) || !isRoyalPet(pet)) {
            return;
        }
        if (!pet.getPassengers().isEmpty()) {
            return;
        }
        LivingEntity owner = pet.getOwner();
        if (owner == null || pet.level() == owner.level()) {
            return;
        }
        teleportNearOwner(pet, owner);
    }

    /**
     * Same-dimension follow catch-up onto solid ground (OreSpawn 1.7.10 style + surface under a flying owner).
     * Never warps pets to midair.
     */
    public static boolean tryFollowTeleport(TamableAnimal pet, LivingEntity owner) {
        if (owner == null || isStayingPut(pet)) {
            return false;
        }
        if (pet.distanceToSqr(owner) < 144.0 && pet.level() == owner.level()) {
            return false;
        }
        return teleportToOwnerOnGround(pet, owner);
    }

    /**
     * OreSpawn 1.7.10 follow teleport: stand on solid ground near the owner's feet. If the owner is
     * flying, use the surface under them so ground pets catch up without fall damage.
     */
    public static boolean teleportToOwnerOnGround(TamableAnimal pet, LivingEntity owner) {
        if (owner == null || isStayingPut(pet) || pet.isRemoved()) {
            return false;
        }
        if (!pet.getPassengers().isEmpty()) {
            return false;
        }
        if (!(owner.level() instanceof ServerLevel dest)) {
            return false;
        }

        BlockPos spot = findGroundSpot(dest, owner, pet);
        if (spot == null) {
            return false;
        }

        pet.stopRiding();
        double x = spot.getX() + 0.5;
        double y = spot.getY();
        double z = spot.getZ() + 0.5;
        boolean moved;
        if (pet.level() != dest) {
            moved = pet.teleportTo(dest, x, y, z, Set.of(), owner.getYRot(), pet.getXRot());
        } else {
            pet.moveTo(x, y, z, pet.getYRot(), pet.getXRot());
            moved = true;
        }
        if (moved) {
            pet.fallDistance = 0.0f;
            pet.setDeltaMovement(0.0, 0.0, 0.0);
            MyUtils.clearChaosFlight(pet);
            PathNavigation navigation = pet.getNavigation();
            if (navigation != null) {
                navigation.stop();
            }
        }
        return moved;
    }

    /** Find a standable block near/under the owner (feet first, then heightmap, then downward scan). */
    private static BlockPos findGroundSpot(Level level, LivingEntity owner, TamableAnimal pet) {
        int ownerX = Mth.floor(owner.getX());
        int ownerZ = Mth.floor(owner.getZ());
        int feetY = Mth.floor(owner.getBoundingBox().minY);

        BlockPos atFeet = findOreSpawnRingSpot(level, pet, ownerX, feetY, ownerZ);
        if (atFeet != null) {
            return atFeet;
        }

        int surfaceY = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ownerX, ownerZ);
        if (surfaceY > level.getMinBuildHeight()) {
            BlockPos atSurface = findOreSpawnRingSpot(level, pet, ownerX, surfaceY, ownerZ);
            if (atSurface != null) {
                return atSurface;
            }
        }

        int startY = Mth.floor(owner.getY());
        int minY = level.getMinBuildHeight() + 1;
        for (int y = startY; y >= minY && startY - y <= 384; --y) {
            BlockPos spot = findOreSpawnRingSpot(level, pet, ownerX, y, ownerZ);
            if (spot != null) {
                return spot;
            }
        }
        return null;
    }

    /**
     * OreSpawn 1.7.10 {@code MyEntityAIFollowOwner} ring search around (baseX, feetY, baseZ).
     * Outer 5x5 cells only (skips the inner 3x3).
     */
    private static BlockPos findOreSpawnRingSpot(
            Level level, TamableAnimal pet, int baseX, int feetY, int baseZ) {
        for (int dx = 0; dx <= 4; ++dx) {
            for (int dz = 0; dz <= 4; ++dz) {
                if (dx >= 1 && dz >= 1 && dx <= 3 && dz <= 3) {
                    continue;
                }
                BlockPos feet = new BlockPos(baseX - 2 + dx, feetY, baseZ - 2 + dz);
                if (canStandAt(pet, level, feet)) {
                    return feet;
                }
            }
        }
        return null;
    }

    public static boolean teleportNearOwner(TamableAnimal pet, LivingEntity owner) {
        if (!(owner.level() instanceof ServerLevel dest) || pet.isRemoved()) {
            return false;
        }
        if (!pet.getPassengers().isEmpty()) {
            return false;
        }
        pet.stopRiding();
        double x = owner.getX() + (pet.getRandom().nextDouble() - 0.5) * 2.0;
        double y = owner.getY() + 1.0;
        double z = owner.getZ() + (pet.getRandom().nextDouble() - 0.5) * 2.0;
        boolean moved = pet.teleportTo(dest, x, y, z, Set.of(), owner.getYRot(), pet.getXRot());
        if (moved) {
            MyUtils.clearChaosFlight(pet);
            PathNavigation navigation = pet.getNavigation();
            if (navigation != null) {
                navigation.stop();
            }
        }
        return moved;
    }

    private static boolean canStandAt(TamableAnimal pet, Level level, BlockPos pos) {
        BlockPos below = pos.below();
        BlockState ground = level.getBlockState(below);
        if (!ground.isFaceSturdy(level, below, Direction.UP)) {
            return false;
        }
        if (level.getBlockState(pos).isCollisionShapeFullBlock(level, pos)
                || level.getBlockState(pos.above()).isCollisionShapeFullBlock(level, pos.above())) {
            return false;
        }
        BlockPathTypes nodeType = WalkNodeEvaluator.getBlockPathTypeStatic(level, pos.mutable());
        if (nodeType == BlockPathTypes.DAMAGE_FIRE || nodeType == BlockPathTypes.LAVA) {
            return false;
        }
        return level.noCollision(
                pet,
                pet.getBoundingBox()
                        .move(
                                pos.getX() + 0.5 - pet.getX(),
                                pos.getY() - pet.getY(),
                                pos.getZ() + 0.5 - pet.getZ()));
    }
}
