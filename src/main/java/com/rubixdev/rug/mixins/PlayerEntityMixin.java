package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty onTickMovement(World world) {
        if (RugSettings.peacefulHunger) {
            return Difficulty.PEACEFUL;
        } else {
            return world.getDifficulty();
        }
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean onTickMovement(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule) {
        if (RugSettings.foodInstantHeal) {
            return false;
        } else {
            return gameRules.getBoolean(rule);
        }
    }

    @Redirect(method = "eatFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;eat(Lnet/minecraft/item/Item;Lnet/minecraft/item/ItemStack;)V"))
    private void onEatFood(HungerManager hungerManager, Item item, ItemStack stack) {
        if (RugSettings.foodInstantHeal && item.isFood()) {
            this.heal(Objects.requireNonNull(item.getFoodComponent()).getHunger());
        } else {
            hungerManager.eat(item, stack);
        }
    }

    @Inject(method = "isUsingEffectiveTool", at = @At("HEAD"), cancellable = true)
    private void onIsUsingEffectiveTool(BlockState block, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.silkTouchSpawners && block.getBlock().is(Blocks.SPAWNER)) {
            cir.setReturnValue(true);
        }
    }
}
