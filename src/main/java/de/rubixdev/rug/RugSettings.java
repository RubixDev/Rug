package de.rubixdev.rug;

import static carpet.api.settings.RuleCategory.*;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Rule;
import carpet.api.settings.Rule.Condition;
import carpet.api.settings.Validator;
import de.rubixdev.rug.util.CraftingRule;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.command.ServerCommandSource;

@SuppressWarnings("CanBeFinal")
public class RugSettings {
    public static final String RUG = "rug";
    public static final String CRAFTING = "crafting";
    public static final String RENEWABLE = "renewable";

    public static class validatorAnvilledIce extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource serverCommandSource, CarpetRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 32 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 32";
        }
    }

    @Rule(
            options = {"0", "4", "9"},
            categories = {FEATURE, EXPERIMENTAL, RUG},
            strict = false,
            validators = validatorAnvilledIce.class)
    public static int anvilledBlueIce = 0;

    @Rule(
            options = {"0", "4", "9"},
            categories = {FEATURE, EXPERIMENTAL, RUG},
            strict = false,
            validators = validatorAnvilledIce.class)
    public static int anvilledIce = 0;

    @Rule(
            options = {"0", "4", "9"},
            categories = {FEATURE, EXPERIMENTAL, RUG},
            strict = false,
            validators = validatorAnvilledIce.class)
    public static int anvilledPackedIce = 0;

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean zombifiedPiglinsSpawningInPortals = true;

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean oldFishingLoot = false;

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean noCreeperGriefing = false;

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean noEndermanGriefing = false;

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean noGhastGriefing = false;

    @Rule(
            options = {"off", "on_death", "on_killed_by_player"},
            categories = {FEATURE, SURVIVAL, RUG})
    public static String playerHeadDrops = "off";

    @Rule(
            options = {
                "none",
                "dragon_egg",
                "elytra",
                "dragon_head",
                "dragon_egg,elytra",
                "dragon_egg,dragon_head",
                "elytra,dragon_head",
                "all"
            },
            categories = {FEATURE, SURVIVAL, RENEWABLE, RUG})
    public static String dragonDrops = "none";

    public static class validatorStrictShulkerShells extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource serverCommandSource, CarpetRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 4 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 4";
        }
    }

    @Rule(
            categories = {FEATURE, SURVIVAL, RUG},
            options = {"0", "1", "2"},
            strict = false,
            validators = validatorStrictShulkerShells.class)
    public static int strictShulkerShells = 0;

    @Rule(categories = {FEATURE, SURVIVAL, RUG})
    public static boolean silenceMobs = false;

    @Rule(categories = {EXPERIMENTAL, RUG})
    public static boolean zeroTickPlants = false;

    @Rule(categories = {FEATURE, RUG})
    public static boolean concreteConvertOnCauldron = false;

    @Rule(
            options = {"off", "normal", "require_hoe"},
            categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static String easyHarvesting = "off";

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean peacefulHunger = false;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean foodInstantHeal = false;

    @Rule(categories = {FEATURE, RUG})
    public static boolean lilyPadsOnCauldron = false;

    public static class validatorDamage extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource serverCommandSource, CarpetRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 10 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 10";
        }
    }

    @Rule(
            options = {"0", "3", "4", "5"},
            strict = false,
            validators = validatorDamage.class,
            categories = {BUGFIX, SURVIVAL, RUG})
    public static int stonecutterDamage = 0;

    @Rule(
            options = {"0", "2", "3", "5"},
            strict = false,
            validators = validatorDamage.class,
            categories = {FEATURE, SURVIVAL, RUG})
    public static int enderPearlDamage = 5;

    public static class validatorReachDistance extends Validator<Double> {
        @Override
        public Double validate(
                ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 100 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 100";
        }
    }

    public static boolean shouldApplyReachDistance() {
        return !FabricLoader.getInstance().isModLoaded("reach-entity-attributes")
                && !FabricLoader.getInstance().isModLoaded("pehkui")
                && !FabricLoader.getInstance().isModLoaded("carpet-org-addition");
    }

    public static class conditionReachDistance implements Condition {
        @Override
        public boolean shouldRegister() {
            return shouldApplyReachDistance();
        }
    }

    @Rule(
            strict = false,
            validators = validatorReachDistance.class,
            conditions = conditionReachDistance.class,
            options = {"0.0", "4.5", "5.0", "10.0"},
            categories = {EXPERIMENTAL, CREATIVE, CLIENT, RUG})
    public static double reachDistance = 4.5;

    public static class validatorCactusFurnaceXp extends Validator<Double> {
        @Override
        public Double validate(
                ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 1 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 1";
        }
    }

    @Rule(
            options = {"0.1", "0.2", "0.5", "1.0"},
            strict = false,
            validators = validatorCactusFurnaceXp.class,
            categories = {BUGFIX, SURVIVAL, RUG})
    public static double cactusFurnaceXp = 1.0;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchFarmland = false;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchPathBlocks = false;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchSpawners = false;

    @CraftingRule(recipes = "easy_dispenser.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyDispenserCrafting = false;

    @CraftingRule(recipes = "easy_bone_block.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyBoneBlockCrafting = false;

    @CraftingRule(
            recipes = {
                "acacia_wood.json",
                "birch_wood.json",
                "crimson_hyphae.json",
                "dark_oak_wood.json",
                "jungle_wood.json",
                "mangrove_wood.json",
                "oak_wood.json",
                "spruce_wood.json",
                "stripped_acacia_wood.json",
                "stripped_birch_wood.json",
                "stripped_crimson_hyphae.json",
                "stripped_dark_oak_wood.json",
                "stripped_jungle_wood.json",
                "stripped_oak_wood.json",
                "stripped_spruce_wood.json",
                "stripped_warped_hyphae.json",
                "warped_hyphae.json"
            },
            recipeNamespace = "minecraft")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean moreBarkCrafting = false;

    @CraftingRule(recipes = "notch_apple.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RENEWABLE, RUG})
    public static boolean craftableNotchApple = false;

    @CraftingRule(recipes = "unpackable_quartz.json")
    @Rule(
            options = {"0", "4"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableQuartz = 0;

    @CraftingRule(recipes = "unpackable_wool.json")
    @Rule(
            options = {"0", "4"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableWool = 0;

    @CraftingRule(recipes = "unpackable_nether_wart.json")
    @Rule(
            options = {"0", "9"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableNetherWart = 0;

    @CraftingRule(recipes = "easy_repeater.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyRepeaterCrafting = false;

    @CraftingRule(recipes = "craftable_name_tag")
    @Rule(
            options = {"off", "with_iron", "with_string", "with_both"},
            categories = {CRAFTING, SURVIVAL, RUG})
    public static String craftableNameTags = "off";

    @CraftingRule(
            recipes = {
                "easy_chest_minecart.json",
                "easy_furnace_minecart.json",
                "easy_hopper_minecart.json",
                "easy_tnt_minecart.json"
            })
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyMinecartsCrafting = false;

    @CraftingRule(recipes = "easy_chest_crafting.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyChestCrafting = false;

    @CraftingRule(recipes = "easy_stick_crafting.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyStickCrafting = false;

    @CraftingRule(
            recipes = {"bread.json", "paper.json", "shulker_box.json"},
            recipeNamespace = "minecraft")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean shapelessCrafting = false;

    @CraftingRule(
            recipes = {
                "powder_to_glass_smelting_yellow_stained_glass.json",
                "powder_to_glass_smelting_white_stained_glass.json",
                "powder_to_glass_smelting_red_stained_glass.json",
                "powder_to_glass_smelting_purple_stained_glass.json",
                "powder_to_glass_smelting_pink_stained_glass.json",
                "powder_to_glass_smelting_orange_stained_glass.json",
                "powder_to_glass_smelting_magenta_stained_glass.json",
                "powder_to_glass_smelting_lime_stained_glass.json",
                "powder_to_glass_smelting_light_gray_stained_glass.json",
                "powder_to_glass_smelting_light_blue_stained_glass.json",
                "powder_to_glass_smelting_green_stained_glass.json",
                "powder_to_glass_smelting_gray_stained_glass.json",
                "powder_to_glass_smelting_cyan_stained_glass.json",
                "powder_to_glass_smelting_brown_stained_glass.json",
                "powder_to_glass_smelting_blue_stained_glass.json",
                "powder_to_glass_smelting_black_stained_glass.json"
            })
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean powderToGlassSmelting = false;

    @CraftingRule(
            recipes = {
                "universal_dyeing_black_bed.json",
                "universal_dyeing_black_concrete_powder.json",
                "universal_dyeing_black_glazed_terracotta.json",
                "universal_dyeing_black_stained_glass.json",
                "universal_dyeing_black_stained_glass_pane.json",
                "universal_dyeing_black_terracotta.json",
                "universal_dyeing_black_wool.json",
                "universal_dyeing_blue_bed.json",
                "universal_dyeing_blue_concrete_powder.json",
                "universal_dyeing_blue_glazed_terracotta.json",
                "universal_dyeing_blue_stained_glass.json",
                "universal_dyeing_blue_stained_glass_pane.json",
                "universal_dyeing_blue_terracotta.json",
                "universal_dyeing_blue_wool.json",
                "universal_dyeing_brown_bed.json",
                "universal_dyeing_brown_concrete_powder.json",
                "universal_dyeing_brown_glazed_terracotta.json",
                "universal_dyeing_brown_stained_glass.json",
                "universal_dyeing_brown_stained_glass_pane.json",
                "universal_dyeing_brown_terracotta.json",
                "universal_dyeing_brown_wool.json",
                "universal_dyeing_cyan_bed.json",
                "universal_dyeing_cyan_concrete_powder.json",
                "universal_dyeing_cyan_glazed_terracotta.json",
                "universal_dyeing_cyan_stained_glass.json",
                "universal_dyeing_cyan_stained_glass_pane.json",
                "universal_dyeing_cyan_terracotta.json",
                "universal_dyeing_cyan_wool.json",
                "universal_dyeing_glass.json",
                "universal_dyeing_glass_pane.json",
                "universal_dyeing_gray_bed.json",
                "universal_dyeing_gray_concrete_powder.json",
                "universal_dyeing_gray_glazed_terracotta.json",
                "universal_dyeing_gray_stained_glass.json",
                "universal_dyeing_gray_stained_glass_pane.json",
                "universal_dyeing_gray_terracotta.json",
                "universal_dyeing_gray_wool.json",
                "universal_dyeing_green_bed.json",
                "universal_dyeing_green_concrete_powder.json",
                "universal_dyeing_green_glazed_terracotta.json",
                "universal_dyeing_green_stained_glass.json",
                "universal_dyeing_green_stained_glass_pane.json",
                "universal_dyeing_green_terracotta.json",
                "universal_dyeing_green_wool.json",
                "universal_dyeing_light_blue_bed.json",
                "universal_dyeing_light_blue_concrete_powder.json",
                "universal_dyeing_light_blue_glazed_terracotta.json",
                "universal_dyeing_light_blue_stained_glass.json",
                "universal_dyeing_light_blue_stained_glass_pane.json",
                "universal_dyeing_light_blue_terracotta.json",
                "universal_dyeing_light_blue_wool.json",
                "universal_dyeing_light_gray_bed.json",
                "universal_dyeing_light_gray_concrete_powder.json",
                "universal_dyeing_light_gray_glazed_terracotta.json",
                "universal_dyeing_light_gray_stained_glass.json",
                "universal_dyeing_light_gray_stained_glass_pane.json",
                "universal_dyeing_light_gray_terracotta.json",
                "universal_dyeing_light_gray_wool.json",
                "universal_dyeing_lime_bed.json",
                "universal_dyeing_lime_concrete_powder.json",
                "universal_dyeing_lime_glazed_terracotta.json",
                "universal_dyeing_lime_stained_glass.json",
                "universal_dyeing_lime_stained_glass_pane.json",
                "universal_dyeing_lime_terracotta.json",
                "universal_dyeing_lime_wool.json",
                "universal_dyeing_magenta_bed.json",
                "universal_dyeing_magenta_concrete_powder.json",
                "universal_dyeing_magenta_glazed_terracotta.json",
                "universal_dyeing_magenta_stained_glass.json",
                "universal_dyeing_magenta_stained_glass_pane.json",
                "universal_dyeing_magenta_terracotta.json",
                "universal_dyeing_magenta_wool.json",
                "universal_dyeing_orange_bed.json",
                "universal_dyeing_orange_concrete_powder.json",
                "universal_dyeing_orange_glazed_terracotta.json",
                "universal_dyeing_orange_stained_glass.json",
                "universal_dyeing_orange_stained_glass_pane.json",
                "universal_dyeing_orange_terracotta.json",
                "universal_dyeing_orange_wool.json",
                "universal_dyeing_pink_bed.json",
                "universal_dyeing_pink_concrete_powder.json",
                "universal_dyeing_pink_glazed_terracotta.json",
                "universal_dyeing_pink_stained_glass.json",
                "universal_dyeing_pink_stained_glass_pane.json",
                "universal_dyeing_pink_terracotta.json",
                "universal_dyeing_pink_wool.json",
                "universal_dyeing_purple_bed.json",
                "universal_dyeing_purple_concrete_powder.json",
                "universal_dyeing_purple_glazed_terracotta.json",
                "universal_dyeing_purple_stained_glass.json",
                "universal_dyeing_purple_stained_glass_pane.json",
                "universal_dyeing_purple_terracotta.json",
                "universal_dyeing_purple_wool.json",
                "universal_dyeing_red_bed.json",
                "universal_dyeing_red_concrete_powder.json",
                "universal_dyeing_red_glazed_terracotta.json",
                "universal_dyeing_red_stained_glass.json",
                "universal_dyeing_red_stained_glass_pane.json",
                "universal_dyeing_red_terracotta.json",
                "universal_dyeing_red_wool.json",
                "universal_dyeing_terracotta.json",
                "universal_dyeing_white_bed.json",
                "universal_dyeing_white_concrete_powder.json",
                "universal_dyeing_white_glazed_terracotta.json",
                "universal_dyeing_white_stained_glass.json",
                "universal_dyeing_white_stained_glass_pane.json",
                "universal_dyeing_white_terracotta.json",
                "universal_dyeing_white_wool.json",
                "universal_dyeing_yellow_bed.json",
                "universal_dyeing_yellow_concrete_powder.json",
                "universal_dyeing_yellow_glazed_terracotta.json",
                "universal_dyeing_yellow_stained_glass.json",
                "universal_dyeing_yellow_stained_glass_pane.json",
                "universal_dyeing_yellow_terracotta.json",
                "universal_dyeing_yellow_wool.json"
            })
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean universalDyeing = false;

    public static class validatorUnpackables extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 0 && newValue <= 9 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 9";
        }
    }

    @CraftingRule(recipes = {"unpackable_blue_ice.json", "unpackable_packed_ice.json"})
    @Rule(
            options = {"0", "3", "4", "9"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableIce = 0;

    @CraftingRule(recipes = "craftable_cobweb")
    @Rule(
            options = {"off", "cross", "full"},
            categories = {CRAFTING, SURVIVAL, RENEWABLE, RUG})
    public static String craftableCobwebs = "off";

    @CraftingRule(recipes = "easy_trapped_chest_crafting.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyTrappedChestCrafting = false;

    @Rule(categories = {EXPERIMENTAL, BUGFIX, SURVIVAL, RUG})
    public static boolean infinityNeedsArrow = true;

    @Rule(
            options = {"1", "2", "3", "4"},
            categories = {EXPERIMENTAL, FEATURE, RUG})
    public static int longerRepeaters = 1;

    public static class validatorRedstoneLampTurnOffDelay extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 0 && newValue <= 8 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 8";
        }
    }

    @Rule(
            options = {"0", "4", "8"},
            strict = false,
            validators = validatorRedstoneLampTurnOffDelay.class,
            categories = {RUG})
    public static int redstoneLampTurnOffDelay = 4;

    @CraftingRule(
            recipes = {
                "craftable_horse_armor_leather_horse_armor",
                "craftable_horse_armor_iron_horse_armor",
                "craftable_horse_armor_golden_horse_armor",
                "craftable_horse_armor_diamond_horse_armor",
            })
    @Rule(
            options = {"off", "vanilla_style", "with_saddle", "armor_pieces"},
            categories = {CRAFTING, SURVIVAL, RENEWABLE, RUG})
    public static String craftableHorseArmor = "off";

    @CraftingRule(
            recipes = {
                "woodcutting_acacia_fence.json",
                "woodcutting_acacia_slab.json",
                "woodcutting_acacia_stairs.json",
                "woodcutting_birch_fence.json",
                "woodcutting_birch_slab.json",
                "woodcutting_birch_stairs.json",
                "woodcutting_crimson_fence.json",
                "woodcutting_crimson_slab.json",
                "woodcutting_crimson_stairs.json",
                "woodcutting_dark_oak_fence.json",
                "woodcutting_dark_oak_slab.json",
                "woodcutting_dark_oak_stairs.json",
                "woodcutting_jungle_fence.json",
                "woodcutting_jungle_slab.json",
                "woodcutting_jungle_stairs.json",
                "woodcutting_mangrove_fence.json",
                "woodcutting_mangrove_slab.json",
                "woodcutting_mangrove_stairs.json",
                "woodcutting_oak_fence.json",
                "woodcutting_oak_slab.json",
                "woodcutting_oak_stairs.json",
                "woodcutting_spruce_fence.json",
                "woodcutting_spruce_slab.json",
                "woodcutting_spruce_stairs.json",
                "woodcutting_warped_fence.json",
                "woodcutting_warped_slab.json",
                "woodcutting_warped_stairs.json"
            })
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean woodcutting = false;

    @Rule(
            options = {"both", "honey", "slime", "none"},
            categories = {EXPERIMENTAL, CLIENT, FEATURE, RUG})
    public static String honeyCombStickiness = "both";

    @Rule(
            options = {"off", "on_teleport", "on_landing", "both"},
            categories = {EXPERIMENTAL, FEATURE, RENEWABLE, RUG})
    public static String dragonEggConvertsCobbleToEndstone = "off";

    public static class validatorThrownItemWaterDrag extends Validator<Double> {
        @Override
        public Double validate(
                ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0.5 && newValue <= 0.99 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0.5 to 0.99";
        }
    }

    @Rule(
            options = {"0.8", "0.9", "0.99"},
            strict = false,
            validators = validatorThrownItemWaterDrag.class,
            categories = {EXPERIMENTAL, CLIENT, RUG})
    public static double enderPearlWaterDrag = 0.8;

    public static class validatorKelpBlockHardness extends Validator<Double> {
        @Override
        public Double validate(
                ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 0.5 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 0.5";
        }
    }

    @Rule(
            options = {"0.0", "0.25", "0.5"},
            strict = false,
            validators = validatorKelpBlockHardness.class,
            categories = {EXPERIMENTAL, CLIENT, SURVIVAL, RUG})
    public static double kelpBlockHardness = 0.5;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean campSleeping = false;

    @Rule(
            options = {"0.8", "0.9", "0.99"},
            strict = false,
            validators = validatorThrownItemWaterDrag.class,
            categories = {EXPERIMENTAL, CLIENT, RUG})
    public static double snowballWaterDrag = 0.8;

    @Rule(
            options = {"0.8", "0.9", "0.99"},
            strict = false,
            validators = validatorThrownItemWaterDrag.class,
            categories = {EXPERIMENTAL, CLIENT, RUG})
    public static double eggWaterDrag = 0.8;

    public static class validatorDragonXpDrop extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 0 && newValue <= 12000 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 12000";
        }
    }

    @Rule(
            options = {"500", "1200", "12000"},
            strict = false,
            validators = validatorDragonXpDrop.class,
            categories = {EXPERIMENTAL, SURVIVAL, RUG})
    public static int dragonXpDrop = 500;

    @Rule(
            options = {"off", "more", "all"},
            categories = {EXPERIMENTAL, FEATURE, RUG})
    public static String moreFortressSpawningBlocks = "off";

    @CraftingRule(recipes = "easy_blue_ice_crafting.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyBlueIceCrafting = false;

    public static class validatorSlimeChunkPercentage extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return (newValue >= 0 && newValue <= 100) && newValue % 10 == 0 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 100 that is a multiple of 10";
        }
    }

    @Rule(
            options = {"0", "10", "50", "100"},
            strict = false,
            validators = validatorSlimeChunkPercentage.class,
            categories = {EXPERIMENTAL, RUG})
    public static int slimeChunkPercentage = 10;

    public static class validatorMaxBannerLayers extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 3 && newValue <= 16 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 3 to 16";
        }
    }

    @Rule(
            options = {"3", "6", "10", "12"},
            strict = false,
            validators = validatorMaxBannerLayers.class,
            categories = {EXPERIMENTAL, SURVIVAL, CLIENT, CRAFTING, RUG})
    public static int maxBannerLayers = 6;

    @Rule(categories = {COMMAND, RUG})
    public static String commandSlimeChunk = "ops";

    @Rule(categories = {EXPERIMENTAL, RUG})
    public static boolean tallPlantNoUpdate = false;

    @Rule(categories = {COMMAND, RUG})
    public static String commandFrame = "ops";

    @Rule(categories = {COMMAND, RUG})
    public static String commandSkull = "ops";

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RENEWABLE, RUG})
    public static boolean netherrackGeneration = false;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RENEWABLE, RUG})
    public static boolean basaltToBlackstoneConversion = false;

    @Rule(categories = {FEATURE, SURVIVAL, RENEWABLE, RUG})
    public static boolean basaltToLavaConversion = false;

    @Rule(categories = {COMMAND, RUG})
    public static String commandSudo = "ops";

    @Rule(categories = {RUG})
    public static boolean endCrystalPlacementRestriction = true;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean concreteMixing = false;

    @Rule(categories = {FEATURE, SURVIVAL, RENEWABLE, RUG})
    public static boolean cobbleCrushing = false;

    @Rule(categories = {FEATURE, SURVIVAL, RENEWABLE, RUG})
    public static boolean gravelCrushing = false;

    @Rule(categories = {COMMAND, RUG})
    public static String commandPeek = "ops";

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean villagersAlwaysConvert = false;

    @CraftingRule(recipes = {"blast_furnace_stone.json", "blast_furnace_smooth_stone.json"})
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceStone = false;

    @CraftingRule(recipes = "blast_furnace_glass.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceGlass = false;

    @CraftingRule(recipes = {"blast_furnace_smooth_sandstone.json", "blast_furnace_smooth_red_sandstone.json"})
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceSmoothSandStone = false;

    @CraftingRule(recipes = "blast_furnace_nether_brick.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceNetherBrick = false;

    @CraftingRule(recipes = "blast_furnace_smooth_quartz.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceSmoothQuartz = false;

    @CraftingRule(
            recipes = {
                "blast_furnace_black_glazed_terracotta.json",
                "blast_furnace_blue_glazed_terracotta.json",
                "blast_furnace_brown_glazed_terracotta.json",
                "blast_furnace_cyan_glazed_terracotta.json",
                "blast_furnace_gray_glazed_terracotta.json",
                "blast_furnace_green_glazed_terracotta.json",
                "blast_furnace_light_blue_glazed_terracotta.json",
                "blast_furnace_light_gray_glazed_terracotta.json",
                "blast_furnace_lime_glazed_terracotta.json",
                "blast_furnace_magenta_glazed_terracotta.json",
                "blast_furnace_orange_glazed_terracotta.json",
                "blast_furnace_pink_glazed_terracotta.json",
                "blast_furnace_purple_glazed_terracotta.json",
                "blast_furnace_red_glazed_terracotta.json",
                "blast_furnace_white_glazed_terracotta.json",
                "blast_furnace_yellow_glazed_terracotta.json"
            })
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceGlazedTerracotta = false;

    @CraftingRule(recipes = "smoker_green_dye.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean smokerGreenDye = false;

    @CraftingRule(recipes = "smoker_lime_dye.json")
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean smokerLimeDye = false;

    @Rule(categories = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchBuddingAmethysts = false;

    public static class validatorVillagersDropEmeralds extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource serverCommandSource, CarpetRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 5 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 5";
        }
    }

    @Rule(
            categories = {FEATURE, SURVIVAL, RUG},
            options = {"0", "1", "3"},
            strict = false,
            validators = validatorVillagersDropEmeralds.class)
    public static int villagersDropEmeralds = 0;

    @Rule(categories = {COMMAND, RUG})
    public static String commandMaxEffect = "ops";

    @Rule(
            categories = {FEATURE, SURVIVAL, RUG},
            options = {"0", "2", "4"},
            strict = false,
            validators = validatorDamage.class)
    public static int stalagmiteSteppingDamage = 0;

    @CraftingRule(recipes = "unpackable_amethysts.json")
    @Rule(
            options = {"0", "4"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableAmethysts = 0;

    @Rule(categories = {COMMAND, RUG})
    public static String commandMods = "true";

    @Rule(categories = {SURVIVAL, RUG})
    public static boolean tameCatsWithCookedFish = false;

    @Rule(categories = {BUGFIX, RUG})
    public static boolean itemFramesActivatePressurePlates = true;

    public static class validatorEnderEyeDropChance extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return (newValue >= 0 && newValue <= 100) ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 100";
        }
    }

    @Rule(
            categories = {SURVIVAL, RUG},
            strict = false,
            validators = validatorEnderEyeDropChance.class,
            options = {"0", "50", "80", "100"})
    public static int enderEyeDropChance = 80;

    @CraftingRule(recipes = "craftable_tuff.json")
    @Rule(categories = {SURVIVAL, CRAFTING, RENEWABLE, RUG})
    public static boolean craftableTuff = false;

    @CraftingRule(
            recipes = {"dispenser.json", "dropper.json", "lever.json", "observer.json", "piston.json"},
            recipeNamespace = "minecraft")
    @Rule(categories = {SURVIVAL, CRAFTING, BUGFIX, RUG})
    public static boolean missingCobbleRecipes = false;

    @Rule(categories = {FEATURE, SURVIVAL, RUG})
    public static boolean splashOxidize = false;

    @Rule(categories = {SURVIVAL, TNT, RUG})
    public static boolean sculkBlocksAlwaysDropXp = false;

    @Rule(categories = {CREATIVE, SURVIVAL, RUG})
    public static boolean snowMelting = true;

    @Rule(categories = {CREATIVE, RUG})
    public static boolean waterInNether = false;

    public static class validatorMinecartMaxSpeedMultiplier extends Validator<Double> {
        @Override
        public Double validate(
                ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0.25 && newValue <= 4 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value between 0.25 and 4.0";
        }
    }

    @Rule(
            categories = {EXPERIMENTAL, RUG},
            strict = false,
            options = {"0.5", "1.0", "2.0", "4.0"},
            validators = validatorMinecartMaxSpeedMultiplier.class)
    public static double minecartMaxSpeedMultiplier = 1.0;

    //#if MC >= 12004
    public static class validatorBulbDelay extends Validator<Integer> {
        @Override
        public Integer validate(
                ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 0 && newValue <= 10 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 10";
        }
    }

    @Rule(
            categories = {RUG},
            strict = false,
            options = {"0", "1", "2"},
            validators = validatorBulbDelay.class)
    public static int bulbDelay = 0;
    //#endif
}

// BUGFIX
// COMMAND
// EXPERIMENTAL
// FEATURE
// CREATIVE
// CLIENT
// DISPENSER
// OPTIMIZATION
// SCARPET
// SURVIVAL
// TNT
// CRAFTING
// RENEWABLE
