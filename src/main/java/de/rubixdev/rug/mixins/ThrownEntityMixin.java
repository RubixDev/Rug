package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
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
        } else if ((Object) this instanceof SnowballEntity) {
            return (float) RugSettings.snowballWaterDrag;
        } else if ((Object) this instanceof EggEntity) {
            return (float) RugSettings.eggWaterDrag;
        }
        return baseValue;
    }
}
