package com.astryxion.chaospersists.core;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Registers mod sounds in 1.12.2. Uses static EventBusSubscriber so we are on the
 * event bus as soon as the mod loads, ensuring RegistryEvent.Register&lt;SoundEvent&gt;
 * is received (it is fired after preInit; having a static subscriber guarantees we're registered).
 */
@Mod.EventBusSubscriber(modid = "chaospersists")
public class RegistrySoundHandler {

    @SubscribeEvent
    public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> event) {
        ChaosSounds.registerSounds(event);
    }
}
