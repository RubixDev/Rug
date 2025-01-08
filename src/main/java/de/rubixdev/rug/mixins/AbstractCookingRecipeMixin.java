package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//#if MC >= 12103
import net.minecraft.recipe.SingleStackRecipe;
//#else
//$$ import org.spongepowered.asm.mixin.Final;
//$$ import org.spongepowered.asm.mixin.Shadow;
//#endif

//#if MC >= 12103
@Mixin(AbstractCookingRecipe.class)
public abstract class AbstractCookingRecipeMixin extends SingleStackRecipe {
    public AbstractCookingRecipeMixin(String group, Ingredient ingredient, ItemStack result) {
        super(group, ingredient, result);
    }
//#else
//$$ @Mixin(AbstractCookingRecipe.class)
//$$ public class AbstractCookingRecipeMixin {
//$$     @Shadow @Final protected ItemStack result;
//$$
//$$     @Shadow @Final protected Ingredient ingredient;
//#endif

    @Inject(method = "getExperience", at = @At("HEAD"), cancellable = true)
    private void setCactusXp(CallbackInfoReturnable<Float> cir) {
        //#if MC >= 12103
        if (this.result().isOf(Items.GREEN_DYE) && this.ingredient().test(Items.CACTUS.getDefaultStack())) {
        //#else
        //$$ if (this.result.isOf(Items.GREEN_DYE) && this.ingredient.test(Items.CACTUS.getDefaultStack())) {
        //#endif
            cir.setReturnValue((float) RugSettings.cactusFurnaceXp);
        }
    }
}
