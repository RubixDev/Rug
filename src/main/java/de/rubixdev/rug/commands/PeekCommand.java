package de.rubixdev.rug.commands;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import java.util.Collection;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Dynamic;
import de.rubixdev.rug.RugSettings;
import de.rubixdev.rug.screenhandlers.PlayerInventoryScreenHandler;

import carpet.settings.SettingsManager;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.dimension.DimensionType;

public class PeekCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("peek").requires(
            (player) -> SettingsManager.canUseCommand(player, RugSettings.commandPeek)
        )
            .then(
                literal("inventory").then(
                    argument("player", GameProfileArgumentType.gameProfile()).suggests(
                        ( (context, builder) -> suggestMatching(getPlayers(context.getSource()), builder) )
                    ).executes(context -> execute(context, false))
                )
            )
            .then(
                literal("enderchest").then(
                    argument("player", GameProfileArgumentType.gameProfile()).suggests(
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
        GameProfile targetPlayerProfile = GameProfileArgumentType.getProfileArgument(context, "player")
            .iterator()
            .next();
        ServerPlayerEntity targetPlayer = playerManager.getPlayer(targetPlayerProfile.getName());
        ServerPlayerEntity executingPlayer = source.getPlayer();

        if (targetPlayer == null) {
            targetPlayer = playerManager.createPlayer(targetPlayerProfile);
            NbtCompound targetPlayerData = playerManager.loadPlayerData(targetPlayer);

            if (targetPlayerData == null) {
                source.sendError(Text.of("Targeted player's data could not be found. Was he ever in this world?"));
                return 0;
            }

            @SuppressWarnings({"deprecation", "OptionalGetWithoutIsPresent"})
            ServerWorld world = source.getMinecraftServer()
                .getWorld(
                    DimensionType.method_28521(new Dynamic<>(NbtOps.INSTANCE, targetPlayerData.get("Dimension")))
                        .result()
                        .get()
                );
            if (world != null) targetPlayer.setWorld(world);
        }

        if (isEnderChest) {
            showEnderchest(executingPlayer, targetPlayer);
        } else {
            showInventory(executingPlayer, targetPlayer);
        }

        return 1;
    }

    public static void showInventory(ServerPlayerEntity executingPlayer, PlayerEntity targetPlayer) {
        executingPlayer.openHandledScreen(
            new SimpleNamedScreenHandlerFactory(
                (syncId, inv, player) -> new PlayerInventoryScreenHandler(
                    syncId,
                    executingPlayer,
                    targetPlayer.inventory
                ),
                new LiteralText("Inventory of " + targetPlayer.getDisplayName().asString())
            )
        );
    }

    public static void showEnderchest(PlayerEntity executingPlayer, PlayerEntity targetPlayer) {
        executingPlayer.openHandledScreen(
            new SimpleNamedScreenHandlerFactory(
                (syncId, inv, player) -> new GenericContainerScreenHandler(
                    ScreenHandlerType.GENERIC_9X3,
                    syncId,
                    player.inventory,
                    targetPlayer.getEnderChestInventory(),
                    3
                ),
                new LiteralText("Ender Chest of " + targetPlayer.getDisplayName().asString())
            )
        );
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        return source.getPlayerNames();
    }
}
