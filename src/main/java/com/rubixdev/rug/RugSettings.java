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

    public static class validatorstrictShulkerShells extends Validator<Integer> {

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
            validate = validatorstrictShulkerShells.class)
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
            desc = "Concrete powder converts to concrete blocks when on top of a filled cauldron",
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean concreteConvertOnCauldron = false;
}
