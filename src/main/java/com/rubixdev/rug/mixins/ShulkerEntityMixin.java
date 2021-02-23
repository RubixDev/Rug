package com.rubixdev.rug.mixins;

import com.rubixdev.rug.RugSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends LivingEntity {
    @Shadow protected abstract boolean isClosed();

    @Shadow protected abstract boolean tryTeleport();

    @Shadow @Nullable public abstract DyeColor getColor();

    @Shadow @Final protected static TrackedData<Byte> COLOR;

    protected ShulkerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At(value = "JUMP", ordinal = 3, shift = At.Shift.AFTER))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (RugSettings.newShulkerBehavior && !((double)this.getHealth() < (double)this.getMaxHealth() * 0.5D && this.random.nextInt(4) == 0) && source.isProjectile()) {
            Entity entity = source.getSource();
            if (entity != null && entity.getType() == EntityType.SHULKER_BULLET) {
                this.spawnNewShulker();
            }
        }
    }

    private void spawnNewShulker() {
        Vec3d vec3d = this.getPos();
        Box box = this.getBoundingBox();
        if (!this.isClosed() && this.tryTeleport()) {
            int i = this.world.getEntitiesByType(EntityType.SHULKER, box.expand(8.0D), Entity::isAlive).size();
            float f = (float)(i - 1) / 5.0F;
            if (this.world.random.nextFloat() >= f) {
                ShulkerEntity shulkerEntity = EntityType.SHULKER.create(this.world);
                DyeColor dyeColor = this.getColor();
                if (dyeColor != null) {
                    assert shulkerEntity != null;
                    setColor(shulkerEntity, dyeColor);
                }

                assert shulkerEntity != null;
                shulkerEntity.refreshPositionAfterTeleport(vec3d);
                this.world.spawnEntity(shulkerEntity);
            }
        }
    }

    private void setColor(ShulkerEntity shulkerEntity, DyeColor color) {
        DataTracker dataTracker = shulkerEntity.getDataTracker();
        TrackedData<Byte> COLOR = ShulkerEntityAccessorMixin.getColorTrackerKey();
        byte colorId = (byte) (color != null ? color.getId() : 16);

        dataTracker.set(COLOR, colorId);
    }
}
