package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void onEatFood(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = user.getStackInHand(hand);

        Item item = stack.getItem();
        if (
                item == Items.NETHERITE_SCRAP && !RugSettings.edibleNetheriteScraps
                        || item == Items.SLIME_BALL && !RugSettings.edibleSlimeBalls
                        || item == Items.GOLD_INGOT && !RugSettings.edibleGoldIngots
                        || item == Items.MAGMA_CREAM && !RugSettings.edibleMagmaCream
        ) {
            cir.setReturnValue(TypedActionResult.pass(stack));
        }
    }

    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void onGetMaxUseTime(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (stack.getItem() == Items.GOLD_INGOT && RugSettings.edibleGoldIngots) {
            cir.setReturnValue(128);
        }
    }
}
