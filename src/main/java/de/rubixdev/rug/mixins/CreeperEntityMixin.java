package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
    @Redirect(
            method = "explode",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"))
    private Explosion noCreeperGriefing(
            World instance,
            Entity entity,
            double x,
            double y,
            double z,
            float power,
            World.ExplosionSourceType explosionSourceType) {
        return instance.createExplosion(
                entity,
                x,
                y,
                z,
                power,
                RugSettings.noCreeperGriefing ? World.ExplosionSourceType.NONE : explosionSourceType);
    }
}
