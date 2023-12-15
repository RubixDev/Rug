package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Degradable.class)
public interface DegradableMixin {
    @ModifyVariable(
            method = "tryDegrade",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextFloat()F", shift = At.Shift.BEFORE),
            ordinal = 1)
    private float applyUnderwaterMultiplier(float value, BlockState state, ServerWorld world, BlockPos pos) {
        if (RugSettings.copperUnderwaterOxidationMultiplier == 1 || !(state.getBlock() instanceof OxidizableBlock)) {
            return value;
        }
        byte waterCount = 0;
        for (Direction dir : Direction.values()) {
            BlockState neighbor = world.getBlockState(pos.offset(dir));
            if (neighbor.isOf(Blocks.WATER)) {
                waterCount++;
            }
        }
        if (waterCount < 3) return value;
        return (float) (value * RugSettings.copperUnderwaterOxidationMultiplier);
    }
}
