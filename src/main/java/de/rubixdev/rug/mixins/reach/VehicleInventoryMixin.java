package de.rubixdev.rug.mixins.reach;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.vehicle.VehicleInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VehicleInventory.class)
public interface VehicleInventoryMixin {
    @ModifyConstant(method = "canPlayerAccess", allow = 1, require = 1, constant = @Constant(doubleValue = 8.0))
    private double changeReachDistance(double baseReachDistance) {
        return baseReachDistance + RugSettings.reachDistance - 4.5;
    }
}
