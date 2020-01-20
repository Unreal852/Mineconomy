package fr.unreal852.mineconomy.common.items;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum EconomyItems
{
    MONEY_COIN_ONE(new Item( new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_coin_one")),
    MONEY_COIN_TWO(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_coin_two")),
    MONEY_BANKNOTE_FIVE(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_five")),
    MONEY_BANKNOTE_TEN(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_ten")),
    MONEY_BANKNOTE_TWENTY(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_twenty")),
    MONEY_BANKNOTE_FIFTY(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_fifty")),
    MONEY_BANKNOTE_ONE_HUNDRED(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_one_hundred")),
    BANK_CHECK(new ItemBankCheck(), ModUtils.getIdentifier("bank_check")),
    BANK_CHECKBOOK(new ItemBankCheckbook(), ModUtils.getIdentifier("bank_checkbook")),
    WALLET(new ItemWallet(), ModUtils.getIdentifier("wallet")),
    CREDIT_CARD(new ItemCreditCard(), ModUtils.getIdentifier("credit_card"));

    private Item       m_Item;
    private Identifier m_Identifier;

    EconomyItems(Item item, Identifier identifier)
    {
        m_Item = item;
        m_Identifier = identifier;
    }

    /**
     * Get item
     *
     * @return Item
     */
    public Item getItem()
    {
        return m_Item;
    }

    /**
     * Create a new itemstack
     * @return Item Stack
     */
    public ItemStack getNewItemStack()
    {
        return new ItemStack(getItem());
    }

    /**
     * Get Identifier
     *
     * @return Identifier
     */
    public Identifier getIdentifier()
    {
        return m_Identifier;
    }

    /**
     * Register all enumerated items
     */
    public static void RegisterItems()
    {
        ModLogger.LogInfo("Registering Items...");
        for(EconomyItems economyItem : values())
        {
            if(economyItem.getItem() == null || economyItem.getIdentifier() == null)
            {
                ModLogger.LogError("Item '" + economyItem.name() + "' is missing item or identifier. Skipping Registration !");
                continue;
            }
            Registry.register(Registry.ITEM, economyItem.getIdentifier(), economyItem.getItem());
            ModLogger.LogInfo("Registered Item '" + economyItem.getIdentifier().getPath() + "'");
        }
    }
}
