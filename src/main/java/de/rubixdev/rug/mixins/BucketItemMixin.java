package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.item.BucketItem;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BucketItem.class, priority = 10000)
public class BucketItemMixin {
    @Redirect(
            method = "placeFluid",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/DimensionType;ultrawarm()Z"))
    private boolean allowWaterInNether(DimensionType instance) {
        if (RugSettings.waterInNether) return false;
        return instance.ultrawarm();
    }
}
