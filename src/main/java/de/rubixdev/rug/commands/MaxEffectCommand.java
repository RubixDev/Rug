package de.rubixdev.rug.commands;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import de.rubixdev.rug.RugSettings;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

//#if MC >= 12006
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
//#else
//$$ import net.minecraft.command.argument.RegistryEntryArgumentType;
//#endif

public class MaxEffectCommand {
    public static void register(
            CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("maxeffect")
                .requires((player) -> CommandHelper.canUseCommand(player, RugSettings.commandMaxEffect))
                .then(argument(
                                "effect",
                                //#if MC >= 12006
                                RegistryEntryReferenceArgumentType.registryEntry(registryAccess, RegistryKeys.STATUS_EFFECT))
                                //#else
                                //$$ RegistryEntryArgumentType.registryEntry(registryAccess, RegistryKeys.STATUS_EFFECT))
                                //#endif
                        .executes(context -> {
                            ServerCommandSource source = context.getSource();

                            ServerPlayerEntity player = source.getPlayer();
                            //#if MC >= 12006
                            RegistryEntry.Reference<StatusEffect> effect = RegistryEntryReferenceArgumentType.getStatusEffect(context, "effect");
                            int seconds = -1;
                            Text effectName = effect.value().getName();
                            //#else
                            //$$ StatusEffect effect = RegistryEntryArgumentType.getStatusEffect(context, "effect").value();
                            //$$ int seconds = effect.isInstant() ? 999999 : (999999 * 20);
                            //$$ Text effectName = effect.getName();
                            //#endif

                            boolean success = false;

                            if (player != null) {
                                StatusEffectInstance statusEffectInstance = new StatusEffectInstance(
                                        effect, seconds, 255, false, false);
                                success = (player).addStatusEffect(statusEffectInstance, source.getEntity());
                            }

                            if (!success) {
                                throw new SimpleCommandExceptionType(Text.translatable("commands.effect.give.failed"))
                                        .create();
                            }

                            source.sendFeedback(
                                    () -> Text.translatable(
                                            "commands.effect.give.success.single",
                                            effectName,
                                            player.getDisplayName()
                                    ),
                                    true);

                            return 1;
                        }));
        dispatcher.register(command);
    }
}
