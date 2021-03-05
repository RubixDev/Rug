package com.rubixdev.rug;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.*;

public class RugSettings {
    public static final String RUG = "rug";
    public static final String CRAFTING = "crafting";

    public static class validatorAnvilledIce extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 32 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 32";
        }
    }

    @Rule(
            desc = "Custom amount of packed ice crushed by falling anvils make one blue ice.",
            options = {"0", "4", "9"},
            category = {FEATURE, EXPERIMENTAL, RUG},
            strict = false,
            validate = validatorAnvilledIce.class
    )
    public static int anvilledBlueIce = 0;
    // anvilledBlueIceAdditional: From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

    @Rule(
            desc = "Custom amount of frosted ice crushed by falling anvils make one ice. Allows for new type of ice farm.",
            options = {"0", "4", "9"},
            category = {FEATURE, EXPERIMENTAL, RUG},
            strict = false,
            validate = validatorAnvilledIce.class
    )
    public static int anvilledIce = 0;
    // anvilledIceAdditional: From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

    @Rule(
            desc = "Custom amount of ice crushed by falling anvils make one packed ice.",
            options = {"0", "4", "9"},
            category = {FEATURE, EXPERIMENTAL, RUG},
            strict = false,
            validate = validatorAnvilledIce.class
    )
    public static int anvilledPackedIce = 0;
    // anvilledPackedIceAdditional: From [QuickCarpet](https://github.com/DeadlyMC/QuickCarpet114)

    @Rule(
            desc = "Allows Zombified Piglins to spawn inside Nether Portals",
            category = {SURVIVAL, RUG}
    )
    public static boolean zombifiedPiglinsSpawningInPortals = true;

    @Rule(
            desc = "Reverts the fishing loot to how it was before 1.16",
            category = {SURVIVAL, RUG}
    )
    public static boolean oldFishingLoot = false;

    @Rule(
            desc = "Prevents Creepers from destroying blocks",
            category = {SURVIVAL, RUG}
    )
    public static boolean noCreeperGriefing = false;

    @Rule(
            desc = "Prevents Endermen from picking up and placing blocks",
            category = {SURVIVAL, RUG}
    )
    public static boolean noEndermanGriefing = false;

    @Rule(
            desc = "Prevents Ghasts from destroying blocks",
            category = {SURVIVAL, RUG}
    )
    public static boolean noGhastGriefing = false;

    @Rule(
            desc = "Players drop their head when killed by a player",
            category = {FEATURE, SURVIVAL, RUG}
    )
    public static boolean playerHeadDrops = false;
    // playerHeadDropsAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

    @Rule(
            desc = "Ender Dragon drops an Elytra when killed",
            category = {FEATURE, SURVIVAL, RUG}
    )
    public static boolean dragonDropsElytra = false;
    // dragonDropsElytraAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

    public static class validatorStrictShulkerShells extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 4 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 4";
        }
    }

    @Rule(
            desc = "Shulkers always drop a given amount of shulker shells when killed",
            category = {FEATURE, SURVIVAL, RUG},
            options = {"0", "1", "2"},
            strict = false,
            validate = validatorStrictShulkerShells.class
    )
    public static int strictShulkerShells = 0;
    // strictShulkerShellsAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

    @Rule(
            desc = "Mobs named with 'silence_me' stop making noise",
            category = {FEATURE, SURVIVAL, RUG}
    )
    public static boolean silenceMobs = false;
    // silenceMobsAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/datapacks/)

    @Rule(
            desc = "Brings back the ability to force grow certain plants using 0-ticks",
            category = {EXPERIMENTAL, RUG}
    )
    public static boolean zeroTickPlants = false;

    @Rule(
            desc = "Makes shulkers behave like in the current 1.17 snapshots",
            extra = "Shulkers hit by a shulker bullet have a chance to spawn a new shulker and teleport",
            category = {EXPERIMENTAL, FEATURE, RUG}
    )
    public static boolean newShulkerBehavior = false;

    @Rule(
            desc = "Concrete powder converts to concrete blocks when on top of a filled cauldron",
            category = {FEATURE, RUG}
    )
    public static boolean concreteConvertOnCauldron = false;

    @Rule(
            desc = "Right clicking on fully grown crops harvests and immediately replants it",
            extra = "Works on: Wheat, Potatoes, Carrots, Beetroots, Nether Warts and Cocoa Beans",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean easyHarvesting = false;

    @Rule(
            desc = "The easyHarvesting feature requires the player to hold a hoe in his main hand",
            extra = "Requires easyHarvesting to be enabled",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean easyHarvestingRequireHoe = true;

    @Rule(
            desc = "Makes Netherite Scraps edible, because, let's be honest, they kinda look like chocolate",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, CLIENT, RUG}
    )
    public static boolean edibleNetheriteScraps = false;

    @Rule(
            desc = "Players do not lose any hunger like in peaceful mode",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean peacefulHunger = false;
    // peacefulHungerAdditional: Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

    @Rule(
            desc = "Food heals hearts not hunger like in the first MC versions and naturalRegeneration is off",
            extra = "Recommended using with peacefulHunger",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean foodInstantHeal = false;
    // foodInstantHealAdditional: Suggestion by [real_zockerhopper](https://www.curseforge.com/members/real_zockerhopper)

    @Rule(
            desc = "Lily Pads can be placed on Cauldrons",
            category = {FEATURE, RUG}
    )
    public static boolean lilyPadsOnCauldron = false;

    public static class validatorStonecutterDamage extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 10 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 10";
        }
    }

    @Rule(
            desc = "How much damage Stonecutters deal when stepping on them",
            options = {"0", "3", "4", "5"},
            strict = false,
            validate = validatorStonecutterDamage.class,
            category = {BUGFIX, SURVIVAL, RUG}
    )
    public static int stonecutterDamage = 0;

    public static class validatorEnderPearlDamage extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 10 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 10";
        }
    }

    @Rule(
            desc = "Amount of damage dealt by Ender Pearls",
            options = {"0", "2", "3", "5"},
            strict = false,
            validate = validatorEnderPearlDamage.class,
            category = {FEATURE, SURVIVAL, RUG}
    )
    public static int enderPearlDamage = 5;

    @Rule(
            desc = "Slime Balls are edible and give Jump Boost and Slowness",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, CLIENT, RUG}
    )
    public static boolean edibleSlimeBalls = false;

    public static class validatorReachDistance extends Validator<Double> {

        @Override
        public Double validate(ServerCommandSource source, ParsedRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 100 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 100";
        }
    }

    @Rule(
            desc = "Reach in which you can place and break blocks. Value will be 0.5 higher in creative",
            extra = "Mod needed on server and client for this feature to work",
            strict = false,
            validate = validatorReachDistance.class,
            options = {"0", "4.5", "5", "10"},
            category = {EXPERIMENTAL, CREATIVE, CLIENT, RUG}
    )
    public static double reachDistance = 4.5;

    @Rule(
            desc = "Butter is finally edible. Keep in mind 250g of pure butter are not that healthy",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, CLIENT, RUG}
    )
    public static boolean edibleGoldIngots = false;

    public static class validatorCactusFurnaceXP extends Validator<Double> {

        @Override
        public Double validate(ServerCommandSource source, ParsedRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 1 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 1";
        }
    }

    @Rule(
            desc = "Amount of XP a Cactus smelted in a furnace gives",
            extra = "1 XP per Cactus seems to be a bug, as in Bedrock Edition it's only 0.2, which fits more in line with other items",
            options = {"0.1", "0.2", "0.5", "1"},
            strict = false,
            validate = validatorCactusFurnaceXP.class,
            category = {BUGFIX, SURVIVAL, RUG}
    )
    public static double cactusFurnaceXP = 1;

    @Rule(
            desc = "Mining Farmland with a Silk Touch tool will drop itself",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean silkTouchFarmland = false;

    @Rule(
            desc = "Mining Path Blocks with a Silk Touch tool will drop itself",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean silkTouchPathBlocks = false;

    @Rule(
            desc = "Mining Spawners with a Silk Touch tool will drop itself",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG}
    )
    public static boolean silkTouchSpawners = false;

    @Rule(
            desc = "Magma Cream is edible and gives 10 seconds of Fire Resistance",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, CLIENT, RUG}
    )
    public static boolean edibleMagmaCream = false;

    @CraftingRule(
            recipes = {"easy_dispenser.json"}
    )
    @Rule(
            desc = "Dispensers can be crafted in full stacks with a dropper plus the raw materials of a bow",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyDispenserCrafting = false;
    // easyDispenserCraftingAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

    @CraftingRule(
            recipes = {"easy_bone_block.json"}
    )
    @Rule(
            desc = "Bone Blocks can be crafted out of Bones",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyBoneBlockCrafting = false;

    @CraftingRule(
            recipes = {
                    "acacia_wood.json",
                    "birch_wood.json",
                    "crimson_hyphae.json",
                    "dark_oak_wood.json",
                    "jungle_wood.json",
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
            recipeNamespace = "minecraft"
    )
    @Rule(
            desc = "Crafting Logs to Wood gives 4 instead of 3",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean moreBarkCrafting = false;

    @CraftingRule(
            recipes = {"notch_apple.json"}
    )
    @Rule(
            desc = "Enchanted Golden Apples can be crafted with 8 Gold Blocks again",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean craftableNotchApple = false;

    @CraftingRule(
            recipes = {"unpackable_quartz.json"}
    )
    @Rule(
            desc = "Quartz Blocks can be crafted into set amount of Quartz",
            extra = "Expect a lag spike when changing the value",
            options = {"0", "4"},
            strict = false,
            validate = validatorUnpackables.class,
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static int unpackableQuartz = 0;

    @CraftingRule(
            recipes = {"unpackable_wool.json"}
    )
    @Rule(
            desc = "White Wool Blocks can be crafted into set amount of String",
            extra = "Expect a lag spike when changing the value",
            options = {"0", "4"},
            strict = false,
            validate = validatorUnpackables.class,
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static int unpackableWool = 0;

    @CraftingRule(
            recipes = {"unpackable_nether_wart.json"}
    )
    @Rule(
            desc = "Nether Wart Blocks can be crafted into set amount of Nether Warts",
            extra = "Expect a lag spike when changing the value",
            options = {"0", "9"},
            strict = false,
            validate = validatorUnpackables.class,
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static int unpackableNetherWart = 0;

    @CraftingRule(
            recipes = {"easy_repeater.json"}
    )
    @Rule(
            desc = "Repeaters can be crafted with the raw materials for Redstone Torches",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyRepeaterCrafting = false;

    @CraftingRule(
            recipes = {"name_tag.json"}
    )
    @Rule(
            desc = "Name Tags can be crafted with Paper and Iron",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean craftableNameTags = false;

    @CraftingRule(
            recipes = {
                    "easy_chest_minecart.json",
                    "easy_furnace_minecart.json",
                    "easy_hopper_minecart.json",
                    "easy_tnt_minecart.json"
            }
    )
    @Rule(
            desc = "The special Minecarts can be crafted like normal Minecarts with the matching Block in the middle",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyMinecartsCrafting = false;

    @CraftingRule(
            recipes = {"easy_chest_crafting.json"}
    )
    @Rule(
            desc = "Chests can be crafted out of logs",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyChestCrafting = false;

    @CraftingRule(
            recipes = {"easy_stick_crafting.json"}
    )
    @Rule(
            desc = "Sticks can be crafted out of logs",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyStickCrafting = false;

    @CraftingRule(
            recipes = {
                    "bread.json",
                    "paper.json",
                    "shulker_box.json"
            },
            recipeNamespace = "minecraft"
    )
    @Rule(
            desc = "Bread, Paper and Shulker Boxes can be crafted in a 2x2 field",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean shapelessCrafting = false;
    // shapelessCraftingAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

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
            }
    )
    @Rule(
            desc = "Concrete Powder can be smelted to Glass of the corresponding color",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean powderToGlassSmelting = false;
    // powderToGlassSmeltingAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

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
            }
    )
    @Rule(
            desc = "Allows to dye already dyed things and clean Glass, Glass Panes and Terracotta with a Water Bucket in the Crafting Table",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean universalDyeing = false;
    // universalDyeingAdditional: Idea from [VanillaTweaks](https://vanillatweaks.net/picker/crafting-tweaks/)

    public static class validatorUnpackables extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 0 && newValue <= 9 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 9";
        }
    }

    @CraftingRule(
            recipes = {
                    "unpackable_blue_ice.json",
                    "unpackable_packed_ice.json"
            }
    )
    @Rule(
            desc = "Blue Ice and Packed Ice can be crafted into set amount of Packed Ice and Normal Ice accordingly",
            extra = "Expect a lag spike when changing the value",
            options = {"0", "3", "4", "9"},
            strict = false,
            validate = validatorUnpackables.class,
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static int unpackableIce = 0;

    @CraftingRule(
            recipes = {"cobweb.json"}
    )
    @Rule(
            desc = "Cobwebs can be crafted out of 5 Strings in a cross pattern",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean craftableCobwebs = false;

    @CraftingRule(
            recipes = {"easy_trapped_chest_crafting.json"}
    )
    @Rule(
            desc = "Trapped Chests can be crafted out of logs and a Tripwire Hook",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean easyTrappedChestCrafting = false;

    @Rule(
            desc = "A Bow enchanted with Infinity needs the player to have an arrow in his inventory",
            category = {EXPERIMENTAL, BUGFIX, SURVIVAL, RUG}
    )
    public static boolean infinityNeedsArrow = true;

    @Rule(
            desc = "Repeaters on top of Redstone BLocks multiply their delay by set amount",
            options = {"1", "2", "3", "4"},
            category = {EXPERIMENTAL, FEATURE, RUG}
    )
    public static int longerRepeaters = 1;

    public static class validatorRedstoneLampTurnOffDelay extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
            return newValue >= 0 && newValue <= 8 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 0 to 8";
        }
    }

    @Rule(
            desc = "Delay in game ticks for Redstone Lamps to turn off",
            options = {"0", "4", "8"},
            strict = false,
            validate = validatorRedstoneLampTurnOffDelay.class,
            category = {RUG}
    )
    public static int redstoneLampTurnOffDelay = 4;

    @CraftingRule(
            recipes = {
                    "craftable_horse_armor_leather_horse_armor",
                    "craftable_horse_armor_iron_horse_armor",
                    "craftable_horse_armor_golden_horse_armor",
                    "craftable_horse_armor_diamond_horse_armor",
            }
    )
    @Rule(
            desc = "Horse Armor can be crafted in vanilla_style (H-shape), with_saddle (like in VanillaTweaks) or with armor_pieces",
            extra = "Expect a lag spike when changing the value",
            options = {"off", "vanilla_style", "with_saddle", "armor_pieces"},
            category = {CRAFTING, SURVIVAL, RUG}
    )
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
                    "woodcutting_oak_fence.json",
                    "woodcutting_oak_slab.json",
                    "woodcutting_oak_stairs.json",
                    "woodcutting_spruce_fence.json",
                    "woodcutting_spruce_slab.json",
                    "woodcutting_spruce_stairs.json",
                    "woodcutting_warped_fence.json",
                    "woodcutting_warped_slab.json",
                    "woodcutting_warped_stairs.json"
            }
    )
    @Rule(
            desc = "The Stonecutter can be used for all wood types",
            extra = "Expect a lag spike when changing the value",
            category = {CRAFTING, SURVIVAL, RUG}
    )
    public static boolean woodcutting = false;

    @Rule(
            desc = "Honey Comb only sticks to selected Blocks",
            extra = "Will render Ghost Blocks on the Client when mod is only Server Side",
            options = {"both", "honey", "slime", "none"},
            category = {EXPERIMENTAL, CLIENT, FEATURE, RUG}
    )
    public static String honeyCombStickiness = "both";
    // honeyCombStickinessAdditional: Suggestion by DragonEggBedrockBreaking#0034

    @Rule(
            desc = "Dragon Eggs will convert Cobble under them to Endstone either on set event",
            options = {"off", "on_teleport", "on_landing", "both"},
            category = {EXPERIMENTAL, FEATURE, RUG}
    )
    public static String dragonEggConvertsCobbleToEndstone = "off";
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
