package fr.unreal852.mineconomy.common.networking;

import net.fabricmc.fabric.api.network.PacketConsumer;

public interface IClientPacketHandler
{
    void onReceive(PacketConsumer packetConsumer);
}
