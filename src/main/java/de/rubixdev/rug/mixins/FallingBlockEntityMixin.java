package de.rubixdev.rug.mixins;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.google.common.collect.Lists;

import de.rubixdev.rug.RugSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

@Mixin(FallingBlockEntity.class)
public abstract class FallingBlockEntityMixin extends Entity {
    @Shadow
    private BlockState block;

    private int frostedIceCount;
    private int iceCount;
    private int packedIceCount;

    public FallingBlockEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
        method = "tick",
        at = @At(value = "FIELD", target = "Lnet/minecraft/entity/FallingBlockEntity;destroyedOnLanding:Z"),
        locals = LocalCapture.CAPTURE_FAILHARD,
        cancellable = true
    )
    private void onTick(CallbackInfo ci, Block block, BlockPos pos) {
        BlockPos posBelow = new BlockPos(this.getX(), this.getY() - 0.06, this.getZ());
        BlockState blockStateBelow = world.getBlockState(posBelow);
        Block blockBelow = blockStateBelow.getBlock();

        if (this.block.isIn(BlockTags.ANVIL)) {
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
            } else if (blockBelow == Blocks.GRAVEL && RugSettings.gravelCrushing) {
                world.breakBlock(posBelow, false);
                world.setBlockState(posBelow, Blocks.SAND.getDefaultState(), 3);
            }
        } else if (this.block.isOf(Blocks.DRAGON_EGG)) {
            String rugSetting = RugSettings.dragonEggConvertsCobbleToEndstone;
            if (blockBelow == Blocks.COBBLESTONE && ( rugSetting.equals("both") || rugSetting.equals("on_landing") )) {
                world.breakBlock(posBelow, false);
                world.setBlockState(posBelow, Blocks.END_STONE.getDefaultState(), 3);
            }
        } else if (( this.block.isOf(Blocks.SAND) && blockStateBelow.isOf(Blocks.GRAVEL) )
            || ( this.block.isOf(Blocks.GRAVEL) && blockStateBelow.isOf(Blocks.SAND) )) {
                BlockState concreteBlock = world.getBlockState(posBelow.down());
                if (isConcrete(concreteBlock) && RugSettings.concreteMixing) {
                    world.syncWorldEvent(
                        WorldEvents.BLOCK_BROKEN,
                        posBelow,
                        Block.getRawIdFromState(Blocks.SAND.getDefaultState())
                    );
                    world.syncWorldEvent(
                        WorldEvents.BLOCK_BROKEN,
                        posBelow,
                        Block.getRawIdFromState(Blocks.GRAVEL.getDefaultState())
                    );

                    Block powderBlock = getCorrespondingPowder(concreteBlock);
                    assert powderBlock != null;
                    world.setBlockState(posBelow, powderBlock.getDefaultState(), 3);
                    this.discard();
                    ci.cancel();
                }
            }
    }

    private boolean isConcrete(BlockState blockState) {
        List<BlockState> concreteBlocks = Lists.newArrayList(
            Blocks.WHITE_CONCRETE.getDefaultState(),
            Blocks.ORANGE_CONCRETE.getDefaultState(),
            Blocks.MAGENTA_CONCRETE.getDefaultState(),
            Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(),
            Blocks.YELLOW_CONCRETE.getDefaultState(),
            Blocks.LIME_CONCRETE.getDefaultState(),
            Blocks.PINK_CONCRETE.getDefaultState(),
            Blocks.GRAY_CONCRETE.getDefaultState(),
            Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(),
            Blocks.CYAN_CONCRETE.getDefaultState(),
            Blocks.PURPLE_CONCRETE.getDefaultState(),
            Blocks.BLUE_CONCRETE.getDefaultState(),
            Blocks.BROWN_CONCRETE.getDefaultState(),
            Blocks.GREEN_CONCRETE.getDefaultState(),
            Blocks.RED_CONCRETE.getDefaultState(),
            Blocks.BLACK_CONCRETE.getDefaultState()
        );

        return concreteBlocks.contains(blockState);
    }

    private Block getCorrespondingPowder(BlockState concreteBlock) {
        if (concreteBlock.isOf(Blocks.WHITE_CONCRETE)) {
            return Blocks.WHITE_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.ORANGE_CONCRETE)) {
            return Blocks.ORANGE_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.MAGENTA_CONCRETE)) {
            return Blocks.MAGENTA_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.LIGHT_BLUE_CONCRETE)) {
            return Blocks.LIGHT_BLUE_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.YELLOW_CONCRETE)) {
            return Blocks.YELLOW_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.LIME_CONCRETE)) {
            return Blocks.LIME_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.PINK_CONCRETE)) {
            return Blocks.PINK_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.GRAY_CONCRETE)) {
            return Blocks.GRAY_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.LIGHT_GRAY_CONCRETE)) {
            return Blocks.LIGHT_GRAY_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.CYAN_CONCRETE)) {
            return Blocks.CYAN_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.PURPLE_CONCRETE)) {
            return Blocks.PURPLE_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.BLUE_CONCRETE)) {
            return Blocks.BLUE_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.BROWN_CONCRETE)) {
            return Blocks.BROWN_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.GREEN_CONCRETE)) {
            return Blocks.GREEN_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.RED_CONCRETE)) {
            return Blocks.RED_CONCRETE_POWDER;
        } else if (concreteBlock.isOf(Blocks.BLACK_CONCRETE)) {
            return Blocks.BLACK_CONCRETE_POWDER;
        } else {
            return null;
        }
    }
}
