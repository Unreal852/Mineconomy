package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.client.gui.BankCheckGUI;
import fr.unreal852.mineconomy.client.gui.BankManagementGUI;
import fr.unreal852.mineconomy.client.gui.CheckbookPrinterContainerScreen;
import fr.unreal852.mineconomy.common.ModHelper;
import fr.unreal852.mineconomy.common.container.CheckbookPrinterContainer;
import fr.unreal852.ucorefabric.client.screen.ClientScreenRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public final class ScreenRegistry
{
    public static final Identifier BANK_CHECK_GUI      = ClientScreenRegistry.registerScreen(ModHelper.getIdentifier("gui:bank_check"), new BankCheckGUI());
    public static final Identifier BANK_MANAGEMENT_GUI = ClientScreenRegistry.registerScreen(ModHelper.getIdentifier("gui:bank_management"), new BankManagementGUI());

    public static void init()
    {
        ScreenProviderRegistry.INSTANCE.registerFactory(ContainerRegistry.CHECKBOOK_PRINTER_CONTAINER,
                (syncId, identifier, player, buf) ->
                {
                    BlockPos pos = buf.readBlockPos();
                    return new CheckbookPrinterContainerScreen(new LiteralText(""), new CheckbookPrinterContainer(syncId, player.inventory, pos), pos, player);
                });
    }
}
