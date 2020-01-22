package fr.unreal852.mineconomy.common.items;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.ModUtils;

import fr.unreal852.mineconomy.common.registry.annotations.ModRegistry;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistryElement;
import fr.unreal852.mineconomy.common.registry.registrables.ItemsRegistrable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@ModRegistry(Registrable = ItemsRegistrable.class)
public enum EconomyItems
{
    MONEY_COIN_ONE(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_coin_one")),
    MONEY_COIN_TWO(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_coin_two")),
    MONEY_BANKNOTE_FIVE(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_five")),
    MONEY_BANKNOTE_TEN(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_ten")),
    MONEY_BANKNOTE_TWENTY(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_twenty")),
    MONEY_BANKNOTE_FIFTY(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_fifty")),
    MONEY_BANKNOTE_ONE_HUNDRED(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("money_banknote_one_hundred")),
    BANK_CHECK(new ItemBankCheck(), ModUtils.getIdentifier("bank_check")),
    BANK_CHECKBOOK(new ItemBankCheckbook(), ModUtils.getIdentifier("bank_checkbook")),
    WALLET(new ItemWallet(), ModUtils.getIdentifier("wallet")),
    MY_BANK(new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)), ModUtils.getIdentifier("my_bank")),
    CREDIT_CARD(new ItemCreditCard(), ModUtils.getIdentifier("credit_card"));

    @ModRegistryElement(Name = "item")
    private Item       m_Item;
    @ModRegistryElement(Name = "identifier")
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
     *
     * @return Item Stack
     */
    public ItemStack createItemStack()
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
}
