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

//#if MC >= 12103
import net.minecraft.server.world.ServerWorld;
//#endif

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
        //#if MC >= 12103
        if (!rugSetting.equals("none") && this.getWorld() instanceof ServerWorld world && world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            boolean dropAll = rugSetting.equals("all");

            if (dropAll || rugSetting.contains("elytra")) {
                this.dropStack(world, new ItemStack(Items.ELYTRA));
            }
            if (dropAll || rugSetting.contains("dragon_egg")) {
                this.dropStack(world, new ItemStack(Items.DRAGON_EGG));
            }
            if (dropAll || rugSetting.contains("dragon_head")) {
                this.dropStack(world, new ItemStack(Items.DRAGON_HEAD));
            }
        }
        //#else
        //$$ if (!rugSetting.equals("none") && this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
        //$$     boolean dropAll = rugSetting.equals("all");
        //$$
        //$$     if (dropAll || rugSetting.contains("elytra")) {
        //$$         this.dropStack(new ItemStack(Items.ELYTRA));
        //$$     }
        //$$     if (dropAll || rugSetting.contains("dragon_egg")) {
        //$$         this.dropStack(new ItemStack(Items.DRAGON_EGG));
        //$$     }
        //$$     if (dropAll || rugSetting.contains("dragon_head")) {
        //$$         this.dropStack(new ItemStack(Items.DRAGON_HEAD));
        //$$     }
        //$$ }
        //#endif
    }

    @ModifyConstant(method = "updatePostDeath", constant = @Constant(intValue = 500))
    private int overwriteXP(final int baseValue) {
        return RugSettings.dragonXpDrop;
    }
}
