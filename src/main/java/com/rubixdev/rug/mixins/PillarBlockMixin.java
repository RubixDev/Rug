package com.rubixdev.rug.mixins;

import com.rubixdev.rug.util.Storage;
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

        if (this.is(Blocks.BASALT) && Storage.shouldConvertBasalt(world, pos)) {
            cir.setReturnValue(Blocks.BLACKSTONE.getDefaultState());
            world.syncWorldEvent(1501, pos, 0);
        }
    }
}
