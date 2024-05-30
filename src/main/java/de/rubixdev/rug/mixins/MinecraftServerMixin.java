package de.rubixdev.rug.mixins;

import carpet.CarpetServer;
import de.rubixdev.rug.RugServer;
import de.rubixdev.rug.util.Storage;
import net.minecraft.resource.DataConfiguration;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//#if MC < 12006
//$$ import net.minecraft.resource.DataPackSettings;
//$$ import net.minecraft.resource.featuretoggle.FeatureSet;
//#endif

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    //#if MC >= 12006
    @Inject(method = "loadDataPacks(Lnet/minecraft/resource/ResourcePackManager;Lnet/minecraft/resource/DataConfiguration;ZZ)Lnet/minecraft/resource/DataConfiguration;", at = @At("HEAD"))
    //#else
    //$$ @Inject(method = "loadDataPacks", at = @At("HEAD"))
    //#endif
    private static void loadRugData(
            //#if MC >= 12006
            ResourcePackManager resourcePackManager,
            DataConfiguration dataConfiguration,
            boolean initMode,
            boolean safeMode,
            //#else
            //$$ ResourcePackManager resourcePackManager,
            //$$ DataPackSettings dataPackSettings,
            //$$ boolean safeMode,
            //$$ FeatureSet enabledFeatures,
            //#endif
            CallbackInfoReturnable<DataConfiguration> cir) {
        // if no session exists yet (still in world creation screen), then do nothing
        if (Storage.session == null) return;
        // make Carpet load the config file(s) early
        CarpetServer.settingsManager.attachServer(null);
        RugServer.settingsManager.attachServer(null);
    }
}
