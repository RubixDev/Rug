package de.rubixdev.rug.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//#if MC >= 12101
import net.minecraft.registry.RegistryKeys;
//#endif

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

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

    //#if MC >= 12004 && MC < 12006
    //$$ @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 4.5f))
    //$$ private static float changeReachDistanceSurvival(final float baseReachDistance) {
    //$$     return (float) RugSettings.reachDistance;
    //$$ }
    //$$
    //$$ @ModifyConstant(method = "getReachDistance", require = 1, allow = 1, constant = @Constant(floatValue = 5.0f))
    //$$ private static float changeReachDistanceCreative(final float baseReachDistance) {
    //$$     return (float) RugSettings.reachDistance + 0.5f;
    //$$ }
    //#endif

    @ModifyExpressionValue(method = "getProjectileType", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z"))
    private boolean infinityNeedsArrow(boolean original, ItemStack bow) {
        return original || (
                !RugSettings.infinityNeedsArrow
                        //#if MC >= 12101
                        && EnchantmentHelper.getLevel(getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.INFINITY), bow) > 0
                        //#else
                        //$$ && EnchantmentHelper.getLevel(Enchantments.INFINITY, bow) > 0
                        //#endif
        );
    }
}
