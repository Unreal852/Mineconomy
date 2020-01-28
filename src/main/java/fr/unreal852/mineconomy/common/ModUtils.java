package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.registry.ItemGroupRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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

    public static Item createItem(ItemGroup group)
    {
        return new Item(new Item.Settings().group(group == null ? ItemGroupRegistry.MINECONOMY_GROUP : group));
    }
}
