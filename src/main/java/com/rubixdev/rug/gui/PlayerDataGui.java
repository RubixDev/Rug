package com.rubixdev.rug.gui;

import com.rubixdev.rug.RugServer;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDataGui extends SimpleGui {
    private final ServerPlayerEntity savedPlayer;

    public PlayerDataGui(ScreenHandlerType<?> type, ServerPlayerEntity player, ServerPlayerEntity savedPlayer) {
        super(type, player, false);
        this.savedPlayer = savedPlayer;
    }

    @Override
    public void onClose() {
        RugServer.savePlayerData(savedPlayer);
    }
}
