package com.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.rubixdev.rug.RugSettings;
import com.rubixdev.rug.screenhandlers.PlayerInventoryScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.*;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

import java.util.Collection;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class PeekCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("peek").
                requires((player) -> SettingsManager.canUseCommand(player, RugSettings.commandPeek)).
                then(literal("inventory").
                        then(argument("player", StringArgumentType.word()).
                                suggests(((context, builder) -> suggestMatching(getPlayers(context.getSource()), builder))).
                                executes(context -> execute(context, false)))).
                then(literal("enderchest").
                        then(argument("player", StringArgumentType.word()).
                                suggests(((context, builder) -> suggestMatching(getPlayers(context.getSource()), builder))).
                                executes(context -> execute(context, true))));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> context, boolean isEnderChest) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();

        PlayerManager playerManager = source.getMinecraftServer().getPlayerManager();
        PlayerEntity targetPlayer = playerManager.getPlayer(StringArgumentType.getString(context, "player"));
        ServerPlayerEntity executingPlayer = source.getPlayer();

        if (targetPlayer == null) {
            source.sendError(new LiteralText("Targeted Player could not be found"));
            return 0;
        }

        if (isEnderChest) {
            showEnderchest(executingPlayer, targetPlayer);
        } else {
            showInventory(executingPlayer, targetPlayer);
        }

        return 1;
    }

    public static void showInventory(ServerPlayerEntity executingPlayer, PlayerEntity targetPlayer) {
        executingPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inv, player) ->
                new PlayerInventoryScreenHandler(syncId, executingPlayer, targetPlayer.inventory),
                new LiteralText("Inventory of " + targetPlayer.getDisplayName().asString())));
    }

    public static void showEnderchest(PlayerEntity executingPlayer, PlayerEntity targetPlayer) {
        executingPlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inv, player) ->
                new GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X3, syncId, player.inventory, targetPlayer.getEnderChestInventory(), 3),
                new LiteralText("Ender Chest of " + targetPlayer.getDisplayName().asString())));
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        return source.getPlayerNames();
    }
}
