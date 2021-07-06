package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonHandler.class)
public abstract class PistonHandlerMixin {
    @Shadow
    private static boolean isBlockSticky(Block block) {
        return false;
    }

    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void onIsAdjacentBlockStuck(Block block, Block block2, CallbackInfoReturnable<Boolean> cir) {
        switch (RugSettings.honeyCombStickiness) {
            case "honey":
                if (( block2.is(Blocks.HONEYCOMB_BLOCK) && block.is(Blocks.SLIME_BLOCK) )
                    || ( block.is(Blocks.HONEYCOMB_BLOCK) && block2.is(Blocks.SLIME_BLOCK) )) {
                    cir.setReturnValue(false);
                }
                break;

            case "slime":
                if (( block2.is(Blocks.HONEYCOMB_BLOCK) && block.is(Blocks.HONEY_BLOCK) )
                    || ( block.is(Blocks.HONEYCOMB_BLOCK) && block2.is(Blocks.HONEY_BLOCK) )) {
                    cir.setReturnValue(false);
                }
                break;

            case "none":
                if (( block2.is(Blocks.HONEYCOMB_BLOCK) && isBlockSticky(block) )
                    || ( block.is(Blocks.HONEYCOMB_BLOCK) && isBlockSticky(block2) )) {
                    cir.setReturnValue(false);
                }
                break;
        }
    }
}
