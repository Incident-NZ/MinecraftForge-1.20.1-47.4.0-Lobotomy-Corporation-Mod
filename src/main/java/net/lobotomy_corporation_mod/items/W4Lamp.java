package net.lobotomy_corporation_mod.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class W4Lamp extends SwordItem {
    public W4Lamp() {
        super(new CustomTier(), 22, -3.6f, new Properties().durability(3000));
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
            // フラグとして効果を付与
            tag.putBoolean("lamp_weapons_buff", true);
            tag.putInt(id, 0); // リセット
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
            return 1.0f;
        }

        @Override
        public int getLevel() {
            return 4;
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public net.minecraft.world.item.crafting.Ingredient getRepairIngredient() {
            return net.minecraft.world.item.crafting.Ingredient.EMPTY; // 任意で変更可能
        }
    }
}
