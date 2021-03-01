package com.rubixdev.rug;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.*;

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

public class RugSettings
{
    public static final String RUG = "rug";

    public static class validatorAnvilledIce extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 32 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 32";}
    }

    @Rule(
            desc = "Custom amount of packed ice crushed by falling anvils make one blue ice.",
            options = {"0", "4", "9"},
            category = {FEATURE, EXPERIMENTAL, SURVIVAL, RUG},
            strict = false,
            validate = validatorAnvilledIce.class)
    public static int anvilledBlueIce = 0;

    @Rule(
            desc = "Custom amount of frosted ice crushed by falling anvils make one ice. Allows for new type of ice farm.",
            options = {"0", "4", "9"},
            category = {FEATURE, EXPERIMENTAL, SURVIVAL, RUG},
            strict = false,
            validate = validatorAnvilledIce.class)
    public static int anvilledIce = 0;

    @Rule(
            desc = "Custom amount of ice crushed by falling anvils make one packed ice.",
            options = {"0", "4", "9"},
            category = {FEATURE, EXPERIMENTAL, SURVIVAL, RUG},
            strict = false,
            validate = validatorAnvilledIce.class)
    public static int anvilledPackedIce = 0;

    @Rule(
            desc = "Allows Zombified Piglins to spawn inside Nether Portals",
            category = {SURVIVAL, RUG})
    public static boolean zombifiedPiglinsSpawningInPortals = true;

    @Rule(
            desc = "Reverts the fishing loot to how it was before 1.16",
            category = {SURVIVAL, RUG})
    public static boolean oldFishingLoot = false;

    @Rule(
            desc = "Prevents Creepers from destroying blocks",
            category = {SURVIVAL, RUG})
    public static boolean noCreeperGriefing = false;

    @Rule(
            desc = "Prevents Endermen from picking up and placing blocks",
            category = {SURVIVAL, RUG})
    public static boolean noEndermanGriefing = false;

    @Rule(
            desc = "Prevents Ghasts from destroying blocks",
            category = {SURVIVAL, RUG})
    public static boolean noGhastGriefing = false;

    @Rule(
            desc = "Players drop their head when killed by a player",
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean playerHeadDrops = false;

    @Rule(
            desc = "Ender Dragon drops an Elytra when killed",
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean dragonDropsElytra = false;

    public static class validatorStrictShulkerShells extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 4 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 4";}
    }

    @Rule(
            desc = "Shulkers always drop a given amount of shulker shells when killed",
            category = {FEATURE, SURVIVAL, RUG},
            options = {"0", "1", "2"},
            strict = false,
            validate = validatorStrictShulkerShells.class)
    public static int strictShulkerShells = 0;

    @Rule(
            desc = "Mobs named with 'silence_me' stop making noise",
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean silenceMobs = false;

    @Rule(
            desc = "Brings back the ability to force grow certain plants using 0-ticks",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean zeroTickPlants = false;

    @Rule(
            desc = "Makes shulkers behave like in the current 1.17 snapshots",
            extra = "Shulkers hit by a shulker bullet have a chance to spawn a new shulker and teleport",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean newShulkerBehavior = false;
  
    @Rule(
            desc = "Concrete powder converts to concrete blocks when on top of a filled cauldron",
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean concreteConvertOnCauldron = false;

    @Rule(
            desc = "Right clicking on fully grown crops harvests and immediately replants it",
            extra = "Works on: Wheat, Potatoes, Carrots, Beetroots, Nether Warts and Cocoa Beans",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean easyHarvesting = false;

    @Rule(
            desc = "The easyHarvesting feature requires the player to hold a hoe in his main hand",
            extra = "Requires easyHarvesting to be enabled",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean easyHarvestingRequireHoe = true;

    @Rule(
            desc = "Makes Netherite Scraps edible, because, let's be honest, they kinda look like chocolate",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, BUGFIX, FEATURE, SURVIVAL, CLIENT, RUG})
    public static boolean edibleNetheriteScraps = false;

    @Rule(
            desc = "Players do not lose any hunger like in peaceful mode",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean peacefulHunger = false;

    @Rule(
            desc = "Food heals hearts not hunger like in the first MC versions and naturalRegeneration is off",
            extra = "Recommended using with peacefulHunger",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean foodInstantHeal = false;

    @Rule(
            desc = "Lily Pads can be placed on Cauldrons",
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean lilyPadsOnCauldron = false;

    public static class validatorStonecutterDamage extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 10 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 10";}
    }

    @Rule(
            desc = "How much damage Stonecutters deal when stepping on them",
            options = {"0", "3", "4", "5"},
            strict = false,
            validate = validatorStonecutterDamage.class,
            category = {BUGFIX, SURVIVAL, RUG})
    public static int stonecutterDamage = 0;

    public static class validatorEnderPearlDamage extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 10 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 10";}
    }

    @Rule(
            desc = "Amount of damage dealt by Ender Pearls",
            options = {"0", "2", "3", "5"},
            strict = false,
            validate = validatorEnderPearlDamage.class,
            category = {FEATURE, SURVIVAL, RUG})
    public static int enderPearlDamage = 5;

    @Rule(
            desc = "Slime Balls are edible and give Jump Boost and Slowness",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, CLIENT, RUG})
    public static boolean edibleSlimeBalls = false;

    public static class validatorReachDistance extends Validator<Double> {

        @Override
        public Double validate(ServerCommandSource source, ParsedRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 100 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 100";}
    }

    @Rule(
            desc = "Reach in which you can place and break blocks. Value will be 0.5 higher in creative",
            extra = "Mod needed on server and client for this feature to work",
            strict = false,
            validate = validatorReachDistance.class,
            options = {"0", "4.5", "5", "10"},
        category = {EXPERIMENTAL, CREATIVE, CLIENT, RUG})
    public static double reachDistance = 4.5;

    @Rule(
            desc = "Butter is finally edible. Keep in mind 250g of pure butter are not that healthy",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, BUGFIX, FEATURE, SURVIVAL, CLIENT, RUG})
    public static boolean edibleGoldIngots = false;

    public static class validatorCactusFurnaceXP extends Validator<Double> {

        @Override
        public Double validate(ServerCommandSource source, ParsedRule<Double> currentRule, Double newValue, String string) {
            return newValue >= 0 && newValue <= 1 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 1";}
    }

    @Rule(
            desc = "Amount of XP a Cactus smelted in a furnace gives",
            extra = "1 XP per Cactus seems to be a bug, as in Bedrock Edition it's only 0.2, which fits more in line with other items",
            options = {"0.1", "0.2", "0.5", "1"},
            strict = false,
            validate = validatorCactusFurnaceXP.class,
            category = {BUGFIX, SURVIVAL, RUG})
    public static double cactusFurnaceXP = 1;

    @Rule(
            desc = "Mining Farmland with a Silk Touch tool will drop itself",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchFarmland = false;

    @Rule(
            desc = "Mining Path Blocks with a Silk Touch tool will drop itself",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchPathBlocks = false;

    @Rule(
            desc = "Mining Spawners with a Silk Touch tool will drop itself",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, RUG})
    public static boolean silkTouchSpawners = false;

    @Rule(
            desc = "Magma Cream is edible and gives 10 seconds of Fire Resistance",
            extra = "Works server side only, but eating animation is only rendered if the mod is on the client too",
            category = {EXPERIMENTAL, FEATURE, SURVIVAL, CLIENT, RUG})
    public static boolean edibleMagmaCream = false;
}
