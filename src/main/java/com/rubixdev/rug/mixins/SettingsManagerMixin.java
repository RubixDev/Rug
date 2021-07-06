package com.rubixdev.rug.mixins;

import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.Translations;
import com.rubixdev.rug.RugServer;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin {
        @Inject(
                method = "listAllSettings",
                at = @At(
                        value = "INVOKE",
                        target = "Lcarpet/settings/SettingsManager;getCategories()Ljava/lang/Iterable;"
                ),
                remap = false
        )
        private void printRugVersion(ServerCommandSource source, CallbackInfoReturnable<Integer> cir) {
                Messenger.m(
                        source,
                        "g Rug Mod ",
                        "g " + Translations.tr("ui.version", "version") + ": ",
                        "g " + RugServer.VERSION
                );
        }
}
