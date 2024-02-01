package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.item.BannerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = BannerItem.class, priority = 990)
public class BannerItemMixin {
    @ModifyConstant(method = "appendBannerTooltip", constant = @Constant(intValue = 6), require = 0)
    private static int overwriteMaxLayers(final int original) {
        return RugSettings.maxBannerLayers;
    }
}
