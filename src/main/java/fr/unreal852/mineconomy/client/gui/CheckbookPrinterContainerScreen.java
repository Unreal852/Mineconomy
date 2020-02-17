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
import spinnery.widget.api.Position;
import spinnery.widget.api.Size;
import static fr.unreal852.mineconomy.client.gui.GUIHelper.*;

@Environment(EnvType.CLIENT)
public class CheckbookPrinterContainerScreen extends BaseContainerScreen<CheckbookPrinterContainer>
{
    public CheckbookPrinterContainerScreen(Text name, CheckbookPrinterContainer linkedContainer, BlockPos blockPos, PlayerEntity player)
    {
        super(name, linkedContainer, player);

        WInterface mainInterface = getInterface();
        WPanel mainPanel = mainInterface.createChild(WPanel.class, Position.of(mainInterface, 0, 0), Size.of(180, 100)).setLabel(new TranslatableText("gui.mineconomy.checkbook_printer"));
        mainPanel.center();

        WSlot fuelSlot = mainInterface.createChild(WSlot.class, Position.of(mainPanel, 10, 24), Size.of(16,16)).setSlotNumber(0).setInventoryNumber(CheckbookPrinterContainer.CHECKBOOK_PRINTER_INVENTORY);
        WSlot resultSlot = mainInterface.createChild(WSlot.class, Position.of(fuelSlot, fuelSlot.getWidth() + 10, 0), Size.of(16,16)).setSlotNumber(2).setInventoryNumber(CheckbookPrinterContainer.CHECKBOOK_PRINTER_INVENTORY);

        WSlot.addPlayerInventory(Position.of(mainInterface, 4, 50), Size.of(18,18), mainInterface);

        mainInterface.setTheme("spinnery:dark");
    }
}
