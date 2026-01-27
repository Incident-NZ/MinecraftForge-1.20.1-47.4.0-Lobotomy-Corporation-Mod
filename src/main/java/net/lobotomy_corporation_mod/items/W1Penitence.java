package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.BlockInit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class W1Penitence extends SwordItem {

    public W1Penitence() {
        super(new CustomTier(), 7, -2.8f, new Properties().durability(800));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player) {
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));

            // HPを2回復（1ハート）
            if (player.getHealth() < player.getMaxHealth()) {
                player.heal(2.0F);
            }
        }

        return true;
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 800;
        }

        @Override
        public float getSpeed() {
            return 4.0f;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0f;
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
            return Ingredient.of(BlockInit.BlockItems.ZAYIN_PE_BOX.get()); // 任意で変更可能
        }
    }
}

