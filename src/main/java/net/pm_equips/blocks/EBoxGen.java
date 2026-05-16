package net.pm_equips.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pm_equips.BlockInit;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.server.level.ServerLevel;
// removed unused import

public class EBoxGen extends Block {

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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.sidedSuccess(true);

        boolean open = state.getValue(OPEN);
        // トグルで蓋を開閉
        level.setBlock(pos, state.setValue(OPEN, !open), 3);
        return InteractionResult.sidedSuccess(false);
    }

    public boolean hasBlockEntity(BlockState state) {
        return true;
    }

    public net.minecraft.world.level.block.entity.BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new net.pm_equips.blockentity.EBoxGenBlockEntity(pos, state);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (level.isClientSide) {
            super.entityInside(state, level, pos, entity);
            return;
        }

        boolean open = state.getValue(OPEN);
        if (!open) {
            super.entityInside(state, level, pos, entity);
            return;
        }

        // ブロックが開いている状態で中に入れられたものを処理
        if (entity instanceof ItemEntity itemEntity) {
            // アイテムを破壊してドロップ
            itemEntity.discard();
            handleContentsAccepted((ServerLevel) level, pos, state);
        } else if (entity instanceof LivingEntity living) {
            // 生き物を破壊（即死）
            living.hurt(level.damageSources().genericKill(), Float.MAX_VALUE);
            // 生き物が死んだかどうかに関わらず、蓋を閉めてドロップ処理
            handleContentsAccepted((ServerLevel) level, pos, state);
        } else {
            // その他のエンティティも同様に扱う
            entity.discard();
            handleContentsAccepted((ServerLevel) level, pos, state);
        }

        super.entityInside(state, level, pos, entity);
    }

    private void handleContentsAccepted(ServerLevel level, BlockPos pos, BlockState state) {
        // 蓋を閉める
        level.setBlock(pos, state.setValue(OPEN, Boolean.FALSE), 3);

        // ドロップ個数 1..10 をランダム
        int count = 1 + level.random.nextInt(10);
        ItemStack stack = new ItemStack(BlockInit.BlockItems.ZAYIN_PE_BOX.get(), count);

        // ドロップ位置はブロックの背面（FACING の反対方向）
        Direction facing = state.getValue(FACING);
        BlockPos dropPos = pos.relative(facing.getOpposite());

        // 安全にドロップ（周囲の空間がない場合は pos.below に落とす）
        if (!level.getBlockState(dropPos).isAir()) {
            dropPos = pos.below();
        }

        Block.popResource(level, dropPos, stack);
    }
}