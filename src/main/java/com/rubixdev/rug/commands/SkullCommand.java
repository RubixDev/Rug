package com.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.rubixdev.rug.RugSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class SkullCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("skull").
                requires((player) -> SettingsManager.canUseCommand(player, RugSettings.commandSkull)).
                then(argument("player", StringArgumentType.word()).
                        executes(context -> giveSkulls(context, 1)).
                        then(argument("count", IntegerArgumentType.integer(1)).
                                executes(context -> giveSkulls(context, context.getArgument("count", Integer.class)))));
        dispatcher.register(command);
    }

    private static int giveSkulls(CommandContext<ServerCommandSource> context, int count) throws CommandSyntaxException {
        ServerCommandSource playerSource = context.getSource();
        ServerCommandSource source = playerSource.getMinecraftServer().getCommandSource();
        CommandManager manager = playerSource.getMinecraftServer().getCommandManager();

        ServerPlayerEntity playerEntity = playerSource.getPlayer();
        String playerName = playerEntity.getName().getString();
        String skullOwner = context.getArgument("player", String.class);

        return manager.execute(source, "give " + playerName + " minecraft:player_head{SkullOwner:" + skullOwner + "} " + count);
    }
}
