package de.rubixdev.rug.mixins;

import de.rubixdev.rug.util.FluidHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//#if MC >= 12004
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//#endif

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
            CallbackInfoReturnable<BlockState> cir) {
        if (state.isOf(Blocks.BASALT) && FluidHelper.shouldConvertToBlackstone(world, pos)) {
            cir.setReturnValue(Blocks.BLACKSTONE.getDefaultState());
            FluidHelper.playFizzleSound(world, pos);
        }
    }

    //#if MC >= 12004
    @Inject(method = "scheduledTick", at = @At("HEAD"))
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {}
    //#endif
}
