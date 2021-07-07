package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends Entity {
    public VillagerEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
        method = "onDeath",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/damage/DamageSource;getAttacker()Lnet/minecraft/entity/Entity;"
        )
    )
    private void dropEmeralds(DamageSource source, CallbackInfo ci) {
        if (RugSettings.villagersDropEmeralds != 0) {
            dropStack(new ItemStack(Items.EMERALD, random.nextInt(RugSettings.villagersDropEmeralds) + 1));
        }
    }
}
