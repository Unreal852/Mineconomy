package fr.unreal852.mineconomy.common.block;

import fr.unreal852.mineconomy.common.ModEntryCommon;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BooleanBiFunction;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import java.util.stream.Stream;

public class BankManagementBlock extends Block
{
    private static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final VoxelShape        VOXEL_SHAPE_NORTH;
    private static final VoxelShape        VOXEL_SHAPE_SOUTH;
    private static final VoxelShape        VOXEL_SHAPE_WEST;
    private static final VoxelShape        VOXEL_SHAPE_EAST;

    public BankManagementBlock()
    {
        super(FabricBlockSettings.of(Material.STONE).build());
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if (world.isClient)
            ModEntryCommon.PROXY.openBankManagementGUI();
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ctx)
    {
        Direction dir = state.get(FACING);
        switch (dir)
        {
            case NORTH:
                return VOXEL_SHAPE_NORTH;
            case SOUTH:
                return VOXEL_SHAPE_SOUTH;
            case EAST:
                return VOXEL_SHAPE_EAST;
            case WEST:
                return VOXEL_SHAPE_WEST;
            default:
                return VoxelShapes.fullCube();
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPosition, EntityContext entityContext)
    {
        return getOutlineShape(blockState, blockView, blockPosition, entityContext);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext)
    {
        return getDefaultState().with(FACING, placementContext.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    static
    {
        VOXEL_SHAPE_SOUTH = Stream.of(
                Block.createCuboidShape(0, 0, 0, 16, 16, 8),
                Block.createCuboidShape(1, 12, 8, 15, 15, 9),
                Block.createCuboidShape(2, 2, 8, 14, 10, 9),
                Block.createCuboidShape(0, 0, 8, 16, 1, 16),
                Block.createCuboidShape(0, 12, 8, 1, 15, 10),
                Block.createCuboidShape(15, 12, 8, 16, 15, 10),
                Block.createCuboidShape(0, 15, 8, 16, 16, 10),
                Block.createCuboidShape(0, 11, 8, 16, 12, 10),
                Block.createCuboidShape(0, 1, 8, 1, 11, 9),
                Block.createCuboidShape(15, 1, 8, 16, 11, 9)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
        VOXEL_SHAPE_NORTH = Stream.of(
                Block.createCuboidShape(0, 0, 8, 16, 16, 16),
                Block.createCuboidShape(1, 12, 7, 15, 15, 8),
                Block.createCuboidShape(2, 2, 7, 14, 10, 8),
                Block.createCuboidShape(0, 0, 0, 16, 1, 8),
                Block.createCuboidShape(15, 12, 6, 16, 15, 8),
                Block.createCuboidShape(0, 12, 6, 1, 15, 8),
                Block.createCuboidShape(0, 15, 6, 16, 16, 8),
                Block.createCuboidShape(0, 11, 6, 16, 12, 8),
                Block.createCuboidShape(15, 1, 7, 16, 11, 8),
                Block.createCuboidShape(0, 1, 7, 1, 11, 8)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
        VOXEL_SHAPE_EAST = Stream.of(
                Block.createCuboidShape(0, 0, 0, 8, 16, 16),
                Block.createCuboidShape(8, 12, 1, 9, 15, 15),
                Block.createCuboidShape(8, 2, 2, 9, 10, 14),
                Block.createCuboidShape(8, 0, 0, 16, 1, 16),
                Block.createCuboidShape(8, 12, 15, 10, 15, 16),
                Block.createCuboidShape(8, 12, 0, 10, 15, 1),
                Block.createCuboidShape(8, 15, 0, 10, 16, 16),
                Block.createCuboidShape(8, 11, 0, 10, 12, 16),
                Block.createCuboidShape(8, 1, 15, 9, 11, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
        VOXEL_SHAPE_WEST = Stream.of(
                Block.createCuboidShape(8, 0, 0, 16, 16, 16),
                Block.createCuboidShape(7, 12, 1, 8, 15, 15),
                Block.createCuboidShape(7, 2, 2, 8, 10, 14),
                Block.createCuboidShape(0, 0, 0, 8, 1, 16),
                Block.createCuboidShape(6, 12, 0, 8, 15, 1),
                Block.createCuboidShape(6, 12, 15, 8, 15, 16),
                Block.createCuboidShape(6, 15, 0, 8, 16, 16),
                Block.createCuboidShape(6, 11, 0, 8, 12, 16),
                Block.createCuboidShape(7, 1, 0, 8, 11, 1),
                Block.createCuboidShape(7, 1, 15, 8, 11, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    }
}
