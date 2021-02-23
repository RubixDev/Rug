package com.rubixdev.rug.mixins;

import net.minecraft.entity.mob.ZombieVillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.UUID;

@Mixin(ZombieVillagerEntity.class)
public interface ZombieVillagerEntityMixin {
    @Invoker("setConverting")
    void invokeSetConverting(UUID uuid, int delay);
}
