package fr.unreal852.mineconomy.client;

import fr.unreal852.mineconomy.client.networking.ClientPacketsHandlers;
import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModEntryCommon;
import fr.unreal852.mineconomy.common.registry.PacketRegistry;
import fr.unreal852.ucorefabric.registry.ModRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;

@Environment(EnvType.CLIENT)
public class ModEntryClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModLogger.LogInfo("Initializing Client Side...");
        ModEntryCommon.PROXY = new ModProxyClient();
        ClientSidePacketRegistry.INSTANCE.register(PacketRegistry.BANK_ACTION_RESULT, ClientPacketsHandlers::onReceiveBankActionResult);
        ModLogger.LogInfo("Initialized Client Side !");
    }
}
