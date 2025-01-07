package de.rubixdev.rug.mixins;

import com.mojang.authlib.GameProfile;
import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//#if MC >= 12006
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
//#else
//$$ import net.minecraft.nbt.NbtCompound;
//$$ import net.minecraft.nbt.NbtHelper;
//#endif

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(
        method = "onDeath",
        at = @At(
            value = "INVOKE",
            //#if MC >= 12101
            target = "Lnet/minecraft/server/network/ServerPlayerEntity;drop(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;)V"
            //#else
            //$$ target = "Lnet/minecraft/server/network/ServerPlayerEntity;drop(Lnet/minecraft/entity/damage/DamageSource;)V"
            //#endif
        )
    )
    private void onOnDeath(DamageSource damageSource, CallbackInfo ci) {
        if ((RugSettings.playerHeadDrops.equals("on_killed_by_player")
                        && damageSource.getAttacker() instanceof PlayerEntity)
                || (RugSettings.playerHeadDrops.equals("on_death"))) {
            ItemStack stack = new ItemStack(Items.PLAYER_HEAD);
            //#if MC >= 12006
            stack.set(DataComponentTypes.PROFILE, new ProfileComponent(this.getGameProfile()));
            //#else
            //$$ stack.getOrCreateNbt()
            //$$         .put("SkullOwner", NbtHelper.writeGameProfile(new NbtCompound(), this.getGameProfile()));
            //#endif
            this.dropStack(stack);
        }
    }

    @Inject(method = "setSpawnPoint", at = @At("HEAD"), cancellable = true)
    private void onSetSpawnPoint(
            RegistryKey<World> dimension,
            BlockPos pos,
            float angle,
            boolean spawnPointSet,
            boolean bl,
            CallbackInfo ci) {
        if (RugSettings.campSleeping
                && bl
                && this.isSneaking()
                && this.getWorld().getBlockState(pos).isIn(BlockTags.BEDS)) {
            ci.cancel();
        }
    }
}
