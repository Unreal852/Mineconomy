package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.client.gui.CheckbookPrinterContainerScreen;
import fr.unreal852.mineconomy.common.container.CheckbookPrinterContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public final class ScreenRegistry
{
    public static void initialize()
    {
        ScreenProviderRegistry.INSTANCE.registerFactory(ContainerRegistry.CHECKBOOK_PRINTER_CONTAINER,
                (syncId, identifier, player, buf) ->
                {
                    BlockPos pos = buf.readBlockPos();
                    return new CheckbookPrinterContainerScreen(new LiteralText(""), new CheckbookPrinterContainer(syncId, player.inventory, pos), pos, player);
                });
    }
}
