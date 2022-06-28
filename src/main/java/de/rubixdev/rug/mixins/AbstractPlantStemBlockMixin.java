package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.block.AbstractPlantPartBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractPlantStemBlock.class)
public abstract class AbstractPlantStemBlockMixin extends AbstractPlantPartBlock {

    protected AbstractPlantStemBlockMixin(
        Settings settings,
        Direction growthDirection,
        VoxelShape outlineShape,
        boolean tickWater
    ) {
        super(settings, growthDirection, outlineShape, tickWater);
    }

    @SuppressWarnings("deprecation")
    @Shadow
    public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (RugSettings.zeroTickPlants) {
            this.randomTick(state, world, pos, random);
        }
    }
}
