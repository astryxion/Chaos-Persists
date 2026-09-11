package com.astryxion.chaospersists.compat.illageandspillage;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.QueenHead;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.item.ThunderBolt;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
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
 * Magispeller {@code die()} starts a custom death instead of removing (totem at tick 80, actual
 * remove at 300). Queen's melee execute can miss because she also kills with beams / fireballs /
 * jump damage, then drops the target once {@code isAlive()} is false. This compat force-removes a
 * Magispeller in that sequence when the Queen is involved.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class IllageAndSpillageExecuteCompat {

    public static final String ILLAGE_AND_SPILLAGE_MODID = "illageandspillage";

    private static boolean checked;
    private static boolean present;

    private IllageAndSpillageExecuteCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(ILLAGE_AND_SPILLAGE_MODID);
            checked = true;
        }
        return present;
    }

    public static boolean isResurrectionMob(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        String simple = entity.getClass().getSimpleName();
        if ("MagispellerEntity".equals(simple) || "OldMagispellerEntity".equals(simple)) {
            return true;
        }
        if (!isPresent()) {
            return false;
        }
        ResourceLocation id = entityId(entity);
        if (id == null || !ILLAGE_AND_SPILLAGE_MODID.equals(id.getNamespace())) {
            return false;
        }
        String path = id.getPath();
        return "magispeller".equals(path) || "old_magispeller".equals(path);
    }

    public static void prepareExecute(@Nullable LivingEntity living) {
        if (!isResurrectionMob(living) || living.level().isClientSide) {
            return;
        }
        invokeBoolean("setCustomDeath", living, false);
        invokeBoolean("setFaking", living, false);
        living.setHealth(living.getHealth() - 1.0e12f);
        discardClones(living);
        discardFieldEntity(living, "totem");
        discardNearbyFakers(living);
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
        return !living.isAlive()
                || living.getHealth() <= 0.0f
                || living.deathTime > 0
                || invokeIsCustomDeath(living);
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

    private static boolean invokeIsCustomDeath(LivingEntity living) {
        Class<?> type = living.getClass();
        while (type != null && type != Object.class) {
            try {
                Method method = type.getDeclaredMethod("isCustomDeath");
                method.setAccessible(true);
                Object value = method.invoke(living);
                return value instanceof Boolean flag && flag;
            } catch (NoSuchMethodException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return false;
            }
        }
        return false;
    }

    private static void invokeBoolean(String name, LivingEntity living, boolean value) {
        Class<?> type = living.getClass();
        while (type != null && type != Object.class) {
            try {
                Method method = type.getDeclaredMethod(name, boolean.class);
                method.setAccessible(true);
                method.invoke(living, value);
                return;
            } catch (NoSuchMethodException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return;
            }
        }
    }

    private static void discardClones(LivingEntity living) {
        Class<?> type = living.getClass();
        while (type != null && type != Object.class) {
            try {
                Field field = type.getDeclaredField("clones");
                field.setAccessible(true);
                Object value = field.get(living);
                if (value instanceof Collection<?> clones) {
                    for (Object clone : clones) {
                        if (clone instanceof Entity entity && !entity.isRemoved()) {
                            entity.discard();
                        }
                    }
                    clones.clear();
                }
                return;
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return;
            }
        }
    }

    private static void discardFieldEntity(LivingEntity living, String fieldName) {
        Class<?> type = living.getClass();
        while (type != null && type != Object.class) {
            try {
                Field field = type.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(living);
                if (value instanceof Entity entity && !entity.isRemoved()) {
                    entity.discard();
                }
                return;
            } catch (NoSuchFieldException ignored) {
                type = type.getSuperclass();
            } catch (ReflectiveOperationException ignored) {
                return;
            }
        }
    }

    private static void discardNearbyFakers(LivingEntity living) {
        AABB box = living.getBoundingBox().inflate(64.0);
        for (LivingEntity nearby : living.level().getEntitiesOfClass(LivingEntity.class, box)) {
            if (nearby == living || nearby.isRemoved()) {
                continue;
            }
            String simple = nearby.getClass().getSimpleName();
            if ("FakeMagispellerEntity".equals(simple)) {
                nearby.discard();
                continue;
            }
            ResourceLocation id = entityId(nearby);
            if (id != null
                    && ILLAGE_AND_SPILLAGE_MODID.equals(id.getNamespace())
                    && "faker".equals(id.getPath())) {
                nearby.discard();
            }
        }
    }
}
