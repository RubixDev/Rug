package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.screen.LoomScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = LoomScreenHandler.class, priority = 990)
public class LoomScreenHandlerMixin {
    @ModifyConstant(method = "onContentChanged", constant = @Constant(intValue = 6), require = 0)
    private int overwriteMaxLayers(final int original) {
        return RugSettings.maxBannerLayers;
    }
}
