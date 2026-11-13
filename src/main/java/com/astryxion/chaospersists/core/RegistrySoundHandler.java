package com.astryxion.chaospersists.core;

import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

/**
 * Registers mod sounds in 1.20.1. Static EventBusSubscriber so we are on the
 * mod bus as soon as the mod loads, ensuring RegisterEvent for SoundEvent
 * is received (fired during registry setup; static subscriber guarantees registration).
 */
@EventBusSubscriber(modid = ChaosPersists.MODID, bus = EventBusSubscriber.Bus.MOD)
public class RegistrySoundHandler {

    @SubscribeEvent
    public static void onRegisterSounds(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.SOUND_EVENT)) {
            ChaosSounds.registerSounds(event);
        }
    }
}
