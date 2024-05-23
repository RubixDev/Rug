package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//#if MC >= 12006
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
//#else
//$$ import org.spongepowered.asm.mixin.injection.ModifyArg;
//#endif

@Mixin(CatEntity.class)
public class CatEntityMixin {
    @Unique
    private static final Ingredient NEW_INGREDIENT =
            Ingredient.ofItems(Items.COD, Items.SALMON, Items.COOKED_COD, Items.COOKED_SALMON);

    @Inject(method = "isBreedingItem", at = @At("HEAD"), cancellable = true)
    private void allowCookedFish(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.tameCatsWithCookedFish) {
            cir.setReturnValue(NEW_INGREDIENT.test(stack));
        }
    }

    //#if MC >= 12006
    @ModifyExpressionValue(method = "method_58365", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    private static boolean allowCookedFish(boolean original, ItemStack stack) {
        return original || (RugSettings.tameCatsWithCookedFish && (stack.isOf(Items.COOKED_COD) || stack.isOf(Items.COOKED_SALMON)));
    }
    //#else
    //$$ @ModifyArg(
    //$$         method = "initGoals",
    //$$         at =
    //$$                 @At(
    //$$                         value = "INVOKE",
    //$$                         target =
    //$$                                 "Lnet/minecraft/entity/passive/CatEntity$TemptGoal;<init>(Lnet/minecraft/entity/passive/CatEntity;DLnet/minecraft/recipe/Ingredient;Z)V"),
    //$$         index = 2)
    //$$ private Ingredient allowCookedFish(Ingredient original) {
    //$$     if (RugSettings.tameCatsWithCookedFish) return NEW_INGREDIENT;
    //$$     return original;
    //$$ }
    //#endif
}
