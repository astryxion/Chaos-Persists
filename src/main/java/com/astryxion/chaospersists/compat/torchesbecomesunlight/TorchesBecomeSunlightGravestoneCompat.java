package com.astryxion.chaospersists.compat.torchesbecomesunlight;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Gathering Torches Become Sunlight Gravestone (phase two) is built around players: a 40-damage /
 * 3-second window on {@code hurt} and {@code setHealth}, melee lifesteal, and
 * {@code HalveHealthBossFight} {@code boss_fight_health_halve} (-50% max HP / armor / attack).
 * Queen purple {@code setHealth} slices get eaten by that window, and OreSpawn bosses are not
 * meant to be halved or farmed for lifesteal.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class TorchesBecomeSunlightGravestoneCompat {

    private static final String HALVE_MODIFIER = "boss_fight_health_halve";
    private static final Attribute[] HALVE_ATTRIBUTES = {
        Attributes.MAX_HEALTH, Attributes.ARMOR, Attributes.ARMOR_TOUGHNESS, Attributes.ATTACK_DAMAGE
    };

    private static EntityDataAccessor<Float> healthId;
    private static boolean healthIdResolved;

    private TorchesBecomeSunlightGravestoneCompat() {}

    public static boolean isGravestone(@Nullable Entity entity) {
        if (!TorchesBecomeSunlightDamageCompat.isPresent() || entity == null) {
            return false;
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null
                && TorchesBecomeSunlightDamageCompat.TORCHES_BECOME_SUNLIGHT_MODID.equals(id.getNamespace())
                && "gravestone".equals(id.getPath());
    }

    /**
     * Queen / King purple beams {@code setHealth} a percent slice. Gravestone reroutes losses
     * through {@code setHealthDamageCalculator} (40 / 3s) unless the last source is
     * {@code generic_kill}. A follow-up {@code genericKill} hit is already exempt from the
     * rolling cap; the health-id write is the fallback if {@code setHealth} still clamps.
     */
    public static void enforceBeamHealth(@Nullable LivingEntity target, float intendedHealth) {
        if (!isGravestone(target) || target.level().isClientSide) {
            return;
        }
        float intended = Mth.clamp(intendedHealth, 0.0f, target.getMaxHealth());
        if (target.getHealth() <= intended + 0.05f) {
            return;
        }
        clearDamageRecords(target);
        float lost = target.getHealth() - intended;
        target.hurt(target.damageSources().genericKill(), lost);
        if (target.getHealth() <= intended + 0.05f) {
            return;
        }
        setHealthDirect(target, intended);
        if (intended <= 0.0f && target.isAlive()) {
            target.hurt(target.damageSources().genericKill(), Float.MAX_VALUE);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!TorchesBecomeSunlightDamageCompat.isPresent() || event == null) {
            return;
        }
        LivingEntity living = event.getEntity();
        if (living == null || living.level() == null || living.level().isClientSide) {
            return;
        }
        if (!ChaosBosses.isBoss(living)) {
            return;
        }
        stripHalveHealth(living);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHeal(LivingHealEvent event) {
        if (!TorchesBecomeSunlightDamageCompat.isPresent()
                || event == null
                || event.isCanceled()
                || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity healer = event.getEntity();
        if (!isGravestone(healer) || healer.level() == null || healer.level().isClientSide) {
            return;
        }
        LivingEntity last = healer.getLastHurtMob();
        LivingEntity aimed = healer instanceof Mob mob ? mob.getTarget() : null;
        if (ChaosBosses.isBoss(last) || ChaosBosses.isBoss(aimed)) {
            event.setCanceled(true);
        }
    }

    private static void stripHalveHealth(LivingEntity living) {
        float oldMax = living.getMaxHealth();
        float oldHp = living.getHealth();
        boolean removed = false;
        for (Attribute attribute : HALVE_ATTRIBUTES) {
            AttributeInstance instance = living.getAttribute(attribute);
            if (instance == null) {
                continue;
            }
            List<AttributeModifier> copy = new ArrayList<>(instance.getModifiers());
            for (AttributeModifier modifier : copy) {
                if (modifier != null && HALVE_MODIFIER.equals(modifier.getName())) {
                    instance.removeModifier(modifier.getId());
                    removed = true;
                }
            }
        }
        if (!removed || oldMax <= 0.0f) {
            return;
        }
        float newMax = living.getMaxHealth();
        if (newMax > oldMax + 0.05f) {
            living.setHealth(Mth.clamp(oldHp * (newMax / oldMax), 1.0f, newMax));
        }
    }

    private static void clearDamageRecords(LivingEntity gravestone) {
        invokeClear(gravestone, "getDamageCalculator");
        invokeClear(gravestone, "getHealthDamageCalculator");
    }

    private static void invokeClear(LivingEntity gravestone, String getter) {
        try {
            Method method = gravestone.getClass().getMethod(getter);
            Object calculator = method.invoke(gravestone);
            if (calculator == null) {
                return;
            }
            Method clear = calculator.getClass().getMethod("clearRecords");
            clear.invoke(calculator);
        } catch (ReflectiveOperationException ignored) {
        }
    }

    @SuppressWarnings("unchecked")
    private static void setHealthDirect(LivingEntity target, float health) {
        EntityDataAccessor<Float> accessor = healthAccessor();
        if (accessor == null) {
            return;
        }
        target.getEntityData().set(accessor, Mth.clamp(health, 0.0f, target.getMaxHealth()));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private static EntityDataAccessor<Float> healthAccessor() {
        if (healthIdResolved) {
            return healthId;
        }
        healthIdResolved = true;
        for (String name : new String[] {"DATA_HEALTH_ID", "f_20961_"}) {
            try {
                Field field = LivingEntity.class.getDeclaredField(name);
                field.setAccessible(true);
                healthId = (EntityDataAccessor<Float>) field.get(null);
                return healthId;
            } catch (ReflectiveOperationException ignored) {
            }
        }
        return null;
    }
}
