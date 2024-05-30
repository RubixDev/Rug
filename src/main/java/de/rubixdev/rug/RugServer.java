package de.rubixdev.rug;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mojang.brigadier.CommandDispatcher;
import de.rubixdev.rug.commands.*;
import de.rubixdev.rug.util.CraftingRule;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import de.rubixdev.rug.util.RuleRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.block.*;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

//#if MC >= 12006
import net.minecraft.entity.LivingEntity;
//#endif

public class RugServer implements CarpetExtension, ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("Rug");
    public static final String MOD_ID = "rug";
    public static final String MOD_NAME;
    public static final String MOD_VERSION;
    public static SettingsManager settingsManager;

    private static MinecraftServer minecraftServer;
    private static final RuntimeResourcePack PACK = RuntimeResourcePack.create(new Identifier(MOD_ID, "recipes"));

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

        RRPCallback.AFTER_VANILLA.register(resources -> {
            PACK.clearResources();
            RuleRecipes.generateRecipes(PACK);
            PACK.setDescription(Text.literal("Flexible data pack for Rug to allow toggleable recipes"));
            resources.add(PACK);
        });
    }

    @Override
    public SettingsManager extensionSettingsManager() {
        return settingsManager;
    }

    @Override
    public void onGameStarted() {
        LOGGER.info("Rug Mod v{} loaded!", MOD_VERSION);

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
                tool.damage(
                        1,
                        player,
                        //#if MC >= 12006
                        LivingEntity.getSlotForHand(hand)
                        //#else
                        //$$ p -> p.sendToolBreakStatus(hand)
                        //#endif
                );
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

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        // register observers for all crafting rules
        for (Field f : RugSettings.class.getDeclaredFields()) {
            CraftingRule craftingRule = f.getAnnotation(CraftingRule.class);
            if (craftingRule == null) continue;
            registerCraftingRule(f.getName());
        }
    }

    private static void registerCraftingRule(String ruleName) {
        SettingsManager.RuleObserver observer = (source, rule, s) -> {
            if (rule.name().equals(ruleName)) {
                reload();
            }
        };
        settingsManager.registerRuleObserver(observer);
        CarpetServer.settingsManager.registerRuleObserver(observer);
    }

    private static void reload() {
        ReloadCommand.tryReloadDataPacks(minecraftServer.getDataPackManager().getEnabledIds(), minecraftServer.getCommandSource());
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
            LOGGER.warn("Failed to save player data for {}", player.getName().getString());
        }
    }
}
