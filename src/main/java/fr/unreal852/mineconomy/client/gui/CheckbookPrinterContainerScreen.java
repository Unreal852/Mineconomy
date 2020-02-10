package fr.unreal852.mineconomy.client.gui;

import fr.unreal852.mineconomy.common.container.CheckbookPrinterContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import spinnery.common.BaseContainer;
import spinnery.common.BaseContainerScreen;
import spinnery.widget.*;

@Environment(EnvType.CLIENT)
public class CheckbookPrinterContainerScreen extends BaseContainerScreen<CheckbookPrinterContainer>
{
    public CheckbookPrinterContainerScreen(Text name, CheckbookPrinterContainer linkedContainer, BlockPos blockPos, PlayerEntity player)
    {
        super(name, linkedContainer, player);

        WInterface mainInterface = new WInterface(GUIHelper.getPosition(0, 0), WSize.of(180, 100), linkedContainer);
        mainInterface.center();
        mainInterface.setLabel(new TranslatableText("gui.mineconomy.checkbook_printer"));

        getHolder().add(mainInterface);

        //WSlot.addPlayerInventory(WSize.of(18,18), mainInterface, BaseContainer.PLAYER_INVENTORY);
        WSlot fuelSlot = new WSlot(GUIHelper.getPosition(10, 24, mainInterface), WSize.of(16, 16), mainInterface, 0, CheckbookPrinterContainer.CHECKBOOK_PRINTER_INVENTORY);
        WSlot resultSlot = new WSlot(GUIHelper.getPosition(fuelSlot.getWidth() + 10, 0, fuelSlot), WSize.of(16, 16), mainInterface, 2, CheckbookPrinterContainer.CHECKBOOK_PRINTER_INVENTORY);

        mainInterface.add(fuelSlot, resultSlot);

        WSlot.addArray(GUIHelper.getPosition(4, 50 + (y * 18 + 24 + 4 + 54), mainInterface), WSize.of(18, 18), mainInterface, 0, BaseContainer.PLAYER_INVENTORY, 9, 1);
        WSlot.addArray(GUIHelper.getPosition(4, 50 + (y * 18 + 24), mainInterface), WSize.of(18, 18), mainInterface, 9, BaseContainer.PLAYER_INVENTORY, 9, 3);

        GUIHelper.setTheme("spinnery:dark", mainInterface);
        GUIHelper.setTheme("spinnery:dark", mainInterface.getAllWidgets());
    }
}
