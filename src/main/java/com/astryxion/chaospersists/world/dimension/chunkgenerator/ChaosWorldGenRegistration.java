package com.astryxion.chaospersists.world.dimension.chunkgenerator;

import net.neoforged.fml.common.EventBusSubscriber;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ChaosWorldGenRegistration {

    private ChaosWorldGenRegistration() {}

    @SubscribeEvent
    public static void registerChunkGenerators(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.CHUNK_GENERATOR)) {
            event.register(
                    Registries.CHUNK_GENERATOR,
                    ResourceLocation.fromNamespaceAndPath(ChaosPersists.MODID, "plains_terrain"),
                    () -> ChaosPlainsChunkGenerator.CODEC);
            event.register(
                    Registries.CHUNK_GENERATOR,
                    ResourceLocation.fromNamespaceAndPath(ChaosPersists.MODID, "sky_islands"),
                    () -> ChaosIslandsChunkGenerator.CODEC);
        }
        if (event.getRegistryKey().equals(Registries.FEATURE)) {
            event.register(
                    Registries.FEATURE,
                    ResourceLocation.fromNamespaceAndPath(ChaosPersists.MODID, "village_foundation_fill"),
                    () -> new com.astryxion.chaospersists.world.dimension.structure.VillageFoundationFillFeature(
                            net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration.CODEC));
        }
    }
}
