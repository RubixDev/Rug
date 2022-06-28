package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SculkBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SculkBlock.class)
public class SculkBlockMixin extends OreBlock {
    public SculkBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onStacksDropped(
        BlockState state,
        ServerWorld world,
        BlockPos pos,
        ItemStack stack,
        boolean dropExperience
    ) {
        super.onStacksDropped(state, world, pos, stack, dropExperience || RugSettings.sculkBlocksAlwaysDropXp);
    }
}
