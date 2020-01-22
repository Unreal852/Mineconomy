package fr.unreal852.mineconomy.common;

import fr.unreal852.mineconomy.common.items.EconomyItems;
import fr.unreal852.mineconomy.common.test.IModRegistrable;
import fr.unreal852.mineconomy.common.test.ModItemsRegistrable;
import fr.unreal852.mineconomy.common.test.ModRegistry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.io.File;

public final class ModConstants
{
    public static final String                       MOD_ID                        = "mineconomy";
    public static final String                       MOD_LOGGER_ID                 = "Mineconomy";
    public static final File                         CONFIG_FILES_DIRECTORY        = new File(FabricLoader.getInstance().getConfigDirectory(), "Mineconomy");
    public static final File                         BANK_FILES_DIRECTORY          = new File(CONFIG_FILES_DIRECTORY, "Bank");
    public static final File                         BANK_ACCOUNTS_FILES_DIRECTORY = new File(BANK_FILES_DIRECTORY, "Accounts");
    public static final ItemGroup                    MOD_ITEM_GROUP                = FabricItemGroupBuilder.build(ModUtils.getIdentifier("tab_mineconomy"), () -> new ItemStack(EconomyItems.CREDIT_CARD.getItem()));
    public static final Identifier                   PACKET_BANK_CHECK_VALIDATION  = ModUtils.getIdentifier("pck_bank_check_validation");
    public static final Identifier                   PACKET_BANK_ACCOUNT_CREATION  = ModUtils.getIdentifier("pck_bank_account_creation");
    public static final IModRegistrable<ModRegistry> REGISTRY_ITEMS                = new ModItemsRegistrable();
    public static final IModRegistrable<ModRegistry> REGISTRY_BLOCKS               = new ModItemsRegistrable();
    public static final IModRegistrable<ModRegistry> REGISTRY_SERVER_PACKETS       = new ModItemsRegistrable();
    public static final IModRegistrable<ModRegistry> REGISTRY_CLIENT_PACKETS       = new ModItemsRegistrable();
}
