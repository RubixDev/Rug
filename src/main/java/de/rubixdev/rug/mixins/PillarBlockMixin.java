package de.rubixdev.rug.mixins;


import de.rubixdev.rug.util.FluidHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PillarBlock.class)
public class PillarBlockMixin extends Block {
    public PillarBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
    private void convertBasalt(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        WorldAccess world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();

        if (this == Blocks.BASALT && FluidHelper.shouldConvertToBlackstone(world, pos)) {
            cir.setReturnValue(Blocks.BLACKSTONE.getDefaultState());
            FluidHelper.playFizzleSound(world, pos);
        }
    }
}
