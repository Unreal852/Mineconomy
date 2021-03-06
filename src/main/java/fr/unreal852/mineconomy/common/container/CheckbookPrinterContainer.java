package fr.unreal852.mineconomy.common.container;

import fr.unreal852.mineconomy.common.block.entity.CheckbookPrinterBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import spinnery.common.BaseContainer;
import spinnery.widget.WInterface;
import spinnery.widget.WSlot;

public class CheckbookPrinterContainer extends BaseContainer
{
    public static final int CHECKBOOK_PRINTER_INVENTORY = 1;

    public CheckbookPrinterContainer(int synchronizationID, PlayerInventory linkedPlayerInventory, BlockPos blockPos)
    {
        super(synchronizationID, linkedPlayerInventory);

        BlockEntity blockEntity = getWorld().getBlockEntity(blockPos);
        if (!(blockEntity instanceof CheckbookPrinterBlockEntity))
            return;
        CheckbookPrinterBlockEntity printerBlockEntity = (CheckbookPrinterBlockEntity) blockEntity;
        WInterface mainInterface = getInterface();

        getInventories().put(CHECKBOOK_PRINTER_INVENTORY, printerBlockEntity.getInventory());

        printerBlockEntity.getInventory().addListener(this::onContentChanged);

        mainInterface.createChild(WSlot::new).setSlotNumber(0).setInventoryNumber(CHECKBOOK_PRINTER_INVENTORY);
        mainInterface.createChild(WSlot::new).setSlotNumber(1).setInventoryNumber(CHECKBOOK_PRINTER_INVENTORY);
        WSlot.addHeadlessPlayerInventory(mainInterface);
    }
}
