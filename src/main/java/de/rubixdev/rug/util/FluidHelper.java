package de.rubixdev.rug.util;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class FluidHelper {
    public static void playFizzleSound(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }

    public static boolean shouldConvertToBlackstone(BlockView world, BlockPos pos) {
        return RugSettings.basaltToBlackstoneConversion
            && isFluidAdjacent(FluidTags.WATER, world, pos)
            && isFluidAdjacent(FluidTags.LAVA, world, pos);
    }

    public static boolean shouldConvertToLava(BlockView world, BlockPos pos) {
        return RugSettings.basaltToLavaConversion
            && isFlowingFluidAdjacent(FluidTags.LAVA, world, pos)
            && isStillFluidAdjacent(FluidTags.LAVA, world, pos)
            && isBlockAdjacent(Blocks.MAGMA_BLOCK, world, pos);
    }

    private static boolean isBlockAdjacent(Block block, BlockView world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(pos.offset(direction)).isOf(block)) return true;
        }
        return false;
    }

    private static boolean isFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos, FluidFlowType type) {
        for (Direction direction : Direction.values()) {
            FluidState fluidState = world.getFluidState(pos.offset(direction));
            if (fluidState.isIn(fluid)
                && ( ( type == FluidFlowType.STILL && fluidState.isStill() )
                    || ( type == FluidFlowType.FLOWING && !fluidState.isStill() )
                    || ( type == FluidFlowType.ANY ) )) {

                return true;
            }
        }
        return false;
    }

    private static boolean isFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        return isFluidAdjacent(fluid, world, pos, FluidFlowType.ANY);
    }

    private static boolean isStillFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        return isFluidAdjacent(fluid, world, pos, FluidFlowType.STILL);
    }

    private static boolean isFlowingFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        return isFluidAdjacent(fluid, world, pos, FluidFlowType.FLOWING);
    }

    private enum FluidFlowType {
        STILL,
        FLOWING,
        ANY
    }
}
