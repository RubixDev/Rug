package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ThrownEntity.class)
public class ThrownEntityMixin {
    @SuppressWarnings("ConstantConditions")
    @ModifyConstant(method = "tick", constant = @Constant(floatValue = 0.8F))
    private float onTick(final float baseValue) {
        if ((Object) this instanceof EnderPearlEntity) {
            return (float) RugSettings.enderPearlWaterDrag;
        }
        return baseValue;
    }
}
