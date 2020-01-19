package fr.unreal852.mineconomy.client;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.client.gui.BankCheckBookGUI;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.LiteralText;
import spinnery.client.BaseScreen;

public class ModEntryClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModLogger.LogInfo("Initializing Client Side...");
        ScreenProviderRegistry.INSTANCE.registerFactory(ModConstants.GUI_ID_BANK_CHECK,
                (syncId, identifier, player, buffer) -> new BaseScreen(new LiteralText(ModConstants.GUI_ID_BANK_CHECK.getPath()),
                        new BankCheckBookGUI(syncId, player.inventory, buffer.readItemStack()), player));
        ModLogger.LogInfo("Initialized Client Side !");
    }
}
