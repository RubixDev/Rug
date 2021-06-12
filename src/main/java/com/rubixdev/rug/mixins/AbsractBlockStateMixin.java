package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net/minecraft/block/AbstractBlock$AbstractBlockState"})
public class AbsractBlockStateMixin {
    @Inject(method = "getHardness(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F", at = @At("HEAD"), cancellable = true)
    private void onGetHardness(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (world.getBlockState(pos).isOf(Blocks.DRIED_KELP_BLOCK)) {
            cir.setReturnValue((float) RugSettings.kelpBlockHardness);
        }
    }
}
