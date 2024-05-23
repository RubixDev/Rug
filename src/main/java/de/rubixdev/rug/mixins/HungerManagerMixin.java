package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import de.rubixdev.rug.util.Storage;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
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
    @Redirect(
            method = "update",
            at =
                    @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/entity/player/HungerManager;foodLevel:I",
                            opcode = Opcodes.PUTFIELD))
    private void onUpdate(HungerManager hungerManager, int value) {
        if (!RugSettings.peacefulHunger) {
            hungerManager.setFoodLevel(value);
        }
    }

    @Redirect(
            method = "update",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean onUpdate(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule) {
        if (RugSettings.foodInstantHeal) {
            return false;
        } else {
            return gameRules.getBoolean(rule);
        }
    }

    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    //#if MC >= 12006
    private void onEat(ItemStack stack, CallbackInfo ci) {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (RugSettings.foodInstantHeal && foodComponent != null) {
            Storage.player.heal(foodComponent.nutrition());
            ci.cancel();
        }
    }
    //#else
    //$$ private void onEat(Item item, ItemStack stack, CallbackInfo ci) {
    //$$     if (RugSettings.foodInstantHeal && item.isFood()) {
    //$$         Storage.player.heal(Objects.requireNonNull(item.getFoodComponent()).getHunger());
    //$$         ci.cancel();
    //$$     }
    //$$ }
    //#endif
}
