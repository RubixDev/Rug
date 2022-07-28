package de.rubixdev.rug.mixins;


import carpet.api.settings.SettingsManager;
import carpet.utils.Messenger;
import carpet.utils.Translations;
import de.rubixdev.rug.RugServer;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin {
    @Inject(
        method = "listAllSettings",
        at = @At(value = "INVOKE", target = "Lcarpet/api/settings/SettingsManager;getCategories()Ljava/lang/Iterable;"),
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
