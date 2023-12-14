package de.rubixdev.rug.mixins.reach;

//#if MC < 12004
//$$ import de.rubixdev.rug.RugSettings;
//$$ import net.minecraft.client.MinecraftClient;
//$$ import org.spongepowered.asm.mixin.Final;
//$$ import org.spongepowered.asm.mixin.Shadow;
//$$ import org.spongepowered.asm.mixin.injection.Constant;
//$$ import org.spongepowered.asm.mixin.injection.ModifyConstant;
//#endif
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    //#if MC < 12004
    //$$ @Shadow @Final private MinecraftClient client;

    //$$ @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 4.5F))
    //$$ private float changeReachDistanceSurvival(final float baseReachDistance) {
    //$$     if (this.client.player != null) {
    //$$         return (float) RugSettings.reachDistance;
    //$$     }
    //$$     return baseReachDistance;
    //$$ }

    //$$ @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 5.0F))
    //$$ private float changeReachDistanceCreative(final float baseReachDistance) {
    //$$     if (this.client.player != null) {
    //$$         return (float) RugSettings.reachDistance + 0.5F;
    //$$     }
    //$$     return baseReachDistance;
    //$$ }
    //#endif
}
