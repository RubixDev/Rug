package de.rubixdev.rug.mixins;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.Translations;
import de.rubixdev.rug.RugServer;
import de.rubixdev.rug.util.Storage;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin {
    @Inject(
            method = "listAllSettings",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lcarpet/api/settings/SettingsManager;getCategories()Ljava/lang/Iterable;"),
            remap = false)
    private void printRugVersion(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
        Messenger.m(
                source, "g Rug Mod ", "g " + Translations.tr("ui.version", "version") + ": ", "g " + RugServer.MOD_VERSION);
    }

    @Redirect(
            method = "getFile",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/server/MinecraftServer;getSavePath(Lnet/minecraft/util/WorldSavePath;)Ljava/nio/file/Path;"))
    private Path catchNullServer(MinecraftServer instance, WorldSavePath worldSavePath) {
        if (instance == null) return Storage.session.getDirectory(worldSavePath);
        return instance.getSavePath(worldSavePath);
    }

    @Redirect(
            method = "loadConfigurationFromConf",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/server/MinecraftServer;getCommandSource()Lnet/minecraft/server/command/ServerCommandSource;"))
    private ServerCommandSource catchNullServer(MinecraftServer instance) {
        if (instance == null) return null;
        return instance.getCommandSource();
    }

    @Redirect(
            method = "disableBooleanCommands",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/server/MinecraftServer;getCommandSource()Lnet/minecraft/server/command/ServerCommandSource;"))
    private ServerCommandSource catchNullServer2(MinecraftServer instance) {
        if (instance == null) return null;
        return instance.getCommandSource();
    }

    @Redirect(
            method = "loadConfigurationFromConf",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;values()Ljava/util/Collection;"),
            remap = false)
    private Collection<CarpetRule<?>> skipReset(Map<String, CarpetRule<?>> instance) {
        // Skip resetting rules to default to allow reading from both carpet.conf and rug.conf.
        // We are only allowed to do this because carpet.conf will always be read first, which already
        // resets all rules to their defaults. (Otherwise settings could persist between worlds)
        // TODO: this assumes that no Rug rules are overwritten by another extension in the CarpetServer.settingsManager
        return (Object) this == RugServer.settingsManager ? Collections.emptyList() : instance.values();
    }
}
