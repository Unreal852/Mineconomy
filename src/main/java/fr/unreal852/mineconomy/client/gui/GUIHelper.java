package fr.unreal852.mineconomy.client.gui;

import spinnery.widget.*;

public final class GUIHelper
{
    public static WDynamicText createDynamicText(WPanel panel, int x, int y, int width, int height)
    {
        return new WDynamicText(WAnchor.GL_ORIGIN, x, y, 0, width, height, panel);
    }

    public static WStaticText createStaticText(WPanel panel, int x, int y, String text)
    {
        return new WStaticText(WAnchor.GL_ORIGIN, x, y, 0, text, panel);
    }

    public static WButton createButtonText(WPanel panel, int x, int y, int width, int height)
    {
        return new WButton(WAnchor.GL_ORIGIN, x, y, 0, width, height, panel);
    }
}
