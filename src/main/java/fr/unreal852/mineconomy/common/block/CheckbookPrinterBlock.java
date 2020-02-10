package fr.unreal852.mineconomy.common.block;

import fr.unreal852.mineconomy.common.block.entity.CheckbookPrinterBlockEntity;
import fr.unreal852.mineconomy.common.registry.ContainerRegistry;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CheckbookPrinterBlock extends Block implements BlockEntityProvider
{
    private CheckbookPrinterBlockEntity m_entity;

    public CheckbookPrinterBlock()
    {
        super(FabricBlockSettings.of(Material.STONE).build());
    }

    public CheckbookPrinterBlockEntity getBlockEntity()
    {
        return m_entity;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view)
    {
        m_entity = new CheckbookPrinterBlockEntity();
        return m_entity;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (!world.isClient)
            ContainerProviderRegistry.INSTANCE.openContainer(ContainerRegistry.CHECKBOOK_PRINTER_CONTAINER, player, (buffer) -> buffer.writeBlockPos(pos));
        return ActionResult.SUCCESS;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!world.isClient)
        {
            for (int i = 0; i < m_entity.getInventory().getInvSize(); ++i)
                Block.dropStack(world, pos, m_entity.getInventory().getInvStack(i).copy());
        }
        super.onBreak(world, pos, state, player);
    }
}
