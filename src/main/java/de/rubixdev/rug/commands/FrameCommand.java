package de.rubixdev.rug.commands;


import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import de.rubixdev.rug.RugSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class FrameCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = CommandManager.literal("frame")
            .requires((player) -> SettingsManager.canUseCommand(player, RugSettings.commandFrame))
            .then(CommandManager.literal("hide").executes((context) -> {
                ServerCommandSource playerSource = context.getSource();
                ServerCommandSource source = playerSource.getServer().getCommandSource();
                ServerPlayerEntity playerEntity = playerSource.getPlayer();
                if (playerEntity == null) {
                    context.getSource().sendError(Text.of("Command must be executed as a player"));
                    return 0;
                }
                CommandManager manager = playerSource.getServer().getCommandManager();
                String playerName = playerEntity.getName().getString();

                manager.execute(
                    source,
                    "execute at " + playerName + " run tag @e[type=minecraft:item_frame,distance=..5] remove hasItem"
                );
                manager.execute(
                    source,
                    "execute at "
                        + playerName
                        + " run tag @e[type=minecraft:item_frame,distance=..5,nbt={Item:{}}] add hasItem"
                );
                manager.execute(
                    source,
                    "execute at "
                        + playerName
                        + " as @e[type=item_frame,distance=..5,tag=hasItem,limit=1,sort=nearest,nbt={Fixed:0b,Invisible:0b}]"
                        + " run data merge entity @s {Invisible:1b,Fixed:1b}"
                );
                manager.execute(
                    source,
                    "execute at " + playerName + " run tag @e[type=minecraft:item_frame,distance=..5] remove hasItem"
                );

                return 1;
            }))
            .then(CommandManager.literal("show").executes(( context -> {
                ServerCommandSource playerSource = context.getSource();
                ServerCommandSource source = playerSource.getServer().getCommandSource();
                ServerPlayerEntity playerEntity = playerSource.getPlayer();
                if (playerEntity == null) {
                    context.getSource().sendError(Text.of("Command must be executed as a player"));
                    return 0;
                }
                CommandManager manager = playerSource.getServer().getCommandManager();
                String playerName = playerEntity.getName().getString();

                manager.execute(
                    source,
                    "execute at "
                        + playerName
                        + " as @e[type=item_frame,distance=..5,limit=1,sort=nearest,nbt={Fixed:1b,Invisible:1b}]"
                        + " run data merge entity @s {Invisible:0b,Fixed:0b}"
                );

                return 1;
            } )));
        dispatcher.register(command);
    }
}
