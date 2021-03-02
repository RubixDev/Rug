package com.rubixdev.rug;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.bundled.BundledModule;
import carpet.settings.ParsedRule;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class RugServer implements CarpetExtension {
    @Override
    public String version() {
        return "rug";
    }

    public static void noop() {
    }

    public static Map<String, String> datapackRules = new HashMap<>();

    static {
        CarpetServer.manageExtension(new RugServer());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(RugSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
            if (!RugSettings.easyHarvesting || world.isClient() || hand != Hand.MAIN_HAND) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();

            if (isMature(state)) {
                ItemStack tool = player != null ? player.getStackInHand(hand) : ItemStack.EMPTY;
                if (RugSettings.easyHarvestingRequireHoe && !(tool.getItem() instanceof HoeItem)) {
                    return ActionResult.PASS;
                }
                List<ItemStack> droppedItems = Block.getDroppedStacks(state, (ServerWorld) world, pos, null, player, tool);
                boolean removedSeed = false;
                for (ItemStack itemStack : droppedItems) {
                    if (!removedSeed) {
                        Item item = itemStack.getItem();
                        if (item instanceof BlockItem && ((BlockItem) item).getBlock() == block) {
                            itemStack.decrement(1);
                            removedSeed = true;
                        }
                    }
                    Block.dropStack(world, pos, itemStack);
                }

                world.setBlockState(pos, removedSeed ? state.with(getAgeProperty(block), 0) : Blocks.AIR.getDefaultState());

                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }));
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        registerDatapackRule(server, "easyDispenserCrafting");
        initializeDatapackRules(server);
    }

    @Override
    public void onTick(MinecraftServer server) {
        // maybe, maybe
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        // here goes extra stuff
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player) {
        // will need that for client features
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayerEntity player) {
        // will need that for client features
    }

    public void initializeDatapackRules(MinecraftServer server) {
        ResourcePackManager resourcePackManager = server.getCommandSource().getMinecraftServer().getDataPackManager();
        resourcePackManager.scanPacks();
        Collection<String> collection = Lists.newArrayList(resourcePackManager.getEnabledNames());
        datapackRules.forEach((ruleName, datapackName) -> {
            ParsedRule<?> rule = CarpetServer.settingsManager.getRule(ruleName);
            String enabledDataPack = Objects.requireNonNull(resourcePackManager.getProfile("file/" + datapackName)).getName();
            if (rule.getBoolValue()) {
                collection.add(enabledDataPack);
            } else {
                collection.remove(enabledDataPack);
            }
        });
        ReloadCommand.method_29480(collection, server.getCommandSource());
    }

    public void registerDatapackRule(MinecraftServer server, String ruleName) {
        String datapackName = "Rug_" + ruleName + ".zip";
        copyDatapackFolder(server, datapackName);
        datapackRules.put(ruleName, datapackName);
        CarpetServer.settingsManager.addRuleObserver((source, rule, s) -> {
            if (rule.name.equals(ruleName)) {
                ResourcePackManager resourcePackManager = source.getMinecraftServer().getDataPackManager();
                Collection<String> collection = Lists.newArrayList(resourcePackManager.getEnabledNames());
                String observedDatapackName = Objects.requireNonNull(resourcePackManager.getProfile("file/" + datapackName)).getName();
                if (rule.getBoolValue()) {
                    collection.add(observedDatapackName);
                } else {
                    collection.remove(observedDatapackName);
                }
                ReloadCommand.method_29480(collection, source);
            }
        });
    }

    private void copyDatapackFolder(MinecraftServer server, String datapackName) {
        try {
            String datapacks = server.getSavePath(WorldSavePath.DATAPACKS).toString();
            Files.copy(
                    Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/datapacks/" + datapackName)),
                    new File(datapacks, datapackName).toPath()
            );
        } catch (IOException ignored) {
        }
    }

    private static boolean isMature(BlockState state) {
        Block block = state.getBlock();
        if (block instanceof CropBlock) {
            return ((CropBlock) block).isMature(state);
        } else if (block instanceof NetherWartBlock) {
            return state.get(NetherWartBlock.AGE) == 3;
        } else if (block instanceof CocoaBlock) {
            return state.get(CocoaBlock.AGE) == 2;
        }
        return false;
    }

    private static IntProperty getAgeProperty(Block block) {
        if (block instanceof CropBlock) {
            return ((CropBlock) block).getAgeProperty();
        } else if (block instanceof NetherWartBlock) {
            return NetherWartBlock.AGE;
        } else if (block instanceof CocoaBlock) {
            return CocoaBlock.AGE;
        }
        return null;
    }
}
