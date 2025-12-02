package net.lobotomy_corporation_mod.blocks;

import net.lobotomy_corporation_mod.BlockInit;
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
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.hurt(level.damageSources().genericKill(), Float.MAX_VALUE);

            if (livingEntity.isDeadOrDying()) {
                livingEntity.spawnAtLocation(BlockInit.BlockItems.ZAYIN_PE_BOX.get());
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
