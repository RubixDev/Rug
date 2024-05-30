package de.rubixdev.rug.util;

import net.minecraft.block.WoodType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;

import java.util.Map;

/**
 * Contains maps from DyeColor or WoodType to various items of the matching type.
 */
public class ItemMaps {
    static final Map<DyeColor, Item> DYES = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_DYE),
            Map.entry(DyeColor.BLUE, Items.BLUE_DYE),
            Map.entry(DyeColor.BROWN, Items.BROWN_DYE),
            Map.entry(DyeColor.CYAN, Items.CYAN_DYE),
            Map.entry(DyeColor.GRAY, Items.GRAY_DYE),
            Map.entry(DyeColor.GREEN, Items.GREEN_DYE),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_DYE),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_DYE),
            Map.entry(DyeColor.LIME, Items.LIME_DYE),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_DYE),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_DYE),
            Map.entry(DyeColor.PINK, Items.PINK_DYE),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_DYE),
            Map.entry(DyeColor.RED, Items.RED_DYE),
            Map.entry(DyeColor.WHITE, Items.WHITE_DYE),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_DYE)
    );

    static final Map<DyeColor, Item> BEDS = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_BED),
            Map.entry(DyeColor.BLUE, Items.BLUE_BED),
            Map.entry(DyeColor.BROWN, Items.BROWN_BED),
            Map.entry(DyeColor.CYAN, Items.CYAN_BED),
            Map.entry(DyeColor.GRAY, Items.GRAY_BED),
            Map.entry(DyeColor.GREEN, Items.GREEN_BED),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_BED),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_BED),
            Map.entry(DyeColor.LIME, Items.LIME_BED),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_BED),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_BED),
            Map.entry(DyeColor.PINK, Items.PINK_BED),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_BED),
            Map.entry(DyeColor.RED, Items.RED_BED),
            Map.entry(DyeColor.WHITE, Items.WHITE_BED),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_BED)
    );

    static final Map<DyeColor, Item> CONCRETE_POWDERS = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_CONCRETE_POWDER),
            Map.entry(DyeColor.BLUE, Items.BLUE_CONCRETE_POWDER),
            Map.entry(DyeColor.BROWN, Items.BROWN_CONCRETE_POWDER),
            Map.entry(DyeColor.CYAN, Items.CYAN_CONCRETE_POWDER),
            Map.entry(DyeColor.GRAY, Items.GRAY_CONCRETE_POWDER),
            Map.entry(DyeColor.GREEN, Items.GREEN_CONCRETE_POWDER),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_CONCRETE_POWDER),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_CONCRETE_POWDER),
            Map.entry(DyeColor.LIME, Items.LIME_CONCRETE_POWDER),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_CONCRETE_POWDER),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_CONCRETE_POWDER),
            Map.entry(DyeColor.PINK, Items.PINK_CONCRETE_POWDER),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_CONCRETE_POWDER),
            Map.entry(DyeColor.RED, Items.RED_CONCRETE_POWDER),
            Map.entry(DyeColor.WHITE, Items.WHITE_CONCRETE_POWDER),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_CONCRETE_POWDER)
    );

    static final Map<DyeColor, Item> GLAZED_TERRACOTTAS = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.BLUE, Items.BLUE_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.BROWN, Items.BROWN_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.CYAN, Items.CYAN_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.GRAY, Items.GRAY_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.GREEN, Items.GREEN_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.LIME, Items.LIME_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.PINK, Items.PINK_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.RED, Items.RED_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.WHITE, Items.WHITE_GLAZED_TERRACOTTA),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_GLAZED_TERRACOTTA)
    );

    static final Map<DyeColor, Item> STAINED_GLASSES = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_STAINED_GLASS),
            Map.entry(DyeColor.BLUE, Items.BLUE_STAINED_GLASS),
            Map.entry(DyeColor.BROWN, Items.BROWN_STAINED_GLASS),
            Map.entry(DyeColor.CYAN, Items.CYAN_STAINED_GLASS),
            Map.entry(DyeColor.GRAY, Items.GRAY_STAINED_GLASS),
            Map.entry(DyeColor.GREEN, Items.GREEN_STAINED_GLASS),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_STAINED_GLASS),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_STAINED_GLASS),
            Map.entry(DyeColor.LIME, Items.LIME_STAINED_GLASS),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_STAINED_GLASS),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_STAINED_GLASS),
            Map.entry(DyeColor.PINK, Items.PINK_STAINED_GLASS),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_STAINED_GLASS),
            Map.entry(DyeColor.RED, Items.RED_STAINED_GLASS),
            Map.entry(DyeColor.WHITE, Items.WHITE_STAINED_GLASS),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_STAINED_GLASS)
    );

    static final Map<DyeColor, Item> STAINED_GLASS_PANES = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_STAINED_GLASS_PANE),
            Map.entry(DyeColor.BLUE, Items.BLUE_STAINED_GLASS_PANE),
            Map.entry(DyeColor.BROWN, Items.BROWN_STAINED_GLASS_PANE),
            Map.entry(DyeColor.CYAN, Items.CYAN_STAINED_GLASS_PANE),
            Map.entry(DyeColor.GRAY, Items.GRAY_STAINED_GLASS_PANE),
            Map.entry(DyeColor.GREEN, Items.GREEN_STAINED_GLASS_PANE),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_STAINED_GLASS_PANE),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_STAINED_GLASS_PANE),
            Map.entry(DyeColor.LIME, Items.LIME_STAINED_GLASS_PANE),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_STAINED_GLASS_PANE),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_STAINED_GLASS_PANE),
            Map.entry(DyeColor.PINK, Items.PINK_STAINED_GLASS_PANE),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_STAINED_GLASS_PANE),
            Map.entry(DyeColor.RED, Items.RED_STAINED_GLASS_PANE),
            Map.entry(DyeColor.WHITE, Items.WHITE_STAINED_GLASS_PANE),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_STAINED_GLASS_PANE)
    );

    static final Map<DyeColor, Item> TERRACOTTAS = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_TERRACOTTA),
            Map.entry(DyeColor.BLUE, Items.BLUE_TERRACOTTA),
            Map.entry(DyeColor.BROWN, Items.BROWN_TERRACOTTA),
            Map.entry(DyeColor.CYAN, Items.CYAN_TERRACOTTA),
            Map.entry(DyeColor.GRAY, Items.GRAY_TERRACOTTA),
            Map.entry(DyeColor.GREEN, Items.GREEN_TERRACOTTA),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_TERRACOTTA),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_TERRACOTTA),
            Map.entry(DyeColor.LIME, Items.LIME_TERRACOTTA),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_TERRACOTTA),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_TERRACOTTA),
            Map.entry(DyeColor.PINK, Items.PINK_TERRACOTTA),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_TERRACOTTA),
            Map.entry(DyeColor.RED, Items.RED_TERRACOTTA),
            Map.entry(DyeColor.WHITE, Items.WHITE_TERRACOTTA),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_TERRACOTTA)
    );

    static final Map<DyeColor, Item> WOOLS = Map.ofEntries(
            Map.entry(DyeColor.BLACK, Items.BLACK_WOOL),
            Map.entry(DyeColor.BLUE, Items.BLUE_WOOL),
            Map.entry(DyeColor.BROWN, Items.BROWN_WOOL),
            Map.entry(DyeColor.CYAN, Items.CYAN_WOOL),
            Map.entry(DyeColor.GRAY, Items.GRAY_WOOL),
            Map.entry(DyeColor.GREEN, Items.GREEN_WOOL),
            Map.entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_WOOL),
            Map.entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_WOOL),
            Map.entry(DyeColor.LIME, Items.LIME_WOOL),
            Map.entry(DyeColor.MAGENTA, Items.MAGENTA_WOOL),
            Map.entry(DyeColor.ORANGE, Items.ORANGE_WOOL),
            Map.entry(DyeColor.PINK, Items.PINK_WOOL),
            Map.entry(DyeColor.PURPLE, Items.PURPLE_WOOL),
            Map.entry(DyeColor.RED, Items.RED_WOOL),
            Map.entry(DyeColor.WHITE, Items.WHITE_WOOL),
            Map.entry(DyeColor.YELLOW, Items.YELLOW_WOOL)
    );

    static final Map<WoodType, Item> LOGS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.ACACIA_LOG),
//            Map.entry(WoodType.BAMBOO, Items.),
            Map.entry(WoodType.BIRCH, Items.BIRCH_LOG),
            Map.entry(WoodType.CHERRY, Items.CHERRY_LOG),
            Map.entry(WoodType.CRIMSON, Items.CRIMSON_STEM),
            Map.entry(WoodType.DARK_OAK, Items.DARK_OAK_LOG),
            Map.entry(WoodType.JUNGLE, Items.JUNGLE_LOG),
            Map.entry(WoodType.MANGROVE, Items.MANGROVE_LOG),
            Map.entry(WoodType.OAK, Items.OAK_LOG),
            Map.entry(WoodType.SPRUCE, Items.SPRUCE_LOG),
            Map.entry(WoodType.WARPED, Items.WARPED_STEM)
    );

    static final Map<WoodType, Item> WOODS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.ACACIA_WOOD),
//            Map.entry(WoodType.BAMBOO, Items.),
            Map.entry(WoodType.BIRCH, Items.BIRCH_WOOD),
            Map.entry(WoodType.CHERRY, Items.CHERRY_WOOD),
            Map.entry(WoodType.CRIMSON, Items.CRIMSON_HYPHAE),
            Map.entry(WoodType.DARK_OAK, Items.DARK_OAK_WOOD),
            Map.entry(WoodType.JUNGLE, Items.JUNGLE_WOOD),
            Map.entry(WoodType.MANGROVE, Items.MANGROVE_WOOD),
            Map.entry(WoodType.OAK, Items.OAK_WOOD),
            Map.entry(WoodType.SPRUCE, Items.SPRUCE_WOOD),
            Map.entry(WoodType.WARPED, Items.WARPED_HYPHAE)
    );

    static final Map<WoodType, Item> STRIPPED_LOGS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.STRIPPED_ACACIA_LOG),
//            Map.entry(WoodType.BAMBOO, Items.),
            Map.entry(WoodType.BIRCH, Items.STRIPPED_BIRCH_LOG),
            Map.entry(WoodType.CHERRY, Items.STRIPPED_CHERRY_LOG),
            Map.entry(WoodType.CRIMSON, Items.STRIPPED_CRIMSON_STEM),
            Map.entry(WoodType.DARK_OAK, Items.STRIPPED_DARK_OAK_LOG),
            Map.entry(WoodType.JUNGLE, Items.STRIPPED_JUNGLE_LOG),
            Map.entry(WoodType.MANGROVE, Items.STRIPPED_MANGROVE_LOG),
            Map.entry(WoodType.OAK, Items.STRIPPED_OAK_LOG),
            Map.entry(WoodType.SPRUCE, Items.STRIPPED_SPRUCE_LOG),
            Map.entry(WoodType.WARPED, Items.STRIPPED_WARPED_STEM)
    );

    static final Map<WoodType, Item> STRIPPED_WOODS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.STRIPPED_ACACIA_WOOD),
//            Map.entry(WoodType.BAMBOO, Items.),
            Map.entry(WoodType.BIRCH, Items.STRIPPED_BIRCH_WOOD),
            Map.entry(WoodType.CHERRY, Items.STRIPPED_CHERRY_WOOD),
            Map.entry(WoodType.CRIMSON, Items.STRIPPED_CRIMSON_HYPHAE),
            Map.entry(WoodType.DARK_OAK, Items.STRIPPED_DARK_OAK_WOOD),
            Map.entry(WoodType.JUNGLE, Items.STRIPPED_JUNGLE_WOOD),
            Map.entry(WoodType.MANGROVE, Items.STRIPPED_MANGROVE_WOOD),
            Map.entry(WoodType.OAK, Items.STRIPPED_OAK_WOOD),
            Map.entry(WoodType.SPRUCE, Items.STRIPPED_SPRUCE_WOOD),
            Map.entry(WoodType.WARPED, Items.STRIPPED_WARPED_HYPHAE)
    );

    static final Map<WoodType, Item> PLANKS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.ACACIA_PLANKS),
            Map.entry(WoodType.BAMBOO, Items.BAMBOO_PLANKS),
            Map.entry(WoodType.BIRCH, Items.BIRCH_PLANKS),
            Map.entry(WoodType.CHERRY, Items.CHERRY_PLANKS),
            Map.entry(WoodType.CRIMSON, Items.CRIMSON_PLANKS),
            Map.entry(WoodType.DARK_OAK, Items.DARK_OAK_PLANKS),
            Map.entry(WoodType.JUNGLE, Items.JUNGLE_PLANKS),
            Map.entry(WoodType.MANGROVE, Items.MANGROVE_PLANKS),
            Map.entry(WoodType.OAK, Items.OAK_PLANKS),
            Map.entry(WoodType.SPRUCE, Items.SPRUCE_PLANKS),
            Map.entry(WoodType.WARPED, Items.WARPED_PLANKS)
    );

    static final Map<WoodType, Item> FENCES = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.ACACIA_FENCE),
            Map.entry(WoodType.BAMBOO, Items.BAMBOO_FENCE),
            Map.entry(WoodType.BIRCH, Items.BIRCH_FENCE),
            Map.entry(WoodType.CHERRY, Items.CHERRY_FENCE),
            Map.entry(WoodType.CRIMSON, Items.CRIMSON_FENCE),
            Map.entry(WoodType.DARK_OAK, Items.DARK_OAK_FENCE),
            Map.entry(WoodType.JUNGLE, Items.JUNGLE_FENCE),
            Map.entry(WoodType.MANGROVE, Items.MANGROVE_FENCE),
            Map.entry(WoodType.OAK, Items.OAK_FENCE),
            Map.entry(WoodType.SPRUCE, Items.SPRUCE_FENCE),
            Map.entry(WoodType.WARPED, Items.WARPED_FENCE)
    );

    static final Map<WoodType, Item> SLABS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.ACACIA_SLAB),
            Map.entry(WoodType.BAMBOO, Items.BAMBOO_SLAB),
            Map.entry(WoodType.BIRCH, Items.BIRCH_SLAB),
            Map.entry(WoodType.CHERRY, Items.CHERRY_SLAB),
            Map.entry(WoodType.CRIMSON, Items.CRIMSON_SLAB),
            Map.entry(WoodType.DARK_OAK, Items.DARK_OAK_SLAB),
            Map.entry(WoodType.JUNGLE, Items.JUNGLE_SLAB),
            Map.entry(WoodType.MANGROVE, Items.MANGROVE_SLAB),
            Map.entry(WoodType.OAK, Items.OAK_SLAB),
            Map.entry(WoodType.SPRUCE, Items.SPRUCE_SLAB),
            Map.entry(WoodType.WARPED, Items.WARPED_SLAB)
    );

    static final Map<WoodType, Item> STAIRS = Map.ofEntries(
            Map.entry(WoodType.ACACIA, Items.ACACIA_STAIRS),
            Map.entry(WoodType.BAMBOO, Items.BAMBOO_STAIRS),
            Map.entry(WoodType.BIRCH, Items.BIRCH_STAIRS),
            Map.entry(WoodType.CHERRY, Items.CHERRY_STAIRS),
            Map.entry(WoodType.CRIMSON, Items.CRIMSON_STAIRS),
            Map.entry(WoodType.DARK_OAK, Items.DARK_OAK_STAIRS),
            Map.entry(WoodType.JUNGLE, Items.JUNGLE_STAIRS),
            Map.entry(WoodType.MANGROVE, Items.MANGROVE_STAIRS),
            Map.entry(WoodType.OAK, Items.OAK_STAIRS),
            Map.entry(WoodType.SPRUCE, Items.SPRUCE_STAIRS),
            Map.entry(WoodType.WARPED, Items.WARPED_STAIRS)
    );
}
