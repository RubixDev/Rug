package com.rubixdev.carpet.addon;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.BUGFIX;
import static carpet.settings.RuleCategory.COMMAND;
import static carpet.settings.RuleCategory.EXPERIMENTAL;
import static carpet.settings.RuleCategory.FEATURE;
import static carpet.settings.RuleCategory.CREATIVE;
import static carpet.settings.RuleCategory.CLIENT;
import static carpet.settings.RuleCategory.DISPENSER;
import static carpet.settings.RuleCategory.OPTIMIZATION;
import static carpet.settings.RuleCategory.SCARPET;
import static carpet.settings.RuleCategory.SURVIVAL;
import static carpet.settings.RuleCategory.TNT;

public class CarpetAddonSettings
{
    public static final String CUSTOM = "custom";  // Custom Category

    public static class validatorExampleRule extends Validator<Integer> {

        @Override
        public Integer validate(ServerCommandSource serverCommandSource, ParsedRule<Integer> parsedRule, Integer newValue, String s) {
            return newValue >= 0 && newValue <= 32 ? newValue : null;
        }

        @Override
        public String description() {return "You must choose a value from 0 to 32";}
    }

    @Rule(
            desc = "Example description for example Rule",
            options = {"0", "2", "5", "10"},
            // All possible categories (I think)
            category = {
                    BUGFIX,
                    COMMAND,
                    CREATIVE,
                    DISPENSER,
                    EXPERIMENTAL,
                    FEATURE,
                    SURVIVAL,
                    CLIENT,
                    OPTIMIZATION,
                    SCARPET,
                    TNT,
                    CUSTOM},
            strict = false,  // strict = true only allows given options, false allows other inputs
            validate = validatorExampleRule.class)  // validator class, only really needed when strict = false
    public static int exampleRule = 0;  // Name and default value of Rule, please make default value keep vanillas default
}
