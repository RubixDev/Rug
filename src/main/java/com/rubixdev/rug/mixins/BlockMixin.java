package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow
    public static void dropStack(World world, BlockPos pos, ItemStack stack) {
    }

    @Inject(
            method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
            cancellable = true,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;"
            )
    )
    private static void onDropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfo ci) {
        boolean usesSilkTouch = EnchantmentHelper.get(stack).containsKey(Enchantments.SILK_TOUCH) && stack.getItem() != Items.ENCHANTED_BOOK;

        if (RugSettings.silkTouchFarmland && state.isOf(Blocks.FARMLAND) && usesSilkTouch) {
            dropStack(world, pos, new ItemStack(Items.FARMLAND));
            ci.cancel();
        } else if (RugSettings.silkTouchPathBlocks && state.isOf(Blocks.DIRT_PATH) && usesSilkTouch) {
            dropStack(world, pos, new ItemStack(Items.DIRT_PATH));
            ci.cancel();
        } else if (RugSettings.silkTouchBuddingAmethysts && state.isOf(Blocks.BUDDING_AMETHYST) && usesSilkTouch) {
            dropStack(world, pos, new ItemStack(Items.BUDDING_AMETHYST));
            ci.cancel();
        } else if (RugSettings.silkTouchSpawners && state.isOf(Blocks.SPAWNER) && usesSilkTouch) {
            ItemStack newStack = new ItemStack(Items.SPAWNER);
            NbtCompound tag = blockEntity.writeNbt(new NbtCompound());
            tag.remove("id");
            tag.remove("x");
            tag.remove("y");
            tag.remove("z");
            newStack.putSubTag("BlockEntityTag", tag);
            dropStack(world, pos, newStack);
            ci.cancel();
        }
    }
}
