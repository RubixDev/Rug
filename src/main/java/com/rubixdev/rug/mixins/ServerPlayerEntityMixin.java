package com.rubixdev.rug.mixins;

import com.mojang.authlib.GameProfile;
import com.rubixdev.rug.RugSettings;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    @Shadow public abstract ServerWorld getServerWorld();

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "onDeath", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;drop(Lnet/minecraft/entity/damage/DamageSource;)V"))
    private void onOnDeath(DamageSource damageSource, CallbackInfo ci) {
        if ((RugSettings.playerHeadDrops.equals("on_killed_by_player") && damageSource.getAttacker() instanceof PlayerEntity)
                || (RugSettings.playerHeadDrops.equals("on_death"))) {
            ItemStack stack = new ItemStack(Items.PLAYER_HEAD);
            stack.getOrCreateTag().put("SkullOwner", NbtHelper.writeGameProfile(new NbtCompound(), this.getGameProfile()));
            this.dropStack(stack);
        }
    }

    @Inject(method = "setSpawnPoint", at = @At("HEAD"), cancellable = true)
    private void onSetSpawnPoint(RegistryKey<World> dimension, BlockPos pos, float angle, boolean spawnPointSet, boolean bl, CallbackInfo ci) {
        if (RugSettings.campSleeping && bl && this.isSneaking() && this.getServerWorld().getBlockState(pos).isIn(BlockTags.BEDS)) {
            ci.cancel();
        }
    }
}
