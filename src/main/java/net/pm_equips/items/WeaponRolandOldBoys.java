package net.pm_equips.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pm_equips.ItemInit;
import net.pm_equips.PMEquipsMain;

public class WeaponRolandOldBoys extends SwordItem {
    private static final float MIN_DAMAGE = 4.0F;
    private static final int DAMAGE_VARIANCE = 5;
    private static final float HEAL_AMOUNT = 5.0F;

    public WeaponRolandOldBoys() {
        super(new CustomTier(), 0, -2.5F, new Properties().durability(1000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            player.heal(HEAL_AMOUNT);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    private static float rollDamage(Player player) {
        return MIN_DAMAGE + player.level().random.nextInt(DAMAGE_VARIANCE);
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 1000;
        }

        @Override
        public float getSpeed() {
            return 4.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0F;
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
            return Ingredient.EMPTY;
        }
    }

    @Mod.EventBusSubscriber(modid = PMEquipsMain.MOD_ID)
    public static class OldBoysEvents {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!(event.getSource().getEntity() instanceof Player player)) {
                return;
            }

            if (!player.getMainHandItem().is(ItemInit.FIXER_ROLAND_OLD_BOYS.get())) {
                return;
            }

            event.setAmount(rollDamage(player));
        }
    }
}
