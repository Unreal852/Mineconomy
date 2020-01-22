package fr.unreal852.mineconomy.server;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModEntryCommon;
import net.fabricmc.api.DedicatedServerModInitializer;

public class ModEntryServer implements DedicatedServerModInitializer
{
    @Override
    public void onInitializeServer()
    {
        ModLogger.LogInfo("Initializing Server Side...");
        ModEntryCommon.PROXY = new ModProxyServer();
        ModLogger.LogInfo("Initialized Server Side !");
    }
}
