package com.astryxion.chaospersists.compat.eeeabsmobs;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * EEEAB bosses add {@code target.maxHealth * mobLevel%} on every hit. Realmwarden smash / Guardian
 * laser also use {@code eeeabsmobs:bypass_armor} (or vanilla {@code indirectMagic}), so OreSpawn
 * {@code getArmorValue()} never runs. Cataclysm flame strike already goes through the 1.7.10
 * defense curve; this does the same for EEEAB bypass hits on Chaos bosses.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class EeeabsMobsDamageCompat {

    private static final float BOSS_WEAPON_DAMAGE_CAP = 40.0f;

    private EeeabsMobsDamageCompat() {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!EeeabsMobsCompat.isPresent()
                || event.isCanceled()
                || event.getAmount() <= 0.0f) {
            return;
        }
        LivingEntity victim = event.getEntity();
        DamageSource source = event.getSource();
        if (victim == null || victim.level() == null || victim.level().isClientSide || source == null) {
            return;
        }
        if (!ChaosBosses.isBoss(victim) || !isEeeabsDamage(source) || !bypassesDefense(source)) {
            return;
        }
        event.setAmount(applyBossDefense(victim, event.getAmount()));
    }

    private static float applyBossDefense(LivingEntity victim, float amount) {
        float weapon = Math.min(amount, BOSS_WEAPON_DAMAGE_CAP);
        int armor = Mth.clamp(victim.getArmorValue(), 0, 25);
        if (armor <= 0) {
            return weapon;
        }
        return Math.max(0.0f, weapon * (25.0f - (float) armor) / 25.0f);
    }

    private static boolean isEeeabsDamage(DamageSource source) {
        if (isEeeabsEntity(source.getEntity()) || isEeeabsEntity(source.getDirectEntity())) {
            return true;
        }
        return source
                .typeHolder()
                .unwrapKey()
                .map(ResourceKey::location)
                .map(id -> EeeabsMobsCompat.EEEABSMOBS_MODID.equals(id.getNamespace()))
                .orElse(false);
    }

    private static boolean isEeeabsEntity(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof PartEntity<?> part) {
            entity = part.getParent();
        }
        ResourceLocation id = EntityType.getKey(entity.getType());
        return id != null && EeeabsMobsCompat.EEEABSMOBS_MODID.equals(id.getNamespace());
    }

    private static boolean bypassesDefense(DamageSource source) {
        return source.is(DamageTypeTags.BYPASSES_ARMOR)
                || source.is(DamageTypes.MAGIC)
                || source.is(DamageTypes.INDIRECT_MAGIC);
    }
}
