package com.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.rubixdev.rug.RugSettings;
import com.rubixdev.rug.gui.PlayerDataGui;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.Collection;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class PeekCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("peek").requires(
            (player) -> SettingsManager.canUseCommand(player, RugSettings.commandPeek)
        )
            .then(
                literal("inventory").then(
                    argument("player", StringArgumentType.word()).suggests(
                        ( (context, builder) -> suggestMatching(getPlayers(context.getSource()), builder) )
                    ).executes(context -> execute(context, false))
                )
            )
            .then(
                literal("enderchest").then(
                    argument("player", StringArgumentType.word()).suggests(
                        ( (context, builder) -> suggestMatching(getPlayers(context.getSource()), builder) )
                    ).executes(context -> execute(context, true))
                )
            );
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> context, boolean isEnderChest)
        throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();

        PlayerManager playerManager = source.getMinecraftServer().getPlayerManager();
        ServerPlayerEntity targetPlayer = playerManager.getPlayer(StringArgumentType.getString(context, "player"));
        ServerPlayerEntity executingPlayer = source.getPlayer();

        if (targetPlayer == null) {
            source.sendError(new LiteralText("Targeted Player could not be found"));
            return 0;
        }

        if (isEnderChest) {
            showEnderChest(executingPlayer, targetPlayer);
        } else {
            showInventory(executingPlayer, targetPlayer);
        }

        return 1;
    }

    public static void showInventory(ServerPlayerEntity executingPlayer, ServerPlayerEntity targetPlayer) {
        PlayerDataGui invScreen = new PlayerDataGui(ScreenHandlerType.GENERIC_9X5, executingPlayer, targetPlayer);
        invScreen.setTitle(Text.of("Inventory of " + targetPlayer.getDisplayName().asString()));
        for (int slot = 0; slot < executingPlayer.getInventory().size(); slot++) {
            invScreen.setSlotRedirect(slot, new Slot(targetPlayer.getInventory(), slot, 0, 0));
        }
        invScreen.open();
    }

    public static void showEnderChest(ServerPlayerEntity executingPlayer, ServerPlayerEntity targetPlayer) {
        EnderChestInventory targetEnderChest = targetPlayer.getEnderChestInventory();

        PlayerDataGui invScreen = new PlayerDataGui(ScreenHandlerType.GENERIC_9X3, executingPlayer, targetPlayer);
        invScreen.setTitle(Text.of("EnderChest of " + targetPlayer.getDisplayName().asString()));
        for (int slot = 0; slot < targetEnderChest.size(); slot++) {
            invScreen.setSlotRedirect(slot, new Slot(targetEnderChest, slot, 0, 0));
        }
        invScreen.open();
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        return source.getPlayerNames();
    }
}
