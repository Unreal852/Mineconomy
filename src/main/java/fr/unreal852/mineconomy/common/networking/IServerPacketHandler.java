package fr.unreal852.mineconomy.common.networking;

import fr.unreal852.mineconomy.common.ModLogger;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

public interface IServerPacketHandler
{
    static void onReceive(PacketContext packetContext, PacketByteBuf buffer)
    {

    }
}
