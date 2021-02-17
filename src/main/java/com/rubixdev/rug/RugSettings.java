package com.rubixdev.rug;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.EXPERIMENTAL;
import static carpet.settings.RuleCategory.FEATURE;
import static carpet.settings.RuleCategory.SURVIVAL;

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
            category = {FEATURE, SURVIVAL, RUG})
    public static boolean zombifiedPiglinsSpawningInPortals = true;
}
