package net.lobotomy_corporation_mod.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.List;

public class W5Justitia extends SwordItem {

    private static final String ATTACK_TAG = "justitia_attack_count";
    private static final String KILL_TAG = "justitia_kill_count";
    private static final String READY_TAG = "justitia_ability_ready";
    private static final String NEXT_STRIKE_TAG = "justitia_next_strike_instant";

    public W5Justitia() {
        super(new CustomTier(), 21, -2.7f, new Properties().durability(4000));
    }

    // === 攻撃ヒット処理 ===
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) return false;
        if (attacker.level().isClientSide) return false;

        CompoundTag tag = stack.getOrCreateTag();

        // 🔹 即死アビリティ処理
        if (tag.getBoolean(NEXT_STRIKE_TAG)) {
            // 対象を即死
            target.hurt(target.damageSources().magic(), Float.MAX_VALUE);

            // 範囲攻撃（半径5ブロック）
            double radius = 5.0;
            List<LivingEntity> entities = player.level().getEntitiesOfClass(
                    LivingEntity.class,
                    target.getBoundingBox().inflate(radius),
                    e -> e != player && e.isAlive()
            );
            for (LivingEntity e : entities) {
                e.hurt(e.damageSources().magic(), Float.MAX_VALUE);
            }

            // 状態リセット
            tag.putBoolean(NEXT_STRIKE_TAG, false);

            // 演出
            player.level().playSound(null, player.blockPosition(),
                    SoundEvents.ENDER_DRAGON_GROWL, SoundSource.PLAYERS, 1.5f, 1.0f);

            stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(attacker.getUsedItemHand()));
            return true;
        }

        // 🔹 薙ぎ払い処理：前方5ブロック60度
        Vec3 look = player.getLookAngle();
        Vec3 origin = player.position().add(0, 1.0, 0);
        double range = 5.0;

        AABB area = new AABB(origin.add(-range, -1.5, -range), origin.add(range, 1.5, range));
        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, area,
                e -> e != player && e.isAlive());

        for (LivingEntity entity : entities) {
            Vec3 toTarget = entity.position().add(0, 1.0, 0).subtract(origin).normalize();
            double angle = Math.acos(look.dot(toTarget));
            if (angle < Math.toRadians(60)) {
                for (int i = 0; i < 5; i++) { // 多段ヒット
                    entity.hurt(entity.damageSources().playerAttack(player), 2.0f); // 合計10
                }
            }
        }

        // 🔹 通常攻撃対象にも多段ヒット
        for (int i = 0; i < 3; i++) {
            target.hurt(target.damageSources().playerAttack(player), 4.0f); // 合計12
        }

        // 🔹 攻撃カウント（5回で重撃100ダメージ）
        int count = tag.getInt(ATTACK_TAG) + 1;
        if (count >= 5) {
            target.hurt(target.damageSources().generic(), 100.0f);
            count = 0;
        }
        tag.putInt(ATTACK_TAG, count);

        // 耐久消費
        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(attacker.getUsedItemHand()));
        return true;
    }

    // === 右クリックでガード ===
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public Multimap<net.minecraft.world.entity.ai.attributes.Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        ImmutableMultimap.Builder<net.minecraft.world.entity.ai.attributes.Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        if (slot == EquipmentSlot.MAINHAND) {
            builder.put(Attributes.ATTACK_DAMAGE,
                    new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.getDamage(), AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED,
                    new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.getTier().getAttackDamageBonus(), AttributeModifier.Operation.ADDITION));
            builder.put(ForgeMod.ENTITY_REACH.get(),
                    new AttributeModifier("Weapon Reach", 3.0, AttributeModifier.Operation.ADDITION));
        }

        return builder.build();
    }

    // === キルカウント管理 ===
    public static void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        ItemStack held = player.getMainHandItem();
        if (!(held.getItem() instanceof W5Justitia)) return;

        CompoundTag tag = held.getOrCreateTag();
        int kills = tag.getInt(KILL_TAG) + 1;

        if (kills >= 150) {
            kills = 0;
            tag.putBoolean(READY_TAG, true);
            player.displayClientMessage(Component.literal("§aJustitia: アビリティ使用可能！"), true);
        }

        tag.putInt(KILL_TAG, kills);
    }

    // === Vキーでアビリティ発動 ===
    public static void tryActivateAbility(Player player) {
        ItemStack held = player.getMainHandItem();
        if (!(held.getItem() instanceof W5Justitia)) return;

        CompoundTag tag = held.getOrCreateTag();
        if (tag.getBoolean(READY_TAG)) {
            tag.putBoolean(READY_TAG, false);
            tag.putBoolean(NEXT_STRIKE_TAG, true);
            player.displayClientMessage(Component.literal("§cJustitia: 次の攻撃が即死範囲攻撃になる！"), true);
        }
    }

    private static class CustomTier implements Tier {
        @Override public int getUses() { return 4000; }
        @Override public float getSpeed() { return -4.0f; }
        @Override public float getAttackDamageBonus() { return 0.0f; }
        @Override public int getLevel() { return 1; }
        @Override public int getEnchantmentValue() { return 0; }
        @Override public Ingredient getRepairIngredient() { return Ingredient.EMPTY; }
    }
}
