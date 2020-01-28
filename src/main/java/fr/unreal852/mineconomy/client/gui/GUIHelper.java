package fr.unreal852.mineconomy.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import spinnery.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Environment(EnvType.CLIENT)
public final class GUIHelper
{
    public static void setTheme(String themeName, Collection<WWidget> widgets)
    {
        if(widgets == null || widgets.size() == 0)
            return;
        for(WWidget widget : widgets)
            widget.setTheme(themeName);
    }

    public static void setTheme(String themeName, WWidget... widgets)
    {
        setTheme(themeName, Arrays.asList(widgets));
    }

    public static WPosition getPosition(int x, int y, WWidget anchor)
    {
        return WPosition.of(WType.ANCHORED, x, y, 0, anchor);
    }

    public static WPosition getPosition(int x, int y)
    {
        return WPosition.of(WType.FREE, x, y, 0);
    }

    public static int getStringWidth(String value)
    {
        return MinecraftClient.getInstance().textRenderer.getStringWidth(value);
    }

    public static int getLargestString(String... values)
    {
        if (values == null || values.length == 0)
            return 0;
        int last = 0;
        for (String value : values)
        {
            int width = getStringWidth(value);
            if (width <= last)
                continue;
            last = width;
        }
        return last;
    }
}
