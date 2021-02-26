package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @ModifyConstant(method = "processBlockBreakingAction", require = 1, allow = 1, constant = @Constant(doubleValue = 36.0))
    private double changeReachDistance(final double baseReachDistance) {
        return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    }
}
