package com.astryxion.chaospersists.command;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public final class CommandUtopia {
    private CommandUtopia() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("utopia")
                        .requires(source -> source.hasPermission(2))
                        .executes(ctx -> execute(ctx.getSource())));
    }

    private static int execute(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(
                    Component.literal("This command can only be used by a player.").withStyle(ChatFormatting.RED));
            return 0;
        }
        ResourceKey<Level> utopiaKey = ChaosPersists.getUtopiaDimensionKey();
        if (player.level().dimension().equals(utopiaKey)) {
            player.displayClientMessage(
                    Component.literal("You are already in the Utopia dimension.")
                            .withStyle(ChatFormatting.YELLOW),
                    true);
            return 0;
        }
        ServerLevel world = player.server.getLevel(utopiaKey);
        if (world == null) {
            player.displayClientMessage(
                    Component.literal("Utopia dimension is not available.").withStyle(ChatFormatting.RED),
                    true);
            return 0;
        }
        UtopiaTeleporter teleporter = new UtopiaTeleporter(player.getX(), player.getZ());
        player.changeDimension(teleporter.transition(player, world));
        player.displayClientMessage(
                Component.literal("Teleported to Utopia.").withStyle(ChatFormatting.GREEN), true);
        return 1;
    }
}
