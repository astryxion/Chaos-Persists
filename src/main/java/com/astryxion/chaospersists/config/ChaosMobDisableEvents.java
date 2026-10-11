package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/** Per-mob Enable bans spawn/summon/give. AllMobsDisable only cancels natural spawns. */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ChaosMobDisableEvents {
    private ChaosMobDisableEvents() {}

    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (event == null || event.getEntity() == null) {
            return;
        }
        Mob mob = event.getEntity();
        if (ChaosMobAccess.shouldCancelSpawn(mob.getType(), event.getSpawnType())) {
            event.setSpawnCancelled(true);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onCommand(CommandEvent event) {
        ParseResults<?> results = event.getParseResults();
        Object source = results.getContext().getSource();
        if (!(source instanceof CommandSourceStack stack)) {
            return;
        }
        String raw = results.getReader().getString();
        if (raw == null || raw.isBlank()) {
            return;
        }
        String command = raw.trim();
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        String[] parts = command.split("\\s+");
        boolean give = false;
        boolean summon = false;
        for (String part : parts) {
            String name = commandName(part);
            if ("give".equals(name)) {
                give = true;
            } else if ("summon".equals(name)) {
                summon = true;
            }
        }
        Component blocked = null;
        if (give) {
            for (String part : parts) {
                Component message = disabledGive(parseId(part));
                if (message != null) {
                    blocked = message;
                    break;
                }
            }
        }
        if (blocked == null && summon) {
            for (String part : parts) {
                Component message = disabledSummon(parseId(part));
                if (message != null) {
                    blocked = message;
                    break;
                }
            }
        }
        if (blocked == null) {
            return;
        }
        event.setCanceled(true);
        Component message = blocked;
        if (stack.getEntity() instanceof ServerPlayer player) {
            player.displayClientMessage(message, true);
        } else {
            stack.sendFailure(message);
        }
    }

    private static Component disabledGive(ResourceLocation id) {
        Component message = ChaosMobAccess.disabledGiveMessage(id);
        if (message != null || id == null) {
            return message;
        }
        if (ChaosPersists.MODID.equals(id.getNamespace())) {
            return null;
        }
        return ChaosMobAccess.disabledGiveMessage(new ResourceLocation(ChaosPersists.MODID, id.getPath()));
    }

    private static Component disabledSummon(ResourceLocation id) {
        Component message = ChaosMobAccess.disabledSummonMessage(id);
        if (message != null || id == null) {
            return message;
        }
        if (ChaosPersists.MODID.equals(id.getNamespace())) {
            return null;
        }
        return ChaosMobAccess.disabledSummonMessage(new ResourceLocation(ChaosPersists.MODID, id.getPath()));
    }

    private static String commandName(String token) {
        int colon = token.indexOf(':');
        return colon >= 0 ? token.substring(colon + 1) : token;
    }

    private static ResourceLocation parseId(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        int cut = token.length();
        for (int i = 0; i < token.length(); i++) {
            char c = token.charAt(i);
            if (c == '{' || c == '[' || c == ',') {
                cut = i;
                break;
            }
        }
        return ResourceLocation.tryParse(token.substring(0, cut));
    }
}
