package fr.unreal852.mineconomy.common.networking;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.bank.MinecraftBank;
import fr.unreal852.mineconomy.common.bank.MinecraftBankAccount;
import fr.unreal852.mineconomy.common.bank.MinecraftBankActionResult;
import fr.unreal852.mineconomy.common.item.BankCheckbookItem;
import fr.unreal852.mineconomy.common.registry.ItemRegistry;
import fr.unreal852.ucorefabric.util.JavaUtils;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.PacketByteBuf;

public class ServerPacketsHandlers
{
    public static void onReceiveCheckbookValidation(PacketContext context, PacketByteBuf buffer)
    {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack handStack = JavaUtils.find(playerEntity.getItemsHand(), itemStack -> itemStack.getItem() instanceof BankCheckbookItem);
        if (handStack == null)
            return;
        int toID = buffer.readInt();
        double amount = buffer.readDouble();
        ItemStack stack = new ItemStack(ItemRegistry.BANK_CHECK);
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
        if (playerEntity == null)
            return;
        MinecraftBank bank = MinecraftBank.getInstance();
        String accountName = packetByteBuf.readString(32767);
        int accountID = packetByteBuf.readInt();
        String accountCode = packetByteBuf.readString(32767);
        String accountOwnerName = packetByteBuf.readString(32767);
        MinecraftBankAccount newAccount = new MinecraftBankAccount(accountID, accountName, accountCode, accountOwnerName);
        MinecraftBankActionResult result = bank.createAccount(newAccount);
        result.send(playerEntity);
        if (result.isSuccess())
            ModLogger.LogInfo("Successfully created account '" + accountID + "'.");
        else
            ModLogger.LogInfo("Couldn't create account '" + accountID + "', " + result.getMessage());
    }

    public static void onReceiveAccountDeletion(PacketContext packetContext, PacketByteBuf packetByteBuf)
    {

    }
}
