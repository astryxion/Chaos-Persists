package com.astryxion.chaospersists.world.dimension;

import com.astryxion.chaospersists.world.dimension.chunkprovider.ChunkProviderChaos5;
import com.astryxion.chaospersists.world.dimension.chunkprovider.ChunkProviderChaos6;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Registers custom {@link ChunkGenerator} codecs so dimension JSON can use 1.12.2 terrain
 * (crystal blocks / floating chaos islands) instead of vanilla {@code minecraft:noise}.
 */
@Mod.EventBusSubscriber(modid = "chaospersists", bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ChaosChunkGeneratorRegistration {

    private ChaosChunkGeneratorRegistration() {
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation("chaospersists", "crystal"), ChunkProviderChaos5.CODEC);
            Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation("chaospersists", "chaos"), ChunkProviderChaos6.CODEC);
        });
    }
}
