package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Cockateil;
import com.astryxion.chaospersists.entity.Cricket;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Dragonfly;
import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityMosquito;
import com.astryxion.chaospersists.entity.Firefly;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.Ghost;
import com.astryxion.chaospersists.entity.GhostSkelly;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.KingHead;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.entity.QueenHead;
import com.astryxion.chaospersists.entity.RockBase;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.Stinky;
import com.astryxion.chaospersists.entity.Termite;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.WaterDragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.math.vector.Vector3d;

public class MyUtils {
    public MyUtils() {
    }

    public static boolean isRoyalty(Entity e) {
        if (!(e instanceof LivingEntity)) {
            return false;
        }
        if (e instanceof ThePrince) {
            return true;
        }
        if (e instanceof ThePrinceTeen) {
            return true;
        }
        if (e instanceof ThePrinceAdult) {
            return true;
        }
        if (e instanceof ThePrincess) {
            return true;
        }
        if (e instanceof TheKing) {
            return true;
        }
        if (e instanceof KingHead) {
            return true;
        }
        if (e instanceof TheQueen) {
            return true;
        }
        if (e instanceof QueenHead) {
            return true;
        }
        if (e instanceof PurplePower) {
            return true;
        }
        return false;
    }

    public static boolean isAttackableNonMob(LivingEntity par1LivingEntity) {
        if (par1LivingEntity instanceof MobEntity) {
            return true;
        }
        if (par1LivingEntity instanceof Mothra) {
            return true;
        }
        if (par1LivingEntity instanceof Leon) {
            return true;
        }
        if (par1LivingEntity instanceof Dragon) {
            return true;
        }
        if (par1LivingEntity instanceof Spyro) {
            return true;
        }
        if (MyUtils.isRoyalty(par1LivingEntity)) {
            return true;
        }
        if (par1LivingEntity instanceof GammaMetroid) {
            return true;
        }
        if (par1LivingEntity instanceof Cephadrome) {
            return true;
        }
        if (par1LivingEntity instanceof WaterDragon) {
            return true;
        }
        if (par1LivingEntity instanceof Girlfriend) {
            return true;
        }
        if (par1LivingEntity instanceof Boyfriend) {
            return true;
        }
        if (par1LivingEntity instanceof VillagerEntity) {
            return true;
        }
        if (par1LivingEntity instanceof Stinky) {
            return true;
        }
        return false;
    }

    public static boolean isIgnoreable(LivingEntity par1LivingEntity) {
        if (par1LivingEntity instanceof RockBase) {
            return true;
        }
        if (par1LivingEntity instanceof EntityAnt) {
            return true;
        }
        if (par1LivingEntity instanceof EntityButterfly) {
            return true;
        }
        if (par1LivingEntity instanceof EntityMosquito) {
            return true;
        }
        if (par1LivingEntity instanceof Dragonfly) {
            return true;
        }
        if (par1LivingEntity instanceof Firefly) {
            return true;
        }
        if (par1LivingEntity instanceof Cricket) {
            return true;
        }
        if (par1LivingEntity instanceof Cockateil) {
            return true;
        }
        if (par1LivingEntity instanceof Termite) {
            return true;
        }
        if (par1LivingEntity instanceof Ghost) {
            return true;
        }
        if (par1LivingEntity instanceof GhostSkelly) {
            return true;
        }
        if (par1LivingEntity instanceof Elevator) {
            return true;
        }
        return false;
    }

    public static void enforceDragonMountGroundSafety(LivingEntity entity) {
        if (entity == null || entity.level == null || entity.level.isClientSide) {
            return;
        }
        if (!entity.getPassengers().isEmpty()) {
            return;
        }
        entity.noPhysics = false;
        entity.setNoGravity(false);
        int n = 0;
        while (entity.isInWall() && n++ < 48) {
            entity.setPos(entity.getX(), Math.min(252.0D, entity.getY() + 0.5D), entity.getZ());
        }
    }

    public static void mulDeltaMovement(Entity entity, double factorX, double factorY, double factorZ) {
        Vector3d motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.x * factorX, motion.y * factorY, motion.z * factorZ);
    }

    public static void addEntityY(Entity entity, double deltaY) {
        entity.setPos(entity.getX(), entity.getY() + deltaY, entity.getZ());
    }

    public static void addEntityZ(Entity entity, double deltaZ) {
        entity.setPos(entity.getX(), entity.getY(), entity.getZ() + deltaZ);
    }

    public static void addEntityX(Entity entity, double deltaX) {
        entity.setPos(entity.getX() + deltaX, entity.getY(), entity.getZ());
    }

    public static void addDeltaMovement(Entity entity, double deltaX, double deltaY, double deltaZ) {
        Vector3d motion = entity.getDeltaMovement();
        entity.setDeltaMovement(motion.x + deltaX, motion.y + deltaY, motion.z + deltaZ);
    }
}
