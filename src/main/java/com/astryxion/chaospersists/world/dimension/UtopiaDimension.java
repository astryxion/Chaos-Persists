package com.astryxion.chaospersists.world.dimension;

import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

/**
 * Utopia dimension client effects (ported from Craziness Awakened {@code UtopiaDimension}).
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class UtopiaDimension {
    public static final ResourceLocation UTOPIA_EFFECTS =
            ResourceLocation.fromNamespaceAndPath(ChaosPersists.MODID, "utopia");

    private UtopiaDimension() {}

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
        DimensionSpecialEffects utopiaEffects =
                new DimensionSpecialEffects(130.0F, true, DimensionSpecialEffects.SkyType.NORMAL, false, false) {
                    @Override
                    public Vec3 getBrightnessDependentFogColor(Vec3 color, float sunHeight) {
                        return color.multiply(
                                sunHeight * 0.94D + 0.06D,
                                sunHeight * 0.94D + 0.06D,
                                sunHeight * 0.91D + 0.09D);
                    }

                    @Override
                    public boolean isFoggyAt(int x, int y) {
                        return false;
                    }
                };
        event.register(UTOPIA_EFFECTS, utopiaEffects);
    }
}
