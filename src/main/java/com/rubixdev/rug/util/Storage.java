package com.rubixdev.rug.util;

import com.rubixdev.rug.RugSettings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Storage {
    public static PlayerEntity player;
    public static BlockPos blockPos;
    public static World world;

    public static boolean shouldConvertBasalt(BlockView world, BlockPos pos) {
        return RugSettings.basaltToBlackstoneConversion && isFluidAdjacent(FluidTags.WATER, world, pos) && isFluidAdjacent(FluidTags.LAVA, world, pos);
    }

    private static boolean isFluidAdjacent(Tag.Identified<Fluid> fluid, BlockView world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (world.getFluidState(pos.offset(direction)).isIn(fluid)) {
                return true;
            }
        }
        return false;
    }
}
