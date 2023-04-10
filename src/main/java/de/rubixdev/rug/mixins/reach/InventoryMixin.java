package de.rubixdev.rug.mixins.reach;

import de.rubixdev.rug.RugSettings;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Inventory.class)
public interface InventoryMixin {
    @ModifyConstant(
            method =
                    "canPlayerUse(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/player/PlayerEntity;)Z",
            allow = 1,
            require = 1,
            constant = @Constant(intValue = 8))
    private static int changeReachDistance(int baseReachDistance) {
        return (int) Math.round(baseReachDistance + RugSettings.reachDistance - 4.5);
    }
}
