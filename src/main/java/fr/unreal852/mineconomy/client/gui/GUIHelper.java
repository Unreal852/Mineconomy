package fr.unreal852.mineconomy.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import spinnery.widget.*;

public final class GUIHelper
{
    public static WDynamicText createDynamicText(WInterface wInterface, int x, int y, int width, int height)
    {
        return new WDynamicText(WAnchor.GL_ORIGIN, x, y, 0, width, height, wInterface);
    }

    public static WStaticText createStaticText(WInterface wInterface, int x, int y, Text text)
    {
        return new WStaticText(WAnchor.GL_ORIGIN, x, y, 0, text, wInterface);
    }

    public static WButton createButton(WInterface wInterface, int x, int y, int width, int height)
    {
        return new WButton(WAnchor.GL_ORIGIN, x, y, 0, width, height, wInterface);
    }

    public static WToggle createToggle(WInterface wInterface, int x, int y, int width, int height)
    {
        return new WToggle(WAnchor.GL_ORIGIN, x, y, 0, width, height, wInterface);
    }

    public static WHorizontalSlider createHorizontalSlider(WInterface wInterface, int limit, int x, int y, int width, int height)
    {
        return new WHorizontalSlider(WAnchor.GL_ORIGIN, x, y, 0, width, height, limit, wInterface);
    }

    public static WTabHolder createTabHolder(WInterface wInterface, int x, int y, int width, int height)
    {
        return new WTabHolder(WAnchor.GL_ORIGIN, x, y, 0, width, height, wInterface);
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
