package net.pm_equips.items;

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
import net.pm_equips.client.renderer.EGOS5SmileR;
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

public class EGOS5Smile extends ArmorItem implements GeoItem {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public EGOS5Smile(ArmorMaterial material, ArmorItem.Type type, Properties props) {
        super(material, type, props);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private EGOS5SmileR renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {

                if (this.renderer == null)
                    this.renderer = new EGOS5SmileR();

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

    private static final Map<UUID, Integer> bonusHealth = new HashMap<>();
    private static final Map<UUID, Integer> bonusAttack = new HashMap<>();

    @Mod.EventBusSubscriber(modid = "pm_equips")
    public static class SmileEvents {
        @SubscribeEvent
        public static void onMobKill(LivingDeathEvent event) {
            if (!(event.getSource().getEntity() instanceof Player player)) return;
            if (!SmileFullSet(player)) return;

            UUID id = player.getUUID();

            int hpBonus = bonusHealth.getOrDefault(id, 0) + 2;
            bonusHealth.put(id, hpBonus);

            if (hpBonus % 20 == 0) {
                player.setHealth(player.getMaxHealth());
            }

            int atkBonus = (hpBonus / 40) * 5;
            bonusAttack.put(id, atkBonus);

            applyModifiers(player, hpBonus, atkBonus);
        }

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;
            Player player = event.player;
            UUID id = player.getUUID();

            if (!SmileFullSet(player)) {
                if (bonusHealth.containsKey(id) || bonusAttack.containsKey(id)) {
                    bonusHealth.remove(id);
                    bonusAttack.remove(id);
                    clearModifiers(player);
                }
                return;
            }

            int hpBonus = bonusHealth.getOrDefault(id, 0);
            int atkBonus = bonusAttack.getOrDefault(id, 0);
            applyModifiers(player, hpBonus, atkBonus);
        }

        private static boolean SmileFullSet(Player player) {
            return (player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof EGOS5Smile) &&
                    (player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof EGOS5Smile);
        }

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
