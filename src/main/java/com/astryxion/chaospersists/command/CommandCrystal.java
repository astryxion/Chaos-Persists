package com.astryxion.chaospersists.command;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.world.dimension.teleporter.TeleporterUtopia;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;

public class CommandCrystal {
    public static final String NAME = "crystal";
    private static final int DIMENSION_INDEX = 5;

    public LiteralArgumentBuilder<CommandSource> register() {
        return build(Commands.literal(NAME));
    }

    public static LiteralArgumentBuilder<CommandSource> build(LiteralArgumentBuilder<CommandSource> builder) {
        return builder.requires(source -> source.hasPermission(2)).executes(CommandCrystal::execute);
    }

    private static int execute(CommandContext<CommandSource> context) {
        CommandSource source = context.getSource();
        if (!(source.getEntity() instanceof ServerPlayerEntity)) {
            source.sendFailure(new StringTextComponent(TextFormatting.RED + "This command can only be used by a player."));
            return 0;
        }
        ServerPlayerEntity player = (ServerPlayerEntity) source.getEntity();
        MinecraftServer server = source.getServer();
        if (server == null) {
            source.sendFailure(new StringTextComponent(TextFormatting.RED + "Server not available."));
            return 0;
        }
        ServerWorld world = ChaosPersists.getServerWorldForDimensionIndex(server, DIMENSION_INDEX);
        if (world == null) {
            player.sendMessage(new StringTextComponent(TextFormatting.RED + "Crystal dimension is not available."), player.getUUID());
            return 0;
        }
        if (player.getLevel().dimension() == world.dimension()) {
            player.sendMessage(new StringTextComponent(TextFormatting.YELLOW + "You are already in the Crystal dimension."), player.getUUID());
            return 0;
        }
        double x = player.getX();
        double z = player.getZ();
        TeleporterUtopia teleporter = new TeleporterUtopia(world, x, z);
        player.changeDimension(world, teleporter);
        player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Teleported to Crystal."), player.getUUID());
        return 1;
    }
}
