package fr.unreal852.mineconomy.client;

import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.client.gui.BankCheckGUI;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class ModProxyClient implements IModProxy
{
    public ModProxyClient()
    {
        ModLogger.LogInfo("Client Proxy Loaded");
    }

    @Override
    public void openCheckGUI(ItemStack stack)
    {
        MinecraftClient.getInstance().openScreen(new BankCheckGUI(stack));
    }
}
