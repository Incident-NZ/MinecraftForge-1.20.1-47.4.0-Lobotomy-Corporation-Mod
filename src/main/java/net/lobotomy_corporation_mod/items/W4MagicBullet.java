package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.Interface.ProjectileWeaponItem;
import net.lobotomy_corporation_mod.entity.MagicBulletEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class W4MagicBullet extends ProjectileWeaponItem {
    public W4MagicBullet(ResourceLocation bulletType, float damage, float velocity) {
        super(new Item.Properties().durability(3000), bulletType, damage, velocity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);

        if (!hasAmmo(player, bulletType)) {
            player.displayClientMessage(Component.literal("No Ammo!"), true);
            return InteractionResultHolder.fail(gun);
        }

        if (level.isClientSide) {
            triggerMuzzleFlash(level, player);
            return InteractionResultHolder.consume(gun);
        }

        shootBullet(level, player);
        consumeAmmo(player, bulletType);
        player.getCooldowns().addCooldown(this, 2);

        return InteractionResultHolder.consume(gun);
    }

    @Override
    public void shootBullet(Level level, Player player) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle().normalize();
        Vec3 spawnPos = eyePos.add(look.scale(0.5));

        MagicBulletEntity bullet = new MagicBulletEntity(
                level,
                player,
                this.damage,  // 武器のダメージ
                this.velocity,  // 武器の速度
                look  // 方向
        );

        bullet.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
        level.addFreshEntity(bullet);

        // 発射音＋エフェクト
        level.playSound(null, player.blockPosition(),
                SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.2F, 1.0F);
        triggerMuzzleFlash(level, player);
    }
}