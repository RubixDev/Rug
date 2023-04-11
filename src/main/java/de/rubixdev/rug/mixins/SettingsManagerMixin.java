package de.rubixdev.rug.mixins;

import carpet.api.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.Translations;
import de.rubixdev.rug.RugServer;
import de.rubixdev.rug.util.Storage;
import java.nio.file.Path;
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
                source, "g Rug Mod ", "g " + Translations.tr("ui.version", "version") + ": ", "g " + RugServer.VERSION);
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
}
