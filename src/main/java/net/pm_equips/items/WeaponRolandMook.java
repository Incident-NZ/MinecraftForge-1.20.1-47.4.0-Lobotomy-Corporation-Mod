package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.UseAnim;
import net.pm_equips.SoundInit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pm_equips.events.RolandMookDropHandler;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeaponRolandMook extends SwordItem {
    private static final String TAG_DRAWN = "roland_mook_drawn";
    private static final String TAG_DAMAGE = "roland_mook_damage";
    private static final String TAG_COOLDOWN = "roland_mook_cooldown";
    private static final int USE_DURATION = 60;
    private static final int COOLDOWN_TICKS = 20 * 15;

    public WeaponRolandMook() {
        super(new CustomTier(), 14, -2.8f, new Properties().durability(1000));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (isDrawn(stack)) {
            setDrawn(stack, false);
            return InteractionResultHolder.consume(stack);
        }

        if (isCoolingDown(stack)) {
            return InteractionResultHolder.fail(stack);
        }

        player.startUsingItem(hand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.ROLAND_MOOK_CHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeCharged) {
        if (!(entity instanceof Player player) || isDrawn(stack)) {
            return;
        }

        if (timeCharged < USE_DURATION) {
            setDrawn(stack, true);
            return;
        }

        if (isCoolingDown(stack)) {
            return;
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.ROLAND_MOOK_ATTACK.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        performSpecialSlash(level, player);
        setCooldown(stack, level);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) {
            return super.hurtEnemy(stack, target, attacker);
        }

        float damage = isDrawn(stack) ? getDamageValue(stack) : 1.0F;
        boolean result = target.hurt(player.damageSources().playerAttack(player), damage);
        if (result) {
            stack.hurtAndBreak(1, player, ignored -> {
            });
        }
        return result;
    }

    public boolean isDrawn(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean(TAG_DRAWN);
    }

    private boolean isCoolingDown(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(TAG_COOLDOWN)) {
            return false;
        }
        return tag.getLong(TAG_COOLDOWN) > tag.getLong("_last_tick");
    }

    private void setDrawn(ItemStack stack, boolean drawn) {
        stack.getOrCreateTag().putBoolean(TAG_DRAWN, drawn);
    }

    private void setCooldown(ItemStack stack, Level level) {
        CompoundTag tag = stack.getOrCreateTag();
        long now = level.getGameTime();
        tag.putLong(TAG_COOLDOWN, now + COOLDOWN_TICKS);
        tag.putLong("_last_tick", now);
    }

    private int getDamageValue(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(TAG_DAMAGE)) {
            tag.putInt(TAG_DAMAGE, 8 + ThreadLocalRandom.current().nextInt(8));
        }
        return tag.getInt(TAG_DAMAGE);
    }

    private void performSpecialSlash(Level level, Player player) {
        if (level.isClientSide) {
            return;
        }

        Vec3 eyePosition = player.getEyePosition();
        Vec3 look = player.getViewVector(1.0F);
        Vec3 targetPosition = eyePosition.add(look.scale(3.0D));
        AABB area = new AABB(eyePosition, targetPosition).inflate(1.0D, 1.0D, 1.0D);

        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, area,
                entity -> entity != player
                        && entity.isAlive()
                        && entity instanceof Mob
                        && !entity.isInvulnerable()
                        && !entity.isAlliedTo(player));

        if (targets.isEmpty()) {
            return;
        }

        DamageSource damageSource = player.damageSources().playerAttack(player);
        for (LivingEntity target : targets) {
            target.hurt(damageSource, Float.MAX_VALUE);
            if (!target.isAlive()) {
                RolandMookDropHandler.markForExtraDrops(target);
            }
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP,
                SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 1000;
        }

        @Override
        public float getSpeed() {
            return 4.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0f;
        }

        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    }
}
