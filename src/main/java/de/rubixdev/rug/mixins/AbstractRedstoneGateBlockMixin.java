package de.rubixdev.rug.mixins;

import de.rubixdev.rug.util.Storage;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractRedstoneGateBlock.class)
public abstract class AbstractRedstoneGateBlockMixin {

    @Inject(
        method = "scheduledTick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractRedstoneGateBlock;getUpdateDelayInternal(Lnet/minecraft/block/BlockState;)I"
        )
    )
    private void onScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        Storage.world = world;
        Storage.blockPos = pos;
    }

    @Inject(
        method = "updatePowered",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/AbstractRedstoneGateBlock;getUpdateDelayInternal(Lnet/minecraft/block/BlockState;)I"
        )
    )
    private void onUpdatePowered(World world, BlockPos pos, BlockState state, CallbackInfo ci) {
        Storage.world = world;
        Storage.blockPos = pos;
    }
}
