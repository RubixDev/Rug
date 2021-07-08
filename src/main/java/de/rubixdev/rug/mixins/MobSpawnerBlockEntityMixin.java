package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobSpawnerBlockEntity.class)
public class MobSpawnerBlockEntityMixin {
    @Inject(method = "copyItemDataRequiresOperator", at = @At("HEAD"), cancellable = true)
    private void onCopyItemDataRequiresOperator(CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.silkTouchSpawners) {
            cir.setReturnValue(false);
        }
    }
}
