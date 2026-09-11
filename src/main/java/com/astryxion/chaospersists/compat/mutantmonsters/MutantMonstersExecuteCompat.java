package com.astryxion.chaospersists.compat.mutantmonsters;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.QueenHead;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.item.ThunderBolt;
import java.lang.reflect.Method;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Mutant Zombie {@code die()} never calls {@code super.die()}. It stays at 0 HP and
 * {@code tickDeath} restores {@code max/4} if lives remain. Queen's melee execute can miss because
 * she also kills with beams / fireballs / jump damage, then drops the target once
 * {@code isAlive()} is false. This compat force-removes a downed Mutant Zombie when the Queen is
 * involved.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class MutantMonstersExecuteCompat {

    public static final String MUTANT_MONSTERS_MODID = "mutantmonsters";
    public static final String MUTANT_BEASTS_MODID = "mutantbeasts";

    private static boolean checked;
    private static boolean present;

    private MutantMonstersExecuteCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(MUTANT_MONSTERS_MODID)
                    || ModList.get().isLoaded(MUTANT_BEASTS_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isResurrectionMob(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        String className = entity.getClass().getName();
        if (className.endsWith(".MutantZombie") || className.endsWith(".MutantZombieEntity")) {
            return true;
        }
        if (!isPresent()) {
            return false;
        }
        ResourceLocation id = entityId(entity);
        if (id == null) {
            return false;
        }
        String namespace = id.getNamespace();
        String path = id.getPath();
        return (MUTANT_MONSTERS_MODID.equals(namespace) || MUTANT_BEASTS_MODID.equals(namespace))
                && path.contains("mutant_zombie");
    }

    public static void prepareExecute(@Nullable LivingEntity living) {
        if (!isResurrectionMob(living) || living.level().isClientSide) {
            return;
        }
        invokeSetLives(living, 0);
    }

    public static void forceExecute(@Nullable LivingEntity living) {
        if (!isResurrectionMob(living) || living.level().isClientSide || living.isRemoved()) {
            return;
        }
        prepareExecute(living);
        living.remove(RemovalReason.KILLED);
        if (!living.isRemoved()) {
            living.discard();
        }
    }

    public static void forceExecuteIfDowned(@Nullable LivingEntity living) {
        if (living == null || living.level().isClientSide || living.isRemoved()) {
            return;
        }
        if (isResurrectionMob(living) && isDowned(living)) {
            forceExecute(living);
        }
    }

    private static boolean isDowned(LivingEntity living) {
        return !living.isAlive() || living.getHealth() <= 0.0f || living.deathTime > 0;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();
        if (living.level().isClientSide || living.isRemoved() || !isResurrectionMob(living)) {
            return;
        }
        if (!isDowned(living) || !isQueenInvolved(living)) {
            return;
        }
        forceExecute(living);
        if (event.isCancelable()) {
            event.setCanceled(true);
        }
    }

    private static boolean isQueenInvolved(LivingEntity living) {
        if (isQueenRelated(living.getLastHurtByMob()) || isQueenRelated(living.getKillCredit())) {
            return true;
        }
        AABB box = living.getBoundingBox().inflate(160.0);
        return !living.level().getEntitiesOfClass(TheQueen.class, box, TheQueen::isAlive).isEmpty();
    }

    private static boolean isQueenRelated(@Nullable Entity entity) {
        return entity instanceof TheQueen
                || entity instanceof QueenHead
                || entity instanceof PurplePower
                || entity instanceof ThunderBolt
                || entity instanceof BetterFireball;
    }

    @Nullable
    private static ResourceLocation entityId(Entity entity) {
        ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(entity.getType());
        return id != null ? id : EntityType.getKey(entity.getType());
    }

    private static void invokeSetLives(LivingEntity living, int lives) {
        Class<?> type = living.getClass();
        while (type != null && type != Object.class) {
            try {
                Method method = type.getDeclaredMethod("setLives", int.class);
                method.setAccessible(true);
                method.invoke(living, lives);
                return;
            } catch (NoSuchMethodException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return;
            }
        }
    }
}
