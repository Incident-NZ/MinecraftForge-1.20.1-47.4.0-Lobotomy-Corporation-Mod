package net.pm_equips.items;

import net.pm_equips.BlockInit;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class EGOW2Lantern extends SwordItem {
    public EGOW2Lantern() {
        super(new CustomTier(), 17, -3.4f, new Properties().durability(1000));
    }

    private static class CustomTier implements Tier {
        @Override public int getUses() { return 1000; }
        @Override public float getSpeed() { return 4.0f; }
        @Override public float getAttackDamageBonus() { return 0.0f; }
        @Override public int getLevel() { return 0; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.of(BlockInit.BlockItems.TETH_PE_BOX.get()); }
    }
}