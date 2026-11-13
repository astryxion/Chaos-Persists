package com.astryxion.chaospersists.compat.cataclysm;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * L_Ender's Cataclysm applies {@code cataclysm:blazing_brand} with {@code addEffect}.
 * Chaos Persists bosses and high-tier armor reject that effect when Cataclysm is loaded.
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class CataclysmBlazingBrandCompat {

    public static final String CATACLYSM_MODID = "cataclysm";
    private static final ResourceLocation BLAZING_BRAND =
            ResourceLocation.fromNamespaceAndPath(CATACLYSM_MODID, "blazing_brand");

    private static boolean checked;
    private static boolean present;

    private CataclysmBlazingBrandCompat() {}

    public static boolean isPresent() {
        if (!checked) {
            present = ModList.get().isLoaded(CATACLYSM_MODID);
            checked = true;
        }
        return present;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        if (!isPresent()) {
            return;
        }
        MobEffectInstance instance = event.getEffectInstance();
        if (instance == null || !isBlazingBrand(instance.getEffect().value())) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity != null
                && (ChaosBosses.isBoss(entity) || CataclysmHighTierGear.immuneToBlazingBrand(entity))) {
            event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof net.minecraft.world.entity.LivingEntity)) return;
        if (!isPresent() || event.getEntity() == null || event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity entity = (LivingEntity) event.getEntity();
        boolean stripBrand = CataclysmHighTierGear.immuneToBlazingBrand(entity)
                || ChaosBosses.isBoss(entity);
        if (entity.tickCount % 20 != 0 || !stripBrand) {
            return;
        }
        MobEffect effect = BuiltInRegistries.MOB_EFFECT.get(BLAZING_BRAND);
        if (effect != null && entity.hasEffect(net.minecraft.core.Holder.direct(effect))) {
            entity.removeEffect(net.minecraft.core.Holder.direct(effect));
        }
    }

    private static boolean isBlazingBrand(MobEffect effect) {
        if (effect == null) {
            return false;
        }
        ResourceLocation id = BuiltInRegistries.MOB_EFFECT.getKey(effect);
        return BLAZING_BRAND.equals(id);
    }
}
