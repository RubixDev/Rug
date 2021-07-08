package de.rubixdev.rug.mixins;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.ShulkerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ShulkerEntity.class)
public interface ShulkerEntityAccessorMixin {
    @Accessor("COLOR")
    static TrackedData<Byte> getColorTrackerKey() {
        throw null;
    }
}
