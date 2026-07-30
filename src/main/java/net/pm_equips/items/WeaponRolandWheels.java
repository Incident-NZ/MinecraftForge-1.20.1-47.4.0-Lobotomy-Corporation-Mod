package net.pm_equips.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WeaponRolandWheels extends SwordItem {
    private static final String TAG_GUARD_ACTIVE = "wheels_guard_active";
    private static final float GUARD_DAMAGE = 8.0F;

    public WeaponRolandWheels() {
        super(new CustomTier(), 23, -3.2f, new Properties().durability(1000));
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

        boolean result = target.hurt(player.damageSources().playerAttack(player), GUARD_DAMAGE);
        if (result) {
            stack.hurtAndBreak(1, player, ignored -> {});
        }
        return result;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        setGuarding(stack, true);
        player.startUsingItem(hand);

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        setGuarding(stack, false);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        setGuarding(stack, false);
        return stack;
    }

    private void performGuard(ItemStack stack, LivingEntity target, Player player) {
        if (!isGuarding(stack)) {
            return;
        }

        target.hurt(player.damageSources().playerAttack(player), GUARD_DAMAGE);

        Vec3 knockbackDir = target.getEyePosition().subtract(player.getEyePosition()).normalize();
        target.knockback(5.0D, knockbackDir.x, knockbackDir.z);

        MobEffectInstance slowness = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 9, false, false, true);
        target.addEffect(slowness);

        stack.hurtAndBreak(1, player, ignored -> {});
        setGuarding(stack, false);
    }

    public boolean isGuarding(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean(TAG_GUARD_ACTIVE);
    }

    private void setGuarding(ItemStack stack, boolean guarding) {
        stack.getOrCreateTag().putBoolean(TAG_GUARD_ACTIVE, guarding);
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
