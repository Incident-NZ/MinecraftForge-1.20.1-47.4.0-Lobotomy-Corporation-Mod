package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.BlockInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class W4Lamp extends SwordItem {
    public W4Lamp() {
        super(new CustomTier(), 27, -3.3f, new Properties().durability(3000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) return super.hurtEnemy(stack, target, attacker);

        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));

        CompoundTag tag = target.getPersistentData();
        String id = "lamp_weapons_hits";
        int hits = tag.getInt(id);

        hits++;
        if (hits >= 4) {
            tag.putBoolean("lamp_weapons_buff", true);
            tag.putInt(id, 0);
        } else {
            tag.putInt(id, hits);
        }

        return true;
    }

    public static float applyExtraDamage(LivingEntity entity, DamageSource source, float originalDamage) {
        CompoundTag tag = entity.getPersistentData();
        if (tag.getBoolean("lamp_weapons_buff")) {
            tag.putBoolean("lamp_weapons_buff", false); // 一度だけ適用
            return originalDamage + 2;
        }
        return originalDamage;
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 3000;
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
            return Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get());
        }
    }
}
