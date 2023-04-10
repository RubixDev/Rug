package de.rubixdev.rug.commands;

import static net.minecraft.command.CommandSource.suggestMatching;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import carpet.utils.CommandHelper;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Dynamic;
import de.rubixdev.rug.RugSettings;
import de.rubixdev.rug.gui.PlayerDataGui;
import java.util.Collection;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.dimension.DimensionType;

public class PeekCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("peek")
                .requires((player) -> CommandHelper.canUseCommand(player, RugSettings.commandPeek))
                .then(literal("inventory")
                        .then(argument("player", GameProfileArgumentType.gameProfile())
                                .suggests(((context, builder) ->
                                        suggestMatching(getPlayers(context.getSource()), builder)))
                                .executes(context -> execute(context, false))))
                .then(literal("enderchest")
                        .then(argument("player", GameProfileArgumentType.gameProfile())
                                .suggests(((context, builder) ->
                                        suggestMatching(getPlayers(context.getSource()), builder)))
                                .executes(context -> execute(context, true))));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> context, boolean isEnderChest)
            throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();

        PlayerManager playerManager = source.getServer().getPlayerManager();
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

            @SuppressWarnings("deprecation")
            ServerWorld world = source.getServer()
                    .getWorld(DimensionType.worldFromDimensionNbt(
                                    new Dynamic<>(NbtOps.INSTANCE, targetPlayerData.get("Dimension")))
                            .result()
                            .orElseThrow());
            if (world != null) targetPlayer.setWorld(world);
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
        invScreen.setTitle(
                Text.of("Inventory of " + targetPlayer.getDisplayName().getString()));
        for (int slot = 0; slot < executingPlayer.getInventory().size(); slot++) {
            invScreen.setSlotRedirect(slot, new Slot(targetPlayer.getInventory(), slot, 0, 0));
        }
        invScreen.open();
    }

    public static void showEnderChest(ServerPlayerEntity executingPlayer, ServerPlayerEntity targetPlayer) {
        EnderChestInventory targetEnderChest = targetPlayer.getEnderChestInventory();

        PlayerDataGui invScreen = new PlayerDataGui(ScreenHandlerType.GENERIC_9X3, executingPlayer, targetPlayer);
        invScreen.setTitle(
                Text.of("EnderChest of " + targetPlayer.getDisplayName().getString()));
        for (int slot = 0; slot < targetEnderChest.size(); slot++) {
            invScreen.setSlotRedirect(slot, new Slot(targetEnderChest, slot, 0, 0));
        }
        invScreen.open();
    }

    private static Collection<String> getPlayers(ServerCommandSource source) {
        return source.getPlayerNames();
    }
}
