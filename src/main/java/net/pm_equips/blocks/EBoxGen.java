package net.pm_equips.blocks;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.pm_equips.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EBoxGen extends Block {
    public EBoxGen(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.0, 0.0, 0.0, 2.0, 1.0, 2.0);
    }

    // 🔥 2. 右クリック判定（インタラクション範囲）
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.box(0.0, 0.0, 0.0, 2.0, 1.0, 2.0);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof LivingEntity livingEntity) {
            // 即死させる
            livingEntity.hurt(level.damageSources().genericKill(), Float.MAX_VALUE);

            if (livingEntity.isDeadOrDying()) {
                // ドロップ個数を最大体力に基づいて決定（例: ceil(maxHealth / 20)）
                float maxHealth = livingEntity.getMaxHealth();
                int count = Math.max(1, (int) Math.ceil(maxHealth / 20.0f));

                ItemStack stack = new ItemStack(BlockInit.BlockItems.ZAYIN_PE_BOX.get(), count);
                Block.popResource(level, pos.below(), stack);
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}