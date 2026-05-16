package net.pm_equips.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.pm_equips.blocks.EBoxGen;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class EBoxGenBlockEntity extends BlockEntity implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean lastOpenState = false;

    public EBoxGenBlockEntity(BlockPos pos, BlockState state) {
        super(net.pm_equips.BlockEntityInit.EBOX_GEN.get(), pos, state);
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







