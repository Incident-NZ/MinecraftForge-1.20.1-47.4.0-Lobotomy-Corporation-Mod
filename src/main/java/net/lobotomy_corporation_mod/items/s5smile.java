package net.lobotomy_corporation_mod.items;

import net.lobotomy_corporation_mod.Lobotomy_corporation_mod;
import net.lobotomy_corporation_mod.client.renderer.s5r_smile;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class s5smile extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final Map<UUID, Integer> bonusHealth = new HashMap<>();
    private static final Map<UUID, Integer> bonusAttack = new HashMap<>();


    public s5smile(ArmorMaterial material, Type type, Properties props) {
        super(material, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private s5r_smile renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if (this.renderer == null)
                    this.renderer = new s5r_smile();

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

    /** フルセット装備チェック */
    public static boolean hasFullSet(Player player) {
        return player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof s5smile &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof s5smile;
    }

    // ===== イベントハンドラ =====
    @Mod.EventBusSubscriber(modid = Lobotomy_corporation_mod.MOD_ID)
    public static class Events {
        /** モブを倒した時にボーナスを付与 */
        @SubscribeEvent
        public static void onMobKill(LivingDeathEvent event) {
            if (!(event.getSource().getEntity() instanceof Player player)) return;
            if (!hasFullSet(player)) return;

            UUID id = player.getUUID();

            // HPボーナスを+2
            int hpBonus = bonusHealth.getOrDefault(id, 0) + 2;
            bonusHealth.put(id, hpBonus);

            // HP上限20ごとに全回復
            if (hpBonus % 20 == 0) {
                player.setHealth(player.getMaxHealth());
            }

            // HP上限40ごとに攻撃力+5
            int atkBonus = (hpBonus / 40) * 5;
            bonusAttack.put(id, atkBonus);

            applyModifiers(player, hpBonus, atkBonus);
        }

        /** 毎tickチェックしてアーマー外したらリセット */
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            Player player = event.player;
            UUID id = player.getUUID();

            if (!hasFullSet(player)) {
                // 外したらリセット
                if (bonusHealth.containsKey(id) || bonusAttack.containsKey(id)) {
                    bonusHealth.remove(id);
                    bonusAttack.remove(id);
                    clearModifiers(player);
                }
            }
        }

        // === Attribute操作 ===
        private static final UUID HEALTH_MODIFIER_UUID = UUID.fromString("11111111-1111-1111-1111-111111111111");
        private static final UUID ATTACK_MODIFIER_UUID = UUID.fromString("22222222-2222-2222-2222-222222222222");

        private static void applyModifiers(Player player, int hpBonus, int atkBonus) {
            AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance attack = player.getAttribute(Attributes.ATTACK_DAMAGE);

            if (maxHealth != null) {
                maxHealth.removeModifier(HEALTH_MODIFIER_UUID);
                maxHealth.addPermanentModifier(new AttributeModifier(HEALTH_MODIFIER_UUID, "Smile armor bonus HP", hpBonus, AttributeModifier.Operation.ADDITION));
            }

            if (attack != null) {
                attack.removeModifier(ATTACK_MODIFIER_UUID);
                attack.addPermanentModifier(new AttributeModifier(ATTACK_MODIFIER_UUID, "Smile armor bonus ATK", atkBonus, AttributeModifier.Operation.ADDITION));
            }
        }

        private static void clearModifiers(Player player) {
            AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance attack = player.getAttribute(Attributes.ATTACK_DAMAGE);

            if (maxHealth != null) {
                maxHealth.removeModifier(HEALTH_MODIFIER_UUID);
            }
            if (attack != null) {
                attack.removeModifier(ATTACK_MODIFIER_UUID);
            }
        }
    }
}