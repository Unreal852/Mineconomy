package fr.unreal852.mineconomy;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public final class ModUtils
{
    public static Identifier getIdentifier(String string)
    {
        return new Identifier(ModConstants.MOD_ID, string);
    }

    public static int getLargestString(String... values)
    {
        if(values == null || values.length == 0)
            return 0;
        int last = 0;
        for (String value : values)
        {
            int width = MinecraftClient.getInstance().textRenderer.getStringWidth(value);
            if (width <= last)
                continue;
            last = width;
        }
        return last;
    }
}
