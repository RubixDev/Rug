package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Redirect(
        method = "onKilledOther",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"
        ),
        require = 0 // Compatability with minitweaks
    )
    private Difficulty allowConversion(ServerWorld world) {
        if (RugSettings.villagersAlwaysConvert && world.getDifficulty() != Difficulty.PEACEFUL) {
            return Difficulty.HARD;
        }
        return world.getDifficulty();
    }
}
