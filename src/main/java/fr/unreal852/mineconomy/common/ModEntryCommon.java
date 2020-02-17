package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.networking.ServerPacketsHandlers;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import fr.unreal852.mineconomy.common.registry.*;
import fr.unreal852.ucorefabric.common.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public class ModEntryCommon implements ModInitializer
{
    public static IModProxy PROXY = null;

    @Override
    public void onInitialize()
    {
        ModLogger.LogInfo("Initializing Common...");
        ModRegistry.initRegistries(ItemGroupRegistry.class, ItemRegistry.class, BlockRegistry.class, EntityRegistry.class, PacketRegistry.class, ContainerRegistry.class);
        ServerSidePacketRegistry.INSTANCE.register(PacketRegistry.ACCOUNT_CREATION, ServerPacketsHandlers::onReceiveAccountCreation);
        ServerSidePacketRegistry.INSTANCE.register(PacketRegistry.ACCOUNT_DELETION, ServerPacketsHandlers::onReceiveAccountDeletion);
        ServerSidePacketRegistry.INSTANCE.register(PacketRegistry.CHECKBOOK_VALIDATION, ServerPacketsHandlers::onReceiveCheckbookValidation);
        ModLogger.LogInfo("Initialized Common !");
    }
}
