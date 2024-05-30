package de.rubixdev.rug.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.rubixdev.rug.RugSettings;
import net.minecraft.item.EndCrystalItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EndCrystalItem.class)
public class EndCrystalItemMixin {
    @ModifyExpressionValue(
            method = "useOnBlock",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean allowPlacement(boolean original) {
        return original || !RugSettings.endCrystalPlacementRestriction;
    }
}
