package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RedstoneLampBlock.class)
public class RedstoneLampBlockMixin {
    @Shadow
    @Final
    public static BooleanProperty LIT;

    @ModifyConstant(method = "neighborUpdate", constant = @Constant(intValue = 4))
    private int modifyLampDelay(final int baseDelay) {
        return RugSettings.redstoneLampTurnOffDelay;
    }

    @Inject(
        method = "neighborUpdate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;createAndScheduleBlockTick(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;I)V"
        ),
        cancellable = true
    )
    private void onNeighborUpdate(
        BlockState state,
        World world,
        BlockPos pos,
        Block block,
        BlockPos fromPos,
        boolean notify,
        CallbackInfo ci
    ) {
        if (RugSettings.redstoneLampTurnOffDelay == 0 && state.get(LIT) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT), 2);
            ci.cancel();
        }
    }
}
