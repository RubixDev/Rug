package com.rubixdev.rug;

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GoldenAppleDispenserBehaviour extends FallibleItemDispenserBehavior {
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        ServerWorld world = pointer.getWorld();
        if (!world.isClient()) {
            BlockPos blockPos = pointer.getBlockPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
            this.setSuccess(tryCureVillager(world, blockPos));
            if (this.isSuccess()) {
                stack.decrement(1);
            }
        }
        return stack;
    }

    private static boolean tryCureVillager(ServerWorld world, BlockPos pos) {
        List<ZombieVillagerEntity> list = world.getEntitiesByClass(ZombieVillagerEntity.class, new Box(pos), EntityPredicates.EXCEPT_SPECTATOR);

        for (ZombieVillagerEntity zombieVillagerEntity : list) {
            if (zombieVillagerEntity.hasStatusEffect(StatusEffects.WEAKNESS)) {
                try {
                    Method m = ZombieVillagerEntity.class.getDeclaredMethod("setConverting", UUID.class, int.class);
                    m.setAccessible(true);
                    m.invoke(zombieVillagerEntity, null, new Random().nextInt(2401) + 3600);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
