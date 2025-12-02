package net.lobotomy_corporation_mod.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class W5DaCapo extends SwordItem {

    public W5DaCapo() {
        super(new CustomTier(), 17, -2.1f, new Properties().durability(4000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide && attacker instanceof Player player) {

            // === 前方5ブロックの薙ぎ払い処理 ===
            Level level = player.level();
            Vec3 look = player.getLookAngle();
            Vec3 origin = player.position().add(0, player.getEyeHeight(), 0);

            for (int i = 1; i <= 5; i++) {
                Vec3 center = origin.add(look.scale(i));
                AABB box = new AABB(center.subtract(1, 1, 1), center.add(1, 1, 1));
                List<LivingEntity> sweepTargets = level.getEntitiesOfClass(LivingEntity.class, box,
                        e -> e != target && e != player && e.isAlive());

                for (LivingEntity e : sweepTargets) {
                    e.hurt(player.damageSources().playerAttack(player), 6.0F);
                }
            }
        }

        if (!(attacker instanceof Player player) || attacker.level().isClientSide) return false;

        Vec3 origin = player.position().add(0, 1.0, 0);
        Vec3 look = player.getLookAngle();
        double range = 5.0;

        AABB area = new AABB(origin.add(-range, -1.5, -range), origin.add(range, 1.5, range));
        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, area,
                e -> e != player && e.isAlive());

        for (LivingEntity entity : entities) {
            Vec3 toTarget = entity.position().add(0, 1.0, 0).subtract(origin).normalize();
            double angle = Math.acos(look.dot(toTarget));
            if (angle < Math.toRadians(60)) {
                for (int i = 0; i < 8; i++) { // 多段ヒット 8回
                    entity.hurt(entity.damageSources().playerAttack(player), 2.0f); // 合計ダメージ16
                }
            }
        }

        // 通常攻撃にも多段ヒット
        for (int i = 0; i < 8; i++) {
            target.hurt(target.damageSources().playerAttack(player), 3.0f); // 合計24
        }

        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(attacker.getUsedItemHand()));
        return true;
    }

    private static class CustomTier implements Tier {
        @Override public int getUses() { return 4000; }
        @Override public float getSpeed() { return 4.0f; }
        @Override public float getAttackDamageBonus() { return 0.0f; }
        @Override public int getLevel() { return 1; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    }
}
