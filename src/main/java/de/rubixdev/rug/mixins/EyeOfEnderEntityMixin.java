package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EyeOfEnderEntity.class)
public class EyeOfEnderEntityMixin {
    @Redirect(
        method = "initTargetPos",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I")
    )
    private int overwriteChance(Random random, int bound) {
        return random.nextInt(100) > RugSettings.enderEyeDropChance - 1 ? 0 : 1;
    }
}
