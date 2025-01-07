package de.rubixdev.rug.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @WrapOperation(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropLoot(Lnet/minecraft/entity/damage/DamageSource;Z)V"))
    private void onDrop(LivingEntity instance, DamageSource damageSource, boolean causedByPlayer, Operation<Void> original) {
        if (this.getType().equals(EntityType.SHULKER) && RugSettings.strictShulkerShells > 0) {
            this.dropStack(new ItemStack(Items.SHULKER_SHELL, RugSettings.strictShulkerShells));
        } else {
            original.call(instance, damageSource, causedByPlayer);
        }
    }
}
