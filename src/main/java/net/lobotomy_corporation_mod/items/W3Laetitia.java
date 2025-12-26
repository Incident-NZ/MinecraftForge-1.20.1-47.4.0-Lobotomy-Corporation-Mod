package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.config.Config;
import net.lobotomy_corporation_mod.entity.BulletEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class W3Laetitia extends ProjectileWeaponItem {
    private static final float DAMAGE = 8.0f;
    private static final float VELOCITY = 12.0f;
    private static final int COOLDOWN_TICKS = 10; // 0.5秒
    public W3Laetitia(Properties properties) {
        super(properties.durability(2000));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == ItemInit.RIFLE_BULLET_AMMO.get();
    }

    @Override
    public int getDefaultProjectileRange() {
        return 128;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);

        if (!hasAmmo(player)) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("弾薬切れ / No Ammo"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(gun);
        }

        if (level.isClientSide) {
            level.playSound(player, player.blockPosition(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.2F, 0.8F);
            return InteractionResultHolder.consume(gun);
        }

        shootBullet(level, player);
        consumeAmmo(player);

        gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        player.awardStat(Stats.ITEM_USED.get(this));
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        return InteractionResultHolder.consume(gun);
    }

    private boolean hasAmmo(Player player) {
        return player.getInventory().contains(new ItemStack(ItemInit.RIFLE_BULLET_AMMO.get()));
    }

    private void consumeAmmo(Player player) {
        if (!player.getAbilities().instabuild) {
            player.getInventory().clearOrCountMatchingItems(
                    stack -> stack.is(ItemInit.RIFLE_BULLET_AMMO.get()),
                    1, player.inventoryMenu.getCraftSlots()
            );
        }
    }

    private void shootBullet(Level level, Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition();
        Vec3 spawnPos = eyePos.add(look.scale(0.5));
        Vec3 endPos = eyePos.add(look.scale(getDefaultProjectileRange()));

        if (!level.isClientSide) {
            EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                    level,
                    player,
                    eyePos,
                    endPos,
                    player.getBoundingBox().expandTowards(look.scale(getDefaultProjectileRange())).inflate(1.0D),
                    (e) -> e != player && e instanceof LivingEntity && e.isAlive()
            );

            if (entityHit != null) {
                if (entityHit.getEntity() instanceof LivingEntity target) {
                    if (!(target instanceof Player) || Config.ALLOW_FRIENDLY_FIRE.get()) {
                        target.hurt(player.level().damageSources().generic(), DAMAGE);
                        level.playSound(null, target.blockPosition(), SoundEvents.GENERIC_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }
                }
            }
        }

        BulletEntity bullet = new BulletEntity(level, player, DAMAGE, VELOCITY, look);
        bullet.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        bullet.setDamage(DAMAGE);
        bullet.setVelocity(VELOCITY);
        bullet.setMaxLifetime(getDefaultProjectileRange()); // 距離に応じた寿命
        level.addFreshEntity(bullet);

        level.playSound(null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.2F, 0.8F);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurt(attacker.level().damageSources().generic(), DAMAGE);

        stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
