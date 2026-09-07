package com.astryxion.chaospersists.world.dimension;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Utopia dimension client effects (ported from Craziness Awakened {@code UtopiaDimension}).
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class UtopiaDimension {
    public static final ResourceLocation UTOPIA_EFFECTS =
            new ResourceLocation(ChaosPersists.MODID, "utopia");

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
