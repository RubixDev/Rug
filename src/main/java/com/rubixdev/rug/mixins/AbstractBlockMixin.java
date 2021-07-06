package com.rubixdev.rug.mixins;

import com.rubixdev.rug.util.Storage;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"), cancellable = true)
    private void convertBasalt(
        BlockState state,
        Direction direction,
        BlockState newState,
        WorldAccess world,
        BlockPos pos,
        BlockPos posFrom,
        CallbackInfoReturnable<BlockState> cir
    ) {
        if (( (Block) (Object) this ).is(Blocks.BASALT) && Storage.shouldConvertToBlackstone(world, pos)) {
            cir.setReturnValue(Blocks.BLACKSTONE.getDefaultState());
            Storage.playFizzleSound(world, pos);
        }
    }
}
