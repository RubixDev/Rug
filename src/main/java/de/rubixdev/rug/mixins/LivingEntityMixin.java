package de.rubixdev.rug.mixins;


import de.rubixdev.rug.RugSettings;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    protected abstract void dropInventory();

    @Shadow
    protected abstract void dropXp();

    @Shadow
    protected abstract void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops);

    @Shadow
    protected int playerHitTimer;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "drop", at = @At("HEAD"), cancellable = true)
    private void onDrop(DamageSource source, CallbackInfo ci) {
        Entity dyingEntity = this;

        if (dyingEntity.getType().equals(EntityType.SHULKER)
            && RugSettings.strictShulkerShells > 0
            && dyingEntity.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            ItemStack stack = new ItemStack(Items.SHULKER_SHELL);
            stack.setCount(RugSettings.strictShulkerShells);
            dyingEntity.dropStack(stack);

            Entity attacker = source.getAttacker();
            int lootingMultiplier;
            if (attacker instanceof PlayerEntity) {
                lootingMultiplier = EnchantmentHelper.getLooting((LivingEntity) attacker);
            } else {
                lootingMultiplier = 0;
            }

            this.dropEquipment(source, lootingMultiplier, this.playerHitTimer > 0);
            this.dropInventory();
            this.dropXp();

            ci.cancel();
        }
    }
}
