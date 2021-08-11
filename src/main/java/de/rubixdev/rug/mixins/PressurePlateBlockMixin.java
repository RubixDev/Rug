package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PressurePlateBlock.class)
public class PressurePlateBlockMixin {
    @Redirect(
        method = "getRedstoneOutput(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)I",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;canAvoidTraps()Z")
    )
    private boolean disableItemFrames(Entity entity) {
        if (!RugSettings.itemFramesActivatePressurePlates && entity.getType() == EntityType.ITEM_FRAME) { return true; }
        return entity.canAvoidTraps();
    }
}
