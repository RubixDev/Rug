package de.rubixdev.rug.mixins.reach;

import net.minecraft.entity.vehicle.VehicleInventory;
import org.spongepowered.asm.mixin.Mixin;

//#if MC < 12006
//$$ import de.rubixdev.rug.RugSettings;
//$$ import org.spongepowered.asm.mixin.injection.Constant;
//$$ import org.spongepowered.asm.mixin.injection.ModifyConstant;
//#endif

@Mixin(VehicleInventory.class)
public interface VehicleInventoryMixin {
    //#if MC < 12006
    //$$ @ModifyConstant(method = "canPlayerAccess", allow = 1, require = 1, constant = @Constant(doubleValue = 8.0))
    //$$ private double changeReachDistance(double baseReachDistance) {
    //$$     return baseReachDistance + RugSettings.reachDistance - 4.5;
    //$$ }
    //#endif
}
