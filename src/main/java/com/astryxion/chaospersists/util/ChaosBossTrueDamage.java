package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * True-damage that skips {@code TheKing.hurt} ({@code setHealth}, Vzling {@code forceHurt}) never
 * hits the 750 / 120 caps or the 20-tick hurt timer. Clamp those HP dumps to the same 1.7.10
 * curve Cataclysm / EEEAB already use.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ChaosBossTrueDamage {

    public static final float WEAPON_CAP = 40.0f;

    private static final String[] TRUE_DAMAGE_NAMESPACES = {
        "hadean_breathe",
        "the_forgotten_dimensions",
        "torchesbecomesunlight",
        "rosmontislib"
    };

    private static final WeakHashMap<LivingEntity, Float> LAST_HEALTH = new WeakHashMap<>();
    private static final WeakHashMap<LivingEntity, Integer> IFRAME_UNTIL = new WeakHashMap<>();

    private ChaosBossTrueDamage() {}

    public static float applyDefense(LivingEntity victim, float amount) {
        float weapon = Math.min(amount, WEAPON_CAP);
        int armor = Mth.clamp(victim.getArmorValue(), 0, 25);
        if (armor <= 0) {
            return weapon;
        }
        return Math.max(0.0f, weapon * (25.0f - (float) armor) / 25.0f);
    }

    public static boolean isListedTrueDamageMod(@Nullable Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof PartEntity<?> part) {
            entity = part.getParent();
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        if (id == null) {
            return false;
        }
        return isListedNamespace(id.getNamespace());
    }

    public static boolean isListedTrueDamage(@Nullable DamageSource source) {
        if (source == null) {
            return false;
        }
        if (isListedTrueDamageMod(source.getEntity()) || isListedTrueDamageMod(source.getDirectEntity())) {
            return true;
        }
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> isListedNamespace(id.getNamespace()))
                .orElse(false);
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity victim = event.getEntity();
        if (victim == null || victim.level().isClientSide() || !ChaosBosses.isBoss(victim)) {
            return;
        }
        float now = victim.getHealth();
        Float prev = LAST_HEALTH.put(victim, now);
        if (prev == null) {
            return;
        }
        float lost = prev - now;
        if (lost <= WEAPON_CAP) {
            return;
        }
        if (!isListedTrueDamageMod(victim.getLastHurtByMob())
                && !isListedTrueDamage(victim.getLastDamageSource())) {
            return;
        }
        Integer until = IFRAME_UNTIL.get(victim);
        if (until != null && victim.tickCount < until) {
            victim.setHealth(prev);
            LAST_HEALTH.put(victim, prev);
            return;
        }
        float allowed = applyDefense(victim, lost);
        float restored = prev - allowed;
        if (restored > now) {
            victim.setHealth(restored);
            LAST_HEALTH.put(victim, restored);
        }
        IFRAME_UNTIL.put(victim, victim.tickCount + 20);
    }

    private static boolean isListedNamespace(String namespace) {
        for (String id : TRUE_DAMAGE_NAMESPACES) {
            if (id.equals(namespace)) {
                return true;
            }
        }
        return false;
    }
}
