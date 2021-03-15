package com.rubixdev.rug.screenhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerInventoryScreenHandler extends ScreenHandler {
    private final ServerPlayerEntity player;

    public PlayerInventoryScreenHandler(int syncId, ServerPlayerEntity player, PlayerInventory targetInventory) {
        super(ScreenHandlerType.GENERIC_9X5, syncId);
        this.player = player;
        PlayerInventory playerInventory = player.inventory;

        int y;
        int x;
        for (y = 0; y < 5; ++y) {
            for (x = 0; x < 9; ++x) {
                this.addSlot(new Slot(targetInventory, x + y * 9, 8 + x * 18, 18 + y * 18));
            }
        }

        for (y = 0; y < 3; ++y) {
            for (x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18 + 18));
            }
        }

        for (y = 0; y < 9; ++y) {
            this.addSlot(new Slot(playerInventory, y, 8 + y * 18, 179));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        this.resendInventory();
        return new ItemStack(Items.GRAY_CARPET);
    }

    @Override
    public ItemStack onSlotClick(int i, int j, SlotActionType actionType, PlayerEntity playerEntity) {
        if (i > 40 && i < 45) {
            this.resendInventory();
            return ItemStack.EMPTY;
        }
        return super.onSlotClick(i, j, actionType, playerEntity);
    }

    private void resendInventory() {
        player.onHandlerRegistered(this, this.getStacks());
    }
}
