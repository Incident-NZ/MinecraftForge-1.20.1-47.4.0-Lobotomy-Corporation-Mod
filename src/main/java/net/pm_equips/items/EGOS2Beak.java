package net.pm_equips.items;

import net.pm_equips.client.renderer.EGOS2BeakR;
import net.pm_equips.EntityInit;
import net.pm_equips.ItemInit;
import net.pm_equips.entity.PBullet;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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

import java.util.function.Consumer;

public class EGOS2Beak extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    // weapon-like behavior
    private static final int MAX_AMMO = 16;
    private static final int RELOAD_TICKS = 20; // 1 second
    private static final int COOLDOWN_TICKS = 10; // 0.5 second
    private static final float VELOCITY = 4.0f;

    public EGOS2Beak(ArmorMaterial material, Type type, Properties props) {
        super(material, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private EGOS2BeakR renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if (this.renderer == null)
                    this.renderer = new EGOS2BeakR();

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

    // =========================
    // Weapon behavior (semi-auto, reload)
    // =========================

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.getCooldowns().isOnCooldown(this)) {
            return InteractionResultHolder.fail(stack);
        }

        int ammo = stack.getOrCreateTag().getInt("Ammo");
        int reload = stack.getOrCreateTag().getInt("Reload");

        if (reload > 0) return InteractionResultHolder.fail(stack);

        if (ammo <= 0) {
            if (!level.isClientSide) {
                player.displayClientMessage(net.minecraft.network.chat.Component.literal("弾薬切れ / No Ammo"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(stack);
        }

        if (level.isClientSide) {
            level.playSound(player, player.blockPosition(), SoundEvents.CROSSBOW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
            return InteractionResultHolder.consume(stack);
        }

        // server-side: spawn projectile
        float damage = 2 + player.getRandom().nextInt(2); // 2..3

        PBullet bullet = new PBullet(EntityInit.BULLET.get(), level);
        bullet.setDamage(damage);
        bullet.setVelocity(VELOCITY);
        bullet.setMaxLifetime(32);
        // position and owner initialization
        bullet.setOwner(player);
        bullet.setPos(player.getEyePosition().x + player.getLookAngle().x * 0.5,
                player.getEyePosition().y + player.getLookAngle().y * 0.5,
                player.getEyePosition().z + player.getLookAngle().z * 0.5);

        level.addFreshEntity(bullet);

        stack.getOrCreateTag().putInt("Ammo", ammo - 1);

        player.awardStat(Stats.ITEM_USED.get(this));
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

        return InteractionResultHolder.success(stack);
    }

    // Rキーリロード開始（WeaponRolandRevolver と同様の方式）
    public void startReload(ItemStack stack, Player player) {
        int reload = stack.getOrCreateTag().getInt("Reload");
        if (reload > 0) return;

        int ammo = stack.getOrCreateTag().getInt("Ammo");
        if (ammo >= MAX_AMMO) return;

        boolean hasAmmo = false;
        for (ItemStack invStack : player.getInventory().items) {
            if (invStack.is(ItemInit.PISTOL_BULLET_AMMO.get())) {
                hasAmmo = true;
                break;
            }
        }

        if (!hasAmmo) return;

        stack.getOrCreateTag().putInt("Reload", RELOAD_TICKS);
        player.level().playSound(null, player.blockPosition(), SoundEvents.IRON_TRAPDOOR_CLOSE, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
        if (!stack.getOrCreateTag().contains("Ammo")) {
            stack.getOrCreateTag().putInt("Ammo", MAX_AMMO);
        }

        int reload = stack.getOrCreateTag().getInt("Reload");
        if (reload > 0) {
            reload--;
            stack.getOrCreateTag().putInt("Reload", reload);

            if (reload <= 0 && entity instanceof Player player) {
                int ammo = stack.getOrCreateTag().getInt("Ammo");
                int needed = MAX_AMMO - ammo;
                if (needed <= 0) return;

                int loaded = 0;
                for (ItemStack invStack : player.getInventory().items) {
                    if (invStack.is(ItemInit.PISTOL_BULLET_AMMO.get())) {
                        while (!invStack.isEmpty() && loaded < needed) {
                            invStack.shrink(1);
                            loaded++;
                        }
                    }
                    if (loaded >= needed) break;
                }

                stack.getOrCreateTag().putInt("Ammo", ammo + loaded);
            }
        }

        super.inventoryTick(stack, level, entity, slot, selected);
    }
}
