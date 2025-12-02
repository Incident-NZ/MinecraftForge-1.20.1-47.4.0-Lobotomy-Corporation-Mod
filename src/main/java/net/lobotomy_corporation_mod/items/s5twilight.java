package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.client.renderer.s5r_twilight;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.function.Consumer;

public class s5twilight extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public s5twilight(ArmorMaterial material, Type type, Properties props) {
        super(material, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
        private s5r_twilight renderer;

        @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

            if (this.renderer == null)
                this.renderer = new s5r_twilight();

            this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
            return this.renderer;
            }
        });
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this,"controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide) {
            // 全セット装備チェック
            boolean fullSet = hasFullSet(player);

            if (fullSet) {
                // 攻撃力上昇（HP減少分に比例）
                float missingHp = player.getMaxHealth() - player.getHealth();
                if (missingHp > 0) {
                    int amplifier = (int) Math.floor(missingHp / 4); // 4HP減少ごとに+1段階
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 40, amplifier, true, false, true));
                }

                // 周囲のモブに5秒ごとに6ダメージ
                if (player.tickCount % 100 == 0) { // 100tick = 5秒
                    List<LivingEntity> nearby = level.getEntitiesOfClass(
                            LivingEntity.class,
                            player.getBoundingBox().inflate(5.0D),
                            e -> e != player
                    );
                    for (LivingEntity target : nearby) {
                        target.invulnerableTime = 0;
                        target.hurt(level.damageSources().magic(), 6.0F);
                    }
                }
            }
        }
    }

    private boolean hasFullSet(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof s5twilight &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof s5twilight &&
                player.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof W5Twilight;
    }
}
