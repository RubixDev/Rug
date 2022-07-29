package de.rubixdev.rug.mixins.reach;

import de.rubixdev.rug.RugSettings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    @Final
    private MinecraftClient client;

    @ModifyConstant(method = "updateTargetedEntity", allow = 1, require = 1, constant = @Constant(doubleValue = 6.0))
    private double changeCreativeAttackRange(double baseReachDistance) {
        ClientPlayerInteractionManager manager = this.client.interactionManager;
        if (manager == null) {
            return RugSettings.reachDistance + 1.5;
        }
        return manager.getReachDistance() + 1;
    }

    @ModifyConstant(method = "updateTargetedEntity", allow = 1, require = 1, constant = @Constant(doubleValue = 9.0))
    private double changeSurvivalAttackRange(double baseReachDistance) {
        return Math.pow(Math.sqrt(baseReachDistance) + RugSettings.reachDistance - 4.5, 2);
    }
}
