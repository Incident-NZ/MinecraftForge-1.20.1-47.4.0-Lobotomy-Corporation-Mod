package net.pm_equips.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pm_equips.BlockInit;
import net.pm_equips.ItemInit;
import net.pm_equips.PMEquipsMain;
import net.pm_equips.config.CommonConfig;

public class EGOW4CrimsonScarR extends SwordItem {
    private static final float MIN_DAMAGE = 11.0F;
    private static final int DAMAGE_VARIANCE = 3;
    private static final float LOW_HEALTH_DAMAGE_MULTIPLIER = 1.5F;

    public EGOW4CrimsonScarR() {
        super(new CustomTier(), 0, -2.4F, new Properties().durability(3000));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (!player.level().isClientSide
                && isEmpowered(player)
                && entity instanceof LivingEntity target
                && target.isAlive()
                && player.isAlliedTo(target)) {
            target.invulnerableTime = 0;
            target.hurt(player.level().damageSources().playerAttack(player), MIN_DAMAGE);
            stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            return true;
        }

        return false;
    }

    private static boolean isEmpowered(Player player) {
        return player.getOffhandItem().is(ItemInit.W4_CRIMSON_SCAR_L.get())
                && player.getHealth() <= player.getMaxHealth() * 0.5F;
    }

    private static float rollDamage(Player player) {
        float damage = MIN_DAMAGE + player.level().random.nextInt(DAMAGE_VARIANCE);
        return isEmpowered(player) ? damage * LOW_HEALTH_DAMAGE_MULTIPLIER : damage;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repair) {
        return repair.is(BlockInit.BlockItems.WAW_PE_BOX.get());
    }

    private static class CustomTier implements Tier {
        @Override
        public int getUses() {
            return 3000;
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
            return Ingredient.of(BlockInit.BlockItems.WAW_PE_BOX.get());
        }
    }

    @Mod.EventBusSubscriber(modid = PMEquipsMain.MOD_ID)
    public static class CrimsonScarEvents {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            if (!(event.getSource().getEntity() instanceof Player player)) {
                return;
            }

            if (!player.getMainHandItem().is(ItemInit.W4_CRIMSON_SCAR_R.get())) {
                return;
            }

            LivingEntity target = event.getEntity();
            boolean empowered = isEmpowered(player);

            if (!empowered
                    && !CommonConfig.ALLOW_FRIENDLY_FIRE.get()
                    && player.isAlliedTo(target)) {
                event.setCanceled(true);
                return;
            }

            event.setAmount(rollDamage(player));
            target.invulnerableTime = 0;
        }
    }
}
