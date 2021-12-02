package de.rubixdev.rug.mixins;

import com.google.common.collect.Lists;
import de.rubixdev.rug.RugSettings;
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
        method = "shouldUseNetherFortressSpawns",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z")
    )
    private static boolean allowMoreSpawnableFortressBlocks(BlockState floorBlock, Block netherBricks) {
        if (RugSettings.moreFortressSpawningBlocks.equals("all")) return true;

        List<Block> allowedBlocks = Lists.newArrayList(
            netherBricks,
            Blocks.NETHERRACK,
            Blocks.SOUL_SAND,
            Blocks.SOUL_SOIL,
            Blocks.PACKED_ICE,
            Blocks.BLUE_ICE,
            Blocks.GRAVEL,
            Blocks.MAGMA_BLOCK,
            Blocks.RED_NETHER_BRICKS
        );

        return RugSettings.moreFortressSpawningBlocks.equals("more") && allowedBlocks.contains(floorBlock.getBlock()) || floorBlock.isOf(netherBricks);
    }
}
