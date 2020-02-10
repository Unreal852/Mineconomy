package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.networking.ServerPacketsHandlers;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import fr.unreal852.mineconomy.common.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class ModEntryCommon implements ModInitializer
{
    public static IModProxy PROXY = null;

    @Override
    public void onInitialize()
    {
        ModLogger.LogInfo("Initializing Common...");
        ItemGroupRegistry.inititalize();
        ItemRegistry.inititalize();
        BlockRegistry.inititalize();
        EntityRegistry.inititalize();
        PacketRegistry.inititalize();
        ContainerRegistry.initalize();
        ServerSidePacketRegistry.INSTANCE.register(PacketRegistry.ACCOUNT_CREATION, ServerPacketsHandlers::onReceiveAccountCreation);
        ServerSidePacketRegistry.INSTANCE.register(PacketRegistry.ACCOUNT_DELETION, ServerPacketsHandlers::onReceiveAccountDeletion);
        ServerSidePacketRegistry.INSTANCE.register(PacketRegistry.CHECKBOOK_VALIDATION, ServerPacketsHandlers::onReceiveCheckbookValidation);
        ModLogger.LogInfo("Initialized Common !");
    }
}
