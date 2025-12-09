package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.entity.MagicBulletEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class W4MagicBullet extends ProjectileWeaponItem {

    private static final float DAMAGE = 24.0F;
    private static final float VELOCITY = 16.0F;
    private static final int COOLDOWN_TICKS = 2; // 0.1秒

    public W4MagicBullet(Properties properties) {
        super(properties.durability(3000).setNoRepair());
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(ItemInit.MAGIC_BULLET_AMMO.get());
    }

    @Override
    public int getDefaultProjectileRange() {
        return 128;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);
        MagicBulletEntity bullet = new MagicBulletEntity(level, player, 26.0F, 24.0F, player.getLookAngle());
        bullet.setMaxLifetime(100);
        bullet.setVelocity(24.0F);
        level.addFreshEntity(bullet);

        if (!hasAmmo(player)) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("弾薬切れ / No Ammo"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(gun);
        }

        if (level.isClientSide) {
            level.playSound(player, player.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.2F, 1.0F);
            return InteractionResultHolder.consume(gun);
        }

        shootBullet(level, player);
        consumeAmmo(player);
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand)); // 耐久減少
        player.awardStat(Stats.ITEM_USED.get(this));

        player.getCooldowns().addCooldown(this, 50);
        return InteractionResultHolder.consume(gun);
    }

    private boolean hasAmmo(Player player) {
        return player.getInventory().contains(new ItemStack(ItemInit.MAGIC_BULLET_AMMO.get()));
    }

    private void consumeAmmo(Player player) {
        if (!player.getAbilities().instabuild) {
            player.getInventory().clearOrCountMatchingItems(
                    stack -> stack.is(ItemInit.MAGIC_BULLET_AMMO.get()),
                    1, player.inventoryMenu.getCraftSlots()
            );
        }
    }

    private void shootBullet(Level level, Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition();
        Vec3 spawnPos = eyePos.add(look.scale(0.8));

        MagicBulletEntity bullet = new MagicBulletEntity(level, player, DAMAGE, VELOCITY, look);
        level.addFreshEntity(bullet);

        level.playSound(null, player, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.5F, 0.9F);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurt(attacker.level().damageSources().magic(), 12.0F);

        stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}