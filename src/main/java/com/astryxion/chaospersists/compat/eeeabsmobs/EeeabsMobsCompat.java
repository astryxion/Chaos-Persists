package com.astryxion.chaospersists.compat.eeeabsmobs;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

/**
 * EEEAB's Mobs bosses (Immortal, Nameless Guardian, Relicron, etc.) often hit through lasers /
 * projectiles and have huge hitboxes. OreSpawn pets otherwise drop them: Mob Battle team rules skip
 * unteamed targets, owner-assist looks at {@code lastHurtByMob} (often the beam, not the boss), and
 * Girlfriend/Boyfriend {@code nearbyOnly} pathing fails on a 2.8-wide Immortal.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class EeeabsMobsCompat {

    public static final String EEEABSMOBS_MODID = "eeeabsmobs";

    private static final Map<UUID, AssistHit> OWNER_HITS = new WeakHashMap<>();

    private static boolean checked;
    private static boolean present;

    private EeeabsMobsCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(EEEABSMOBS_MODID);
            checked = true;
        }
        return present;
    }

    /** Living EEEAB hostile (Immortal, Nameless Guardian, Relicron, etc. — not lasers). */
    public static boolean isCombatMob(@Nullable Entity entity) {
        if (!isPresent() || !(entity instanceof LivingEntity living) || !living.isAlive()) {
            return false;
        }
        if (!(living instanceof Enemy)) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(living.getType());
        return id != null && EEEABSMOBS_MODID.equals(id.getNamespace());
    }

    /**
     * Girlfriend/Boyfriend hunt via {@code targetSelector}. Mob Battle strips those goals when the
     * pet is teamed, so this is the fallback scan.
     */
    @Nullable
    public static LivingEntity findNearbyHunt(Mob hunter) {
        if (!isPresent() || hunter == null || hunter.level().isClientSide) {
            return null;
        }
        LivingEntity best = null;
        double bestDist = 24.0 * 24.0;
        AABB box = hunter.getBoundingBox().inflate(24.0, 12.0, 24.0);
        for (LivingEntity candidate :
                hunter.level().getEntitiesOfClass(LivingEntity.class, box, EeeabsMobsCompat::isCombatMob)) {
            if (candidate == hunter) {
                continue;
            }
            if (hunter instanceof TamableAnimal tame) {
                if (!PetCombatHelper.wantsPetToAttack(tame, candidate)) {
                    continue;
                }
            } else if (MyUtils.shouldSkipCombatTarget(hunter, candidate)) {
                continue;
            }
            double dist = hunter.distanceToSqr(candidate);
            if (dist < bestDist) {
                bestDist = dist;
                best = candidate;
            }
        }
        return best;
    }

    /**
     * Friends use vanilla panic when hurt. Without this they flee the Immortal instead of swinging.
     * No-ops when EEEAB's Mobs is absent.
     */
    public static PanicGoal panicGoal(PathfinderMob mob, double speed) {
        return new PanicGoal(mob, speed) {
            @Override
            public boolean canUse() {
                if (isCombatMob(mob.getLastHurtByMob()) || isCombatMob(mob.getTarget())) {
                    return false;
                }
                return super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                if (isCombatMob(mob.getLastHurtByMob()) || isCombatMob(mob.getTarget())) {
                    return false;
                }
                return super.canContinueToUse();
            }
        };
    }

    /**
     * Teamed OreSpawn pets should still fight EEEAB hostiles that are not on their team. Mob Battle
     * {@code canTargetEntity} requires both sides teamed; the Immortal usually is not.
     */
    public static boolean shouldAllowTeamedHunt(@Nullable Entity attacker, @Nullable LivingEntity target) {
        if (!isCombatMob(target) || attacker == null) {
            return false;
        }
        return !attacker.isAlliedTo(target);
    }

    /** Extra melee reach so Girlfriend/Boyfriend can hit a 2.8-wide Immortal from the AABB edge. */
    public static float extraMeleeReach(@Nullable LivingEntity target) {
        if (!isCombatMob(target)) {
            return 0.0f;
        }
        return target.getBbWidth() * 0.5f;
    }

    /**
     * Girlfriend {@code nearbyOnly} pathing measures the path end against the boss origin. A 2.8-wide
     * Immortal never counts as "easily reachable".
     */
    public static boolean skipNearbyOnlyReachCheck(@Nullable LivingEntity target) {
        return isCombatMob(target);
    }

    @Nullable
    public static LivingEntity findOwnerAssistAttacker(@Nullable LivingEntity owner) {
        if (!isPresent() || owner == null) {
            return null;
        }
        AssistHit hit = OWNER_HITS.get(owner.getUUID());
        if (hit == null || hit.attacker() == null || !hit.attacker().isAlive()) {
            return null;
        }
        if (owner.tickCount - hit.tick() > 200) {
            return null;
        }
        if (hit.attacker().level() != owner.level()) {
            return null;
        }
        return hit.attacker();
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!isPresent() || event.isCanceled() || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        if (victim == null || victim.level().isClientSide) {
            return;
        }
        LivingEntity attacker = resolveCombatMob(event.getSource());
        if (attacker == null || attacker == victim) {
            return;
        }
        if (victim instanceof Player) {
            OWNER_HITS.put(victim.getUUID(), new AssistHit(attacker, victim.tickCount));
        }
        victim.setLastHurtByMob(attacker);
    }

    @Nullable
    private static LivingEntity resolveCombatMob(@Nullable DamageSource source) {
        if (source == null) {
            return null;
        }
        LivingEntity fromCausing = asCombatMob(source.getEntity());
        if (fromCausing != null) {
            return fromCausing;
        }
        return asCombatMob(source.getDirectEntity());
    }

    @Nullable
    private static LivingEntity asCombatMob(@Nullable Entity entity) {
        if (entity == null) {
            return null;
        }
        if (isCombatMob(entity)) {
            return (LivingEntity) entity;
        }
        if (entity instanceof PartEntity<?> part && isCombatMob(part.getParent())) {
            return (LivingEntity) part.getParent();
        }
        if (entity instanceof Projectile projectile && isCombatMob(projectile.getOwner())) {
            return (LivingEntity) projectile.getOwner();
        }
        if (entity instanceof TraceableEntity traceable && isCombatMob(traceable.getOwner())) {
            return (LivingEntity) traceable.getOwner();
        }
        if (entity instanceof OwnableEntity ownable && isCombatMob(ownable.getOwner())) {
            return ownable.getOwner();
        }
        return null;
    }

    private record AssistHit(LivingEntity attacker, int tick) {}
}
