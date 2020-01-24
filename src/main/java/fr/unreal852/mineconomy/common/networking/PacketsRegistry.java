package fr.unreal852.mineconomy.common.networking;

import fr.unreal852.mineconomy.common.ModUtils;
import fr.unreal852.mineconomy.common.networking.bank.BankPacketsHandlers;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistry;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistryElement;
import fr.unreal852.mineconomy.common.registry.registrables.PacketsRegistrable;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.minecraft.util.Identifier;

@ModRegistry(Registrable = PacketsRegistrable.class)
public enum PacketsRegistry
{
    CHECKBOOK_VALIDATION("pck_checkbook_validation", PacketEnv.SERVER, BankPacketsHandlers::onReceiveCheckbookValidation),
    ACCOUNT_CREATION("pck_account_creation", PacketEnv.SERVER, BankPacketsHandlers::onReceiveAccountCreation),
    ACCOUNT_DELETION("pck_account_deletion", PacketEnv.SERVER, BankPacketsHandlers::onReceiveAccountDeletion);

    @ModRegistryElement(Name = "identifier")
    private Identifier m_identifier;
    @ModRegistryElement(Name = "packetEnv")
    private PacketEnv m_packetEnv;
    @ModRegistryElement(Name = "packetConsumer")
    private PacketConsumer m_packetConsumer;

    PacketsRegistry(String identifier, PacketEnv packetEnv, PacketConsumer packetConsumer)
    {
        m_identifier = ModUtils.getIdentifier(identifier);
        m_packetEnv = packetEnv;
        m_packetConsumer = packetConsumer;
    }

    public Identifier getIdentifier()
    {
        return m_identifier;
    }

    public PacketEnv getPacketEnv()
    {
        return m_packetEnv;
    }

    public PacketConsumer getConsumer()
    {
        return m_packetConsumer;
    }
}
