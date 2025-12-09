package net.lobotomy_corporation_mod.items;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

import java.util.List;
import java.util.UUID;

public class W5Twilight extends SwordItem {

    private static final UUID REACH_UUID = UUID.fromString("e3b32f1a-6c19-4bfb-8dc1-4b1d0d77d64f");
    private static final AttributeModifier REACH_MODIFIER =
            new AttributeModifier(REACH_UUID, "twilight_reach_bonus", 5.0, AttributeModifier.Operation.ADDITION);

    public W5Twilight() {
        super(new CustomTier(), 25, -2.8f, new Properties().durability(4000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide && attacker instanceof Player player) {
            Vec3 look = player.getLookAngle();
            Vec3 origin = player.position().add(0, 1.0, 0);
            double range = 8.0;

            AABB box = new AABB(origin.add(-range, -1.5, -range), origin.add(range, 1.5, range));
            List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, box,
                    e -> e != player && e.isAlive());

            for (LivingEntity entity : entities) {
                Vec3 toTarget = entity.position().add(0, 1.0, 0).subtract(origin).normalize();
                double angle = Math.acos(look.dot(toTarget));
                if (angle < Math.toRadians(60)) {
                    entity.hurt(entity.damageSources().playerAttack(player), 26.0f); // 薙ぎ払いダメージ
                }
            }
        }

        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(attacker.getUsedItemHand()));
        return true;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (level.isClientSide || !(entity instanceof Player player)) return;

        boolean isHolding = selected && player.getMainHandItem() == stack;

        AttributeInstance reachAttr = player.getAttribute(ForgeMod.ENTITY_REACH.get());
        if (reachAttr != null) {
            if (isHolding && !reachAttr.hasModifier(REACH_MODIFIER)) {
                reachAttr.addTransientModifier(REACH_MODIFIER);
            } else if (!isHolding && reachAttr.hasModifier(REACH_MODIFIER)) {
                reachAttr.removeModifier(REACH_MODIFIER);
            }
        }

        if (isHolding) {
            addEffect(player, MobEffects.ABSORPTION, 3);
            addEffect(player, MobEffects.DAMAGE_BOOST, 3);
            addEffect(player, MobEffects.MOVEMENT_SPEED, 3);
        }
    }

    private void addEffect(Player player, MobEffect effect, int level) {
        MobEffectInstance current = player.getEffect(effect);
        if (current == null || current.getAmplifier() < level - 1) {
            player.addEffect(new MobEffectInstance(effect, 2, level - 1, true, false, false));
        }
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
