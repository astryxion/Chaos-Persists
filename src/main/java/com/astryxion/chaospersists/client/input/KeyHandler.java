package com.astryxion.chaospersists.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class KeyHandler {
    public static final String KEY_CATEGORY = "key.categories.chaospersists";

    /** Jump = fly up, matching Ice and Fire. Rebindable in Controls → Chaos Persists. */
    public static final KeyMapping KEY_FLY_UP =
            new KeyMapping(
                    "key.chaospersists.dragon_up",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_SPACE,
                    KEY_CATEGORY);

    /** Ice and Fire {@code dragon_down} — GLFW 88 / X. */
    public static final KeyMapping KEY_FLY_DOWN =
            new KeyMapping(
                    "key.chaospersists.dragon_down",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_X,
                    KEY_CATEGORY);

    /** Ice and Fire {@code dragon_fireAttack} (R). Dragon small fireball / prince cycle. */
    public static final KeyMapping KEY_MOUNT_FIRE_LEFT =
            new KeyMapping(
                    "key.chaospersists.mount_fire_left",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_R,
                    KEY_CATEGORY);

    /** Ice and Fire {@code dragon_strike} (G). Dragon big fireball / prince cycle. */
    public static final KeyMapping KEY_MOUNT_FIRE_RIGHT =
            new KeyMapping(
                    "key.chaospersists.mount_fire_right",
                    InputConstants.Type.KEYSYM,
                    InputConstants.KEY_G,
                    KEY_CATEGORY);

    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KEY_FLY_UP);
        event.register(KEY_FLY_DOWN);
        event.register(KEY_MOUNT_FIRE_LEFT);
        event.register(KEY_MOUNT_FIRE_RIGHT);
    }

    /**
     * Minecraft only stores one {@link KeyMapping} per physical key. Defaults that share Space
     * with Jump never see {@code isDown()}, so read the key from GLFW when that happens.
     */
    public static boolean isActive(KeyMapping mapping) {
        if (mapping == null || mapping.isUnbound()) {
            return false;
        }
        if (mapping.isDown()) {
            return true;
        }
        Minecraft mc = Minecraft.getInstance();
        if (mc == null || mc.getWindow() == null || mc.screen != null) {
            return false;
        }
        InputConstants.Key key = mapping.getKey();
        long window = mc.getWindow().getWindow();
        if (key.getType() == InputConstants.Type.MOUSE) {
            return GLFW.glfwGetMouseButton(window, key.getValue()) == GLFW.GLFW_PRESS;
        }
        if (key.getType() == InputConstants.Type.KEYSYM) {
            return InputConstants.isKeyDown(window, key.getValue());
        }
        if (key.getType() == InputConstants.Type.SCANCODE) {
            return GLFW.glfwGetKey(window, key.getValue()) == GLFW.GLFW_PRESS;
        }
        return false;
    }

    public static boolean isFlyUp(Minecraft mc, Input input) {
        if (isActive(KEY_FLY_UP)) {
            return true;
        }
        // Ice and Fire always reads Jump. Sharing Space with KEY_FLY_UP steals KeyMapping.MAP,
        // so also poll the vanilla Jump key (GLFW) rather than input.jumping alone.
        if (mc != null && mc.options != null && isActive(mc.options.keyJump)) {
            return true;
        }
        return input != null && input.jumping;
    }

    public static boolean isMountFireLeft(Minecraft mc, Input input) {
        return isActive(KEY_MOUNT_FIRE_LEFT);
    }

    public static boolean isMountFireRight(Minecraft mc, Input input) {
        return isActive(KEY_MOUNT_FIRE_RIGHT);
    }
}
