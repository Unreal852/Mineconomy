package fr.unreal852.mineconomy.common.blocks;

import fr.unreal852.mineconomy.common.ModUtils;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistry;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistryElement;
import fr.unreal852.mineconomy.common.registry.registrables.BlocksRegistrable;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

@ModRegistry(Registrable = BlocksRegistrable.class)
public enum BlocksRegistry
{
    CHECKBOOK_FABRICATOR("checkbook_fabricator", null, true),
    VENDING_MACHINE("vending_machine", new Block(Block.Settings.copy(Blocks.BEDROCK)), true),
    CASH_MACHINE("cash_machine", null,true),
    CREDIT_CARD_PAYMENT_MACHINE("credit_card_payment_machine", null, true),
    BANK_MANAGEMENT("bank_management", new BlockBankManagement(), true);

    @ModRegistryElement(Name = "block")
    private Block      m_Block;
    @ModRegistryElement(Name = "identifier")
    private Identifier m_Identifier;
    @ModRegistryElement(Name = "itemblock")
    private boolean    m_hasItemBlock;

    BlocksRegistry(String identifier, Block block, boolean hasItemBlock)
    {
        m_Identifier = ModUtils.getIdentifier(identifier);
        m_Block = block;
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
}
