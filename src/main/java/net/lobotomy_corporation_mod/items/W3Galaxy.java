package net.lobotomy_corporation_mod.items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;

public class W3Galaxy extends SwordItem {
    public W3Galaxy() {
        super(new CustomTier(), 12, -2.5f, new Properties().durability(3000));
    }

    private static class CustomTier implements net.minecraft.world.item.Tier {
        @Override public int getUses() { return 4000; }
        @Override public float getSpeed() { return 4.0f; }
        @Override public float getAttackDamageBonus() { return 0.0f; }
        @Override public int getLevel() { return 4; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    }
}
