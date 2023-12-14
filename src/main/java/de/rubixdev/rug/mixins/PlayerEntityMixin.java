package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import de.rubixdev.rug.util.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Redirect(
            method = "tickMovement",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/World;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty onTickMovement(World world) {
        return RugSettings.peacefulHunger ? Difficulty.PEACEFUL : world.getDifficulty();
    }

    @Redirect(
            method = "tickMovement",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean onTickMovement(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule) {
        return !RugSettings.foodInstantHeal && gameRules.getBoolean(rule);
    }

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void savePlayer(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        Storage.player = (PlayerEntity) (Object) this;
    }

    @Inject(method = "canHarvest", at = @At("HEAD"), cancellable = true)
    private void onCanHarvest(BlockState block, CallbackInfoReturnable<Boolean> cir) {
        if ((RugSettings.silkTouchSpawners && block.isOf(Blocks.SPAWNER))
                || (RugSettings.silkTouchBuddingAmethysts && block.isOf(Blocks.BUDDING_AMETHYST))) {
            cir.setReturnValue(true);
        }
    }

    @ModifyVariable(method = "canConsume", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private boolean ignoreHunger(boolean original) {
        return RugSettings.foodInstantHeal || original;
    }

    //#if MC >= 12004
    @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 4.5f))
    private static float changeReachDistanceSurvival(final float baseReachDistance) {
        return (float) RugSettings.reachDistance;
    }

    @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 5.0f))
    private static float changeReachDistanceCreative(final float baseReachDistance) {
        return (float) RugSettings.reachDistance + 0.5f;
    }
    //#endif
}
