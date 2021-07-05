package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 4.5F))
    private float changeReachDistanceSurvival(final float baseReachDistance) {
        if (this.client.player != null) { return (float) RugSettings.reachDistance; }
        return baseReachDistance;
    }

    @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 5.0F))
    private float changeReachDistanceCreative(final float baseReachDistance) {
        if (this.client.player != null) { return (float) RugSettings.reachDistance + 0.5F; }
        return baseReachDistance;
    }
}
