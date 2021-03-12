package com.rubixdev.rug.util;

import com.google.common.collect.Lists;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        List<String> reachDistanceMixins = Lists.newArrayList(
                "com.rubixdev.rug.mixins.ClientPlayerInteractionManagerMixin",
                "com.rubixdev.rug.mixins.ServerPlayerInteractionManagerMixin",
                "com.rubixdev.rug.mixins.ServerPlayNetworkHandlerMixin"
        );

        if (reachDistanceMixins.contains(mixinClassName)) {
            Optional<ModContainer> container = FabricLoader.getInstance().getModContainer("reach-entity-attributes");
            return !container.isPresent();
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
