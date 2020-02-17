package fr.unreal852.mineconomy.common.proxy;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public interface IModProxy
{
    default void openScreen(Identifier identifier, Object... params)
    {

    }
}
