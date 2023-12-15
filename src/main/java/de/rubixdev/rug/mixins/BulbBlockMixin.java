package de.rubixdev.rug.mixins;

import org.spongepowered.asm.mixin.Mixin;
//#if MC >= 12004
import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.BulbBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BulbBlock.class)
public abstract class BulbBlockMixin extends AbstractBlockMixin {
    @Shadow public abstract void update(BlockState state, ServerWorld world, BlockPos pos);

    @Redirect(
            method = "onBlockAdded",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BulbBlock;update(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V"))
    private void updateWithDelay(BulbBlock instance, BlockState state, ServerWorld world, BlockPos pos) {
        if (RugSettings.bulbDelay != 0) {
            world.scheduleBlockTick(pos, instance, RugSettings.bulbDelay);
        } else {
            instance.update(state, world, pos);
        }
    }

    @Redirect(
            method = "neighborUpdate",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BulbBlock;update(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V"))
    private void updateWithDelay2(BulbBlock instance, BlockState state, ServerWorld world, BlockPos pos) {
        if (RugSettings.bulbDelay != 0) {
            world.scheduleBlockTick(pos, instance, RugSettings.bulbDelay);
        } else {
            instance.update(state, world, pos);
        }
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (RugSettings.bulbDelay != 0) this.update(state, world, pos);
    }
}
//#else
//$$ @Mixin(net.minecraft.SharedConstants.class)
//$$ public abstract class BulbBlockMixin {}
//#endif
