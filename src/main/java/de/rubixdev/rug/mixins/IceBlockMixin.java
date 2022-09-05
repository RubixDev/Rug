package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.IceBlock;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = IceBlock.class, priority = 10000)
public class IceBlockMixin {
    @Redirect(
            method = "afterBreak",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/DimensionType;ultrawarm()Z"))
    private boolean allowWaterInNether(DimensionType instance) {
        if (RugSettings.waterInNether) return false;
        return instance.ultrawarm();
    }

    @Redirect(
            method = "melt",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/DimensionType;ultrawarm()Z"))
    private boolean allowMeltingInNether(DimensionType instance) {
        if (RugSettings.waterInNether) return false;
        return instance.ultrawarm();
    }
}
