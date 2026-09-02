// java
package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;
import net.pm_equips.SoundInit;
import net.pm_equips.config.CommonConfig;
import net.pm_equips.entity.PBullet;
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
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class EGOW4LamentR extends ProjectileWeaponItem {
    private static final int MAX_AMMO = 30;
    private static final int RELOAD_TICKS = 20;
    private static final float DAMAGE = 2.0f;
    private static final float VELOCITY = 4.0f;
    private static final int RANGE = 64;
    private static final int COOLDOWN_TICKS = 5; // 0.25秒
    private static final double ANGLE_DEGREES = 10.0; // 弾を左右に少し振る角度

    public EGOW4LamentR(Properties properties) {
        super(properties.durability(1500));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.is(ItemInit.PISTOL_BULLET_AMMO.get());
    }

    @Override
    public int getDefaultProjectileRange() {
        return RANGE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);

        // オフハンドに EGOW4LamentL が無ければ使用不可
        ItemStack off = player.getItemInHand(InteractionHand.OFF_HAND);
        if (off.getItem() != ItemInit.W4_SOLEMN_LAMENT_L.get()) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("オフハンドに崇高な誓い 白が必要です"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(gun);
        }

        int reload = gun.getOrCreateTag().getInt("Reload");
        int ammo = gun.getOrCreateTag().getInt("Ammo");

        if (reload > 0) {
            return InteractionResultHolder.fail(gun);
        }

        // 必要弾数は2発
        if (ammo < 2) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("弾薬切れ / No Ammo (2 required)"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(gun);
        }

        // サーバー側で2発射撃
        shootDouble(level, player);

        gun.getOrCreateTag().putInt("Ammo", ammo - 2);

        gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        player.awardStat(Stats.ITEM_USED.get(this));
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        return InteractionResultHolder.consume(gun);
    }

    private void shootDouble(Level level, Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition();
        Vec3 spawnBase = eyePos.add(look.scale(0.5));

        // 左右2方向（角度を回転）
        Vec3 dirLeft = rotateY(look, ANGLE_DEGREES);
        Vec3 dirRight = rotateY(look, -ANGLE_DEGREES);

        if (!level.isClientSide) {
            applyRayDamage(level, player, eyePos, dirLeft);
            applyRayDamage(level, player, eyePos, dirRight);
        }

        PBullet b1 = new PBullet(level, player, DAMAGE, VELOCITY, dirLeft);
        b1.setPos(spawnBase.x, spawnBase.y, spawnBase.z);
        b1.setDamage(DAMAGE);
        b1.setVelocity(VELOCITY);
        b1.setMaxLifetime(getDefaultProjectileRange());
        level.addFreshEntity(b1);

        PBullet b2 = new PBullet(level, player, DAMAGE, VELOCITY, dirRight);
        b2.setPos(spawnBase.x, spawnBase.y, spawnBase.z);
        b2.setDamage(DAMAGE);
        b2.setVelocity(VELOCITY);
        b2.setMaxLifetime(getDefaultProjectileRange());
        level.addFreshEntity(b2);

        level.playSound(
                null,
                player.blockPosition(),
                SoundInit.EGO_LAMENT.get(),
                SoundSource.PLAYERS,
                1.0F,
                1.0F
        );
    }

    // Rキーリロード開始
    public void startReload(ItemStack stack, Player player) {
        int reload = stack.getOrCreateTag().getInt("Reload");
        if (reload > 0) return;

        int ammo = stack.getOrCreateTag().getInt("Ammo");
        if (ammo >= MAX_AMMO) return;

        boolean hasAmmo = false;
        for (ItemStack invStack : player.getInventory().items) {
            if (invStack.is(ItemInit.PISTOL_BULLET_AMMO.get())) {
                hasAmmo = true; break;
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
                            invStack.shrink(1); loaded++;
                        }
                    }
                    if (loaded >= needed) break;
                }

                stack.getOrCreateTag().putInt("Ammo", ammo + loaded);
            }
        }

        super.inventoryTick(stack, level, entity, slot, selected);
    }

    private void applyRayDamage(Level level, Player player, Vec3 eyePos, Vec3 dir) {
        Vec3 endPos = eyePos.add(dir.scale(getDefaultProjectileRange()));
        EntityHitResult entityHit = ProjectileUtil.getEntityHitResult(
                level,
                player,
                eyePos,
                endPos,
                player.getBoundingBox().expandTowards(dir.scale(getDefaultProjectileRange())).inflate(1.0D),
                (e) -> e != player && e instanceof LivingEntity && e.isAlive()
        );

        if (entityHit != null) {
            if (entityHit.getEntity() instanceof LivingEntity target) {
                if (!(target instanceof Player) || CommonConfig.ALLOW_FRIENDLY_FIRE.get()) {
                    target.hurt(player.level().damageSources().playerAttack(player), DAMAGE);
                    level.playSound(null, target.blockPosition(), SoundEvents.GENERIC_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }

    // Y軸回転（度）
    private Vec3 rotateY(Vec3 v, double degrees) {
        double rad = Math.toRadians(degrees);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        double x = v.x * cos - v.z * sin;
        double z = v.x * sin + v.z * cos;
        return new Vec3(x, v.y, z).normalize();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof Player && !CommonConfig.ALLOW_FRIENDLY_FIRE.get()) {
            stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            return true;
        }

        target.hurt(attacker.level().damageSources().generic(), DAMAGE);

        stack.hurtAndBreak(1, attacker, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repair) {
        return repair.is(BlockInit.BlockItems.WAW_PE_BOX.get());
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

