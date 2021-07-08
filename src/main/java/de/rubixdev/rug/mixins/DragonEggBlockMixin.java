package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DragonEggBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DragonEggBlock.class)
public class DragonEggBlockMixin {
    @Inject(
        method = "teleport",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"
        )
    )
    private void onTeleport(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        String rugSetting = RugSettings.dragonEggConvertsCobbleToEndstone;
        if (( rugSetting.equals("both") || rugSetting.equals("on_teleport") )
            && world.getBlockState(pos.down()).getBlock().is(Blocks.COBBLESTONE)) {
            world.setBlockState(pos.down(), Blocks.END_STONE.getDefaultState(), 3);
        }
    }
}
