package de.rubixdev.rug.mixins.reach;

import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;

//#if MC < 12006
//$$ import de.rubixdev.rug.RugSettings;
//$$ import org.spongepowered.asm.mixin.injection.Constant;
//$$ import org.spongepowered.asm.mixin.injection.ModifyConstant;
//#endif

@Mixin(Inventory.class)
public interface InventoryMixin {
    //#if MC < 12006
    //$$ @ModifyConstant(
    //$$         method =
    //$$                 "canPlayerUse(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/player/PlayerEntity;)Z",
    //$$         allow = 1,
    //$$         require = 1,
    //$$         constant = @Constant(intValue = 8))
    //$$ private static int changeReachDistance(int baseReachDistance) {
    //$$     return (int) Math.round(baseReachDistance + RugSettings.reachDistance - 4.5);
    //$$ }
    //#endif
}
