package de.rubixdev.rug.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
    @ModifyExpressionValue(method = "explode", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World$ExplosionSourceType;MOB:Lnet/minecraft/world/World$ExplosionSourceType;"))
    private World.ExplosionSourceType noCreeperGriefing(World.ExplosionSourceType original) {
        // TODO: test this
        return RugSettings.noCreeperGriefing ? World.ExplosionSourceType.NONE : original;
    }
}
