/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Cephadrome
 *  com.astryxion.chaospersists.Cockateil
 *  com.astryxion.chaospersists.Cricket
 *  com.astryxion.chaospersists.Dragon
 *  com.astryxion.chaospersists.Dragonfly
 *  com.astryxion.chaospersists.Elevator
 *  com.astryxion.chaospersists.EntityAnt
 *  com.astryxion.chaospersists.EntityButterfly
 *  com.astryxion.chaospersists.EntityMosquito
 *  com.astryxion.chaospersists.Firefly
 *  com.astryxion.chaospersists.GammaMetroid
 *  com.astryxion.chaospersists.Ghost
 *  com.astryxion.chaospersists.GhostSkelly
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.KingHead
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.PurplePower
 *  com.astryxion.chaospersists.QueenHead
 *  com.astryxion.chaospersists.RockBase
 *  com.astryxion.chaospersists.Spyro
 *  com.astryxion.chaospersists.Stinky
 *  com.astryxion.chaospersists.Termite
 *  com.astryxion.chaospersists.TheKing
 *  com.astryxion.chaospersists.ThePrince
 *  com.astryxion.chaospersists.ThePrinceAdult
 *  com.astryxion.chaospersists.ThePrinceTeen
 *  com.astryxion.chaospersists.ThePrincess
 *  com.astryxion.chaospersists.TheQueen
 *  com.astryxion.chaospersists.WaterDragon
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityVillager
 */
package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.compat.eeeabsmobs.EeeabsMobsCompat;
import com.astryxion.chaospersists.compat.guardvillagers.GuardVillagersArmorCompat;
import com.astryxion.chaospersists.compat.mobbattle.MobBattleTeamCompat;
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
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.Termite;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.WaterDragon;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import java.util.UUID;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import javax.annotation.Nullable;

/*
 * Exception performing whole class analysis ignored.
 */
public class MyUtils {
    private static final Map<Mob, LivingEntity> chaseTargets = new WeakHashMap<>();
    private static final Set<LivingEntity> CHAOS_FLIGHT_ENTITIES =
            Collections.newSetFromMap(new WeakHashMap<>());

    public MyUtils() {
    }

    /** 1.12 {@code World.isDaytime()} for {@link LevelAccessor} spawn checks. */
    public static boolean isDay(LevelAccessor level) {
        if (level instanceof Level world) {
            return world.isDay();
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            return serverLevel.getLevel().isDay();
        }
        return false;
    }

    /** False for empty stacks and armor with Curse of Binding. */
    public static boolean canMobStripItem(@Nullable ItemStack stack) {
        return stack != null && !stack.isEmpty() && !EnchantmentHelper.hasBindingCurse(stack);
    }

    /** 1.12 {@code EntityLivingBase.playAmbientSound()} equivalent. */
    public static void playAmbientSound(LivingEntity entity) {
        if (entity instanceof Mob mob) {
            mob.playAmbientSound();
        }
    }

    /** Returns false for creative/spectator players and armor stands. */
    public static boolean isValidAggroTarget(@Nullable LivingEntity target) {
        if (target == null) {
            return false;
        }
        if (target instanceof ArmorStand) {
            return false;
        }
        if (target instanceof Player player) {
            return !player.isCreative() && !player.isSpectator();
        }
        return true;
    }

    public static boolean isValidAggroTarget(@Nullable Entity attacker, @Nullable LivingEntity target) {
        return isValidAggroTarget(target) && !shouldSkipCombatTarget(attacker, target);
    }

    /** Vanilla scoreboard teammates ({@link Entity#isAlliedTo(Entity)}). */
    public static boolean isScoreboardAlly(@Nullable Entity attacker, @Nullable Entity target) {
        if (attacker == null || target == null || attacker == target) {
            return false;
        }
        return attacker.isAlliedTo(target);
    }

    /**
     * Skip this combat target: same scoreboard team, or (when Mob Battle is loaded) a teamed
     * Chaos Persists mob hunting anyone who is not on an enemy team.
     */
    public static boolean shouldSkipCombatTarget(@Nullable Entity attacker, @Nullable LivingEntity target) {
        if (attacker == null || target == null || attacker == target) {
            return attacker != null && attacker == target;
        }
        if (isScoreboardAlly(attacker, target)) {
            return true;
        }
        if (attacker.getTeam() != null && MobBattleTeamCompat.isPresent()) {
            if (EeeabsMobsCompat.shouldAllowTeamedHunt(attacker, target)) {
                return false;
            }
            if (isVillageCombatTarget(target) && !attacker.isAlliedTo(target)) {
                return false;
            }
            return target.getTeam() == null || attacker.isAlliedTo(target);
        }
        return false;
    }

    /** Iron Golems and Guard Villagers — village defenders CP hostiles should fight. */
    public static boolean isVillageCombatTarget(@Nullable LivingEntity target) {
        if (target == null) {
            return false;
        }
        return target instanceof IronGolem || GuardVillagersArmorCompat.isGuard(target);
    }

    /** 1.7.10 {@code IMob.mobSelector} parity — includes Slimes ({@code Enemy}, not {@code Monster}). */
    public static boolean isHostileMobTarget(LivingEntity entity) {
        return entity instanceof Enemy || entity instanceof Mothra;
    }

    /** Villagers, farm animals, golems, and the owner's other tamed pets must not be attacked. */
    public static boolean isProtectedCompanion(Mob attacker, LivingEntity candidate) {
        if (candidate instanceof Villager) {
            return true;
        }
        if (candidate instanceof IronGolem) {
            return true;
        }
        if (candidate instanceof Animal) {
            return true;
        }
        if (candidate instanceof WaterAnimal) {
            return true;
        }
        if (attacker == null || !(attacker instanceof TamableAnimal tame) || !tame.isTame()) {
            return false;
        }
        LivingEntity owner = tame.getOwner();
        if (owner != null) {
            if (candidate == owner) {
                return true;
            }
            if (candidate instanceof TamableAnimal other && other.isTame() && other.isOwnedBy(owner)) {
                return true;
            }
        }
        UUID ownerUuid = tame.getOwnerUUID();
        if (ownerUuid != null && candidate instanceof OwnableEntity ownable) {
            UUID candidateOwner = ownable.getOwnerUUID();
            if (candidateOwner != null && ownerUuid.equals(candidateOwner)) {
                return true;
            }
        }
        return false;
    }

    /** Re-applies boss max HP when vanilla's 1024 attribute cap was in effect at spawn. */
    public static void ensureBossMaxHealth(LivingEntity entity, int desiredMax) {
        if (entity.level().isClientSide || desiredMax <= 0) {
            return;
        }
        if (entity.getMaxHealth() + 0.5F >= desiredMax) {
            return;
        }
        entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) desiredMax);
        float newMax = entity.getMaxHealth();
        if (newMax + 0.5F < desiredMax) {
            return;
        }
        if (entity.getHealth() <= 0.0F || entity.getHealth() > newMax) {
            entity.setHealth(newMax);
        }
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

    public static boolean isAttackableNonMob(LivingEntity par1EntityLiving) {
        // Prefer Enemy over Monster so Slimes (and similar) count as hostiles.
        if (par1EntityLiving instanceof Enemy || par1EntityLiving instanceof Monster) {
            return true;
        }
        if (par1EntityLiving instanceof Mothra) {
            return true;
        }
        if (par1EntityLiving instanceof Leon) {
            return true;
        }
        if (par1EntityLiving instanceof Dragon) {
            return true;
        }
        if (par1EntityLiving instanceof Spyro) {
            return true;
        }
        if (MyUtils.isRoyalty((Entity)par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof GammaMetroid) {
            return true;
        }
        if (par1EntityLiving instanceof Cephadrome) {
            return true;
        }
        if (par1EntityLiving instanceof WaterDragon) {
            return true;
        }
        if (par1EntityLiving instanceof Girlfriend) {
            return true;
        }
        if (par1EntityLiving instanceof Boyfriend) {
            return true;
        }
        if (par1EntityLiving instanceof Villager) {
            return true;
        }
        if (par1EntityLiving instanceof Stinky) {
            return true;
        }
        return false;
    }

    public static boolean isIgnoreable(LivingEntity par1EntityLiving) {
        if (par1EntityLiving instanceof ArmorStand) {
            return true;
        }
        if (par1EntityLiving instanceof RockBase) {
            return true;
        }
        if (par1EntityLiving instanceof EntityAnt) {
            return true;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return true;
        }
        if (par1EntityLiving instanceof EntityMosquito) {
            return true;
        }
        if (par1EntityLiving instanceof Dragonfly) {
            return true;
        }
        if (par1EntityLiving instanceof Firefly) {
            return true;
        }
        if (par1EntityLiving instanceof Cricket) {
            return true;
        }
        if (par1EntityLiving instanceof Cockateil) {
            return true;
        }
        if (par1EntityLiving instanceof Termite) {
            return true;
        }
        if (par1EntityLiving instanceof Ghost) {
            return true;
        }
        if (par1EntityLiving instanceof GhostSkelly) {
            return true;
        }
        if (par1EntityLiving instanceof Elevator) {
            return true;
        }
        return false;
    }

    /** True when a large prince should keep flight AI instead of ground pathing. */
    public static boolean isPrinceAirborne(net.minecraft.world.entity.TamableAnimal pet) {
        if (pet.onGround()) {
            return false;
        }
        LivingEntity owner = pet.getOwner();
        if (owner != null && pet.getY() <= owner.getY() + 2.5) {
            return false;
        }
        return true;
    }

    /** Offset follow point behind the owner so large princes do not stack on top of them. */
    public static Vec3 getPrinceWalkFollowTarget(LivingEntity owner, double followDist, double hoverHeight) {
        double yawRad = Math.toRadians(owner.getYRot());
        double offX = -Math.sin(yawRad) * followDist;
        double offZ = Math.cos(yawRad) * followDist;
        return new Vec3(owner.getX() + offX, owner.getY() + hoverHeight, owner.getZ() + offZ);
    }

    /** True when a prince should use flight AI instead of follow-owner pathing/teleport. */
    public static boolean isPrinceFlying(net.minecraft.world.entity.TamableAnimal pet) {
        if (!pet.getPassengers().isEmpty()) {
            return true;
        }
        if (pet instanceof Dragon dragon && (dragon.getActivity() != 0 || !dragon.onGround())) {
            return true;
        }
        if (pet instanceof TerribleTerror terror && terror.isTame() && !terror.isOrderedToSit() && !terror.isInSittingPose()) {
            return true;
        }
        if (pet instanceof Stinky stinky && stinky.getActivity() == 2) {
            return true;
        }
        if (pet instanceof Spyro spyro && spyro.getActivity() == 2) {
            return true;
        }
        // Leon: activity 1 = flying (unlike Spyro's 2)
        if (pet instanceof Leon leon && leon.getActivity() != 0) {
            return true;
        }
        if (pet instanceof ThePrince prince && prince.getActivity() == 2) {
            return true;
        }
        if (pet instanceof ThePrincess princess && princess.getActivity() == 2) {
            return true;
        }
        if (pet instanceof ThePrinceTeen teen) {
            if (teen.getActivity() == 0) {
                return false;
            }
            LivingEntity owner = pet.getOwner();
            if (owner instanceof net.minecraft.world.entity.player.Player player
                    && !player.getAbilities().flying
                    && teen.onGround()) {
                return false;
            }
            return true;
        }
        if (pet instanceof ThePrinceAdult adult) {
            if (adult.getActivity() == 0) {
                return false;
            }
            LivingEntity owner = pet.getOwner();
            if (owner instanceof net.minecraft.world.entity.player.Player player
                    && !player.getAbilities().flying
                    && adult.onGround()) {
                return false;
            }
            return true;
        }
        return false;
    }

    /** Skip follow-owner teleport while a prince is flying nearby; still allow catch-up when far away. */
    public static boolean shouldPrinceSkipFollowTeleport(net.minecraft.world.entity.TamableAnimal pet, LivingEntity owner) {
        if (pet.getPassengers().isEmpty() && owner != null) {
            if (pet instanceof ThePrince prince && prince.getActivity() == 2) {
                return pet.distanceToSqr(owner) < 625.0;
            }
            if (pet instanceof ThePrincess princess && princess.getActivity() == 2) {
                return pet.distanceToSqr(owner) < 625.0;
            }
            if (pet instanceof ThePrinceTeen teen && teen.getActivity() != 0) {
                return pet.distanceToSqr(owner) < 625.0;
            }
            if (pet instanceof ThePrinceAdult adult && adult.getActivity() != 0) {
                return pet.distanceToSqr(owner) < 625.0;
            }
            if (pet instanceof Dragon dragon && dragon.getActivity() != 0) {
                return pet.distanceToSqr(owner) < 625.0;
            }
            if (pet instanceof Leon leon && leon.getActivity() != 0) {
                return pet.distanceToSqr(owner) < 625.0;
            }
        }
        return false;
    }

    public static boolean isPrinceGrounded(net.minecraft.world.entity.TamableAnimal pet) {
        if (pet instanceof ThePrinceTeen teen) {
            return teen.getActivity() == 0;
        }
        if (pet instanceof ThePrinceAdult adult) {
            return adult.getActivity() == 0;
        }
        return false;
    }

    /**
     * Prince-family mounts: while nobody is riding, never leave noClip/gravity-off on or stay inside blocks.
     */
    public static void enforceDragonMountGroundSafety(Mob entity) {
        if (entity == null || entity.level() == null || entity.level().isClientSide) {
            return;
        }
        if (!entity.getPassengers().isEmpty()) {
            return;
        }
        if (entity instanceof ThePrinceTeen teen && teen.getActivity() != 0) {
            return;
        }
        if (entity instanceof ThePrinceAdult adult && adult.getActivity() != 0) {
            return;
        }
        if (entity instanceof Leon leon && leon.getActivity() != 0) {
            return;
        }
        if (entity instanceof Dragon dragon && dragon.getActivity() != 0) {
            return;
        }
        entity.noPhysics = false;
        entity.setNoGravity(false);
        clearChaosFlight(entity);
        int n = 0;
        while (entity.isInWall() && !entity.onGround() && n++ < 48) {
            entity.setPos(entity.getX(), Math.min(252.0, entity.getY() + 0.5), entity.getZ());
        }
    }

    /**
     * Apply 3-axis flight velocity from chaos AI. In 1.7.10 motionX/Y/Z moved the entity once per tick.
     * On 1.20.1 {@link net.minecraft.world.entity.LivingEntity#travel(Vec3)} also runs before
     * {@code customServerAiStep}; skipping vanilla travel for flagged mobs avoids ~2x flight speed.
     * <p>
     * OreSpawn 1.7.10 flyers collide with blocks unless the entity itself enables {@code noClip}
     * (royals, ghosts). Never force {@code noPhysics} here — that made birds/bees ghost through terrain.
     */
    public static void applyChaosFlightMovement(LivingEntity entity) {
        if (entity.level().isClientSide || entity.isDeadOrDying()) {
            return;
        }
        entity.setNoGravity(true);
        if (!keepsOwnFlightNoPhysics(entity)) {
            entity.noPhysics = false;
        }
        CHAOS_FLIGHT_ENTITIES.add(entity);
        entity.move(MoverType.SELF, entity.getDeltaMovement());
    }

    /**
     * Ice and Fire {@code FlightMoveHelper}: turn toward the destination, then accelerate only along
     * facing. Never strafes or reverse-thrusts — OreSpawn's bat {@code signum} + slow yaw did both.
     */
    public static void steerChaosFlightForward(
            LivingEntity entity, double destX, double destY, double destZ, double speedFactor) {
        double distX = destX - entity.getX();
        double distY = destY - entity.getY();
        double distZ = destZ - entity.getZ();
        double planeDist = Math.sqrt(distX * distX + distZ * distZ);
        if (planeDist < 1.0E-4 && Math.abs(distY) < 1.0E-4) {
            return;
        }

        float targetYaw = (float) (Mth.atan2(distZ, distX) * (180.0 / Math.PI)) - 90.0f;
        float yawErr = Mth.wrapDegrees(targetYaw - entity.getYRot());
        // IAF tackle turns 10°/tick, cruise 4°. Stinky is small; 12° still banks instead of snapping.
        float maxTurn = 12.0f;
        float newYaw = entity.getYRot() + Mth.clamp(yawErr, -maxTurn, maxTurn);
        entity.setYRot(newYaw);
        entity.yBodyRot = newYaw;
        entity.setYHeadRot(newYaw);

        if (planeDist > 1.0E-4) {
            float targetPitch = (float) (-(Mth.atan2(distY, planeDist) * (180.0 / Math.PI)));
            entity.setXRot(Mth.clamp(targetPitch, -30.0f, 30.0f));
        }

        // IAF skips thrust when dist < 1 so they don't overshoot and look like they're strafing.
        if (planeDist < 1.0) {
            Vec3 motion = entity.getDeltaMovement();
            double ny = motion.y;
            if (Math.abs(distY) > 0.35) {
                ny += (Math.signum(distY) * 0.2 * speedFactor - motion.y) * 0.21;
            } else {
                ny *= 0.6;
            }
            entity.setDeltaMovement(motion.x * 0.45, ny, motion.z * 0.45);
            return;
        }

        float yawRad = newYaw * ((float) Math.PI / 180.0f);
        double forwardX = -Mth.sin(yawRad);
        double forwardZ = Mth.cos(yawRad);

        double horizCruise = 0.5 * speedFactor;
        // IAF: while yaw is still catching up, drop speed so the body cannot crab sideways.
        if (Math.abs(yawErr) >= 8.0f) {
            horizCruise *= 0.25;
        }

        Vec3 motion = entity.getDeltaMovement();
        double along = motion.x * forwardX + motion.z * forwardZ;
        if (along < 0.0) {
            along = 0.0;
        }
        double newAlong = along + (horizCruise - along) * 0.25;

        double vertCruise = 0.0;
        if (Math.abs(distY) > 0.35) {
            vertCruise = Math.signum(distY) * 0.45 * speedFactor;
        }
        double ny = motion.y + (vertCruise - motion.y) * 0.21;

        entity.setDeltaMovement(forwardX * newAlong, ny, forwardZ * newAlong);
    }

    /** Entities that manage noClip themselves (OreSpawn royals / ghosts). */
    private static boolean keepsOwnFlightNoPhysics(LivingEntity entity) {
        return entity instanceof TheKing
                || entity instanceof TheQueen
                || entity instanceof ThePrince
                || entity instanceof ThePrincess
                || entity instanceof ThePrinceTeen
                || entity instanceof ThePrinceAdult
                || entity instanceof Ghost
                || entity instanceof GhostSkelly
                || entity instanceof KingHead
                || entity instanceof QueenHead;
    }

    /** Skip vanilla {@code travel()} when chaos flight AI already moved this mob on the server. */
    public static boolean usesChaosFlight(LivingEntity entity) {
        if (!entity.isNoGravity()) {
            CHAOS_FLIGHT_ENTITIES.remove(entity);
            return false;
        }
        return CHAOS_FLIGHT_ENTITIES.contains(entity);
    }

    public static void clearChaosFlight(LivingEntity entity) {
        CHAOS_FLIGHT_ENTITIES.remove(entity);
    }

    /** Avoid {@link WorldGenRegion} out-of-bounds chunk access during natural spawn in chunk generation. */
    public static boolean canAccessBlockDuringWorldGen(LevelAccessor level, BlockPos pos) {
        if (level instanceof WorldGenRegion region) {
            return region.hasChunk(pos.getX() >> 4, pos.getZ() >> 4)
                    && pos.getY() >= region.getMinBuildHeight()
                    && pos.getY() < region.getMaxBuildHeight();
        }
        if (level instanceof Level worldLevel) {
            return worldLevel.isInWorldBounds(pos);
        }
        return pos.getY() >= level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight();
    }

    /** Spawn-rule block reads that must not cross {@link WorldGenRegion} chunk bounds during generation. */
    public static BlockState getBlockStateForSpawnRules(LevelAccessor level, BlockPos pos) {
        if (!canAccessBlockDuringWorldGen(level, pos)) {
            return Blocks.VOID_AIR.defaultBlockState();
        }
        return level.getBlockState(pos);
    }

    @Nullable
    public static BlockEntity getBlockEntityForSpawnRules(LevelAccessor level, BlockPos pos) {
        if (!canAccessBlockDuringWorldGen(level, pos)) {
            return null;
        }
        return level.getBlockEntity(pos);
    }

    /**
     * 1.7.10 {@code EntityLiving#faceEntity} parity for melee range only. Do not call while
     * pathing — {@link ChaosChaseMoveControl} owns body yaw from the path and soft-aims the head.
     */
    public static void faceEntity(LivingEntity mob, Entity target, float maxYawIncrease, float maxPitchIncrease) {
        if (target == null) {
            return;
        }
        double dx = target.getX() - mob.getX();
        double dz = target.getZ() - mob.getZ();
        float desiredYaw = (float) (Mth.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0f;
        float yaw =
                mob.getYRot()
                        + Mth.clamp(
                                Mth.wrapDegrees(desiredYaw - mob.getYRot()),
                                -maxYawIncrease,
                                maxYawIncrease);
        mob.setYRot(yaw);
        mob.yHeadRot = yaw;
        mob.yBodyRot = yaw;

        double dy = target.getEyeY() - mob.getEyeY();
        double horizDist = Math.sqrt(dx * dx + dz * dz);
        float desiredPitch = (float) (-(Mth.atan2(dy, horizDist) * (180.0 / Math.PI)));
        float pitch =
                mob.getXRot()
                        + Mth.clamp(
                                Mth.wrapDegrees(desiredPitch - mob.getXRot()),
                                -maxPitchIncrease,
                                maxPitchIncrease);
        mob.setXRot(pitch);
    }

    public static void setChaseTarget(Mob mob, @Nullable LivingEntity target) {
        if (target == null) {
            chaseTargets.remove(mob);
        } else {
            chaseTargets.put(mob, target);
        }
    }

    @Nullable
    public static LivingEntity getChaseTarget(Mob mob) {
        return chaseTargets.get(mob);
    }
}

