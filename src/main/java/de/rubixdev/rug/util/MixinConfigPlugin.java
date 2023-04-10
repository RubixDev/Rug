package de.rubixdev.rug.util;

import com.google.common.collect.Lists;
import de.rubixdev.rug.RugSettings;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class MixinConfigPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        List<String> reachDistanceMixins = Lists.newArrayList(
                "de.rubixdev.rug.mixins.reach.ClientPlayerInteractionManagerMixin",
                "de.rubixdev.rug.mixins.reach.InventoryMixin",
                "de.rubixdev.rug.mixins.reach.GameRendererMixin",
                "de.rubixdev.rug.mixins.reach.PlayerInventoryMixin",
                "de.rubixdev.rug.mixins.reach.ScreenHandlerMixin",
                "de.rubixdev.rug.mixins.reach.ServerPlayerInteractionManagerMixin",
                "de.rubixdev.rug.mixins.reach.ServerPlayNetworkHandlerMixin",
                "de.rubixdev.rug.mixins.reach.VehicleInventoryMixin");

        if (reachDistanceMixins.contains(mixinClassName)) {
            return RugSettings.shouldApplyReachDistance();
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
