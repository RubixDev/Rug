package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ServerPlayNetworkHandler.class, priority = 900)
public class ServerPlayNetworkHandlerMixin {
    @ModifyConstant(method = "onPlayerInteractEntity", allow = 2, require = 2, constant = @Constant(doubleValue = 36.0))
    private double changeAttackRange(final double baseAttackRange, final PlayerInteractEntityC2SPacket packet) {
        if (packet.getType() == PlayerInteractEntityC2SPacket.InteractionType.ATTACK) {
            return baseAttackRange;
        }
        return Math.pow(Math.sqrt(baseAttackRange) + RugSettings.reachDistance - 4.5, 2);
    }

    @ModifyConstant(method = "onPlayerInteractBlock", allow = 1, require = 1, constant = @Constant(doubleValue = 64.0))
    private double changeReachDistance(final double baseReachDistance) {
        return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    }
}
