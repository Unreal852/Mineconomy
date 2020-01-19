package fr.unreal852.mineconomy.server;

import fr.unreal852.mineconomy.ModLogger;
import fr.unreal852.mineconomy.common.proxy.IModProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;

@Environment(EnvType.SERVER)
public class ModProxyServer implements IModProxy
{
    public ModProxyServer()
    {
        ModLogger.LogInfo("Server Proxy Loaded");
    }

    @Override
    public void openCheckGUI(ItemStack stack)
    {

    }
}
