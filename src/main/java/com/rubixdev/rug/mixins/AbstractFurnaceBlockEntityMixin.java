package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    @Shadow
    private static void dropExperience(World world, Vec3d vec3d, int i, float f) {
    }

    private static boolean isCactusRecipe;

    @Inject(
            method = "method_17761(Ljava/util/List;Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3d;Lit/unimi/dsi/fastutil/objects/Object2IntMap$Entry;Lnet/minecraft/recipe/Recipe;)V",
            at = @At(
                    value = "INVOKE",
                    target = "java/util/List.add(Ljava/lang/Object;)Z"
            )
    )
    private static void onSyntheticMethod_17761(List list, World world, Vec3d vec3d, Object2IntMap.Entry entry, Recipe recipe, CallbackInfo ci) {
        if (recipe.getOutput().getItem() == Items.GREEN_DYE) {
            isCactusRecipe = true;
        }
    }

    @Redirect(
            method = "method_17761(Ljava/util/List;Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3d;Lit/unimi/dsi/fastutil/objects/Object2IntMap$Entry;Lnet/minecraft/recipe/Recipe;)V",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/block/entity/AbstractFurnaceBlockEntity.dropExperience(Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3d;IF)V"
            )
    )
    private static void onSyntheticMethod_17761(World world, Vec3d vec3d, int i, float f) {
        dropExperience(world, vec3d, i, isCactusRecipe ? (float) RugSettings.cactusFurnaceXP : f);
        isCactusRecipe = false;
    }
}
