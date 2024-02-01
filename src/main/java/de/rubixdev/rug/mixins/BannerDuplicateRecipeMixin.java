package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.recipe.BannerDuplicateRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = BannerDuplicateRecipe.class, priority = 990)
public class BannerDuplicateRecipeMixin {
    @ModifyConstant(
            method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z",
            constant = @Constant(intValue = 6),
            require = 0)
    private int matches_overwriteMaxLayers(final int original) {
        return RugSettings.maxBannerLayers;
    }

    @ModifyConstant(
            method =
                    "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;",
            constant = @Constant(intValue = 6),
            require = 0)
    private int craft_overwriteMaxLayers(final int original) {
        return RugSettings.maxBannerLayers;
    }
}
