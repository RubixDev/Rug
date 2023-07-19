package de.rubixdev.rug.mixins;

import carpet.CarpetServer;
import de.rubixdev.rug.RugServer;
import de.rubixdev.rug.util.Storage;
import java.util.Set;
import net.minecraft.resource.DataConfiguration;
import net.minecraft.resource.DataPackSettings;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "loadDataPacks", at = @At("HEAD"))
    private static void loadRugData(
            ResourcePackManager resourcePackManager,
            DataPackSettings dataPackSettings,
            boolean safeMode,
            FeatureSet enabledFeatures,
            CallbackInfoReturnable<DataConfiguration> cir) {
        // if no session exists yet (still in world creation screen), then do nothing
        if (Storage.session == null) return;
        // make Carpet load the config file early
        CarpetServer.settingsManager.attachServer(null);
        // initialize `RugData` datapack
        RugServer.initializeRugData(Storage.session.getDirectory(WorldSavePath.DATAPACKS));
    }

    @Inject(
            method = "loadDataPacks",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/resource/ResourcePackManager;setEnabledProfiles(Ljava/util/Collection;)V",
                            ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    private static void ensureEnabled(
            ResourcePackManager resourcePackManager,
            DataPackSettings dataPackSettings,
            boolean safeMode,
            FeatureSet enabledFeatures,
            CallbackInfoReturnable<DataConfiguration> cir,
            Set<String> set) {
        // always include `RugData` in the enabled datapacks set
        set.add("file/RugData");
    }
}
