package de.rubixdev.rug.mixins;

import de.rubixdev.rug.RugSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.ChunkRandom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin {
    private static BlockPos blockPos;
    private static WorldAccess worldAccess;

    @Inject(method = "canSpawn", at = @At("HEAD"))
    private static void getParams(
        EntityType<SlimeEntity> type,
        WorldAccess world,
        SpawnReason spawnReason,
        BlockPos pos,
        Random random,
        CallbackInfoReturnable<Boolean> cir
    ) {
        blockPos = pos;
        worldAccess = world;
    }

    @ModifyVariable(method = "canSpawn", at = @At("STORE"), ordinal = 0)
    private static boolean overwriteChance(boolean original) {
        ChunkPos chunkPos = new ChunkPos(blockPos);
        return ChunkRandom.getSlimeRandom(
            chunkPos.x,
            chunkPos.z,
            ( (StructureWorldAccess) worldAccess ).getSeed(),
            987234911L
        ).nextInt(10) < RugSettings.slimeChunkPercentage / 10;
    }
}
