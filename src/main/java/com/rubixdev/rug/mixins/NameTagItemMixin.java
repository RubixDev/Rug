package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.NameTagItem;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Objects;

@Mixin(NameTagItem.class)
public abstract class NameTagItemMixin {
    @Redirect(method = "useOnEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setCustomName(Lnet/minecraft/text/Text;)V"))
    private void onUseOnEntity(LivingEntity entity, Text name) {
        if (RugSettings.silenceMobs && name.getString().equals("silence_me")) {
            entity.setCustomName(Text.of("silenced"));
            entity.setSilent(true);
        } else {
            if (entity.hasCustomName() && Objects.requireNonNull(entity.getCustomName()).getString().equals("silenced")) {
                entity.setSilent(false);
            }
            entity.setCustomName(name);
        }
    }
}
