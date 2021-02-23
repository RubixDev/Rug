package com.rubixdev.rug;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class RugServer implements CarpetExtension {
    @Override
    public String version()
    {
        return "rug";
    }

    public static void noop() {}

    static
    {
        CarpetServer.manageExtension(new RugServer());
        // temporary until CM proper runs tiny bit later
        //CarpetServer.settingsManager.parseSettingsClass(RugSettings.class);
    }

    @Override
    public void onGameStarted()
    {
        // let's /carpet handle our few simple settings
        CarpetServer.settingsManager.parseSettingsClass(RugSettings.class);

        // set-up a snooper to observe how rules are changing in carpet
        CarpetServer.settingsManager.addRuleObserver( (serverCommandSource, currentRuleState, originalUserTest) ->
        {
            // here we will be snooping for command changes
        });
    }

    @Override
    public void onServerLoaded(MinecraftServer server)
    {
        // reloading of /carpet settings is handled by carpet
        // reloading of own settings is handled as an extension, since we claim own settings manager
        // in case something else falls into

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
                for(ItemStack itemStack : droppedItems) {
                    if (!removedSeed) {
                        Item item = itemStack.getItem();
                        if (item instanceof BlockItem && ((BlockItem) item).getBlock() == block) {
                            itemStack.decrement(1);;
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
    public void onTick(MinecraftServer server)
    {
        // maybe, maybe
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        // here goes extra stuff
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player)
    {
        // will need that for client features
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayerEntity player)
    {
        // will need that for client features
    }

    //@Override
    //public Map<String, String> canHasTranslations(String lang)
    //{
    //    return CarpetExtraTranslations.getTranslationFromResourcePath(lang);
    //}

    private static boolean isMature(BlockState state) {
        Block block = state.getBlock();
        if (block instanceof CropBlock) {
            return ((CropBlock) block).isMature(state);
        }
        else if (block instanceof NetherWartBlock) {
            return state.get(NetherWartBlock.AGE) == 3;
        }
        else if (block instanceof CocoaBlock) {
            return state.get(CocoaBlock.AGE) == 2;
        }
        return false;
    }

    private static IntProperty getAgeProperty(Block block) {
        if (block instanceof CropBlock) {
            return ((CropBlock) block).getAgeProperty();
        }
        else if (block instanceof NetherWartBlock) {
            return NetherWartBlock.AGE;
        }
        else if (block instanceof CocoaBlock) {
            return CocoaBlock.AGE;
        }
        return null;
    }
}
