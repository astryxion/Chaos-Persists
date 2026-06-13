package com.astryxion.chaospersists.util;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyHandler {
    public static final String KEY_CATEGORY = "key.categories.chaospersists";
    public static final KeyBinding KEY_FLY_UP = new KeyBinding(
            "chaospersists UP/FAST",
            KeyConflictContext.IN_GAME,
            InputMappings.Type.KEYSYM,
            56,
            KEY_CATEGORY);

    public KeyHandler() {
        ClientRegistry.registerKeyBinding(KEY_FLY_UP);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
    }
}
