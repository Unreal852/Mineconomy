package fr.unreal852.mineconomy.client.gui;

import spinnery.widget.*;

public final class GUIHelper
{
    public static WDynamicText createDynamicText(WInterface wInterface, int x, int y, int width, int height)
    {
        return new WDynamicText(WAnchor.GL_ORIGIN, x, y, 0, width, height, wInterface);
    }

    public static WStaticText createStaticText(WInterface wInterface, int x, int y, String text)
    {
        return new WStaticText(WAnchor.GL_ORIGIN, x, y, 0, text, wInterface);
    }

    public static WButton createButtonText(WInterface wInterface, int x, int y, int width, int height)
    {
        return new WButton(WAnchor.GL_ORIGIN, x, y, 0, width, height, wInterface);
    }
}
