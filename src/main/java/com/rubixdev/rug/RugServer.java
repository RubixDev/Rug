package com.rubixdev.rug;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.bundled.BundledModule;
import carpet.settings.ParsedRule;
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
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;

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
        boolean isFirstLoad = !Files.isDirectory(new File(datapackPath).toPath());
        try {
            Files.createDirectories(new File(datapackPath + "data/rug/recipes").toPath());
            Files.createDirectories(new File(datapackPath + "data/rug/advancements").toPath());
            Files.createDirectories(new File(datapackPath + "data/minecraft/recipes").toPath());
            Files.copy(
                    Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/Rug_flexibleDataStorage/pack.mcmeta")),
                    new File(datapackPath, "pack.mcmeta").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
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
        if (isFirstLoad) {
            server.getCommandManager().execute(server.getCommandSource(), "/datapack enable \"file/Rug_flexibleData\"");
        }
    }

    private void registerCraftingRule(String ruleName, String[] recipes, String recipeNamespace, String datapackPath, MinecraftServer server) {
        updateCraftingRule(CarpetServer.settingsManager.getRule(ruleName), recipes, recipeNamespace, datapackPath, ruleName);

        CarpetServer.settingsManager.addRuleObserver(((source, rule, s) -> {
            if (rule.name.equals(ruleName)) {
                updateCraftingRule(rule, recipes, recipeNamespace, datapackPath, ruleName);
                reload(server);
            }
        }));
    }

    private void updateCraftingRule(ParsedRule<?> rule, String[] recipes, String recipeNamespace, String datapackPath, String ruleName) {
        ruleName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, ruleName);

        if (rule.type == String.class) {
            String value = rule.getAsString();

            List<String> installedRecipes = Lists.newArrayList();
            try {
                Stream<Path> fileStream = Files.list(new File(datapackPath + recipeNamespace, "recipes").toPath());
                fileStream.forEach((path -> {
                    for (String recipeName : recipes) {
                        String fileName = path.getFileName().toString();
                        if (fileName.startsWith(recipeName)) {
                            installedRecipes.add(fileName);
                        }
                    }
                }));
            } catch (IOException ignored) {
            }

            deleteRecipes(installedRecipes.toArray(new String[0]), recipeNamespace, datapackPath, ruleName, false);

            if (recipeNamespace.equals("rug")) {
                List<String> installedAdvancements = Lists.newArrayList();
                try {
                    Stream<Path> fileStream = Files.list(new File(datapackPath, "rug/advancements").toPath());
                    String finalRuleName = ruleName;
                    fileStream.forEach((path -> {
                        String fileName = path.getFileName().toString().replace(".json", "");
                        if (fileName.startsWith(finalRuleName)) {
                            installedAdvancements.add(fileName);
                        }
                    }));
                } catch (IOException ignored) {
                }
                for (String advancement : installedAdvancements.toArray(new String[0])) {
                    removeAdvancement(datapackPath, advancement);
                }
            }

            if (!value.equals("off")) {
                List<String> tempRecipes = Lists.newArrayList();
                for (String recipeName : recipes) {
                    tempRecipes.add(recipeName + "_" + value + ".json");
                }

                copyRecipes(tempRecipes.toArray(new String[0]), recipeNamespace, datapackPath, ruleName + "_" + value);
            }
        } else {
            if (rule.getBoolValue()) {
                copyRecipes(recipes, recipeNamespace, datapackPath, ruleName);

                if (rule.type == int.class) {
                    int value = (Integer) rule.get();
                    for (String recipeName : recipes) {
                        String filePath = datapackPath + recipeNamespace + "/recipes/" + recipeName;
                        JsonObject jsonObject = readJson(filePath);
                        assert jsonObject != null;
                        jsonObject.getAsJsonObject("result").addProperty("count", value);
                        writeJson(jsonObject, filePath);
                    }
                }
            } else {
                deleteRecipes(recipes, recipeNamespace, datapackPath, ruleName, true);
            }
        }
    }

    private void copyRecipes(String[] recipes, String recipeNamespace, String datapackPath, String ruleName) {
        for (String recipeName : recipes) {
            try {
                Files.copy(
                        Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/rug/Rug_flexibleDataStorage/" + recipeNamespace + "/recipes/" + recipeName)),
                        new File(datapackPath + recipeNamespace + "/recipes", recipeName).toPath(),
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException ignored) {
            }
        }
        if (recipeNamespace.equals("rug")) {
            writeAdvancement(datapackPath, ruleName, recipes);
        }
    }

    private void deleteRecipes(String[] recipes, String recipeNamespace, String datapackPath, String ruleName, boolean removeAdvancement) {
        for (String recipeName : recipes) {
            try {
                Files.deleteIfExists(new File(datapackPath + recipeNamespace + "/recipes", recipeName).toPath());
            } catch (IOException ignored) {
            }
        }
        if (removeAdvancement && recipeNamespace.equals("rug")) {
            removeAdvancement(datapackPath, ruleName);
        }
    }

    private void writeAdvancement(String datapackPath, String ruleName, String[] recipes) {
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

    private void removeAdvancement(String datapackPath, String ruleName) {
        try {
            Files.deleteIfExists(new File(datapackPath + "rug/advancements/" + ruleName + ".json").toPath());
        } catch (IOException ignored) {
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
