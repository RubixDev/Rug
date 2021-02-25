package com.rubixdev.rug.mixins;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Items.class)
public abstract class ItemsMixin {

    @Shadow
    private static Item register(Identifier id, Item item) {
        return null;
    }

    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At("HEAD"), cancellable = true)
    private static void onRegister(String id, Item item, CallbackInfoReturnable<Item> cir) {
        if (id.equals("netherite_scrap")) {
            cir.setReturnValue(
                    register(new Identifier(id), new Item((new Item.Settings()).group(ItemGroup.MATERIALS).fireproof().food((new FoodComponent.Builder()).hunger(20).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 6000, 1), 1.0F).build()))));
        }
    }
}
