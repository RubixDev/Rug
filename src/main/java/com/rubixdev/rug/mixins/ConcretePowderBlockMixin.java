package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ConcretePowderBlock.class)
public class ConcretePowderBlockMixin {


    @Inject(
        method = "hardensOnAnySide",
        at = @At(value = "JUMP", ordinal = 1, shift = At.Shift.AFTER),
        locals = LocalCapture.CAPTURE_FAILSOFT,
        cancellable = true
    )
    private static void onHardensOnAnySide(
        BlockView world,
        BlockPos pos,
        CallbackInfoReturnable<Boolean> cir,
        boolean bl,
        BlockPos.Mutable mutable,
        Direction[] var4,
        int var5,
        int var6,
        Direction direction
    ) {
        if (RugSettings.concreteConvertOnCauldron && direction == Direction.DOWN) {
            BlockState blockState = world.getBlockState(pos.down());
            if (blockState.getBlock().is(Blocks.CAULDRON) && blockState.get(CauldronBlock.LEVEL) == 3) {
                cir.setReturnValue(true);
            }
        }
    }
}
