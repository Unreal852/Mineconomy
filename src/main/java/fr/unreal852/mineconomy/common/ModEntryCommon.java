package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.blocks.EconomyBlocks;
import fr.unreal852.mineconomy.common.items.EconomyItems;
import fr.unreal852.mineconomy.common.networking.bank.BankPacketsHandlers;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class ModEntryCommon implements ModInitializer
{
    public static IModProxy PROXY = null;

    @Override
    public void onInitialize()
    {
        ModLogger.LogInfo("Initializing Common...");
        EconomyItems.RegisterItems();
        EconomyBlocks.RegisterBlocks();

        ServerSidePacketRegistry.INSTANCE.register(ModConstants.PACKET_BANK_CHECK_VALIDATION, BankPacketsHandlers::onReceiveBankCheckValidation);
        ServerSidePacketRegistry.INSTANCE.register(ModConstants.PACKET_BANK_ACCOUNT_CREATION, BankPacketsHandlers::onReceiveAccountCreation);

        ModConstants.BANK_ACCOUNTS_FILES_DIRECTORY.mkdirs();

        ModLogger.LogInfo("Initialized Common !");
    }
}
