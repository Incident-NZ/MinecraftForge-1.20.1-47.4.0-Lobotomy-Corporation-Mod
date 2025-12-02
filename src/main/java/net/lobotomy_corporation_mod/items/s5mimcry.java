package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.client.renderer.s5r_mimicry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.function.Consumer;

public class s5mimcry extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public s5mimcry(ArmorMaterial mat, Type type, Properties props) {
        super(mat, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private s5r_mimicry renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity lEnt, ItemStack stack, EquipmentSlot equip, HumanoidModel<?> og) {

                if (this.renderer == null)
                    this.renderer = new s5r_mimicry();

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
    /** フルセット装備チェック */
    public static boolean hasFullSet(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof s5mimcry &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof s5mimcry;
    }
}