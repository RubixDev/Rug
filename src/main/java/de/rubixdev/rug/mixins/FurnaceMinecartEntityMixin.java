package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceMinecartEntity.class)
public class FurnaceMinecartEntityMixin {
    @Inject(method = "getMaxSpeed", at = @At("RETURN"), cancellable = true)
    private void applyMultiplier(CallbackInfoReturnable<Double> cir) {
        if (RugSettings.minecartMaxSpeedMultiplier != 1.0) {
            cir.setReturnValue(cir.getReturnValueD() * RugSettings.minecartMaxSpeedMultiplier);
        }
    }
}
