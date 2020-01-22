package fr.unreal852.mineconomy.common.networking.bank;

import com.sun.org.apache.xpath.internal.operations.Mod;
import fr.unreal852.mineconomy.common.ModConstants;
import fr.unreal852.mineconomy.common.ModUtils;
import fr.unreal852.mineconomy.common.items.EconomyItems;
import fr.unreal852.mineconomy.common.items.ItemBankCheckbook;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;

public final class BankPacketsHandlers
{
    public static void onReceiveBankCheckValidation(PacketContext context, PacketByteBuf buffer)
    {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack handStack = ModUtils.findItemStack(playerEntity.getItemsHand(), itemStack -> itemStack.getItem() instanceof ItemBankCheckbook);
        if (handStack == null)
            return;
        int fromID = buffer.readInt();
        int toID = buffer.readInt();
        double amount = buffer.readDouble();
        ItemStack stack = EconomyItems.BANK_CHECK.createItemStack();
        CompoundTag stackTag = stack.getOrCreateTag();
        stackTag.putString("bankCheckFromName", playerEntity.getDisplayName().asString());
        stackTag.putInt("bankCheckFrom", fromID);
        stackTag.putInt("bankCheckTo", toID);
        stackTag.putDouble("bankCheckAmount", amount);
        handStack.decrement(1);
        playerEntity.inventory.insertStack(stack);
    }

    public static void onReceiveAccountCreation(PacketContext packetContext, PacketByteBuf packetByteBuf)
    {

    }
}
