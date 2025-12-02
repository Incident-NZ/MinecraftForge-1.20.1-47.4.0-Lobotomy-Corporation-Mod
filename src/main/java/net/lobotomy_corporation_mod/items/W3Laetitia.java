package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.config.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;

public class W3Laetitia extends CrossbowItem {

    private static final String AMMO_TAG = "laetitia_ammo";
    private static final int MAX_AMMO = 5;
    private static final int RELOAD_TICKS = 30; // 1.5秒 (20tick * 1.5)
    private static final float DAMAGE = 6.0f;

    public W3Laetitia() {
        super(new Properties().durability(2000).setNoRepair());
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return RELOAD_TICKS; // 長押しできる時間
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)) return;

        int useTicks = this.getUseDuration(stack) - timeLeft;
        // 3秒以上押して離したらリロード
        if (useTicks >= RELOAD_TICKS) {
            setAmmo(stack, MAX_AMMO);
            CompoundTag tag = stack.getOrCreateTag();
            tag.putBoolean("Reloaded", true);
            stack.setTag(tag);
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ARMOR_EQUIP_GENERIC, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int ammo = getAmmo(stack);

        if (ammo > 0) {
            // 発射処理
            if (!level.isClientSide) {
                shoot(level, player);
                setAmmo(stack, ammo - 1);
                stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            }
            return InteractionResultHolder.success(stack);
        } else {
            // リロード開始
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        }
    }

    private void shoot(Level level, Player player) {
        Vec3 start = player.getEyePosition();
        Vec3 look = player.getLookAngle();

        int maxStep = 256;
        for (int i = 0; i < maxStep; i++) {
            Vec3 pos = start.add(look.scale(i * 0.5));
            // パーティクルは常にクライアント側で表示
            if (level.isClientSide) {
                level.addParticle(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, 0, 0, 0);
            }
            // ダメージ判定はサーバー側のみ
            if (!level.isClientSide) {
                AABB box = new AABB(pos.subtract(0.5, 0.5, 0.5), pos.add(0.5, 0.5, 0.5));
                List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, box, e -> e != player);
                for (LivingEntity target : targets) {
                    if (!Config.ALLOW_FRIENDLY_FIRE.get() && target instanceof Player) continue;
                    target.hurt(target.damageSources().playerAttack(player), DAMAGE);
                    return;
                }
            }
        }
        // サウンドはどちらでもOK
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENDER_DRAGON_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private int getAmmo(ItemStack stack) {
        return stack.getOrCreateTag().getInt(AMMO_TAG);
    }

    private void setAmmo(ItemStack stack, int ammo) {
        stack.getOrCreateTag().putInt(AMMO_TAG, Math.max(0, Math.min(MAX_AMMO, ammo)));
    }
}
