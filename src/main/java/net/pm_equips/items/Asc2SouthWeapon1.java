package net.pm_equips.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;

public class Asc2SouthWeapon1 extends SwordItem {
    public Asc2SouthWeapon1() {
        super(new CustomTier(), 17, -3.2f, new Properties());
    }

    private static class CustomTier implements Tier {
        @Override public int getUses() { return 1000; }
        @Override public float getSpeed() { return 4.0f; }
        @Override public float getAttackDamageBonus() { return 0.0f; }
        @Override public int getLevel() { return 1; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(ItemInit.METAL_INGOT.get()); }
    }
}
