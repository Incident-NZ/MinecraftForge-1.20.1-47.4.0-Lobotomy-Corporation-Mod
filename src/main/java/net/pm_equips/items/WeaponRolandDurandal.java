package net.pm_equips.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;

public class WeaponRolandDurandal extends SwordItem {
    public WeaponRolandDurandal() {
        super(new CustomTier(), 17, -1.9f, new Properties().durability(1000));
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
            return 0f;
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

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;

        // 効力は利き手（main hand）に持っている間だけ
        ItemStack main = player.getMainHandItem();
        boolean has = main.getItem() instanceof WeaponRolandDurandal;

        if (has) {
            // 攻撃力上昇 II (amplifier 1)
            MobEffectInstance mei = new MobEffectInstance(MobEffects.DAMAGE_BOOST, 12, 1, false, false, true);
            player.addEffect(mei);
        } else {
            if (player.hasEffect(MobEffects.DAMAGE_BOOST)) {
                player.removeEffect(MobEffects.DAMAGE_BOOST);
            }
        }

        super.inventoryTick(stack, level, entity, slot, selected);
    }
}
