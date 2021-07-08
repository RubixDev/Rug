package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Redirect(
        method = "use",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerEntity;getArrowType(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"
        )
    )
    private ItemStack onUse(PlayerEntity playerEntity, ItemStack stack) {
        ItemStack arrowType = playerEntity.getArrowType(stack);
        if (!RugSettings.infinityNeedsArrow
            && arrowType.isEmpty()
            && EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0) {
            return new ItemStack(Items.ARROW);
        } else {
            return arrowType;
        }
    }
}
