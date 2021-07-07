package de.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.rubixdev.rug.RugSettings;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class SudoCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("sudo").requires(
            (player) -> SettingsManager.canUseCommand(player, RugSettings.commandSudo)
        )
            .then(
                argument("player", StringArgumentType.word()).suggests(
                    ( (context, builder) -> suggestMatching(getPlayers(context.getSource()), builder) )
                ).then(argument("command", StringArgumentType.greedyString()).executes(context -> {
                    String targetPlayerName = StringArgumentType.getString(context, "player");
                    String commandString = StringArgumentType.getString(context, "command");

                    MinecraftServer server = context.getSource().getServer();
                    PlayerManager playerManager = server.getPlayerManager();
                    ServerPlayerEntity targetPlayer = playerManager.getPlayer(targetPlayerName);

                    if (targetPlayer == null) {
                        context.getSource().sendError(new LiteralText("Targeted Player could not be found"));
                        return 0;
                    }

                    if (commandString.startsWith("/")) {
                        server.getCommandManager().execute(targetPlayer.getCommandSource(), commandString);
                    } else {
                        Text text = new TranslatableText("chat.type.text", targetPlayerName, commandString);
                        playerManager.broadcastChatMessage(text, MessageType.CHAT, targetPlayer.getUuid());
                    }

                    context.getSource()
                        .sendFeedback(
                            new LiteralText("Executed \"" + commandString + "\" as " + targetPlayerName),
                            true
                        );
                    return 1;
                }))
            );
        dispatcher.register(command);
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        return source.getPlayerNames();
    }
}
