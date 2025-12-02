package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.lobotomy_corporation_mod.client.renderer.s5r_star;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class s5star extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public s5star(ArmorMaterial material, Type type, Properties props) {
        super(material, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private s5r_star renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if (this.renderer == null)
                    this.renderer = new s5r_star();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this,"controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /** フルセット装備かどうかチェック */
    public static boolean hasFullSet(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof s5star &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof s5star;
    }

    // ====== パッシブ回復処理 ======
    @Mod.EventBusSubscriber(modid = Lobotomy_corporation_mod.MOD_ID)
    public static class ArmorTickHandler {
        private static final int COOLDOWN_TICKS = 200; // 10秒 = 200tick
        private static final Map<UUID, Integer> cooldowns = new HashMap<>();

        @SubscribeEvent
        public static void onLivingTick(LivingEvent.LivingTickEvent event) {
            if (!(event.getEntity() instanceof Player player)) return;
            if (player.level().isClientSide) return;

            // フルセット確認
            if (!hasFullSet(player)) return;

            UUID uuid = player.getUUID();
            int ticks = cooldowns.getOrDefault(uuid, 0);

            if (ticks <= 0) {
                // === 効果発動 ===
                // 同じチャンクにいるプレイヤー取得
                ChunkPos chunkPos = new ChunkPos(player.blockPosition());
                List<? extends Player> playersInChunk = player.level().players().stream()
                        .filter(p -> new ChunkPos(p.blockPosition()).equals(chunkPos))
                        .toList();

                for (Player target : playersInChunk) {
                    target.heal(6.0f);
                }
                player.heal(6.0f);

                // サウンドエフェクトとかもここで鳴らせる
                player.level().playSound(null, player.blockPosition(),
                        SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 0.5f, 1.0f);

                // クールダウンリセット
                cooldowns.put(uuid, COOLDOWN_TICKS);
            } else {
                cooldowns.put(uuid, ticks - 1);
            }
        }
    }
}

