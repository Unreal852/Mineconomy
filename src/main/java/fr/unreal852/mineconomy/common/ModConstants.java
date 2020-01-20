package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.items.EconomyItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public final class ModConstants
{
    public static final String     MOD_ID                = "mineconomy";
    public static final String     MOD_LOGGER_ID         = "Mineconomy";
    public static final ItemGroup  MOD_ITEM_GROUP        = FabricItemGroupBuilder.build(ModUtils.getIdentifier("tab_mineconomy"), () -> new ItemStack(EconomyItems.CREDIT_CARD.getItem()));
    public static final Identifier PACKET_BANK_CHECK_VALIDATION = ModUtils.getIdentifier("pck_bank_check_validation");
    public static final Identifier PACKET_BANK_ACCOUNT_CREATION = ModUtils.getIdentifier("pck_bank_account_creation");
}
