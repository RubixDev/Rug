package com.rubixdev.rug.util;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class Storage {
    public static PlayerEntity player;
    public static BlockPos blockPos;
    public static World world;

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

    private static boolean isFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos, short type) {
        for (Direction direction : Direction.values()) {
            FluidState fluidState = world.getFluidState(pos.offset(direction));
            if (fluidState.isIn(fluid)
                && ( ( type == 1 && fluidState.isStill() )
                    || ( type == 2 && !fluidState.isStill() )
                    || ( type == 0 ) )) {

                return true;
            }
        }
        return false;
    }

    private static boolean isFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        return isFluidAdjacent(fluid, world, pos, (short) 0);
    }

    private static boolean isStillFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        return isFluidAdjacent(fluid, world, pos, (short) 1);
    }

    private static boolean isFlowingFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        return isFluidAdjacent(fluid, world, pos, (short) 2);
    }
}
