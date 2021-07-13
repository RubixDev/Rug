package de.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.rubixdev.rug.RugSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = CommandManager.literal("mods")
                .requires((player) -> SettingsManager.canUseCommand(player, RugSettings.commandMods))
                .executes(ctx -> execute(ctx, false))
                .then(CommandManager.literal("showfabric").executes(ctx -> execute(ctx, true)));
        dispatcher.register(command);
    }

    private static int execute(CommandContext<ServerCommandSource> context, boolean showFabric) {
        List<ModMetadata> installedMods = FabricLoader.getInstance()
                .getAllMods()
                .stream()
                .map(ModContainer::getMetadata)
                .sorted(Comparator.comparing(ModMetadata::getName))
                .filter(
                        mod -> !mod.getId().equals("minecraft")
                                && !mod.getId().equals("java")
                                && (showFabric || !mod.getName().toLowerCase().startsWith("fabric"))
                )
                .collect(Collectors.toList());

        String chatMessageJson = "[\"Mods (" + installedMods.size() + "):\\n    \",";
        List<String> modJsons = new ArrayList<>();
        for (ModMetadata mod : installedMods) {
            modJsons.add(getJsonForMod(mod.getName(), mod.getDescription(), mod.getVersion().getFriendlyString()));
        }
        chatMessageJson += String.join(",\"\\n    \",", modJsons) + "]";

        context.getSource().sendFeedback(Text.Serializer.fromJson(chatMessageJson), false);
        return 1;
    }

    private static String getJsonForMod(String modName, String modDescription, String modVersion) {
        return String.format("{\n" +
                "  \"text\": \"%s\",\n" +
                "  \"color\": \"%s\",\n" +
                "  \"hoverEvent\": {\n" +
                "    \"action\": \"show_text\",\n" +
                "    \"contents\": [\n" +
                "      {\n" +
                "        \"text\": \"%s\",\n" +
                "        \"color\": \"yellow\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "},\n" +
                "{\n" +
                "    \"text\": \" %s\",\n" +
                "    \"italic\": true\n" +
                "}", modName, modName.toLowerCase().startsWith("fabric") ? "gold" : "light_purple", modDescription, modVersion);
    }
}
