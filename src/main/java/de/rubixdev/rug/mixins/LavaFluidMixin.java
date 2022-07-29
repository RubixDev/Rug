package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
    @Inject(method = "isInfinite", at = @At("HEAD"), cancellable = true)
    private void setInfinite(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(RugSettings.infiniteLavaSources);
    }
}
