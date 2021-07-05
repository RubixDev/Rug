package com.rubixdev.rug.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.rubixdev.rug.RugSettings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.ChunkRandom;

public class SlimeChunkCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = CommandManager.literal("slimechunk")
            .requires((player) -> SettingsManager.canUseCommand(player, RugSettings.commandSlimeChunk))
            .executes(c -> {
                ServerPlayerEntity playerEntity = c.getSource().getPlayer();
                ChunkPos chunkPos = new ChunkPos(playerEntity.getBlockPos());
                StructureWorldAccess worldAccess = c.getSource().getWorld();
                boolean isSlimeChunk = ChunkRandom.getSlimeRandom(
                    chunkPos.x,
                    chunkPos.z,
                    worldAccess.getSeed(),
                    987234911L
                ).nextInt(10) < RugSettings.slimeChunkPercentage / 10;
                playerEntity.sendSystemMessage(
                    new LiteralText("You are " + ( isSlimeChunk ? "" : "not " ) + "in a Slime Chunk"),
                    Util.NIL_UUID
                );
                return 1;
            });
        dispatcher.register(command);
    }
}
