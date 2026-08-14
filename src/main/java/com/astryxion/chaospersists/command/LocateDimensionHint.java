package com.astryxion.chaospersists.command;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.world.dimension.structure.ChaosLocateStructures;
import com.mojang.brigadier.ParseResults;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Tells the player which dimension a Chaos structure belongs in instead of sending them to an
 * empty overworld slot.
 */
@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class LocateDimensionHint {
    private LocateDimensionHint() {}

    @SubscribeEvent
    public static void onCommand(CommandEvent event) {
        ParseResults<?> results = event.getParseResults();
        Object source = results.getContext().getSource();
        if (!(source instanceof CommandSourceStack stack)) {
            return;
        }
        ServerLevel level = stack.getLevel();
        ResourceLocation id = structureIdFromLocate(results.getReader().getString());
        if (id == null || !ChaosPersists.MODID.equals(id.getNamespace())) {
            return;
        }
        ResourceKey<Structure> key = ResourceKey.create(Registries.STRUCTURE, id);
        String dims = ChaosLocateStructures.dimensionDisplayNames(key);
        if (dims.isEmpty()) {
            return;
        }
        if (ChaosLocateStructures.isAllowedDimension(level.dimension(), key)) {
            return;
        }
        event.setCanceled(true);
        Component message = Component.translatable("commands.chaospersists.locate.wrong_dimension", dims);
        if (stack.getEntity() instanceof ServerPlayer player) {
            player.displayClientMessage(message, true);
        } else {
            stack.sendSuccess(() -> message, false);
        }
    }

    private static ResourceLocation structureIdFromLocate(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String command = raw.trim();
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        String[] parts = command.split("\\s+");
        if (parts.length < 3 || !"locate".equals(parts[0]) || !"structure".equals(parts[1])) {
            return null;
        }
        String token = parts[2];
        if (token.startsWith("#")) {
            return null;
        }
        return ResourceLocation.tryParse(token);
    }
}
