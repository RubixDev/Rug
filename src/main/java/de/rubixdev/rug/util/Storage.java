package de.rubixdev.rug.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.storage.LevelStorage;

public class Storage {
    public static PlayerEntity player;
    public static BlockPos blockPos;
    public static World world;

    public static LevelStorage.Session session;
}
