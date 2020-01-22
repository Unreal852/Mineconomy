package fr.unreal852.mineconomy.common.test;

/*
public enum ModRegistry implements IModRegistry<ModRegistry>
{
    ITEM_COIN_ONE("money_coin_one", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_COIN_TWO("money_coin_two", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_BANKNOTE_FIVE("money_banknote_five", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_BANKNOTE_TEN("money_banknote_ten", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_BANKNOTE_TWENTY("money_banknote_twenty", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_BANKNOTE_FIFTY("money_banknote_fifty", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_BANKNOTE_ONE_HUNDRED("money_banknote_one_hundred", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_BANK_CHECK("bank_check", new ItemBankCheck()),
    ITEM_BANK_CHECKBOOK("bank_checkbook", new ItemBankCheckbook()),
    ITEM_WALLET("wallet", new ItemWallet()),
    ITEM_MY_BANK("my_bank", new Item(new Item.Settings().group(ModConstants.MOD_ITEM_GROUP))),
    ITEM_CREDIT_CARD("credit_card", new ItemCreditCard()),
    BLOCK_VENDING_MACHINE("vending_machine", new Block(Block.Settings.copy(Blocks.BEDROCK))),
    BLOCK_CASH_MACHINE("cash_machine", new Block(Block.Settings.copy(Blocks.BEDROCK))),
    PACKET_SERVER_BANK_CHECK_VALIDATION("svrpck_bank_check_validation", BankPacketsHandlers::onReceiveAccountCreation);

    public static void registerAll()
    {
        ModLogger.LogInfo("Loading Registry...");
        for (ModRegistry element : values())
        {
            if (element.getRegistrable() == null)
            {
                ModLogger.LogError("Missing registrable for element '" + element.name() + "'. Skipping Registration !");
                continue;
            }
            element.getRegistrable().onRegister(element);
        }
        ModLogger.LogInfo("Successfully loaded Registry !");
    }

    private Identifier                   m_identifier;
    private IModRegistrable<ModRegistry> m_registrable;
    private Item                         m_item;
    private Block                        m_block;
    private PacketConsumer               m_packetConsumer;

    ModRegistry(String identifier, IModRegistrable<ModRegistry> registrable)
    {
        m_identifier = ModUtils.getIdentifier(identifier);
        m_registrable = registrable;
    }

    ModRegistry(String identifier, Item item)
    {
        this(identifier, ModConstants.REGISTRY_ITEMS);
        m_item = item;
    }

    ModRegistry(String identifier, Block block)
    {
        this(identifier, ModConstants.REGISTRY_BLOCKS);
        m_block = block;
    }

    ModRegistry(String identifier, PacketConsumer packetConsumer)
    {
        this(identifier, ModConstants.REGISTRY_BLOCKS);
        m_packetConsumer = packetConsumer;
    }

    public Identifier getIdentifier()
    {
        return m_identifier;
    }

    public IModRegistrable<ModRegistry> getRegistrable()
    {
        return m_registrable;
    }

    public boolean hasItem()
    {
        return m_item != null;
    }

    public boolean hasBlock()
    {
        return m_block != null;
    }

    public Item getItem()
    {
        return m_item;
    }

    public Block getBlock()
    {
        return m_block;
    }
} */
