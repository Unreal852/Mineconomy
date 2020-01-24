package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.blocks.BlocksRegistry;
import fr.unreal852.mineconomy.common.items.ItemsRegistry;
import fr.unreal852.mineconomy.common.networking.PacketsRegistry;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import fr.unreal852.mineconomy.common.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;

public class ModEntryCommon implements ModInitializer
{
    public static IModProxy PROXY = null;

    @Override
    public void onInitialize()
    {
        ModLogger.LogInfo("Initializing Common...");

        ModRegistry.registerAll(ItemsRegistry.class, BlocksRegistry.class, PacketsRegistry.class);

        ModConstants.BANK_ACCOUNTS_FILES_DIRECTORY.mkdirs();

        ModLogger.LogInfo("Initialized Common !");
    }
}
