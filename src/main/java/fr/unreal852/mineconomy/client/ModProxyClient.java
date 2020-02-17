package fr.unreal852.mineconomy.client;

import fr.unreal852.mineconomy.client.gui.BankManagementGUI;
import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.client.gui.BankCheckGUI;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import fr.unreal852.ucorefabric.client.screen.ClientScreenRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModProxyClient implements IModProxy
{
    public ModProxyClient()
    {
        ModLogger.LogInfo("Client Proxy Loaded");
    }

    @Override
    public void openScreen(Identifier identifier, Object... params)
    {
        ClientScreenRegistry.openScreen(identifier, params);
    }
}
