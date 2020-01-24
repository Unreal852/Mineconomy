package fr.unreal852.mineconomy.common.registry.registrables;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.networking.PacketEnv;
import fr.unreal852.mineconomy.common.registry.IModRegistrable;
import fr.unreal852.mineconomy.common.registry.ModRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PacketsRegistrable<T extends Enum<T>> implements IModRegistrable<T>
{
    @Override
    public void onRegister(T enumValue)
    {
        Identifier identifier = ModRegistry.getRegistryElementValue(enumValue, "identifier", Identifier.class);
        PacketEnv packetEnv = ModRegistry.getRegistryElementValue(enumValue, "packetEnv", PacketEnv.class);
        PacketConsumer packetConsumer = ModRegistry.getRegistryElementValue(enumValue, "packetConsumer", PacketConsumer.class);
        if(packetEnv == null || packetConsumer == null)
        {
            ModLogger.LogError("Packet '" + enumValue.name() + "' is missing environment or packet consumer. Skipping Registration !");
            return;
        }
        switch (packetEnv)
        {
            case CLIENT:
                ClientSidePacketRegistry.INSTANCE.register(identifier, packetConsumer);
                break;
            case COMMON:
                break;
            case SERVER:
                ServerSidePacketRegistry.INSTANCE.register(identifier, packetConsumer);
                break;
        }
    }
}
