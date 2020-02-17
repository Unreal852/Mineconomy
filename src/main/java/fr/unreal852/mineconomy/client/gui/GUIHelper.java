package fr.unreal852.mineconomy.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import spinnery.widget.*;
import spinnery.widget.api.Position;
import spinnery.widget.api.WPositioned;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Environment(EnvType.CLIENT)
public final class GUIHelper
{
    public static void setTheme(String themeName, Collection<WAbstractWidget> widgets)
    {
        if(widgets == null || widgets.size() == 0)
            return;
        for(WAbstractWidget widget : widgets)
            widget.setTheme(new Identifier(themeName));
    }

    public static void setTheme(String themeName, WAbstractWidget... widgets)
    {
        setTheme(themeName, Arrays.asList(widgets));
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
