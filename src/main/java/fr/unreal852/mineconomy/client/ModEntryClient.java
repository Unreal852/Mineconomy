package fr.unreal852.mineconomy.client;

import fr.unreal852.mineconomy.ModConstants;
import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.client.gui.BankCheckGUI;
import fr.unreal852.mineconomy.common.ModEntryCommon;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import spinnery.client.BaseScreen;

public class ModEntryClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        ModLogger.LogInfo("Initializing Client Side...");
        ModEntryCommon.PROXY = new ModProxyClient();
        ModLogger.LogInfo("Initialized Client Side !");
    }
}
