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
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @ModifyArg(
            method = "initGoals",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/entity/passive/CatEntity$TemptGoal;<init>(Lnet/minecraft/entity/passive/CatEntity;DLnet/minecraft/recipe/Ingredient;Z)V"),
            index = 2)
    private Ingredient allowCookedFish(Ingredient original) {
        if (RugSettings.tameCatsWithCookedFish) return NEW_INGREDIENT;
        return original;
    }
}
