package net.pm_equips.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class WeaponArgalia extends SwordItem {
    public WeaponArgalia() {
        super(new CustomTier(), 21, -2.8f, new Properties().durability(5000));
    }

    private static class CustomTier implements Tier {

        @Override
        public int getUses() {
            return 5000;
        }

        @Override
        public float getSpeed() {
            return 4.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0;
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
