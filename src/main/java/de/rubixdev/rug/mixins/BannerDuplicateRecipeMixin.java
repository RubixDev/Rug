package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.recipe.BannerDuplicateRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = BannerDuplicateRecipe.class, priority = 990)
public class BannerDuplicateRecipeMixin {
    @ModifyConstant(
            //#if MC >= 12101
            method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z",
            //#else
            //$$ method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z",
            //#endif
            constant = @Constant(intValue = 6),
            require = 0)
    private int matches_overwriteMaxLayers(final int original) {
        return RugSettings.maxBannerLayers;
    }

    @ModifyConstant(
            //#if MC >= 12101
            method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;",
            //#elseif MC >= 12006
            //$$ method = "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;",
            //#else
            //$$ method = "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;",
            //#endif
            constant = @Constant(intValue = 6),
            require = 0)
    private int craft_overwriteMaxLayers(final int original) {
        return RugSettings.maxBannerLayers;
    }
}
