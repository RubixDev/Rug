package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import de.rubixdev.rug.util.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.state.property.IntProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RepeaterBlock.class)
public class RepeaterBlockMixin {
    @Shadow
    @Final
    public static IntProperty DELAY;

    @Inject(method = "getUpdateDelayInternal", at = @At("HEAD"), cancellable = true)
    private void modifyDelay(BlockState state, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(getModifiedDelay(state));
    }

    private int getModifiedDelay(BlockState state) {
        int defaultDelay = state.get(DELAY) * 2;
        if (RugSettings.longerRepeaters > 1
            && Storage.world.getBlockState(Storage.blockPos.down()).isOf(Blocks.REDSTONE_BLOCK)) {
            return defaultDelay * RugSettings.longerRepeaters;
        } else {
            return defaultDelay;
        }
    }
}
