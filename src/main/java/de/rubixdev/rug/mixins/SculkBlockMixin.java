package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.SculkBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
//#if MC >= 12004
import net.minecraft.util.math.intprovider.IntProvider;
//#endif

@Mixin(SculkBlock.class)
public class SculkBlockMixin extends ExperienceDroppingBlock {
    //#if MC >= 12004
    public SculkBlockMixin(IntProvider experienceDropped, Settings settings) {
        super(experienceDropped, settings);
    }
    //#else
    //$$ public SculkBlockMixin(Settings settings) {
    //$$     super(settings);
    //$$ }
    //#endif

    @Override
    public void onStacksDropped(
            BlockState state, ServerWorld world, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.onStacksDropped(state, world, pos, stack, dropExperience || RugSettings.sculkBlocksAlwaysDropXp);
    }
}
