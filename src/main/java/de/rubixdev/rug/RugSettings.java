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

    //#if MC < 12006
    //$$ public static class validatorReachDistance extends Validator<Double> {
    //$$     @Override
    //$$     public Double validate(
    //$$             ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
    //$$         return newValue >= 0 && newValue <= 100 ? newValue : null;
    //$$     }
    //$$
    //$$     @Override
    //$$     public String description() {
    //$$         return "You must choose a value from 0 to 100";
    //$$     }
    //$$ }
    //$$
    //$$ public static boolean shouldApplyReachDistance() {
    //$$     return !FabricLoader.getInstance().isModLoaded("reach-entity-attributes")
    //$$             && !FabricLoader.getInstance().isModLoaded("pehkui")
    //$$             && !FabricLoader.getInstance().isModLoaded("carpet-org-addition");
    //$$ }
    //$$
    //$$ public static class conditionReachDistance implements Condition {
    //$$     @Override
    //$$     public boolean shouldRegister() {
    //$$         return shouldApplyReachDistance();
    //$$     }
    //$$ }
    //$$
    //$$ @Rule(
    //$$         strict = false,
    //$$         validators = validatorReachDistance.class,
    //$$         conditions = conditionReachDistance.class,
    //$$         options = {"0.0", "4.5", "5.0", "10.0"},
    //$$         categories = {EXPERIMENTAL, CREATIVE, CLIENT, RUG})
    //$$ public static double reachDistance = 4.5;
    //#endif

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

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyDispenserCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyBoneBlockCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean moreBarkCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RENEWABLE, RUG})
    public static boolean craftableNotchApple = false;

    @CraftingRule
    @Rule(
            options = {"0", "4"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableQuartz = 0;

    @CraftingRule
    @Rule(
            options = {"0", "4"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableWool = 0;

    @CraftingRule
    @Rule(
            options = {"0", "9"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableNetherWart = 0;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyRepeaterCrafting = false;

    @CraftingRule
    @Rule(
            options = {"off", "with_iron", "with_string", "with_both"},
            categories = {CRAFTING, SURVIVAL, RUG})
    public static String craftableNameTags = "off";

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyMinecartsCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyChestCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean easyStickCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean shapelessCrafting = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean powderToGlassSmelting = false;

    @CraftingRule
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

    @CraftingRule
    @Rule(
            options = {"0", "3", "4", "9"},
            strict = false,
            validators = validatorUnpackables.class,
            categories = {CRAFTING, SURVIVAL, RUG})
    public static int unpackableIce = 0;

    @CraftingRule
    @Rule(
            options = {"off", "cross", "full"},
            categories = {CRAFTING, SURVIVAL, RENEWABLE, RUG})
    public static String craftableCobwebs = "off";

    @CraftingRule
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

    @CraftingRule
    @Rule(
            options = {"off", "vanilla_style", "with_saddle", "armor_pieces"},
            categories = {CRAFTING, SURVIVAL, RENEWABLE, RUG})
    public static String craftableHorseArmor = "off";

    @CraftingRule
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

    @CraftingRule
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

    public static boolean shouldApplyBannerLayers() {
        return !FabricLoader.getInstance().isModLoaded("infinite_banner_stack");
    }

    public static class conditionMaxBannerLayers implements Condition {
        @Override
        public boolean shouldRegister() {
            return shouldApplyBannerLayers();
        }
    }

    @Rule(
            options = {"3", "6", "10", "12"},
            strict = false,
            validators = validatorMaxBannerLayers.class,
            conditions = conditionMaxBannerLayers.class,
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

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceStone = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceGlass = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceSmoothSandStone = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceNetherBrick = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceSmoothQuartz = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean blastFurnaceGlazedTerracotta = false;

    @CraftingRule
    @Rule(categories = {CRAFTING, SURVIVAL, RUG})
    public static boolean smokerGreenDye = false;

    @CraftingRule
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

    @CraftingRule
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

    @CraftingRule
    @Rule(categories = {SURVIVAL, CRAFTING, RENEWABLE, RUG})
    public static boolean craftableTuff = false;

    @CraftingRule
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

    public static class validatorCopperUnderwaterOxidationMultiplier extends Validator<Double> {
        @Override
        public Double validate(
                ServerCommandSource source, CarpetRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 1 && newValue <= 100 ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 1 to 100";
        }
    }

    @Rule(
            categories = {RUG},
            strict = false,
            options = {"1", "2", "5", "10"},
            validators = validatorCopperUnderwaterOxidationMultiplier.class)
    public static double copperUnderwaterOxidationMultiplier = 1;
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
