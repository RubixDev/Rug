package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Unique
    private ServerPlayerEntity interactPlayer;
    @Unique
    private BlockState interactBlock;

    @Inject(method = "interactBlock", at = @At("HEAD"))
    private void getParameters(
            ServerPlayerEntity player,
            World world,
            ItemStack stack,
            Hand hand,
            BlockHitResult hitResult,
            CallbackInfoReturnable<ActionResult> cir) {
        interactPlayer = player;
        interactBlock = world.getBlockState(hitResult.getBlockPos());
    }

    @ModifyVariable(
            method = "interactBlock",
            at =
                    @At(
                            value = "INVOKE_ASSIGN",
                            target = "Lnet/minecraft/server/network/ServerPlayerEntity;shouldCancelInteraction()Z"))
    private boolean allowSneakRightClick(boolean original) {
        if (RugSettings.campSleeping
                && interactBlock.isIn(BlockTags.BEDS)
                && interactPlayer.isSneaking()
                && interactPlayer.getMainHandStack().isEmpty()) {
            return false;
        }
        return original;
    }
}
