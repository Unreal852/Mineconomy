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
    public static final String     TAG_ITEM_BANK_CHECK_FILLED = "bankCheckFilled";
    public static final ItemGroup  MOD_ITEM_GROUP             = FabricItemGroupBuilder.build(ModUtils.getIdentifier("tab_mineconomy"), () -> new ItemStack(EconomyItems.MONEY_BANKNOTE_ONE_HUNDRED.getItem()));
    public static final Identifier GUI_ID_BANK_CHECK          = ModUtils.getIdentifier("sync_gui_bank_check_id");
}
