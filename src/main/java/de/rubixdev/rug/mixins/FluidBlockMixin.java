package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {
    @Unique
    private boolean blockWasPlaced = true;

    @Shadow
    protected abstract void playExtinguishSound(WorldAccess world, BlockPos pos);

    @Inject(
            method = "receiveNeighborFluids",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/fluid/FluidState;isStill()Z"),
            cancellable = true)
    private void generateNetherrack(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.netherrackGeneration
                && world.getBlockState(pos.down()).isOf(Blocks.MAGMA_BLOCK)
                && !world.getFluidState(pos).isStill()) {
            world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
            this.playExtinguishSound(world, pos);
            cir.setReturnValue(false);
        }
    }

    @Redirect(
            method = "receiveNeighborFluids",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"))
    private boolean getBlockSetResult(World world, BlockPos pos, BlockState state) {
        blockWasPlaced = world.setBlockState(pos, state);
        return blockWasPlaced;
    }

    @Inject(
            method = "receiveNeighborFluids",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/block/FluidBlock;playExtinguishSound(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;)V"),
            cancellable = true)
    private void catchExtinguishSound(
            World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.basaltToLavaConversion && !blockWasPlaced) {
            cir.setReturnValue(false);
        }
    }
}
