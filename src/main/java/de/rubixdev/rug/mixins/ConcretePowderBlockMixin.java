package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.block.*;
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
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/BlockView;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;",
            ordinal = 0,
            shift = At.Shift.AFTER
        ),
        locals = LocalCapture.CAPTURE_FAILHARD,
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
            if (blockState.isOf(Blocks.WATER_CAULDRON) && blockState.get(LeveledCauldronBlock.LEVEL) == 3) {
                cir.setReturnValue(true);
            }
        }
    }
}
