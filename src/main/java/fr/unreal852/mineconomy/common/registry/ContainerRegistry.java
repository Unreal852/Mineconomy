package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModHelper;
import fr.unreal852.mineconomy.common.container.CheckbookPrinterContainer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.util.Identifier;

public final class ContainerRegistry
{
    public static final Identifier CHECKBOOK_PRINTER_CONTAINER = ModHelper.getIdentifier("checkbook_printer_container");

    public static void init()
    {
        ContainerProviderRegistry.INSTANCE.registerFactory(CHECKBOOK_PRINTER_CONTAINER, (syncId, id, player, buf) -> new CheckbookPrinterContainer(syncId, player.inventory, buf.readBlockPos()));
    }
}
