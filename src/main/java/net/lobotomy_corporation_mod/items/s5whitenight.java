package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.lobotomy_corporation_mod.client.renderer.s5r_whitenight;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.UUID;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = Lobotomy_corporation_mod.MOD_ID)
public class s5whitenight extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public s5whitenight(ArmorMaterial material, Type type, Properties props) {
        super(material, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private s5r_whitenight renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new s5r_whitenight();
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

    // ==== フルセットチェック（3部位） ====
    public static boolean hasFullSet(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof s5whitenight &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof s5whitenight;
    }

    // ==== イベント処理 ====

    private static final UUID HEALTH_BONUS_UUID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeee0001");
    private static final UUID RESIST_BONUS_UUID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeee0002");

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase != TickEvent.Phase.END) return;

        if (hasFullSet(player)) {
            // HP上限 +20
            applyModifier(player, Attributes.MAX_HEALTH, HEALTH_BONUS_UUID, 20.0, AttributeModifier.Operation.ADDITION);

            // ノックバック耐性 (80% = 0.8)
            applyModifier(player, Attributes.KNOCKBACK_RESISTANCE, RESIST_BONUS_UUID, 0.8, AttributeModifier.Operation.ADDITION);
        } else {
            // 外したらリセット
            removeModifier(player, Attributes.MAX_HEALTH, HEALTH_BONUS_UUID);
            removeModifier(player, Attributes.KNOCKBACK_RESISTANCE, RESIST_BONUS_UUID);
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!hasFullSet(player)) return;

        float damage = event.getAmount();

        if (damage <= 5.0f) {
            event.setCanceled(true); // ダメージ無効化
            // 吸収ハートを回復
            player.setAbsorptionAmount(player.getAbsorptionAmount() + damage);
        }
    }

    private static void applyModifier(Player player, Attribute attribute, UUID id, double value, AttributeModifier.Operation op) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance == null) return;

        if (instance.getModifier(id) == null) { // UUIDで確認
            instance.addPermanentModifier(new AttributeModifier(id, "a5_paradise_lost_bonus", value, op));
        }
    }

    private static void removeModifier(Player player, Attribute attribute, UUID id) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance != null && instance.getModifier(id) != null) {
            // === 現在HPを保存 ===
            float currentHp = player.getHealth();

            // === 修飾子削除 ===
            instance.removeModifier(id);

            // === 上限に応じて再設定 ===
            float newMax = (float) player.getAttributeValue(attribute);
            player.setHealth(Math.min(currentHp, newMax));
        }
    }
}
