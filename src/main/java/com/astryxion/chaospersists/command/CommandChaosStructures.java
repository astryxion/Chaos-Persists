package com.astryxion.chaospersists.command;

import com.astryxion.chaospersists.world.dimension.structure.ChaosLocateStructures;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class CommandChaosStructures {
    private CommandChaosStructures() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("chaosstructures")
                        .requires(source -> source.hasPermission(0))
                        .executes(ctx -> execute(ctx.getSource())));
    }

    private static int execute(CommandSourceStack source) {
        source.sendSuccess(
                () -> Component.literal("Chaos structure locate names:").withStyle(ChatFormatting.GOLD),
                false);
        for (ResourceKey<Structure> key : ChaosLocateStructures.allLocateKeys()) {
            String dims = ChaosLocateStructures.dimensionDisplayNames(key);
            String id = key.location().toString();
            source.sendSuccess(
                    () -> Component.translatable(structureLangKey(key))
                            .append(Component.literal(" — /locate structure " + id + " — " + dims)
                                    .withStyle(ChatFormatting.GRAY)),
                    false);
        }
        return 1;
    }

    private static String structureLangKey(ResourceKey<Structure> key) {
        return "structure." + key.location().getNamespace() + "." + key.location().getPath();
    }
}
