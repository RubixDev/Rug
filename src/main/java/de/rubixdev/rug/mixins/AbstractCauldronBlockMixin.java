package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//#if MC >= 12006
import net.minecraft.item.ItemStack;
import net.minecraft.util.ItemActionResult;
//#else
//$$ import net.minecraft.util.ActionResult;
//#endif

@Mixin(AbstractCauldronBlock.class)
public class AbstractCauldronBlockMixin {
    //#if MC >= 12006
    @Inject(
            method = "onUseWithItem",
            at =
            @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;",
                    shift = At.Shift.AFTER),
            cancellable = true)
    //#else
    //$$ @Inject(
    //$$         method = "onUse",
    //$$         at =
    //$$                 @At(
    //$$                         value = "INVOKE",
    //$$                         target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;",
    //$$                         shift = At.Shift.AFTER),
    //$$         cancellable = true)
    //#endif
    private void onOnUse(
            //#if MC >= 12006
            ItemStack stack,
            //#endif
            BlockState state,
            World world,
            BlockPos pos,
            PlayerEntity player,
            Hand hand,
            BlockHitResult hit,
            //#if MC >= 12006
            CallbackInfoReturnable<ItemActionResult> cir
            //#else
            //$$ CallbackInfoReturnable<ActionResult> cir
            //#endif
    ) {
        Item item = player.getStackInHand(hand).getItem();
        if (RugSettings.lilyPadsOnCauldron && item == Items.LILY_PAD && state.get(LeveledCauldronBlock.LEVEL) == 3) {
            if (world.getBlockState(pos.up()).isOf(Blocks.AIR)) {

                world.setBlockState(pos.up(), Blocks.LILY_PAD.getDefaultState());
                world.playSound(null, pos, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);

                if (!player.isCreative()) {
                    player.getStackInHand(hand).decrement(1);
                }

                //#if MC >= 12006
                cir.setReturnValue(ItemActionResult.SUCCESS);
                //#else
                //$$ cir.setReturnValue(ActionResult.SUCCESS);
                //#endif
            }
        }
    }
}
