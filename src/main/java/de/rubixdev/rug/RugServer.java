package de.rubixdev.rug;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.RuleHelper;
import carpet.api.settings.SettingsManager;
import carpet.script.Module;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mojang.brigadier.CommandDispatcher;
import de.rubixdev.rug.commands.*;
import de.rubixdev.rug.util.CraftingRule;
import de.rubixdev.rug.util.Logging;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Stream;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.block.*;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class RugServer implements CarpetExtension, ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Rug");
    public static final String MOD_ID = "rug";
    public static final String MOD_NAME;
    public static final String MOD_VERSION;
    public static SettingsManager settingsManager;

    private static MinecraftServer minecraftServer;
    private static Path datapackPath;

    static {
        ModMetadata metadata = FabricLoader.getInstance()
                .getModContainer(MOD_ID)
                .orElseThrow(RuntimeException::new)
                .getMetadata();
        MOD_NAME = metadata.getName();
        MOD_VERSION = metadata.getVersion().getFriendlyString();
        settingsManager = new SettingsManager(MOD_VERSION, MOD_ID, MOD_NAME);
    }

    @Override
    public String version() {
        return MOD_ID;
    }

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new RugServer());
    }

    @Override
    public SettingsManager extensionSettingsManager() {
        return settingsManager;
    }

    @Override
    public void onGameStarted() {
        LOGGER.info("Rug Mod v" + MOD_VERSION + " loaded!");

        // load rules into both settings managers
        settingsManager.parseSettingsClass(RugSettings.class);
        CarpetServer.settingsManager.parseSettingsClass(RugSettings.class);
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        InputStream langFile =
                RugServer.class.getClassLoader().getResourceAsStream("assets/rug/lang/%s.json".formatted(lang));
        if (langFile == null) {
            // we don't have that language
            return Collections.emptyMap();
        }
        String jsonData;
        try {
            jsonData = IOUtils.toString(langFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Collections.emptyMap();
        }

        // create translation keys for both Carpet and Rug settingsManagers
        Map<String, String> map = new Gson().fromJson(jsonData, new TypeToken<Map<String, String>>() {}.getType());
        Map<String, String> map2 = new HashMap<>();
        map.forEach((key, value) -> {
            map2.put(key, value);
            if(key.startsWith("rug.rule.")) {
                map2.put(key.replace("rug.rule.", "carpet.rule."), value);
            }
        });
        return map2;
    }

    @Override
    public void registerCommands(
            CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        SlimeChunkCommand.register(dispatcher);
        FrameCommand.register(dispatcher);
        SkullCommand.register(dispatcher);
        SudoCommand.register(dispatcher);
        PeekCommand.register(dispatcher);
        MaxEffectCommand.register(dispatcher, registryAccess);
        ModsCommand.register(dispatcher);
    }

    @Override
    public void onServerClosed(MinecraftServer server) {
        if (Files.isDirectory(datapackPath.resolve("data"))) {
            try {
                FileUtils.deleteDirectory(datapackPath.resolve("data").toFile());
            } catch (IOException e) {
                Logging.logStackTrace(e);
            }
        }
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        minecraftServer = server;

        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
            if (RugSettings.easyHarvesting.equals("off") || world.isClient() || hand != Hand.MAIN_HAND) {
                return ActionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            ItemStack tool = player != null ? player.getStackInHand(hand) : ItemStack.EMPTY;
            if (RugSettings.easyHarvesting.equals("require_hoe") && !(tool.getItem() instanceof HoeItem)) {
                return ActionResult.PASS;
            }
            boolean allowsHarvestFromTop =
                    RugSettings.easyHarvesting.equals("require_hoe") || hitResult.getSide() != Direction.UP;
            boolean allowsHarvestFromBottom =
                    RugSettings.easyHarvesting.equals("require_hoe") || hitResult.getSide() != Direction.DOWN;

            if (isMature(state)) {
                List<ItemStack> droppedItems =
                        Block.getDroppedStacks(state, (ServerWorld) world, pos, null, player, tool);
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

                world.setBlockState(
                        pos, removedSeed ? state.with(getAgeProperty(block), 0) : Blocks.AIR.getDefaultState());
            } else if (List.of(Blocks.KELP, Blocks.KELP_PLANT).contains(state.getBlock()) && allowsHarvestFromTop) {
                if (!harvestStemPlant(pos, world, Blocks.KELP_PLANT, Blocks.KELP, Direction.UP))
                    return ActionResult.PASS;
            } else if (List.of(Blocks.TWISTING_VINES, Blocks.TWISTING_VINES_PLANT)
                            .contains(state.getBlock())
                    && allowsHarvestFromTop) {
                if (!harvestStemPlant(pos, world, Blocks.TWISTING_VINES_PLANT, Blocks.TWISTING_VINES, Direction.UP))
                    return ActionResult.PASS;
            } else if (List.of(Blocks.WEEPING_VINES, Blocks.WEEPING_VINES_PLANT).contains(state.getBlock())
                    && allowsHarvestFromBottom) {
                if (!harvestStemPlant(pos, world, Blocks.WEEPING_VINES_PLANT, Blocks.WEEPING_VINES, Direction.DOWN))
                    return ActionResult.PASS;
            } else if (List.of(Blocks.CAVE_VINES, Blocks.CAVE_VINES_PLANT).contains(state.getBlock())
                    && allowsHarvestFromBottom) {
                if (!harvestStemPlant(pos, world, Blocks.CAVE_VINES_PLANT, Blocks.CAVE_VINES, Direction.DOWN))
                    return ActionResult.PASS;
            } else if (List.of(Blocks.SUGAR_CANE, Blocks.CACTUS, Blocks.BAMBOO).contains(state.getBlock())
                    && allowsHarvestFromTop) {
                if (!harvestStemPlant(pos, world, state.getBlock(), null, Direction.UP)) return ActionResult.PASS;
            } else {
                return ActionResult.PASS;
            }

            if (RugSettings.easyHarvesting.equals("require_hoe") && player != null) {
                tool.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }
            return ActionResult.SUCCESS;
        }));
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean harvestStemPlant(
            BlockPos pos, World world, Block stem, @Nullable Block end, Direction growDirection) {
        int count = 1;
        BlockPos root = pos.offset(growDirection.getOpposite());
        while (world.getBlockState(root).isOf(stem)) {
            count++;
            root = root.offset(growDirection.getOpposite());
        }

        if (count == 1
                && !world.getBlockState(pos.offset(growDirection)).isOf(stem)
                && !world.getBlockState(pos.offset(growDirection)).isOf(end)) {
            return false;
        }
        world.breakBlock(root.offset(growDirection, 2), true);
        return true;
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        // register observers for all crafting rules
        for (Field f : RugSettings.class.getDeclaredFields()) {
            CraftingRule craftingRule = f.getAnnotation(CraftingRule.class);
            if (craftingRule == null) continue;
            registerCraftingRule(
                    craftingRule.name().isEmpty() ? f.getName() : craftingRule.name(),
                    craftingRule.recipes(),
                    craftingRule.recipeNamespace());
        }
    }

    public static void initializeRugData(Path datapacksPath) {
        // delete old `Rug_flexibleData` directory if present
        Path legacyPath = datapacksPath.resolve("Rug_flexibleData");
        if (Files.isDirectory(legacyPath)) {
            try {
                FileUtils.deleteDirectory(legacyPath.toFile());
            } catch (IOException e) {
                Logging.logStackTrace(e);
            }
        }

        // our datapack is called `RugData`
        datapackPath = datapacksPath.resolve("RugData");

        // setup basic datapack structure
        try {
            Files.createDirectories(datapackPath.resolve("data/rug/recipes"));
            Files.createDirectories(datapackPath.resolve("data/rug/advancements"));
            Files.createDirectories(datapackPath.resolve("data/minecraft/recipes"));
            copyFile("assets/rug/RugDataStorage/pack.mcmeta", datapackPath.resolve("pack.mcmeta"));
        } catch (IOException e) {
            Logging.logStackTrace(e);
        }
        // add root advancement
        copyFile(
                "assets/rug/RugDataStorage/rug/advancements/root.json",
                datapackPath.resolve("data/rug/advancements/root.json"));

        // set values of all crafting rules
        for (Field f : RugSettings.class.getDeclaredFields()) {
            CraftingRule craftingRule = f.getAnnotation(CraftingRule.class);
            if (craftingRule == null) continue;
            String ruleName = craftingRule.name().isEmpty() ? f.getName() : craftingRule.name();
            updateCraftingRule(
                    settingsManager.getCarpetRule(ruleName),
                    craftingRule.recipes(),
                    craftingRule.recipeNamespace(),
                    ruleName);
        }
    }

    private static void registerCraftingRule(String ruleName, String[] recipes, String recipeNamespace) {
        updateCraftingRule(settingsManager.getCarpetRule(ruleName), recipes, recipeNamespace, ruleName);

        SettingsManager.RuleObserver observer = (source, rule, s) -> {
            if (rule.name().equals(ruleName)) {
                updateCraftingRule(rule, recipes, recipeNamespace, ruleName);
                reload();
            }
        };
        settingsManager.registerRuleObserver(observer);
        CarpetServer.settingsManager.registerRuleObserver(observer);
    }

    private static void updateCraftingRule(
            CarpetRule<?> rule, String[] recipes, String recipeNamespace, String ruleName) {
        ruleName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, ruleName);

        if (rule.type() == String.class) {
            String value = RuleHelper.toRuleString(rule.value());

            List<String> installedRecipes = Lists.newArrayList();
            try {
                Stream<Path> fileStream = Files.list(
                        datapackPath.resolve("data").resolve(recipeNamespace).resolve("recipes"));
                fileStream.forEach((path -> {
                    for (String recipeName : recipes) {
                        String fileName = path.getFileName().toString();
                        if (fileName.startsWith(recipeName)) {
                            installedRecipes.add(fileName);
                        }
                    }
                }));
                fileStream.close();
            } catch (IOException e) {
                Logging.logStackTrace(e);
            }

            deleteRecipes(installedRecipes.toArray(new String[0]), recipeNamespace, ruleName, false);

            if (recipeNamespace.equals("rug")) {
                List<String> installedAdvancements = Lists.newArrayList();
                try {
                    Stream<Path> fileStream = Files.list(datapackPath.resolve("data/rug/advancements"));
                    String finalRuleName = ruleName;
                    fileStream.forEach((path -> {
                        String fileName = path.getFileName().toString().replace(".json", "");
                        if (fileName.startsWith(finalRuleName)) {
                            installedAdvancements.add(fileName);
                        }
                    }));
                    fileStream.close();
                } catch (IOException e) {
                    Logging.logStackTrace(e);
                }
                for (String advancement : installedAdvancements.toArray(new String[0])) {
                    removeAdvancement(advancement);
                }
            }

            if (!value.equals("off")) {
                List<String> tempRecipes = Lists.newArrayList();
                for (String recipeName : recipes) {
                    tempRecipes.add(recipeName + "_" + value + ".json");
                }

                copyRecipes(tempRecipes.toArray(new String[0]), recipeNamespace, ruleName + "_" + value);
            }
        } else if (rule.type() == Integer.class && (Integer) rule.value() > 0) {
            copyRecipes(recipes, recipeNamespace, ruleName);

            int value = (Integer) rule.value();
            for (String recipeName : recipes) {
                File file = datapackPath
                        .resolve("data")
                        .resolve(recipeNamespace)
                        .resolve("recipes")
                        .resolve(recipeName)
                        .toFile();
                JsonObject jsonObject = readJson(file);
                assert jsonObject != null;
                jsonObject.getAsJsonObject("result").addProperty("count", value);
                writeJson(jsonObject, file);
            }
        } else if (rule.type() == Boolean.class && RuleHelper.getBooleanValue(rule)) {
            copyRecipes(recipes, recipeNamespace, ruleName);
        } else {
            deleteRecipes(recipes, recipeNamespace, ruleName, true);
        }
    }

    private static void copyRecipes(String[] recipes, String recipeNamespace, String ruleName) {
        for (String recipeName : recipes) {
            copyFile(
                    "assets/rug/RugDataStorage/" + recipeNamespace + "/recipes/" + recipeName,
                    datapackPath
                            .resolve("data")
                            .resolve(recipeNamespace)
                            .resolve("recipes")
                            .resolve(recipeName));
        }
        if (recipeNamespace.equals("rug")) {
            writeAdvancement(ruleName, recipes);
        }
    }

    private static void deleteRecipes(
            String[] recipes, String recipeNamespace, String ruleName, boolean removeAdvancement) {
        for (String recipeName : recipes) {
            try {
                Files.deleteIfExists(datapackPath
                        .resolve("data")
                        .resolve(recipeNamespace)
                        .resolve("recipes")
                        .resolve(recipeName));
            } catch (IOException e) {
                Logging.logStackTrace(e);
            }
        }
        if (removeAdvancement && recipeNamespace.equals("rug")) {
            removeAdvancement(ruleName);
        }
    }

    private static void writeAdvancement(String ruleName, String[] recipes) {
        copyFile(
                "assets/rug/RugDataStorage/rug/advancements/recipe_rule.json",
                datapackPath.resolve("data/rug/advancements/" + ruleName + ".json"));

        JsonObject advancementJson = readJson(datapackPath
                .resolve("data/rug/advancements/" + ruleName + ".json")
                .toFile());
        assert advancementJson != null;
        JsonArray recipeRewards = advancementJson.getAsJsonObject("rewards").getAsJsonArray("recipes");

        for (String recipeName : recipes) {
            recipeRewards.add("rug:" + recipeName.replace(".json", ""));
        }
        writeJson(
                advancementJson,
                datapackPath
                        .resolve("data/rug/advancements/" + ruleName + ".json")
                        .toFile());
    }

    private static void removeAdvancement(String ruleName) {
        try {
            Files.deleteIfExists(datapackPath.resolve("data/rug/advancements/" + ruleName + ".json"));
        } catch (IOException e) {
            Logging.logStackTrace(e);
        }
    }

    private static JsonObject readJson(File file) {
        try {
            FileReader reader = new FileReader(file);
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            Logging.logStackTrace(e);
        }
        return null;
    }

    private static void writeJson(JsonObject jsonObject, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject));
            writer.close();
        } catch (IOException e) {
            Logging.logStackTrace(e);
        }
    }

    private static void reload() {
        ResourcePackManager resourcePackManager = minecraftServer.getDataPackManager();
        resourcePackManager.scanPacks();
        Collection<String> collection = Lists.newArrayList(resourcePackManager.getEnabledNames());
        collection.add("RugData");

        ReloadCommand.tryReloadDataPacks(collection, minecraftServer.getCommandSource());
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
        // UPDATE: check for new classes overriding CropBlock.getAgeProperty()
        if (block instanceof BeetrootsBlock) {
            return BeetrootsBlock.AGE;
        } else if (block instanceof TorchflowerBlock) {
            return TorchflowerBlock.AGE;
        } else if (block instanceof CropBlock) {
            return CropBlock.AGE;
        } else if (block instanceof NetherWartBlock) {
            return NetherWartBlock.AGE;
        } else if (block instanceof CocoaBlock) {
            return CocoaBlock.AGE;
        }
        return null;
    }

    private static void copyFile(String resourcePath, Path targetPath) {
        InputStream source = Module.class.getClassLoader().getResourceAsStream(resourcePath);

        try {
            assert source != null;
            Files.copy(source, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Logging.logStackTrace(e);
        } catch (NullPointerException e) {
            LOGGER.error("Resource '" + resourcePath + "' is null:");
            Logging.logStackTrace(e);
        }
    }

    public static void savePlayerData(ServerPlayerEntity player) {
        File playerDataDir =
                minecraftServer.getSavePath(WorldSavePath.PLAYERDATA).toFile();
        try {
            NbtCompound compoundTag = player.writeNbt(new NbtCompound());
            File file = File.createTempFile(player.getUuidAsString() + "-", ".dat", playerDataDir);
            //#if MC >= 12004
            NbtIo.writeCompressed(compoundTag, file.toPath());
            //#else
            //$$ NbtIo.writeCompressed(compoundTag, file);
            //#endif
            File file2 = new File(playerDataDir, player.getUuidAsString() + ".dat");
            File file3 = new File(playerDataDir, player.getUuidAsString() + ".dat_old");
            //#if MC >= 12004
            Util.backupAndReplace(file2.toPath(), file.toPath(), file3.toPath());
            //#else
            //$$ Util.backupAndReplace(file2, file, file3);
            //#endif
        } catch (Exception ignored) {
            LOGGER.warn("Failed to save player data for " + player.getName().getString());
        }
    }
}
