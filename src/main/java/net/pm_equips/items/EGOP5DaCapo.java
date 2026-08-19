package net.pm_equips.items;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.pm_equips.client.renderer.EGOS5DaCapoR;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.function.Consumer;

public class EGOP5DaCapo extends CorePageItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public EGOP5DaCapo(ArmorMaterial mat, Type type, Properties props) {
        super(mat, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private EGOS5DaCapoR renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity lEnt, ItemStack stack, EquipmentSlot equip, HumanoidModel<?> og) {

                if (this.renderer == null)
                    this.renderer = new EGOS5DaCapoR();

                this.renderer.prepForRender(lEnt, stack, equip, og);
                return this.renderer;
            }
        });
    }

    private PlayState predicate(AnimationState state) {
        state.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
