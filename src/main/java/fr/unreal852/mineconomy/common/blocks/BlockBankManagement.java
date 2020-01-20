package fr.unreal852.mineconomy.common.blocks;

import fr.unreal852.mineconomy.common.ModEntryCommon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBankManagement extends Block
{
    public BlockBankManagement()
    {
        super(Block.Settings.copy(Blocks.STONE));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if(world.isClient)
            ModEntryCommon.PROXY.openBankManagementGUI();
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
