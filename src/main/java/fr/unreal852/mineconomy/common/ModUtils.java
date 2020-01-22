package fr.unreal852.mineconomy.common;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

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

    public static ItemStack findItemStack(Iterable<ItemStack> itemStacks, Predicate<ItemStack> itemStackPredicate)
    {
        if(itemStacks == null)
            return null;
        for(ItemStack stack : itemStacks)
        {
            if(itemStackPredicate.test(stack))
                return stack;
        }
        return null;
    }
}
