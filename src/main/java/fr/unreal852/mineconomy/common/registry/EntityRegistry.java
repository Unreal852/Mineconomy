package fr.unreal852.mineconomy.common.registry;

import fr.unreal852.mineconomy.common.ModLogger;
import fr.unreal852.mineconomy.common.block.entity.CheckbookPrinterBlockEntity;
import net.minecraft.block.entity.BlockEntityType;

import static fr.unreal852.ucorefabric.common.registry.ModRegistry.*;
import static fr.unreal852.mineconomy.common.ModHelper.getIdentifier;

public final class EntityRegistry
{
    public static BlockEntityType<CheckbookPrinterBlockEntity> CHECKBOOK_PRINTER = registerBlockEntity(getIdentifier("checkbook_printer"), CheckbookPrinterBlockEntity::new, BlockRegistry.CHECKBOOK_PRINTER);

    public static void init()
    {
        ModLogger.LogInfo("Registered Entities !");
    }
}
