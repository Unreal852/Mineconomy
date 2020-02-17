package fr.unreal852.ucorefabric.client.util;

import net.minecraft.client.MinecraftClient;

public final class ClientUtils
{
    public static void closeScreen()
    {
        if (MinecraftClient.getInstance().player != null)
            MinecraftClient.getInstance().player.closeScreen();
    }

    public static void closeContainer()
    {
        if (MinecraftClient.getInstance().player != null)
            MinecraftClient.getInstance().player.closeContainer();
    }
}
