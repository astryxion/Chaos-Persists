/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.ChaosTeleporter
 *  com.astryxion.chaospersists.legacy.minecraft.block.Block
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockGrass
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockSand
 *  com.astryxion.chaospersists.legacy.minecraft.block.BlockTallGrass
 *  com.astryxion.chaospersists.legacy.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  com.astryxion.chaospersists.legacy.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  com.astryxion.chaospersists.legacy.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.Teleporter
 *  com.astryxion.chaospersists.legacy.minecraft.world.World
 *  com.astryxion.chaospersists.legacy.minecraft.world.WorldProvider
 *  com.astryxion.chaospersists.legacy.minecraft.world.WorldServer
 */
package com.astryxion.chaospersists.core;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.util.ITeleporter;

public class ChaosTeleporter implements ITeleporter {
    private final ServerLevel world;
    private final Level oldWorld;
    private final int newdim;

    public ChaosTeleporter(ServerLevel par1WorldServer, int dim, Level par2World) {
        this.world = par1WorldServer;
        this.oldWorld = par2World;
        this.newdim = dim;
    }

    @Override
    public Entity placeEntity(
            Entity par1Entity,
            ServerLevel currentWorld,
            ServerLevel destWorld,
            float rotationYaw,
            Function<Boolean, Entity> repositionEntity) {
        this.justPutMe(destWorld, par1Entity);
        return par1Entity;
    }

    private boolean isGroundBlock(Block bid) {
        if (bid == Blocks.AIR) {
            return false;
        }
        if (bid == Blocks.DIRT) {
            return true;
        }
        if (bid == Blocks.GRASS_BLOCK) {
            return true;
        }
        if (bid == Blocks.STONE) {
            return true;
        }
        if (bid == Blocks.END_STONE) {
            return true;
        }
        if (bid == Blocks.NETHERRACK) {
            return true;
        }
        if (bid == Blocks.COBBLESTONE) {
            return true;
        }
        if (bid == Blocks.SAND) {
            return true;
        }
        if (bid == Blocks.SANDSTONE) {
            return true;
        }
        if (bid == Blocks.FARMLAND) {
            return true;
        }
        return false;
    }

    /**
     * Find a safe feet Y: block at (x, feetY-1, z) is solid and not liquid;
     * blocks at (x, feetY, z) and (x, feetY+1, z) are not solid and not liquid (standing + head room).
     */
    private int findSafeFeetY(ServerLevel level, int posX, int posZ) {
        int maxY = Math.min(255, level.getMaxBuildHeight() - 1);
        for (int feetY = maxY; feetY >= 2; feetY--) {
            BlockPos ground = new BlockPos(posX, feetY - 1, posZ);
            BlockPos stand = new BlockPos(posX, feetY, posZ);
            BlockPos head = new BlockPos(posX, feetY + 1, posZ);
            BlockState groundState = level.getBlockState(ground);
            BlockState standState = level.getBlockState(stand);
            BlockState headState = level.getBlockState(head);
            if (isSolidNotLiquid(groundState)
                    && isPassable(standState)
                    && isPassable(headState)) {
                return feetY;
            }
        }
        return -1;
    }

    private static boolean isSolidNotLiquid(BlockState state) {
        return state.isSolid() && state.getFluidState().isEmpty();
    }

    private static boolean isPassable(BlockState state) {
        return !state.isSolid() && state.getFluidState().isEmpty();
    }

    public boolean justPutMe(ServerLevel dest, Entity par1Entity) {
        if (dest.isClientSide()) {
            return true;
        }
        int posX = (int) par1Entity.getX();
        int posZ = (int) par1Entity.getZ();
        int posY = findSafeFeetY(dest, posX, posZ);

        for (int i = 0; posY < 0 && i < 200; i++) {
            int spread = 2 + i / 20;
            posX = (int) par1Entity.getX() + dest.getRandom().nextInt(2 * spread + 1) - spread;
            posZ = (int) par1Entity.getZ() + dest.getRandom().nextInt(2 * spread + 1) - spread;
            posY = findSafeFeetY(dest, posX, posZ);
        }

        if (posY < 0 && this.newdim == ChaosPersists.getDimension(4)) {
            posX = (int) par1Entity.getX();
            posZ = (int) par1Entity.getZ();
            BlockPos ground8 = new BlockPos(posX, 8, posZ);
            if (dest.getBlockState(ground8).isSolid()) {
                posY = 9;
            }
        }

        if (posY < 0) {
            posY = Math.min(255, dest.getMaxBuildHeight() - 1);
        }

        double oldX = par1Entity.getX();
        double oldY = par1Entity.getY();
        double oldZ = par1Entity.getZ();
        double newX = posX + 0.5D;
        double newZ = posZ + 0.5D;
        double newY = (double) posY;

        par1Entity.moveTo(newX, newY, newZ, par1Entity.getYRot(), par1Entity.getXRot());
        par1Entity.setDeltaMovement(0.0D, 0.0D, 0.0D);
        if (par1Entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.fallDistance = 0.0f;
        }

        if (par1Entity instanceof Player ep && this.oldWorld instanceof ServerLevel oldServer) {
            AABB bb = new AABB(oldX - 24.0D, oldY - 12.0D, oldZ - 24.0D, oldX + 24.0D, oldY + 12.0D, oldZ + 24.0D);
            List<TamableAnimal> var5 = oldServer.getEntitiesOfClass(TamableAnimal.class, bb);

            for (TamableAnimal et : var5) {
                if (!et.isOrderedToSit()) {
                    String p1 = ep.getUUID().toString();
                    UUID ownerId = et.getOwnerUUID();
                    String p2 = ownerId != null ? ownerId.toString() : null;
                    if (((p1 != null) && (p2 != null) && (p1.equals(p2))) || (et.isOwnedBy(ep))) {
                        sendToThisDimension(dest, et, newX, newY, newZ, (int) ep.getYRot());
                    }
                }
            }
        }

        return true;
    }

    public void sendToThisDimension(ServerLevel dest, Entity e, double newX, double newY, double newZ, int ro) {
        if (this.oldWorld.isClientSide()) {
            return;
        }
        CompoundTag nbt = new CompoundTag();
        e.saveWithoutId(nbt);
        Entity var6 = e.getType().create(dest);
        if (var6 != null) {
            var6.load(nbt);
            var6.moveTo(newX, newY, newZ, (float) ro, 0.0f);
            var6.setDeltaMovement(0.0, 0.0, 0.0);
            dest.addFreshEntity(var6);
        }
        e.remove(RemovalReason.CHANGED_DIMENSION);
        e.discard();
    }

    public void removeStalePortalLocations(long par1) {
    }
}
