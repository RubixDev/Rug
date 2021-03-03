package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractRedstoneGateBlock.class)
public abstract class AbstractRedstoneGateBlockMixin {
    @Shadow
    protected abstract int getUpdateDelayInternal(BlockState state);

    private ServerWorld serverWorld;
    private BlockPos blockPos;
    private World world;

    @Inject(method = "scheduledTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractRedstoneGateBlock;getUpdateDelayInternal(Lnet/minecraft/block/BlockState;)I"))
    private void onScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        this.serverWorld = world;
        this.blockPos = pos;
    }

    @Redirect(method = "scheduledTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractRedstoneGateBlock;getUpdateDelayInternal(Lnet/minecraft/block/BlockState;)I"))
    private int onScheduledTick(AbstractRedstoneGateBlock abstractRedstoneGateBlock, BlockState state) {
        int defaultDelay = this.getUpdateDelayInternal(state);
        if (
                RugSettings.longerRepeaters
                        && state.getBlock().is(Blocks.REPEATER)
                        && this.serverWorld.getBlockState(this.blockPos.down()).getBlock().is(Blocks.REDSTONE_BLOCK)
        ) {
            return defaultDelay * 2;
        } else {
            return defaultDelay;
        }
    }

    @Inject(method = "updatePowered", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractRedstoneGateBlock;getUpdateDelayInternal(Lnet/minecraft/block/BlockState;)I"))
    private void onUpdatePowered(World world, BlockPos pos, BlockState state, CallbackInfo ci) {
        this.world = world;
        this.blockPos = pos;
    }

    @Redirect(method = "updatePowered", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractRedstoneGateBlock;getUpdateDelayInternal(Lnet/minecraft/block/BlockState;)I"))
    private int onUpdatePowered(AbstractRedstoneGateBlock abstractRedstoneGateBlock, BlockState state) {
        int defaultDelay = this.getUpdateDelayInternal(state);
        if (
                RugSettings.longerRepeaters
                        && state.getBlock().is(Blocks.REPEATER)
                        && this.world.getBlockState(this.blockPos.down()).getBlock().is(Blocks.REDSTONE_BLOCK)
        ) {
            return defaultDelay * 2;
        } else {
            return defaultDelay;
        }
    }
}
