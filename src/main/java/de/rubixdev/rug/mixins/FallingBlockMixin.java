package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FallingBlock.class)
public class FallingBlockMixin {
    @Inject(method = "canFallThrough", at = @At("HEAD"), cancellable = true)
    private static void onCanFallThrough(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.tallPlantNoUpdate && isTallPlant(state)) {
            cir.setReturnValue(false);
        }
    }

    private static boolean isTallPlant(BlockState blockState) {
        return blockState.isIn(BlockTags.TALL_FLOWERS)
                || blockState.isOf(Blocks.TALL_GRASS)
                || blockState.isOf(Blocks.LARGE_FERN);
    }
}
