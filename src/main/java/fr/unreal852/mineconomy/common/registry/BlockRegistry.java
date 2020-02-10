package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.block.BankManagementBlock;
import fr.unreal852.mineconomy.common.block.CheckbookPrinterBlock;
import fr.unreal852.mineconomy.common.block.CreditCardPaymentBlock;
import fr.unreal852.mineconomy.common.block.VendingMachineBlock;

import static fr.unreal852.ucorefabric.registry.ModRegistry.*;
import static fr.unreal852.mineconomy.common.ModHelper.getIdentifier;

public final class BlockRegistry
{
    public static final CheckbookPrinterBlock  CHECKBOOK_PRINTER   = registerBlock(getIdentifier("checkbook_printer"), new CheckbookPrinterBlock(), ItemGroupRegistry.MINECONOMY_GROUP);
    public static final BankManagementBlock    BANK_MANAGEMENT     = registerBlock(getIdentifier("bank_management"), new BankManagementBlock(), ItemGroupRegistry.MINECONOMY_GROUP);
    public static final VendingMachineBlock    VENDING_MACHINE     = registerBlock(getIdentifier("vending_machine"), new VendingMachineBlock(), ItemGroupRegistry.MINECONOMY_GROUP);
    public static final CreditCardPaymentBlock CREDIT_CARD_PAYMENT = registerBlock(getIdentifier("credit_card_payment_terminal"), new CreditCardPaymentBlock(), ItemGroupRegistry.MINECONOMY_GROUP);

    public static void inititalize()
    {
        ModLogger.LogInfo("Registered Blocks !");
    }
}
