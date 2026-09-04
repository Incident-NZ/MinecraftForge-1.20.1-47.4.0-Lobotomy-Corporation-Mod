package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;
import net.pm_equips.SoundInit;
import net.pm_equips.entity.AmmoGun;
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

import java.util.List;
import java.util.function.Predicate;

public class EGOW4Hornet extends ProjectileWeaponItem {
    private static final int MAX_AMMO = 5;
    private static final int RELOAD_TICKS = 40; // 2秒
    private static final float VELOCITY = 4.0f;
    private static final int COOLDOWN_TICKS = 10; // 0.5秒
    public EGOW4Hornet(Properties properties) {
        super(properties.durability(3000));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (stack) -> stack.getItem() == ItemInit.RIFLE_BULLET_AMMO.get();
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 128;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack gun = player.getItemInHand(hand);

        int reload = gun.getOrCreateTag().getInt("Reload");
        int ammo = gun.getOrCreateTag().getInt("Ammo");

        if (reload > 0) return InteractionResultHolder.fail(gun);

        if (ammo <= 0) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("弾薬切れ / No Ammo"), true);
                level.playSound(null, player, SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS, 1.0F, 1.2F);
            }
            return InteractionResultHolder.fail(gun);
        }

        if (level.isClientSide) {
            player.level().playSound(
                    null,
                    player.blockPosition(),
                    SoundInit.GUN_SEMI.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
            return InteractionResultHolder.success(gun);
        }

        shootBullet(level, player);

        gun.getOrCreateTag().putInt("Ammo", ammo - 1);

        gun.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
        player.awardStat(Stats.ITEM_USED.get(this));
        player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);

        return InteractionResultHolder.consume(gun);
    }

    private void shootBullet(Level level, Player player) {
        Vec3 look = player.getLookAngle();
        Vec3 eyePos = player.getEyePosition();
        Vec3 spawnPos = eyePos.add(look.scale(0.5));
        Vec3 endPos = eyePos.add(look.scale(getDefaultProjectileRange()));

        if (!level.isClientSide) {
            float damage = 7 + level.random.nextInt(2); // 7..8

            AmmoGun bullet = new AmmoGun(level, player, damage, VELOCITY, look);
            bullet.setDamage(damage);
            bullet.setVelocity(VELOCITY);
            bullet.setMaxLifetime(getDefaultProjectileRange());
            level.addFreshEntity(bullet);
        }

        player.level().playSound(
                null,
                player.blockPosition(),
                SoundInit.GUN_SEMI.get(),
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
            if (invStack.is(ItemInit.RIFLE_BULLET_AMMO.get())) {
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
                    if (invStack.is(ItemInit.RIFLE_BULLET_AMMO.get())) {
                        while (!invStack.isEmpty() && loaded < needed) { invStack.shrink(1); loaded++; }
                    }
                    if (loaded >= needed) break;
                }

                stack.getOrCreateTag().putInt("Ammo", ammo + loaded);
            }
        }

        super.inventoryTick(stack, level, entity, slot, selected);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);
        if (result && !attacker.level().isClientSide()) {
            // Iフレーム無視
            target.hurtTime = 0;           // クライアント側の赤フラッシュ時間
            target.invulnerableTime = 0;   // または noDamageTicks (バージョンにより名称確認)
        }

        target.hurt(attacker.level().damageSources().generic(), 7.0f);

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