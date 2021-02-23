package com.rubixdev.rug.behaviours;

import com.rubixdev.rug.mixins.ZombieVillagerEntityMixin;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class GoldenAppleDispenserBehaviour extends FallibleItemDispenserBehavior {
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ServerWorld world = pointer.getWorld();
        if (!world.isClient()) {
            BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
            this.setSuccess(tryCureZombieVillager(world, blockPos));
            if (this.isSuccess()) {
                stack.decrement(1);
            }
        }
        return stack;
    }

    private static boolean tryCureZombieVillager(ServerWorld world, BlockPos pos) {
        List<ZombieVillagerEntity> list = world.getEntitiesByClass(ZombieVillagerEntity.class, new Box(pos), EntityPredicates.EXCEPT_SPECTATOR);

        for (ZombieVillagerEntity zombieVillagerEntity : list) {
            if (!zombieVillagerEntity.isConverting() && zombieVillagerEntity.hasStatusEffect(StatusEffects.WEAKNESS)) {
                ((ZombieVillagerEntityMixin) zombieVillagerEntity).invokeSetConverting(null, zombieVillagerEntity.getRandom().nextInt(2401) + 3600);
                return true;
            }
        }
        return false;
    }
}
