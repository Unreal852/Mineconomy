package fr.unreal852.mineconomy.common.bank;

import fr.unreal852.mineconomy.common.registry.PacketRegistry;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.PacketByteBuf;

public class MinecraftBankActionResult
{
    private boolean m_success;
    private String  m_message;

    public MinecraftBankActionResult(boolean success)
    {
        this(success, "");
    }

    public MinecraftBankActionResult(boolean success, String message)
    {
        m_success = success;
        m_message = message;
    }

    public boolean isSuccess()
    {
        return m_success;
    }

    public String getMessage()
    {
        return m_message;
    }

    public void send(PlayerEntity player)
    {
        PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
        packetByteBuf.writeString(m_message, 32767);
        packetByteBuf.writeBoolean(m_success);
        ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PacketRegistry.BANK_ACTION_RESULT, packetByteBuf);
    }
}
