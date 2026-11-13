package com.astryxion.chaospersists.command;

import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public final class CommandOverworld {
    private CommandOverworld() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("overworld")
                        .requires(source -> source.hasPermission(2))
                        .executes(ctx -> execute(ctx.getSource())));
    }

    private static int execute(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(
                    Component.literal("This command can only be used by a player.").withStyle(ChatFormatting.RED));
            return 0;
        }
        if (player.level().dimension().equals(Level.OVERWORLD)) {
            player.displayClientMessage(
                    Component.literal("You are already in the Overworld.").withStyle(ChatFormatting.YELLOW),
                    true);
            return 0;
        }
        ServerLevel world = player.server.getLevel(Level.OVERWORLD);
        if (world == null) {
            player.displayClientMessage(
                    Component.literal("Overworld is not available.").withStyle(ChatFormatting.RED), true);
            return 0;
        }
        UtopiaTeleporter teleporter = new UtopiaTeleporter(player.getX(), player.getZ());
        player.changeDimension(teleporter.transition(player, world));
        player.displayClientMessage(
                Component.literal("Teleported to Overworld.").withStyle(ChatFormatting.GREEN), true);
        return 1;
    }
}
