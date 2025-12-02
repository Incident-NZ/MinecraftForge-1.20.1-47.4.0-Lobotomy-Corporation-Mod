package net.lobotomy_corporation_mod.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class WeaponRolandDurandal extends SwordItem {
    public WeaponRolandDurandal() {
        super(new CustomTier(), 16, -2.0f, new Properties().durability(4000));
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
            return 0f;
        }

        @Override
        public int getLevel() {
            return 1;
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
