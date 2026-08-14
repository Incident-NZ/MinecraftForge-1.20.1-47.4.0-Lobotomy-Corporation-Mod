package net.pm_equips.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.pm_equips.BlockInit;
import net.pm_equips.entity.EGOHeavenProjectile;

public class EGOW4Heaven extends SwordItem {
    private static final int MIN_THROW_CHARGE_TICKS = 10;

    public EGOW4Heaven() {
        // Player base damage is 1.0, so this 15.0 modifier results in 16.0 attack damage.
        // Player base attack speed is 4.0, so -2.2 results in 1.8 attack speed.
        super(new CustomTier(), 15, -2.2F, new Properties().durability(3000));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.SPEAR;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (!(entity instanceof Player player)
                || getUseDuration(stack) - timeLeft < MIN_THROW_CHARGE_TICKS
                || level.isClientSide) {
            return;
        }

        EGOHeavenProjectile projectile = new EGOHeavenProjectile(level, player, player.getLookAngle());
        level.addFreshEntity(projectile);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
        player.awardStat(Stats.ITEM_USED.get(this));

        // The weapon is consumed on every successful throw, including in creative mode.
        stack.shrink(1);
    }

    private static class CustomTier implements Tier {
        @Override public int getUses() { return 3000; }
        @Override public float getSpeed() { return 4.0F; }
        @Override public float getAttackDamageBonus() { return 0.0F; }
        @Override public int getLevel() { return 0; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() {
            return Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get());
        }
    }
}
