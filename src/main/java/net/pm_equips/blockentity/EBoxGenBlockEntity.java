package net.pm_equips.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.pm_equips.BlockEntityInit;
import net.pm_equips.BlockInit;
import net.pm_equips.blocks.EBoxGen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class EBoxGenBlockEntity extends BlockEntity implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean lastOpenState = false;

    public EBoxGenBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EBOX_GEN.get(), pos, state);
    }

    // Toggle open/close. Called from block use()
    public void toggleOpen(Player player) {
        if (this.level == null || this.level.isClientSide) return;
        BlockState bs = this.level.getBlockState(this.getBlockPos());
        boolean open = bs.getValue(EBoxGen.OPEN);
        this.level.setBlock(this.getBlockPos(), bs.setValue(EBoxGen.OPEN, !open), 3);
    }

    // Called when an entity enters the block's space while open
    public void acceptEntity(Entity entity) {
        if (this.level == null || this.level.isClientSide) return;
        BlockState bs = this.level.getBlockState(this.getBlockPos());
        boolean open = bs.getValue(EBoxGen.OPEN);
        if (!open) return;

        // Destroy or kill entity as previous logic
        if (entity instanceof ItemEntity itemEntity) {
            itemEntity.discard();
        } else if (entity instanceof LivingEntity living) {
            living.hurt(this.level.damageSources().genericKill(), Float.MAX_VALUE);
        } else {
            entity.discard();
        }

        // Close and drop
        closeAndDrop();
    }

    private void closeAndDrop() {
        if (!(this.level instanceof ServerLevel server)) return;

        BlockState bs = server.getBlockState(this.getBlockPos());
        // close
        server.setBlock(this.getBlockPos(), bs.setValue(EBoxGen.OPEN, Boolean.FALSE), 3);

        int count = 1 + server.random.nextInt(10);
        ItemStack stack = new ItemStack(BlockInit.BlockItems.ZAYIN_PE_BOX.get(), count);

        Direction facing = bs.getValue(EBoxGen.FACING);
        BlockPos dropPos = this.getBlockPos().relative(facing.getOpposite());
        if (!server.getBlockState(dropPos).isAir()) dropPos = this.getBlockPos().below();

        Block.popResource(server, dropPos, stack);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<EBoxGenBlockEntity> state) {
        boolean open = false;
        if (this.level != null) {
            BlockState bs = this.level.getBlockState(this.getBlockPos());
            if (bs.getBlock() instanceof EBoxGen) {
                open = bs.getValue(EBoxGen.OPEN);
            }
        }

        // トランジション検出: 開いた瞬間に open アニメーションを再生
        if (open && !lastOpenState) {
            state.getController().setAnimation(RawAnimation.begin().then("open", Animation.LoopType.PLAY_ONCE));
            lastOpenState = true;
            return PlayState.CONTINUE;
        }

        // 閉じたら idle に戻す
        if (!open && lastOpenState) {
            state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            lastOpenState = false;
            return PlayState.CONTINUE;
        }

        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}







