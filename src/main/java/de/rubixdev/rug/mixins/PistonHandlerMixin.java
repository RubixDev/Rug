package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
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
    private static boolean isBlockSticky(BlockState state) {
        return false;
    }

    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void onIsAdjacentBlockStuck(
        BlockState state,
        BlockState adjacentState,
        CallbackInfoReturnable<Boolean> cir
    ) {
        switch (RugSettings.honeyCombStickiness) {
            case "honey":
                if (( adjacentState.isOf(Blocks.HONEYCOMB_BLOCK) && state.isOf(Blocks.SLIME_BLOCK) )
                    || ( state.isOf(Blocks.HONEYCOMB_BLOCK) && adjacentState.isOf(Blocks.SLIME_BLOCK) )) {
                    cir.setReturnValue(false);
                }
                break;

            case "slime":
                if (( adjacentState.isOf(Blocks.HONEYCOMB_BLOCK) && state.isOf(Blocks.HONEY_BLOCK) )
                    || ( state.isOf(Blocks.HONEYCOMB_BLOCK) && adjacentState.isOf(Blocks.HONEY_BLOCK) )) {
                    cir.setReturnValue(false);
                }
                break;

            case "none":
                if (( adjacentState.isOf(Blocks.HONEYCOMB_BLOCK) && isBlockSticky(state) )
                    || ( state.isOf(Blocks.HONEYCOMB_BLOCK) && isBlockSticky(adjacentState) )) {
                    cir.setReturnValue(false);
                }
                break;
        }
    }
}
