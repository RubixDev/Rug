package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.mob.GhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {
    @Inject(method = "getFireballStrength", at = @At("HEAD"), cancellable = true)
    private void onGetFireballStrength(CallbackInfoReturnable<Integer> cir) {
        if (RugSettings.noGhastGriefing) {
            cir.setReturnValue(0);
        }
    }
}
