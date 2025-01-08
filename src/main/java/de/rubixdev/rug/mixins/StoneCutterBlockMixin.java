package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

//#if MC >= 12103
import net.minecraft.server.world.ServerWorld;
//#endif

@Mixin(StonecutterBlock.class)
public class StoneCutterBlockMixin extends Block {
    public StoneCutterBlockMixin(Settings settings) {
        super(settings);
    }

    // TODO: rewrite this with Mixin inheritance
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (RugSettings.stonecutterDamage > 0 && !(entity instanceof ItemEntity)) {
            //#if MC >= 12103
            if (world instanceof ServerWorld serverWorld)
                entity.damage(serverWorld, world.getDamageSources().generic(), RugSettings.stonecutterDamage);
            //#else
            //$$ entity.damage(world.getDamageSources().generic(), RugSettings.stonecutterDamage);
            //#endif
        }
    }
}
