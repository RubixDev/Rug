package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.AbstractPressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractPressurePlateBlock.class)
public class AbstractPressurePlateBlockMixin {
    @Redirect(
            method = "method_52209",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;canAvoidTraps()Z"))
    private static boolean disableItemFrames(Entity entity) {
        if (!RugSettings.itemFramesActivatePressurePlates && entity.getType() == EntityType.ITEM_FRAME) {
            return true;
        }
        return entity.canAvoidTraps();
    }
}
