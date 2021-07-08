package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
    targets = {
        "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal",
        "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal" }
)
public class EndermanEntityMixin {
    @Inject(method = "canStart()Z", at = @At("HEAD"), cancellable = true)
    private void onPickUpCanStart(CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.noEndermanGriefing) {
            cir.setReturnValue(false);
        }
    }
}
