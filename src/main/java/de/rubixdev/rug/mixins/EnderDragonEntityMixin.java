package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends Entity {
    public EnderDragonEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "updatePostDeath",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V",
                            ordinal = 1))
    private void onUpdatePostDeath(CallbackInfo ci) {
        String rugSetting = RugSettings.dragonDrops;
        if (!rugSetting.equals("none") && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            boolean dropAll = rugSetting.equals("all");

            if (rugSetting.contains("elytra") || dropAll) {
                this.dropStack(new ItemStack(Items.ELYTRA));
            }
            if (rugSetting.contains("dragon_egg") || dropAll) {
                this.dropStack(new ItemStack(Items.DRAGON_EGG));
            }
            if (rugSetting.contains("dragon_head") || dropAll) {
                this.dropStack(new ItemStack(Items.DRAGON_HEAD));
            }
        }
    }

    @ModifyConstant(method = "updatePostDeath", constant = @Constant(intValue = 500))
    private int overwriteXP(final int baseValue) {
        return RugSettings.dragonXpDrop;
    }
}
