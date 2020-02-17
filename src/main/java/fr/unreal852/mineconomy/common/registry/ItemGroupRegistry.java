package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModLogger;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static fr.unreal852.mineconomy.common.ModHelper.getIdentifier;

public final class ItemGroupRegistry
{
    public static final ItemGroup MINECONOMY_GROUP = FabricItemGroupBuilder.build(getIdentifier("tab_mineconomy"), () -> new ItemStack(ItemRegistry.CREDIT_CARD));

    public static void init()
    {
        ModLogger.LogInfo("Registered Item Groups !");
    }
}
