package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;
import net.pm_equips.entity.AmmoExp;

import java.util.List;
import java.util.function.Predicate;

public class EGOW3Harmony extends ProjectileWeaponItem {

    private static final int MAX_AMMO = 1;
    private static final int RELOAD_TICKS = 100;
    private static final float MIN_DAMAGE = 30.0F;
    private static final float VELOCITY = 3.0F;
    private static final int RANGE = 128;
    private static final int COOLDOWN_TICKS = 20;
    private static final double EXPLOSION_RADIUS = 9.0D;

    public EGOW3Harmony(
            Properties properties
    ) {
        super(properties.durability(2000));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(ItemInit.EXPLOSIVE_BULLET_AMMO.get());
    }

    @Override
    public int getDefaultProjectileRange() {
        return RANGE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player player,
            InteractionHand hand
    ) {

        ItemStack gun =
                player.getItemInHand(hand);

        CompoundTag tag =
                gun.getOrCreateTag();

        int reload =
                tag.getInt("Reload");

        int ammo =
                tag.getInt("Ammo");

        if (reload > 0) {
            return InteractionResultHolder.fail(gun);
        }

        if (ammo <= 0) {

            if (!level.isClientSide) {
                player.displayClientMessage(
                        Component.literal("弾薬切れ / No Ammo"),
                        true
                );

                level.playSound(
                        null,
                        player,
                        SoundEvents.DISPENSER_FAIL,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.2F
                );
            }

            return InteractionResultHolder.fail(gun);
        }

        if (level.isClientSide) {
            level.playSound(
                    player,
                    player.blockPosition(),
                    SoundEvents.GENERIC_EXPLODE,
                    SoundSource.PLAYERS,
                    1.2F,
                    0.8F
            );

            return InteractionResultHolder.consume(gun);
        }

        shootBullet(
                level,
                player
        );

        tag.putInt(
                "Ammo",
                ammo - 1
        );

        gun.hurtAndBreak(
                1,
                player,
                p -> p.broadcastBreakEvent(hand)
        );

        player.awardStat(
                Stats.ITEM_USED.get(this)
        );

        player.getCooldowns()
                .addCooldown(
                        this,
                        COOLDOWN_TICKS
                );

        return InteractionResultHolder.consume(gun);
    }

    private void shootBullet(
            Level level,
            Player player
    ) {

        Vec3 look =
                player.getLookAngle();

        float damage =
                MIN_DAMAGE + level.random.nextInt(21);

        AmmoExp bullet =
                new AmmoExp(
                        level,
                        player,
                        damage,
                        VELOCITY,
                        look
                );

        bullet.setDamage(damage);
        bullet.setVelocity(VELOCITY);
        bullet.setMaxLifetime(getDefaultProjectileRange());
        bullet.setExplosionRadius(EXPLOSION_RADIUS);

        level.addFreshEntity(bullet);

        level.playSound(
                null,
                player.blockPosition(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS,
                1.2F,
                0.8F
        );
    }

    // Rキーリロード開始
    public void startReload(
            ItemStack stack,
            Player player
    ) {

        CompoundTag tag =
                stack.getOrCreateTag();

        int reload =
                tag.getInt("Reload");

        if (reload > 0) {
            return;
        }

        int ammo =
                tag.getInt("Ammo");

        if (ammo >= MAX_AMMO) {
            return;
        }

        boolean hasAmmo =
                false;

        for (ItemStack invStack : player.getInventory().items) {

            if (invStack.is(ItemInit.EXPLOSIVE_BULLET_AMMO.get())) {
                hasAmmo = true;
                break;
            }
        }

        if (!hasAmmo) {
            return;
        }

        tag.putInt(
                "Reload",
                RELOAD_TICKS
        );

        player.level().playSound(
                null,
                player.blockPosition(),
                SoundEvents.IRON_TRAPDOOR_CLOSE,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            net.minecraft.world.entity.Entity entity,
            int slot,
            boolean selected
    ) {

        CompoundTag tag =
                stack.getOrCreateTag();

        if (!tag.contains("Ammo")) {
            tag.putInt(
                    "Ammo",
                    MAX_AMMO
            );
        }

        int reload =
                tag.getInt("Reload");

        if (reload > 0) {

            reload--;

            tag.putInt(
                    "Reload",
                    reload
            );

            if (reload <= 0
                    && entity instanceof Player player) {

                int ammo =
                        tag.getInt("Ammo");

                int needed =
                        MAX_AMMO - ammo;

                if (needed <= 0) {
                    return;
                }

                int loaded =
                        0;

                for (ItemStack invStack : player.getInventory().items) {

                    if (invStack.is(ItemInit.EXPLOSIVE_BULLET_AMMO.get())) {

                        while (!invStack.isEmpty()
                                && loaded < needed) {

                            invStack.shrink(1);
                            loaded++;
                        }
                    }

                    if (loaded >= needed) {
                        break;
                    }
                }

                tag.putInt(
                        "Ammo",
                        ammo + loaded
                );
            }
        }

        super.inventoryTick(
                stack,
                level,
                entity,
                slot,
                selected
        );
    }

    @Override
    public boolean hurtEnemy(
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker
    ) {

        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result && !attacker.level().isClientSide()) {
            // Iフレーム無視
            target.hurtTime = 0;           // クライアント側の赤フラッシュ時間
            target.invulnerableTime = 0;   // または noDamageTicks (バージョンにより名称確認)
        }

        target.hurt(
                attacker.level().damageSources().explosion(null),
                MIN_DAMAGE
        );

        stack.hurtAndBreak(
                1,
                attacker,
                p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND)
        );

        return true;
    }

    @Override
    public boolean isValidRepairItem(
            ItemStack stack,
            ItemStack repair
    ) {
        return repair.is(BlockInit.BlockItems.HE_PE_BOX.get());
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {

        CompoundTag tag =
                stack.getOrCreateTag();

        tooltip.add(
                Component.literal(
                        "Ammo: "
                                + tag.getInt("Ammo")
                                + " / "
                                + MAX_AMMO
                )
        );

        super.appendHoverText(
                stack,
                level,
                tooltip,
                flag
        );
    }
}
