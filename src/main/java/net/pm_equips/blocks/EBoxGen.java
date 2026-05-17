package net.pm_equips.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
 import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.RenderShape;

public class EBoxGen extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;

    public EBoxGen(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(OPEN, Boolean.FALSE));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
    }

    @SuppressWarnings({"unchecked"})
    protected void createBlockStateDefinition(StateDefinition.Builder builder) {
        builder.add(FACING, OPEN);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        // Use block entity renderer (GeoBlockRenderer) exclusively so the block model
        // from blockstates doesn't double-render and cause lighting/alpha issues.
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);

        var be = level.getBlockEntity(pos);
        if (be instanceof net.pm_equips.blockentity.EBoxGenBlockEntity gen) {
            gen.toggleOpen(player);
            return InteractionResult.sidedSuccess(false);
        }

        return InteractionResult.sidedSuccess(false);
    }

    public boolean hasBlockEntity(BlockState state) {
        return true;
    }

    @Override
    public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new net.pm_equips.blockentity.EBoxGenBlockEntity(pos, state);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level.isClientSide) {
            super.entityInside(state, level, pos, entity);
            return;
        }

        var be = level.getBlockEntity(pos);
        if (be instanceof net.pm_equips.blockentity.EBoxGenBlockEntity gen) {
            gen.acceptEntity(entity);
            return;
        }

        super.entityInside(state, level, pos, entity);
    }
}