package com.astryxion.chaospersists.world.dimension.chunkgenerator;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ChaosWorldGenRegistration {

    private ChaosWorldGenRegistration() {}

    @SubscribeEvent
    public static void registerChunkGenerators(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.CHUNK_GENERATOR)) {
            event.register(
                    Registries.CHUNK_GENERATOR,
                    new ResourceLocation(ChaosPersists.MODID, "plains_terrain"),
                    () -> ChaosPlainsChunkGenerator.CODEC);
            event.register(
                    Registries.CHUNK_GENERATOR,
                    new ResourceLocation(ChaosPersists.MODID, "sky_islands"),
                    () -> ChaosIslandsChunkGenerator.CODEC);
        }
        if (event.getRegistryKey().equals(Registries.FEATURE)) {
            event.register(
                    Registries.FEATURE,
                    new ResourceLocation(ChaosPersists.MODID, "village_foundation_fill"),
                    () -> new com.astryxion.chaospersists.world.dimension.structure.VillageFoundationFillFeature(
                            net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration.CODEC));
        }
    }
}
