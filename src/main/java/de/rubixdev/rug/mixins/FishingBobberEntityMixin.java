package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @Inject(method = "isOpenOrWaterAround", at = @At("HEAD"), cancellable = true)
    private void injectIsOpenOrWaterAround(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.oldFishingLoot) {
            cir.setReturnValue(true);
        }
    }
}
