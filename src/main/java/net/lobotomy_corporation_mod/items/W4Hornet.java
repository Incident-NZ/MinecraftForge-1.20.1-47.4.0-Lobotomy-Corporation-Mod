package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.ItemInit;
import net.lobotomy_corporation_mod.entity.BulletEntity;
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

public class W4Hornet extends ProjectileWeaponItem {
    private static final float DAMAGE = 16.0f;
    private static final float VELOCITY = 8.0f;
    private static final int RANGE_TICKS = 200;
    public W4Hornet(Properties properties) {
        super(properties.durability(3000));
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

        if (!level.isClientSide) {
            Vec3 look = player.getLookAngle();
            Vec3 spawnPos = player.getEyePosition().add(look.scale(0.5));

            BulletEntity bullet = new BulletEntity(level, player, 8.0F, 16.0F, player.getLookAngle());
            bullet.setDamage(8.0F);
            bullet.setVelocity(30.0F);
            bullet.setMaxLifetime(80);
            level.addFreshEntity(bullet);

            level.playSound(null, player, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.2F, 0.8F);
            gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

            player.awardStat(Stats.ITEM_USED.get(this));
        }

        player.getCooldowns().addCooldown(this, 20);
        return InteractionResultHolder.consume(gun);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurt(attacker.level().damageSources().generic(), 12.0F);

        stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }
}
