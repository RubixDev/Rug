package com.rubixdev.rug.mixins;

import com.google.common.collect.Lists;
import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.SpawnHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(SpawnHelper.class)
public class SpawnHelperMixin {
    @Redirect(
        method = "method_29950",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;getBlock()Lnet/minecraft/block/Block;")
    )
    private static Block allowMoreSpawnableFortressBlocks(BlockState blockState) {
        Block block = blockState.getBlock();

        Block allow = Blocks.NETHER_BRICKS;
        if (RugSettings.moreFortressSpawningBlocks.equals("all")) { return allow; }

        List<Block> allowedBlocks = Lists.newArrayList(
            allow,
            Blocks.NETHERRACK,
            Blocks.SOUL_SAND,
            Blocks.SOUL_SOIL,
            Blocks.PACKED_ICE,
            Blocks.BLUE_ICE,
            Blocks.GRAVEL,
            Blocks.MAGMA_BLOCK,
            Blocks.RED_NETHER_BRICKS
        );
        if (RugSettings.moreFortressSpawningBlocks.equals("more") && allowedBlocks.contains(block)) { return allow; }

        return block;
    }
}
