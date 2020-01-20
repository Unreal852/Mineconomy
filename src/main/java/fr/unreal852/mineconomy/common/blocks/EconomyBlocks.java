package fr.unreal852.mineconomy.common.blocks;

import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum EconomyBlocks
{
    VENDING_MACHINE(new Block(Block.Settings.copy(Blocks.BEDROCK)), ModUtils.getIdentifier("vending_machine"), true),
    CASH_MACHINE(null, ModUtils.getIdentifier("cash_machine"), true),
    CREDIT_CARD_PAYMENT_MACHINE(null, ModUtils.getIdentifier("credit_card_payment_machine"), true),
    BANK_MANAGEMENT(new BlockBankManagement(), ModUtils.getIdentifier("bank_management"), true);

    private Block      m_Block;
    private Identifier m_Identifier;
    private boolean    m_hasItemBlock;

    EconomyBlocks(Block block, Identifier identifier, boolean hasItemBlock)
    {
        m_Block = block;
        m_Identifier = identifier;
        m_hasItemBlock = hasItemBlock;
    }

    /**
     * Get Block
     *
     * @return Block
     */
    public Block getBlock()
    {
        return m_Block;
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
     * Get has item block
     *
     * @return True if this block require a item block
     */
    public boolean hasItemBlock()
    {
        return m_hasItemBlock;
    }

    /**
     * Register all enumerated items
     */
    public static void RegisterBlocks()
    {
        ModLogger.LogInfo("Registering Blocks...");
        for (EconomyBlocks economyBlock : values())
        {
            if (economyBlock.getBlock() == null || economyBlock.getIdentifier() == null)
            {
                ModLogger.LogError("Block '" + economyBlock.name() + "' is missing block or identifier. Skipping Registration !");
                continue;
            }
            Registry.register(Registry.BLOCK, economyBlock.getIdentifier(), economyBlock.getBlock());
            ModLogger.LogInfo("Registered Block '" + economyBlock.getIdentifier().getPath() + "'");
            if (economyBlock.hasItemBlock())
            {
                Registry.register(Registry.ITEM, economyBlock.getIdentifier(), new BlockItem(economyBlock.getBlock(), new Item.Settings().group(ModConstants.MOD_ITEM_GROUP)));
                ModLogger.LogInfo("Registered Item Block '" + economyBlock.getIdentifier().getPath() + "' (Associated with previous registered block)");
            }
        }
    }
}
