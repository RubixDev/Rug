package de.rubixdev.rug.mixins.reach;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;

//#if MC < 12006
//$$ import de.rubixdev.rug.RugSettings;
//$$ import net.minecraft.server.network.ServerPlayNetworkHandler;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Redirect;
//#endif

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    //#if MC < 12006
    //$$ @Redirect(
    //$$         method = "processBlockBreakingAction",
    //$$         at =
    //$$                 @At(
    //$$                         value = "FIELD",
    //$$                         target =
    //$$                                 "Lnet/minecraft/server/network/ServerPlayNetworkHandler;MAX_BREAK_SQUARED_DISTANCE:D"))
    //$$ private double changeReachDistance() {
    //$$     return Math.pow(
    //$$             Math.sqrt(ServerPlayNetworkHandler.MAX_BREAK_SQUARED_DISTANCE) + RugSettings.reachDistance - 4.5, 2);
    //$$ }
    //#endif
}
