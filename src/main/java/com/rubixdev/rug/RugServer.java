package com.rubixdev.rug;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.bundled.BundledModule;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.gson.*;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class RugServer implements CarpetExtension {
    @Override
    public String version() {
        return "rug";
    }

    public static void noop() {
    }

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
        String datapackPath = server.getSavePath(WorldSavePath.DATAPACKS).toString() + "/Rug_flexibleData/";
        try {
            Files.createDirectories(new File(datapackPath + "data/rug/recipes").toPath());
            Files.createDirectories(new File(datapackPath + "data/rug/advancements").toPath());
            Files.createDirectories(new File(datapackPath + "data/minecraft/recipes").toPath());
            Files.copy(
                    Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/Rug_flexibleDataStorage/pack.mcmeta")),
                    new File(datapackPath, "pack.mcmeta").toPath()
            );
        } catch (IOException ignored) {
        }
        try {
            Files.copy(
                    Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/Rug_flexibleDataStorage/rug/advancements/root.json")),
                    new File(datapackPath, "data/rug/advancements/root.json").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException ignored) {
        }

        for (Field f : RugSettings.class.getDeclaredFields()) {
            CraftingRule craftingRule = f.getAnnotation(CraftingRule.class);
            if (craftingRule == null) continue;
            registerCraftingRule(
                    craftingRule.name().isEmpty() ? f.getName() : craftingRule.name(),
                    craftingRule.recipes(),
                    craftingRule.recipeNamespace(),
                    datapackPath + "data/",
                    server
            );
        }
        reload(server);
    }

    public void registerCraftingRule(String ruleName, String[] recipes, String recipeNamespace, String datapackPath, MinecraftServer server) {
        copyOrDeleteRecipes(CarpetServer.settingsManager.getRule(ruleName).getBoolValue(), recipes, recipeNamespace, datapackPath, ruleName);

        CarpetServer.settingsManager.addRuleObserver(((source, rule, s) -> {
            if (rule.name.equals(ruleName)) {
                copyOrDeleteRecipes(rule.getBoolValue(), recipes, recipeNamespace, datapackPath, ruleName);
                reload(server);
            }
        }));
    }

    private void copyOrDeleteRecipes(boolean isEnabled, String[] recipes, String recipeNamespace, String datapackPath, String ruleName) {
        ruleName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, ruleName);

        if (isEnabled) {
            for (String recipeName : recipes) {
                try {
                    Files.copy(
                            Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/Rug_flexibleDataStorage/" + recipeNamespace + "/recipes/" + recipeName)),
                            new File(datapackPath + recipeNamespace + "/recipes", recipeName).toPath()
                    );
                } catch (IOException ignored) {
                }
            }
            if (recipeNamespace.equals("rug")) {
                try {
                    Files.copy(
                            Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/Rug_flexibleDataStorage/rug/advancements/recipe_rule.json")),
                            new File(datapackPath, "rug/advancements/" + ruleName + ".json").toPath(),
                            StandardCopyOption.REPLACE_EXISTING
                    );
                } catch (IOException ignored) {
                }
                JsonObject advancementJson = readJson(datapackPath + "rug/advancements/" + ruleName + ".json");
                assert advancementJson != null;
                JsonArray recipeRewards = advancementJson.getAsJsonObject("rewards").getAsJsonArray("recipes");

                for (String recipeName : recipes) {
                    recipeRewards.add("rug:" + recipeName.replace(".json", ""));
                }
                writeJson(advancementJson, datapackPath + "rug/advancements/" + ruleName + ".json");
            }
        } else {
            for (String recipeName : recipes) {
                try {
                    Files.deleteIfExists(new File(datapackPath + recipeNamespace + "/recipes", recipeName).toPath());
                } catch (IOException ignored) {
                }
            }
            if (recipeNamespace.equals("rug")) {
                try {
                    Files.deleteIfExists(new File(datapackPath + "rug/advancements/" + ruleName + ".json").toPath());
                } catch (IOException ignored) {
                }
            }
        }
    }

    private JsonObject readJson(String filePath) {
        JsonParser jsonParser = new JsonParser();
        try {
            FileReader reader = new FileReader(filePath);
            return jsonParser.parse(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeJson(JsonObject jsonObject, String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            writer.close();
        } catch (IOException ignored) {
        }
    }

    private void reload(MinecraftServer server) {
        ResourcePackManager resourcePackManager = server.getDataPackManager();
        resourcePackManager.scanPacks();
        Collection<String> collection = Lists.newArrayList(resourcePackManager.getEnabledNames());
        collection.add("Rug_flexibleData");

        ReloadCommand.method_29480(collection, server.getCommandSource());
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
