package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(CactusBlock.class)
public class CactusBlockMixin extends Block {
    @Shadow @Final public static IntProperty AGE;

    public CactusBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author RubixDev
     */
    @Overwrite
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (RugSettings.zeroTickPlants) {
            BlockPos blockPos = pos.up();
            if (world.isAir(blockPos)) {
                int i;
                for (i = 1; world.getBlockState(pos.down(i)).getBlock() == this; ++i) {
                }

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
