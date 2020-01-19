package fr.unreal852.mineconomy.server;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.common.items.EconomyItems;
import fr.unreal852.mineconomy.common.items.ItemBankCheck;
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
        //ServerSidePacketRegistry.INSTANCE.register(EconomyPackets.BANK_CHECK_VALIDATION, this::onReceiveBankCheckValidation);
       /* ContainerProviderRegistry.INSTANCE.registerFactory(ModConstants.GUI_ID_BANK_CHECK, (syncId, id, player, buffer) -> {
            return null;
        }); */
        ModLogger.LogInfo("Initialized Server Side !");
    }

    //This should be moved, only for testing
    public static void onReceiveBankCheckValidation(PacketContext context, PacketByteBuf buffer)
    {
        PlayerEntity playerEntity = context.getPlayer();
        if(!(playerEntity.getMainHandStack().getItem() instanceof ItemBankCheckbook) && !(playerEntity.getOffHandStack().getItem() instanceof ItemBankCheckbook))
            return;
        String from = buffer.readString(32767);
        String to = buffer.readString(32767);
        String amount = buffer.readString(32767);
        context.getTaskQueue().execute(() ->
        {
            ItemStack stack = EconomyItems.BANK_CHECK.getNewItemStack();
            stack.setCustomName(new LiteralText("Bank Check (" + playerEntity.getDisplayName().asString() + ")"));
            stack.getOrCreateTag().putString(ModConstants.TAG_ITEM_BANK_CHECK_FILLED, "filled");
            stack.getOrCreateTag().putString("bankFrom", from);
            stack.getOrCreateTag().putString("bankTo", to);
            stack.getOrCreateTag().putString("bankAmount", amount);
            playerEntity.inventory.insertStack(stack);
            playerEntity.inventory.removeOne(playerEntity.getMainHandStack());
        });
    }
}
