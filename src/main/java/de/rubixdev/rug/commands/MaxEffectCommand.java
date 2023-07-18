package de.rubixdev.rug.commands;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import de.rubixdev.rug.RugSettings;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.RegistryEntryArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class MaxEffectCommand {
    public static void register(
            CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("maxeffect")
                .requires((player) -> CommandHelper.canUseCommand(player, RugSettings.commandMaxEffect))
                .then(argument(
                                "effect",
                                RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.STATUS_EFFECT))
                        .executes(context -> {
                            ServerCommandSource source = context.getSource();

                            ServerPlayerEntity player = source.getPlayer();
                            StatusEffect effect = RegistryEntryArgumentType.getStatusEffect(context, "effect")
                                    .value();

                            boolean success = false;

                            if (player instanceof LivingEntity) {
                                StatusEffectInstance statusEffectInstance = new StatusEffectInstance(
                                        effect, effect.isInstant() ? 999999 : (999999 * 20), 255, false, false);
                                success = (player).addStatusEffect(statusEffectInstance, source.getEntity());
                            }

                            if (!success) {
                                throw new SimpleCommandExceptionType(Text.translatable("commands.effect.give.failed"))
                                        .create();
                            }

                            source.sendFeedback(
                                    () -> Text.translatable(
                                            "commands.effect.give.success.single",
                                            effect.getName(),
                                            player.getDisplayName(),
                                            999999),
                                    true);

                            return 1;
                        }));
        dispatcher.register(command);
    }
}
