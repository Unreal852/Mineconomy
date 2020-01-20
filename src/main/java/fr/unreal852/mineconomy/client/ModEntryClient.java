package fr.unreal852.mineconomy.client;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModEntryCommon;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModEntryClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModLogger.LogInfo("Initializing Client Side...");
        ModEntryCommon.PROXY = new ModProxyClient();
        ModLogger.LogInfo("Initialized Client Side !");
    }
}
