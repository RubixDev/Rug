package de.rubixdev.rug.mixins.reach;

import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;

//#if MC < 12006
//$$ import de.rubixdev.rug.RugSettings;
//$$ import org.spongepowered.asm.mixin.injection.Constant;
//$$ import org.spongepowered.asm.mixin.injection.ModifyConstant;
//#endif

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    //#if MC < 12006
    //$$ @ModifyConstant(method = "canPlayerUse", allow = 1, require = 1, constant = @Constant(doubleValue = 64.0))
    //$$ private static double changeReachDistance(double baseReachDistance) {
    //$$     return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    //$$ }
    //#endif
}
