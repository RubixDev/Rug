package de.rubixdev.rug.commands;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import de.rubixdev.rug.RugSettings;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ModsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = CommandManager.literal("mods")
                .requires((player) -> CommandHelper.canUseCommand(player, RugSettings.commandMods))
                .executes(ctx -> execute(ctx, false))
                .then(CommandManager.literal("showfabric").executes(ctx -> execute(ctx, true)));
        dispatcher.register(command);
    }

    @SuppressWarnings("SameReturnValue")
    private static int execute(CommandContext<ServerCommandSource> context, boolean showFabric) {
        List<ModMetadata> installedMods = FabricLoader.getInstance().getAllMods().stream()
                .map(ModContainer::getMetadata)
                .sorted(Comparator.comparing(ModMetadata::getName))
                .filter(mod -> !mod.getId().equals("minecraft")
                        && !mod.getId().equals("java")
                        && (showFabric || !mod.getName().toLowerCase().startsWith("fabric")))
                .toList();

        String chatMessageJson = "[\"Mods (" + installedMods.size() + "):\\n    \",";
        List<String> modJsons = new ArrayList<>();
        for (ModMetadata mod : installedMods) {
            modJsons.add(getJsonForMod(
                    mod.getName(), mod.getDescription(), mod.getVersion().getFriendlyString()));
        }
        chatMessageJson += String.join(",\"\\n    \",", modJsons) + "]";

        String finalChatMessageJson = chatMessageJson;
        context.getSource().sendFeedback(() -> Text.Serializer.fromJson(finalChatMessageJson), false);
        return 1;
    }

    private static String getJsonForMod(String modName, String modDescription, String modVersion) {
        return """
            {
              "text": "%s",
              "color": "%s",
              "hoverEvent": {
                "action": "show_text",
                "contents": [
                  {
                    "text": "%s",
                    "color": "yellow"
                  }
                ]
              }
            },
            {
                "text": " %s",
                "italic": true
            }"""
                .formatted(
                        modName,
                        modName.toLowerCase().startsWith("fabric") ? "gold" : "light_purple",
                        modDescription,
                        modVersion);
    }
}
