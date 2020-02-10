package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModHelper;
import fr.unreal852.mineconomy.common.item.BankCheckItem;
import fr.unreal852.mineconomy.common.item.BankCheckbookItem;
import fr.unreal852.mineconomy.common.item.CreditCardItem;
import fr.unreal852.mineconomy.common.item.WalletItem;
import net.minecraft.item.Item;

import static fr.unreal852.ucorefabric.registry.ModRegistry.*;
import static fr.unreal852.mineconomy.common.ModHelper.getIdentifier;

public final class ItemRegistry
{
    public static final Item MONEY_COIN_ONE             = registerItem(getIdentifier("money_coin_one"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item MONEY_COIN_TWO             = registerItem(getIdentifier("money_coin_two"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item MONEY_BANKNOTE_FIVE        = registerItem(getIdentifier("money_banknote_five"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item MONEY_BANKNOTE_TEN         = registerItem(getIdentifier("money_banknote_ten"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item MONEY_BANKNOTE_TWENTY      = registerItem(getIdentifier("money_banknote_twenty"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item MONEY_BANKNOTE_FIFTY       = registerItem(getIdentifier("money_banknote_fifty"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item MONEY_BANKNOTE_ONE_HUNDRED = registerItem(getIdentifier("money_banknote_one_hundred"), ModHelper.createItem(ItemGroupRegistry.MINECONOMY_GROUP));
    public static final Item BANK_CHECK                 = registerItem(getIdentifier("bank_check"), new BankCheckItem());
    public static final Item BANK_CHECKBOOK             = registerItem(getIdentifier("bank_checkbook"), new BankCheckbookItem());
    public static final Item WALLET                     = registerItem(getIdentifier("wallet"), new WalletItem());
    public static final Item CREDIT_CARD                = registerItem(getIdentifier("credit_card"), new CreditCardItem());

    public static void inititalize()
    {
        ModLogger.LogInfo("Registered Items !");
    }
}
