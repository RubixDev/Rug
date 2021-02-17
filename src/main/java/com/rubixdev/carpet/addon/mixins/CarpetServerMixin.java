package com.rubixdev.carpet.addon.mixins;

import carpet.CarpetServer;
import com.rubixdev.carpet.addon.CarpetAddonServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarpetServer.class)
public abstract class CarpetServerMixin {
    @Inject(
            method = "onGameStarted",
            at = @At(value = "HEAD"),
            remap = false
    )
    private static void gameStarted(CallbackInfo ci) {
        CarpetAddonServer.noop();
    }
}
