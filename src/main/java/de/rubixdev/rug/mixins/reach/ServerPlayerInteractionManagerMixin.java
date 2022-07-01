package de.rubixdev.rug.mixins.reach;


import de.rubixdev.rug.RugSettings;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Redirect(
        method = "processBlockBreakingAction",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;MAX_BREAK_SQUARED_DISTANCE:D"
        )
    )
    private double changeReachDistance() {
        return Math.pow(
            Math.sqrt(ServerPlayNetworkHandler.MAX_BREAK_SQUARED_DISTANCE) + RugSettings.reachDistance - 4.5,
            2
        );
    }
}
