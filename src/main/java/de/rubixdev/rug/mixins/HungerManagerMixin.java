package de.rubixdev.rug.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC >= 12006
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
//#else
//$$ import java.util.Objects;
//$$ import net.minecraft.item.Item;
//#endif

@Mixin(HungerManager.class)
public class HungerManagerMixin {
    @Unique private int pendingHealing = 0;

    @WrapOperation(
            method = "update",
            at =
                    @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/entity/player/HungerManager;foodLevel:I",
                            opcode = Opcodes.PUTFIELD))
    private void dontSetFoodLevel(HungerManager instance, int value, Operation<Void> original) {
        if (!RugSettings.peacefulHunger) {
            original.call(instance, value);
        }
    }

    @ModifyExpressionValue(
            method = "update",
            at =
            @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean noNaturalRegen(boolean original) {
        return original && !RugSettings.foodInstantHeal;
    }

    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    //#if MC >= 12101
    private void instantHeal(FoodComponent foodComponent, CallbackInfo ci) {
        if (RugSettings.foodInstantHeal) {
            pendingHealing += foodComponent.nutrition();
            ci.cancel();
        }
    }
    //#elseif MC >= 12006
    //$$ private void instantHeal(ItemStack stack, CallbackInfo ci) {
    //$$     FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
    //$$     if (RugSettings.foodInstantHeal && foodComponent != null) {
    //$$         pendingHealing += foodComponent.nutrition();
    //$$         ci.cancel();
    //$$     }
    //$$ }
    //#else
    //$$ private void instantHeal(Item item, ItemStack stack, CallbackInfo ci) {
    //$$     if (RugSettings.foodInstantHeal && item.isFood()) {
    //$$         pendingHealing += Objects.requireNonNull(item.getFoodComponent()).getHunger();
    //$$         ci.cancel();
    //$$     }
    //$$ }
    //#endif

    @Inject(method = "update", at = @At("HEAD"))
    private void applyPendingHealing(PlayerEntity player, CallbackInfo ci) {
        if (pendingHealing > 0) {
            player.heal(pendingHealing);
            pendingHealing = 0;
        }
    }
}
