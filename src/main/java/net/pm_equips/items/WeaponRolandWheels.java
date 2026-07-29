package net.pm_equips.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;

import java.util.concurrent.ThreadLocalRandom;

public class WeaponRolandWheels extends SwordItem {
    private static final String TAG_GUARD_ACTIVE = "wheels_guard_active";
    private static final String TAG_GUARD_DAMAGE = "wheels_guard_damage";

    public WeaponRolandWheels() {
        super(new CustomTier(), 0, 1.2f, new Properties().durability(1000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) {
            return super.hurtEnemy(stack, target, attacker);
        }

        if (isGuarding(stack)) {
            performGuard(stack, target, player);
            return true;
        }

        float damage = getGuardDamageValue(stack);
        boolean result = target.hurt(player.damageSources().playerAttack(player), damage);
        if (result) {
            stack.hurtAndBreak(1, player, ignored -> {});
        }
        return result;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!(entity instanceof LivingEntity target)) {
            return false;
        }
        toggleGuard(stack);
        return true;
    }

    private void performGuard(ItemStack stack, LivingEntity target, Player player) {
        if (!isGuarding(stack)) {
            return;
        }

        float damage = getGuardDamageValue(stack);
        target.hurt(player.damageSources().playerAttack(player), damage);

        Vec3 knockbackDir = target.getEyePosition().subtract(player.getEyePosition()).normalize();
        target.knockback(5.0D, knockbackDir.x, knockbackDir.z);

        MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 9, false, false, true);
        target.addEffect(slowness);

        stack.hurtAndBreak(1, player, ignored -> {});
        toggleGuard(stack);
    }

    public boolean isGuarding(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean(TAG_GUARD_ACTIVE);
    }

    private void toggleGuard(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putBoolean(TAG_GUARD_ACTIVE, !tag.getBoolean(TAG_GUARD_ACTIVE));
    }

    private float getGuardDamageValue(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains(TAG_GUARD_DAMAGE)) {
            tag.putInt(TAG_GUARD_DAMAGE, 12 + ThreadLocalRandom.current().nextInt(13));
        }
        return tag.getInt(TAG_GUARD_DAMAGE);
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
