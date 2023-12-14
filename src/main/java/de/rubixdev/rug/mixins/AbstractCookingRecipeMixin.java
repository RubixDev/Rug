package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractCookingRecipe.class)
public class AbstractCookingRecipeMixin {
    @Shadow @Final protected ItemStack result;

    @Shadow @Final protected Ingredient ingredient;

    @Inject(method = "getExperience", at = @At("HEAD"), cancellable = true)
    private void setCactusXp(CallbackInfoReturnable<Float> cir) {
        if (this.result.isOf(Items.GREEN_DYE) && this.ingredient.test(Items.CACTUS.getDefaultStack())) {
            cir.setReturnValue((float) RugSettings.cactusFurnaceXp);
        }
    }
}
