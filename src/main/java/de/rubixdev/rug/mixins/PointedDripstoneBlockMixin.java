package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

//#if MC >= 12103
import net.minecraft.server.world.ServerWorld;
//#endif

@Mixin(PointedDripstoneBlock.class)
public class PointedDripstoneBlockMixin extends Block {
    public PointedDripstoneBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    // TODO: rewrite this with Mixin inheritance
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        //#if MC >= 12103
        if (RugSettings.stalagmiteSteppingDamage > 0 && world instanceof ServerWorld serverWorld) {
            entity.damage(serverWorld, world.getDamageSources().generic(), RugSettings.stalagmiteSteppingDamage);
        }
        //#else
        //$$ if (RugSettings.stalagmiteSteppingDamage > 0) {
        //$$     entity.damage(world.getDamageSources().generic(), RugSettings.stalagmiteSteppingDamage);
        //$$ }
        //#endif
        super.onSteppedOn(world, pos, state, entity);
    }
}
