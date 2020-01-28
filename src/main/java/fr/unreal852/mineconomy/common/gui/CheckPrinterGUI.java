package fr.unreal852.mineconomy.common.gui;

import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.container.FurnaceFuelSlot;
import net.minecraft.entity.player.PlayerInventory;
import spinnery.common.BaseContainer;

public class CheckPrinterGUI extends BaseContainer
{
    public CheckPrinterGUI(int synchronizationID, PlayerInventory linkedPlayerInventory)
    {
        super(synchronizationID, linkedPlayerInventory);

    }
}
