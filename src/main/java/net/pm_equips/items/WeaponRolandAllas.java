package net.pm_equips.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pm_equips.ItemInit;
import net.pm_equips.PMEquipsMain;
import net.pm_equips.config.CommonConfig;

public class WeaponRolandAllas extends SwordItem {
    private static final float MIN_DAMAGE = 5.0F;
    private static final int DAMAGE_VARIANCE = 5;
    private static final double WEAKNESS_RADIUS = 3.0D;
    private static final int WEAKNESS_DURATION = 20;
    private static final int WEAKNESS_AMPLIFIER = 1;

    public WeaponRolandAllas() {
        super(new CustomTier(), 0, -2.3F, new Properties().durability(1000));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide
                && entity instanceof Player player
                && player.getMainHandItem() == stack) {
            applyWeaknessAura(level, player);
        }

        super.inventoryTick(stack, level, entity, slot, selected);
    }

    private static void applyWeaknessAura(Level level, Player player) {
        for (LivingEntity target : level.getEntitiesOfClass(
                LivingEntity.class,
                player.getBoundingBox().inflate(WEAKNESS_RADIUS),
                target -> target != player && target.isAlive()
        )) {
            if (!CommonConfig.ALLOW_FRIENDLY_FIRE.get() && target instanceof Player) {
                continue;
            }

            target.addEffect(new MobEffectInstance(
                    MobEffects.WEAKNESS,
                    WEAKNESS_DURATION,
                    WEAKNESS_AMPLIFIER,
                    false,
                    false,
                    true
            ));
        }
    }

    private static float rollDamage(Player player) {
        return MIN_DAMAGE + player.level().random.nextInt(DAMAGE_VARIANCE);
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 1000;
        }

        @Override
        public float getSpeed() {
            return 4.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0F;
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
            return Ingredient.EMPTY;
        }
    }

    @Mod.EventBusSubscriber(modid = PMEquipsMain.MOD_ID)
    public static class AllasEvents {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!(event.getSource().getEntity() instanceof Player player)) {
                return;
            }

            if (!player.getMainHandItem().is(ItemInit.FIXER_ROLAND_ALLAS.get())) {
                return;
            }

            event.setAmount(rollDamage(player));
        }
    }
}
