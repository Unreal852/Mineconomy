package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.client.gui.BankCheckBookGUI;
import fr.unreal852.mineconomy.common.blocks.EconomyBlocks;
import fr.unreal852.mineconomy.common.items.EconomyItems;
import fr.unreal852.mineconomy.server.ModEntryServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class ModEntryCommon implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        ModLogger.LogInfo("Initializing Common...");
        EconomyItems.RegisterItems();
        EconomyBlocks.RegisterBlocks();
        ContainerProviderRegistry.INSTANCE.registerFactory(ModConstants.GUI_ID_BANK_CHECK, (syncId, id, player, buffer) -> new BankCheckBookGUI(syncId, player.inventory, buffer.readItemStack()));
        ServerSidePacketRegistry.INSTANCE.register(EconomyPackets.BANK_CHECK_VALIDATION, ModEntryServer::onReceiveBankCheckValidation);
        ModLogger.LogInfo("Initialized Common !");
    }
}
