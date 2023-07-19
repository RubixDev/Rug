package de.rubixdev.rug.mixins;

import de.rubixdev.rug.util.Storage;
import net.minecraft.world.level.storage.LevelStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelStorage.class)
public class LevelStorageMixin {
    @Inject(method = "createSession", at = @At("RETURN"))
    private void storeSession(String directoryName, CallbackInfoReturnable<LevelStorage.Session> cir) {
        Storage.session = cir.getReturnValue();
    }

    @Inject(method = "createSessionWithoutSymlinkCheck", at = @At("RETURN"))
    private void storeSessionWithoutSymlinkCheck(
            String directoryName, CallbackInfoReturnable<LevelStorage.Session> cir) {
        Storage.session = cir.getReturnValue();
    }
}
