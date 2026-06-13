/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.ChaosTeleporter
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 *  net.minecraft.world.ServerWorld
 */
package com.astryxion.chaospersists.core;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import java.util.Random;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ChaosTeleporter
extends Teleporter {
    private ServerWorld world;
    private World oldWorld;
    private Random random;
    private int newdim;

    public ChaosTeleporter(ServerWorld par1ServerWorld, int dim, World par2World) {
        super(par1ServerWorld);
        this.world = par1ServerWorld;
        this.oldWorld = par2World;
        this.random = new Random(par1ServerWorld.getSeed());
        this.newdim = dim;
    }

    /** Called by transferPlayerEntityToDimension in 1.12.2 — must override this for entity teleports to work. */
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        this.justPutMe(entityIn);
    }

    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
        this.justPutMe(par1Entity);
    }

    public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8) {
        this.justPutMe(par1Entity);
        return true;
    }

    public boolean makePortal(Entity par1Entity) {
        return true;
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
    private int findSafeFeetY(int posX, int posZ) {
        int maxY = Math.min(255, this.level.getMaxBuildHeight() - 1);
        for (int feetY = maxY; feetY >= 2; feetY--) {
            BlockPos ground = new BlockPos(posX, feetY - 1, posZ);
            BlockPos stand = new BlockPos(posX, feetY, posZ);
            BlockPos head = new BlockPos(posX, feetY + 1, posZ);
            net.minecraft.block.BlockState groundState = this.level.getBlockState(ground);
            net.minecraft.block.BlockState standState = this.level.getBlockState(stand);
            net.minecraft.block.BlockState headState = this.level.getBlockState(head);
            if (groundState.isSolidRender(this.level, ground) && groundState.getFluidState().isEmpty()
                    && !standState.isSolidRender(this.level, stand) && standState.getFluidState().isEmpty()
                    && !headState.isSolidRender(this.level, head) && headState.getFluidState().isEmpty()) {
                return feetY;
            }
        }
        return -1;
    }

    public boolean justPutMe(Entity par1Entity) {
        if (this.level.isClientSide) {
            return true;
        }
        int posX = (int) par1Entity.getX();
        int posZ = (int) par1Entity.getZ();
        int posY = findSafeFeetY(posX, posZ);

        for (int i = 0; posY < 0 && i < 200; i++) {
            int spread = 2 + i / 20;
            posX = (int) par1Entity.getX() + this.level.random.nextInt(2 * spread + 1) - spread;
            posZ = (int) par1Entity.getZ() + this.level.random.nextInt(2 * spread + 1) - spread;
            posY = findSafeFeetY(posX, posZ);
        }

        if (posY < 0 && this.newdim == ChaosPersists.getDimension(4)) {
            posX = (int) par1Entity.getX();
            posZ = (int) par1Entity.getZ();
            BlockPos ground8 = new BlockPos(posX, 8, posZ);
            if (this.level.getBlockState(ground8).isSolidRender(this.level, ground8)) {
                posY = 9;
            }
        }

        if (posY < 0) {
            posY = Math.min(255, this.level.getMaxBuildHeight() - 1);
        }

        double oldX = par1Entity.getX();
        double oldY = par1Entity.getY();
        double oldZ = par1Entity.getZ();
        double newX = posX + 0.5D;
        double newZ = posZ + 0.5D;
        double newY = (double) posY;

        par1Entity.moveTo(newX, newY, newZ, par1Entity.yRot, par1Entity.xRot);
        par1Entity.setDeltaMovement(0.0D, par1Entity.getDeltaMovement().y, par1Entity.getDeltaMovement().z);
        par1Entity.setDeltaMovement(par1Entity.getDeltaMovement().x, 0.0D, par1Entity.getDeltaMovement().z);
        par1Entity.setDeltaMovement(par1Entity.getDeltaMovement().x, par1Entity.getDeltaMovement().y, 0.0D);
        if (par1Entity instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) par1Entity).fallDistance = 0.0f;
        }

      MinecraftServer minecraftserver = this.oldWorld.getServer();
      ServerWorld worldserver = minecraftserver != null ? minecraftserver.getLevel(this.oldWorld.dimension()) : null;
      ServerWorld worldserver1 = ChaosPersists.getServerWorldForDimensionIndex(minecraftserver, this.newdim);

      if ((par1Entity instanceof PlayerEntity)) {
        PlayerEntity ep = (PlayerEntity)par1Entity;
        AxisAlignedBB bb = new AxisAlignedBB(oldX - 24.0D, oldY - 12.0D, oldZ - 24.0D, oldX + 24.0D, oldY + 12.0D, oldZ + 24.0D);
        List<TameableEntity> var5 = this.oldWorld.getEntitiesOfClass(TameableEntity.class, bb);
        Iterator var2 = var5.iterator();

        while (var2.hasNext())
        {
          Entity var3 = (Entity)var2.next();
          TameableEntity et = (TameableEntity)var3;

          if (!et.isOrderedToSit())
          {
            String p1 = ep.getUUID().toString();
            UUID ownerId = et.getOwnerUUID();
            String p2 = ownerId != null ? ownerId.toString() : null;
            if (((p1 != null) && (p2 != null) && (p1.equals(p2))) || (et.isOwnedBy(ep))) {
              sendToThisDimension(var3, newX, newY, newZ, (int)ep.yRot);
            }

          }

        }

      }

      return true;
    }

    public void sendToThisDimension(Entity e, double newX, double newY, double newZ, int ro) {
        if (this.oldWorld.isClientSide) {
            return;
        }
        e.remove();
        e.moveTo(newX, newY, newZ, (float)ro, 0.0f);
        e.setDeltaMovement(e.getDeltaMovement().x, e.getDeltaMovement().y, 0.0);
        e.setDeltaMovement(e.getDeltaMovement().x, 0.0, e.getDeltaMovement().z);
        e.setDeltaMovement(0.0, e.getDeltaMovement().y, e.getDeltaMovement().z);
        EntityType<?> entityType = e.getType();
        Entity var6 = entityType != null ? entityType.create(this.level) : null;
        if (var6 != null) {
            CompoundNBT nbt = new CompoundNBT();
            e.saveWithoutId(nbt);
            var6.load(nbt);
            var6.moveTo(newX, newY, newZ, (float)ro, 0.0f);
            var6.setDeltaMovement(var6.getDeltaMovement().x, var6.getDeltaMovement().y, 0.0);
            var6.setDeltaMovement(var6.getDeltaMovement().x, 0.0, var6.getDeltaMovement().z);
            var6.setDeltaMovement(0.0, var6.getDeltaMovement().y, var6.getDeltaMovement().z);
            this.level.addFreshEntity(var6);
        }
        e.remove();
    }

    public void removeStalePortalLocations(long par1) {
    }
}

