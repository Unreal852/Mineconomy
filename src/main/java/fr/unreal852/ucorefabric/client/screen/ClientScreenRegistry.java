package fr.unreal852.ucorefabric.client.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public final class ClientScreenRegistry
{
    private static Map<Identifier, Screen> s_registeredScreens = new HashMap<>();

    public static <T extends Screen & ICachedScreen> Identifier registerScreen(Identifier identifier, T screen)
    {
        s_registeredScreens.put(identifier, screen);
        return identifier;
    }

    public static void openScreen(Identifier identifier, Object... params)
    {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if(player == null || !s_registeredScreens.containsKey(identifier))
            return;
        ((ICachedScreen)s_registeredScreens.get(identifier)).open(params);
    }
}
