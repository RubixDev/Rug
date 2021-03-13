package com.rubixdev.rug.mixins;

import com.google.common.collect.Lists;
import com.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    public FallingBlockEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    private int frostedIceCount;
    private int iceCount;
    private int packedIceCount;

    @Inject(method = "tick", at = @At(value = "INVOKE", ordinal = 0,
            target = "Lnet/minecraft/entity/FallingBlockEntity;remove()V"),
            slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z", ordinal = 1)),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private void onTick(CallbackInfo ci, Block block, BlockPos pos) {
        BlockPos posBelow = new BlockPos(this.getX(), this.getY() - 0.06, this.getZ());
        Block blockBelow = world.getBlockState(posBelow).getBlock();

        if (block.isIn(BlockTags.ANVIL)) {
            if (blockBelow == Blocks.FROSTED_ICE && RugSettings.anvilledIce > 0) {
                if (++frostedIceCount < RugSettings.anvilledIce) {
                    world.breakBlock(posBelow, false);
                    onGround = false;
                    ci.cancel();
                } else {
                    world.breakBlock(posBelow, false);
                    world.setBlockState(posBelow, Blocks.ICE.getDefaultState(), 3);
                }
            } else if (blockBelow == Blocks.ICE && RugSettings.anvilledPackedIce > 0) {
                if (++iceCount < RugSettings.anvilledPackedIce) {
                    world.breakBlock(posBelow, false);
                    onGround = false;
                    ci.cancel();
                } else {
                    world.breakBlock(posBelow, false);
                    world.setBlockState(posBelow, Blocks.PACKED_ICE.getDefaultState(), 3);
                }
            } else if (blockBelow == Blocks.PACKED_ICE && RugSettings.anvilledBlueIce > 0) {
                if (++packedIceCount < RugSettings.anvilledBlueIce) {
                    world.breakBlock(posBelow, false);
                    onGround = false;
                    ci.cancel();
                } else {
                    world.breakBlock(posBelow, false);
                    world.setBlockState(posBelow, Blocks.BLUE_ICE.getDefaultState(), 3);
                }
            } else if (blockBelow == Blocks.COBBLESTONE && RugSettings.cobbleCrushing) {
                world.breakBlock(posBelow, false);
                world.setBlockState(posBelow, Blocks.GRAVEL.getDefaultState(), 3);
            }
        } else if (block.is(Blocks.DRAGON_EGG)) {
            String rugSetting = RugSettings.dragonEggConvertsCobbleToEndstone;
            if (blockBelow == Blocks.COBBLESTONE && (rugSetting.equals("both") || rugSetting.equals("on_landing"))) {
                world.breakBlock(posBelow, false);
                world.setBlockState(posBelow, Blocks.END_STONE.getDefaultState(), 3);
            }
        } else if ((block.is(Blocks.SAND) && blockBelow.is(Blocks.GRAVEL))
                || (block.is(Blocks.GRAVEL) && blockBelow.is(Blocks.SAND))) {
            Block concreteBlock = world.getBlockState(posBelow.down()).getBlock();
            if (isConcrete(concreteBlock) && RugSettings.concreteMixing) {
                world.syncWorldEvent(2001, posBelow, Block.getRawIdFromState(Blocks.SAND.getDefaultState()));
                world.syncWorldEvent(2001, posBelow, Block.getRawIdFromState(Blocks.GRAVEL.getDefaultState()));

                Block powderBlock = getCorrespondingPowder(concreteBlock);
                assert powderBlock != null;
                world.setBlockState(posBelow, powderBlock.getDefaultState(), 3);
                this.remove();
                ci.cancel();
            }
        }
    }

    private boolean isConcrete(Block block) {
        List<Block> concreteBlocks = Lists.newArrayList(
                Blocks.WHITE_CONCRETE,
                Blocks.ORANGE_CONCRETE,
                Blocks.MAGENTA_CONCRETE,
                Blocks.LIGHT_BLUE_CONCRETE,
                Blocks.YELLOW_CONCRETE,
                Blocks.LIME_CONCRETE,
                Blocks.PINK_CONCRETE,
                Blocks.GRAY_CONCRETE,
                Blocks.LIGHT_GRAY_CONCRETE,
                Blocks.CYAN_CONCRETE,
                Blocks.PURPLE_CONCRETE,
                Blocks.BLUE_CONCRETE,
                Blocks.BROWN_CONCRETE,
                Blocks.GREEN_CONCRETE,
                Blocks.RED_CONCRETE,
                Blocks.BLACK_CONCRETE
        );

        return concreteBlocks.contains(block);
    }

    private Block getCorrespondingPowder(Block concreteBlock) {
        if (concreteBlock.is(Blocks.WHITE_CONCRETE)) {
            return Blocks.WHITE_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.ORANGE_CONCRETE)) {
            return Blocks.ORANGE_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.MAGENTA_CONCRETE)) {
            return Blocks.MAGENTA_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.LIGHT_BLUE_CONCRETE)) {
            return Blocks.LIGHT_BLUE_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.YELLOW_CONCRETE)) {
            return Blocks.YELLOW_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.LIME_CONCRETE)) {
            return Blocks.LIME_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.PINK_CONCRETE)) {
            return Blocks.PINK_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.GRAY_CONCRETE)) {
            return Blocks.GRAY_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.LIGHT_GRAY_CONCRETE)) {
            return Blocks.LIGHT_GRAY_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.CYAN_CONCRETE)) {
            return Blocks.CYAN_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.PURPLE_CONCRETE)) {
            return Blocks.PURPLE_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.BLUE_CONCRETE)) {
            return Blocks.BLUE_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.BROWN_CONCRETE)) {
            return Blocks.BROWN_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.GREEN_CONCRETE)) {
            return Blocks.GREEN_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.RED_CONCRETE)) {
            return Blocks.RED_CONCRETE_POWDER;
        } else if (concreteBlock.is(Blocks.BLACK_CONCRETE)) {
            return Blocks.BLACK_CONCRETE_POWDER;
        } else {
            return null;
        }
    }
}
