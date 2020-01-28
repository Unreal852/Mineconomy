package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModLogger;
import net.minecraft.util.Identifier;

import static fr.unreal852.mineconomy.common.ModUtils.getIdentifier;

public final class PacketRegistry
{
    public static final Identifier BANK_ACTION_RESULT   = getIdentifier("pck_bank_action_result");
    public static final Identifier CHECKBOOK_VALIDATION = getIdentifier("pck_checkbook_validation");
    public static final Identifier ACCOUNT_CREATION     = getIdentifier("pck_account_creation");
    public static final Identifier ACCOUNT_DELETION     = getIdentifier("pck_account_deletion");

    public static void inititalize()
    {
        ModLogger.LogInfo("Registered Packets !");
    }
}
