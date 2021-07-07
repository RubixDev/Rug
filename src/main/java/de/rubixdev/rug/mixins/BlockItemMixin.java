package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin {
    @Shadow
    public abstract Block getBlock();

    private boolean isValidLilyPad;

    @Inject(
        method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
        at = @At("HEAD")
    )
    private void onPlace(ItemPlacementContext context, CallbackInfoReturnable<ActionResult> cir) {
        isValidLilyPad = RugSettings.lilyPadsOnCauldron
            && context.getStack().getItem() == Items.LILY_PAD
            && context.getWorld().getBlockState(context.getBlockPos().down()).isOf(Blocks.CAULDRON);
    }

    @Redirect(
        method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V"
        )
    )
    private void onPlace(
        World world,
        PlayerEntity player,
        BlockPos pos,
        SoundEvent sound,
        SoundCategory category,
        float volume,
        float pitch
    ) {
        if (isValidLilyPad) {
            world.playSound(null, pos, sound, category, volume, pitch);
        } else {
            world.playSound(player, pos, sound, category, volume, pitch);
        }
    }

    @Redirect(
        method = "place(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/util/ActionResult;",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/ActionResult;success(Z)Lnet/minecraft/util/ActionResult;"
        )
    )
    private ActionResult onPlace(boolean swingHand) {
        if (isValidLilyPad) {
            return ActionResult.success(true);
        } else {
            return ActionResult.success(swingHand);
        }
    }
}
