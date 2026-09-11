package com.astryxion.chaospersists.util;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import com.astryxion.chaospersists.compat.eeeabsmobs.EeeabsMobsCompat;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Stinky;
import com.astryxion.chaospersists.entity.TerribleTerror;

/**
 * Owner-assist combat for all Chaos Persists attack-capable tameables (including Girlfriend /
 * Boyfriend, Rubber Ducky, royals, dragons, etc.).
 *
 * <p>Fixes the long-standing {@code instanceof Monster} hole (Slimes, many modded hostiles) by:
 * <ul>
 *   <li>auto-hunting anything implementing {@link Enemy} (covers Slimes)</li>
 *   <li>assisting when the owner attacks / is attacked — like vanilla wolves — including modded mobs
 *       the pet would not auto-target (Lycanites, Cataclysm bosses, Naturalist deer, etc.)</li>
 * </ul>
 *
 * <p>Guards: never assist against tamed/owned companions (except OreSpawn BF/GF jealousy); while
 * sitting, sync owner-combat timestamps so standing up does not resume a fight the owner started
 * while the pet was sat.
 */
public final class PetCombatHelper {
    private static final Map<UUID, int[]> SEEN_OWNER_COMBAT = new WeakHashMap<>();

    private PetCombatHelper() {}

    /** Freeroam hunt list — broader than {@code Monster} (Slime implements {@link Enemy} only). */
    public static boolean isAutoHostileTarget(@Nullable LivingEntity entity) {
        if (entity == null) {
            return false;
        }
        return entity instanceof Enemy || entity instanceof Mothra;
    }

    /**
     * Whether this pet is allowed to hurt {@code target} at all (assist or keep-target).
     * Blocks owned/tamed companions from any mod that uses TamableAnimal / OwnableEntity.
     */
    public static boolean wantsPetToAttack(TamableAnimal pet, @Nullable LivingEntity target) {
        if (target == null || !target.isAlive() || target == pet) {
            return false;
        }
        if (!MyUtils.isValidAggroTarget(target)) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(pet, target)) {
            return false;
        }
        if (MyUtils.isRoyalty(target)) {
            return false;
        }
        LivingEntity owner = pet.getOwner();
        if (owner != null && target == owner) {
            return false;
        }
        // OreSpawn jealousy: BF/GF may fight other BF/GF even when both are tamed.
        boolean jealousyOk = isPartnerJealousyTarget(pet, target);
        if (target instanceof TamableAnimal tame && tame.isTame() && !jealousyOk) {
            return false;
        }
        if (target instanceof AbstractHorse horse && horse.isTamed()) {
            return false;
        }
        // Non-TamableAnimal owned mobs (some mod pets); TamableAnimal already handled above.
        if (!jealousyOk
                && !(target instanceof TamableAnimal)
                && target instanceof OwnableEntity ownable
                && !(target instanceof Player)) {
            UUID ownedBy = ownable.getOwnerUUID();
            if (ownedBy != null) {
                return false;
            }
        }
        if (owner instanceof Player ownerPlayer
                && target instanceof Player targetPlayer
                && !ownerPlayer.canHarmPlayer(targetPlayer)) {
            return false;
        }
        return true;
    }

    private static boolean isPartnerJealousyTarget(TamableAnimal pet, LivingEntity target) {
        if (!(pet instanceof Girlfriend || pet instanceof Boyfriend)) {
            return false;
        }
        return target instanceof Girlfriend || target instanceof Boyfriend;
    }

    /**
     * 16 blocks: OreSpawn 1.7.10 princess walking→fly follow, and “owner fled this fight”.
     * Royals with noPhysics flight must drop combat here or they chase the boss forever.
     */
    private static final double ROYAL_OWNER_FLED_DIST_SQ = 256.0;

    /** Keep fighting a live, legal target (including owner-assist deer/bosses). */
    public static boolean shouldRetainTarget(TamableAnimal pet, @Nullable LivingEntity target) {
        if (RoyalPetFollowHelper.isStayingPut(pet)) {
            return false;
        }
        return wantsPetToAttack(pet, target);
    }

    /** Royals and Stinky: airborne pets whose FollowOwner AI is off while flying. */
    private static boolean usesOwnerFledCombat(TamableAnimal pet) {
        return RoyalPetFollowHelper.isRoyalPet(pet)
                || pet instanceof Stinky
                || pet instanceof TerribleTerror;
    }

    /**
     * Stop fighting when the owner has fled, left the dimension, or the pet is below 25% health.
     * Other enhanced-targeting pets are unchanged.
     */
    public static boolean shouldRoyalAbandonCombat(TamableAnimal pet) {
        if (!pet.isTame() || !usesOwnerFledCombat(pet)) {
            return false;
        }
        if (RoyalPetFollowHelper.isStayingPut(pet)) {
            return true;
        }
        if (pet.getMaxHealth() > 0.0f && pet.getHealth() / pet.getMaxHealth() < 0.25f) {
            return true;
        }
        LivingEntity owner = pet.getOwner();
        if (owner == null) {
            return false;
        }
        if (pet.level() != owner.level()) {
            return true;
        }
        return pet.distanceToSqr(owner) > ROYAL_OWNER_FLED_DIST_SQ;
    }

    /**
     * After the owner has retreated, do not auto-hunt a leftover boss that is still in the pet's
     * search box. Owner-assist (the player hitting something) still pulls them back in.
     */
    private static boolean isRoyalAutoHuntNearOwner(TamableAnimal pet, @Nullable LivingEntity target) {
        if (target == null || !usesOwnerFledCombat(pet) || !pet.isTame()) {
            return true;
        }
        LivingEntity owner = pet.getOwner();
        if (owner == null) {
            return true;
        }
        return target.distanceToSqr(owner) <= ROYAL_OWNER_FLED_DIST_SQ;
    }

    /**
     * Call when the pet sits (or each tick while sat) so owner hits made while sat are ignored after
     * standing.
     */
    public static void syncOwnerAssistSeen(TamableAnimal pet) {
        LivingEntity owner = pet.getOwner();
        if (owner == null) {
            return;
        }
        SEEN_OWNER_COMBAT.put(
                pet.getUUID(),
                new int[] {owner.getLastHurtMobTimestamp(), owner.getLastHurtByMobTimestamp()});
    }

    public static void onPetSit(TamableAnimal pet) {
        pet.setTarget(null);
        pet.setLastHurtByMob(null);
        syncOwnerAssistSeen(pet);
    }

    /**
     * Call each server AI tick for combat pets. While sat, keeps owner-combat timestamps current so
     * standing does not resume a fight the owner started while the pet was sitting.
     */
    public static void tickPetCombat(TamableAnimal pet) {
        if (pet.isTame() && RoyalPetFollowHelper.isStayingPut(pet)) {
            syncOwnerAssistSeen(pet);
        }
    }

    /**
     * New owner-assist victim, or null. Prefers what the owner attacked, then who attacked the
     * owner.
     */
    @Nullable
    public static LivingEntity findOwnerAssistTarget(TamableAnimal pet) {
        if (!pet.isTame() || RoyalPetFollowHelper.isStayingPut(pet)) {
            syncOwnerAssistSeen(pet);
            return null;
        }
        LivingEntity owner = pet.getOwner();
        if (owner == null) {
            return null;
        }
        int[] seen = SEEN_OWNER_COMBAT.get(pet.getUUID());
        int seenHurt = seen == null ? Integer.MIN_VALUE : seen[0];
        int seenHurtBy = seen == null ? Integer.MIN_VALUE : seen[1];

        int hurtTs = owner.getLastHurtMobTimestamp();
        LivingEntity ownerVictim = owner.getLastHurtMob();
        if (hurtTs != seenHurt && wantsPetToAttack(pet, ownerVictim)) {
            SEEN_OWNER_COMBAT.put(pet.getUUID(), new int[] {hurtTs, owner.getLastHurtByMobTimestamp()});
            return ownerVictim;
        }

        int hurtByTs = owner.getLastHurtByMobTimestamp();
        LivingEntity ownerAttacker = owner.getLastHurtByMob();
        if (hurtByTs != seenHurtBy && wantsPetToAttack(pet, ownerAttacker)) {
            SEEN_OWNER_COMBAT.put(pet.getUUID(), new int[] {owner.getLastHurtMobTimestamp(), hurtByTs});
            return ownerAttacker;
        }
        LivingEntity eeeab = EeeabsMobsCompat.findOwnerAssistAttacker(owner);
        if (wantsPetToAttack(pet, eeeab)) {
            return eeeab;
        }
        LivingEntity retaliate = pet.getLastHurtByMob();
        if (EeeabsMobsCompat.isCombatMob(retaliate) && wantsPetToAttack(pet, retaliate)) {
            return retaliate;
        }
        return null;
    }

    /**
     * Standard combat target resolution for custom pet AI: drop illegal/sitting targets, prefer
     * owner-assist, then freeroam hunt.
     */
    @Nullable
    public static LivingEntity resolveCombatTarget(
            TamableAnimal pet, @Nullable LivingEntity current, Supplier<LivingEntity> findAutoHunt) {
        if (RoyalPetFollowHelper.isStayingPut(pet)) {
            syncOwnerAssistSeen(pet);
            return null;
        }
        if (shouldRoyalAbandonCombat(pet)) {
            // Forget the owner's last hit so catching up next to you does not resume the boss.
            syncOwnerAssistSeen(pet);
            return null;
        }
        if (shouldRetainTarget(pet, current)) {
            return current;
        }
        LivingEntity assist = findOwnerAssistTarget(pet);
        if (assist != null) {
            return assist;
        }
        if (pet.level().getDifficulty() == net.minecraft.world.Difficulty.PEACEFUL) {
            return null;
        }
        LivingEntity hunt = findAutoHunt.get();
        if (!isRoyalAutoHuntNearOwner(pet, hunt)) {
            return null;
        }
        return hunt;
    }

    /** Predicate for {@link net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal}. */
    public static boolean isNearestHostileGoalTarget(LivingEntity living) {
        return isAutoHostileTarget(living);
    }

    public static boolean canUseNearestHostileGoal(Mob mob) {
        if (mob instanceof TamableAnimal tame && RoyalPetFollowHelper.isStayingPut(tame)) {
            return false;
        }
        return true;
    }
}
