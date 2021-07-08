package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CactusBlock.class)
public class CactusBlockMixin extends Block {
    @Shadow
    @Final
    public static IntProperty AGE;

    public CactusBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "scheduledTick", at = @At("TAIL"))
    private void onScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (state.canPlaceAt(world, pos) && RugSettings.zeroTickPlants) {
            BlockPos blockPos = pos.up();
            if (world.isAir(blockPos)) {
                int i;
                for (i = 1; world.getBlockState(pos.down(i)).getBlock() == this; ++i) {}

                if (i < 3) {
                    int j = state.get(AGE);
                    if (j == 15) {
                        world.setBlockState(blockPos, this.getDefaultState());
                        BlockState blockState = state.with(AGE, 0);
                        world.setBlockState(pos, blockState, 4);
                        blockState.neighborUpdate(world, blockPos, this, pos, false);
                    } else {
                        world.setBlockState(pos, state.with(AGE, j + 1), 4);
                    }
                }
            }
        }
    }
}
