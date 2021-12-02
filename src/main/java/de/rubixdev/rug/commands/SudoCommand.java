package de.rubixdev.rug.commands;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import java.util.Collection;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import carpet.settings.SettingsManager;
import de.rubixdev.rug.RugSettings;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class SudoCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("sudo").requires(
            (player) -> SettingsManager.canUseCommand(player, RugSettings.commandSudo)
        )
            .then(
                argument("player", StringArgumentType.word()).suggests(
                    ( (context, builder) -> suggestMatching(getPlayers(context.getSource()), builder) )
                ).then(literal("chat").then(argument("message", StringArgumentType.greedyString()).executes(context -> {
                    String targetPlayerName = StringArgumentType.getString(context, "player");

                    MinecraftServer server = context.getSource().getServer();
                    PlayerManager playerManager = server.getPlayerManager();
                    ServerPlayerEntity targetPlayer = playerManager.getPlayer(targetPlayerName);

                    if (targetPlayer == null) {
                        context.getSource().sendError(new LiteralText("Targeted Player could not be found"));
                        return 0;
                    }

                    Text text = new TranslatableText(
                        "chat.type.text",
                        targetPlayerName,
                        StringArgumentType.getString(context, "message")
                    );
                    playerManager.broadcast(text, MessageType.CHAT, targetPlayer.getUuid());
                    return 1;
                }))).then(literal("command").redirect(dispatcher.getRoot(), context -> {
                    String targetPlayerName = StringArgumentType.getString(context, "player");
                    MinecraftServer server = context.getSource().getServer();
                    ServerPlayerEntity player = server.getPlayerManager().getPlayer(targetPlayerName);

                    if (player == null) {
                        Message errorMessage = new LiteralText("Targeted player could not be found");
                        throw new CommandSyntaxException(new SimpleCommandExceptionType(errorMessage), errorMessage);
                    }

                    return player.getCommandSource();
                }))
            );
        dispatcher.register(command);
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        return source.getPlayerNames();
    }
}
