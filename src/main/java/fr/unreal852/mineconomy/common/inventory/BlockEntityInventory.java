package fr.unreal852.mineconomy.common.inventory;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockEntityInventory implements Inventory, InventoryListener
{
    private DefaultedList<ItemStack> m_itemStacks;
    private List<InventoryListener>  m_listeners = new ArrayList<>();
    private BlockEntity              m_blockEntity;
    private int                      m_invSize;

    public BlockEntityInventory(BlockEntity blockEntity, int invSize)
    {
        m_blockEntity = blockEntity;
        m_invSize = invSize;
        m_itemStacks = DefaultedList.ofSize(m_invSize, ItemStack.EMPTY);
    }

    @Override
    public int getInvSize()
    {
        return m_invSize;
    }

    @Override
    public boolean isInvEmpty()
    {
        return m_itemStacks.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getInvStack(int slot)
    {
        return m_itemStacks.get(slot);
    }

    @Override
    public ItemStack takeInvStack(int slot, int amount)
    {
        markDirty();
        ItemStack stack = getInvStack(slot).split(amount);
        onInvChange(this);
        return stack;
    }

    @Override
    public ItemStack removeInvStack(int slot)
    {
        markDirty();
        ItemStack stack = m_itemStacks.remove(slot);
        onInvChange(this);
        return stack;
    }

    @Override
    public void setInvStack(int slot, ItemStack stack)
    {
        markDirty();
        m_itemStacks.set(slot, stack);
        onInvChange(this);
    }

    @Override
    public void markDirty()
    {
        m_blockEntity.markDirty();
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity player)
    {
        return true;
    }

    @Override
    public void onInvChange(Inventory inventory)
    {
        if (m_blockEntity.getWorld() != null && !m_blockEntity.getWorld().isClient)
            m_listeners.forEach(inventoryListener -> inventoryListener.onInvChange(inventory));
    }

    @Override
    public void clear()
    {
        markDirty();
        m_itemStacks.clear();
    }

    public void addListener(InventoryListener... listeners)
    {
        m_listeners.addAll(Arrays.asList(listeners));
    }

    public void removeListener(InventoryListener... listeners)
    {
        m_listeners.removeAll(Arrays.asList(listeners));
    }
}
