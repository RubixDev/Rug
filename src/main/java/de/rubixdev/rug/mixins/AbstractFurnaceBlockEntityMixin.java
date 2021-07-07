package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
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
    private static void dropExperience(ServerWorld world, Vec3d vec3d, int i, float f) {}

    private static boolean isCactusRecipe;

    @SuppressWarnings("rawtypes")
    @Inject(
        method = "method_17761(Ljava/util/List;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;Lit/unimi/dsi/fastutil/objects/Object2IntMap$Entry;Lnet/minecraft/recipe/Recipe;)V",
        at = @At(value = "INVOKE", target = "java/util/List.add(Ljava/lang/Object;)Z")
    )
    private static void onSyntheticMethod_17761(
        List list,
        ServerWorld world,
        Vec3d pos,
        Object2IntMap.Entry entry,
        Recipe recipe,
        CallbackInfo ci
    ) {
        if (recipe.getOutput().getItem() == Items.GREEN_DYE) {
            isCactusRecipe = true;
        }
    }

    @Redirect(
        method = "method_17761(Ljava/util/List;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;Lit/unimi/dsi/fastutil/objects/Object2IntMap$Entry;Lnet/minecraft/recipe/Recipe;)V",
        at = @At(
            value = "INVOKE",
            target = "net/minecraft/block/entity/AbstractFurnaceBlockEntity.dropExperience(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;IF)V"
        )
    )
    private static void onSyntheticMethod_17761(ServerWorld world, Vec3d vec3d, int i, float f) {
        dropExperience(world, vec3d, i, isCactusRecipe ? (float) RugSettings.cactusFurnaceXp : f);
        isCactusRecipe = false;
    }
}
