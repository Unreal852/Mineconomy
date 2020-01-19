package fr.unreal852.mineconomy.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import spinnery.common.BaseContainer;

public abstract class BaseGUI extends BaseContainer
{
    public BaseGUI(int synchronizationID, PlayerInventory linkedPlayerInventory, Object... args)
    {
        super(synchronizationID, linkedPlayerInventory);
        if (linkedPlayerInventory.player.world.isClient)
            onInitClient(MinecraftClient.getInstance().player, MinecraftClient.getInstance().textRenderer, args);
        else
            onInitServer();
    }

    protected void onInitClient(ClientPlayerEntity client, TextRenderer textRenderer, Object... args)
    {

    }

    protected void onInitServer()
    {

    }
}
