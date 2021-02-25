package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import jdk.internal.org.objectweb.asm.Opcodes;
import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @Redirect(method = "update", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/HungerManager;foodLevel:I", opcode = Opcodes.PUTFIELD))
    private void onUpdate(HungerManager hungerManager, int value) {
        if (!RugSettings.peacefulHunger) {
            hungerManager.setFoodLevel(value);
        }
    }
}
