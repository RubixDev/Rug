package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import java.util.Optional;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionEntity.class)
public abstract class PotionEntityMixin extends ThrownEntity {
    protected PotionEntityMixin(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(
        method = "onBlockHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/projectile/thrown/PotionEntity;extinguishFire(Lnet/minecraft/util/math/BlockPos;)V",
            ordinal = 0
        )
    )
    private void ageCopper(BlockHitResult blockHitResult, CallbackInfo ci) {
        BlockPos blockPos = blockHitResult.getBlockPos();
        BlockState blockState = this.world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if (block instanceof Oxidizable && RugSettings.splashOxidize) {
            Optional<BlockState> oxidized = ( (Oxidizable) block ).getDegradationResult(blockState);
            oxidized.ifPresent(state -> this.world.setBlockState(blockPos, state));
        }
    }
}
