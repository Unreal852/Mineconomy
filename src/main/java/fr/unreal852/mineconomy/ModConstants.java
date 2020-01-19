package fr.unreal852.mineconomy;

import fr.unreal852.mineconomy.common.items.EconomyItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class ModConstants
{
    public static final String     MOD_ID                     = "mineconomy";
    public static final String     MOD_LOGGER_ID              = "Mineconomy";
    public static final ItemGroup  MOD_ITEM_GROUP             = FabricItemGroupBuilder.build(ModUtils.getIdentifier("tab_mineconomy"), () -> new ItemStack(EconomyItems.BANK_CHECKBOOK.getItem()));
}
