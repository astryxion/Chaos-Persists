package com.astryxion.chaospersists.util;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.Vec3;

/**
 * OreSpawn sea creatures skim the water surface (1.7 / 1.12). Modern {@code FloatGoal} uses
 * jump bursts that look like hopping when {@code SWIM_SPEED} is raised; this applies smooth
 * buoyancy instead. 1.13+ swim physics will not step onto a bank, so a shore hop is applied
 * only when blocked by land or chasing a target that is already out of the water.
 */
public final class SurfaceWaterFloat {
    private SurfaceWaterFloat() {}

    /** Call after {@code super.aiStep()} so travel/gravity for this tick are corrected. */
    public static void keepOnSurface(LivingEntity mob) {
        if (!mob.isInWater()) {
            return;
        }
        double depth = mob.getFluidHeight(FluidTags.WATER);
        if (depth <= 0.0) {
            return;
        }

        boolean stayPut =
                mob instanceof TamableAnimal t && (t.isOrderedToSit() || t.isInSittingPose());
        if (!stayPut && mob instanceof Mob m) {
            LivingEntity target = m.getTarget();
            if (target != null && target.isAlive() && !target.isInWater()) {
                swimTowardLandTarget(mob, target);
            }
        }

        Vec3 motion = mob.getDeltaMovement();
        double y = motion.y;
        if (depth > mob.getFluidJumpThreshold()) {
            // Submerged: climb toward the surface smoothly (no JumpControl).
            y = Math.max(y, 0.08);
            if (y > 0.2) {
                y = 0.2;
            }
        } else if (!stayPut && hittingShore(mob)) {
            // 1.13+ water travel has no step-up; leap onto the bank.
            y = Math.max(y, 0.42);
        } else {
            // At the surface: hold skim height — no sinking, no hopping out.
            if (y < 0.0) {
                y = 0.0;
            } else if (y > 0.03) {
                y = 0.03;
            }
        }
        mob.setDeltaMovement(motion.x, y, motion.z);
    }

    private static void swimTowardLandTarget(LivingEntity mob, LivingEntity target) {
        double dx = target.getX() - mob.getX();
        double dz = target.getZ() - mob.getZ();
        double distSq = dx * dx + dz * dz;
        if (distSq < 0.04) {
            return;
        }
        double dist = Math.sqrt(distSq);
        double speed = 0.14;
        Vec3 motion = mob.getDeltaMovement();
        mob.setDeltaMovement(
                motion.x * 0.75 + (dx / dist) * speed,
                motion.y,
                motion.z * 0.75 + (dz / dist) * speed);
    }

    private static boolean hittingShore(LivingEntity mob) {
        if (mob.horizontalCollision) {
            return true;
        }
        Vec3 vel = mob.getDeltaMovement();
        double dx = vel.x;
        double dz = vel.z;
        if (dx * dx + dz * dz < 1.0E-6) {
            float yaw = mob.getYRot() * ((float) Math.PI / 180F);
            dx = -Mth.sin(yaw);
            dz = Mth.cos(yaw);
        } else {
            double len = Math.sqrt(dx * dx + dz * dz);
            dx /= len;
            dz /= len;
        }
        double reach = mob.getBbWidth() * 0.5 + 0.35;
        BlockPos ahead = BlockPos.containing(mob.getX() + dx * reach, mob.getY(), mob.getZ() + dz * reach);
        return !mob.level().getFluidState(ahead).is(FluidTags.WATER)
                && !mob.level().getBlockState(ahead).getCollisionShape(mob.level(), ahead).isEmpty();
    }
}
