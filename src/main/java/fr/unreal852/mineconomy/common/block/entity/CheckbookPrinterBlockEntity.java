package fr.unreal852.mineconomy.common.block.entity;

import fr.unreal852.mineconomy.common.inventory.BlockEntityInventory;
import fr.unreal852.mineconomy.common.registry.EntityRegistry;
import fr.unreal852.mineconomy.common.registry.ItemRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Tickable;

public class CheckbookPrinterBlockEntity extends BlockEntity implements Tickable
{
    private BlockEntityInventory m_inventory   = new BlockEntityInventory(this, 2);
    private int                  m_printTime   = 20 * 2;
    private int                  m_currentTick = 0;

    public CheckbookPrinterBlockEntity()
    {
        super(EntityRegistry.CHECKBOOK_PRINTER);
    }

    public BlockEntityInventory getInventory()
    {
        return m_inventory;
    }

    @Override
    public void tick()
    {
        if (world == null || world.isClient)
            return;
        m_currentTick++;
        if (m_currentTick < m_printTime)
            return;
        ItemStack sourceStack = getInventory().getInvStack(0);
        if (sourceStack.getItem() == Items.PAPER && sourceStack.getCount() > 0)
        {
            ItemStack resultStack = getInventory().getInvStack(1);
            if (resultStack.getItem() != ItemRegistry.BANK_CHECKBOOK)
                resultStack = new ItemStack(ItemRegistry.BANK_CHECKBOOK, 0);
            if (resultStack.getCount() < ItemRegistry.BANK_CHECKBOOK.getMaxCount())
            {
                getInventory().takeInvStack(0, 1);
                resultStack.increment(1);
                getInventory().setInvStack(1, resultStack);
            }
        }
        m_currentTick = 0;
    }
}
