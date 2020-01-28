package fr.unreal852.mineconomy.common.block;

import fr.unreal852.mineconomy.common.block.entity.CheckbookPrinterBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class CheckbookPrinterBlock extends Block implements BlockEntityProvider
{
    private CheckbookPrinterBlockEntity m_entity;

    public CheckbookPrinterBlock()
    {
        super(FabricBlockSettings.of(Material.STONE).build());

    }

    @Override
    public BlockEntity createBlockEntity(BlockView view)
    {
        m_entity = new CheckbookPrinterBlockEntity();
        return m_entity;
    }
}
