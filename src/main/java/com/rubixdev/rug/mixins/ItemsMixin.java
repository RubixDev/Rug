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
        switch (id) {
            case "netherite_scrap":
                cir.setReturnValue(
                        register(new Identifier(id), new Item((new Item.Settings()).group(ItemGroup.MATERIALS).fireproof().food((new FoodComponent.Builder()).hunger(20).saturationModifier(1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 6000, 0), 1.0F).build())))
                );
                break;
            case "slime_ball":
                cir.setReturnValue(
                        register(new Identifier(id), new Item((new Item.Settings()).group(ItemGroup.MISC).food((new FoodComponent.Builder()).hunger(2).saturationModifier(0.5F).alwaysEdible().snack().statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 160, 0), 0.98F).build())))
                );
                break;
            case "gold_ingot":
                cir.setReturnValue(
                        register(new Identifier(id), new Item((new Item.Settings()).group(ItemGroup.MATERIALS).food((new FoodComponent.Builder()).hunger(1).saturationModifier(20F).statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1), 1.0F).build())))
                );
                break;
        }
    }
}
