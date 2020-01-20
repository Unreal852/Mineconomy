package fr.unreal852.mineconomy.common;

import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

public final class ModUtils
{
    public static Identifier getIdentifier(String string)
    {
        return new Identifier(ModConstants.MOD_ID, string);
    }

    public static int randInt(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
