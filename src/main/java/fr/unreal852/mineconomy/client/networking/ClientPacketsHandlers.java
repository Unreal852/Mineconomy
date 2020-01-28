package fr.unreal852.mineconomy.client.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

@Environment(EnvType.CLIENT)
public final class ClientPacketsHandlers
{
    public static void onReceiveBankActionResult(PacketContext packetContext, PacketByteBuf packetByteBuf)
    {

    }
}
