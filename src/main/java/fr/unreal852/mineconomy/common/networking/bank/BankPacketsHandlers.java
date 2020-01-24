package fr.unreal852.mineconomy.common.networking.bank;

import fr.unreal852.mineconomy.common.ModUtils;
import fr.unreal852.mineconomy.common.bank.MinecraftBank;
import fr.unreal852.mineconomy.common.items.ItemsRegistry;
import fr.unreal852.mineconomy.common.items.ItemBankCheckbook;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;

public final class BankPacketsHandlers
{
    public static void onReceiveCheckbookValidation(PacketContext context, PacketByteBuf buffer)
    {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack handStack = ModUtils.findItemStack(playerEntity.getItemsHand(), itemStack -> itemStack.getItem() instanceof ItemBankCheckbook);
        if (handStack == null)
            return;
        int toID = buffer.readInt();
        double amount = buffer.readDouble();
        ItemStack stack = ItemsRegistry.BANK_CHECK.createItemStack();
        CompoundTag stackTag = stack.getOrCreateTag();
        stackTag.putString("bankCheckFromName", playerEntity.getDisplayName().asString());
        stackTag.putInt("bankCheckFrom", 852);
        stackTag.putInt("bankCheckTo", toID);
        stackTag.putDouble("bankCheckAmount", amount);
        handStack.decrement(1);
        playerEntity.inventory.insertStack(stack);
    }

    public static void onReceiveAccountCreation(PacketContext packetContext, PacketByteBuf packetByteBuf)
    {
        PlayerEntity playerEntity = packetContext.getPlayer();
        if(playerEntity == null || !playerEntity.allowsPermissionLevel(4))
            return;
        MinecraftBank bank = MinecraftBank.getInstance();

    }

    public static void onReceiveAccountDeletion(PacketContext packetContext, PacketByteBuf packetByteBuf)
    {

    }
}
