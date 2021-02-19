package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected abstract void drop(DamageSource source);

    @Redirect(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;drop(Lnet/minecraft/entity/damage/DamageSource;)V"))
    private void onOnDeath(LivingEntity dyingEntity, DamageSource damageSource) {
        if (dyingEntity instanceof ShulkerEntity && RugSettings.strictShulkerShells > 0 && dyingEntity.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            ItemStack stack = new ItemStack(Items.SHULKER_SHELL);
            stack.setCount(RugSettings.strictShulkerShells);
            dyingEntity.dropStack(stack);
        } else {
            this.drop(damageSource);
        }
    }
}
