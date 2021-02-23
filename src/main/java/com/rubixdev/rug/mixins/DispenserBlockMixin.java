package com.rubixdev.rug.mixins;

import com.rubixdev.rug.behaviours.GoldenAppleDispenserBehaviour;
import com.rubixdev.rug.RugSettings;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {
    @Inject(method = "getBehaviorForItem", at = @At("HEAD"), cancellable = true)
    private void onGetBehaviourForItem(ItemStack stack, CallbackInfoReturnable<DispenserBehavior> cir) {
        if (RugSettings.dispensersCureVillagers && stack.getItem() == Items.GOLDEN_APPLE) {
            cir.setReturnValue(new GoldenAppleDispenserBehaviour());
        }
    }
}
