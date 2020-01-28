package fr.unreal852.mineconomy.common.block.entity;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.registry.EntityRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;

public class CheckbookPrinterBlockEntity extends BlockEntity implements Tickable
{
    public CheckbookPrinterBlockEntity()
    {
        super(EntityRegistry.CHECKBOOK_PRINTER);
    }


    @Override
    public void tick()
    {
        ModLogger.LogInfo("TICKIIIING");
    }
}
