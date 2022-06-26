package de.rubixdev.rug.commands;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import de.rubixdev.rug.RugSettings;

import carpet.settings.SettingsManager;
import net.minecraft.command.argument.StatusEffectArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class MaxEffectCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("maxeffect").requires(
            (player) -> SettingsManager.canUseCommand(player, RugSettings.commandMaxEffect)
        ).then(argument("effect", StatusEffectArgumentType.statusEffect()).executes(context -> {
            ServerCommandSource source = context.getSource();

            ServerPlayerEntity player = source.getPlayer();
            StatusEffect effect = StatusEffectArgumentType.getStatusEffect(context, "effect");

            boolean success = false;

            if (player instanceof LivingEntity) {
                StatusEffectInstance statusEffectInstance = new StatusEffectInstance(
                    effect,
                    effect.isInstant() ? 999999 : ( 999999 * 20 ),
                    255,
                    false,
                    false
                );
                success = ( player ).addStatusEffect(statusEffectInstance, source.getEntity());
            }


            if (!success) {
                throw new SimpleCommandExceptionType(Text.translatable("commands.effect.give.failed")).create();
            }

            source.sendFeedback(
                Text.translatable(
                    "commands.effect.give.success.single",
                        effect.getName(), player.getDisplayName(), 999999),
                true
            );

            return 1;
        }));
        dispatcher.register(command);
    }
}
