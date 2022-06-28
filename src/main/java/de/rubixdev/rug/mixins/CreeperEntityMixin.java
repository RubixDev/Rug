package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "explode", at = @At("STORE"))
    private Explosion.DestructionType onExplode(Explosion.DestructionType destructionType) {
        return RugSettings.noCreeperGriefing ? Explosion.DestructionType.NONE : destructionType;
    }
}
