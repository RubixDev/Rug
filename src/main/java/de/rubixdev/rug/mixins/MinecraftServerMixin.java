package de.rubixdev.rug.mixins;

import carpet.CarpetServer;
import de.rubixdev.rug.RugServer;
import de.rubixdev.rug.util.Storage;
import java.util.Set;
import net.minecraft.resource.DataConfiguration;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//#if MC >= 12006
import java.util.Collection;
import java.util.LinkedHashSet;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
//#else
//$$ import net.minecraft.resource.DataPackSettings;
//$$ import net.minecraft.resource.featuretoggle.FeatureSet;
//$$ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
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
        // initialize `RugData` datapack
        RugServer.initializeRugData(Storage.session.getDirectory(WorldSavePath.DATAPACKS));
    }

    //#if MC >= 12006
    @ModifyVariable(
            method = "loadDataPacks(Lnet/minecraft/resource/ResourcePackManager;Ljava/util/Collection;Lnet/minecraft/resource/featuretoggle/FeatureSet;Z)Lnet/minecraft/resource/DataConfiguration;",
            at = @At("HEAD"),
            argsOnly = true
    )
    private static Collection<String> ensureEnabled(Collection<String> original, ResourcePackManager manager) {
        Set<String> set = new LinkedHashSet<>(original);
        // always include `RugData` in the enabled datapacks set
        set.add("file/RugData");
        return set;
    }
    //#else
    //$$ @Inject(
    //$$         method = "loadDataPacks",
    //$$         at =
    //$$                 @At(
    //$$                         value = "INVOKE",
    //$$                         target =
    //$$                                 "Lnet/minecraft/resource/ResourcePackManager;setEnabledProfiles(Ljava/util/Collection;)V",
    //$$                         ordinal = 1),
    //$$         locals = LocalCapture.CAPTURE_FAILSOFT)
    //$$ private static void ensureEnabled(
    //$$         ResourcePackManager resourcePackManager,
    //$$         DataPackSettings dataPackSettings,
    //$$         boolean safeMode,
    //$$         FeatureSet enabledFeatures,
    //$$         CallbackInfoReturnable<DataConfiguration> cir,
    //$$         Set<String> set) {
    //$$     // always include `RugData` in the enabled datapacks set
    //$$     set.add("file/RugData");
    //$$ }
    //#endif
}
