package net.lobotomy_corporation_mod.Events;

import net.lobotomy_corporation_mod.items.W5Justitia;
import net.lobotomy_corporation_mod.items.W5Mimicry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "lobotomy_corporation_mod", value = Dist.CLIENT)
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerAttacked(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        ItemStack stack = player.getMainHandItem();

        // ego_justitia_weaponsのガード処理
        if (stack.getItem() instanceof W5Justitia &&
                player.isUsingItem() &&
                !player.getCooldowns().isOnCooldown(stack.getItem())) {

            event.setCanceled(true);

            player.level().playSound(null, player.blockPosition(), SoundEvents.SHIELD_BLOCK, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(stack.getItem(), 20 * 10);
            player.stopUsingItem();
        }

        // ego_mimicry_weaponsのガード処理
        if (stack.getItem() instanceof W5Mimicry &&
                player.isUsingItem() &&
                !player.getCooldowns().isOnCooldown(stack.getItem())) {

            event.setCanceled(true); // 完全ダメージカット
            player.level().playSound(null, player.blockPosition(), SoundEvents.ANVIL_FALL, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.getCooldowns().addCooldown(stack.getItem(), 20 * 15); // 15秒
            player.stopUsingItem();
        }
    }

    private static final String ATTACK_COUNT_TAG = "mimicry_attack_count";
    private static final String DAMAGE_ACCUM_TAG = "mimicry_damage_total";

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        DamageSource source = event.getSource();

        // 攻撃者がプレイヤーかチェック
        if (!(source.getEntity() instanceof Player player)) return;

        // メイン・オフハンドのどちらかに武器を持っているか確認
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof W5Mimicry)) {
            stack = player.getOffhandItem();
            if (!(stack.getItem() instanceof W5Mimicry)) return;
        }

        // NBT取得と初期化
        var tag = stack.getOrCreateTag();
        int count = tag.getInt(ATTACK_COUNT_TAG);
        float accum = tag.getFloat(DAMAGE_ACCUM_TAG);

        count++;
        accum += event.getAmount();

        // 5回目の攻撃ごとに25%分回復
        if (count >= 5) {
            float heal = accum * 0.25f;
            player.heal(heal);
            count = 0;
            accum = 0;

            // サウンド再生
            Level level = player.level();
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        // 更新して保存
        tag.putInt(ATTACK_COUNT_TAG, count);
        tag.putFloat(DAMAGE_ACCUM_TAG, accum);
    }
}
