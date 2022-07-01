package de.rubixdev.rug.mixins.reach;


import de.rubixdev.rug.RugSettings;
import net.minecraft.block.entity.EnderChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderChestBlockEntity.class)
public class EnderChestBlockEntityMixin {
    @ModifyConstant(method = "canPlayerUse", allow = 1, require = 1, constant = @Constant(doubleValue = 64.0))
    private static double changeReachDistance(double baseReachDistance) {
        return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    }
}
