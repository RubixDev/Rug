package com.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.google.common.collect.Sets;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class SkullCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("skull").
                requires((player) -> SettingsManager.canUseCommand(player, RugSettings.commandSkull)).
                executes(context -> execute(context, 0)).
                then(argument("player", StringArgumentType.word()).
                        suggests(((context, builder) -> suggestMatching(getPlayers(context.getSource()), builder))).
                        executes(context -> execute(context, 1)).
                        then(argument("count", IntegerArgumentType.integer(1)).
                                executes(context -> execute(context, context.getArgument("count", Integer.class)))));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> context, int count) throws CommandSyntaxException {
        ServerCommandSource playerSource = context.getSource();
        ServerCommandSource source = playerSource.getMinecraftServer().getCommandSource();
        CommandManager manager = playerSource.getMinecraftServer().getCommandManager();

        ServerPlayerEntity playerEntity = playerSource.getPlayer();
        String playerName = playerEntity.getName().getString();
        String skullOwner = count == 0 ? playerName : context.getArgument("player", String.class);

        if (count == 0) count = 1;

        return manager.execute(source, "give " + playerName + " minecraft:player_head{SkullOwner:" + skullOwner + "} " + count);
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        Set<String> players = Sets.newLinkedHashSet(Arrays.asList("RubixDev", "Gnembon", "Steve", "Alex"));
        players.addAll(source.getPlayerNames());
        return players;
    }
}
