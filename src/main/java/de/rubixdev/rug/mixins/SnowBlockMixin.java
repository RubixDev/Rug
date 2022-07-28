package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnowBlock.class)
public class SnowBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void toggleMelting(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!RugSettings.snowMelting) ci.cancel();
    }
}
