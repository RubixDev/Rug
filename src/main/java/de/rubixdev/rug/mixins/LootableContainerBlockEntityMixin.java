package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LootableContainerBlockEntity.class)
public class LootableContainerBlockEntityMixin {
    @ModifyConstant(
            method = "canPlayerUse",
            allow = 1,
            require = 1,
            constant = @Constant(doubleValue = 64.0)
    )
    private static double changeReachDistance(double baseReachDistance) {
        return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    }
}
