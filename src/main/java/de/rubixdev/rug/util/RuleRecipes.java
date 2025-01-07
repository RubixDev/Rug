package de.rubixdev.rug.util;

import de.rubixdev.rug.RugSettings;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.block.WoodType;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.tuple.Triple;
import pers.solid.brrp.v1.api.RuntimeResourcePack;

import java.util.Map;

import static de.rubixdev.rug.util.ItemMaps.*;
import static de.rubixdev.rug.util.Utils.id;

//#if MC >= 12002
import net.minecraft.advancement.Advancement;
//#else
//$$ import java.util.function.Consumer;
//#endif

//#if MC >= 12004
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.recipe.Recipe;
import org.jetbrains.annotations.Nullable;
//#endif

public class RuleRecipes {
    public static void generateRecipes(RuntimeResourcePack pack) {
        //#if MC >= 12002
        RecipeExporter onlyRecipe = new RecipeExporter() {
            @Override
            //#if MC >= 12004
            public void accept(Identifier recipeId, Recipe<?> recipe, @Nullable AdvancementEntry advancement) {
                pack.addRecipe(recipeId, recipe);
            }
            //#else
            //$$ public void accept(RecipeJsonProvider recipeJsonProvider) {
            //$$     pack.addRecipe(recipeJsonProvider.id(), recipeJsonProvider);
            //$$ }
            //#endif

            @Override
            public Advancement.Builder getAdvancementBuilder() {
                return Advancement.Builder.createUntelemetered();
            }
        };
        //#else
        //$$ Consumer<RecipeJsonProvider> onlyRecipe = (provider) -> pack.addRecipe(provider.getRecipeId(), provider);
        //#endif

        if (RugSettings.easyDispenserCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_dispenser"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DISPENSER)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern(" /S")
                            .pattern("/DS")
                            .pattern(" /S")
                            .input('/', Items.STICK)
                            .input('S', Items.STRING)
                            .input('D', Items.DROPPER)
            );
        }

        if (RugSettings.easyBoneBlockCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_bone_block"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BONE_BLOCK, 3)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("###")
                            .pattern("###")
                            .pattern("###")
                            .input('#', Items.BONE)
            );
        }

        if (RugSettings.moreBarkCrafting) {
            WoodType.stream().forEach(woodType -> {
                moreBark(pack, LOGS.get(woodType), WOODS.get(woodType));
                moreBark(pack, STRIPPED_LOGS.get(woodType), STRIPPED_WOODS.get(woodType));
            });
        }

        if (RugSettings.craftableNotchApple) {
            pack.addRecipeAndAdvancement(
                    id("notch_apple"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.ENCHANTED_GOLDEN_APPLE)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("###")
                            .pattern("#A#")
                            .pattern("###")
                            .input('#', Items.GOLD_BLOCK)
                            .input('A', Items.APPLE)
            );
        }

        unpackable(pack, RugSettings.unpackableAmethysts, Items.AMETHYST_BLOCK, Items.AMETHYST_SHARD);
        unpackable(pack, RugSettings.unpackableIce, Items.BLUE_ICE, Items.PACKED_ICE);
        unpackable(pack, RugSettings.unpackableIce, Items.PACKED_ICE, Items.ICE);
        unpackable(pack, RugSettings.unpackableNetherWart, Items.NETHER_WART_BLOCK, Items.NETHER_WART);
        unpackable(pack, RugSettings.unpackableQuartz, Items.QUARTZ_BLOCK, Items.QUARTZ);
        unpackable(pack, RugSettings.unpackableWool, Items.WHITE_WOOL, Items.STRING);

        if (RugSettings.easyRepeaterCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_repeater"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.REPEATER)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("* *")
                            .pattern("/*/")
                            .pattern("###")
                            .input('*', Items.REDSTONE)
                            .input('/', Items.STICK)
                            .input('#', Items.STONE)
            );
        }

        if (!RugSettings.craftableNameTags.equals("off")) {
            ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.NAME_TAG)
                    .criterion("tick", TickCriterion.Conditions.createTick())
                    .input('P', Items.PAPER);
            if (RugSettings.craftableNameTags.equals("with_iron")) {
                builder.pattern("  I");
                builder.pattern(" P ");
                builder.pattern("P  ");
                builder.input('I', Items.IRON_INGOT);
            }
            if (RugSettings.craftableNameTags.equals("with_string")) {
                builder.pattern("  S");
                builder.pattern(" P ");
                builder.pattern("P  ");
                builder.input('S', Items.STRING);
            }
            if (RugSettings.craftableNameTags.equals("with_both")) {
                builder.pattern(" IS");
                builder.pattern(" PI");
                builder.pattern("P  ");
                builder.input('I', Items.IRON_INGOT);
                builder.input('S', Items.STRING);
            }
            pack.addRecipeAndAdvancement(id("craftable_name_tag"), builder);
        }

        if (RugSettings.easyMinecartsCrafting) {
            Map<Item, Item> minecarts = Map.ofEntries(
                    Map.entry(Items.CHEST, Items.CHEST_MINECART),
                    Map.entry(Items.FURNACE, Items.FURNACE_MINECART),
                    Map.entry(Items.HOPPER, Items.HOPPER_MINECART),
                    Map.entry(Items.TNT, Items.TNT_MINECART)
            );
            for (Map.Entry<Item, Item> minecartType : minecarts.entrySet()) {
                pack.addRecipeAndAdvancement(
                        id("easy_" + itemId(minecartType.getValue()).getPath()),
                        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, minecartType.getValue())
                                .criterion("tick", TickCriterion.Conditions.createTick())
                                .group("minecart")
                                .pattern("#O#")
                                .pattern("###")
                                .input('#', Items.IRON_INGOT)
                                .input('O', minecartType.getKey())
                );
            }
        }

        if (RugSettings.easyChestCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_chest"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.CHEST, 4)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("###")
                            .pattern("# #")
                            .pattern("###")
                            .input('#', ItemTags.LOGS)
            );
        }

        if (RugSettings.easyTrappedChestCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_trapped_chest"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.TRAPPED_CHEST, 4)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("###")
                            .pattern("#H#")
                            .pattern("###")
                            .input('#', ItemTags.LOGS)
                            .input('H', Items.TRIPWIRE_HOOK)
            );
        }

        if (RugSettings.easyStickCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_stick"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STICK, 16)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("#")
                            .pattern("#")
                            .input('#', ItemTags.LOGS)
            );
        }

        if (RugSettings.shapelessCrafting) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, Items.BREAD)
                    .setBypassesValidation(true)
                    .input(Items.WHEAT, 3)
                    .offerTo(onlyRecipe);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PAPER)
                    .setBypassesValidation(true)
                    .input(Items.SUGAR_CANE, 3)
                    .offerTo(onlyRecipe);
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.SHULKER_BOX)
                    .setBypassesValidation(true)
                    .input(Items.SHULKER_SHELL, 2)
                    .input(Items.CHEST)
                    .offerTo(onlyRecipe);
        }

        if (RugSettings.powderToGlassSmelting) {
            for (DyeColor color : DyeColor.values()) {
                Item input = CONCRETE_POWDERS.get(color);
                Item output = STAINED_GLASSES.get(color);
                pack.addRecipeAndAdvancement(
                        id("powder_to_glass_smelting_" + itemId(output).getPath()),
                        CookingRecipeJsonBuilder.createSmelting(
                                        Ingredient.ofItems(input),
                                        RecipeCategory.MISC,
                                        output,
                                        .1f,
                                        200
                                )
                                .criterion("tick", TickCriterion.Conditions.createTick())
                                .group("powder_to_glass")
                );
            }
        }

        if (RugSettings.universalDyeing) {
            universalDyeingShapeless(pack, BEDS, "bed");
            universalDyeingShapeless(pack, WOOLS, "wool");
            universalDyeingRing(pack, CONCRETE_POWDERS, "concrete_powder");
            universalDyeingRing(pack, GLAZED_TERRACOTTAS, "glazed_terracotta");
            universalDyeingRing(pack, STAINED_GLASSES, "stained_glass");
            universalDyeingRing(pack, STAINED_GLASS_PANES, "stained_glass_pane");
            universalDyeingRing(pack, TERRACOTTAS, "terracotta");
            universalUnDyeing(pack, STAINED_GLASSES, Items.GLASS);
            universalUnDyeing(pack, STAINED_GLASS_PANES, Items.GLASS_PANE);
            universalUnDyeing(pack, TERRACOTTAS, Items.TERRACOTTA);
        }

        if (!RugSettings.craftableCobwebs.equals("off")) {
            ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.COBWEB)
                    .criterion("tick", TickCriterion.Conditions.createTick())
                    .input('#', Items.STRING);
            if (RugSettings.craftableCobwebs.equals("cross")) {
                builder.pattern("# #");
                builder.pattern(" # ");
                builder.pattern("# #");
            }
            if (RugSettings.craftableCobwebs.equals("full")) {
                builder.pattern("###");
                builder.pattern("###");
                builder.pattern("###");
            }
            pack.addRecipeAndAdvancement(id("craftable_cobweb"), builder);
        }

        if (!RugSettings.craftableHorseArmor.equals("off")) {
            Map<Item, Triple<Item, Item, Item>> materials = Map.ofEntries(
                    Map.entry(Items.DIAMOND_HORSE_ARMOR, Triple.of(Items.DIAMOND, Items.DIAMOND_BOOTS, Items.DIAMOND_HELMET)),
                    Map.entry(Items.GOLDEN_HORSE_ARMOR, Triple.of(Items.GOLD_INGOT, Items.GOLDEN_BOOTS, Items.GOLDEN_HELMET)),
                    Map.entry(Items.IRON_HORSE_ARMOR, Triple.of(Items.IRON_INGOT, Items.IRON_BOOTS, Items.IRON_HELMET)),
                    Map.entry(Items.LEATHER_HORSE_ARMOR, Triple.of(Items.LEATHER, Items.LEATHER_BOOTS, Items.LEATHER_HELMET))
            );
            for (Map.Entry<Item, Triple<Item, Item, Item>> material : materials.entrySet()) {
                ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, material.getKey())
                        .criterion("tick", TickCriterion.Conditions.createTick())
                        .input('#', material.getValue().getLeft());
                if (RugSettings.craftableHorseArmor.equals("vanilla_style")) {
                    builder.pattern("# #");
                    builder.pattern("###");
                    builder.pattern("# #");
                }
                if (RugSettings.craftableHorseArmor.equals("with_saddle")) {
                    builder.pattern("  #");
                    builder.pattern("###");
                    builder.pattern("#S#");
                    builder.input('S', Items.SADDLE);
                }
                if (RugSettings.craftableHorseArmor.equals("armor_pieces")) {
                    builder.pattern("  A");
                    builder.pattern("###");
                    builder.pattern(". .");
                    builder.input('.', material.getValue().getMiddle());
                    builder.input('A', material.getValue().getRight());
                }
                if (RugSettings.craftableHorseArmor.equals("with_wool")) {
                    builder.pattern("  #");
                    builder.pattern("#W#");
                    builder.pattern("###");
                    builder.input('W', ItemTags.WOOL);
                }
                pack.addRecipeAndAdvancement(id("craftable_horse_armor_" + itemId(material.getKey()).getPath()), builder);
            }
        }

        if (RugSettings.woodcutting) {
            WoodType.stream().forEach(woodType -> {
                Item planks = PLANKS.get(woodType);
                Item fence = FENCES.get(woodType);
                Item slab = SLABS.get(woodType);
                Item stairs = STAIRS.get(woodType);
                pack.addRecipeAndAdvancement(
                        id("woodcutting_" + itemId(fence).getPath()),
                        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(planks), RecipeCategory.MISC, fence)
                                .criterion("tick", TickCriterion.Conditions.createTick())
                );
                pack.addRecipeAndAdvancement(
                        id("woodcutting_" + itemId(slab).getPath()),
                        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(planks), RecipeCategory.MISC, slab, 2)
                                .criterion("tick", TickCriterion.Conditions.createTick())
                );
                pack.addRecipeAndAdvancement(
                        id("woodcutting_" + itemId(stairs).getPath()),
                        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(planks), RecipeCategory.MISC, stairs)
                                .criterion("tick", TickCriterion.Conditions.createTick())
                );
            });
        }

        if (RugSettings.easyBlueIceCrafting) {
            pack.addRecipeAndAdvancement(
                    id("easy_blue_ice"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLUE_ICE, 8)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("###")
                            .pattern("#D#")
                            .pattern("###")
                            .input('#', Items.ICE)
                            .input('D', Items.BLUE_DYE)
            );
        }

        blasting(pack, RugSettings.blastFurnaceStone, Items.COBBLESTONE, Items.STONE);
        blasting(pack, RugSettings.blastFurnaceStone, Items.STONE, Items.SMOOTH_STONE);
        blasting(pack, RugSettings.blastFurnaceGlass, Ingredient.ofItems(Items.SAND, Items.RED_SAND), Items.GLASS);
        blasting(pack, RugSettings.blastFurnaceSmoothSandStone, Items.SANDSTONE, Items.SMOOTH_SANDSTONE);
        blasting(pack, RugSettings.blastFurnaceSmoothSandStone, Items.RED_SANDSTONE, Items.SMOOTH_RED_SANDSTONE);
        blasting(pack, RugSettings.blastFurnaceNetherBrick, Items.NETHERRACK, Items.NETHER_BRICK);
        blasting(pack, RugSettings.blastFurnaceSmoothQuartz, Items.QUARTZ_BLOCK, Items.SMOOTH_QUARTZ);
        for (DyeColor color : DyeColor.values()) {
            blasting(pack, RugSettings.blastFurnaceGlazedTerracotta, TERRACOTTAS.get(color), GLAZED_TERRACOTTAS.get(color));
        }

        smoking(pack, RugSettings.smokerGreenDye, Items.CACTUS, Items.GREEN_DYE, 1f);
        smoking(pack, RugSettings.smokerLimeDye, Items.SEA_PICKLE, Items.LIME_DYE, .1f);

        if (RugSettings.craftableTuff) {
            pack.addRecipeAndAdvancement(
                    id("craftable_tuff"),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.TUFF, 2)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .pattern("#C")
                            .pattern("C#")
                            .input('#', Items.ANDESITE)
                            .input('C', Items.COBBLESTONE)
            );
        }

        if (RugSettings.missingCobbleRecipes) {
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DISPENSER)
                    .setBypassesValidation(true)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("#R#")
                    .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                    .input('R', Items.REDSTONE)
                    .input('X', Items.BOW)
                    .offerTo(onlyRecipe);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.DROPPER)
                    .setBypassesValidation(true)
                    .pattern("###")
                    .pattern("# #")
                    .pattern("#R#")
                    .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                    .input('R', Items.REDSTONE)
                    .offerTo(onlyRecipe);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.LEVER)
                    .setBypassesValidation(true)
                    .pattern("X")
                    .pattern("#")
                    .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                    .input('X', Items.STICK)
                    .offerTo(onlyRecipe);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.OBSERVER)
                    .setBypassesValidation(true)
                    .pattern("###")
                    .pattern("RRQ")
                    .pattern("###")
                    .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                    .input('R', Items.REDSTONE)
                    .input('Q', Items.QUARTZ)
                    .offerTo(onlyRecipe);
            ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Items.PISTON)
                    .setBypassesValidation(true)
                    .pattern("TTT")
                    .pattern("#X#")
                    .pattern("#R#")
                    .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                    .input('R', Items.REDSTONE)
                    .input('X', Items.IRON_INGOT)
                    .input('T', ItemTags.PLANKS)
                    .offerTo(onlyRecipe);
        }
    }

    private static Identifier itemId(ItemConvertible item) {
        return CraftingRecipeJsonBuilder.getItemId(item);
    }

    private static void moreBark(RuntimeResourcePack pack, Item log, Item wood) {
        if (log == null || wood == null) return;
        pack.addRecipeAndAdvancement(
                itemId(wood),
                ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, wood, 4)
                        .criterion("tick", TickCriterion.Conditions.createTick())
                        .group("bark")
                        .pattern("##")
                        .pattern("##")
                        .input('#', log)
        );
    }

    private static void unpackable(RuntimeResourcePack pack, int count, Item input, Item output) {
        if (count != 0) {
            pack.addRecipeAndAdvancement(
                    id("unpackable_" + itemId(input).getPath()),
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, count)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .input(input)
            );
        }
    }

    private static void universalDyeingShapeless(RuntimeResourcePack pack, Map<DyeColor, Item> map, String group) {
        for (Map.Entry<DyeColor, Item> entry : map.entrySet()) {
            pack.addRecipeAndAdvancement(
                    id("universal_dyeing_" + itemId(entry.getValue()).getPath()),
                    ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, entry.getValue())
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .group("universal_dyeing_" + group)
                            .input(DYES.get(entry.getKey()))
                            .input(Ingredient.ofItems(map.entrySet().stream().filter(e -> e.getKey() != entry.getKey()).map(Map.Entry::getValue).toArray(Item[]::new)))
            );
        }
    }

    private static void universalDyeingRing(RuntimeResourcePack pack, Map<DyeColor, Item> map, String group) {
        for (Map.Entry<DyeColor, Item> entry : map.entrySet()) {
            pack.addRecipeAndAdvancement(
                    id("universal_dyeing_" + itemId(entry.getValue()).getPath()),
                    ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, entry.getValue(), 8)
                            .criterion("tick", TickCriterion.Conditions.createTick())
                            .group("universal_dyeing_" + group)
                            .pattern("###")
                            .pattern("#O#")
                            .pattern("###")
                            .input('O', DYES.get(entry.getKey()))
                            .input('#', Ingredient.ofItems(map.entrySet().stream().filter(e -> e.getKey() != entry.getKey()).map(Map.Entry::getValue).toArray(Item[]::new)))
            );
        }
    }

    private static void universalUnDyeing(RuntimeResourcePack pack, Map<DyeColor, Item> map, Item output) {
        pack.addRecipeAndAdvancement(
                id("universal_dyeing_" + itemId(output).getPath()),
                ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, output, 8)
                        .criterion("tick", TickCriterion.Conditions.createTick())
                        .pattern("###")
                        .pattern("#O#")
                        .pattern("###")
                        .input('O', Items.WATER_BUCKET)
                        .input('#', Ingredient.ofItems(map.values().toArray(Item[]::new)))
        );
    }

    private static void blasting(RuntimeResourcePack pack, boolean enabled, Item input, Item output) {
        blasting(pack, enabled, Ingredient.ofItems(input), output);
    }

    private static void blasting(RuntimeResourcePack pack, boolean enabled, Ingredient input, Item output) {
        if (!enabled) return;
        pack.addRecipeAndAdvancement(
                id("blast_furnace_" + itemId(output).getPath()),
                CookingRecipeJsonBuilder.createBlasting(
                                input,
                                RecipeCategory.MISC,
                                output,
                                .1f,
                                100
                        )
                        .criterion("tick", TickCriterion.Conditions.createTick())
        );
    }

    private static void smoking(RuntimeResourcePack pack, boolean enabled, Item input, Item output, float exp) {
        if (!enabled) return;
        pack.addRecipeAndAdvancement(
                id("smoker_" + itemId(output).getPath()),
                CookingRecipeJsonBuilder.createSmoking(
                                Ingredient.ofItems(input),
                                RecipeCategory.MISC,
                                output,
                                exp,
                                100
                        )
                        .criterion("tick", TickCriterion.Conditions.createTick())
        );
    }
}
