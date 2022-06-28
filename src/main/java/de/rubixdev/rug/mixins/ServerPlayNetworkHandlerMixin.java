package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ServerPlayNetworkHandler.class, priority = 900)
public class ServerPlayNetworkHandlerMixin {
    @Shadow
    @Final
    public static double MAX_BREAK_SQUARED_DISTANCE;

    @Redirect(
        method = "onPlayerInteractEntity",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;MAX_BREAK_SQUARED_DISTANCE:D"
        )
    )
    private double changeAttackRangeForEntities() {
        return Math.pow(Math.sqrt(MAX_BREAK_SQUARED_DISTANCE) + RugSettings.reachDistance - 4.5, 2);
    }

    @Redirect(
        method = "onPlayerInteractBlock",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;MAX_BREAK_SQUARED_DISTANCE:D"
        )
    )
    private double changeAttackRangeForBlocks() {
        return Math.pow(Math.sqrt(MAX_BREAK_SQUARED_DISTANCE) + RugSettings.reachDistance - 4.5, 2);
    }

    @ModifyConstant(method = "onPlayerInteractBlock", allow = 1, require = 1, constant = @Constant(doubleValue = 64.0))
    private double changeReachDistance(final double baseReachDistance) {
        return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    }
}
