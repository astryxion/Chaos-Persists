package com.astryxion.chaospersists.compat.cataclysm;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ChaosBosses;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * L_Ender's Cataclysm applies {@code cataclysm:blazing_brand} with {@code addEffect}.
 * Chaos Persists bosses and high-tier armor reject that effect when Cataclysm is loaded.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class CataclysmBlazingBrandCompat {

    public static final String CATACLYSM_MODID = "cataclysm";
    private static final ResourceLocation BLAZING_BRAND =
            new ResourceLocation(CATACLYSM_MODID, "blazing_brand");

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
        if (instance == null || !isBlazingBrand(instance.getEffect())) {
            return;
        }
        LivingEntity entity = event.getEntity();
        if (entity != null
                && (ChaosBosses.isBoss(entity) || CataclysmHighTierGear.immuneToBlazingBrand(entity))) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (!isPresent() || event.getEntity() == null || event.getEntity().level().isClientSide) {
            return;
        }
        LivingEntity entity = event.getEntity();
        boolean stripBrand = CataclysmHighTierGear.immuneToBlazingBrand(entity)
                || ChaosBosses.isBoss(entity);
        if (entity.tickCount % 20 != 0 || !stripBrand) {
            return;
        }
        MobEffect effect = ForgeRegistries.MOB_EFFECTS.getValue(BLAZING_BRAND);
        if (effect != null && entity.hasEffect(effect)) {
            entity.removeEffect(effect);
        }
    }

    private static boolean isBlazingBrand(MobEffect effect) {
        if (effect == null) {
            return false;
        }
        ResourceLocation id = ForgeRegistries.MOB_EFFECTS.getKey(effect);
        return BLAZING_BRAND.equals(id);
    }
}
