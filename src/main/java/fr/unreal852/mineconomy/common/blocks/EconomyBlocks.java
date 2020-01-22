package fr.unreal852.mineconomy.common.blocks;

import fr.unreal852.mineconomy.common.ModUtils;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistry;
import fr.unreal852.mineconomy.common.registry.annotations.ModRegistryElement;
import fr.unreal852.mineconomy.common.registry.registrables.BlocksRegistrable;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

@ModRegistry(Registrable = BlocksRegistrable.class)
public enum EconomyBlocks
{
    VENDING_MACHINE(new Block(Block.Settings.copy(Blocks.BEDROCK)), ModUtils.getIdentifier("vending_machine"), true),
    CASH_MACHINE(null, ModUtils.getIdentifier("cash_machine"), true),
    CREDIT_CARD_PAYMENT_MACHINE(null, ModUtils.getIdentifier("credit_card_payment_machine"), true),
    BANK_MANAGEMENT(new BlockBankManagement(), ModUtils.getIdentifier("bank_management"), true);

    @ModRegistryElement(Name = "block")
    private Block      m_Block;
    @ModRegistryElement(Name = "identifier")
    private Identifier m_Identifier;
    @ModRegistryElement(Name = "itemblock")
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
}
