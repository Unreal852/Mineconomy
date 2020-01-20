package fr.unreal852.mineconomy.server;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.ModEntryCommon;
import fr.unreal852.mineconomy.common.items.EconomyItems;
import fr.unreal852.mineconomy.common.items.ItemBankCheckbook;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.PacketByteBuf;

public class ModEntryServer implements DedicatedServerModInitializer
{
    @Override
    public void onInitializeServer()
    {
        ModLogger.LogInfo("Initializing Server Side...");
        ModEntryCommon.PROXY = new ModProxyServer();
        ModLogger.LogInfo("Initialized Server Side !");
    }

    //This should be moved, only for testing
    public static void onReceiveBankCheckValidation(PacketContext context, PacketByteBuf buffer)
    {
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack handStack = null;
        for (ItemStack stack : playerEntity.getItemsHand())
        {
            if (!(stack.getItem() instanceof ItemBankCheckbook))
                continue;
            handStack = stack;
        }
        if (handStack == null)
            return;
        String from = buffer.readString(32767);
        String to = buffer.readString(32767);
        String amount = buffer.readString(32767);
        ItemStack stack = EconomyItems.BANK_CHECK.getNewItemStack();
        stack.setCustomName(new LiteralText("Bank Check (" + playerEntity.getDisplayName().asString() + ")"));
        stack.getOrCreateTag().putString("bankFromName", playerEntity.getDisplayName().asString());
        stack.getOrCreateTag().putString("bankFrom", from);
        stack.getOrCreateTag().putString("bankTo", to);
        stack.getOrCreateTag().putString("bankAmount", amount);
        handStack.decrement(1);
        playerEntity.inventory.insertStack(stack);
    }
}
